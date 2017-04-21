package com.soecode.lyf.service;

import java.util.Date;

import com.soecode.lyf.dto.EmailVo;
import com.soecode.lyf.entity.Account;

public interface AccountService {
	Account getOneByName(String accountName);

	/**
	 * 发送简单文本Email消息
	 * 
	 * @param emailVo
	 */
	public void sendEmailMessageOfSimpleText(EmailVo emailVo, Date date);
}
