package com.github.kdvolder.fortune.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.github.kdvolder.fortune.service.data.FortuneMessage;
import com.github.kdvolder.fortune.service.data.FortuneRepository;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(FortuneConfig.class)
@EnableScheduling
public class FortuneServiceApplication {

	@Autowired
	FortuneRepository fortunes;

	Random rnd = new Random();
	
    @LoadBalanced @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@GetMapping(value = "/")
	public String random() {
		//TODO: more efficient way to select a random element?
		List<FortuneMessage> all = new ArrayList<>();
		for (FortuneMessage m : fortunes.findAll()) {
			all.add(m);
		}
		return all.get(rnd.nextInt(all.size())).getMessage();
	}

	public static void main(String[] args) {
		SpringApplication.run(FortuneServiceApplication.class, args);
	}

	@PostMapping(value = "/fortune")
	public FortuneMessage create(@RequestBody String message) {
		FortuneMessage msg = new FortuneMessage();
		msg.setMessage(message);
		return fortunes.save(msg);
	}

	
	@GetMapping(value = "/fortune/{id}")
	public FortuneMessage getById(@PathVariable long id) {
		Optional<FortuneMessage> found = fortunes.findById(id);
		if (found.isPresent()) {
			return found.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fortune with that id");
	}

}
