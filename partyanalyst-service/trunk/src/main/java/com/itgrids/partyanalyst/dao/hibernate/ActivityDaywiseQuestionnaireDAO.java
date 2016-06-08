package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityDaywiseQuestionnaireDAO;
import com.itgrids.partyanalyst.model.Activity;
import com.itgrids.partyanalyst.model.ActivityDaywiseQuestionnaire;

public class ActivityDaywiseQuestionnaireDAO extends GenericDaoHibernate<ActivityDaywiseQuestionnaire, Long> implements  IActivityDaywiseQuestionnaireDAO {
	public ActivityDaywiseQuestionnaireDAO() {
		super(ActivityDaywiseQuestionnaire.class);
		
	}

}
