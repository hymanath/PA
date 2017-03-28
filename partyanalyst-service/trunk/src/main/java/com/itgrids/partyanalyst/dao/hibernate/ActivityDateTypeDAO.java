package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.impl.IActivityDateTypeDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityDaywiseQuestionnaireDAO;
import com.itgrids.partyanalyst.model.ActivityDateType;
import com.itgrids.partyanalyst.model.ActivityDaywiseQuestionnaire;

public class ActivityDateTypeDAO extends
		GenericDaoHibernate<ActivityDateType, Long> implements
		IActivityDateTypeDAO {
	public ActivityDateTypeDAO() {
		super(ActivityDateType.class);

	}

}
