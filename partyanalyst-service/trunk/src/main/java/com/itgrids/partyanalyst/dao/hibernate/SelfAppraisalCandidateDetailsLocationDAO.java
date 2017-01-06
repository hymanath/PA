package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsLocation;

public class SelfAppraisalCandidateDetailsLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsLocation, Long>
		implements ISelfAppraisalCandidateDetailsLocationDAO {


	public SelfAppraisalCandidateDetailsLocationDAO() {
		super(SelfAppraisalCandidateDetailsLocation.class);
	}
	
	public int deleteSelfAppraisalCandidateDetailsLocations(Long detailsNewId){
		
		Query query = getSession().createQuery(" delete from SelfAppraisalCandidateDetailsLocation model where model.selfAppraisalCandidateDetailsNewId = :detailsNewId   ");
		
		query.setParameter("detailsNewId", detailsNewId);
		
		return query.executeUpdate();
	}
	
}
