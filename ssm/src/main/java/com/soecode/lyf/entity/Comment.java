package com.soecode.lyf.entity;

public class Comment {
	private int commentId;
	private int headerItemId;
	private int itemId;
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
