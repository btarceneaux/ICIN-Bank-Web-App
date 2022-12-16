package com.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bean.CheckingAccount;


@Repository
public interface CheckingAccountRepository extends AccountRepository
{
	List<CheckingAccount> findByCheckbookRequested(boolean checkbookRequested);
}