package com.lt.crs.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.ProfessorCRSMenu;
import com.lt.crs.constants.EnumGrade;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.exception.InvalidUserNameException;
import com.lt.crs.utils.DbUtils;
import com.lt.crs.validation.LoginValidation;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProfessorHandlerImpl implements ProfessorHandler {
	private static Logger logger = Logger.getLogger(ProfessorHandlerImpl.class);
	
	static Scanner sc = new Scanner(System.in);
	
	DbUtils dbConn= new DbUtils();
	Connection conn=null;
	PreparedStatement ps=null;
	
 
	@Override
	public String viewGrades(int studentid) {
		try {
			String sql= SqlConstants.studentGradeQuery;
			conn=(Connection) dbConn.createConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,studentid );
			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error generated"+e.getMessage());
		}finally{
			dbConn.closeConnection(conn);
		}
		return null;
	}

	@Override
	public String insertGrade(String studentUsername, String grade) {
		
		String sql= SqlConstants.InsertGradeQuery;
		String studIdQuery= SqlConstants.selectStudentQuery;
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
			ps.setString(2, grade);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error generated"+e.getMessage());
		}finally{
			dbConn.closeConnection(conn);
		}
		
		return null;
	}
	
	public int listStudent(int studentOption) {
		
		List<String> userNameList = new ArrayList<>();
		System.out.println();
		System.out.println("Listing Student for Grading: ");
		String studIdQuery= SqlConstants.listStudentQuery;
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
				userNameList.add(rs.getString(5));
			}
			System.out.println();
			System.out.println("_____________________________________");
			boolean looping = false;
			do {
				System.out.println();
				try {
					System.out.println("Enter student username you want to provide grade: ");
					String name = sc.nextLine();
					if(!userNameList.contains(name))
						throw new InvalidUserNameException("Enter valid username");
					System.out.println();
					System.out.println("Enter grade you want to give: ");
					String grade = EnumGrade.valueOf(sc.nextLine()).toString();
					insertGrade(name,grade);
				} catch (IllegalArgumentException iax) {
					logger.error("Please enter valid grade: " + EnumGrade.values().toString());
					return listStudent(studentOption);
				} catch (InvalidUserNameException iune) {
					logger.error("Error generated"+iune.getMessage());
					return listStudent(studentOption);
				}
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
			logger.error("Error generated"+e.getMessage());
		} finally {
			dbConn.closeConnection(conn);
		}
		return studentOption;
	}

	
}
