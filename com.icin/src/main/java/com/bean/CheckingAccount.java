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
	}
	
	
	public int getCheckNumber() 
	{
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) 
	{
		this.checkNumber = checkNumber;
	}

	@Override
	public String toString() {
		return "CheckingAccount [checkNumber=" + checkNumber + "]";
	}
	
}