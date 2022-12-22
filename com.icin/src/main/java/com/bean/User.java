package com.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
//import javax.persistence.CascadeType;

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
	private String password;
	@OneToOne (cascade = CascadeType.ALL)
	private CheckingAccount myCheckingAccount = new CheckingAccount();
	@OneToOne (cascade = CascadeType.ALL)
	private SavingsAccount mySavingsAccount = new SavingsAccount();
	
	private boolean activated = false;
	
	public User() 
	{
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CheckingAccount getMyCheckingAccount() {
		return myCheckingAccount;
	}

	public void setMyCheckingAccount(CheckingAccount myCheckingAccount) {
		this.myCheckingAccount = myCheckingAccount;
	}

	public SavingsAccount getMySavingAccount() {
		return mySavingsAccount;
	}

	public void setMySavingAccount(SavingsAccount mySavingAccount) {
		this.mySavingsAccount = mySavingAccount;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", emailAddress=" + emailAddress + ", password=" + password + ", myCheckingAccount="
				+ myCheckingAccount + ", mySavingsAccount=" + mySavingsAccount + ", activated=" + activated + "]";
	}

}