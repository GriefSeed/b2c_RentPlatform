package com.soecode.lyf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soecode.lyf.dao.CommentDao;
import com.soecode.lyf.entity.Comment;
import com.soecode.lyf.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public int saveComment(Comment comment) {
		return commentDao.saveComment(comment);
	}

	@Override
	public void modifyComment(Comment comment) {
		commentDao.modifyComment(comment);
	}

	@Override
	public List<Comment> selectItemAllComment(int itemId) {
		return commentDao.selectItemAllComment(itemId);
	}

	@Override
	public Comment selectOneComment(int headerItemId) {
		return commentDao.selectOneComment(headerItemId);
	}

	@Override
	public Double selectAvgScoreOfItem(int itemId) {
		return commentDao.selectAvgScoreOfItem(itemId);
	}

}
