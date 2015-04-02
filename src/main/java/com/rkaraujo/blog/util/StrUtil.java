package com.rkaraujo.blog.util;

public final class StrUtil {

	public static final String slug(String str) {
		str = remove(str, ".");
		str = str.replaceAll("\\W", " ").replaceAll("\\s+", "-");
		str = trim(str, '-');
		return str.toLowerCase();
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

	public static final String remove(String str, String charsToRemove) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (charsToRemove.indexOf(str.charAt(i)) < 0) {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}

}
