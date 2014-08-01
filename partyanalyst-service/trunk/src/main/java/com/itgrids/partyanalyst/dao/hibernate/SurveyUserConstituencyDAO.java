package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;
import com.itgrids.partyanalyst.model.SurveyUserConstituency;

public class SurveyUserConstituencyDAO extends GenericDaoHibernate<SurveyUserConstituency, Long> implements ISurveyUserConstituencyDAO{

	public SurveyUserConstituencyDAO() {
		super(SurveyUserConstituency.class);
		
	}
	
	public List<Long> getAlreadyAssignedUsers()
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId from SurveyUserConstituency model where model.activeStatus = 'Y'");
		return query.list();
	}
	
	public List<Long> getAlreadyAssignedConstituencies(Long surveyUserTypeId)
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId from SurveyUserConstituency model where model.activeStatus = 'Y' and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUserTypeId");
		
		query.setParameter("surveyUserTypeId", surveyUserTypeId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencyList(){
		
		return getHibernateTemplate().find(" select distinct model.constituency.constituencyId , model.constituency.name from SurveyUserConstituency model order by model.constituency.name asc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencyLeadersList(Long constituencyId){
		
		return getHibernateTemplate().find("select distinct model.surveyUser.surveyUserId, model.surveyUser.firstName,model.surveyUser.lastName,model.surveyUser.userName from SurveyUserConstituency model " +
				" where model.constituency.constituencyId = "+constituencyId+" and model.activeStatus = 'Y' and model.surveyUser.activeStatus = 'Y' order by model.surveyUser.firstName asc");
	}
	
	
	public List<Object[]> getSurveyUserConstituency(Long surveyUserId)
	{
		
		Query query = getSession().createQuery("select distinct model.surveyUserConstituencyId,model.constituency.constituencyId from SurveyUserConstituency model" +
				" where model.surveyUser.surveyUserId = :surveyUserId");
		query.setParameter("surveyUserId", surveyUserId);
		return query.list();
		
	}
	
	public int updateActiveStatusByList(List<Long> Ids)
	{
		Query query = getSession().createQuery("update SurveyUserConstituency model set model.activeStatus = 'N' where model.surveyUserConstituencyId in (:Ids)");
		
		query.setParameterList("Ids", Ids);
		int count = query.executeUpdate();
		return count;
		
	}
	
	public List<Object[]> getExistedConstituenciesDetailsByUserId(Long userId)
	{
		Query query = getSession().createQuery("select distinct SUC.constituency.constituencyId,SUC.constituency.name from SurveyUserConstituency SUC " +
				"where " +
				"SUC.surveyUser.surveyUserId in(select  SUR.surveyLeader.surveyUserId from SurveyUserRelation SUR where  SUR.surveyUser.surveyUserId  = :surveyUserId " +
				"and SUR.activeStatus = 'Y') and " +
				"SUC.activeStatus = 'Y'");
		
		query.setParameter("surveyUserId", userId);
		
		return query.list();
		
	}
	
	public List<Object[]> getLeadersByConstituency()
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId,model.surveyUser.userName,model.constituency.constituencyId" +
				" ,model.constituency.name from SurveyUserConstituency model where model.activeStatus = 'Y'");
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencies(){
		
		return getHibernateTemplate().find(" select distinct model.constituency.constituencyId , model.constituency.name from SurveyUserConstituency model where model.activeStatus = 'Y' order by model.constituency.name asc");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyLeaderByConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,model.surveyUser.userName , model.surveyUser.mobileNo from SurveyUserConstituency model where model.activeStatus = 'Y' and model.constituency.constituencyId = :constituencyId and model.surveyUser.activeStatus= 'Y' ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public int updateUserConstituencies(Long surveyUserId,Long constituencyId)
	{
		Query query = getSession().createQuery("update SurveyUserConstituency model set model.activeStatus = 'N' where model.surveyUser.surveyUserId =:surveyUserId " +
				" and  model.constituency.constituencyId = :constituencyId ");
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("constituencyId", constituencyId);
		int count = query.executeUpdate();
		return count;
	}
	
	public List<Object[]> getSurveyUserConstituencyDetails(Long surveyUserId)
	{
		
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name from SurveyUserConstituency model" +
				" where model.surveyUser.surveyUserId = :surveyUserId and model.activeStatus = 'Y'");
		query.setParameter("surveyUserId", surveyUserId);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyLeadersForAllConstituency()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name,model.surveyUser.surveyUserId,model.surveyUser.userName from SurveyUserConstituency model where model.activeStatus = 'Y' and model.surveyUser.activeStatus= 'Y' ");
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSurveyConstituencyLeadersList(List<Long> constituencyIds){
		
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId,model.surveyUser.userName from SurveyUserConstituency model" +
				" where model.constituency.constituencyId in(:constituencyIds) and model.activeStatus = 'Y' and model.surveyUser.activeStatus = 'Y' order by model.surveyUser.userName asc");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
}

