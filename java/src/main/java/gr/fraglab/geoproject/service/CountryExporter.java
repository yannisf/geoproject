package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.vo.LineRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.stream.Collectors.toList;

@Component
public class CountryExporter {

    //    private static final String RESOURCE_URL_FORMAT = "http://download.geonames.org/export/dump";
    private static final String RESOURCE_URL_FORMAT = "http://localhost:8000/%s.zip";

    List<LineRecord> getLineRecords(String countryCode) throws IOException {
        List<LineRecord> result = Collections.emptyList();
        URL url = new URL(String.format(RESOURCE_URL_FORMAT, countryCode));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                 ZipInputStream zis = new ZipInputStream(inputStream)) {
                ZipEntry ze = zis.getNextEntry();
                while (!ze.getName().equals(String.format("%s.txt", countryCode))) {
                    ze = zis.getNextEntry();
                }
                result = toLineRecords(zis);
            }
        }
        return result;

    }

    private List<LineRecord> toLineRecords(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\n|\r\n")) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            return StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(LineRecord::of)
                    .collect(toList());
        }
    }

}
