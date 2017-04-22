package com.soecode.lyf.service.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.BaseTest;
import com.soecode.lyf.dto.EmailVo;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.service.AccountService;
import com.soecode.lyf.service.BookService;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.util.Util;

public class itemServicesTest extends BaseTest {

	@Autowired
	private ItemService itemService;

	@Autowired
	private AccountService accountService;

	@Test
	public void searchFunTest() {
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

	@Test
	public void commendFunTest() {
		// 先拿到平均值
		int accountAvgCost = itemService.getAvgCostOfAccountHeader(1);
		// 然后拿全部商品
		List<Item> itemList = itemService.getAllItem();
		if (!itemList.isEmpty()) {
			// 将所有Item以价格排序,价格从低到高
			itemList.sort((f, s) -> Integer.compare(f.getUnitCost(), s.getUnitCost()));
			// 记录下标变量,和被选中的商品
			int aimIndex = -1;
			List<Item> itemChoosed = new ArrayList<Item>();
			// 挑选跟平均价格差距最少的那个，向前向后取足够的商品个数
			for (Item item : itemList) {
				if (item.getUnitCost() > accountAvgCost) {
					aimIndex = itemList.indexOf(item);
					break;
				}
				// 如果比平均价格都高的商品全租出去了,那就从低处开始
				if (item.getUnitCost() < accountAvgCost) {
					aimIndex = itemList.indexOf(item);
					break;
				}
			}
			System.out.println(aimIndex + "            apple");

			int itemChoosedNum = 0;
			int i = aimIndex;
			// 向后取够2个，因为自己也算一个，所以
			while (i <= itemList.size() - 1 && itemList.size() >= itemList.size() - i && itemChoosedNum < 2) {
				itemChoosed.add(itemList.get(i));
				itemChoosedNum++;
				i++;
			}
			// 如果向后没有3个，那就向前取够数,如果前面也没有，那就只返回后面的也没办法了
			i = aimIndex;
			i--;
			while (i <= itemList.size() - 1 && itemList.size() >= itemList.size() - i && itemChoosedNum < 5) {
				itemChoosed.add(itemList.get(i));
				itemChoosedNum++;
				i--;
			}
			for (Item item : itemChoosed) {
				System.out.println(item.getItemId() + "    ddd     " + item.getItemName());
			}
		}
	}

	@Test
	public void sendEmailTest() throws Exception {
		EmailVo emailVo = new EmailVo();
		emailVo.setReceivers(new String[] { "1049640434@qq.com" });
		emailVo.setCc(new String[] {});
		emailVo.setBcc(new String[] {});
		emailVo.setSubject("B2C物品租赁平台——更改密码");
		emailVo.setSender("shusen2013@outlook.com");
		emailVo.setEmailContent("你的新密码是XXX，请尽快修改密码");

		accountService.sendEmailMessageOfSimpleText(emailVo, new Date());
		;

	}

}
