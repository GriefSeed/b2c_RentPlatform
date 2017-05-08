package com.soecode.lyf.service;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.dto.EmailVo;
import com.soecode.lyf.entity.Account;

public interface AccountService {
	Account getOneByName(String accountName);

	/**
	 * 发送简单文本Email消息
	 * 
	 * @param emailVo
	 */
	public void sendEmailMessageOfSimpleText(EmailVo emailVo, Date date);

	/**
	 * 根据编号ID查询用户
	 * 
	 * @param accountId
	 * @return
	 */
	Account queryByAccountId(@Param("accountId") int accountId);

	/**
	 * 根据email找用户，用于找回密码
	 * 
	 * @param email
	 * @return
	 */
	Account queryByAccountEmail(@Param("email") String email);

	/**
	 * 注册时插入主数据，主数据注册后不能更改，除了密码
	 * 
	 * @param account
	 * @return
	 */
	int insertAccountMain(Account account);

	/**
	 * 用户修改辅数据
	 * 
	 * @param account
	 * @return
	 */
	void modifyAccount(Account account);
}
