package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Menu;
import com.entity.UserAuth;
import com.service.AuthorityService;
import com.service.MenuService;


@Controller
public class AuthorityController {
	
	@Resource(name="AuthorityService")
	private AuthorityService authorityService;
	
	@Resource(name="MenuService")
	private MenuService menuService;
	
	@RequestMapping(value="/authority")
	private ModelAndView authority(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
    	//get param
    	String userId = request.getParameter("userId");
    	//get userAuth
    	Map<String, UserAuth> userAuth = authorityService.getUserAuth(userId);
    	//set userAuth to the session
    	request.getSession().setAttribute("userAuth", userAuth);
    	//get menuList
    	List<Menu> menuList = menuService.getSubMenu(userId);
    	
/*    	for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			System.out.println(menu.getMenuId());
			System.out.println(menu.getMenuName());
			List<Menu> subMenu = menu.getSubMenu();
			for (int j = 0; j < subMenu.size(); j++) {
				System.out.println(subMenu.get(j).getMenuId());
				System.out.println(subMenu.get(j).getMenuName());
			}
			System.out.println("-----------------");
		}*/

    	ModelAndView mav = new ModelAndView();    	
    	mav.addObject(menuList);
    	mav.setViewName("Main");
    	return mav;
    }
}
