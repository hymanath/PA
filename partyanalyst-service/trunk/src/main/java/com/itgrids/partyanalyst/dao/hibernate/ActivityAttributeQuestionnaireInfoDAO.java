package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.model.ActivityAttributeQuestionnaireInfo;

public class ActivityAttributeQuestionnaireInfoDAO extends GenericDaoHibernate<ActivityAttributeQuestionnaireInfo, Long> implements IActivityAttributeQuestionnaireInfoDAO{

	public ActivityAttributeQuestionnaireInfoDAO() {
		super(ActivityAttributeQuestionnaireInfo.class);
	}
}
