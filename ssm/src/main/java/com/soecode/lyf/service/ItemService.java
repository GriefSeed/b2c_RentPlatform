package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.Item;

public interface ItemService {
	List<Item> queryByItemsId(int itemsId);
	
	Item queryByItemId(int itemId);
}
