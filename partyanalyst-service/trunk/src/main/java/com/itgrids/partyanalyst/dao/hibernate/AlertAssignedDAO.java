package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.model.AlertAssigned;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertAssignedDAO extends GenericDaoHibernate<AlertAssigned, Long> implements IAlertAssignedDAO{

	public AlertAssignedDAO() {
		super(AlertAssigned.class);
		
	}
	
	
	public List<Long> checkCadreExistsForAlert(List<Long> tdpCadreIds,Long alertId)
	{
		Query query = getSession().createQuery("select distinct model.tdpCadre.tdpCadreId from AlertAssigned model" +
				" where model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId in(:tdpCadreIds) and model.isDeleted ='N'");
		query.setParameter("alertId", alertId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	public List<Long> getDeleteAlertAssignedCandidates(Long alertId,Long tdpCadreId)
	{
		Query query = getSession().createQuery("select  model.alertAssignedId from AlertAssigned model" +
				" where model.isDeleted ='N' and  model.alert.alertId = :alertId and model.tdpCadre.tdpCadreId =:tdpCadreId ");
		query.setParameter("alertId", alertId);
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidateForDashBoard(Long alertId,Long stateId,Long alertTypeId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.tdpCadre.tdpCadreId, " +
				   " model.tdpCadre.firstname, " +
				   " count(distinct model.alert.alertId) "+
				   " from " +
				   " AlertAssigned model " +
				   " left join model.alert.userAddress.state state " +
				   " where " +
				   " model.alert.isDeleted ='N' and " +
				   " model.isDeleted ='N' and " +
				   " model.alert.alertType.alertTypeId = :alertTypeId and " +
				   " date(model.alert.createdTime) between :fromDate and :toDate and ");
		if(stateId != null && stateId.longValue() > 0L){
			if(stateId.longValue() == 1L){
				str.append(" state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				str.append(" state.stateId = 36 ");
			}else{
				str.append(" state.stateId in (1,36) ");
			}
		}
		if(alertId != null && alertId > 0)
			str.append(" and  model.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString() +" group by model.tdpCadre.tdpCadreId order by model.tdpCadre.firstname ");
		if(alertId != null && alertId > 0)
			query.setParameter("alertId", alertId); 
		query.setParameter("alertTypeId", alertTypeId); 
		query.setDate("fromDate", fromDate); 
		query.setDate("toDate", toDate);    
		return query.list();
	}
	public List<Object[]> getAlertAssignedCandidate(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId, model.tdpCadre.firstname"+
				" from AlertAssigned model where model.alert.isDeleted ='N' and model.isDeleted ='N' ");
		if(alertId != null && alertId > 0)
			str.append(" and  model.alert.alertId = :alertId");
		Query query = getSession().createQuery(str.toString() +" order by model.tdpCadre.firstname ");
		if(alertId != null && alertId > 0)  
		query.setParameter("alertId", alertId);
		return query.list();
	}
	/*
	 * Author 	: 	Srishailam Pittala
	 * Date 	:	29th Dec,2016
	 * Description : to get tdpCadre Wise assigned alert details
	 * */
	
	public List<Object[]> getTdpCadreWiseAssignedAlertDetails(Long tdpCadreId,Date fromDate, Date toDate,Long alertTypeId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertCategoryId,model.alertTypeId,model.alertStatusId,count(distinct model.alertId) from Alert model,AlertAssigned model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and model1.isDeleted ='N' and " +
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
				" from Alert model,AlertAssigned model1 " +
				" where  model.alertId = model1.alertId and " +
				" model.isDeleted ='N' and model1.isDeleted='N' and  " +
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
	//swadhin lenka
	public List<Object[]> getAssignedCandidateList(Long alertId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  " +
						" alertAssigned.tdpCadre.tdpCadreId, " +//0
						" alertAssigned.tdpCadre.firstname," +//1
						" alertAssigned.tdpCadre.mobileNo, " +//2
						" constituency.name, " +//3
						" alertAssigned.tdpCadre.image " +//4
				     	" from " +
				     	" AlertAssigned alertAssigned " +
				     	" left join alertAssigned.tdpCadre.userAddress userAddress " +
				     	" left join userAddress.constituency constituency " +
				     	" where " +
				     	" alertAssigned.isDeleted = 'N' and " +
				     	" alertAssigned.alert.alertId = :alertId and " +
				     	" constituency.electionScope = 2 and " +
				     	" constituency.deformDate is null ");  
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();  
	}

public List<Long> getAssignedTdpCadreIdsByAlertId(Long alertId){
	
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append("select distinct model.tdpCadreId from AlertAssigned model where model.alertId =:alertId and model.isDeleted = 'N'");
	
	Query qry = getSession().createQuery(queryStr.toString());
	
	if(alertId != null && alertId.longValue()>0l){
		qry.setParameter("alertId", alertId);
	}
	return qry.list();  
}


public int deleteAlertAssignedByExistingIds(Long tdpCadreId,Long alertId){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" delete from AlertAssigned model " +
			  " where model.tdpCadreId = :cadreId " +
			  " and model.alertId = :alertId");
	
	Query qry = getSession().createQuery(sb.toString());
	
	if(tdpCadreId != null && tdpCadreId.longValue()>0l){
		qry.setParameter("cadreId", tdpCadreId);
	}
	if(alertId != null && alertId.longValue()>0l){
		qry.setParameter("alertId", alertId);
	}
	
	return qry.executeUpdate();
}
public List<Long> checkCadreAssignedForAlert(Long alertId)
{
	Query query = getSession().createQuery("select distinct model.tdpCadre.tdpCadreId from AlertAssigned model " +
			" where model.alert.alertId = :alertId and model.isDeleted ='N'");
	query.setParameter("alertId", alertId);
	return query.list();
}
public List<Long> getTotalAlertsRelatedToCadre(Long cadreId,Long stateId,Long alertTypeId,Date fromDate,Date toDate){
	StringBuilder str = new StringBuilder();  
	str.append("select distinct " +
			" model.alert.alertId " +
			" from AlertAssigned model " +
			" left join model.alert.userAddress.state state " +
			" where " +
			" model.tdpCadre.tdpCadreId = :cadreId and " +
			" model.isDeleted ='N' and " +
			" model.tdpCadre.isDeleted = 'N' and " +
			" model.alert.isDeleted ='N' and " +
			" model.alert.alertType.alertTypeId = :alertTypeId and " +
			" date(model.alert.createdTime) between :fromDate and :toDate and ");
	if(stateId != null && stateId.longValue() > 0L){
		if(stateId.longValue() == 1L){
			str.append(" state.stateId = 1 ");
		}else if(stateId.longValue() == 36L){
			str.append(" state.stateId = 36 ");
		}else{
			str.append(" state.stateId in (1,36) ");
		}
	}
	Query query = getSession().createQuery(str.toString());
	
	query.setParameter("cadreId", cadreId);
	query.setParameter("alertTypeId", alertTypeId); 
	query.setDate("fromDate", fromDate); 
	query.setDate("toDate", toDate);   
	return query.list();
}
public List<Object[]> getLeaderDtls(Long assignedId){
	Query query = getSession().createQuery("select distinct model.alert.alertId," +
			" model.alert.description," +
			" model.tdpCadre.mobileNo " +
			" from AlertAssigned model " +
			"  where model.alertAssignedId = :assignedId " +
			"  and model.isDeleted ='N' and model.smsStatus ='N' ");
	query.setParameter("assignedId", assignedId);
	return query.list();
	
}
public int updateAlertSmsStatus(Long assignedId){
	Query query = getSession().createQuery(" update AlertAssigned model set model.smsStatus ='Y' where " +
			"  model.alertAssignedId =:assignedId ");
	query.setParameter("assignedId", assignedId);
	return query.executeUpdate();
}

	public List<Object[]> getAssignedAlertsSummary(List<Long> cadreIds){
		Query query = getSession().createQuery(" select model.tdpCadreId,count(distinct model.alertId) " +
				" from AlertAssigned model " +
				" where model.isDeleted='N' and model.alert.isDeleted='N' " +
				" and model.tdpCadreId in (:cadreIds) " +
				" group by model.tdpCadreId ");
		
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	
	public List<Object[]> getSmsTdpCadreDetails(){
		
		Query query = getSession().createQuery(" select model.tdpCadreId,model.tdpCadre.mobileNo,model.tdpCadre.firstname,model.alert.alertStatusId," +
				" model.alert.alertStatus.alertStatus,count(distinct model.alertId) " +
				" from AlertAssigned model " +
				" where model.isDeleted ='N' and model.alert.isDeleted ='N'" +
				" and model.alert.alertStatusId in (:alertStatusIds) " +
				" and model.alert.alertTypeId  =:alertTypeId" +
				" and model.tdpCadre.mobileNo is not null" +
				" group by model.tdpCadreId,model.alert.alertStatusId ");
		
		query.setParameterList("alertStatusIds", IConstants.PARTY_ALERT_SMS_STATUS_IDS);
		query.setParameter("alertTypeId", IConstants.PARTY_ALERT_TYPE_ID);
		return query.list();
		
	}
	
	public List<Object[]> getDesignationWiseAssignedAlertys(Date fromDate ,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select  model.alert.alertStatus.alertStatusId,model.alert.alertStatus.alertStatus" +
		  				  ",model.alert.alertStatus.color," +
		  				  " model1.publicRepresentativeType.publicRepresentativeTypeId,model1.publicRepresentativeType.type" +
		  				  " ,count(distinct model.alert.alertId )" +
		  				  " from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  				  " where  " +
		  				  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  				  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N'  ");
		
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
		/*if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
		}
		if(districtId != null && districtId.longValue() > 0){
		  queryStr.append(" and model.alert.userAddress.district.districtId=:districtId ");	
		}
	   if(alertStatusIds != null && alertStatusIds.size() > 0){
		   queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) "); 
	   }*/
        queryStr.append(" group by model1.publicRepresentativeType.publicRepresentativeTypeId,model.alert.alertStatus.alertStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    /*if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }*/
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		}
		/*if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}*/
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			query.setParameterList("alertTypeIds", alertTypeIds);  
		}
		/*if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}*/
		/*if(districtId != null && districtId.longValue() > 0){
		   query.setParameter("districtId", districtId);	
		}*/
		/*if(alertStatusIds != null && alertStatusIds.size() > 0){
		  query.setParameterList("alertStatusIds", alertStatusIds);
		}*/
		return query.list();
	}
	//fromDate, toDate, locationValues, alertTypeIds, locationTypeId, year,type
	public List<Object[]> getDesignationWiseAssignedAlerts(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type,List<Long> statusIdsList,Long designationId){
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
		queryStr.append(" from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
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
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency ");
		queryStr.append(" where  model2.candidate.candidateId=model1.candidate.candidateId " +
		  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId and model.alert.isDeleted ='N'  and  model.isDeleted ='N' " );
				//"and alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		
		/*from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  " where  " +
		  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId
*/		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");
		}     
		if(alertTypeIds != null && alertTypeIds.size() > 0){
			queryStr.append(" and alertType.alertTypeId in (:alertTypeIds)");
		}
		if(statusIdsList != null && statusIdsList.size() > 0L){
			queryStr.append(" and alertStatus.alertStatusId in (:statusIdsList) ");
		}
		if(type != null && type.equalsIgnoreCase("candidateAssignedOthers")){
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
	
	public List<Long> getAlertAssignedCandidateInfo(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId "+
				" from AlertAssigned model where model.alert.isDeleted ='N' and model.isDeleted ='N' " +
				" and  model.alert.alertId = :alertId ");
		Query query = getSession().createQuery(str.toString() +" order by model.tdpCadre.tdpCadreId desc ");
		
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
}
