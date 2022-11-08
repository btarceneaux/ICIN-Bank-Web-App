package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.CheckingAccount;
import com.bean.SavingsAccount;
import com.bean.User;
import com.service.UserService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class UserController 
{
	@Autowired
	UserService service;
	
	@Autowired
	AccountController accountController;
	
	
	@PostMapping(value = "/storeUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User myUser)
	{
		System.out.println("The user being registered is " + myUser.getFirstName() + " " + myUser.getLastName());
		//The register user service already checks to see if the user exists.
		User tempUser = service.registerUser(myUser);
		
		if(tempUser != null)
		{
			// Now that the user has been registered, create the savings and checking account for the user.
			accountController.createAccount("checking", tempUser);
			accountController.createAccount("savings", tempUser);
			
			return "successful.";
		}
		else
			return "unsuccessful.";
	}
	
	@GetMapping(value = "getUsers")
	public List<User> getAllUsers()
	{
		List<User> listOfUsers = service.getAllUsers();
		return listOfUsers;
	}

	@PostMapping(value = "/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String loginUser(@RequestBody User myUser)
	{
		int result = service.loginUser(myUser.getEmailAddress(), myUser.getPassword());
		
		if(result == 1)
		{
			return "successful";
		}
		else
		{
			return "Error! Login unsuccessful. Please try again.";
		}
	}
}	