package gr.fraglab.geoproject.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.fraglab.geoproject.persistence.GeoEntry;
import gr.fraglab.geoproject.vo.LineRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
public class GeoTopicConsumer {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ObjectMapper mapper;

    @Transactional
    @KafkaListener(topics = "my_topic")
    public void listen(ConsumerRecord<String, String> cr) throws IOException {
        LineRecord lineRecord = mapper.readValue(cr.value(), LineRecord.class);
        GeoEntry geoEntry = GeoEntry.from(lineRecord);
        em.persist(geoEntry);
    }

}
