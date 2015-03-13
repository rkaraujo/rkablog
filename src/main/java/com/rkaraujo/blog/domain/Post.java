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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Entity
@Table(name = "posts", indexes = { @Index(name = "index_slug_title", columnList = "slugTitle", unique = true) })
@EntityListeners(Post.DateTrigger.class)
@JsonAutoDetect(fieldVisibility=Visibility.ANY, creatorVisibility=Visibility.NONE, getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE)
public class Post {
	
	private static final int SUMMARY_CONTENT_CUT_INDEX = 460;

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
	
	@Column
	private String pageDescription;
	
	public String getHtmlContent() {
		StringBuilder sb = new StringBuilder("<p>");

		// replace newLine in between with </p><p>
		boolean isNewParagraph = false;
		for (int i = 0; i < content.length(); i++) {
			char charAt = content.charAt(i);
			
			while (charAt == '\n') {
				charAt = content.charAt(++i);
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

	public String getSummaryContent() {
		int endIndex = content.indexOf('\n');
		if (endIndex == -1) {
			endIndex = content.length();
		}
		
		if (endIndex > SUMMARY_CONTENT_CUT_INDEX) {
			for (int i = SUMMARY_CONTENT_CUT_INDEX; i >= 0; i--) {
				char character = content.charAt(i);
				boolean previousIsNotAComma = (i - 1 >= 0 && content.charAt(i - 1) != ',');
				if (character == ',' || (character == ' ' && previousIsNotAComma)) {
					return content.substring(0, i) + " ...";
				} else if (character == '.') {
					return content.substring(0, i + 1);
				}
			}
		}
		
		return content.substring(0, endIndex);
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

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
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
