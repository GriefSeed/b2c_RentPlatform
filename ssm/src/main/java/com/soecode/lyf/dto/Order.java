package com.soecode.lyf.dto;
/**
 * 
 * @author ShuSe
 * 用于存储后台与前台间的订单中转数据
 */
public class Order {
	private int accountId;//用户ID
	private int amount;//总价
	private String address;//地址
	private String items;//所包含的商品ID
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
	
}
