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
		testUser.setFirstName("Test");
		testUser.setLastName("User");
		testUser.setPassword("testing123");
		testUser.setPhoneNumber("1111111111");
		testUser.setUsername("test.user@test.com");
		
		int result = service.registerUser(testUser);
		
		assertEquals(1, result);
	}
	
	@Test
	@Order(2)
	public void getAllUsers()
	{
		List<User> allUsers = service.getAllUsers();
		assertEquals(1, allUsers.size());
	}
	
	@Test
	@Order(3)
	public void deleteUserTest()
	{
		User testUser = new User();
		testUser.setEmailAddress("test@test.com");
		testUser.setFirstName("Test");
		testUser.setLastName("User");
		testUser.setPassword("testing123");
		testUser.setPhoneNumber("1111111111");
		testUser.setUsername("test.user@test.com");
		
		int result = service.deleteUser(testUser);
		
		assertEquals(1, result);
	}

}
