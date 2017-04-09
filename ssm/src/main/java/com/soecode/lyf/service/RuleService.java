package com.soecode.lyf.service;

import com.soecode.lyf.entity.Rule;

public interface RuleService {

	int insertRule(Rule rule);

	void modifyRule(Rule rule);

	Rule selectRuleByItemsId(int itemsId);
}
