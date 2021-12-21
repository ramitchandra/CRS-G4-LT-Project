package com.lt.crs.business;

import com.lt.crs.bean.Student;

public interface AdminHandler {

	//public boolean validateUser(String username, String password);
	public void generateReportCard();
	public void addProfessor();
	public void approveStudentRegistration();
	public void addCourse();
	public void removeCourse();
}
