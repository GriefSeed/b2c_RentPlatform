package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Header;

public interface HeaderService {
	int insertHeader(Header header);

	List<Header> getHeadersByAccountId(int accountId);

	Header getHeaderByHeaderId(int headerId);

	int deleteHeader(int headerId);

	/**
	 * 订单由工作人员改为USING状态，并录入start_date时间
	 * 
	 * @param header
	 */
	void modifyHeaderUsing(Header header);

	/**
	 * 订单由工作人员改为DEBT状态，并录入end_date时间
	 * 
	 * @param header
	 */
	void modifyHeaderDebt(Header header);

	/**
	 * 订单修改状态，主要用于工作人员将订单改为CLOSE状态
	 * 
	 * @param header
	 */
	void modifyHeaderCLOSE(Header header);
}
