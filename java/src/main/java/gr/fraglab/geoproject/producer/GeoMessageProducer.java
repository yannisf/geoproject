package gr.fraglab.geoproject.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GeoMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> template;


    public void send(String message) {
        template.send("my_topic", message);
    }

}
