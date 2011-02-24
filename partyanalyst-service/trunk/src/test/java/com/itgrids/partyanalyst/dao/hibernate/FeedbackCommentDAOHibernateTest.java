package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFeedbackCommentDAO;

public class FeedbackCommentDAOHibernateTest extends BaseDaoTestCase {
	
	private IFeedbackCommentDAO feedbackCommentDAO;

	public void setFeedbackCommentDAO(IFeedbackCommentDAO feedbackCommentDAO) {
		this.feedbackCommentDAO = feedbackCommentDAO;
	}

	public void test(){
		feedbackCommentDAO.getAll();		
	}

	
}