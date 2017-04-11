package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Comment;

public interface CommentDao {

	/**
	 * 新增评分和评论
	 * 
	 * @param comment
	 * @return
	 */
	@Insert("INSERT INTO comment VALUES (#{headerItemId},#{itemId},#{score},#{comment})")
	int saveComment(Comment comment);

	/**
	 * 修改评分和评论，因为header_item_id与commentId一对一，所以用header_item_id，只修改评分和评论
	 * 
	 * @param comment
	 */
	@Update("UPDATE comment SET score = #{score},comment = #{comment} WHERE header_item_id = #{headerItemId}")
	void modifyComment(Comment comment);

	/**
	 * 挑选该商品下的所有comment
	 * 
	 * @param itemId
	 * @return
	 */
	@Select("SELECT * FROM comment where item_id = #{itemId}")
	List<Comment> selectItemAllComment(int itemId);

	/**
	 * 使用headerItemId挑选单个评论
	 * 
	 * @param headerItemId
	 * @return
	 */
	@Select("SELECT * FROM comment where header_item_id = #{headerItemId}")
	Comment selectOneComment(int headerItemId);
}
