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
import com.rkaraujo.blog.domain.PostRepository;
import com.rkaraujo.blog.util.StrUtil;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private PostRepository postRepository;

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public List<Post> posts() {
		// TODO set page
		Pageable pageable = new PageRequest(0, 25, new Sort(new Order(
				Direction.DESC, "publishedAt"), new Order(Direction.DESC,
				"updatedAt")));
		Page<Post> postsPage = postRepository.findAll(pageable);
		return postsPage.getContent();
	}

	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public Post save(@RequestBody Post post) {
		post.setContent(StrUtil.trim(post.getContent(), '\n'));
		post.setSlugTitle(StrUtil.slug(post.getTitle()));
		return postRepository.save(post);
	}

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		postRepository.delete(id);;
	}

	@RequestMapping(value = "/posts/{id}/publish", method = RequestMethod.POST)
	public Post publish(@PathVariable("id") Integer id) {
		Post post = postRepository.findOne(id);
		post.setPublishedAt(new Date());
		postRepository.save(post);
		return post;
	}

}
