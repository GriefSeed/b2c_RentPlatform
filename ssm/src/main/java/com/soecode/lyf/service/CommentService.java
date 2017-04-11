package com.soecode.lyf.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Comment;

public interface CommentService {

	/**
	 * 新增评分和评论
	 * 
	 * @param comment
	 * @return
	 */
	int saveComment(Comment comment);

	/**
	 * 修改评分和评论，因为header_item_id与commentId一对一，所以用header_item_id，只修改评分和评论
	 * 
	 * @param comment
	 */
	void modifyComment(Comment comment);

	/**
	 * 挑选该商品下的所有comment
	 * 
	 * @param itemId
	 * @return
	 */
	List<Comment> selectItemAllComment(int itemId);

	/**
	 * 使用headerItemId挑选单个评论
	 * 
	 * @param headerItemId
	 * @return
	 */
	Comment selectOneComment(int headerItemId);
}
