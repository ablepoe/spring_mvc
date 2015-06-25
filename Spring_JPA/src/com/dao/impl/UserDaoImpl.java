package com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext 
	private EntityManager em; 

	@Override
	@Transactional 
	public void save(User user) {
		em.persist(user);
		
	}

	@Override
	@Transactional 
	public void update(User user) {
		em.merge(user);
	}

	@Override
	@Transactional 
	public void delete(User user) {
		User u = em.find(User.class, user.getId());
		em.remove(u);
	}

}
