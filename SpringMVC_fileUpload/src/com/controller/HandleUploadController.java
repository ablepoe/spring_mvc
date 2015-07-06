package com.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author hanliang 2015/05/28
 * -handle file upload
 */
@Controller
public class HandleUploadController{

	//this fileName reference the bean(configProperties) in spring-servlet.xml
	//inside bean(configProperties) value reference the property_file location
	//${_properties_name}
	@Value("${fileName}") 
	private String fileDir;
	
	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	@RequestMapping(value = "/handleUpload")
	public ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		//set the response head
		res.setContentType("text/plain");
		res.setCharacterEncoding("utf-8");
		//request type check
		if(!(req instanceof MultipartHttpServletRequest)){
			res.sendError(HttpServletResponse.SC_BAD_REQUEST,"Expected mutipart request");
		}
		//type change
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		//get file
		MultipartFile multipart = multipartRequest.getFile("uploaded");
		//get fileName
		String fileName = multipart.getOriginalFilename();
		//get file suffix
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		//file suffix check
		if ((".zip.rar.gz.tar.bz2.txt".indexOf(suffix.toLowerCase()) != -1)) {
			//jump page to upload.html
			req.getRequestDispatcher("/Upload.html").forward(req,res);
			//make the file
			File file = new File(fileDir+"\\"+fileName);
			//generate the file
			multipart.transferTo(file);    
		}else{
			//type not pass
			//return msg
			res.getWriter().write("Error, type is not in .zip.rar.gz.tar.bz2.txt");
			res.flushBuffer();
		}
		return null;
	}
}
