package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.model.CadreTxnUser;

public class CadreTxnUserDAO extends GenericDaoHibernate<CadreTxnUser, Long> implements ICadreTxnUserDAO{

	public CadreTxnUserDAO() {
		super(CadreTxnUser.class);
	}
	
	public List checkUserExists(Long cadreSurveyUserId , String mobileNo)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId from CadreTxnUser model where model.mobileNo =:mobileNo and model.cadreSurveyUser.cadreSurveyUserId =:cadreSurveyUserId");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return query.list();
		
	}
	
	public List<String> checkForExistsMobileNo(String mobileNo)
	{
		Query query = getSession().createQuery("select model.mobileNo from CadreTxnUser model where model.mobileNo =:mobileNo ");
		query.setParameter("mobileNo", mobileNo);
		return query.list();
	}

}
