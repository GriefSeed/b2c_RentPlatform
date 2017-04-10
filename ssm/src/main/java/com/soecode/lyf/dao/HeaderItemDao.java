package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;

public interface HeaderItemDao {
	// 插入数据
	@Insert("INSERT INTO header_item(header_id,item_id) VALUES(#{headerId},#{itemId})")
	int insertHeaderItem(HeaderItem headerItem);

	// 返回单个订单里所有的商品
	@Select("SELECT * FROM header_item where header_id=#{headerId}")
	List<HeaderItem> getItemsByHeaderId(@Param("headerId") int headerId);

	/**
	 * 删除该订单所有相关商品，解除冻结，USING状态后，通过前台设置，用户将不能删除订单
	 * 
	 * @param headerId
	 * @return
	 */
	@Delete("DELETE FROM header_item WHERE header_id = #{headerId}")
	int removeHeaderItems(@Param("headerId") int headerId);

	/**
	 * 添加或者修改订单下的商品的损耗，仅用作供用户观看赔偿，
	 * 
	 * @param headerItem
	 */
	@Update("UPDATE header_item SET attrition = #{attrition},compens = #{compens} WHERE header_item_id = #{headerItemId}")
	void modifyItemAttrition(HeaderItem headerItem);

	/**
	 * 根据headerItemId查找相对应的订单-商品，用于工作人员录入商品损耗
	 * @param headerItemId
	 * @return
	 */
	@Select("SELECT * FROM header_item where header_item_id=#{headerItemId}")
	HeaderItem getItemsByHeaderItemId(int headerItemId);
}
