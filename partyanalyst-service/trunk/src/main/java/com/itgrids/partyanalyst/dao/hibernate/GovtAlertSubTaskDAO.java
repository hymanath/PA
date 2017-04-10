package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class GovtAlertSubTaskDAO extends GenericDaoHibernate<GovtAlertSubTask, Long> implements
		IGovtAlertSubTaskDAO {
       public GovtAlertSubTaskDAO(){
    	   super(GovtAlertSubTask.class);
       }
       
       public List<Object[]> getDistrictOfficerAlertsSubTasksCount(Long govtDepDesigOffcrId,Long govtOffcrId,String countType,String type){
    	   StringBuilder sb = new StringBuilder();
	    	
    	   
       	if(countType != null && countType.equalsIgnoreCase("today")){
       		sb.append(" select model.govtAlertSubTaskId from GovtAlertSubTask model ");
       	}else{
       		sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId,model.alertSubTaskStatus.status,count(model.govtAlertSubTaskId),model.alertSubTaskStatus.color from GovtAlertSubTask model ");
       	}
       	sb.append(" where model.isDeleted = 'N' " );
       	
       	if(type != null && type.equalsIgnoreCase("mySubTasks")){
       	  	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
       	  	if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    }else{
       		if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
       		
       			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
       				sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
       			}
	    	  
       	}
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  sb.append(" and model.createdTime = :todayDate " ); 
	    	  }
	    	  
	    	  if(countType != null && !countType.equalsIgnoreCase("today")){
	    		  sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId ");
	    	  }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
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
	public List<Object[]> getDistrictLevelDeptWiseStatusOverViewForSubTask(Date fromDate, Date toDate,Long scopeId,Long deptId,Long levelId){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.alertSubTaskStatus.alertSubTaskStatusId," +
	  	      		    " model.alertSubTaskStatus.status," +
	  	      		    " model.alertSubTaskStatus.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from GovtAlertSubTask model ");
	  	     
	  	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	     if(scopeId != null && scopeId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:scopeId ");
	  	     }
	  	     if(deptId != null && deptId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId ");
	  	     }
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
	  	    if(levelId != null && levelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId =:levelId ");
	  	     }
	    sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId, " +
	    		" model.alertSubTaskStatus.alertSubTaskStatusId " );
	     	    
	  	    Query query = getSession().createQuery(sb.toString());
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(scopeId != null && scopeId.longValue() > 0){
	  	    	 query.setParameter("scopeId",scopeId);
	  	    }
	  	    if(deptId != null && deptId.longValue() > 0){
	  	    	query.setParameter("deptId",deptId);
	  	    }
	  	    if(levelId != null && levelId.longValue() > 0){
	  	    	query.setParameter("levelId",levelId);
	  	    }
	  	  return query.list();
	  	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelViewForSubtask(Date fromDate, Date toDate,Long deptId){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from GovtAlertSubTask model ");
	  	     
	  	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");

	  	     if(deptId != null && deptId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
	  	     }
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
	  	  
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
	  	  return query.list();
	  	}
   	
    public List<Object[]> getSubOrdinateTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,String type){
    	
    	StringBuilder sb = new StringBuilder();  
	    sb.append("select model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	    		"model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName,model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
	    		"model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName,count(model.govtAlertSubTaskId) ");
    	
    	sb.append(" from GovtAlertSubTask model " );
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
	    	sb.append("  and mode.alertSubTaskStatus.alertSubTaskStatusId = 3 ");
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
	    
	    if(priorityId != null && priorityId.longValue() >0l)
	    	query.setParameter("priorityId", priorityId);
	    
	    return query.list();
	    	
    }
    
    public List<Long> getDistrictOfficerSubTasksAlertIds(Long govtDepDesigOffcrId,Long govtOffcrId,String countType,String type){
 	   StringBuilder sb = new StringBuilder();
	    	
 	   
    	
    		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
    	
    	sb.append(" where model.isDeleted = 'N' " );
    	
    	if(type != null && type.equalsIgnoreCase("mySubTasks")){
    	  	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
    	  	if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    }else{
    		if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
    		
    			if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
    				sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
    			}
	    	  
    	}
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  sb.append(" and model.createdTime = :todayDate " ); 
	    	  }
	    	  
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  if(countType != null && countType.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  return query.list();
    }
   	
}
