package com.soecode.lyf.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.dao.WorkAccountDao;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.WorkAccount;

public interface WorkAccountService {
	/**
	 * 
	 * @param accountId
	 * @return
	 */
	WorkAccount queryByAccountId(@Param("accountId") int accountId);

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	WorkAccount queryByAccountName(@Param("accountName") String accountName);

	/**
	 * 工作人员修改辅数据
	 * 
	 * @param workAccount
	 * @return
	 */
	void modifyWorkAccount(WorkAccount workAccount);

	/**
	 * 工作人员修改密码
	 * 
	 * @param workAccount
	 * @return
	 */
	void modifyAccountPassword(WorkAccount workAccount);
}
