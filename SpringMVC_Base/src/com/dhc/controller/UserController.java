package com.dhc.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dhc.common.Common;
import com.dhc.common.CommonUtil;
import com.dhc.common.StringUtil;
import com.dhc.entity.HibernateUserFunctionAuth;
import com.dhc.entity.HibernateUserPrimaryKey;
import com.dhc.entity.MenuItem;
import com.dhc.entity.ResponseDTO;
import com.dhc.entity.User;
import com.dhc.entity.UserFunctionAuth;
import com.dhc.service.CommonService;
import com.dhc.service.UserService;

/**
 * 
 * @author hanliang 20150617
 * -user login/logout/validate 
 * -user authRole
 *
 */
@Controller
public class UserController {
	
	@Resource(name="UserService")
	private UserService userService;
	
	@Resource(name="CommonService")
	private CommonService commonService;
	
	
    /**
     * 显示登录页面
     * 
     */
    @RequestMapping("/Index")
    public ModelAndView Index(HttpServletRequest request){
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("Index");
        return mav;
    }
	
    /**
     * 退出系统
     * 
     */
    @RequestMapping("/LogOut")
    public ModelAndView LogOut(HttpServletRequest request){
    	CommonUtil.clearAllSession(request.getSession());
        ModelAndView mav = new ModelAndView("redirect:/Index.do");
        return mav;
    }
	
    /**
     * 验证用户是否存在
     * @param reqUser
     * @param request
     * @return
     */
	@ResponseBody
	@RequestMapping(value="/user/checkUser")
	private ResponseDTO checkUser(@RequestBody User reqUser,HttpServletRequest request){
    	
    	//用户ID
    	String userName = reqUser.getUserName();
    	String password = reqUser.getPassword();
    	//加密后密码
    	//String password = CommonUtil.encodePassword(reqUser.getPassword());
    	ResponseDTO resp = new ResponseDTO();
    	User user = userService.checkUser(userName, password);
    	if (user == null) {
    		//登陆失败，用户不存或者密码错误或者过期
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0001");
    		resp.setMsgContent(CommonUtil.getMessage("E0001"));
    	}else { 
    		resp.setStatus(Common.STATUS_SUCCESS);
    	}
    	return resp;
    }
	
	/**
	 * 用户登陆 
	 * @param request
	 * @return
	 */
	@RequestMapping("/Login")
    public ModelAndView login(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        //获取参数
        String sessionUser_name = (String) request.getSession().getAttribute("userName");
        String userName = request.getParameter("userName");
        String password = (String) request.getSession().getAttribute("password");
        String user_pwd = request.getParameter("password");
        //验证是否是同一用户F5操作
        if(CommonUtil.checkUser(sessionUser_name, userName, password, user_pwd)){
        	 //验证是否存在其他选项卡登陆系统
        	if(CommonUtil.checkSystemLogin(request.getSession())) {
                mav.setViewName("Index");
                mav.addObject("userName", request.getParameter("userName"));
                mav.addObject("password", request.getParameter("userPwd"));
                mav.addObject("errorMsg", CommonUtil.getMessage("E0002"));
            }else{
            	mav = loginCheck(request);
            }
        }else{
        	mav = loginCheck(request);
        }
        return mav;
    }
	
	/**
	 * 用户登陆检测
	 * @param request
	 * @return
	 */
	private ModelAndView loginCheck(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//为下一次其他用户登陆/F5保存参数
		request.getSession().setAttribute("userName", request.getParameter("userName"));
		request.getSession().setAttribute("password", request.getParameter("password"));
		//用户
		String userName = request.getParameter("userName");
		//加密后密码
		String password = Common.EMPTY;
		if (StringUtil.isNotEmpty(request.getParameter("password"))) {
			password = request.getParameter("password");
		}
		User checkUser = userService.checkUser(userName, password);
		if (checkUser == null) {
			//登陆失败，用户不存或者密码错误或者过期
		    mav.setViewName("Index");
		    mav.addObject("userName", request.getParameter("userName"));
		    mav.addObject("password", request.getParameter("password"));
		    mav.addObject("errorMsg", CommonUtil.getMessage("E0001"));
		} else {
			//取得用户登录信息
			mav.setViewName("Main");
			mav.addObject("user", checkUser);
			
			List<MenuItem> menuItem = commonService.getMenuByUserId(checkUser.getUser_Id());
			mav.addObject("menuList", menuItem);
			CommonUtil.setLoginInfo(request.getSession(), checkUser);
		}
		
		return mav;
	}
	
