package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
       
       public List<Object[]> getDistrictOfficerAlertsSubTasksCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type,Date fromDate,Date toDate){
    	   StringBuilder sb = new StringBuilder();
	    	
    	   
       	if(countType != null && countType.equalsIgnoreCase("today")){
       		sb.append(" select model.govtAlertSubTaskId from GovtAlertSubTask model ");
       	}else{
       		sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId,model.alertSubTaskStatus.status,count(model.govtAlertSubTaskId),model.alertSubTaskStatus.color from GovtAlertSubTask model ");
       	}
       	sb.append(" where model.isDeleted = 'N' " );
       	
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
    
    public List<Long> getDistrictOfficerSubTasksAlertIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type){
 	   StringBuilder sb = new StringBuilder();
	    	
 	   
    	
    		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
    	
    	sb.append(" where model.isDeleted = 'N' and model.isApproved='Y' " );
    	
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
	    	  }
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
    	Query query = getSession().createQuery(" select distinct model.govtAlertSubTaskId,model.title,'',date(model.dueDate)  "
    			+ " from GovtAlertSubTask model"
    			+ " where model.alertId=:alertId and model.isDeleted='N' order by model.govtAlertSubTaskId desc ");
    	query.setParameter("alertId", alertId);
    	return query.list();
    }
   public List<Object[]> getDistrictOfficerAlertsSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type){
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
   	
   		sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
   	
   		  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	  		  sb.append(" and model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	  	  }
   		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	  		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	  	  }
	  	  if(type != null && type.equalsIgnoreCase("today")){
	  		  sb.append(" and date(model.createdTime) between :todayDate and :todayDate " ); 
	  	  }
 	  
	  	  sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  return query.list();
   }
   public List<Object[]> getDistrictOfficerMyAssignedSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type){
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
   	
   		sb.append(" where model.alertAssignedOfficer.isDeleted = 'N' and model.alertAssignedOfficer.alert.isDeleted = 'N' " );
   	
   		if(govtOffcrIds != null && govtOffcrIds.size()>0){
	  		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	  	  }
   		if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	  		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	  	  }
	  	  if(type != null && type.equalsIgnoreCase("today")){
	  		  sb.append(" and model.alertAssignedOfficer.insertedTime = :todayDate " ); 
	  	  }
 	  
	  	  sb.append(" group by model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  return query.list();
   }
   public List<Object[]> getDistrictOfficerMySubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds){
   	StringBuilder sb = new StringBuilder();
   	sb.append(" select model.alertSubTaskStatus.alertSubTaskStatusId," +
   			"  model.alertSubTaskStatus.status," +
   			"  model.alertSubTaskStatus.color," +
   			"  count(distinct model.govtAlertSubTaskId) ");
   	
   	sb.append(" from GovtAlertSubTask model ");
   	
   	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
   	
   	  
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  sb.append(" and model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
   	  }
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
   	  }
   	  
   	  sb.append(" group by model.alertSubTaskStatus.alertSubTaskStatusId ");
   	 
   	  Query query = getSession().createQuery(sb.toString());
   	  
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
   	  }
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
   	  }
   	  return query.list();
   }
   public List<Object[]> getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds){
   	StringBuilder sb = new StringBuilder();
   	sb.append(" select model.alertAssignedOfficer.alertStatus.alertStatusId," +
   			"  model.alertAssignedOfficer.alertStatus.alertStatus," +
   			"  model.alertAssignedOfficer.alertStatus.color," +
   			"  count(distinct model.govtAlertSubTaskId) ");
   	
   	sb.append(" from GovtAlertSubTask model ");
   	
   	sb.append(" where model.alertAssignedOfficer.isDeleted = 'N' and model.alertAssignedOfficer.alert.isDeleted = 'N' " );
   	
   	  
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  sb.append(" and model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
   	  }
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
   	  }
   	  
   	  sb.append(" group by model.alertAssignedOfficer.alertStatus.alertStatusId ");
   	 
   	  Query query = getSession().createQuery(sb.toString());
   	  
   	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
   		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
   	  }
   	if(govtOffcrIds != null && govtOffcrIds.size()>0){
   		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
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
		}else{
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
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
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		return query.list();
   }
   
   public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
		   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds){
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
		
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		return query.list();
   }
   //sub division scope lvl
   public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(Date fromDate,Date toDate,Long stateId,
		   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
		   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
		   Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds){
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
		
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		}else{
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
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
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
	    	    if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds){
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
		}
		return query.list();
   }
   public List<Long> getSubDivisionWorkLocationDeptScopeWiseSubTaskCountDetails(Date fromDate,Date toDate,Long stateId,
		   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
		   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
		   Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds){
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_sub_task_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and date(AAO.created_time) between :fromDate and :toDate ");
		}
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) ");
			if( calCntrIds !=null && !calCntrIds.isEmpty() ){
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
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			query.setParameter("govtDeprtMentScopeId",govtDeprtMentScopeId);
		}
		return query.list();
   }
  public List<Long> getDistrictOffcrSubTasksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId){
	   StringBuilder sb = new StringBuilder();
	    	
   		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
   	
   	sb.append(" where model.isDeleted = 'N'  and model.alert.isDeleted='N' and model.isApproved='Y' " );
   	
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
	    	  return query.list();
   }
    public List<Long> getDistrictOffcerSubTsksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId){
	   StringBuilder sb = new StringBuilder();
   		sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
   	
   	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' and model.isApproved='Y' " );
   	
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
	    	  return query.list();
   }
  @SuppressWarnings("unchecked")
