package com.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "checking")
public class CheckingAccount extends Account
{
	public int checkNumber;

	public CheckingAccount()
	{
		super();
		System.out.println("Checking account created.");
	}

	public int getCheckNumber() 
	{
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) 
	{
		this.checkNumber = checkNumber;
	}
}