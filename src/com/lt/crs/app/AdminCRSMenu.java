package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.AdminHandlerImpl;

public class AdminCRSMenu {

	Scanner sc = new Scanner(System.in);
	public void adminLogin() {
	/*System.out.println("Enter Username/Password");
		String username = sc.nextLine();
		String password = sc.nextLine();
		AdminHandlerImpl sh = new AdminHandlerImpl();
		sh.validateUser(username, password);
		sh.createStudent();
		if(sh.validateUser(username, password))
			System.out.println("Validation Success");
		else
			System.out.println("Validation Failed");
	}*/
		System.out.println();
		System.out.println("Please select the required option");
		System.out.println("------------------------------------");
		System.out.println("1. Approve Student Registration");
		System.out.println("2. Add Professor");
		System.out.println("3. Add Course");
		System.out.println("4. Remove Course");
		System.out.println("5. Generate Report Card");
		System.out.println("6. Logout");
		
		int adminOption = sc.nextInt();
		sc.nextLine();
		
		switch(adminOption)
		{
		case 1: AdminHandlerImpl ah= new AdminHandlerImpl();
		        ah.approveStudentRegistration();
		         break;
		case 6: MainCRSMenu mainMenu = new MainCRSMenu();
		        mainMenu.mainMenu();
		        break;
			
		}
	
	
	}
}