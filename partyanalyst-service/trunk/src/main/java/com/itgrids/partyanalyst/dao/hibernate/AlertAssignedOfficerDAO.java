package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficer;

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
	public List<Object[]> getDepartmentWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList){
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
		
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList){
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
		queryStr.append(" ,govt_department_designation_officer GDDO ");
		queryStr.append(" left outer join user_address UA on UA.user_address_id = GDDO.address_id ");
		queryStr.append(" left outer join state S on S.state_id = UA.state_id ");
		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");
		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");
		queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");
		queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");
		queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");
		queryStr.append(" left outer join constituency CON on CON.constituency_id = UA.ward, ");
		queryStr.append(" alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer AAO, ");
		queryStr.append(" govt_department_designation GDD, ");
		queryStr.append(" govt_department_level GDL, ");
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
		queryStr.append(" GDDO.govt_department_level_id = GDL.govt_department_level_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptId) and ");
		queryStr.append(" GDL.govt_department_level_id = :levelValue and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
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
}
