package com.spring.service;

import org.springframework.stereotype.Service;

@Service
// ʹ��jdk��̬����
public class AServiceImpl {

	public void barA() {
		System.out.println("AServiceImpl.barA()");
	}

	public void fooA(String _msg) {
		System.out.println("AServiceImpl.fooA(msg:" + _msg + ")");
	}
}