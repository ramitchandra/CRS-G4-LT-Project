package com.lt.crs.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.AdminCRSMenu;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.dao.AdminDAO;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.utils.DbUtils;
import com.lt.crs.validation.LoginValidation;
import com.mysql.jdbc.Connection;

public class AdminHandlerImpl implements AdminHandler {
	private static Logger logger = Logger.getLogger(AdminHandlerImpl.class);

	DbUtils dbConn = new DbUtils();
	static Scanner sc = new Scanner(System.in);
	
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
		
		String sql= SqlConstants.pendingApprovalQuery;
		
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
			logger.error("Error generated"+e.getMessage());
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
