package com.baedal.storeService;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreServiceApplication.class, args);
	}

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
