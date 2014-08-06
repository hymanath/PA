package com.itgrids.partyanalyst.dao.hibernate;
	
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyWmThirdPartyStatusDAO;
import com.itgrids.partyanalyst.model.SurveyWmThirdPartyStatus;

public class SurveyWmThirdPartyStatusDAO extends GenericDaoHibernate<SurveyWmThirdPartyStatus, Long> implements ISurveyWmThirdPartyStatusDAO
{

	public SurveyWmThirdPartyStatusDAO() {
		super(SurveyWmThirdPartyStatus.class);
	}

}
