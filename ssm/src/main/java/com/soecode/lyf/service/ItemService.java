package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Item;

public interface ItemService {
	List<Item> queryByItemsId(int itemsId);

	Item queryByItemId(int itemId);

	/**
	 * 改变单个商品的使用状态
	 * 
	 * @param item
	 */
	@Update("UPDATE item SET status = #{status} WHERE item_id = #{itemId}")
	void modifyItemStatus(Item item);
	
	/**
	 * 更改单个商品的所有信息
	 * @param item
	 */
	void modifyItemAll(Item item);
}
