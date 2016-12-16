package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDayTourDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDayTour;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDayTourDAO extends GenericDaoHibernate<SelfAppraisalCandidateDayTour, Long> implements
		ISelfAppraisalCandidateDayTourDAO {
	
	   public SelfAppraisalCandidateDayTourDAO(){
		   super(SelfAppraisalCandidateDayTour.class);
	   }
	   public List<Object[]> getToursSubmittedLeaderCntDesignationBy(Date fromDate,Date toDate){
				StringBuilder queryStr = new StringBuilder();
				queryStr.append(" select " +
				 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
 				 " model.selfAppraisalDesignation.designation," +
 				 " count(distinct model.selfAppraisalCandidateId) " +
 				 " from SelfAppraisalCandidateDayTour model where model.is_deleted='N' " +
 				 " and model.selfAppraisalDesignation.isActive='Y' ");
			   if(fromDate != null && toDate != null ){
	                  queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
	           }
 			  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
 			  Query query = getSession().createQuery(queryStr.toString());
 			  if(fromDate!= null && toDate!=null){
	 			   query.setDate("fromDate", fromDate);
	 			   query.setDate("toDate", toDate);
 			  }
 			  return query.list();
	     }
	   public List<Object[]> getLeaderComplainceCnt(Date fromDate,Date toDate){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId," +
			 " count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.is_deleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
                 queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
          }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				 "  model.selfAppraisalCandidateId");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  return query.list();
      }
	   public List<Object[]> getLeaderComplainceCntCategoryWise(Date fromDate,Date toDate){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId,model.selfAppraisalTourCategoryId," +
			 " count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.is_deleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
                queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
         }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				 "  model.selfAppraisalCandidateId," +
		  				 "  model.selfAppraisalTourCategoryId");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  return query.list();
    }
}
