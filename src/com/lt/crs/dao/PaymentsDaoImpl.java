package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.bean.CardDetails;
import com.lt.crs.bean.Payment;
import com.lt.crs.utils.DbUtils;

public class PaymentsDaoImpl implements PaymentsDao {
	
	DbUtils dbConn = new DbUtils();
	Payment payment = new Payment();

	@Override
	public boolean makePayment(String studentUsername, String amount) {

		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = (Connection) dbConn.createConnection();
		String sql1 = "insert into payment (paymentMode, courseAmount, studentUsername) values (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, "Online");
			stmt.setString(2, amount);
			stmt.setString(3, studentUsername);
			result = stmt.executeUpdate();
			if (result == 1) {
				return true;
			} 
			dbConn.closeConnection(conn);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public float checkPayment(String studentUsername) {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;

		int courseId = 0;
		float courseAmount = 0;
		float totalAmount = 0;
		conn = (Connection) dbConn.createConnection();
		String sql1 = "select courseId from enrolledcourses where studentUsername=?";
		String sql2 = "SELECT distinct (course.onlineAmount) FROM course inner JOIN enrolledcourses ON course.courseId = ?";

		try {
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, studentUsername);
			ResultSet rs2 = stmt1.executeQuery();

			while (rs2.next()) {
				courseId = rs2.getInt(1);
				try {
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setInt(1, courseId);

					ResultSet rs3 = stmt2.executeQuery();

					while (rs3.next()) {

						courseAmount = rs3.getFloat(1);
						totalAmount = totalAmount + courseAmount;

					}

					stmt2.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			stmt1.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return totalAmount;
	}

	@Override
	public boolean cardDetails(String studentUsername, CardDetails cardDetails) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt3 = null;
		conn = (Connection) dbConn.createConnection();
		String sql1 = "insert into carddetails values(?,?,?,?)";
		try {
			stmt3 = conn.prepareStatement(sql1);
			stmt3.setString(1, studentUsername);
			stmt3.setString(2, cardDetails.getCardNumber());
			stmt3.setString(3, cardDetails.getCardHolderName());
			stmt3.setString(4, cardDetails.getExpiryDate());
			result = stmt3.executeUpdate();
			if (result == 1) {
				return true;
			} 
			dbConn.closeConnection(conn);
			
			stmt3.close();
			conn.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
