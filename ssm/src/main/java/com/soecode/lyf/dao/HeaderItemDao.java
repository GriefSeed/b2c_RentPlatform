package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	 * 删除该订单所有相关商品，解除冻结
	 * @param headerId
	 * @return
	 */
	@Delete("DELETE FROM header_item WHERE header_id = #{headerId}")
	int removeHeaderItems(@Param("headerId") int headerId);
}
