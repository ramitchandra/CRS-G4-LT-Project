package com.lt.crs.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.lt.crs.app.AdminCRSMenu;
import com.lt.crs.dao.AdminDAO;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

public class AdminHandlerImpl implements AdminHandler {

	DbUtils dbConn = new DbUtils();
	static Scanner sc = new Scanner(System.in);
	
/*	@Override
	public boolean validateUser(String username,String password) {
		// TODO Auto-generated method stub
		AdminDAOImpl ad= new AdminDAOImpl();
		return ad.validateUserDao(username, password);
		
	}*/

	@Override
	public void generateReportCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProfessor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approveStudentRegistration() {
		// TODO Auto-generated method stub
		boolean furtherRequired = false;
		Connection conn=null;
		PreparedStatement stmt=null ;
		ResultSet rs = null;
		
		conn=(Connection) dbConn.createConnection();
		
		String sql= "select * from user where isApproved = false";
		
		try {
			stmt= conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println();
				System.out.println("___________________________________________________________________________________________");
				System.out.format("| %15s | %15s | %15s | %15s |","UserId","UserName","Role","Approval Status");
				System.out.println();
				System.out.format("|%15s | %15s | %15s | %15s |",rs.getInt(1),rs.getString(2),rs.getString(4),rs.getBoolean(5));
				}
				System.out.println();
				System.out.println("___________________________________________________________________________________________");
			 // System.out.println("Select studentID which you want to approve");
			
			  do
			  {
				  System.out.println("Select studentID which you want to approve");
					
			  int studentId = sc.nextInt();
			  sc.nextLine();
			  AdminDAO ad = new AdminDAOImpl();
			  ad.userApproval(studentId);
			  
			  System.out.println();
			  System.out.println("Want to approve any other student : (y/n)");
			  String input = sc.nextLine();
			  if("Y".equalsIgnoreCase(input)) {
			  furtherRequired = true;
			 
			  }
			  else {
			  furtherRequired = false;
			  }
			  }
			  while(furtherRequired);
				AdminCRSMenu ad = new AdminCRSMenu();
				ad.adminLogin();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
	}

	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
		
	}
	
	

}
