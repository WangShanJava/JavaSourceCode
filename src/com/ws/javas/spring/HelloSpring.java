package com.ws.javas.spring;

public class HelloSpring {
	private String message;

	public void getMessage() {
		//return message;
		System.out.println("Your message : " + message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
