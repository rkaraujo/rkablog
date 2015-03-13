package com.rkaraujo.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = { "/", "/index.html" }, method = RequestMethod.GET)
	public String index() {
		return "admin/index";
	}

}
