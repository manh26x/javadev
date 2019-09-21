package com.mike.chatapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
	
	@RequestMapping(value = {"/"}, method= RequestMethod.GET)
	public String index() {
		System.out.println("Hello-----------------------------");
		return "index.html";
	}
	
	@RequestMapping("/login")
	public String login(String username, String password, Model model) {
		model.addAttribute("username", username);
		
		return "userinfo.html";
	}
}
