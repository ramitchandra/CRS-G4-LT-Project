package com.lt.crs.business;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 *
 *
 */
public interface PaymentHandler {
	/**
	 * @param studentUsername
	 */
	public void checkPayment(String studentUsername);
	/**
	 * @param studentUsername
	 * @param amount
	 */
	public void makePayment(String studentUsername, float amount);
	/**
	 * @param studentUsername
	 * @param totalAmount
	 */
	public void cardDetails(String studentUsername, float totalAmount);
}
