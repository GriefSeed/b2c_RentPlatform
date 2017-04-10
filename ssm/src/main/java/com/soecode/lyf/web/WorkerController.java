package com.soecode.lyf.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

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
	 * 点击后，订单改为欠费debt阶段，修改相关数据，增加已使用的时间，
	 * 
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/debtHeader")
	@ResponseBody
	private String debtHeader(@RequestBody Header header) throws Exception {
		// 查询订单，抽取订单生成日期

		// 查询相关的item
		// 计算间隔日期，将时间一一存入相关的item的used_time，并将使用时间存入订单的end_date

		return "\"success\"";
	}

	/**
	 * debt欠费阶段，工作人员将商品损耗写入对应的header_item里
	 * 
	 * @param headerItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addItemDamage")
	@ResponseBody
	private String addItemDamage(@RequestBody HeaderItem headerItem) throws Exception {
		// 查询订单，抽取订单生成日期
		// 查询相关的item
		// 计算间隔日期，将时间一一存入相关的item的used_time，并将使用时间存入订单的end_date

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
	private String closeHeader(@RequestBody Header header) throws Exception {

		return "\"success\"";
	}

}
