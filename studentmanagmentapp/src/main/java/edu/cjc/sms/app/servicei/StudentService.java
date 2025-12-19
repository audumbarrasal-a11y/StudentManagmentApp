package edu.cjc.sms.app.servicei;

import java.util.List;

import edu.cjc.sms.app.model.Student;


public interface StudentService {

	public void saveStudentDetails(Student st);

	public List<Student> getAllStudent();
	
	public List<Student>searchStudentsByBatch(String batchNumber);
	
	public void deleteStudent(int studentId);
	public List<Student> paging(int pageNo, int i);
	public void updateStudentFees(int studentid, double ammount);

	public Student getSingleStudent(int id);

	public void updateBatch(int studentid, String batchNumber);

}
