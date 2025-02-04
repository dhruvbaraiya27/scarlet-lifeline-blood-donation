package edu.neu.csye6200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ScarletLifelineApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScarletLifelineApplication.class, args);
	}
}
