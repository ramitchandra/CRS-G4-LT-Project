package com.lt.crs.business;

import com.lt.crs.dao.PaymentsDaoImpl;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 * 
 * This module will be responsible for sending the required notification to the user.
 *
 */
public interface NotificationHandler {
	/**
	 * @param paymentsDao
	 * Send Notification to student after payment.
	 */
	public void checkPayment(PaymentsDaoImpl paymentsDao);

}
