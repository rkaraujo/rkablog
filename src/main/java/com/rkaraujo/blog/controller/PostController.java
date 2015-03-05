package com.rkaraujo.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rkaraujo.blog.domain.Post;
import com.rkaraujo.blog.domain.PostsRepository;

@Controller
public class PostController {

	@Autowired
	private PostsRepository postsRepository;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model) {
		Pageable pageable = new PageRequest(0, 25, new Sort(new Order(Direction.DESC, "publishedAt")));
		Page<Post> page = postsRepository.findByPublishedAtIsNotNull(pageable);
		model.addAttribute("posts", page.getContent());
		return "index";
	}

}
