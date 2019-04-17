package com.stu.untils;

public class CheckStr {
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str)) {
			return false;
		}
		return true;
	}
}
