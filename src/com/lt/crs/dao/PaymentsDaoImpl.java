package com.lt.crs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.bean.Payment;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

public abstract class PaymentsDaoImpl implements PaymentsDao {
	DbUtils dbConn = new DbUtils();

	@Override
	public void makePayment() {
		
		Payment payment = new Payment();

		Connection conn = null;
		PreparedStatement stmt = null;

		conn = (Connection) dbConn.createConnection();
		String sql = "insert into student values(?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, payment.getPaymentId()); // This would set age
			stmt.setString(2, payment.getPaymentMode());
			stmt.setString(3, payment.getCourseAmount());
			stmt.setInt(4, payment.getStudentId());
			stmt.executeUpdate();

			dbConn.closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkPayment(int studentId, String courseAmount) {
		
		Payment payment = new Payment();

		Connection conn = null;
		PreparedStatement stmt = null;

		conn = (Connection) dbConn.createConnection();
		String sql = "select * from student where studentId=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, payment.getStudentId());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}

			dbConn.closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
