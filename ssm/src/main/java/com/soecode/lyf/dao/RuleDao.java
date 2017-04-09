package com.soecode.lyf.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Rule;

public interface RuleDao {

	@Insert("INSERT INTO rule VALUES (#{ruleId},#{itemsId},#{damageUnit},#{damageCut},#{usedTimeUnit},#{usedTimeCut})")
	int insertRule(Rule rule);
	
	@Update("UPDATE rule SET damage_unit = #{damageUnit},damage_cut = #{damageCut},used_time_unit = #{usedTimeUnit},used_time_cut = #{usedTimeCut} WHERE items_id = #{itemsId}")
	void modifyRule(Rule rule);
	
	@Select("SELECT * FROM rule where items_id = #{itemsId}")
	Rule selectRuleByItemsId(int itemsId);
}
