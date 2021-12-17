package com.lt.crs.validation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

public class LoginValidation {
	DbUtils dbConn = new DbUtils();
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
		String sql="select * from user";
		
		//System.out.println(sql);
		try {
			stmt= conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(userName.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(3))) {
					roleId = rs.getInt(4);
					String sql1="select role from role where id ="+roleId;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//stmt.setInt(1, course.getCourseId()); // This would set age
		dbConn.closeConnection(conn);
		if(role.equalsIgnoreCase("Student") && isApproved == true ||role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Professor"))
		    return role;
		else
			return null;
	}
}


