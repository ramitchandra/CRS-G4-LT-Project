package com.lt.crs.dao;

public interface PaymentsDao {
	
	public void  makePayment(String studentUsername, float payment);
	public void checkPayment(String studentUsername);
	
}
