package com.fpt.doan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.doan.data.entity.Account;
import com.fpt.doan.service.AccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/login")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/{username}")
	public Account getAcc(@PathVariable String username) {
		return accountService.getAccountByUsername(username);
	}
}
