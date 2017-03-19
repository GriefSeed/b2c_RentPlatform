package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.ItemDao;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> queryByItemsId(int itemsId) {
		return itemDao.queryByItemsId(itemsId);
	}

	@Override
	public Item queryByItemId(int itemId) {
		return itemDao.queryByItemId(itemId);
	}

}
