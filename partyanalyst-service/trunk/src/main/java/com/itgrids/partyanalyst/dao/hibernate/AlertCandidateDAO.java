package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertCandidateDAO extends
		GenericDaoHibernate<AlertCandidate, Long> implements IAlertCandidateDAO {
	public AlertCandidateDAO() {
		super(AlertCandidate.class);
	}
	public List<Object[]> getAlertCandidateCount(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadreId),model.alert.alertId" +
				" from AlertCandidate model where model.alert.isDeleted ='N' ");
		if(alertIds != null && alertIds.size() > 0)
			str.append(" and model.alert.alertId in(:alertIds)");
		str.append(" group by model.alert.alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		return query.list();
	}
	
	
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> sourceIds,Date fromDate,Date toDate,Long levelId,Long statusId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,model.alertSource.source,model.alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" where model.isDeleted ='N' and model.impactLevelId=:levelId");
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate");
		if(statusId != null && statusId > 0)
			str.append(" and model.alertStatus.alertStatusId = :statusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(statusId != null && statusId > 0)
			query.setParameter("statusId", statusId);
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	
	public List<Object[]> getAlertCandidatesData(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(",model.alertImpact.alertImpactId,model.alertImpact.impact");
		str.append(" ,model.tdpCadre.image");
		str.append(" ,model.tdpCadre.mobileNo," +
				" model.tdpCadre.memberShipNo ");
		str.append(" from AlertCandidate model left join model.tdpCadre.userAddress.panchayat panc ");
		str.append(" left join model.tdpCadre.userAddress.tehsil tehsil ");
		str.append(" left join model.tdpCadre.userAddress.constituency constituency ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.tdpCadre.userAddress.district district ");
		str.append(" left join model.tdpCadre.userAddress.state state ");
		str.append(" left join model.tdpCadre.userAddress.ward ward ");
	
		str.append(" where model.alert.isDeleted ='N' and model.alert.alertId in(:alertIds) and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear");
		str.append(" group by model.alertId,model.tdpCadre.tdpCadreId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidates(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" ,model.tdpCadre.image,model.tdpCadre.mobileNo");
		str.append(" from AlertAssigned model left join model.tdpCadre.userAddress.panchayat panc ");
		str.append(" left join model.tdpCadre.userAddress.tehsil tehsil ");
		str.append(" left join model.tdpCadre.userAddress.constituency constituency ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.tdpCadre.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.tdpCadre.userAddress.district district ");
		str.append(" left join model.tdpCadre.userAddress.state state ");
		str.append(" left join model.tdpCadre.userAddress.ward ward ");
	
		str.append(" where model.alert.isDeleted ='N' and model.isDeleted ='N' and model.alert.alertId in(:alertIds) and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear");
		str.append(" group by model.alertId,model.tdpCadre.tdpCadreId order by model.tdpCadre.firstname ");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	
	public List<Object[]> getInvolvedCandidateDetailsOfAlert(List<Long> alertIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.alertId,model.newsCandidateId,model.newsCandidate,model.designation,model.organization," +
				" model.alertImpact.alertImpactId,model.alertImpact.impact,model.candidateId,tdpCadre.memberShipNo,tdpCadre.image,tdpCadre.mobileNo " +
				" FROM AlertCandidate model " +
				" left join model.tdpCadre tdpCadre " +
				" WHERE model.alert.isDeleted ='N' " +
				" AND model.alertId in (:alertIds) " +
				" GROUP BY  model.newsCandidateId " +
				" ORDER BY model.newsCandidate ");
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameterList("alertIds", alertIds);		
		
		return query.list();
	}
	public List<Object[]> getAlertNewsCandidateCount(List<Long> alertIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.newsCandidateId),model.alert.alertId" +
				" from AlertCandidate model where model.alert.isDeleted ='N' ");
		if(alertIds != null && alertIds.size() > 0)
			str.append(" and model.alert.alertId in(:alertIds)");
		str.append(" group by model.alert.alertId");
		Query query = getSession().createQuery(str.toString());
		if(alertIds != null && alertIds.size() > 0)
		query.setParameterList("alertIds", alertIds);
		return query.list();
	}
	public List<Long> getAlertCandidatesForUpdate(Long alertId){
	
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.alertCandidateId from AlertCandidate model " +
				" where " +
				" model.alertId=:alertId " +
				" and model.alert.isDeleted='N' ");		
		Query query = getSession().createQuery(str.toString());		
		
		query.setParameter("alertId", alertId);
		
		return query.list();
	}
	public int deleteAlertCandidatesForUpdate(List<Long> alertCandidateIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append("  delete from AlertCandidate model " +
				" where " +
				" model.alertCandidateId in (:alertCandidateIds) ");
	
		Query query = getSession().createQuery(str.toString());
		
		query.setParameterList("alertCandidateIds", alertCandidateIds);
		
		return query.executeUpdate();
	}
	
	
	/*
	 * Author 	: 	Srishailam Pittala
	 * Date 	:	29th Dec,2016
	 * Description : to get tdpCadre Wise assigned alert details
	 * */
	
	public List<Object[]> getTdpCadreWiseInvoledAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertCategoryId,model.alertTypeId,model.alertStatusId,count(distinct model.alertId) from Alert model,AlertCandidate model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and  " +
				" model1.tdpCadreId = :tdpCadreId  ");
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			queryStr.append(" and model.alertTypeId =:alertTypeId");
		
		if(fromDate != null && toDate != null ){
			queryStr.append(" and ( date(model.createdTime) between :fromDate and :toDate) ");
		}
		queryStr.append("  group by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		queryStr.append("  order by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			query.setParameter("alertTypeId", alertTypeId);
		if(fromDate != null && toDate != null ){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	
	/*
	 * Author 	: 	Srishailam Pittala
	 * Date 	:	30th Dec,2016
	 * Description : to get tdpCadre Wise assigned alert details
	 * */
	
public List<Object[]> getCandidateAlertDetailsBySearch(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId,Long categoryId,Long statusId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertId,model.description,date(model.createdTime),date(model.updatedTime), " +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus,model.alertImpactScope.alertImpactScopeId,model.alertImpactScope.impactScope   " +
				" from Alert model,AlertCandidate model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and  " +
				" model1.tdpCadreId = :tdpCadreId  ");
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			queryStr.append(" and model.alertTypeId =:alertTypeId");
		if(categoryId != null && categoryId.longValue()>0L)
			queryStr.append(" and model.alertCategoryId =:categoryId ");
		if(statusId != null && statusId.longValue()>0L)
			queryStr.append(" and model.alertStatusId =:statusId ");
		
		if(fromDate != null && toDate != null ){
			queryStr.append(" and ( date(model.createdTime) between :fromDate and :toDate) ");
		}
		queryStr.append("  order by model.alertCategoryId,model.alertTypeId,model.alertStatusId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		
		if(alertTypeId != null && alertTypeId.longValue()>0L)
			query.setParameter("alertTypeId", alertTypeId);
		if(categoryId != null && categoryId.longValue()>0L)
			query.setParameter("categoryId", categoryId);
		if(statusId != null && statusId.longValue()>0L)
			query.setParameter("statusId", statusId);
		
		if(fromDate != null && toDate != null ){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}

	public List<Object[]> getDeptWiseStatusWiseAlerts(Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.newsOrganizationId," +
						" model.organization," +
						" model.alert.alertStatus.alertStatusId," +
						" model.alert.alertStatus.alertStatus," +
						" count(distinct model.alert.alertId)" +
						" from AlertCandidate model" +
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" where model.alert.isDeleted = 'N'" +
						" and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")" +
						" and model.isDepartment = 'Y'");
		if(fromDate != null && toDate != null){ 
			sb.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				sb.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				sb.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				sb.append(" and state.stateId in (1,36) ");
			}
		}
		sb.append(" group by model.alert.alertStatus.alertStatusId,model.newsOrganizationId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);   
		}
		
		return query.list();
	}
	
	public List<Object[]> getDeptWiseStatusWiseAlertDetails(Date fromDate,Date toDate,Long stateId,Long deptId,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");     
		sb.append(" model.alert.alertId, " +//0
						" model.alert.createdTime, " +//1
						" model.alert.updatedTime, " +//2
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" model.alert.title, " +//9
						" constituency.name, " +//10
						" district.districtName," +//11
						" alertSource.alertSourceId, " +//12
						" alertSource.source," +//13
						" editionType.editionTypeId, " +//14
						" editionType.editionType, " +//15
						" edition.editionId, " +//16
						" edition.editionAlias, " +//17
						" tvNewsChannel.tvNewsChannelId, " +//18
						" tvNewsChannel.channelName ");//19
		sb.append(" from AlertCandidate model" +
						" left join model.alert.alertSource alertSource " +
		        		" left join model.alert.editionType editionType " +
		        		" left join model.alert.edition edition " +
		        		" left join model.alert.tvNewsChannel tvNewsChannel "+
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");  
		sb.append(" left join model.alert.alertCategory alertCategory ");
		sb.append(" left join model.alert.alertStatus alertStatus ");
		sb.append(" left join model.alert.alertImpactScope alertImpactScope ");  
		sb.append(" left join model.alert.alertType alertType ");
		sb.append(" left join userAddress.parliamentConstituency parliamentConstituency");
		
		sb.append(" where model.alert.isDeleted = 'N'" +
						" and alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")" +
						" and model.isDepartment = 'Y'");
		if(fromDate != null && toDate != null){ 
			sb.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				sb.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				sb.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				sb.append(" and state.stateId in (1,36) ");
			}
		}
		if(deptId != null && deptId.longValue() > 0l)
			sb.append(" and model.newsOrganizationId = :deptId");
		if(statusId != null && statusId.longValue() > 0l)
			sb.append(" and alertStatus.alertStatusId = :statusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);   
		}
		if(deptId != null && deptId.longValue() > 0l)
			query.setParameter("deptId", deptId);
		if(statusId != null && statusId.longValue() > 0l)
			query.setParameter("statusId", statusId);
		
		return query.list();
	}
	public List<Object[]> getInvolveCandidateList(Long alertId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertCandidate.tdpCadre.tdpCadreId, " +
						" alertCandidate.tdpCadre.firstname, " +
						" alertCandidate.tdpCadre.mobileNo, " +
						" constituency.name, " +
						" alertCandidate.alertImpact.alertImpactId," +
						" alertCandidate.tdpCadre.image " +  
						" from " +
						" AlertCandidate alertCandidate " +
						" left join alertCandidate.tdpCadre.userAddress userAddress " +
				     	" left join userAddress.constituency constituency " +
						" where" +
						" alertCandidate.alert.alertId = :alertId and" +
						" constituency.electionScope = 2 and " +
						" constituency.deformDate is null ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<Object[]> getAlertAssignedCandidatesForCentralMembers(Long tdpCadreId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.firstname");
		str.append(" from AlertAssigned model");
		str.append(" where model.alert.isDeleted ='N' and model.isDeleted ='N' and model.tdpCadre.tdpCadreId = :tdpCadreId" +
					" and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear");
		str.append(" order by model.tdpCadre.firstname ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	public List<Long> getTdpCadreIdsByAlertId(Long alertId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tdpCadreId from AlertCandidate model where model.alertId =:alertId");
		
		Query qry = getSession().createQuery(sb.toString());
		
		if(alertId != null && alertId.longValue() > 0l){
			qry.setParameter("alertId", alertId);
		}
		return qry.list();
	}
	
	
