package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.elasticsearch.IndexRepository;
import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IndexService {

    @Autowired
    private GeoEntryService geoEntryService;

    @Autowired
    private IndexRepository indexRepository;

    public void indexGeoEntry(GeoEntry geoEntry) {
        String adm1 = geoEntryService.getAdmin1(geoEntry.getAdmin1Code()).getAsciiName();
        String adm2 = geoEntryService.getAdmin2(geoEntry.getAdmin2Code()).getAsciiName();
        String adm3 = geoEntryService.getAdmin3(geoEntry.getAdmin3Code()).getAsciiName();

        gr.fraglab.geoproject.elasticsearch.GeoEntry elasticEntry = new gr.fraglab.geoproject.elasticsearch.GeoEntry();
        elasticEntry.setId(geoEntry.getGeonameid());
        elasticEntry.setName(geoEntry.getAsciiName());
        elasticEntry.setAdm1(adm1);
        elasticEntry.setAdm2(adm2);
        elasticEntry.setAdm3(adm3);

        indexRepository.save(elasticEntry);
    }

    public void indexGeonameId(String geonameId) {
        GeoEntry geoEntry = geoEntryService.getGeoEntry(geonameId);
        String adm1 = "N/A";
        GeoEntry geoEntryServiceAdmin1 = geoEntryService.getAdmin1(geonameId);
        if (geoEntryServiceAdmin1 != null) {
            adm1 = geoEntryServiceAdmin1.getAsciiName();
        }

        String adm2 = "N/A";
        GeoEntry geoEntryServiceAdmin2 = geoEntryService.getAdmin2(geonameId);
        if (geoEntryServiceAdmin2 != null) {
            adm2 = geoEntryServiceAdmin2.getAsciiName();
        }

        String adm3 = "N/A";
        GeoEntry geoEntryServiceAdmin3 = geoEntryService.getAdmin3(geonameId);
        if (geoEntryServiceAdmin3 != null) {
            adm3 = geoEntryServiceAdmin3.getAsciiName();
        }

        gr.fraglab.geoproject.elasticsearch.GeoEntry elasticEntry = new gr.fraglab.geoproject.elasticsearch.GeoEntry();
        elasticEntry.setId(geoEntry.getGeonameid());
        elasticEntry.setName(geoEntry.getAsciiName());
        elasticEntry.setAdm1(adm1);
        elasticEntry.setAdm2(adm2);
        elasticEntry.setAdm3(adm3);

        indexRepository.save(elasticEntry);
    }

    public void indexPopulatedPlaces(String countryCode) {
        Collection<GeoEntryRepository.GeonameidOnly> populatedPlaces = geoEntryService.getPopulatedPlaces(countryCode);
        populatedPlaces.stream()
                .map(GeoEntryRepository.GeonameidOnly::getGeonameid)
                .forEach(this::indexGeonameId);
    }

}
