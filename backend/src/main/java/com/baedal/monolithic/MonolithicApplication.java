package com.baedal.monolithic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.baedal.monolithic.domain"})
public class MonolithicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithicApplication.class, args);
	}

}
