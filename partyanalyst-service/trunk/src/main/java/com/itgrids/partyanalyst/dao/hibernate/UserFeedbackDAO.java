package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserFeedbackDAO;
import com.itgrids.partyanalyst.model.UserFeedback;

public class UserFeedbackDAO extends GenericDaoHibernate<UserFeedback, Long> implements IUserFeedbackDAO{

	public UserFeedbackDAO() {
		super(UserFeedback.class);
		
	}

}
