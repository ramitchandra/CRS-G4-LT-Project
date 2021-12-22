package com.lt.crs.bean;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * Bean of grades to store grades details
 */
public class Grades {
	private String studentID;
	private char studentGrade;
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	/**
	 * @return the studentGrade
	 */
	public char getStudentGrade() {
		return studentGrade;
	}
	/**
	 * @param studentGrade the studentGrade to set
	 */
	public void setStudentGrade(char studentGrade) {
		this.studentGrade = studentGrade;
	}
	
}
