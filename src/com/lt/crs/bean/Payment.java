package com.lt.crs.bean;

public class Payment {
	private int patmentId;
	private String paymentMode;
	private String courseAmount;
	private int studentId;
	
	public int getPatmentId() {
		return patmentId;
	}
	public void setPatmentId(int patmentId) {
		this.patmentId = patmentId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCourseAmount() {
		return courseAmount;
	}
	public void setCourseAmount(String courseAmount) {
		this.courseAmount = courseAmount;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
