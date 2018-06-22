package gr.fraglab.geoproject.persistence.repository;

import gr.fraglab.geoproject.persistence.GeoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GeoEntryRepository extends JpaRepository<GeoEntry, String> {
    List<GeoEntry> getAllAdmin(String geonameId);

    GeoEntry getAdmin3(String geonameId);

    GeoEntry getAdmin2(String geonameId);

    GeoEntry getAdmin1(String geonameId);

    List<GeoEntry> getPopulatedPlaces(String countryCode);
}
