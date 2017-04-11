package com.soecode.lyf.web;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.dto.Order;
import com.soecode.lyf.dto.OrderDetail;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Address;
import com.soecode.lyf.entity.Comment;
import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.service.AccountService;
import com.soecode.lyf.service.AddressService;
import com.soecode.lyf.service.CommentService;
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
	private CommentService commentService;
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

	/**
	 * 生成订单，生成header和header_item里的信息，冻结商品,1表示已租
	 * 
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createOrder")
	private String createOrder(@RequestBody Order order) {
		// 将接收的items切开，放入数组itemList,
		String[] itemList = order.getItems().toString().split("\\|");
		// System.out.println(itemList[0].replace("item_", ""));

		// 创建订单头，并取回自动生成的headerId，存入现在的日期
		// Header header = new Header();
		header.setAccountId(order.getAccountId());
		header.setCreateDate(new Date());
		header.setStatus("NEW");
		header.setAddress(order.getAddress());
		headerService.insertHeader(header);
		// System.out.println(header.getHeaderId()+"!!!!!!!!!!!!!!");
		// 将headerId和切开的item分别一一放入header_item,损坏和赔偿默认为0
		for (String i : itemList) {
			HeaderItem hi = new HeaderItem();
			hi.setHeaderId(header.getHeaderId());
			hi.setItemId(Integer.parseInt(i.replace("item_", "")));
			headerItemService.insertHeaderItem(hi);

			// 同时锁上相应的商品，让其他人不能选择
			Item it = new Item();
			it.setItemId(Integer.parseInt(i.replace("item_", "")));
			it.setStatus(1);
			itemService.modifyItemStatus(it);
		}
		return "\"success\"";
	}

	// 返回用户所有订单
	@ResponseBody
	@RequestMapping(value = "/getOrderList")
	public List<Header> getOrderList(@RequestBody int accountId) {
		return headerService.getHeadersByAccountId(accountId);
	}

	// 用户订单详细页面,返回该订单的所有详细信息、损耗率和商品详细信息
	@ResponseBody
	@RequestMapping(value = "/getOrderDetail")
	public OrderDetail getOrderDetail(@RequestBody int headerId) {
		List<HeaderItem> headerItemList = headerItemService.getItemsByHeaderId(headerId);
		OrderDetail od = new OrderDetail();
		ArrayList<Item> itemList = new ArrayList<Item>();
		// 将所有商品的详细信息塞进去
		for (HeaderItem hi : headerItemList) {
			itemList.add(itemService.queryByItemId(hi.getItemId()));
		}
		// 塞订单头
		od.setHeader(headerService.getHeaderByHeaderId(headerId));
		// 塞订单头-商品表里的赔偿信息
		od.setHeaderItemList(headerItemList);
		// 塞订单详细列表
		od.setItemList(itemList);
		return od;
	}

	/**
	 * 取消订单，删除header和header_item表里的信息，解冻商品,0表示待租
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelOrder")
	public String cancelOrder(@RequestBody int headerId) {
		// 先在header_item里取到所有相关商品
		List<HeaderItem> hi = headerItemService.getItemsByHeaderId(headerId);
		// 再循环，同时对商品进行解冻和删除header_item里的信息
		for (HeaderItem kid : hi) {
			Item i = new Item();
			i.setItemId(kid.getItemId());
			i.setStatus(0);
			itemService.modifyItemStatus(i);
		}
		headerItemService.removeHeaderItems(headerId);
		// 最后删除header表里的信息
		headerService.deleteHeader(headerId);
		return "\"success\"";
	}

	/**
	 * 用户从订单进入了评论页面后，查找相关的评论，没有就放回null
	 * 
	 * @param headerItemId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryComment")
	public Comment queryComment(@RequestBody int headerItemId) {
		// 抽取header_item_id,先检查是否存在评论，没有就插入，有就更改
		Comment cTemp = commentService.selectOneComment(headerItemId);
		if (cTemp == null) {
			return null;
		}
		return cTemp;
	}

	/**
	 * 用户新增或修改评论,前台要负责将itemId、headerItemId事先存入comment
	 * 
	 * @param comment
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyComment")
	public String modifyComment(@RequestBody Comment comment) {
		// 抽取header_item_id,先检查是否存在评论，没有就插入，有就更改
		Comment cTemp = commentService.selectOneComment(comment.getHeaderItemId());
		if (cTemp == null) {
			commentService.saveComment(comment);
		} else {
			commentService.modifyComment(comment);
		}
		return "\"success\"";
	}

	/**
	 * 查找该商品下的所有评论
	 * 
	 * @param itemId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryItemAllComment")
	public List<Comment> queryItemAllComment(@RequestBody int itemId) {
		return commentService.selectItemAllComment(itemId);
	}
}
