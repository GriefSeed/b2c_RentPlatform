package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.AddressDao;
import com.soecode.lyf.entity.Address;
import com.soecode.lyf.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Address> queryByAccountId(int accountId) {
		return addressDao.queryByAccountId(accountId);
	}

}
