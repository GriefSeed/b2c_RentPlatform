package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.HeaderItem;

public interface HeaderItemService {
	int insertHeaderItem(HeaderItem headerItem);

	List<HeaderItem> getItemsByHeaderId(int headerId);

	/**
	 * 删除该订单所有相关商品，解除冻结
	 * 
	 * @param headerId
	 * @return
	 */
	int removeHeaderItems(int headerId);

	/**
	 * 添加或者修改订单下的商品的损耗，仅用作供用户观看赔偿，
	 * 
	 * @param headerItem
	 */
	void modifyItemAttrition(HeaderItem headerItem);

	/**
	 * 根据headerItemId查找相对应的订单-商品，用于工作人员录入商品损耗
	 * 
	 * @param headerItemId
	 * @return
	 */
	HeaderItem getItemsByHeaderItemId(int headerItemId);
}
