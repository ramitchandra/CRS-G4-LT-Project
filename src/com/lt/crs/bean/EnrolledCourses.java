package com.lt.crs.bean;

public class EnrolledCourses {
	private int studentId;
	private String studentName;
	private int courseId;
	private String courseName;
	private String studentUsername;
	
	public String getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
