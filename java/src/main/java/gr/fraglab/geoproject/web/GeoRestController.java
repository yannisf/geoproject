package gr.fraglab.geoproject.web;

import gr.fraglab.geoproject.service.CountryExporter;
import gr.fraglab.geoproject.service.ImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeoRestController {

    @Autowired
    private ImporterService importerService;

    @Autowired
    private CountryExporter exporter;

    @RequestMapping("/import")
    public String importGeo(@RequestParam(name = "country") String countryCode) throws IOException {
        Integer numberOfRecords = importerService.importGeo(countryCode);
        return String.format("%d records imported", numberOfRecords);
    }

    @RequestMapping("/kafka")
    public void kafka(@RequestParam(name = "country") String countryCode) throws IOException {
        exporter.sendJsonMessages(countryCode);
    }

}