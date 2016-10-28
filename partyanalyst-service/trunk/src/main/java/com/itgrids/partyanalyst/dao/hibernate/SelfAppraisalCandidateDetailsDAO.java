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
	  
	  public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append( " select " +
		    		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
		    		       " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +
		    		       " sum(model.ownTours)," +
		    		       " sum(model.inchargeTours) " +
		    		       " from SelfAppraisalCandidateDetails model " +
		    		       " where model.selfAppraisalCandidate.isActive='Y' ");
		                if(fromDate != null && toDate != null ){
		                	queryStr.append(" and date(model.updatedTime) :fromDate and :toDate ");
		                }
		                queryStr.append("group by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                return query.list();
	  }
}
