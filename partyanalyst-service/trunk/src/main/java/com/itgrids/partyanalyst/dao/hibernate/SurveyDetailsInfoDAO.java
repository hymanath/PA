package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.model.SurveyDetailsInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyDetailsInfoDAO extends GenericDaoHibernate<SurveyDetailsInfo, Long> implements ISurveyDetailsInfoDAO{

	private static final Logger lOG = Logger.getLogger(SurveyDetailsInfoDAO.class);

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
		
		str.append("select count(SDI.caste.casteStateId)," +
				"SDI.surveyUser.surveyUserId,SDI.surveyUser.userName," +
				"SDI.booth.boothId , SDI.booth.partNo,SDI.booth.totalVoters, DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and  " +
				" date(SDI.date) <= :endDate ");
				
				if(boothIds != null && boothIds.size() >0)
					str.append("and SDI.booth.boothId in(:boothIds)");
				
				
				str.append(" group by " +
				"SDI.surveyUser.surveyUserId,SDI.booth.boothId,DATE(SDI.date) order by  DATE(SDI.date) asc");
				
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
	
	
	public List<Object[]> getDayWisereportDetailsByConstituencyIdAndUserIds(Long constituencyId,Date startDate,Date endDate,Long userTypeId,List<Long> boothIds,List<Long> userIds)
	{
		
		StringBuffer str = new StringBuffer();
		
		str.append("select count(SDI.booth.constituency.constituencyId)," +
				"SDI.surveyUser.surveyUserId,SDI.surveyUser.userName," +
				"SDI.booth.boothId , SDI.booth.partNo,SDI.booth.totalVoters, DATE(SDI.date) from " +
				"SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId  and date(SDI.date) >= :startDate and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and  " +
				" date(SDI.date) <= :endDate and SDI.surveyUser.surveyUserId in (:userIds) ");
				
				if(boothIds != null && boothIds.size() >0)
					str.append("and SDI.booth.boothId in(:boothIds)");
				
				
				str.append(" group by " +
				"SDI.surveyUser.surveyUserId,SDI.booth.boothId,DATE(SDI.date) order by  DATE(SDI.date) asc ");
				
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
		query.setParameterList("userIds", userIds);
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
		Query query = getSession().createQuery("select model from SurveyDetailsInfo model where " +
				" model.booth.boothId in (:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 ");
		query.setParameter("boothIds", boothIds);
		return query.list();
	}
	
	public SurveyDetailsInfo checkUserForVoter(long userId,String uuid ,Long voterId)
	{
		StringBuilder logMsg= new StringBuilder();
		logMsg.append("entered into checkUserForVoter for userId");
		logMsg.append(userId);
		logMsg.append("and voter id is ");
		logMsg.append(voterId);
		logMsg.append("and uuid  is ");
		logMsg.append(uuid);
		
		lOG.info(logMsg );
		
		Query query = getSession().createQuery("select model from SurveyDetailsInfo model  where model.surveyUser.surveyUserId = :userId and model.uuid=:uuid and model.voter.voterId = :voterId" );
				
		query.setParameter("userId", userId);
		query.setParameter("uuid", uuid);
		query.setParameter("voterId", voterId);
		logMsg=null;
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
		queryStr.append(" SUM(CASE WHEN SDI.mobileNumber is not null and SDI.mobileNumber != 'null' and SDI.mobileNumber != '' and length(SDI.mobileNumber) != 0  THEN 1 ELSE 0 END )  ");		// mobileNumber count 		7
			
			queryStr.append(" 	from SurveyDetailsInfo SDI where ");
			
			queryStr.append(" 	SDI.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId							and ");
			
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
		queryStr.append(" SUM(CASE WHEN SDI.mobileNumber is not null and SDI.mobileNumber != 'null' and  SDI.mobileNumber != '' and length(SDI.mobileNumber) != 0THEN 1 ELSE 0 END ),  ");		// mobileNumber count 		7
		queryStr.append(" SUM(CASE WHEN SDI.localArea is not null THEN 1 ELSE 0 END)  ");			// localArea count 		8
		
			queryStr.append(" 	from SurveyDetailsInfo SDI where  ");
			queryStr.append(" 	SDI.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId	and ");
			queryStr.append("	SDI.booth.boothId = :boothId ");
			
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("surveyUsertypeId", surveyUsertypeId);
		return query.list();
	}
	

	
	public List<Object[]> getLatLongForSurveyUsersByConstituency(Long constituencyId,Date date)
	{
		Query query = getSession().createQuery("select model.surveyUser.userName,model.booth.partNo,model.booth.tehsil.tehsilName,  " +
				" model.booth.panchayat.panchayatName,model.booth.villagesCovered,model.booth.location , model.surveyUser.surveyUserType.userType," +
				" model.surveyUser.surveyUserId,model.longitude,model.latitude,model.booth.boothId ,model.surveyUser.mobileNo " +
				" from SurveyDetailsInfo model where" + 
				" model.booth.constituency.constituencyId =:constituencyId and model.surveyUser.activeStatus ='Y' and date(model.date) = :date  and model.longitude != '0.0' and model.latitude != '0.0' " +
				" order by model.date desc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);
		return query.list();
	}
	
	public List<Object[]> getLatLongForSurveyUsersByConstituencyByUser(Long constituencyId,Date date,List<Long> userIds)
	{
		Query query = getSession().createQuery("select model.surveyUser.userName,model.booth.partNo,model.booth.tehsil.tehsilName,  " +
				" model.booth.panchayat.panchayatName,model.booth.villagesCovered,model.booth.location , model.surveyUser.surveyUserType.userType," +
				" model.surveyUser.surveyUserId,model.longitude,model.latitude,model.booth.boothId  ,model.surveyUser.mobileNo " +
				" from SurveyDetailsInfo model where" + 
				" model.booth.constituency.constituencyId =:constituencyId and date(model.date) = :date  and model.longitude != '0.0' and model.latitude != '0.0'  " +
				" and model.surveyUser.surveyUserId in (:userIds) and model.surveyUser.activeStatus ='Y' order by model.date desc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	
	public Long getCasteCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.caste.casteStateId is not null or model.casteName is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getHamletCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.hamlet.hamletId is not null or model.hamletName is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getLocalAreaCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.localArea is not null");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getCadreCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.isCadre != 'N' ");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getInfluencingPeopleCountByBooth(Long userId,Long boothId)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId = :userId and " +
				"model.booth.boothId = :boothId and model.isInfluencingPeople  != 'N' ");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getVoterDetailsByBoothId(Long boothId,List<Long> assignUsers,Date searchDate)
	{
		Query query = getSession().createQuery("select SDI.surveyUser.userName, SDI.voter.voterIDCardNo," +
				" SDI.mobileNumber, SDI.caste.caste.casteName,SDI.casteName, SDI.hamlet.hamletName, SDI.hamletName, SDI.localArea,  SDI.surveyUser.surveyUserId, " +
				" SDI.voter.voterId , SDI.isCadre , SDI.isInfluencingPeople, SDI.voter.name, SDI.voter.houseNo, SDI.voter.relativeName, BPV.serialNo   " +
				" from SurveyDetailsInfo SDI ,BoothPublicationVoter BPV where SDI.booth.boothId = BPV.booth.boothId and SDI.voter.voterId = BPV.voter.voterId " +
				" and SDI.booth.boothId  = :boothId and SDI.surveyUser.surveyUserId in (:assignUsers)   " +
				" and date(SDI.date) = :searchDate "+
				//" and SDI.voter.voterId not in ( select SCS.voter.voterId from SurveyCallStatus SCS where  SDI.surveyUser.surveyUserId =  SCS.surveyUser.surveyUserId )" +
				"  order by SDI.voter.voterId ");
		query.setParameter("boothId", boothId);		
		query.setParameter("searchDate", searchDate);
		query.setParameterList("assignUsers", assignUsers);	
		return query.list();
	}
	
	/*
	public List<Object[]> getVotersDetailsByBoothId(Long boothId,List<Long> assignUsers,Date searchDate,Long casteStateId)
	{
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select SDI, BPV.serialNo from SurveyDetailsInfo SDI ,BoothPublicationVoter BPV ");
		queryStr.append("  where SDI.booth.boothId = BPV.booth.boothId and SDI.voter.voterId = BPV.voter.voterId ");
		queryStr.append(" and SDI.booth.boothId  = :boothId and SDI.surveyUser.surveyUserId in (:assignUsers)  ");
	//	queryStr.append("  and date(SDI.date) = :searchDate  and SDI.voter.voterId not in ( select SCS.voter.voterId from SurveyCallStatus SCS where  SDI.surveyUser.surveyUserId =  SCS.surveyUser.surveyUserId )" );
		
		if(casteStateId != null && casteStateId != 0){
			queryStr.append(" and  SDI.caste.casteStateId = :casteStateId ");
		}
		
		queryStr.append("  order by SDI.voter.voterId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);		
		//query.setParameter("searchDate", searchDate);
		query.setParameterList("assignUsers", assignUsers);	
		
		if(casteStateId != null && casteStateId != 0)
		{
			query.setParameter("casteStateId", casteStateId);
		}
		
		return query.list();
	}
	*/
	public List<Object[]> getVotersDetailsByBoothId(Long boothId,List<Long> assignUsers,Date searchDate)
	{
		Query query = getSession().createQuery("select SDI, BPV.serialNo   " +
				" from SurveyDetailsInfo SDI ,BoothPublicationVoter BPV where SDI.booth.boothId = BPV.booth.boothId and SDI.voter.voterId = BPV.voter.voterId " +
				" and SDI.booth.boothId  = :boothId and SDI.surveyUser.surveyUserId in (:assignUsers)   " +
				//" and date(SDI.date) = :searchDate "+
				//" and SDI.voter.voterId not in ( select SCS.voter.voterId from SurveyCallStatus SCS where  SDI.surveyUser.surveyUserId =  SCS.surveyUser.surveyUserId )" +
				"  order by SDI.voter.voterId ");
		query.setParameter("boothId", boothId);		
		//query.setParameter("searchDate", searchDate);
		query.setParameterList("assignUsers", assignUsers);	
		return query.list();
	}
	
	public List<Long> getSurveyStartedConstituenciesDetails()
	{
		Query query = getSession().createQuery("select distinct SDI.booth.constituency.constituencyId from SurveyDetailsInfo SDI");
		
		return query.list();
		
	}

	public List<Object[]> getSurveyStartedConstituencyInfo()
	{
		Query query = getSession().createQuery("select distinct SDI.booth.constituency.constituencyId,SDI.booth.constituency.name from SurveyDetailsInfo SDI order by SDI.booth.constituency.name asc ");
		
		return query.list();
		
	}
	
	public List<Object[]> getSurveyBooths()
	{
		Query query = getSession()
				.createQuery(
						"select distinct SDI.booth.boothId , SDI.booth.constituency.constituencyId from SurveyDetailsInfo SDI ");
		
		return query.list();
		
	}
	public List<Object[]> getSurveyDetailsByConstituency(Long constituencyId,Long userTypeId,Date date,Date todate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.surveyUser.surveyUserId,model.surveyUser.userName,model.booth.boothId,model.booth.partNo,model.booth.totalVoters,model.surveyUser.mobileNo from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		if(date.equals(todate))
		str.append(" and date(model.date) = :date ");
		else
		str.append(" and date(model.date) >= :date and date(model.date) <= :todate");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		if(!date.equals(todate))
		query.setParameter("todate", todate);
		
		return query.list();
		
	}
	
	
	public List<Object[]> getSurveyDetailsByConstituencyByUsers(Long constituencyId,Long userTypeId,Date date,List<Long> userIds,Date todate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.surveyUser.surveyUserId,model.surveyUser.userName,model.booth.boothId,model.booth.partNo," +
				" model.booth.totalVoters,model.surveyUser.mobileNo from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and model.surveyUser.surveyUserId in (:userIds)");
		if(date.equals(todate))
			str.append(" and date(model.date) = :date");
		else
			str.append(" and date(model.date) >= :date and date(model.date) <= :todate");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		query.setParameterList("userIds", userIds);
		if(!date.equals(todate))
		query.setParameter("todate", todate);
		return query.list();
		
	}
	
	public List<Object[]> getBoothCount(Long constituencyId,Long userTypeId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,model.booth.boothId,model.booth.partNo,count(distinct model.booth.boothId),model.booth.totalVoters from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId" +
				" and model.booth.boothId is not null group by model.surveyUser.surveyUserId,model.booth.boothId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
	}
	
	public List<Object[]> getHamletCount(Long constituencyId,Long userTypeId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,count(distinct model.hamlet.hamletId) from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId" +
				" and model.hamlet is not null group by model.surveyUser.surveyUserId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		
		return query.list();
	}
	
	
	public List<Object[]> getCasteCount(Long constituencyId,Long userTypeId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId,count(distinct model.caste.caste.casteId) from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId" +
				" and model.caste is not null group by model.surveyUser.surveyUserId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
	}
	
	public List<Object[]> getAllUserDetailsByConstituency(Long constituencyId,Date date)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId ,model.surveyUser.userName,model.surveyUser.mobileNo,model.booth.partNo," +
				" model.booth.tehsil.tehsilName, model.booth.panchayat.panchayatName,model.booth.location,model.booth.villagesCovered ,model.surveyUser.surveyUserType.userType,model.booth.boothId,model.insertedTime " +
				" from SurveyDetailsInfo model where" + 
				" model.booth.constituency.constituencyId =:constituencyId and date(model.date) = :date and model.surveyUser.activeStatus = 'Y' order by model.insertedTime asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);
		return query.list();
	}
	
	public List<Object[]> getAllUserDetailsByConstituencyByUsers(Long constituencyId,Date date,List<Long> userIds)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId ,model.surveyUser.userName,model.surveyUser.mobileNo,model.booth.partNo," +
				" model.booth.tehsil.tehsilName, model.booth.panchayat.panchayatName,model.booth.location,model.booth.villagesCovered ,model.surveyUser.surveyUserType.userType,model.booth.boothId,model.insertedTime " +
				" from SurveyDetailsInfo model where" + 
				" model.booth.constituency.constituencyId =:constituencyId and date(model.date) = :date and model.surveyUser.activeStatus = 'Y'" +
				" and model.surveyUser.surveyUserId in (:userIds) order by model.insertedTime asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	
	public List<Object[]> getCasteCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.surveyUser.surveyUserId,model.booth.boothId,count(model.caste.caste.casteId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId in(:userIds) and " +
				"model.booth.boothId in(:boothIds) and (model.caste.casteStateId is not null or model.casteName is not null) and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId ");
		if(date.equals(toDate))
		str.append("and date(model.date) = :date group by model.surveyUser.surveyUserId,model.booth.boothId");
		else
			str.append("and date(model.date) >= :date and date(model.date) <= :toDate group by model.surveyUser.surveyUserId,model.booth.boothId");	
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		if(!date.equals(toDate))
		query.setParameter("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getHamletCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.surveyUser.surveyUserId,model.booth.boothId,count(model.hamlet.hamletId) from SurveyDetailsInfo model where model.surveyUser.surveyUserId in(:userIds) and " +
				"model.booth.boothId in(:boothIds) and (model.hamlet.hamletId is not null or model.hamletName is not null)");
		str.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		if(date.equals(toDate))
		str.append(" and date(model.date) = :date group by model.surveyUser.surveyUserId,model.booth.boothId");
		else
			str.append(" and date(model.date) >= :date and date(model.date) <=:toDate group by model.surveyUser.surveyUserId,model.booth.boothId");	
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		if(!date.equals(toDate))
		query.setParameter("toDate", toDate);	
		return query.list();
	}
	
	public List<Object[]> getMbileNoCountByBooths(List<Long> userIds,List<Long> boothIds,Long userTypeId,Date date,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.surveyUser.surveyUserId,model.booth.boothId,count( model.mobileNumber) from SurveyDetailsInfo model where model.surveyUser.surveyUserId in(:userIds) and " +
				"model.booth.boothId in(:boothIds) and model.mobileNumber is not null and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and model.mobileNumber is not null " +
				" and model.mobileNumber != 'null' and model.mobileNumber != '' and length(model.mobileNumber) != 0 ");
		if(date.equals(toDate))
			str.append("and date(model.date) = :date  group by model.surveyUser.surveyUserId,model.booth.boothId");
		else
			str.append("and date(model.date) >= :date and date(model.date) <= :toDate group by model.surveyUser.surveyUserId,model.booth.boothId");	
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		if(!date.equals(toDate))
			query.setParameter("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getCasteWiseCountInBooth(Long boothId,List<Long> surveyUserIds)
	{
		Query query = getSession().createQuery(" select  SDI.caste.casteStateId,SDI.caste.caste.casteName , count(SDI.caste.casteStateId) from SurveyDetailsInfo SDI where " +
				"  SDI.booth.boothId = :boothId and SDI.surveyUser.surveyUserId in (:surveyUserIds)  group by SDI.caste.casteStateId " +
				" order by SDI.caste.caste.casteName asc ");
		query.setParameter("boothId", boothId);
		query.setParameterList("surveyUserIds", surveyUserIds);
		//query.setParameter("date", date);
		return query.list();
	}
	
	public Long getTotalVotersinBooth(Long boothId)
	{
		Query query = getSession().createQuery(" select count(distinct SDI.voter.voterId) from SurveyDetailsInfo SDI where SDI.booth.boothId = :boothId ");
		query.setParameter("boothId", boothId);
		//query.setParameter("date", date);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> findConstituenciesByDistrictId(Long districtId) {
		Query query = getSession().createQuery("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name from SurveyDetailsInfo model where model.booth.constituency.district.districtId = :districtId");
		query.setParameter("districtId", districtId);
		return query.list();
	}
	public Long getTotalCastecollectedCount()
	{
		Query query = getSession().createQuery("select  count(distinct model.voter.voterId) from SurveyDetailsInfo model where (model.caste.casteStateId is not null or model.casteName is not null) and model.surveyUser.surveyUserType.surveyUsertypeId = 1");
		return (Long) query.uniqueResult();
	}
	
	public Long getTotalCastecollectedCountForToday(Date date)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" (model.caste.casteStateId is not null or model.casteName is not null) " +
				" and date(model.date) = date(:date) and model.surveyUser.surveyUserType.surveyUsertypeId = 1");
		query.setParameter("date",date);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getTotalCastecollectedCountForDates()
	{
		Query query = getSession().createQuery("select model.date,count(distinct model.surveyDetailsInfoId) from SurveyDetailsInfo model where (model.caste.casteStateId is not null or model.casteName is not null) group by date(model.date)");
		return query.list();
	}
	
	public List<Object[]> getTotalCastecollectedCountForTodayForDates(Date date)
	{
		Query query = getSession().createQuery("select model.date,count(distinct model.surveyDetailsInfoId) from SurveyDetailsInfo model where " +
				" (model.caste.casteStateId is not null or model.casteName is not null) " +
				" and date(model.date) = date(:date)");
		query.setParameter("date",date);
		return query.list();
	}
	
	public List<Long> getPresentDayUserWiseSamplesCountByUserIds(List<Long> userIds,Date presentDate)
	{
		Query query = getSession().createQuery("select distinct SDI.surveyUser.surveyUserId from SurveyDetailsInfo SDI where date(SDI.date) = date(:presentDate) and " +
				"SDI.surveyUser.surveyUserId in(:userIds)");
		
		query.setParameter("presentDate", presentDate);
		query.setParameterList("userIds", userIds);
		
		return query.list();
	}
