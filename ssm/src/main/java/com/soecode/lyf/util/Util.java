package com.soecode.lyf.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Util {
	// 提取服务器的IP地址，作为图片的url发送给前端，而数据库只存储相对路径
	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	}

	public static String imgSuffix(String srcPrix) throws Exception {
		String suffix = "";
		if ("data:image/jpeg;".equalsIgnoreCase(srcPrix)) {// data:image/jpeg;base64,base64编码的jpeg图片数据
			suffix = ".jpg";
		} else if ("data:image/x-icon;".equalsIgnoreCase(srcPrix)) {// data:image/x-icon;base64,base64编码的icon图片数据
			suffix = ".ico";
		} else if ("data:image/gif;".equalsIgnoreCase(srcPrix)) {// data:image/gif;base64,base64编码的gif图片数据
			suffix = ".gif";
		} else if ("data:image/png;".equalsIgnoreCase(srcPrix)) {// data:image/png;base64,base64编码的png图片数据
			suffix = ".png";
		} else {
			throw new Exception("上传图片格式不合法");
		}
		return suffix;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 输出字符串的所有顺序组合
	 * 
	 * @param str
	 *            输入字符串
	 * @return 所有顺序组合
	 */
	public static List<String> orderCharGroup(String str) {
		ArrayList<String> group = new ArrayList<String>();
		combiantion(str.toCharArray(), group);
		return group;
	}

	public static void combiantion(char chs[], List<String> group) {
		if (chs.length == 0)
			return;

		Stack<Character> stack = new Stack<Character>();
		for (int i = 1; i <= chs.length; i++) {
			combine(chs, 0, i, stack, group);
		}
	}

	// 从字符数组中第begin个字符开始挑选number个字符加入list中
	public static void combine(char[] chs, int begin, int number, Stack<Character> stack, List<String> group) {
		if (number == 0) {
			// System.out.println(stack.iterator().toString());
			Iterator<Character> iterator = stack.iterator();
			String strTemp = "";
			while (iterator.hasNext()) {
				strTemp += iterator.next();
			}
			group.add(strTemp);
			return;
		}
		if (begin == chs.length) {
			return;
		}
		stack.push(chs[begin]);
		combine(chs, begin + 1, number - 1, stack, group);
		stack.pop();
		combine(chs, begin + 1, number, stack, group);
	}
}
