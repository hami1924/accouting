package com.vista.accouting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AccoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccoutingApplication.class, args);
    }

}
