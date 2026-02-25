package com.aspera.neurosocial.web_service.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aspera.neurosocial.common.db_service.DbService;


@Controller
public class MainController {
	@GetMapping("/")
	public String home(Model model) {
		DbService db = new DbService();
		Connection connection = db.getConnection();	

		model.addAttribute("author", "Meida");
		model.addAttribute("timestamp", "time");
		model.addAttribute("message", "Neurosocial");
		model.addAttribute("rating", "0");
		
		return "home";
	}
}
