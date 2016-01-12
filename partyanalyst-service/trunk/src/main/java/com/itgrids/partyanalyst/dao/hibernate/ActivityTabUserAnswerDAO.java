package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityTabUserAnswerDAO;
import com.itgrids.partyanalyst.model.ActivityTabUserAnswer;

public class ActivityTabUserAnswerDAO extends GenericDaoHibernate<ActivityTabUserAnswer,Long> implements IActivityTabUserAnswerDAO{
	public ActivityTabUserAnswerDAO(){
		super(ActivityTabUserAnswer.class);
	}
}