public List<Object[]> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds){
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
	     sb.append(" ,count(distinct model.alert.alertId) ");
	      
	    sb.append(" from  GovtAlertSubTask model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    
	    if(departmentIds != null && !departmentIds.isEmpty())
	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
	 
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
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }  
	    else if(printIdsList != null && !printIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }
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
		      List<Long> levelValues,String type,List<Long> alertSubTaskStatusIds,List<Long> departmentScopeIds){
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
	     sb.append(" ,count(distinct model.alert.alertId) ");
	      
	    sb.append(" from GovtAlertSubTask model" +
	          " left join model.alert.edition EDS " +
	          " left join model.alert.tvNewsChannel TNC " +
	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    
	    if(departmentIds != null && !departmentIds.isEmpty())
	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	    
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
	 
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
	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }  
	    else if(printIdsList != null && !printIdsList.isEmpty()){
	      query.setParameterList("printIdList", printIdsList);
	    }else if(electronicIdsList != null && !electronicIdsList.isEmpty()){
	      query.setParameterList("electronicIdList", electronicIdsList);
	    }
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
				Date fromDate,Date toDate,Long desigDeptOfficerId,Long officerId,Long scopeId){
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct model.alert.alertId "+
					  " from " +
					  " GovtAlertSubTask model " +
					  " where " +
					  " model.isDeleted = 'N' and model.alert.isDeleted='N' ");
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
			
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId " );
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
			query.setParameter("scopeId", 5l);
			
			query.setParameter("scopeId",scopeId);
			return query.list();
		}
		public List<Long> getStateLevelAlertclickViewAlertIds(List<Long> govtDepDesigOffcrIds,
				List<Long> govtOffcrIds,String type,Long deptId,Long statusId){
		 	   StringBuilder sb = new StringBuilder();
		    	
		    	sb.append(" select distinct model.alert.alertId from GovtAlertSubTask model ");
		    	
		    	sb.append(" where model.isDeleted = 'N' " );
		    	
		      if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.subTaskGovtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
			    
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and date(model.createdTime) between :todayDate and :todayDate " ); 
	    	  }
	    	  if(deptId != null && deptId.longValue() > 0){
	    		  sb.append(" and  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId " );
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
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  query.setParameter("statusId", statusId);
	    	  }
	    	  if(deptId != null && deptId.longValue() > 0){
	    		  query.setParameter("deptId", deptId);
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
		@SuppressWarnings("unchecked")
		public List<Long> getStateLevelAssignedAlertClickViewAlertIds(List<Long> govtDepDesigOffcrIds,
				List<Long> govtOffcrIds,String type,Long deptId,Long statusId){
		 	   StringBuilder sb = new StringBuilder();
		    	
		    	sb.append(" select distinct model.alertAssignedOfficer.alert.alertId from GovtAlertSubTask model ");
		    	
		    	sb.append(" where model.isDeleted = 'N' and model.alertAssignedOfficer.isDeleted = 'N' " +
		    			" and model.alertAssignedOfficer.alert.isDeleted = 'N' " );
		    	
		      if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and  model.alertAssignedOfficer.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
    			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
			    
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and date(model.alertAssignedOfficer.insertedTime) between :todayDate and :todayDate " ); 
	    	  }
	    	  if(deptId != null && deptId.longValue() > 0){
	    		  sb.append(" and  model.alertAssignedOfficer.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId " );
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  sb.append(" and  model.alertAssignedOfficer.alertStatus.alertStatusId = :statusId " );
	    	  }
	    	Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
	    	  }
	    	  if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  query.setParameterList("govtOffcrIds", govtOffcrIds);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  if(statusId != null && statusId.longValue() > 0){
	    		  query.setParameter("statusId", statusId);
	    	  }
	    	  if(deptId != null && deptId.longValue() > 0){
	    		  query.setParameter("deptId", deptId);
	    	  }
			  return query.list();
		    }
}
