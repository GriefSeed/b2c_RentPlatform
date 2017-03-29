package com.soecode.lyf.dto;

import java.util.List;

import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.entity.Item;

/**
 * 
 * @author ShuSe 包含订单头所有信息和该订单下的所有商品详细信息
 */
public class OrderDetail {
	private Header header;
	private List<HeaderItem> headerItemList;
	private List<Item> itemList;
	
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<HeaderItem> getHeaderItemList() {
		return headerItemList;
	}
	public void setHeaderItemList(List<HeaderItem> headerItemList) {
		this.headerItemList = headerItemList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
}
