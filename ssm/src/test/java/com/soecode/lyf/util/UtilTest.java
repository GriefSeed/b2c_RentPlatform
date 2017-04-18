package com.soecode.lyf.util;

import java.util.List;

import org.junit.Test;

import com.soecode.lyf.entity.Book;

public class UtilTest {

	@Test
	public void OrderCharGroupTest() throws Exception {
		List<String> aimStr = Util.orderCharGroup("水壶电");
		for (String str : aimStr) {
			System.out.println(str);
		}
	}
}
