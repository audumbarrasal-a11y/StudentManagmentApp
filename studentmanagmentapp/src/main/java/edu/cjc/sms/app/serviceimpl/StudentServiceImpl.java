package edu.cjc.sms.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.cjc.sms.app.controller.AdminController;
import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repository.StudentRepository;
import edu.cjc.sms.app.servicei.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository sr;

	@Override
	public void saveStudentDetails(Student st) {
		sr.save(st);

	}

	@Override
	public List<Student> getAllStudent() {

		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentsByBatch(String batchNumber) {
		List<Student> batchStudents = sr.findAllByBatchNumber(batchNumber);
		return batchStudents;
	}

	@Override
	public void deleteStudent(int studentId) {
		sr.deleteById(studentId);

	}

	@Override
	public List<Student> paging(int pageNo, int i) {
		Pageable page = PageRequest.of(pageNo, i);
		System.out.println(page);
		return sr.findAll(page).getContent();
	}

	@Override
	public void updateStudentFees(int studentid, double ammount) {
		Optional<Student> opStudent = sr.findById(studentid);
		Student st = opStudent.get();
		st.setFeesPaid(st.getFeesPaid() + ammount);
		sr.save(st);

	}

	@Override
	public Student getSingleStudent(int id) {
		Optional<Student> opStudent = sr.findById(id);
		return opStudent.get();
	}

	@Override
	public void updateBatch(int studentid, String batchNumber) {
		Optional<Student> opStudent = sr.findById(studentid);
		Student st = opStudent.get();
		st.setBatchNumber(batchNumber);
		sr.save(st);

	}

}