	/**
	 * 获取用户已分配角色
	 * @param reqUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/checkUserByName")
	private ResponseDTO checkUserByName(@RequestBody User reqUser,HttpServletRequest request){
    	
    	//用户名
    	String userName = reqUser.getUserName();
    	ResponseDTO resp = new ResponseDTO();
    	List<User> user_role = userService.checkUserByName(userName);
    	if (user_role == null) {
    		//登陆失败，用户不存或者密码错误或者过期
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0001");
    		resp.setMsgContent(CommonUtil.getMessage("E0001"));
    	}else { 
    		resp.setData(user_role);
    		resp.setStatus(Common.STATUS_SUCCESS);
    		request.getSession().setAttribute("user_Id", user_role.get(0).getUser_Id());
    	}
    	return resp;
    }
	
	/**
	 * 获取用户可分配角色
	 * @param reqUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/getUserRole")
	private ResponseDTO getUserRole(@RequestBody User reqUser,HttpServletRequest request){
    	
    	//用户名
    	String userName = reqUser.getUserName();
    	ResponseDTO resp = new ResponseDTO();
    	List<User> user_role = userService.checkUserRoleListByName(userName);
		resp.setData(user_role);
		resp.setStatus(Common.STATUS_SUCCESS);
    	return resp;
    }
	
	/**
	 * 添加用户角色
	 * @param reqUserRole
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/addUserRole")
	private ResponseDTO addRoleList(@RequestBody List<String> reqUserRole,HttpServletRequest request){
    	
		//用户ID
		String user_Id = (String) request.getSession().getAttribute("user_Id");
    	boolean result = userService.addUserRoleListById(user_Id, reqUserRole);
    	ResponseDTO resp = new ResponseDTO();
    	if(result){
    		resp.setStatus(Common.STATUS_SUCCESS);
    	}else{
    		resp.setStatus(Common.STATUS_FAILURE);
    	}
		return resp;
    }
	
	/**
	 * 删除用户角色
	 * @param reqUserRole
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/deleteUserRole")
	private ResponseDTO deleteRoleList(@RequestBody List<String> reqUserRole,HttpServletRequest request){
    	
		//用户ID
		String user_Id = (String) request.getSession().getAttribute("user_Id");
    	boolean result = userService.deleteUserRoleListById(user_Id, reqUserRole);
    	ResponseDTO resp = new ResponseDTO();
    	if(result){
    		resp.setStatus(Common.STATUS_SUCCESS);
    	}else{
    		resp.setStatus(Common.STATUS_FAILURE);
    	}
		return resp;
    }
	
	/**
	 * 获得用户单独页面权限
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/getUserFunc")
	private ResponseDTO getUserFunc(HttpServletRequest request){
		//用户ID
		String user_Id = (String) request.getSession().getAttribute("user_Id");
    	List<UserFunctionAuth> userFunctionAuth = userService.getAllFunctionAuth(user_Id);
    	ResponseDTO resp = new ResponseDTO();
    	if(userFunctionAuth == null){
    		resp.setStatus(Common.STATUS_FAILURE);
    	}else{
    		resp.setStatus(Common.STATUS_SUCCESS);
    		resp.setData(userFunctionAuth);
    	}
		return resp;
    }
	
	/**
	 * 分配用户单独页面权限
	 * @param userFunctionAuths
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/saveUserFunc")
	private ResponseDTO saveUserFunc(@RequestBody List<UserFunctionAuth> userFunctionAuths,HttpServletRequest request){
		//用户ID
		String user_Id = (String) request.getSession().getAttribute("user_Id");
		List<HibernateUserFunctionAuth> HibernateUserFunctionAuthList = new ArrayList<HibernateUserFunctionAuth>();
		//对象转换
		for (int i = 0; i < userFunctionAuths.size(); i++) {
			HibernateUserFunctionAuth hibernateUser = new HibernateUserFunctionAuth();
			HibernateUserPrimaryKey primaryKey = new HibernateUserPrimaryKey(); 
			
			UserFunctionAuth userFunctionAuth = userFunctionAuths.get(i);
			//联合主键
			primaryKey.setUser_Id(user_Id);
			primaryKey.setFunc_Id(userFunctionAuth.getFunc_Id());
			
			hibernateUser.setAuthority(userFunctionAuth.getAuthority());
			hibernateUser.setPrimaryKey(primaryKey);
			
			HibernateUserFunctionAuthList.add(hibernateUser);
		}
		
		userService.saveOrUpdate(HibernateUserFunctionAuthList);
		
		ResponseDTO resp = new ResponseDTO();
		resp.setStatus(Common.STATUS_SUCCESS);
		return resp;
    }
	
}
