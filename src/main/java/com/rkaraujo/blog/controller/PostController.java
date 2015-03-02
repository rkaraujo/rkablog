package com.rkaraujo.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rkaraujo.blog.domain.PostsRepository;

@Controller
public class PostController {

	@Autowired
	private PostsRepository postsRepository;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("posts", postsRepository.findAll());
		return "index";
	}

	@RequestMapping(value = "/posts.html", method = RequestMethod.POST)
	public String create() {
		return "redirect:index";
	}

}
