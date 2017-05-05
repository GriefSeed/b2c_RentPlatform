package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Address;

public interface AccountDao {
	Account queryOneByName(@Param("accountName") String accountName);

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@Select("SELECT * FROM account where account_id=#{accountId}")
	Account queryByAccountId(@Param("accountId") int accountId);

	/**
	 * 根据email找用户，用于找回密码
	 * 
	 * @param email
	 * @return
	 */
	@Select("SELECT * FROM account where email=#{email}")
	Account queryByAccountEmail(@Param("email") String email);
}
