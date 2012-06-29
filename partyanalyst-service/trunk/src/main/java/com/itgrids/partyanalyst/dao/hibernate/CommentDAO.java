package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ICommentDAO;
import com.itgrids.partyanalyst.model.Comment;

public class CommentDAO extends GenericDaoHibernate<Comment, Long>implements ICommentDAO {
	
	public CommentDAO() {
		super(Comment.class);
	}
	
}
