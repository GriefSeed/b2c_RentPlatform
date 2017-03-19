package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Item;

public interface ItemDao {
	// 在单一类商品下查询所有item
	@Select("SELECT * FROM item where items_id=#{itemsId}")
	List<Item> queryByItemsId(@Param("itemsId") int itemsId);
	
	// 查找单个商品
	@Select("SELECT * FROM item where item_id=#{itemId}")
	Item queryByItemId(@Param("itemId") int itemId);
}

