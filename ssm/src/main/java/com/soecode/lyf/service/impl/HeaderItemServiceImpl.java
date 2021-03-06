package com.soecode.lyf.service.impl;

import java.util.List;

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

	@Override
	public List<HeaderItem> getItemsByHeaderId(int headerId) {
		return headerItemDao.getItemsByHeaderId(headerId);
	}

	@Override
	public int removeHeaderItems(int headerId) {
		return headerItemDao.removeHeaderItems(headerId);
	}

	@Override
	public void modifyItemAttrition(HeaderItem headerItem) {
		headerItemDao.modifyItemAttrition(headerItem);
	}

	@Override
	public HeaderItem getItemsByHeaderItemId(int headerItemId) {
		return headerItemDao.getItemsByHeaderItemId(headerItemId);
	}

}
