package com.lt.crs.app;

import java.sql.PreparedStatement;
import java.util.Scanner;

import com.lt.crs.business.ProfessorHandler;
import com.lt.crs.business.ProfessorHandlerImpl;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public class ProfessorCRSMenu {
	
	Scanner sc = new Scanner(System.in);
	
	DbUtils dbConn= new DbUtils();
	Connection conn= null;
	PreparedStatement stmt = null;
	
	/**
	 * @param username
	 */
	public void professorLogin(String username) {
		professorMenu();
		conn=(Connection) dbConn.createConnection();
		int studentOption = sc.nextInt();
		sc.nextLine();
		do{
			if(studentOption==1) {
				ProfessorHandler ph= new ProfessorHandlerImpl();
				studentOption = ph.listStudent(studentOption);				
			}else if(studentOption==2){
				MainCRSMenu mainMenu = new MainCRSMenu();
				mainMenu.mainMenu();
			}else{
				System.out.println("Invalid Input");
			}
		}while(studentOption>0 && studentOption<=6);
		sc.close();
		dbConn.closeConnection(conn);
	}
	
	/**
	 * 
	 */
	public void professorMenu() {
		System.out.println();
		System.out.println("Please select the appropriate option");
		System.out.println("------------------------------------");
		System.out.println("1. Update the grades");
		System.out.println("2. Logout");
	}
}
