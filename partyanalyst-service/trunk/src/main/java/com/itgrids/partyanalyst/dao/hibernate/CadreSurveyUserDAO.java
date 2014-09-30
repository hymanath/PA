package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;

public class CadreSurveyUserDAO extends GenericDaoHibernate<CadreSurveyUser, Long> implements ICadreSurveyUserDAO{

	public CadreSurveyUserDAO() {
		super(CadreSurveyUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserByUserNameAndPassword(String userName , String password)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId from CadreSurveyUser model where model.userName = :userName and model.password = :password and model.isDeleted = 'N' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}

}
