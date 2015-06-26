package cn.luxh.app.repository;


import org.springframework.data.repository.CrudRepository;

import com.entity.MyUser;

import cn.luxh.app.domain.User;

/**
 * 用户持久层接口
 * @author Luxh
 * 2012-8-31
 */
public interface UserRepository extends CrudRepository<User,Integer>{
    
	
}