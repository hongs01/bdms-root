package com.bdms.auth.account;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdms.auth.JunitSpringInitialize;
import com.bdms.auth.service.AccountService;
import com.bdms.entity.auth.Account;

public class AccountServiceTest extends JunitSpringInitialize {

	private static Logger logger = LoggerFactory
			.getLogger(AccountServiceTest.class);

	@Autowired
	private AccountService accountService;

	//@Test
	public void InsertAccount() {
		Account account = new Account();
		account.setAccountName("admin");
		// User user=new User();
		// account.setUser(user);
		// accountService.saveAccount(account);
		logger.info("插入帐号成功...");
	}

	@Test
	public void delAccount() {
		//accountService.delAccount(1);
	}

}
