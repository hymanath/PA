package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueDAO;
import com.itgrids.partyanalyst.model.CadreRegIssue;

public class CadreRegIssueDAO extends GenericDaoHibernate<CadreRegIssue, Long> implements ICadreRegIssueDAO {

	public CadreRegIssueDAO() {
		super(CadreRegIssue.class);
		
	}
	
	public List<Object[]> getTabUsersDetailsByVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.cadreSurveyUser.cadreSurveyUserId," +
					" model1.cadreSurveyUser.userName," +
					" model.tdpCadre.tabUserInfo.tabUserInfoId," +
					" model.tdpCadre.tabUserInfo.name," +
					" model.tdpCadre.tabUserInfo.mobileNo," +
					" min(model.tdpCadre.surveyTime)," +
					" max(model.tdpCadre.surveyTime)," +
					" count(model.tdpCadre.tdpCadreId)" +
					" from TdpCadreEnrollmentYear model,FieldVendorTabUser model1,FieldVendorLocation model2,Constituency C" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.fieldVendor.fieldVendorId = model2.fieldVendor.fieldVendorId" +
					" and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model2.constituency.constituencyId = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
				
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" group by model.tdpCadre.insertedBy.cadreSurveyUserId,model.tdpCadre.tabUserInfoId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getLastHourCounts(Long vendorId,Date lastOneHourTime,Date today,String locationType,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.cadreSurveyUser.cadreSurveyUserId," +
					" model.tdpCadre.tabUserInfo.tabUserInfoId," +
					" count(model.tdpCadre.tdpCadreId)" +
					" from TdpCadreEnrollmentYear model,FieldVendorTabUser model1,FieldVendorLocation model2,Constituency C" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.fieldVendor.fieldVendorId = model2.fieldVendor.fieldVendorId" +
					" and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model2.constituency.constituencyId = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
				
