package com.lt.crs.business;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.app.MainCRSMenu;
import com.lt.crs.app.StudentCRSMenu;
import com.lt.crs.bean.Course;
import com.lt.crs.bean.Grades;
import com.lt.crs.bean.Student;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.dao.StudentDAO;
import com.lt.crs.dao.StudentDAOImpl;
import com.lt.crs.exception.CourseAlreadyRegisteredException;
import com.lt.crs.exception.NoCourseSelectedException;
import com.mysql.jdbc.Connection;

public class StudentHandlerImpl implements StudentHandler {
	private static Logger logger = Logger.getLogger(StudentHandlerImpl.class);
	
	List<Student> studentList = new ArrayList<>();
	Map<String, String> studentCred = new HashMap<>();
	List<Grades> studentGrade = new ArrayList<>();
	List<Course> courseList = CourseHandlerImpl.courseList;
	Scanner sc = new Scanner(System.in);
	
	StudentDAO studentDao= new StudentDAOImpl();
	
	public void createDummyStudent() {
		Student student1 = new Student();
		student1.setStudentId(0001);
		student1.setStudentEmail("student1@gmail.com");
		student1.setStudentName("Stud1");
		student1.setStudentPassword("0001");
		student1.setStudentUsername("Stud1");
		studentCred.put(student1.getStudentName(), student1.getStudentPassword());
		
		Student student2 = new Student();
		student2.setStudentId(0002);
		student2.setStudentEmail("student2@gmail.com");
		student2.setStudentName("Stud2");
		student2.setStudentPassword("0002");
		student2.setStudentUsername("Stud2");
		studentCred.put(student2.getStudentName(), student2.getStudentPassword());
		
		
		studentDao.createStudent(student1);
		studentDao.createStudent(student2);
	}
	
	public boolean validateStudent(String username, String password) {
		if(studentCred.get(username).equalsIgnoreCase(password))
			return true;
		return false;
	}
	
	public int registerForCourse(String username, String courseEnrolled, Connection conn, List<Course> courseList, int studentOption) {

		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		String sql= SqlConstants.selectStudentdetailsQuery;
		String sql2= SqlConstants.selectEnrolledCoursesQuery+username+"'";
		String insertEnrolledCourse= SqlConstants.insertEnrolledCoursesQuery;
		List<String> optCourse=Arrays.asList(courseEnrolled.split(","));
		List<String> uptCourse= new ArrayList<String>(optCourse);
		List<String> removedCourse= new ArrayList<String>();
		if(courseEnrolled.isEmpty()) {
			//System.out.println("Please add course before registering");
			try {
			throw new NoCourseSelectedException ("Please add course before registering");
			}
			catch(NoCourseSelectedException ncs) {
				
				logger.error("Error generated"+ncs.getMessage());
			}
		}
		else {
		try {
			stmt2= conn.prepareStatement(sql2);
			ResultSet rs2 = stmt2.executeQuery();
			while(rs2.next()) {		
				optCourse.forEach(co->{
					try {
						if(rs2.getString(1).equalsIgnoreCase(co)) {
							uptCourse.remove(co);
							removedCourse.add(co);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});
			}
			String listString = String.join(", ", removedCourse);
			if(!listString.isEmpty())
			throw new CourseAlreadyRegisteredException (listString+" Already Registered.");
			
		}catch (CourseAlreadyRegisteredException calr) {
		// TODO Auto-generated catch block
			logger.error("Error generated"+calr.getMessage());
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error generated"+e.getMessage());
		} 
		if(!uptCourse.isEmpty()) {
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				uptCourse.forEach(s-> {
					courseList.forEach(c->{
						if(c.getCourseName().equalsIgnoreCase(s)){
							try {
								PreparedStatement stmt3 = null;
								stmt3= conn.prepareStatement(insertEnrolledCourse);
								stmt3.setInt(1, rs.getInt("studentId"));
								stmt3.setString(2,rs.getString("studentName"));
								stmt3.setInt(3, c.getCourseId());
								stmt3.setString(4, s);
								stmt3.executeUpdate();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					});
				});
			}
		} catch (SQLException e) {
			logger.error("Error generated"+e.getMessage());
		}
		String registerd = String.join(", ", uptCourse);

		System.out.println();
		NotificationHandler nh = new NotificationHandlerImpl();
		nh.courseRegistrationNotification(username, registerd);
		}
		else {
			System.out.println("Please add other courses");
		}
		}
		System.out.println();
		System.out.println("Select further operation");
		StudentCRSMenu menu = new StudentCRSMenu();
		menu.studentMenu();
		studentOption = sc.nextInt();
		sc.nextLine();
		return studentOption;
	
	}
	
//	public void addCourse(String studentName, String courseName) {
//		for(Student s : studentList) {
//			if(s.getStudentName().equalsIgnoreCase(studentName)) {
//				for(Course course : courseList) {
//					if(course.getCourseName().equalsIgnoreCase(courseName)) {
//						s.setCourseList(new Course[] {course});
//						System.out.println("Course added successfully : " + courseName);
//						return;
//					}
//				}
//			}
//		}
//	}
//	
//	public void dropCourse(String studentName, String courseName) {
//		for(Student s : studentList) {
//			if(s.getStudentName().equalsIgnoreCase(studentName)) {
//				s.setCourseList(new Course[] {});
//				System.out.println("Course removed successfully : " + courseName);
//			}
//		}
//	}
	
	public int viewGrade(String studentName,int studentOption) {
		System.out.println();
		System.out.println("Please Confirm your StudentID: ");
		int inputId = sc.nextInt();
		sc.nextLine();
		ProfessorHandler ph = new ProfessorHandlerImpl();
		String gradeResult =ph.viewGrades(inputId);
		if(gradeResult != null){
			System.out.println("Your Grade is: "+gradeResult);
		}else{
			System.out.println("Invalid studentID");
		}
		System.out.println();
		System.out.println("Select further operation");
		StudentCRSMenu menu = new StudentCRSMenu();
		menu.studentMenu();
		studentOption = sc.nextInt();
		sc.nextLine();
		return studentOption;
		
	}
	
//	public void payFees(String studentName) {
//		int finalAmount = 0;
//		for(Student s : studentList) {
//			if(s.getStudentName().equalsIgnoreCase(studentName)) {
//				for(Course c : s.getCourseList())
//					finalAmount += c.getOnlineFees();
//			}
//		}
//		System.out.println("Amount to be paid - " + finalAmount);
//	}
	
	public void createStudent() {
		Student student = new Student();
		System.out.println();
		System.out.println("Enter StudentId: ");
		student.setStudentId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter StudentName: ");
		student.setStudentName(sc.nextLine());
		System.out.println("Enter StudentUsername: ");
		student.setStudentUsername(sc.nextLine());
		System.out.println("Enter StudentEmail: ");
		student.setStudentEmail(sc.nextLine());
		System.out.println("Enter StudentPassword: ");
		student.setStudentPassword(sc.nextLine());
		
		studentDao.createStudent(student);
		System.out.println();
		NotificationHandler nh = new NotificationHandlerImpl();
		nh.registrationSuccessfulNotification(student.getStudentUsername());
		System.out.println();
		System.out.println();
		
		
		
		MainCRSMenu mainMenu = new MainCRSMenu();
		mainMenu.mainMenu();
	}
	
	@Override
	public int payFees(String username, int studentOption) {
		PaymentHandler paymentHandler = new PaymentHandlerImpl();
		paymentHandler.checkPayment(username);
		
		studentOption = sc.nextInt();
		
		return studentOption;
		
	}
	
}
