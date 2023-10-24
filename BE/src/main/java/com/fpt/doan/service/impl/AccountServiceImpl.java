package com.fpt.doan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Account;
import com.fpt.doan.data.repository.AccountRepository;
import com.fpt.doan.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account getAccountByUsername(String username) {
		return accountRepository.getById(username);
	}

}
