package com.github.kdvolder.fortune.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@Controller
@EnableZuulProxy
public class FortuneUiApplication {
	
	@Bean @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(FortuneUiApplication.class, args);
	}
	
	@GetMapping(value = "/")
	public String home(Model model) {
		String message = restTemplate().getForObject("http://fortune-service/", String.class);
		model.addAttribute("message", message);
		return "home";
	}
}
