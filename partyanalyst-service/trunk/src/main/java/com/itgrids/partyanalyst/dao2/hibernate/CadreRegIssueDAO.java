package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegIssueDAO;
import com.itgrids.partyanalyst.model.CadreRegIssue;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
	
	public List<Object[]> getTabUsersDetailsByVendorAndLocationNew(Long cadreRegUserId,Date fromDate,Date toDate,Long constituencyId,Long userId,Long districtId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.cadreSurveyUser.cadreSurveyUserId," +
					" model1.cadreSurveyUser.userName," +
					" model.tdpCadre.tabUserInfo.tabUserInfoId," +
					" model.tdpCadre.tabUserInfo.name," +
					" model.tdpCadre.tabUserInfo.mobileNo," +
					" min(model.tdpCadre.surveyTime)," +
					" max(model.tdpCadre.surveyTime)," +
					" count(model.tdpCadre.tdpCadreId)," +
					" model2.constituency.constituencyId," +
					" model.tdpCadre.tabUserInfo.imgPath," +
					" model2.constituency.district.districtId, " +
					" model2.constituency.district.districtName,model2.constituency.name " +
					" from TdpCadreEnrollmentYear model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreRegUser.userType = 'FM'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
				sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			sb.append(" and model2.constituency.district.districtId = :districtId");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and  model2.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and   model2.constituency.district.districtId between 1 and 10 ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
				
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isEnabled = 'Y'" +
					" group by model.tdpCadre.tabUserInfoId,model.tdpCadre.insertedBy.cadreSurveyUserId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getTotalTabUsersDetailsByVendorAndLocationNew(Long cadreRegUserId,Date fromDate,Date toDate,Long constituencyId,Long userId,Long districtId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.cadreSurveyUserId," +
					" model1.userName," +
					" model3.tabUserInfoId," +
					" model3.name," +
					" model3.mobileNo,model2.constituency.district.districtId,model2.constituency.district.districtName" +
					" ,model2.constituency.constituencyId,model2.constituency.name,model3.imgPath,max(model3.updatedTime)," +
					"  model4.cadreRegUser.user.userName" +
					" from CadreSurveyUser model1,CadreSurveyUserAssignDetails model2,TabUserInfo model3" );
		if(fromDate != null && toDate != null){
			sb.append(" ,TdpCadreEnrollmentYear model " );
		}
		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" ,CadreRegUserTabUser model4");
		sb.append(" where model1.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUserId = model3.cadreSurveyUserId" +
					//" and model3.isEnabled = 'Y'" +
					" and model1.cadreSurveyUserId = model4.cadreSurveyUser.cadreSurveyUserId" +
					" and model4.cadreRegUser.userType = 'FM'" +
					" and model1.isDeleted = 'N'");
					//" and model1.cadreRegUser.userType = 'FM'");
		if(fromDate != null && toDate != null){  
			sb.append(" and  model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUserId ");
		}
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model4.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			sb.append(" and model2.constituency.district.districtId = :districtId");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and  model2.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and  model2.constituency.district.districtId between 1 and 10 ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUserId = :userId");
				
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
		
		sb.append(" " +
					" and model2.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					//" and model1.isEnabled = 'Y'" +
					//" and model3.isEnabled = 'Y'" +
					//" and model3.isOtpVerified = 'Y' " +
					" group by model1.cadreSurveyUserId,model3.tabUserInfoId" +
					" order by model3.insertedTime desc ");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getTotalCadreSurveyUsersTemplate(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.cadreSurveyUser.cadreSurveyUserId," +
					" model1.cadreSurveyUser.userName," +
					"  model1.cadreRegUser.user.userName," +
					" model2.constituency.district.districtId,model2.constituency.district.districtName" +
					" ,model2.constituency.constituencyId,model2.constituency.name" +
					" from CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" );
		
		sb.append(" where model1.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
					//" and model1.cadreSurveyUser.isEnabled = 'Y'" );
					" and model1.cadreRegUser.userType = 'FM'");
					//" and model1.cadreRegUser.userType = 'FM'");
		
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			sb.append(" and model2.constituency.district.districtId = :districtId");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				sb.append("  and  model2.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and  model2.constituency.district.districtId between 1 and 10 ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
				
		sb.append(" " +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					//" and model3.isEnabled = 'Y'" +
					//" and model3.isOtpVerified = 'Y' " +
					" group by model1.cadreSurveyUserId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		
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
	}
	
	public List<Object[]> getLastHourCountsNew(Long cadreRegUserId,Date lastOneHourTime,Date today,Long constituencyId,Long userId,Long districtId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.cadreSurveyUser.cadreSurveyUserId," +
					" model.tdpCadre.tabUserInfo.tabUserInfoId," +
					" count(model.tdpCadre.tdpCadreId)" +
					" from TdpCadreEnrollmentYear model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreRegUser.userType = 'FM'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
					sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			sb.append(" and model2.constituency.district.districtId = :districtId");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				sb.append(" and  model2.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append("  and  model2.constituency.district.districtId between 1 and 10 ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
					
		if(lastOneHourTime!= null && today!=null){
			sb.append(" and model.tdpCadre.surveyTime between :lastOneHourTime and :today");	 
		}
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" group by model.tdpCadre.insertedBy.cadreSurveyUserId,model.tdpCadre.tabUserInfoId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		if(lastOneHourTime!= null && today!=null){
			query.setParameter("lastOneHourTime", lastOneHourTime);
			query.setParameter("today", today);
		}
		
		return query.list();
	}
	
	public List<Object[]> getcadreRegIssuesCountsNew(Long cadreRegUserId,Long constituencyId,Long userId,Date startDate,Date endDate,Long districtId,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.cadreSurveyUserId," +
					" model.tabUserInfoId," +
					" model.cadreRegIssueStatusId," +
					" count(model.cadreRegIssueId)" +
					" from CadreRegIssue model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
					" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreRegUser.userType = 'FM'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
					sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l){
			sb.append(" and model2.constituency.district.districtId = :districtId");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				sb.append(" and   model2.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				sb.append(" and  model2.constituency.district.districtId between 1 and 10 ");
			}
		}
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
							
		if(startDate != null && endDate != null)
			sb.append(" and date(model.insertedTime) between :startDate and :endDate");
				
		sb.append(" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" group by model.cadreSurveyUserId,model.tabUserInfoId,model.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
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
	
	public Long getTotalDataCollectorsCountsVendorAndLocationNew(Long cadreRegUserId,Long constituencyId,Long userId,Date fromDate,Date toDate,Long districtId){
		StringBuilder sb = new StringBuilder();
		/*sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
					" from TdpCadreEnrollmentYear model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId");
					//" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
					
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate");
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" and model1.cadreRegUser.userType = 'FM'");
					//" group by model1.cadreRegUser.cadreRegUserId");*/
		
		sb.append("select count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
					" from CadreSurveyUserAssignDetails model,CadreRegUserTabUser model1" +
					" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model.isDeleted = 'N'" +
					" and model.cadreSurveyUser.isEnabled = 'Y'" +
					" and model.cadreSurveyUser.isDeleted = 'N'");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUser.cadreSurveyUserId = :userId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		/*if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}*/
		
		return (Long) query.uniqueResult();
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
	
	public Long getActiveDataCollectorsCountsVendorAndLocationNew(Long cadreRegUserId,Long constituencyId,Long userId,Date lastOneHourTime,Date today,Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.tdpCadre.insertedBy.cadreSurveyUserId)" +
					" from TdpCadreEnrollmentYear model,CadreRegUserTabUser model1,CadreSurveyUserAssignDetails model2" +
					" where model.tdpCadre.insertedBy.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
					sb.append(" and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model2.constituency.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model2.constituency.constituencyId = :constituencyId");
		if(userId != null && userId.longValue() > 0l)
			sb.append(" and model1.cadreSurveyUser.cadreSurveyUserId = :userId");
					
		
		if(lastOneHourTime!= null && today!=null){
			sb.append(" and model.tdpCadre.surveyTime between :lastOneHourTime and :today");	 
		}
		
		sb.append(" and model.tdpCadre.enrollmentYear = 2014" +
					" and model.enrollmentYear.enrollmentYearId = 4" +
					" and model.isDeleted = 'N'" +
					" and model.tdpCadre.isDeleted = 'N'" +
					" and model1.isDeleted = 'N'" +
					" and model2.isDeleted = 'N'" +
					" and model1.cadreSurveyUser.isDeleted = 'N'" +
					" and model1.cadreRegUser.userType = 'FM'");
					//" group by model1.cadreRegUser.cadreRegUserId");
		
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);;
		if(userId != null && userId.longValue() > 0l)
			query.setParameter("userId", userId);
		if(lastOneHourTime!= null && today!=null){
			query.setParameter("lastOneHourTime", lastOneHourTime);
			query.setParameter("today", today);
		}
		
		return (Long) query.uniqueResult();
	}
	
	public Long getCadreRegUsersId(Long cadreRegUserId){
		Query query = getSession().createQuery("select count(distinct model.cadreSurveyUserId) from CadreRegUserTabUser model" +
				" where model.cadreRegUserId = :cadreRegUserId" +
				" and model.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
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
	}

	public List<Object[]> getIssueStatusWiseCounts(Date fromDate,Date toDate,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status " +
				"         from CadreRegIssue model  " );
		
		sb.append(" where ");
		
		if(fromDate != null && toDate != null)
			sb.append("   date(model.insertedTime) between :fromDate and :toDate and ");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("   model.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append("   model.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append("  model.userAddress.state.stateId = 1 ");
		}
		
		sb.append(" group by model.cadreRegIssueStatus.cadreRegIssueStatusId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getIssueTypeWiseCounts(Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
				" model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType from " +
				" CadreRegIssue model   ");
		sb.append(" where ");
		if(fromDate != null && toDate != null)
			sb.append("  date(model.insertedTime) between :fromDate and :toDate and ");
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("   model.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append("   model.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append("  model.userAddress.state.stateId = 1 ");
		}
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
				"          model.cadreRegIssueStatus.cadreRegIssueStatusId, model.cadreRegIssueStatus.status, model.updatedTime " +
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
	
	public List<Object[]> getIssuesForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long issueStatusId,Long cadreRegUserId,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegIssueId,model.description,model.insertedTime," +
				"          model.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegIssueType.issueType," +
				"          model.cadreRegIssueStatus.cadreRegIssueStatusId, model.cadreRegIssueStatus.status, model.updatedTime " +
				"   from   CadreRegIssue model,CadreRegUserTabUser model1 " +
				"   where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				"   and  model.cadreSurveyUserId = :cadreSurveyUserId and model.tabUserInfoId = :tabUserInfoId" +
				" and model1.cadreRegUser.userType = 'FM'");
				//"  and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		
		if(issueStatusId != null && issueStatusId > 0l){
			sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId  = :issueStatusId");
		}
		/*if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
		}*/
		
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.userAddress.state.stateId = 1 ");
		}
		
		    sb.append(" order by model.insertedTime desc ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreSurveyUserId",cadreSurveyUserId );
		query.setParameter("tabUserInfoId",tabUserInfoId );
		//query.setParameter("cadreRegUserId", cadreRegUserId);
		if(issueStatusId != null && issueStatusId > 0l){
			query.setParameter("issueStatusId",issueStatusId );
		}
		/*if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate );
			query.setDate("toDate",toDate );
		}*/
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
	
