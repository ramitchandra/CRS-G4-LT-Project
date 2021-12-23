package com.lt.crs.dao;

import com.lt.crs.bean.Student;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public interface StudentDAO {
	
 /**
 * @param student
 */
public void  createStudent(Student student);

 /**
 * @param studentName
 * @param courseName
 */
public void addCourse(String studentName, String courseName);
}
