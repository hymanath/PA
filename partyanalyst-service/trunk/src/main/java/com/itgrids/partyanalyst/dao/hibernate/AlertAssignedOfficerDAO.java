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
											" model.govtOfficer.officerName ," +
											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
											" model.govtOfficer.mobileNo," +
											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
											" from AlertAssignedOfficer model " +
											" where model.alert.alertId = :alertId" +
											" and model.isDeleted = 'N'" +
											" and model.isApproved = 'Y'");
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
	//balu
	public List<Object[]> getDepartmentWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long levelId, List<Long> levelValues, String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" GD.govt_department_id as govt_department_id "+
						" ,GD.department_name as department_name ");
		if(type != null && type.equalsIgnoreCase("status")){
			queryStr.append(" ,ALTS.alert_status_id as alert_status_id " +
							" ,ALTS.alert_status as alert_status ");
		}
		
		queryStr.append(" ,count(distinct AAO.alert_id) as count ");
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
		if(levelId != null && levelId.longValue() == 3L){
			queryStr.append("  ,district D ");
		}else if(levelId != null && levelId.longValue() == 4l){
			queryStr.append("  ,constituency C ");
		}else if(levelId != null && levelId.longValue() == 5l){
			queryStr.append("  ,tehsil T ");
		}else if(levelId != null && levelId.longValue() == 6l){
			queryStr.append("  ,panchayat P ");
		}else if(levelId != null && levelId.longValue() == 7l){
			queryStr.append("  ,local_election_body LEB ");
		}else if(levelId != null && levelId.longValue() == 8l){
			queryStr.append("  ,constituency CON ");
		}		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted = 'N' and ");      
		queryStr.append(" A.alert_category_id = ALTC.alert_category_id and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		queryStr.append(" A.alert_type_id = ALTT.alert_type_id and ");
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") and GDDO.address_id = UA.user_address_id and ");
		
		if(levelId != null && levelId.longValue() == 2L){
			queryStr.append(" S.state_id = UA.state_id and S.state_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 3L){
			queryStr.append(" D.district_id = UA.district_id and D.district_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 4l){
			queryStr.append(" C.constituency_id = UA.constituency_id and C.constituency_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 5l){
			queryStr.append(" T.tehsil_id = UA.tehsil_id and T.tehsil_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 6l){
			queryStr.append(" P.panchayat_id = UA.panchayat_id and P.panchayat_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 7l){
			queryStr.append(" LEB.local_election_body_id = UA.local_election_body and LEB.local_election_body_id in (:locIdList) and ");
		}else if(levelId != null && levelId.longValue() == 8l){
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
		queryStr.append(" AAO.is_deleted = 'N' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id  ");
		if(deptIdList != null && deptIdList.size() > 0){
			queryStr.append(" and GD.govt_department_id in (:deptIdList)  ");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and ( (EDS.newsPaperId in (:printIdList))  or (TNC.tvNewsChannelId in (:electronicIdList)) ) ");
		}else if(printIdList != null && !printIdList.isEmpty()){
			queryStr.append(" and EDS.newsPaperId in (:printIdList)");
		}else if(electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		}
		queryStr.append(" group by GD.govt_department_id " );
		if(type != null && type.equalsIgnoreCase("status")){
			queryStr.append(", ALTS.alert_status_id " );
		}
		
		queryStr.append(" order by GD.department_name; ");
		Query query = null;
		if(type != null && type.equalsIgnoreCase("status")){
			 query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("govt_department_id", Hibernate.LONG)
					.addScalar("department_name", Hibernate.STRING)
					.addScalar("alert_status_id", Hibernate.LONG)
					.addScalar("alert_status", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG);
		}else{
			 query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("govt_department_id", Hibernate.LONG)
					.addScalar("department_name", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG);
		}
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}	
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(levelValues != null && levelValues.size() > 0){
			query.setParameterList("locIdList", levelValues);
		}
		return query.list(); 
		
		/*StringBuilder sb = new StringBuilder();
		sb.append(" select govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
				  " govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName,");
		if(type != null && type.equalsIgnoreCase("status")){
			sb.append(" model.alert.alertStatus.alertStatusId ," +
					  " model.alert.alertStatus.alertStatus,");
		}
		
		sb.append(" count(distinct model.alert.alertId)  " +
				  " from AlertAssignedOfficer model  " +
				  " left join model.alert.edition EDS" +
				" left join model.alert.tvNewsChannel TNC " +
				" left join model.govtDepartmentDesignationOfficer govtDepartmentDesignationOfficer" +
				" left join govtDepartmentDesignationOfficer.userAddress UA" +
				  
				  " left join UA.state S " +
				  " left join UA.district D " +
				  " left join UA.constituency C " +
				  " left join UA.tehsil T " +
				  " left join UA.localElectionBody LEB " +
				  " left join UA.panchayat P " +
				  " left join UA.ward W ");
		
		sb.append(" where " +
				  " model.alert.isDeleted = 'N'  " +
				  " and model.isDeleted ='N' " +
				  " and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
		
		
		if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty()){
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
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate " );
		}
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" and ( (EDS.newsPaperId in (:printIdList))  or (TNC.tvNewsChannelId in (:electronicIdList)) ) ");
		}else if(printIdList != null && !printIdList.isEmpty()){
			sb.append(" and EDS.newsPaperId in (:printIdList)");
		}else if(electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		}
			
		
		if(deptIdList != null && !deptIdList.isEmpty()){
			sb.append(" and govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) ");
		}
		sb.append(" group by govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
		if(type != null && type.equalsIgnoreCase("status")){
			sb.append(" , model.alert.alertStatus.alertStatusId ");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(deptIdList != null && !deptIdList.isEmpty()){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues", levelValues);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}	
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		return query.list();*/  
	}
	public List<Object[]> getTotalAlertGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long locValue, List<Long> locIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" GD.govt_department_id as govt_department_id," +
						" GD.department_name as department_name," +
						" ALTS.alert_status_id as alert_status_id," +
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
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted = 'N' and ");      
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
		queryStr.append(" AAO.is_deleted = 'N' and ");
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		queryStr.append(" group by GD.govt_department_id ,ALTS.alert_status_id order by GD.department_name; ");
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
	//last
	public List<Object[]> getLocationWiseThenStatusWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long uILevelId){
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();  
		StringBuilder sbg = new StringBuilder();
		
		
		sb.append(" select ");		
		sbg.append(" group by  ");
		
		sbm.append(" from AlertAssignedOfficer model " +
				  " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
				  " left join model.alert.tvNewsChannel TNC " +
				  " left join model.alert.edition EDS "+
				  " left join UA.state S " +
				  " left join UA.district D " +
				  " left join UA.constituency C " +
				  " left join UA.tehsil T " +
				  " left join UA.localElectionBody LEB " +
				  " left join UA.panchayat P " +
				  " left join UA.ward W  ");
		
		sbe.append(" where " +
				  " model.alert.isDeleted = 'N'  " +
				  " and model.isDeleted ='N' " +
				  " and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
		
		if(uILevelId != null && uILevelId.longValue()>0l){
			if(uILevelId == 2l){
				sb.append("  S.stateId,S.stateName ");
				sbg.append(" S.stateId ");
				sbe.append(" and S.stateId is not null ");
			}else if(uILevelId == 3l){
				sb.append(" D.districtId,D.districtName ");
				sbg.append(" D.districtId ");
				sbe.append(" and D.districtId is not null ");
			}else if(uILevelId == 4l){
				sb.append(" C.constituencyId,C.name ");
				sbg.append(" C.constituencyId ");
				sbe.append(" and C.constituencyId is not null ");
			}else if(uILevelId == 5l){
				sb.append(" T.tehsilId,T.tehsilName ");
				sbg.append(" T.tehsilId ");
				sbe.append(" and T.tehsilId is not null ");
			}else if(uILevelId == 6l){
				sb.append(" P.panchayatId,P.panchayatName ");
				sbg.append(" P.panchayatId ");
				sbe.append(" and P.panchayatId is not null ");
			}else if(uILevelId == 7l){
				sb.append(" LEB.localElectionBodyId,LEB.name ");
				sbg.append(" LEB.localElectionBodyId ");
				sbe.append(" and LEB.localElectionBodyId is not null ");
			}else if(uILevelId == 8l){
				sb.append(" W.constituencyId,W.name ");
				sbg.append(" W.constituencyId ");
				sbe.append(" and W.constituencyId is not null ");
			}
		}
		
		sb.append( " ,model.alert.alertStatus.alertStatusId " +
				" ,model.alert.alertStatus.alertStatus " );		
		sb.append(" ,count(distinct model.alert.alertId)  " );
		
		if(stateId != null){
			if(stateId.longValue() == 1L){
				sbe.append(" and S.stateId = 1");
			}else if(stateId.longValue() == 36L){
				sbe.append(" and S.stateId = 36");
			}else if(stateId.longValue() == 0L){
				sbe.append(" and S.stateId in (1,36)");
			}			
		}
		
		if(fromDate != null && toDate != null){
			sbe.append(" and date(model.insertedTime) between :fromDate and :toDate " );
		}
		if(printIdList != null && printIdList.size()>0 && electronicIdList != null && electronicIdList.size()>0){
			sbe.append(" and ( (EDS.newsPaperId in (:printIdList))  or (TNC.tvNewsChannelId in (:electronicIdList)) ) ");
		}else if(printIdList != null && printIdList.size()>0){
			sbe.append(" and EDS.newsPaperId in (:printIdList)");
		}else if(electronicIdList != null && electronicIdList.size()>0){
			sbe.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		}
		
		if(deptIdList != null && deptIdList.size()>0l){
			sbe.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) ");
		}

		sbg.append(" ,model.alert.alertStatus.alertStatusId ");
		
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());	
		
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(deptIdList != null && deptIdList.size()>0){
			query.setParameterList("deptIdList", deptIdList);
		}

		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}	
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
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
	
	public List<Object[]> getStatusWiseAlertDetails(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,
			Long levelId,List<Long> levelValues,Long statusId,Long desigOffcId,Long govtOffcId){
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
					" from AlertAssignedOfficer model" +
					" left join model.govtDepartmentDesignationOfficer.userAddress UA");
		//if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" left join model.alert.tvNewsChannel TNC" +
						" left join model.alert.edition E");
	//	}
		sb.append(" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted ='N' " +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		if(desigOffcId != null && desigOffcId.longValue() > 0l)
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :desigOffcId");
		if(govtOffcId != null && govtOffcId.longValue() > 0l)
			sb.append(" and model.govtOfficer.govtOfficerId = :govtOffcId");
		
		//if(levelId != null && levelId > 0l)
			//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId");
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
		/*if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			sb.append(" and ((E.newsPaperId in (:printIdList)) or (TNC.tvNewsChannelId in (:electronicIdList)))");*/
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
		else if(printIdList != null && !printIdList.isEmpty())
			sb.append(" and EDS.newsPaperId in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		
		if(deptIdList != null && !deptIdList.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList)");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		/*if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			query.setParameterList("printIdList", printIdList);
			query.setParameterList("electronicIdList", electronicIdList);
		}*/
		if(deptIdList != null && !deptIdList.isEmpty())
			query.setParameterList("deptIdList", deptIdList);
		/*if(levelId != null && levelId > 0l)
			query.setParameter("levelId", levelId);*/
		
		if(desigOffcId != null && desigOffcId.longValue() > 0l)
			query.setParameter("desigOffcId", desigOffcId);
		if(govtOffcId != null && govtOffcId.longValue() > 0l)
			query.setParameter("govtOffcId", govtOffcId);
		
		if(levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		
		if(printIdList != null && printIdList.size() > 0)
			query.setParameterList("printIdList", printIdList);   
		if(electronicIdList != null && electronicIdList.size() > 0)
			query.setParameterList("electronicIdList", electronicIdList);
		
		return query.list();
	}
	
	public List<Object[]> getSubOrdinatesAlertDetails(Long designationId,Long levelId,Date fromDate,Date toDate,Long userLevelId,Set<Long> levelValues){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		if(levelId != null && levelId == 2l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.stateId,model.govtDepartmentDesignationOfficer.userAddress.state.stateName ,");
		else if(levelId != null && levelId == 3l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.district.districtId,model.govtDepartmentDesignationOfficer.userAddress.district.districtName,");
		else if(levelId != null && levelId == 4l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId,model.govtDepartmentDesignationOfficer.userAddress.constituency.name,");
		else if(levelId != null && levelId == 5l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId,model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilName,");
		else if(levelId != null && levelId == 6l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId,model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatName,");
		else if(levelId != null && levelId == 7l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId,model.govtDepartmentDesignationOfficer.userAddress.localElectionBody..name,");
		else if(levelId != null && levelId == 8l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId,model.govtDepartmentDesignationOfficer.userAddress.ward.name,");
		
		sb.append(" model.alertStatus.alertStatusId," +
					" model.alertStatus.alertStatus," +
					" count(model.alert.alertId)" +
					" from AlertAssignedOfficer model" +
					//" ,  model.govtDepartmentDesignationOfficer.userAddress UA " +
				/*	" left join UA.state S " +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W" +*/
					" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")" +
					" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId");
		if(fromDate != null && toDate != null)
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate)");
		
		if(userLevelId !=null && userLevelId.longValue()>0l && levelValues !=null && levelValues.size()>0){
				if(userLevelId.longValue() == 2L){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.stateId in (:levelValues) ");
				}else if(userLevelId.longValue() == 3L){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.district.districtId in (:levelValues) ");
				}else if(userLevelId.longValue() == 4l){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId in (:levelValues) ");
				}else if(userLevelId.longValue() == 5l){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId in (:levelValues) ");
				}else if(userLevelId.longValue() == 6l){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId in (:levelValues) ");
				}else if(userLevelId.longValue() == 7l){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId in (:levelValues) ");
				}else if(userLevelId.longValue() == 8l){
					sb.append(" and model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId in (:levelValues)");
				}
		}
		
		
		
		sb.append(" group by");
		if(levelId != null && levelId == 2l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.stateId, ");
		else if(levelId != null && levelId == 3l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.district.districtId,");
		else if(levelId != null && levelId == 4l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId,");
		else if(levelId != null && levelId == 5l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId,");
		else if(levelId != null && levelId == 6l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId,");
		else if(levelId != null && levelId == 7l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId,");
		else if(levelId != null && levelId == 8l)
			sb.append(" model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId,");
		sb.append(" model.alertStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("designationId", designationId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(userLevelId !=null && userLevelId.longValue()>0l && levelValues !=null && levelValues.size()>0){
			query.setParameterList("levelValues", levelValues);
		}
		
		return query.list();
	}
	
	public List<Long> getAlertAssignedOfficerIdsForAlertByUser(Long alertId,Long userId){
		Query query = getSession().createQuery(" select distinct model.alertAssignedOfficerId" +
												" from AlertAssignedOfficer model,GovtDepartmentDesignationOfficerDetails model1" +
												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = model1.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
												//" and model.govtOfficer.govtOfficerId = model1.govtOfficer.govtOfficerId" +
												" and model.alert.alertId = :alertId" +
												" and model1.user.userId = :userId" +
												" and model1.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(Date fromDate,Date toDate, List<Long> deptIdList, Long locValue, List<Long> locIdList,Long statusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, " +
						" model.govtOfficerId, " +
						" model.alert.alertId " +
						" from " +
						" AlertAssignedOfficer model " +
						" left join model.govtDepartmentDesignationOfficer.userAddress UA " +
						" left join UA.state S " +
						" left join UA.district D " +
						" left join UA.constituency C " +
						" left join UA.tehsil T " +
						" left join UA.localElectionBody LEB " +
						" left join UA.panchayat P " +
						" left join UA.ward W " +
						" where " +
						" model.alert.isDeleted = 'N' and model.isDeleted = 'N' " );
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(model.insertedTime) between :fromDate and :toDate  ");
		}
		queryStr.append(" and model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and " +
						" model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		if(deptIdList != null && deptIdList.size() > 0){
			queryStr.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList)  " );
		}
		
		if(locValue !=null && locIdList !=null && locIdList.size()>0){
			if(locValue.longValue() == 2L){
				queryStr.append(" and S.stateId in (:locIdList) ");
			}else if(locValue.longValue() == 3L){
				queryStr.append(" and D.districtId in (:locIdList) ");
			}else if(locValue.longValue() == 4l){
				queryStr.append(" and C.constituencyId in (:locIdList) ");
			}else if(locValue.longValue() == 5l){
				queryStr.append(" and T.tehsilId in (:locIdList) ");
			}else if(locValue.longValue() == 6l){
				queryStr.append(" and P.panchayatId in (:locIdList) ");
			}else if(locValue.longValue() == 7l){
				queryStr.append(" and LEB.localElectionBodyId in (:locIdList) ");
			}else if(locValue.longValue() == 8l){
				queryStr.append(" and W.constituencyId in (:locIdList)");
			}
		}
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(locValue !=null && locIdList !=null && locIdList.size()>0){
			query.setParameterList("locIdList", locIdList);
		}
		
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
		queryStr.append(" A.is_deleted = 'N' and AAO.is_deleted='N' ");
		if(alertIdSet != null && alertIdSet.size() > 0){
			queryStr.append(" and AAO.alert_id in (:alertIdSet) ");
		}
		
		queryStr.append(" group by ");
		queryStr.append(" GD.govt_department_id; ");
		Query query = getSession().createSQLQuery(queryStr.toString())
									.addScalar("govtDepartmentId", Hibernate.LONG)
									.addScalar("departmentName", Hibernate.STRING)
									.addScalar("count", Hibernate.LONG);
		if(alertIdSet != null && alertIdSet.size() > 0){
			query.setParameterList("alertIdSet", alertIdSet);
		}
		
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
					" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
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
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");*/
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		else if(printIdsList != null && !printIdsList.isEmpty())
			sb.append(" and EDS.newsPaperId in (:printIdsList)");
		else if(electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");
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
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}*/
		if(printIdsList != null && printIdsList.size() > 0)
			query.setParameterList("printIdsList", printIdsList);   
		if(electronicIdsList != null && electronicIdsList.size() > 0)
			query.setParameterList("electronicIdsList", electronicIdsList);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getDepartmentAndDistrictWiseAlertsCounts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
					" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " );
		
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" ,S.stateId " +
					" , S.stateName " );
		}else{
			sb.append(" ,D.districtId " +  
					" ,D.districtName " );
		}
		sb.append(" ,count(distinct model.alert.alertId)");
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
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
					" and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentLevelId =:levelId ");
		}
		/*else{
			sb.append(" and D.districtId is not null");   
		}*/
		
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");*/
		
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		/*else if(printIdsList != null && !printIdsList.isEmpty())
			sb.append(" and EDS.newsPaperId in (:printIdsList)");
		else if(electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");*/    
		
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
		
		
		sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId" );
		
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" ,S.stateId ");
		}else{
			sb.append(" ,D.districtId");
		}
		
		
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty())
			query.setParameterList("departmentIds", departmentIds);
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdsList", printIdsList);
			query.setParameterList("electronicIdsList", electronicIdsList);
		}*/
		if(printIdsList != null && printIdsList.size() > 0)
			query.setParameterList("printIdsList", printIdsList);   
		if(electronicIdsList != null && electronicIdsList.size() > 0)
			query.setParameterList("electronicIdsList", electronicIdsList);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			query.setParameter("levelId", levelId);
		}
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
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and " +
					" model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		else if(printIdsList != null && !printIdsList.isEmpty())
			sb.append(" and EDS.newsPaperId in (:printIdsList)");
		else if(electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");*/
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
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}*/
		if(printIdsList != null && printIdsList.size() > 0)
			query.setParameterList("printIdsList", printIdsList);   
		if(electronicIdsList != null && electronicIdsList.size() > 0)
			query.setParameterList("electronicIdsList", electronicIdsList);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	public List<Object[]> getAlertCountForCccAdminLongin(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type){
		StringBuilder sb = new StringBuilder();  
	    sb.append("select ");
	    sb.append(" model.alertStatus.alertStatusId," +
	          " model.alertStatus.alertStatus " );
	    
	      if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("department")){
	          sb.append(" ,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	              " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName ");
	      }
	      
	      sb.append(" ,count(distinct model.alert.alertId) ");
	      

	    sb.append(" from AlertAssignedOfficer model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
	          " left join UA.state S " +
	          " left join UA.district D" +
	          " left join UA.constituency C" +
	          " left join UA.tehsil T" +
	          " left join UA.localElectionBody LEB" +
	          " left join UA.panchayat P" +
	          " left join UA.ward W");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    
	    if(departmentIds != null && !departmentIds.isEmpty())
	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
	   /* }else if(printIdsList != null && !printIdsList.isEmpty()){
	      sb.append(" and  EDS.newsPaperId in (:printIdList) ");
	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
	      sb.append(" and TNC.tvNewsChannelId in (:electronicIdList) ");
	    }  */      
	      
	    if(fromDate != null && toDate != null)
	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
	    
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
	    
	    sb.append(" group by model.alertStatusId ");
	    
	    if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("department")){  
	      sb.append(" ,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    }
	        
	    Query query = getSession().createQuery(sb.toString());
	    if(departmentIds != null && !departmentIds.isEmpty())
	      query.setParameterList("departmentIds", departmentIds);
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }  
	    else if(printIdsList != null && !printIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
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
	public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues){
		StringBuilder sb = new StringBuilder();  
	    sb.append("select distinct model.alert.alertId ");
	    sb.append(" from AlertAssignedOfficer model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
	          " left join UA.state S " +
	          " left join UA.district D" +
	          " left join UA.constituency C" +
	          " left join UA.tehsil T" +
	          " left join UA.localElectionBody LEB" +
	          " left join UA.panchayat P" +
	          " left join UA.ward W");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and  " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    if(statusId != null && statusId.longValue() > 0L){
	    	sb.append(" and  model.alertStatus.alertStatusId = :statusId ");
	    }
	    if(departmentIds != null && departmentIds.size()>0){
	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    }
	      
	    
	    if(printIdsList != null && printIdsList.size()>0 && electronicIdsList != null && electronicIdsList.size()>0){
	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
	    }else if(printIdsList != null && printIdsList.size()>0){
	      sb.append(" and  EDS.newsPaperId in (:printIdList) ");
	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	      sb.append(" and TNC.tvNewsChannelId in (:electronicIdList) ");
	    }        
	      
	    if(fromDate != null && toDate != null){
	    	 sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
	    }
	     	    
	    if(levelId !=null && levelValues !=null && levelValues.size()>0){
	    	 if(levelId == 2l){
	    		 sb.append(" and S.stateId in (:levelValues)");
	    	 }else if(levelId == 3l){
	    		 sb.append(" and D.districtId in (:levelValues)");
	    	 }else if(levelId == 4l){
	    		 sb.append(" and C.constituencyId in (:levelValues)");
	    	 }else if( levelId == 5l){
	    		 sb.append(" and T.tehsilId in (:levelValues)");
	    	 }else if(levelId == 6l){
	    		 sb.append(" and P.panchayatId in (:levelValues)");	    		
	    	 }else if(levelId == 7l){
	    		 sb.append(" and LEB.localElectionBodyId in (:levelValues)");
	    	 }else if(levelId == 8l){
	    		 sb.append(" and W.constituencyId in (:levelValues)");
	    	 }
	   	      
	    }
	   
	    Query query = getSession().createQuery(sb.toString());
	    
	    if(departmentIds != null && departmentIds.size()>0){
	    	query.setParameterList("departmentIds", departmentIds);
	    }
	      
	    if(printIdsList != null && printIdsList.size()>0 && electronicIdsList != null && electronicIdsList.size()>0){
	      query.setParameterList("printIdList", printIdsList);
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }  
	    else if(printIdsList != null && printIdsList.size()>0){
	      query.setParameterList("printIdList", printIdsList);
	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }
	    if(fromDate != null && toDate != null){
	        query.setDate("fromDate", fromDate);
	        query.setDate("toDate", toDate);
	    }
	    if(levelId != null && levelValues != null && levelValues.size()>0){
	    	 query.setParameterList("levelValues", levelValues);
	    }	       
	    if(statusId != null && statusId.longValue() > 0L){
	    	query.setParameter("statusId",statusId);
	    }
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
					" left join model.alert.userAddress UA" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(designationId != null && designationId.longValue() > 0l)
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and model.alertStatus.alertStatusId = :statusId");
		
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");*/
		
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		else if(printIdsList != null && !printIdsList.isEmpty())
			sb.append(" and EDS.newsPaperId in (:printIdsList)");
		else if(electronicIdsList != null && !electronicIdsList.isEmpty())
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");
		
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
		
		/*if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			query.setParameterList("printIdList", printIdsList);
			query.setParameterList("electronicIdList", electronicIdsList);
		}*/
		
		if(printIdsList != null && printIdsList.size() > 0)
			query.setParameterList("printIdsList", printIdsList);   
		if(electronicIdsList != null && electronicIdsList.size() > 0)
			query.setParameterList("electronicIdsList", electronicIdsList);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
			
		return query.list();
	}
	
	public List<Object[]> getDistWiseTotalAlertsStatusForAlert(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList, String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct ");
		if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" S.stateId " +
					  " ,S.stateName");  
		}else{
			sb.append(" D.districtId" +
					  " , D.districtName");
		}
		
		sb.append(" ,model.alertStatus.alertStatusId " +
				  " ,model.alertStatus.alertStatus" +
				  " ,count(distinct model.alert.alertId)");
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
				  " and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentLevelId =:levelId ");
		}else{
			sb.append(" and D.districtId is not null");   
		}  
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
		if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" group by S.stateId ");
		}else{
			sb.append(" group by D.districtId ");
		}
		sb.append(" ,model.alertStatusId");
		
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
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			query.setParameter("levelId", levelId);
		}
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
					" left join model.alert.userAddress UA" +
					" left join model.alert.edition EDS" +
					" left join model.alert.tvNewsChannel TNC" +
					" left join UA.state S" +
					" left join UA.district D" +
					" left join UA.constituency C" +
					" left join UA.tehsil T" +
					" left join UA.localElectionBody LEB" +
					" left join UA.panchayat P" +
					" left join UA.ward W");
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+" ) " +
							" and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		if(departmentIds != null && !departmentIds.isEmpty())
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and model.alertStatus.alertStatusId = :statusId");
		
		if(typeStr != null && typeStr.trim().equalsIgnoreCase("PMedia")){
			if(printIdsList != null && !printIdsList.isEmpty()){
				sb.append(" and EDS.newsPaperId in (:printIdsList)");
			}
		}else if(typeStr != null && typeStr.trim().equalsIgnoreCase("electronic")){
			if(electronicIdsList !=null && electronicIdsList.size() > 0){
				sb.append(" and TNC.tvNewsChannelId in (:electronicIdsList)");
			}
		}else if(typeStr != null && typeStr.trim().equalsIgnoreCase("Totals")){
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
		}else if(typeStr != null && typeStr.trim().equalsIgnoreCase("electronic")){
			if(electronicIdsList !=null && electronicIdsList.size() > 0){
				query.setParameterList("electronicIdsList", electronicIdsList);
			}
		}else if(typeStr != null && typeStr.trim().equalsIgnoreCase("Totals")){
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
	public List<Long> getTotalAlertIdGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long levelId, List<Long> levelValues,Long statusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct AAO.alert_id as count");
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
		if(levelId != null && levelId.longValue() == 3L){
			queryStr.append("  ,district D ");
		}else if(levelId != null && levelId.longValue() == 4l){
			queryStr.append("  ,constituency C ");
		}else if(levelId != null && levelId.longValue() == 5l){
			queryStr.append("  ,tehsil T ");
		}else if(levelId != null && levelId.longValue() == 6l){
			queryStr.append("  ,panchayat P ");
		}else if(levelId != null && levelId.longValue() == 7l){
			queryStr.append("  ,local_election_body LEB ");
		}else if(levelId != null && levelId.longValue() == 8l){
			queryStr.append("  ,constituency CON ");
		}		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and  A.is_deleted = 'N' and ");
		queryStr.append(" A.alert_category_id = ALTC.alert_category_id and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		queryStr.append(" A.alert_type_id = ALTT.alert_type_id and ");
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") and GDDO.address_id = UA.user_address_id and ");
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" AAO.alert_status_id = :statusId and  ");
		}
		
		if(levelId != null && levelId.longValue() == 2L){
			queryStr.append(" S.state_id = UA.state_id and S.state_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 3L){
			queryStr.append(" D.district_id = UA.district_id and D.district_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 4l){
			queryStr.append(" C.constituency_id = UA.constituency_id and C.constituency_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 5l){
			queryStr.append(" T.tehsil_id = UA.tehsil_id and T.tehsil_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 6l){
			queryStr.append(" P.panchayat_id = UA.panchayat_id and P.panchayat_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 7l){
			queryStr.append(" LEB.local_election_body_id = UA.local_election_body and LEB.local_election_body_id in (:levelValues) and ");
		}else if(levelId != null && levelId.longValue() == 8l){
			queryStr.append(" CON.constituency_id = UA.ward and CON.constituency_id in (:levelValues) and ");
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
		queryStr.append(" AAO.alert_status_id = ALTS.alert_status_id and AAO.is_deleted = 'N' and ");
		queryStr.append(" AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id and ");
		queryStr.append(" GDDO.govt_department_designation_id = GDD.govt_department_designation_id and ");
		queryStr.append(" GDD.govt_department_id = GD.govt_department_id and ");
		queryStr.append(" GD.govt_department_id in (:deptIdList) and ");
		queryStr.append(" date(AAO.inserted_time) between :fromDate and :toDate ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString())
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
		if(levelValues != null && levelValues.size() > 0){
			query.setParameterList("levelValues", levelValues);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId", statusId);
		}
		return query.list(); 
		/*StringBuilder sb = new StringBuilder();
		sb.append(" select distinct " +
				  " model.alert.alertId  " +
				  " from AlertAssignedOfficer model " +
				  " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
				  " left join model.alert.tvNewsChannel TNC " +
				  " left join model.alert.edition EDS "+
				  " left join UA.state S " +
				  " left join UA.district D " +
				  " left join UA.constituency C " +
				  " left join UA.tehsil T " +
				  " left join UA.localElectionBody LEB " +
				  " left join UA.panchayat P " +
				  " left join UA.ward W ");
		
		sb.append(" where " +
				  " model.alert.isDeleted = 'N'  " +
				  " and model.isDeleted ='N' " +
				  " and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
		if(statusId != null && statusId.longValue() > 0L){
			sb.append(" and model.alert.alertStatus.alertStatusId = :statusId ");
		}
		
		
		if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty()){
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
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate " );
		}
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" and ( (EDS.newsPaperId in (:printIdList))  or (TNC.tvNewsChannelId in (:electronicIdList)) ) ");
		}else if(printIdList != null && !printIdList.isEmpty()){
			sb.append(" and EDS.newsPaperId in (:printIdList)");
		}else if(electronicIdList != null && !electronicIdList.isEmpty()){
			sb.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		}
		if(deptIdList != null && !deptIdList.isEmpty()){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(deptIdList != null && !deptIdList.isEmpty()){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues", levelValues);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}	
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();*/
		
	}
	public List<Long> getLocationWiseThenStatusWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long uILevelId,Long locId,Long statusId){
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		
		
		sb.append(" select distinct model.alert.alertId  " );	
		
		sbm.append(" from AlertAssignedOfficer model " +
				  " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
				  " left join model.alert.tvNewsChannel TNC " +
				  " left join model.alert.edition EDS "+
				  " left join UA.state S " +
				  " left join UA.district D " +
				  " left join UA.constituency C " +
				  " left join UA.tehsil T " +
				  " left join UA.localElectionBody LEB " +
				  " left join UA.panchayat P " +
				  " left join UA.ward W  ");
		
		sbe.append(" where " +
				  " model.alert.isDeleted = 'N'  " +
				  " and model.isDeleted ='N' " +
				  " and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
		
		if(uILevelId != null && uILevelId.longValue()>0l){
			if(uILevelId == 2l){
				sbe.append(" and S.stateId =:locId  ");
			}else if(uILevelId == 3l){
				sbe.append(" and D.districtId=:locId  ");
			}else if(uILevelId == 4l){
				sbe.append(" and C.constituencyId=:locId ");
			}else if(uILevelId == 5l){
				sbe.append(" and T.tehsilId=:locId ");
			}else if(uILevelId == 6l){
				sbe.append(" and P.panchayatId=:locId ");
			}else if(uILevelId == 7l){
				sbe.append(" and LEB.localElectionBodyId=:locId ");
			}else if(uILevelId == 8l){
				sbe.append(" and W.constituencyId=:locId ");
			}
		}
		
		if(statusId !=null && statusId.longValue()>0l){
			sbe.append( " and model.alert.alertStatus.alertStatusId=:statusId " );
		}
		
		if(stateId != null){
			if(stateId.longValue() == 1L){
				sbe.append(" and S.stateId = 1");
			}else if(stateId.longValue() == 36L){
				sbe.append(" and S.stateId = 36");
			}else if(stateId.longValue() == 0L){
				sbe.append(" and S.stateId in (1,36)");
			}			
		}
		
		if(fromDate != null && toDate != null){
			sbe.append(" and date(model.insertedTime) between :fromDate and :toDate " );
		}
		if(printIdList != null && printIdList.size()>0 && electronicIdList != null && electronicIdList.size()>0){
			sbe.append(" and ( (EDS.newsPaperId in (:printIdList))  or (TNC.tvNewsChannelId in (:electronicIdList)) ) ");
		}else if(printIdList != null && printIdList.size()>0){
			sbe.append(" and EDS.newsPaperId in (:printIdList)");
		}else if(electronicIdList != null && electronicIdList.size()>0){
			sbe.append(" and TNC.tvNewsChannelId in (:electronicIdList)");
		}
		
		if(deptIdList != null && deptIdList.size()>0l){
			sbe.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) ");
		}

		
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());	
		
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(deptIdList != null && deptIdList.size()>0){
			query.setParameterList("deptIdList", deptIdList);
		}

		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}	
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		if(statusId != null && statusId.longValue() > 0l){
			query.setParameter("statusId", statusId);
		}
		
		if(locId !=null && locId.longValue()>0l){
			query.setParameter("locId", locId);
		}
		
		return query.list();
		
		/*
	
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct AAO.alert_id as alertId ");
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and ALTS.alert_status_id = :statusId ");
		}
		if(levelValue != null && levelValue.longValue() == 2L){
			queryStr.append(" and S.state_id = UA.state_id and S.state_id = :locId and");
		}else if(levelValue != null && levelValue.longValue() == 3L){  
			queryStr.append(" and D.district_id = UA.district_id and  D.district_id = :locId and ");
		}else if(levelValue != null && levelValue.longValue() == 4l){
			queryStr.append(" and C.constituency_id = UA.constituency_id and C.constituency_id = :locId and ");
		}else if(levelValue != null && levelValue.longValue() == 5l){
			queryStr.append(" and T.tehsil_id = UA.tehsil_id and T.tehsil_id = :locId and ");
		}else if(levelValue != null && levelValue.longValue() == 6l){
			queryStr.append(" and P.panchayat_id = UA.panchayat_id and P.panchayat_id = :locId and ");
		}else if(levelValue != null && levelValue.longValue() == 7l){
			queryStr.append(" and LEB.local_election_body_id = UA.local_election_body and LEB.local_election_body_id = :locId and ");
		}else if(levelValue != null && levelValue.longValue() == 8l){
			queryStr.append(" and CON.constituency_id = UA.ward and CON.constituency_id = :locId and ");
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
		
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alertId", Hibernate.LONG);
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
		query.setParameter("locId",locId);
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId", statusId);
		}
		return query.list();
	*/}
	
	public List<Long> getDepartmentAndDistrictWiseAlertsCountsAlerts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList
			,String type,Long statusId,int startIndex,int endIndex){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct");
		
		sb.append(" model.alert.alertId ");
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
		sb.append(" where model.alert.isDeleted = 'N' and model.isDeleted = 'N'" +
					" and model.alert.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " +
					" and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentLevelId =:levelId ");
		}
		if(departmentIds != null && !departmentIds.isEmpty()){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
		}
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
			sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) )");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
		}
		
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
		
		if(statusId !=null && statusId.longValue()>0l){
			sb.append(" and model.alertStatusId =:statusId ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(departmentIds != null && !departmentIds.isEmpty()){
			query.setParameterList("departmentIds", departmentIds);
		}
		if(printIdsList != null && printIdsList.size() > 0){
			query.setParameterList("printIdsList", printIdsList);
		}   
		if(electronicIdsList != null && electronicIdsList.size() > 0){
			query.setParameterList("electronicIdsList", electronicIdsList);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues", levelValues);
		}
		if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("state")){
			query.setParameter("levelId", levelId);
		}
		if(statusId !=null && statusId.longValue()>0l){
			query.setParameter("statusId", statusId);
		}
		
		if(endIndex !=0){
			query.setFirstResult(startIndex);
			query.setMaxResults(endIndex);
		}
		
		return query.list();
	}
	
}
