package com.soap.weservices.Soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.in28minutes.soap.webservices.soapcoursemanagement.soap"})
public class SoapCourseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapCourseManagementApplication.class, args);
	}
}