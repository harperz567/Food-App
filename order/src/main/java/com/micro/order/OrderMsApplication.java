package com.micro.order;

import com.mongodb.client.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMsApplication.class, args);
	}

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){return new RestTemplate();}

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "orderdb");
    }
}
