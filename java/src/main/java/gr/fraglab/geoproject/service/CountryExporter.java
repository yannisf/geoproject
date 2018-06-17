package gr.fraglab.geoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.fraglab.geoproject.producer.GeoMessageProducer;
import gr.fraglab.geoproject.vo.LineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.stream.Collectors.toList;

@Component
public class CountryExporter {

    private static final String COUNTRY_URL = "http://download.geonames.org/export/dump";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GeoMessageProducer producer;

    public List<LineRecord> getLineRecords(String countryCode) throws IOException {
        return processGeoFileResource(this::toLineRecords, countryCode);
    }

    public void sendJsonMessages(String countryCode) throws IOException {
        processGeoFileResource(this::toJsonMessages, countryCode);
    }

    private <T> T processGeoFileResource(Function<InputStream, Optional<T>> processor, String countryCode) throws IOException {
        T result = null;
        URL url = new URL(String.format("%s/%s.zip", COUNTRY_URL, countryCode));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                 ZipInputStream zis = new ZipInputStream(inputStream)) {
                ZipEntry ze = zis.getNextEntry();
                while (!ze.getName().equals(String.format("%s.txt", countryCode))) {
                    ze = zis.getNextEntry();
                }
                result = processor.apply(zis).orElse(null);
            }
        }
        return result;

    }

    private Optional<List<LineRecord>> toLineRecords(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\n|\r\n")) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            return Optional.of(StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(LineRecord::of)
                    .collect(toList()));
        }
    }

    private Optional<Void> toJsonMessages(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\n|\r\n")) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(LineRecord::of)
                    .map(this::getJson)
                    .forEach(producer::send);
        }

        return Optional.empty();
    }

    private String getJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
