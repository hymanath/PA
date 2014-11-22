package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssigneeDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignee;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreSurveyUserAssigneeDAO extends GenericDaoHibernate<CadreSurveyUserAssignee, Long> implements ICadreSurveyUserAssigneeDAO{

	
	public CadreSurveyUserAssigneeDAO()
	{
		super(CadreSurveyUserAssignee.class);
	}
	
	public Long getLatestUserByCadreSurveyUser(Long userId)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserAssigneeId from CadreSurveyUserAssignee model where model.cadreSurveyUser.cadreSurveyUserId = :userId " +
				" order by model.cadreSurveyUserAssigneeId desc");
		query.setParameter("userId", userId);
		query.setMaxResults(1);
		return (Long) query.uniqueResult();
	}
	
	public List checkUserExists(Long userId,Date startDate)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserAssigneeId from CadreSurveyUserAssignee model where model.cadreSurveyUser.cadreSurveyUserId = :userId" +
				" and date(model.fromDate) = :startDate");
		query.setParameter("userId", userId);
		query.setParameter("startDate", startDate);
		return query.list();
		
	}
	
	public List<Object[]> getDuplicateUsersForUserId(Long userId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.name,model.mobileNo,date(model.fromDate),date(model.toDate) from CadreSurveyUserAssignee model");
		str.append( " where model.cadreSurveyUser.cadreSurveyUserId =:userId order by date(model.fromDate) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		return query.list();
	}	
	
}
