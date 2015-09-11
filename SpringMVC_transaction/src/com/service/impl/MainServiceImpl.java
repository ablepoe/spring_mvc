package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.MainService;
import com.service.TeacherService;
import com.service.UserService;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	//Main transaction
	//parent transaction
//	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void testTransaction() {
		//child 1
		userService.addUser();
		//child 2
		teacherService.addTeacher();
		
	}

}
