package com.example.beautysalon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
public class BeautysalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautysalonApplication.class, args);
	}

}
