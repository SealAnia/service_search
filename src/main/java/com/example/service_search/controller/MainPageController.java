package com.example.service_search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/welcome")
public class MainPageController {
	
	@GetMapping
	public ModelAndView startApplication() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("MainPage");
		modelAndView.getModel().put("data", "Welcome home man");
		return modelAndView;
	}

}
