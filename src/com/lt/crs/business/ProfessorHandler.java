package com.lt.crs.business;

public interface ProfessorHandler {
	public String viewGrades(int studentid);
	public String insertGrade (String username, String grade);
	public int listStudent(int studentOption);
}
