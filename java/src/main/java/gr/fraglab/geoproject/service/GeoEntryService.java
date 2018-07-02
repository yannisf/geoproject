package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoEntryService {

    @Autowired
    private GeoEntryRepository geoEntryRepository;

    public GeoEntry findGeoEntry(String geonameId) {
        return geoEntryRepository.findById(geonameId).orElseThrow();
    }

}
