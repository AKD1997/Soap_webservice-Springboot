package com.soap.weservices.Soapcoursemanagement;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapwebservice.courses.CourseDetails;
import com.soapwebservice.courses.GetCourseDetailsRequest;
import com.soapwebservice.courses.GetCourseDetailsResponse;

@Endpoint 
public class CourseDetailsEndpoint {
	
	@PayloadRoot(namespace ="http://soapwebservice.com/courses",localPart ="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsResponse(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails= new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Spring_boot_soapwebservice");
		courseDetails.setDescription("That is wandower full course");
		
		response.setCourseDetails(courseDetails);
		
		return response;
		
	}
}
