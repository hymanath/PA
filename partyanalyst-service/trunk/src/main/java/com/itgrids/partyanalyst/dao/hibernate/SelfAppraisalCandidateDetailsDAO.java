package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;

public class SelfAppraisalCandidateDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetails, Long> implements
		ISelfAppraisalCandidateDetailsDAO {
	  public SelfAppraisalCandidateDetailsDAO() {
			super(SelfAppraisalCandidateDetails.class);
	  }
	  
	  public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,Long desigId){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append( " select " +
		    		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
		    		       " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +
		    		       " sum(model.ownTours)," +
		    		       " sum(model.inchargeTours) " +
		    		       " from SelfAppraisalCandidateDetails model " +
		    		       " where model.selfAppraisalCandidate.isActive='Y' "); 
		                if(fromDate != null && toDate != null ){
		                	queryStr.append(" and date(model.updatedTime) between :fromDate and :toDate ");
		                }
		                if(desigId != null){
		                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId = :desigId ");
		                }
		                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                if(desigId != null){
		     			   query.setParameter("desigId",desigId); 
		     		    }    
		                return query.list();
	  }
	  public List<Object[]> getCandidateDtlsList(Date startDate, Date endDate,List<Long> candidateList){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " SACD.selfAppraisalCandidateDetailsId, " +
		  				  " SACD.selfAppraisalCandidate.selfAppraisalCandidateId, " +
		  				  " SACD.selfAppraisalCandidate.tdpCadre.firstname, " +
		  				  " SACD.month, " +
		  				  " SACD.year, " +
		  				  " SACD.ownTours, " +
		  				  " SACD.inchargeTours," +    
		  				  " SACD.remarks, " +
		  				  " SACD.reportPath " +          
		  				  " from " +
		  				  " SelfAppraisalCandidateDetails SACD " +  
		  				  " where " +
		  				  " SACD.selfAppraisalCandidate.selfAppraisalCandidateId in (:candidateList) and " +
		  				  " date(SACD.updatedTime) between :fromDate and :toDate ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(startDate != null && endDate != null ){
			  query.setDate("fromDate", startDate);
			  query.setDate("toDate", endDate);
          }
		  if(candidateList != null && candidateList.size() > 0){
			  query.setParameterList("candidateList",candidateList);
		  }
		  
		  return query.list();
	  }
}
