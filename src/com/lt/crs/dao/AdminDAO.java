package com.lt.crs.dao;

import com.lt.crs.bean.Student;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public interface AdminDAO {
	
	//public boolean validateUserDao(String username, String password);
	/**
	 * @param studentId
	 */
	public void userApproval(int studentId);
}
