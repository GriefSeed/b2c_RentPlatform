package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.soecode.lyf.entity.Header;

public interface HeaderDao {
	//插入数据后，返回自增ID
	@Insert("INSERT INTO header(account_id,create_date,status,address) VALUES(#{accountId},#{createDate},#{status},#{address})")
	@Options(useGeneratedKeys=true,keyProperty="headerId")
	int insertHeader(Header header);
}
