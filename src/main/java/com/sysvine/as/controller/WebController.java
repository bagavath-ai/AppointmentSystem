package com.sysvine.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String appointmentScreen(Model model) {
		return "AppointmentScreen.html";
	}

}
