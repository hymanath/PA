package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public class CadreSurveyUserAssignDetailsDAO extends GenericDaoHibernate<CadreSurveyUserAssignDetails, Long> implements ICadreSurveyUserAssignDetailsDAO{

	public CadreSurveyUserAssignDetailsDAO() {
		super(CadreSurveyUserAssignDetails.class);
	}

	
	public List<CadreSurveyUserAssignDetails> getCadreAssinedDetails(Long cadreSurveyUserId)
	{
		Query query = getSession().createQuery("select model from CadreSurveyUserAssignDetails model where model.cadreSurveyUserId = :cadreSurveyUserId and model.isDeleted = 'N'");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return query.list();
	}
}
