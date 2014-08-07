package com.itgrids.partyanalyst.dao.hibernate;
	
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyWmThirdPartyStatusDAO;
import com.itgrids.partyanalyst.model.SurveyWmThirdPartyStatus;

public class SurveyWmThirdPartyStatusDAO extends GenericDaoHibernate<SurveyWmThirdPartyStatus, Long> implements ISurveyWmThirdPartyStatusDAO
{

	public SurveyWmThirdPartyStatusDAO() {
		super(SurveyWmThirdPartyStatus.class);
	}
	
	public List<Object[]> getStatusTypes(){
		Query query = getSession().createQuery("select model.surveyWmThirdPartyStatusId, model.description from SurveyWmThirdPartyStatus model" +
				" order by model.surveyWmThirdPartyStatusId");
		return query.list();
	}

}
