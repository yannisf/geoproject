package gr.fraglab.geoproject.web;

import gr.fraglab.geoproject.service.Importer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AppController {

    @Autowired
    private Importer importer;

    @RequestMapping("/import")
    public String importGeo(@RequestParam(name = "country") String countryCode) throws IOException {
        Integer numberOfRecords = importer.importGeo(countryCode);
        return String.format("%d records imported", numberOfRecords);
    }
}