package com.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller  
public class MainController {  
    protected static Logger logger = Logger.getLogger("controller");  
  
    /** 
     * ��ת��commonpageҳ�� 
     *  
     * @return 
     */  
    @RequestMapping(value = "/common")  
    public String getCommonPage() {  
        logger.debug("Received request to show common page");  
        System.out.println("main/common");
        return "commonpage";  
    }  
  
    /** 
     * ��ת��adminpageҳ�� 
     *  
     * @return 
     */  
    @RequestMapping(value = "/admin")  
    public String getAadminPage() {  
        logger.debug("Received request to show admin page");  
        System.out.println("main/admin");
        return "adminpage";  
  
    }  
  
}  
