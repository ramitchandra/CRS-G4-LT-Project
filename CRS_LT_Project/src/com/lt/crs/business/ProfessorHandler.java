package com.lt.crs.business;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 * This Module will be responsible for giving grades to the students.
 *
 */
public interface ProfessorHandler {
	/**
	 * Grades available for students to view.
	 * 
	 * @param studentid
	 * @return
	 */
	public String viewGrades(int studentid);
	/**
	 * Insert grades into DB
	 * 
	 * @param username
	 * @param grade
	 * @return
	 */
	public String insertGrade (String username, String grade);
	/**
	 * Giving Grades to students by professor.
	 * @param studentOption
	 * @return
	 */
	public int listStudent(int studentOption);
}
