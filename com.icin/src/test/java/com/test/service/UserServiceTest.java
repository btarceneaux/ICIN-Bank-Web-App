package com.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.Application;
import com.bean.User;
import com.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest
{
	@Autowired
	UserService service;
	
	@Test
	@Order(1)
	public void createUserTest()
	{
		User testUser = new User();
		testUser.setEmailAddress("test@test.com");
		testUser.setPassword("12345");
		testUser.setFirstName("Test");
		testUser.setLastName("User");
		testUser.setPhoneNumber("1111111111");
		
		User tempUser = service.registerUser(testUser);
		
		assertEquals("test@test.com",tempUser.getEmailAddress());
	}
	
	@Test
	@Order(2)
	public void loginTest()
	{
		int result = service.loginUser("test@test.com", "12345");
		assertNotEquals(0, result);
	}
	
	@Test
	@Order(3)
	public void getAllUsers()
	{
		List<User> allUsers = service.getAllUsers();
		assertNotEquals(0, allUsers.size());
	}
	
	@Test
	@Order(4)
	public void activateOrDeactivateUser()
	{
		User myUser = service.findUserByEmailAddress("test@test.com");
		boolean activated = myUser.isActivated();
		int result = service.activateOrDeactivateUser(myUser.getUserId());
		User newUser = service.findUserByEmailAddress("test@test.com");
		
		assertEquals(1, result);
		assertNotEquals(activated, newUser.isActivated());
	}
	
	
	
	@Test
	@Order(5)
	public void deleteUserTest()
	{
		User myUser = service.findUserByEmailAddress("test@test.com");
		
		int result = service.deleteUser(myUser);
		
		assertEquals(1, result);
	}

}
