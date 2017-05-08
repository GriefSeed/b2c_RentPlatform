package com.soecode.lyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.WorkAccountDao;
import com.soecode.lyf.entity.WorkAccount;
import com.soecode.lyf.service.WorkAccountService;

@Service
public class WorkAccountImpl implements WorkAccountService {

	@Autowired
	private WorkAccountDao workAccountDao;

	@Override
	public WorkAccount queryByAccountId(int accountId) {
		return workAccountDao.queryByAccountId(accountId);
	}

	@Override
	public WorkAccount queryByAccountName(String accountName) {
		return workAccountDao.queryByAccountName(accountName);
	}

	@Override
	public void modifyWorkAccount(WorkAccount workAccount) {
		workAccountDao.modifyWorkAccount(workAccount);
	}

}
