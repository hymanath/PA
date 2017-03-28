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
	
	public List<Object[]> getTabNos(List<Long> surveyUserIds)
	{
		Query query = getSession().createQuery("select model.tabNo,model.surveyUserTabAssignId from SurveyUserTabAssign model where" +
				" model.surveyUser.surveyUserId in(:surveyUserIds) and model.activeStatus = 'Y'");
		query.setParameterList("surveyUserIds", surveyUserIds);
		return query.list();
		
	}
	
	public List<Long> getSurveyUserTabAssignIds(Long surveyUserID)
	{
		Query query = getSession().createQuery("select model.surveyUserTabAssignId from SurveyUserTabAssign model where" +
				" model.surveyUser.surveyUserId = :surveyUserID and model.activeStatus = 'Y'");
		query.setParameter("surveyUserID", surveyUserID);
		return query.list();
		
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyTabsBySurveyUserId(Long leaderId){
		
		return getHibernateTemplate().find(" select distinct model.surveyUserTabAssignId, model.tabNo from SurveyUserTabAssign model where model.surveyUser.surveyUserId = "+leaderId+"  and model.activeStatus = 'Y'  order by model.tabNo asc ");
	}
	
	public List<Object[]> getSurveyTabsBySurveyUserIdsList(List<Long> surveyUserIds){
		
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId,model.tabNo,model.surveyUserTabAssignId from SurveyUserTabAssign model where " +
				"  model.surveyUser.surveyUserId in (:surveyUserIds)  and model.activeStatus = 'Y' ");
		query.setParameterList("surveyUserIds", surveyUserIds);
		
		return query.list();
	}
	
	public int updateActiveStatus(List<Long> Ids)
	{
		Query query = getSession().createQuery("update SurveyUserTabAssign model set model.activeStatus = 'N' where model.surveyUserTabAssignId in (:Ids)");
		
		query.setParameterList("Ids", Ids);
		int count = query.executeUpdate();
		return count;
		
	}

}
