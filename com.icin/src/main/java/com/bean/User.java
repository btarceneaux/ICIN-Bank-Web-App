package com.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String username;
	private String password;
	@OneToMany
	private List<CheckingAccount> myCheckingAccount = new ArrayList<CheckingAccount>();
	@OneToMany
	private List<SavingsAccount> mySavingAccount = new ArrayList<SavingsAccount>();;
	private boolean activated;
	
	public User() 
	{
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CheckingAccount> getMyCheckingAccount() {
		return myCheckingAccount;
	}

	public void setMyCheckingAccount(List<CheckingAccount> myCheckingAccount) {
		this.myCheckingAccount = myCheckingAccount;
	}

	public List<SavingsAccount> getMySavingAccount() {
		return mySavingAccount;
	}

	public void setMySavingAccount(List<SavingsAccount> mySavingAccount) {
		this.mySavingAccount = mySavingAccount;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}