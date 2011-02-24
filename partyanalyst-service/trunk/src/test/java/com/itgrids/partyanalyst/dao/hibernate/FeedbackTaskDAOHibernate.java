package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFeedbackTaskDAO;

public class FeedbackTaskDAOHibernate extends BaseDaoTestCase {
	
	private IFeedbackTaskDAO feedbackTaskDAO;

	public void setFeedbackTaskDAO(IFeedbackTaskDAO feedbackTaskDAO) {
		this.feedbackTaskDAO = feedbackTaskDAO;
	}

	public void test(){
		feedbackTaskDAO.getAll();		
	}

	
}