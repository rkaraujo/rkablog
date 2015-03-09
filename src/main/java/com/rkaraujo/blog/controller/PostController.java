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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rkaraujo.blog.domain.Post;
import com.rkaraujo.blog.domain.PostRepository;

@Controller
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@RequestMapping(value = { "/", "/index.html" }, method = RequestMethod.GET)
	public String index(Integer page, Model model) {
		page = page == null ? 0 : page;
		Pageable pageable = new PageRequest(page, 4, new Sort(new Order(Direction.DESC, "publishedAt")));
		Page<Post> posts = postRepository.findByPublishedAtIsNotNull(pageable);
		model.addAttribute("posts", posts.getContent());
		return "index";
	}

	@RequestMapping(value = "/p/{slugTitle}.html", method = RequestMethod.GET)
	public String show(@PathVariable("slugTitle") String slugTitle, Model model) {
		Post post = postRepository.findBySlugTitleAndPublishedAtIsNotNull(slugTitle);
		model.addAttribute("post", post);
		return "show";
	}

}
