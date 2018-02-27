package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertIssueCategoryRelationDAO;
import com.itgrids.partyanalyst.model.AlertIssueCategoryRelation;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertIssueCategoryRelationDAO extends GenericDaoHibernate<AlertIssueCategoryRelation, Long> implements IAlertIssueCategoryRelationDAO{

	public AlertIssueCategoryRelationDAO()
    {
		super(AlertIssueCategoryRelation.class);
	}

	public int deleteIssueCategoryDetails(List<Long> alertIssueCategoryRelationIds){
		
		Query query = getSession().createQuery(" update AlertIssueCategoryRelation model set model.isDeleted ='Y' where model.alertIssueCategoryRelationId in (:alertIssueCategoryRelationIds) ");
		
		query.setParameterList("alertIssueCategoryRelationIds", alertIssueCategoryRelationIds);
		
		return query.executeUpdate();
	}
	
	public List<Long> getAlertIssueCategoryList(Long alertId,Long alertTypeId){
		
		Query query = getSession().createQuery(" select model.alertIssueCategoryId " +
				"  from AlertIssueCategoryRelation model where model.alertId =:alertId " +
				" and model.alertIssueCategory.alertTypeId =:alertTypeId " +
				" and model.isDeleted ='N'  " +
				" and model.alertIssueCategory.isDeleted ='N' ");
		
		query.setParameter("alertId", alertId);
		query.setParameter("alertTypeId", alertTypeId);
		
		return query.list();
	}
	
	public List<Long> getAlertIssueCategoryIdsList(Long alertId){
		
		Query query = getSession().createQuery(" select model.alertIssueCategoryRelationId " +
				"  from AlertIssueCategoryRelation model where model.alertId =:alertId " +
				" and model.isDeleted ='N' " );
		
		query.setParameter("alertId", alertId);
		
		return query.list();
	}
	
	public List<Object[]> getIssueCategoryDetailsOfAlerts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeIds,
			List<Long> editionTypeList,List<Long> alertStatusIds,List<Long> impactScopeIdList,String type){
	
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.alertIssueCategory.alertIssueCategoryId,model.alertIssueCategory.issueCategory ");
		
		if(type !=null && type.equalsIgnoreCase("status")){
			queryStr.append(" ,model.alert.alertStatus.alertStatusId,model.alert.alertStatus.alertStatus " );
		}
		 
		queryStr.append("  ,count(distinct model.alertId) from  AlertIssueCategoryRelation model " +
				" where model.isDeleted ='N' and model.alert.isDeleted='N' ");
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
		
		if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		}
		
		if(fromDate !=null && toDate !=null){
			queryStr.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");  
		}
		
		if(alertTypeIds != null && alertTypeIds.size() > 0){
		    queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeIds) ");  
		}
	    if(editionTypeList != null && editionTypeList.size() > 0){
	    	queryStr.append(" and model.alert.editionType.editionTypeId in (:editionTypeList) ");
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
	    }
	    if(impactScopeIdList != null && impactScopeIdList.size() > 0){
			queryStr.append(" and model.alert.impactScopeId in (:impactScopeIdList) ");
		}
	    
	    queryStr.append(" group by model.alertIssueCategory.alertIssueCategoryId ");
	    
	    if(type !=null && type.equalsIgnoreCase("status")){
	    	queryStr.append(" ,model.alert.alertStatus.alertStatusId ");
	    }
	    
	    queryStr.append(" order by model.alertIssueCategory.alertIssueCategoryId ");
	    
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(stateId != null && stateId.longValue() > 0l){
			query.setParameter("stateId", stateId);
		}
		  
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelId !=null && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			query.setParameterList("alertTypeList", alertTypeIds);
	    }
	    if(editionTypeList != null && editionTypeList.size() > 0){
	    	query.setParameterList("editionList", editionTypeList);
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(impactScopeIdList != null && impactScopeIdList.size() > 0){
			query.setParameterList("scopeIdList", impactScopeIdList);
		}
		
		
		return query.list();
		
	}
	
	public List<Object[]> getBriefIssueCategoryDetailsOfAlert(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeIds,
			List<Long> editionTypeList,List<Long> alertStatusIds,List<Long> impactScopeIdList,Long issueCategoryId){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct  model.alertId, " +//0
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
				" state.stateName, "+//20
				" tehsil.tehsilName, " +//21
				" panchayat.panchayatName , " +//22
				" localElectionBody.name, " +//23
				" alertSeverity.severityColor, " +//24
				" alertStatus.color "); //25
	
		queryStr.append(" from  AlertIssueCategoryRelation model " +
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
		
		queryStr.append(" where model.isDeleted ='N' and model.alert.isDeleted='N' ");
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
		
		if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		}
		
		if(fromDate !=null && toDate !=null){
			queryStr.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");  
		}
		
		if(alertTypeIds != null && alertTypeIds.size() > 0){
		    queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeIds) ");  
		}
	    if(editionTypeList != null && editionTypeList.size() > 0){
	    	queryStr.append(" and model.alert.editionType.editionTypeId in (:editionTypeList) ");
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
	    }
	    if(impactScopeIdList != null && impactScopeIdList.size() > 0){
			queryStr.append(" and model.alert.impactScopeId in (:impactScopeIdList) ");
		}
	    
	    if(issueCategoryId !=null && issueCategoryId>0){
	    	queryStr.append(" and model.alertIssueCategory.alertIssueCategoryId =:issueCategoryId ");
	    }
	    
		Query query = getSession().createQuery(queryStr.toString());
		
		if(stateId != null && stateId.longValue() > 0l){
			query.setParameter("stateId", stateId);
		}
		  
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelId !=null && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			query.setParameterList("alertTypeList", alertTypeIds);
	    }
	    if(editionTypeList != null && editionTypeList.size() > 0){
	    	query.setParameterList("editionList", editionTypeList);
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(impactScopeIdList != null && impactScopeIdList.size() > 0){
			query.setParameterList("scopeIdList", impactScopeIdList);
		}
		
	    if(issueCategoryId !=null && issueCategoryId>0){
	    	query.setParameter("issueCategoryId", issueCategoryId);
	    }
		return query.list();
	}
	
	public List<Object[]> getStateWiseAlertIssueCategoryDetails(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> statusIds,Long districtId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		   queryStr.append("select ");
		   queryStr.append(" model.alertIssueCategory.alertIssueCategoryId,model.alertIssueCategory.issueCategory,"); 
	       queryStr.append(" count(distinct model.alertId)" );
	       if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
	    	   queryStr.append(" ,model.alert.userAddress.localElectionBody.localElectionBodyId,model.alert.userAddress.localElectionBody.name ");
	       }
	       queryStr.append(" from AlertIssueCategoryRelation model" );
	 	   queryStr.append(" where model.isDeleted='N' " +
	 	   		" and model.alert.isDeleted = 'N' ");  
	 	   //queryStr.append(" and model1.alertType.alertTypeId = model.alertType.alertTypeId and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
	 	   
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		 }
		 if(statusIds != null && statusIds.size() > 0){
		  queryStr.append(" and model.alert.alertStatus.alertStatusId in (:statusIds)");	 
		 }
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){			
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
		  if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
			  queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
	     }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}else{
			queryStr.append(" and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		}
	    
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
		}
		if(districtId != null && districtId.longValue() > 0){
			queryStr.append(" and model.alert.userAddress.district.districtId=:districtId ");
		}
	    queryStr.append(" group by model.alertIssueCategory.alertIssueCategoryId ");
	    
	    if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
	    	queryStr.append(",model.alert.userAddress.localElectionBody.localElectionBodyId");
	    }
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		 if(statusIds != null && statusIds.size() > 0){
			 query.setParameterList("statusIds", statusIds); 
		 }
		 if(districtId != null && districtId.longValue() > 0){
			 query.setParameter("districtId", districtId); 
		 }
		return query.list();
	}
	
	public List<Object[]> getTotalAlertOfLocationAndIssueCategory(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, 
			Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue
			,Long disctrictId,List<Long> alertStatusIds,List<Long> locationValues){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(filterType != null && filterType.equalsIgnoreCase("District")){
			queryStr.append(" district.districtId, " +//0
					       " district.districtName, ");//1
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
			queryStr.append(" constituency.constituencyId, " +//0
							" constituency.name, ");//1
		}
		if(step.equalsIgnoreCase("two")){
			queryStr.append(" model.alertIssueCategory.alertIssueCategoryId, " +//2  
							" model.alertIssueCategory.issueCategory, ");//3
		}
		queryStr.append(" count(distinct model.alertId) " +  //4  
						" from AlertIssueCategoryRelation model " +
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward " +
						" where model.isDeleted ='N' " +
						" and model.alert.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");  
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");     
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			queryStr.append(" and model.alert.impactScopeId in (:scopeIdList) ");
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
		}
		if(locationValue != null && locationValue.longValue() > 0){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" and district.districtId =:locationValue ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" and constituency.constituencyId =:locationValue");
			}
		}else if(locationValues != null && locationValues.size() > 0){
			queryStr.append(" and constituency.constituencyId in (:locationValues) ");
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			queryStr.append(" and district.districtId =:disctrictId ");
		}
		if(step.equalsIgnoreCase("one")){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId order by district.districtId ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId order by constituency.constituencyId ");
			}
		}else{
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId,model.alertIssueCategory.alertIssueCategoryId order by district.districtId  ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId,model.alertIssueCategory.alertIssueCategoryId order by constituency.constituencyId ");
			}
		}  
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}    
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);  
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(locationValue != null && locationValue.longValue() > 0){
		 query.setParameter("locationValue", locationValue);	
		}else if(locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			 query.setParameter("disctrictId", disctrictId);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
		  query.setParameterList("alertStatusIds", alertStatusIds);
		}
		return query.list();   
	}
	
	// Clicking Functionality For Issue Category Of CoreDashBoard
	public List<Object[]> getIssueCategoryWiseLocationAlertDetails(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,List<Long> districtIdList, List<Long> alertTypeList, List<Long> editionList,
			Long constituencyId,List<Long> alertStatusIds,Long localElectionBodyId,String locationLevel,String type,List<Long> constituencyList,List<Long> issueCategoryIds){
			
			StringBuilder queryStr = new StringBuilder();      
		    queryStr.append(" select distinct ");     
		    queryStr.append(" model.alertId, " +//0
						" alert.createdTime, " +//1
						" alert.updatedTime, " +//2
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" alert.title, " +//9
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
						" state.stateName, "+//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" alertSeverity.severityColor, " +//24
						" alertStatus.color "); //25
			 queryStr.append(" from " +
		                	" AlertIssueCategoryRelation model " +
		                	" left join model.alert alert  " +
		                	" left join alert.alertCategory alertCategory " +
		   	   			 	" left join alert.alertSource alertSource " +
		   	   			 	" left join alert.editionType editionType " +
		   	   			 	" left join alert.edition edition " +
		   	   			 	" left join alert.alertSeverity alertSeverity " +   
		   	   			 	" left join alert.tvNewsChannel tvNewsChannel "+
		                	" left join alert.alertStatus alertStatus " +
		                	" left join alert.alertCategory alertCategory " +
		                	" left join alert.alertImpactScope alertImpactScope " +
		                	" left join alert.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district " +
							" left join userAddress.constituency constituency " +
							" left join userAddress.parliamentConstituency parliamentConstituency" +
							" left join userAddress.tehsil tehsil " +
							" left join userAddress.localElectionBody localElectionBody " +
							" left join userAddress.panchayat panchayat " +
							" left join localElectionBody.electionType electionType  " +
							" where  " +
		                	" model.isDeleted='N' " +
		                	" and alert.isDeleted ='N' " +
		    				"  ");
	                	
			 if(stateId != null && stateId.longValue() > 0l){
			    	queryStr.append(" and alert.userAddress.state.stateId=:stateId ");  
			 }
	  
		    if(districtIdList != null && districtIdList.size() > 0){
		    	queryStr.append(" and district.districtId in (:districtIds)");
		    }
		    if(constituencyId != null && constituencyId.longValue() > 0){
		    	queryStr.append(" and constituency.constituencyId=:constituencyId");
		    }
		    
		    if(constituencyList != null && constituencyList.size() > 0){
		    	queryStr.append(" and constituency.constituencyId in (:constituencyList)");
		    }
		    if(alertStatusIds != null && alertStatusIds.size() > 0){
		    	queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds)");
		    }
		   
		    if(fromDate !=null && toDate !=null){  
		       queryStr.append(" and date(alert.createdTime) between :startDate and :endDate  ");
		    }
		    if(type != null && type.trim().equalsIgnoreCase("impactScopeWise")){
				queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactLevelIds) ");	
			}else if(type != null && type.trim().equalsIgnoreCase("locationWise")){
				queryStr.append(" and alert.impactLevelId in (:impactLevelIds) ");
			}
		    if(localElectionBodyId != null && localElectionBodyId.longValue() > 0){
		    	if(type != null && type.trim().equalsIgnoreCase("impactScopeWise")){
		    		if(impactLevelIds.get(0).longValue() == 8l){//ghmc
		   	    	  queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
		       	   }
		    	}else if(type != null && type.trim().equalsIgnoreCase("locationWise")){
		    		if(impactLevelIds.get(0).longValue() == 7l){//ghmc
			   	    	  queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			       	  }
		    	}
		       queryStr.append(" and userAddress.localElectionBody.localElectionBodyId = :localElectionBodyId ");	
		    }
		    if(locationLevel.equalsIgnoreCase("OtherLocations")){
		      if(impactLevelIds.get(0).longValue() == 8l){//ghmc
		    	  queryStr.append(" and ((electionType.electionTypeId not in ("+IConstants.ELECTION_TYPE_IDS+")) or (userAddress.localElectionBody is null )) ");//Not CORPORATION & Greater Municipal Corp
		      	}
		    }

		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		    	queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		          queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		        queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		         queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues) ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		          queryStr.append(" and tehsil.tehsilId in (:userAccessLevelValues)");  
		    }
		    if(alertTypeList != null && alertTypeList.size() > 0){
		    	queryStr.append(" and alert.alertTypeId in (:alertTypeList) ");  
		    }
		    if(editionList != null && editionList.size() > 0){
		    	queryStr.append(" and alert.editionTypeId in (:editionList) ");
		    }
		    
		    if(issueCategoryIds !=null && issueCategoryIds.size()>0){
		    	queryStr.append(" and model.alertIssueCategoryId in (:issueCategoryIds) ");  
		    }
		   
		    Query query = getSession().createQuery(queryStr.toString());
		    
		    if(stateId != null && stateId.longValue() > 0l){            
		    	query.setParameter("stateId", stateId);
		    }
		    if(districtIdList != null && districtIdList.size() > 0){
		    	query.setParameterList("districtIds", districtIdList);
		    }
		    if(constituencyId != null && constituencyId.longValue() > 0){
		    	query.setParameter("constituencyId", constituencyId);
		    }
		    if(constituencyList != null && constituencyList.size() > 0){
		    	query.setParameterList("constituencyList", constituencyList);
		    }
		    if(alertStatusIds != null && alertStatusIds.size() > 0){
		    	query.setParameterList("alertStatusIds", alertStatusIds);
		    }
		    
		    if(fromDate !=null && toDate !=null){
		    	query.setDate("startDate", fromDate);
		    	query.setDate("endDate", toDate);
		    }
		    if(userAccessLevelId != null && userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		    }
		    if(type != null && type.trim().equalsIgnoreCase("impactScopeWise") || type.trim().equalsIgnoreCase("locationWise")){
		    	query.setParameterList("impactLevelIds", impactLevelIds); 
		    }
		    if(alertTypeList != null && alertTypeList.size() > 0){
				query.setParameterList("alertTypeList", alertTypeList);
		    }
		    if(editionList != null && editionList.size() > 0){
		    	query.setParameterList("editionList", editionList);
		    }
		    if(localElectionBodyId != null && localElectionBodyId.longValue() > 0){
		    	query.setParameter("localElectionBodyId", localElectionBodyId);	
			}
		    if(issueCategoryIds !=null && issueCategoryIds.size()>0){
		    	query.setParameterList("issueCategoryIds", issueCategoryIds);
		    }
			 
		    return query.list();  
		}
	
	public List<String> getAlertIssueCategory(Long alertId){
		
		Query query = getSession().createQuery(" select model.alertIssueCategory.issueCategory " +
				"  from AlertIssueCategoryRelation model where model.alertId =:alertId " +
				" and model.isDeleted ='N' and model.alertIssueCategory.isDeleted='N' " +
				"  order by model.alertIssueCategoryId desc " );
		
		query.setParameter("alertId", alertId);
		
		return query.list();
	}
	
	
}
