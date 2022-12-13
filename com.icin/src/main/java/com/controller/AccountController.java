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

@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:4201/"})
@RestController
public class AccountController 
{
	@Autowired
	AccountService service;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/checkingDeposit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String depositMoneyToAccount(@RequestBody CheckingAccount myCheckingAccount)
	{
		System.out.println("Depositing money into checking");
		String result;
		System.out.println("The amount being deposited is " + myCheckingAccount.getBalance());
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
	
	@PostMapping(value = "/savingsDeposit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String depositMoneyToAccount(@RequestBody SavingsAccount mySavingsAccount)
	{
		System.out.println("Depositing money into checking");
		String result;
		System.out.println("The amount being deposited is " + mySavingsAccount.getBalance());
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