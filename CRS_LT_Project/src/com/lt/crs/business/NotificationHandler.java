package com.lt.crs.business;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 * This module will be responsible for sending the required notification to the user.
 *
 */
public interface NotificationHandler {
	/**
	 * @param username
	 * Send Notification to student after registration.
	 */
	public void registrationSuccessfulNotification(String username);
	/**
	 * @param username
	 * Send Notification to student after payment successful.
	 */
	public void paymentSuccessfulNotification(String username);
	/**
	 * @param username
	 * Send Notification to student after payment declined.
	 */
	public void paymentDeclinedNotification(String username);
	/**
	 * @param studentId
	 * Send Notification to student after admin approves.
	 */
	public void approvedNotification(int studentId);
	/**
	 * @param username
	 * @param role
	 * Send Notification to user after login.
	 */
	public void loginNotification(String username, String role);
	/**
	 * @param username
	 * @param registerd
	 * Send Notification to student after course registration.
	 */
	public void courseRegistrationNotification(String username, String registerd);
	
	/**
	 * @param username
	 * Send Notification to user after login.
	 */
	public void updatePasswordNotification(String username);
	
	/**
	 * Send Notification to student about course limit.
	 */
	public void courseLimitNotification();
}
