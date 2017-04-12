package com.soecode.lyf.dto;

import java.util.List;

import com.soecode.lyf.entity.*;

/**
 * 所有评论和平均分,减轻前台负担
 * 
 * @author ShuSe
 *
 */
public class CommentAvg {
	private Double avgScore;
	private List<Comment> commentList;

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
