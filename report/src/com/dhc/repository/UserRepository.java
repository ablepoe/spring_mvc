package com.dhc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhc.entity.HibernateUserFunctionAuth;

public interface UserRepository extends JpaRepository<HibernateUserFunctionAuth,Integer>{

}
