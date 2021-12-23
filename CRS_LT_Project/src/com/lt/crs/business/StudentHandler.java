package com.lt.crs.business;

import java.util.List;

import com.lt.crs.bean.Course;
import com.mysql.jdbc.Connection;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 * This Module will be responsible for the new user creation, Validate student, course registration,
 * view grades, payment of course.
 */
public interface StudentHandler {
	public void createDummyStudent();
	/**
	 * validate student in Db
	 * @param username
	 * @param password
	 * @return 
	 */
	public boolean validateStudent(String username, String password);
	/**
	 * Student register for available course 
	 * @param username
	 * @param courseEnrolled
	 * @param conn
	 * @param courseList
	 * @param studentOption
	 * @return
	 */
	public int registerForCourse(String username, String courseEnrolled, Connection conn,List<Course> courseList, int studentOption);
//	public void addCourse(String studentName, String courseName);
//	public void dropCourse(String studentName, String courseName);
	/**
	 * Student can view grades
	 * @param studentName
	 * @param studentOption
	 * @return
	 */
	public int viewGrade(String studentName,int studentOption);
	/**
	 * student can make payment of courses.
	 * 
	 * @param username
	 * @param studentOption
	 * @return
	 */
	public int payFees(String username, int studentOption);
	/**
	 * New student creation in DB
	 */
	public void createStudent();
}
