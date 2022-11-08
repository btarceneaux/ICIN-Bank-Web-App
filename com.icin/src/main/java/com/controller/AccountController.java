package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.User;
import com.service.AccountService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class AccountController 
{
	@Autowired
	AccountService service;
	
	@PostMapping(value = "/deposit")
	public String depositMoneyToAccount(String accountType,float amountToDeposit)
	{
		return "Successful";
	}
	
	@PostMapping(value = "/withdraw")
	public String withdrawMoneyFromAccount(String accountType,float amountToWithdraw)
	{
		return "Successful";
	}
	
	public int createAccount(String accountType, User myUser)
	{
		int result = 0;
		
		if(accountType.equals("checking"))
		{
			int accountCreationResult = 0;
			
			CheckingAccount myCheckingAccount = new CheckingAccount();
			myCheckingAccount.setUser(myUser);
			accountCreationResult = service.createCheckingAccount(myCheckingAccount);
			
			if(accountCreationResult > 0)
			{
				
				result = 1;
			}
		}
		else
		{
			int accountCreationResult = 0;
			
			SavingsAccount mySavingsAccount = new SavingsAccount();
			mySavingsAccount.setUser(myUser);
			accountCreationResult = service.createSavingsAccount(mySavingsAccount);
			
			if(accountCreationResult > 0)
			{
				System.out.println("Savings account user is : " + mySavingsAccount.getUser().getFirstName());
				result = 1;
			}
		}
		
		return result;
	}
}