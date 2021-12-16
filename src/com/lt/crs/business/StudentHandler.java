package com.lt.crs.business;

public interface StudentHandler {
	public void createStudent();
	public boolean validateStudent(String username, String password);
	public void registerForCourse(int student, int courseId);
//	public void addCourse(String studentName, String courseName);
//	public void dropCourse(String studentName, String courseName);
	public void viewGrade(String studentName);
//	public void payFees(String studentName);
}
