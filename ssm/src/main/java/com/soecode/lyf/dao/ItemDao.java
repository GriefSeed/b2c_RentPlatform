package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Item;

public interface ItemDao {
	/**
	 * 在单一类商品下查询所有item
	 * 
	 * @param itemsId
	 * @return Item列
	 */
	@Select("SELECT * FROM item where items_id=#{itemsId}")
	List<Item> queryByItemsId(@Param("itemsId") int itemsId);

	/**
	 * 查找单个商品
	 * 
	 * @param itemId
	 * @return 单个目标item
	 */
	@Select("SELECT * FROM item where item_id=#{itemId}")
	Item queryByItemId(@Param("itemId") int itemId);

	/**
	 * 改变单个商品的使用状态
	 * 
	 * @param item
	 */
	@Update("UPDATE item SET status = #{status} WHERE item_id = #{itemId}")
	void modifyItemStatus(Item item);

	/**
	 * 更改单个商品的所有信息
	 * 
	 * @param item
	 */
	@Update("UPDATE item SET item_name = #{itemName}, unit_cost = #{unitCost}, item_img = #{itemImg}, items_id = #{itemsId}, status = #{status}, used_time = #{usedTime}, damage = #{damage}, description = #{description} WHERE item_id = #{itemId}")
	void modifyItemAll(Item item);

	/**
	 * 根据商品类型模糊查找符合名字的商品
	 * 
	 * @param itemTypeName
	 * @return
	 */
	@Select("SELECT i.* FROM items its,item i,items_type it WHERE "
			+ "its.items_type = it.items_type_id AND its.items_id = i.items_id AND it.items_type_name LIKE \"%\"#{itemTypeName}\"%\"")
	List<Item> queryLikeItemTypeName(@Param("itemTypeName") String itemTypeName);

	/**
	 * 根据商品名字模糊查找符合名字的商品
	 * @param itemName
	 * @return
	 */
	@Select("SELECT i.* FROM item i WHERE i.item_name LIKE \"%\"#{itemName}\"%\"")
	List<Item> queryLikeItemName(@Param("itemName") String itemName);
}
