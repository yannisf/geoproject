package gr.fraglab.geoproject.service;

import gr.fraglab.geoproject.elasticsearch.IndexRepository;
import gr.fraglab.geoproject.persistence.GeoEntry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class IndexService {

    @Value("${topic.updateGeoEntry}")
    private String updateGeoEntryTopic;

    @Autowired
    private GeoEntryService geoEntryService;

    @Autowired
    private IndexRepository indexRepository;

    public void indexGeonameId(String geonameId) {
        GeoEntry geoEntry = geoEntryService.findGeoEntry(geonameId);

        gr.fraglab.geoproject.elasticsearch.GeoEntry elasticEntry = new gr.fraglab.geoproject.elasticsearch.GeoEntry();
        elasticEntry.setId(geoEntry.getGeonameid());
        elasticEntry.setName(geoEntry.getAsciiName());

        indexRepository.save(elasticEntry);
    }

    @KafkaListener(topics = "${topic.updateGeoEntry}")
    @Transactional
    public void listen(ConsumerRecord<String, String> geonameIdConsumerRecord) {
        String geonameId = geonameIdConsumerRecord.value();
        indexGeonameId(geonameId);
    }

}
