package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.WorkAccount;

public interface WorkAccountDao {
	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@Select("SELECT * FROM work_account where work_account_id=#{workAccountId}")
	WorkAccount queryByAccountId(@Param("workAccountId") int workAccountId);

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	@Select("SELECT * FROM work_account where account_name=#{accountName}")
	WorkAccount queryByAccountName(@Param("accountName") String accountName);

	/**
	 * 工作人员修改辅数据
	 * 
	 * @param workAccount
	 * @return
	 */
	@Update("UPDATE work_account SET name = #{name},show_img = #{showImg},sex = #{sex},age = #{age} where work_account_id = #{workAccountId}")
	void modifyWorkAccount(WorkAccount workAccount);

	/**
	 * 工作人员修改密码
	 * 
	 * @param workAccount
	 * @return
	 */
	@Update("UPDATE work_account SET password = #{password} where work_account_id = #{workAccountId}")
	void modifyAccountPassword(WorkAccount workAccount);
}
