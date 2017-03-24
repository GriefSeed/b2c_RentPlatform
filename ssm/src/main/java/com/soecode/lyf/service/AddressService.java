package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.Address;

public interface AddressService {
	List<Address> queryByAccountId(int accountId);
}
