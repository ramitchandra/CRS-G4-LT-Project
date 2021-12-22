package com.lt.crs.constants;

/**
 * @author Naman,Purnima,Radha,Ramit,Venisraj,Vignesh
 *
 */
public class SqlConstants {
	public static final  String userQuery = "select * from user";
	public static final String roleQuery="select role from role where id =";
	public static final String studentInsertQuery = "insert into student values(?,?,?,?,?)";
	public static final String courseInsertQuery ="insert into course values(?,?,?,?,?)";
	public static final String roleInsertQuery="insert into role values(?,?)";
	public static final String userInsertQuery="insert into user values(?,?,?,?,?)";
	public static final String roleIdQuery ="select id from role where role = 'Student'";
	public static final String pendingApprovalQuery ="select * from user where isApproved = false";
	public static final String studentGradeQuery ="Select studentGrade from grades where studentId=?";
	public static final String InsertGradeQuery ="insert into grades values (?,?)";
	public static final String selectStudentQuery ="Select studentId from student where studentUsername=?";
	public static final String selectStudentdetailsQuery ="Select studentId,studentName from student where studentUsername=?";
	public static final String listStudentQuery ="Select * from student where studentId in (select distinct studentId from EnrolledCourses where studentId not in(select distinct studentId from grades))";
	public static final String selectEnrolledCoursesQuery ="Select courseName from enrolledcourses where studentName = '";
	public static final String selectEnrolledCourseIdQuery="select courseId from enrolledcourses where studentName=?";
	public static final String insertEnrolledCoursesQuery ="insert into enrolledcourses value (?,?,?,?)";
	public static final String userApprovalQuery = "UPDATE user SET isApproved = true WHERE userid = ";
	public static final String insertPaymentQuery = "insert into payment (paymentMode, courseAmout, studentUsername) values (?,?,?)";
	public static final String paymentAmountQuery = "SELECT distinct (course.onlineAmount) FROM course inner JOIN enrolledcourses ON course.courseId = ?";
	public static final String insertCardDetailsQuery ="insert into carddetails values(?,?,?,?)";
	public static final String selectUserdetailsQuery ="Select * from user where username=?";
	public static final String updateUserPasswordQuery ="UPDATE user SET userPassword='";
	public static final String updateStudentPasswordQuery = "UPDATE student SET studentPassword='";
	public static final String selectCourseQuery = "SELECT * FROM course";
}