public List<Object[]> getIssuesCountsForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long cadreRegUserId,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegIssueStatus.cadreRegIssueStatusId, model.cadreRegIssueStatus.status,count(model.cadreRegIssueId) " +
				"   from   CadreRegIssue model,CadreRegUserTabUser model1 " +
				"   where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				"   and  model.cadreSurveyUserId = :cadreSurveyUserId and model.tabUserInfoId = :tabUserInfoId " +
				" and model1.cadreRegUser.userType = 'FM'");
				//"  and model1.cadreRegUser.cadreRegUserId = :cadreRegUserId");
		
		/*if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
		}*/
		if(stateId != null && stateId.longValue() == 1l){
			sb.append(" and  model.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and  model.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.userAddress.state.stateId = 1 ");
		}
		
		sb.append(" group by model.cadreRegIssueStatus.cadreRegIssueStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreSurveyUserId",cadreSurveyUserId );
		query.setParameter("tabUserInfoId",tabUserInfoId );
		//query.setParameter("cadreRegUserId", cadreRegUserId);
		/*if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate );
			query.setDate("toDate",toDate );
		}*/
		return query.list();
	}
	
public Long getActiveUsersCount(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select  count(distinct model.createdBy) " +
				"  from CadreRegIssue model  " );
		
		sb.append(" where date(model.insertedTime) = :today");
		
		Query query = getSession().createQuery(sb.toString());
		query.setDate("today", new DateUtilService().getCurrentDateAndTime());
		
		return (Long)query.uniqueResult();
	}

