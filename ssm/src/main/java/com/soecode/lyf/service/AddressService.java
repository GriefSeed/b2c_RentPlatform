package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Address;

public interface AddressService {
	List<Address> queryByAccountId(int accountId);

	int insertAddress(Address address);

	void modifyAddress(Address address);

	int removeAddress(int addressId);
}
