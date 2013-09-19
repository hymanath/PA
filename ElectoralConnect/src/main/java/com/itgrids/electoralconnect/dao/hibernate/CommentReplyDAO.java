package com.itgrids.electoralconnect.dao.hibernate;


import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.ICommentReplyDAO;
import com.itgrids.electoralconnect.model.CommentReply;



public class CommentReplyDAO extends GenericDaoHibernate<CommentReply, Long> implements ICommentReplyDAO{

	public CommentReplyDAO() {
		super(CommentReply.class);
	}
	
	public List<CommentReply> getRepliesForCommentById(Long commentId){
		Query query=getSession().createQuery("from CommentReply modal where modal.commentedOn=:commentedOn and modal.comment.isDelete='NO'");
		query.setParameter("commentedOn", commentId);
		return query.list();
	}
	
	public Long getRepliesForCommentByIdCount(Long commentId){
		Query query=getSession().createQuery("select count(commentReplyId) from CommentReply modal where modal.commentedOn=:commentedOn and modal.comment.isDelete='NO'");
		query.setParameter("commentedOn", commentId);
		return (Long)query.uniqueResult();
	}
	
	public List<CommentReply> getRepliesForCommentByIds(List<Long> cmmntIds){
		Query query=getSession().createQuery("from CommentReply modal where modal.commentedOn in (:commentedOn) and modal.comment.isDelete='NO'");
		query.setParameterList("commentedOn", cmmntIds);
		return query.list();
	}
	
	public List<Object[]> getReplyCountsByIdsList(List<Long> cmmntIds){
		Query query=getSession().createQuery("select modal.commentedOn,count(modal.commentReplyId) from CommentReply modal where modal.commentedOn in(:commentedOn) and modal.comment.isDelete='NO' group by modal.commentedOn");
		query.setParameterList("commentedOn", cmmntIds);
		return query.list();
	}
	
}
