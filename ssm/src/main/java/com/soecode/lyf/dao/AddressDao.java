package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Address;
import com.soecode.lyf.entity.Items;

public interface AddressDao {
	// 查询所有items
	@Select("SELECT * FROM address where account_id=#{accountId}")
	List<Address> queryByAccountId(@Param("accountId") int accountId);
}
