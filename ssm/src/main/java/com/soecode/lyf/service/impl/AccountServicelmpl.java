package com.soecode.lyf.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.AccountDao;
import com.soecode.lyf.dto.EmailVo;
import com.soecode.lyf.entity.Account;
import com.soecode.lyf.service.AccountService;

@Service
public class AccountServicelmpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	private JavaMailSender mailSender;

	@Autowired
	public void setEmailTemplate(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public Account getOneByName(String accountName) {
		return accountDao.queryOneByName(accountName);
	}

	@Override
	public void sendEmailMessageOfSimpleText(EmailVo emailVo, Date date) {
		SimpleMailMessage simpleTextMessage = new SimpleMailMessage();
		simpleTextMessage.setFrom(emailVo.getSender());
		simpleTextMessage.setTo(emailVo.getReceivers());
		if (emailVo.getBcc().length > 0) {
			simpleTextMessage.setBcc(emailVo.getBcc());
		}
		if (emailVo.getCc().length > 0) {
			simpleTextMessage.setCc(emailVo.getCc());
		}
		simpleTextMessage.setSubject(emailVo.getSubject());
		simpleTextMessage.setText(emailVo.getEmailContent());

		if (null == date) {
			date = new Date();
		}
		simpleTextMessage.setSentDate(date);

		mailSender.send(simpleTextMessage);
	}

	@Override
	public Account queryByAccountId(int accountId) {
		return accountDao.queryByAccountId(accountId);
	}

	@Override
	public Account queryByAccountEmail(String email) {
		return accountDao.queryByAccountEmail(email);
	}

	@Override
	public int insertAccountMain(Account account) {
		return accountDao.insertAccountMain(account);
	}

	@Override
	public void modifyAccount(Account account) {
		accountDao.modifyAccount(account);
	}

	@Override
	public void modifyAccountPassword(Account account) {
		accountDao.modifyAccountPassword(account);
	}

	@Override
	public void modifyAccountByWorker(Account account) {
		accountDao.modifyAccountByWorker(account);
	};
}
