package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soap.webservices.Soapcoursemanagement.soap.bean.Course;
import com.soap.webservices.Soapcoursemanagement.soap.service.CourseDetailsService;
import com.soap.webservices.Soapcoursemanagement.soap.service.CourseDetailsService.Status;
import com.soapwebservice.courses.CourseDetails;
import com.soapwebservice.courses.DeleteCourseDetailsRequest;
import com.soapwebservice.courses.DeleteCourseDetailsResponse;
import com.soapwebservice.courses.GetAllCourseDetailsRequest;
import com.soapwebservice.courses.GetAllCourseDetailsResponse;
import com.soapwebservice.courses.GetCourseDetailsRequest;
import com.soapwebservice.courses.GetCourseDetailsResponse;

@Endpoint
@ComponentScan(basePackages = { "com.soap.webservices.Soapcoursemanagement.soap.service" })
public class CourseDetailsEndpoint {

	@Autowired
	public CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://in28minutes.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://soapwebservice.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = service.findById(request.getId());
		if (course == null) {
			/*
			 * //custom exception by runtimeException
			 *  throw new RuntimeException("Invalid CourseID" + request.getId());
			 */
			//without using runtime exception we can also use custom exception
			throw new CourseNotfoundException("Invalid CourseID : " + request.getId());
		}
		return mapCourseDetails(course);
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();

		courseDetails.setId(course.getId());

		courseDetails.setName(course.getName());

		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://soapwebservice.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = service.findAll();

		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://soapwebservice.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = service.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapstatus(status));

		return response;
	}

	private com.soapwebservice.courses.Status mapstatus(Status status) {
		if (status == Status.FALUER) {
			return com.soapwebservice.courses.Status.FALUER;
		}
		return com.soapwebservice.courses.Status.SUCCESS;
	}

}
