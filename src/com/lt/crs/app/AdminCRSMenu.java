package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.business.AdminHandlerImpl;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public class AdminCRSMenu {

	Scanner sc = new Scanner(System.in);
	/**
	 * 
	 */
	public void adminLogin() {
	
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
		AdminHandlerImpl ah= new AdminHandlerImpl();
		switch(adminOption)
		{
		case 1:  ah.approveStudentRegistration();
		         break;
		case 2:  ah.addProfessor();
        		 break; 
		case 3:  ah.addCourse();
		 		 break;	
		case 4:  ah.removeCourse();
		 		 break;
		case 5:  ah.generateReportCard();
		 		 break;		 
		case 6: MainCRSMenu mainMenu = new MainCRSMenu();
		        mainMenu.mainMenu();
		        break;
			
		}
	
	
	}
}