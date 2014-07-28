package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.model.SurveyCallStatus;

public class SurveyCallStatusDAO extends GenericDaoHibernate<SurveyCallStatus,Long> implements ISurveyCallStatusDAO{

	public SurveyCallStatusDAO() {
		super(SurveyCallStatus.class);
	}

	
	public Long getSurveyCallDtalsByVoterId(Long voterId){
		
		Query query = getSession().createQuery(" select model.surveyCallStatusId from SurveyCallStatus model where model.voter.voterId = :voterId");
		query.setParameter("voterId", voterId);		
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getStatusListForUser(List<Long> userIds,List<Long> boothIds,Long userTypeId)
	{
		
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,model.booth.boothId,model.mobileNoStatus,model.matchedStatus from SurveyCallStatus model" +
				" where model.surveyUser.surveyUserId in(:userIds) and model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
		
	}
	
	public List<Object[]> getSurveyCallDtalsByboothId(Long boothId,Long surveyUserId){
		Query query = getSession().createQuery("select distinct model.voter.voterId, model.mobileNoStatus, model.matchedStatus,model.casteState.casteStateId from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId and model.surveyUser.surveyUserId = :surveyUserId   order by model.surveyCallStatusId");
		
		query.setParameter("boothId", boothId);
		query.setParameter("surveyUserId", surveyUserId);
		return query.list();		
	}
	
	public List<Object[]> getBoothWiseWmCasteUpdationDetails(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.voterId , model.casteStateId,model.matchedStatus,model.boothId from SurveyCallStatus model where model.boothId in(:boothIds)");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getBoothsByConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId,model.booth.partNo from " +
				"SurveyCallStatus model where model.booth.constituency.constituencyId = :constituencyId order by model.booth.boothId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();	
	}
}
