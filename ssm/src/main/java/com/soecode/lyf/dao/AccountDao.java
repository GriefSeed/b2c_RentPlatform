package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	/**
	 * 注册时插入主数据，主数据注册后不能更改，除了密码
	 * 
	 * @param account
	 * @return
	 */
	@Insert("INSERT INTO account VALUES (#{accountId},#{accountName},#{password},#{email},#{status},#{credit},#{name},#{showImg},#{sex},#{age})")
	int insertAccountMain(Account account);

	/**
	 * 用户修改辅数据
	 * 
	 * @param account
	 * @return
	 */
	@Update("UPDATE account SET name = #{name},show_img = #{showImg},sex = #{sex},age = #{age} where account_id = #{accountId}")
	void modifyAccount(Account account);

	/**
	 * 用户修改密码
	 * 
	 * @param account
	 * @return
	 */
	@Update("UPDATE account SET password = #{password} where account_id = #{accountId}")
	void modifyAccountPassword(Account account);
}
