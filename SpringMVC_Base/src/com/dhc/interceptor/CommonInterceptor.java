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
  
    private String mappingURL;//利用正则映射到需要拦截的路径    
    public void setMappingURL(String mappingURL) {    
           this.mappingURL = mappingURL;    
	}
 
    private String defaultURL;  
    public void setDefaultURL(String defaultURL) {    
           this.defaultURL = defaultURL;    
	}
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception{  
    		
	    	//正式环境启用 START
    		//同浏览器多开选项卡
	    	if(request.getSession().isNew()) {
	    		response.sendRedirect(request.getContextPath() + defaultURL);
	    		return false;
	    	}
	    	
	        String requestURL = request.getRequestURL().toString();
	        //比较是否包含在mapping的地址里
	    	String[] mappingURLs = mappingURL.split(",");
	    	Boolean isOK = false;
	    	for(int i = 0; i < mappingURLs.length; i++) {
	    		if (requestURL.indexOf(mappingURLs[i]) >= 0) {
	    			isOK = true;
	    			break;
	    		}
	    	}
	    	//直接地址访问，验证是否登陆
	    	if(!isOK) {
	        	User loginInfo = CommonUtil.getLoginInfo(request.getSession());
		        if(loginInfo == null) {
//		    		response.sendRedirect(request.getContextPath() + defaultURL);
		            return false;
		        }
	    	}
	    	//正式环境启用 END
	    	return true;  
    }  
  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {
    	//logger.info("==============执行顺序: 2、postHandle================");  
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {
    	//logger.info("==============执行顺序: 3、afterCompletion================");  
    }  
}
