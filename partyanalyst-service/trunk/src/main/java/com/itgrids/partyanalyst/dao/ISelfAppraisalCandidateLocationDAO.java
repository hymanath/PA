package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;

public interface ISelfAppraisalCandidateLocationDAO extends GenericDao<SelfAppraisalCandidateLocation, Long> {
	 public List<Object[]> getCandiateLocationScopeIdAndValues(Long candidateId);
	 public List<Object[]> getNoOfLeadersCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId);
	 public List<Object[]> getDesigWiseAllCandidate(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet, List<Long> desigList);
	 public List<Object[]> getLocationListByCndIdAndScopeId(Set<Long> cndId, Set<Long> selfAppLocationScpId);
	 public List<Object[]> getTotalLeadersDesignationBy(List<Long> desigIdList,Long userAccessLevelId,Set<Long> locationValueSet,Date fromDate,Date toDate);
	 public List<Object[]> getDesignationListDtls(Long userAccessLevelId, Set<Long> locationValueSet);
	 public List<Object[]> getTotalLeaderDesignationWise(List<Long> designationIds,Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId);
	 public List<Long> getCandiateIdList(Long userAccessLevelId, Set<Long> locationValueSet,List<Long> desigIdList);
	 public List<Object[]> getCandiateIdsScope(List<Long> candiateIds,String designations);
	 public List<Object[]> getCandiateLocation(Long locationScopeId,List<Long> candiateIds,String designationType);
}
