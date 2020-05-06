package com.soap.webservices.Soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soap.webservices.Soapcoursemanagement.soap.bean.Course;


@Service
public class CourseDetailsService {

	public enum Status{
		SUCCESS,FALUER;
	}
	private static List<Course> Courses = new ArrayList<>();
	static {
		Course courses1 = new Course(1, "Spring", "Chapter1");
		Courses.add(courses1);

		Course courses2 = new Course(2, "Spring_webservice", "Chapter2");
		Courses.add(courses2);

		Course courses3 = new Course(3, "Spring_soapwebservice", "Chapter3");
		Courses.add(courses3);

		Course courses4 = new Course(4, "Spring_Restfullwebservice", "Chapter4");
		Courses.add(courses4);

		Course courses5 = new Course(5, "Spring_mvn_plugin", "jaxb");
		Courses.add(courses5);
	}

	// Get courses by id
	public Course findById(int id) {
		for (Course course : Courses) {
			if (course.getId() == id)
				return course;
		}
		return null;
	}

	// get All courses
	public List<Course> findAll() {
		return Courses;
	}

	// delete courses
	public Status deleteById(int id) {
		Iterator<Course> iterator = Courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FALUER;
	}

	// updating course & new course
}
