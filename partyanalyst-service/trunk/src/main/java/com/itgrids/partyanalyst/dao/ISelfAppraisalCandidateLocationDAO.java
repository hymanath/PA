package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;

public interface ISelfAppraisalCandidateLocationDAO extends GenericDao<SelfAppraisalCandidateLocation, Long> {
	 public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId);
	 public List<Object[]> getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId);
	 public List<Object[]> getDesigWiseAllCandidate(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet);
	 public List<Object[]> getLocationListByCndIdAndScopeId(Set<Long> cndId, Set<Long> selfAppLocationScpId);
}
