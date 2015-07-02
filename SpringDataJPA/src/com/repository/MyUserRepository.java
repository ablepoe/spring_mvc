package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.entity.MyUser;



public interface MyUserRepository extends JpaRepository<MyUser,Integer>{

	public MyUser findByNameAndPassword(String username, String password);
	
}
