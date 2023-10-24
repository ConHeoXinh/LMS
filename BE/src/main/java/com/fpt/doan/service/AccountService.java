package com.fpt.doan.service;

import org.springframework.stereotype.Service;

import com.fpt.doan.data.entity.Account;

@Service
public interface AccountService {

	Account getAccountByUsername(String username);
}
