package gr.fraglab.geoproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@Configuration
@EnableAsync
public class GeoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeoProjectApplication.class, args);
    }

    @Bean()
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(name = "geo-executor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
//        Memory danger
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(1000);
        return executor;
    }

}
