package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaire;

public class ActivityQuestionnaireDAO extends GenericDaoHibernate<ActivityQuestionnaire, Long> implements IActivityQuestionnaireDAO{

	public ActivityQuestionnaireDAO() {
		super(ActivityQuestionnaire.class);
		
	}

}
