package com.gome.generator.utils;

public class StringUtil {

	// add by wuhq 2011.11.30. copy from http://ben-sin.iteye.com/blog/659611
	public static String[] split(String s, String delimiter) {
		if (s == null) {
			return null;
		}
		int delimiterLength;
		int stringLength = s.length();
		if (delimiter == null || (delimiterLength = delimiter.length()) == 0) {
			return new String[] { s };
		}

		// a two pass solution is used because a one pass solution would
		// require the possible resizing and copying of memory structures
		// In the worst case it would have to be resized n times with each
		// resize having a O(n) copy leading to an O(n^2) algorithm.

		int count;
		int start;
		int end;

		// Scan s and count the tokens.
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			count++;
			start = end + delimiterLength;
		}
		count++;

		// allocate an array to return the tokens,
		// we now know how big it should be
		String[] result = new String[count];

		// Scan s again, but this time pick out the tokens
		count = 0;
		start = 0;
		while ((end = s.indexOf(delimiter, start)) != -1) {
			result[count] = (s.substring(start, end));
			count++;
			start = end + delimiterLength;
		}
		end = stringLength;
		result[count] = s.substring(start, end);

		return (result);
	}

	public static String trim(Object src) {
		if (src == null)
			return "";
		return src.toString().trim();
	}

	public static boolean isEmpty(String src) {
		return src == null || src.equals("");
	}
	
	/**
	 * 将类的包名过滤，只返回类名 的数组<br>如果只有类名，则直接返回类名
	 * */
	public static String classNameSubName(String className) {
		int index = className.lastIndexOf(".");
		if (index != -1) {
			return className.substring(index + 1);
		}
		return className;
	}
	
	/**
	 * 字符串首个字母大写
	 */
	public static String firstToUpperCase(String str) {
		char trimChars[] = str.toCharArray();
		trimChars[0] = Character.toUpperCase(trimChars[0]);
		return new String(trimChars);
	}
	/**
	 * 字符串首个字母小写
	 */
	public static String firstToLowerCase(String str) {
		char trimChars[] = str.toCharArray();
		trimChars[0] = Character.toLowerCase(trimChars[0]);
		return new String(trimChars);
	}
	
	/**
	 * 字符串小写
	 */
	public static String toLowerCase(String str) {
		return str.toLowerCase();
	}
	/**
	 * 替换- 并
	* @Title: replace_ 
	* @Description: 
	* @param str
	* @return    设定文件
	 */
	public static String replace_(String str){
		String a[]=str.split("_");
		StringBuilder sb=new StringBuilder();
		sb.append(a[0]);
		for (int i = 1; i < a.length; i++) {
			char trimChars[] = a[i].toCharArray();
			trimChars[0] = Character.toUpperCase(trimChars[0]);
			sb.append(new String(trimChars));
		}
		return sb.toString();
	}
	
}
