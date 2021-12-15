package com.lt.crs.app;

import java.util.List;
import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.business.CourseHandler;
import com.lt.crs.business.CourseHandlerImpl;
import com.lt.crs.business.StudentHandlerImpl;

public class StudentCRSMenu {
	
	static Scanner sc = new Scanner(System.in);
	
	public void studentLogin() {
		System.out.println("Enter Username/Password");
		String username = sc.nextLine();
		String password = sc.nextLine();
		StudentHandlerImpl sh = new StudentHandlerImpl();
		sh.createStudent();
		if(sh.validateStudent(username, password))
			courseRegistration(username,sh);
		else
			System.out.println("Validation Failed");
	}
	private static void courseRegistration(String username, StudentHandlerImpl sh) {
		System.out.println("Please select the appropriate option");
		System.out.println("1. Register for course");
		System.out.println("2. Add Course");
		System.out.println("3. Drop Course");
		System.out.println("4. View Grade");
		System.out.println("5. Pay Fee");
		
		int studentOption = sc.nextInt();
		sc.nextLine();
		switch(studentOption) {
		case 1 : 	sh.registerForCourse(studentOption, studentOption);
					break;
		case 2 :	System.out.println("Available Courses");
					CourseHandler courseHandler = new CourseHandlerImpl();
					courseHandler.createCourse();
					List<Course> courseList = CourseHandlerImpl.courseList;
					for(Course course : courseList) {
						System.out.println(course.getCourseId() + course.getCourseName());
					}
					String courseName = sc.nextLine();
					sh.addCourse(username, courseName);
					break;
		case 3 :	System.out.println("Course to be removed");
					String courseNameTobeRemoved = sc.nextLine();
					sh.dropCourse(username, courseNameTobeRemoved);
					break;
		case 4 :	sh.viewGrade(username);
					break;
		case 5 :	sh.payFees(username);
					break;
		default :	System.out.println("Invalid input");
		}
	}
}
