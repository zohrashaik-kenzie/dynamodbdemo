package com.spring.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class DynamodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamodbApplication.class, args);
	}

}
