package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.Account;

public interface AccountDao {
	Account queryOneByName(@Param("accountName") String accountName);
}
