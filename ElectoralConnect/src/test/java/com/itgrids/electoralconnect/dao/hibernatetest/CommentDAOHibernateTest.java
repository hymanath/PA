package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.ICommentDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;

public class CommentDAOHibernateTest extends BaseDaoTestCase{
	
	private ICommentDAO commentDAO;

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}


	public void test(){
		commentDAO.getAll();
	}

}
