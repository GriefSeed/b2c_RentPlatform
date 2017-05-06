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

	@Override
	public int insertAddress(Address address) {
		return addressDao.insertAddress(address);
	}

	@Override
	public void modifyAddress(Address address) {
		addressDao.modifyAddress(address);
	}

	@Override
	public int removeAddress(int addressId) {
		return removeAddress(addressId);
	}

}
