package dev.tobiadegbuji.artistpersistenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ArtistPersistenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtistPersistenceServiceApplication.class, args);
    }

}
