package com.github.kdvolder.fortune.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fortunes")
public class FortuneConfig {
	
	private String[] messages;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

}
