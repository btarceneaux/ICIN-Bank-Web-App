package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class AccountController 
{
	
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
	
	
}