package dev.tobiadegbuji.artistpersistenceservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate retrieveRestTemplate(){
        return new RestTemplate();
    }

}
