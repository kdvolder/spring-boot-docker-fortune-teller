package com.github.kdvolder.fortune.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogPing {

	
	private static final Logger log = LoggerFactory.getLogger(LogPing.class);

	
	@Scheduled(fixedRate = 2000) 
	void ping() {
		log.debug("Ping!");
	}
	
}
