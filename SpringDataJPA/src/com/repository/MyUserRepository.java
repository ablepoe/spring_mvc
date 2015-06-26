package com.repository;

import org.springframework.data.repository.Repository;

import com.entity.MyUser;



public interface MyUserRepository extends Repository<MyUser,Integer>{

	public MyUser findByNameAndPassword(String username, String password);
	
}
