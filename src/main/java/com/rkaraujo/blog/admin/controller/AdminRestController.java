package com.rkaraujo.blog.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkaraujo.blog.domain.Post;
import com.rkaraujo.blog.domain.PostsRepository;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private PostsRepository postsRepository;
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public List<Post> posts(Pageable pageable) {
		Page<Post> postsPage = postsRepository.findAll(pageable);
		return postsPage.getContent();
	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.PUT)
	public Post create(@RequestBody Post post) {
		return postsRepository.save(post);
	}
	
}
