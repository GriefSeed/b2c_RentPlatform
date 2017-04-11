package com.soecode.lyf.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.HeaderItem;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.Rule;
import com.soecode.lyf.service.HeaderItemService;
import com.soecode.lyf.service.HeaderService;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.service.ItemsService;
import com.soecode.lyf.service.RuleService;
import com.soecode.lyf.util.Util;

@Controller
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemsService itemsService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private HeaderService headerService;
	@Autowired
	private HeaderItemService headerItemService;

	@RequestMapping(value = "/queryItemById")
	@ResponseBody
	private Item queryItemById(@RequestBody int itemsId) {
		return itemService.queryByItemId(itemsId);
	}

	@RequestMapping(value = "/queryItemsById")
	@ResponseBody
	private Items queryItemsById(@RequestBody int itemsId) {
		return itemsService.queryByItemsId(itemsId);
	}

	@RequestMapping(value = "/changeItem")
	@ResponseBody
	private String changeItem(@RequestBody Item item) throws Exception {
		// 取出原始数据
		Item itemTemp = itemService.queryByItemId(item.getItemId());
		if (item.getItemImg().indexOf("base64") != -1) {
			// 先根据itemsId找出存储的图片，切去/img/,取出名称
			long imgName = (new Date()).getTime() / 1000;
			// 取item的BASE64图片格式，转换为文件后存储
			String[] d = item.getItemImg().split("base64,");
			String suffix = Util.imgSuffix(d[0]);
			// 使用spring的base64工具包转二进制
			byte[] bs = Base64Utils.decodeFromString(d[1]);
			// 删去原文件
			System.out.println(System.getProperty("webapp.root") + "img\\"
					+ itemTemp.getItemImg().toString().replace("/img/", ""));
			(new File(System.getProperty("webapp.root") + "\\img\\"
					+ itemTemp.getItemImg().toString().replace("/img/", ""))).delete();
			// System.out.println(System.getProperty("webapp.root") + "\\img\\"
			// + imgName + suffix);
			File file = new File(System.getProperty("webapp.root") + "\\img\\" + imgName + suffix);
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			os.write(bs);
			os.flush();
			os.close();
			// 将文件名写入item的itemImg，代替itemImg里的BASE64字符串
			item.setItemImg("/img/" + imgName + suffix); // 存储

		}
		// 调用商品类型的降价规则,不能用item,要用items,价格未受污染,公式：总价*损耗折扣*时间折扣
		Rule r = ruleService.selectRuleByItemsId(item.getItemsId());
		Items i = itemsService.queryByItemsId(item.getItemsId());
		if (r != null) {
			System.out.println("i have a banana");
			// 因为都使用整数，所以要乘0.01
			double cutPrice = i.getItemsPrice() * (1 - (item.getDamage() / r.getDamageUnit()) * r.getDamageCut() * 0.01)
					* (1 - (Integer.valueOf(item.getUsedTime()) / r.getUsedTimeUnit()) * r.getUsedTimeCut() * 0.01);
			item.setUnitCost((int) cutPrice);
		}
		itemService.modifyItemAll(item);
		return "\"success\"";
	}

	@RequestMapping(value = "/changeItems")
	@ResponseBody
	private String changeItems(@RequestBody Items items) throws Exception {
		System.out.println(items.getItemsId() + " banana");
		if (items.getItemsImg().indexOf("base64") != -1) {
			// 先根据itemsId找出存储的图片，切去/img/,取出名称
			Items itemsTemp = itemsService.queryByItemsId(items.getItemsId());

			long imgName = (new Date()).getTime() / 1000;

			// 取item的BASE64图片格式，转换为文件后存储
			String[] d = items.getItemsImg().split("base64,");
			String suffix = Util.imgSuffix(d[0]);
			// 使用spring的base64工具包转二进制
			byte[] bs = Base64Utils.decodeFromString(d[1]);
			// 删去原文件
			(new File(System.getProperty("webapp.root") + "\\img\\"
					+ itemsTemp.getItemsImg().toString().replace("/img/", ""))).delete();
			// System.out.println(System.getProperty("webapp.root") + "\\img\\"
			// + imgName + suffix);
			File file = new File(System.getProperty("webapp.root") + "\\img\\" + imgName + suffix);
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			os.write(bs);
			os.flush();
			os.close();
			// 将文件名写入item的itemImg，代替itemImg里的BASE64字符串
			items.setItemsImg("/img/" + imgName + suffix); // 存储

		}
		itemsService.modifyItemsAll(items);
		return "\"success\"";
	}

	/**
	 * 查找相关的变价规则，有则提供修改，没有就让工作人员添加
	 * 
	 * @param itemsId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRule")
	@ResponseBody
	private Rule selectRule(@RequestBody int itemsId) throws Exception {
		Rule r = ruleService.selectRuleByItemsId(itemsId);
		if (r != null) {
			return r;
		} else
			return null;
	}

	/**
	 * 增加规则，新增加商品时必须要同时生成对应的降价规则
	 * 
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRule")
	@ResponseBody
	private String addRule(@RequestBody Rule rule) throws Exception {
		ruleService.insertRule(rule);
		return "\"success\"";
	}

	/**
	 * 修改订单
	 * 
	 * @param rule
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeRule")
	@ResponseBody
	private String changeRule(@RequestBody Rule rule) throws Exception {
		ruleService.modifyRule(rule);
		return "\"success\"";
	}

	/**
	 * 修改订单状态，改为USING
	 * 
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/usingHeader")
	@ResponseBody
	private String usingHeader(@RequestBody int headerId) throws Exception {
		// 保证除了状态status和start_date外，其他数据不被修改
		Header header = new Header();
		header.setHeaderId(headerId);
		// 录入时间，开始计费
		header.setStartDate(new Date());
		header.setStatus("USING");
		headerService.modifyHeaderUsing(header);
		return "\"success\"";
	}

	/**
	 * 点击后，订单改为欠费debt阶段，修改相关数据，增加已使用的时间
	 * 
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/debtHeader")
	@ResponseBody
	private String debtHeader(@RequestBody int headerId) throws Exception {
		// 查询订单，抽取订单生成日期，并将现在的时间存入订单的end_date
		Header headerTemp = headerService.getHeaderByHeaderId(headerId);
		headerTemp.setEndDate(new Date());
		headerTemp.setStatus("DEBT");
		// 计算从USING到debt之间的相差天数,因为订单里面包含很多商品，但租赁时间都是一样的，所以可以用同一个时间
		int usedTime = Util.daysBetween(headerTemp.getStartDate(), headerTemp.getEndDate());
		// 查询所有相关的item
		List<HeaderItem> headerItems = headerItemService.getItemsByHeaderId(headerTemp.getHeaderId());

		// 计算间隔日期，将时间一一与原来的usedTime相加，重新存入相关的item的used_time
		for (HeaderItem hi : headerItems) {
			Item i = itemService.queryByItemId(hi.getItemId());
			// 因为使用日期是String,没办法
			i.setUsedTime((Integer.valueOf(i.getUsedTime()) + Integer.valueOf(usedTime)) + "");
			// 存储进数据库
			itemService.modifyItemAll(i);
		}
		// 最后将headerTemp存入数据库
		headerService.modifyHeaderDebt(headerTemp);
		return "\"success\"";
	}

	/**
	 * debt欠费阶段，工作人员将商品损耗写入对应的header_item里，工作人员只可在debt状态录入商品损耗和赔偿
	 * 
	 * @param headerItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addItemDamage")
	@ResponseBody
	private String addItemDamage(@RequestBody HeaderItem headerItem) throws Exception {
		// 查看item是否为1，即锁定状态,这工作交给前端做，前端加个条件判断，非debt不允许调到损耗输入界面
		headerItemService.modifyItemAttrition(headerItem);
		return "\"success\"";
	}

	/**
	 * 关闭订单
	 * 
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/closeHeader")
	@ResponseBody
	private String closeHeader(@RequestBody int headerId) throws Exception {
		Header headerTemp = new Header();
		headerTemp.setHeaderId(headerId);
		headerTemp.setStatus("CLOSE");
		headerService.modifyHeaderCLOSE(headerTemp);
		return "\"success\"";
	}

}