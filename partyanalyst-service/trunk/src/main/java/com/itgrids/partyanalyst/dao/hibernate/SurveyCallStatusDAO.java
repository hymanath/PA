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
	
	public List<Object[]> getSurveyCallDtalsByboothId(Long boothId){
		Query query = getSession().createQuery("select distinct model.voter.voterId, model.mobileNoStatus, model.matchedStatus from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId order by model.surveyCallStatusId");
		
		query.setParameter("boothId", boothId);
		return query.list();		
	}
}
