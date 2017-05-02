package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.MessageDao;
import com.soecode.lyf.entity.Message;
import com.soecode.lyf.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Message> queryByAcceptAccountId(int acceptAccountId) {
		return messageDao.queryByAcceptAccountId(acceptAccountId);
	}

	@Override
	public int insertMessage(Message message) {
		return messageDao.insertMessage(message);
	}

	@Override
	public Message queryOneMessage(int messageId) {
		return messageDao.queryOneMessage(messageId);
	}

}
