package com.dhc.export;


/**
 * 
 * @author hanliang 20150623
 * 
 */
public class ExportUtil {

	private static String path;

	/**
	 * get template path
	 * @return
	 */
	public static String getTemplatePath(){
		//get current path
		String currentPath = ExportUtil.class.getResource("/").getPath();
		path = currentPath.substring(0,currentPath.indexOf("WEB-INF"));
		//add template folder name
		path = path.concat("template/");
		return path;
	}
	
	/**
	 * 
	 * get download path
	 * @return
	 */
	public static String getDownloadPath(){
		//get current path
		String currentPath = ExportUtil.class.getResource("/").getPath();
		path = currentPath.substring(0,currentPath.indexOf("WEB-INF"));
		//add template folder name
		path = path.concat("download/");
		return path;
	}
	
}
