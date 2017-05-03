package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class GovtAlertSubTaskDAO extends GenericDaoHibernate<GovtAlertSubTask, Long> implements
		IGovtAlertSubTaskDAO {
       public GovtAlertSubTaskDAO(){
    	   super(GovtAlertSubTask.class);
       }
       
       public List<Object[]> getDistrictOfficerAlertsSubTasksCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
    	   StringBuilder sb = new StringBuilder();
	    	
    	   
       	if(countType != null && countType.equalsIgnoreCase("today")){
       		sb.append(" select model.govtAlertSubTaskId from GovtAlertSubTask model ");
       	}else{
       		sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId,model.alertSubTaskStatus.status,count(model.govtAlertSubTaskId),model.alertSubTaskStatus.color from GovtAlertSubTask model ");
       	}
       	
       	sb.append(" left join model.alert.edition EDS " +
  	             " left join model.alert.tvNewsChannel TNC ");
       	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
       	
       	if(type != null && type.equalsIgnoreCase("mySubTasks")){
       		if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
       	  	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    }else{
	    		if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in( :govtOffcrIds) " );
	    		}
	    		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
       				sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in( :govtDepDesigOffcrIds) " );
       			}
	    	  
       	}
       	
        if((printIdsList != null && printIdsList.size() > 0) && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
    	      if( calCntrIdList !=null && !calCntrIdList.isEmpty() &&  calCntrIdList.get(0) != 0){
	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	  			}else{
	  				sb.append(" and model.alert.alertCallerId is null ");
	  			}
	    	      sb.append(" )");
    	    }
        
       	if(fromDate != null && toDate != null){
	    		  sb.append(" and date(model.createdTime) between  :fromDate and :toDate " ); 
	    	  }
	    	  
	    	  if(countType != null && !countType.equalsIgnoreCase("today")){
	    		  sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId ");
	    	  }
	    	 
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
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
       public List<Object[]> getSubTaskCount(List<Long> alertIds){
    	   StringBuilder sb = new StringBuilder();
    	   sb.append(" select GAST.alert_id, count(distinct GAST.govt_alert_sub_task_id) " +
    	   			 " from govt_alert_sub_task GAST, alert ALT " +
    	   			 " where " +
    	   			 " GAST.alert_id = ALT.alert_id " +
    	   			 " and ALT.is_deleted = 'N' " +
    	   			 " and GAST.is_deleted = 'N' " +
    	   			 " and GAST.alert_id in (:alertIds) " +
    	   			 " group by GAST.alert_id ");
    	   Query query = getSession().createSQLQuery(sb.toString());
    	   query.setParameterList("alertIds", alertIds);
    	   return query.list();
       }
       
       public List<Long> getSubTasksIdsList(List<Long> alertIds){
    	   StringBuilder sb = new StringBuilder();
    	   sb.append(" select GAST.govt_alert_sub_task_id as id " +
    	   			 " from govt_alert_sub_task GAST, alert ALT " +
    	   			 " where " +
    	   			 " GAST.alert_id = ALT.alert_id " +
    	   			 " and ALT.is_deleted = 'N' " +
    	   			 " and GAST.is_deleted = 'N' " +
    	   			 " and GAST.alert_id in (:alertIds) " +
    	   			 "  ");
    	   Query query = getSession().createSQLQuery(sb.toString()).addScalar("id", Hibernate.LONG);
    	   query.setParameterList("alertIds", alertIds);
    	   return query.list();
       }
       
       @SuppressWarnings("unchecked")
   	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate){
     		StringBuilder sb = new StringBuilder();  
     	     sb.append("select ");
     	    
     	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
         		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
     	    
     	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
     	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName, " );
      	   
     	     sb.append(" count(distinct model.alert.alertId) ");
     	      
     	     sb.append(" from  GovtAlertSubTask model ");
     	     
     	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");
     	    
     	    if(fromDate != null && toDate != null)
     	      sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
     	  
       sb.append(" group by " +
       		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
       		" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
        	    
     	    Query query = getSession().createQuery(sb.toString());
     	    if(fromDate != null && toDate != null){
     	        query.setDate("fromDate", fromDate);
     	        query.setDate("toDate", toDate);
     	    }
     	  return query.list();
     	}
   	
   	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseStatusOverViewForSubTask(Date fromDate, Date toDate,Long levelId,Long deptId,
			Long filterLevelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );

              sb.append(" model.alertSubTaskStatus.alertSubTaskStatusId," +
	  	      		    " model.alertSubTaskStatus.status," +
	  	      		    " model.alertSubTaskStatus.color, " );
	  	     
	  	     sb.append(" count(distinct model.govtAlertSubTaskId) ");
	  	     
	  	     sb.append(" from GovtAlertSubTask model left join model.alert.edition EDS " +
    	              " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA ");
	  	     
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId " +
	  	     		   " and model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	     if(levelId != null && levelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:levelId ");
	  	     }
	  	     if(deptId != null && deptId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId ");
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
	  	      sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
	  	    if(filterLevelId != null && filterLevelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId =:filterLevelId ");
	  	     }
	         sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
	    		      "   model.alertSubTaskStatus.alertSubTaskStatusId " );
	     	    
	  	    Query query = getSession().createQuery(sb.toString());
	  	    
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(levelId != null && levelId.longValue() > 0){
	  	    	 query.setParameter("levelId",levelId);
	  	    }
	  	    if(deptId != null && deptId.longValue() > 0){
	  	    	query.setParameter("deptId",deptId);
	  	    }
	  	    if(filterLevelId != null && filterLevelId.longValue() > 0){
	  	    	query.setParameter("filterLevelId",filterLevelId);
	  	    }
	  	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	  	      query.setParameterList("printIdList", printIdsList);
	  	      query.setParameterList("electronicIdList", electronicIdsList);
	  	    }
		  	if(levelId != null && levelValues != null && !levelValues.isEmpty()){
		  		 query.setParameterList("levelValues", levelValues);  
		  	}
	  	  return query.list();
	  	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelViewForSubtask(Date fromDate, Date toDate,Long deptId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long levelId,List<Long> levelValues){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color, " );
	  	     
	  	     sb.append(" count(distinct model.govtAlertSubTaskId) ");
	  	     
	  	     sb.append(" from GovtAlertSubTask model left join model.alert.edition EDS " +
    	             " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA ");
	  	     
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId " +
	  	     		   " and model.alert.isDeleted='N' and model.isDeleted = 'N' ");

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
	  	    
	  	   if(fromDate != null && toDate != null){
	  	    	sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
	  	    }
	  	      
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
	  	   if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
    	      query.setParameterList("printIdList", printIdsList);
    	      query.setParameterList("electronicIdList", electronicIdsList);
    	    }
	  	  
	  	    if(deptId != null && deptId.longValue() > 0){
	  	    	query.setParameter("deptId", deptId);
	  	    }
		  	if(levelId != null && levelValues != null && !levelValues.isEmpty()){
		  		 query.setParameterList("levelValues", levelValues);  
		  	}
		  	if(levelId != null && levelId.longValue() > 0){
		  		query.setParameter("levelId",levelId);  
		  	}
  	     
	  	  return query.list();
	  	}
   	
    public List<Object[]> getSubOrdinateTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,String type){
    	
    	StringBuilder sb = new StringBuilder();  
	    sb.append("select model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	    		"model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
	    		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName,count(model.govtAlertSubTaskId) ");
    	
	    if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
  	      sb.append(" , S.locationValue,S.locationName ");
  	    else if(govtScopeIds != null  && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
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
	    
    	sb.append(" from GovtAlertSubTask model" +
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
	    
	    if(type != null && type.equalsIgnoreCase("completedTasks")){
	    	sb.append("  and model.alertSubTaskStatus.alertSubTaskStatusId = 3 ");
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
    
    public List<Long> getDistrictOfficerSubTasksAlertIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date formDate,Date toDate){
 	   StringBuilder sb = new StringBuilder();
	    	
 	   
    	
    		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
    	
    		sb.append(" left join model.alert.edition EDS " +
    		          " left join model.alert.tvNewsChannel TNC ");
    		
    	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
    	
    	
    	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
            sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) )");
            if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
                sb.append(" or model.alert.alertCallerId is not null ");
          }else{
            sb.append(" and model.alert.alertCallerId is null ");
          }
              sb.append(" )");
          }
    	
    	if(type != null && type.equalsIgnoreCase("mySubTasks")){
    		if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    }else{
	    	if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
    			sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
    		}
	    	  
    	}
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  sb.append(" and date(model.createdTime) between :todayDate and :todayDate " ); 
	    	  }else if(countType.equalsIgnoreCase("overAll") && formDate != null && toDate!= null ){
	    		  sb.append(" and date(model.createdTime) between :formDate and :toDate " );
	    	  }
	    	  
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }else if(countType.equalsIgnoreCase("overAll") && formDate != null && toDate!= null ){
	    		  query.setParameter("formDate", formDate);
	    		  query.setParameter("toDate", toDate);
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
    
    public List<Object[]> getDistrictOfficerSubTaskAlerts(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,
    		List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,String type){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select ");
    
    	queryStr.append(" GDWL.govt_department_work_location_id as govtDepartmentWorkLocationId, ");
    	queryStr.append(" GDWL.govt_department_scope_id as parentGovtDepartmentScopeId, ");
    	queryStr.append(" GDWL.location_name as locationName, ");
        	
        	if(type != null && type.equalsIgnoreCase("status")){
        		queryStr.append(" ,AAO.alert_sub_task_status_id as alertStatusId ");
        		queryStr.append(" ,ALTS.status as alertStatus ");
        	}else if(type != null && type.equalsIgnoreCase("scope")){
        		queryStr.append(" GDS.govt_department_scope_id as scopeId, ");
            	queryStr.append(" GDS.level_name as scope, ");
        	}
        	
        	queryStr.append(" count(distinct AAO.govt_alert_sub_task_id) as count,");
        	if(type != null && type.equalsIgnoreCase("status")){
        		queryStr.append(" ALTS.color as color");
        	}else if(type != null && type.equalsIgnoreCase("scope")){
        		queryStr.append(" GDS.color as color");
        	}
    	
    	
		queryStr.append(" from ");  
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_department_designation_new GDD, ");
		queryStr.append(" govt_department_scope GDS, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" govt_department GD, ");
		//queryStr.append(" alert_category ALTC, ");
		//queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location_relation GDWLR ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id  ");
		//queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		//queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y'  ");
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
		
		
		queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
		
		if(type != null && type.equalsIgnoreCase("status")){
			queryStr.append(" group by GDWL.govt_department_work_location_id, AAO.alert_sub_task_status_id ");
		}else if(type != null && type.equalsIgnoreCase("scope")){
			queryStr.append(" group by GDWL.govt_department_work_location_id, GDS.govt_department_scope_id ");
		}
		
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		
			query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
    		query.addScalar("locationName", Hibernate.STRING);
    		if(type != null && type.equalsIgnoreCase("status")){
	    		query.addScalar("alertStatusId", Hibernate.LONG);
	    		query.addScalar("alertStatus", Hibernate.STRING);
    		}else if(type != null && type.equalsIgnoreCase("scope")){
	    		query.addScalar("scopeId", Hibernate.LONG);
	    		query.addScalar("scope", Hibernate.STRING);
    		}
    		query.addScalar("count", Hibernate.LONG);
    		query.addScalar("color", Hibernate.STRING);
		
		
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
   	
    public Integer updateSubTaskPriority(Long subTaskId,Long priorityId,Long userId,Date date){
    	Query query = getSession().createQuery(" update GovtAlertSubTask model set model.alertSeverityId=:priorityId,model.updatedBy=:userId,model.updatedTime=:date "
    			+ " where model.govtAlertSubTaskId=:subTaskId and model.isDeleted='N' ");
    	query.setParameter("subTaskId", subTaskId);
    	query.setParameter("priorityId", priorityId);
    	query.setParameter("userId", userId);
    	query.setDate("date", date);
    	
    	return query.executeUpdate();
    }
    
    public Integer updateSubTaskDueDate(Long subTaskId,Date dueDate,Long userId,Date date){
    	Query query = getSession().createQuery(" update GovtAlertSubTask model set model.dueDate=:dueDate,model.updatedBy=:userId,model.updatedTime=:date "
    			+ " where model.govtAlertSubTaskId=:subTaskId and model.isDeleted='N' ");
    	query.setParameter("subTaskId", subTaskId);
    	query.setDate("dueDate", dueDate);
    	query.setParameter("userId", userId);
    	query.setDate("date", date);
    	
    	return query.executeUpdate();
    }
    
    public List<Object[]> getSubTaskInfoForAlert(Long alertId){
    	Query query = getSession().createQuery(" select distinct model.govtAlertSubTaskId,model.title,'',date(model.dueDate),model.alertSubTaskStatus.status," +
    			" model.alertSubTaskStatus.color "
    			+ " from GovtAlertSubTask model"
    			+ " where model.alertId=:alertId and model.isDeleted='N' order by model.govtAlertSubTaskId desc ");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
   public List<Object[]> getDistrictOfficerAlertsSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	   	StringBuilder sb = new StringBuilder();
	    	if(type != null && type.equalsIgnoreCase("today")){
	    		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	    				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
	    				" count(distinct  model.govtAlertSubTaskId) ");
	    	}else if(type != null && type.equalsIgnoreCase("completed")){
	    		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
		      		      " count(distinct  model.govtAlertSubTaskId) ");
	    	}
   		sb.append(" from GovtAlertSubTask model ");
   		sb.append(" left join model.alert.edition EDS " +
   				  " left join model.alert.tvNewsChannel TNC ");
   		sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
   	
   		  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	  		  sb.append(" and model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	  	  }
   		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	  		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	  	  }
	  	  if(startDate != null && endDate != null){
	  		  sb.append(" and date(model.createdTime) between :startDate and :endDate " ); 
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
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
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
   public List<Object[]> getDistrictOfficerMyAssignedSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	   	StringBuilder sb = new StringBuilder();
	    	if(type != null && type.equalsIgnoreCase("today")){
	    		sb.append(" select model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	    				" model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
	    				" count(distinct  model.govtAlertSubTaskId) ");
	    	}else if(type != null && type.equalsIgnoreCase("completed")){
	    		sb.append(" select model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		      		      " model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
		      		      " count(distinct  model.govtAlertSubTaskId) ");
	    	}
   		sb.append(" from GovtAlertSubTask model ");
   		sb.append(" left join model.alert.edition EDS " +
  				 " left join model.alert.tvNewsChannel TNC ");
   		sb.append(" where model.alertAssignedOfficer.isDeleted = 'N' and model.alertAssignedOfficer.alert.isDeleted = 'N' and model.isDeleted='N' " );
   	
   		if(govtOffcrIds != null && govtOffcrIds.size()>0){
	  		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
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
   		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	  		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	  	  }
	  	  if(fromDate != null && toDate != null){
	  		  sb.append(" and date(model.alertAssignedOfficer.insertedTime) between :fromDate and :toDate " ); 
	  	  }
 	  
	  	  sb.append(" group by model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
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
	    	  return query.list();
   }
   
   public List<Object[]> getDistrictOfficerMySubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
   	StringBuilder sb = new StringBuilder();
   	sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId," +
   			"  model.alertSubTaskStatus.status," +
   			"  model.alertSubTaskStatus.color," +
   			"  count(distinct model.govtAlertSubTaskId) ");
   	
   	sb.append(" from GovtAlertSubTask model ");
	sb.append(" left join model.alert.edition EDS " +
			  " left join model.alert.tvNewsChannel TNC ");
   	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
   	
   	  
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  sb.append(" and model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
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
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
   	  }
   	  if(startDate != null && endDate != null){
   		 sb.append(" and date(model.createdTime) between :startDate and :endDate ");
   	  }
   	  sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId ");
   	 
   	  Query query = getSession().createQuery(sb.toString());
   	  
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
   	  }
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
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
   public List<Object[]> getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
   	StringBuilder sb = new StringBuilder();
   	sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId," +
   			"  model.alertSubTaskStatus.status," +
   			"  model.alertSubTaskStatus.color," +
   			"  count(distinct model.govtAlertSubTaskId) ");
   	
   	sb.append(" from GovtAlertSubTask model ");
   	sb.append(" left join model.alert.edition EDS " +
			  " left join model.alert.tvNewsChannel TNC ");
   	sb.append(" where model.alertAssignedOfficer.isDeleted = 'N' and model.alertAssignedOfficer.alert.isDeleted = 'N' and model.isDeleted='N' " );
   	
   	  
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
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
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
   	  }
   	  if(fromDate != null && toDate != null){
   		sb.append(" and date(model.alertAssignedOfficer.insertedTime) between :fromDate and :toDate "); 
   	  }
   	  sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId ");
   	 
   	  Query query = getSession().createQuery(sb.toString());
   	  
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
   	  }
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
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
   	  return query.list();
   }
   
   public List<Object[]> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
		   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
		   Long districtWorkLocationId, String group,String searchType,List<Long> calCntrIds){
   	StringBuilder queryStr = new StringBuilder();
   	queryStr.append(" select ");
   	
   	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
   	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
   	queryStr.append(" GDWL1.location_name as locationName, ");//2
   	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
   		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
   			queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
   		}else{
   			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
   				queryStr.append(" AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
   			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
   				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
   			}
   		}
   		
   	}else{
   		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
   	}
   	
   	queryStr.append(" count(distinct AAO.govt_alert_sub_task_id) as count");
   	
		queryStr.append(" from ");
		
		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
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
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_sub_task_status_id ");
			}else{
				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_sub_task_status_id ");
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
   //division
   public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
		   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long justUpperLvl){
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
   			queryStr.append(" AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
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
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
		//queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id and GDWL2.govt_department_scope_id = 5  ");->modified as dynamic
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
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
				queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_sub_task_status_id ");
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
   public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(Date fromDate,Date toDate,Long stateId,
		   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
		   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
		   Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long levelTwo,Long levelThree){
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
   			queryStr.append(" AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
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
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
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
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
		
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
				queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_sub_task_status_id ");
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
   public List<Long> getStateAndDistrictWorkLocationGovtDeptScopeWiseSubTaskCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
		   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
		   Long districtWorkLocationId, String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds){
   	StringBuilder queryStr = new StringBuilder();
   	queryStr.append(" select ");
    queryStr.append(" distinct AAO.alert_id as alertIds ");
         queryStr.append(" from ");  
         queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
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
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
			queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
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
		
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
   public List<Long> getDivisionWorkLocationGovtDeptScopeWiseSubTaskDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
		   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long justUpperLvl){
   	StringBuilder queryStr = new StringBuilder();
   	queryStr.append(" select ");
    queryStr.append(" distinct AAO.alert_id as alertIds ");
         queryStr.append(" from ");  
         queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
		queryStr.append(" govt_department_designation_officer_new GDDO, ");
		queryStr.append(" govt_user_address GUA, ");
		queryStr.append(" alert_category ALTC, ");
		queryStr.append(" alert_type ALTT, ");
		
		queryStr.append(" govt_department_work_location GDWL, ");
		queryStr.append(" govt_department_work_location GDWL1, ");
		queryStr.append(" govt_department_work_location GDWL2 ");
		
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
		
		queryStr.append(" and AAO.is_approved = 'Y' and AAO.is_deleted='N' ");
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
			queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
   public List<Long> getSubDivisionWorkLocationDeptScopeWiseSubTaskCountDetails(Date fromDate,Date toDate,Long stateId,
		   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
		   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
		   Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long levelTwo,Long levelThree){
   	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" select ");
    queryStr.append(" distinct AAO.alert_id as alertIds ");
         queryStr.append(" from ");  
         queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_sub_task_status ALTS, ");
		queryStr.append(" govt_alert_sub_task AAO, ");
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
		queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
			queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
  public List<Long> getDistrictOffcrSubTasksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	   StringBuilder sb = new StringBuilder();
	    	
   		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
   	
   		sb.append(" left join model.alert.edition EDS " +
  	          " left join model.alert.tvNewsChannel TNC ");
   		
   	sb.append(" where model.isDeleted = 'N'  and model.alert.isDeleted='N' " );
   	
   	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
        sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) )");
	        if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	            sb.append(" or model.alert.alertCallerId is not null ");
	      }else{
	        sb.append("  and model.alert.alertCallerId is null ");
	      }
          sb.append(" )");
      }
   	if(type != null && type.equalsIgnoreCase("mySubTasks")){
   	  	  if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId = :govtOffceId " );
	    	  }
   	  	if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDeptDesigOffceId " );
	    	  }
	    }else{
   		if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId = :govtOffceId " );
	    	  }
   		
   			if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
   				sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDeptDesigOffceId " );
   			}
	    	  
   	}
   	   if(statusId != null && statusId.longValue() > 0L){
   		 sb.append(" and  model.alertSubTaskStatus.alertSubTaskStatusId = :statusId");
		  }
		  if(fromDate != null && toDate != null){
			 sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
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
	    	    /*else if(printIdsList != null && printIdsList.size()>0){
	    	      query.setParameterList("printIdList", printIdsList);
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }*/
	    	  return query.list();
   }
    public List<Long> getDistrictOffcerSubTsksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
	   StringBuilder sb = new StringBuilder();
   		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
   	
   		sb.append(" left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC ");
   		
   	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
   	
   	if(type != null && type.equalsIgnoreCase("mySubTasks")){
   	  	  if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId = :govtOffceId " );
	    	  }
   	  	if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDeptDesigOffceId " );
	    	  }
	    }else{
   		if(govtOffceId != null && govtOffceId.longValue() >0l){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId = :govtOffceId " );
	    	  }
   		
   			if(govtDeptDesigOffceId != null && govtDeptDesigOffceId.longValue() >0l){
   				sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDeptDesigOffceId " );
   			}
	    	  
   	}
   	
   	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
        sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) )");
        if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
            sb.append(" or model.alert.alertCallerId is not null ");
      }else{
        sb.append(" and model.alert.alertCallerId is null ");
      }
          sb.append(" )");
      }
   	if(statusId != null && statusId.longValue() > 0L){
   		sb.append(" and  model.alertSubTaskStatus.alertSubTaskStatusId = :statusId");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
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
	    	    /*else if(printIdsList != null && printIdsList.size()>0){
	    	      query.setParameterList("printIdList", printIdsList);
	    	    }else if(electronicIdsList != null && electronicIdsList.size()>0){
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	    }*/
	    	  return query.list();
   }
  @SuppressWarnings("unchecked")//Use
