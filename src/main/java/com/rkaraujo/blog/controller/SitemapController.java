package com.rkaraujo.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rkaraujo.blog.domain.Post;
import com.rkaraujo.blog.domain.PostRepository;

@Controller
public class SitemapController {

	@Autowired
	private PostRepository postRepository;

	@RequestMapping("/sitemap.xml")
	@ResponseBody
	public String sitemap(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/xml");
		response.setCharacterEncoding("UTF-8");
		String baseDomain = request.getScheme() + "://" + request.getServerName();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
		
		appendUrl(sb, baseDomain, "/index.html");
		Iterable<Post> allPosts = postRepository.findAll();
		for (Post post : allPosts) {
			if (post.getPublishedAt() != null) {
				appendUrl(sb, baseDomain, "/p/" + post.getSlugTitle() + ".html");
			}
		}
		
		sb.append("</urlset>");
		return sb.toString();
	}

	private void appendUrl(StringBuilder sb, String baseDomain, String url) {
		sb.append("<url>");
		sb.append("<loc>").append(baseDomain).append(url).append("</loc>");
		sb.append("</url>");
	}
	
}
