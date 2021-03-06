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

	/**
	 * 修改订单状态
	 */
	@Override
	public void modifyItemStatus(Item item) {
		itemDao.modifyItemStatus(item);
	}

	@Override
	public void modifyItemAll(Item item) {
		itemDao.modifyItemAll(item);
	}

	@Override
	public List<Item> queryLikeItemTypeName(String itemTypeName) {
		return itemDao.queryLikeItemTypeName(itemTypeName);
	}

	@Override
	public List<Item> queryLikeItemName(String itemName) {
		return itemDao.queryLikeItemName(itemName);
	}

	@Override
	public int getAvgCostOfAccountHeader(int accountId) {
		return itemDao.getAvgCostOfAccountHeader(accountId);
	}

	@Override
	public List<Item> getAllItem() {
		return itemDao.getAllItem();
	}

	@Override
	public int saveItem(Item item) {
		return itemDao.saveItem(item);
	}

}
