package com;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.AccountInfo;
import com.service.AccountInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountInfoTest {

	@Resource(name = "AccountInfoService")
	private AccountInfoService accountInfoService;

	@Test
	public void test() {
		System.out.println("..");
		AccountInfo accountInfo = accountInfoService.getAccountInfo(1l);
		System.out.println(accountInfo.getFirstName() + "| "+accountInfo.getLastname() + " | "+accountInfo.getId());
	}

}