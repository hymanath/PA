package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;

public class SelfAppraisalCandidateLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocation, Long> implements
		ISelfAppraisalCandidateLocationDAO {
	   public SelfAppraisalCandidateLocationDAO() {
			super(SelfAppraisalCandidateLocation.class);
		 }
	   
	   public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId){
		   StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		    				" model.selfAppraisalLocationScopeId," +
		    				" model.locationValue," +
		    				" model.type " +
		    				" from SelfAppraisalCandidateLocation model" +
		    				" where " +
		    				" model.selfAppraisalCandidateId=:candidateId ");
		    Query query = getSession().createQuery(queryStr.toString());
		      query.setParameter("candidateId", candidateId);
		       return query.list();
	   }
}
