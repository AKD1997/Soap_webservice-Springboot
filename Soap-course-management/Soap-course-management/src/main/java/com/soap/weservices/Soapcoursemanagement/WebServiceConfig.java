package com.soap.weservices.Soapcoursemanagement;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring web service
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig {
	// Message Dispatcher
	// Application context
	// Url - /ws/*

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
		// Until now we are created a bean so as soon as we send a request to /ws/*
		// It would handle the request and it will start processing the request that's
		// what we have done until now
	}

	/// ws/courses.wsdl
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition  defination = new DefaultWsdl11Definition();
		// portType -Course port
		defination.setPortTypeName("CoursePort");
		// NameSpace - http://soapwebservice.com/courses
		defination.setTargetNamespace("http://soapwebservice.com/courses");
		// /Ws
		defination.setLocationUri("/ws");
		// Schema
		defination.setSchema(coursesSchema);
		return defination;
	}

	// Define a Schema
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Course-details.xsd"));
	}
}
