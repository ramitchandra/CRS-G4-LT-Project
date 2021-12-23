package com.lt.crs.business;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

public class NotificationHandlerImpl implements NotificationHandler{
	
	private static Logger logger = Logger.getLogger(NotificationHandlerImpl.class);
	
	@Override
	public void registrationSuccessfulNotification(String username) {
		logger.info("Registration successful for student : " + username);
	}

	@Override
	public void paymentSuccessfulNotification(String username) {
		logger.info("Payment successful for student : " + username);
		
	}

	@Override
	public void paymentDeclinedNotification(String username) {
		logger.info("Payment declined for student : " + username);
		
	}

	@Override
	public void approvedNotification(int studentId) {
		logger.info("User id "+studentId+" is approved");
		
	}

	@Override
	public void loginNotification(String username, String role) {
		logger.info("Logged in user \"" + username + "\" as " + role + " at " + LocalDateTime.now());
		
	}

	@Override
	public void courseRegistrationNotification(String username, String registerd) {
		logger.info("Courses Registered for Student "+username+ " : "+registerd);
		
	}

	@Override
	public void updatePasswordNotification(String username) {
		logger.info("Password Updated Successfully for: "+username);
	}

	@Override
	public void courseLimitNotification() {
		logger.error("Max course count is 6");
	}

	
}
