package com.github.kdvolder.fortune.service.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

import com.github.kdvolder.fortune.service.FortuneConfig;

@Configuration
public class InitializeFortunes {

	private static final Logger log = LoggerFactory.getLogger(InitializeFortunes.class);
	
	private ApplicationContext ac;

	@Autowired
	private FortuneRepository fortunes;
	
	@Autowired
	FortuneConfig conf;
	
	@EventListener(classes= {ContextStartedEvent.class, ContextRefreshedEvent.class})
	public void contextStarted() {
		log.info("Checking fortunes in database");
		if (fortunes.count()==0) {
			log.info("No fortunes in database, creating some...");
			List<FortuneMessage> defaultFortunes = Stream.of(conf.getMessages())
					.map(s -> {
						FortuneMessage m = new FortuneMessage();
						m.setMessage(s);
						return m;
					})
					.collect(Collectors.toList());
			fortunes.saveAll(defaultFortunes);
		}
	}
	
}
