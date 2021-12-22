package com.lt.crs.business;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 * This Module will be responsible for handling student registration approval, Course add/remove,
 * generation of report card.
 *
 */
public interface AdminHandler {

	//public boolean validateUser(String username, String password);
	public void generateReportCard();
	public void addProfessor();
	/**
	 *  This method responsible for approval of student by Admin.
	 */
	public void approveStudentRegistration();
	public void addCourse();
	public void removeCourse();
}