public List<Object[]> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList){
		StringBuilder sb = new StringBuilder();  
	    sb.append("select ");
	    if(type.equalsIgnoreCase("Status")){
	      sb.append(" model.alertSubTaskStatus.alertSubTaskStatusId, model.alertSubTaskStatus.status,model.alertSubTaskStatus.color " );
	    }else if(type.equalsIgnoreCase("Level")){
	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color " );
	    }else if(type.equalsIgnoreCase("Department")){
	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName," +
	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.color ");	
	    }
	     sb.append(" ,count(distinct model.govtAlertSubTaskId) ");
	      
	    sb.append(" from  GovtAlertSubTask model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    
	    if(departmentIds != null && !departmentIds.isEmpty())
	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    
	    
	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && callCenterIdsList !=null && !callCenterIdsList.isEmpty() ){
			sb.append(" AND ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) ) ");
			 if( callCenterIdsList !=null && !callCenterIdsList.isEmpty() && callCenterIdsList.get(0) != 0 ){
				sb.append(" or model.alert.alertCallerId is not null ");
			}else{
				sb.append(" and model.alert.alertCallerId is null ");
			}
			 sb.append(" )");
		}
	    
	    if(fromDate != null && toDate != null)
	      sb.append(" and date(model.createdTime) between :fromDate and :toDate");
	    
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
	    	sb.append(" and model.alertSubTaskStatus.alertSubTaskStatusId in (:alertStatusIds)");
	    }
	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:departmentScopeIds)");
	    }
	    if(type.equalsIgnoreCase("Status")){
	      sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId " );
	    }else if(type.equalsIgnoreCase("Level")){
	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
 	    }else if(type.equalsIgnoreCase("Department")){
 	    	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );	
 	    }
	  
	    Query query = getSession().createQuery(sb.toString());
	    if(departmentIds != null && !departmentIds.isEmpty())
	      query.setParameterList("departmentIds", departmentIds);
	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && callCenterIdsList !=null && callCenterIdsList.size() > 0){
	      query.setParameterList("printIdsList", printIdsList);
	      query.setParameterList("electronicIdsList", electronicIdsList);
	    }  
	   /* else if(printIdsList != null && !printIdsList.isEmpty()){
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
   @SuppressWarnings("unchecked")
public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(Date fromDate, Date toDate, Long stateId,
		      List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,
		      List<Long> levelValues,String type,List<Long> alertSubTaskStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList){
		StringBuilder sb = new StringBuilder();  
	    sb.append("select ");
	    if(type.equalsIgnoreCase("Status")){
	      sb.append(" model.alertSubTaskStatus.alertSubTaskStatusId, model.alertSubTaskStatus.status,model.alertSubTaskStatus.color," +
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
	     sb.append(" ,count(distinct model.govtAlertSubTaskId) ");
	      
	    sb.append(" from GovtAlertSubTask model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    
	    if(departmentIds != null && !departmentIds.isEmpty())
	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    
	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && callCenterIdsList !=null && callCenterIdsList.size() > 0){
			sb.append(" AND ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList) ) ");
			 if( callCenterIdsList !=null && !callCenterIdsList.isEmpty() && callCenterIdsList.get(0) != 0 ){
				sb.append(" or model.alert.alertCallerId is not null ");
			}else{
				sb.append(" and model.alert.alertCallerId is null ");
			}
			 sb.append(" )");
		}
	 
	    if(fromDate != null && toDate != null)
	      sb.append(" and date(model.createdTime) between :fromDate and :toDate");
	    
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
	    
	    if(alertSubTaskStatusIds != null && alertSubTaskStatusIds.size() > 0){
	    	sb.append(" and model.alertSubTaskStatus.alertSubTaskStatusId in (:alertSubTaskStatusIds)");
	    }
	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:departmentScopeIds)");
	    }
	    if(type.equalsIgnoreCase("Status")){
	      sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );
	    }else if(type.equalsIgnoreCase("Level")){
	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
	    }else if(type.equalsIgnoreCase("Department")){
	    	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );	
	    }
	  
	    Query query = getSession().createQuery(sb.toString());
	    if(departmentIds != null && !departmentIds.isEmpty())
	      query.setParameterList("departmentIds", departmentIds);
	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && callCenterIdsList !=null && !callCenterIdsList.isEmpty() ){
	      query.setParameterList("printIdsList", printIdsList);
	      query.setParameterList("electronicIdsList", electronicIdsList);
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
	    
	    if(alertSubTaskStatusIds != null && alertSubTaskStatusIds.size() > 0){
	    	 query.setParameterList("alertSubTaskStatusIds", alertSubTaskStatusIds);
	    }
	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
	      query.setParameterList("departmentScopeIds", departmentScopeIds);
	    }
	      return query.list();
	}
		@SuppressWarnings("unchecked")
		public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId,
				Date fromDate,Date toDate,Long desigDeptOfficerId,Long officerId,Long levelId,List<Long> levelValues,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct model.alert.alertId "+
					  " from " +
					  " GovtAlertSubTask model left join model.alert.edition EDS " +
					  " left join model.alert.tvNewsChannel TNC,GovtUserAddress UA " +
					  " where UA.userAddressId=model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId " +
					  " and model.isDeleted = 'N' and model.alert.isDeleted='N' ");
			if(deptId != null && deptId.longValue() >0){
				sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
			}
			if(locationLevelId != null && locationLevelId.longValue() > 0){
				sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
			}
			if(statusId != null && statusId.longValue() >0){
				sb.append(" and model.alertSubTaskStatus.alertSubTaskStatusId = :statusId ");
			}
			if(fromDate != null && toDate != null){
				sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
			}
			if(desigDeptOfficerId != null && desigDeptOfficerId.longValue() > 0){
				sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId =:desigDeptOfficerId ");
			}
			if(officerId != null && officerId.longValue() > 0){
				sb.append(" and model.subTaskGovtOfficer.govtOfficerId =:officerId ");
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
			 
			//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId " );
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
			if(levelId != null && levelValues != null && !levelValues.isEmpty())
			{
		        query.setParameterList("levelValues", levelValues);
		    }
			//query.setParameter("scopeId", 5l);
			if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	}
			 
			
			//query.setParameter("scopeId",scopeId);
			return query.list();
		}
		public List<Long> getStateLevelAlertclickViewAlertIds(List<Long> govtDepDesigOffcrIds,
				List<Long> govtOffcrIds,String type,List<Long> deptIds,Long statusId,Date fromDate,Date endDate){
		 	   StringBuilder sb = new StringBuilder();
		    	
		    	sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
		    	
		    	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
		    	
		      if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
			    
	    	  if(fromDate != null && endDate != null){
	    		  sb.append(" and date(model.createdTime) between :fromDate and :endDate " ); 
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  sb.append(" and  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) " );
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  sb.append(" and  model.alertSubTaskStatus.alertSubTaskStatusId = :statusId " );
	    	  }
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(fromDate != null && endDate != null){
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
		public Long getGovtDeptDesigOfficerIdBySubTaskId(Long subTaskId){
			Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId " +
					" from  GovtAlertSubTask model " +
					" where model.govtAlertSubTaskId = :subTaskId ");
			query.setParameter("subTaskId", subTaskId);
			return (Long) query.uniqueResult();  
		}
		
		public List<Object[]> getGovtDeptDesigOfficerIdsListBySubTaskId(List<Long> subTaskIdsList){
			/*Query query = getSession().createQuery("select distinct model.govtAlertSubTaskId, model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId " +
					" from  GovtAlertSubTask model " +
					" where model.govtAlertSubTaskId in (:subTaskIdsList)  ");
			query.setParameterList("subTaskIdsList", subTaskIdsList);
			return query.list(); */ 
			
			Query query = getSession().createQuery("select distinct model.govtAlertSubTaskId, model.govtDepartmentDesignationOfficerId " +
					" from  GovtAlertSubTask model " +
					" where model.govtAlertSubTaskId in (:subTaskIdsList)  ");
			query.setParameterList("subTaskIdsList", subTaskIdsList);
			return query.list();  
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getStateLevelAssignedAlertClickViewAlertIds(List<Long> govtDepDesigOffcrIds,
				List<Long> govtOffcrIds,String type,List<Long> deptIds,Long statusId,Date fromDate,Date endDate){
		 	   StringBuilder sb = new StringBuilder();
		    	
		    	sb.append(" select distinct model.alertAssignedOfficer.alert.alertId from GovtAlertSubTask model ");
		    	
		    	sb.append(" where model.isDeleted = 'N' and model.alertAssignedOfficer.isDeleted='N' " +
		    			" and model.alertAssignedOfficer.alert.isDeleted = 'N' " );
		    	
		      if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
			    
	    	  if(fromDate != null && endDate != null){
	    		  sb.append(" and date(model.alertAssignedOfficer.insertedTime) between :fromDate and :endDate " ); 
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  sb.append(" and  model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) " );
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  //sb.append(" and  model.alertAssignedOfficer.alertStatus.alertStatusId = :statusId " );
	    		  sb.append(" and  model.alertSubTaskStatus.alertSubTaskStatusId = :statusId " );
	    	  }
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(fromDate != null && endDate != null){
	    		  query.setDate("fromDate",fromDate);
	    		  query.setDate("endDate",endDate);
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  query.setParameter("statusId", statusId);
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  query.setParameterList("deptIds", deptIds);
	    	  }
			  return query.list();
		    }
		@SuppressWarnings("unchecked")
		public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,Long statusId,
				Date fromDate,Date toDate,Long levelId,List<Long> levelValues,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList){
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct model.alert.alertId "+
					  " from " +
					  " GovtAlertSubTask model " );
			sb.append(" left join model.alert.edition EDS " +
				      " left join model.alert.tvNewsChannel TNC " +
				  	  " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA ");
		    sb.append(" where " +
					 " model.isDeleted = 'N' and model.alert.isDeleted='N' ");
			if(deptIds != null && deptIds.size() >0){
				sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) ");
			}
			if(locationLevelId != null && locationLevelId.longValue() > 0){
				sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
			}
			if(statusId != null && statusId.longValue() >0){
				sb.append(" and model.alertSubTaskStatus.alertSubTaskStatusId = :statusId ");
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
			
			if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
	    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) )");
	    	      if( calCntrIdList !=null && !calCntrIdList.isEmpty() &&  calCntrIdList.get(0) != 0){
		    	    	  sb.append(" or model.alert.alertCallerId is not null ");
		  			}else{
		  				sb.append(" and model.alert.alertCallerId is null ");
		  			}
		    	      sb.append(" )");
	    	    }
			if(fromDate != null && toDate != null){
				sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
			}
			
		 //	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId " );
			Query query = getSession().createQuery(sb.toString());
			if(deptIds != null && deptIds.size() >0)
			   query.setParameterList("deptIds",deptIds);
			
			if(locationLevelId != null && locationLevelId.longValue() > 0)
			   query.setParameter("locationLevelId",locationLevelId);
			
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
			if(levelId != null && levelValues != null && !levelValues.isEmpty()){
				 query.setParameterList("levelValues", levelValues); 
			}
			//query.setParameter("scopeId",scopeId);
			return query.list();
		}
		
		 public List<Long> getAssignedSubTaskOfficerIdsDtls(Long govtAlertSubTaskId){
			 Query query = getSession().createQuery(" select distinct model.subTaskGovtOfficerId from  GovtAlertSubTask model where model.govtAlertSubTaskId = :govtAlertSubTaskId and " +
			 		" model.isDeleted='N' and model.isApproved='Y' ");
			 query.setParameter("govtAlertSubTaskId", govtAlertSubTaskId);
			 return query.list();
		 }
		 public Object[] getSubTaskDetailsForSMS(Long govtAlertSubTaskId){
		    	Query query = getSession().createQuery(" select model.title, " +
		    			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
		    			" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName "
		    			+ " from GovtAlertSubTask model "
		    			+ " where model.govtAlertSubTaskId=:govtAlertSubTaskId and model.isDeleted='N' ");
		    	query.setParameter("govtAlertSubTaskId", govtAlertSubTaskId);
		    	return (Object[])query.uniqueResult();
		    }
		//Santosh
		 public List<Object[]> getSubTaskAlertLocationLevelWise(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
				   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
				   List<Long> calCntrIds){
		   	  StringBuilder queryStr = new StringBuilder();
		   	
			   	queryStr.append(" select distinct"); 
			   	
			   	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
			   	queryStr.append(" GDWL1.location_name as locationName ");//2
			   	queryStr.append(" from alert A");
				
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
					queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
				}
				queryStr.append(" ,alert_sub_task_status ALTS, ");
				queryStr.append(" govt_alert_sub_task AAO, ");
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
				
				queryStr.append("  and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
				queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
				queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
				
				queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
				queryStr.append(" and GDDO.govt_department_designation_officer_id = AAO.govt_department_designation_officer_id ");
				
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
					queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
				
				if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
					query.setParameter("govtDepartmentId",govtDepartmentId);
				}
				/*if(stateId != null && stateId.longValue() > 0){
		 			query.setParameter("stateId",stateId);
		 		}*/
				return query.list();
		   }
		 public List<Object[]> getSubTaskChildLocationByParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
				   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> deptScopeIdList,
				   List<Long> calCntrIds){
		   	  StringBuilder queryStr = new StringBuilder();
		   	
			   	queryStr.append(" select distinct");
			   	
				queryStr.append(" GDWLC.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
		     	queryStr.append(" GDWLC.location_name as locationName ");//2
			   	queryStr.append(" from alert A");
				
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
					queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
				}
				queryStr.append(" ,alert_sub_task_status ALTS, ");
				queryStr.append(" govt_alert_sub_task AAO, ");
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
				
				queryStr.append("  and AAO.is_deleted='N' ");
				queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
				if(parentGovtDepartmentScopeValue != null && parentGovtDepartmentScopeValue.longValue() > 0L){
		 			queryStr.append(" and GDWL1.govt_department_work_location_id=:parentGovtDepartmentScopeValue   ");
		 		}
				if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
					queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
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
		 		   
				if(fromDate != null && toDate != null){
					queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
				if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
					query.setParameter("govtDepartmentId",govtDepartmentId);
				}
				/*if(stateId != null && stateId.longValue() > 0){
		 			query.setParameter("stateId",stateId);
		 		}*/
				if(childLevelId != null && childLevelId.longValue() > 0L){
					query.setParameter("childLevelId",childLevelId); 
				}
				return query.list();
		   }
		 public List<Object[]> getSubTaskAlertCntBasedOnDepartmentLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
				   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
				    String group,String searchType,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue){
		   	StringBuilder queryStr = new StringBuilder();
		   	queryStr.append(" select ");
		   	
		   	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
		   	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
		   	queryStr.append(" GDWL1.location_name as locationName, ");//2
		   	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
		   		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
		   			queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
		   		}else{
		   			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
		   				queryStr.append(" AAO.alert_sub_task_status_id as govtDepartmentScopeId, ");//3
		   			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
		   				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
		   			}
		   		}
		   		
		   	}else{
		   		queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
		   	}
		   	
		   	queryStr.append(" count(distinct AAO.govt_alert_sub_task_id) as count");
		   	
				queryStr.append(" from ");
				
				queryStr.append(" alert A ");
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
					queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
				}
				queryStr.append(" ,alert_sub_task_status ALTS, ");
				queryStr.append(" govt_alert_sub_task AAO, ");
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
				queryStr.append(" A.alert_id = AAO.alert_id and A.is_deleted='N' ");
				queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
				queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
				queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
				queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
				
				queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
				queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
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
				
				if(fromDate != null && toDate != null){
					queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
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
							queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_sub_task_status_id ");
						}else{
							if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
								queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_sub_task_status_id ");
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
		 public List<Long> getSubTaskAlertIdsBasedOnLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
				   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long locationValue,Long deptLevelId
				   ,Long statusId,List<Long> calCntrIds){
		   	StringBuilder queryStr = new StringBuilder();
		   	queryStr.append(" select ");
		    queryStr.append(" distinct AAO.alert_id as alertIds ");
		         queryStr.append(" from ");  
		         queryStr.append(" alert A ");
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
					queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
				}
				queryStr.append(" ,alert_sub_task_status ALTS, ");
				queryStr.append(" govt_alert_sub_task AAO, ");
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
				
				queryStr.append(" and AAO.is_deleted='N' ");
				queryStr.append(" and AAO.alert_sub_task_status_id = ALTS.alert_sub_task_status_id  ");
				queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
				
				queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
				
				queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
				queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
				
				
				if(deptLevelId != null && deptLevelId > 0){
					queryStr.append(" and GDWL.govt_department_scope_id =:deptLevelId ");
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
				
				if(locationValue != null && locationValue.longValue() > 0L){
					queryStr.append(" and GDWL1.govt_department_work_location_id=:locationValue   ");
				}
				
				if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
					queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
				}
				if(statusId != null && statusId.longValue() > 0L){
					queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
				}
			/*	if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
					queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
				}*/
				if(fromDate != null && toDate != null){
					queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
				}
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
					if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l){
						queryStr.append(" or (A.alert_caller_id is not null) ");
					}else{
						queryStr.append("  and (A.alert_caller_id is null) ");
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
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
					query.setParameterList("printIdList", printIdList);  
					query.setParameterList("electronicIdList", electronicIdList);
				}
				if(levelId != null && levelValues != null && !levelValues.isEmpty()){
					query.setParameterList("levelValues",levelValues);  
				}
				if(deptLevelId != null && deptLevelId > 0){
					query.setParameter("deptLevelId",deptLevelId);
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
			/*	if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
					query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
				}*/
				if(locationValue != null && locationValue.longValue() > 0L){
					query.setParameter("locationValue",locationValue);
				}
				/*if(stateId != null && stateId.longValue() > 0){
		 			query.setParameter("stateId",stateId);
		 		}*/
				return query.list();
		   }
		 
		 public List<Object[]> getSubOrdinateFilterSubTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
					List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList){
		    	
		    	StringBuilder sb = new StringBuilder();  
			    sb.append("select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
			    		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName,model.govtAlertSubTaskId ");
		    	
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
	    	    	
			    sb.append(" , model.alertSubTaskStatus.alertSubTaskStatusId,model.insertedTime,model.updatedTime ");
			    
		    	sb.append(" from GovtAlertSubTask model" +
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
		    	          " left join UA.panchayat P " +
		    	          " left join  model.govtDepartmentDesignationOfficer.govtDepartmentScope GDS " );
		    	sb.append(" left join model.alert.edition EDS " +
		  	    		   " left join model.alert.tvNewsChannel TNC  ");
			    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
			    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
			    
			    if(desigIds != null && !desigIds.isEmpty())
		  	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:desigIds)");
			    
			    if(fromDate != null && endDate != null){
			    	sb.append(" and date(model.createdTime) between :fromDate and :endDate " );
			    }
			   
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
			    	sb.append("  and model.alertSubTaskStatus.alertSubTaskStatusId in (:statusIds) ");
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
			    if(statusIds != null && statusIds.size() > 0){
			    	query.setParameterList("statusIds", statusIds);
			    }
			    
			    if(levelId != null && levelValues != null && !levelValues.isEmpty())
			    	query.setParameterList("levelValues", levelValues);
			    	
			    if(priorityId != null && priorityId.longValue() >0l)
			    	query.setParameter("priorityId", priorityId);
			    
			    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() ){
		    	      query.setParameterList("printIdList", printIdsList);
		    	      query.setParameterList("electronicIdList", electronicIdsList);
		    	    }
			    
			    return query.list();
			    	
		    }
		//srujana
	       public List<Object[]> getSubTaskStatusIds(Set<Long> alertSet){   
	   		StringBuilder queryStr = new StringBuilder();
	   		queryStr.append(" select distinct ");
	   		queryStr.append(" A.alert_id as alert_id, " +//0
	   				        " A.created_time as created_time, " +//1
	   				        " A.updated_time as updated_time, " +//2
	   				        " A.alert_sub_task_status_id as alert_status_id, " +//3
	   				        " ALTS.status as alert_status, " +//4
	   				        " A.alert_category_id as alert_category_id, " +//5
	   				        " AC.category as category, " +//6
	   				        " AIS.alert_impact_scope_id as alert_impact_scope_id, " +//7
	   				        " AIS.impact_scope as impact_scope, " +//8
	   				        " A.title as title, " +//9
	   				        " C.name as name, " +//10
	   				        " D.district_name as district_name, " +//11
	   				        " A.alert_sub_task_status_id as alert_source_id, " +//12
	   				        " ALTSRC.source as source, " +//13
	   				        " 0 as edition_type_id, " +//14
	   				        " '' as edition_type, " +//15
	   				        " EDS.edition_id as edition_id, " +//16
	   				        " EDS.edition_alias as edition_alias, " +//17
	   				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
	   				        " TNC.channel_name as channel_name," + //19
	   				        " S.state_name, "+ //20
	   					 	" T.tehsil_name as tehsilName, " +//21
	   				        " P.panchayat_name as panchayatName, " +//22
	   				        " LEB.name as localElectionBodyNeme, " +//23
	   				        " ALTSVR.severity_color as severityColor, "+ //24
	   						" ALTS.alert_color as color"); //25
	   		queryStr.append(" from govt_alert_sub_task A ");  
	   		queryStr.append(" left outer join tv_news_channel TNC on A.tv_news_channel_id = TNC.tv_news_channel_id ");//
	   		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");//
	   		queryStr.append(" left outer join alert_sub_task_status ALTSRC on ALTSRC.alert_sub_task_status_id = A.alert_sub_task_status_id ");//
	   		queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");//
	   		queryStr.append(" left outer join alert_severity ALTSVR on ALTSVR.alert_severity_id = A.alert_severity_id ");//
	   		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");//
	   		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");//
	   		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");//
	   		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");//
	   		queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");//
	   		queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");//
	   		queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");//
	   		queryStr.append(" join alert_sub_task_status ALTS on A.alert_sub_task_status_id=ALTS.alert_sub_task_status_id ");//
	   		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
	   		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");//
	   		queryStr.append(" join alert model on model.alert_id = A.alert_id ");
	   		queryStr.append(" where ");
	   		queryStr.append(" A.alert_id in (:alertSet) ");
	   		Query query = getSession().createSQLQuery(queryStr.toString())
	   				.addScalar("alert_id", Hibernate.LONG)//0
	   				.addScalar("created_time", Hibernate.STRING)//1
	   				.addScalar("updated_time", Hibernate.STRING)//2
	   				.addScalar("alert_status_id", Hibernate.LONG)//3
	   				.addScalar("alert_status", Hibernate.STRING)//4
	   				.addScalar("alert_category_id", Hibernate.LONG)//5
	   				.addScalar("category", Hibernate.STRING)//6
	   				.addScalar("alert_impact_scope_id", Hibernate.LONG)//7
	   				.addScalar("impact_scope", Hibernate.STRING)//8
	   				.addScalar("title", Hibernate.STRING)//9
	   				.addScalar("name", Hibernate.STRING)//10
	   				.addScalar("district_name", Hibernate.STRING)//11
	   				.addScalar("alert_source_id", Hibernate.LONG)//12
	   				.addScalar("source", Hibernate.STRING)//13
	   				.addScalar("edition_type_id", Hibernate.LONG)//14
	   				.addScalar("edition_type", Hibernate.STRING)//15
	   				.addScalar("edition_id", Hibernate.LONG)//16
	   				.addScalar("edition_alias", Hibernate.STRING)//17
	   				.addScalar("tv_news_channel_id", Hibernate.LONG)//18
	   				.addScalar("channel_name", Hibernate.STRING) // 19
	   				.addScalar("state_name", Hibernate.STRING)//20
	   				.addScalar("tehsilName", Hibernate.STRING)//21
	   				.addScalar("panchayatName", Hibernate.STRING)//22
	   				.addScalar("localElectionBodyNeme", Hibernate.STRING)//23
	   				.addScalar("severityColor", Hibernate.STRING)//24
	   				.addScalar("color", Hibernate.STRING);//25
	   		if(alertSet != null && alertSet.size() > 0){
	   			query.setParameterList("alertSet", alertSet);   
	   		}
	   		return query.list(); 
	   	}
}
