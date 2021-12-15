package com.lt.crs.bean;

public class Payment {
	private int patmentId;
	private String paymentMode;
	private String courseAmount;
	
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
	
}
