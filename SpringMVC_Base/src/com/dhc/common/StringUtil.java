package com.dhc.common;

/**
 * 
 * @author hanliang 20150617
 * -String compare method
 * 
 */
public class StringUtil {
	
	/**
	 * 字符串转大写
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
	 * 字符串不为空或null
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
	 * 字符串为空
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
