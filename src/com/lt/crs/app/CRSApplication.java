package com.lt.crs.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.lt.crs.constants.CRSConstants;
import com.lt.crs.validation.LoginValidation;

public class CRSApplication {
		
	static Scanner sc = new Scanner(System.in);
	CRSConstants crsConstants = new CRSConstants();
	
	public static void main(String[] args) {
		
		System.out.println("\t\t\t\t\t\t\t-----Welcome to CRS Application-----");
		//Main Menu
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("-----------------------------");
		System.out.println("1. Login");
		System.out.println("2. New User");
		System.out.println("3. Update Password");
		System.out.println("4. Exit");
		System.out.println();
		System.out.println("Enter required option : ");
		int mainOption = sc.nextInt();
		sc.nextLine();
		switch(mainOption) {
		case 1 :	checkRespectiveRole();
		}
		
	}

	private static void checkRespectiveRole() {
		System.out.println();
		System.out.println("Enter creadentials");
		System.out.println("----------------------");
		System.out.println("Username");
		String userName = sc.nextLine();
		System.out.println("Password");
		String password = sc.nextLine();
		LoginValidation lv = new LoginValidation();
		String role = lv.validateCredentials(userName, password);
		System.out.println();
		System.out.println("Logged in user \"" + userName + "\" as " + role);
		if(CRSConstants.STUDENT.equalsIgnoreCase(role)) {
			StudentCRSMenu sm = new StudentCRSMenu();
			try {
				sm.courseRegistration(userName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(CRSConstants.ADMIN.equalsIgnoreCase(role)) {
			AdminCRSMenu am = new AdminCRSMenu();
			am.adminLogin();
		} else if(CRSConstants.PROFESSOR.equalsIgnoreCase(role)) {
			ProfessorCRSMenu pm = new ProfessorCRSMenu();
			pm.professorLogin();
		} else {
			System.out.println("Invalid Role");
		}
	}

	
}
