package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Message;

public interface MessageService {
	/**
	 * 查找所有账号下的通知
	 * 
	 * @param acceptAccountId
	 * @return
	 */
	List<Message> queryByAcceptAccountId(@Param("acceptAccountId") int acceptAccountId);

	/**
	 * 查询信息详情
	 * 
	 * @param acceptAccountId
	 * @return
	 */
	Message queryOneMessage(@Param("messageId") int messageId);

	/**
	 * 插入信息，包括工作人员发送的消息或者是用户的反馈，工作人员ID可以为空,status暂时想不到有什么用
	 * 
	 * @param header
	 * @return
	 */
	int insertMessage(Message message);
}
