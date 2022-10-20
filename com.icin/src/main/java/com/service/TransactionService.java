package com.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionService
{
	public int depositMoney(String accountType, float amountToDeposit)
	{
		int result = 0;
		
		if(accountType.equals("savings"))
		{
			//Insert code here to make the deposit for savings.
			result = 1;
		}
		else
		{
			//Insert code her to make the deposit for checking
		}
		
		return result;
	}
	
	public int withdrawMoney(String accountType, float amountToDeposit)
	{
		int result = 0;
		
		if(accountType.equals("savings"))
		{
			//Insert code here to make the deposit for savings.
			result = 1;
		}
		else
		{
			//Insert code her to make the deposit for checking
		}
		
		return result;
	}
}
