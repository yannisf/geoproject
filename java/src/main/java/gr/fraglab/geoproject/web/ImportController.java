package gr.fraglab.geoproject.web;

import gr.fraglab.geoproject.service.ImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImporterService importerService;

    @RequestMapping("/sync")
    public void importGeoSync(@RequestParam(name = "country") String countryCode) throws IOException {
        importerService.importGeoSync(countryCode);
    }

    @RequestMapping("/async")
    public void importGeoAsync(@RequestParam(name = "country") String countryCode) throws IOException {
        importerService.importGeoAsync(countryCode);
    }

}