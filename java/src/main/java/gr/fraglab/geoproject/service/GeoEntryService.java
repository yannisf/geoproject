package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import gr.fraglab.geoproject.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoEntryService {

    @Autowired
    private GeoEntryRepository geoEntryRepository;

    public List<GeoEntry> getPopulatedPlaces(String countryCode) {
        return geoEntryRepository.findByCountryCodeIgnoreCaseAndFeatureClassAndPopulationNot(countryCode, "P", "0");
    }

    public GeoEntry getAdmin1(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                "ADM1");
    }

    public GeoEntry getAdmin2(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndAdmin2CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                geoEntry.getAdmin2Code(),
                "ADM2");

    }

    public GeoEntry getAdmin3(String geonameId) {
        GeoEntry geoEntry = geoEntryRepository.findById(geonameId).orElseThrow();
        return geoEntryRepository.findByAdmin1CodeAndAdmin2CodeAndAdmin3CodeAndFeatureCode(
                geoEntry.getAdmin1Code(),
                geoEntry.getAdmin2Code(),
                geoEntry.getAdmin3Code(),
                "ADM3");
    }

    public AdminVo getAllAdmin(String geonameId) {
//        List<GeoEntry> admins = geoEntryRepository.getAllAdmin(geonameId);
//        return new AdminVo(admins.get(0).getAsciiName(), admins.get(1).getAsciiName(), admins.get(2).getAsciiName());
        return null;
    }

}
