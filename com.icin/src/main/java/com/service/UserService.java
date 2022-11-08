package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.User;
import com.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository repository;
	
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
		
		if(userList.size() > 0)
		{
			result = 1;
		}
		
		return result;
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
}