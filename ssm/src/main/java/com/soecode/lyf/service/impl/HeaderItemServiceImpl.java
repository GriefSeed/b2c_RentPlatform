package com.soecode.lyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.HeaderItemDao;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.service.HeaderItemService;

@Service
public class HeaderItemServiceImpl implements HeaderItemService {
	@Autowired
	private HeaderItemDao headerItemDao;

	@Override
	public int insertHeaderItem(HeaderItem headerItem) {
		return headerItemDao.insertHeaderItem(headerItem);
	}

}
