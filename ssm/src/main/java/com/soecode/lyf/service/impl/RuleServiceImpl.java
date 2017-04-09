package com.soecode.lyf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.RuleDao;
import com.soecode.lyf.entity.Rule;
import com.soecode.lyf.service.RuleService;

@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleDao ruleDao;

	@Override
	public int insertRule(Rule rule) {
		return ruleDao.insertRule(rule);
	}

	@Override
	public void modifyRule(Rule rule) {
		ruleDao.modifyRule(rule);
	}

	@Override
	public Rule selectRuleByItemsId(int itemsId) {
		return ruleDao.selectRuleByItemsId(itemsId);
	}

}
