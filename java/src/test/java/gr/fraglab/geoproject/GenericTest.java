package gr.fraglab.geoproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.fraglab.geoproject.vo.LineRecord;
import org.junit.Test;

import java.io.IOException;

public class GenericTest {

    @Test
    public void test() throws IOException {
        String value = "{\"geonameid\":\"251156\",\"name\":\"Fratzeskianá Metóchia\",\"asciiName\":\"Fratzeskiana Metochia\",\"alternateNames\":[{\"name\":\"Metókhia Fratzeskianá\",\"script\":\"LATIN\"},{\"name\":\"Frantzeskiana Metochia\",\"script\":\"LATIN\"},{\"name\":\"Frantzeskiana Metokhia\",\"script\":\"LATIN\"},{\"name\":\"Metokhia Fratzeskiana\",\"script\":\"LATIN\"},{\"name\":\"Φραντζεσκιανά Μετόχια\",\"script\":\"GREEK\"},{\"name\":\"Frantzeskianá Metókhia\",\"script\":\"LATIN\"}],\"latitude\":\"35.32836\",\"longitude\":\"24.40793\",\"featureClass\":\"P\",\"featureCode\":\"PPL\",\"countryCode\":\"GR\",\"cc2\":null,\"admin1Code\":\"ESYE43\",\"admin2Code\":\"44\",\"admin3Code\":\"9318\",\"admin4Code\":null,\"population\":\"250\",\"elevation\":null,\"dem\":\"207\",\"timezone\":\"Europe/Athens\",\"modificationDate\":\"2015-11-27\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        LineRecord lineRecord = objectMapper.readValue(value, LineRecord.class);
        System.out.println(lineRecord);
    }
}
