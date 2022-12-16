package com.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bean.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends AccountRepository
{
	List<SavingsAccount> findByCheckbookRequested(boolean checkbookRequested);
}