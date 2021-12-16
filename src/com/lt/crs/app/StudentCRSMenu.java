package com.lt.crs.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.business.CourseHandler;
import com.lt.crs.business.CourseHandlerImpl;
import com.lt.crs.utils.DbConnection;
import com.mysql.jdbc.Connection;

public class StudentCRSMenu {
	
	static Scanner sc = new Scanner(System.in);
	
	DbConnection dbConn= new DbConnection();
	Connection conn= null;
	PreparedStatement stmt = null;
	
	public void courseRegistration(String username) throws SQLException {
		studentMenu();
		CourseHandler courseHandler = new CourseHandlerImpl();
		courseHandler.createCourse();
		int studentOption = sc.nextInt();
		sc.nextLine();
		retry:
		switch(studentOption) {
//		case 1 : 	sh.registerForCourse(studentOption, studentOption);
//					break;
		case 2 :	System.out.println("Available Courses");
					StringBuilder coursesEnrolled = new StringBuilder();
					List<Course> courseList	= new ArrayList<>();		
					conn=(Connection) dbConn.createConnection();			
					String sql = "SELECT * FROM course";
					stmt= conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						Course course = new Course();
						course.setCourseId(rs.getInt("courseId"));
						course.setCourseName(rs.getString("courseName"));
						course.setCourseAvailable(rs.getBoolean("courseAvailable"));
						course.setOfflieFees(rs.getInt("offlineAmount"));
						course.setOnlineFees(rs.getInt("onlineAmount"));
						courseList.add(course);
					}
					System.out.println(courseList);
					boolean furtherRequired = false;
					do {
						String courseName = sc.nextLine();
						coursesEnrolled.append(courseName);
						System.out.println("Want to add more course : (y/n)");
						String input =  sc.nextLine();
						if("Y".equalsIgnoreCase(input)) {
							furtherRequired = true;
							coursesEnrolled.append(",");
						}
						else {
							furtherRequired = false;
						}
						
					} while (furtherRequired);
					System.out.println("Courses selected " + coursesEnrolled.toString());
					System.out.println("Select further operation");
					studentMenu();
					studentOption = sc.nextInt();
					sc.nextLine();
					break retry;
					
//		case 3 :	System.out.println("Course to be removed");
//					String courseNameTobeRemoved = sc.nextLine();
//					sh.dropCourse(username, courseNameTobeRemoved);
//					break;
//		case 4 :	sh.viewGrade(username);
//					break;
//		case 5 :	sh.payFees(username);
//					break;
		default :	System.out.println("Invalid input");
		}
	}

	private void studentMenu() {
		System.out.println("Please select the appropriate option");
		System.out.println("1. Register for course");
		System.out.println("2. Add Course");
		System.out.println("3. Drop Course");
		System.out.println("4. View Grade");
		System.out.println("5. Pay Fee");
	}
}
