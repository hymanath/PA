package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaireOption;

public class ActivityQuestionnaireOptionDAO extends GenericDaoHibernate<ActivityQuestionnaireOption, Long> implements IActivityQuestionnaireOptionDAO{

	public ActivityQuestionnaireOptionDAO() {
		super(ActivityQuestionnaireOption.class);
		
	}

}
