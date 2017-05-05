package com.soecode.lyf.web;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import com.soecode.lyf.dto.CommentAvg;
import com.soecode.lyf.dto.EmailVo;
import com.soecode.lyf.dto.Order;
import com.soecode.lyf.dto.OrderDetail;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Address;
import com.soecode.lyf.entity.Comment;
import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.Message;
import com.soecode.lyf.service.AccountService;
import com.soecode.lyf.service.AddressService;
import com.soecode.lyf.service.CommentService;
import com.soecode.lyf.service.HeaderItemService;
import com.soecode.lyf.service.HeaderService;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.service.ItemsService;
import com.soecode.lyf.service.MessageService;
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
	private MessageService messageService;
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

	/**
	 * 用户忘记密码，发送密码邮件
	 * 
	 * @param emailInput
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendPasswordEmail")
	@ResponseBody
	public String sendPasswordEmail(@RequestBody String emailInput) throws Exception {
		// 根据email查找账号。如果没有就返回null
		Account account = accountService.queryByAccountEmail(emailInput);
		if (account == null)
			return null;
		else {
			// 提取密码，然后发送邮件，返回success字符串
			EmailVo emailVo = new EmailVo();
			emailVo.setReceivers(new String[] { account.getEmail() });
			emailVo.setCc(new String[] {});
			emailVo.setBcc(new String[] {});
			emailVo.setSubject("B2C物品租赁平台——更改密码");
			emailVo.setSender("shusen2013@outlook.com");
			emailVo.setEmailContent("你的密码是" + account.getPassword() + "，请尽快修改密码,如不是本人操作，请忽略该邮件");

			accountService.sendEmailMessageOfSimpleText(emailVo, new Date());
			return "\"success\"";
		}
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
	 * 查找该商品下的所有评论和平均分
	 * 
	 * @param itemId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryItemAllComment")
	public CommentAvg queryItemAllComment(@RequestBody int itemId) {
		CommentAvg commentAvg = new CommentAvg();
		if (commentService.selectAvgScoreOfItem(itemId) != null)
			commentAvg.setAvgScore(commentService.selectAvgScoreOfItem(itemId));
		else
			commentAvg.setAvgScore(null);
		commentAvg.setCommentList(commentService.selectItemAllComment(itemId));
		return commentAvg;
	}

	/**
	 * 返回搜索结果
	 * 
	 * @param str
	 * @return
	 */
	public List<Item> searchResult(@RequestBody String str) {
		// 优先查找用户的完整
		List itemList = itemService.queryLikeItemTypeName(str);
		if (itemList == null) {
			// 计算用户字符串的所有组合，从最后，即最长那条开始找，不为null即返回
			List<String> strGroup = Util.orderCharGroup("abc");
			// 使用Collections反转List
			Collections.reverse(strGroup);
			for (String strTemp : strGroup) {
				itemList = itemService.queryLikeItemTypeName(str);
				if (itemList != null) {
					return itemList;
				}
			}
		}
		return itemList;
	}

	/**
	 * 返回用户的所有信息
	 * 
	 * @param accountId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAccountMessage")
	public List<Message> getAccountMessage(@RequestBody int accountId) {
		return messageService.queryByAcceptAccountId(accountId);
	}

	/**
	 * 插入信息,反馈信息
	 * 
	 * @param message
	 */
	@ResponseBody
	@RequestMapping(value = "/sendMessage")
	public String sendMessage(@RequestBody Message message) {
		message.setCreateDate(new Date());
		// 0是工作人员发给用户，1是用户反馈信息
		message.setType(1);
		messageService.insertMessage(message);
		return "\"success\"";
	}

	/**
	 * 搜索功能
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/searchFun")
	public List<Item> searchFun(@RequestBody String searchValue) throws UnsupportedEncodingException {
		// 转码，既可以在controller层进行转码，也可以在tomcat里配置
		String str = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(str + " apple");
		// 优先从商品表里查找商品，然后再是从类型表里找
		List<Item> itemList = itemService.queryLikeItemName(str);
		if (itemList.isEmpty()) {
			// 计算用户字符串的所有组合，从最后，即最长那条开始找，不为null即返回
			List<String> strGroup = Util.orderCharGroup(str);
			// 使用Collections反转List
			Collections.reverse(strGroup);
			for (String strTemp : strGroup) {
				itemList = itemService.queryLikeItemName(strTemp);
				if (!itemList.isEmpty()) {
					return itemList;
				}
			}
			// 如果还是为null，那就找商品类型
			// 优先查找用户的完整字符串
			itemList = itemService.queryLikeItemTypeName(str);
			if (itemList.isEmpty()) {
				// 计算用户字符串的所有组合，从最后，即最长那条开始找，不为null即返回
				strGroup = Util.orderCharGroup(str);
				// 使用Collections反转List
				Collections.reverse(strGroup);
				for (String strTemp : strGroup) {
					itemList = itemService.queryLikeItemTypeName(strTemp);
					if (!itemList.isEmpty()) {
						return itemList;
					}
				}
			} else {
				return itemList;
			}
		} else {
			return itemList;
		}
		return null;
	}

}
