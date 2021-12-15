package com.lt.crs.business;

import com.lt.crs.bean.Course;

public class CourseHandlerImpl implements CourseHandler {
	public static Course[] courseArray = new Course[2];
	
	public void createCourse() {
		Course course1 = new Course();
		course1.setCourseAvailable(true);
		course1.setCourseId(1001);
		course1.setCourseName("Course1");
		course1.setOfflieFees(1000);
		course1.setOnlineFees(500);
		
		Course course2 = new Course();
		course2.setCourseAvailable(true);
		course2.setCourseId(1002);
		course2.setCourseName("Course2");
		course2.setOfflieFees(2000);
		course2.setOnlineFees(1500);
		
		courseArray[0] = course1;
		courseArray[1] = course2;
	}
}
