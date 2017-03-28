package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
		
		queryString.append("select distinct model.voter.voterId, model.mobileNoStatus, model.matchedStatus,model.casteState.casteStateId,model.hamletStatus,model.hamletId,model.dcWardStatus,model.dcWardId, " +
				"  model.ctpMobileMatched, model.surveyMobileMatched, model.dataMobileMatched, model.ceoMobileMatched  from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId ");
		
		if(surveyUserId != null && surveyUserId.longValue() != 0l)
			queryString.append("and model.surveyUser.surveyUserId = :surveyUserId and model.surveyUser.surveyUserType.surveyUsertypeId = 1");
		//else
		//	queryString.append(" and model.surveyUser.surveyUserType.surveyUsertypeId = 1 ");
			
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("boothId", boothId);
		
		if(surveyUserId != null && surveyUserId.longValue() != 0l)
		 query.setParameter("surveyUserId", surveyUserId);
		return query.list();		
	}
	

	public List<Object[]> getDVSurveyCallDtailsByboothId(Long boothId,Long surveyUserId){
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select distinct model.voter.voterId, model.dvMobileNoStatus, model.dvMatchedStatus,model.dvCasteState.casteStateId,model.dvhamletStatus,model.dvHamletId,model.dvWardStatus,model.dvWardId,model.ctpMobileMatched, model.surveyMobileMatched, model.dataMobileMatched, model.ceoMobileMatched  from SurveyCallStatus model" +
				" where model.booth.boothId =:boothId ");
		
		if(surveyUserId != null && surveyUserId.longValue() != 0l)
			queryString.append("and model.surveyUser.surveyUserId = :surveyUserId and model.surveyUser.surveyUserType.surveyUsertypeId = 4 ");
		//else
		//	queryString.append(" and model.dvSurveyUserId is null ");
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("boothId", boothId);
		
		if(surveyUserId != null && surveyUserId.longValue() != 0l)
		 query.setParameter("surveyUserId", surveyUserId);
		return query.list();		
	}

	public List<Object[]> getDvSurveyCallDtalsByboothId(Long boothId,Long surveyUserId){
		Query query = getSession().createQuery("select distinct model.voter.voterId, model.dvMobileNoStatus, model.dvMatchedStatus,model.dvCasteState.casteStateId,model.dvhamletStatus,model.dvHamletId,model.dvWardStatus, model.dvWardId,  " +
				" model.ctpMobileMatched, model.surveyMobileMatched, model.dataMobileMatched, model.ceoMobileMatched  from SurveyCallStatus model" +
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
	
	
	public Long getWmVerifiedVoters()
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyCallStatus model where model.matchedStatus is not null");
		return (Long) query.uniqueResult();
	}
	
	public Long getWmVerifiedBooths()
	{
		Query query = getSession().createQuery("select count(distinct model.boothId) from SurveyCallStatus model where model.matchedStatus is not null");
		return (Long) query.uniqueResult();
	}
	
	public Long getWmVerifiedConstituencyes()
	{
		Query query = getSession().createQuery("select count(distinct model.boothId.constituency.constituencyId) from SurveyCallStatus model where model.matchedStatus is not null");
		return (Long) query.uniqueResult();
	}
	
	public Long getWmVerifiedRecordsCount(String type)
	{
		Query query = getSession().createQuery("select count(distinct model.voter.voterId) from SurveyCallStatus model where model.matchedStatus = :type");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getVerifierCounts(Long stateId,Date fromDate , Date toDate)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.matchedStatus , count(distinct model.voter.voterId),count(distinct model.booth.constituency.constituencyId) ,count(distinct model.booth.boothId) from SurveyCallStatus model where model.matchedStatus is not null  ");
		if(fromDate != null && toDate != null)
		{
			queryString.append(" and date(model.insertedDate) >= :fromDate and date(model.insertedDate) <= :toDate ");
		}
		
		if(stateId.longValue() == 2)
		{
			queryString.append("  and model.booth.constituency.district.districtId > 10 ");
		}
		if(stateId.longValue() == 1)
		{
			queryString.append(" and model.booth.constituency.district.districtId <= 10");
		}
		queryString.append("  group by  model.matchedStatus ");
		Query query = getSession().createQuery(queryString.toString());
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getVerifiesCountDetails(Long stateId)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select count(distinct model.voter.voterId),count(distinct model.booth.boothId) ,count(distinct model.booth.constituency.constituencyId) from SurveyCallStatus model where model.matchedStatus is not null ");
		if(stateId.longValue() == 2)
		{
			queryString.append("  and model.booth.constituency.district.districtId <= 10 ");
		}
		if(stateId.longValue() == 1)
		{
			queryString.append(" and model.booth.constituency.district.districtId  > 10");
		}
		Query query = getSession().createQuery(queryString.toString());
		
		return query.list();
	}
	public List<Object[]> getConstituencyWiseInternalVerificationSummary(String type)
	{
		Query query = getSession().createQuery("model.booth.constituency.constituencyId");
		
		return query.list();
	}
	
	
	public List<Object[]> getConstituencyWiseVerifiedVoters(Long startDistrictId,Long endDistrictId,Date startDate,Date endDate)
	{
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select count(distinct SCS.voter.voterId)," +
				"SCS.booth.constituency.constituencyId  " +
				"from " +
				"SurveyCallStatus SCS where SCS.matchedStatus is not null and " +
				"SCS.booth.constituency.district.districtId >= :startDistrictId and " +
				"SCS.booth.constituency.district.districtId <= :endDistrictId   ");
		
		if(startDate != null && endDate != null )
			queryString.append("and date(SCS.insertedDate) >= :startDate and date(SCS.insertedDate) <= :endDate ");
		
		queryString.append("group by SCS.booth.constituency.constituencyId");
			
	/*	
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId)," +
				"SCS.booth.constituency.constituencyId  " +
				"from " +
				"SurveyCallStatus SCS where SCS.matchedStatus is not null and " +
				"SCS.booth.constituency.district.districtId >= :startDistrictId and " +
				"SCS.booth.constituency.district.districtId <= :endDistrictId  and " +
				"date(SCS.insertedDate) >= :startDate and date(SCS.insertedDate) <= :endDate " +
				"group by SCS.booth.constituency.constituencyId ");*/
		
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("startDistrictId", startDistrictId);
		query.setParameter("endDistrictId", endDistrictId);
		
		if(startDate != null && endDate != null )
		{		
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
		
	}
	public List<Object[]> getConstituencyWiseVerifiedBooths(Long startDistrictId,Long endDistrictId,Date startDate,Date endDate)
	{
		
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select count(distinct SCS.booth.boothId)," +
				"SCS.booth.constituency.constituencyId  " +
				"from " +
				"SurveyCallStatus SCS where SCS.matchedStatus is not null and " +
				"SCS.booth.constituency.district.districtId >= :startDistrictId and " +
				"SCS.booth.constituency.district.districtId <= :endDistrictId  ");
		
		if(startDate != null && endDate != null )
			queryString.append("and date(SCS.insertedDate) >= :startDate and date(SCS.insertedDate) <= :endDate ");
		
		queryString.append("group by SCS.booth.constituency.constituencyId");
		
	/*	
		Query query = getSession().createQuery("select count(distinct SCS.booth.boothId)," +
				"SCS.booth.constituency.constituencyId  " +
				"from " +
				"SurveyCallStatus SCS where SCS.matchedStatus is not null and " +
				"SCS.booth.constituency.district.districtId >= :startDistrictId and " +
				"SCS.booth.constituency.district.districtId <= :endDistrictId  and " +
				"date(SCS.insertedDate) >= :startDate and date(SCS.insertedDate) <= :endDate " +				
				"group by SCS.booth.constituency.constituencyId ");*/
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("startDistrictId", startDistrictId);
		query.setParameter("endDistrictId", endDistrictId);
		
		if(startDate != null && endDate != null )
		{
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		
		return query.list();
		
	}
	
	public List<Object[]> getBoothWiseVerifiedDetailsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId),SCS.booth.boothId from " +
				"SurveyCallStatus SCS where SCS.booth.constituency.constituencyId = :constituencyId and " +
				"SCS.matchedStatus is not null " +
				"group by SCS.booth.boothId");
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getBoothWiseUsersDetailsByConstituencyId(
			Long constituencyId)	{
		
		Query query = getSession().createSQLQuery(
				"SELECT B.booth_id ,cast(GROUP_CONCAT( distinct U.username SEPARATOR ',') as CHAR) " +
				"FROM survey_call_status SCS , booth B , user U " +
				"where " +
				"SCS.booth_id = B.booth_id and " +
				"SCS.user_id = U.user_id and " +
				" B.constituency_id = "+constituencyId+" " +
				"GROUP BY booth_id");
		
		return query.list();
	}
	public List<Object[]> getVotersDetailsByBoothId(Long boothId)
	{
				Query query = getSession().createQuery("select " +
				"SCS.voter.name,SCS.voter.relativeName , " +
				"SCS.voter.houseNo,SCS.voter.age,SCS.voter.gender," +
				"SCS.voter.voterId  ,SCS.casteState.caste.casteName  " +
				"from SurveyCallStatus SCS " +
				"where " +
				"SCS.booth.boothId = :boothId and SCS.matchedStatus = 'N'");
		
		query.setParameter("boothId", boothId);
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalVotersByConstituencyIds(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.voter.voterId) from SurveyCallStatus model where model.booth.constituency.constituencyId in(:constituencyIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 group by model.booth.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	public List<Object[]> getTotalBoothsByConstituencyIds(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.booth.boothId) from SurveyCallStatus model where model.booth.constituency.constituencyId in(:constituencyIds) and model.surveyUser.surveyUserType.surveyUsertypeId = 1 group by model.booth.constituency.constituencyId");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	public List<Long> getDataCollectorWebMonitorDetailsForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SCS.booth.boothId from SurveyCallStatus " +
				"SCS where SCS.booth.constituency.constituencyId = :constituencyId and SCS.matchedStatus is not null");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Long> getDataVerifierWebMonitorDetailsForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SCS.booth.boothId from SurveyCallStatus " +
				"SCS where SCS.booth.constituency.constituencyId = :constituencyId and SCS.dvMatchedStatus is not null");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Long> getVerifiedBoothIdsByConstituencyId(Long constituencyId)
	{
		Query query = getSession()
				.createQuery(
						"select distinct SCS.booth.boothId from SurveyCallStatus SCS  where SCS.booth.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getTotalVerifiedBoothsinAllConstituencyIds(List<Long> constituencyIds)
	{
			Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.booth.boothId) from SurveyCallStatus model where model.booth.constituency.constituencyId in(:constituencyIds) group by model.booth.constituency.constituencyId");
			query.setParameterList("constituencyIds", constituencyIds);
			return query.list();
	}
	
	public List<Object[]>  getConstituencyWiseSummaryForWmDc()
	{
		Query query = getSession().createQuery(" select model.booth.constituency.constituencyId,count(distinct model.boothId), count(model.voterId) from SurveyCallStatus model where model.matchedStatus is not null group by model.booth.constituency.constituencyId ");
		
		return query.list();
	}
	
	public List<Object[]>  getConstituencyWiseSummaryForWmDV()
	{
		Query query = getSession().createQuery(" select model.booth.constituency.constituencyId,count(distinct model.boothId), count(distinct model.voterId) from SurveyCallStatus model where model.dvMatchedStatus is not null group by model.booth.constituency.constituencyId ");
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseCasteUpdate()
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,model.booth.constituency.name , count(distinct model.voterId) from SurveyCallStatus model where model.matchedStatus = 'N'  group by model.booth.constituency.constituencyId");
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseBoothsCount()
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(distinct model.boothId)  from SurveyCallStatus model where model.matchedStatus is not null group by model.booth.constituency.constituencyId");
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseMobilesUnMatched()
	{
		Query query = getSession().createQuery("select model.booth.constituency.constituencyId,count(model.mobileNoStatus)  from SurveyCallStatus model where model.mobileNoStatus = 'N' group by model.booth.constituency.constituencyId ");
		
		return query.list();
	}
	
	
	public List<Object[]> getDcBoothWiseCasteCollectedDetailsForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId),SCS.booth.boothId,SCS.matchedStatus from SurveyCallStatus SCS " +
				"where SCS.booth.constituency.constituencyId = :constituencyId group by SCS.booth.boothId, SCS.matchedStatus");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getNewlyCollectdCasteDetails(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId),SCS.booth.boothId from SurveyCallStatus SCS " +
				"where SCS.booth.constituency.constituencyId = :constituencyId and SCS.matchedStatus is null and SCS.casteState is not null   group by SCS.booth.boothId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	public List<Object[]> getDcBoothWiseMobileCollectedDetailsForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId),SCS.booth.boothId,SCS.mobileNoStatus from SurveyCallStatus SCS " +
				"where SCS.booth.constituency.constituencyId = :constituencyId group by SCS.booth.boothId, SCS.mobileNoStatus");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getDvBoothWiseCasteCollectewdDetailsForConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(distinct SCS.voter.voterId),SCS.booth.boothId,SCS.dvMatchedStatus from " +
				"SurveyCallStatus SCS where SCS.booth.constituency.constituencyId = :constituencyId group by SCS.booth.boothId,SCS.dvMatchedStatus");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<String> getInvalidMobileDetailsInCTP()
	{
		Query query = getSession().createQuery("select distinct SDI.mobileNumber from SurveyCallStatus SCS, SurveyDetailsInfo SDI where SCS.voterId = SDI.voter.voterId and " +
				" (SCS.mobileNoStatus is not null and SCS.mobileNoStatus ='N' ) ");
		
		return query.list();
	}
}
