package edu.cjc.sms.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

	@RequestMapping("/")
	public String preLogin()
	{
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam String username, @RequestParam String password, Model m) {
		
		if(username.equals("admin") && password.equals("admin"))
		{
			return"adminscreen";
		}else 
		{
			m.addAttribute("login_fail","Enter valid login details..");
			return "login";
		}
	}
}
