package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserTabAssignDAO;
import com.itgrids.partyanalyst.model.SurveyUserTabAssign;

public class SurveyUserTabAssignDAO extends GenericDaoHibernate<SurveyUserTabAssign, Long>  implements ISurveyUserTabAssignDAO{

	public SurveyUserTabAssignDAO()
	{
		super(SurveyUserTabAssign.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyTabsBySurveyUserId(Long leaderId){
		
		return getHibernateTemplate().find(" select model.surveyUserTabAssignId, model.tabNo from SurveyUserTabAssign model where model.surveyUser.surveyUserId = "+leaderId+" order by model.tabNo asc ");
	}
	
	public List<Object[]> getSurveyTabsBySurveyUserIdsList(List<Long> surveyUserIds){
		
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId,model.tabNo from SurveyUserTabAssign model where " +
				"  model.surveyUser.surveyUserId in (:surveyUserIds)  and model.activeStatus = 'Y' ");
		query.setParameterList("surveyUserIds", surveyUserIds);
		
		return query.list();
	}

}
