package edu.cjc.sms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.servicei.StudentService;

@Controller
public class AdminController {

	@Autowired
	StudentService ss;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}

	@RequestMapping("/login")
	public String onLogin(@RequestParam String username, @RequestParam String password, Model m) {

		if (username.equals("admin") && password.equals("admin")) {
			List<Student> students = ss.getAllStudent();
			m.addAttribute("data", students);
			return "adminscreen";
		} else {
			m.addAttribute("login_fail", "Enter valid login details..");
			return "login";
		}
	}

	@RequestMapping("enroll_student")
	public String saveStudent(@ModelAttribute Student student, Model m) {
		ss.saveStudentDetails(student);
		List<Student> students = ss.getAllStudent();
		return "adminscreen";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id, Model m) {
		ss.deleteStudent(id);
		List<Student> student = ss.getAllStudent();
		m.addAttribute("data", student);
		return "adminscreen";
	}

	@RequestMapping("/paging")
	public String paging(@RequestParam("pageNo") int pageNo, Model m) {
		List<Student> list = ss.paging(pageNo, 10);
		m.addAttribute("data", list);
		return "adminscreen";

	}

	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam String batchNumber, Model m) {
		List<Student> result = ss.searchStudentsByBatch(batchNumber);
		if (result.size() > 0) {
			m.addAttribute("data", result);
		} else {
			List<Student> students = ss.getAllStudent();
			m.addAttribute("data", students);
			m.addAttribute("message", "No recode are available for this batch " + batchNumber);
		}
		return "adminscreen";
	}

	@RequestMapping("/fees")
	public String onFees(@RequestParam int id, Model m) {
		Student st = ss.getSingleStudent(id);
		m.addAttribute("st", st);
		return "fees";
	}

	@RequestMapping("/payfees")
	public String updateFees(@RequestParam int studentid, @RequestParam double ammount, Model m) {
		ss.updateStudentFees(studentid, ammount);
		List<Student> students = ss.getAllStudent();
		m.addAttribute("data", students);
		return "adminscreen";

	}

	@RequestMapping("/changeBatch")
	public String onShift(@RequestParam int id, Model m) {
		Student st = ss.getSingleStudent(id);
		m.addAttribute("st", st);
		return "batch";
	}

	@RequestMapping("/batchShift")
	public String updateBatch(@RequestParam int studentid, @RequestParam String batchNumber, Model m) {
		ss.updateBatch(studentid, batchNumber);
		List<Student> students = ss.getAllStudent();
		m.addAttribute("data", students);
		return "adminscreen";

	}
}
