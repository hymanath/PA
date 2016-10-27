package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;

public interface ISelfAppraisalCandidateLocationDAO extends GenericDao<SelfAppraisalCandidateLocation, Long> {
	 public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId);
}
