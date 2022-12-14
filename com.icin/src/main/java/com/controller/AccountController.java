package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.User;
import com.service.AccountService;
import com.service.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class AccountController 
{
	@Autowired
	AccountService service;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/checkingDeposit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String depositMoneyToCheckingAccount(@RequestBody CheckingAccount myCheckingAccount)
	{
		String result;
		float amt = service.depositIntoChecking(myCheckingAccount);
		
		System.out.println("The amount being deposited is " + amt);
		
		if(amt > 0)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result; 
	}
	
	@PostMapping(value = "/checkingWithdrawal", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String withdrawMoneyFromChecking(@RequestBody CheckingAccount myCheckingAccount)
	{
		String result;
		
		float amt = service.withdrawFromChecking(myCheckingAccount);
		
		System.out.println("The amount being withdrawn from the account is " + amt);
		
		if(amt > 0)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result; 
	}
	
	@PostMapping(value = "/savingsDeposit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String depositMoneyToSavingsAccount(@RequestBody SavingsAccount mySavingsAccount)
	{
		String result;
		
		float amt = service.depositIntoSavings(mySavingsAccount);
		
		System.out.println("The amount being deposited is " + amt);
		
		if(amt > 0)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result; 
	}
	
	@PostMapping(value = "/savingsWithdrawal", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String withdrawMoneyFromSavingsAccount(@RequestBody SavingsAccount mySavingsAccount)
	{
		String result;
		
		float amt = service.withdrawFromSavings(mySavingsAccount);
		
		System.out.println("The amount being withdrawn from the account is " + amt);
		
		if(amt > 0)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result; 
	}
	
	
	@PostMapping(value = "/withdraw")
	public String withdrawMoneyFromAccount(String accountType,float amountToWithdraw)
	{
		return "Successful";
	}
	
	@GetMapping(value = "/getCheckingAccountInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CheckingAccount getCheckingAccountByUserId(@PathVariable("userId") int userId)
	{
		User myUser = userService.getUserbyId(userId); 
		return myUser.getMyCheckingAccount();
	}
	
	@GetMapping(value = "/getSavingsAccountInfo/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SavingsAccount getSavingsAccountByUser(@PathVariable("userId") int userId)
	{
		User myUser = userService.getUserbyId(userId); 
		return myUser.getMySavingAccount();
	}
	
}