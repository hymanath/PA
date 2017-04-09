package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
        		sb.append(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus,count(model.alertAssignedOfficerId),model.alertStatus.color from AlertAssignedOfficerNew model ");
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
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,Long deptId){
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
	  	  return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.alert.alertId "+
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
		Query query = getSession().createQuery(sb.toString());
		if(deptId != null && deptId.longValue() >0)
		      query.setParameter("deptId",deptId);
		if(locationLevelId != null && locationLevelId.longValue() > 0)
		      query.setParameter("locationLevelId",locationLevelId);
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
        	
        	sb.append(" from AlertAssignedOfficerNew model " );
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
    	    	sb.append("  and mode.alertStatus.alertStatusId = 4 ");
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
}
