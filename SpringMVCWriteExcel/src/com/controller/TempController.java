package com.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Common;
import com.common.CommonUtil;
import com.entity.ResponseDTO;
import com.entity.StringUtil;
import com.entity.TempParam;
import com.service.TempService;

@Controller
public class TempController {

	@Autowired
	private TempService tempService;
	
	
	@ResponseBody
	@RequestMapping(value = "/getExport")
	public ResponseDTO getExport(@RequestBody TempParam tempParam,HttpServletRequest req) {

		ResponseDTO resp = new ResponseDTO();
		CommonUtil commonUtil;
		try{
			commonUtil = new CommonUtil();
			resp = commonUtil.write2excel(Common.TEMP_TEMPLATE_NAME,tempService,tempParam,null);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			commonUtil = null;
		}
		return resp;
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
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				finally {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}						
					}
					if (outputStream != null) {
						try {
							outputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
    	return null;
    }
	
}
