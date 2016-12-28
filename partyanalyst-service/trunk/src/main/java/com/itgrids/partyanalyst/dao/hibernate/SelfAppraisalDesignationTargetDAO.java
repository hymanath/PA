package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalDesignationTarget;

public class SelfAppraisalDesignationTargetDAO extends GenericDaoHibernate<SelfAppraisalDesignationTarget, Long> implements
		ISelfAppraisalDesignationTargetDAO {
	   
	public SelfAppraisalDesignationTargetDAO(){
		super(SelfAppraisalDesignationTarget.class);
	}
    public List<Object[]> getDesignationWiseTargetCnt(Date fromDate,Date toDate,String type){
         StringBuilder queryStr = new StringBuilder();
         queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " sum(model.targetDays) " +
			 " from SelfAppraisalDesignationTarget model where model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
           if(type != null && type.equalsIgnoreCase("Category")){
        	 queryStr.append(" and model.tourTypeId is null ");  
           }else{
        	   queryStr.append(" and model.selfAppraisalTourCategoryId is null ");   
           }
           if(fromDate != null){
        	   queryStr.append(" and date(model.startTime)<=:fromDate");
           }
           if(toDate != null){
        	   queryStr.append(" and (model.endTime is null or date(model.endTime)>=:toDate)"); 
           }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
    	 Query query = getSession().createQuery(queryStr.toString());
    	   if(fromDate != null){
        	   query.setParameter("fromDate", fromDate);
           }
           if(toDate != null){
        	   query.setParameter("toDate", toDate); 
           }
           return query.list();
    }
    public List<Object[]> getTourCategoryWiseTargetCnt(Date fromDate,Date toDate,String type){
            StringBuilder queryStr = new StringBuilder();
            queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," );
            if(type.equalsIgnoreCase("Category")){
            	 queryStr.append(" model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
            			        " model.selfAppraisalTourCategory.tourCategory," );
            }else{
            	 queryStr.append(" model.tourType.tourTypeId," +
            			         " model.tourType.tourType," );
            }
             queryStr.append(" sum(model.targetDays) " +
			 " from SelfAppraisalDesignationTarget model where model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
           if(fromDate != null){
        	   queryStr.append(" and  date(model.startTime)<=:fromDate");
           }
           if(toDate != null){
        	   queryStr.append(" and (model.endTime is null or date(model.endTime)>=:toDate) "); 
           }
        
           if(type.equalsIgnoreCase("Category")){
        	  queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
           }else{
        	   queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
           }
		    queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId,");  	 
           if(type.equalsIgnoreCase("Category")){
        	 queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
           }else{
        	   queryStr.append("model.tourType.tourTypeId");   
           }
    	    Query query = getSession().createQuery(queryStr.toString());
    	   if(fromDate != null){
        	   query.setParameter("fromDate", fromDate);
           }
           if(toDate != null){
        	   query.setParameter("toDate", toDate); 
           }
           return query.list();
    }
    public List<Object[]> getCandiateWiseTargetCnt(Date fromDate,Date toDate,String type){
             StringBuilder queryStr = new StringBuilder();
             queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model2.selfAppraisalCandidateId," );
             if(type.equalsIgnoreCase("Category")){
            	 queryStr.append(" model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
            			        " model.selfAppraisalTourCategory.tourCategory," );
            }else{
            	 queryStr.append(" model.tourType.tourTypeId," +
            			         " model.tourType.tourType," );
            }
			 queryStr.append(" sum(model.targetDays) " +
			 " from SelfAppraisalDesignationTarget model,SelfAppraisalCandidate model2 " +
			 " where model.selfAppraisalDesignation.selfAppraisalDesignationId=model2.selfAppraisalDesignation.selfAppraisalDesignationId" +
			 " and model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y'");
             if(type.equalsIgnoreCase("Category")){
            	queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
             }else{
            	 queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
             }
             //boolean isEntered=false;
             if(fromDate != null){
          	   queryStr.append(" and date(model.startTime)<=:fromDate");
          	 //  isEntered=true;
             }
             if(toDate != null){
          	   queryStr.append(" and (model.endTime is null or date(model.endTime)>=:toDate)"); 
             }
              /* if(isEntered)
          	    queryStr.append(" )");*/
             
		    queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				  "  model2.selfAppraisalCandidateId");
		    if(type.equalsIgnoreCase("Category")){
	        	 queryStr.append(",model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
	           }else{
	        	   queryStr.append(",model.tourType.tourTypeId");   
	           }
    	   Query query = getSession().createQuery(queryStr.toString());
    	   if(fromDate != null){
        	   query.setParameter("fromDate", fromDate);
           }
           if(toDate != null){
        	   query.setParameter("toDate", toDate); 
           }
           return query.list();
    }
    public List<Object[]> getCandiateAndCategoryWiseTargetCnt(Date fromDate,Date toDate,String type,Long selfAppraisalCandiateId){
            StringBuilder queryStr = new StringBuilder();
             queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model2.selfAppraisalCandidateId,");
            if(type.equalsIgnoreCase("Category")){
            	 queryStr.append(" model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
            			        "  model.selfAppraisalTourCategory.tourCategory," );
            }else{
            	 queryStr.append(" model.tourType.tourTypeId," +
            			         " model.tourType.tourType," );
            }
			 queryStr.append(" sum(model.targetDays) " +
			 " from SelfAppraisalDesignationTarget model,SelfAppraisalCandidate model2 " +
			 " where model.selfAppraisalDesignation.selfAppraisalDesignationId=model2.selfAppraisalDesignation.selfAppraisalDesignationId" +
			 " and model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		     if(fromDate != null){
          	   queryStr.append(" and date(model.startTime)<=:fromDate");
             }
             if(toDate != null){
          	   queryStr.append(" and (model.endTime is null or date(model.endTime)>=:toDate)"); 
             }
             
           if(type.equalsIgnoreCase("Category")){
         	  queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
            }else{
         	   queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
            }
            if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
            	queryStr.append(" and model2.selfAppraisalCandidateId=:selfAppraisalCandidateId");
            }
		      queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				      " model2.selfAppraisalCandidateId ");
		  if(type.equalsIgnoreCase("Category")){
	         queryStr.append(",model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
	      }else{
	         queryStr.append(",model.tourType.tourTypeId");   
	       }
    	   Query query = getSession().createQuery(queryStr.toString());
    	   if(fromDate != null){
        	   query.setParameter("fromDate", fromDate);
           }
           if(toDate != null){
        	   query.setParameter("toDate", toDate); 
           }
           if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
        	  query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId); 
           }
           return query.list();
    }
    
    public List<Object[]> getTotalTargetOfDesignation(Date fromDate,Date toDate,List<Long> designationsList){
    	
    	StringBuilder str = new StringBuilder();
    	
    	str.append( " select model.selfAppraisalDesignationId,sum(model.targetDays) " +
    			" from SelfAppraisalDesignationTarget model " +
    			" where model.isActive='Y' " +
    			" and model.selfAppraisalDesignation.isActive='Y' " );
    	
    	str.append(" and model.selfAppraisalTourCategoryId is not null ");
    	
    	/*if(fromDate !=null && toDate !=null){
    		str.append(" and date(model.startTime)>=:fromDate and date(model.endTime)<=:toDate ");
    	}  */  
    	
    	if(fromDate !=null && toDate !=null){
    		str.append(" and date(model.startTime) between :fromDate and :toDate ");
    	}  
    	
    	if(designationsList !=null && designationsList.size()>0){
    		str.append(" and model.selfAppraisalDesignationId in (:designationsList) ");
    	}
    	
    	str.append(" group by model.selfAppraisalDesignationId ");    	
    	
    	Query query = getSession().createQuery(str.toString());
    	
    	if(fromDate !=null && toDate !=null){
    		query.setParameter("fromDate", fromDate);
    		query.setParameter("toDate", toDate);
    	}
    	if(designationsList !=null && designationsList.size()>0){
    		query.setParameterList("designationsList", designationsList);
    	}
    	
    	return query.list();
    	
    	
    }
}