		if(lastOneHourTime!= null && today!=null){
			sb.append(" and model.tdpCadre.surveyTime between :lastOneHourTime and :today");	 
		}
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" group by model.tdpCadre.insertedBy.cadreSurveyUserId,model.tdpCadre.tabUserInfoId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(lastOneHourTime!= null && today!=null){
			query.setParameter("lastOneHourTime", lastOneHourTime);
			query.setParameter("today", today);
		}
		
		return query.list();
		/*StringBuilder sb = new StringBuilder();
		sb.append("select FVTU.cadre_survey_user_id," +
					" TC.tab_user_info_id," +
					" count(TC.tdp_cadre_id)" +
					" from tdp_cadre_enrollment_year TCEY,tdp_cadre TC,field_vendor_tab_user FVTU,field_vendor_location FVL,constituency C" +
					" where TCEY.tdp_cadre_id = TC.tdp_cadre_id" +
					" and TC.created_by = FVTU.cadre_survey_user_id" +
					" and FVTU.field_vendor_id = FVL.field_vendor_id" +
					" and FVL.field_vendor_id = :vendorId" +
					" and FVL.constituency_id = C.constituency_id");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state_id = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district_id = :locationVal");
		else
			sb.append(" and C.constituency_id = :locationVal");
				
		if(currentDate != null)
			sb.append(" and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(TC.survey_time)))/60 < 61");
		
		sb.append(" and TC.enrollment_year = 2014" +
					" and TCEY.enrollment_year_id = 4" +
					" and TCEY.is_deleted = 'N'" +
					" and TC.is_deleted = 'N'" +
					" and FVTU.is_deleted = 'N'" +
					" and FVL.is_deleted = 'N'" +
					" group by TC.created_by,TC.tab_user_info_id");
		
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(currentDate != null){
			query.setParameter("currentTime", currentDate);
		}
		
		return query.list();*/
	}
	
	public List<Object[]> getcadreRegIssuesCounts(Long vendorId,String locationType,Long locationVal,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.cadreSurveyUserId," +
					" model.tabUserInfoId," +
					" model.cadreRegIssueStatusId," +
					" count(model.cadreRegIssueId)" +
					" from CadreRegIssue model,FieldVendorTabUser model1,Constituency C" +
					" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model.locationScopeId = 4" +
					" and model.locationValue = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
		if(startDate != null && endDate != null)
			sb.append(" and date(model.insertedTime) between :startDate and :endDate");
				
		sb.append(" and model1.isDeleted = 'N'" +
					" group by model.cadreSurveyUserId,model.tabUserInfoId,model.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	
	public Long getTotalDataCollectorsCountsVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
					" from TdpCadreEnrollmentYear model,FieldVendorTabUser model1,FieldVendorLocation model2,Constituency C" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.fieldVendor.fieldVendorId = model2.fieldVendor.fieldVendorId" +
					" and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model2.constituency.constituencyId = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
				
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" group by model1.fieldVendor.fieldVendorId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return (Long) query.uniqueResult();
	}
	
	public Long getActiveDataCollectorsCountsVendorAndLocation(Long vendorId,Date lastOneHourTime,Date today,String locationType,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
					" from TdpCadreEnrollmentYear model,FieldVendorTabUser model1,FieldVendorLocation model2,Constituency C" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.fieldVendor.fieldVendorId = model2.fieldVendor.fieldVendorId" +
					" and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model2.constituency.constituencyId = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
				
		if(lastOneHourTime!= null && today!=null){
			sb.append(" and model.tdpCadre.surveyTime between :lastOneHourTime and :today");	 
		}
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" group by model1.fieldVendor.fieldVendorId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(lastOneHourTime!= null && today!=null){
			query.setParameter("lastOneHourTime", lastOneHourTime);
			query.setParameter("today", today);
		}
		
		return (Long) query.uniqueResult();
		/*StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct TC.created_by) as count" +
					" from tdp_cadre_enrollment_year TCEY,tdp_cadre TC,field_vendor_tab_user FVTU,field_vendor_location FVL,constituency C" +
					" where TCEY.tdp_cadre_id = TC.tdp_cadre_id" +
					" and TC.created_by = FVTU.cadre_survey_user_id" +
					" and FVTU.field_vendor_id = FVL.field_vendor_id" +
					" and FVL.field_vendor_id = :vendorId" +
					" and FVL.constituency_id = C.constituency_id");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state_id = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district_id = :locationVal");
		else
			sb.append(" and C.constituency_id = :locationVal");
				
		if(currentDate != null)
			sb.append(" and (TIME_TO_SEC(TIME(:currentTime))-TIME_TO_SEC(TIME(TC.survey_time)))/60 < 61");
		
		sb.append(" and TC.enrollment_year = 2014" +
					" and TCEY.enrollment_year_id = 4" +
					" and TCEY.is_deleted = 'N'" +
					" and TC.is_deleted = 'N'" +
					" and FVTU.is_deleted = 'N'" +
					" and FVL.is_deleted = 'N'" +
					" group by FVL.field_vendor_id");
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("count",Hibernate.LONG);
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(currentDate != null){
			query.setParameter("currentTime", currentDate);
		}
		
		return (Long) query.uniqueResult();*/
	}

	public List<Object[]> getIssueStatusWiseCounts(Date fromDate,Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status " +
				"         from CadreRegIssue model  " );
		
		if(fromDate != null && toDate != null)
			sb.append(" where date(model.insertedTime) between :fromDate and :toDate");
		
		sb.append(" group by model.cadreRegIssueStatus.cadreRegIssueStatusId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getIssueTypeWiseCounts(Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
				" model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType from " +
				" CadreRegIssue model   ");
		if(fromDate != null && toDate != null)
			sb.append(" where date(model.insertedTime) between :fromDate and :toDate");
		
		sb.append(" group by model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueType.cadreRegIssueTypeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
    
	public List<Object[]> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long issueStatusId,Long vendorId,String locationType,Long locationVal){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegIssueId,model.description,model.insertedTime," +
				"          model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType," +
				"          model.cadreRegIssueStatus.cadreRegIssueStatusId, model.cadreRegIssueStatus.status " +
				"   from   CadreRegIssue model,FieldVendorTabUser model1,Constituency C " +
				"   where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				"   and  model.cadreSurveyUserId = :cadreSurveyUserId and model.tabUserInfoId = :tabUserInfoId" +
				"  and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model.locationScopeId = 4" +
					" and model.locationValue = C.constituencyId");
		
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
		
		if(issueStatusId != null && issueStatusId > 0l){
			sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId  = :issueStatusId");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
		}
		    sb.append(" order by model.insertedTime desc ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreSurveyUserId",cadreSurveyUserId );
		query.setParameter("tabUserInfoId",tabUserInfoId );
		query.setParameter("vendorId", vendorId);
		if(issueStatusId != null && issueStatusId > 0l){
			query.setParameter("issueStatusId",issueStatusId );
		}
		query.setParameter("locationVal", locationVal);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate );
			query.setDate("toDate",toDate );
		}
		return query.list();
	}
	
	public List<Object[]> getIssuesCountsForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long vendorId,String locationType,Long locationVal){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegIssueStatus.cadreRegIssueStatusId, model.cadreRegIssueStatus.status,count(model.cadreRegIssueId) " +
				"   from   CadreRegIssue model,FieldVendorTabUser model1,Constituency C " +
				"   where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				"   and  model.cadreSurveyUserId = :cadreSurveyUserId and model.tabUserInfoId = :tabUserInfoId " +
				"  and model1.fieldVendor.fieldVendorId = :vendorId" +
					" and model.locationScopeId = 4" +
					" and model.locationValue = C.constituencyId ");
		if(locationType != null && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and C.state.stateId = :locationVal");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and C.district.districtId = :locationVal");
		else
			sb.append(" and C.constituencyId = :locationVal");
		
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by model.cadreRegIssueStatus.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreSurveyUserId",cadreSurveyUserId );
		query.setParameter("tabUserInfoId",tabUserInfoId );
		query.setParameter("vendorId", vendorId);
		query.setParameter("locationVal", locationVal);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate );
			query.setDate("toDate",toDate );
		}
		return query.list();
	}
	
