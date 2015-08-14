package com.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.entity.ResponseDTO;
import com.entity.TempParam;
import com.entity.TempReturn;
import com.service.TempService;


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
	
	public static String getMessage(String key) {
		return resb.getString(key);
	}
	
	public static String getMessage(String key, Object... param) {
		return String.format((String)resb.getString(key), param);
	}

	/**
	 * check the same user/F5
	 * @param sessionUser_name
	 * @param userName
	 * @param password
	 * @param user_pwd
	 * @return
	 */
	public static boolean checkUser(String sessionUser_name, String userName, String password, String user_pwd){
		//get name&password in session
		if(sessionUser_name !=null && password != null){
			//check name equals the last
			if(!sessionUser_name.equals(userName) || !password.equals(user_pwd) ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * clear session
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
	 * get server date
	 * @return
	 */
	public static String getCalendar(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String currentTime = sdf.format(date);
		return "_"+currentTime;
	}

	/**
	 * write data2excel
	 * @param templateName
	 * @param service
	 * @param param
	 * @return
	 */
	@SuppressWarnings("finally")
	public ResponseDTO write2excel(String templateName, Object service, Object param, Object type) throws Exception{
		
		ResponseDTO resp = new ResponseDTO();
		ExcelUtil excelUtil = new ExcelUtil();
		String calendar = CommonUtil.getCalendar();
		//鑾峰彇璺緞
		String path = ExportUtil.getTemplatePath();
		String basicPath = path + templateName + Common.XLS;
		String downloadPath = ExportUtil.getDownloadPath();
		String downloadFileName = templateName + calendar + Common.XLS;
		String downloadFile = downloadPath + downloadFileName;
		ExcelUtil.clearFolder(downloadPath);
		try{
			//璇诲彇妯℃澘
			File fs = ExcelUtil.getFile(basicPath);
			Workbook wb = ExcelUtil.getWorkbook(fs);
			List<?> dataReturn = getReturnList(service,param,type);
//			//鑾峰彇鏁版嵁鎬婚噺
			Class<?> clazz = getParamClass(param);
//			//鑾峰彇鍏蜂綋鏁版嵁鍊�
			List<String> demoData = excelUtil.transList(dataReturn, clazz);
//			//鍐欏叆excel
			excelUtil.writeData(dataReturn.size(), wb, demoData, clazz);
			//杈撳嚭鏂囦欢
			FileOutputStream fo = new FileOutputStream(downloadFile);
			wb.write(fo);
			wb.close();
			fo.close();
			wb.close();
			
			resp.setStatus(Common.STATUS_SUCCESS);
			Map<String,Object> map = new HashMap<String,Object>();
	  	    map.put("dlSrcFile", downloadFile);
	  	    map.put("dlFileName", downloadFileName);
			resp.setData(map);
		} catch (FileNotFoundException e) {
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E1001");
			resp.setMsgContent(CommonUtil.getMessage("E1001"));
			e.printStackTrace();
		} catch (IOException e) {
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E1002");
			resp.setMsgContent(CommonUtil.getMessage("E1002"));
			e.printStackTrace();
		} catch (Exception e) {
			resp.setStatus(Common.STATUS_FAILURE);
			resp.setMsgCode("E1003");
			resp.setMsgContent(CommonUtil.getMessage("E1003"));
			e.printStackTrace();
		} finally{
			excelUtil = null;
			return resp;
		}
	}
	
	/**
	 * get object param
	 * @param param
	 * @return
	 */
	public Class<?> getParamClass(Object param){
		Class<?> clazz = null;
		if(param instanceof TempParam){
			clazz = TempReturn.class;
			return clazz;
		}
		return clazz;
	}
	
	/**
	 * get returnList
	 * @param service
	 * @param param
	 * @return
	 */
	public List<?> getReturnList(Object service, Object param, Object type){
		List<?> list = null;
		if(param instanceof TempParam){
			TempService demoService = (TempService) service;
			list = demoService.getTempList((TempParam) param);
		}
		return list;
	}
	
	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param fieldName
	 * @return
	 */
	public static String getRowFilterString(long pageNumber, long pageSize, String fieldName) {
		long rowStart;
		long rowEnd;
		if (pageNumber <= 0 || pageSize <= 0) {
			rowStart = 1;
			rowEnd = 1;
		} else {
			rowStart = (pageNumber - 1) * pageSize + 1;
			rowEnd = rowStart + pageSize - 1;
		}
		
		return fieldName + " >= " + rowStart + " AND " + fieldName + " <= " + rowEnd; 
	}
	
	
}