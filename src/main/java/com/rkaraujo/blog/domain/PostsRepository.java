package com.rkaraujo.blog.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Integer> {

	Page<Post> findAll(Pageable pageable);

}
