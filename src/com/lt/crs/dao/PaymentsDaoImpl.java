package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.lt.crs.bean.Payment;
import com.lt.crs.utils.DbUtils;

public class PaymentsDaoImpl implements PaymentsDao {
	int result = 0;
	DbUtils dbConn = new DbUtils();
	Scanner sc = new Scanner(System.in);
	Payment payment = new Payment();

	@Override
	public void makePayment(String studentUsername, float amount) {
		System.out.println();
		System.out.println("Please Enter the Amount to be paid: ");
		String amountToPaid = sc.next();
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = (Connection) dbConn.createConnection();
		String sql1 = "insert into payment (paymentMode, courseAmount, studentUsername) values (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, "Online");
			stmt.setString(2, amountToPaid);
			stmt.setString(3, studentUsername);
			result = stmt.executeUpdate();
			if (result == 1) {
				System.out.println("Payment Successful");
			} else {
				System.out.println("Payment Declined");
			}
			dbConn.closeConnection(conn);
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkPayment(String studentUsername) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;

		int courseId = 0;
		float courseAmount = 0;
		float totalAmount = 0;
		conn = (Connection) dbConn.createConnection();
		String sql1 = "select courseId from enrolledcourses where studentUsername=?";
		String sql2 = "SELECT distinct (course.onlineAmount) FROM course inner JOIN enrolledcourses ON course.courseId = ?";

		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, studentUsername);
			ResultSet rs2 = stmt.executeQuery();

			while (rs2.next()) {
				courseId = rs2.getInt(1);
				try {
					stmt1 = conn.prepareStatement(sql2);
					stmt1.setInt(1, courseId);

					ResultSet rs3 = stmt1.executeQuery();

					while (rs3.next()) {

						courseAmount = rs3.getFloat(1);
						totalAmount = totalAmount + courseAmount;

					}

					stmt1.close();

				} catch (SQLException ex) {
				}
			}

			System.out.println();
			System.out.println("Total Amount to be paid for courses " + totalAmount);

			PaymentsDao newPaymentsDao = new PaymentsDaoImpl();
			newPaymentsDao.makePayment(studentUsername, totalAmount);

			stmt.close();
			conn.close();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
