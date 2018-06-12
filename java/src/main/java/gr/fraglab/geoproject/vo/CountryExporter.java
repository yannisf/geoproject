package gr.fraglab.geoproject.vo;

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

    private static final String COUNTRY_URL = "http://download.geonames.org/export/dump";

    public List<LineRecord> export(String countryCode) throws IOException {
        URL url = new URL(String.format("%s/%s.zip", COUNTRY_URL, countryCode));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                 ZipInputStream zis = new ZipInputStream(inputStream);) {
                ZipEntry ze = zis.getNextEntry();
                while (!ze.getName().equals(String.format("%s.txt", countryCode))) {
                    ze = zis.getNextEntry();
                }
                return extractEntries(zis);
            }
        }
        return Collections.emptyList();
    }

    private List<LineRecord> extractEntries(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\n|\r\n");) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            return StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(LineRecord::of)
                    .collect(toList());
        }
    }

}
