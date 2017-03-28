package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyServiceDAO;
import com.itgrids.partyanalyst.model.IvrSurveyService;

public class IvrSurveyServiceDAO extends GenericDaoHibernate<IvrSurveyService, Long> implements IIvrSurveyServiceDAO{

	public IvrSurveyServiceDAO() {
		super(IvrSurveyService.class);
		
	}

	public Object[] getSurveyServiceBySurveyId(Long ivrSurveyServiceId){
		
		Query query = getSession().createQuery(" select model.ivrSurveyServiceId," +
								" model.serviceName," +
								" date(model.ivrDate)" +
								" from IvrSurveyService model" +
								" where model.ivrSurveyServiceId = :ivrSurveyServiceId" +
								" and model.isDeleted = 'false'");
		query.setParameter("ivrSurveyServiceId", ivrSurveyServiceId);
		
		return (Object[]) query.uniqueResult();
	}
}
