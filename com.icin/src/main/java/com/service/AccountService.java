package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.repository.CheckingAccountRepository;
import com.repository.SavingsAccountRepository;

@Service
public class AccountService 
{
	@Autowired
	CheckingAccountRepository checkingRepository;
	
	@Autowired
	SavingsAccountRepository savingsRepository;
	
	public int createCheckingAccount(CheckingAccount account)
	{	
		int result = 0;
		
		CheckingAccount tmpAccount = checkingRepository.save(account);
		if(tmpAccount.getId() > 0)
		{
			result = tmpAccount.getId();
		}
		
		return result;
	}
	
	public int deleteCheckingAccount(CheckingAccount account)
	{
		int result = 0;
		
		if(checkingRepository.existsById(account.getId()))
		{
			checkingRepository.delete(account);
			result = 1;
		}
		
		return result;
	}
	
	public CheckingAccount getCheckingAccountById(int checkingAccountId)
	{
		CheckingAccount tmpCheckingAccount = (CheckingAccount) checkingRepository.getOne(checkingAccountId);
		return tmpCheckingAccount;
	}
	
	public int createSavingsAccount(SavingsAccount account)
	{	
		int result = 0;
		
		SavingsAccount tmpAccount = savingsRepository.save(account);
		if(tmpAccount.getId() > 0)
		{
			result = tmpAccount.getId();
		}
		
		return result;
	}
	
	public int deleteSavingsAccount(SavingsAccount account)
	{
		int result = 0;
		
		if(savingsRepository.existsById(account.getId()))
		{
			savingsRepository.delete(account);
			result = 1;
		}
		
		return result;
	}
	
	public SavingsAccount getSavingsAccountById(int savingsAccountId)
	{
		SavingsAccount tmpSavingsAccount = (SavingsAccount) checkingRepository.getOne(savingsAccountId);
		return tmpSavingsAccount;
	}
	
}