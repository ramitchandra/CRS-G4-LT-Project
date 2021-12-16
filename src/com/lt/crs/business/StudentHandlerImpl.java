package com.lt.crs.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Grades;
import com.lt.crs.bean.Student;
import com.lt.crs.dao.StudentDAO;
import com.lt.crs.dao.StudentDAOImpl;

public class StudentHandlerImpl implements StudentHandler {
	
	List<Student> studentList = new ArrayList<>();
	Map<String, String> studentCred = new HashMap<>();
	List<Grades> studentGrade = new ArrayList<>();
	List<Course> courseList = CourseHandlerImpl.courseList;
	
	StudentDAO studentDao= new StudentDAOImpl();
	
	public void createStudent() {
		Student student1 = new Student();
		student1.setStudentId(0001);
		student1.setStudentEmail("student1@gmail.com");
		student1.setStudentName("Stud1");
		student1.setStudentPassword("0001");
		studentCred.put(student1.getStudentName(), student1.getStudentPassword());
		
		Student student2 = new Student();
		student2.setStudentId(0002);
		student2.setStudentEmail("student2@gmail.com");
		student2.setStudentName("Stud2");
		student2.setStudentPassword("0002");
		studentCred.put(student2.getStudentName(), student2.getStudentPassword());
		
		
		studentDao.createStudent(student1);
		studentDao.createStudent(student2);
	}
	
	public boolean validateStudent(String username, String password) {
		if(studentCred.get(username).equalsIgnoreCase(password))
			return true;
		return false;
	}
	
	public void registerForCourse(int student, int courseId) {
		System.out.println("Registered");
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
}
