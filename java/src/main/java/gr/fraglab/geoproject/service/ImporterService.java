package gr.fraglab.geoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.persistence.repository.GeoEntryRepository;
import gr.fraglab.geoproject.vo.LineRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImporterService {

    private static final Logger LOG = LoggerFactory.getLogger(ImporterService.class);

    @Value("${topic.importGeoEntry}")
    private String importGeoEntryTopic;

    @Value("${topic.updateGeoEntry}")
    private String updateGeoEntryTopic;

    @Autowired
    private CountryExporter countryExporter;

    @Autowired
    private GeoEntryRepository geoEntryRepository;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Autowired
    private ObjectMapper objectMapper;

    public void importGeoNames(String countryCode) throws IOException {
        countryExporter.getLineRecords(countryCode).stream()
                .map(this::asJson)
                .forEach(l -> template.send(importGeoEntryTopic, l));
    }

    @KafkaListener(topics = "${topic.importGeoEntry}")
    @Transactional
    public void listen(ConsumerRecord<String, String> geoEntryConsumerRecord) throws IOException {
        LineRecord lineRecord = objectMapper.readValue(geoEntryConsumerRecord.value(), LineRecord.class);
        GeoEntry geoEntry = GeoEntry.from(lineRecord);
        LOG.debug("Saving GeoEntry[{}]", geoEntry.getGeonameid());
        geoEntryRepository.save(geoEntry);
        template.send(updateGeoEntryTopic, geoEntry.getGeonameid());
    }

    private String asJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
