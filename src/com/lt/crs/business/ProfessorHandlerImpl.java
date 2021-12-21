package com.lt.crs.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.lt.crs.app.ProfessorCRSMenu;
import com.lt.crs.constants.EnumGrade;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProfessorHandlerImpl implements ProfessorHandler {
	
	static Scanner sc = new Scanner(System.in);
	
	DbUtils dbConn= new DbUtils();
	Connection conn=null;
	PreparedStatement ps=null;
	
 
	@Override
	public String viewGrades(int studentid) {
		try {
			String sql= "Select studentGrade from grades where studentId=?";
			conn=(Connection) dbConn.createConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,studentid );
			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
		return null;
	}

	@Override
	public String insertGrade(String studentUsername, String grade) {
		
		String sql= "insert into grades values (?,?)";
		String studIdQuery= "Select studentId from student where studentUsername=?";
		int studId=0;
		try {
			conn=(Connection) dbConn.createConnection();
			ps= (PreparedStatement) conn.prepareStatement(studIdQuery);
			ps.setString(1, studentUsername);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				studId = rs.getInt(1);
			}
			ps= (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, studId);
			ps.setString(2, EnumGrade.valueOf(grade).toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
		
		return null;
	}
	
	public int listStudent(int studentOption) {
		
		System.out.println();
		System.out.println("Listing Student for Grading: ");
		String studIdQuery= "Select * from student where studentId in (select distinct studentId from EnrolledCourses where studentId not in(select distinct studentId from grades))";
		try {
			conn=(Connection) dbConn.createConnection();
			ps= (PreparedStatement) conn.prepareStatement(studIdQuery);
			ResultSet rs= ps.executeQuery();
			System.out.println();
			System.out.println("_____________________________________");
			System.out.format("| %15s | %15s |","StudentId","StudentUserName");
			System.out.println();
			System.out.println("_____________________________________");
			while(rs.next()) {
				System.out.println();
				System.out.format("| %15s | %15s |",rs.getInt(1),rs.getString(5));
			}
			System.out.println();
			System.out.println("_____________________________________");
			boolean looping = false;
			do {
				System.out.println();
				System.out.println("Enter student username you want to provide grade: ");
				String name = sc.nextLine();
				System.out.println();
				System.out.println("Enter grade you want to give: ");
				String grade = sc.nextLine();
				insertGrade(name,grade);
				System.out.println("Want to update more grades: (y/n)");
				String option = sc.nextLine();
				if("Y".equalsIgnoreCase(option))
					looping = true;
				else
					looping = false;
			} while (looping);
			ProfessorCRSMenu pcm = new ProfessorCRSMenu();
			pcm.professorMenu();
			studentOption = sc.nextInt();
			sc.nextLine();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn.closeConnection(conn);
		}
		return studentOption;
	}

	
}
