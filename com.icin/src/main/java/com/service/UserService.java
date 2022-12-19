package com.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Account;
import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.User;
import com.repository.AccountRepository;
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
	
	public List<User> getAllUsers()
	{
		return repository.findAll();
	}
	
	public User registerUser(User incomingUser)
	{	
		//First see if the email address exists.
		if (repository.findByEmailAddress(incomingUser.getEmailAddress()).size() == 0)
		{
			User tempUser = repository.save(incomingUser);
			
			return tempUser;
		}
		
		return null;
	}
	
	public int loginUser(String emailAddress, String password)
	{
		int result = 0;
		
		List<User> userList = repository.findByEmailAddressAndPassword(emailAddress, password);
		
		
		//Let's return the ID of the user instead
		if(userList.size() > 0)
		{
			User myUser = userList.get(0);
			result = myUser.getUserId();
		}
		
		return result;
	}
	
	public User getUserbyId(int id)
	{
		return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("User with id " + id + " was not found!"));
	}
	
	public int deleteUser(User incomingUser)
	{
		int result = 0;
		//First check to see if the user is in the database.
		if(repository.findByEmailAddress(incomingUser.getEmailAddress()).size() == 1)
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