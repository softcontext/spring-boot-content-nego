package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.web.model.Users;
import com.example.web.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * http://localhost:8080/download --> JSP
	 * http://localhost:8080/download.html --> JSP
	 * http://localhost:8080/download.pdf
	 * http://localhost:8080/download.xls
	 * http://localhost:8080/download.xml
	 * http://localhost:8080/download.json
	 * http://localhost:8080/download.csv
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(HttpServletRequest request) {
		System.out.println("UserController > "+request.getRequestURL());
		
		ModelAndView mav = new ModelAndView("download");
		mav.addObject("users-info", new Users(userService.findAllUsers()));
		return mav;
	}

}
