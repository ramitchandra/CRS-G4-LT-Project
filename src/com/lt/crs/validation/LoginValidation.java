package com.lt.crs.validation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lt.crs.app.MainCRSMenu;
import com.lt.crs.business.NotificationHandler;
import com.lt.crs.business.NotificationHandlerImpl;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public class LoginValidation {
	DbUtils dbConn = new DbUtils();
	private static Logger logger = Logger.getLogger(LoginValidation.class);
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public String validateCredentials(String userName, String password) {
		int roleId = 0 ;
		String role = null;
		boolean isApproved = false;
		Connection conn=null;
		PreparedStatement stmt=null ;
		ResultSet rs = null;
		PreparedStatement stmt1=null ;
		ResultSet rs1 = null;
		conn=(Connection) dbConn.createConnection();
		String sql=SqlConstants.userQuery;
		
		try {
			stmt= conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(userName.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(3))) {
					roleId = rs.getInt(4);
					String sql1=SqlConstants.roleQuery+roleId;
					stmt1= conn.prepareStatement(sql1);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					 role=rs1.getString(1);
					 isApproved = rs.getBoolean(5);
					}
						
				}
			}
		}
		catch (SQLException e) {
			logger.error("Error generated"+e.getMessage());
		}finally{
			dbConn.closeConnection(conn);
		}
		if(role.equalsIgnoreCase("Student") && isApproved == true ||role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Professor"))
		    return role;
		else
			return null;
		
	}
	
// This Method is responsible for updating the password for Student/Proff/Admin in user table.	
	/**
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updateCredentials(String username, String oldPassword, String newPassword){
		Connection conn= null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2=null;
		ResultSet rs= null;
		
		conn=(Connection) dbConn.createConnection();
		String sql=SqlConstants.selectUserdetailsQuery;
		try{
			stmt1= conn.prepareStatement(sql);
			stmt1.setString(1, username);
			 rs= stmt1.executeQuery();
			
			while(rs.next()){
				 	if(username.equalsIgnoreCase(rs.getString(2))&& oldPassword.equalsIgnoreCase(rs.getString(3))){
					String passw = SqlConstants.updateUserPasswordQuery+newPassword+"' WHERE userName='"+username+"' ";
					stmt2= conn.prepareStatement(passw);
					
					stmt2.executeUpdate();
					String updatestud= SqlConstants.updateStudentPasswordQuery+newPassword+"' WHERE studentUserName='"+username+"' ";
					stmt2= conn.prepareStatement(updatestud);
					NotificationHandler nh = new NotificationHandlerImpl();
					nh.updatePasswordNotification(username);
					stmt2.executeUpdate();
				}else if(!username.equalsIgnoreCase(rs.getString(2))|| !oldPassword.equalsIgnoreCase(rs.getString(3))){
					System.out.println("Invalid Username or Password");
					
				}
				
			}
		} catch(SQLException e){
			logger.error("Error generated"+e.getMessage());
		}finally{
		dbConn.closeConnection(conn);
		MainCRSMenu crs= new MainCRSMenu();
		crs.mainMenu();
		}
	}
}


