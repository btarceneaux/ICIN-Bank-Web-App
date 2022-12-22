package com.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Account;
import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.User;
import com.repository.CheckingAccountRepository;
import com.repository.SavingsAccountRepository;
import com.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository repository;
	
	@Autowired
	SavingsAccountRepository sacRepository;
	
	@Autowired
	CheckingAccountRepository cacRepository;
	
	public int activateOrDeactivateUser(int userId)
	{
		Optional<User> myUserList = repository.findById(userId);
		int result = 0;
		
		if(myUserList.isPresent())
		{
			User myUser = myUserList.get();
			
			if(myUser.isActivated() == false)
			{
				myUser.setActivated(true);
			}
			else 
			{
				myUser.setActivated(false);
			}
			
			repository.save(myUser);
			
			result = 1;
		}
		
		return result;
	}
	
	public List<User> getAllUsers()
	{
		return repository.findAll();
	}
	
	public User registerUser(User incomingUser)
	{	
		List<User> myList = repository.findByEmailAddress(incomingUser.getPassword());
		
		if (myList.size() == 0)
		{
			User tmpUser = repository.save(incomingUser);
			
			return tmpUser;
		}
		
		return null;
	}
	
	public int loginUser(String emailAddress, String password)
	{
		System.out.println("Email address coming over is " + emailAddress);
		
		int result = 0;
		
		List<User> myList = repository.findByEmailAddress(emailAddress);
		System.out.println("The list size is " + myList.size());
		
		if(myList.size() > 0)
		{
			User tmpUser = myList.get(0);
			if(tmpUser.getPassword().equals(password))
			{
				result = tmpUser.getUserId();
			}
		}
		
		
		return result;
	}
	
	public User getUserbyId(int id)
	{
		Optional<User> tmpUser = repository.findById(id);
		User myUser = new User();
		
		if(tmpUser.isPresent())
		{
			myUser = tmpUser.get();
		}
		
		return myUser;
	}
	
	public int deleteUser(User incomingUser)
	{
		List<User> myList = repository.findByEmailAddress(incomingUser.getEmailAddress());
		
		int result = 0;
		//First check to see if the user is in the database.
		if(myList.size() > 0)
		{
			repository.delete(incomingUser);
			result = 1;
		}
		
		return result;
	}
	
	public User findUserByCheckingAccount(int id)
	{
		CheckingAccount myFoundCheckingAccount = new CheckingAccount();
		
		Optional<Account> tmpCheckingAccount = cacRepository.findById(id);
		if(tmpCheckingAccount.isPresent())
		{
			myFoundCheckingAccount = (CheckingAccount) tmpCheckingAccount.get();
		}
		
		User myUser = repository.findUserByMyCheckingAccount(myFoundCheckingAccount);
		
		return myUser;
	}
	
	public User findUserBySavingsAccount(int id)
	{
		SavingsAccount myFoundSavingsAccount = new SavingsAccount();
		
		Optional<Account> tmpSavingsAccount = sacRepository.findById(id);
		if(tmpSavingsAccount.isPresent())
		{
			myFoundSavingsAccount = (SavingsAccount) tmpSavingsAccount.get();
		}
		
		User myUser = repository.findUserByMySavingsAccount(myFoundSavingsAccount);
		
		return myUser;
	}
}