public Long getActiveUsersCount(Date fromDate,Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select  count(distinct model.createdBy) " +
				"  from CadreRegIssue model  " );
		
		if(fromDate != null && toDate != null)
			sb.append(" where date(model.insertedTime) between :fromDate and :toDate");
		
		Query query = getSession().createQuery(sb.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return (Long)query.uniqueResult();
	}

public List<Object[]> getDistrictWiseIssueTypesCount(Date fromDate,Date toDate,Long statusTypeId,List<Long> stateIds){
	StringBuilder sb = new StringBuilder();
	sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
			" model.cadreRegIssueType.cadreRegIssueTypeId,model.userAddress.district.districtId" +
			" ,model.userAddress.district.state.stateId,model.userAddress.district.state.stateName  from " +
			" CadreRegIssue model   ");
	if(fromDate != null && toDate != null)
		sb.append(" where date(model.insertedTime) between :fromDate and :toDate");
	
		sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId = :statusTypeId ");
		if(stateIds != null  && stateIds.size()>0)
		sb.append(" and model.userAddress.district.state.stateId in (1) ");
				
	sb.append(" group by model.userAddress.district.districtId,model.cadreRegIssueType.cadreRegIssueTypeId ");
	
	Query query = getSession().createQuery(sb.toString());
		
	
	query.setParameter("statusTypeId",statusTypeId );
	query.setDate("fromDate", fromDate);
	query.setDate("toDate", toDate);
	return query.list();
}

public List<Object[]> getLocationWiseDetailedOverViewDetails(Date fromDate,Date toDate,String locationType,Long locationVal){
	StringBuilder sb = new StringBuilder();
	sb.append("select");
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId," +
					" model.tdpCadre.userAddress.district.districtName,'','',");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId," +
					" model.tdpCadre.userAddress.constituency.name,'','',");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,tehsil.tehsilName," +
					" leb.localElectionBodyId,leb.name,");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId,panc.panchayatName,'','',");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId,ward.name,'','',");
	
	sb.append(" count(model.tdpCadre.tdpCadreId)");
	
	sb.append(" from TdpCadreEnrollmentYear model" +
				" left join model.tdpCadre.userAddress.tehsil tehsil" +
				" left join model.tdpCadre.userAddress.localElectionBody leb" +
				" left join model.tdpCadre.userAddress.panchayat panc" +
				" left join model.tdpCadre.userAddress.ward ward " +
				" where model.tdpCadre.enrollmentYear = 2014" +
				" and model.enrollmentYear.enrollmentYearId = 4" +
				" and model.isDeleted = 'N'" +
				" and model.tdpCadre.isDeleted = 'N'");
	if(fromDate != null && toDate != null)
		sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
	
	if(locationType != null && locationType.equalsIgnoreCase("state") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.state.stateId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("district") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.district.districtId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal") && locationVal.longValue() > 0l)
		sb.append(" and tehsil.tehsilId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality") && locationVal.longValue() > 0l)
		sb.append(" and leb.localElectionBodyId = :locationVal");
	
	sb.append(" group by");
	
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId" +
					" order by model.tdpCadre.userAddress.district.districtName");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId" +
					" order by model.tdpCadre.userAddress.constituency.name");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,leb.localElectionBodyId" +
					" order by tehsil.tehsilName,leb.name");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId order by panc.panchayatName");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId order by ward.name");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(locationVal != null && locationVal.longValue() > 0l)
		query.setParameter("locationVal", locationVal);
	
	return query.list();
}

