package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

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
	@Delete("DELETE FROM header_item WHERE header_id = #{headerId}")
	int removeHeaderItems(int headerId);
}
