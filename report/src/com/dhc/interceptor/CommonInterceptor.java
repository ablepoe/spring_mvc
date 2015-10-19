package com.dhc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhc.common.CommonUtil;
import com.dhc.entity.User;

/**
 * 
 * @author hanliang 20160617
 * -intercept every *.do
 *
 */
public class CommonInterceptor implements HandlerInterceptor {
	
    public CommonInterceptor() {
    	
    }  
  
    private String mappingURL;
    
    public void setMappingURL(String mappingURL) {    
           this.mappingURL = mappingURL;    
	}
 
    private String defaultURL;  
    public void setDefaultURL(String defaultURL) {    
           this.defaultURL = defaultURL;    
	}
    
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception{  
    		//muti TAB
	    	if(request.getSession().isNew()) {
	    		response.sendRedirect("http://localhost:8080/report/Index.do");
//	    		response.sendRedirect(request.getContextPath() + defaultURL);
	    		return false;
	    	}
	    	
	        String requestURL = request.getRequestURL().toString();
	        //check current url in xml 
	    	String[] mappingURLs = mappingURL.split(",");
	    	Boolean isOK = false;
	    	for(int i = 0; i < mappingURLs.length; i++) {
	    		if (requestURL.indexOf(mappingURLs[i]) >= 0) {
	    			isOK = true;
	    			break;
	    		}
	    	}
	    	//direct url,check login
	    	if(!isOK) {
//	    		return false;
	        	User loginInfo = CommonUtil.getLoginInfo(request.getSession());
		        if(loginInfo == null) {
		        	response.sendRedirect("http://localhost:8080/report/Index.do");
//		    		response.sendRedirect(request.getContextPath() + defaultURL);
		            return false;
		        }
	    	}
	    	return true;  
    }  
  
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {
    	//logger.info("==============执行顺序: 2、postHandle================");  
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {
    	//logger.info("==============执行顺序: 3、afterCompletion================");  
    }  
}
