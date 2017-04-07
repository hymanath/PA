package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
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
    	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName " );
     	    }else if(type.equalsIgnoreCase("Department")){
     	      sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
     	      		    " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName ");	
     	    }
    	     sb.append(" ,count(distinct model.alert.alertId) ");
    	      
    	    sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC " +
    	          " left join model.govtDepartmentDesignationOfficer.userAddress UA " +
    	          " left join UA.state S " +
    	          " left join UA.district D" +
    	          " left join UA.constituency C" +
    	          " left join UA.tehsil T" +
    	          " left join UA.localElectionBody LEB" +
    	          " left join UA.panchayat P" +
    	          " left join UA.ward W");
    	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
    	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
    	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
    	    
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty())
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) )");
    	 
    	    if(fromDate != null && toDate != null)
    	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    
    	    if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 1l)
    	      sb.append(" and S.stateId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 5l)
    	      sb.append(" and D.districtId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 4l)
    	      sb.append(" and C.constituencyId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
    	      sb.append(" and T.tehsilId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 6l)
    	      sb.append(" and P.panchayatId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 7l)
    	      sb.append(" and LEB.localElectionBodyId in (:levelValues)");
    	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId == 8l)
    	      sb.append(" and W.constituencyId in (:levelValues)");
    	    
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
}
