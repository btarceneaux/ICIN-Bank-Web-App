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
import com.bean.User;
import com.service.AccountService;
import com.service.UserService;

//@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost/", "http://localhost:80/"})
@CrossOrigin(origins = "http://ec2-35-89-254-103.us-west-2.compute.amazonaws.com")
@RestController
public class UserController 
{
	@Autowired
	UserService service;
	
	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/activateOrDeactivateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String activateDeactivateUser(@RequestBody User myUser)
	{
		System.out.println("The id being passed from the path variable is " + myUser.getUserId());
		String myResult;
		int result = service.activateOrDeactivateUser(myUser.getUserId());
		
		if(result == 1)
		{
			myResult = "Successful";
		}
		else
		{
			myResult = "Unsuccessful";
		}
		
		return myResult;
	}
	
	@PostMapping(value = "/storeUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User myUser)
	{
		System.out.println("The user being registered is " + myUser.getFirstName() + " " + myUser.getLastName());
		//The register user service already checks to see if the user exists.
		User tempUser = service.registerUser(myUser);
		
		if(tempUser != null)
		{
			System.out.println(tempUser);
			
			return "successful.";
		}
		else
			return "unsuccessful.";
	}
	
	@GetMapping(value = "/getUsers")
	public List<User> getAllUsers()
	{
		List<User> listOfUsers = service.getAllUsers();
		return listOfUsers;
	}

	//Now returning the auto generated id of the user.
	@PostMapping(value = "/loginUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int loginUser(@RequestBody User myUser)
	{
		System.out.println("User coming over is " + myUser);
		
		int result = service.loginUser(myUser.getEmailAddress(), myUser.getPassword());
		
		return result;
	}
	
	//We need this to get all the details from a user.
	@GetMapping(value = "/getUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("id") String id)
	{
		User tempUser = service.getUserbyId(Integer.parseInt(id));
		System.out.println("Now printing user details");
		
		if(tempUser.getEmailAddress().length() > 0)
		{
			return tempUser;
		}
		else
		{
			return new User();
		}
	}
	
	@GetMapping(value = "/getUserByAccountId/{id}/{accountType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByAccountId(@PathVariable("id") String id, @PathVariable("accountType") String accountType)
	{
		User myUser = new User();
		
		if(accountType.equals("checking"))
		{
			myUser = service.findUserByCheckingAccount(Integer.parseInt(id));
		}
		else
		{
			myUser = service.findUserBySavingsAccount(Integer.parseInt(id));
		}
		
		return myUser;
	}
}	