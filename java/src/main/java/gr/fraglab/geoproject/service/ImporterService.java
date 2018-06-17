package gr.fraglab.geoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import gr.fraglab.geoproject.vo.LineRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImporterService {

    @Autowired
    private CountryExporter countryExporter;

    @Autowired
    private GeoEntryRepository geoEntryRepository;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext context;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void importGeoSync(String countryCode) throws IOException {
        countryExporter.getLineRecords(countryCode).stream()
                .map(GeoEntry::from)
                .forEach(geoEntryRepository::save);
    }

    public void importGeoAsync(String countryCode) throws IOException {
        countryExporter.getLineRecords(countryCode).stream()
                .map(GeoEntry::from)
                .map(this::asJson)
                .forEach(g -> template.send("my_topic", g));
    }


    @KafkaListener(topics = "my_topic")
    public void listen(ConsumerRecord<String, String> geoEntryConsumerRecord) throws IOException {
        ImporterService importerService = (ImporterService) context.getBean("importerService");
        importerService.transformAndPersist(geoEntryConsumerRecord.value());
    }

    @Async("geo-executor")
    @Transactional
    public void transformAndPersist(String value) throws IOException {
        System.out.println(Thread.currentThread().getName());
        GeoEntry geoEntry = objectMapper.readValue(value, GeoEntry.class);
//        geoEntryRepository.save(geoEntry);
        em.persist(geoEntry);
    }

    private String asJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