public int deleteAlertCandidatesExistingtdpCadreIds(Long tdpCadreIds,Long alertId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("  delete from AlertCandidate model " +
				" where " +
				" model.tdpCadreId = :tdpCadreIds and model.alertId =:alertId ");
	
		Query query = getSession().createQuery(str.toString());
		
		if(tdpCadreIds != null && tdpCadreIds.longValue()>0l){
		query.setParameter("tdpCadreIds", tdpCadreIds);
		  }
		if(alertId != null && alertId.longValue()>0l){
			query.setParameter("alertId", alertId);
		}
		return query.executeUpdate();  
	}

public List<Object[]> getInvolvedMembersInAlert(Long alertId){
	Query query = getSession().createQuery("select model.newsOrganizationId," +
										" model.organization," +
										" tdpCadre.tdpCadreId," +
										" tdpCadre.firstname," +
										" tdpCadre.memberShipNo," +
										" tdpCadre.mobileNo," +
										" tdpCadre.image," +
										" model.alertImpact.alertImpactId," +
										" model.alertImpact.impact" +
										" from AlertCandidate model" +
										" left join model.tdpCadre tdpCadre" +
										" where model.alert.alertId = :alertId" +
										" and model.alert.alertTypeId = :alertTypeId" +
										" and model.isDepartment = 'Y'");
	query.setParameter("alertId", alertId);
	query.setParameter("alertTypeId", IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID);
	return query.list();
}    
public List<Object[]> getAlertInvolvedCandidate(List<Long> alertIdList,Long stateId,Long alertTypeId,Date fromDate,Date toDate)
{
	StringBuilder str = new StringBuilder();
	str.append("select " +
			   " model.tdpCadre.tdpCadreId, " +
			   " model.tdpCadre.firstname, " +
			   " count(distinct model.alert.alertId) "+
			   " from " +
			   " AlertCandidate model " +
			   " left join model.alert.userAddress.state state " +
			   " where " +
			   " model.alert.isDeleted ='N' and " +
			   " model.tdpCadre.isDeleted = 'N' and " +
			   " model.alert.alertType.alertTypeId = :alertTypeId and " +
			   " date(model.alert.createdTime) between :fromDate and :toDate and ");
	if(alertIdList != null && alertIdList.size() > 0)
		str.append("  model.alert.alertId in (:alertIdList)  and ");
	if(stateId != null && stateId.longValue() > 0L){
		if(stateId.longValue() == 1L){
			str.append(" state.stateId = 1 ");
		}else if(stateId.longValue() == 36L){
			str.append(" state.stateId = 36 ");
		}else{
			str.append(" state.stateId in (1,36) ");
		}
	}
	Query query = getSession().createQuery(str.toString() +" group by model.tdpCadre.tdpCadreId order by model.tdpCadre.firstname ");
	if(alertIdList != null && alertIdList.size() > 0){
		query.setParameterList("alertIdList", alertIdList);
	}
	query.setParameter("alertTypeId", alertTypeId); 
	query.setDate("fromDate", fromDate); 
	query.setDate("toDate", toDate);   
	return query.list();
}

