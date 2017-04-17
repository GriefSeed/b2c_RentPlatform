package com.soecode.lyf.service.impl;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.BaseTest;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.service.BookService;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.util.Util;

public class itemServicesTest extends BaseTest {

	@Autowired
	private ItemService itemService;

	@Test
	public void test() {
		String str = "美的MK-TM1502";
		// 优先从商品表里查找商品，然后再是从类型表里找
		List<Item> itemList = itemService.queryLikeItemName(str);
		if (itemList.isEmpty()) {
			// 计算用户字符串的所有组合，从最后，即最长那条开始找，不为null即返回
			List<String> strGroup = Util.orderCharGroup(str);
			// 使用Collections反转List
			Collections.reverse(strGroup);
			for (String strTemp : strGroup) {
				itemList = itemService.queryLikeItemName(strTemp);
				if (itemList != null) {
					Iterator iterator = itemList.iterator();
					while (iterator.hasNext()) {
						Item item = (Item) iterator.next();
						System.out.println(item.getItemName());
						Thread.currentThread().interrupted();
					}
				}
			}
			// 如果还是为null，那就找商品类型
			// 优先查找用户的完整字符串
			itemList = itemService.queryLikeItemTypeName(str);
			if (itemList.isEmpty()) {
				// 计算用户字符串的所有组合，从最后，即最长那条开始找，不为null即返回
				strGroup = Util.orderCharGroup("str");
				// 使用Collections反转List
				Collections.reverse(strGroup);
				for (String strTemp : strGroup) {
					itemList = itemService.queryLikeItemTypeName(strTemp);
					if (itemList != null) {
						Iterator iterator = itemList.iterator();
						while (iterator.hasNext()) {
							Item item = (Item) iterator.next();
							System.out.println(item.getItemName());
							Thread.currentThread().interrupted();
						}
					}
				}
			} else {
				Iterator iterator = itemList.iterator();
				while (iterator.hasNext()) {
					Item item = (Item) iterator.next();
					System.out.println(item.getItemName());
					Thread.currentThread().interrupted();
				}
			}
		} else {
			Iterator iterator = itemList.iterator();
			while (iterator.hasNext()) {
				Item item = (Item) iterator.next();
				System.out.println(item.getItemName());
				Thread.currentThread().interrupted();
			}
		}

	}

}
