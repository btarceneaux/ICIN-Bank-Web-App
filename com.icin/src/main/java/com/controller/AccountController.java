package com.controller;

import java.util.List;

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

//@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost/", "http://localhost:80/"})
@CrossOrigin(origins = "http://icin-bank-frontend.s3-website-us-west-2.amazonaws.com/")
@RestController
public class AccountController 
{
	@Autowired
	AccountService service;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/approveCheckingCheckbookRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String approveCheckbookRequest(@RequestBody CheckingAccount myCheckingAccount)
	{
		String result;
		int requested = service.approveCheckingCheckbookRequest(myCheckingAccount);
		
		if(requested == 1)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result;
	}
	
	@PostMapping(value = "/approveSavingsCheckbookRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String approveCheckbookRequest(@RequestBody SavingsAccount mySavingsAccount)
	{
		String result;
		int requested = service.approveSavingsCheckbookRequest(mySavingsAccount);
		
		if(requested == 1)
		{
			result = "Successful";
		}
		else
		{
			result = "Unsuccessful";
		}
		
		return result;
	}
	
	@PostMapping(value = "/requestSavingsCheckbook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String requestSavingsAccountCheckbook(@RequestBody SavingsAccount mySavingsAccount)
	{
		String result;
		int requested = service.requestSavingsCheckbook(mySavingsAccount);
		
			if(requested == 1)
			{
				result = "Successful";
			}
			else
			{
				result = "Unsuccessful";
			}
			
			
			return result;
	}
	
	@PostMapping(value = "/requestCheckingCheckbook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String requestCheckingAccountCheckbook(@RequestBody CheckingAccount myCheckingAccount)
	{
		String result;
		System.out.println("In requestCheckingCheckbook");
		myCheckingAccount.setCheckbookRequested(true);
		
		int requested = service.requestCheckingAccountCheckbook(myCheckingAccount);
		
			if(requested == 1)
			{
				result = "Successful";
			}
			else
			{
				result = "Unsuccessful";
			}
			
			return result;
	}
	
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
		
		System.out.println("User being passed in is " + myUser);
		
		return myUser.getMySavingAccount();
	}
	
	@GetMapping(value = "/getSavingsAccountCheckbookRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SavingsAccount> getSavingsAccountsWhereCheckbookWasRequested()
	{
		List<SavingsAccount> mySavingsAccountList = service.getRequestedSavingsAccountByCheckbookRequest();
		
		return mySavingsAccountList;
	}
	
	@GetMapping(value = "/getCheckingAccountCheckbookRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CheckingAccount> getCheckingAccountsWhereCheckbookWasRequested()
	{
		List<CheckingAccount> myCheckingAccountList = service.getRequestedCheckingAccountByCheckbookRequest();
		
		return myCheckingAccountList;
	}
	
}