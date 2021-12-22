package com.lt.crs.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;

import com.lt.crs.bean.Course;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.utils.DbUtils;
import com.lt.crs.validation.LoginValidation;
import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

public class CourseDAOImpl implements CourseDAO {
	private static Logger logger = Logger.getLogger(CourseDAOImpl.class);
	
	DbUtils dbConn= new DbUtils();
	@Override
	public void createCourse(Course course) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement stmt=null ;
		
		conn=(Connection) dbConn.createConnection();
		String sql=SqlConstants.courseInsertQuery;
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setInt(1, course.getCourseId());  // This would set age
		      stmt.setString(2,course.getCourseName());
		      stmt.setBoolean(3, course.isCourseAvailable());
		      stmt.setInt(4, course.getOfflieFees());
		      stmt.setInt(5, course.getOnlineFees());
		      stmt.executeUpdate();
		      
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error generated"+e.getMessage());
		} finally{
			dbConn.closeConnection(conn);
		}
		
	}

}
