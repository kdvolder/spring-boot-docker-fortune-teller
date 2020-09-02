package com.github.kdvolder.fortune.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FortuneEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortuneEurekaApplication.class, args);
	}

}
