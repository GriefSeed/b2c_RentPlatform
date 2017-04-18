package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
	void modifyItemStatus(Item item);

	/**
	 * 更改单个商品的所有信息
	 * 
	 * @param item
	 */
	void modifyItemAll(Item item);

	/**
	 * 模糊查找符合名字的商品类型
	 * 
	 * @param itemName
	 * @return
	 */
	List<Item> queryLikeItemTypeName(String itemTypeName);

	/**
	 * 根据商品名字模糊查找符合名字的商品
	 * 
	 * @param itemName
	 * @return
	 */
	List<Item> queryLikeItemName(String itemName);

	/**
	 * 返回该用户的所有购买过的商品的平均价格
	 * 
	 * @param accountId
	 * @return
	 */
	int getAvgCostOfAccountHeader(@Param("accountId") int accountId);

	/**
	 * 返回商品列表里的所有商品
	 * 
	 * @return
	 */
	List<Item> getAllItem();
}
