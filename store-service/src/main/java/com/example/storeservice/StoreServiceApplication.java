package com.example.storeservice;

import com.example.shared.message.MessageSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.example.shared.*", "com.example.storeservice.*"})
@EntityScan(value = {"com.example.shared.entity", "com.example.storeservice.entity"})
@EnableJpaRepositories(value = {"com.example.shared.repository", "com.example.storeservice.repository"})
@Import({MessageSourceConfig.class})
public class StoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreServiceApplication.class, args);
	}

}
