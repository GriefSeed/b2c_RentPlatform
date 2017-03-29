package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.Item;

public interface HeaderDao {
	// 插入数据后，返回自增ID
	@Insert("INSERT INTO header(account_id,create_date,status,address) VALUES(#{accountId},#{createDate},#{status},#{address})")
	@Options(useGeneratedKeys = true, keyProperty = "headerId")
	int insertHeader(Header header);

	// 返回用户所有订单
	@Select("SELECT * FROM header where account_id=#{accountId} order by create_date DESC")
	List<Header> getHeadersByAccountId(@Param("accountId") int accountId);

	// 返回单个订单
	@Select("SELECT * FROM header where header_id=#{headerId}")
	Header getHeaderByHeaderId(@Param("headerId") int headerId);
	
}
