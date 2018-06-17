package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.vo.LineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class ImporterService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CountryExporter countryExporter;

    @Transactional
    public Integer importGeo(String countryCode) throws IOException {
        List<LineRecord> lineRecords = countryExporter.getLineRecords(countryCode);
        lineRecords.stream()
                .map(GeoEntry::from)
                .forEach(e -> {
                    em.persist(e);
                });
        return lineRecords.size();
    }

}
