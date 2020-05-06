package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM,customFaultCode = "{http://soapwebservice.com/courses} 500 Corse Not found for this Search")
public class CourseNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CourseNotfoundException(String message) {
		super(message);
	}

}
