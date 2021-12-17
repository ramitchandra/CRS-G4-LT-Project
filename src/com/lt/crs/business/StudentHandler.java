package com.lt.crs.business;

import java.util.List;

import com.lt.crs.bean.Course;
import com.mysql.jdbc.Connection;

public interface StudentHandler {
	public void createStudent();
	public boolean validateStudent(String username, String password);
	public void registerForCourse(String username, String courseEnrolled, Connection conn,List<Course> courseList);
//	public void addCourse(String studentName, String courseName);
//	public void dropCourse(String studentName, String courseName);
	public void viewGrade(String studentName);
//	public void payFees(String studentName);
}
