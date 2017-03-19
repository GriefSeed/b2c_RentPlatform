package com.soecode.lyf.web;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.service.AccountService;
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

	@RequestMapping(value = "/login")
	@ResponseBody
	private Account login(@RequestBody Account a, Model model) {
		System.out.println( Util.getLocalIP());//获得本机IP );
		//System.out.println("your are success!");
		Map<String, Object> map = new HashMap<String, Object>();
		Account account = new Account();
		account = accountService.getOneByName(a.getAccountName());
		if (account != null) {
			map.put("Account", account);
			//map.put("result", "1");
		} else
			map.put("Account", null);
		//return map;
		return account;
	}
	
	
	//返回所有生活类的产品列表
	@RequestMapping(value = "/getItemsList")
	@ResponseBody
	private List<Items> getItemsList(@RequestBody String itemsType) {
		//System.out.println( Util.getLocalIP());//获得本机IP );
		System.out.println("your are success!+" + itemsType);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Items> itemsList = itemsService.queryByItemsType(Integer.parseInt(itemsType));
		return itemsList;
	}
}
