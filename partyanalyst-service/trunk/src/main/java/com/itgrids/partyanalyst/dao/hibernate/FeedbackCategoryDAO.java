package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFeedbackCategoryDAO;
import com.itgrids.partyanalyst.model.FeedbackCategory;

public class FeedbackCategoryDAO extends GenericDaoHibernate<FeedbackCategory, Long> implements IFeedbackCategoryDAO{

	public FeedbackCategoryDAO() {
		super(FeedbackCategory.class);
		// TODO Auto-generated constructor stub
	}

}
