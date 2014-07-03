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
	
	public List<Object[]> getDayWisereportDetailsByConstituencyId(Long constituencyId,Date startDate,Date endDate)
	{
		Query query = getSession().createQuery("select count(*),SDI.surveyUser.surveyUserId,SDI.surveyUser.userName,DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				" date(SDI.date) <= :endDate group by " +
				"SDI.surveyUser.surveyUserId,DATE(SDI.date)");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate,Date endDate)
	{
		
		Query query = getSession().createQuery("select count(SDI.surveyDetailsInfoId),BPV.booth.boothId,BPV.booth.partNo " +
				"from SurveyDetailsInfo SDI,BoothPublicationVoter BPV where " +
				"SDI.voter.voterId = BPV.voter.voterId and " +
				"BPV.publicationDate.publicationDateId = :publicationDateId and " +
				"DATE(SDI.date) >= :startDate and DATE(SDI.date) <= :endDate group by " +
				"BPV.booth.boothId");
		
		query.setParameter("publicationDateId", 10L);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		return query.list();
		
	}

	
	public List<SurveyDetailsInfo> getLatLongForSurveyDetails(Long surveyUserId,Date date)
	{
		Query query = getSession().createQuery(" from SurveyDetailsInfo model where model.surveyUser.surveyUserId =:surveyUserId and model.date = :date");
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("date", date);
		return query.list();
	}
	
	public List<SurveyDetailsInfo> getSurveyDetilsForAssibnedBooths(Long boothIds)
	{
		Query query = getSession().createQuery("from SurveyDetailsInfo model ,SurveyUserBoothAssign model1 where model.surveyUser.surveyUserId = model1.surveyUser.surveyUserId " +
				" and model1.booth.boothId in (:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1");
		query.setParameter("boothIds", boothIds);
		return query.list();
	}
	public List<Object[]> getVoterDetailsForbooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model1.booth.boothId,model from SurveyDetailsInfo model,BoothPublicationVoter model1" +
				" where model1.booth.boothId in ( :boothIds) and model.voter.voterId = model1.voter.voterId group by model1.booth.boothId");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
}
