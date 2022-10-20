package com.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "savings")
public class SavingsAccount extends Account 
{

	public SavingsAccount() 
	{
		super();
		System.out.println("Savings account created.");
	}
}