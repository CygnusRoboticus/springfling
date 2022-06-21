package com.example.springfling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringFlingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFlingApplication.class, args);
    }
}
