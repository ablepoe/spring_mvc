package com.test;

import static org.junit.Assert.*;

import javax.xml.ws.Action;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.enetity.User;

public class IndexControllerTest extends SpringBase{

	@Autowired
	private User user;
	
	@Ignore
	public void testIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testTest() {
		System.out.println("==========");
		assertTrue("is true", user.getName().equals("NAME"));
		System.out.println("==========");
	}

}
