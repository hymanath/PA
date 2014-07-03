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

}
