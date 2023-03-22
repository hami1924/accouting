package com.vista.accouting;

import com.vista.accouting.exceptions.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@Import(GlobalExceptionHandler.class)
public class AccoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccoutingApplication.class, args);
    }

}
