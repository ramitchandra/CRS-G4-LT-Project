package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.StudentHandlerImpl;

public class AdminCRSMenu {

	Scanner sc = new Scanner(System.in);
	public void adminLogin() {
		System.out.println("Enter Username/Password");
		String username = sc.nextLine();
		String password = sc.nextLine();
		StudentHandlerImpl sh = new StudentHandlerImpl();
		sh.createStudent();
		if(sh.validateStudent(username, password))
			System.out.println("Validation Success");
		else
			System.out.println("Validation Failed");
	}
}
