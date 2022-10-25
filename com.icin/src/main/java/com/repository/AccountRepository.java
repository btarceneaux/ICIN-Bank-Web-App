package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.bean.Account;

@NoRepositoryBean
public interface AccountRepository extends JpaRepository<Account, Integer>
{

}