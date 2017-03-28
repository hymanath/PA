package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityQuestionDAO;
import com.itgrids.partyanalyst.model.ActivityQuestion;

public class ActivityQuestionDAO extends GenericDaoHibernate<ActivityQuestion, Long> implements IActivityQuestionDAO{

	public ActivityQuestionDAO() {
		super(ActivityQuestion.class);
		
	}

}
