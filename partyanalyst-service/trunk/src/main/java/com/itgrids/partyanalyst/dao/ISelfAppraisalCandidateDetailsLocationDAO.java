package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsLocation;

public interface ISelfAppraisalCandidateDetailsLocationDAO extends GenericDao<SelfAppraisalCandidateDetailsLocation, Long> {

	public int deleteSelfAppraisalCandidateDetailsLocations(Long detailsNewId);
	
}
