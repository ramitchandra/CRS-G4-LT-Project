package com.lt.crs.business;

import java.util.Scanner;

import com.lt.crs.app.StudentCRSMenu;
import com.lt.crs.bean.CardDetails;
import com.lt.crs.dao.PaymentsDao;
import com.lt.crs.dao.PaymentsDaoImpl;

public class PaymentHandlerImpl implements PaymentHandler {

	PaymentsDao paymentsDao = new PaymentsDaoImpl();
	Scanner sc = new Scanner(System.in);

	@Override
	public void makePayment(String studentUsername, float amount) {

		System.out.println();
		System.out.println("Please Enter the Amount to be paid: ");
		String amountToPaid = sc.next();
		NotificationHandler nh = new NotificationHandlerImpl();
		if (paymentsDao.makePayment(studentUsername, amountToPaid)) {
			nh.paymentSuccessfulNotification(studentUsername);
		} else {
			nh.paymentDeclinedNotification(studentUsername);
		}

		System.out.println();
		System.out.println("Select further operation");
		StudentCRSMenu menu = new StudentCRSMenu();
		menu.studentMenu();

	}

	@Override
	public void cardDetails(String studentUsername, float totalAmount) {

		CardDetails cardDetails = new CardDetails();

		System.out.println();
		System.out.println("-----CARD DETAILS-----");
		System.out.println();
		
		System.out.println("Enter card number:");
		String cardNumber = sc.nextLine();
		cardDetails.setCardNumber(cardNumber);
		
		System.out.println("Enter Card Holder Name: ");
		cardDetails.setCardHolderName(sc.nextLine());
		
		System.out.println("Enter Expiry Date: ");
		cardDetails.setExpiryDate(sc.nextLine());
		
		if(paymentsDao.cardDetails(studentUsername,cardDetails)) {
			
			System.out.println("Card details added successfully.");
			
			PaymentHandler payments = new PaymentHandlerImpl();
			payments.makePayment(studentUsername, totalAmount);
			
		}else {
			System.out.println("Please enter valid card details");
			
			PaymentHandler payments = new PaymentHandlerImpl();
			payments.cardDetails(studentUsername, totalAmount);
		}
	}

	@Override
	public void checkPayment(String studentUsername) {

		float totalAmount = paymentsDao.checkPayment(studentUsername);

		System.out.println();
		System.out.println("Total Amount to be paid for courses " + totalAmount);

		PaymentHandler payments = new PaymentHandlerImpl();
		payments.cardDetails(studentUsername, totalAmount);

	}

}
