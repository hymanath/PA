package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateTourLocation;

public interface ISelfAppraisalCandidateTourLocationDAO extends GenericDao<SelfAppraisalCandidateTourLocation, Long> {
	 public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId);
	 public List<Object[]> getCandiateLocationScopeIdAndValuesByDesignation(Long designationId);

}
