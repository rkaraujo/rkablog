package com.rkaraujo.blog.domain;

import org.junit.Assert;
import org.junit.Test;

public class PostTest {

	@Test
	public void testGetHtmlContent() throws InterruptedException {
		String content = "\n\n\nHello World.\n\n\nTesting1,2,3 world\n\n";

		Post post = new Post();
		post.setContent(content);

		Assert.assertEquals(
				"<p>Hello World.</p><p>Testing1,2,3 world</p>",
				post.getHtmlContent());
	}

}
