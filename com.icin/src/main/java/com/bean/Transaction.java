package com.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction 
{
	@Id
	private long transactionNumber;
	private Date transactionDate;
	private float amount;
	private String description;
	private int accountId;
	private String transactionType;
	
	public Transaction()
	{
	}

	public long getTransactionNumber()
	{
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber)
	{
		this.transactionNumber = transactionNumber;
	}

	public Date getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public float getAmount()
	{
		return amount;
	}

	public void setAmount(float amount)
	{
		this.amount = amount;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getAccountId()
	{
		return accountId;
	}

	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}

	public String getTransactionType()
	{
		return transactionType;
	}

	public void setTransactionType(String transactionType)
	{
		this.transactionType = transactionType;
	}

}