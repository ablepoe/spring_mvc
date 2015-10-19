package com.dhc.common;

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

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import com.dhc.entity.CrmParam;
import com.dhc.entity.CrmReturn;
import com.dhc.entity.DemoParam;
import com.dhc.entity.DemoReturn;
import com.dhc.entity.IkedaReportAParam;
import com.dhc.entity.ResponseDTO;
import com.dhc.entity.SampleBuyBackAnalysisByMonthParam;
import com.dhc.entity.SampleBuyBackAnalysisByMonthReturn;
import com.dhc.entity.ShoppingAnalyzeParam;
import com.dhc.entity.ShoppingAnalyzeReturn;
import com.dhc.entity.User;
import com.dhc.export.ExportUtil;
import com.dhc.service.CrmService;
import com.dhc.service.DemoService;
import com.dhc.service.IkedaReportAService;
import com.dhc.service.SampleBuyBackAnalysisByMonthService;
import com.dhc.service.ShoppingAnalyzeService;



/**
 * 
 * @author hanliang 20150617
 * -commonUtil methods
 * 
 */
public class CommonUtil {
	
	private static Logger log = Logger.getLogger(CommonUtil.class); 
	
	private static ResourceBundle resb;
	
	static {
		
		try {
			//get the message in properties file
			resb = ResourceBundle.getBundle("message", Locale.getDefault());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
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
	 * check if logined
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
	 * get login_user info
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
	 * set session info
	 * @param session
	 * @param obj
	 */
	public static void setLoginInfo(HttpSession session, User obj) {
		session.setAttribute(session.getId() + Common.SESSIONKEY_LOGIN_INFO, obj);
		session.setAttribute("userName", obj.getUserName());
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
		//获取路径
		String path = ExportUtil.getTemplatePath();
		String basicPath = path + templateName + Common.XLS;
		String downloadPath = ExportUtil.getDownloadPath();
		String downloadFileName = templateName + calendar + Common.XLS;
		String downloadFile = downloadPath + downloadFileName;
		ExcelUtil.clearFolder(downloadPath);
		try{
			//读取模板
			File fs = ExcelUtil.getFile(basicPath);
			Workbook wb = ExcelUtil.getWorkbook(fs);
			List<?> dataReturn = getReturnList(service,param,type);
//			//获取数据总量
			Class<?> clazz = getParamClass(param);
//			//获取具体数据值
			List<String> demoData = excelUtil.transList(dataReturn, clazz);
//			//写入excel
			excelUtil.writeData(dataReturn.size(), wb, demoData, clazz);
			//输出文件
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
		if(param instanceof DemoParam){
			clazz = DemoReturn.class;
			return clazz;
		}
		if(param instanceof CrmParam){
			clazz = CrmReturn.class;
			return clazz;
		}
		if(param instanceof ShoppingAnalyzeParam){
			clazz = ShoppingAnalyzeReturn.class;
			return clazz;
		}
		if(param instanceof SampleBuyBackAnalysisByMonthParam){
			clazz = SampleBuyBackAnalysisByMonthReturn.class;
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
		if(param instanceof DemoParam){
			DemoService demoService = (DemoService) service;
			list = demoService.getDemoList((DemoParam) param);
		}
		if(param instanceof CrmParam){
			CrmService crmService = (CrmService)service;
			list = crmService.getCrmDownloadData((CrmParam) param);
		}
		if(param instanceof ShoppingAnalyzeParam){
			ShoppingAnalyzeService shoppingAnalyzeService = (ShoppingAnalyzeService) service;
			switch((int)type){
			case 1: list = shoppingAnalyzeService.firstShoppingAnalyze((ShoppingAnalyzeParam) param);
				break;
			case 2: shoppingAnalyzeService.truncateTempTable();
					shoppingAnalyzeService.createTempTable((ShoppingAnalyzeParam) param);
					list = shoppingAnalyzeService.secondShoppingAnalyze();
				break;
			default:break;
			}
		}
		if(param instanceof SampleBuyBackAnalysisByMonthParam){
			SampleBuyBackAnalysisByMonthService sampleBuyBackAnalysisByMonthService = (SampleBuyBackAnalysisByMonthService) service;
			List<String> provinceList = sampleBuyBackAnalysisByMonthService.getProvince();
			list = sampleBuyBackAnalysisByMonthService.getSampleBuyBackAnalysisByMonth((SampleBuyBackAnalysisByMonthParam) param, provinceList);
		}
		//param type
		if(param instanceof IkedaReportAParam){
			//get data
			IkedaReportAService ikedaReportAService = (IkedaReportAService) service;
			list = ikedaReportAService.getIkedaReportAExport((IkedaReportAParam) param);
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