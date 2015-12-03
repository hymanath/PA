package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;

public class ActivityQuestionAnswerDAO extends GenericDaoHibernate<ActivityQuestionAnswer, Long> implements IActivityQuestionAnswerDAO{

	public ActivityQuestionAnswerDAO() {
		super(ActivityQuestionAnswer.class);
		
	}

}
