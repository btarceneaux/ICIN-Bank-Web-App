package com.test.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.Application;
import com.bean.Account;
import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@TestMethodOrder(OrderAnnotation.class)
class AccountServiceTest
{
	@Autowired
	AccountService service;
	
	int tmpAccountNumber = 0;

	@Test
	@Order(1)
	public void createAndDeleteCheckingAccountTest()
	{
		CheckingAccount account = new CheckingAccount();
		
		account.setBalance(1000);
		
		tmpAccountNumber = service.createCheckingAccount(account);
		assertNotEquals(0, tmpAccountNumber);
		
		int result = service.deleteCheckingAccount(account);
		assertEquals(1, result);
	}
	
	@Test
	@Order(2)
	public void createAndDeleteSavingsAccountTest()
	{
		
		SavingsAccount account = new SavingsAccount();
		account.setBalance(1000);
		tmpAccountNumber = service.createSavingsAccount(account);
				
		assertNotEquals(0, account.getId());
		
		int result = service.deleteSavingsAccount(account);
		assertEquals(1, result);
	}

}