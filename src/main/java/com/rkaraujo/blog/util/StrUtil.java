package com.rkaraujo.blog.util;

public final class StrUtil {

	public static final String slug(String str) {
		return str.toLowerCase().replaceAll("\\W", " ").replaceAll("\\s+", "-");
	}

	public static final String startTrim(String str, char c) {
		int i = 0;
		while (str.charAt(i) == '\n') {
			i++;
		}
		return str.substring(i, str.length());
	}

	public static final String endTrim(String content, char c) {
		int i = content.length() - 1;
		while (content.charAt(i) == '\n') {
			i--;
		}
		return content.substring(0, i + 1);
	}
}
