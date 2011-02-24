package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFeedbackDAO;

public class FeedbackDAOHibernateTest extends BaseDaoTestCase {
	
	private IFeedbackDAO feedbackDAO;

	public void setFeedbackDAO(IFeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	public void test(){
		feedbackDAO.getAll();		
	}

	
}