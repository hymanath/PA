package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocationNew;
import com.itgrids.partyanalyst.model.UserAddress;

public interface ISelfAppraisalCandidateLocationNewDAO extends GenericDao<SelfAppraisalCandidateLocationNew, Long> {
 public List<Object[]> getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId);
 public List<Object[]> getDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId);
 public List<UserAddress> getAllCandidateLocations(Long tourCategoryId,Long tourCandidateId);
}
