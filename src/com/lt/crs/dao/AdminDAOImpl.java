package com.lt.crs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.utils.DbConnection;
import com.mysql.jdbc.Connection;

public class AdminDAOImpl implements AdminDAO {
	DbConnection dbConn= new DbConnection();
	@Override
	public boolean validateUserDao(String username, String password) {
		// TODO Auto-generated method stub
		boolean indicator = false;
		Connection conn=null;
		PreparedStatement stmt=null ;
		ResultSet rs = null;
		conn=(Connection) dbConn.createConnection();
		String sql="select * from admin";
		//System.out.println(sql);
		
			try {
				stmt= conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(username.equalsIgnoreCase(rs.getString(1)) && password.equalsIgnoreCase(rs.getString(4))) {
						indicator = true;
					}
					
					
				}
			      
			   
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//stmt.setInt(1, course.getCourseId());  // This would set age
			
		      
		    dbConn.closeConnection(conn);
		
		    return indicator;

	}	

}
