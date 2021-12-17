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

import com.lt.crs.app.MainCRSMenu;
import com.lt.crs.bean.Course;
import com.lt.crs.bean.Grades;
import com.lt.crs.bean.Student;
import com.lt.crs.dao.AdminDAO;
import com.lt.crs.dao.AdminDAOImpl;
import com.lt.crs.dao.StudentDAO;
import com.lt.crs.dao.StudentDAOImpl;
import com.mysql.jdbc.Connection;

public class StudentHandlerImpl implements StudentHandler {
	
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
	
	public void registerForCourse(String username, String courseEnrolled, Connection conn, List<Course> courseList) {
		PreparedStatement stmt = null;
		createDummyStudent();
		String sql= "Select studentId,studentName from student where studentUsername=?";
		String insertEnrolledCourse= "insert into enrolledcourses value (?,?,?,?)";
		List<String> optCourse=Arrays.asList(courseEnrolled.split(","));
		try {
			stmt= conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("studentId"));
				for(String s: optCourse){
					System.out.println(courseList);
					for(Course c: courseList){
						if(c.getCourseName().equalsIgnoreCase(s)){
							stmt= conn.prepareStatement(insertEnrolledCourse);
							stmt.setInt(1, rs.getInt("studentId"));  // This would set age
							stmt.setString(2,rs.getString("studentName"));
							stmt.setInt(3, c.getCourseId());
							stmt.setString(4, s);
							stmt.executeUpdate();

						}
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully Registered Student: " +username);
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
	
	public void viewGrade(String studentName) {
		Grades grade1 = new Grades();
		grade1.setStudentName("Stud1");
		grade1.setStudentGrade('A');
		
		Grades grade2 = new Grades();
		grade2.setStudentName("Stud2");
		grade2.setStudentGrade('B');
		
		studentGrade.add(grade1);
		studentGrade.add(grade2);
		
		for(Grades g : studentGrade) {
			if(g.getStudentName().equalsIgnoreCase(studentName))
				System.out.println("Grades : " + g.getStudentGrade());
		}
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
		System.out.println("Registration successful for student : " + student.getStudentName());
		System.out.println();
		System.out.println();
		
		AdminDAO adminDao = new AdminDAOImpl();
		adminDao.userApproval(student);
		
		MainCRSMenu mainMenu = new MainCRSMenu();
		mainMenu.mainMenu();
	}
}
