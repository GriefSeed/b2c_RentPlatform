package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.Header;

public interface HeaderService {
	int insertHeader(Header header);
	
	List<Header> getHeadersByAccountId(int accountId);
}