public List<Object[]> getLocationWiseDataVerifiedCounts(Date fromDate,Date toDate,String locationType,Long locationVal){
	StringBuilder sb = new StringBuilder();
	sb.append("select");
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,leb.localElectionBodyId,");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId,'',");
	
	sb.append(" count(model.tdpCadre.tdpCadreId)");
	
	sb.append(" from TdpCadreEnrollmentYear model" +
				" left join model.tdpCadre.userAddress.tehsil tehsil" +
				" left join model.tdpCadre.userAddress.localElectionBody leb" +
				" left join model.tdpCadre.userAddress.panchayat panc" +
				" left join model.tdpCadre.userAddress.ward ward " +
				" where model.tdpCadre.enrollmentYear = 2014" +
				" and model.enrollmentYear.enrollmentYearId = 4" +
				" and model.isDeleted = 'N'" +
				" and model.tdpCadre.isDeleted = 'N'" +
				" and model.tdpCadre.cadreVerificationStatusId is not null");
	if(fromDate != null && toDate != null)
		sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
	
	if(locationType != null && locationType.equalsIgnoreCase("state") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.state.stateId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("district") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.district.districtId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal") && locationVal.longValue() > 0l)
		sb.append(" and tehsil.tehsilId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality") && locationVal.longValue() > 0l)
		sb.append(" and leb.localElectionBodyId = :locationVal");
	
	sb.append(" group by");
	
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId" +
					" order by model.tdpCadre.userAddress.district.districtName");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId" +
					" order by model.tdpCadre.userAddress.constituency.name");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,leb.localElectionBodyId" +
					" order by tehsil.tehsilName,leb.name");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId order by panc.panchayatName");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId order by ward.name");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(locationVal != null && locationVal.longValue() > 0l)
		query.setParameter("locationVal", locationVal);
	
	return query.list();
}

public List<Object[]> getLocationWiseStatusWiseIssuesCounts(Date fromDate,Date toDate,String locationType,Long locationVal){
	StringBuilder sb = new StringBuilder();
	sb.append("select");
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,leb.localElectionBodyId,");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId,'',");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId,'',");
	
	sb.append(" model1.cadreRegIssueStatus.cadreRegIssueStatusId," +
				" count(distinct model1.cadreRegIssueId)");
	
	sb.append(" from TdpCadreEnrollmentYear model,CadreRegIssue model1" +
				" left join model.tdpCadre.userAddress.tehsil tehsil" +
				" left join model.tdpCadre.userAddress.localElectionBody leb" +
				" left join model.tdpCadre.userAddress.panchayat panc" +
				" left join model.tdpCadre.userAddress.ward ward " +
				" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				" and model.tdpCadre.tabUserInfo.tabUserInfoId = model1.tabUserInfo.tabUserInfoId" +
				" and model.tdpCadre.enrollmentYear = 2014" +
				" and model.enrollmentYear.enrollmentYearId = 4" +
				" and model.isDeleted = 'N'" +
				" and model.tdpCadre.isDeleted = 'N'");
	if(fromDate != null && toDate != null)
		sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
	
	if(locationType != null && locationType.equalsIgnoreCase("state") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.state.stateId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("district") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.district.districtId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal.longValue() > 0l)
		sb.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal") && locationVal.longValue() > 0l)
		sb.append(" and tehsil.tehsilId = :locationVal");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality") && locationVal.longValue() > 0l)
		sb.append(" and leb.localElectionBodyId = :locationVal");
	
	sb.append(" group by");
	
	if(locationType != null && locationType.equalsIgnoreCase("state"))
		sb.append(" model.tdpCadre.userAddress.district.districtId,model1.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.tdpCadre.userAddress.district.districtName");
	else if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId,model1.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.tdpCadre.userAddress.constituency.name");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" tehsil.tehsilId,leb.localElectionBodyId,model1.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by tehsil.tehsilName,leb.name");
	else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
		sb.append(" panc.panchayatId,model1.cadreRegIssueStatus.cadreRegIssueStatusId order by panc.panchayatName");
	else if(locationType != null && locationType.equalsIgnoreCase("muncipality"))
		sb.append(" ward.constituencyId,model1.cadreRegIssueStatus.cadreRegIssueStatusId order by ward.name");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
	}
	if(locationVal != null && locationVal.longValue() > 0l)
		query.setParameter("locationVal", locationVal);
	
	return query.list();
}
}
