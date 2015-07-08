package com.dhc.common;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import com.dhc.entity.User;



/**
 * 
 * @author hanliang 20150617
 * -commonUtil methods
 * 
 */
public class CommonUtil {
	
	private static ResourceBundle resb;
	
	static {
		
		try {
			//get the message in properties file
			resb = ResourceBundle.getBundle("message", Locale.getDefault());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String encodePassword(String pwd) {
		return MD5Util.Encoding(pwd);
	}
	
	public static String getMessage(String key) {
		return resb.getString(key);
	}
	
	public static String getMessage(String key, Object... param) {
		return String.format((String)resb.getString(key), param);
	}

	/**
	 * 判断是否是同一用户/F5
	 * @param sessionUser_name
	 * @param userName
	 * @param password
	 * @param user_pwd
	 * @return
	 */
	public static boolean checkUser(String sessionUser_name, String userName, String password, String user_pwd){
		//检查session中的user_id和password
		if(sessionUser_name !=null && password != null){
			//判断新请求的user_id是否与之前的相同
			if(!sessionUser_name.equals(userName) || !password.equals(user_pwd) ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证是否存在其他选项卡登陆系统
	 * @param session
	 * @return
	 */
	public static boolean checkSystemLogin(HttpSession session) {
		Object obj = session.getAttribute(session.getId() + Common.SESSIONKEY_LOGIN_INFO);
		if(obj == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 清空缓存
	 * @param session
	 */
	public static void clearAllSession(HttpSession session) {
		String temp = "";
		String sessionId = session.getId();
		Enumeration<String> sessionKeys = session.getAttributeNames();
		for(Enumeration<String> e = sessionKeys; e.hasMoreElements();){
		       temp = e.nextElement().toString();
		       if (temp.startsWith(sessionId)) {
		    	   session.removeAttribute(temp);
		       }
		}
		session.removeAttribute("userName");
	}
	
	/**
	 * 验证用户是否登陆
	 * @param session
	 * @return
	 */
	public static User getLoginInfo(HttpSession session) {
		Object obj = session.getAttribute(session.getId() + Common.SESSIONKEY_LOGIN_INFO);
		if (obj == null) {
			return null;
		} else {
			return (User)obj;
		}
	}
	
	/**
	 * 存放session值
	 * @param session
	 * @param obj
	 */
	public static void setLoginInfo(HttpSession session, User obj) {
		session.setAttribute(session.getId() + Common.SESSIONKEY_LOGIN_INFO, obj);
		session.setAttribute("userName", obj.getUserName());
	}
}