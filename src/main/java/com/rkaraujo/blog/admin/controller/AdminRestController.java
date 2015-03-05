package com.rkaraujo.blog.admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Post> posts() {
		Pageable pageable = new PageRequest(0, 25, new Sort(new Order(
				Direction.DESC, "publishedAt"), new Order(Direction.DESC,
				"updatedAt")));
		Page<Post> postsPage = postsRepository.findAll(pageable);
		return postsPage.getContent();
	}

	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public Post create(@RequestBody Post post) {
		return postsRepository.save(post);
	}

	@RequestMapping(value = "/posts/{id}/publish", method = RequestMethod.POST)
	public Post publish(@PathVariable("id") Integer id) {
		System.out.println(id);
		Post post = postsRepository.findOne(id);
		post.setPublishedAt(new Date());
		postsRepository.save(post);
		return post;
	}

}
