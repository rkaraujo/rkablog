package com.rkaraujo.blog.util;

public final class StrUtil {

	public static final String slug(String str) {
		return str.toLowerCase().replaceAll("\\W", " ").replaceAll("\\s+", "-");
	}

}
