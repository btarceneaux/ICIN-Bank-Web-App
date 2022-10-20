package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
	List<User> findByEmailAddress(String emailAddress);
	List<User> findByUsername(String userName);
	
	List<User> findByUsernameAndPassword(String userName, String password);
}