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
           if(type != null && type.equalsIgnoreCase("tourCategory")){
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
    public List<Object[]> getTourCategoryWiseTargetCnt(List<Long> monthYearsIds,String type){
            StringBuilder queryStr = new StringBuilder();
            queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," );
            if(type.equalsIgnoreCase("tourCategory")){
            	 queryStr.append(" model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
            			        " model.selfAppraisalTourCategory.tourCategory," );
            }else{
            	 queryStr.append(" model.tourType.tourTypeId," +
            			         " model.tourType.tourType," );
            }
             queryStr.append(" model.selfAppraisalToursMonth.selfAppraisalToursMonthId," +
             				" model.selfAppraisalToursMonth.monthName," +
             				" sum(model.targetDays) " +
			 " from SelfAppraisalDesignationTarget model where model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
           if(type.equalsIgnoreCase("tourCategory")){
        	  queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
           }else{
        	   queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
           }
           if(monthYearsIds != null && monthYearsIds.size() > 0){
        	   queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthYearids)");
           }
		    queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId,");  	 
           if(type.equalsIgnoreCase("tourCategory")){
        	 queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
           }else{
        	   queryStr.append("model.tourType.tourTypeId");   
           }
           queryStr.append(",model.selfAppraisalToursMonth.selfAppraisalToursMonthId");
           queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId");
           if(type.equalsIgnoreCase("tourCategory")){
          	 queryStr.append(",model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
             }else{
          	   queryStr.append(",model.tourType.tourTypeId");   
             }
    	    Query query = getSession().createQuery(queryStr.toString());
    	    if(monthYearsIds != null && monthYearsIds.size() > 0){
         	   query.setParameterList("monthYearids", monthYearsIds);
            }
           return query.list();
    }
  public List<Object[]> getCandiateAndCategoryWiseTargetCnt(List<Long> monthYearsIds,String type,Long selfAppraisalCandiateId){
            StringBuilder queryStr = new StringBuilder();
             queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
			 " model.selfAppraisalDesignation.designation," +//1
			 " model2.selfAppraisalCandidateId,");//2
            if(type.equalsIgnoreCase("tourCategory")){
            	 queryStr.append(" model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +//3
            			        "  model.selfAppraisalTourCategory.tourCategory," );//4
            }else{
            	 queryStr.append(" model.tourType.tourTypeId," +//3
            			         " model.tourType.tourType," );//4
            }
           queryStr.append("  model.selfAppraisalToursMonth.selfAppraisalToursMonthId," +//5
           				   " model.selfAppraisalToursMonth.monthName, " +//6
           				   " sum(model.targetDays) " +//7
			 " from SelfAppraisalDesignationTarget model,SelfAppraisalCandidate model2 " +
			 " where model.selfAppraisalDesignation.selfAppraisalDesignationId=model2.selfAppraisalDesignation.selfAppraisalDesignationId" +
			 " and model.isActive='Y' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
			 if(monthYearsIds != null && monthYearsIds.size() > 0){
	        	   queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthYearids)");
	         }
            if(type.equalsIgnoreCase("tourCategory")){
         	  queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
            }else{
         	   queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
            }
            if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
            	queryStr.append(" and model2.selfAppraisalCandidateId=:selfAppraisalCandidateId");
            }
		      queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				      " model2.selfAppraisalCandidateId ");
		  if(type.equalsIgnoreCase("tourCategory")){
	         queryStr.append(",model.selfAppraisalTourCategory.selfAppraisalTourCategoryId ");   
	      }else{
	         queryStr.append(",model.tourType.tourTypeId");   
	       }
		  queryStr.append(",model.selfAppraisalToursMonth.selfAppraisalToursMonthId ");
		   queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
  				   		   "  model2.selfAppraisalCandidateId ");
    	   Query query = getSession().createQuery(queryStr.toString());
    	   if(monthYearsIds != null && monthYearsIds.size() > 0){
         	   query.setParameterList("monthYearids", monthYearsIds);
            }
           if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
        	  query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId); 
           }
           return query.list();
    }
    
    public List<Object[]> getTotalTargetOfDesignation(Date fromDate,Date toDate,List<Long> designationsList,String type){
    	
    	StringBuilder str = new StringBuilder();
    	
    	str.append( " select model.selfAppraisalDesignationId,sum(model.targetDays) " +
    			" from SelfAppraisalDesignationTarget model " +
    			" where model.isActive='Y' " +
    			" and model.selfAppraisalDesignation.isActive='Y' " );
    	
    	if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("tourCategory")){
    		str.append(" and model.tourTypeId is null ");
    	}else if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("tourType")){
    		str.append(" and model.selfAppraisalTourCategoryId is null ");
    	}
    	
    	
    	if(fromDate != null && toDate != null){
    		str.append(" and date(model.startTime)<=:fromDate ");
    		str.append(" and (model.endTime is null or date(model.endTime)>=:toDate) "); 
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
    
    public List<Object[]> getDesignationAndCategoryWiseCandidatesTarget(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId){
        StringBuilder queryStr = new StringBuilder();
         queryStr.append(" select " +
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
		 
		 if(fromDate != null && toDate != null){
      	   queryStr.append(" and date(model.startTime)<=:fromDate");
      	   queryStr.append(" and (model.endTime is null or date(model.endTime)>=:toDate)"); 
         }
         
       if(type.equalsIgnoreCase("Category")){
     	  queryStr.append(" and model.tourTypeId is null and model.selfAppraisalTourCategory.isDeleted='N' "); 
        }else{
     	   queryStr.append(" and model.selfAppraisalTourCategoryId is null "); 
        }
        if(designationIds != null && designationIds.size() > 0){
        	queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds)");
        }
        
        if(candidateId !=null && candidateId>0l){
        	queryStr.append(" and model2.selfAppraisalCandidateId =:candidateId ");
        }
        
	      queryStr.append(" group by " +
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
       if(designationIds != null && designationIds.size() > 0){
    	  query.setParameterList("designationIds", designationIds); 
       }
       if(candidateId !=null && candidateId>0l){
    	   query.setParameter("candidateId", candidateId); 
       }
       return query.list();
}
    
    public List<Object[]> getTourCategoryWiseTargetCntDesignation(Date fromDate,Date toDate,String type,List<Long> designationIds){
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
       
       if(designationIds !=null && designationIds.size()>0){
    	   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
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
       
       if(designationIds !=null && designationIds.size()>0){
    	   query.setParameterList("designationIds", designationIds);
       }
       return query.list();
    }
    public List<Object[]> getCategoryWiseTargetCnt(Date fromDate,Date toDate,String type,List<Long> desinationIds){
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
       if(desinationIds != null && desinationIds.size() > 0){
           queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds)");	
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
       if(desinationIds != null && desinationIds.size() > 0){
    	  query.setParameterList("designationIds", desinationIds); 
       }
       return query.list();
}
    
}
