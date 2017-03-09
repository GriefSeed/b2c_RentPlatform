package com.soecode.lyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.AccountDao;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.service.AccountService;

@Service
public class AccountServicelmpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	@Override
	public Account getOneByName(String accountName) {
		return accountDao.queryOneByName(accountName);
	};
}
