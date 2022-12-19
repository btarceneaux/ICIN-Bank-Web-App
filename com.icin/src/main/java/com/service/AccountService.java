package com.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Account;
import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.Transaction;
import com.repository.CheckingAccountRepository;
import com.repository.SavingsAccountRepository;

@Service
public class AccountService 
{
	@Autowired
	CheckingAccountRepository checkingRepository;
	
	@Autowired
	SavingsAccountRepository savingsRepository;
	
	public int approveCheckingCheckbookRequest(CheckingAccount account)
	{
		int result = 0;
		CheckingAccount myCheckingAccount = new CheckingAccount();
		Optional<Account> tmpAccount = checkingRepository.findById(account.getId());
		
		if(tmpAccount.isPresent())
		{
			myCheckingAccount = (CheckingAccount) tmpAccount.get();
			myCheckingAccount.setCheckbookRequestApproved(true);
			
			checkingRepository.save(myCheckingAccount);
			
			result = 1;
		}
		
		return result;
		
	}
	
	public int approveSavingsCheckbookRequest(SavingsAccount account)
	{
		int result = 0;
		SavingsAccount mySavingsAccount = new SavingsAccount();
		Optional<Account> tmpAccount = savingsRepository.findById(account.getId());
		
		if(tmpAccount.isPresent())
		{
			mySavingsAccount = (SavingsAccount) tmpAccount.get();
			mySavingsAccount.setCheckbookRequestApproved(true);
			
			savingsRepository.save(mySavingsAccount);
			
			result = 1;
		}
		
		return result;
		
	}
	
	public List<SavingsAccount> getRequestedSavingsAccountByCheckbookRequest()
	{
		List<SavingsAccount> accountList = savingsRepository.findByCheckbookRequested(true);
		
		return accountList;
	}
	
	public List<CheckingAccount> getRequestedCheckingAccountByCheckbookRequest()
	{
		List<CheckingAccount> accountList = checkingRepository.findByCheckbookRequested(true);
		
		return accountList;
	}
	
	public int requestCheckingAccountCheckbook(CheckingAccount account)
	{
		int result = 0;
		CheckingAccount myCheckingAccount = new CheckingAccount();
		
		System.out.println("Requesting checking account number " + account.getId() + "from account service.");
		Optional<Account> tmpAccount = checkingRepository.findById(account.getId());
		if(tmpAccount.isPresent())
		{
			System.out.println("Inside the present block.");
			myCheckingAccount = (CheckingAccount) tmpAccount.get();
			myCheckingAccount.setCheckbookRequested(true);
			
			checkingRepository.save(myCheckingAccount);
			
			result = 1;
		}
		
		return result;
	}
	
	public int requestSavingsCheckbook(SavingsAccount account)
	{
		int result = 0;
		SavingsAccount mySavingsAccount = new SavingsAccount();
		
		Optional<Account> tmpAccount = savingsRepository.findById(account.getId());
		if(tmpAccount.isPresent())
		{
			mySavingsAccount = (SavingsAccount) tmpAccount.get();
			mySavingsAccount.setCheckbookRequested(true);
			
			savingsRepository.save(mySavingsAccount);
			
			result = 1;
		}
		
		return result;
	}
	
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
	
	public Optional<Account> getCheckingAccountById(int checkingAccountId)
	{
		Optional<Account> tmpCheckingAccount = checkingRepository.findById(checkingAccountId);
		return tmpCheckingAccount;
	}
	
