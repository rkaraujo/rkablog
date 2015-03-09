package com.rkaraujo.blog.util;

public final class StrUtil {

	public static final String slug(String str) {
		return str.toLowerCase().replaceAll("\\W", " ").replaceAll("\\s+", "-");
	}

	public static final String trim(String str, char c) {
		int start = 0;
		while (str.charAt(start) == c) {
			start++;
		}

		int end = str.length() - 1;
		while (str.charAt(end) == c) {
			end--;
		}

		return str.substring(start, end + 1);
	}
}
