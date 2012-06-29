package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICommentDAO;

public class CommentDAOHibernateTest extends BaseDaoTestCase{
	
	private ICommentDAO commentDAO;

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public void test()
	{
		commentDAO.getAll();
	}
}
