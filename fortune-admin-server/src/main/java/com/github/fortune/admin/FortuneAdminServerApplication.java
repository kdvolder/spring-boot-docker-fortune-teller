package com.github.fortune.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class FortuneAdminServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FortuneAdminServerApplication.class, args);
	}

//	 @Bean
//	    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
//	        ThreadPoolTaskScheduler threadPoolTaskScheduler
//	          = new ThreadPoolTaskScheduler();
//	        threadPoolTaskScheduler.setPoolSize(5);
//	        threadPoolTaskScheduler.setThreadNamePrefix(
//	          "ThreadPoolTaskScheduler");
//	        return threadPoolTaskScheduler;
//	    }
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(scheduler);
//	}

//    @Configuration
//    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll()  
//                .and().csrf().disable();
//        }
//    }
}
