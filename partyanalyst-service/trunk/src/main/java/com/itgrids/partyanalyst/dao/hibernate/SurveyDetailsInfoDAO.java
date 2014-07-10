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
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getDayWisereportDetailsByConstituencyIdAndUserTypeId(Long constituencyId,Date startDate,Date endDate,Long userTypeId,List<Long> boothIds)
	{
		
		StringBuffer str = new StringBuffer();
		
		str.append("select count(SDI.booth.constituency.constituencyId)," +
				"SDI.surveyUser.surveyUserId,SDI.surveyUser.userName," +
				"SDI.booth.boothId , SDI.booth.partNo,SDI.booth.totalVoters, DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and  " +
				" date(SDI.date) <= :endDate ");
				
				if(boothIds != null && boothIds.size() >0)
					str.append("and SDI.booth.boothId in(:boothIds)");
				
				
				str.append(" group by " +
				"SDI.surveyUser.surveyUserId,SDI.booth.boothId,DATE(SDI.date) ");
				
				Query query = getSession().createQuery(str.toString());
		
	/*	Query query = getSession().createQuery("select count(SDI.booth.constituency.constituencyId)," +
				"SDI.surveyUser.surveyUserId,SDI.surveyUser.userName," +
				"SDI.booth.boothId , SDI.booth.partNo,SDI.booth.totalVoters, DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and  " +
				" date(SDI.date) <= :endDate group by " +
				"SDI.surveyUser.surveyUserId,SDI.booth.boothId,DATE(SDI.date) ");*/
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		
		if(boothIds != null && boothIds.size() >0)
			query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
	}

	public List<Object[]> getBoothWiseUserSamplesDetailsByDates(Long userId,Date startDate)
	{
		
		Query query = getSession().createQuery("select count(SDI.booth.constituency.constituencyId),BPV.booth.boothId,BPV.booth.partNo " +
				"from SurveyDetailsInfo SDI,BoothPublicationVoter BPV where " +
				"SDI.voter.voterId = BPV.voter.voterId and " +
				"BPV.booth.publicationDate.publicationDateId = :publicationDateId and " +
				"DATE(SDI.date) = :startDate  and SDI.surveyUser.surveyUserId = :surveyUserId group by " +
				"BPV.booth.boothId");
		
		query.setParameter("publicationDateId", 10L);
		query.setParameter("startDate", startDate);
		query.setParameter("surveyUserId", userId);
		
		return query.list();
		
	}

	
	public List<SurveyDetailsInfo> getLatLongForSurveyDetails(Long surveyUserId,Date date)
	{
		Query query = getSession().createQuery(" from SurveyDetailsInfo model where model.surveyUser.surveyUserId =:surveyUserId and date(model.date) = :date " +
				" and model.longitude is not null and  model.latitude is not null");
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
	
	public List<Object[]> getsurveyDetailsInfoByConstituencyId(Long constituencyId,Long surveyUsertypeId){
		
		StringBuilder  queryStr = new StringBuilder();
		queryStr.append(" select count(distinct SDI.voter.voterId), ");									// voters count 			0
		queryStr.append(" SUM(CASE WHEN SDI.caste.casteStateId is not null THEN 1 ELSE 0 END), ");	// casteStateId count 		1			
		queryStr.append(" SUM(CASE WHEN SDI.casteName is not null THEN 1 ELSE 0 END), ");			// casteName count 			2
		queryStr.append(" SUM(CASE WHEN SDI.hamlet.hamletId is not null THEN 1 ELSE 0 END ),  ");	// hamletId count 			3
		queryStr.append(" SUM(CASE WHEN SDI.hamletName is not null THEN 1 ELSE 0 END ),  " );		// hamletName count 		4
		queryStr.append(" SUM(CASE WHEN SDI.isCadre ='Y' THEN 1 ELSE 0 END),");						// Cadre count 				5
		queryStr.append(" SUM(CASE WHEN SDI.isInfluencingPeople ='Y' THEN 1 ELSE 0 END),  ");		// InfluencingPeople count	6 
		queryStr.append(" SUM(CASE WHEN SDI.mobileNumber is not null THEN 1 ELSE 0 END )  ");		// mobileNumber count 		7
			
			queryStr.append(" 	from SurveyDetailsInfo SDI ,SurveyUser SU , SurveyUserType SUT, Booth B ");
			queryStr.append(" 	where  SDI.surveyUser.surveyUserId = SU.surveyUserId 				and ");
			queryStr.append(" 	SU.surveyUserType.surveyUsertypeId = SUT.surveyUsertypeId 			and ");
			queryStr.append(" 	SUT.surveyUsertypeId = :surveyUsertypeId 							and ");
			queryStr.append("   SDI.booth.boothId = B.boothId 										and ");
			queryStr.append("   B.publicationDate.publicationDateId = 10 							and ");
			queryStr.append("	SDI.booth.constituency.constituencyId = :constituencyId ");
			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("surveyUsertypeId", surveyUsertypeId);
		return query.list();
	
	}
	
	public List<Object[]> getBoothDetailsByConstituencyId(Long constituencyId){
		StringBuilder  queryStr = new StringBuilder();
		queryStr.append("select distinct SDI.booth.boothId, SDI.booth.partNo from SurveyDetailsInfo SDI, Booth B where SDI.booth.boothId = B.boothId and SDI.booth.constituency.constituencyId = :constituencyId ");
			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId", constituencyId);

		return query.list();
		
	}
	
public List<Object[]> getsurveyDetailsInfoByboothId(Long boothId,Long surveyUsertypeId){
		
		StringBuilder  queryStr = new StringBuilder();
		queryStr.append(" select count(distinct SDI.voter.voterId), ");									// voters count 			0
		queryStr.append(" SUM(CASE WHEN SDI.caste.casteStateId is not null THEN 1 ELSE 0 END), ");	// casteStateId count 		1			
		queryStr.append(" SUM(CASE WHEN SDI.casteName is not null THEN 1 ELSE 0 END), ");			// casteName count 			2
		queryStr.append(" SUM(CASE WHEN SDI.hamlet.hamletId is not null THEN 1 ELSE 0 END ),  ");	// hamletId count 			3
		queryStr.append(" SUM(CASE WHEN SDI.hamletName is not null THEN 1 ELSE 0 END ),  " );		// hamletName count 		4
		queryStr.append(" SUM(CASE WHEN SDI.isCadre ='Y' THEN 1 ELSE 0 END),");						// Cadre count 				5
		queryStr.append(" SUM(CASE WHEN SDI.isInfluencingPeople ='Y' THEN 1 ELSE 0 END),  ");		// InfluencingPeople count	6 
		queryStr.append(" SUM(CASE WHEN SDI.mobileNumber is not null THEN 1 ELSE 0 END ),  ");		// mobileNumber count 		7
		queryStr.append(" SUM(CASE WHEN SDI.localArea is not null THEN 1 ELSE 0 END)  ");			// localArea count 		8
		
			queryStr.append(" 	from SurveyDetailsInfo SDI ,SurveyUser SU , SurveyUserType SUT, Booth B ");
			queryStr.append(" 	where  SDI.surveyUser.surveyUserId = SU.surveyUserId 				and ");
			queryStr.append(" 	SU.surveyUserType.surveyUsertypeId = SUT.surveyUsertypeId 			and ");
			queryStr.append(" 	SUT.surveyUsertypeId = :surveyUsertypeId 							and ");
			queryStr.append("   SDI.booth.boothId = B.boothId 										and ");
			queryStr.append("   B.publicationDate.publicationDateId = 10 							and ");
			queryStr.append("	B.boothId = :boothId ");
			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("surveyUsertypeId", surveyUsertypeId);
		return query.list();
	}
	

	
	public List<Object[]> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date)
	{
		Query query = getSession().createQuery("select model.surveyUser.userName,model.booth.partNo,model.booth.tehsil.tehsilName,  " +
				" model.booth.panchayat.panchayatName,model.booth.villagesCovered,model.booth.location , model.surveyUser.surveyUserType.userType," +
				" model.surveyUser.surveyUserId,model.longitude,model.latitude,model.booth.boothId  " +
				" from SurveyDetailsInfo model where" + 
				" model.booth.constituency.constituencyId =:constituencyId and date(model.date) = :date order by model.date desc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);
		return query.list();
	}
	
	public Long getCasteCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.caste.casteStateId is not null or model.casteName is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getHamletCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.hamlet.hamletId is not null or model.hamletName is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getLocalAreaCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.localArea is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getCadreCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.isCadre is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getInfluencingPeopleCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.isInfluencingPeople is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getVoterDetailsByBoothId(Long boothId,List<Long> assignUsers)
	{
		Query query = getSession().createQuery("select SDI.surveyUser.userName, SDI.voter.voterIDCardNo," +
				" SDI.mobileNumber, SDI.caste.caste.casteName,SDI.casteName, SDI.hamlet.hamletName, SDI.hamletName, SDI.localArea,  SDI.surveyUser.surveyUserId, " +
				" SDI.voter.voterId , SDI.isCadre , SDI.isInfluencingPeople  " +
				"   from SurveyDetailsInfo SDI where SDI.booth.boothId  = :boothId and SDI.surveyUser.surveyUserId in (:assignUsers) and SDI.mobileNumber != null  ");
		query.setParameter("boothId", boothId);		
		query.setParameterList("assignUsers", assignUsers);	
		return query.list();
	}
	
	
	public List<Long> getSurveyStartedConstituenciesDetails()
	{
		Query query = getSession().createQuery("select distinct SDI.booth.constituency.constituencyId from SurveyDetailsInfo SDI");
		
		return query.list();
		
	}
}
