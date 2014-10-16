package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.model.SurveyFinalData;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyFinalDataDAO extends GenericDaoHibernate<SurveyFinalData, Long>  implements ISurveyFinalDataDAO
{

	public SurveyFinalDataDAO() {
		super(SurveyFinalData.class);
	}
	
	public List<Object[]> getThirdPartyStatusWithBooths(List<Long> boothIds){
		Query query = getSession().createQuery("select model.booth.boothId,model.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId," +
				" count(model.surveyWmThirdPartyStatusId)" +
				" from SurveyFinalData model" +
				" where model.booth.boothId in(:boothIds)" +
				" and model.surveyWmThirdPartyStatusId is not null" +
				" group by model.booth.boothId, model.surveyWmThirdPartyStatusId");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public int deleteExistingBoothDetails(Long boothId)
	{
		Query query = getSession().createQuery("delete from SurveyFinalData model where model.boothId = :boothId");
		query.setParameter("boothId", boothId);
		int c= query.executeUpdate();
		return c;
		
	}
	
	public List<Object[]> getBoothWiseVoterDetails(Long boothId)
	{
		Query query = getSession().createQuery(" select model.booth.boothId,model.voter.voterId,model.voter.voterIDCardNo,model.voter.name,model.voter.gender,model.voter.age " +
				" , model.voter.houseNo , model.mobileNo,model.isCadre , model.isInfluencingPeople , model.casteStateId , model.casteName ," +
				" model.hamletId , model.hamletName , model.wardId,model.localArea ,model.voter.relativeName,model.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId,model.uuid,bpv.serialNo,model.comment " +
				"from SurveyFinalData model,BoothPublicationVoter bpv where model.boothId = :boothId and bpv.booth.boothId = model.boothId and bpv.voter.voterId = model.voter.voterId ");
		query.setParameter("boothId", boothId);
		return query.list();
	}

	public List<Object[]> getSurveyFinalConstituencyInfo()
	{
		Query query = getSession().createQuery("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name from SurveyFinalData model order by model.booth.constituency.name asc ");		
		return query.list();
		
	}
	
	public int updatedThirdPartyStatus(List<Long> voterIds,Long statusId)
	{
		Query query = getSession().createQuery("update  SurveyFinalData set surveyWmThirdPartyStatusId = :statusId where voterId in(:voterIds)");
		query.setParameterList("voterIds", voterIds);
		query.setParameter("statusId", statusId);
		int c = query.executeUpdate();
		return c;
	}
	
	
	public List<Object[]> getBoothWiseErrorCountForAConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(SFD.booth.boothId),SFD.booth.boothId from SurveyFinalData SFD where " +
				"SFD.booth.constituency.constituencyId =:constituencyId and SFD.surveyWmThirdPartyStatusId = 3 " +
				"group by SFD.booth.boothId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getWMUpdatedStatusOnThirdPartyData(List<Long> userIds,List<Long> boothIds){
		//0 count,1 surveyUserId,2 boothId,3 statusId
		Query query = getSession().createQuery("select count(distinct sfd.voterId),sdi.surveyUser.surveyUserId,sdi.booth.boothId,sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId from  SurveyFinalData sfd,SurveyDetailsInfo sdi where sfd.booth.boothId in(:boothIds)  " +
				" and sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId is not null and sfd.voter.voterId = sdi.voter.voterId and sfd.booth.boothId = sdi.booth.boothId and sdi.surveyUser.surveyUserId in (:userIds) group by " +
				" sdi.surveyUser.surveyUserId,sdi.booth.boothId,sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId");
		query.setParameterList("userIds", userIds);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public int updatedThirdPartyComment(List<Long> voterIds,String comment)
	{
		Query query = getSession().createQuery("update  SurveyFinalData set comment = :comment where voterId in (:voterIds)");
		query.setParameterList("voterIds", voterIds);
		query.setParameter("comment", comment);
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getWmCommentedDetails(Long boothId)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.voter.name,model.casteStateId , model.comment,model.surveyWmThirdPartyStatusId from SurveyFinalData model where model.boothId = :boothId and model.surveyWmThirdPartyStatusId not in (1)");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getThirdPartyBooths(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.booth.boothId,model.booth.partNo from SurveyFinalData model where model.booth.constituency.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getWMUpdatedStatusOnThirdPartyDataByBooth(Long userId,Long boothId){
		//0 count,1 statusId
		Query query = getSession().createQuery("select count(distinct sfd.voterId),sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId from  SurveyFinalData sfd,SurveyDetailsInfo sdi where sfd.booth.boothId =:boothId  " +
				" and sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId is not null and sfd.voter.voterId = sdi.voter.voterId and sfd.booth.boothId = sdi.booth.boothId and sdi.surveyUser.surveyUserId =:userId group by " +
				" sfd.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId");
		query.setParameter("userId", userId);
		query.setParameter("boothId", boothId);
		return query.list();
	}
	public List<Object[]> getBoothDetails(Long boothId){
		//0 count,1 partNo
		Query query = getSession().createQuery("select count(distinct model.voterId),model.booth.partNo from  SurveyFinalData model where model.booth.boothId =:boothId ");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public int updateDefaultCasteMatchStatus(Long voterId,Long casteStateId,Long boothId,Long statusId){
		Query query = getSession().createQuery("update SurveyFinalData model set model.surveyWmThirdPartyStatusId = :statusId where model.voter.voterId = :voterId and model.casteState.casteStateId = :casteStateId  and model.booth.boothId = :boothId and  " +
				" model.voter.voterId is not null and model.casteState.casteStateId is not null ");
		query.setParameter("voterId", voterId);
		query.setParameter("casteStateId", casteStateId);
		query.setParameter("boothId", boothId);
		query.setParameter("statusId", statusId);
		 return query.executeUpdate();
	}
	
	public List<SurveyFinalData> getSurveyFinalDataObj(Long voterId,Long boothId){
		Query query = getSession().createQuery("select model from SurveyFinalData model where model.voter.voterId = :voterId   and model.booth.boothId = :boothId and  " +
				" model.voter.voterId is not null and model.casteState.casteStateId is not null ");
		query.setParameter("voterId", voterId);
		
		query.setParameter("boothId", boothId);
		
		 return query.list();
	}
	
	public Long getQcCollectedMatchedUnMatchedDetails(Long stateId , List<Long> statusIds,Date fromDate , Date toDate)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select count(distinct model.voterId) from SurveyFinalData model where model.surveyWmThirdPartyStatusId in (:statusIds)  ");
		if(fromDate != null && toDate != null)
		{
			queryString.append("  and  date(model.insertedTime) >= :fromDate and date(model.insertedTime) <= :toDate ");
		}
		
		if(stateId.longValue() == 1)
		{
			queryString.append("  and model.booth.constituency.district.districtId > 10 ");
		}
		if(stateId.longValue() == 2)
		{
			queryString.append(" and model.booth.constituency.district.districtId <= 10");
		}
		Query query = getSession().createQuery(queryString.toString());
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		query.setParameterList("statusIds", statusIds);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getQcDataForSelection(Long stateId , List<Long> statusIds,Date fromDate , Date toDate)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.booth.constituency.constituencyId, model.booth.constituency.name , count(distinct model.booth.boothId) , count(model.voter.voterId) from SurveyFinalData model where model.surveyWmThirdPartyStatusId in (:statusIds) ");
		if(fromDate != null && toDate != null)
		{
			queryString.append("  and  date(model.insertedTime) >= :fromDate and date(model.insertedTime) <= :toDate ");
		}
		
		if(stateId.longValue() == 1)
		{
			queryString.append("  and model.booth.constituency.district.districtId > 10 ");
		}
		if(stateId.longValue() == 2)
		{
			queryString.append(" and model.booth.constituency.district.districtId <= 10");
		}
		queryString.append("  group by model.booth.constituency.constituencyId");
		Query query = getSession().createQuery(queryString.toString());
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		query.setParameterList("statusIds", statusIds);
		return query.list();
	}
	
	public List<Object[]> getBoothWiseQcData(Long stateId , Long constituencyId , List<Long> statusIds,Date fromDate,Date toDate)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("select model.booth.boothId, model.booth.partNo , model.surveyUser.surveyUserId , model.surveyUser.userName , model.surveyUser.mobileNo , count(distinct model.voter.voterId) from SurveyFinalData model where model.booth.constituency.constituencyId =:constituencyId and model.surveyWmThirdPartyStatusId in (:statusIds)   ");
		if(fromDate != null && toDate != null)
		{
			queryString.append("  and  date(model.insertedTime) >= :fromDate and date(model.insertedTime) <= :toDate ");
		}
		if(stateId.longValue() == 1)
		{
			queryString.append("  and model.booth.constituency.district.districtId > 10 ");
		}
		if(stateId.longValue() == 2)
		{
			queryString.append(" and model.booth.constituency.district.districtId <= 10");
		}
		queryString.append("  group by model.booth.boothId ");
		Query query = getSession().createQuery(queryString.toString());
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		query.setParameterList("statusIds", statusIds);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterFinalCasteDataFromSurveyFinalData(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.casteState.casteStateId from SurveyFinalData model where " +
				" model.booth.constituency.constituencyId = :constituencyId and model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.casteState is not null and (model.surveyWmThirdPartyStatus is null or model.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId in (1,3,4))");
		
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterFinalCasteDataFromSurveyDetailsInfo(Long constituencyId)
	{
		Query query = getSession().createQuery("Select SDI.voter.voterId,SDI.caste.casteStateId from SurveyFinalData SFD,SurveyDetailsInfo SDI where " +
				" SDI.voter.voterId = SFD.voter.voterId and SFD.booth.constituency.constituencyId = :constituencyId and SFD.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" SDI.surveyUser.surveyUserType.surveyUsertypeId = :thirdPartyUser and SDI.caste is not null and " +
				" SFD.surveyWmThirdPartyStatus.surveyWmThirdPartyStatusId in (2,7)");
		
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",IConstants.VOTER_DATA_PUBLICATION_ID);
		query.setParameter("thirdPartyUser",10l);
		return query.list();
		
	}
}
