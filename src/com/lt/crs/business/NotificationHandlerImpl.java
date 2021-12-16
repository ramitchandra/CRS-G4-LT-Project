package com.lt.crs.business;

import com.lt.crs.bean.Payment;
import com.lt.crs.dao.PaymentsDaoImpl;

public class NotificationHandlerImpl implements NotificationHandler{

	@Override
	public void checkPayment(PaymentsDaoImpl paymentsDaoImpl) {
		
		Payment payment = new Payment();
		
		if(paymentsDaoImpl.checkPayment(payment.getStudentId(), payment.getCourseAmount())) {
			System.out.println("Payment Successful");
		}else {
			System.out.println("Payment was not completed");
		}
	}
	

}
