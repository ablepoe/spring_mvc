package com.entity;

/**
 * 
 * @author hanliang 20150617
 * -String compare method
 * 
 */
public class StringUtil {
	
	/**
	 * toUpperCase
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (str != null) {
			return str.toUpperCase();
		}
		else {
			return str;
		}
	}
	
	/**
	 * String not null or ""
	 * @param str (null,"","  ")
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(!isNullString(str)) {
			return true;
		}
        return false;
	}
	
	/**
	 * String null or ""
	 * @param str
	 * @return
	 */
	public static boolean isNullString(String str) {
		if(str == null || str.trim().length() == 0) {
			return true;
		}
        return false;
	}
}
