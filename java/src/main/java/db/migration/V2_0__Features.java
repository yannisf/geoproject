package db.migration;

import gr.fraglab.geoproject.persistence.Feature;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class V2_0__Features implements SpringJdbcMigration {

    public static final String GEODATA_FEATURE_CODES = "/geodata/featureCodes_en.txt";
    public static final String INSERT_FEATURE_QUERY = "INSERT INTO feature(id, category, code, name, description) VALUES(?, ?, ?, ?, ?)";

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        try (InputStream inputStream = V2_0__Features.class.getResourceAsStream(GEODATA_FEATURE_CODES);
             Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\n|\r\n")) {
            Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(scanner, Spliterator.ORDERED);
            StreamSupport.stream(spliterator, false)
                    .map(l -> l.split("\t"))
                    .map(Feature::of)
                    .forEach(f -> jdbcTemplate.update(INSERT_FEATURE_QUERY,
                            f.getId(), f.getCategory(), f.getCode(), f.getName(), f.getDescription()));
        }
    }
}
