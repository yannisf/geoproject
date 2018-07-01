package gr.fraglab.geoproject.web;

import gr.fraglab.geoproject.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index/{geonameId}")
    public void index(@PathVariable String geonameId) {
        indexService.indexGeonameId(geonameId);
    }

    @RequestMapping("/{country}/index")
    public void indexCoindexPopulatedPlaces(@PathVariable String country) {
        indexService.indexPopulatedPlaces(country);
    }

}
