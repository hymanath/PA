package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.model.SurveyCallStatus;
import com.itgrids.partyanalyst.utils.IConstants;

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
				" where model.surveyUser.surveyUserId in(:userIds) and model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId group by model.voterId ");
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
		
	}
	
	public List<Object[]> getSurveyCallDtalsByboothId(Long boothId,Long surveyUserId){
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select distinct model.voter.voterId, model.mobileNoStatus, model.matchedStatus,model.casteState.casteStateId,model.hamletStatus,model.hamletId from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId ");
		
		if(surveyUserId != 0)
			queryString.append("and model.surveyUser.surveyUserId = :surveyUserId ");
		else
			queryString.append("and model.surveyUser.surveyUserType.surveyUsertypeId = 1 ");
			
	/*		
		Query query = getSession().createQuery("select distinct model.voter.voterId, model.mobileNoStatus, model.matchedStatus,model.casteState.casteStateId,model.hamletStatus,model.hamletId from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId and model.surveyUser.surveyUserId = :surveyUserId   order by model.surveyCallStatusId");*/
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("boothId", boothId);
		
		if(surveyUserId != 0)
		 query.setParameter("surveyUserId", surveyUserId);
		return query.list();		
	}
	
	public List<Object[]> getDvSurveyCallDtalsByboothId(Long boothId,Long surveyUserId){
		Query query = getSession().createQuery("select distinct model.voter.voterId, model.dvMobileNoStatus, model.dvMatchedStatus,model.dvCasteState.casteStateId,model.dvhamletStatus,model.dvHamletId from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId and model.dvSurveyUser.surveyUserId = :surveyUserId   order by model.surveyCallStatusId");
		
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
	
	public List<Object[]> getBoothWiseDvWmCasteUpdationDetails(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.voterId , model.dvCasteStateId,model.dvMatchedStatus,model.boothId from SurveyCallStatus model where model.boothId in(:boothIds)");
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
	
	public List<Object[]> getCasteVotersForAllConstituencies()
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.voter.voterId),model.matchedStatus from " +
				"SurveyCallStatus model where model.booth.publicationDate.publicationDateId =:publicationDateId group by model.booth.constituency.constituencyId,model.matchedStatus");
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();	
	}
	public List<Object[]> getMobileVotersForAllConstituencies()
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.voter.voterId),model.mobileNoStatus from " +
				"SurveyCallStatus model where model.booth.publicationDate.publicationDateId =:publicationDateId group by model.booth.constituency.constituencyId,model.mobileNoStatus");
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();	
	}
	
	public SurveyCallStatus getSurveyCallStatusByVoterId(Long voterId){
		
		Query query = getSession().createQuery(" select model from SurveyCallStatus model where model.voter.voterId = :voterId");
		query.setParameter("voterId", voterId);		
		return (SurveyCallStatus) query.uniqueResult();
	}
	
	public List<Object[]> getCasteStatusForBooth(List<Long> boothIds){
		Query query = getSession().createQuery(" select model.booth.boothId,model.matchedStatus,count(model.matchedStatus) from SurveyCallStatus model" +
				" where model.booth.boothId in(:boothIds) and model.matchedStatus is not null group by model.booth.boothId,model.matchedStatus ");
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
	}
	public List<Object[]> getStatusListForVerifier(List<Long> userIds,List<Long> boothIds,Long userTypeId)
	{
		
		Query query = getSession().createQuery("select model.dvSurveyUser.surveyUserId,model.booth.boothId,model.dvMobileNoStatus,model.dvMatchedStatus from SurveyCallStatus model" +
				" where model.dvSurveyUser.surveyUserId in(:userIds) and model.booth.boothId in(:boothIds) and model.dvSurveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
		
	}
	
	public List<Object[]> getWmDvMappedUnMappedDetailsBoothWise(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.booth.boothId,model.dvMatchedStatus,count(model.surveyCallStatusId) from SurveyCallStatus model where model.booth.constituency.constituencyId = :constituencyId  and model.dvMatchedStatus is not null  group by model.booth.boothId , model.dvMatchedStatus  ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getBoothWiseErrorCountForConstituencyByUsertypeId(Long constituencyId,Long userTypeId)
	{
		Query query = getSession().createQuery("select count(distinct SDI.voter.voterId),SDI.booth.boothId,SDI.booth.partNo from SurveyDetailsInfo SDI,SurveyCallStatus SCS where " +
				"SDI.surveyUser.surveyUserId = SCS.surveyUser.surveyUserId and  " +
				"SDI.voter.voterId = SCS.voter.voterId and " +
				"SDI.booth.boothId = SCS.booth.boothId and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :surveyUserTypeId and " +
				"SDI.booth.constituency.constituencyId = :constituencyId and SCS.matchedStatus = 'N' group by " +
				"SDI.booth.boothId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("surveyUserTypeId", userTypeId);
		
		return query.list();
	}
	
	public List<Long> getSurveyCallDetailsByVoterId(Long voterId){
		
		Query query = getSession().createQuery(" select model.surveyCallStatusId from SurveyCallStatus model where model.voter.voterId = :voterId");
		query.setParameter("voterId", voterId);		
		return query.list();
	}
}
