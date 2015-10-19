package com.dhc.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@Resource(name="UserService")
	private UserService userService;
	
	@Resource(name="CommonService")
	private CommonService commonService;
	
	
    /**
     * login page
     * 
     */
    @RequestMapping("/Index")
    public ModelAndView Index(HttpServletRequest request){
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("Index");
        return mav;
    }
	
    /**
     * exit system
     * 
     */
    @RequestMapping("/LogOut")
    public ModelAndView LogOut(HttpServletRequest request){
    	CommonUtil.clearAllSession(request.getSession());
        ModelAndView mav = new ModelAndView("redirect:/Index.do");
        return mav;
    }
	
    /**
     * check user exist
     * @param reqUser
     * @param request
     * @return
     */
	@ResponseBody
	@RequestMapping(value="/user/checkUser")
	private ResponseDTO checkUser(@RequestBody User reqUser,HttpServletRequest request){
    	//get input params
    	String userName = reqUser.getUserName().toUpperCase();
    	String password = reqUser.getPassword().toUpperCase();
    	String input_code = reqUser.getCode();
    	//get code in image
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//check if input_code equals image code ignoreCase
		boolean flag = false;
		flag = input_code.equalsIgnoreCase(code);
		//code check
		ResponseDTO resp = new ResponseDTO();
		if(!flag){
			//login fail:code error
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0003");
    		resp.setMsgContent(CommonUtil.getMessage("E0003"));
    		return resp;
		}
    	//user check
    	User user = userService.checkUser(userName, password);
    	if (user == null) {
    		//login fail:user not exist or password error
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0001");
    		resp.setMsgContent(CommonUtil.getMessage("E0001"));
    	}else { 
    		resp.setStatus(Common.STATUS_SUCCESS);
    	}
    	return resp;
    }
	
	/**
	 * check user register
	 * @param reqUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/registerUser")
	private ResponseDTO registerUser(@RequestBody User reqUser,HttpServletRequest request){
    	//get input params
    	String userName = reqUser.getUserName().toUpperCase();
    	String input_code = reqUser.getCode();
    	//get code in image
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//check if input_code equals image code ignoreCase
		boolean flag = false;
		flag = input_code.equalsIgnoreCase(code);
		//code check
		ResponseDTO resp = new ResponseDTO();
		if(!flag){
			//login fail:code error
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0003");
    		resp.setMsgContent(CommonUtil.getMessage("E0003"));
    		return resp;
		}
    	//user register check
    	User user = userService.checkRegisterUser(userName);
    	if (user == null) {
    		resp.setStatus(Common.STATUS_SUCCESS);
    	}else { 
    		//register fail:user not exist
    		resp.setStatus(Common.STATUS_FAILURE);
    		resp.setMsgCode("E0004");
    		resp.setMsgContent(CommonUtil.getMessage("E0004"));
    	}
    	return resp;
    }
	
	/**
	 * user login
	 * @param request
	 * @return
	 */
	@RequestMapping("/Login")
    public ModelAndView login(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        String sessionUser_name = (String) request.getSession().getAttribute("userName");
        String userName = request.getParameter("userName").toUpperCase();
        String password = (String) request.getSession().getAttribute("password");
        String user_pwd = request.getParameter("password").toUpperCase();
        //check the same user/F5
        if(CommonUtil.checkUser(sessionUser_name, userName, password, user_pwd)){
        	 //check if other TAB login
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
	 * user register
	 * @param request
	 * @return
	 */
	@RequestMapping("/Register")
    public ModelAndView register(HttpServletRequest request) {
        
        String userName = request.getParameter("userName").toUpperCase();
        String user_pwd = request.getParameter("password").toUpperCase();
        
        ModelAndView mav = new ModelAndView();
//        mav.setViewName("Register");
        mav.addObject("userName", request.getParameter("userName"));
        mav.addObject("password", request.getParameter("userPwd"));
        
        if( (userName == null || userName.equals("")) || (user_pwd == null || user_pwd.equals("")) ){
        	mav.setViewName("Register");
             mav.addObject("errorMsg", CommonUtil.getMessage("E0005"));
        }else{
        	//check if successful registered
        	boolean registered = userService.registerUser(userName, user_pwd);
        	if(registered){
        		mav.setViewName("Index");
                mav.addObject("errorMsg", CommonUtil.getMessage("E0006"));
        	}else{
        		mav.setViewName("Register");
                mav.addObject("errorMsg", CommonUtil.getMessage("E0007"));
        	}
        }
        return mav;
    }
	
	/**
	 * jump to indexPage
	 * @return
	 */
	@RequestMapping("/IndexPage")
	public ModelAndView indexPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Index");
		return mav;
	}
	
	/**
	 * jump to registerPage
	 * @return
	 */
	@RequestMapping("/RegisterPage")
	public ModelAndView registerPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Register");
		return mav;
	}
	
	
	/**
	 * user login check
	 * @param request
	 * @return
	 */
	private ModelAndView loginCheck(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		//save value
		request.getSession().setAttribute("userName", request.getParameter("userName").toUpperCase());
		request.getSession().setAttribute("password", request.getParameter("password").toUpperCase());
		String userName = request.getParameter("userName").toUpperCase();
		String password = Common.EMPTY;
		if (StringUtil.isNotEmpty(request.getParameter("password"))) {
			password = request.getParameter("password");
		}
		User checkUser = userService.checkUser(userName, password);
		if (checkUser == null) {
			//login fail:user not exist or password error
		    mav.setViewName("Index");
		    mav.addObject("userName", request.getParameter("userName"));
		    mav.addObject("password", request.getParameter("password"));
		    mav.addObject("errorMsg", CommonUtil.getMessage("E0001"));
		} else {
			//get user login info
			mav.setViewName("Main");
			mav.addObject("user", checkUser);
			
			List<MenuItem> menuItem = commonService.getMenuByUserId(checkUser.getUser_Id());
			mav.addObject("menuList", menuItem);
			CommonUtil.setLoginInfo(request.getSession(), checkUser);
		}
		
		return mav;
	}
	
	/**
	 * get user owned role
	 * @param reqUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/checkUserByName")
	private ResponseDTO checkUserByName(@RequestBody User reqUser,HttpServletRequest request){
    	
    	String userName = reqUser.getUserName().toUpperCase();
    	ResponseDTO resp = new ResponseDTO();
    	List<User> user_role = userService.checkUserByName(userName);
    	if (user_role == null) {
    		//login fail:user not exist or password error
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
	 * get user able to get role
	 * @param reqUser
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/getUserRole")
	private ResponseDTO getUserRole(@RequestBody User reqUser,HttpServletRequest request){
    	
    	String userName = reqUser.getUserName().toUpperCase();
    	ResponseDTO resp = new ResponseDTO();
    	List<User> user_role = userService.checkUserRoleListByName(userName);
		resp.setData(user_role);
		resp.setStatus(Common.STATUS_SUCCESS);
    	return resp;
    }
	
	/**
	 * add role
	 * @param reqUserRole
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/addUserRole")
	private ResponseDTO addRoleList(@RequestBody List<String> reqUserRole,HttpServletRequest request){
    	
		int user_Id = (Integer) request.getSession().getAttribute("user_Id");
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
	 * delete role
	 * @param reqUserRole
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/deleteUserRole")
	private ResponseDTO deleteRoleList(@RequestBody List<String> reqUserRole,HttpServletRequest request){
    	
		int user_Id = (Integer) request.getSession().getAttribute("user_Id");
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
	 * get user single function auth
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/getUserFunc")
	private ResponseDTO getUserFunc(HttpServletRequest request){
		//用户ID
		int user_Id = (Integer) request.getSession().getAttribute("user_Id");
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
	 * set user function auth
	 * @param userFunctionAuths
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/saveUserFunc")
	private ResponseDTO saveUserFunc(@RequestBody List<UserFunctionAuth> userFunctionAuths,HttpServletRequest request){

		int user_Id = (Integer) request.getSession().getAttribute("user_Id");
		List<HibernateUserFunctionAuth> HibernateUserFunctionAuthList = new ArrayList<HibernateUserFunctionAuth>();
		//target translate
		for (int i = 0; i < userFunctionAuths.size(); i++) {
			HibernateUserFunctionAuth hibernateUser = new HibernateUserFunctionAuth();
			HibernateUserPrimaryKey primaryKey = new HibernateUserPrimaryKey(); 
			
			UserFunctionAuth userFunctionAuth = userFunctionAuths.get(i);
			//_PK
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
