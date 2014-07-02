package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserTypeDAO;
import com.itgrids.partyanalyst.model.SurveyUserType;

public class SurveyUserTypeDAO extends GenericDaoHibernate<SurveyUserType, Long>  implements ISurveyUserTypeDAO{

	public SurveyUserTypeDAO() 
	{
		super(SurveyUserType.class);
	}
	
	public Long checkForUsertype(String description)
	{
		Query query = getSession().createQuery("select model.surveyUsertypeId from SurveyUserType model where model.description = :description");
		query.setParameter("description", description);
		return (Long) query.uniqueResult();
	}

}
