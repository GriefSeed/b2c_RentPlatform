package com.soecode.lyf.entity;

import java.util.Date;

public class Message {
	private int messageId;
	private int sendAccountId;
	private int acceptAccountId;
	private int type;
	private String title;
	private String message;
	private Date createDate;
	private int status;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getSendAccountId() {
		return sendAccountId;
	}

	public void setSendAccountId(int sendAccountId) {
		this.sendAccountId = sendAccountId;
	}

	public int getAcceptAccountId() {
		return acceptAccountId;
	}

	public void setAcceptAccountId(int acceptAccountId) {
		this.acceptAccountId = acceptAccountId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