public List<Object[]> getDistrictWiseIssueTypesCount(Date fromDate,Date toDate,Long statusTypeId,Long stateId){
	StringBuilder sb = new StringBuilder();
	sb.append(" select count(model.cadreRegIssueId),model.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegIssueStatus.status," +
			" model.cadreRegIssueType.cadreRegIssueTypeId,model.userAddress.district.districtId" +
			" ,model.userAddress.district.state.stateId,model.userAddress.district.state.stateName  from " +
			" CadreRegIssue model   ");
	if(fromDate != null && toDate != null)
		sb.append(" where date(model.insertedTime) between :fromDate and :toDate");
	
		sb.append(" and model.cadreRegIssueStatus.cadreRegIssueStatusId = :statusTypeId ");
		if(stateId != null && stateId.longValue() == 1l ){
			sb.append(" and model.userAddress.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.userAddress.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.userAddress.state.stateId = 1 ");
		}
		
				
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
	
	if(locationType != null && locationType.equalsIgnoreCase("state") && locationVal.longValue() > 0l){
		if(locationVal.longValue() == 1l){
			sb.append(" and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(locationVal.longValue() == 36l){
			sb.append(" and model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}
	}
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
	if(locationType != null && !locationType.equalsIgnoreCase("state") && locationVal != null && locationVal.longValue() > 0l)
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
	
	if(locationType != null && locationType.equalsIgnoreCase("state") && locationVal.longValue() > 0l){
		if(locationVal.longValue() == 1l){
			sb.append(" and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}else if(locationVal.longValue() == 36l){
			sb.append(" and model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}
	}
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
	if(locationType != null && !locationType.equalsIgnoreCase("state") && locationVal != null && locationVal.longValue() > 0l)
		query.setParameter("locationVal", locationVal);
	
	return query.list();
}

public List<Object[]> getLocationWiseIssuesCounts(Date fromDate,Date toDate,String locationType,Long locationVal){
	StringBuilder sb = new StringBuilder();
	sb.append("select");
	if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.userAddress.district.districtId,");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" model.userAddress.constituency.constituencyId,");
	
	sb.append(" model.cadreRegIssueStatus.cadreRegIssueStatusId," +
				" count(distinct model.cadreRegIssueId)" +
				" from CadreRegIssue model");
	
	if(fromDate != null && toDate != null)
		sb.append(" where (date(model.insertedTime) between :fromDate and :toDate)");
	
	if(locationType != null && locationType.equalsIgnoreCase("district") && locationVal.longValue() > 0l){
		if(locationVal.longValue() == 1l){
			sb.append(" and model.userAddress.district.districtId between 11 and 23 ");
		}else if(locationVal.longValue() == 36l){
			sb.append(" and model.userAddress.district.districtId between 1 and 10 ");
		}
	}
	else if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal.longValue() > 0l)
		sb.append(" and model.userAddress.district.districtId = :locationVal");
	
	sb.append(" group by");
	if(locationType != null && locationType.equalsIgnoreCase("district"))
		sb.append(" model.userAddress.district.districtId,model.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.userAddress.district.districtName");
	else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
		sb.append(" model.userAddress.constituency.constituencyId,model.cadreRegIssueStatus.cadreRegIssueStatusId" +
					" order by model.userAddress.constituency.name");
	
	Query query = getSession().createQuery(sb.toString());
	if(fromDate != null && toDate != null){
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
	}
	if(locationType != null && locationType.equalsIgnoreCase("constituency") && locationVal != null && locationVal.longValue() > 0l)
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
		sb.append(" and date(model1.insertedTime) between :fromDate and :toDate");
	
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
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
	}
	if(locationVal != null && locationVal.longValue() > 0l)
		query.setParameter("locationVal", locationVal);
	
	return query.list();
}

}
