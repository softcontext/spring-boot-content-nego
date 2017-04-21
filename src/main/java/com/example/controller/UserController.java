package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.web.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * http://localhost:8080/download
	 * http://localhost:8080/download.pdf
	 * http://localhost:8080/download.xls
	 * http://localhost:8080/download.json
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download() {
		ModelAndView mav = new ModelAndView("download");
		mav.addObject("users", userService.findAllUsers());
		return mav;
	}

}
