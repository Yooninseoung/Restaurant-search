package com.restaurant.Restaurant_search;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableBatchProcessing // 배치 사용을 위한 선언
@SpringBootApplication
public class RestaurantSearchApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantSearchApplication.class, args);
	}

}
