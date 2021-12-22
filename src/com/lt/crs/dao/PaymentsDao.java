package com.lt.crs.dao;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public interface PaymentsDao {
	
	/**
	 * @param studentUsername
	 * @param payment
	 */
	public void  makePayment(String studentUsername, float payment);
	
	/**
	 * @param studentUsername
	 */
	public void checkPayment(String studentUsername);
	
}
