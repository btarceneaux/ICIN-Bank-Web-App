package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bean.User;
import com.service.UserService;

@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:4201/"})
//@CrossOrigin(origins = "*")
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
}	