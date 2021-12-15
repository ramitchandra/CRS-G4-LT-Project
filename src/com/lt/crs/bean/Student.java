package com.lt.crs.bean;

public class Student {
	private int studentId;
	private String studentName;
	private String studentEmail;
	private String studentPassword;
	private Course[] courseList = new Course[6];
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getstudentEmail() {
		return studentEmail;
	}
	public void setstudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public Course[] getCourseList() {
		return courseList;
	}
	public void setCourseList(Course[] courseList) {
		this.courseList = courseList;
	}
	
	
}
