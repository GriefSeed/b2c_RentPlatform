package com.soecode.lyf.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soecode.lyf.entity.Account;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.service.ItemService;
import com.soecode.lyf.service.ItemsService;
import com.soecode.lyf.util.Util;

@Controller
@RequestMapping("/worker")
public class WorkerController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemsService itemsService;

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
		if (item.getItemImg().indexOf("base64") != -1) {
			// 先根据itemsId找出存储的图片，切去/img/,取出名称
			Item itemTemp = itemService.queryByItemId(item.getItemId());

			String imgName = (itemTemp.getItemImg().toString().replace("/img/", "")).split("\\.")[0];

			// 取item的BASE64图片格式，转换为文件后存储
			String[] d = item.getItemImg().split("base64,");
			String suffix = Util.imgSuffix(d[0]);
			// 使用spring的base64工具包转二进制
			byte[] bs = Base64Utils.decodeFromString(d[1]);
			// 删去原文件
			ResourceUtils.getFile(item.getItemImg()).delete();
			System.out.println(System.getProperty("webapp.root") + "\\img\\" + imgName + suffix);
			File file = new File(System.getProperty("webapp.root") + "\\img\\" + imgName + suffix);
			file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			os.write(bs);
			os.flush();
			os.close();
			// 将文件名写入item的itemImg，代替itemImg里的BASE64字符串
			item.setItemImg("/img/" + imgName + suffix); // 存储
			itemService.modifyItemAll(item);

		}

		return "\"success\"";
	}

	@RequestMapping(value = "/changeItems")
	@ResponseBody
	private String changeItems(@RequestBody Items items) {
		// 先根据itemsId找出存储的图片，切去/img/,取出名称
		// 取item的BASE64图片格式，转换为文件后用原名称存储
		// 将文件名写入item的itemImg，代替itemImg里的BASE64字符串
		// 存储
		itemsService.modifyItemsAll(items);
		return "\"success\"";
	}

}
