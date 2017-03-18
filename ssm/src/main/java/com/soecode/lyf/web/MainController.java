package com.soecode.lyf.web;

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
import com.soecode.lyf.service.AccountService;

@Controller
@RequestMapping("/main")
public class MainController {
	// 用于打印日志
	// private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/login")
	@ResponseBody
	private Map<String, Object> test(@RequestBody Account a, Model model) {
		System.out.println(a.getAccountName());
		System.out.println("your are success!");
		Map<String, Object> map = new HashMap<String, Object>();
		Account account = new Account();
		account = accountService.getOneByName(a.getAccountName());
		if (account != null) {
			map.put("Account", account);
			//map.put("result", "1");
		} else
			map.put("Account", null);
		return map;
	}
}
