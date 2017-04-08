package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
       		sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId,model.alertSubTaskStatus.status,count(model.govtAlertSubTaskId) from GovtAlertSubTask model ");
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
   	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate,int startIndex,int maxIndex){
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
     	    if(maxIndex >0){
     	    	query.setFirstResult(startIndex);
     	    	query.setMaxResults(maxIndex);
     	    }
     	  return query.list();
     	}
}
