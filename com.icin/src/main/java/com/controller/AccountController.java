package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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