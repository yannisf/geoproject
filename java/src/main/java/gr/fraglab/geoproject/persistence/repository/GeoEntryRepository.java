package gr.fraglab.geoproject.persistence.repository;

import gr.fraglab.geoproject.persistence.GeoEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GeoEntryRepository extends JpaRepository<GeoEntry, String> {
}
