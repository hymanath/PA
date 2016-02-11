package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyRoundDAO;
import com.itgrids.partyanalyst.model.IvrSurveyRound;

public class IvrSurveyRoundDAO extends GenericDaoHibernate<IvrSurveyRound, Long> implements IIvrSurveyRoundDAO{

	public IvrSurveyRoundDAO() {
		super(IvrSurveyRound.class);
		
	}

}
