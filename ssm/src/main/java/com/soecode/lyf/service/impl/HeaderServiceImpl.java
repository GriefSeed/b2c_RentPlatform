package com.soecode.lyf.service.impl;

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

}
