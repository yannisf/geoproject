package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GeoEntryService {

    public static final String FEATURE_ADM1 = "ADM1";
    public static final String FEATURE_ADM2 = "ADM2";
    public static final String FEATURE_ADM3 = "ADM3";

    @Autowired
    private GeoEntryRepository geoEntryRepository;

    public Collection<GeoEntryRepository.GeonameidOnly> getPopulatedPlaces(String countryCode) {
        return geoEntryRepository.findByCountryCodeIgnoreCaseAndFeatureClassAndPopulationNot(countryCode, "P", "0");
    }

    public GeoEntry getGeoEntry(String geonameId) {
        return geoEntryRepository.findById(geonameId).orElseThrow();
    }

    public GeoEntry getAdmin1(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                FEATURE_ADM1);
    }

    public GeoEntry getAdmin2(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndAdmin2CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                geoEntry.getAdmin2Code(),
                FEATURE_ADM2);

    }

    public GeoEntry getAdmin3(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndAdmin2CodeAndAdmin3CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                geoEntry.getAdmin2Code(),
                geoEntry.getAdmin3Code(),
                FEATURE_ADM3);
    }

}
