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
		System.out.println("Enter required option : ");
		int mainOption = sc.nextInt();
		switch(mainOption) {
		case 1 : 	System.out.println("Select required role");
					System.out.println("1. Student");
					System.out.println("2. Professor");
					System.out.println("3. Admin");
					System.out.println("Enter required role option : ");
					int roleValue = sc.nextInt();
					sc.nextLine();
					furtherRoleHandling(roleValue);
		}
		
	}

	private static void furtherRoleHandling(int roleValue) {		
		if(roleValue == 1) {
			StudentCRSMenu sm = new StudentCRSMenu();
			sm.studentLogin();
		}
		else if(roleValue == 2){
			ProfessorCRSMenu pm = new ProfessorCRSMenu();
			pm.professorLogin();
		}
		else if(roleValue == 3) {
			AdminCRSMenu am = new AdminCRSMenu();
			am.adminLogin();
		}
		else {
			System.out.println("Please enter valid option");
		}
	}

	
}
