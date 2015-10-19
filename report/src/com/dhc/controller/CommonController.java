package com.dhc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhc.common.StringUtil;
import com.dhc.entity.User;

/**
 * 
 * @author hanliang 20150617
 * -deal common method or direct url access
 *
 */
@Controller
public class CommonController {
	
	private Logger log = Logger.getLogger(CommonController.class);
	
	@ResponseBody
	@RequestMapping(value = "/pageInit")
	public User pageInit(){
		//just for the page user login check
		return new User();
	}
	
	@RequestMapping("/downloadFile")
    public ModelAndView downloadfile(HttpServletRequest request, HttpServletResponse response) {  
    	File file = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
    	String dlSrcFile = request.getParameter("dlSrcFile");
    	String dlFileName = request.getParameter("dlFileName");
    	if(StringUtil.isNotEmpty(dlSrcFile)) {
    		//获取下载的文件
    		file = new File(dlSrcFile);
    		if(file.exists()) {
				// 以流的形式下载文件
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
					outputStream = new BufferedOutputStream(response.getOutputStream());
					byte[] buffer = new byte[inputStream.available()];
					inputStream.read(buffer);
					inputStream.close();
					// 清空response
					response.reset();
					// 设置response的Header
					// 重命名下载的文件名
					response.addHeader("Content-Disposition", "attachment;filename="
							+new String(dlFileName.getBytes("utf-8"),"ISO-8859-1"));
					response.addHeader("Content-Length", "" + file.length());
					response.setContentType("application/octet-stream");
					outputStream.write(buffer);
					outputStream.flush();
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage(),e);
					e.printStackTrace();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
					e.printStackTrace();
				} 
				finally {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (IOException e) {
							log.error(e.getMessage(),e);
							e.printStackTrace();
						}						
					}
					if (outputStream != null) {
						try {
							outputStream.close();
						} catch (IOException e) {
							log.error(e.getMessage(),e);
							e.printStackTrace();
						}
					}
				}
			}
		}
    	return null;
    }
	

}
