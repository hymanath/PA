package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertAssignedOfficerNewDAO extends GenericDaoHibernate<AlertAssignedOfficerNew, Long> implements IAlertAssignedOfficerNewDAO {
	
        public AlertAssignedOfficerNewDAO(){
        	super(AlertAssignedOfficerNew.class);
        }
        public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> calCntrIds){
    		StringBuilder sb = new StringBuilder();  
    	    sb.append("select ");
    	    if(type.equalsIgnoreCase("Status")){
    	      sb.append(" model.alertStatus.alertStatusId, model.alertStatus.alertStatus,model.alertStatus.color " );
    	    }else if(type.equalsIgnoreCase("Level")){
    	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
    	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
    	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color " );
     	    }else if(type.equalsIgnoreCase("Department")){
     	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
     	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
     	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.color ");	
     	    }
    	     sb.append(" ,count(distinct model.alert.alertId) ");
    	      
    	    sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC " +
    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
    	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
    	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
    	    
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty()){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) ");
	    	    if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
	    	    	sb.append(" or model.alert.alertCallerId is not null ");
				}else{
					sb.append(" and model.alert.alertCallerId is null ");
				}
	    	    sb.append(" )");
    	    } 
    	 
    	    if(fromDate != null && toDate != null)
    	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    	      sb.append(" and UA.stateId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
    	      sb.append(" and UA.zoneId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
    	      sb.append(" and UA.regionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
    	      sb.append(" and UA.circleId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
    	      sb.append(" and UA.districtId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.divisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.subDivisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	      sb.append(" and UA.tehsilId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	      sb.append(" and UA.panchayatId in (:levelValues)");
    	    
    	    if(alertStatusIds != null && alertStatusIds.size() > 0){
    	    	sb.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
    	    }
    	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
    	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:departmentScopeIds)");
    	    }
    	    if(type.equalsIgnoreCase("Status")){
      	      sb.append(" group by model.alertStatus.alertStatusId " );
      	    }else if(type.equalsIgnoreCase("Level")){
      	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
       	    }else if(type.equalsIgnoreCase("Department")){
       	    	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );	
       	    }
    	  
    	    Query query = getSession().createQuery(sb.toString());
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      query.setParameterList("departmentIds", departmentIds);
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty()){
    	      query.setParameterList("printIdList", printIdsList);
    	      query.setParameterList("electronicIdList", electronicIdsList);
    	    }  
    	    /*else if(printIdsList != null && !printIdsList.isEmpty()){
    	      query.setParameterList("printIdList", printIdsList);
    	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
    	      query.setParameterList("electronicIdList", electronicIdsList);
    	    }*/
    	    if(fromDate != null && toDate != null){
    	        query.setDate("fromDate", fromDate);
    	        query.setDate("toDate", toDate);
    	    }
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty())
    	        query.setParameterList("levelValues", levelValues);
    	    
    	    if(alertStatusIds != null && alertStatusIds.size() > 0){
    	    	 query.setParameterList("alertStatusIds", alertStatusIds);
    	    }
    	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
    	      query.setParameterList("departmentScopeIds", departmentScopeIds);
    	    }
    	      return query.list();
    	}
       
        
       public List<Object[]> getAssignedStatuses(){
    		Query query = getSession().createQuery(" SELECT model.alertStatus.alertStatusId,model.alertStatus.alertStatus " +
    				" FROM AlertAssignedOfficerNew model " +
    				" WHERE  " +
    				" model.isDeleted = 'N' " +
    				" GROUP BY model.alertStatus.alertStatusId " +
    				" ORDER BY model.alertStatus.statusOrder ");
    		return query.list();
      }
      @SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentScope(){
    		Query query = getSession().createQuery(" " +
    				" SELECT model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
    				" model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName " +
    				" FROM AlertAssignedOfficerNew model " +
    				" WHERE  " +
    				" model.isDeleted = 'N' " +
    				" GROUP BY model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId ");
    		return query.list();
      }
      
      @SuppressWarnings("unchecked")
	public List<AlertAssignedOfficerNew> getModelForAlert(Long alertId){
  		Query query = getSession().createQuery(" select model from AlertAssignedOfficerNew model where model.alertId=:alertId and model.isDeleted='N' ");
  		query.setParameter("alertId", alertId);
  		return query.list();
  	}
        public List<Object[]> getDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
        	StringBuilder sb = new StringBuilder();
	    	  
        	if(type != null && type.equalsIgnoreCase("today")){
        		sb.append(" select model.alertAssignedOfficerId, model.alert.alertId from AlertAssignedOfficerNew model ");
        	}else{
        		sb.append(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus,count(distinct model.alert.alertId),model.alertStatus.color from AlertAssignedOfficerNew model ");
        	}
        	sb.append(" left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC ");
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );//and model.isApproved = 'Y'
        	
         if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
      	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
  	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
  	  			}else{
  	  				sb.append(" and model.alert.alertCallerId is null ");
  	  			}
  	    	      sb.append(" )");
      	    }
        	
        	if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  
	    	  if(fromDate != null && toDate != null){
	    		  sb.append(" and date(model.insertedTime) between :fromDate and :toDate " );
	    	  }
	    	  
	    	  if(type != null && !type.equalsIgnoreCase("today")){
	              sb.append(" group by model.alertStatus.alertStatusId ");
	            }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size() >0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(fromDate != null && toDate != null){
	    		  query.setDate("fromDate", fromDate);
	    		  query.setDate("toDate", toDate);
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	   }  
	    	    /*else if(printIdsList != null && printIdsList.size()>0){
	    	      query.setParameterList("printIdList", printIdsList);
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }*/
	    	  return query.list();
        }
        
        //swadhin alertids based on status.
        public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList){
    		StringBuilder sb = new StringBuilder();  
    	    sb.append("select distinct model.alert.alertId ");
    	    sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC " +
    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
    	          " left join UA.state S " +
    	          " left join UA.zone Z " +
    	          " left join UA.region R" +
    	          " left join UA.circle C" +
    	          " left join UA.district D" +
    	          " left join UA.division DIV" +
    	          " left join UA.subDivision SUBDIV" +
    	          " left join UA.tehsil T" +
    	          " left join UA.localElectionBody LEB " +
    	          " left join UA.panchayat P");
    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N'  " +
    	    		  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " +
    	    		  " and model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
    	    if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :govtDepartmentScopeId ");
    	    }
    	    if(statusId != null && statusId.longValue() > 0L){
    	    	sb.append(" and  model.alertStatus.alertStatusId = :statusId ");
    	    }
    	    if(deptId != null && deptId.longValue() > 0L){
    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId ");
    	    }else{
    	    	if(departmentIds != null && departmentIds.size()>0){
        	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
        	    }
    	    }
    	    
    	      
    	    
    	    if(printIdsList != null && printIdsList.size()>0 && electronicIdsList != null && electronicIdsList.size()>0){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
	    	      if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	  			}else{
	  				sb.append(" and model.alert.alertCallerId is null ");
	  			}
	    	      sb.append(" )");
    	    }else if(printIdsList != null && printIdsList.size()>0){
    	      sb.append(" and  EDS.newsPaperId in (:printIdList) ");
    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
    	      sb.append(" and TNC.tvNewsChannelId in (:electronicIdList) ");
    	    }        
    	      
    	    if(fromDate != null && toDate != null){
    	    	 sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    }
    	     	    
    	    if(levelId !=null && levelValues !=null && levelValues.size()>0){
	    		if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	    	      sb.append(" and UA.stateId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	    	      sb.append(" and UA.zoneId in (:levelValues)");
	    	    else if(levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	    	      sb.append(" and UA.regionId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	    	      sb.append(" and UA.circleId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	    	      sb.append(" and UA.districtId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	    	      sb.append(" and UA.divisionId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	    	      sb.append(" and UA.subDivisionId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	      	      sb.append(" and UA.tehsilId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
	    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	      	      sb.append(" and UA.panchayatId in (:levelValues)");
    	   	      
    	    }
    	   if(impactLevelIdList != null && impactLevelIdList.size()>0){
    		   sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:impactLevelIdList) ");
    	   }
    	   if(priorityIdList != null && priorityIdList.size()>0){
    		   sb.append(" and model.alert.alertSeverity.alertSeverityId in (:priorityIdList) ");
    	   }
    	   if(alertSourceIdList != null && alertSourceIdList.size()>0){
    		   sb.append(" and model.alert.alertCategory.alertCategoryId in (:alertSourceIdList) ");
    	   }
    	   if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
    		   sb.append(" and ( model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) or ( model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ))" +
    		   		" ");
    	   }else if(printMediaIdList != null && printMediaIdList.size()>0){
    		   sb.append(" and model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) ");
    	   }else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
    		   sb.append(" and model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ");
    	   }
    	   /*if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
    		   sb.append(" and model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ");
    	   }*/
    	    Query query = getSession().createQuery(sb.toString());
    	    
    	    if(deptId != null && deptId.longValue() > 0L){
    	    	query.setParameter("deptId",deptId);
    	    }else{
    	    	if(departmentIds != null && departmentIds.size()>0){
        	    	query.setParameterList("departmentIds", departmentIds);
        	    }
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
    	    if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
    	    	query.setParameter("govtDepartmentScopeId",govtDepartmentScopeId);
    	    }
    	    if(impactLevelIdList != null && impactLevelIdList.size()>0){
    	    	query.setParameterList("impactLevelIdList", impactLevelIdList);
     	   }
     	   if(priorityIdList != null && priorityIdList.size()>0){
     		  query.setParameterList("priorityIdList", priorityIdList);
     	   }
     	   if(alertSourceIdList != null && alertSourceIdList.size()>0){
     		  query.setParameterList("alertSourceIdList", alertSourceIdList);
     	   }
     	   if(printMediaIdList != null && printMediaIdList.size()>0){
   	    	  query.setParameterList("printMediaIdList", printMediaIdList);
   	       }
     	   if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
   	    	  query.setParameterList("electronicMediaIdList", electronicMediaIdList);
   	       }
     	  
    	    return query.list();
    	}
    	
      @SuppressWarnings("unchecked")
	public List<Object[]> getAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate){
  		StringBuilder sb = new StringBuilder();  
  		
  	     sb.append("select ");
  	    
  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
  	    
  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName, " );
   	   
  	     sb.append(" count(distinct model.alert.alertId) ");
  	      
  	     sb.append(" from AlertAssignedOfficerNew model ");
  	     
  	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");

  	    if(fromDate != null && toDate != null)
  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
  	  
    sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
    		" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
     	    
  	    Query query = getSession().createQuery(sb.toString());
  	    if(fromDate != null && toDate != null){
  	        query.setDate("fromDate", fromDate);
  	        query.setDate("toDate", toDate);
  	    }
  	  return query.list();
  	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,Long deptId,List<Long> printIdsList,
			List<Long> electronicIdsList,List<Long> calCntrIdList,Long levelId,List<Long> levelValues){
	  		StringBuilder sb = new StringBuilder();  
	  		 sb.append("select ");
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	  	      		   " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
	  	      		   " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     sb.append(" from AlertAssignedOfficerNew model  ");
	  	     sb.append(" left join model.alert.edition EDS " +
	  	    		   " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA ");
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId and model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	    if(deptId != null && deptId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
	  	    }

	  	   if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
     	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
     	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
 	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
 	  			}else{
 	  				sb.append(" and model.alert.alertCallerId is null ");
 	  			}
 	    	      sb.append(" )");
     	    }
		  	 if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	   	      sb.append(" and UA.stateId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	   	      sb.append(" and UA.zoneId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	   	      sb.append(" and UA.regionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	   	      sb.append(" and UA.circleId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	   	      sb.append(" and UA.districtId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	   	      sb.append(" and UA.divisionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	   	      sb.append(" and UA.subDivisionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	     	  sb.append(" and UA.tehsilId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	     	  sb.append(" and UA.localElectionBodyId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	     	  sb.append(" and UA.panchayatId in (:levelValues)");
	  	     
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	       
	  	     if(levelId != null && levelId > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:levelId "); 
	  	     }
	  	      
	  	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
	    	           " model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
	     	    
	  	    Query query = getSession().createQuery(sb.toString());
	  	    
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(deptId != null && deptId.longValue() > 0){
	  	    	query.setParameter("deptId", deptId);
	  	    }
	  	   if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
    	      query.setParameterList("printIdList", printIdsList);
    	      query.setParameterList("electronicIdList", electronicIdsList);
    	    }
		  	if(levelId != null && levelValues != null && !levelValues.isEmpty()){
	  			query.setParameterList("levelValues",levelValues);
	  		}
		  	if(levelId != null && levelId.longValue() > 0){
		  		query.setParameter("levelId",levelId);  
		  	}
	  	    return query.list();
	  	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long levelId,Long deptId,
			Long filterLevelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      "  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.alertStatus.alertStatusId," +
	  	      		    " model.alertStatus.alertStatus," +
	  	      		    " model.alertStatus.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from AlertAssignedOfficerNew model left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA  ");
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId " +
	  	     		   " and model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	     if(levelId != null && levelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:levelId ");
	  	     }
	  	     if(deptId != null && deptId.longValue() >0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId " );
	  	     }
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	    if(filterLevelId != null && filterLevelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId =:filterLevelId ");
	  	     }
	  	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
      	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
  	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
  	  			}else{
  	  				sb.append(" and model.alert.alertCallerId is null ");
  	  			}
  	    	      sb.append(" )");
      	    }
	  	     if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    	      sb.append(" and UA.stateId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
    	      sb.append(" and UA.zoneId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
    	      sb.append(" and UA.regionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
    	      sb.append(" and UA.circleId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
    	      sb.append(" and UA.districtId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.divisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.subDivisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	      sb.append(" and UA.tehsilId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	      sb.append(" and UA.panchayatId in (:levelValues)");
	  	    
	  	  
	  	    sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId,  " +
	  	    		  " model.alertStatus.alertStatusId " );
	     	    
	  	    Query query = getSession().createQuery(sb.toString());
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(levelId != null && levelId.longValue() > 0){
	  	    	 query.setParameter("levelId",levelId);
	  	    }
	  	  if(deptId != null && deptId.longValue() >0){
	  		  query.setParameter("deptId",deptId);
	  	  }
	  	  if(filterLevelId != null && filterLevelId.longValue() > 0){
	  		query.setParameter("filterLevelId",filterLevelId);
  	      }
	 	 if(levelId != null && levelValues != null && !levelValues.isEmpty()){
  			query.setParameterList("levelValues",levelValues);
  		}
	  	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
  	      query.setParameterList("printIdList", printIdsList);
  	      query.setParameterList("electronicIdList", electronicIdsList);
  	    }
	  	
	  	  return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId,
			Date fromDate,Date toDate,Long desigDeptOfficerId,Long officerId,Long levelId,List<Long> levelValues,
			List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.alert.alertId "+
				  " from " +
				  " AlertAssignedOfficerNew model left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA  " +
				  " where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId and " +
				  " model.isDeleted = 'N' " +
				  " and model.alert.isDeleted = 'N'");
		if(deptId != null && deptId.longValue() >0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() > 0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
		}
		if(statusId != null && statusId.longValue() >0){
			sb.append(" and model.alertStatus.alertStatusId = :statusId ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
		}
		if(desigDeptOfficerId != null && desigDeptOfficerId.longValue() > 0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId =:desigDeptOfficerId ");
		}
		if(officerId != null && officerId.longValue() > 0){
			sb.append(" and model.govtOfficer.govtOfficerId = :officerId " );
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
  	      sb.append(" and UA.stateId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	      sb.append(" and UA.zoneId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	      sb.append(" and UA.regionId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	      sb.append(" and UA.circleId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	      sb.append(" and UA.districtId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	      sb.append(" and UA.divisionId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	      sb.append(" and UA.subDivisionId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
    	      sb.append(" and UA.tehsilId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
    	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
    	      sb.append(" and UA.panchayatId in (:levelValues)");
		
		if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
    	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	  			}else{
	  				sb.append(" and model.alert.alertCallerId is null ");
	  			}
	    	      sb.append(" )");
    	    }
		//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId ");
		Query query = getSession().createQuery(sb.toString());
		if(deptId != null && deptId.longValue() >0)
		      query.setParameter("deptId",deptId);
		if(locationLevelId != null && locationLevelId.longValue() > 0)
		      query.setParameter("locationLevelId",locationLevelId);
		if(statusId != null && statusId.longValue() >0){
			query.setParameter("statusId",statusId);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate);
			query.setDate("toDate",toDate);
		}
		 if(desigDeptOfficerId != null && desigDeptOfficerId.longValue() > 0){
			query.setParameter("desigDeptOfficerId",desigDeptOfficerId);
		 }
		 if(officerId != null && officerId.longValue() > 0){
		 	query.setParameter("officerId",officerId);
		 }
		  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	   	      query.setParameterList("printIdList", printIdsList);
	   	      query.setParameterList("electronicIdList", electronicIdsList);
	   	  }
		  if(levelId != null && levelValues != null && !levelValues.isEmpty()){
	  			query.setParameterList("levelValues",levelValues);
	  	  } 
		//	query.setParameter("scopeId",scopeId);
		
		return query.list();
	}
       /* public List<Object[]> getAlertDetailsForDistrictOfficer(Date fromDate, Date toDate, Long stateId, 
        		List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,String dateType){
    		StringBuilder sb = new StringBuilder();  
    	    sb.append("select ");
    	   
    	      sb.append(" model.alert.alertId, model.alert.description,EDS.editionAlias,TNC.channelName,model.alert.alertType.alertTypeId," +
    	      		" model.alert.alertCategory.alertCategoryId,date(model.insertedTime) " );
    	    
    	      
    	    sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC " +
    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
    	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
    	    
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
    	    
    	    if(dateType != null && dateType.equalsIgnoreCase("pastData")){
    	    	if(fromDate != null){
    	    		sb.append(" and date(model.insertedTime) <=  :fromDate");
    	    	}
    	    }else{
    	    	if(fromDate != null && toDate != null){
    	    		sb.append(" and date(model.insertedTime) between  :fromDate and ");
    	    	}
    	    }
    	 
    	    if(fromDate != null && toDate != null)
    	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    	      sb.append(" and UA.stateId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
    	      sb.append(" and UA.zoneId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
    	      sb.append(" and UA.regionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
    	      sb.append(" and UA.circleId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
    	      sb.append(" and UA.districtId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.divisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
    	      sb.append(" and UA.subDivisionId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	      sb.append(" and UA.tehsilId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	      sb.append(" and UA.panchayatId in (:levelValues)");
    	
    	  
    	    Query query = getSession().createQuery(sb.toString());
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      query.setParameterList("departmentIds", departmentIds);
    	 
    	    if(fromDate != null && toDate != null){
    	        query.setDate("fromDate", fromDate);
    	        query.setDate("toDate", toDate);
    	    }
    	    
    	    
    	    if(dateType != null && dateType.equalsIgnoreCase("pastData")){
    	    	if(fromDate != null){
    	    		query.setDate("fromDate", fromDate);
    	    	}
    	    }else{
    	    	if(fromDate != null && toDate != null){
    	    		query.setDate("fromDate", fromDate);
    	    		query.setDate("toDate", toDate);
    	    	}
    	    }
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty())
    	        query.setParameterList("levelValues", levelValues);
    	    
    	  
    	      return query.list();
    	}*/
        
        public List<Object[]> getSubOrdinateAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
    			List<Long> desigIds,Long priorityId,String type){
        	
        	StringBuilder sb = new StringBuilder();  
    	    sb.append("select model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
    	    		"model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
    	    		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName,count(distinct  model.alert.alertId) ");
    	    
    	    	if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
        	      sb.append(" , S.locationValue,S.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
        	      sb.append(" , Z.locationValue,Z.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
        	      sb.append(" , R.locationValue,R.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
        	      sb.append(" , C.locationValue,C.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
        	      sb.append(" , D.locationValue,D.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
        	      sb.append(" , DIV.locationValue,DIV.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
        	      sb.append(" , SUBDIV.locationValue,SUBDIV.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
          	      sb.append(" , T.locationValue,T.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
          	      sb.append(" , LEB.locationValue,LEB.locationName ");
        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
          	      sb.append(" , P.locationValue,P.locationName ");
        	
        	sb.append(" from AlertAssignedOfficerNew model " +
        			" left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
    	          " left join UA.state S " +
    	          " left join UA.zone Z " +
    	          " left join UA.region R " +
    	          " left join UA.circle C " +
    	          " left join UA.district D " +
    	          " left join UA.division DIV " +
    	          " left join UA.subDivision SUBDIV " +
    	          " left join UA.tehsil T" +
    	          " left join UA.localElectionBody LEB " +
    	          " left join UA.panchayat P " );
    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
  	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
    	    
    	    if(desigIds != null && !desigIds.isEmpty())
      	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:desigIds)");
    	    
    	    if(govtScopeIds != null && !govtScopeIds.isEmpty())
    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
    	   
    	    if(locationValues != null && !locationValues.isEmpty())
    	    	sb.append("  and model.govtDepartmentDesignationOfficer.levelValue in (:locationValues) ");
    	    
    	    if(priorityId != null && priorityId.longValue() >0l)
    	    	sb.append("  and model.alert.alertSeverityId = :priorityId " );
    	    
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
      	      sb.append(" and UA.stateId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	      sb.append(" and UA.zoneId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	      sb.append(" and UA.regionId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	      sb.append(" and UA.circleId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	      sb.append(" and UA.districtId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	      sb.append(" and UA.divisionId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	      sb.append(" and UA.subDivisionId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
        	      sb.append(" and UA.tehsilId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
        	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
        	      sb.append(" and UA.panchayatId in (:levelValues)");
    	    
    	    if(type != null && type.equalsIgnoreCase("completedAlerts")){
    	    	sb.append("  and model.alertStatus.alertStatusId = 4 ");
    	    }
    	    
    	    sb.append(" group by  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId,model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
    	    		" model.govtDepartmentDesignationOfficer.levelValue ");
    	    Query query = getSession().createQuery(sb.toString());
    	    
    	    if(govtScopeIds != null && !govtScopeIds.isEmpty())
    	      query.setParameterList("govtScopeIds", govtScopeIds);
    	    
    	    if(desigIds != null && !desigIds.isEmpty())
    	    	 query.setParameterList("desigIds", desigIds);
    	    
    	    if(locationValues != null && !locationValues.isEmpty())
    	    	query.setParameterList("locationValues", locationValues);
    	    
    	    if(fromDate != null && endDate != null){
    	    	query.setDate("fromDate", fromDate);
    	    	query.setDate("endDate", endDate);
    	    }
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty())
    	    		query.setParameterList("levelValues", levelValues);
    	    
    	    if(priorityId != null && priorityId.longValue() >0l)
    	    	query.setParameter("priorityId", priorityId);
    	    
    	    return query.list();
    	    	
        }
        
        public List<Long> getDistrictOfficerAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date fromDate,Date toDate){
        	StringBuilder sb = new StringBuilder();
	    	  
        	  sb.append(" select  distinct model.alert.alertId from AlertAssignedOfficerNew model ");
        	  
        	  sb.append(" left join model.alert.edition EDS " +
        	          " left join model.alert.tvNewsChannel TNC ");
        	
        	  sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );// and model.isApproved='Y'
        	
        	  
        	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
                  sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) )");
                  if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
                      sb.append(" or model.alert.alertCallerId is not null ");
                }else{
                  sb.append(" and model.alert.alertCallerId is null ");
                }
                    sb.append(" )");
                }
        	  
        	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
        	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and date(model.insertedTime) between :todayDate  and :todayDate " ); 
	    	  }else if(type.equalsIgnoreCase("overAll") && fromDate != null && toDate != null ){
	    		  sb.append(" and date(model.insertedTime) between :fromDate  and :toDate " );
	    	  }
	    	  
	    	  
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today") ){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }else if(type.equalsIgnoreCase("overAll") && fromDate != null && toDate != null){
	    		  query.setParameter("fromDate",fromDate);
	    		  query.setParameter("toDate",toDate);
	    	  }
	    	  
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdsList", printIdsList);
	    	      query.setParameterList("electronicIdsList", electronicIdsList);
	    	    }  
	    	    /*else if(printIdsList != null && printIdsList.size()>0){
	    	      query.setParameterList("printIdList", printIdsList);
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }*/
	    	  return query.list();
        }
       
        public List<Long> getAlertAssignedOfficerId(Long alertId){
        	
        	Query query = getSession().createQuery(" select model.alertAssignedOfficerId from AlertAssignedOfficerNew model " +
        			" where model.alertId = :alertId" +
        			" and model.isDeleted = 'N' " +
        			" and model.isApproved ='Y' " +
        			" order by model.alertAssignedOfficerId desc  ");
        	
        	query.setParameter("alertId", alertId);
        			 
        	return query.list();
        }
        public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select ");
        	queryStr.append(" GDWL.govt_department_work_location_id as govtDepartmentWorkLocationId, ");
        	queryStr.append(" GDWL.govt_department_scope_id as parentGovtDepartmentScopeId, ");
        	queryStr.append(" GDWL.location_name as locationName, ");
        	queryStr.append(" GDS.govt_department_scope_id as govtDepartmentScopeId, ");
        	queryStr.append(" GDS.level_name as levelName, ");
        	queryStr.append(" count(distinct AAO.alert_id) as count,");
        	queryStr.append(" GDS.color as color");
    		queryStr.append(" from ");  
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_department_designation_new GDD, ");
    		queryStr.append(" govt_department_scope GDS, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" govt_department GD, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location_relation GDWLR ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y'  ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDS.govt_department_scope_id = GDDO.govt_department_scope_id  ");
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDDO.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		queryStr.append(" and GDDO.govt_department_designation_id = GDD.govt_department_designation_id  ");
    		queryStr.append(" and GUA.user_address_id = GDDO.address_id  ");
    		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
    		queryStr.append(" and GD.govt_department_id = GDD.govt_department_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_work_location_id=GDWLR.parent_govt_department_work_location_id   ");
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GD.govt_department_id = :govtDepartmentId   ");
    		}
    		queryStr.append(" and GDDO.level_value=GDWLR.govt_department_work_location_id    ");
    		
    		
    		queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
      	    
    		queryStr.append(" group by GDWL.govt_department_work_location_id, GDS.govt_department_scope_id ");
    		
    		
    		Query query = getSession().createSQLQuery(queryStr.toString())
    				.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG)
    				.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG)
    				.addScalar("locationName", Hibernate.STRING)
    				.addScalar("govtDepartmentScopeId", Hibernate.LONG)
    				.addScalar("levelName", Hibernate.STRING)
    				.addScalar("count", Hibernate.LONG)
    				.addScalar("color", Hibernate.STRING);
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList", deptScopeIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		return query.list(); 
        	
        	
        }
        public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCountForStatus(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select ");
        	if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
        		queryStr.append(" GDWL.govt_department_work_location_id as govtDepartmentWorkLocationId, ");
            	queryStr.append(" GDWL.govt_department_scope_id as parentGovtDepartmentScopeId, ");
            	queryStr.append(" GDWL.location_name as locationName, ");
            	queryStr.append(" GDS.govt_department_scope_id as govtDepartmentScopeId, ");
            	queryStr.append(" GDS.level_name as levelName, ");
            	queryStr.append(" AAO.alert_status_id as alertStatusId, ");
            	queryStr.append(" ALTS.alert_status as alertStatus, ");
            	queryStr.append(" count(distinct AAO.alert_id) as count,");
            	queryStr.append(" GDS.color as color");
        	}else{
        		queryStr.append(" GDWL.govt_department_work_location_id as govtDepartmentWorkLocationId, ");
            	queryStr.append(" GDWL.govt_department_scope_id as parentGovtDepartmentScopeId, ");
            	queryStr.append(" GDWL.location_name as locationName, ");
            	queryStr.append(" AAO.alert_status_id as alertStatusId, ");
            	queryStr.append(" ALTS.alert_status as alertStatus, ");
            	queryStr.append(" count(distinct AAO.alert_id) as count,");
            	queryStr.append(" ALTS.alert_color as color");
        	}
        	
    		queryStr.append(" from ");  
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_department_designation_new GDD, ");
    		queryStr.append(" govt_department_scope GDS, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" govt_department GD, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location_relation GDWLR ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y'  ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDS.govt_department_scope_id = GDDO.govt_department_scope_id  ");
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDDO.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		queryStr.append(" and GDDO.govt_department_designation_id = GDD.govt_department_designation_id  ");
    		queryStr.append(" and GUA.user_address_id = GDDO.address_id  ");
    		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
    		queryStr.append(" and GD.govt_department_id = GDD.govt_department_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_work_location_id=GDWLR.parent_govt_department_work_location_id   ");
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GD.govt_department_id = :govtDepartmentId   ");
    		}
    		queryStr.append(" and GDDO.level_value=GDWLR.govt_department_work_location_id    ");
    		
    		if(fromDate != null && toDate != null){
    		queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    			queryStr.append(" group by GDWL.govt_department_work_location_id, GDS.govt_department_scope_id,AAO.alert_status_id ");
    		}else{
    			queryStr.append(" group by GDWL.govt_department_work_location_id, AAO.alert_status_id ");
    		}
    		
    		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    			query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
        		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
        		query.addScalar("locationName", Hibernate.STRING);
        		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
        		query.addScalar("levelName", Hibernate.STRING);
        		query.addScalar("alertStatusId", Hibernate.LONG);
        		query.addScalar("alertStatus", Hibernate.STRING);
        		query.addScalar("count", Hibernate.LONG);
        		query.addScalar("color", Hibernate.STRING);
    		}else{
    			query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
        		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
        		query.addScalar("locationName", Hibernate.STRING);
        		query.addScalar("alertStatusId", Hibernate.LONG);
        		query.addScalar("alertStatus", Hibernate.STRING);
        		query.addScalar("count", Hibernate.LONG);
        		query.addScalar("color", Hibernate.STRING);
    		}
    		
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList", deptScopeIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		return query.list();
        }
        public List<Object[]> getDeptList(Long userId){
        	Query query = getSession().createQuery(" select distinct " +
        			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
        			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " +
        			" from AlertAssignedOfficerNew model " +
        			" where " +
        			" model.govtDepartmentDesignationOfficer.userId = :userId");
        	query.setParameter("userId", userId);
        	return query.list();
        }
        
        public List<Object[]> getDistrictOfficerScopesWiseAlerts(Date fromDate,Date toDate,Long stateId,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select ");
        	
        		queryStr.append(" GDWL.govt_department_work_location_id as govtDepartmentWorkLocationId, ");
            	queryStr.append(" GDWL.govt_department_scope_id as parentGovtDepartmentScopeId, ");
            	queryStr.append(" GDWL.location_name as locationName, ");
            	queryStr.append(" GDS.govt_department_scope_id as scopeId, ");
            	queryStr.append(" GDS.level_name as scope, ");
            	queryStr.append(" count(distinct AAO.alert_id) as count,");
            	queryStr.append(" GDS.color as color");
        	
        	queryStr.append(" from ");  
    		queryStr.append(" alert A ");
    		
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_department_designation_new GDD, ");
    		queryStr.append(" govt_department_scope GDS, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" govt_department GD, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location_relation GDWLR ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y'  ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDS.govt_department_scope_id = GDDO.govt_department_scope_id  ");
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDDO.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		queryStr.append(" and GDDO.govt_department_designation_id = GDD.govt_department_designation_id  ");
    		queryStr.append(" and GUA.user_address_id = GDDO.address_id  ");
    		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
    		queryStr.append(" and GD.govt_department_id = GDD.govt_department_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_work_location_id=GDWLR.parent_govt_department_work_location_id   ");
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GD.govt_department_id = :govtDepartmentId   ");
    		}
    		queryStr.append(" and GDDO.level_value=GDWLR.govt_department_work_location_id    ");
    		
    		if(fromDate != null && toDate != null){
    			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
    		
    		queryStr.append(" group by GDWL.govt_department_work_location_id, AAO.alert_status_id ");
    		
    		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    		
    			query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
        		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
        		query.addScalar("locationName", Hibernate.STRING);
        		query.addScalar("scopeId", Hibernate.LONG);
        		query.addScalar("scope", Hibernate.STRING);
        		query.addScalar("count", Hibernate.LONG);
        		query.addScalar("color", Hibernate.STRING);
    		
    		
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList", deptScopeIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		return query.list();
        }
        public List<Object[]> getAssignedOfficersDetails(Long alertId){
    		Query query = getSession().createQuery("select distinct model.alertAssignedOfficerId," +
    											" model.govtOfficer.officerName ," +
    											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
    											" model.govtOfficer.mobileNo," +
    											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName," +
    											" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName " +
    											" from AlertAssignedOfficerNew model " +
    											" where model.alert.alertId = :alertId" +
    											" and model.isDeleted = 'N'" +
    											" and model.isApproved = 'Y'");
    		query.setParameter("alertId", alertId);
    		return query.list();
    	}
        public List<Long> getLocationThenGovtDeptScopeWiseAlertCountOnClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long childLocationId){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select distinct AAO.alert_id as id");
    		queryStr.append(" from ");  
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_department_designation_new GDD, ");
    		queryStr.append(" govt_department_scope GDS, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" govt_department GD, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location_relation GDWLR ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y'  ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDS.govt_department_scope_id = GDDO.govt_department_scope_id  ");
    		if(locationId != null && locationId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_work_location_id = :locationId ");
    		}
    		if(childLocationId != null && childLocationId.longValue() > 0L){
    			queryStr.append(" and GDS.govt_department_scope_id = :childLocationId  ");
    		}
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDDO.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		queryStr.append(" and GDDO.govt_department_designation_id = GDD.govt_department_designation_id  ");
    		queryStr.append(" and GUA.user_address_id = GDDO.address_id  ");
    		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
    		queryStr.append(" and GD.govt_department_id = GDD.govt_department_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_work_location_id=GDWLR.parent_govt_department_work_location_id   ");
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GD.govt_department_id = :govtDepartmentId   ");
    		}
    		queryStr.append(" and GDDO.level_value=GDWLR.govt_department_work_location_id    ");
    		
    		
    		queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
      	    
    		//queryStr.append(" group by GDWL.govt_department_work_location_id, GDS.govt_department_scope_id ");
    		
    		
    		Query query = getSession().createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.LONG);
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		
    		if(childLocationId != null && childLocationId.longValue() > 0L){
    			query.setParameter("childLocationId",childLocationId);
    		}
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList", deptScopeIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		if(locationId != null && locationId.longValue() > 0){
    			query.setParameter("locationId",locationId);
    		}
    		return query.list();   
        	
        	
        }
        public List<Long> getLocationThenGovtDeptScopeWiseAlertCountForStatusForClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long statusId){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select distinct AAO.alert_id as id");
    		queryStr.append(" from ");  
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_department_designation_new GDD, ");
    		queryStr.append(" govt_department_scope GDS, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" govt_department GD, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location_relation GDWLR ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y'  ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDS.govt_department_scope_id = GDDO.govt_department_scope_id  ");
    		
    		if(statusId != null && statusId.longValue() > 0L){
    			queryStr.append(" and AAO.alert_status_id = :statusId  ");
    		}
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDDO.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		queryStr.append(" and GDDO.govt_department_designation_id = GDD.govt_department_designation_id  ");
    		queryStr.append(" and GUA.user_address_id = GDDO.address_id  ");
    		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
    		queryStr.append(" and GD.govt_department_id = GDD.govt_department_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_work_location_id=GDWLR.parent_govt_department_work_location_id   ");
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    			queryStr.append(" and GDWL.govt_department_work_location_id = 1 ");
    			queryStr.append(" and GDWL.govt_department_scope_id=1   ");
    		}else{
    			if(locationId != null && locationId.longValue() > 0L){
        			queryStr.append(" and GDWL.govt_department_work_location_id = :locationId ");
        		}
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
        			queryStr.append(" and GDWL.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
        		}
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GD.govt_department_id = :govtDepartmentId   ");
    		}
    		queryStr.append(" and GDDO.level_value=GDWLR.govt_department_work_location_id    ");
    		
    		
    		queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
      	    
    		//queryStr.append(" group by GDWL.govt_department_work_location_id, GDS.govt_department_scope_id ");
    		
    		
    		Query query = getSession().createSQLQuery(queryStr.toString()).addScalar("id", Hibernate.LONG);
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		
    		if(statusId != null && statusId.longValue() > 0L){
    			query.setParameter("statusId",statusId);
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList", deptScopeIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    			
    		}else{
    			if(locationId != null && locationId.longValue() > 0){
        			query.setParameter("locationId",locationId);
        		}
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
        			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
        		}
    		}
    		return query.list();   
        	
        	
        }
        
        public List<Object[]> getDesignationOfficerDetails(Long alertId){
        	
        	Query query = getSession().createQuery(" SELECT model1.userId,model2.levelValue  " +
        											" FROM AlertAssignedOfficerNew model,GovtDepartmentDesignationOfficerDetailsNew model1,GovtAlertDepartmentLocationNew model2 " +
        											" WHERE model.isDeleted ='N' " +
        											"  and  model1.isDeleted='N' " +
        											" and model.govtDepartmentDesignationOfficerId = model1.govtDepartmentDesignationOfficerId" +
        											" and model2.userId = model1.userId " +
        											" and model.govtOfficerId = model1.govtOfficerId" +
        											" and model.alertId  = :alertId " +
        											" and model.isApproved = 'Y' " +
        											" GROUP BY model1.userId,model2.levelValue  ");
        	
        	query.setParameter("alertId", alertId);
        	return query.list();
        }
        //state and district scope lvl
        public List<Object[]> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,
        		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
        		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,String searchType,List<Long> calCntrIds){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select ");
        	
        	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
        	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
        	queryStr.append(" GDWL1.location_name as locationName, ");//2
        	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
        		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
        			queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3
        		}else{
        			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
        				queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
        			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
        				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
        			}
        		}
        		
        	}else{
        		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
        	}
        	
        	queryStr.append(" count(distinct AAO.alert_id) as count");
        	
    		queryStr.append(" from ");
    		
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location GDWL1 ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
    		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
    		
    		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
    		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
			}
			
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			queryStr.append(" and  GDWL1.govt_department_work_location_id = :districtWorkLocationId");
    		}
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
    		}
    		
    		
    		if(fromDate != null && toDate != null){
    			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
    			 if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
    				queryStr.append(" or A.alert_caller_id is not null ");
    			}else{
    				queryStr.append(" or A.alert_caller_id is null ");
    			}
    			queryStr.append(" )");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
    		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_status_id ");
    			}else{
    				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
    					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
        			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
        				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
        			}
    				
    			}
    			
        	}else{
        		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
        	}
    		
    		
    		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
    		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("locationName", Hibernate.STRING);
    		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    				query.addScalar("GDSI", Hibernate.LONG);
    			}
    		}
    		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
    		query.addScalar("count", Hibernate.LONG);
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList",deptScopeIdList);
    		}
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			query.setParameter("districtWorkLocationId",districtWorkLocationId);
    		}
    		return query.list();
        }
        //division scope lvl
        public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
        		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
        		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long justUpperLvl){
        	StringBuilder queryStr = new StringBuilder();
        	queryStr.append(" select ");
        	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
        	if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
        		queryStr.append(" GDWL2.govt_department_work_location_id as parentGovtDepartmentWorkLocationId, ");
        		queryStr.append(" GDWL2.location_name as DISTRICT , ");
        	}
        	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
        	queryStr.append(" GDWL1.location_name as locationName, ");//2
        	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
        		if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
        			queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
    			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
    				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
    			}  
        		
        	}else{
        		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
        	}
        	queryStr.append(" count(distinct AAO.alert_id) as count");//4
        	
    		queryStr.append(" from ");
    		
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location GDWL1, ");
    		queryStr.append(" govt_department_work_location GDWL2 ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
    		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
    		
    		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
    		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
    		
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		//queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");->modified as dynamic
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
			}
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		//queryStr.append(" and GDWL2.govt_department_work_location_id = GTA.district_id and GDWL2.govt_department_scope_id=5  ");->modified as dynamic
    		
    		if(justUpperLvl != null && justUpperLvl.longValue() == 2L){
    			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.zone_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 3L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 4L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 5L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 6L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 7L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 8L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 9L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}else if(justUpperLvl != null && justUpperLvl.longValue() == 10L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
			}
    		
    		
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
    		}
    		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
    			queryStr.append(" and   GDWL1.govt_department_work_location_id = :divisionWorkLocationId");
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
    		}
    		
    		
    		if(fromDate != null && toDate != null){
    			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		}
    		 if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
    			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
    				queryStr.append(" or A.alert_caller_id is not null ");
    			}else{
    				queryStr.append(" or A.alert_caller_id is null ");
    			}
    			queryStr.append(" )");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
      	    
    		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    			
    			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
    			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
    				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
    			}
        	}else{
        		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
        	}
    		
    		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
    		if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
    			query.addScalar("parentGovtDepartmentWorkLocationId", Hibernate.LONG);
        		query.addScalar("DISTRICT", Hibernate.STRING);
        	}
    		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("locationName", Hibernate.STRING);
    		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
    		query.addScalar("count", Hibernate.LONG);
    		
    		
    		
    		
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);  
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList",deptScopeIdList);
    		}
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			query.setParameter("districtWorkLocationId",districtWorkLocationId);
    		}
    		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
    			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
    		}
    		if(justUpperLvl != null && justUpperLvl.longValue() > 0L){
    			query.setParameter("justUpperLvl",justUpperLvl);
			}
    		return query.list();
        }
        //sub division scope lvl
        public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
        		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
        		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long levelTwo,Long levelThree){
        	StringBuilder queryStr = new StringBuilder();    
        	queryStr.append(" select ");  
        	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
        	if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
        		queryStr.append(" GDWL2.govt_department_work_location_id as parentGovtDepartmentWorkLocationId, ");
        		queryStr.append(" GDWL2.location_name as DISTRICT , ");
        		queryStr.append(" GDWL3.govt_department_work_location_id as GDWLI, ");
        		queryStr.append(" GDWL3.location_name as DIVISION, ");
        	}
        	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
        	queryStr.append(" GDWL1.location_name as locationName, ");//2
        	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
        		if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
    				queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
    			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
    				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
    			}
        	}else{
        		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
        	}
        	queryStr.append(" count(distinct AAO.alert_id) as count");//4
        	
    		queryStr.append(" from ");
    		
    		queryStr.append(" alert A ");
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
    			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
    			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
    		}
    		queryStr.append(" ,alert_status ALTS, ");
    		queryStr.append(" alert_assigned_officer_new AAO, ");
    		queryStr.append(" govt_department_designation_officer_new GDDO, ");
    		queryStr.append(" govt_user_address GUA, ");
    		queryStr.append(" alert_category ALTC, ");
    		queryStr.append(" alert_type ALTT, ");
    		
    		queryStr.append(" govt_department_work_location GDWL, ");
    		queryStr.append(" govt_department_work_location GDWL1, ");
    		queryStr.append(" govt_department_work_location GDWL2, ");
    		queryStr.append(" govt_department_work_location GDWL3 ");
    		
    		queryStr.append(" where ");
    		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
    		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
    		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
    		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
    		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
    		
    		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
    		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
    		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
    		
    		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
    		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
    		
    		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
    		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
    		
    		
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
    		}
    		
    		
    		//queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");->modified as dynamic
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
			}
    		
    		
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
    		}
    		
    		//queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");->modified to dynamic
    		if(levelTwo != null && levelTwo.longValue() == 2L){
    			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.zone_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 3L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 4L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 5L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 6L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 7L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 8L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 9L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body and GDWL2.govt_department_scope_id = :levelTwo  ");
			}else if(levelTwo != null && levelTwo.longValue() == 10L){
				queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id and GDWL2.govt_department_scope_id = :levelTwo  ");
			}
    		
    		//queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.division_id and GDWL3.govt_department_scope_id = 6 ");->modified to dynamic
    		if(levelThree != null && levelThree.longValue() == 3L){//Before it has joined with GDWL2 
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.region_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 4L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.circle_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 5L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.district_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 6L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.division_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 7L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.sub_division_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 8L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.tehsil_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 9L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.local_election_body and GDWL3.govt_department_scope_id = :levelThree  ");
			}else if(levelThree != null && levelThree.longValue() == 10L){
				queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.panchayat_id and GDWL3.govt_department_scope_id = :levelThree  ");
			}
    		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
    			queryStr.append(" and   GDWL3.govt_department_work_location_id = :divisionWorkLocationId");
    		}
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
    		}
    		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
    			queryStr.append(" and   GDWL1.govt_department_work_location_id = :subDivisionWorkLocationId");
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
    		}
    		
    		
    		if(fromDate != null && toDate != null){
    			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
    			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
    				queryStr.append(" or A.alert_caller_id is not null ");
    			}else{
    				queryStr.append(" or A.alert_caller_id is null ");
    			}
    			queryStr.append(" )");
    		}
    		
    		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
    			queryStr.append(" and GUA.state_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
      	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
      	    	queryStr.append(" and GUA.region_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
      	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
      	    	queryStr.append(" and GUA.district_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
      	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
      	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
      	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
      	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
      	    
    		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
    			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
    				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
    			}
    		}else{
        		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
        	}
    		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
    		if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
    			query.addScalar("parentGovtDepartmentWorkLocationId", Hibernate.LONG);
        		query.addScalar("DISTRICT", Hibernate.STRING);
        		query.addScalar("GDWLI", Hibernate.LONG);
        		query.addScalar("DIVISION", Hibernate.STRING);
        	}
    		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("locationName", Hibernate.STRING);
    		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
    		query.addScalar("count", Hibernate.LONG);
    		
    		if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
    			query.setParameterList("printIdList", printIdList);  
    			query.setParameterList("electronicIdList", electronicIdList);
    		}
    		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
    			query.setParameterList("levelValues",levelValues);  
    		}
    		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    			query.setParameterList("deptScopeIdList",deptScopeIdList);
    		}
    		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
    			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
    		}
    		
    		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
    			query.setParameter("govtDepartmentId",govtDepartmentId);
    		}
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			query.setParameter("districtWorkLocationId",districtWorkLocationId);
    		}
    		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
    			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
    		}
    		
    		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
    			query.setParameter("subDivisionWorkLocationId",subDivisionWorkLocationId);
    		}
    		if(levelTwo != null && levelTwo.longValue() > 0L){
    			query.setParameter("levelTwo",levelTwo);
			}
    		if(levelThree != null && levelThree.longValue() > 0L){
    			query.setParameter("levelThree",levelThree);
			}
    		return query.list();
        }
    @SuppressWarnings("unchecked")
    public List<Object[]> getDistrictOfficerMyAlertsCountView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
        	StringBuilder sb = new StringBuilder();
        	  
        	if(type != null && type.equalsIgnoreCase("today")){
        		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
        				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
        				" count(distinct  model.alert.alertId) ");
        	}else if(type != null && type.equalsIgnoreCase("completed")){
        		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
        				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
        				" count(distinct  model.alert.alertId) ");
        	}
        	sb.append(" from AlertAssignedOfficerNew model ");
          	sb.append(" left join model.alert.edition EDS " +
          			  " left join model.alert.tvNewsChannel TNC ");
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
        		            	
        		    	    	  
        	  if(govtOffcrIds != null && govtOffcrIds.size() > 0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(startDate != null && endDate!= null){
	    		  sb.append(" and date(model.insertedTime) between  :startDate and  :endDate " ); 
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
	      	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	  	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	  	  			}else{
	  	  				sb.append(" and model.alert.alertCallerId is null ");
	  	  			}
	  	    	      sb.append(" )");
	      	    }
	    	  
	    	  sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    	 
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size() > 0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(startDate != null && endDate!= null){
	    		  query.setDate("startDate", startDate);
	    		  query.setDate("endDate", endDate);
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	   }  
	    	  return query.list();
        }  
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
        	StringBuilder sb = new StringBuilder();
        	sb.append(" select model.alertStatus.alertStatusId," +
        			"  model.alertStatus.alertStatus," +
        			"  model.alertStatus.color," +
        			"  count(distinct model.alert.alertId) ");
        	
        	sb.append(" from AlertAssignedOfficerNew model ");
         	sb.append(" left join model.alert.edition EDS " +
        			  " left join model.alert.tvNewsChannel TNC ");
   
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
        		            	
        		    	    	  
        	  if(govtOffcrIds != null && govtOffcrIds.size() > 0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
	      	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	  	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	  	  			}else{
	  	  				sb.append(" and model.alert.alertCallerId is null ");
	  	  			}
	  	    	      sb.append(" )");
	      	    }
	    	  if(startDate != null && endDate != null){
	    		  sb.append(" and date(model.insertedTime) between :startDate and :endDate ");
	    	  }
	    	  sb.append(" group by model.alertStatus.alertStatusId ");
	    	 
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size() > 0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(startDate != null && endDate != null){
	    		  query.setDate("startDate", startDate);
	    		  query.setDate("endDate", endDate);
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	   } 
	    	  return query.list();
        }
	 //state scope lvl for click 
	
    public List<Long> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long deptScopeId,Long parentGovtDepartmentScopeId, Long statusId,List<Long> calCntrIds){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct AAO.alert_id as count ");  
    	
    	/*queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
    	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
    	queryStr.append(" GDWL1.location_name as locationName, ");//2
    	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    		queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3
    	}else{
    		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
    	}
    	
    	queryStr.append(" count(distinct AAO.alert_id) as count");*/  
    	
		queryStr.append(" from ");
		
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		//for location scope filter.
		if(deptScopeId != null && deptScopeId.longValue() > 0L){//listOfId to id
			queryStr.append(" and GDWL.govt_department_scope_id = :deptScopeId");
		}
		//for status filter
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and AAO.alert_status_id = :statusId");
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
		}else{
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}
		
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" or A.alert_caller_id is null ");
			}
			queryStr.append(" )");
		}
		
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
		
		/*if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
			queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_status_id ");
    	}else{
    		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
    	}
		*/
		
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		/*
		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
		query.addScalar("locationName", Hibernate.STRING);
		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				query.addScalar("GDSI", Hibernate.LONG);
			}
		}
		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
		*/
		query.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);
		}
		if(deptScopeId != null && deptScopeId.longValue() > 0L){
			query.setParameter("deptScopeId",deptScopeId);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		
		return query.list();
    }
  //division scope lvl for click
    public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long govtDepartmentScopeId){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct AAO.alert_id as count");
    	/*queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
    	if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
    		queryStr.append(" GDWL2.govt_department_work_location_id as parentGovtDepartmentWorkLocationId, ");
    		queryStr.append(" GDWL2.location_name as DISTRICT , ");
    	}
    	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
    	queryStr.append(" GDWL1.location_name as locationName, ");//2
    	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    		queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
    	}else{
    		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
    	}
    	queryStr.append(" count(distinct AAO.alert_id) as count");//4
*/    	
		queryStr.append(" from ");
		
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		
		
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
		}
		
		
		queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");
		
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL1.govt_department_work_location_id = :divisionWorkLocationId");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
	    		queryStr.append(" and AAO.alert_status_id = :govtDepartmentScopeId, ");//3
	    	}else{
	    		queryStr.append(" and GDWL.govt_department_scope_id = :govtDepartmentScopeId, ");//3
	    	}  
		}
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
  	    
		/*if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
			queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
    	}else{
    		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
    	}*/
		
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
		/*if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
			query.addScalar("parentGovtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("DISTRICT", Hibernate.STRING);
    	}
		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
		query.addScalar("locationName", Hibernate.STRING);
		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);*/
		query.addScalar("count", Hibernate.LONG);
		
		
		
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);  
		}
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			query.setParameterList("deptScopeIdList",deptScopeIdList);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			query.setParameter("districtWorkLocationId",districtWorkLocationId);
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
		}
		if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
			query.setParameter("govtDepartmentScopeId",govtDepartmentScopeId);
		}
		
		return query.list();
    } 
  //sub division scope lvl for click
    public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long govtDepartmentScopeId){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select ");
    	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
    	if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
    		queryStr.append(" GDWL2.govt_department_work_location_id as parentGovtDepartmentWorkLocationId, ");
    		queryStr.append(" GDWL2.location_name as DISTRICT , ");
    		queryStr.append(" GDWL3.govt_department_work_location_id as GDWLI, ");
    		queryStr.append(" GDWL3.location_name as DIVISION, ");
    	}
    	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
    	queryStr.append(" GDWL1.location_name as locationName, ");//2
    	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
    		queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
    	}else{
    		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
    	}
    	queryStr.append(" count(distinct AAO.alert_id) as count");//4
    	
		queryStr.append(" from ");
		
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2, ");
		queryStr.append(" govt_department_work_location GDWL3 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y'  ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		
		
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
		}
		
		
		queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");
		queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.division_id and GDWL3.govt_department_scope_id = 6 ");
		
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL3.govt_department_work_location_id = :divisionWorkLocationId");
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
		}
		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL1.govt_department_work_location_id = :subDivisionWorkLocationId");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
	    		queryStr.append(" and AAO.alert_status_id =:govtDepartmentScopeId, ");//3
	    	}else{
	    		queryStr.append(" and GDWL.govt_department_scope_id = :govtDepartmentScopeId, ");//3
	    	}
		}
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
  	    
		/*if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
			queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
    	}else{
    		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
    	}*/
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
		/*if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
			query.addScalar("parentGovtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("DISTRICT", Hibernate.STRING);
    		query.addScalar("GDWLI", Hibernate.LONG);
    		query.addScalar("DIVISION", Hibernate.STRING);
    	}
		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
		query.addScalar("locationName", Hibernate.STRING);
		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);*/
		query.addScalar("count", Hibernate.LONG);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);  
		}
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			query.setParameterList("deptScopeIdList",deptScopeIdList);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			query.setParameter("districtWorkLocationId",districtWorkLocationId);
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
		}
		
		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
			query.setParameter("subDivisionWorkLocationId",subDivisionWorkLocationId);
		}
		if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
			query.setParameter("govtDepartmentScopeId",govtDepartmentScopeId);
		}
		
    	
		return query.list();
    }


	public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select ");
        queryStr.append(" distinct AAO.alert_id as alertIds ");
             queryStr.append(" from ");  
             queryStr.append(" alert A ");
	   if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
		}
		
		
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
		
		
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			queryStr.append(" and  GDWL1.govt_department_work_location_id = :districtWorkLocationId");
		}
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
	    	    	queryStr.append(" or A.alert_caller_id is not null ");
				}else{
					queryStr.append(" or A.alert_caller_id is null ");
				}
	    	    queryStr.append(" )");
  	    }
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
		
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		  query.addScalar("alertIds", Hibernate.LONG);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);
		}
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			query.setParameterList("deptScopeIdList",deptScopeIdList);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			query.setParameter("districtWorkLocationId",districtWorkLocationId);
		}
		
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
		}
		return query.list();
    }
	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
    		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long justUpperLvl){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select ");
        queryStr.append(" distinct AAO.alert_id as alertIds ");
             queryStr.append(" from ");  
             queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id  and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		
		
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
		}
		
		
		//queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");//
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
		
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		//queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");//
		if(justUpperLvl != null && justUpperLvl.longValue() == 2L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.zone_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 3L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 4L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 5L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 6L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 7L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 8L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 9L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}else if(justUpperLvl != null && justUpperLvl.longValue() == 10L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id and GDWL2.govt_department_scope_id = :justUpperLvl  ");
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL1.govt_department_work_location_id = :divisionWorkLocationId");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}	
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
				queryStr.append(" or (A.alert_caller_id is not null) ");
			}else{
				queryStr.append(" or (A.alert_caller_id is null) ");
			}
			queryStr.append(" )");
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("alertIds", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);  
		}
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			query.setParameterList("deptScopeIdList",deptScopeIdList);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			query.setParameter("districtWorkLocationId",districtWorkLocationId);
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
		}
		if(justUpperLvl != null && justUpperLvl.longValue() > 0L){
			query.setParameter("justUpperLvl",justUpperLvl);
		}
		return query.list();
    }
	public List<Long> getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
    		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
    		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long levelTwo,Long levelThree){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select ");
        queryStr.append(" distinct AAO.alert_id as alertIds ");
             queryStr.append(" from ");  
             queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS, ");
		queryStr.append(" alert_assigned_officer_new AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2, ");
		queryStr.append(" govt_department_work_location GDWL3 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
		
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		
		
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
		}
		
		
		//queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
		
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
		}
		//queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");
		if(levelTwo != null && levelTwo.longValue() == 2L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.zone_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 3L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 4L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 5L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 6L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 7L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 8L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 9L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body and GDWL2.govt_department_scope_id = :levelTwo  ");
		}else if(levelTwo != null && levelTwo.longValue() == 10L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id and GDWL2.govt_department_scope_id = :levelTwo  ");
		}
		
		//queryStr.append(" and GDWL3.govt_department_work_location_id = GUA.division_id and GDWL3.govt_department_scope_id = 6 ");
		if(levelThree != null && levelThree.longValue() == 3L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 4L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 5L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 6L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 7L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 8L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 9L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body and GDWL2.govt_department_scope_id = :levelThree  ");
		}else if(levelThree != null && levelThree.longValue() == 10L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id and GDWL2.govt_department_scope_id = :levelThree  ");
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL3.govt_department_work_location_id = :divisionWorkLocationId");
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL2.govt_department_work_location_id = :districtWorkLocationId");
		}
		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
			queryStr.append(" and   GDWL1.govt_department_work_location_id = :subDivisionWorkLocationId");
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
		}
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0) != 0 ){
				queryStr.append(" or (A.alert_caller_id is not null) ");
			}else{
				queryStr.append(" or (A.alert_caller_id is null) ");
			}
			queryStr.append(" )");
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			queryStr.append(" and GUA.state_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
  	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
  	    	queryStr.append(" and GUA.region_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
  	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
  	    	queryStr.append(" and GUA.district_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
  	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
  	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
  	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
  	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
  	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
  	    
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		
		query.addScalar("alertIds", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
			query.setParameterList("printIdList", printIdList);  
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
			query.setParameterList("levelValues",levelValues);  
		}
		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
			query.setParameterList("deptScopeIdList",deptScopeIdList);
		}
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
		}
		
		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
			query.setParameter("govtDepartmentId",govtDepartmentId);
		}
		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
			query.setParameter("districtWorkLocationId",districtWorkLocationId);
		}
		if(divisionWorkLocationId != null && divisionWorkLocationId.longValue() > 0L){
			query.setParameter("divisionWorkLocationId",divisionWorkLocationId);
		}
		
		if(subDivisionWorkLocationId != null && subDivisionWorkLocationId.longValue() > 0L){
			query.setParameter("subDivisionWorkLocationId",subDivisionWorkLocationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
		}
		if(levelTwo != null && levelTwo.longValue() > 0L){
			query.setParameter("levelTwo",levelTwo);
		}
		if(levelThree != null && levelThree.longValue() > 0L){
			query.setParameter("levelThree",levelThree);
		}
		return query.list();
    }
	 public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsView(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIds){
 		StringBuilder sb = new StringBuilder();  
 	    sb.append("select ");
 	    if(type.equalsIgnoreCase("Status")){
 	      sb.append(" model.alertStatus.alertStatusId, model.alertStatus.alertStatus,model.alertStatus.color," +
 	      		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
 	      		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " );
 	    }else if(type.equalsIgnoreCase("Level")){
 	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
 	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
 	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color " );
  	    }else if(type.equalsIgnoreCase("Department")){
  	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.color ");	
  	    }
 	     sb.append(" ,count(distinct model.alert.alertId) ");
 	      
 	    sb.append(" from AlertAssignedOfficerNew model" +
 	          " left join model.alert.edition EDS " +
 	          " left join model.alert.tvNewsChannel TNC " +
 	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
 	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
 	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
 	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
 	    
 	    if(departmentIds != null && !departmentIds.isEmpty())
 	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
 	    
 	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty()){
 	    	sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
            if( callCenterIds !=null && !callCenterIds.isEmpty() && callCenterIds.get(0) != 0){
                sb.append(" or model.alert.alertCallerId is not null ");
          }else{
            sb.append(" and model.alert.alertCallerId is null ");
          }
              sb.append(" )");
	 }
 	    if(fromDate != null && toDate != null)
 	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
 	    
 	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
 	      sb.append(" and UA.stateId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
 	      sb.append(" and UA.zoneId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
 	      sb.append(" and UA.regionId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
 	      sb.append(" and UA.circleId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
 	      sb.append(" and UA.districtId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
 	      sb.append(" and UA.divisionId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
 	      sb.append(" and UA.subDivisionId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
   	      sb.append(" and UA.tehsilId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
   	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
 	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
   	      sb.append(" and UA.panchayatId in (:levelValues)");
 	    
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	sb.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
 	    }
 	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
 	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:departmentScopeIds)");
 	    }
 	    if(type.equalsIgnoreCase("Status")){
   	      sb.append(" group by model.alertStatus.alertStatusId,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );
   	    }else if(type.equalsIgnoreCase("Level")){
   	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
    	    }else if(type.equalsIgnoreCase("Department")){
    	    	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );	
    	    }
 	  
 	    Query query = getSession().createQuery(sb.toString());
 	    if(departmentIds != null && !departmentIds.isEmpty())
 	      query.setParameterList("departmentIds", departmentIds);
 	   if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty()){
 	      query.setParameterList("printIdList", printIdsList);
 	      query.setParameterList("electronicIdList", electronicIdsList);
 	    }  
 	    /*else if(printIdsList != null && !printIdsList.isEmpty()){
 	      query.setParameterList("printIdList", printIdsList);
 	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
 	      query.setParameterList("electronicIdList", electronicIdsList);
 	    }*/
 	    if(fromDate != null && toDate != null){
 	        query.setDate("fromDate", fromDate);
 	        query.setDate("toDate", toDate);
 	    }
 	    if(levelId != null && levelValues != null && !levelValues.isEmpty())
 	        query.setParameterList("levelValues", levelValues);
 	    
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	 query.setParameterList("alertStatusIds", alertStatusIds);
 	    }
 	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
 	      query.setParameterList("departmentScopeIds", departmentScopeIds);
 	    }
 	      return query.list();
 	}
	 
	 public List<Long> getDistrictOffrAlertsIds(Long govtDeptDesigOffceId,Long govtOffceId,Date fromDate,Date toDate,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	    	StringBuilder sb = new StringBuilder();
	    	  
	    	  sb.append(" select distinct model.alert.alertId from AlertAssignedOfficerNew model ");
	    	
	    	  sb.append(" left join model.alert.edition EDS " +
	    	          " left join model.alert.tvNewsChannel TNC ");
	    	  
	    	  sb.append(" where model.isDeleted = 'N' and  model.alert.isDeleted='N' " );//and model.isApproved='Y'
	    	
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	              sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) )");
		              if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
		                  sb.append(" or model.alert.alertCallerId is not null ");
		            }else{
		              sb.append(" and model.alert.alertCallerId is null ");
		            }
	                sb.append(" )");
	            }
	    	  
	    	  if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  sb.append(" and model.govtOfficer.govtOfficerId = :govtOffceId " );
	    	  }
	    	  if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDeptDesigOffceId " );
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0L){
	    		  sb.append(" and  model.alertStatus.alertStatusId = :statusId");
	  		  }
	    	  if(fromDate != null && toDate != null){
	    		  sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  		  }
	    	  
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
	    		  query.setParameter("govtDeptDesigOffceId", govtDeptDesigOffceId);  
	    	  }
	    	  if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  query.setParameter("govtOffceId", govtOffceId);  
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0L){
	    		  query.setParameter("statusId", statusId); 
	    	  }
	    	  if(fromDate != null && toDate != null){
	  			query.setDate("fromDate", fromDate);
	  			query.setDate("toDate", toDate);
	  		}
	    	  
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdsList", printIdsList);
	    	      query.setParameterList("electronicIdsList", electronicIdsList);
	    	    }  
	    	   
	    	  
	    	  /*if(printIdsList != null && printIdsList.size()>0 && electronicIdsList != null && electronicIdsList.size()>0  && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }  
	    	    else if(printIdsList != null && printIdsList.size()>0){
	    	      query.setParameterList("printIdList", printIdsList);
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }*/
	    	  return query.list();
	    }
	 
	 public List<Long> getGovtDepartmentDesignationOfficer(Long alertId){
     	
     	Query query = getSession().createQuery(" SELECT model.govtDepartmentDesignationOfficerId  " +
     											" FROM AlertAssignedOfficerNew model" +
     											" WHERE model.isDeleted ='N' " +
     											" and model.alertId  = :alertId " +
     											" and model.isApproved = 'Y' " );
     	
     	query.setParameter("alertId", alertId);
     	return query.list();
     }
	 public List<Long> getStateLevelAlertclickViewAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,
			 String type,List<Long> deptIds,Long statusId,Date fromDate,Date endDate){
     	StringBuilder sb = new StringBuilder();
	    	  
     	  sb.append(" select  distinct model.alert.alertId from AlertAssignedOfficerNew model ");
     	
     	  sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
     	
	    	  
     	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
     	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(fromDate != null && endDate!= null){
	    		  sb.append(" and date(model.insertedTime) between :fromDate  and :endDate " ); 
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  sb.append(" and  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) " );
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  sb.append(" and  model.alertStatus.alertStatusId = :statusId " );
	    	  }
	    	  
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(fromDate != null && endDate!= null){
	    		  query.setDate("fromDate", fromDate);
	    		  query.setDate("endDate", endDate);
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  query.setParameter("statusId", statusId);
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  query.setParameterList("deptIds", deptIds);
	    	  }
	    	  return query.list();
     }
	 public Long getGovtDeptScopeIdForAlert(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " +
		 				 " from AlertAssignedOfficerNew model " +
		 				 " where model.alertId =:alertId" +
				 		 " and model.alert.isDeleted = 'N' " +
				 		 " and model.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("alertId", alertId);
		 return (Long) query.uniqueResult();
	 }
	 public Long getGovtDeptDesigOfficerIdListByUserId(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId from AlertAssignedOfficerNew model " +
		 				 " where " +
		 				 " model.isDeleted = 'N' " +
		 				 //" and model.isApproved = 'Y' " +
		 				 " and model.alert.alertId = :alertId " +
		 				 " and model.alert.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("alertId", alertId);
		 return (Long) query.uniqueResult();
	 }
	 public Long getGovtDeptDesigIdListByUserId(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId" +
		 				 " from AlertAssignedOfficerNew model " +
		 				 " where " +
		 				 " model.isDeleted = 'N' " +
		 				 //" and model.isApproved = 'Y' " +
		 				 " and model.alert.alertId = :alertId " +
		 				 " and model.alert.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());  
		 query.setParameter("alertId", alertId);
		 return (Long) query.uniqueResult();
	 }
	 public Integer deleteAssignment(Long alertId){
		 Query query = getSession().createQuery(" delete from AlertAssignedOfficerNew model where model.alertId = :alertId");
		 query.setParameter("alertId", alertId);  
		 return (Integer) query.executeUpdate();
	 }
	 public List<Long> getAssignedDtls(Long alertId){
		 Query query = getSession().createQuery(" select distinct model.alertAssignedOfficerId from  AlertAssignedOfficerNew model where model.alertId = :alertId and " +
		 		" model.isDeleted='N'  ");
		 query.setParameter("alertId", alertId);
		 return query.list();
	 }
	 @SuppressWarnings("unchecked")
		public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,Long statusId,
				Date fromDate,Date toDate,Long levelId,List<Long> levelValues,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList){
			 StringBuilder sb = new StringBuilder();
			sb.append(" select distinct model.alert.alertId "+
					  " from " +
					  " AlertAssignedOfficerNew model " +
					  " left join model.alert.edition EDS " +
					  " left join model.alert.tvNewsChannel TNC " +
					  " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA  " +
					  " where " +
					  " model.isDeleted = 'N' " +
					  " and model.alert.isDeleted = 'N'");
				if(deptIds != null && deptIds.size() >0){
					sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) ");
				}
				if(locationLevelId != null && locationLevelId.longValue() > 0){
					sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
				}
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
		             sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
		             if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
		                 sb.append(" or model.alert.alertCallerId is not null ");
		           }else{
		             sb.append(" and model.alert.alertCallerId is null ");
		           }
		               sb.append(" )");
		        }
			
				if(statusId != null && statusId.longValue() >0){
					sb.append(" and model.alertStatus.alertStatusId = :statusId ");
				}
				if(fromDate != null && toDate != null){
					sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
				}
			   if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	    	      sb.append(" and UA.stateId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	    	      sb.append(" and UA.zoneId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	    	      sb.append(" and UA.regionId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	    	      sb.append(" and UA.circleId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	    	      sb.append(" and UA.districtId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	    	      sb.append(" and UA.divisionId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	    	      sb.append(" and UA.subDivisionId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	      	      sb.append(" and UA.tehsilId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
	    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	      	      sb.append(" and UA.panchayatId in (:levelValues)");
			 
			//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId ");
			 Query query = getSession().createQuery(sb.toString());
			
			if(deptIds != null && deptIds.size() >0){
				query.setParameterList("deptIds",deptIds);
			 }
			      
			 if(locationLevelId != null && locationLevelId.longValue() > 0){
				 query.setParameter("locationLevelId",locationLevelId);
			 }
			     
			 if(statusId != null && statusId.longValue() >0){
				query.setParameter("statusId",statusId);
			  }
			 if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
				query.setParameterList("printIdList",printIdList);
				query.setParameterList("electronicIdList",electronicIdList);
			 }
			 if(fromDate != null && toDate != null){
				query.setDate("fromDate",fromDate);
				query.setDate("toDate",toDate);
			 }
			 if(levelId != null && levelValues != null && levelValues.size() > 0){
				query.setParameterList("levelValues",levelValues);
			 }
			
			//query.setParameter("scopeId",scopeId);
			
			return query.list();
		}
	 
	 public List<Object[]> getAlertAssignedLevelDetails(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.govtDepartmentDesignationOfficer.govtDepartmentScopeId, model.govtDepartmentDesignationOfficer.levelValue " +
		 				 " from AlertAssignedOfficerNew model " +
		 				 " where model.isDeleted = 'N' " +
		 				 " and model.isApproved = 'Y' " +
		 				 " and model.alert.alertId = :alertId " +
		 				 " and model.alert.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());  
		 query.setParameter("alertId", alertId);
		 return  query.list();
	 }
	 
	 //Santosh
     public List<Object[]> getLocationBasedOnDepartmentLevelId(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds){
     	StringBuilder queryStr = new StringBuilder();
     	queryStr.append(" select ");
     	queryStr.append(" distinct GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWL1.location_name as locationName ");//2
     	queryStr.append(" from ");
 		
 		queryStr.append(" alert A ");
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
 		}
 		queryStr.append(" ,alert_status ALTS, ");
 		queryStr.append(" alert_assigned_officer_new AAO, ");
 		queryStr.append(" govt_department_designation_officer_new GDDO, ");
 		queryStr.append(" govt_user_address GUA, ");
 		queryStr.append(" alert_category ALTC, ");
 		queryStr.append(" alert_type ALTT, ");
 		
 		queryStr.append(" govt_department_work_location GDWL, ");
 		queryStr.append(" govt_department_work_location GDWL1 ");
 		
 		queryStr.append(" where ");
 		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
 		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
 		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
 		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
 		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
 		
 		queryStr.append(" and AAO.is_deleted='N' ");
 		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
 		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
 		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
 		
 		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
 		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
 		
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
 		}
 		/*if(stateId != null && stateId.longValue() > 0){
 			queryStr.append(" and GUA.state_id = :stateId ");
 		}*/
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
			
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
 		}
 		
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
 		}
 		
 		if(fromDate != null && toDate != null){
 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 ){
 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
 			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l){
 				queryStr.append(" or A.alert_caller_id is not null ");
 			}else{
 				queryStr.append(" and A.alert_caller_id is null ");
 			}
 			queryStr.append(" )");
 		}
 		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
 			queryStr.append(" and GUA.state_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
   	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
   	    	queryStr.append(" and GUA.region_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
   	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
   	    	queryStr.append(" and GUA.district_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
   	    	queryStr.append(" and GUA.division_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
   	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
   	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
   	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
   	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
 		
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 	
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 		}
 		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
 			query.setParameterList("levelValues",levelValues);
 		}
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			query.setParameterList("deptScopeIdList",deptScopeIdList);
 		}
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
 		}
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			query.setParameter("govtDepartmentId",govtDepartmentId);
 		}
 		/*if(stateId != null && stateId.longValue() > 0){
 			query.setParameter("stateId",stateId);
 		}*/
 		return query.list();
     }
     public List<Object[]> getChildLocationBasedOnParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
     		Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,
     		Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> calCntrIds){
    	 
     	StringBuilder queryStr = new StringBuilder();    
     	queryStr.append(" select distinct ");  
     	queryStr.append(" GDWLC.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWLC.location_name as locationName ");//2
     
    	queryStr.append(" from ");
 		queryStr.append(" alert A ");
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
 		}
 		queryStr.append(" ,alert_status ALTS, ");
 		queryStr.append(" alert_assigned_officer_new AAO, ");
 		queryStr.append(" govt_department_designation_officer_new GDDO, ");
 		queryStr.append(" govt_user_address GUA, ");
 		queryStr.append(" alert_category ALTC, ");
 		queryStr.append(" alert_type ALTT, ");
 		
 		queryStr.append(" govt_department_work_location GDWL, ");
 		queryStr.append(" govt_department_work_location GDWL1 ");
 		if(childLevelId != null && childLevelId.longValue() > 0l){
 			queryStr.append(" ,govt_department_work_location GDWLC ");
 		}
 		
 		queryStr.append(" where ");
 		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
 		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
 		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
 		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
 		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
 		
 		queryStr.append(" and AAO.is_deleted='N' ");
 		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
 		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
 		
 		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
 		
 		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
 		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
 		
 		
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
 		}
 	/*	if(stateId != null && stateId.longValue() > 0){
 			queryStr.append(" and GUA.state_id = :stateId ");
 		}*/
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
	   
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
 		}
 		if(parentGovtDepartmentScopeValue != null && parentGovtDepartmentScopeValue.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_work_location_id=:parentGovtDepartmentScopeValue   ");
 		}
 		
 		 //Child Location
 		   if(childLevelId != null && childLevelId.longValue() == 2L){
 			queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.zone_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 3L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.region_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 4L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.circle_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 5L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.district_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 6L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.division_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 7L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.sub_division_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 8L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.tehsil_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 9L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.local_election_body and GDWLC.govt_department_scope_id = :childLevelId  ");
			}else if(childLevelId != null && childLevelId.longValue() == 10L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.panchayat_id and GDWLC.govt_department_scope_id = :childLevelId  ");
			}
 		
 		
	 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
	 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
	 		}
 		
 		
	 		if(fromDate != null && toDate != null){
	 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
	 		}
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
	 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
	 			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
	 				queryStr.append(" or A.alert_caller_id is not null ");
	 			}else{
	 				queryStr.append(" and A.alert_caller_id is null ");
	 			}
	 			queryStr.append(" )");
	 		}
	 		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	 			queryStr.append(" and GUA.state_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	   	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	   	    	queryStr.append(" and GUA.region_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	   	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	   	    	queryStr.append(" and GUA.district_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	   	    	queryStr.append(" and GUA.division_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	   	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	   	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	   	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	   	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
   	    
 	
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 	
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 		}
 		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
 			query.setParameterList("levelValues",levelValues);  
 		}
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			query.setParameterList("deptScopeIdList",deptScopeIdList);
 		}
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
 		}
 		if(parentGovtDepartmentScopeValue != null && parentGovtDepartmentScopeValue.longValue() > 0L){
 			query.setParameter("parentGovtDepartmentScopeValue",parentGovtDepartmentScopeValue);
 		}
 		/*if(stateId != null && stateId.longValue() > 0){
 			query.setParameter("stateId",stateId);
 		}*/
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			query.setParameter("govtDepartmentId",govtDepartmentId);
 		}
 		if(childLevelId != null && childLevelId.longValue() > 0L){
 			query.setParameter("childLevelId",childLevelId);
		}
 		return query.list();
     }
     
     public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue){
    	 
     	StringBuilder queryStr = new StringBuilder();
     	queryStr.append(" select ");
     	
     	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
     	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWL1.location_name as locationName, ");//2
     	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
     		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
     			queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
     			}
     		}
     	}else{
     		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
     	}
     	
     	queryStr.append(" count(distinct AAO.alert_id) as count");
     	
 		queryStr.append(" from ");
 		
 		queryStr.append(" alert A ");
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
 		}
 		queryStr.append(" ,alert_status ALTS, ");
 		queryStr.append(" alert_assigned_officer_new AAO, ");
 		queryStr.append(" govt_department_designation_officer_new GDDO, ");
 		queryStr.append(" govt_user_address GUA, ");
 		queryStr.append(" alert_category ALTC, ");
 		queryStr.append(" alert_type ALTT, ");
 		
 		queryStr.append(" govt_department_work_location GDWL, ");
 		queryStr.append(" govt_department_work_location GDWL1 ");
 		if(filterParentScopeId != null && filterParentScopeId.longValue() > 0){
 			queryStr.append(" ,govt_department_work_location GDWLP ");	
 		}
 		queryStr.append(" where ");
 		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
 		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
 		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
 		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
 		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
 		
 		queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
 		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
 		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
 		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
 		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
 		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
 		
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
 		}
 		/*if(stateId != null && stateId.longValue() > 0){
 			queryStr.append(" and GUA.state_id = :stateId ");
 		}*/
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
			}
			
			//Parent
			if(filterParentScopeId != null && filterParentScopeId.longValue() == 1L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.state_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 2L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.zone_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 3L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.region_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 4L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.circle_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 5L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.district_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 6L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.division_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 7L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.sub_division_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 8L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.tehsil_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 9L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.local_election_body  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 10L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.panchayat_id  ");
			}
			
			if(filterScopeValue != null && filterScopeValue.longValue() > 0L){
    			queryStr.append(" and  GDWLP.govt_department_work_location_id = :filterScopeValue");
    		}
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			  queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
 		    }
 		
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
 		}
 		
 		
 		if(fromDate != null && toDate != null){
 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
 			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
 				queryStr.append(" or A.alert_caller_id is not null ");
 			}else{
 				queryStr.append(" and A.alert_caller_id is null ");
 			}
 			queryStr.append(" )");
 		}
 		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
 			queryStr.append(" and GUA.state_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
   	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
   	    	queryStr.append(" and GUA.region_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
   	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
   	    	queryStr.append(" and GUA.district_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
   	    	queryStr.append(" and GUA.division_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
   	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
   	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
   	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
   	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
 				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_status_id ");
 			}else{
 				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
 					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
     			}
 			}
     	}else{
     		queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
     	}
 		
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
 				query.addScalar("GDSI", Hibernate.LONG);
 			}
 		}
 		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("count", Hibernate.LONG);
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 		}
 		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
 			query.setParameterList("levelValues",levelValues);
 		}
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			query.setParameterList("deptScopeIdList",deptScopeIdList);
 		}
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
 		}
 		
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			query.setParameter("govtDepartmentId",govtDepartmentId);
 		}
 		if(filterScopeValue != null && filterScopeValue.longValue() > 0L){
 			query.setParameter("filterScopeValue",filterScopeValue);
 		}
 		/*if(stateId != null && stateId.longValue() > 0){
 			query.setParameter("stateId",stateId);
 		}*/
 		return query.list();
     }
     public List<Long> getAlertIdsBasedOnRequiredParameter(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		 List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
      		Long parentGovtDepartmentScopeId,Long locationValue,List<Long> calCntrIds,Long deptLevelId,Long alertStatusId){
     	 
      	StringBuilder queryStr = new StringBuilder();
      	queryStr.append(" select ");
      	queryStr.append(" distinct AAO.alert_id as alertId ");
     	queryStr.append(" from alert A ");
  		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
  			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
  			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
  		}
  		queryStr.append(" ,alert_status ALTS, ");
  		queryStr.append(" alert_assigned_officer_new AAO, ");
  		queryStr.append(" govt_department_designation_officer_new GDDO, ");
  		queryStr.append(" govt_user_address GUA, ");
  		queryStr.append(" alert_category ALTC, ");
  		queryStr.append(" alert_type ALTT, ");
  		
  		queryStr.append(" govt_department_work_location GDWL, ");
  		queryStr.append(" govt_department_work_location GDWL1 ");
  		
  		queryStr.append(" where ");
  		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
  		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
  		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
  		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
  		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
  		
  		queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
  		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
  		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
  		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
  		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
  		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
  		
  		/*if(stateId != null && stateId.longValue() > 0){
  			queryStr.append(" and GUA.state_id = :stateId ");
  		}*/
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
		}
 			
 		  if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
  			  queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
  		    }
 		  if(locationValue != null && locationValue.longValue() > 0L){
 			queryStr.append(" and  GDWL1.govt_department_work_location_id = :locationValue");
 		  }
 		  if(deptLevelId != null && deptLevelId.longValue() > 0){
 			 queryStr.append(" and  GDWL.govt_department_scope_id = :deptLevelId");
 		  }
 		  if(alertStatusId != null && alertStatusId.longValue() > 0){
   			queryStr.append(" and AAO.alert_status_id =:alertStatusId ");
   		  }
  		  if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
  			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
  		  }
  		
  		
  		if(fromDate != null && toDate != null){
  			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
  		}
  		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
  			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
  			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
  				queryStr.append(" or A.alert_caller_id is not null ");
  			}else{
  				queryStr.append(" and A.alert_caller_id is null ");
  			}
  			queryStr.append(" )");
  		}
  		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
  			queryStr.append(" and GUA.state_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
    	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
    	    	queryStr.append(" and GUA.region_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
    	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
    	    	queryStr.append(" and GUA.district_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
    	    	queryStr.append(" and GUA.division_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
    	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
    	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
    	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
    	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
  		
  		
  		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
  		query.addScalar("alertId", Hibernate.LONG);
  		if(fromDate != null && toDate != null){
  			query.setDate("fromDate", fromDate);
  			query.setDate("toDate", toDate);
  		}
  		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
  			query.setParameterList("printIdList", printIdList);  
  			query.setParameterList("electronicIdList", electronicIdList);
  		}
  		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
  			query.setParameterList("levelValues",levelValues);
  		}
  	
  		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
  			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
  		}
  		
  		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
  			query.setParameter("govtDepartmentId",govtDepartmentId);
  		}
  		/*if(stateId != null && stateId.longValue() > 0){
  			query.setParameter("stateId",stateId);
  		}*/
  		if(locationValue != null && locationValue.longValue() > 0){
  			query.setParameter("locationValue",locationValue);
  		}
  		if(deptLevelId != null && deptLevelId.longValue() > 0){
  			query.setParameter("deptLevelId",deptLevelId);
  		}
  		if(alertStatusId != null && alertStatusId.longValue() > 0){
  			query.setParameter("alertStatusId",alertStatusId);
  		}
  		return query.list();
      }
	     public List<Object[]> getDepartmentDetaislByDeptIds(Long departmentId){
	     	Query query = getSession().createQuery(" select distinct " +
	     			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	     			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " +
	     			" from AlertAssignedOfficerNew model " +
	     			" where " +
	     			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:departmentId ");
	     	query.setParameter("departmentId", departmentId);
	     	return query.list();
	     }
	     
	     public List<Object[]> getDepartmentDetailsOfAlert(Long alertId){
	    	 Query query = getSession().createQuery(" SELECT distinct " +
		     			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		     			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName " +
		     			" FROM AlertAssignedOfficerNew model " +
		     			" WHERE " +
		     			" model.alertId =:alertId " +
		     			" and model.isDeleted='N'");
		     	query.setParameter("alertId", alertId);
		     	return query.list();
	     }
	     
	     public List<Object[]> getAllDepartmentHasData(){
		 		
		 		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		 				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName "
		 				+ " from AlertAssignedOfficerNew model") ;
		 		return query.list();
		 	}
	     
	     public List<Object[]> getSubOrdinateFilterAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
	    			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	        	
	        	StringBuilder sb = new StringBuilder();  
	        	
	        	 sb.append("select ");
	        	
	        	sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
	    	    		"model1.designationName,  model.alert.alertId ");
	    	    
	    	    
	    	    	if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	        	      sb.append(" , S.govtDepartmentWorkLocationId,S.locationName,S.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	        	      sb.append(" , Z.govtDepartmentWorkLocationId,Z.locationName,Z.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	        	      sb.append(" , R.govtDepartmentWorkLocationId,R.locationName,R.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	        	      sb.append(" , C.govtDepartmentWorkLocationId,C.locationName,C.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	        	      sb.append(" , D.govtDepartmentWorkLocationId,D.locationName,D.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	        	      sb.append(" , DIV.govtDepartmentWorkLocationId,DIV.locationName,DIV.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	        	      sb.append(" , SUBDIV.govtDepartmentWorkLocationId,SUBDIV.locationName ,SUBDIV.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	          	      sb.append(" , T.govtDepartmentWorkLocationId,T.locationName,T.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	          	      sb.append(" , LEB.govtDepartmentWorkLocationId,LEB.locationName,LEB.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	          	      sb.append(" , P.govtDepartmentWorkLocationId,P.locationName,P.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	    	    	
	    	    	sb.append(" ,model.alertStatus.alertStatusId,model.insertedTime,model.updatedTime ");
	        	
	    	    	
	        	sb.append(" from GovtDepartmentDesignation model1,AlertAssignedOfficerNew model " +
	        			" left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
	    	          " left join UA.state S  " +
	    	          " left join UA.zone Z " +
	    	          " left join UA.region R " +
	    	          " left join UA.circle C " +
	    	          " left join UA.district D " +
	    	          " left join UA.division DIV " +
	    	          " left join UA.subDivision SUBDIV " +
	    	          " left join UA.tehsil T" +
	    	          " left join UA.localElectionBody LEB " +
	    	          " left join UA.panchayat P " +
	    	          " left join  model.govtDepartmentDesignationOfficer.govtDepartmentScope GDS " );
	        	sb.append(" left join model.alert.edition EDS " +
		  	    		   " left join model.alert.tvNewsChannel TNC  ");
	    	    sb.append(" where model1.govtDepartmentDesignationId=model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId and   model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	  	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
	    	    
	    	    if(desigIds != null && !desigIds.isEmpty())
	      	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:desigIds)");
	    	    
	    	    if(locationValues != null && !locationValues.isEmpty()){
	    	    		if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
		        	      sb.append(" and  S.govtDepartmentWorkLocationId in (:locationValues)  ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
		        	      sb.append(" and Z.govtDepartmentWorkLocationId in (:locationValues)  ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
		        	      sb.append(" and R.govtDepartmentWorkLocationId in (:locationValues)  ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
		        	      sb.append(" and C.govtDepartmentWorkLocationId in (:locationValues) ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
		        	      sb.append(" and D.govtDepartmentWorkLocationId in (:locationValues) ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
		        	      sb.append(" and DIV.govtDepartmentWorkLocationId in (:locationValues)  ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
		        	      sb.append(" and SUBDIV.govtDepartmentWorkLocationId in (:locationValues)   ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
		          	      sb.append(" and T.govtDepartmentWorkLocationId in (:locationValues) ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
		          	      sb.append(" and LEB.govtDepartmentWorkLocationId in (:locationValues)  ");
		        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
		          	      sb.append(" and P.govtDepartmentWorkLocationId in (:locationValues)   ");
	    	    }
	    	    	
	    	    if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	        	      sb.append("  and GDS.govtDepartmentScopeId = S.govtDepartmentScope.govtDepartmentScopeId and S.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	        	      sb.append("  and GDS.govtDepartmentScopeId = Z.govtDepartmentScope.govtDepartmentScopeId  and Z.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	        	      sb.append("  and GDS.govtDepartmentScopeId = R.govtDepartmentScope.govtDepartmentScopeId and R.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	        	      sb.append(" and GDS.govtDepartmentScopeId = C.govtDepartmentScope.govtDepartmentScopeId and C.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds)");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	        	      sb.append(" and GDS.govtDepartmentScopeId = D.govtDepartmentScope.govtDepartmentScopeId and D.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds)");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	        	      sb.append(" and GDS.govtDepartmentScopeId = DIV.govtDepartmentScope.govtDepartmentScopeId and DIV.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	        	      sb.append(" and GDS.govtDepartmentScopeId = SUBDIV.govtDepartmentScope.govtDepartmentScopeId and SUBDIV.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds)");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	          	      sb.append("  and GDS.govtDepartmentScopeId = T.govtDepartmentScope.govtDepartmentScopeId and T.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	          	      sb.append(" and GDS.govtDepartmentScopeId = LEB.govtDepartmentScope.govtDepartmentScopeId and LEB.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	          	      sb.append(" and GDS.govtDepartmentScopeId = P.govtDepartmentScope.govtDepartmentScopeId  and P.govtDepartmentScope.govtDepartmentScopeId in (:govtScopeIds) ");
	    	    
	    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	       	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
	       	     if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	   	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	   	  			}else{
	   	  				sb.append(" and model.alert.alertCallerId is null ");
	   	  			}
	   	    	      sb.append(" )");
	       	    }
	    	    
	    	    if(priorityId != null && priorityId.longValue() >0l)
	    	    	sb.append("  and model.alert.alertSeverityId = :priorityId " );
	    	    
	    	    if(fromDate != null && endDate != null){
	    	    	sb.append("  and date(model.insertedTime) between :fromDate and :endDate "); 
	    	    }
	    	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	      	      sb.append(" and UA.stateId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	      	      sb.append(" and UA.zoneId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	      	      sb.append(" and UA.regionId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	      	      sb.append(" and UA.circleId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	      	      sb.append(" and UA.districtId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	      	      sb.append(" and UA.divisionId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	      	      sb.append(" and UA.subDivisionId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	        	      sb.append(" and UA.tehsilId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	        	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
	      	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	        	      sb.append(" and UA.panchayatId in (:levelValues)");
	    	    
	    	    if(statusIds != null && statusIds.size() > 0){
	    	    	sb.append("  and model.alertStatus.alertStatusId in (:statusIds) ");
	    	    }
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	    if(govtScopeIds != null && !govtScopeIds.isEmpty())
	    	      query.setParameterList("govtScopeIds", govtScopeIds);
	    	    
	    	    if(desigIds != null && !desigIds.isEmpty())
	    	    	 query.setParameterList("desigIds", desigIds);
	    	    
	    	    if(locationValues != null && !locationValues.isEmpty())
	    	    	query.setParameterList("locationValues", locationValues);
	    	    
	    	    if(fromDate != null && endDate != null){
	    	    	query.setDate("fromDate", fromDate);
	    	    	query.setDate("endDate", endDate);
	    	    }
	    	    if(levelId != null && levelValues != null && !levelValues.isEmpty())
	    	    		query.setParameterList("levelValues", levelValues);
	    	    
	    	    if(priorityId != null && priorityId.longValue() >0l)
	    	    	query.setParameter("priorityId", priorityId);
	    	    
	    	    if(statusIds != null && statusIds.size() > 0){
	    	    	query.setParameterList("statusIds", statusIds);
	    	    }
	    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	      	      query.setParameterList("printIdList", printIdsList);
	      	      query.setParameterList("electronicIdList", electronicIdsList);
	      	    }
	    	    
	    	    return query.list();
	    	    	
	        }
	     
	  
	    	 
	    	 public List<Object[]> getSubOrdinateFilterAlertsDetailsForUser(Date fromDate,Date toDate,
	    	     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,List<Long> govtDepartmentIds,
	    	     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> filterLocationValues,List<Long> desigIds){
	    	    	 
	    	     	StringBuilder queryStr = new StringBuilder();
	    	     	queryStr.append(" select ");
	    	     	
	    	     	queryStr.append(" GDS.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
	    	     	queryStr.append(" GDS.level_name as govtDepartmentScopeName, ");
	    	     	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
	    	     	queryStr.append(" GDWL1.location_name as locationName, ");//2
	    	     	queryStr.append(" GDD.govt_department_designation_id as govtDesigId, ");
	    	     	queryStr.append(" GDD.designation_name as desigName, " );
	    	     	queryStr.append(" AAO.alert_id as alertId,  " );
	    	     	
	    	     	queryStr.append(" AAO.alert_status_id as statusId, ");//3
	    	     	queryStr.append(" AAO.inserted_time as insertedTime,  " );
	    	     	queryStr.append(" AAO.updated_time as updatedTime  " );
	    	     	
	    	     	queryStr.append(" from ");
	    	 		
	    	 		queryStr.append(" alert A ");
	    	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
	    	 			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
	    	 			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
	    	 		}
	    	 		queryStr.append(" ,alert_status ALTS, ");
	    	 		queryStr.append(" alert_assigned_officer_new AAO, ");
	    	 		queryStr.append(" govt_department_designation_officer_new GDDO, ");
	    	 		queryStr.append(" govt_user_address GUA, ");
	    	 		queryStr.append(" alert_category ALTC, ");
	    	 		queryStr.append(" alert_type ALTT, ");
	    	 		
	    	 		//queryStr.append(" govt_department_work_location GDWL, ");
	    	 		queryStr.append(" govt_department_scope GDS, ");
	    	 		queryStr.append(" govt_department_work_location GDWL1, ");
	    	 		queryStr.append(" govt_department_designation GDD ");
	    	 		/*if(filterParentScopeId != null && filterParentScopeId.longValue() > 0){
	    	 			queryStr.append(" ,govt_department_work_location GDWLP ");	
	    	 		}*/
	    	 		queryStr.append(" where ");
	    	 		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N'  ");
	    	 		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
	    	 		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
	    	 		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
	    	 		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
	    	 		//queryStr.append(" and AAO.govt_department_designation_officer_id=GDDOF.govt_department_designation_officer_id ");
	    	 		
	    	 		queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
	    	 		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
	    	 		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
	    	 		queryStr.append(" and GDWL1.govt_department_scope_id = GDS.govt_department_scope_id ");
	    	 		//queryStr.append(" and GUA.user_address_id = GDWL1.govt_user_address_id  ");
	    	 		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
	    	 		//queryStr.append(" and GDDO.govt_department_scope_id = GDS.govt_department_scope_id  ");
	    	 		queryStr.append(" and GDD.govt_department_designation_id = GDDO.govt_department_designation_id  ");
	    	 		
	    	 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
	    	 			queryStr.append(" and GDWL1.govt_department_scope_id in(:deptScopeIdList)");
	    	 		}
	    	 		
	    	 		if(desigIds != null && desigIds.size() >0){
	    	 			queryStr.append(" and GDDO.govt_department_designation_id in(:desigIds)");
	    	 		}
	    	 		/*if(stateId != null && stateId.longValue() > 0){
	    	 			queryStr.append(" and GUA.state_id = :stateId ");
	    	 		}*/
	    				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 2L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.zone_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 3L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.region_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 4L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.circle_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.division_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.sub_division_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 8L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 9L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.local_election_body  ");
	    				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 10L){
	    					queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.panchayat_id  ");
	    				}
	    				if(filterLocationValues != null && filterLocationValues.size() > 0L){
	    	    			queryStr.append(" and  GDWL1.govt_department_work_location_id in (:filterLocationValues) ");
	    	    		}
	    			
	    	 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
	    	 			  queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
	    	 		    }
	    	 		
	    	 		if(govtDepartmentIds != null && govtDepartmentIds.size() > 0){
	    	 			queryStr.append(" and GDWL1.govt_department_id in (:govtDepartmentIds)   ");
	    	 		}
	    	 		
	    	 		
	    	 		if(fromDate != null && toDate != null){
	    	 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
	    	 		}
	    	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
	    	 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
	    	 			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
	    	 				queryStr.append(" or A.alert_caller_id is not null ");
	    	 			}else{
	    	 				queryStr.append(" and A.alert_caller_id is null ");
	    	 			}
	    	 			queryStr.append(" )");
	    	 		}
	    	 		if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	    	 			queryStr.append(" and GUA.state_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.zone_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.region_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.circle_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.district_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.division_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.sub_division_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.tehsil_id in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.local_election_body in (:levelValues)");
	    	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	    	   	    	queryStr.append(" and GUA.panchayat_id in (:levelValues)");
	    	 		
	    	 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
	    	 		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
	    	 		query.addScalar("govtDepartmentScopeName", Hibernate.STRING);
	    	 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
	    	 		query.addScalar("locationName", Hibernate.STRING);
	    	 		query.addScalar("govtDesigId", Hibernate.LONG);
	    	 		query.addScalar("desigName", Hibernate.STRING);
	    	 		query.addScalar("alertId", Hibernate.LONG);
	    	 		query.addScalar("statusId", Hibernate.LONG);
	    	 		query.addScalar("insertedTime", Hibernate.STRING);
	    	 		query.addScalar("updatedTime", Hibernate.STRING);
	    	 		if(fromDate != null && toDate != null){
	    	 			query.setDate("fromDate", fromDate);
	    	 			query.setDate("toDate", toDate);
	    	 		}
	    	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
	    	 			query.setParameterList("printIdList", printIdList);  
	    	 			query.setParameterList("electronicIdList", electronicIdList);
	    	 		}
	    	 		if(levelId != null && levelValues != null && !levelValues.isEmpty()){
	    	 			query.setParameterList("levelValues",levelValues);
	    	 		}
	    	 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
	    	 			query.setParameterList("deptScopeIdList",deptScopeIdList);
	    	 		}
	    	 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
	    	 			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
	    	 		}
	    	 		
	    	 		if(govtDepartmentIds != null && govtDepartmentIds.size() > 0){
	    	 			query.setParameter("govtDepartmentIds",govtDepartmentIds);
	    	 		}
	    	 		if(filterLocationValues != null && filterLocationValues.size() > 0L){
	    	 			query.setParameter("filterLocationValues",filterLocationValues);
	    	 		}
	    	 		
	    	 		if(desigIds != null && desigIds.size() >0){
	    	 			query.setParameter("desigIds",desigIds);
	    	 		}
	    	 		/*if(stateId != null && stateId.longValue() > 0){
	    	 			query.setParameter("stateId",stateId);
	    	 		}*/
	    	 		return query.list();
	    	     }
	    	 
	    
	  public List<Long> getTotalAlertByOtherStatus1(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, List<Long> statusIdList,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationList){
	    		StringBuilder sb = new StringBuilder();  
	    	    sb.append("select distinct model.alert.alertId ");
	    	    sb.append(" from AlertAssignedOfficerNew model" +
	    	          " left join model.alert.edition EDS " +
	    	          " left join model.alert.tvNewsChannel TNC " +
	    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
	    	          " left join UA.state S " +
	    	          " left join UA.zone Z " +
	    	          " left join UA.region R" +
	    	          " left join UA.circle C" +
	    	          " left join UA.district D" +
	    	          " left join UA.division DIV" +
	    	          " left join UA.subDivision SUBDIV" +
	    	          " left join UA.tehsil T" +
	    	          " left join UA.localElectionBody LEB " +
	    	          " left join UA.panchayat P");
	    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N'  " +
	    	    		  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " +
	    	    		  " and model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    	    if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
	    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :govtDepartmentScopeId ");
	    	    }
	    	    if(statusIdList != null && statusIdList.size() > 0L){
	    	    	sb.append(" and  model.alertStatus.alertStatusId in (:statusIdList) ");
	    	    }
	    	    if(deptId != null && deptId.longValue() > 0L){
	    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId ");
	    	    }else{
	    	    	if(departmentIds != null && departmentIds.size()>0){
	        	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	        	    }
	    	    }
	    	    
	    	      
	    	    
	    	    if(printIdsList != null && printIdsList.size()>0 && electronicIdsList != null && electronicIdsList.size()>0){
	    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
		    	      if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
		    	    	  sb.append(" or model.alert.alertCallerId is not null ");
		  			}else{
		  				sb.append(" and model.alert.alertCallerId is null ");
		  			}
		    	      sb.append(" )");
	    	    }else if(printIdsList != null && printIdsList.size()>0){
	    	      sb.append(" and  EDS.newsPaperId in (:printIdList) ");
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      sb.append(" and TNC.tvNewsChannelId in (:electronicIdList) ");
	    	    }        
	    	      
	    	    if(fromDate != null && toDate != null){
	    	    	 sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
	    	    }
	    	     	    
	    	    if(levelId !=null && levelValues !=null && levelValues.size()>0){
		    		if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
		    	      sb.append(" and UA.stateId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
		    	      sb.append(" and UA.zoneId in (:levelValues)");
		    	    else if(levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
		    	      sb.append(" and UA.regionId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
		    	      sb.append(" and UA.circleId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
		    	      sb.append(" and UA.districtId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
		    	      sb.append(" and UA.divisionId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
		    	      sb.append(" and UA.subDivisionId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
		      	      sb.append(" and UA.tehsilId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
		      	      sb.append(" and UA.localElectionBodyId in (:levelValues)");
		    	    else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
		      	      sb.append(" and UA.panchayatId in (:levelValues)");
	    	   	      
	    	    }
	    	   if(impactLevelIdList != null && impactLevelIdList.size()>0){
	    		   sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:impactLevelIdList) ");
	    	   }
	    	   if(priorityIdList != null && priorityIdList.size()>0){
	    		   sb.append(" and model.alert.alertSeverity.alertSeverityId in (:priorityIdList) ");
	    	   }
	    	   if(alertSourceIdList != null && alertSourceIdList.size()>0){
	    		   sb.append(" and model.alert.alertCategory.alertCategoryId in (:alertSourceIdList) ");
	    	   }
	    	   if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
	    		   sb.append(" and ( model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) or ( model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ))" +
	    		   		" ");
	    	   }else if(printMediaIdList != null && printMediaIdList.size()>0){
	    		   sb.append(" and model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) ");
	    	   }else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
	    		   sb.append(" and model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ");
	    	   }
	    	   /*if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
	    		   sb.append(" and model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ");
	    	   }*/
	    	   if(locationList != null && locationList.size()>0){
	    			if(scopeId != null && scopeId.longValue() == 1L ){
	    				sb.append(" and UA.stateId in (:locationList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 2L ){
	    				sb.append(" and  UA.districtId in (:locationList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 3L ){
	    				sb.append(" and UA.constituencyId in (:locationList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 5L ){
	    				sb.append(" and UA.tehsilId in (:locationList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 6L ){
	    				sb.append(" and UA.panchayatId in (:locationList) ");
	    			}
	    			}
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	    if(deptId != null && deptId.longValue() > 0L){
	    	    	query.setParameter("deptId",deptId);
	    	    }else{
	    	    	if(departmentIds != null && departmentIds.size()>0){
	        	    	query.setParameterList("departmentIds", departmentIds);
	        	    }
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
	    	    if(statusIdList != null && statusIdList.size() > 0L){
	    	    	query.setParameterList("statusIdList",statusIdList);
	    	    }
	    	    if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
	    	    	query.setParameter("govtDepartmentScopeId",govtDepartmentScopeId);
	    	    }
	    	    if(impactLevelIdList != null && impactLevelIdList.size()>0){
	    	    	query.setParameterList("impactLevelIdList", impactLevelIdList);
	     	   }
	     	   if(priorityIdList != null && priorityIdList.size()>0){
	     		  query.setParameterList("priorityIdList", priorityIdList);
	     	   }
	     	   if(alertSourceIdList != null && alertSourceIdList.size()>0){
	     		  query.setParameterList("alertSourceIdList", alertSourceIdList);
	     	   }
	     	   if(printMediaIdList != null && printMediaIdList.size()>0){
	   	    	  query.setParameterList("printMediaIdList", printMediaIdList);
	   	       }
	     	   if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
	   	    	  query.setParameterList("electronicMediaIdList", electronicMediaIdList);
	   	       }
	     	   if(locationList != null && locationList.size()>0){
	  			query.setParameterList("locationList", locationList);
	  		   }
	    	    return query.list();
	    	}
}