package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TeacherDao;
import com.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public void addTeacher() {
		teacherDao.addTeacher();
		System.out.println("2");
	}

}