public List<Object[]> getProcecingBoothCountByConstId(Long constituencyId){
		
		Query query = getSession().createQuery("select distinct SDI.booth.boothId,SDI.booth.partNo from SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
   /**
    * 
    * @param panchayatIds
    * @return
    */
    
    
	 public List<?> getHamletCountBasedOnPanchayIds(List<?> panchayatIds)
	 
	 {
		 
		StringBuffer sb =new StringBuffer();
		
		sb.append("select ph.panchayat.panchayatName,ph.panchayat.panchayatId,sinfo.hamlet.hamletName,sinfo.hamlet.hamletId,count(distinct sinfo.surveyDetailsInfoId)  ");
		sb.append("from ");
		sb.append("SurveyDetailsInfo sinfo,PanchayatHamlet ph ");
		
		sb.append("where ");
		
		sb.append("sinfo.hamlet.hamletId=ph.hamlet.hamletId and sinfo.isDelete='N' and  ph.panchayat.panchayatId in (:panchayatIds) and sinfo.surveyUser.surveyUserType.surveyUsertypeId = 1 ");
		sb.append("group by sinfo.hamlet.hamletId ");
		
		sb.append("order by ph.panchayat.panchayatName,sinfo.hamlet.hamletName ");

		
		
		 Query query = getSession().createQuery(sb.toString());
			
			
		 query.setParameterList("panchayatIds",panchayatIds );
		 
		return query.list();
		 
		 
	 }
	
	public List<Object[]> getHamletCountByListOfBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.booth.boothId,count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.boothId in(:boothIds) and (model.hamlet.hamletId is not null or model.hamletName is not null) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 group by model.booth.boothId");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
	}
	
	public List<Object[]> getCasteCountByListOfBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.booth.boothId,count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.boothId in(:boothIds) and (model.caste.casteStateId is not null or model.casteName is not null) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 group by model.booth.boothId");	
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getMobileNoCountByListOfBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.booth.boothId,count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 and model.mobileNumber is not null " +
				" and model.mobileNumber != 'null' and model.mobileNumber != '' and length(model.mobileNumber) != 0  group by model.booth.boothId");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
	}
	
	
	public Long getHamletCountByBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where" +
				" model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 and " +
				"(model.hamlet.hamletId is not null or model.hamletName is not null)");
		
		query.setParameterList("boothIds", boothIds);
	
		return (Long) query.uniqueResult();
	}
	
	public Long getMbileNoCountByBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				"model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 and model.mobileNumber is not null " +
				"and model.mobileNumber != 'null' and model.mobileNumber != '' and length(model.mobileNumber) != 0");
		
		query.setParameterList("boothIds", boothIds);
		
		return (Long) query.uniqueResult();
	}
	
	public Long getCasteCountByBooths(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				"model.booth.boothId in(:boothIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 and (model.caste.casteStateId is not null or model.casteName is not null)");
		
		query.setParameterList("boothIds", boothIds);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getBoothWiseDcAndDvDetails(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId, model.voter.voterId,model.caste.casteStateId , " +
				" model.surveyUser.surveyUserType.surveyUsertypeId,model.booth.boothId,model.surveyUser.userName,model.booth.partNo,date(model.date), model.statusId from SurveyDetailsInfo model where model.booth.boothId in(:boothIds) order by model.booth.boothId asc ");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getBoothWiseDcDetails(Long boothId)
	{
		Query query = getSession().createQuery("select model.surveyUser.surveyUserId, model.voter.voterId,model.caste.casteStateId , " +
				" model.surveyUser.surveyUserType.surveyUsertypeId,model.booth.boothId,model.surveyUser.userName,model.booth.partNo,date(model.date) " +
				" from SurveyDetailsInfo model where model.booth.boothId = :boothId  and model.surveyUser.surveyUserType.surveyUsertypeId = 1 order by model.booth.boothId asc ");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getBoothWiseUser(List<Long> boothIds,Long typeId)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId,model.surveyUser.surveyUserId , model.surveyUser.userName, " +
				"  model.surveyUser.surveyUserType.surveyUsertypeId from SurveyDetailsInfo model where model.booth.boothId in(:boothIds)  and model.surveyUser.surveyUserType.surveyUsertypeId = :typeId order by model.booth.boothId asc ");
		query.setParameterList("boothIds", boothIds);
		query.setParameter("typeId", typeId);
		return query.list();
	}
	

	public List<Object[]> getBooths(Long constituencyId,Long surveyUserType)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId,model.booth.partNo " +
				" from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUserType and model.isDelete = 'N' order by model.booth.boothId asc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("surveyUserType", surveyUserType);
		return query.list();	
	}
	
	public List<Object[]> getDCPerformanceBoothWise(Long surveyUserId,Long userTypeId,Date fromdate,Date toDate )
	{
		
		StringBuilder  queryStr = new StringBuilder();
		queryStr.append(" select SDI.booth.partNo,date(SDI.date) , count(distinct SDI.voter.voterId), ");									// voters count 			0
		queryStr.append(" SUM(CASE WHEN SDI.caste.casteStateId is not null THEN 1 ELSE 0 END), ");	// casteStateId count 		1			
		queryStr.append(" SUM(CASE WHEN SDI.casteName is not null THEN 1 ELSE 0 END), ");			// casteName count 			2
		queryStr.append(" SUM(CASE WHEN SDI.hamlet.hamletId is not null THEN 1 ELSE 0 END ),  ");	// hamletId count 			3
		queryStr.append(" SUM(CASE WHEN SDI.hamletName is not null THEN 1 ELSE 0 END ),  " );		// hamletName count 		4
		queryStr.append(" SUM(CASE WHEN SDI.isCadre ='Y' THEN 1 ELSE 0 END),");						// Cadre count 				5
		queryStr.append(" SUM(CASE WHEN SDI.isInfluencingPeople ='Y' THEN 1 ELSE 0 END),  ");		// InfluencingPeople count	6 
		queryStr.append(" SUM(CASE WHEN SDI.mobileNumber is not null and SDI.mobileNumber != 'null' and  SDI.mobileNumber != '' and length(SDI.mobileNumber) != 0THEN 1 ELSE 0 END ),  ");		// mobileNumber count 		7
		queryStr.append(" SUM(CASE WHEN SDI.localArea is not null THEN 1 ELSE 0 END) , SDI.booth.constituency.name,SDI.booth.boothId ");			// localArea count 		8
		
		queryStr.append(" 	from SurveyDetailsInfo SDI where  ");
		queryStr.append(" 	SDI.surveyUser.surveyUserId = :surveyUserId	and ");
		queryStr.append(" 	SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId	and ");
		queryStr.append("	date(SDI.date) >= :fromdate and date(SDI.date) <= :toDate group by SDI.booth.boothId order by SDI.date");
		
			
		Query query = getSession().createQuery(queryStr.toString());
		
		
		//query.setParameter("constituencyId", constituencyId);
		query.setParameter("surveyUserId", surveyUserId);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("fromdate", fromdate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	public List<Object[]> getDcorDvUsersByConstituency(Long userTypeId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId , model.surveyUser.userName from SurveyDetailsInfo model where" +
				"  model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId   " +
				"   order by model.surveyUser.userName asc");
		//query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		return query.list();
	}
	
	

	public List<String> getCasteCollectedDatesByConstituencyId(Long constituencyId)
	{
		Query query = getSession()
				.createQuery(
						"select distinct cast(concat(day(SDI.date),'-',month(SDI.date),'-',year(SDI.date)),string) " +
						"from SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId = :constituencyId order by date asc");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}

	public List<Object[]> getUsersCompleteReportForSameDate(Date startDate,Date endDate)
 {
		Query query = getSession()
				.createQuery(
						"select SDI.voter.voterId, SDI.surveyUser.surveyUserId, SDI.mobileNumber , SDI.caste ,SDI.hamlet,SDI.surveyUser.userName from SurveyDetailsInfo SDI where "
								+ "date(SDI.date) >= date(:startDate) and date(SDI.date) <= date(:endDate)");

		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		return query.list();

	}
	
	public List<Object[]> getStartAndEndTimesByUserIds(Date startdate,Date endDate )
	{
		
		Query query = getSession()
				.createQuery(
						"select min(date), max(date),SDI.surveyUser.surveyUserId from SurveyDetailsInfo SDI  where date(SDI.date) >= date(:startdate) " +
						"and date(SDI.date) <= date(:endDate) and SDI.surveyUser.surveyUserType.surveyUsertypeId = 1 group by survey_user_id");
		
		query.setParameter("startdate", startdate);
		query.setParameter("endDate", endDate);
		
		return query.list();
		
	}
	
	public List<Object[]> getUsersReportDetailsForBetweenDates(Date startDate,Date endDate)
	{
		Query query = getSession().createQuery("select count(SDI.surveyDetailsInfoId),SDI.surveyUser.surveyUserId,SDI.surveyUser.userName,date(SDI.date) " +
				" from SurveyDetailsInfo SDI " +
				"where " +
				"date(SDI.date) >= date(:startdate)  and date(SDI.date) <= date(:endDate) and SDI.surveyUser.surveyUserType.surveyUsertypeId = 1" +
				" group by SDI.surveyUser.surveyUserId ,date(SDI.date) ");
		
		query.setParameter("startdate", startDate);
		query.setParameter("endDate", endDate);
		
		return query.list();
	}
	
	public List<Long> getUserIdsForConstituency(Long constituencyId,Long userTypeId,Date date,Date todate)
	{

		StringBuilder str = new StringBuilder();
		str.append("select distinct model.surveyUser.surveyUserId from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		if(date.equals(todate))
		str.append(" and date(model.date) = :date ");
		else
		str.append(" and date(model.date) >= :date and date(model.date) <= :todate");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		if(!date.equals(todate))
		query.setParameter("todate", todate);
		
		return query.list();

	}


	public List<Long> getUserIdsForConstituencyByUser(Long constituencyId,Long userTypeId,Date date,List<Long> userIds,Date todate)
	{

		StringBuilder str = new StringBuilder();
		str.append("select distinct model.surveyUser.surveyUserId from SurveyDetailsInfo model where model.booth.constituency.constituencyId = :constituencyId " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId and model.surveyUser.surveyUserId in (:userIds)");
		if(date.equals(todate))
			str.append(" and date(model.date) = :date");
		else
			str.append(" and date(model.date) >= :date and date(model.date) <= :todate");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("date", date);
		query.setParameterList("userIds", userIds);
		if(!date.equals(todate))
		query.setParameter("todate", todate);
		return query.list();

	}
	public List<Object[]> getUserReportForADate(Long userId,Date surveyDate)
	{
		Query query = getSession()
				.createQuery(
						"select SDI.voter.voterId, SDI.surveyUser.surveyUserId, SDI.mobileNumber, SDI.caste ,SDI.hamlet,SDI.surveyUser.userName," +
						"SDI.booth.boothId,SDI.booth.partNo,SDI.booth.constituency.constituencyId ,SDI.booth.constituency.name " +
						" from SurveyDetailsInfo SDI where "
								+ "date(SDI.date) = :surveyDate and  SDI.surveyUser.surveyUserId = :surveyUserId");

		query.setParameter("surveyUserId", userId);
		query.setParameter("surveyDate", surveyDate);

		return query.list();

	}
	
	public List<Long> getVerificationStartedBoothsDetailsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SDI.booth.boothId from SurveyDetailsInfo SDI where " +
				"SDI.booth.constituency.constituencyId = :constituencyId  and SDI.surveyUser.surveyUserType.surveyUsertypeId = :verifierUserTypeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("verifierUserTypeId", IConstants.VERIFIER_ROLE_ID);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getVerifierCollectedDetails(Long surveyUserId,Long boothId)
	{
		Query query = getSession().createQuery("select model.statusId ,count(distinct model.voter.voterId) " +
				" from SurveyDetailsInfo model where model.booth.boothId = :boothId  and model.surveyUser.surveyUserType.surveyUsertypeId = 4  group by model.statusId ");
		query.setParameter("boothId", boothId);
		return query.list();

	}
	
	public List<Object[]> getDataCollectedCountForConstituency(List<Long> constituenycIds)
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.voter.voterId) from SurveyDetailsInfo model where model.surveyUser.surveyUserType.surveyUsertypeId = 1 and model.booth.constituency.constituencyId in(:constituenycIds) and model.isDelete = 'N' group by model.booth.constituency.constituencyId ");
		query.setParameterList("constituenycIds", constituenycIds);
		return query.list();

	}
	
	
	

	public Long getCasteCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId = :publicationDateId  and (model.caste.casteStateId is not null or model.casteName is not null or  model.casteName != '') " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" (model.caste.casteStateId is not null or model.casteName is not null or  model.casteName != '') ");
		queryStr.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	

		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public Long getHamletCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId = :publicationDateId and (model.hamlet.hamletId is not null or model.hamletName is not null or model.hamletName != '') " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" (model.hamlet.hamletId is not null or model.hamletName is not null or model.hamletName != '') ");
		queryStr.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	

		
		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public Long getLocalAreaCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId = :publicationDateId and model.localArea is not null " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" model.localArea is not null ");
		
		queryStr.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	

		
		
		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public Long getCadreCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where" +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId =:publicationDateId and model.isCadre != 'N' " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" model.isCadre != 'N' ");
		
		queryStr.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	

		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public Long getInfluencingPeopleCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId =:publicationDateId and model.isInfluencingPeople  != 'N' " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" model.isInfluencingPeople  != 'N' ");
		
		queryStr.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	
		
		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public Long getTotalSurveyVotersByconstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*
		 * Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyDetailsInfo model where " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId =:publicationDateId " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct model.voter.voterId) from SurveyDetailsInfo model where  ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	
		
		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	
	public Long getMobileCountByBoothByConstituency(Long locationId,Long surveyUsertypeId, String searchType)
	{
		/*	
		 * Query query = getSession().createQuery("select count(distinct  model.mobileNumber) from SurveyDetailsInfo model where " +
				" model.booth.constituency.constituencyId = :constituencyId and  model.booth.publicationDate.publicationDateId =:publicationDateId and model.mobileNumber is not null and  " +
				" model.mobileNumber !='' " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId");	
		 */
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select count(distinct  model.mobileNumber) from SurveyDetailsInfo model where   ");
		
		if(searchType.equalsIgnoreCase("constituencyWise")){
			queryStr.append(" model.booth.constituency.constituencyId = :locationId and  model.booth.publicationDate.publicationDateId = 11  and ");
		}
		else if(searchType.equalsIgnoreCase("boothWise")){
			queryStr.append(" model.booth.boothId = :locationId and ");
		}
		
		queryStr.append(" model.mobileNumber is not null and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());	
		
		
		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("locationId", locationId);
		//query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getCasteTaggedVotersForAllConstituencies(Long surveyUsertypeId)
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.voter.voterId) from SurveyDetailsInfo model where  " +
				"model.booth.publicationDate.publicationDateId = :publicationDateId  and (model.caste.casteStateId is not null or model.casteName is not null or  model.casteName != '') " +
				" and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId and model.isDelete = 'N' group by model.booth.constituency.constituencyId");	

		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
        query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	public List<Object[]> getMobileTaggedVotersForAllConstituencies(Long surveyUsertypeId)

	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.mobileNumber) from SurveyDetailsInfo model where " +
				"model.booth.publicationDate.publicationDateId =:publicationDateId and model.mobileNumber is not null and  " +
				" model.mobileNumber !='' " +
				"and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId and model.isDelete = 'N' group by model.booth.constituency.constituencyId");	

		query.setParameter("surveyUsertypeId", surveyUsertypeId);	
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	
	public List<Object[]> getAllverificationDetails(Long surveyUserId,Long boothId)
	{
		Query query = getSession().createQuery("select model.statusId ,count(distinct model.voter.voterId) " +
				" from SurveyDetailsInfo model where model.booth.boothId = :boothId  and model.surveyUser.surveyUserType.surveyUsertypeId = 4  group by model.statusId ");
		query.setParameter("boothId", boothId);
		return query.list();

	}
	
	public List<Long> getUsersByDate(Long constituencyId,Date date)
	{
		
		Query query = getSession().createQuery("select distinct SDI.surveyUser.surveyUserId from SurveyDetailsInfo SDI where  SDI.booth.constituency.constituencyId = :constituencyId and DATE(SDI.date) = :date  and SDI.surveyUser.activeStatus = 'Y' ");		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("date", date);		
		return query.list();
		
	}
	
	public List<SurveyDetailsInfo> getVerifierSurveyDetails(Long boothId)
	{
		Query query = getSession().createQuery("select model from SurveyDetailsInfo model where " +
				" model.booth.boothId in (:boothId) and model.surveyUser.surveyUserType.surveyUsertypeId = 4 ");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getStartedBoothsDetailsByConstituencyIds(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select count(distinct SDI.booth.boothId), SDI.booth.constituency.constituencyId, SDI.booth.constituency.name from " +
				" SurveyDetailsInfo SDI where SDI.booth.constituency.constituencyId  in(:constituencyIds) and " +
				"SDI.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId group by SDI.booth.constituency.constituencyId");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("surveyUsertypeId", IConstants.DATA_COLLECTOR_ROLE_ID);
		return query.list();
		
	}
	
	public List<Long> getBoothsInProcessByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SDI.booth.boothId from SurveyDetailsInfo SDI where " +
				"SDI.booth.constituency.constituencyId = :constituencyId and SDI.surveyUser.surveyUserType.surveyUsertypeId = :userTypeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userTypeId", IConstants.DATA_COLLECTOR_ROLE_ID);
		
		return query.list();
		
	}
	
	
	
}
