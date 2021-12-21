package com.lt.crs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.bean.Student;
import com.lt.crs.business.ProfessorHandler;
import com.lt.crs.business.ProfessorHandlerImpl;
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
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		conn=(Connection) dbConn.createConnection();
		String sql="insert into student values(?,?,?,?,?)";
		String sql2="insert into user values(?,?,?,?,?)";
		String sql3 = "select id from role where role = 'Student'";
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1,student.getStudentId());  
		      stmt.setString(2,student.getStudentName());
		      stmt.setString(3, student.getStudentEmail());
		      stmt.setString(4, student.getStudentPassword());
		      stmt.setString(5, student.getStudentUsername());
		      stmt.executeUpdate();
		      
		      stmt2= conn.prepareStatement(sql2);
		      stmt3= conn.prepareStatement(sql3);
				ResultSet rs = stmt3.executeQuery();
				stmt2.setInt(1,student.getStudentId());  
			      stmt2.setString(2,student.getStudentUsername());
			      stmt2.setString(3, student.getStudentPassword());
			      stmt2.setString(4, student.getStudentUsername());
			      while(rs.next()) {
						stmt2.setInt(4,rs.getInt(1));
					}
			      stmt2.setBoolean(5, false);
			      stmt2.executeUpdate();
			      
		      
		   //ProfessorHandler ph= new ProfessorHandlerImpl();
		  // ph.insertGrade(student.getStudentUsername());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			dbConn.closeConnection(conn);
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
