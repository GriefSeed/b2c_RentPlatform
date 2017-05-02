package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Header;
import com.soecode.lyf.entity.Item;
import com.soecode.lyf.entity.Message;

public interface MessageDao {

	/**
	 * 查找所有账号下的通知
	 * 
	 * @param acceptAccountId
	 * @return
	 */
	@Select("SELECT * FROM message where accept_account_id=#{acceptAccountId}")
	List<Message> queryByAcceptAccountId(@Param("acceptAccountId") int acceptAccountId);

	/**
	 * 查询信息详情
	 * 
	 * @param acceptAccountId
	 * @return
	 */
	@Select("SELECT * FROM message where message_id=#{messageId}")
	Message queryOneMessage(@Param("messageId") int messageId);

	/**
	 * 插入信息，包括工作人员发送的消息或者是用户的反馈，工作人员ID可以为空,status暂时想不到有什么用
	 * 
	 * @param header
	 * @return
	 */
	@Insert("INSERT INTO message(send_account_id,accept_account_id,type,title,message,create_date,status) VALUES (#{sendAccountId},#{acceptAccountId},#{type},#{title},#{message},#{createDate},#{status})")
	int insertMessage(Message message);

}
