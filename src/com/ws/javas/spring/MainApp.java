package com.ws.javas.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class MainApp {
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		//HelloSpring obj = (HelloSpring) context.getBean("hellospring");
//		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));
//		HelloSpring obj = (HelloSpring) xmlBeanFactory.getBean("hellospring");
//		obj.getMessage();
		
		ApplicationContext ctx = 
				   new AnnotationConfigApplicationContext(HelloWorldConfig.class); 
		HelloSpring helloSpring = ctx.getBean(HelloSpring.class);
		helloSpring.setMessage("Hello World!");
		helloSpring.getMessage();
	}
}
