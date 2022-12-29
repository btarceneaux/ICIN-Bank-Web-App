package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bean.Transaction;
import com.service.TransactionService;

//@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost/", "http://localhost:80/"})
@CrossOrigin(origins = "http://icin-bank-frontend.s3-website-us-west-2.amazonaws.com/")
@RestController
public class TransactionController 
{
	@Autowired
	TransactionService service;
	
	@GetMapping(value = "/getTransactions/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransanctions(@PathVariable("accountId") String accountId)
	{
		return service.getTransactionsByAccountId(Integer.parseInt(accountId));
	}
}
