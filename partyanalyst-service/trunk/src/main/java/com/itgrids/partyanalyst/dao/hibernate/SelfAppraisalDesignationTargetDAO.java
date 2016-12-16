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
    public List<Object[]> getDesignationWiseTargetCnt(Date fromDate,Date toDate){
    	StringBuilder queryStr = new StringBuilder();
    	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
    	 				 " model.selfAppraisalDesignation.designation," +
    	 				 " sum(model.targetDays) " +
    	 				 " from SelfAppraisalDesignationTarget model where model.isActive='Y' " +
    	 				 " and model.selfAppraisalDesignation.isActive='Y' ");
    	               if(fromDate != null){
    	            	   queryStr.append(" and date(model.startTime)=:fromDate");
    	               }
    	               if(toDate != null){
    	            	   queryStr.append(" and date(model.endTime)=:toDate"); 
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
    public List<Object[]> getTourCategoryWiseTargetCnt(Date fromDate,Date toDate){
    	StringBuilder queryStr = new StringBuilder();
    	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
    	 				 " model.selfAppraisalDesignation.designation," +
    	 				 " model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
    	 				 " model.selfAppraisalTourCategory.tourCategory," +
    	 				 " sum(model.targetDays) " +
    	 				 " from SelfAppraisalDesignationTarget model where model.isActive='Y' " +
    	 				 " and model.selfAppraisalDesignation.isActive='Y' " +
    	 				 " and model.selfAppraisalTourCategory.isDeleted='N' ");
    	               if(fromDate != null){
    	            	   queryStr.append(" and date(model.startTime)=:fromDate");
    	               }
    	               if(toDate != null){
    	            	   queryStr.append(" and date(model.endTime)=:toDate"); 
    	               }
    	 			  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
    	 			  				"model.selfAppraisalTourCategory.selfAppraisalTourCategoryId");
    	    Query query = getSession().createQuery(queryStr.toString());
    	   if(fromDate != null){
        	   query.setParameter("fromDate", fromDate);
           }
           if(toDate != null){
        	   query.setParameter("toDate", toDate); 
           }
           return query.list();
    }
}
