package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateTourLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateTourLocation;

public class SelfAppraisalCandidateTourLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateTourLocation, Long> implements ISelfAppraisalCandidateTourLocationDAO {
	    public SelfAppraisalCandidateTourLocationDAO() {
			super(SelfAppraisalCandidateTourLocation.class);
		 }
	    
	    public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId){
			   StringBuilder queryStr = new StringBuilder();
			    queryStr.append(" select " +
			    				" model.selfAppraisalLocationScopeId," +
			    				" model.locationValue," +
			    				" model.type " +
			    				" from SelfAppraisalCandidateTourLocation model" +
			    				" where " +
			    				" model.selfAppraisalCandidateId=:candidateId ");
			    Query query = getSession().createQuery(queryStr.toString());
			      query.setParameter("candidateId", candidateId);
			       return query.list();
		   }
	    public List<Object[]> getCandiateLocationScopeIdAndValuesByDesignation(Long designationId){
			   StringBuilder queryStr = new StringBuilder();
			    queryStr.append(" select model.selfAppraisalCandidateId, " +
			    				" model.selfAppraisalLocationScopeId," +
			    				" model.locationValue " +
			    				" from SelfAppraisalCandidateTourLocation model " +
			    				" where " +
			    				" model.selfAppraisalCandidate.selfAppraisalDesignationId=:designationId ");
			    Query query = getSession().createQuery(queryStr.toString());
			      query.setParameter("designationId", designationId);
			       return query.list();
		   }
}
