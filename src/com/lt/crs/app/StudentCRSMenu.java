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
import com.lt.crs.business.StudentHandler;
import com.lt.crs.business.StudentHandlerImpl;
import com.lt.crs.utils.DbUtils;
import com.mysql.jdbc.Connection;

public class StudentCRSMenu {
	
	static Scanner sc = new Scanner(System.in);
	
	DbUtils dbConn= new DbUtils();
	Connection conn= null;
	PreparedStatement stmt = null;
	
	public void courseRegistration(String username) throws SQLException {
		studentMenu();
		conn=(Connection) dbConn.createConnection();
		StringBuilder coursesEnrolled = new StringBuilder();
		CourseHandler courseHandler = new CourseHandlerImpl();
		courseHandler.createCourse();
		List<Course> courseList	= new ArrayList<>();
		int studentOption = sc.nextInt();
		sc.nextLine();
		do{
		if(studentOption==1) {
			StudentHandler sh= new StudentHandlerImpl();
			sh.registerForCourse(username,coursesEnrolled.toString(), conn, courseList);
		}else if(studentOption==2){
			
		studentOption=	addCourseHandling(coursesEnrolled, courseList,studentOption);
			
		}else if(studentOption==3){
			studentOption= dropCourseHandling(coursesEnrolled,studentOption);
		}else{
			System.out.println("Invalid Input");
		}}while(studentOption>0 && studentOption<4);
	//	retry:
//		switch(studentOption) {
//		case 1 : 	StudentHandler sh= new StudentHandlerImpl();
//					sh.registerForCourse(username,coursesEnrolled.toString(), conn, courseList);
//			
//					break;
//		case 2 :	addCourseHandling(coursesEnrolled, courseList);
//					break retry;
//					
//		case 3 :	dropCourseHandling(coursesEnrolled);
//					break retry;
////		case 4 :	sh.viewGrade(username);
////					break;
////		case 5 :	sh.payFees(username);
////					break;
//		default :	System.out.println("Invalid input");
//		}
	}

	private int dropCourseHandling(StringBuilder coursesEnrolled,int studentOption) {
				System.out.println("Course Added: " +coursesEnrolled.toString());
					System.out.println("Select the courses you want to remove: ");
					String tobeRemoved=sc.nextLine();
					coursesEnrolled.replace(coursesEnrolled.indexOf(tobeRemoved)-1, tobeRemoved.length()+ coursesEnrolled.indexOf(tobeRemoved), "");
					System.out.println("Finally Added Courses: "+coursesEnrolled.toString());			
					studentMenu();
					studentOption = sc.nextInt();
					sc.nextLine();
					return studentOption;
	}

	private int addCourseHandling(StringBuilder coursesEnrolled, List<Course> courseList,int studentOption) throws SQLException {
		
		System.out.println();
					System.out.println("Available Courses");
					System.out.println("-----------------");	
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
					System.out.println(studentOption);
					sc.nextLine();
					return studentOption;
				}

	private void studentMenu() {
		System.out.println();
		System.out.println("Please select the appropriate option");
		System.out.println("------------------------------------");
		System.out.println("1. Register for course");
		System.out.println("2. Add Course");
		System.out.println("3. Drop Course");
		System.out.println("4. View Grade");
		System.out.println("5. Pay Fee");
	}
}
