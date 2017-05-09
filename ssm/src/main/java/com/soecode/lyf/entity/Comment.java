package com.soecode.lyf.entity;

import java.util.Date;

public class Comment {
	private int commentId;
	private int headerItemId;
	private int itemId;
	private int accountId;
	private Date createDate;
	private int score;
	private String comment;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getHeaderItemId() {
		return headerItemId;
	}

	public void setHeaderItemId(int headerItemId) {
		this.headerItemId = headerItemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
