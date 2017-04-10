package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	/**
	 * 删除单个订单
	 * 
	 * @param headerId
	 * @return
	 */
	@Delete("DELETE FROM header WHERE header_id = #{headerId}")
	int deleteHeader(@Param("headerId") int headerId);

	/**
	 * 订单由工作人员改为USING状态，并录入start_date时间
	 * 
	 * @param header
	 */
	@Update("UPDATE header SET status = #{status},start_date = #{startDate} WHERE header_id = #{headerId}")
	void modifyHeaderUsing(Header header);

	/**
	 * 订单由工作人员改为DEBT状态，并录入end_date时间
	 * 
	 * @param header
	 */
	@Update("UPDATE header SET status = #{status},end_date = #{endDate} WHERE header_id = #{headerId}")
	void modifyHeaderDebt(Header header);

	/**
	 * 订单修改状态，主要用于工作人员将订单改为CLOSE状态
	 * 
	 * @param header
	 */
	@Update("UPDATE header SET status = #{status} WHERE header_id = #{headerId}")
	void modifyHeaderCLOSE(Header header);
}
