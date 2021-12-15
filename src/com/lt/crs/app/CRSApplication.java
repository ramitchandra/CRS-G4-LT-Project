package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.business.CourseHandler;
import com.lt.crs.business.CourseHandlerImpl;
import com.lt.crs.business.StudentHandlerImpl;

public class CRSApplication {
		
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to CRS Application");
		//Main Menu
		System.out.println("Main Menu");
		System.out.println("1. Login");
		System.out.println("2. New User");
		System.out.println("3. Update Password");
		System.out.println("4. Exit");
		int mainOption = sc.nextInt();
		switch(mainOption) {
		case 1 : 	System.out.println("Select required role");
					System.out.println("1. Student");
					System.out.println("2. Professor");
					System.out.println("3. Admin");
					int roleValue = sc.nextInt();
					sc.nextLine();
					furtherRoleHandling(roleValue);
		}
		
	}

	private static void furtherRoleHandling(int roleValue) {
		System.out.println("Enter Username/Password");
		String username = sc.nextLine();
		String password = sc.nextLine();
		if(roleValue == 1) {
			StudentHandlerImpl sh = new StudentHandlerImpl();
			sh.createStudent();
			if(sh.validateStudent(username, password))
				courseRegistration(username,sh);
			else
				System.out.println("Validation Failed");
		}
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
					Course[] courseArray = CourseHandlerImpl.courseArray;
					for(Course course : courseArray) {
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
