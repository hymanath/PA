package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;

public class SurveyDetailsInfoDAO extends GenericDaoHibernate<SurveyDetailsInfo, Long> implements ISurveyDetailsInfoDAO{

	public SurveyDetailsInfoDAO() {
		super(SurveyDetailsInfo.class);
	}
	
	public List<Object[]> getDayWisereportDetailsByConstituencyId(Long constituencyId,Date startDate,Date endDate,Long userTypeId)
	{
		Query query = getSession().createQuery("select count(SDI.booth.constituency.constituencyId),SDI.surveyUser.surveyUserId,SDI.surveyUser.userName,DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				" date(SDI.date) <= :endDate group by " +
				"SDI.surveyUser.surveyUserId,DATE(SDI.date)");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate,Date endDate)
	{
		
		Query query = getSession().createQuery("select count(SDI.booth.constituency.constituencyId),BPV.booth.boothId,BPV.booth.partNo " +
				"from SurveyDetailsInfo SDI,BoothPublicationVoter BPV where " +
				"SDI.voter.voterId = BPV.voter.voterId and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				"DATE(SDI.date) >= :startDate and DATE(SDI.date) <= :endDate and SDI.surveyUser.surveyUserId = :surveyUserId group by " +
				"BPV.booth.boothId");
		
		query.setParameter("publicationDateId", 10L);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("surveyUserId", userId);
		
		return query.list();
		
	}

	
	public List<SurveyDetailsInfo> getLatLongForSurveyDetails(Long surveyUserId,Date date)
	{
		Query query = getSession().createQuery(" from SurveyDetailsInfo model where model.surveyUser.surveyUserId =:surveyUserId and model.date = :date");
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("date", date);
		return query.list();
	}
	
	//chenge here
	public List<SurveyDetailsInfo> getSurveyDetilsForAssibnedBooths(Long boothIds)
	{
		Query query = getSession().createQuery("select model from SurveyDetailsInfo model ,SurveyUserBoothAssign model1 where model.surveyUser.surveyUserId = model1.surveyUser.surveyUserId " +
				" and model1.booth.boothId in (:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1");
		query.setParameter("boothIds", boothIds);
		return query.list();
	}
	
	public SurveyDetailsInfo checkUserForVoter(long userId,String uuid ,Long voterId)
	{
		
		Query query = getSession().createQuery("select model from SurveyDetailsInfo model  where model.surveyUser.surveyUserId = :userId and model.uuid=:uuid and model.voter.voterId = :voterId" );
				
		query.setParameter("userId", userId);
		query.setParameter("uuid", uuid);
		query.setParameter("voterId", voterId);
		return (SurveyDetailsInfo) query.uniqueResult();
		 
	}
	public List<Object[]> getVoterDetailsForbooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model1.booth.boothId,model from SurveyDetailsInfo model,BoothPublicationVoter model1" +
				" where model1.booth.boothId in ( :boothIds) and model.voter.voterId = model1.voter.voterId group by model1.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<SurveyDetailsInfo> getVerifiedVotersDetailsBySurveyDetailsInfoId(Long voterId)
	{
		Query query = getSession().createQuery("select SDI from SurveyDetailsInfo SDI where SDI.voter.voterId = :voterId and SDI.verified = 'Y'");
		
		query.setParameter("voterId", voterId);
		
		return query.list();
		
	}
	
	public List<Long> getDataCollectedVoterIdsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select distinct SDI.voter.voterId from SurveyDetailsInfo SDI where SDI.booth.boothId in(:boothIds)");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
	}
}
