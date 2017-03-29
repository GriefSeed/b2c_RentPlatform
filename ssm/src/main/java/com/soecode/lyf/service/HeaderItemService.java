package com.soecode.lyf.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.soecode.lyf.entity.HeaderItem;

public interface HeaderItemService {
	int insertHeaderItem(HeaderItem headerItem);
	
	List<HeaderItem> getItemsByHeaderId(int headerId);
}
