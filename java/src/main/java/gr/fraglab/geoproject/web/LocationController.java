package gr.fraglab.geoproject.web;

import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.service.GeoEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/populated-places")
public class LocationController {

    @Autowired
    private GeoEntryService geoEntryService;

    @RequestMapping("/admin1")
    public GeoEntry getAdmin1(@RequestParam(name = "geonameId") String geonameId) {
        return geoEntryService.getAdmin1(geonameId);
    }

    @RequestMapping("/admin2")
    public GeoEntry getAdmin2(@RequestParam(name = "geonameId") String geonameId) {
        return geoEntryService.getAdmin2(geonameId);
    }

    @RequestMapping("/admin3")
    public GeoEntry getAdmin3(@RequestParam(name = "geonameId") String geonameId) {
        return geoEntryService.getAdmin3(geonameId);
    }

}
