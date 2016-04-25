package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityStatusQuestionnaireDAO;
import com.itgrids.partyanalyst.model.AcademicYear;
import com.itgrids.partyanalyst.model.ActivityStatusQuestionnaire;

public class ActivityStatusQuestionnaireDAO extends GenericDaoHibernate<ActivityStatusQuestionnaire, Long> implements IActivityStatusQuestionnaireDAO {

	public ActivityStatusQuestionnaireDAO() {
		super(ActivityStatusQuestionnaire.class);
	}

}
