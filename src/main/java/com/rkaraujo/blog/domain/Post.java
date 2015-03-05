package com.rkaraujo.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@EntityListeners(Post.DateTrigger.class)
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@Column(nullable = false)
	private Date createdAt;
	
	@Column(nullable = false)
	private Date updatedAt;
	
	@Column
	private Date publishedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public static class DateTrigger {
		@PrePersist
		public void create(Post post) {
			post.setCreatedAt(new Date());
			post.setUpdatedAt(new Date());
		}

		@PreUpdate
		public void update(Post post) {
			post.setUpdatedAt(new Date());
		}
	}
	
}
