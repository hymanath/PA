package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeIvrSurveyDAO;
import com.itgrids.partyanalyst.model.CommitteeIvrSurvey;

public class CommitteeIvrSurveyDAO extends GenericDaoHibernate<CommitteeIvrSurvey, Long> implements ICommitteeIvrSurveyDAO{

	public CommitteeIvrSurveyDAO() {
		super(CommitteeIvrSurvey.class);
	}
}
