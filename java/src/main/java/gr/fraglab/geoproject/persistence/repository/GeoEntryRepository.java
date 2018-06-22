package gr.fraglab.geoproject.persistence.repository;

import gr.fraglab.geoproject.persistence.GeoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GeoEntryRepository extends JpaRepository<GeoEntry, String> {


    List<GeoEntry> findByCountryCodeIgnoreCaseAndFeatureClassAndPopulationNot(String countryCode, String featureClass, String population);

    GeoEntry findByAdmin1CodeAndFeatureCode(String admin1code,
                                            String featureCode);

    GeoEntry findByAdmin1CodeAndAdmin2CodeAndFeatureCode(String admin1code,
                                                         String admin2code,
                                                         String featureCode);

    GeoEntry findByAdmin1CodeAndAdmin2CodeAndAdmin3CodeAndFeatureCode(String admin1code,
                                                                      String admin2code,
                                                                      String admin3code,
                                                                      String featureCode);

}
