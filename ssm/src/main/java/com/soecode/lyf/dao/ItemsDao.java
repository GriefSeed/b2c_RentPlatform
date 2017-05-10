package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;

public interface ItemsDao {

	@Insert("INSERT INTO items(items_name,items_price,items_img,items_type,status) VALUES (#{itemsName},#{itemsPrice},#{itemsImg},#{itemsType},#{status})")
	int saveItems(Items items);

	/**
	 * 获取所有Items
	 * 
	 * @param itemsType
	 * @return
	 */
	@Select("SELECT * FROM items where items_type=#{itemsType}")
	List<Items> queryByItemsType(@Param("itemsType") int itemsType);

	/**
	 * 查询单个Items信息
	 * 
	 * @param itemsType
	 * @return
	 */
	@Select("SELECT * FROM items where items_id=#{itemsId}")
	Items queryByItemsId(@Param("itemsId") int itemsId);

	@Update("UPDATE items SET items_name = #{itemsName}, items_price = #{itemsPrice}, items_img = #{itemsImg}, items_type = #{itemsType}, status = #{status} WHERE items_id = #{itemsId}")
	void modifyItemsAll(Items items);
}
