package com.lt.crs.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.lt.crs.bean.Student;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

public class StudentDAOImpl implements StudentDAO {
	DbUtils dbConn= new DbUtils();
	@Override
	public void createStudent(Student student) {
		// TODO Auto-generated method stub
		Connection conn= null;
		PreparedStatement stmt = null;
		
		conn=(Connection) dbConn.createConnection();
		String sql="insert into student values(?,?,?,?,?)";
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,student.getStudentId());  
		      stmt.setString(2,student.getStudentName());
		      stmt.setString(3, student.getStudentEmail());
		      stmt.setString(4, student.getStudentPassword());
		      stmt.setString(5, student.getStudentUsername());
		      stmt.executeUpdate();
		      
		    dbConn.closeConnection(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}

	@Override
	public void addCourse(String studentName, String courseName) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement stmt = null;
		
		conn=(Connection) dbConn.createConnection();
		String sql="insert into course values(?,?,?,?,?)";
        		
		
	}
	
	

}
