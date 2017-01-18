package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertActionTypeDAO;
import com.itgrids.partyanalyst.model.AlertActionType;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertActionTypeDAO extends GenericDaoHibernate<AlertActionType, Long> implements IAlertActionTypeDAO {

	public AlertActionTypeDAO(){
		super(AlertActionType.class);
	}
	
	public List<Object[]> getAlertCountStatusWiseBasedOnActionType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes)	
	   {
		    StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		    		" model.actionType.actionTypeId," +
		    		" model.actionTypeStatus.actionTypeStatusId," +
		    		" count(distinct model1.alertId) " +
		    		" from AlertActionType model,Alert model1 " +
		    		" where " +
		    		" model.alert.alertId=model1.alertId and model.isDeleted='N' and model1.isDeleted='N' ");
		    
		     if(stateId != null && stateId.longValue() > 0l){
				  queryStr.append(" and model1.userAddress.state.stateId=:stateId ");  
			 }
			 if(fromDate !=null && toDate !=null){
				  queryStr.append(" and date(model1.createdTime) between :startDate and :endDate  ");
			 }
			 if(editionTypes != null && editionTypes.get(0).longValue() > 0L){
		     queryStr.append(" and model1.editionType.editionTypeId in (:editionTypes) ");
		    }
			if(alertType != null && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model1.alertType.alertTypeId in (:alertType)");	
			}
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			  queryStr.append(" and model1.userAddress.state.stateId in (:userAccessLevelValues)");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			      queryStr.append(" and model1.userAddress.district.districtId in (:userAccessLevelValues)");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model1.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		       queryStr.append(" and model1.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			      queryStr.append(" and model1.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			}
		    queryStr.append(" group by model.actionType.actionTypeId,model.actionTypeStatus.actionTypeStatusId " +
		    		        " order by model.actionType.actionTypeId ");
		    
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
				return query.list();
	   }
	public List<Object[]> getActionTypeAlertDetails(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertActionStatusId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,Long actionTypeId){
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
						" tvNewsChannel.channelName," + //19
						" model1.actionTypeStatus.actionTypeStatusId," +//20
						" model1.actionTypeStatus.status ");//21
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
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency,AlertActionType model1 ");
		queryStr.append(" where model1.alert.alertId=model.alertId and model.isDeleted ='N'");
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}     
		
		if(stateId != null && stateId.longValue() > 0l){
			queryStr.append(" and state.stateId=:stateId ");  
		}
		
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			queryStr.append(" and alertType.alertTypeId = (:alertTypeId) ");
		}
		
		if(alertActionStatusId != null && alertActionStatusId.longValue() > 0L){
			queryStr.append(" and model1.actionTypeStatusId = (:actionTypeStatusId) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and editionType.editionTypeId in (:editionList) ");
		}
		if(actionTypeId != null && actionTypeId.longValue() > 0){
			queryStr.append(" and model1.actionTypeId=:actionTypeId");
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
		if(alertActionStatusId != null && alertActionStatusId.longValue() > 0L){
			query.setParameter("actionTypeStatusId", alertActionStatusId);
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
		return query.list();
	}
}
