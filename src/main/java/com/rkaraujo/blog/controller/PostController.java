package com.rkaraujo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/posts.html", method = RequestMethod.POST)
	public String create() {
		return "redirect:index";
	}

}
