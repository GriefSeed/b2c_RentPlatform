package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.HeaderDao;
import com.soecode.lyf.entity.Header;
import com.soecode.lyf.service.HeaderService;

@Service
public class HeaderServiceImpl implements HeaderService {

	@Autowired
	private HeaderDao headerDao;
	
	@Override
	public int insertHeader(Header header) {
		return headerDao.insertHeader(header);
	}

	@Override
	public List<Header> getHeadersByAccountId(int accountId) {
		return headerDao.getHeadersByAccountId(accountId);
	}

	@Override
	public Header getHeaderByHeaderId(int headerId) {
		return headerDao.getHeaderByHeaderId(headerId);
	}

	@Override
	public int deleteHeader(int headerId) {
		return headerDao.deleteHeader(headerId);
	}

}
