package com.intercepter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.enetity.User;

public class myIntercepter implements HandlerInterceptor {

	private Logger log = Logger.getLogger(myIntercepter.class);
	@Resource(name="user")
	private User user;

	
	public myIntercepter() {
		// TODO Auto-generated constructor stub
	}

	private String mappingURL;//��������ӳ�䵽��Ҫ���ص�·��  
        public void setMappingURL(String mappingURL) {  
               this.mappingURL = mappingURL;  
       } 

	/**
	 * ��ҵ��������������֮ǰ������
	 * �������false
	 *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳���������
	 * 
	 * �������true
	 *    ִ����һ��������,ֱ�����е���������ִ�����
	 *    ��ִ�б����ص�Controller
	 *    Ȼ�������������,
	 *    �����һ������������ִ�����е�postHandle()
	 *    �����ٴ����һ������������ִ�����е�afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
//		log.info("==============ִ��˳��: 1��preHandle================");
		System.out.println(mappingURL);
		System.out.println("==============ִ��˳��: 1��preHandle================");
		String url=request.getRequestURL().toString();  
		System.out.println(url);
		
		System.out.println(user.getName()+":"+user.getPassword());
		
        if(mappingURL==null || url.matches(mappingURL)){  
			request.getRequestDispatcher("/test.jsp").forward(request, response);
			return false; 
        }  
		return true;
	}

	//��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ��� 
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		log.info("==============ִ��˳��: 2��postHandle================");
		System.out.println("==============ִ��˳��: 2��postHandle================");
	}

	/**
	 * ��DispatcherServlet��ȫ����������󱻵��� 
	 * 
	 *   �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
//		log.info("==============ִ��˳��: 3��afterCompletion================");
		System.out.println("==============ִ��˳��: 3��afterCompletion================");
	}

}
