 package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.DbUser;

/**
 * 一个自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
 * 
 * @author liukai
 * 
 */
@Service("customUserDetailsService")
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	@Qualifier("UserDao")
	private UserDao userDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {

			// 搜索数据库以匹配用户登录名.
			// 我们可以通过dao使用JDBC来访问数据库
			DbUser User = userDAO.getDatabase(username);

			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct
			// role type

			user = new User(User.getUsername(), User.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(User.getEnabled(),User.getUsername()));

		} catch (Exception e) {
			System.out.println("Error in retrieving user");
			e.printStackTrace();
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access, String username) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		
		if(access.compareTo(1) == 0){
			System.out.println("Enabled User");
			//获取数据库中的权限
			List<String> auth = userDAO.getUserAuth(username);
			for (int i = 0; i < auth.size(); i++) {
				System.out.println(auth.get(i));
				authList.add(new SimpleGrantedAuthority(auth.get(i)));
			}
			
			//根据此权限得到真正的
//			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else{
			System.out.println("disabled User");
			
		}

		return authList;
	}
}