	public Optional<Account> getSavingsAccountById(int savingsAccountId)
	{
		Optional<Account> tmpSavingsAccount = savingsRepository.findById(savingsAccountId);
		return tmpSavingsAccount;
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
	
	public float depositIntoChecking(CheckingAccount account)
	{
		//First get the specified checking account.
		Optional<Account> obj = checkingRepository.findById(account.getId());
		
		CheckingAccount myCheckingAccount = new CheckingAccount();
		
		//After getting the specified checking account, deposit the money. 
		if(obj.isPresent())
		{
			myCheckingAccount = (CheckingAccount) obj.get();
			
			
			//Get the current Balance from the object then add in the amount deposited.
			float currentBalance = myCheckingAccount.getBalance();
			float newBalance = currentBalance + account.getBalance();
			
			myCheckingAccount.setBalance(newBalance);
			
			System.out.println("The balance of the checking account is " + myCheckingAccount.getBalance());
					
			//Record the transaction 
			Transaction myTransaction = new Transaction();
			myTransaction.setAccountId(account.getId());
			myTransaction.setAmount(account.getBalance());
			myTransaction.setDescription("Deposit");
			
			Date date = new java.sql.Date(System.currentTimeMillis());
			myTransaction.setTransactionDate(date);

			myTransaction.setTransactionType("Deposit");
			
			myCheckingAccount.getMyTransaction().add(myTransaction);
			
			//Save to the database
			checkingRepository.save(myCheckingAccount);
		}
		
		return myCheckingAccount.getBalance();
	}
	
	public float depositIntoSavings(SavingsAccount account)
	{
		//First get the specified checking account.
		Optional<Account> obj = savingsRepository.findById(account.getId());
		
		SavingsAccount mySavingsAccount = new SavingsAccount();
		
		//After getting the specified checking account, deposit the money. 
		if(obj.isPresent())
		{
			mySavingsAccount = (SavingsAccount) obj.get();
			System.out.println("Checking account now passed to the account service : " + mySavingsAccount);
			
			//Get the current Balance from the object then add in the amount deposited.
			float currentBalance = mySavingsAccount.getBalance();
			float newBalance = currentBalance + account.getBalance();
			
			mySavingsAccount.setBalance(newBalance);
			
			System.out.println("The balance of the savings account is " + mySavingsAccount.getBalance());
			
			//Record the transaction 
			Transaction myTransaction = new Transaction();
			myTransaction.setAccountId(account.getId());
			myTransaction.setAmount(account.getBalance());
			myTransaction.setDescription("Deposit");
			
			Date date = new java.sql.Date(System.currentTimeMillis());
			myTransaction.setTransactionDate(date);
			
			myTransaction.setTransactionType("Deposit");
			
			mySavingsAccount.getMyTransaction().add(myTransaction);
			
			//Save to the database
			savingsRepository.save(mySavingsAccount);
		}
		
		return mySavingsAccount.getBalance();
	}

	public float withdrawFromChecking(CheckingAccount account) 
	{
		
		//First get the specified checking account.
		Optional<Account> obj = checkingRepository.findById(account.getId());
				
		CheckingAccount myCheckingAccount = new CheckingAccount();
				
		//After getting the specified checking account, deposit the money. 
		if(obj.isPresent())
		{
			myCheckingAccount = (CheckingAccount) obj.get();
			
			//Get the current Balance from the object then add in the amount deposited.
			float currentBalance = myCheckingAccount.getBalance();
			float newBalance = currentBalance - account.getBalance();
			
			myCheckingAccount.setBalance(newBalance);
			
			System.out.println("The balance of the checking account is " + myCheckingAccount.getBalance());
					
			//Record the transaction 
			Transaction myTransaction = new Transaction();
			myTransaction.setAccountId(account.getId());
			myTransaction.setAmount(account.getBalance());
			myTransaction.setDescription("Withdrawal");
					
			Date date = new java.sql.Date(System.currentTimeMillis());
			myTransaction.setTransactionDate(date);
					
			myTransaction.setTransactionType("Withdrawal");
			
			myCheckingAccount.getMyTransaction().add(myTransaction);
					
					//Save to the database
			checkingRepository.save(myCheckingAccount);
		}
				
		return myCheckingAccount.getBalance();
	}

	public float withdrawFromSavings(SavingsAccount account) 
	{
		//First get the specified checking account.
		Optional<Account> obj = savingsRepository.findById(account.getId());
		
		SavingsAccount mySavingsAccount = new SavingsAccount();
		
		//After getting the specified checking account, deposit the money. 
		if(obj.isPresent())
		{
			mySavingsAccount = (SavingsAccount) obj.get();
			System.out.println("Checking account now passed to the account service : " + mySavingsAccount);
			
			//Get the current Balance from the object then add in the amount deposited.
			float currentBalance = mySavingsAccount.getBalance();
			float newBalance = currentBalance - account.getBalance();
			
			mySavingsAccount.setBalance(newBalance);
			
			System.out.println("The balance of the savings account is " + mySavingsAccount.getBalance());
			
			//Record the transaction 
			Transaction myTransaction = new Transaction();
			myTransaction.setAccountId(account.getId());
			myTransaction.setAmount(account.getBalance());
			myTransaction.setDescription("Withdrawal");
			
			Date date = new java.sql.Date(System.currentTimeMillis());
			myTransaction.setTransactionDate(date);
			
			myTransaction.setTransactionType("Withdrawal");
			
			mySavingsAccount.getMyTransaction().add(myTransaction);
			
			//Save to the database
			savingsRepository.save(mySavingsAccount);
		}
		
		return mySavingsAccount.getBalance();
	}
}