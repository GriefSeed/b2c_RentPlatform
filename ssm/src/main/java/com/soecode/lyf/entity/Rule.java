package com.soecode.lyf.entity;

public class Rule {
	private int ruleId;
	private int itemsId;
	private int damageUnit;
	private int damageCut;
	private int usedTimeUnit;
	private int usedTimeCut;
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	public int getItemsId() {
		return itemsId;
	}
	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}
	public int getDamageUnit() {
		return damageUnit;
	}
	public void setDamageUnit(int damageUnit) {
		this.damageUnit = damageUnit;
	}
	public int getDamageCut() {
		return damageCut;
	}
	public void setDamageCut(int damageCut) {
		this.damageCut = damageCut;
	}
	public int getUsedTimeUnit() {
		return usedTimeUnit;
	}
	public void setUsedTimeUnit(int usedTimeUnit) {
		this.usedTimeUnit = usedTimeUnit;
	}
	public int getUsedTimeCut() {
		return usedTimeCut;
	}
	public void setUsedTimeCut(int usedTimeCut) {
		this.usedTimeCut = usedTimeCut;
	}
	
	
}
