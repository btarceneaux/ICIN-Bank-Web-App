package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bean.Transaction;
import com.repository.TransactionRepository;

@Service
public class TransactionService
{
	@Autowired
	TransactionRepository repository;
	
	public List<Transaction> getTransactionsByAccountId(int accountId)
	{
		return repository.findByAccountId(accountId);
	}
}
