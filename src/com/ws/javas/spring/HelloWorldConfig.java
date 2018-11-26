package com.ws.javas.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {
	@Bean
	public HelloSpring helloSpring() {
		return new HelloSpring();
	}
	
}
