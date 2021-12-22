package com.lt.crs.dao;

import com.lt.crs.bean.CardDetails;

public interface PaymentsDao {
	
	public boolean  makePayment(String studentUsername, String payment);
	public float checkPayment(String studentUsername);
	public boolean cardDetails(String studentUsername, CardDetails cardDetails);
	
}
