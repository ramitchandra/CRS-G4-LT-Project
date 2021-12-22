package com.lt.crs.business;

public interface PaymentHandler {
	public void checkPayment(String studentUsername);
	public void makePayment(String studentUsername, float amount);
	public void cardDetails(String studentUsername, float totalAmount);
}
