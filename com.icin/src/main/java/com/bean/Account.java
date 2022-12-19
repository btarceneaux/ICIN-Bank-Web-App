package com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float balance;
	private boolean checkbookRequested = false;
	private boolean checkbookRequestApproved = false;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> myTransaction = new ArrayList<>();
	
	public Account() 
	{
		this.balance = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public List<Transaction> getMyTransaction() {
		return myTransaction;
	}

	public void setMyTransaction(List<Transaction> myTransaction) {
		this.myTransaction = myTransaction;
	}

	
	public boolean isCheckbookRequested() {
		return checkbookRequested;
	}

	public void setCheckbookRequested(boolean checkbookRequested) {
		this.checkbookRequested = checkbookRequested;
	}

	public boolean isCheckbookRequestApproved() {
		return checkbookRequestApproved;
	}

	public void setCheckbookRequestApproved(boolean checkbookRequestApproved) {
		this.checkbookRequestApproved = checkbookRequestApproved;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", checkbookRequested=" + checkbookRequested
				+ ", checkbookRequestApproved=" + checkbookRequestApproved + ", myTransaction=" + myTransaction + "]";
	}
}