public List<String> getCategoryListForAlertAndDepartment(Long alertId,Long cnpDeptId){
	Query query = getSession().createQuery("select model.category" +
											" from AlertCandidate model" +
											" where model.alert.alertId = :alertId" +
											" and model.newsOrganizationId = :cnpDeptId" +
											" and model.isDepartment = 'Y'" +
											" and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")" +
											" and model.alert.isDeleted = 'N'" +
											" order by model.alertCandidateId desc");
	query.setParameter("alertId", alertId);
	query.setParameter("cnpDeptId", cnpDeptId);
	return query.list();
}
	
	public List<Object[]> getInvolvedAlertsSummary(List<Long> cadreIds){
		Query query = getSession().createQuery(" select model.tdpCadreId,count(distinct model.alertId) " +
				" from AlertCandidate model " +
				" where model.alert.isDeleted = 'N' " +
				" and model.tdpCadreId in (:cadreIds) ");
		
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getInvolvedMemberAlertDetails(Date fromDate,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
 		StringBuilder queryStr = new StringBuilder();
 			queryStr.append(" SELECT model.alert.alertType.alertTypeId,model.alert.alertType.alertType,count(distinct model.alert.alertId) " +
 				" FROM AlertCandidate model " +
 				" WHERE model.alert.isDeleted ='N' " +
 				" and model.alert.alertTypeId  in (:alertTypeIds) ");
 		
 		if(fromDate != null && toDate != null){
			queryStr.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");  
		}
 		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
 	        if(locationTypeId == 4l){
 	        	queryStr.append(" and model.alert.userAddress.constituency.constituencyId in(:locationValues) ");
 	        }else if(locationTypeId == 3l){
 	        	queryStr.append(" and model.alert.userAddress.district.districtId in(:locationValues) ");
 	        }else if(locationTypeId == 5l){
 	        	queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in(:locationValues) ");
 	        }else if(locationTypeId == 6l){
 	        	queryStr.append(" and model.alert.userAddress.panchayat.panchayatId in(:locationValues) ");
 	        }else if(locationTypeId == 7l){
 	        	queryStr.append(" and model.alert.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
 	        }else if(locationTypeId == 8l){
 	        	queryStr.append(" and model.alert.userAddress.ward.constituencyId in(:locationValues) ");
 	        }
 	    }
 		if(year != null && !year.trim().isEmpty()){
 	    	queryStr.append(" and year(model.createdTime) =:year ");   
 	    }
 		queryStr.append(" group by  model.alert.alertType.alertTypeId ");
 		Query query = getSession().createQuery(queryStr.toString());
 			query.setParameterList("alertTypeIds", alertTypeIds);
		 if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		 }
		 if(year !=null && !year.trim().isEmpty()){
			 query.setParameter("year", Integer.parseInt(year));
	 	 }
		 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
 	        if(locationTypeId == 4l || locationTypeId == 8l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 3l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 5l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 6l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 7l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }
	 	 }
 		return  query.list();
 	}
	
	public List<Object[]> getDesignationWiseInvolvedAlertys(Date fromDate ,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select  model.alert.alertStatus.alertStatusId,model.alert.alertStatus.alertStatus" +
		  				  ",model.alert.alertStatus.color," +
		  				  " model1.publicRepresentativeType.publicRepresentativeTypeId,model1.publicRepresentativeType.type" +
		  				  " ,count(distinct model.alert.alertId )" +
		  				  " from AlertCandidate model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  				  " where  " +
		  				  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  				  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N'   ");
		
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		
		 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 	    	if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 4l){
		        	queryStr.append(" and model.alert.userAddress.constituency.constituencyId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 3l){
		        	queryStr.append(" and model.alert.userAddress.district.districtId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 10l){
		        	queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 5l){
		        	queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 6l){
		        	queryStr.append(" and model.alert.userAddress.panchayat.panchayatId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 7l){
	 	        	queryStr.append(" and model.alert.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.alert.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 2l){
	 	        	queryStr.append(" and model.alert.userAddress.state.stateId in(:locationValues) ");
	 	        }
	 	    }
        if(alertTypeIds != null && alertTypeIds.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeIds) ");
		}
		
        queryStr.append(" group by model1.publicRepresentativeType.publicRepresentativeTypeId,model.alert.alertStatus.alertStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		}
		
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			query.setParameterList("alertTypeIds", alertTypeIds);  
		}
		
		return query.list();
	}
	public List<Object[]> getDesignationWiseInvolvedAlerts(Date fromDate,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type,List<Long> statusIdsList,Long designationId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");     
		queryStr.append(" model.alert.alertId, " +//0
						" model.alert.createdTime, " +//1
						" model.alert.updatedTime, " +//2
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" model.alert.title, " +//9
						" constituency.name, " +//10
						" district.districtName," +//11
						" alertSource.alertSourceId, " +//12
						" alertSource.source," +//13
						" editionType.editionTypeId, " +//14
						" editionType.editionType, " +//15
						" edition.editionId, " +//16
						" edition.editionAlias, " +//17
						" tvNewsChannel.tvNewsChannelId, " +//18
						" tvNewsChannel.channelName," +//19
						" state.stateName, " +//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" alertSeverity.severityColor, " +//24
						" alertStatus.color ");//25
		queryStr.append(" from AlertCandidate model,PublicRepresentative model1,TdpCadreCandidate model2 " +
						" left join model.alert.alertSource alertSource " +
		        		" left join model.alert.editionType editionType " +
		        		" left join model.alert.edition edition " +
		        		" left join model.alert.alertSeverity alertSeverity " +   
		        		" left join model.alert.tvNewsChannel tvNewsChannel "+
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");  
		queryStr.append(" left join model.alert.alertCategory alertCategory ");
		queryStr.append(" left join model.alert.alertStatus alertStatus ");
		queryStr.append(" left join model.alert.alertImpactScope alertImpactScope ");  
		queryStr.append(" left join model.alert.alertType alertType ");
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency");
		queryStr.append(" where  model2.candidate.candidateId=model1.candidate.candidateId " +
		  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId and model.alert.isDeleted ='N' " );
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");
		}     
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			queryStr.append(" and alertType.alertTypeId in (:alertTypeIds)");
		}
		if(statusIdsList != null && statusIdsList.size() > 0L){
			queryStr.append(" and alertStatus.alertStatusId in (:statusIdsList) ");
		}
		if(type != null && type.equalsIgnoreCase("candidateInvolvedOthers")){
			queryStr.append(" and alertStatus.alertStatusId not in (3,4) ");
		}
		if(designationId != null && designationId.longValue()>0l){
			queryStr.append(" and model1.publicRepresentativeType.publicRepresentativeTypeId =:designationId ");
		}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
 	    	if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 4l){
	        	queryStr.append(" and model.alert.userAddress.constituency.constituencyId in(:locationValues) ");
	        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 3l){
	        	queryStr.append(" and model.alert.userAddress.district.districtId in(:locationValues) ");
	        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 5l){
	        	queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in(:locationValues) ");
	        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 6l){
	        	queryStr.append(" and model.alert.userAddress.panchayat.panchayatId in(:locationValues) ");
	        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 7l){
 	        	queryStr.append(" and model.alert.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
 	        }else if(locationTypeId == 8l){
 	        	queryStr.append(" and model.alert.userAddress.ward.constituencyId in(:locationValues) ");
 	        }else if(locationTypeId == 2l){
 	        	queryStr.append(" and model.alert.userAddress.state.stateId in(:locationValues) ");
 	        }
 	    }
		Query query = getSession().createQuery(queryStr.toString());   
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			query.setParameterList("alertTypeIds", alertTypeIds);
		}
		if(statusIdsList != null && statusIdsList.size() > 0L){
			query.setParameterList("statusIdsList", statusIdsList);
		}
		
		if(year !=null && !year.trim().isEmpty()){
				query.setParameter("year", Integer.parseInt(year));
			}
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	 
			if(locationTypeId == 4l || locationTypeId == 8l){
				query.setParameterList("locationValues", locationValues);
        }else if(locationTypeId == 3l){
        	query.setParameterList("locationValues", locationValues);
        }else if(locationTypeId == 5l){
        	query.setParameterList("locationValues", locationValues);
        }else if(locationTypeId == 6l){
        	query.setParameterList("locationValues", locationValues);
        }else if(locationTypeId == 7l){
        	query.setParameterList("locationValues", locationValues);
        }else if(locationTypeId == 2l){
        	query.setParameterList("locationValues", locationValues);
	        }
		}
		if(designationId != null && designationId.longValue()>0l){
			query.setParameter("designationId", designationId);
		}
		return query.list();
	}
	
	public List<Object[]> getAlertInvolvedCandidateInfo(Long alertId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select tdpCadre.firstname,tdpCadre.lastname,model.newsCandidate,model.organization " +
				" from AlertCandidate model " +
				" left join model.tdpCadre tdpCadre " +
				" where " +
				// " model.alert.isDeleted ='N' " +
				" model.alertId=:alertId ");
		
		Query query = getSession().createQuery(str.toString());   
		
		query.setParameter("alertId", alertId);
		
		return query.list(); 
		
	}
	
	
}
