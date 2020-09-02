package com.github.kdvolder.fortune.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(FortuneConfig.class)
public class FortuneServiceApplication {

	@Autowired
	FortuneConfig conf;

	@Autowired
	private DiscoveryClient eureka;

	Random rnd = new Random();
	
    @LoadBalanced @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@GetMapping(value = "/")
	public String fortune() {
		String[] fortunes = conf.getMessages();
		return fortunes[rnd.nextInt(fortunes.length)];
	}

	@GetMapping("/service-instances/{appName}")
	public List<ServiceInstance> serviceInstances(@PathVariable String appName) {
		return eureka.getInstances(appName);
	}
	
	@GetMapping("/test")
	public String selfTest() {
		String message = restTemplate().getForObject("http://fortune-service/", String.class);
		return "Got message: "+message;
	}


	public static void main(String[] args) {
		SpringApplication.run(FortuneServiceApplication.class, args);
	}

}
