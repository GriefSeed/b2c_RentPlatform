package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.WorkAccount;

public interface WorkAccountDao {
	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@Select("SELECT * FROM work_account where account_id=#{accountId}")
	WorkAccount queryByAccountId(@Param("accountId") int accountId);

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@Select("SELECT * FROM work_account where account_name=#{accountName}")
	WorkAccount queryByAccountName(@Param("accountName") String accountName);
}
