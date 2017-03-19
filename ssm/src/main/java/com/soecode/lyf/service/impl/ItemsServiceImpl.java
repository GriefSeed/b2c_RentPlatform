package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.ItemsDao;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService{
	@Autowired
	private ItemsDao itemsDao;
	@Override
	public List<Items> queryByItemsType(int itemsType) {
		return itemsDao.queryByItemsType(itemsType);
	}
	
}
