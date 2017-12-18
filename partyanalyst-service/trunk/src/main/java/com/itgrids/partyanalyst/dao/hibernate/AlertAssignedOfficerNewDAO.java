package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertAssignedOfficerNewDAO extends GenericDaoHibernate<AlertAssignedOfficerNew, Long> implements IAlertAssignedOfficerNewDAO {
	
        public AlertAssignedOfficerNewDAO(){
        	super(AlertAssignedOfficerNew.class);
        }
        public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
        		String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
        		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
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
     	    }else if(type.equalsIgnoreCase("alertSource")){
     	    	sb.append(" AC.alertCategoryId,AC.category,'',model.alertStatus.alertStatusId," +
     	    			  " model.alertStatus.alertStatus,model.alertStatus.color " );	
     	    }
    	     sb.append(" ,count(distinct model.alert.alertId) ");
    	      
    	     sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC " +
    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
    	          " left join model.alert.alertCategory AC" +
    	          " left join model.alert.socialMediaType SMT " +
    	          " left join model.alert.alertCallCenterType ACCT ");
         sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
    	          " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
    	    	  " AC.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
    	    
    	     if(departmentIds != null && !departmentIds.isEmpty())
    	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
    	    
    	      if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
    	  	      sb.append(" and (EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) ) ");
    		   } 
    	     
    	      if(fromDate != null && toDate != null)
      	        sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    
    	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
    	      
    	      if(querySb.length() > 0){
    	    	 sb.append(querySb); 
    	      }
    	    
    	    if(alertStatusIds != null && alertStatusIds.size() > 0){
    	    	sb.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
    	    }
    	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
    	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in (:departmentScopeIds)");
    	    }
    	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
    	    	sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
    	    }
    	    if(type.equalsIgnoreCase("Status")){
      	      sb.append(" group by model.alertStatus.alertStatusId " );
      	    }else if(type.equalsIgnoreCase("Level")){
      	      sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " );
       	    }else if(type.equalsIgnoreCase("Department")){
       	    	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId " );	
       	    }else if(type.equalsIgnoreCase("alertSource")){
     	    	sb.append(" group by  model.alert.alertCategory.alertCategoryId,model.alertStatus.alertStatusId " +
     	    			 " order by model.alert.alertCategory.order asc,model.alertStatus.statusOrder asc");	
     	    }
    	  
    	    Query query = getSession().createQuery(sb.toString());
    	    if(departmentIds != null && !departmentIds.isEmpty())
    	      query.setParameterList("departmentIds", departmentIds);
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
    	       query.setParameterList("printIdList", printIdsList);
       	       query.setParameterList("electronicIdList", electronicIdsList);
       	       query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
       	       query.setParameterList("calCntrIds", calCntrIds);
	       	   query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
       	     }  
    	    if(fromDate != null && toDate != null){
    	        query.setDate("fromDate", fromDate);
    	        query.setDate("toDate", toDate);
    	    }
    	    if(querySb.length()>0){
    	    	query.setParameterList("levelValues", levelValues);
    	    }
    	    if(alertStatusIds != null && alertStatusIds.size() > 0){
    	    	 query.setParameterList("alertStatusIds", alertStatusIds);
    	    }
    	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
    	      query.setParameterList("departmentScopeIds", departmentScopeIds);
    	    }
    	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
    	    	query.setParameterList("alertSeverityIds", alertSeverityIds);
    	    }
    	      return query.list();
    	}
       
        
       public List<Object[]> getAssignedStatuses(){
    		Query query = getSession().createQuery(" SELECT model.alertStatus.alertStatusId,model.alertStatus.alertStatus " +
    				" FROM AlertAssignedOfficerNew model " +
    				" WHERE  " +
    				" model.isDeleted = 'N' and model.alertStatus.alertStatusId != 1 " +
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
      
      public List<AlertAssignedOfficerNew> getModelForApprovedAlert(Long alertId){
    		Query query = getSession().createQuery(" select model from AlertAssignedOfficerNew model where model.alertId=:alertId and model.isDeleted='N' " +
    				" and model.isApproved ='Y' ");
    		query.setParameter("alertId", alertId);
    		return query.list();
      }
     
        public List<Object[]> getDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
        	StringBuilder sb = new StringBuilder();
	    	  
        	if(type != null && type.equalsIgnoreCase("today")){
        		sb.append(" select model.alertAssignedOfficerId, model.alert.alertId from AlertAssignedOfficerNew model ");
        	}else{
        		sb.append(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus,count(distinct model.alert.alertId),model.alertStatus.color from AlertAssignedOfficerNew model ");
        	}
        	sb.append(" left join model.alert.edition EDS " +
	                  " left join model.alert.tvNewsChannel TNC" +
	                  " left join model.alert.socialMediaType SMT" +
	                  " left join model.alert.alertCallCenterType ACCT ");
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
        	
             if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
      	     }
        	 if(govtOffcrIds != null && govtOffcrIds.size()>0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  sb.append(" and model.alert.alertStatusId in (:alertStatusIds)");
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
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	      query.setParameterList("calCntrIdList", calCntrIdList);
	    	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	    		   query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	    	   }  
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  query.setParameterList("alertSeverityIds", alertSeverityIds);
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  query.setParameterList("alertStatusIds", alertStatusIds);
	    	  }
	    	  return query.list();
        }
       
        //swadhin alertids based on status.
        public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, 
        		List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues,
        		Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,
        		List<Long> priorityIdList,List<Long> alertSourceIdList,
        		List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
    		StringBuilder sb = new StringBuilder();  
    	    sb.append("select distinct model.alert.alertId ");
    	    sb.append(" from AlertAssignedOfficerNew model" +
    	          " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC" +
    	          " left join model.alert.socialMediaType SMT" +
    	          " left join model.alert.alertCallCenterType ACCT " +
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
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
    	  	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) ) ");
    		 } 
    	 
    	      
    	    if(fromDate != null && toDate != null){
    	    	 sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
    	    }
    	    
    	     StringBuilder querySb =  prepareQueryBasedOnUserAccessLevel(levelId,levelValues); //Getting Dynamic Query Based On User Access Level
    	      if(querySb.length() > 0){
    	    	  sb.append(querySb); 
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
    	    
    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
    	    	 query.setParameterList("printIdList", printIdsList);
       	         query.setParameterList("electronicIdList", electronicIdsList);
       	         query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
       	         query.setParameterList("calCntrIds", calCntrIds);
       	         query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		     query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		     query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		     query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
       	    }
    	      
    	  
    	    if(fromDate != null && toDate != null){
    	        query.setDate("fromDate", fromDate);
    	        query.setDate("toDate", toDate);
    	    }
    	    if(querySb.length() > 0){
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
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,List<Long> deptIds,List<Long> printIdsList,
			List<Long> electronicIdsList,List<Long> calCntrIdList,Long levelId,List<Long> levelValues,String type,List<Long> deptScopeIds,
			List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	  		StringBuilder sb = new StringBuilder();  
	  		 sb.append("select ");
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      " model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	     if(type != null && type.equalsIgnoreCase("ScopeLevel")){
	  	    	  sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId," +
		  	      		   " model.govtDepartmentDesignationOfficer.govtDepartmentScope.levelName," +
		  	      		   " model.govtDepartmentDesignationOfficer.govtDepartmentScope.color, " );
	  	     }else if(type != null && type.equalsIgnoreCase("AlertCategory")){
	  	 	  sb.append(" model.alert.alertCategory.alertCategoryId," +
	  	      		   "  model.alert.alertCategory.category," +
	  	      		   "  ''," );
 	         }
	  	   
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     sb.append(" from AlertAssignedOfficerNew model  ");
	  	     sb.append(" left join model.alert.edition EDS " +
	  	    		   " left join model.alert.tvNewsChannel TNC" +
	  	    		   " left join model.alert.socialMediaType SMT" +
	  	    		   " left join model.alert.alertCallCenterType ACCT" +
	  	    		   " ,GovtUserAddress UA ");
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId " +
	  	     		  " and model.alert.isDeleted='N' and model.isDeleted = 'N' " +
	  	     		  " and model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	  	     
	  	    if(deptIds != null && deptIds.size() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) ");
	  	    }
            if(deptScopeIds != null && deptScopeIds.size() > 0){
            	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId in(:deptScopeIds) ");
            }
	  	   if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
     	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or(SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList))  or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
     	    }
	  	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	  		   sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	  	    }
	  	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	  		   sb.append(" and model.alert.alertStatusId in (:alertStatusIds)");
	  	    }
	  	  
	     	   StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	  	      
	  	      if(querySb.length() > 0){
	  	    	 sb.append(querySb); 
	  	      }
	  	     
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	       
	  	     if(levelId != null && levelId > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:levelId "); 
	  	     }
	  	     sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId" );
	  	     if(type != null && type.equalsIgnoreCase("ScopeLevel")){
	  	    	  sb.append(",model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId " +
	  	    	  		    " order by model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId asc");
	  	     }else if(type != null && type.equalsIgnoreCase("AlertCategory")){
	  	 	  sb.append(",model.alert.alertCategory.alertCategoryId order by model.alert.alertCategory.order asc");
	         }
	  	   
	  	     Query query = getSession().createQuery(sb.toString());
	  	    
	  	    if(fromDate != null && toDate != null){
	  	        query.setDate("fromDate", fromDate);
	  	        query.setDate("toDate", toDate);
	  	    }
	  	    if(deptIds != null && deptIds.size() > 0){
	  	    	query.setParameterList("deptIds", deptIds);
	  	    }
	  	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
    	      query.setParameterList("printIdList", printIdsList);
    	      query.setParameterList("electronicIdList", electronicIdsList);
    	      query.setParameterList("calCntrIdList", calCntrIdList);
    	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
    	 	  query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
    	    }
		  	if(levelId != null && levelValues != null && !levelValues.isEmpty()){
	  			query.setParameterList("levelValues",levelValues);
	  		}
		  	if(querySb.length() > 0){
		  		query.setParameter("levelId",levelId);  
		  	}
		  	 if(deptScopeIds != null && deptScopeIds.size() > 0){
		  		query.setParameterList("deptScopeIds",deptScopeIds);
	         }
	  	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	  	    	query.setParameterList("alertSeverityIds",alertSeverityIds);
	  	    }
	  	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	  	    	query.setParameterList("alertStatusIds",alertStatusIds);
	  	    }
	  	    return query.list();
	  	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long levelId,List<Long> deptIds,
			Long filterLevelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues,
			List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	  		StringBuilder sb = new StringBuilder();  
	  		
	  	     sb.append("select ");
	  	    
	  	     sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	      		      "  model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " );
	  	    
	  	     sb.append(" model.alertStatus.alertStatusId," +
	  	      		    " model.alertStatus.alertStatus," +
	  	      		    " model.alertStatus.color, " );
	  	     
	  	     sb.append(" count(distinct model.alert.alertId) ");
	  	     
	  	     sb.append(" from AlertAssignedOfficerNew model" +
	  	     		   " left join model.alert.edition EDS " +
    	               " left join model.alert.tvNewsChannel TNC" +
    	               " left join model.alert.socialMediaType SMT" +
    	               " left join model.alert.alertCallCenterType ACCT" +
    	               " ,GovtUserAddress UA  ");
	  	     sb.append(" where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId " +
	  	     		   " and model.alert.isDeleted='N' and model.isDeleted = 'N' ");
	  	     
	  	     if(levelId != null && levelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >=:levelId ");
	  	     }
	  	     if(deptIds != null && deptIds.size() >0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds) " );
	  	     }
	  	    if(fromDate != null && toDate != null)
	  	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  	    if(filterLevelId != null && filterLevelId.longValue() > 0){
	  	    	sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId =:filterLevelId ");
	  	     }
	  	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList) ) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
      	    }
	  	    
	  	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	  	      sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");	
	  	    }
	  	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	  	      sb.append(" and model.alert.alertStatusId in (:alertStatusIds)");	
	  	    }
	  	  
		  	  StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
		      
		      if(querySb.length() > 0){
		    	 sb.append(querySb); 
		      }
	  	    
	  	  
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
	  	  if(deptIds != null && deptIds.size() >0){
	  		  query.setParameterList("deptIds",deptIds);
	  	  }
	  	  if(filterLevelId != null && filterLevelId.longValue() > 0){
	  		query.setParameter("filterLevelId",filterLevelId);
  	      }
	  	  if(querySb.length() > 0){
  			query.setParameterList("levelValues",levelValues);
  		  }
	 	 if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
  	      query.setParameterList("printIdList", printIdsList);
  	      query.setParameterList("electronicIdList", electronicIdsList);
  	      query.setParameterList("calCntrIdList", calCntrIdList);
  	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
  	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
  	    }
 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 	    	query.setParameterList("alertSeverityIds", alertSeverityIds);
  	    }
  	    if(alertStatusIds != null && alertStatusIds.size() > 0){
  	    	query.setParameterList("alertStatusIds", alertStatusIds);
  	    }
	  	  return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,List<Long> statusIds,
			Date fromDate,Date toDate,Long desigDeptOfficerId,Long officerId,Long levelId,List<Long> levelValues,
			List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId,
			List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.alert.alertId "+
				  " from " +
				  " AlertAssignedOfficerNew model " +
				  " left join model.alert.edition EDS " +
    	          " left join model.alert.tvNewsChannel TNC" +
    	          " left join model.alert.socialMediaType SMT" +
    	          " left join model.alert.alertCallCenterType ACCT" +
    	          " ,GovtUserAddress UA  " +
				  " where model.govtDepartmentDesignationOfficer.govtUserAddress.userAddressId=UA.userAddressId and " +
				  " model.isDeleted = 'N' " +
				  " and model.alert.isDeleted = 'N'");
		if(deptId != null && deptId.longValue() >0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() > 0){
			sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :locationLevelId ");
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model.alertStatus.alertStatusId in (:statusIds) ");
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
		if(alertCategoryId != null && alertCategoryId.longValue() > 0){
			sb.append(" and  model.alert.alertCategoryId=:alertCategoryId");
		}
		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
		   sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");	
		}
			
		 StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
	      if(querySb.length() > 0){
	    	 sb.append(querySb); 
	      }
		
		if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() >0 ){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or(SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
    	}
		//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId ");
		Query query = getSession().createQuery(sb.toString());
		if(deptId != null && deptId.longValue() >0)
		      query.setParameter("deptId",deptId);
		if(locationLevelId != null && locationLevelId.longValue() > 0)
		      query.setParameter("locationLevelId",locationLevelId);
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds",statusIds);
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
		 if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() >0 ){
	   	      query.setParameterList("printIdList", printIdsList);
	   	      query.setParameterList("electronicIdList", electronicIdsList);
	   	      query.setParameterList("calCntrIdList", calCntrIdList);
	   	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	   	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 		      query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 		      query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 		      query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	   	  }
		  if(querySb.length() > 0){
	  			query.setParameterList("levelValues",levelValues);
	  	  } 
		  if(alertCategoryId != null && alertCategoryId.longValue() > 0){
			  query.setParameter("alertCategoryId",alertCategoryId);
		  }
		  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
			  query.setParameterList("alertSeverityIds",alertSeverityIds);	
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
        
        public List<Long> getDistrictOfficerAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date fromDate,Date toDate,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
        	  
        	  StringBuilder sb = new StringBuilder();
	    	  
        	  sb.append(" select  distinct model.alert.alertId from AlertAssignedOfficerNew model ");
        	  
        	  sb.append(" left join model.alert.edition EDS " +
        	           "  left join model.alert.tvNewsChannel TNC" +
        	           "  left join model.alert.socialMediaType SMT" +
        	           "  left join model.alert.alertCallCenterType ACCT");
        	
        	  sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
        	
        	  
        	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
                  sb.append(" and (EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) or(SMT.socialMediaTypeId in(:socialMediaTypeIds)) or(ACCT.alertCallCenterTypeId in(:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
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
	    	  
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  sb.append(" and model.alert.alertSeverityId in(:alertSeverityIds) " );
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  sb.append(" and model.alert.alertStatusId in(:alertStatusIds) " );
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
	    	  
	    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	    	      query.setParameterList("printIdsList", printIdsList);
	    	      query.setParameterList("electronicIdsList", electronicIdsList);
	    	      query.setParameterList("calCntrIdList", calCntrIdList);
	    	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	    	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	    	     }  
		    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
		    		  query.setParameterList("alertSeverityIds", alertSeverityIds);
		    	  }
		    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
		    		  query.setParameterList("alertStatusIds", alertStatusIds);
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
    public List<Object[]> getDistrictOfficerMyAlertsCountView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypes,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
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
          			  " left join model.alert.tvNewsChannel TNC" +
          			  " left join model.alert.socialMediaType SMT" +
          			  " left join model.alert.alertCallCenterType ACCT ");
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
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypes != null && !socialMediaTypes.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypes)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds))  )");
	      	  }
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  sb.append(" and model.alert.alertStatusId in (:alertStatusIds)");
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
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypes != null && !socialMediaTypes.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	      query.setParameterList("calCntrIdList", calCntrIdList);
	    	      query.setParameterList("socialMediaTypes", socialMediaTypes);
	    	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	    	   }  
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  query.setParameterList("alertSeverityIds", alertSeverityIds);
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  query.setParameterList("alertStatusIds", alertStatusIds);
	    	  }
	    	  return query.list();
        }  
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusids,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
        	StringBuilder sb = new StringBuilder();
        	sb.append(" select model.alertStatus.alertStatusId," +
        			"  model.alertStatus.alertStatus," +
        			"  model.alertStatus.color," +
        			"  count(distinct model.alert.alertId) ");
        	
        	sb.append(" from AlertAssignedOfficerNew model ");
         	sb.append(" left join model.alert.edition EDS " +
        			  " left join model.alert.tvNewsChannel TNC" +
        			  " left join model.alert.socialMediaType SMT" +
        			  " left join model.alert.alertCallCenterType ACCT ");
   
        	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted = 'N' " );
        		            	
        		    	    	  
        	  if(govtOffcrIds != null && govtOffcrIds.size() > 0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffcrIds) " );
	    	  }
	    	  if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
	    	  }
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	      	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
	      	  }
	    	  if(startDate != null && endDate != null){
	    		  sb.append(" and date(model.insertedTime) between :startDate and :endDate ");
	    	  }
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	    	  }
	    	  if(alertStatusids != null && alertStatusids.size() > 0){
	    		  sb.append(" and model.alertStatus.alertStatusId in (:alertStatusids)");
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
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	    	      query.setParameterList("printIdList", printIdsList);
	    	      query.setParameterList("electronicIdList", electronicIdsList);
	    	      query.setParameterList("calCntrIdList", calCntrIdList);
	    	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	    	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	    	   } 
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  query.setParameterList("alertSeverityIds", alertSeverityIds);
	    	  }
	    	  if(alertStatusids != null && alertStatusids.size() > 0){
	    		  query.setParameterList("alertStatusids", alertStatusids);
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
	 public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsView(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
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
 	          " left join model.alert.tvNewsChannel TNC" +
 	          " left join model.alert.socialMediaType SMT " +
 	          " left join model.alert.alertCallCenterType ACCT" +
 	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA");
 	    sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
 	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
 	    		  " model.alert.alertCategory.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
 	    
 	    if(departmentIds != null && !departmentIds.isEmpty())
 	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
 	    
 	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 	    	sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:callCenterIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
         }
 	    if(fromDate != null && toDate != null)
 	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
 	    
 	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
	      if(querySb.length() > 0){
	    	 sb.append(querySb); 
	      }
 	    
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	sb.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
 	    }
 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 	    	sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
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
 	   if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 	      query.setParameterList("printIdList", printIdsList);
 	      query.setParameterList("electronicIdList", electronicIdsList);
 	      query.setParameterList("callCenterIds", callCenterIds);
 	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
 	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
 	    }  
 	  
 	    if(fromDate != null && toDate != null){
 	        query.setDate("fromDate", fromDate);
 	        query.setDate("toDate", toDate);
 	    }
 	    if(querySb.length() > 0){
 	        query.setParameterList("levelValues", levelValues);
 	     }
 	    
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	 query.setParameterList("alertStatusIds", alertStatusIds);
 	    }
 	    if(departmentScopeIds != null && departmentScopeIds.size() > 0){
 	      query.setParameterList("departmentScopeIds", departmentScopeIds);
 	    }
 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 	    	query.setParameterList("alertSeverityIds", alertSeverityIds);
	    }
 	      return query.list();
 	}
	 
	 public List<Long> getDistrictOffrAlertsIds(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,Date fromDate,Date toDate,List<Long> alertStatusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	    	StringBuilder sb = new StringBuilder();
	    	  
	    	  sb.append(" select distinct model.alert.alertId from AlertAssignedOfficerNew model ");
	    	
	    	  sb.append(" left join model.alert.edition EDS " +
	    	           " left join model.alert.tvNewsChannel TNC" +
	    	           " left join model.alert.socialMediaType SMT" +
	    	           " left join model.alert.alertCallCenterType ACCT ");
	    	  
	    	  sb.append(" where model.isDeleted = 'N' and  model.alert.isDeleted='N' " );
	    	
	    	  if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	              sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds))  )");
		       }
	    	  
	    	  if(govtOffceIds != null && govtOffceIds.size() >0){
	    		  sb.append(" and model.govtOfficer.govtOfficerId in(:govtOffceIds) " );
	    	  }
	    	  if(govtDeptDesigOffceIds != null && govtDeptDesigOffceIds.size() >0){
	    		  sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDeptDesigOffceIds) " );
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  sb.append(" and  model.alertStatus.alertStatusId in(:alertStatusIds)");
	  		  }
	    	  if(fromDate != null && toDate != null){
	    		  sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  		  }
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		 sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	    	  }
	    	  Query query = getSession().createQuery(sb.toString());
	    	  
	    	  if(govtDeptDesigOffceIds != null && govtDeptDesigOffceIds.size() >0){
	    		  query.setParameterList("govtDeptDesigOffceIds", govtDeptDesigOffceIds);  
	    	  }
	    	  if(govtOffceIds != null && govtOffceIds.size() >0){
	    		  query.setParameterList("govtOffceIds", govtOffceIds);  
	    	  }
	    	  if(alertStatusIds != null && alertStatusIds.size() > 0){
	    		  query.setParameterList("alertStatusIds", alertStatusIds);
	  		  }
	    	  if(fromDate != null && toDate != null){
	  			query.setDate("fromDate", fromDate);
	  			query.setDate("toDate", toDate);
	  		}
	    	  
    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
    	      query.setParameterList("printIdsList", printIdsList);
    	      query.setParameterList("electronicIdsList", electronicIdsList);
    	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
    	      query.setParameterList("calCntrIdList", calCntrIdList);
    	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
    	    }  
    	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		 query.setParameterList("alertSeverityIds", alertSeverityIds);
	    	}
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
			 String type,List<Long> deptIds,List<Long> statusIds,Date fromDate,Date endDate,List<Long> printIdsList,
			 List<Long> electronicIdsList,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
     	 StringBuilder sb = new StringBuilder();
	    	  
     	  sb.append(" select  distinct model.alert.alertId from AlertAssignedOfficerNew model ");
     	  sb.append(" left join model.alert.edition EDS " +
     			  	" left join model.alert.tvNewsChannel TNC" +
     			  	" left join model.alert.socialMediaType SMT " +
     			  	" left join model.alert.alertCallCenterType ACCT ");
     	
     	  sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );
     	
     	  if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
   	    	sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:callCenterIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
           }
     	  
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
	    	  if(statusIds != null && statusIds.size() > 0){
	    		  sb.append(" and  model.alertStatus.alertStatusId in (:statusIds) " );
	    	  }
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds) ");
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
	    	  if(statusIds != null && statusIds.size() > 0){
	    		  query.setParameterList("statusIds", statusIds);
	    	  }
	    	  if(deptIds != null && deptIds.size() > 0){
	    		  query.setParameterList("deptIds", deptIds);
	    	  }
	    	  if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && callCenterIds !=null && !callCenterIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	     	      query.setParameterList("printIdList", printIdsList);
	     	      query.setParameterList("electronicIdList", electronicIdsList);
	     	      query.setParameterList("callCenterIds", callCenterIds);
	     	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	     	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	     	  }  
	    	  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	    		  query.setParameterList("alertSeverityIds", alertSeverityIds);
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
		 /*Query query = getSession().createQuery(" select  model.alertAssignedOfficerId from  AlertAssignedOfficerNew model where model.alertId = :alertId and " +
		 		" model.isDeleted='N'  ");*/
		 
		 Query query = getSession().createSQLQuery(" SELECT distinct  model.alert_assigned_officer_id as alert_assigned_officer_id  from alert_assigned_officer_new model " +
		 		" where model.alert_id = :alertId  and model.is_deleted='N'  ")
		 		.addScalar("alert_assigned_officer_id", Hibernate.LONG);
		 query.setParameter("alertId", alertId);
		 return query.list();
	 }
	 @SuppressWarnings("unchecked")
		public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,List<Long> statusIds,Date fromDate,Date toDate,Long levelId,List<Long> levelValues,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
			 StringBuilder sb = new StringBuilder();
			 sb.append(" select distinct model.alert.alertId "+
					  " from " +
					  " AlertAssignedOfficerNew model " +
					  " left join model.alert.edition EDS " +
					  " left join model.alert.tvNewsChannel TNC" +
					  " left join model.alert.socialMediaType SMT" +
					  " left join model.alert.alertCallCenterType ACCT " +
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
				if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
		             sb.append(" and (EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
		        }
			
				if(statusIds != null && statusIds.size() >0){
					sb.append(" and model.alertStatus.alertStatusId in (:statusIds) ");
				}
				if(fromDate != null && toDate != null){
					sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
				}
				
				if(alertSeverityIds != null && alertSeverityIds.size() > 0){
				   sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
				}
				
				StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	    	      
	    	      if(querySb.length() > 0){
	    	    	 sb.append(querySb); 
	    	      }
			 
			//sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId >= :scopeId ");
			 Query query = getSession().createQuery(sb.toString());
			
			if(deptIds != null && deptIds.size() >0){
				query.setParameterList("deptIds",deptIds);
			 }
			      
			 if(locationLevelId != null && locationLevelId.longValue() > 0){
				 query.setParameter("locationLevelId",locationLevelId);
			 }
			     
			 if(statusIds != null && statusIds.size() >0){
				query.setParameterList("statusIds",statusIds);
			  }
			 if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
				query.setParameterList("printIdList",printIdList);
				query.setParameterList("electronicIdList",electronicIdList);
				query.setParameterList("calCntrIdList",calCntrIdList);
				query.setParameterList("socialMediaTypeIds",socialMediaTypeIds);
				query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
			 }
			 if(fromDate != null && toDate != null){
				query.setDate("fromDate",fromDate);
				query.setDate("toDate",toDate);
			 }
			  if(querySb.length() > 0){
				query.setParameterList("levelValues",levelValues);
			  }
			  if(alertSeverityIds != null && alertSeverityIds.size() > 0){
				  query.setParameterList("alertSeverityIds",alertSeverityIds);
				}
			//query.setParameter("scopeId",scopeId);
			
			return query.list();
		}
	 
	 public List<Object[]> getAlertAssignedLevelDetails(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.govtDepartmentDesignationOfficer.govtDepartmentScopeId, model.govtDepartmentDesignationOfficer.levelValue " +
		 				 " from AlertAssignedOfficerNew model " +
		 				 " where model.isDeleted = 'N' " +
		 				// " and model.isApproved = 'Y' " +
		 				 " and model.alert.alertId = :alertId " +
		 				 " and model.alert.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());  
		 query.setParameter("alertId", alertId);
		 return  query.list();
	 }
	 
	 //Santosh
     public List<Object[]> getLocationBasedOnDepartmentLevelId(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long childLevelId){
     	StringBuilder queryStr = new StringBuilder();
     	queryStr.append(" select ");
     	queryStr.append(" distinct GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWL1.location_name as locationName ");//2
     	queryStr.append(" from ");
 		
 		queryStr.append(" alert A ");
 		
 		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
 		queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
 		queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");
 		
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
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
		}
 			
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
 		}
 		 //Child Location
		   if(childLevelId != null && childLevelId.longValue() == 2L){
			queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.zone_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 3L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.region_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 4L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.circle_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 5L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.district_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 6L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.division_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 7L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.sub_division_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 8L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.tehsil_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 9L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.local_election_body ");
			}else if(childLevelId != null && childLevelId.longValue() == 10L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.panchayat_id");
			}else if(childLevelId != null && childLevelId.longValue() == 11L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.ward ");
			}else if(childLevelId != null && childLevelId.longValue() == 12L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.gmc_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 13L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.cluster_id ");
			}
		    
		    if(childLevelId != null && childLevelId.longValue() > 0){
		    	queryStr.append(" and GDWLC.govt_department_scope_id = :childLevelId ");
		    }
		    
 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
 		}
 		
 		if(fromDate != null && toDate != null){
 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or (ACCT.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )  ");
 		}
 		
 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			queryStr.append(" and A.alert_severity_id in (:alertSeverityIds)");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and A.alert_status_id in (:alertStatusIds)");
 		}
 		
 		 StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
 		 if(querySb.length() > 0){
	    	  queryStr.append(querySb); 
	      }
 		
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 	
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
 			query.setParameterList("calCntrIds", calCntrIds);
 			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
 		}
 		if(querySb.length() > 0){
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
 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			query.setParameterList("alertSeverityIds",alertSeverityIds);
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			query.setParameterList("alertStatusIds",alertStatusIds);
 		}
 		if(childLevelId != null && childLevelId.longValue() > 0L){
 			query.setParameter("childLevelId",childLevelId);
		}
 		return query.list();
     }
     public List<Object[]> getChildLocationBasedOnParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
     		Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,
     		Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,
     		List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long resultLevelDeptScopeId){
    	 
     	StringBuilder queryStr = new StringBuilder();    
     	queryStr.append(" select distinct ");  
     	queryStr.append(" GDWLC.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWLC.location_name as locationName ");//2
     
    	queryStr.append(" from ");
 		queryStr.append(" alert A ");
 		
 		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
 		queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");
 		
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
 		if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() > 0){
 			queryStr.append(" ,govt_department_work_location GDWL2 ");
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
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
		}
	   
 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_scope_id=:parentGovtDepartmentScopeId   ");
 		}
 		if(parentGovtDepartmentScopeValue != null && parentGovtDepartmentScopeValue.longValue() > 0L){
 			queryStr.append(" and GDWL1.govt_department_work_location_id=:parentGovtDepartmentScopeValue   ");
 		}
 		
 		if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 2L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.zone_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 3L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.region_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 4L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.circle_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 5L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.district_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 6L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.division_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 7L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.sub_division_id ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 8L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.tehsil_id  ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 9L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.local_election_body  ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 10L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.panchayat_id  ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 11L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.ward  ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 12L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.gmc_id  ");
		}else if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() == 13L){
			queryStr.append(" and GDWL2.govt_department_work_location_id = GUA.cluster_id  ");
		}
 		
 		if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() > 0L){
 			queryStr.append(" and GDWL2.govt_department_scope_id=:resultLevelDeptScopeId ");
 		}
 		
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
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
		}
 		 //Child Location
 		   if(childLevelId != null && childLevelId.longValue() == 2L){
 			queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.zone_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 3L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.region_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 4L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.circle_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 5L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.district_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 6L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.division_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 7L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.sub_division_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 8L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.tehsil_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 9L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.local_election_body ");
			}else if(childLevelId != null && childLevelId.longValue() == 10L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.panchayat_id");
			}else if(childLevelId != null && childLevelId.longValue() == 11L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.ward ");
			}else if(childLevelId != null && childLevelId.longValue() == 12L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.gmc_id ");
			}else if(childLevelId != null && childLevelId.longValue() == 13L){
				queryStr.append(" and GDWLC.govt_department_work_location_id = GUA.cluster_id ");
			}
 		    
 		    if(childLevelId != null && childLevelId.longValue() > 0){
 		    	queryStr.append(" and GDWLC.govt_department_scope_id = :childLevelId ");
 		    }
 		
	 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
	 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
	 		}
 		
 		
	 		if(fromDate != null && toDate != null){
	 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
	 		}
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or (ACCT.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )  ");
	 		}
	 		
	 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	 			queryStr.append(" and A.alert_severity_id in (:alertSeverityIds)");
	 		}
	 		if(alertStatusIds != null && alertStatusIds.size() > 0){
	 			queryStr.append(" and A.alert_status_id in (:alertStatusIds)");
	 		}
	 		
	 		  StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
		      
		      if(querySb.length() > 0){
		    	  queryStr.append(querySb); 
		      }
   	    
 	
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 	
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
 			query.setParameterList("calCntrIds", calCntrIds);
 			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
 		}
 		if(querySb.length() > 0){
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
 		if(childLevelId != null && childLevelId.longValue() > 0L){
 			query.setParameter("childLevelId",childLevelId);
		}
 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			query.setParameterList("alertSeverityIds",alertSeverityIds);
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			query.setParameterList("alertStatusIds",alertStatusIds);
 		}
 		if(resultLevelDeptScopeId != null && resultLevelDeptScopeId.longValue() > 0L){
 			query.setParameter("resultLevelDeptScopeId",resultLevelDeptScopeId);
 		}
 		return query.list();
     }
     
     public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
     		Long source,String pendingType,List<Long> alertSeverityIds,List<Long> alertStatusIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
    	 
     	StringBuilder queryStr = new StringBuilder();
     	queryStr.append(" select ");
     	
     	queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
     	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
     	queryStr.append(" GDWL1.location_name as locationName, ");//2
     	if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
     		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
     			if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
     				queryStr.append(" GDWL.govt_department_scope_id as GDSI, A.alert_category_id as govtDepartmentScopeId, ");//3
     			}else if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3	
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
     			}
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" GDWL.govt_department_scope_id as govtDepartmentScopeId, ");//3
     			}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
     				queryStr.append(" A.alert_category_id as govtDepartmentScopeId, ");//3
     			}
     		}
     	}
     	
     	queryStr.append(" count(distinct AAO.alert_id) as count");
     	
 		queryStr.append(" from ");
 		
 		queryStr.append(" alert A ");
 		
 		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
 		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
 		queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");
 
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
 		if(source != null && source.longValue() > 0){
 			queryStr.append(" and A.alert_category_id =:source   ");
 		}else{
 			if(pendingType != null && pendingType.trim().equalsIgnoreCase("pending")){
 				queryStr.append(" and A.alert_category_id in (4,5)  ");
 			}else{
 				queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
 			}
 			
 		}
 		
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
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
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
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 11L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.ward  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 12L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.gmc_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 13L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.cluster_id  ");
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
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or (ACCT.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )  ");
 		}
 		
 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			queryStr.append(" and A.alert_severity_id in (:alertSeverityIds)");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and A.alert_status_id in (:alertStatusIds)");
 		}
 		
 		  StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
	      if(querySb.length() > 0){
	    	  queryStr.append(querySb); 
	      }
 		
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
     			if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id,GDWL.govt_department_scope_id, A.alert_category_id ");
     			}else if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_status_id ");
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");//3
     			}
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
 					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
     			}else if(searchType != null && searchType.equalsIgnoreCase("scopeWise")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id ");
     			}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id,A.alert_category_id ");
     			}
     		}
 	 	}
 		
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
 				if(searchType != null && searchType.equalsIgnoreCase("alertSource") || searchType.equalsIgnoreCase("statusWise")){
 					query.addScalar("GDSI", Hibernate.LONG);
     			}
 			}
 		}
 		query.addScalar("govtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("count", Hibernate.LONG);
 		if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
 			query.setParameterList("calCntrIds", calCntrIds);
 			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
 		}
 		if(querySb.length() > 0){
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
 		if(source != null && source.longValue() > 0){
 			query.setParameter("source",source);
 		}
 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			query.setParameterList("alertSeverityIds",alertSeverityIds);
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			query.setParameterList("alertStatusIds",alertStatusIds);
 		}
 		return query.list();
     }
     public List<Long> getAlertIdsBasedOnRequiredParameter(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		 List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
      		Long parentGovtDepartmentScopeId,Long locationValue,List<Long> calCntrIds,Long deptLevelId,
      		List<Long> alertStatusIds,Long alertCategoryId,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,
      		List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
      		List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long govtOfficerId){
     	 
      	StringBuilder queryStr = new StringBuilder();
      	queryStr.append(" select ");
      	queryStr.append(" distinct AAO.alert_id as alertId ");
     	queryStr.append(" from alert A ");
  			
     	queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
  		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
 		queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");

  		
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
  		
  		if(alertCategoryId != null && alertCategoryId.longValue() > 0){
  			queryStr.append(" and A.alert_category_id = :alertCategoryId ");
  		}
  		if(deptScopeIds != null && deptScopeIds.size() > 0){
 			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIds)");
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
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
		}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
			queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
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
 		  if(alertStatusIds != null && alertStatusIds.size() > 0){
   			queryStr.append(" and AAO.alert_status_id in(:alertStatusIds) ");
   		  }
  		  if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
  			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
  		  }
  		
  		
  		if(fromDate != null && toDate != null){
  			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
  		}
  		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or (ACCT.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )  ");
 		}
  		
  		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			queryStr.append(" and A.alert_severity_id in (:alertSeverityIds)");
 		}
 	     
  		if(govtOfficerId != null && govtOfficerId.longValue() > 0){
  			queryStr.append(" and AAO.govt_officer_id =:govtOfficerId");
  		}
  		
	  	  StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
	      if(querySb.length() > 0){
	    	  queryStr.append(querySb); 
	      }
  		
  		
  		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
  		query.addScalar("alertId", Hibernate.LONG);
  		if(fromDate != null && toDate != null){
  			query.setDate("fromDate", fromDate);
  			query.setDate("toDate", toDate);
  		}
  		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 			query.setParameterList("printIdList", printIdList);  
 			query.setParameterList("electronicIdList", electronicIdList);
 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
 			query.setParameterList("calCntrIds", calCntrIds);
 			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
 		}
  		if(querySb.length() > 0){
  			query.setParameterList("levelValues",levelValues);
  		}
  	
  		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0L){
  			query.setParameter("parentGovtDepartmentScopeId",parentGovtDepartmentScopeId);
  		}
  		
  		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
  			query.setParameter("govtDepartmentId",govtDepartmentId);
  		}
  		if(alertCategoryId != null && alertCategoryId.longValue() > 0){
  			query.setParameter("alertCategoryId",alertCategoryId);
  		}
  		if(locationValue != null && locationValue.longValue() > 0){
  			query.setParameter("locationValue",locationValue);
  		}
  		if(deptLevelId != null && deptLevelId.longValue() > 0){
  			query.setParameter("deptLevelId",deptLevelId);
  		}
  		if(alertStatusIds != null && alertStatusIds.size() > 0){
  			query.setParameterList("alertStatusIds",alertStatusIds);
    	}
  		if(deptScopeIds != null && deptScopeIds.size() > 0){
 			query.setParameterList("deptScopeIds",deptScopeIds);
 		}
  		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 			query.setParameterList("alertSeverityIds",alertSeverityIds);
 		}
  		if(govtOfficerId != null && govtOfficerId.longValue() > 0){
  			query.setParameter("govtOfficerId",govtOfficerId);
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
	     public List<Object[]> getAllDepartmentHasData(List<Long> deptIds,Long levelId,List<Long> levelValues){
		 		StringBuilder queryStr = new StringBuilder();
		 		queryStr.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		 				"    model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName "
		 				+ "  from AlertAssignedOfficerNew model " +
		 				"    left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
		 				"    where model.isDeleted='N' " +
		 				"    and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in(:deptIds)") ;
		 		    
		 		   StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
		    	      
		    	      if(querySb.length() > 0){
		    	    	  queryStr.append(querySb); 
		    	      }
		 		    
		 		    Query query = getSession().createQuery(queryStr.toString());
		 		   query.setParameterList("deptIds", deptIds);
		 		  if(querySb.length() > 0){
		    	     query.setParameterList("levelValues", levelValues);
		 		  }
		    	   
		 		return query.list();
		 	}
	    
	     public List<Object[]> getSubOrdinateFilterAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
	    			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,
	    			List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> deptIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	        	
	        	StringBuilder sb = new StringBuilder();  
	        	
	        	 sb.append("select ");
	        	
	        	sb.append(" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
	    	    		"model1.designationName,  model.alert.alertId ");
	    	    
	        	sb.append(" ,model.alertStatus.alertStatusId,model.insertedTime,model.updatedTime ");
	    	    	if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	        	      sb.append(" , S.govtDepartmentWorkLocationId,S.locationName,S.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	        	      sb.append(" , Z.govtDepartmentWorkLocationId,Z.locationName,Z.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	        	      sb.append(" , R.govtDepartmentWorkLocationId,R.locationName,R.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	        	      sb.append(" , C.govtDepartmentWorkLocationId,C.locationName,C.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	        	      sb.append(" , D.govtDepartmentWorkLocationId,D.locationName, "+
	        	      		"D.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	        	      sb.append(" , DIV.govtDepartmentWorkLocationId,DIV.locationName, DIV.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName,D.govtDepartmentWorkLocationId,D.locationName  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	        	      sb.append(" , SUBDIV.govtDepartmentWorkLocationId,SUBDIV.locationName , " +
	        	      		"SUBDIV.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName, D.govtDepartmentWorkLocationId" +
	        	      		",D.locationName, DIV.govtDepartmentWorkLocationId,DIV.locationName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	          	      sb.append(", T.govtDepartmentWorkLocationId,T.locationName, " +
	          	      		"T.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName  ," +
	          	      		" D.govtDepartmentWorkLocationId,D.locationName, DIV.govtDepartmentWorkLocationId,DIV.locationName" +
	        	      		", SUBDIV.govtDepartmentWorkLocationId,SUBDIV.locationName ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	          	      sb.append(", LEB.govtDepartmentWorkLocationId,LEB.locationName, " +
	        	      		"LEB.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName , D.govtDepartmentWorkLocationId,D.locationName, DIV.govtDepartmentWorkLocationId,DIV.locationName" +
	        	      		", SUBDIV.govtDepartmentWorkLocationId,SUBDIV.locationName, T.govtDepartmentWorkLocationId,T.locationName  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	          	      sb.append(" , P.govtDepartmentWorkLocationId,P.locationName,P.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName" +
	          	      		", D.govtDepartmentWorkLocationId,D.locationName, DIV.govtDepartmentWorkLocationId,DIV.locationName" +
	        	      		", SUBDIV.govtDepartmentWorkLocationId,SUBDIV.locationName, " +
	        	      		"T.govtDepartmentWorkLocationId,T.locationName, LEB.govtDepartmentWorkLocationId,LEB.locationName ");
	    	    	
	    	    	
	        	
	    	    	
	        	sb.append(" from GovtDepartmentDesignationNew model1,AlertAssignedOfficerNew model " +
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
		  	    		   " left join model.alert.tvNewsChannel TNC" +
		  	    		   " left join model.alert.socialMediaType SMT" +
		  	    		   " left join model.alert.alertCallCenterType ACCT  ");
	    	    sb.append(" where model1.govtDepartmentDesignationId=model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId and   model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	  	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
	    	    
	    	    if(desigIds != null && !desigIds.isEmpty())
	      	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:desigIds)");
	    	    
	    	    if(deptIds != null && !deptIds.isEmpty())
		      	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIds)");
	    	    
	    	    if(childLevelVals != null && !childLevelVals.isEmpty()){
	    	    		if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
		        	      sb.append(" and  S.govtDepartmentWorkLocationId in (:childLevelVals)  ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
		        	      sb.append(" and Z.govtDepartmentWorkLocationId in (:childLevelVals)  ");
		        	    else if(childLevelId != null && childLevelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
		        	      sb.append(" and R.govtDepartmentWorkLocationId in (:childLevelVals)  ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
		        	      sb.append(" and C.govtDepartmentWorkLocationId in (:childLevelVals) ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
		        	      sb.append(" and D.govtDepartmentWorkLocationId in (:childLevelVals) ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
		        	      sb.append(" and DIV.govtDepartmentWorkLocationId in (:childLevelVals)  ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
		        	      sb.append(" and SUBDIV.govtDepartmentWorkLocationId in (:childLevelVals)   ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
		          	      sb.append(" and T.govtDepartmentWorkLocationId in (:childLevelVals) ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
		          	      sb.append(" and LEB.govtDepartmentWorkLocationId in (:childLevelVals)  ");
		        	    else if(childLevelId != null && childLevelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
		          	      sb.append(" and P.govtDepartmentWorkLocationId in (:childLevelVals)   ");
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
	    	    
	    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	       	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
	       	   /*  if( calCntrIdList !=null && !calCntrIdList.isEmpty() && calCntrIdList.get(0) != 0){
	   	    	    	  sb.append(" or model.alert.alertCallerId is not null ");
	   	  			}else{
	   	  				sb.append(" and model.alert.alertCallerId is null ");
	   	  			}
	   	    	      sb.append(" )");*/
	       	    }
	    	   
	    	    if(priorityId != null && priorityId.longValue() >0l)
	    	    	sb.append("  and model.alert.alertSeverityId = :priorityId " );
	    	    
	    	    if(fromDate != null && endDate != null){
	    	    	sb.append("  and date(model.insertedTime) between :fromDate and :endDate "); 
	    	    }
	    	   
	    	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	    	      
	    	      if(querySb.length() > 0){
	    	    	 sb.append(querySb); 
	    	      }
	    	    
	    	    if(statusIds != null && statusIds.size() > 0){
	    	    	sb.append("  and model.alertStatus.alertStatusId in (:statusIds) ");
	    	    }
	    	    
	    	    /*sb.append(" group by ");
	    	    	if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	        	      sb.append("   S.govtDepartmentWorkLocationId  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	        	      sb.append("  Z.govtDepartmentWorkLocationId   ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	        	      sb.append("  R.govtDepartmentWorkLocationId   ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	        	      sb.append("  C.govtDepartmentWorkLocationId  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	        	      sb.append("  D.govtDepartmentWorkLocationId  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	        	      sb.append("  DIV.govtDepartmentWorkLocationId   ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	        	      sb.append("  SUBDIV.govtDepartmentWorkLocationId    ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	          	      sb.append("  T.govtDepartmentWorkLocationId  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	          	      sb.append("  LEB.govtDepartmentWorkLocationId   ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	          	      sb.append("  P.govtDepartmentWorkLocationId    ");*/
	    	    
	    	    Query query = getSession().createQuery(sb.toString());
	    	    
	    	    if(govtScopeIds != null && !govtScopeIds.isEmpty())
	    	      query.setParameterList("govtScopeIds", govtScopeIds);
	    	    
	    	    if(desigIds != null && !desigIds.isEmpty())
	    	    	 query.setParameterList("desigIds", desigIds);
	    	    
	    	    if(childLevelVals != null && !childLevelVals.isEmpty())
	    	    	query.setParameterList("childLevelVals", childLevelVals);
	    	    
	    	    if(fromDate != null && endDate != null){
	    	    	query.setDate("fromDate", fromDate);
	    	    	query.setDate("endDate", endDate);
	    	    }
	    	     if(querySb.length() > 0){
	    	    	query.setParameterList("levelValues", levelValues);
	    	     }
	    	    
	    	    if(priorityId != null && priorityId.longValue() >0l)
	    	    	query.setParameter("priorityId", priorityId);
	    	    
	    	    if(statusIds != null && statusIds.size() > 0){
	    	    	query.setParameterList("statusIds", statusIds);
	    	    }
	    	    if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
	      	      query.setParameterList("printIdList", printIdsList);
	      	      query.setParameterList("electronicIdList", electronicIdsList);
	      	      query.setParameterList("calCntrIdList", calCntrIdList);
	      	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	      	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	      	    }
	    	    if(deptIds != null && !deptIds.isEmpty())
	    	    	query.setParameterList("deptIds", deptIds);
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
	    	
	    	 public List<Long> getAlertCntByAlertCategory(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,List<Long> calCntrIds,Long alertCategoryId,List<Long> alertStatusIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	     		StringBuilder sb = new StringBuilder();  
	     	     sb.append("select ");
	     	     sb.append(" distinct model.alert.alertId ");
	     	     sb.append(" from AlertAssignedOfficerNew model" +
	     	          " left join model.alert.edition EDS " +
	     	          " left join model.alert.tvNewsChannel TNC " +
	     	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
	     	          " left join model.alert.alertCategory AC " +
	     	          " left join model.alert.socialMediaType SMT " +
	     	          " left join model.alert.alertCallCenterType ACCT ");
	        sb.append(" where model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	     	    	  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	     	    	  " AC.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	     	    
	     	    if(departmentIds != null && !departmentIds.isEmpty())
	     	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	     	    
	     	   if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	    	  	      sb.append(" and (EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) ) ");
	    		} 
	     	    if(alertCategoryId != null && alertCategoryId.longValue() > 0){
	     	       sb.append(" and AC.alertCategoryId =:alertCategoryId");
	     	    }
	     	    if(fromDate != null && toDate != null)
	     	      sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
	     	     
	     	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	     	    	sb.append(" and model.alertStatusId in (:alertStatusIds)");
	     	    }
	     	    
	     	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	     	    	sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	     	    }
	     	    
	     	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based On User Access Level
	    	      
	    	      if(querySb.length() > 0){
	    	    	 sb.append(querySb); 
	    	      }
	     	    
	     	    Query query = getSession().createQuery(sb.toString());
	     	    
	     	    if(departmentIds != null && !departmentIds.isEmpty()){
	     	    	query.setParameterList("departmentIds", departmentIds);
	     	    }
	     	      
	     	   if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	    	       query.setParameterList("printIdList", printIdsList);
	       	       query.setParameterList("electronicIdList", electronicIdsList);
	       	       query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	       	       query.setParameterList("calCntrIds", calCntrIds);
		       	   query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	       	     }  
	     	    if(fromDate != null && toDate != null){
	     	        query.setDate("fromDate", fromDate);
	     	        query.setDate("toDate", toDate);
	     	    }
	     	    if(querySb.length()>0){
	    	    	query.setParameterList("levelValues", levelValues);
	    	    }
	     	        
	     	    if(alertCategoryId != null && alertCategoryId.longValue() > 0){
	     	      query.setParameter("alertCategoryId", alertCategoryId);
	     	    }
	     	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	     	    	query.setParameterList("alertStatusIds", alertStatusIds);
	     	    }
		     	if(alertSeverityIds != null && alertSeverityIds.size() > 0){
		     		query.setParameterList("alertSeverityIds", alertSeverityIds);
		     	  }
	     	      return query.list();
	     	}
	    	
	  public List<Long> getTotalAlertByAllAlertStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, List<Long> statusIdList,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,
			  List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	    		StringBuilder sb = new StringBuilder();  
	    	    sb.append("select distinct model.alert.alertId ");
	    	    sb.append(" from AlertAssignedOfficerNew model" +
	    	          " left join model.alert.edition EDS " +
	    	          " left join model.alert.tvNewsChannel TNC " +
	    	          " left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
	    	          " left join model.alert.alertCategory AC " +
	    	    	  " left join model.alert.socialMediaType SMT " +
	    	    	  " left join model.alert.alertCallCenterType ACCT "+
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
	    	    	  "  and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") " +
	    	          "   and AC.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	    	    if(govtDepartmentScopeId != null && govtDepartmentScopeId.longValue() > 0L){
	    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentScope.govtDepartmentScopeId = :govtDepartmentScopeId ");
	    	    }
	    	    if(statusIdList != null && statusIdList.size() > 0L){
	    	    	sb.append(" and  model.alertStatusId in (:statusIdList) ");
	    	    }
	    	    if(deptId != null && deptId.longValue() > 0L){
	    	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId =:deptId ");
	    	    }else{
	    	    	if(departmentIds != null && departmentIds.size()>0){
	        	    	sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	        	    }
	    	    }
	    	     
	      
	    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	    	    	sb.append(" and ( EDS.newsPaperId in (:printIdsList)  or (TNC.tvNewsChannelId in (:electronicIdsList)) or(SMT.socialMediaTypeId in(:socialMediaTypeIds)) or(ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds))  ) ");
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
	    		   sb.append(" and AC.alertCategoryId in (:alertSourceIdList) ");
	    	   }
	    	   if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
	    		   sb.append(" and ( model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) or ( model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList))  or (SMT.socialMediaTypeId in(:filterSocialMediaIds)) or (model.alert.alertCallCenterTypeId in(:filterCallCenterIdList)))");
	    	   }else if(printMediaIdList != null && printMediaIdList.size()>0){
	    		   sb.append(" and model.alert.edition.newsPaper.newsPaperId in (:printMediaIdList) ");
	    	   }else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
	    		   sb.append(" and model.alert.tvNewsChannel.tvNewsChannelId in (:electronicMediaIdList) ");
	    	   }else if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
	    		   sb.append(" and SMT.socialMediaTypeId in (:filterSocialMediaIds) ");
	    	   }else if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
	    		   sb.append(" and model.alert.alertCallCenterTypeId in(:filterCallCenterIdList) ");
	    		   
	    	   }
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
	    			}else if(scopeId != null && scopeId.longValue() == 4L ){
	    				sb.append(" and UA.parliamentConstituencyId in (:locationIdList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 8L ){
	    				sb.append(" and UA.localElectionBody in (:locationIdList) ");
	    			}else if(scopeId != null && scopeId.longValue() == 7L ){
	    				sb.append(" and UA.hamletId in (:locationIdList) ");
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
	    	    
	    	    
	    	      
	    	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	    	      query.setParameterList("printIdsList", printIdsList);
	    	      query.setParameterList("electronicIdsList", electronicIdsList);
	    	      query.setParameterList("calCntrIds", calCntrIds);
	  			  query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
		  		  query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
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
	     	   if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
	     		  query.setParameterList("filterSocialMediaIds", filterSocialMediaIds);
	     	   }
	     	  if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
	     		 query.setParameterList("filterCallCenterIdList", filterCallCenterIdList);
	     	  }
	     	   if(locationList != null && locationList.size()>0){
	  			query.setParameterList("locationList", locationList);
	  		   }
	    	    return query.list();
	    	}
	 
	public List<Object[]> getBellowLvlDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " +
				  " model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, " +
				  " model.alertStatus.alertStatusId, " +
				  " model.alertStatus.alertStatus, " +
				  " model.alertStatus.color, " +
				  " count(distinct model.alert.alertId) " +
				  " from " +
				  " AlertAssignedOfficerNew model ");
      
      	sb.append(" left join model.alert.edition EDS " +
      			  " left join model.alert.tvNewsChannel TNC" +
      			  " left join model.alert.socialMediaType SMT" +
      			  " left join model.alert.alertCallCenterType ACCT ");
      	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );//and model.isApproved = 'Y'
      	
      	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      		sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
      	}
	      	
      	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
      		sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId in(:govtDepDesigOffcrIds) " );
      	}
	  
      	if(fromDate != null && toDate != null){
      		sb.append(" and date(model.insertedTime) between :fromDate and :toDate " );
      	}	
      	if(alertSeverityIds != null && alertSeverityIds.size() > 0){
      		sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
      	}
    	if(alertStatusIds != null && alertStatusIds.size() > 0){
      		sb.append(" and model.alert.alertStatusId in (:alertStatusIds)");
      	}
      	
      	sb.append(" group by model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, model.alertStatus.alertStatusId ");
      	Query query = getSession().createQuery(sb.toString());
	  
      	if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0){
      		query.setParameterList("govtDepDesigOffcrIds", govtDepDesigOffcrIds);  
      	}
	  
      	if(fromDate != null && toDate != null){
      		query.setDate("fromDate", fromDate);
      		query.setDate("toDate", toDate);
      	}
      	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      		query.setParameterList("printIdList", printIdsList);
      		query.setParameterList("electronicIdList", electronicIdsList);
      		query.setParameterList("calCntrIdList", calCntrIdList);
      		query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
      	    query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 		    query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 		    query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 		    query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
      	} 
    	if(alertSeverityIds != null && alertSeverityIds.size() > 0){
    		query.setParameterList("alertSeverityIds", alertSeverityIds);
      	}
    	if(alertStatusIds != null && alertStatusIds.size() > 0){
    		query.setParameterList("alertStatusIds", alertStatusIds);
      	}
      	return query.list();
	}
	public List<Long> getBellowDistrictOfficerAlertsDtls(Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long statusId,Long govtDepDesigOffcrId,Long officerId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.alert.alertId " +
				  " from " +
				  " AlertAssignedOfficerNew model ");
      
      	sb.append(" left join model.alert.edition EDS " +
      			  " left join model.alert.tvNewsChannel TNC" +
      			  " left join model.alert.socialMediaType SMT " +
      			  " left join model.alert.alertCallCenterType ACCT ");
      	sb.append(" where model.isDeleted = 'N' and model.alert.isDeleted='N' " );//and model.isApproved = 'Y'
      	
      	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      		sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
      	}
          	
      	if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0L){
      		sb.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId =:govtDepDesigOffcrId " );
      	}
      	if(officerId != null && officerId.longValue() > 0L){
      		sb.append(" and model.govtOfficer.govtOfficerId =:officerId " );
      	}
	  
      	if(fromDate != null && toDate != null){
      		sb.append(" and date(model.insertedTime) between :fromDate and :toDate " );
      	}	  
      	if(statusId != null && statusId.longValue() > 0L){
      		sb.append(" and model.alertStatus.alertStatusId =:statusId " );
      	}
      	if(alertSeverityIds != null && alertSeverityIds.size() > 0){
      		sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
      	}
      	Query query = getSession().createQuery(sb.toString());
	  
      	if(govtDepDesigOffcrId != null && govtDepDesigOffcrId.longValue() > 0L){
      		query.setParameter("govtDepDesigOffcrId", govtDepDesigOffcrId);  
      	}
      	if(officerId != null && officerId.longValue() > 0L){
      		query.setParameter("officerId", officerId);
      	}
      	if(statusId != null && statusId.longValue() > 0L){
      		query.setParameter("statusId", statusId);
      	}
      	if(fromDate != null && toDate != null){
      		query.setDate("fromDate", fromDate);
      		query.setDate("toDate", toDate);
      	}
      	if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
      		query.setParameterList("printIdList", printIdsList);
      		query.setParameterList("electronicIdList", electronicIdsList);
      		query.setParameterList("calCntrIdList", calCntrIdList);
      		query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
      	    query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 		    query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 		    query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 		    query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
   	    
      	} 
    	if(alertSeverityIds != null && alertSeverityIds.size() > 0){
    		query.setParameterList("alertSeverityIds", alertSeverityIds);
      	}
      	return query.list();
	}
	public List<Long> getLocationFilterClickAlertIds(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
 			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> socialMediaTypeIds,
 			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
     	
     	StringBuilder sb = new StringBuilder();  
     	
     	 sb.append("select ");
     	
     	sb.append(" distinct model.alert.alertId ");
 	    
     	sb.append(" from GovtDepartmentDesignationNew model1,AlertAssignedOfficerNew model " +
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
	  	    	  " left join model.alert.tvNewsChannel TNC" +
	  	    	  " left join model.alert.socialMediaType SMT" +
	  	    	  " left join model.alert.alertCallCenterType ACCT  ");
 	    sb.append(" where model1.govtDepartmentDesignationId=model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId and   model.alert.isDeleted='N' and model.isDeleted = 'N' and " +
	    		  " model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+")   ");
 	    
 	    if(desigIds != null && !desigIds.isEmpty())
   	      sb.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId in (:desigIds)");
 	    
 	  
 	    		if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
	        	      sb.append(" and  S.govtDepartmentWorkLocationId in (:childLevelVals)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	        	      sb.append(" and Z.govtDepartmentWorkLocationId in (:childLevelVals)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	        	      sb.append(" and R.govtDepartmentWorkLocationId in (:childLevelVals)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	        	      sb.append(" and C.govtDepartmentWorkLocationId in (:childLevelVals) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	        	      sb.append(" and D.govtDepartmentWorkLocationId in (:childLevelVals) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	        	      sb.append(" and DIV.govtDepartmentWorkLocationId in (:childLevelVals)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	        	      sb.append(" and SUBDIV.govtDepartmentWorkLocationId in (:childLevelVals)   ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	          	      sb.append(" and T.govtDepartmentWorkLocationId in (:childLevelVals) ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	          	      sb.append(" and LEB.govtDepartmentWorkLocationId in (:childLevelVals)  ");
	        	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	          	      sb.append(" and P.govtDepartmentWorkLocationId in (:childLevelVals)   ");
 	   
 	    	
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
 	    
 	       if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
    	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in (:calCntrIdList)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) )");
    	    }
 	    
 	    if(priorityId != null && priorityId.longValue() >0l)
 	    	sb.append("  and model.alert.alertSeverityId = :priorityId " );
 	    
 	    if(fromDate != null && endDate != null){
 	    	sb.append("  and date(model.insertedTime) between :fromDate and :endDate "); 
 	    }
 	    
 	     StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	      
	      if(querySb.length() > 0){
	    	 sb.append(querySb); 
	      }
 	    
 	    if(statusIds != null && statusIds.size() > 0){
 	    	sb.append("  and model.alertStatus.alertStatusId in (:statusIds) ");
 	    }
 	    
 	    /*sb.append(" group by ");
 	    	if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
     	      sb.append("   S.govtDepartmentWorkLocationId  ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
     	      sb.append("  Z.govtDepartmentWorkLocationId   ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
     	      sb.append("  R.govtDepartmentWorkLocationId   ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
     	      sb.append("  C.govtDepartmentWorkLocationId  ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
     	      sb.append("  D.govtDepartmentWorkLocationId  ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
     	      sb.append("  DIV.govtDepartmentWorkLocationId   ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
     	      sb.append("  SUBDIV.govtDepartmentWorkLocationId    ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
       	      sb.append("  T.govtDepartmentWorkLocationId  ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
       	      sb.append("  LEB.govtDepartmentWorkLocationId   ");
     	    else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
       	      sb.append("  P.govtDepartmentWorkLocationId    ");*/
 	    
 	    Query query = getSession().createQuery(sb.toString());
 	    
 	    if(govtScopeIds != null && !govtScopeIds.isEmpty())
 	      query.setParameterList("govtScopeIds", govtScopeIds);
 	    
 	    if(desigIds != null && !desigIds.isEmpty())
 	    	 query.setParameterList("desigIds", desigIds);
 	    
 	    if(childLevelVals != null && !childLevelVals.isEmpty())
 	    	query.setParameterList("childLevelVals", childLevelVals);
 	    
 	    if(fromDate != null && endDate != null){
 	    	query.setDate("fromDate", fromDate);
 	    	query.setDate("endDate", endDate);
 	    }
 	   if(querySb.length() > 0){
 	    	query.setParameterList("levelValues", levelValues);
 	    }
 	    
 	    if(priorityId != null && priorityId.longValue() >0l)
 	    	query.setParameter("priorityId", priorityId);
 	    
 	    if(statusIds != null && statusIds.size() > 0){
 	    	query.setParameterList("statusIds", statusIds);
 	    }
 	   if(printIdsList != null && printIdsList.size() > 0 && electronicIdsList != null && electronicIdsList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0 ){
   	      query.setParameterList("printIdList", printIdsList);
   	      query.setParameterList("electronicIdList", electronicIdsList);
   	      query.setParameterList("calCntrIdList", calCntrIdList);
   	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
   	      query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		  query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		  query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		  query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
   	    }
 	    
 	    return query.list();
 	    	
     }
	public StringBuilder prepareQueryBasedOnUserAccessLevel(Long levelId,List<Long> levelValues){
		
		  StringBuilder sb = new StringBuilder();
		  
		if(levelId != null && levelId.longValue()>0l && levelValues != null && !levelValues.isEmpty()){
			
			   if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID){
				   sb.append(" and UA.stateId in (:levelValues)");  
			   }else if( levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID){
				   sb.append(" and UA.zoneId in (:levelValues)"); 
			   }else if(levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID){
				   sb.append(" and UA.regionId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID){
				   sb.append(" and UA.circleId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID){
				   sb.append(" and UA.districtId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID){
				   sb.append(" and UA.divisionId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID){
				   sb.append(" and UA.subDivisionId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID){
				   sb.append(" and UA.tehsilId in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID){
				   sb.append(" and UA.localElectionBodyId in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID){
				   sb.append(" and UA.panchayatId in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_WARD_LEVEL_ID){
				   sb.append(" and UA.wardId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_GMC_LEVEL_ID){
				   sb.append(" and UA.gmcId in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CLUSTER_LEVEL_ID){
				   sb.append(" and UA.clusterId in (:levelValues)"); 
			   }
		}
		return sb;
	}
	public StringBuilder prepareSqlQueryBasedOnUserAccessLevel(Long levelId,List<Long> levelValues){
		
		  StringBuilder queryStr = new StringBuilder();
		  
		  if(levelId != null && levelId.longValue()>0l && levelValues != null && !levelValues.isEmpty()){
				
			   if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID){
				   queryStr.append(" and GUA.state_id in (:levelValues)");  
			   }else if( levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID){
				   queryStr.append(" and GUA.zone_id in (:levelValues)"); 
			   }else if(levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID){
				   queryStr.append(" and GUA.region_id in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID){
				   queryStr.append(" and GUA.circle_id in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID){
				   queryStr.append(" and GUA.district_id in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID){
				   queryStr.append(" and GUA.division_id in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID){
				   queryStr.append(" and GUA.sub_division_id in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID){
				   queryStr.append(" and GUA.tehsil_id in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID){
				   queryStr.append(" and GUA.local_election_body in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID){
				   queryStr.append(" and GUA.panchayat_id in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_WARD_LEVEL_ID){
				   queryStr.append(" and GUA.ward in (:levelValues)"); 
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_GMC_LEVEL_ID){
				   queryStr.append(" and GUA.gmc_id in (:levelValues)");
			   }else if(levelId.longValue() == IConstants.GOVT_DEPARTMENT_CLUSTER_LEVEL_ID){
				   queryStr.append(" and GUA.cluster_id in (:levelValues)"); 
			   }
		}
	  return queryStr;
	}
	
	public StringBuilder prepareQueryBasedOnFilterType(List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIds,List<Long> socialMediaTypeIds){
		StringBuilder sb = new StringBuilder();
		if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty()){
  	      sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (model.alert.socialMediaTypeId in(:socialMediaTypeIds)) or (model.alert.alertCallCenterTypeId in(:calCntrIds))) ");
	    } 
		return sb;
	}
	public Long getAlertdetails(Long alertId,List<Long> deptsList,List<Long> levelValues,Long levelId,Set<Long> govtScopeIds){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.alert.alertId " +
		 				 " from AlertAssignedOfficerNew model " +
		 				" left join model.govtDepartmentDesignationOfficer.govtUserAddress UA " +
		    	          " left join UA.state S " +
		    	          " left join UA.zone Z  " +
		    	          " left join UA.region R " +
		    	          " left join UA.circle C " +
		    	          " left join UA.district D " +
		    	          " left join UA.division DIV " +
		    	          " left join UA.subDivision SUBDIV " +
		    	          " left join UA.tehsil T " +
		    	          " left join UA.localElectionBody LEB " +
		    	          " left join UA.panchayat P " +
		 				 " where model.alertId =:alertId  " );
		 if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID)
			 queryStr.append(" and UA.stateId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID)
	   	    	queryStr.append(" and UA.zoneId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID)
	   	    	queryStr.append(" and UA.regionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID)
	   	    	queryStr.append(" and UA.circleId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID)
	   	    	queryStr.append(" and UA.districtId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID)
	   	    	queryStr.append(" and UA.divisionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID)
	   	    	queryStr.append(" and UA.subDivisionId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID)
	   	    	queryStr.append(" and UA.tehsilId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID)
	   	    	queryStr.append(" and UA.localElectionBodyId in (:levelValues)");
	   	    else if(levelId != null && levelValues != null && !levelValues.isEmpty() && levelId.longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID)
	   	    	queryStr.append(" and UA.panchayatId in (:levelValues)");
		 if(deptsList != null && deptsList.size()>0){
			 queryStr.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptsList)");
		 }
		 
		 if(govtScopeIds != null && govtScopeIds.size() >0){
			 queryStr.append("  and model.govtDepartmentDesignationOfficer.govtDepartmentScopeId in (:govtScopeIds)");
		 }
		 queryStr.append(" and model.alert.isDeleted = 'N' " +
				 		 " and model.isDeleted = 'N' ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("alertId", alertId);
		 if(levelId != null && levelValues != null && levelValues.size()>0){
	    	 query.setParameterList("levelValues", levelValues);
	     }
		 
		 if(govtScopeIds != null && govtScopeIds.size() >0){
			 query.setParameterList("govtScopeIds", govtScopeIds);
		 }
		 if(deptsList != null && deptsList.size()>0){
			 query.setParameterList("deptsList", deptsList);
		 }
		 return (Long) query.uniqueResult();
	 }
	public List<Object[]> getAlertFeedBackDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
	     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
	     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
	     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue, String reopen,Long source,String feedbackType){
	    	 
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select ");
	    
		queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
		queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
		queryStr.append(" GDWL1.location_name as locationName, ");//2
		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
					if(reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true")){
						queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3
					}else{
						queryStr.append(" GDWL.govt_department_scope_id as GDSI, ALTFS.alert_feedback_status_id as govtDepartmentScopeId, ");//3
					}
						
				}
			}else{
				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
					if(reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true")){
						queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
					}else{
						queryStr.append(" ALTFS.alert_feedback_status_id as govtDepartmentScopeId, ");//3
					}
					
				}
			}
		}
	    queryStr.append(" count(distinct AAO.alert_id) as count");
		queryStr.append(" from ");

		queryStr.append(" alert A ");
		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
		}
		queryStr.append(" ,alert_status ALTS ");
		queryStr.append(" ,alert_assigned_officer_new AAO ");
		queryStr.append(" ,govt_department_designation_officer_new GDDO ");
		queryStr.append(" ,govt_user_address GUA ");
		queryStr.append(" ,alert_category ALTC ");
		queryStr.append(" ,alert_type ALTT ");
		queryStr.append(" ,alert_feedback_status ALTFS ");
		queryStr.append(" ,govt_department_work_location GDWL ");
		queryStr.append(" ,govt_department_work_location GDWL1 ");
		if(filterParentScopeId != null && filterParentScopeId.longValue() > 0){
			queryStr.append(" ,govt_department_work_location GDWLP ");	
		}
		queryStr.append(" where ");
		queryStr.append(" A.alert_id = AAO.alert_id ");
		if((reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true"))){
			queryStr.append(" and ALTFS.alert_feedback_status_id in (2,3) ");
			queryStr.append(" and AAO.alert_status_id = 11 ");
	    }
		queryStr.append(" and ALTFS.alert_feedback_status_id = A.alert_feedback_status_id ");
		queryStr.append(" and A.is_deleted='N' ");
		queryStr.append(" and ALTFS.is_deleted='N' ");
		queryStr.append(" and (A.social_media_type_id is not null or A.alert_call_center_type_id is not null) ");
		queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
		if(source != null && source.longValue() > 0L){
			queryStr.append(" and A.alert_category_id =:source  ");
		}else{
			if(reopen.trim().equalsIgnoreCase("true") || feedbackType.trim().equalsIgnoreCase("pending")){
				queryStr.append(" and A.alert_category_id in (4,5)  ");    
			}else{
				//queryStr.append(" and A.alert_category_id in (2,3,4,5)  ");
				queryStr.append(" and A.alert_category_id in (4,5)  ");
			}
			
		}
		
		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");

		queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
		if(feedbackType != null && feedbackType.trim().equalsIgnoreCase("pending")){
			queryStr.append(" and ALTS.alert_status_id in (4,12) ");   
		}
		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
		

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
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				if(reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true")){
     					queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id,  AAO.alert_status_id ");
     				}else{
     					queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, ALTFS.alert_feedback_status_id ");
     				}
     			}
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				if(reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true")){
     					queryStr.append(" group by GDWL1.govt_department_work_location_id ,  AAO.alert_status_id ");
     				}else{
     					queryStr.append(" group by GDWL1.govt_department_work_location_id , ALTFS.alert_feedback_status_id ");
     				}
 					
     			}
     		}
 	 	}
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
 				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
 					query.addScalar("GDSI", Hibernate.LONG);
     			}
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
 		if(source != null && source.longValue() > 0L){
 			query.setParameter("source",source);
		}
	 	return query.list();
	}
	public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevelForOfficer(Date fromDate,Date toDate,
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
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3	
     			}
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
     			}
     		}
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
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
     				queryStr.append(" group by GDWL1.govt_department_work_location_id , GDWL.govt_department_scope_id, AAO.alert_status_id ");
     			}
     		}else{
     			if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
 					queryStr.append(" group by GDWL1.govt_department_work_location_id , AAO.alert_status_id ");
     			}
     		}
 	 	}
 		
 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
 		query.addScalar("parentGovtDepartmentScopeId", Hibernate.LONG);
 		query.addScalar("govtDepartmentWorkLocationId", Hibernate.LONG);
 		query.addScalar("locationName", Hibernate.STRING);
 		if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status")){
 			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
 				if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
 					query.addScalar("GDSI", Hibernate.LONG);
     			}
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
 		
 		return query.list();
	}
	public List<Long> getAllReOpenAlerts(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.alert.alertId from AlertAssignedOfficerNew model where " +
						" model.isDeleted = 'N' " +
						" and model.alert.isDeleted = 'N' " +
						" and model.alertStatus.alertStatusId = 11 ");//reopen
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Long> getAlertDetailsForGrievanceReportClick(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,Long statusId,Long sourseId,String feedbackType){
    	 
     	StringBuilder queryStr = new StringBuilder();
     	queryStr.append(" select ");
     	
     	queryStr.append(" distinct AAO.alert_id as alertId");
     	
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
 		
 		queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
 		queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");
 		
 		queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
 		queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
 		queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
 		queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
 		queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
 		queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
 		if(feedbackType != null && feedbackType.trim().equalsIgnoreCase("pending")){
 			queryStr.append(" and ALTS.alert_status_id in (12) ");
 			queryStr.append(" and AAO.alert_status_id in (12) ");
 			queryStr.append(" and A.alert_category_id in (4,5)  ");
 		}else{
 			queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
 		}
 		
 		if(deptScopeIdList != null && deptScopeIdList.size() > 0){
 			queryStr.append(" and GDWL.govt_department_scope_id in(:deptScopeIdList)");
 		}
 		
 		if(statusId != null && statusId.longValue() >0l)
 			queryStr.append(" and ALTS.alert_status_id = :statusId ");
 		
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
 		if(sourseId != null && sourseId.longValue() > 0l)
 			queryStr.append(" and A.alert_category_id = :sourseId " );
 		
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
 		
 		if(statusId != null && statusId.longValue() >0l)
 			query.setParameter("statusId",statusId);
 		
 		if(sourseId != null && sourseId.longValue() > 0l)
 			query.setParameter("sourseId",sourseId);
 		
 		return query.list();
     }
	
	public List<Long> getAlertFeedBackDetailsForGrievanceReportClick(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue, String reopen,Long source,Long statusId){
    	 
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append(" select distinct AAO.alert_id as alertId ");
    
	queryStr.append(" from ");

	queryStr.append(" alert A ");
	if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0){
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
	}
	queryStr.append(" ,alert_status ALTS ");
	queryStr.append(" ,alert_assigned_officer_new AAO ");
	queryStr.append(" ,govt_department_designation_officer_new GDDO ");
	queryStr.append(" ,govt_user_address GUA ");
	queryStr.append(" ,alert_category ALTC ");
	queryStr.append(" ,alert_type ALTT ");
	queryStr.append(" ,alert_feedback_status ALTFS ");
	queryStr.append(" ,govt_department_work_location GDWL ");
	queryStr.append(" ,govt_department_work_location GDWL1 ");
	if(filterParentScopeId != null && filterParentScopeId.longValue() > 0){
		queryStr.append(" ,govt_department_work_location GDWLP ");	
	}
	queryStr.append(" where ");
	queryStr.append(" A.alert_id = AAO.alert_id ");
	if((reopen != null && reopen.trim().length() > 0 && !reopen.trim().isEmpty() && reopen.trim().equalsIgnoreCase("true"))){
		queryStr.append(" and ALTFS.alert_feedback_status_id in (2,3) ");
		queryStr.append(" and AAO.alert_status_id = 11 ");
    }
	queryStr.append(" and ALTFS.alert_feedback_status_id = A.alert_feedback_status_id ");
	queryStr.append(" and A.is_deleted='N' ");
	queryStr.append(" and ALTFS.is_deleted='N' ");
	queryStr.append(" and (A.social_media_type_id is not null or A.alert_call_center_type_id is not null) ");
	queryStr.append(" and A.alert_category_id = ALTC.alert_category_id  ");
	if(source != null && source.longValue() > 0L){
		queryStr.append(" and A.alert_category_id =:source  ");
	}else{
		queryStr.append(" and A.alert_category_id in (4,5)  ");  
	}
	
	queryStr.append(" and A.alert_type_id = ALTT.alert_type_id  ");
	queryStr.append(" and A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+")  ");

	queryStr.append(" and AAO.is_deleted='N' ");//and AAO.is_approved = 'Y'
	queryStr.append(" and AAO.alert_status_id = ALTS.alert_status_id  ");
	queryStr.append(" and AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id  ");
	queryStr.append(" and GDWL.govt_department_scope_id = GDDO.govt_department_scope_id ");
	queryStr.append(" and GDDO.address_id = GUA.user_address_id  ");
	queryStr.append(" and GUA.user_address_id = GDWL.govt_user_address_id  ");
	

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
 	
	if(statusId != null && statusId.longValue() == 4l){
		queryStr.append(" and ALTFS.alert_feedback_status_id in (1,2,3)  ");
		queryStr.append(" and A.alert_status_id = 12 and AAO.alert_status_id = 12");      
	}else if(statusId != null && statusId.longValue() > 0l){
		queryStr.append(" and ALTFS.alert_feedback_status_id = :statusId   ");
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
		if(source != null && source.longValue() > 0L){
			query.setParameter("source",source);
		}
		
		if(statusId != null && statusId.longValue() > 0l && statusId.longValue() != 4l){
			query.setParameter("statusId",statusId);
		}
 	return query.list();
	}
	 public List<Object[]> getDataAvailableAlertStatus(){
		 Query query = getSession().createQuery("select distinct model.alertStatus.alertStatusId,model.alertStatus.alertStatus from AlertAssignedOfficerNew model where model.isDeleted='N' and model.alertStatus.alertStatusId != 1 ");//except pending
		 return query.list();
				 
	 }
	 public List<Object[]> getMainDeptAndItsSubDepartment(){
 		
		StringBuilder sb = new StringBuilder();  
		sb.append("select");
		sb.append(" gd.govt_department_id as parentDeptId ,gd.department_name as parentName,gd.color color," +
				  " gd1.govt_department_id as childDeptId,gd1.department_name as childDeptName,gd1.color as color1 "+
		          " from govt_department_relation gdr,govt_department gd,govt_department gd1" +
				  " where gdr.parent_govt_department_id = gd.govt_department_id"+
				  " and gdr.sub_govt_department_id = gd1.govt_department_id"+ 
				  " group by gdr.parent_govt_department_id,gd1.govt_department_id " +
				  " order by gd.govt_department_id,gd1.govt_department_id");
 	    
 	    SQLQuery query = getSession().createSQLQuery(sb.toString());
 	
 		query.addScalar("parentDeptId", Hibernate.LONG);
 		query.addScalar("parentName", Hibernate.STRING);
 		query.addScalar("color", Hibernate.STRING);
 		query.addScalar("childDeptId", Hibernate.LONG);
 		query.addScalar("childDeptName", Hibernate.STRING);
 		query.addScalar("color1", Hibernate.STRING);
 	    
 	      return query.list();
 	}
	 public List<Object[]> getDifferenceTimeList(Date fromDate,Date toDate,
	     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
	     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> socialMediaTypeIds,Long source,List<Long> alertStatusIds){
	     	StringBuilder queryStr = new StringBuilder();
	     	queryStr.append(" select distinct ");
	     	
	     	/*queryStr.append(" GDWL1.govt_department_scope_id as parentGovtDepartmentScopeId, ");//0
	     	queryStr.append(" GDWL1.govt_department_work_location_id as govtDepartmentWorkLocationId, ");//1
	     	queryStr.append(" GDWL1.location_name as locationName, ");//2
	     	
  		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
  			queryStr.append(" GDWL.govt_department_scope_id as GDSI, AAO.alert_status_id as govtDepartmentScopeId, ");//3
  		}else{
  			queryStr.append(" AAO.alert_status_id as govtDepartmentScopeId, ");//3
  		}*/
	     	
	     	queryStr.append(" A.alert_id, A.created_time, A.updated_time, TIMESTAMPDIFF(DAY,A.created_time,A.updated_time) ");
	     	
	 		queryStr.append(" from ");
	 		
	 		queryStr.append(" alert A ");
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty()){
	 			queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
	 			queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
				queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
	 			queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");
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
	 		if(source != null && source.longValue() > 0){
	 			queryStr.append(" and A.alert_category_id =:source   ");
	 		}else{
	 			queryStr.append(" and A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+")  ");
	 		}
	 		
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
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 11L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.ward  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 12L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.gmc_id  ");
			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 13L){
				queryStr.append(" and GDWL1.govt_department_work_location_id = GUA.cluster_id  ");
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
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() ){
	 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or(ACCT.alert_call_center_type_id in(:calCntrIds)))  ");
	 		}
	 		
	 		if(alertStatusIds != null && alertStatusIds.size() > 0){
	 			queryStr.append(" and A.alert_status_id in (:alertStatusIds)");
	 		}
	 		
	 		StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
		      
	 		if(querySb.length() > 0){
	 			queryStr.append(querySb); 
	 		}
	 		
	 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
	 		
	 		if(fromDate != null && toDate != null){
	 			query.setDate("fromDate", fromDate);
	 			query.setDate("toDate", toDate);
	 		}
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty()){
	 			query.setParameterList("printIdList", printIdList);  
	 			query.setParameterList("electronicIdList", electronicIdList);
	 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	 			query.setParameterList("calCntrIds", calCntrIds);
	 		}
	 		if(querySb.length() > 0){
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
	 		
	 		if(source != null && source.longValue() > 0){
	 			query.setParameter("source",source);
	 		}
	 		if(alertStatusIds != null && alertStatusIds.size() > 0){
	 			query.setParameterList("alertStatusIds",alertStatusIds);
	 		}
	 		return query.list();
	     }
	 
	 public List<Object[]> getPresentAssignedDepartmentOfAlert(Long alertId){
		 Query query = getSession().createQuery(" SELECT model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
		 		" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName" +
		 		" FROM AlertAssignedOfficerNew model WHERE model.alertId = :alertId ");
		 query.setParameter("alertId", alertId);
		 return query.list();
	 }
	 
	 public List<Object[]> getAlertProposalCategoryWiseAlertCnt(Date fromDate, Date toDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
     		List<Long> alertStatusIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type){
 		
		  StringBuilder sb = new StringBuilder();  
 	      
		  sb.append("select ");
		  if(type != null && type.equalsIgnoreCase("Department")){
	  	    	 sb.append(" GDDON.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	  	    	 		  "  GDDON.govtDepartmentDesignation.govtDepartment.departmentName,") ;
	  	  }
 	      sb.append(" model.govtProposalCategory.govtProposalCategoryId," +
 	      		    " model.govtProposalCategory.category," +
 	      		    " model.govtProposalStatus.govtProposalStatusId," +
 	      		    " model.govtProposalStatus.status " );
 	      
  	     sb.append(" ,count(distinct model.alert.alertId),sum(model.proposalAmount) ");
 	     
	 	 sb.append(" from GovtProposalPropertyCategory model" +
	 	          " left join model.alert.edition EDS " +
	 	          " left join model.alert.tvNewsChannel TNC " +
	 	          " left join model.alert.alertCategory AC" +
	 	          " left join model.alert.socialMediaType SMT " +
	 	          " left join model.alert.alertCallCenterType ACCT,AlertAssignedOfficerNew model1," +
	 	          " GovtDepartmentDesignationOfficerNew GDDON," +
	 	          " GovtUserAddress UA  ");
	     sb.append(" where model1.alertId = model.alert.alertId " +
	     		  "  and model1.govtDepartmentDesignationOfficerId=GDDON.govtDepartmentDesignationOfficerId " +
	     		  "  and GDDON.addressId=UA.userAddressId " +
	     		  "  and model.alert.isDeleted='N' and model.isDeleted = 'N' and model1.isDeleted='N' " +
	 	          "  and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
	 	    	  "  AC.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
 	    
 	     if(departmentIds != null && !departmentIds.isEmpty())
 	      sb.append("  and GDDON.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
 	    
 	      if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 	  	      sb.append(" and (EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) ) ");
 		   } 
 	     
 	      if(fromDate != null && toDate != null)
   	        sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
 	    
 	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
 	      
 	      if(querySb.length() > 0){
 	    	 sb.append(querySb); 
 	      }
 	    
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	sb.append(" and model1.alertStatus.alertStatusId in (:alertStatusIds)");
 	    }
 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 	    	sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
 	    }
 	   if(type != null && type.equalsIgnoreCase("Department")){
	    	 sb.append(" group by " +
	    	 		   " GDDON.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	    	 		   " model.govtProposalCategory.govtProposalCategoryId," +
	    	 		   " model.govtProposalStatus.govtProposalStatusId" +
	    	 		   " order by GDDON.govtDepartmentDesignation.govtDepartment.govtDepartmentId," +
	    	 		   " model.govtProposalCategory.govtProposalCategoryId,model.govtProposalStatus.govtProposalStatusId");
	   }else{
		   sb.append(" group by model.govtProposalCategory.govtProposalCategoryId,model.govtProposalStatus.govtProposalStatusId" +
	 	    		"  order by model.govtProposalCategory.govtProposalCategoryId,model.govtProposalStatus.govtProposalStatusId ");
	  }
 	   
 	  
 	    Query query = getSession().createQuery(sb.toString());
 	    if(departmentIds != null && !departmentIds.isEmpty())
 	      query.setParameterList("departmentIds", departmentIds);
 	    
 	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
 	       query.setParameterList("printIdList", printIdsList);
    	       query.setParameterList("electronicIdList", electronicIdsList);
    	       query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
    	       query.setParameterList("calCntrIds", calCntrIds);
	       	   query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
    	     }  
 	    if(fromDate != null && toDate != null){
 	        query.setDate("fromDate", fromDate);
 	        query.setDate("toDate", toDate);
 	    }
 	    if(querySb.length()>0){
 	    	query.setParameterList("levelValues", levelValues);
 	    }
 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
 	    	 query.setParameterList("alertStatusIds", alertStatusIds);
 	    }
 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
 	    	query.setParameterList("alertSeverityIds", alertSeverityIds);
 	    }
 	      return query.list();
 	}
	 public List<Long> getAlertProposalCategoryWiseAlertDtls(Date fromDate, Date toDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
	     		List<Long> alertStatusIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
	     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
	     		List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long proposalCategoryId,Long proposalStatusId){
	 		
			  StringBuilder sb = new StringBuilder();  
			  
	 	     sb.append(" select distinct model.alert.alertId ");
	 	     
	 	 	 sb.append(" from GovtProposalPropertyCategory model" +
		 	          " left join model.alert.edition EDS " +
		 	          " left join model.alert.tvNewsChannel TNC " +
		 	          " left join model.alert.alertCategory AC" +
		 	          " left join model.alert.socialMediaType SMT " +
		 	          " left join model.alert.alertCallCenterType ACCT,AlertAssignedOfficerNew model1," +
		 	          " GovtDepartmentDesignationOfficerNew GDDON," +
		 	          " GovtUserAddress UA  ");
		     sb.append(" where model1.alertId = model.alert.alertId " +
		     		  "  and model1.govtDepartmentDesignationOfficerId=GDDON.govtDepartmentDesignationOfficerId " +
		     		  "  and GDDON.addressId=UA.userAddressId " +
		     		  "  and model.alert.isDeleted='N' and model.isDeleted = 'N' and model1.isDeleted='N' " +
		 	          "  and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") and " +
		 	    	  "  AC.alertCategoryId in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
	 	    
	 	     if(departmentIds != null && !departmentIds.isEmpty())
	 	      sb.append("  and GDDON.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:departmentIds)");
	 	    
	 	      if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	 	  	      sb.append(" and (EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList))  or (SMT.socialMediaTypeId in(:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIds)) or (model.alert.mondayGrievanceTypeId in (:mondayGrievanceTypeIds)) or (model.alert.janmabhoomiTypeId in (:janmabhoomiTypeIds)) or (model.alert.specialGrievanceTypeId in (:specialGrievanceTypeIds)) or (model.alert.generalGrievanceTypeId in (:generalGrievanceTypeIds)) ) ");
	 		   } 
	 	     
	 	      if(fromDate != null && toDate != null)
	   	        sb.append(" and date(model.insertedTime) between :fromDate and :toDate");
	 	    
	 	      StringBuilder querySb = prepareQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
	 	      
	 	      if(querySb.length() > 0){
	 	    	 sb.append(querySb); 
	 	      }
	 	    
	 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	 	    	sb.append(" and model1.alertStatus.alertStatusId in (:alertStatusIds)");
	 	    }
	 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	 	    	sb.append(" and model.alert.alertSeverityId in (:alertSeverityIds)");
	 	    }
	 	    if(proposalCategoryId != null && proposalCategoryId.longValue() > 0){
	 	      sb.append(" and model.govtProposalCategory.govtProposalCategoryId=:proposalCategoryId");
	 	    }
	 	   if(proposalStatusId != null && proposalStatusId.longValue() > 0){
		 	  sb.append(" and model.govtProposalStatus.govtProposalStatusId=:proposalStatusId");
		 	}
	 	    Query query = getSession().createQuery(sb.toString());
	 	    if(departmentIds != null && !departmentIds.isEmpty())
	 	      query.setParameterList("departmentIds", departmentIds);
	 	    
	 	    if(printIdsList != null && !printIdsList.isEmpty() && electronicIdsList != null && !electronicIdsList.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	 	       query.setParameterList("printIdList", printIdsList);
	    	       query.setParameterList("electronicIdList", electronicIdsList);
	    	       query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	    	       query.setParameterList("calCntrIds", calCntrIds);
		       	   query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
		 		   query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
		 		   query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
		 		   query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	    	     }  
	 	    if(fromDate != null && toDate != null){
	 	        query.setDate("fromDate", fromDate);
	 	        query.setDate("toDate", toDate);
	 	    }
	 	    if(querySb.length()>0){
	 	    	query.setParameterList("levelValues", levelValues);
	 	    }
	 	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	 	    	 query.setParameterList("alertStatusIds", alertStatusIds);
	 	    }
	 	    if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	 	    	query.setParameterList("alertSeverityIds", alertSeverityIds);
	 	    }
	 	   if(proposalCategoryId != null && proposalCategoryId.longValue() > 0){
	 		  query.setParameter("proposalCategoryId", proposalCategoryId);
		   }
		   if(proposalStatusId != null && proposalStatusId.longValue() > 0){
			   query.setParameter("proposalStatusId", proposalStatusId);
		   }
	 	      return query.list();
	 	}
	 public List<Object[]> getOfficerWiseAlertCountBasedOnDepartmentScopeLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
	     		Long parentGovtDepartmentScopeId,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
	     		List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	    	 
	     	StringBuilder queryStr = new StringBuilder();
	     	queryStr.append(" select ");
	     	queryStr.append(" GON.govt_officer_id as govtOfficerId," +//0
	     	
	     			        " GON.officer_name as officerName," +//1
	     			        " GON.mobile_no as mobileNo, "+//2
	     	                " GDDN.govt_department_designation_id as govtDepartmentDesignationId," +//3
	     			        " GDDN.designation_name as designationName," +//4
	     			        " GDWL.govt_department_id as departmentId," +//5
	     			        " GDWL.govt_department_work_location_id as locationId," +//6
	     			        " GDWL.location_name as locationName," +//7
	     			        " ALTS.alert_status_id as statusId," +//8
	     			        " ALTS.alert_status as alertStatus," +//9
	     			        " ALTS.alert_color as color,");//10
	     	queryStr.append(" count(distinct AAO.alert_id) as alertCount");//11
	     	
	     	queryStr.append(" from ");
	 		queryStr.append(" alert A ");
	 		
	 		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N')  ");
	 		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id  ");
			queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
	 		queryStr.append(" left outer join alert_call_center_type ACCT on ACCT.alert_call_center_type_id=A.alert_call_center_type_id ");
	 		
	 		queryStr.append(" ,alert_status ALTS, ");
	 		queryStr.append(" alert_assigned_officer_new AAO, ");
	 		queryStr.append(" govt_department_designation_officer_new GDDO, ");
	 		queryStr.append(" govt_user_address GUA, ");
	 		queryStr.append(" alert_category ALTC, ");
	 		queryStr.append(" alert_type ALTT,");
	 		queryStr.append(" govt_officer_new GON,");
	 		queryStr.append(" govt_department_designation_new GDDN,");
	 		queryStr.append(" govt_department_work_location GDWL ");
	 		
	 		if(filterParentScopeId != null && filterParentScopeId.longValue() > 0){
	 			queryStr.append(" ,govt_department_work_location GDWLP ");	
	 		}
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
	 		queryStr.append(" and AAO.govt_officer_id = GON.govt_officer_id  ");
	 		queryStr.append(" and GDDO.govt_department_designation_id = GDDN.govt_department_designation_id  ");
	 		
	 		if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue()> 0){
	 			queryStr.append(" and GDWL.govt_department_scope_id =:parentGovtDepartmentScopeId");
	 		}
	 			
	 		//child
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
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 11L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.ward  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 12L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.gmc_id  ");
			}else if(filterParentScopeId != null && filterParentScopeId.longValue() == 13L){
				queryStr.append(" and GDWLP.govt_department_work_location_id = GUA.cluster_id  ");
			}
			if(filterScopeValue != null && filterScopeValue.longValue() > 0L){
    			queryStr.append(" and  GDWLP.govt_department_work_location_id = :filterScopeValue");
    		}
			
	 		if(govtDepartmentId != null && govtDepartmentId.longValue() > 0L){
	 			queryStr.append(" and GDWL.govt_department_id = :govtDepartmentId   ");
	 		}
	 		
	 		if(fromDate != null && toDate != null){
	 			queryStr.append(" and date(AAO.inserted_time) between :fromDate and :toDate ");
	 		}
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	 			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or (SMT.social_media_type_id in(:socialMediaTypeIds)) or (ACCT.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )  ");
	 		}
	 		
	 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	 			queryStr.append(" and A.alert_severity_id in (:alertSeverityIds)");
	 		}
	 		if(alertStatusIds != null && alertStatusIds.size() > 0){
	 			queryStr.append(" and A.alert_status_id in (:alertStatusIds)");
	 		}
	 		
	 		  StringBuilder querySb = prepareSqlQueryBasedOnUserAccessLevel(levelId,levelValues);//Getting Dynamic Query Based on Access Level
		      
		      if(querySb.length() > 0){
		    	  queryStr.append(querySb); 
		      }
	 		
	 		queryStr.append(" group by GON.govt_officer_id,ALTS.alert_status_id order by GON.govt_officer_id,ALTS.alert_status_id");
	    	
	 		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
	 		query.addScalar("govtOfficerId", Hibernate.LONG);
	 		query.addScalar("officerName", Hibernate.STRING);
	 		query.addScalar("mobileNo", Hibernate.STRING);
	 		query.addScalar("govtDepartmentDesignationId", Hibernate.LONG);
	 		query.addScalar("designationName", Hibernate.STRING);
	 		query.addScalar("departmentId", Hibernate.LONG);
	 		query.addScalar("locationId", Hibernate.LONG);
	 		query.addScalar("locationName", Hibernate.STRING);
	 		query.addScalar("statusId", Hibernate.LONG);
	 		query.addScalar("alertStatus", Hibernate.STRING);
	 		query.addScalar("color", Hibernate.STRING);
	 		query.addScalar("alertCount", Hibernate.LONG);
	 		
	 		
	 		if(fromDate != null && toDate != null){
	 			query.setDate("fromDate", fromDate);
	 			query.setDate("toDate", toDate);
	 		}
	 		if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && socialMediaTypeIds != null && socialMediaTypeIds.size() > 0 && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	 			query.setParameterList("printIdList", printIdList);  
	 			query.setParameterList("electronicIdList", electronicIdList);
	 			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
	 			query.setParameterList("calCntrIds", calCntrIds);
	 			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
	 		}
	 		if(querySb.length() > 0){
	 			query.setParameterList("levelValues",levelValues);
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
	 		if(alertSeverityIds != null && alertSeverityIds.size() > 0){
	 			query.setParameterList("alertSeverityIds",alertSeverityIds);
	 		}
	 		if(alertStatusIds != null && alertStatusIds.size() > 0){
	 			query.setParameterList("alertStatusIds",alertStatusIds);
	 		}
	 		return query.list();
	     }

	 public List<Object[]> getLocationWiseAlertStatusCounts(Long departmentId,Date fromDate,Date toDate,String year,Long groupByValue,List<Long> locationValues){
		//0-scopeId,1-locationId,2-location,3-statusId,4-status,5-count
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select GDWL1.govt_department_scope_id as scopeId, " +
		 		" GDWL1.govt_department_work_location_id as locationId, " +
		 		" GDWL1.location_name as location, " +
		 		" AST.alert_status_id as statusId, " +
		 		" AST.alert_status as status, " +
		 		" count(distinct A.alert_id) as count " +
		 		" from alert_assigned_officer_new AAON,govt_department_designation_officer_new GDDON,govt_department_designation_new GDD,alert A,govt_user_address GUA, " +
		 		" alert_status AST,govt_department_work_location GDWL,govt_department_work_location GDWL1 " +
		 		" where AAON.govt_department_designation_officer_id = GDDON.govt_department_designation_officer_id " +
		 		" and GDD.govt_department_designation_id = GDDON.govt_department_designation_id " +
		 		" and A.alert_id = AAON.alert_id " +
		 		" and A.is_deleted ='N' " +
		 		" and AAON.is_deleted ='N' " +
		 		" and GDDON.level_value = GDWL.govt_department_work_location_id " +
		 		" and AST.alert_status_id = AAON.alert_status_id " +
		 		" and AST.alert_status_id != :pendingStatusId " +
		 		" and GDD.govt_department_id = :departmentId " +
		 		" and GDWL.govt_user_address_id = GUA.user_address_id " );
		 
		 if(groupByValue !=null && groupByValue == 5l){
			 sb.append(" and GDWL1.govt_department_work_location_id = GUA.district_id ");
		 }else if(groupByValue !=null && groupByValue == 8l){
			 sb.append(" and GDWL1.govt_department_work_location_id = GUA.tehsil_id ");
		 }
			
		 
		 if(locationValues !=null && locationValues.size()>0){
			 sb.append(" and GDWL.govt_department_work_location_id in (:locationValues) ");
		 }
		 
		 	if(year !=null && !year.trim().isEmpty()){
	 			sb.append(" and (year(A.created_time) = :year) ");
	 		}
	 		else if(fromDate != null && toDate != null){
				sb.append(" and (date(model.created_time) between :fromDate and :toDate) ");  
			}
		 	
		 	sb.append(" group by GDWL1.location_name,AST.alert_status_id ");
		 
		 Query query = getSession().createSQLQuery(sb.toString())
				 .addScalar("scopeId", Hibernate.LONG)
				 .addScalar("locationId", Hibernate.LONG)
				 .addScalar("location", Hibernate.STRING)
				 .addScalar("statusId", Hibernate.LONG)
				 .addScalar("status", Hibernate.STRING)
				 .addScalar("count", Hibernate.LONG);
		 
		 query.setParameter("pendingStatusId", 1l);
		 query.setParameter("departmentId", departmentId);
		 
		 if(year !=null && !year.trim().isEmpty()){
 			query.setParameter("year", year);
 		}
 		else if(fromDate != null && toDate != null){
 			query.setDate("fromDate", fromDate);
 			query.setDate("toDate", toDate);
 		}
		 if(locationValues !=null && locationValues.size()>0){
			 query.setParameterList("locationValues", locationValues);
		 }
		 
		 return query.list();
		
	 }
	 public List<Object[]> getHamletWiseIvrStatusCounts(Date fromDate,Date toDate,String year,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues){
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT ");
	       
	       sbm.append(" FROM ivr_survey_entity ISE,ivr_survey_entity_type ISET,ivr_survey_answer ISA,ivr_option IOP,user_address UA,ivr_respondent_location IRL,"
	             + " ivr_survey ISV ");
	       
	       sbe.append(" WHERE ISET.ivr_survey_entity_type_id = ISE.ivr_survey_entity_type_id "
	                     +" and ISE.ivr_survey_id = ISA.ivr_survey_id"
	                     +" and  ISA.ivr_option_id = IOP.ivr_option_id"
	                     +" and ISA.ivr_respondent_id = IRL.ivr_respondent_id"
	                     +" and IRL.address_id = UA.user_address_id"
	                     +" and ISV.ivr_survey_id = ISA.ivr_survey_id"
	                     +" and ISA.is_deleted ='false'"
	                     +" and ISET.ivr_survey_entity_type_id=6"
	                     +" and ISET.is_deleted ='false'"
	                     +" and UA.hamlet_id is not null"
	                     +" and IOP.is_deleted ='false'"
	                     +" and ISV.is_deleted ='false'" +
	                     " and IOP.satisfied_status is not null ");
	       
	       if(year!=null && !year.trim().isEmpty()){
	         sbe.append(" and year(ISV.start_date) =:year  ");
	       }
	       else if(fromDate!=null && toDate!=null){
	         sbe.append(" and date(ISV.start_date) between :fromDate and :toDate  ");
	       }
	       
	       sbg.append(" GROUP BY ");
	       
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
	         if(locationTypeId ==2l){
	           sb.append( "  UA.state_id as typeId,S.state_name as type " );
	           sbm.append( "  ,state S  " );          
	           sbe.append( "  and S.state_id = UA.state_id  " );
	           sbg.append(" UA.state_id ");
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   //sbe.append(" and S.state_id in (:locationValues) ");
	        	   sbe.append(" and UA.district_id between 11 and 23 ");
	           }
	         	 
	           
	         }else if(locationTypeId ==3l){
	           
	           sb.append( "  UA.district_id as typeId,D.district_name as type  " );
	           sbm.append( "  ,district D  " );           
	           sbe.append( "  and D.district_id = UA.district_id  " );	           
	           sbg.append(" UA.district_id ");
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and D.district_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==4l){
	           sb.append( "  UA.constituency_id as typeId,C.name as type " );
	           sbm.append( "  ,constituency C  " );
	           sbe.append( "  and C.constituency_id = UA.constituency_id  " );
	           
	           sbg.append(" UA.constituency_id ");         
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and C.constituency_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==5l){
	           sb.append( "  UA.tehsil_id as typeId,T.tehsil_name as type " );
	           sbm.append( "  ,tehsil T  " );           
	           sbe.append( "  and T.tehsil_id = UA.tehsil_id  " );
	           
	           sbg.append(" UA.tehsil_id ");  
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and T.tehsil_id  in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==6l){
	           sb.append( "  UA.panchayat_id as typeId,P.panchayat_name as type " );
	           sbm.append( "  ,panchayat P  " );
	           sbe.append( "  and P.panchayat_id =  UA.panchayat_id  " );
	           
	           sbg.append(" UA.panchayat_id "); 
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append( "  and P.panchayat_id in  (:locationValues)  " );
	           }	           
	         }
	         
	       }
	       
	       
	       if(searchlevelId !=null && searchlevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
		         if(searchlevelId ==2l){
		           //sbe.append("  and UA.state_id in (:searchLevelValues)  ");
		        	 sbe.append(" and UA.district_id between 11 and 23 ");
		         }else if(searchlevelId ==3l){
		           sbe.append(" and UA.district_id in (:searchLevelValues) ");		           
		         }else if(searchlevelId ==4l){
		           sbe.append(" and UA.constituency_id in (:searchLevelValues) ");		           
		         }else if(searchlevelId ==5l){
		           sbe.append(" and UA.tehsil_id  in (:searchLevelValues) ");
		         }else if(searchlevelId ==6l){
		           sbe.append( " and UA.panchayat_id in  (:searchLevelValues)  " );
		         }
		    }
	       
	       
	       
	       sb.append(" ,UA.hamlet_id as hamletId,IOP.satisfied_status as satisfiedStatus,count(distinct ISA.ivr_survey_answer_id) as count ");
	       
	       sbg.append(" ,UA.hamlet_id,IOP.satisfied_status ");
	       
	       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
	       
	       Query query = getSession().createSQLQuery(sb.toString())
	           .addScalar("typeId",Hibernate.LONG) 
	           .addScalar("type",Hibernate.STRING) 
	             .addScalar("hamletId",Hibernate.LONG) 
	             .addScalar("satisfiedStatus",Hibernate.STRING)
	             .addScalar("count",Hibernate.LONG);
				 if(year!=null && !year.trim().isEmpty()){
					 query.setParameter("year", Integer.parseInt(year));
				 }
	       else if(fromDate!=null && toDate!=null){
	         query.setParameter("fromDate", fromDate);
	         query.setParameter("toDate", toDate);
	       }
		  if(locationTypeId !=null && locationTypeId.longValue() != 2l){
	      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
	        query.setParameterList("locationValues", locationValues);
	      }
		  }
	      if(searchlevelId !=null && searchlevelId.longValue() != 2l){
	      if(searchlevelId !=null && searchlevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
	    	  query.setParameterList("searchLevelValues", searchLevelValues);
	      }
	      }
	      
	      return query.list();
	 }
	 @SuppressWarnings("unchecked")
	public List<Object[]> getAssignedMemberAlertDetails(Date fromDate,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
	 		StringBuilder queryStr = new StringBuilder();
	 			queryStr.append(" SELECT model.alert.alertType.alertTypeId,model.alert.alertType.alertType,count(distinct model.alert.alertId) " +
	 				" FROM AlertAssignedOfficerNew model " +
	 				" WHERE model.alert.isDeleted ='N' " +
	 				" and model.alert.alertTypeId  in (:alertTypeIds)" );
	 		
	 		if(fromDate != null && toDate != null){
				queryStr.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");   
			}
	 		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 	        if(locationTypeId == 4l){
	 	        	queryStr.append(" and model.alert.userAddress.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	queryStr.append(" and model.alert.userAddress.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	queryStr.append(" and model.alert.userAddress.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	queryStr.append(" and model.alert.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.alert.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }
	 	    }
	 		if(year != null && !year.trim().isEmpty()){
	 	    	queryStr.append(" and year(model.createdTime) =:year ");   
	 	    }
	 			queryStr.append(" group by  model.alert.alertType.alertTypeId ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		query.setParameterList("alertTypeIds", alertTypeIds);
			 if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
			 }
			 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 	        if(locationTypeId == 4l || locationTypeId ==8l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 3l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 5l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 6l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }else if(locationTypeId == 7l){
	 	        	query.setParameterList("locationValues", locationValues);
	 	        }
		 	 }
			 if(year !=null && !year.trim().isEmpty()){
				 query.setParameter("year", Integer.parseInt(year));
		 	 }
	 		return query.list();
	 	}

	public List<Object[]> getHamletWiseIvrStatusList(Date fromDate, Date toDate, String year, List<Long> locationValues,
			Long locationTypeId) {
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT UA.state_id as sId,S.state_name as sName,UA.district_id as dId,D.district_name as dName,UA.constituency_id cId,C.name as cName"
	       		+ " ,UA.tehsil_id as tId,T.tehsil_name as tName,P.panchayat_id as pId,P.panchayat_name as pName,UA.hamlet_id as hId,"
	    		   + " H.hamlet_name as hName,IOP.satisfied_status as status,"
	    		   + " count(distinct ISA.ivr_survey_answer_id) as count ");
	       
	       sbm.append(" FROM ivr_survey_entity ISE,ivr_survey_entity_type ISET,"
	       		+ "  ivr_survey_answer ISA,ivr_option IOP,user_address UA,ivr_respondent_location IRL,state S,district D,"
	       		+ "  constituency C,tehsil T,panchayat P,hamlet H,ivr_survey ISV ");
	  
	       sbe.append(" WHERE ISET.ivr_survey_entity_type_id = ISE.ivr_survey_entity_type_id"
	       		+ " and ISE.ivr_survey_id = ISA.ivr_survey_id"
	       		+ " and  ISA.ivr_option_id = IOP.ivr_option_id"
	       		+ "	and ISA.ivr_respondent_id = IRL.ivr_respondent_id"
	       		+ "	and IRL.address_id = UA.user_address_id " +   
	       		"  and ISV.ivr_survey_id = ISA.ivr_survey_id"
	       		+ "	and UA.state_id = S.state_id"
	       		+ "	and UA.district_id = D.district_id"
	       		+ "	and UA.constituency_id = C.constituency_id"
	       		+ "	and UA.tehsil_id = T.tehsil_id"
	       		+ "	and UA.panchayat_id = P.panchayat_id"
	       		+ " and UA.hamlet_id = H.hamlet_id	"
	       		+ " and ISA.is_deleted ='false'"
	       		+ " and ISET.ivr_survey_entity_type_id=6"
	       		+ " and ISET.is_deleted ='false'"
	       		+ " and UA.hamlet_id is not null"
	       		+ " and IOP.is_deleted ='false' " +
	       		" and ISV.is_deleted ='false' " +
	       		" and IOP.satisfied_status is not null ");       
	       if(year!=null && !year.trim().isEmpty()){
	         sbe.append(" and year(ISV.start_date) =:year  ");
	       }
	       else if(fromDate!=null && toDate!=null){
	         sbe.append(" and date(ISV.start_date) between :fromDate and :toDate  ");
	       }
	       
	       sbg.append(" GROUP BY  UA.state_id,UA.district_id,UA.constituency_id,UA.tehsil_id,UA.panchayat_id,UA.hamlet_id,IOP.satisfied_status ");
	       
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
	         if(locationTypeId ==2l){
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and S.state_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==3l){
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and D.district_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==4l){
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and C.constituency_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==5l){
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and T.tehsil_id  in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==6l){
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append( "  and P.panchayat_id in  (:locationValues)  " );
	           }	           
	         }
	         
	       }
	       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
	       
	       Query query = getSession().createSQLQuery(sb.toString())
	           .addScalar("sId",Hibernate.LONG) 
	           .addScalar("sName",Hibernate.STRING) 
	           .addScalar("dId",Hibernate.LONG)
	           .addScalar("dName",Hibernate.STRING)
	           .addScalar("cId",Hibernate.LONG)
	           .addScalar("cName",Hibernate.STRING)
	           .addScalar("tId",Hibernate.LONG)
	           .addScalar("tName",Hibernate.STRING)
	           .addScalar("pId",Hibernate.LONG)
	           .addScalar("pName",Hibernate.STRING)
	             .addScalar("hId",Hibernate.LONG) 
	             .addScalar("hName",Hibernate.STRING)
	             .addScalar("status",Hibernate.STRING)
	             .addScalar("count",Hibernate.LONG);
				 if(year!=null && !year.trim().isEmpty()){
					 query.setParameter("year", Integer.parseInt(year));
				 }
	       else if(fromDate!=null && toDate!=null){
	         query.setParameter("fromDate", fromDate);
	         query.setParameter("toDate", toDate);
	       }
	      
	      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
	        query.setParameterList("locationValues", locationValues);
	      }
	      
	      return query.list();
	
	}
	
	public List<Object[]> getDrainsIvrStatusCounts(Date fromDate,Date toDate,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues,Long entityType,List<Long> questionsList,List<Date> selectedDates){
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT ");
	       
	       sbm.append(" FROM ivr_survey_entity ISE,ivr_survey_entity_type ISET,ivr_survey_answer ISA,ivr_option IOP,user_address UA,ivr_respondent_location IRL,"
	             + " ivr_survey ISV ");
	       
	       sbe.append(" WHERE ISET.ivr_survey_entity_type_id = ISE.ivr_survey_entity_type_id "
	                     +" and ISE.ivr_survey_id = ISA.ivr_survey_id"
	                     +" and  ISA.ivr_option_id = IOP.ivr_option_id"
	                     +" and ISA.ivr_respondent_id = IRL.ivr_respondent_id"
	                     +" and IRL.address_id = UA.user_address_id"
	                     +" and ISV.ivr_survey_id = ISA.ivr_survey_id"
	                     +" "
	                     +" and ISET.ivr_survey_entity_type_id=:entityType " +
	                     "  and UA.panchayat_id is not null"
	                     +" and ISET.is_deleted ='false'"
	                     +" "
	                     +" and IOP.is_deleted ='false'"
	                     +" and ISV.is_deleted ='false'" +
	                     "  and ISA.is_deleted ='false'" +
	                     " and ISE.is_deleted ='false'" +
	                     " and ISA.is_valid ='Y' " +
	                     " and IRL.is_deleted ='N' ");
	       
	       /*if(year!=null && !year.trim().isEmpty()){
	         sbe.append(" and year(ISV.start_date) =:year  ");
	       }*/
	       if(fromDate!=null && toDate!=null){
	         sbe.append(" and date(ISV.start_date) between :fromDate and :toDate  ");
	       }else if(selectedDates !=null && selectedDates.size()>0){
	    	   sbe.append(" and date(ISV.start_date) in (:selectedDates)  ");
	       }
	       
	       sbg.append(" GROUP BY ");
	       
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
	         if(locationTypeId ==2l){
	           sb.append( "  UA.state_id as typeId,S.state_name as type " );
	           sbm.append( "  ,state S  " );          
	           sbe.append( "  and S.state_id = UA.state_id  " );
	           sbe.append( " and UA.district_id between 11 and 23 ");
	           sbg.append(" UA.state_id ");
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and S.state_id in (:locationValues) ");
	           }
	         	 
	           
	         }else if(locationTypeId ==3l){
	           
	           sb.append( "  UA.district_id as typeId,D.district_name as type  " );
	           sbm.append( "  ,district D  " );           
	           sbe.append( "  and D.district_id = UA.district_id  " );	           
	           sbg.append(" UA.district_id ");
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and D.district_id in (:locationValues) ");
	           }else{
	        	   sbe.append( " and UA.district_id between 11 and 23 ");
	           }
	           
	         }else if(locationTypeId ==4l){
	           sb.append( "  UA.constituency_id as typeId,C.name as type " );
	           sbm.append( "  ,constituency C  " );
	           sbe.append( "  and C.constituency_id = UA.constituency_id  " );
	           
	           sbg.append(" UA.constituency_id ");         
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and C.constituency_id in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==5l){
	           sb.append( "  UA.tehsil_id as typeId,T.tehsil_name as type " );
	           sbm.append( "  ,tehsil T  " );           
	           sbe.append( "  and T.tehsil_id = UA.tehsil_id  " );
	           
	           sbg.append(" UA.tehsil_id ");  
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append(" and T.tehsil_id  in (:locationValues) ");
	           }
	           
	         }else if(locationTypeId ==6l){
	           sb.append( "  UA.panchayat_id as typeId,P.panchayat_name as type " );
	           sbm.append( "  ,panchayat P  " );
	           sbe.append( "  and P.panchayat_id =  UA.panchayat_id  " );
	           
	           sbg.append(" UA.panchayat_id "); 
	           
	           if(locationValues !=null && locationValues.size()>0){
	        	   sbe.append( "  and P.panchayat_id in  (:locationValues)  " );
	           }	           
	         }else if(locationTypeId ==10l){
		           sb.append( "  UA.parliament_constituency_id as typeId,PC.name as type " );
		           sbm.append( "  ,Constituency PC  " );
		           sbe.append( "  and PC.constituency_id =  UA.parliament_constituency_id  " );
		           
		           sbg.append(" UA.parliament_constituency_id "); 
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append( "  and UA.parliament_constituency_id in  (:locationValues)  " );
		           }	           
		         }
	         
	       }
	       
	       
	       if(searchlevelId !=null && searchlevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
		         if(searchlevelId ==2l){
		           sbe.append("  and UA.state_id in (:searchLevelValues)  ");
		         }else if(searchlevelId ==3l){
		           sbe.append(" and UA.district_id in (:searchLevelValues) ");		           
		         }else if(searchlevelId ==4l){
		           sbe.append(" and UA.constituency_id in (:searchLevelValues) ");		           
		         }else if(searchlevelId ==5l){
		           sbe.append(" and UA.tehsil_id  in (:searchLevelValues) ");
		         }else if(searchlevelId ==6l){
		           sbe.append( " and UA.panchayat_id in  (:searchLevelValues)  " );
		         }else if(searchlevelId ==10l){
			           sbe.append( " and UA.parliament_constituency_id in  (:searchLevelValues)  " );
			      }
		    }
	       if(questionsList !=null && questionsList.size()>0){
	    	   sbm.append(" ,ivr_survey_question ISQ,ivr_question IQ ");
	    	   
	    	   sbe.append(" and ISA.ivr_survey_question_id = ISQ.ivr_survey_question_id" +
	    	   		" and IQ.ivr_question_id = ISQ.ivr_question_id" +
	    	   		" and  IQ.ivr_question_id in (:questionsList) ");
	       }
	       
	       if(selectedDates !=null && selectedDates.size()>1){
		       sb.append(" ,UA.panchayat_id as panchayatId,IOP.satisfied_status as satisfiedStatus,count(distinct ISA.ivr_survey_answer_id) as count,date(ISV.start_date) as date ");	       
		       sbg.append(" ,date(ISV.start_date),UA.panchayat_id,IOP.satisfied_status ");
	       }else{
	    	   sb.append(" ,UA.panchayat_id as panchayatId,IOP.satisfied_status as satisfiedStatus,count(distinct ISA.ivr_survey_answer_id) as count ");	       
		       sbg.append(" ,UA.panchayat_id,IOP.satisfied_status ");
	       }
	       
	       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
	       
	       Query query = null;
	       if(selectedDates !=null && selectedDates.size()>1){
	    	   query = getSession().createSQLQuery(sb.toString())
		           .addScalar("typeId",Hibernate.LONG) 
		           .addScalar("type",Hibernate.STRING) 		          
		           .addScalar("panchayatId",Hibernate.LONG) 
		           .addScalar("satisfiedStatus",Hibernate.STRING)		           
		           .addScalar("count",Hibernate.LONG)
		           .addScalar("date",Hibernate.DATE);
	       }else{
	    	   query = getSession().createSQLQuery(sb.toString())
			           .addScalar("typeId",Hibernate.LONG) 
			           .addScalar("type",Hibernate.STRING) 
			           .addScalar("panchayatId",Hibernate.LONG) 
			           .addScalar("satisfiedStatus",Hibernate.STRING)
		               .addScalar("count",Hibernate.LONG);
	       }
	       
			/*if(year!=null && !year.trim().isEmpty()){
				 query.setParameter("year", Integer.parseInt(year));
			}
	       else */if(fromDate!=null && toDate!=null){
	         query.setParameter("fromDate", fromDate);
	         query.setParameter("toDate", toDate);
	       }else if(selectedDates !=null && selectedDates.size()>0){
	    	   query.setParameterList("selectedDates", selectedDates);
	       }
	      
	      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
	        query.setParameterList("locationValues", locationValues);
	      }
	      if(searchlevelId !=null && searchlevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
	    	  query.setParameterList("searchLevelValues", searchLevelValues);
	      }
	      if(questionsList !=null && questionsList.size()>0){
	    	  query.setParameterList("questionsList", questionsList);
	      }
	      query.setParameter("entityType", entityType);
	      
	      return query.list();
	 }
	
	public List<Object[]> getIvrResponseDetails(Date fromDate,Date toDate,Long entityType,List<Long> questionsList){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.ivrOption.ivrOptionId,count(distinct ISA.ivrSurveyAnswerId) FROM IvrSurveyEntity model,IvrSurveyAnswer ISA," +
				" IvrRespondentLocation  IRL " +				
				" WHERE ISA.ivrSurveyId = model.ivrSurveyId " +
				" and IRL.ivrRespondent.ivrRespondentId = ISA.ivrRespondentId  " +
				" and model.ivrSurveyEntityTypeId = :entityType" + //dynamic
				" and model.isDeleted ='false'" +
				" and model.ivrSurveyEntityType.isDeleted='false'" +
				" and ISA.isDeleted ='false' " +
				" and ISA.ivrSurvey.isDeleted ='false' " +
				" and ISA.isValid ='Y' " +
				" and IRL.isDeleted ='N' " );
		
		if(questionsList !=null && questionsList.size()>0){
			str.append(" and ISA.ivrSurveyQuestion.ivrSurveyQuestionId in (:questionsList) " +
					" and ISA.ivrSurveyQuestion.isDeleted='false' ");
		}
		
		if(fromDate !=null && toDate !=null){
			str.append(" and ISA.ivrSurvey.startDate between :fromDate and :toDate ");
		}
		
		str.append("and model.ivrRespondent.isDeleted ='false' " +
				" and IRL.userAddress.state.stateId = :stateId ");
			
		str.append(" GROUP BY " +
				" model.ivrOption.ivrOptionId  ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("entityType", entityType);
		query.setParameter("stateId", 1l);
		
		if(questionsList !=null && questionsList.size()>0){
			query.setParameterList("questionsList", questionsList);
		}
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
public List<Object[]> getOverAllIvrDetails(Date fromDate,Date toDate,Long entityType,List<Long> questionsList,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT ISA.ivrOption.ivrOptionId,ISA.ivrOption.option ");
		
		if(type !=null && type.trim().equalsIgnoreCase("panchayat")){
			str.append(" ,count(distinct IRL.userAddress.panchayatId)");
		}else if(type !=null && type.trim().equalsIgnoreCase("hamlet")){
			str.append(" ,count(distinct IRL.userAddress.hamlet.hamletId)");
		}else{
			str.append(" ,count(distinct ISA.ivrSurveyAnswerId) ");
		}
		
		str.append(" FROM IvrSurveyEntity model,IvrSurveyAnswer ISA," +
					" IvrRespondentLocation  IRL " +				
				" WHERE ISA.ivrSurveyId = model.ivrSurveyId " +
				" and IRL.ivrRespondent.ivrRespondentId = ISA.ivrRespondentId  " +
				" and model.ivrSurveyEntityTypeId = :entityType" + //dynamic
				" and model.isDeleted ='false'" +
				" and model.ivrSurveyEntityType.isDeleted='false'" +
				" and ISA.isDeleted ='false' " +
				" and ISA.ivrSurvey.isDeleted ='false' " +
				" and ISA.isValid ='Y'" +
				" and ISA.ivrOption.isDeleted ='false' " +
				" and IRL.isDeleted ='N' " );
		
		if(questionsList !=null && questionsList.size()>0){
			str.append(" and ISA.ivrSurveyQuestion.ivrSurveyQuestionId in (:questionsList) " +
					" and ISA.ivrSurveyQuestion.isDeleted='false' ");
		}
		
		if(fromDate !=null && toDate !=null){
			str.append(" and ISA.ivrSurvey.startDate between :fromDate and :toDate ");
		}
		
		str.append("and IRL.ivrRespondent.isDeleted ='false' " +
				" and IRL.userAddress.state.stateId = :stateId ");
			
		str.append(" GROUP BY " +
				" ISA.ivrOption.ivrOptionId  ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("entityType", entityType);
		query.setParameter("stateId", 1l);
		
		if(questionsList !=null && questionsList.size()>0){
			query.setParameterList("questionsList", questionsList);
		}
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Date> getIvrSurveyDates(Date fromDate,Date toDate,Long entityType){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" SELECT date(model.ivrSurvey.startDate) FROM IvrSurveyEntity model " +
				" WHERE model.isDeleted ='false' " +
				" and model.ivrSurvey.isDeleted = 'false'" +
				" and model.ivrSurveyEntityType.isDeleted ='false' " +
				" and model.ivrSurveyEntityTypeId =:entityType ");
		
		if(fromDate !=null && toDate !=null){
			str.append(" and  date(model.ivrSurvey.startDate) between :fromDate and :toDate ");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		query.setParameter("entityType", entityType);
		
		return query.list();
	}
	public List<Object[]> getIvrSurveyQuestions(Date fromDate,Date toDate,Long entityType){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" SELECT distinct iq.ivr_question_id as ivrQuestionId,iq.question as question FROM ivr_survey_entity ise,ivr_survey_answer isa,ivr_survey_question isq,ivr_question iq ," +
				" ivr_survey_entity_type iset,ivr_survey isv " +
				"  WHERE ise.ivr_survey_id = isa.ivr_survey_id " +
				" and isa.ivr_survey_question_id = isq.ivr_survey_question_id" +
				" and isq.ivr_question_id = iq.ivr_question_id" +
				" and ise.ivr_survey_entity_type_id = iset.ivr_survey_entity_type_id " +
				" and isv.ivr_survey_id  = isa.ivr_survey_id " +
				" and ise.ivr_survey_entity_type_id = :entityType " +
				" and isq.is_deleted ='false'" +
				" and iq.is_deleted ='false'" +
				" and ise.is_deleted ='false'" +
				" and isa.is_deleted ='false'" +
				" and iset.is_deleted ='false' ");
		
		if(fromDate !=null && toDate !=null){
			str.append(" and  date(isv.start_date) between :fromDate and :toDate ");
		}
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("ivrQuestionId",Hibernate.LONG)
				.addScalar("question",Hibernate.STRING)  ;
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		query.setParameter("entityType", entityType);
		
		return query.list();
	}

}
