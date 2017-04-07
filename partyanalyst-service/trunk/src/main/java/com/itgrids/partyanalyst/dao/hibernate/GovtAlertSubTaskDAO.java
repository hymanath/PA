package com.itgrids.partyanalyst.dao.hibernate;

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
}
