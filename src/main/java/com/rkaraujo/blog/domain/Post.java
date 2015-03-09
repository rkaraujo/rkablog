package com.rkaraujo.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.rkaraujo.blog.util.StrUtil;

@Entity
@Table(name = "posts", indexes = { @Index(name = "index_slug_title", columnList = "slugTitle", unique = true) })
@EntityListeners(Post.DateTrigger.class)
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String slugTitle;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(nullable = false)
	private Date createdAt;

	@Column(nullable = false)
	private Date updatedAt;

	@Column
	private Date publishedAt;
	
	public String getHtmlContent() {
		if (content == null) {
			return null;
		}
		String htmlContent = StrUtil.startTrim(content, '\n');
		htmlContent = StrUtil.endTrim(htmlContent, '\n');
		
		StringBuilder sb = new StringBuilder("<p>");

		// replace newLine in between with </p><p>
		boolean isNewParagraph = false;
		for (int i = 0; i < htmlContent.length(); i++) {
			char charAt = htmlContent.charAt(i);
			
			while (charAt == '\n') {
				charAt = htmlContent.charAt(++i);
				isNewParagraph = true;
			}
			
			if (!isNewParagraph) {
				sb.append(charAt);
			} else {
				sb.append("</p><p>").append(charAt);
				isNewParagraph = false;
			}
		}
		
		sb.append("</p>");
		
		return sb.toString();
	}

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

	public String getSlugTitle() {
		return slugTitle;
	}

	public void setSlugTitle(String slugTitle) {
		this.slugTitle = slugTitle;
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
