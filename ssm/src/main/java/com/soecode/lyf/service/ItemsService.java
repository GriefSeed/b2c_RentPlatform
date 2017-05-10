package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.Items;

public interface ItemsService {

	int saveItems(Items items);

	List<Items> queryByItemsType(int itemsType);

	Items queryByItemsId(int itemsId);

	void modifyItemsAll(Items items);
}
