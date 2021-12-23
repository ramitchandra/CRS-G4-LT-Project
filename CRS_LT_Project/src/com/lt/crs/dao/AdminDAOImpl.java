package com.lt.crs.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lt.crs.business.NotificationHandler;
import com.lt.crs.business.NotificationHandlerImpl;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;


public class AdminDAOImpl implements AdminDAO {
	private static Logger logger = Logger.getLogger(AdminDAOImpl.class);
	
	DbUtils dbConn= new DbUtils();
	
//	public  AdminDAOImpl() {
//
//		role r1 = new role();
//		r1.setRoleId(101);
//		r1.setRoleName("Admin");
//
//		role r2 = new role();
//		r2.setRoleId(102);
//		r2.setRoleName("Professor");
//
//		role r3 = new role();
//		r3.setRoleId(103);
//		r3.setRoleName("Student");
//
//		Connection conn=null;
//		PreparedStatement stmt=null ;
//		PreparedStatement stmt1=null ;
//		PreparedStatement stmt2=null ;
//		conn=(Connection) dbConn.createConnection();
//		String sql="insert into role values(?,?)";
//
//		try {
//			stmt= conn.prepareStatement(sql);
//			stmt1= conn.prepareStatement(sql);
//			stmt2= conn.prepareStatement(sql);
//			stmt.setInt(1,r1.getRoleId()); 
//			stmt.setString(2,r1.getRoleName());
//			stmt1.setInt(1,r2.getRoleId()); 
//			stmt1.setString(2,r2.getRoleName());
//			stmt2.setInt(1,r3.getRoleId()); 
//			stmt2.setString(2,r3.getRoleName());
//			stmt.executeUpdate();
//			stmt1.executeUpdate();
//			stmt2.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		dbConn.closeConnection(conn);
//		return;
//
//	}

	public void userApproval(int  studentId) {

		//@Override
		Connection conn=null;
		String sql = null;
		PreparedStatement stmt=null ;
		conn=(Connection) dbConn.createConnection();
		/*try {
			 sql="UPDATE user SET isApproved = true WHERE userid = "+studentId;
			throw new AlreadyApprovedException("enter valid id");
			}
			catch(AlreadyApprovedException aae) {
			System.out.println(aae.getMessage());
			}*/
		sql=SqlConstants.userApprovalQuery+studentId;
		try {
		stmt= conn.prepareStatement(sql);
		stmt.executeUpdate();
		NotificationHandler nh = new NotificationHandlerImpl();
		nh.approvedNotification(studentId);

		} catch (SQLException e) {
			logger.error("Error generated"+e.getMessage());
		} finally{
		dbConn.closeConnection(conn);
		}
	}
}