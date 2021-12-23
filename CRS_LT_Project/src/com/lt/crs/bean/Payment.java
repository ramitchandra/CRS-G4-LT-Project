package com.lt.crs.bean;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * Bean of payment to store payment details
 */
public class Payment {
	private int paymentId;
	private String paymentMode;
	private String courseAmount;
	private int studentId;
	private String studentUsername;
	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}
	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	/**
	 * @return the courseAmount
	 */
	public String getCourseAmount() {
		return courseAmount;
	}
	/**
	 * @param courseAmount the courseAmount to set
	 */
	public void setCourseAmount(String courseAmount) {
		this.courseAmount = courseAmount;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the studentUsername
	 */
	public String getStudentUsername() {
		return studentUsername;
	}
	/**
	 * @param studentUsername the studentUsername to set
	 */
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	
	
	
}
