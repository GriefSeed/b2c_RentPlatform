package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Items;

public interface ItemsDao {
	
	//查询所有items
	@Select("SELECT * FROM items where items_type=#{itemsType}")
	List<Items> queryByItemsType(@Param("itemsType") int itemsType);
}
