package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.model.SurveyCompletedLocations;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyCompletedLocationsDAO extends GenericDaoHibernate<SurveyCompletedLocations, Long>implements
		ISurveyCompletedLocationsDAO {
	
	public SurveyCompletedLocationsDAO()
	{
		super(SurveyCompletedLocations.class);
		
	}
	
	public List<Object[]> getCompletedBoothsDetailsByConstituencyIds(List<Long> constituencyIds)
	{
		
		Query query = getSession().createQuery("select  count(SCL.locationValue),B.constituency.constituencyId,B.constituency.name" +
				" from SurveyCompletedLocations SCL , Booth B " +
				"where SCL.locationValue = B.boothId and SCL.locationScopeId = :boothScopeId " +
				"and B.constituency.constituencyId in(:constituencyIds) and " +
				"SCL.statusId = :dvCompletedStatus  " +
				"group by  B.constituency.constituencyId");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("boothScopeId", IConstants.BOOTH_SCOPE_ID);
		query.setParameter("dvCompletedStatus", IConstants.DV_COMPLETED_STATUS_ID);
		
		return query.list();
	}
	
	
	public List<Object[]> getBoothsStatusDetailsByConstituencyId(Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count(SCL.locationValue), SCL.surveyCompletedStatus.surveyCompletedStatusId" +
				" from SurveyCompletedLocations SCL  , Booth B " +
				"where " +
				" SCL.locationValue = B.boothId and B.constituency.constituencyId = :constituencyId group by" +
				" SCL.surveyCompletedStatus.surveyCompletedStatusId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getCompletedBoothsCountForPanchayatisByConstituencyId(Long constituencyId)
	{
		
		Query query = getSession().createQuery("select count(SCL.locationValue),B.panchayat.panchayatId,B.panchayat.panchayatName from " +
				"SurveyCompletedLocations SCL  , Booth B where " +
				"B.boothId = SCL.locationValue and B.constituency.constituencyId = :constituencyId and SCL.statusId >= 3  group by  B.panchayat.panchayatId ");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public void deleteSurveyCompletedDetailsByLocationValueAndScope(Long locationValue,Long scopeId)
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocations SCL where " +
				"SCL.locationValue = :locationValue and SCL.locationScopeId = :scopeId ");
		
		query.setParameter("locationValue", locationValue);
		query.setParameter("scopeId", scopeId);
		
		 query.executeUpdate();
	}
	
	
	public void deleteSurveyCompletedDetailsByLocationValueAndScopeForThirdParty(Long locationValue,Long scopeId,List<Long> thirdPartyScopes)
	{
		Query query = getSession().createQuery("delete from SurveyCompletedLocations SCL where " +
				"SCL.locationValue = :locationValue and SCL.locationScopeId = :scopeId and SCL.surveyCompletedStatus.surveyCompletedStatusId in(:thirdPartyScopes)");
		
		query.setParameter("locationValue", locationValue);
		query.setParameter("scopeId", scopeId);
		query.setParameterList("thirdPartyScopes", thirdPartyScopes);
		
		 query.executeUpdate();
	}
	
	public List<Long> getBoothsOfConstituecyByStatus(Long constituencyId,Long statusId,Long scopeId){
		Query query = getSession().createQuery(" select model.locationValue " +
				" from SurveyCompletedLocations model, Booth model1 " +
				" where " +
				" model.locationValue = model1.boothId " +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.surveyCompletedStatus.surveyCompletedStatusId = :statusId" +
				" and model.locationScopeId = :scopeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("statusId", statusId);
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
	public List<Long> getBoothsOfConstituecyByStatusByPanchayat(Long constituencyId,Long scopeId){
		Query query = getSession().createQuery(" select model.locationValue " +
				" from SurveyCompletedLocations model, Booth model1 " +
				" where " +
				" model.locationValue = model1.boothId " +
				" and model1.constituency.constituencyId = :constituencyId" +
				" and model.surveyCompletedStatus.surveyCompletedStatusId >=3" +
				" and model.locationScopeId = :scopeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
	
	public List<Object[]> getSurveyCompletedLocations(){
		
		Query query = getSession().createQuery("select  B.constituency.constituencyId, B.constituency.name, count(SCL.locationValue)  from SurveyCompletedLocations SCL , Booth B " +
				" where SCL.locationValue = B.boothId and SCL.locationScopeId = :boothScopeId and SCL.statusId = 5 group by B.constituency.constituencyId order by B.constituency.name asc ");		
		query.setParameter("boothScopeId", IConstants.BOOTH_SCOPE_ID);		
	
		return query.list();
	}
	
	public List<Long> getCompletedStatusBoothsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select distinct SCL.locationValue from SurveyCompletedLocations SCL where " +
				"SCL.locationValue in(:boothIds) and SCL.locationScopeId = :scopeId ");
		
		query.setParameterList("boothIds", boothIds);
		query.setParameter("scopeId", IConstants.BOOTH_SCOPE_ID);
		
		return query.list();
		
	}
	
	public List<Long> getProsessingBoothsCountForPanchayatisByConstituencyId(Long constituencyId,List<Long> panchayatIds)
	{
		Query query = getSession().createQuery("select distinct SDI.booth.panchayat.panchayatId from SurveyDetailsInfo SDI " +
				"where " +
				"SDI.booth.panchayat.panchayatId not in(:panchayatIds) " +
				"and SDI.booth.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("panchayatIds", panchayatIds);
		
		return query.list();
		
	}
	
	public List<Long> getCompletedBoothsIdsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SCL.locationValue from SurveyCompletedLocations SCL,Booth B " +
				"where " +
				"SCL.locationValue = B.boothId and " +
				"B.constituency.constituencyId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}
	
	public List<Long> getVerificationProcessBoothsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SDI.booth.boothId from SurveyDetailsInfo SDI where " +
				"SDI.booth.constituency.constituencyId = :constituencyId and SDI.surveyUser.surveyUserType.surveyUsertypeId = :verifierTypeId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("verifierTypeId", IConstants.VERIFIER_ROLE_ID);
		
		return query.list();
		
	}
	
	public List<Long> getCompletedBoothsDetailsByStatusIdAndConstituencyId(Long constituencyId,Long statusId)
	{
		Query query = getSession().createQuery("select distinct SCL.locationValue from  SurveyCompletedLocations SCL,Booth B  " +
				"where " +
				"SCL.locationValue = B.boothId and " +
				"B.constituency.constituencyId = :constituencyId and SCL.statusId = :statusId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("statusId", statusId);
		return query.list();
	}
	
	
	public List<Long> getThirdPartyVerificationProcessingBoothsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct SDI.booth.boothId from SurveyDetailsInfo SDI " +
				"where SDI.booth.constituency.constituencyId = :constituencyId and SDI.surveyUser.surveyUserType.surveyUsertypeId = :thirdPartyUserId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("thirdPartyUserId", IConstants.THIRD_PARTY_ROLE_ID);
		
		return query.list();
		
	}
	
	
	//TO GET THE LIST OF BOOTHS WITH STATUS AND CONSTITUENCY
	public List<Long> getBoothsOfTPWithStatus(Long constituencyId,Long statusId){
		Query query = getSession().createQuery("select distinct model.locationValue from " +
				" SurveyCompletedLocations model,Booth model1  " +
				" where " +
				" model.locationValue = model1.boothId and " +
				" model1.constituency.constituencyId = :constituencyId and " +
				" model.statusId = :statusId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("statusId", statusId);
		return query.list();
	}
	
	public List<Long> getAllThirdPartyRelatedBoothByConstituencyId(Long constituencyId,List<Long> thirdPartyStatusIds)
	{
		Query query = getSession().createQuery("select distinct SCL.locationValue from  SurveyCompletedLocations SCL,Booth B  " +
				"where " +
				"SCL.locationValue = B.boothId and " +
				"B.constituency.constituencyId = :constituencyId and SCL.statusId in(:statusIds)");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("statusIds", thirdPartyStatusIds);
		return query.list();
	}
	
	
	//TO GET THE LIST OF BOOTHS & CONSTITUENCIES WITH STATUS
	public List<Object[]> getBoothsAndConstituenciesOfTPWithStatus(Long statusId){
		Query query = getSession().createQuery("select distinct model.locationValue," +
				" model1.constituency.name," +
				" model1.constituency.constituencyId," +
				" model1.constituency.areaType" +
				" from " +
				" SurveyCompletedLocations model,Booth model1  " +
				" where " +
				" model.locationValue = model1.boothId and " +
				" model.statusId = :statusId");
			
		query.setParameter("statusId", statusId);
		return query.list();
	}
	
	
	public List<Object[]> getDistrictWiseCompletedConstituenciesDetails()
	{
		Query query = getSession().createQuery("select count(C.district.districtId),C.district.districtId" +
				" from SurveyCompletedLocations SCL , Constituency C where  " +
				"SCL.locationValue = C.constituencyId and SCL.locationScopeId = :constituencyScopeId " +
				"group by C.district.districtId");
		
		query.setParameter("constituencyScopeId", IConstants.CONSTITUENCY_SCOPE_ID);

		return query.list();
		
	}
	
	public List<Object[]> getCompletedConstituencyDetails()
	{
		
		Query query = getSession().createQuery("select  distinct C.constituencyId,C.name" +
				" from SurveyCompletedLocations SCL , Constituency C " +
				" where SCL.locationValue = C.constituencyId and SCL.locationScopeId = :constituencyScopeId ");
		
		query.setParameter("constituencyScopeId", IConstants.CONSTITUENCY_SCOPE_ID);

		
		return query.list();
	}
	
	
	public List<Object[]> getSurveyConstituenciesStatus()
	{
		Query query = getSession().createQuery("select B.constituency.constituencyId,B.constituency.name,SCL.surveyCompletedStatus.surveyCompletedStatusId,count(B.boothId) from SurveyCompletedLocations SCL,Booth B " +
				" where B.boothId = SCL.locationValue and SCL.locationScopeId = :scopeId group by B.constituency.constituencyId,SCL.surveyCompletedStatus.surveyCompletedStatusId");
		
		query.setParameter("scopeId", IConstants.BOOTH_SCOPE_ID);
		return query.list();
	}
}
