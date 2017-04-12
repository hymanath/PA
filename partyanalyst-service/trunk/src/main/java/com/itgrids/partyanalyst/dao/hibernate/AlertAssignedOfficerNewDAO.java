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
        public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds){
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
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
    	 
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
       
        
       public List<Object[]> getAssignedStatuses(){
    		Query query = getSession().createQuery(" SELECT model.alertStatus.alertStatusId,model.alertStatus.alertStatus " +
    				" FROM AlertAssignedOfficerNew model " +
    				" WHERE  " +
    				" model.isDeleted = 'N' " +
    				" GROUP BY model.alertStatus.alertStatusId " +
    				" ORDER BY model.alertStatus.statusOrder ");
    		return query.list();
      }
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
      
      public List<AlertAssignedOfficerNew> getModelForAlert(Long alertId){
  		Query query = getSession().createQuery(" select model from AlertAssignedOfficerNew model where model.alertId=:alertId and model.isDeleted='N' ");
  		query.setParameter("alertId", alertId);
  		return query.list();
  	}
        public List<Object[]> getDistrictOfficerAlertsCount(Long govtDepDesigOffcrId,Long govtOffcrId,String type){
        	StringBuilder sb = new StringBuilder();
	    	  
        	if(type != null && type.equalsIgnoreCase("today")){
        		sb.append(" select model.alertAssignedOfficerId,model.alert.alertId from AlertAssignedOfficerNew model ");
        	}else{
        		sb.append(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus,count(model.alert.alertId),model.alertStatus.color from AlertAssignedOfficerNew model ");
        	}
        	sb.append(" where model.isDeleted = 'N' " );
        	
	    	  
        	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and model.insertedTime = :todayDate " ); 
	    	  }
	    	  
	    	  if(type != null && !type.equalsIgnoreCase("today")){
	    		  sb.append(" group by model.alertStatus.alertStatusId ");
	    	  }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  return query.list();
        }
        
        //swadhin alertids based on status.
        public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId){
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
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
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
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,Long deptId){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
	  	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from AlertAssignedOfficerNew model ");
	  	     
	  	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	   if(deptId != null && deptId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
	  	     }

	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId ");
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
	  	    query.setParameter("scopeId",5l);
	  	  return query.list();
	  	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,Long deptId,Long levelId){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.alertStatus.alertStatusId," +
	  	      		    " model.alertStatus.alertStatus," +
	  	      		    " model.alertStatus.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from AlertAssignedOfficerNew model ");
	  	     
	  	     sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	     if(scopeId != null && scopeId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:scopeId ");
	  	     }
	  	     if(deptId != null && deptId.longValue() >0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId " );
	  	     }
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	    if(levelId != null && levelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId =:levelId ");
	  	     }
	  	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId,  " +
	    		" model.alertStatus.alertStatusId " );
	     	    
	  	    Query query = getSession().createQuery(sb.toString());
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(scopeId != null && scopeId.longValue() > 0){
	  	    	 query.setParameter("scopeId",scopeId);
	  	    }
	  	  if(deptId != null && deptId.longValue() >0){
	  		  query.setParameter("deptId",deptId);
	  	  }
	  	 if(levelId != null && levelId.longValue() > 0){
	  		query.setParameter("levelId",levelId);
  	     }
	  	  return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.alert.alertId "+
				  " from " +
				  " AlertAssignedOfficerNew model " +
				  " where " +
				  " model.isDeleted = 'N' ");
		if(deptId != null && deptId.longValue() >0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() > 0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
		}
		if(statusId != null && statusId.longValue() >0){
			sb.append(" and model.alertStatus.alertStatusId = :statusId ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(deptId != null && deptId.longValue() >0)
		      query.setParameter("deptId",deptId);
		if(locationLevelId != null && locationLevelId.longValue() > 0)
		      query.setParameter("locationLevelId",statusId);
		if(statusId != null && statusId.longValue() >0){
			query.setParameter("statusId",statusId);
		}
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
        
        public List<Long> getDistrictOfficerAlertsIds(Long govtDepDesigOffcrId,Long govtOffcrId,String type){
        	StringBuilder sb = new StringBuilder();
	    	  
        	  sb.append(" select distinct model.alert.alertId from AlertAssignedOfficerNew model ");
        	
        	  sb.append(" where model.isDeleted = 'N' " );
        	
	    	  
        	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and model.insertedTime = :todayDate " ); 
	    	  }
	    	  
	    	  
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
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
    											" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
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
        		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,String searchType){
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
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
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
    		if(districtWorkLocationId != null && districtWorkLocationId.longValue() > 0L){
    			query.setParameter("districtWorkLocationId",districtWorkLocationId);
    		}
    		return query.list();
        }
        //division scope lvl
        public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
        		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
        		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType){
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
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
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
        public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
        		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
        		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,String searchType){
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
    		queryStr.append(" A.alert_id = AAO.alert_id  ");
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

          
    @SuppressWarnings("unchecked")
    public List<Object[]> getDistrictOfficerMyAlertsCountView(Long govtDepDesigOffcrId,Long govtOffcrId,String type){
        	StringBuilder sb = new StringBuilder();
        	  
        	if(type != null && type.equalsIgnoreCase("today")){
        		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
        				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartment.departmentName," +
        				" count(distinct  model.alert.alertId) ");
        	}else if(type != null && type.equalsIgnoreCase("completed")){
        		sb.append(" select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
        				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartment.departmentName," +
        				" count(distinct  model.alert.alertId) ");
        	}
        	sb.append(" from AlertAssignedOfficerNew model ");
        	
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
        		            	
        		    	    	  
        	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  sb.append(" and model.insertedTime = :todayDate " ); 
	    	  }
	    	  
	    	  sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId ");
	    	 
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  if(type != null && type.equalsIgnoreCase("today")){
	    		  query.setParameter("todayDate", new DateUtilService().getCurrentDateAndTime());
	    	  }
	    	  return query.list();
        }  
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(Long govtDepDesigOffcrId,Long govtOffcrId){
        	StringBuilder sb = new StringBuilder();
        	sb.append(" select model.alertStatus.alertStatusId," +
        			"  model.alertStatus.alertStatus," +
        			"  model.alertStatus.color," +
        			"  count(distinct model.alert.alertId) ");
        	
        	sb.append(" from AlertAssignedOfficerNew model ");
        	
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
        		            	
        		    	    	  
        	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtOfficer.govtOfficerId = :govtOffcrId " );
	    	  }
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId = :govtDepDesigOffcrId " );
	    	  }
	    	  
	    	  sb.append(" group by model.alertStatus.alertStatusId ");
	    	 
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() >0l){
	    		  query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
	    	  }
	    	  if(govtOffcrId != null && govtOffcrId.longValue() >0l){
	    		  query.setParameter("govtOffcrId", govtOffcrId);  
	    	  }
	    	  return query.list();
        }
	public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,Long statusId,Long govtDeprtMentScopeId){
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
		queryStr.append(" A.alert_id = AAO.alert_id  ");
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
		
		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.state_id  ");
		}else{
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.district_id  ");
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
	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
    		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId){
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
		queryStr.append(" A.alert_id = AAO.alert_id  ");
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
			queryStr.append(" and  AAO.alert_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
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
	public List<Long> getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
    		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
    		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId){
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
		queryStr.append(" A.alert_id = AAO.alert_id  ");
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and  AAO.alert_status_id = :statusId");
		}
		if(govtDeprtMentScopeId != null && govtDeprtMentScopeId.longValue() > 0L){
			queryStr.append(" and  GDWL.govt_department_scope_id = :govtDeprtMentScopeId");
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
  	    
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
		if(filter != null && !filter.trim().isEmpty() && filter.trim().equalsIgnoreCase("true")){
			query.addScalar("parentGovtDepartmentWorkLocationId", Hibernate.LONG);
    		query.addScalar("DISTRICT", Hibernate.STRING);
    		query.addScalar("GDWLI", Hibernate.LONG);
    		query.addScalar("DIVISION", Hibernate.STRING);
    	}
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
}// GDWL.govt_department_work_location_id, GDS.govt_department_scope_id,AAO.alert_status_id
