package gr.fraglab.geoproject.vo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class CountryImporter {

    private static final String COUNTRY_GR_TXT = "/country/GR.txt";

    public static void main(String[] args) {
        List<LineRecord> lineRecords = importCountry();
        System.out.println(lineRecords);
    }

    public static List<LineRecord> importCountry() {
        try (InputStream inputStream = CountryImporter.class.getResourceAsStream(COUNTRY_GR_TXT);
             Scanner scanner = new Scanner(inputStream).useDelimiter("\n|\r\n");) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            return StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(LineRecord::of)
                    .collect(toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
