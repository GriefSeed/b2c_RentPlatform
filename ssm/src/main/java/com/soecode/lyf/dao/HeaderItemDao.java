package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Insert;
import com.soecode.lyf.entity.HeaderItem;

public interface HeaderItemDao {
	// 插入数据
	@Insert("INSERT INTO header_item(header_id,item_id) VALUES(#{headerId},#{itemId})")
	int insertHeaderItem(HeaderItem headerItem);
}
