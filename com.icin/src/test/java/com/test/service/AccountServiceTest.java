package com.test.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
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
	
	int tmpAccountNumber;

	@Test
	@Order(1)
	public void createCheckingAccountTest()
	{
		CheckingAccount account = new CheckingAccount();
		
		account.setBalance(1000);
		
		tmpAccountNumber = service.createCheckingAccount(account);
		assertNotEquals(0, tmpAccountNumber);
	}
	
	@Test
	@Order(2)
	public void deleteCheckingAccountTest()
	{
		CheckingAccount account = service.getCheckingAccountById(tmpAccountNumber);
		int result = service.deleteCheckingAccount(account);
		assertEquals(1, result);
	}
	
	@Test
	@Order(3)
	public void createSavingsAccountTest()
	{
		
		SavingsAccount account = new SavingsAccount();
		account.setBalance(1000);
		tmpAccountNumber = service.createSavingsAccount(account);
				
		assertNotEquals(0, account.getId());
	}
	
	@Test
	@Order(4)
	public void deleteSavingsAccountTest()
	{
		SavingsAccount account = service.getSavingsAccountById(tmpAccountNumber);
		int result = service.deleteSavingsAccount(account);
		assertEquals(1, result);
	}

}
