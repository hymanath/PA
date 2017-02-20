package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficer;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertAssignedOfficerDAO extends GenericDaoHibernate<AlertAssignedOfficer, Long> implements IAlertAssignedOfficerDAO{

	public AlertAssignedOfficerDAO() {
		super(AlertAssignedOfficer.class);
		  
	}

	public List<Object[]> getAssignedOfficersForAlert(Long alertId){
		Query query = getSession().createQuery("select distinct model.alertAssignedOfficerId," +
											" model.govtOfficer.officerName," +
											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
											" model.govtOfficer.mobileNo," +
											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
											" from AlertAssignedOfficer model" +
											" where model.alert.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByStatusForGovtOneDept(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" AAO.alert_status_id as alert_status_id," +
						" ALTS.alert_status as alert_status," +
						" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		
		queryStr.append(" left outer join user_address UA on UA.user_address_id = A.address_id ");
		queryStr.append(" left outer join state S on S.state_id = UA.state_id,  ");
		queryStr.append(" alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation_officer GDDO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department GD, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT ");
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and ");
		queryStr.append(" A.alert_category_id = ALTC.alert_category_id and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		queryStr.append(" A.alert_type_id = ALTT.alert_type_id and ");
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" AAO.is_approved = 'Y' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		queryStr.append(" group by AAO.alert_status_id order by ALTS.alert_status; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		
		return query.list(); 
	}//swa
	
	public List<Object[]> getDepartmentWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long locValue, List<Long> locIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" GD.govt_department_id as govt_department_id," +
						" GD.department_name as department_name," +
						" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		}
		queryStr.append(" , alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation_officer GDDO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department GD, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		queryStr.append(" user_address UA, state S");
		if(locValue != null && locValue.longValue() == 3L){
			queryStr.append("  ,district D ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append("  ,constituency C ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append("  ,tehsil T ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append("  ,panchayat P ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append("  ,local_election_body LEB ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append("  ,constituency CON ");
		}		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and ");
		queryStr.append(" A.alert_category_id = ALTC.alert_category_id and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		queryStr.append(" A.alert_type_id = ALTT.alert_type_id and ");
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") and GDDO.address_id = UA.user_address_id and ");
		
		if(locValue != null && locValue.longValue() == 2L){
			queryStr.append(" S.state_id = UA.state_id and S.state_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 3L){
			queryStr.append(" D.district_id = UA.district_id and D.district_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append(" C.constituency_id = UA.constituency_id and C.constituency_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append(" T.tehsil_id = UA.tehsil_id and T.tehsil_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append(" P.panchayat_id = UA.panchayat_id and P.panchayat_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append(" LEB.local_election_body_id = UA.local_election_body and LEB.local_election_body_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append(" CON.constituency_id = UA.ward and CON.constituency_id in (:locIdList) and ");
		}		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" AAO.is_approved = 'Y' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		queryStr.append(" group by GD.govt_department_id order by GD.department_name; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("govt_department_id", Hibernate.LONG)
				.addScalar("department_name", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(locIdList != null && locIdList.size() > 0){
			query.setParameterList("locIdList", locIdList);
		}
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long locValue, List<Long> locIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" GD.govt_department_id as govt_department_id, " +
						" GD.department_name as department_name, " +
						" AAO.alert_status_id as alert_status_id," +
						" ALTS.alert_status as alert_status," +
						" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		}
		
		queryStr.append(" , alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation_officer GDDO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department GD, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		queryStr.append(" user_address UA, state S");
		if(locValue != null && locValue.longValue() == 3L){
			queryStr.append("  ,district D ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append("  ,constituency C ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append("  ,tehsil T ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append("  ,panchayat P ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append("  ,local_election_body LEB ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append("  ,constituency CON ");
		}		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and ");
		queryStr.append(" A.alert_category_id = ALTC.alert_category_id and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		queryStr.append(" A.alert_type_id = ALTT.alert_type_id and ");
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") and GDDO.address_id = UA.user_address_id and ");
		
		if(locValue != null && locValue.longValue() == 2L){
			queryStr.append(" S.state_id = UA.state_id and S.state_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 3L){
			queryStr.append(" D.district_id = UA.district_id and D.district_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append(" C.constituency_id = UA.constituency_id and C.constituency_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append(" T.tehsil_id = UA.tehsil_id and T.tehsil_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append(" P.panchayat_id = UA.panchayat_id and P.panchayat_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append(" LEB.local_election_body_id = UA.local_election_body and LEB.local_election_body_id in (:locIdList) and ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append(" CON.constituency_id = UA.ward and CON.constituency_id in (:locIdList) and ");
		}		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" AAO.is_approved = 'Y' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		queryStr.append(" group by GD.govt_department_id,AAO.alert_status_id order by GD.department_name; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("govt_department_id", Hibernate.LONG)
				.addScalar("department_name", Hibernate.STRING)
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(locIdList != null && locIdList.size() > 0){
			query.setParameterList("locIdList", locIdList);
		}
		return query.list(); 
	}
	public List<Object[]> getLocationWiseThenStatusWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,Long deptId,Long levelValue){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" GDDO.level_value as level_value, ");
		if(levelValue.longValue() == 2L){
			queryStr.append(" S.state_name as name, ");
		}else if(levelValue.longValue() == 3L){
			queryStr.append(" D.district_name as name, ");
		}else if(levelValue.longValue() == 4L){
			queryStr.append(" C.name as name, ");
		}else if(levelValue.longValue() == 5L){
			queryStr.append(" T.tehsil_name as name, ");
		}else if(levelValue.longValue() == 6L){
			queryStr.append(" P.panchayat_name as name, ");
		}else if(levelValue.longValue() == 7L){
			queryStr.append(" LEB.name as name, ");
		}else if(levelValue.longValue() == 8L){
			queryStr.append(" CON.name as name, ");    
		}
		queryStr.append(" AAO.alert_status_id as alert_status_id, " +
						" ALTS.alert_status as alert_status, " +
						" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");    
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		}
		queryStr.append(" ,govt_department_designation_officer GDDO,  user_address UA, state S ");
		if(levelValue != null && levelValue.longValue() == 3L){
			queryStr.append("  ,district D ");
		}else if(levelValue != null && levelValue.longValue() == 4l){
			queryStr.append("  ,constituency C ");
		}else if(levelValue != null && levelValue.longValue() == 5l){
			queryStr.append("  ,tehsil T ");
		}else if(levelValue != null && levelValue.longValue() == 6l){
			queryStr.append("  ,panchayat P ");
		}else if(levelValue != null && levelValue.longValue() == 7l){
			queryStr.append("  ,local_election_body LEB ");
		}else if(levelValue != null && levelValue.longValue() == 8l){
			queryStr.append("  ,constituency CON ");
		}		
		
		queryStr.append(" , alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department_level GDL, ");
		queryStr.append(" govt_department GD, ");
		queryStr.append(" alert_status ALTS  ");
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and ");
		
		queryStr.append(" AAO.is_approved = 'Y' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and GDDO.address_id = UA.user_address_id and");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDDO.govt_department_level_id = GDL.govt_department_level_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id =:deptId and ");
		queryStr.append(" GDL.govt_department_level_id = :levelValue and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		if(levelValue != null && levelValue.longValue() == 2L){
			queryStr.append(" and S.state_id = UA.state_id and ");
		}else if(levelValue != null && levelValue.longValue() == 3L){  
			queryStr.append(" and D.district_id = UA.district_id and ");
		}else if(levelValue != null && levelValue.longValue() == 4l){
			queryStr.append(" and C.constituency_id = UA.constituency_id and ");
		}else if(levelValue != null && levelValue.longValue() == 5l){
			queryStr.append(" and T.tehsil_id = UA.tehsil_id and ");
		}else if(levelValue != null && levelValue.longValue() == 6l){
			queryStr.append(" and P.panchayat_id = UA.panchayat_id and ");
		}else if(levelValue != null && levelValue.longValue() == 7l){
			queryStr.append(" and LEB.local_election_body_id = UA.local_election_body and ");
		}else if(levelValue != null && levelValue.longValue() == 8l){
			queryStr.append(" and CON.constituency_id = UA.ward and ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 1  ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id = 36  ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id = UA.state_id and S.state_id in (1,36)  ");
			}
		}
		queryStr.append(" group by GDDO.level_value,AAO.alert_status_id order by ");
		if(levelValue.longValue() == 2L){  
			queryStr.append(" S.state_name; ");
		}else if(levelValue.longValue() == 3L){
			queryStr.append(" D.district_name; ");
		}else if(levelValue.longValue() == 4L){
			queryStr.append(" C.name; ");
		}else if(levelValue.longValue() == 5L){
			queryStr.append(" T.tehsil_name; ");
		}else if(levelValue.longValue() == 6L){
			queryStr.append(" P.panchayat_name ; ");
		}else if(levelValue.longValue() == 7L){
			queryStr.append(" P.name; ");
		}else if(levelValue.longValue() == 8L){
			queryStr.append(" CON.name; ");    
		}
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("level_value", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);  
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		query.setParameter("deptId", deptId);
		query.setParameter("levelValue",levelValue);
		return query.list();
	}
	
	public List<Object[]> getTotalAlerts(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select A.alert_id ");  
		queryStr.append(" from ");
		queryStr.append(" alert A ");   
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		
		queryStr.append(" left outer join user_address UA on UA.user_address_id = A.address_id ");
		queryStr.append(" left outer join state S on S.state_id = UA.state_id,  ");
		queryStr.append(" alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation_officer GDDO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department GD ");
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" AAO.is_approved = 'Y' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		queryStr.append(" group by AAO.alert_status_id order by ALTS.alert_status; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		
		return query.list(); 
	}
	
	public List<Object[]> getStatusWiseAlertDetails(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long levelId,List<Long> levelValues,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.alert.alertId," +
					" model.alert.alertSeverity.alertSeverityId," +
					" model.alert.alertSeverity.severity," +
					" model.alert.title," +
					" date(model.alert.createdTime)," +
					" date(model.insertedTime)," +
					" date(model.alert.updatedTime)," +
					" model.alert.alertStatus.alertStatusId," +
					" model.alert.alertStatus.alertStatus," +
					" model.alert.alertStatus.color" +
					" from AlertAssignedOfficer model");
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" left join model.alert.tvNewsChannel TNC" +
						" left join model.alert.edition E");
		}
		sb.append(" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		if(levelId != null && levelId > 0l)
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and model.alert.alertStatus.alertStatusId = :statusId");
		if(levelValues != null && !levelValues.isEmpty()){
			if(levelId == 2l)
				sb.append(" and S.stateId in (:levelValues)");
			else if(levelId == 3l)
				sb.append(" and D.districtId in (:levelValues)");
			else if(levelId == 4l)
				sb.append(" and C.constituencyId in (:levelValues)");
			else if(levelId == 5l)
				sb.append(" and T.tehsilId in (:levelValues)");
			else if(levelId == 6l)
				sb.append(" and P.panchayatId in (:levelValues)");
			else if(levelId == 7l)
				sb.append(" and LEB.localElectionBodyId in (:levelValues)");
			else if(levelId == 8l)
				sb.append(" and W.constituencyId in (:levelValues)");
		}
			
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			sb.append(" and ((E.newsPaperId in (:printIdList)) or (TNC.tvNewsChannelId in (:electronicIdList)))");
		if(deptIdList != null && !deptIdList.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList)");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			query.setParameterList("printIdList", printIdList);
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(deptIdList != null && !deptIdList.isEmpty())
			query.setParameterList("deptIdList", deptIdList);
		if(levelId != null && levelId > 0l)
			query.setParameter("levelId", levelId);
		if(levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		
		return query.list();
	}
	
	public List<Object[]> getSubOrdinatesAlertDetails(Long designationId,Long levelId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		if(levelId != null && levelId == 3l)
			sb.append(" D.districtId,D.districtName,");
		else if(levelId != null && levelId == 4l)
			sb.append(" C.constituencyId,C.name,");
		else if(levelId != null && levelId == 5l)
			sb.append(" T.tehsilId,T.tehsilName,");
		else if(levelId != null && levelId == 6l)
			sb.append(" P.panchayatId,P.panchayatName,");
		else if(levelId != null && levelId == 7l)
			sb.append(" LEB.localElectionBodyId,LEB.name,");
		else if(levelId != null && levelId == 8l)
			sb.append(" W.constituencyId,W.name,");
		
		sb.append(" model.alertStatus.alertStatusId," +
					" model.alertStatus.alertStatus," +
					" count(model.alert.alertId)" +
					" from AlertAssignedOfficer model" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W" +
					" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")" +
					" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId");
		if(fromDate != null && toDate != null)
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate)");
		
		sb.append(" group by");
		if(levelId != null && levelId == 3l)
			sb.append(" D.districtId,");
		else if(levelId != null && levelId == 4l)
			sb.append(" C.constituencyId,");
		else if(levelId != null && levelId == 5l)
			sb.append(" T.tehsilId,");
		else if(levelId != null && levelId == 6l)
			sb.append(" P.panchayatId,");
		else if(levelId != null && levelId == 7l)
			sb.append(" LEB.localElectionBodyId,");
		else if(levelId != null && levelId == 8l)
			sb.append(" W.constituencyId,");
		sb.append(" model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("designationId", designationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Long> getAlertAssignedOfficerIdsForAlertByUser(Long alertId,Long userId){
		Query query = getSession().createQuery(" select distinct model.alertAssignedOfficerId" +
												" from AlertAssignedOfficer model,GovtDepartmentDesignationOfficerDetails model1" +
												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = model1.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
												" and model.govtOfficer.govtOfficerId = model1.govtOfficer.govtOfficerId" +
												" and model.alert.alertId = :alertId" +
												" and model1.user.userId = userId" +
												" and model1.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(Date fromDate,Date toDate, List<Long> deptIdList, Long locValue, List<Long> locIdList,Long statusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, " +
						" model.govtOfficer.govtOfficerId, " +
						" model.alert.alertId " +
						" from " +
						" AlertAssignedOfficer model " +
						" where " +
						" model.alert.isDeleted = 'N' and" +
						" date(model.insertedTime) between :fromDate and :toDate and " +
						" model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and " +
						" model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) and " );
		if(locValue != null && locValue.longValue() == 2L){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.state.stateId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 3L){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.district.districtId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId in (:locIdList)");
		}
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alert.alertStatus.alertStatusId = :statusId ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		query.setParameterList("deptIdList", deptIdList);
		query.setParameterList("locIdList", locIdList);
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();
	}
	
	public List<Object[]> getTotalAlertGroupByStatusForAlertList(Set<Long> alertIdSet){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		queryStr.append(" ALTS.alert_status_id as alertStatusId, ");
		queryStr.append(" ALTS.alert_status as alertStatus, ");
		queryStr.append(" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert_assigned_officer AAO, alert_status ALTS, alert A ");
		queryStr.append(" where ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.alert_id = A.alert_id and ");
		queryStr.append(" A.is_deleted = 'N' and ");
		queryStr.append(" AAO.alert_id in (:alertIdSet) ");
		queryStr.append(" group by ");
		queryStr.append(" ALTS.alert_status_id ");
		queryStr.append(" order by ");
		queryStr.append(" ALTS.status_order; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
									.addScalar("alertStatusId", Hibernate.LONG)
									.addScalar("alertStatus", Hibernate.STRING)
									.addScalar("count", Hibernate.LONG);
		query.setParameterList("alertIdSet", alertIdSet);
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByDeptForAlertList(Set<Long> alertIdSet){
		StringBuilder queryStr = new StringBuilder();  
		queryStr.append(" select ");
		queryStr.append(" GD.govt_department_id as govtDepartmentId, ");
		queryStr.append(" GD.department_name as departmentName, ");
		queryStr.append(" count(distinct AAO.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation_officer GDDO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department GD, ");
		queryStr.append(" alert A ");
		queryStr.append(" where ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		//queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" AAO.alert_id = A.alert_id and ");
		queryStr.append(" A.is_deleted = 'N' and ");
		queryStr.append(" AAO.alert_id in (:alertIdSet) ");
		queryStr.append(" group by ");
		queryStr.append(" GD.govt_department_id; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
									.addScalar("govtDepartmentId", Hibernate.LONG)
									.addScalar("departmentName", Hibernate.STRING)
									.addScalar("count", Hibernate.LONG);
		query.setParameterList("alertIdSet", alertIdSet);
		//query.setParameterList("deptIdList", deptIdList);
		return query.list();
	}
	
	public List<Object[]> getSubOrdinateLevelWiseAlertsDetails(Long designationId,Long levelId,Long levelValue,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.alert.alertId, " +//0
			        " model.alert.createdTime, " +//1
			        " model.alert.updatedTime, " +//2
			        " model.alert.alertStatus.alertStatusId, " +//3
			        " model.alert.alertStatus.alertStatus, " +//4
			        " model.alert.alertCategory.alertCategoryId, " +//5
			        " model.alert.alertCategory.category as category, " +//6
			        " model.alert.alertImpactScope.alertImpactScopeId, " +//7
			        " model.alert.alertImpactScope.impactScope, " +//8
			        " model.alert.title, " +//9
			        " C.name, " +//10
			        " D.districtName, " +//11
			        " model.alert.alertSource.alertSourceId, " +//12
			        " model.alert.alertSource.source, " +//13
			        " 0, " +//14
			        " '', " +//15
			        " EDS.editionId, " +//16
			        " EDS.editionAlias, " +//17
			        " TNC.tvNewsChannelId, " +//18
			        " TNC.channelName"); //19
		
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W" +
					" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")" +
					" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId");
		if(fromDate != null && toDate != null)
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate)");
		
		if(levelId != null && levelId == 3l)
			sb.append(" and D.districtId = :levelValue");
		else if(levelId != null && levelId == 4l)
			sb.append(" and C.constituencyId = :levelValue");
		else if(levelId != null && levelId == 5l)
			sb.append(" and T.tehsilId = :levelValue");
		else if(levelId != null && levelId == 6l)
			sb.append(" and P.panchayatId = :levelValue");
		else if(levelId != null && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId = :levelValue");
		else if(levelId != null && levelId == 8l)
			sb.append(" and W.constituencyId = :levelValue");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("designationId", designationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelId > 2l)
			query.setParameter("levelValue", levelValue);
		
		return query.list();
	}
	
	public List<Object[]> getDesigAndStatusWiseAlertsCounts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
					" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName," +
					" model.alertStatus.alertStatusId," +
					" model.alertStatus.alertStatus," +
					" count(distinct model.alert.alertId)");
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId,model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getDepartmentAndDistrictWiseAlertsCounts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
					" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
					" D.districtId," +
					" D.districtName," +
					" count(distinct model.alert.alertId)");
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId,D.districtId");
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getStatusWiseTotalCountsForAlert(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.alertStatus.alertStatusId," +
					" model.alertStatus.alertStatus," +
					" count(distinct model.alert.alertId)," +
					" model.alert.alertCategoryId," +
					" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId");
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId,model.alert.alertCategoryId,model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getDesigAndStatusWiseAlertDetails(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,
			List<Long> printIdsList,List<Long> electronicIdsList,Long designationId,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.alert.alertId, " +//0
			        " model.alert.createdTime, " +//1
			        " model.alert.updatedTime, " +//2
			        " model.alert.alertStatus.alertStatusId, " +//3
			        " model.alert.alertStatus.alertStatus, " +//4
			        " model.alert.alertCategory.alertCategoryId, " +//5
			        " model.alert.alertCategory.category as category, " +//6
			        " model.alert.alertImpactScope.alertImpactScopeId, " +//7
			        " model.alert.alertImpactScope.impactScope, " +//8
			        " model.alert.title, " +//9
			        " C.name, " +//10
			        " D.districtName, " +//11
			        " model.alert.alertSource.alertSourceId, " +//12
			        " model.alert.alertSource.source, " +//13
			        " 0, " +//14
			        " '', " +//15
			        " EDS.editionId, " +//16
			        " EDS.editionAlias, " +//17
			        " TNC.tvNewsChannelId, " +//18
			        " TNC.channelName"); //19
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(designationId != null && designationId.longValue() > 0l)
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and model.alertStatus.alertStatusId = :statusId");
		
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		//sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId,model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(designationId != null && designationId.longValue() > 0l)
			query.setParameter("designationId", designationId);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getDistWiseTotalAlertsStatusForAlert(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" D.districtId," +
					" D.districtName," +
					" model.alertStatus.alertStatusId," +
					" model.alertStatus.alertStatus," +
					" count(distinct model.alert.alertId)");
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		sb.append(" group by D.districtId,model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getTotalAlertByStatusForDeptWiseClick(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,
			List<Long> printIdsList,List<Long> electronicIdsList,String typeStr,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.alert.alertId, " +//0
			        " model.alert.createdTime, " +//1
			        " model.alert.updatedTime, " +//2
			        " model.alert.alertStatus.alertStatusId, " +//3
			        " model.alert.alertStatus.alertStatus, " +//4
			        " model.alert.alertCategory.alertCategoryId, " +//5
			        " model.alert.alertCategory.category as category, " +//6
			        " model.alert.alertImpactScope.alertImpactScopeId, " +//7
			        " model.alert.alertImpactScope.impactScope, " +//8
			        " model.alert.title, " +//9
			        " C.name, " +//10
			        " D.districtName, " +//11
			        " model.alert.alertSource.alertSourceId, " +//12
			        " model.alert.alertSource.source, " +//13
			        " 0, " +//14
			        " '', " +//15
			        " EDS.editionId, " +//16
			        " EDS.editionAlias, " +//17
			        " TNC.tvNewsChannelId, " +//18
			        " TNC.channelName"); //19
		sb.append(" from AlertAssignedOfficer model" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and model.alertStatus.alertStatusId = :statusId");
		
		if(typeStr != null && typeStr.trim().equalsIgnoreCase("PMedia")){
			if(printIdsList != null && !printIdsList.isEmpty()){
				sb.append(" and EDS.newsPaperId in (:printIdsList)");
			}
		}else if(typeStr != null && !typeStr.trim().equalsIgnoreCase("electronic")){
			if(electronicIdsList !=null && electronicIdsList.size() > 0){
				sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");
			}
		}else if(typeStr != null && !typeStr.trim().equalsIgnoreCase("Totals")){
			if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
				sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
			else if(printIdsList != null && !printIdsList.isEmpty())
				sb.append(" and EDS.newsPaperId in (:printIdsList)");
			else if(electronicIdsList != null && !electronicIdsList.isEmpty())
				sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");
		}
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L)
				sb.append(" and S.stateId = 1");
			else if(stateId.longValue() == 36L)
				sb.append(" and S.stateId = 36");
			else if(stateId.longValue() == 0L)
				sb.append(" and S.stateId in (1,36)");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 2l)
			sb.append(" and S.stateId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 3l)
			sb.append(" and D.districtId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
			sb.append(" and C.constituencyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
			sb.append(" and T.tehsilId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
			sb.append(" and P.panchayatId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
			sb.append(" and LEB.localElectionBodyId in (:levelValues)");
		else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
			sb.append(" and W.constituencyId in (:levelValues)");
		
		//sb.append(" group by D.districtId,model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		if(typeStr != null && typeStr.trim().equalsIgnoreCase("PMedia")){
			if(printIdsList != null && !printIdsList.isEmpty()){
				query.setParameterList("printIdsList", printIdsList);
			}
		}else if(typeStr != null && !typeStr.trim().equalsIgnoreCase("electronic")){
			if(electronicIdsList !=null && electronicIdsList.size() > 0){
				query.setParameterList("electronicIdsList", electronicIdsList);
			}
		}else if(typeStr != null && !typeStr.trim().equalsIgnoreCase("Totals")){
			if(printIdsList != null && !printIdsList.isEmpty())
				query.setParameterList("printIdsList", printIdsList);
			if(electronicIdsList != null && !electronicIdsList.isEmpty())
				query.setParameterList("electronicIdsList", electronicIdsList);
		}
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
}
