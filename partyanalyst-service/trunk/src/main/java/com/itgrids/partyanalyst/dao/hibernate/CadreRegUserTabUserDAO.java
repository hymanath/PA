package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.CadreRegUserTabUser;

public class CadreRegUserTabUserDAO extends GenericDaoHibernate<CadreRegUserTabUser, Long> implements ICadreRegUserTabUserDAO{

	public CadreRegUserTabUserDAO() {
		super(CadreRegUserTabUser.class);
		
	}

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId,Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.constituency.constituencyId," +
								" model1.constituency.name" +
								" from CadreSurveyUserAssignDetails model1");
			sb.append(",CadreRegUserTabUser model");
			sb.append(" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" );
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
		    sb.append(" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId");
			sb.append(" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
						" and model1.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y'");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model1.constituency.district.districtId = :districtId");
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
		query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
		/*Query query = getSession().createQuery("select distinct model1.constituency.constituencyId," +
								" model1.constituency.name" +
								" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
								" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
								" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
		return query.list();*/
	}
	
	public List<Object[]> getUserAssignedUsers(Long cadreRegUserId,Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.cadreSurveyUser.cadreSurveyUserId," +
								" model.cadreSurveyUser.userName" +
								" from CadreRegUserTabUser model");
			sb.append(",CadreSurveyUserAssignDetails model1");
		  sb.append(" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId ");
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId");
			sb.append(" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y'");
		if(constituencyId != null && constituencyId.longValue() > 0l)
				sb.append("and model1.constituency.constituencyId = :constituencyId and model1.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			query.setParameter("cadreRegUserId", cadreRegUserId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getAllAssignedConstituencies(Long districtId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.constituency.constituencyId," +
										" model1.constituency.name" +
								" from CadreRegUserTabUser model");
		sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
								" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
								" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.isDeleted = 'N'");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model1.constituency.district.districtId = :districtId");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("searchType", searchType);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
		/*Query query = getSession().createQuery("select distinct model1.constituency.constituencyId," +
										" model1.constituency.name" +
										" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
										" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
										" and model.cadreRegUser.userType = 'FM'" +
										" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
										" and model.cadreSurveyUser.isDeleted = 'N'");
		return query.list();*/
	}
	
	public List<Object[]> getAllUserAssignedUsers(Long constituencyId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.cadreSurveyUser.cadreSurveyUserId," +
								" model.cadreSurveyUser.userName" +
								" from CadreRegUserTabUser model");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y'");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.constituency.constituencyId = :constituencyId and model1.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("searchType", searchType);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getUserAssignedDistricts(Long cadreRegUserId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.constituency.district.districtId," +
								" model1.constituency.district.districtName" +
								" from CadreSurveyUserAssignDetails model1" );
			sb.append(",CadreRegUserTabUser model");
		 sb.append(" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId");
		 if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			sb.append(	" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
								" and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y' ");
		 Query query=getSession().createQuery(sb.toString());
		 if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l)
			 query.setParameter("cadreRegUserId", cadreRegUserId);
		return query.list();
	}
	
	public List<Object[]> getAllAssignedDistricts(String searchType){
		Query query = getSession().createQuery("select distinct model1.constituency.district.districtId," +
										" model1.constituency.district.districtName" +
										" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
										" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
										" and (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
										" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
										" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("searchType", searchType);
		return query.list();
	}
	
	public Long getAssignedUsersCountForRegUser(Long cadreRegUserId){
		Query query = getSession().createQuery("select count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
										" from CadreRegUserTabUser model" +
										" where model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
										" and model.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getFieldMonitoringUserWiseDetails( GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select model.cadreRegUser.user.userId,model.cadreRegUser.user.userName," +
											" model1.constituency.district.districtId,model1.constituency.district.districtName," +
											" model1.constituency.constituencyId,model1.constituency.name," +
											" count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
											" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
											" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
											" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
											" and model.cadreSurveyUser.isEnabled = 'Y' and model1.isDeleted = 'N'" +
											" and model.cadreRegUser.userType = 'FM'" +
											" group by model1.constituency.constituencyId,model1.constituency.district.districtId,model.cadreRegUser.user.userId" +
											" order by model.cadreRegUser.user.userName");
		return query.list();
	}
	
	public List<Object[]> getIssueTypeWiseCountsForFieldMonrUsers(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select model.cadreRegUser.user.userId,''," +
											" model2.cadreRegIssueType.cadreRegIssueTypeId,count(distinct model2.cadreRegIssueId)," +
											" model2.locationValue" +
											" from CadreRegUserTabUser model,CadreRegIssue model2" +
											" where model.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
											//" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
											" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
											" and model.cadreSurveyUser.isEnabled = 'Y'" +
											" and model2.locationScopeId = 4" +
											//" and model1.isDeleted = 'N'" +
											" and model.cadreRegUser.userType = 'FM' and model2.cadreRegIssueStatus.cadreRegIssueStatusId = 1" +
											" and date(model2.insertedTime) = :today" +
											//" and model2.locationValue = 311" +
											" group by model2.cadreRegIssueType.cadreRegIssueTypeId,model2.locationValue," +
											" 		model.cadreRegUser.user.userId" +
											" order by model2.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegUser.user.userName");
		query.setDate("today", today);
		return query.list();
	}
	
	public List<Object[]> getStartedUsersIssueTypeWiseCountsForFieldMonrUsers(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select model.cadreRegUser.user.userId,''," +
											" model2.cadreRegIssueType.cadreRegIssueTypeId,count(distinct model2.cadreRegIssueId)," +
											" model2.locationValue" +
											" from CadreRegUserTabUser model,CadreRegIssue model2,TabUserEnrollmentInfo model3" +
											" where model.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
											//" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
											" and model2.cadreSurveyUser.cadreSurveyUserId = model3.cadreSurveyUserId" +
											" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
											" and model.cadreSurveyUser.isEnabled = 'Y'" +
											" and model2.locationScopeId = 4" +
											" and model3.enrollmentYearId = 4" +
											//" and model1.isDeleted = 'N'" +
											" and model.cadreRegUser.userType = 'FM' and model2.cadreRegIssueStatus.cadreRegIssueStatusId = 1" +
											" and date(model2.insertedTime) = :today and date(model3.surveyTime) = :today" +
											//" and model2.locationValue = 311" +
											" group by model2.cadreRegIssueType.cadreRegIssueTypeId,model2.locationValue," +
											" 		model.cadreRegUser.user.userId" +
											" order by model2.cadreRegIssueType.cadreRegIssueTypeId,model.cadreRegUser.user.userName");
		query.setDate("today", today);
		return query.list();
	}
	
	public List<Object[]> getIssueStatusWiseCountsForFieldMonrUsers(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select model.cadreRegUser.user.userId,model1.constituency.district.districtId," +
											" model2.cadreRegIssueStatus.cadreRegIssueStatusId,count(distinct model2.cadreRegIssueId)," +
											" model1.constituency.constituencyId" +
											" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1,CadreRegIssue model2" +
											" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
											" and model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
											" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
											" and model.cadreSurveyUser.isEnabled = 'Y' and model1.isDeleted = 'N'" +
											" and model.cadreRegUser.userType = 'FM'" +
											" and date(model2.insertedTime) = :today" +
											" group by model2.cadreRegIssueStatus.cadreRegIssueStatusId,model1.constituency.constituencyId," +
											" model1.constituency.district.districtId,model.cadreRegUser.user.userId" +
											" order by model2.cadreRegIssueStatus.cadreRegIssueStatusId,model.cadreRegUser.user.userName");
		query.setDate("today", today);
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseFMUsersDetails(){
		Query query = getSession().createQuery("select model.cadreRegUser.user.userId,model.cadreRegUser.user.userName," +
											" model1.constituency.district.districtId,model1.constituency.district.districtName," +
											" model1.constituency.constituencyId,model1.constituency.name " +
											" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
											" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
											" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
											" and model.cadreSurveyUser.isEnabled = 'Y' and model1.isDeleted = 'N'" +
											" and model.cadreRegUser.userType = 'FM'" +
											" group by model1.constituency.district.districtId,model1.constituency.constituencyId,model.cadreRegUser.user.userId" +
											" order by model.cadreRegUser.user.userName");
		return query.list();
	}
	
	public List<Object[]> getTotalUsersCountForFMUsers(){
		Query query = getSession().createQuery("select "+
				" model1.constituency.constituencyId,count(distinct model.cadreSurveyUser.cadreSurveyUserId)" +
				" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
				" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
				" and model.cadreSurveyUser.isEnabled = 'Y' and model1.isDeleted = 'N'" +
				" and model.cadreRegUser.userType = 'FM' " +
				" group by model1.constituency.district.districtId,model1.constituency.constituencyId,model.cadreRegUser.user.userId" +
				" order by model.cadreRegUser.user.userName");
		
return query.list();
		
	}
	
	public List<Object[]> getTotalRegisteredUsers(GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select " +
				" model1.constituency.constituencyId,count(distinct model.cadreSurveyUser.cadreSurveyUserId)," +
				" model.cadreRegUser.user.userId " +
				" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1,TabUserInfo model2" +
				" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
				" and model.cadreSurveyUser.isEnabled = 'Y' and model1.isDeleted = 'N'" +
				" and model2.cadreSurveyUser.cadreSurveyUserId = model.cadreSurveyUser.cadreSurveyUserId" +
				" and model2.isEnabled = 'Y' " +
				" and model.cadreRegUser.userType = 'FM' " +
				" group by model1.constituency.district.districtId,model1.constituency.constituencyId,model.cadreRegUser.user.userId" +
				" order by model.cadreRegUser.user.userName");
		
		return query.list();
		
	}
	
	public List<Object[]> getTodayStartedUsersOfFMUser(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select " +
				" model2.constituency.constituencyId,count(distinct model2.cadreSurveyUserId)," +
				" model.cadreRegUser.user.userId " +
				" from CadreRegUserTabUser model,TabUserEnrollmentInfo model2" +
				" where model.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUserId"+
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
				" and model.cadreSurveyUser.isEnabled = 'Y'" +
				" and model2.enrollmentYearId = 4" +
				" and date(model2.surveyTime) = :today " +
				" and model.cadreRegUser.userType = 'FM' " +
				" group by model2.constituency.constituencyId,model.cadreRegUser.user.userId ");
		
		query.setDate("today", today);
		return query.list();
		
	}
	
	public List<Object[]> getLastOneHourUsersOfFMUser(Date lastHourTime,Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select " +
				" model2.constituency.constituencyId,count(distinct model2.cadreSurveyUserId)," +
				" model.cadreRegUser.user.userId " +
				" from CadreRegUserTabUser model,TabUserEnrollmentInfo model2" +
				" where model.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUserId"+
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
				" and model.cadreSurveyUser.isEnabled = 'Y'" +
				" and date(model2.surveyTime) = :today" +
				//" and model2.endTime between :today and :lastHourTime" +
				" and model2.endTime > :lastHourTime" +
				" and model2.enrollmentYearId = 4" +
				" and model.cadreRegUser.userType = 'FM' " +
				" group by model2.constituency.constituencyId,model.cadreRegUser.user.userId ");
		
		query.setParameter("today", today);
		query.setParameter("lastHourTime", lastHourTime);
		return query.list();
		
	}
	
	public List<Object[]> getTodayTotalIssues(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select " +
				" model2.userAddress.constituency.constituencyId,count(distinct model2.cadreRegIssueId),model.cadreRegUser.user.userId " +
				" from CadreRegUserTabUser model,CadreRegIssue model2" +
				" where model.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
				" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
				" and model.cadreSurveyUser.isEnabled = 'Y'"+
				" and model2.cadreRegIssueStatus.cadreRegIssueStatusId = 1  " +
				"  and date(model2.updatedTime) = :today " +
				" and model.cadreRegUser.userType = 'FM'" +
				" group by model2.userAddress.constituency.constituencyId,model.cadreRegUser.user.userId ");
		
		query.setDate("today", today);
		return query.list();
		
	}
	
	public List<Object[]> getTodayTotalStartedIssues(Date today, GISVisualizationParameterVO inputVO){
		Query query = getSession().createQuery("select  " +
				" model.constituency.constituencyId,count(distinct model2.cadreRegIssueId),model1.cadreRegUser.user.userId " +
				" from CadreRegIssue model2,TabUserEnrollmentInfo model,CadreRegUserTabUser model1" +
				" where model1.cadreSurveyUser.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId" +
				" and model2.cadreSurveyUser.cadreSurveyUserId = model.cadreSurveyUserId" +
				" and model1.isDeleted = 'N' and model1.cadreSurveyUser.isDeleted = 'N'" +
				" and model1.cadreSurveyUser.isEnabled = 'Y' "+
				"  and model2.cadreRegIssueStatus.cadreRegIssueStatusId = 1  " +
				"  and date(model2.updatedTime) = :today" +
				" and model.enrollmentYearId = 4" +
				" and model1.cadreRegUser.userType = 'FM' " +
				" group by model.constituency.constituencyId,model1.cadreRegUser.user.userId ");
		
		query.setDate("today", today);
		return query.list();
		
	}
	public List<Object[]> getFieldMonitoringMapReportDetails(Long constitunecyId, Long fieldUserId){
		Query query = getSession().createQuery("select distinct cru.cadreSurveyUserId,cru.cadreSurveyUser.userName from CadreRegUserTabUser cru,CadreSurveyUserAssignDetails csuad where cru.cadreRegUser.userId = :fieldUserId " +
				" and cru.cadreSurveyUserId = csuad.cadreSurveyUserId and csuad.constituencyId = :constitunecyId and cru.cadreSurveyUser.isEnabled='Y' order by cru.cadreSurveyUser.userName asc ");
		
		query.setParameter("constitunecyId", constitunecyId);
		query.setParameter("fieldUserId", fieldUserId);
		return query.list();
		
	}
}
