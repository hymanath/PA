package com.itgrids.partyanalyst.dao.hibernate;



import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.VerificationStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class VerificationStatusDAO extends GenericDaoHibernate<VerificationStatus, Long> implements IVerificationStatusDAO {

	public VerificationStatusDAO() {
		super(VerificationStatus.class);
	}
	public List<Object[]> getStatusWiseAlertCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId,Long assignedUserId,String verificationUserType){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model1.actionTypeStatus.actionType.actionTypeId, " +
						" model1.actionTypeStatus.actionType.typeName, " +
						" model1.actionTypeStatus.actionTypeStatusId, " +
						" model1.actionTypeStatus.status, " +
						" model1.alert.alertCategory.alertCategoryId, " +
						" model1.alert.alertCategory.category, " +
						" count(distinct model1.alert.alertId) " +
						" from VerificationStatus model1 ");
		if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) {
					   queryStr.append(",AlertVerificationUser model2 ");
		}
		queryStr.append(" where model1.isDeleted = 'N' and model1.alert.isDeleted = 'N' " +
						" and model1.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+")" );
		
		 if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) {
			 queryStr.append(" and model2.alertId=model1.alertId and model2.isDeleted='N' ");
			 queryStr.append(" and model2.verificationUserId=:assignedUserId ");
		 }
		
						
		if(stateId != null && stateId.longValue() > 0){
			queryStr.append(" and model1.alert.userAddress.state.stateId = :stateId ");
		}
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model1.alert.createdTime) between :fromDate and :toDate) ");
		}
		if(alertTypeId != null && alertTypeId.longValue() > 0 ){
			queryStr.append(" and  model1.alert.alertType.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by " +
						" model1.actionTypeStatus.actionType.actionTypeId, " +
						" model1.actionTypeStatus.actionTypeStatusId, " +
						" model1.alert.alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(stateId != null && stateId.longValue() > 0){
			query.setParameter("stateId", stateId);
		}
		if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) { 
			query.setParameter("assignedUserId", assignedUserId);
		}
		
		return query.list();
	}
	public List<Object[]> getAllAlerts(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,Date fromDate2,Date toDate2,Long assignedUserId,String verificationUserType){
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.alertId ," +//0
				   " model.description, " +//1
				   " date(model.createdTime)," +//2
				   " alertType.alertType.alertTypeId, " +//3
				   " alertSource.source, " +//4
				   " alertSeverity.severity, " +//5
				   " model.regionScopes.regionScopesId, " +//6
				   " model.regionScopes.scope," +//7
				   " model.alertStatus.alertStatusId, " +//8
				   " model.alertStatus.alertStatus ");//9
		str.append(" ,tehsil.tehsilId, " +//10
				   " tehsil.tehsilName , " +//11
				   " panc.panchayatId, " +//12
				   " panc.panchayatName, " +//13
				   " localElectionBody.localElectionBodyId, " +//14
				   " localElectionBody.name, " +//15
				   " district.districtId, " +//16
				   " district.districtName, " +//17
				   " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +//19
				   " constituency.name");//20
		str.append(" ,state.stateId, " +//21
				   " state.stateName ");//22
		str.append(" ,ward.constituencyId, " +//23
				   " ward.name,");//24
		str.append(" alertCategory.alertCategoryId, " +//25
				   " alertCategory.category, " + //26
				   " editionType.editionTypeId, " +//27
				   " editionType.editionType, " +//28
				   " edition.editionId, " +//29
				   " edition.editionAlias, " +//30
				   " tvNewsChannel.tvNewsChannelId, " +//31
				   " tvNewsChannel.channelName, " +//32
				   " model.title, " +//33
				   " vs.actionTypeStatus.actionTypeStatusId, " +//34
				   " vs.actionTypeStatus.status ");//35
				  
		str.append(" from VerificationStatus vs " +
				" 	 left join vs.alert model " +
				" 	 left join model.editionType editionType " +
        		"  	 left join model.edition edition " +
        		" 	 left join model.tvNewsChannel tvNewsChannel "+
				" 	 left join model.alertSeverity alertSeverity " +
				" 	 left join model.alertSource  alertSource " +
				"	 left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.alertCategory alertCategory ");
		str.append(" left join model.alertType  alertType,AlertDepartmentStatus model1"); 
		if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) {
			 str.append(",AlertVerificationUser model2 ");
		}
		 str.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
	      " and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
		
		 if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) {
			 str.append(" and model2.alertId=vs.alertId and model2.isDeleted='N' ");
			 str.append(" and model2.verificationUserId=:assignedUserId ");
		 }
		
		str.append(" and model.isDeleted ='N' and vs.isDeleted='N'  ");
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0l){
			str.append(" and model.impactScopeId=:impactScopeId ");
		}
		if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() == 1L)
			str.append(" and state.stateId in (1) ");
		else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() == 36L ))
			str.append(" and state.stateId in (36) ");
		else
			str.append(" and state.stateId in (1,36) ");
	
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			str.append(" and model.alertTypeId = :alertTypeId ");
		
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in (:sourceIds)");
		
		if(fromDate != null && toDate != null){ 
			str.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			str.append(" and vs.actionTypeStatus.actionTypeStatusId = :actionTypeStatusId ");
		
		str.append(" and vs.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+")  ");
		
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			str.append(" and model.alertStatus.alertStatusId = :alertStatusId");
		if(fromDate2 != null && toDate2 != null){ 
			str.append(" and (date(vs.updatedTime) between :fromDate2 and :toDate2) ");
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0L){
			query.setParameter("impactScopeId", inputVO.getAlertImpactScopeId());			
		}
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null && toDate != null){ 
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			query.setParameter("actionTypeStatusId", inputVO.getActionTypeStatusId());
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			query.setParameter("alertStatusId", inputVO.getStatusId());
		if(fromDate2 != null && toDate2 != null){ 
			query.setDate("fromDate2", fromDate2);
			query.setDate("toDate2", toDate2);
		}
		if (verificationUserType != null && verificationUserType.equalsIgnoreCase("infoCellCommittee")) { 
			query.setParameter("assignedUserId", assignedUserId);	
		}
		return query.list();
	}
	public Integer updateStatusForOldAlert(Long userId,Long alertId,Date date){
		Query query = getSession().createQuery(" update VerificationStatus model set model.isDeleted='Y', model.updatedBy=:updatedBy,model.updatedTime=:updatedTime " +
				"  where model.alertId=:alertId and model.isDeleted='N'");
		query.setParameter("updatedBy", userId);
		query.setParameter("updatedTime", date);
		query.setParameter("alertId", alertId);
		return query.executeUpdate();
	}
	public Object[] getAertStausIdAndName(Long alertId){
		Query query = getSession().createQuery(" select model.actionTypeStatus.actionTypeStatusId,model.actionTypeStatus.status " +
											   " from VerificationStatus model where model.alert.alertId=:alertId " +
											   " and model.alert.isDeleted='N' and model.isDeleted='N' ");
		query.setParameter("alertId", alertId);
		return (Object[])query.uniqueResult();
	}
	public Long getAlertStatusId(Long alertId){
		Query query = getSession().createQuery("select model.actionTypeStatus.actionTypeStatusId from VerificationStatus model where model.isDeleted='N' and model.alert.alertId=:alertId ");
		query.setParameter("alertId", alertId);
		return (Long)query.uniqueResult();
	}

	public List<Object[]> getAlertCountStatusWiseBasedOnActionType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds)	
	   {
		    StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		    		" model.actionTypeStatus.actionType.actionTypeId," +
		    		" model.actionTypeStatus.actionTypeStatusId," +
		    		" count(distinct model.alertId) " +
		    		" from VerificationStatus model " +
		    		" where " +
		    		"  model.isDeleted='N' and model.alert.isDeleted='N' ");
		    
		     if(stateId != null && stateId.longValue() > 0l){
				  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
			 }
			 if(fromDate !=null && toDate !=null){
				  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
			 }
			 if(editionTypes != null && editionTypes.get(0).longValue() > 0L){
		       queryStr.append(" and model.alert.editionType.editionTypeId in (:editionTypes) ");
		    }
			if(alertType != null && alertType.get(0).longValue() > 0L){
			    queryStr.append(" and model.alert.alertType.alertTypeId in (:alertType)");	
			}
			if(scopeIds != null && scopeIds.size() > 0L){
				/* if(scopeIds.get(0).longValue() == 8l){
						queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
				 }*/
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:scopeIds)");	
			}
			if(alertStatusIds != null && alertStatusIds.size() > 0L){
			    queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
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
		    queryStr.append(" group by model.actionTypeStatus.actionType.actionTypeId,model.actionTypeStatus.actionTypeStatusId " +
		    		        " order by model.actionTypeStatus.actionType.actionTypeId ");
		    
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
			if(alertType != null && alertType.get(0).longValue() > 0L){
				query.setParameterList("alertType", alertType);
			}
			if(editionTypes != null && editionTypes.get(0).longValue() > 0L){
				query.setParameterList("editionTypes", editionTypes);
			}
			if(scopeIds != null && scopeIds.size() > 0L){
			query.setParameterList("scopeIds", scopeIds);
			}
			if(alertStatusIds != null && alertStatusIds.size() > 0L){
			    query.setParameterList("alertStatusIds", alertStatusIds);
			 }
				return query.list();
	   }
	//anil
	public List<Object[]> getActionTypeAlertDetails(Date fromDate, Date toDate, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,Long actionTypeId,List<Long> impactScopeIds,Long alertVerificationStatusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");     
		queryStr.append(" model.alertId, " +//0
						" model.createdTime, " +//1
						" model.updatedTime, " +//2
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" model.title, " +//9
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
						" state.stateName," + //20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22  
						" localElectionBody.name, " +//23
						" 1 , " +
						" alertStatus.color ");//24
		queryStr.append(" from  Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.tvNewsChannel tvNewsChannel "+
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");  
		queryStr.append(" left join model.alertCategory alertCategory ");
		queryStr.append(" left join model.alertStatus alertStatus ");
		queryStr.append(" left join model.alertImpactScope alertImpactScope ");  
		queryStr.append(" left join model.alertType alertType ");
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency,VerificationStatus model1 ");
		queryStr.append(" where model1.alert.alertId=model.alertId and model.isDeleted ='N' and model1.isDeleted='N' ");
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}     
		
		if(stateId != null && stateId.longValue() > 0l){
			queryStr.append(" and state.stateId=:stateId ");  
		}
		
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			queryStr.append(" and alertType.alertTypeId = (:alertTypeId) ");
		}
		
		if(alertStatusIds != null && alertStatusIds.size() > 0L){
			queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds) ");
		}
		if(alertVerificationStatusId != null && alertVerificationStatusId.longValue() > 0){
			queryStr.append(" and model1.actionTypeStatusId =:actionTypeStatusId ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and editionType.editionTypeId in (:editionList) ");
		}
		if(impactScopeIds != null && impactScopeIds.size() > 0){
			/*if(impactScopeIds.get(0).longValue() == 8l){//ghmc
	    		queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")");//election type
	    	}*/
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactScopeIds) ");
		}
		
		if(actionTypeId != null && actionTypeId.longValue() > 0){
			queryStr.append(" and model1.actionTypeStatus.actionType.actionTypeId=:actionTypeId");
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");       
		}if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) "); 
		}
		Query query = getSession().createQuery(queryStr.toString());   
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(stateId != null && stateId.longValue() > 0){
			query.setParameter("stateId", stateId);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0L){
			query.setParameterList("alertStatusIds", alertStatusIds);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);
		}
		if(actionTypeId != null && actionTypeId.longValue() > 0){
			query.setParameter("actionTypeId", actionTypeId);
		}
		if(impactScopeIds != null && impactScopeIds.size() > 0){
			query.setParameterList("impactScopeIds", impactScopeIds);
		}
		if(alertVerificationStatusId != null && alertVerificationStatusId.longValue() > 0){
			query.setParameter("actionTypeStatusId", alertVerificationStatusId);
		}
		return query.list();
	}
	public List<Object[]> getTotalStatus(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.alert.alertId, model.actionTypeStatus.status from VerificationStatus model " +
						" where model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
	
	public List<Object[]> getAllAlertsForCentralMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,Date fromDate2,Date toDate2){
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.alertId ," +//0
				   " model.description, " +//1
				   " date(model.createdTime)," +//2
				   " alertType.alertType.alertTypeId, " +//3
				   " alertSource.source, " +//4
				   " alertSeverity.severity, " +//5
				   " model.regionScopes.regionScopesId, " +//6
				   " model.regionScopes.scope," +//7
				   " alertStatus.alertStatusId, " +//8
				   " alertStatus.alertStatus ");//9
		str.append(" ,tehsil.tehsilId, " +//10
				   " tehsil.tehsilName , " +//11
				   " panc.panchayatId, " +//12
				   " panc.panchayatName, " +//13
				   " localElectionBody.localElectionBodyId, " +//14
				   " localElectionBody.name, " +//15
				   " district.districtId, " +//16
				   " district.districtName, " +//17
				   " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +//19
				   " constituency.name");//20
		str.append(" ,state.stateId, " +//21
				   " state.stateName ");//22
		str.append(" ,ward.constituencyId, " +//23
				   " ward.name,");//24
		str.append(" alertCategory.alertCategoryId, " +//25
				   " alertCategory.category, " + //26
				   " editionType.editionTypeId, " +//27
				   " editionType.editionType, " +//28
				   " edition.editionId, " +//29
				   " edition.editionAlias, " +//30
				   " tvNewsChannel.tvNewsChannelId, " +//31
				   " tvNewsChannel.channelName, " +//32
				   " model.title, " +//33
				   " vs.actionTypeStatus.actionTypeStatusId, " +//34
				   " vs.actionTypeStatus.status ");//35
				  
		str.append(" from AlertAssigned model1,VerificationStatus vs " +
				" 	 left join vs.alert model " +
				" 	 left join model.editionType editionType " +
        		"  	 left join model.edition edition " +
        		" 	 left join model.tvNewsChannel tvNewsChannel "+
				" 	 left join model.alertSeverity alertSeverity " +
				" 	 left join model.alertSource  alertSource " +
				"	 left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.alertCategory alertCategory ");
		str.append(" left join model.alertType  alertType " +
				   " left join model.alertStatus alertStatus ");
		
		str.append(" where model.isDeleted ='N' and vs.isDeleted='N' " +
						" and model1.alert.alertId = model.alertId" +
						" and model1.isDeleted = 'N'");
		if(inputVO.getAssignId() != null && inputVO.getAssignId() > 0l)
			str.append(" and model1.tdpCadreId = :tdpCadreId");
		
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0l){
			str.append(" and model.impactScopeId=:impactScopeId ");
		}
		if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() == 1L)
			str.append(" and state.stateId in (1) ");
		else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() == 36L ))
			str.append(" and state.stateId in (36) ");
		else
			str.append(" and state.stateId in (1,36) ");
	
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			str.append(" and model.alertTypeId = :alertTypeId ");
		
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in (:sourceIds)");
		
		if(fromDate != null && toDate != null){ 
			str.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			str.append(" and vs.actionTypeStatus.actionTypeStatusId = :actionTypeStatusId ");
		
		str.append(" and vs.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+")  ");
		
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			str.append(" and alertStatus.alertStatusId = :alertStatusId");
		if(fromDate2 != null && toDate2 != null){ 
			str.append(" and (date(vs.updatedTime) between :fromDate2 and :toDate2) ");
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0L){
			query.setParameter("impactScopeId", inputVO.getAlertImpactScopeId());			
		}
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null && toDate != null){ 
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			query.setParameter("actionTypeStatusId", inputVO.getActionTypeStatusId());
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			query.setParameter("alertStatusId", inputVO.getStatusId());
		if(fromDate2 != null && toDate2 != null){ 
			query.setDate("fromDate2", fromDate2);
			query.setDate("toDate2", toDate2);
		}
		if(inputVO.getAssignId() != null && inputVO.getAssignId() > 0l)
			query.setParameter("tdpCadreId", inputVO.getAssignId());
		return query.list();
	}
}
