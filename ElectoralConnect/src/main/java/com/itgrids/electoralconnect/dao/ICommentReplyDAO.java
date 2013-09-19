package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.CommentReply;

public interface ICommentReplyDAO  extends GenericDao<CommentReply, Long> {
	public List<CommentReply> getRepliesForCommentById(Long commentId);
	public Long getRepliesForCommentByIdCount(Long commentId);
	public List<CommentReply> getRepliesForCommentByIds(List<Long> cmmntIds);
	public List<Object[]> getReplyCountsByIdsList(List<Long> cmmntIds);
}
