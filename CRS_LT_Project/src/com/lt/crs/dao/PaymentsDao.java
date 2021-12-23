package com.lt.crs.dao;

import com.lt.crs.bean.CardDetails;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public interface PaymentsDao {
	
	/**
	 * @param studentUsername
	 * @param payment
	 * @return
	 */
	public boolean  makePayment(String studentUsername, String payment);
	/**
	 * @param studentUsername
	 * @return
	 */
	public float checkPayment(String studentUsername);
	/**
	 * @param studentUsername
	 * @param cardDetails
	 * @return
	 */
	public boolean cardDetails(String studentUsername, CardDetails cardDetails);
	
}
