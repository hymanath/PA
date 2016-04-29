package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserFeedbackModuleDAO;
import com.itgrids.partyanalyst.model.UserFeedbackModule;

public class UserFeedbackModuleDAO extends GenericDaoHibernate<UserFeedbackModule, Long> implements IUserFeedbackModuleDAO{

	public UserFeedbackModuleDAO() {
		super(UserFeedbackModule.class);
		
	}

}
