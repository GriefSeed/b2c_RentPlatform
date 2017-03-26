package com.soecode.lyf.web;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.dto.Order;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Address;
import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.service.AccountService;
import com.soecode.lyf.service.AddressService;
import com.soecode.lyf.service.HeaderItemService;
import com.soecode.lyf.service.HeaderService;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.service.ItemsService;
import com.soecode.lyf.util.Util;

@Controller
@RequestMapping("/main")
public class MainController {
	// 用于打印日志
	// private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;
	@Autowired
	private ItemsService itemsService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private HeaderService headerService;
	@Autowired
	private HeaderItemService headerItemService;
	@Autowired
	private Header header;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	private Account login(@RequestBody Account a, Model model) {
		System.out.println(Util.getLocalIP());// 获得本机IP );
		// System.out.println("your are success!");
		// Map<String, Object> map = new HashMap<String, Object>();
		// account = new Account();
		Account account = accountService.getOneByName(a.getAccountName());
		if (account != null && account.getPassword().equals(a.getPassword().toString()))
			return account;
		else
			return null;
	}

	// 返回所有生活类的产品列表
	@RequestMapping(value = "/getItemsList")
	@ResponseBody
	private List<Items> getItemsList(@RequestBody int itemsType) {
		// System.out.println( Util.getLocalIP());//获得本机IP );
		System.out.println("your are success!+" + itemsType);
		List<Items> itemsList = itemsService.queryByItemsType(itemsType);
		return itemsList;
	}

	// 返回一种所有产品列表
	@RequestMapping(value = "/getSingleItemsList")
	@ResponseBody
	private List<Item> getSingleItemsList(@RequestBody int itemsId) {
		// System.out.println( Util.getLocalIP());//获得本机IP );
		// System.out.println("your are success!+" + itemsId);
		List<Item> singleItemsList = itemService.queryByItemsId(itemsId);
		return singleItemsList;
	}

	// 返回单个产品的详细信息
	@RequestMapping(value = "/getSingleItemDetail")
	@ResponseBody
	private Item getSingleItemDetail(@RequestBody int itemId) {
		Item singleItem = itemService.queryByItemId(itemId);
		return singleItem;
	}

	// 返回用户的所有地址
	@ResponseBody
	@RequestMapping(value = "/getAccontAddressList")
	private List<Address> getAccontAddressList(@RequestBody int accountId) {
		return addressService.queryByAccountId(accountId);
	}

	@ResponseBody
	@RequestMapping(value = "/createOrder")
	private String createOrder(@RequestBody Order order) {
		// 将接收的items切开，放入数组items,
		//System.out.println(order.getItems().toString());
		String[] itemList = order.getItems().toString().split("\\|");
		//System.out.println(itemList[0].replace("item_", ""));
		
		// 创建订单头，并取回自动生成的headerId，存入现在的日期
		//Header header = new Header();
		
		header.setAccountId(order.getAccountId());
		header.setCreateDate(new Date());
		header.setStatus("NEW");
		header.setAddress(order.getAddress());
		headerService.insertHeader(header);
		//System.out.println(header.getHeaderId()+"!!!!!!!!!!!!!!");
		// 将headerId和切开的item分别一一放入header_item,损坏和赔偿默认为0
		for(String i : itemList){
			HeaderItem hi = new HeaderItem();
			hi.setHeaderId(header.getHeaderId());
			hi.setItemId(Integer.parseInt(i.replace("item_", "")));
			headerItemService.insertHeaderItem(hi);
		}
		return "\"success\"";
	}
}
