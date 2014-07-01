package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.model.SurveyUser;

public class SurveyUserDAO extends GenericDaoHibernate<SurveyUser, Long> implements ISurveyUserDAO{

	public SurveyUserDAO() 
	{
		super(SurveyUser.class);
	}
	
	public Long getUserDetails(String userName,String password)
	{
		Query query = getSession().createQuery("select model.surveyUserId from SurveyUser model where model.userName = :userName and model.password = : password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (Long) query.uniqueResult();
	}

}
