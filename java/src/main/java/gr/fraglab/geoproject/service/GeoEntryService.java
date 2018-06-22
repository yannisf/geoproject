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
        return geoEntryRepository.getPopulatedPlaces(countryCode);
    }

    public GeoEntry getAdmin1(String geonameId) {
        return geoEntryRepository.getAdmin1(geonameId);
    }

    public GeoEntry getAdmin2(String geonameId) {
        return geoEntryRepository.getAdmin2(geonameId);
    }

    public GeoEntry getAdmin3(String geonameId) {
        return geoEntryRepository.getAdmin3(geonameId);
    }

    public AdminVo getAllAdmin(String geonameId) {
        List<GeoEntry> admins = geoEntryRepository.getAllAdmin(geonameId);
        return new AdminVo(admins.get(0).getAsciiName(), admins.get(1).getAsciiName(), admins.get(2).getAsciiName());
    }

}
