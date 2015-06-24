package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet implementation class myTestServlet
 */
@WebServlet("/myTestServlet")
public class myTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("testServlet");
    	String parm = request.getParameter("mytest");
//    	String parm2 = request.getParameter("mytest2");
    	System.out.println("parm is "+parm);
//    	System.out.println("parm2 is "+parm2);
    	String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
    	ObjectMapper objectMapper = new ObjectMapper();
    	PrintWriter out = response.getWriter();
    	objectMapper.writeValue(out, json);
    	out.flush();
    	out.close();
	}

}
