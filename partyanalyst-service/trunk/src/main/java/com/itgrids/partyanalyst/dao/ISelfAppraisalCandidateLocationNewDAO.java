package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocationNew;

public interface ISelfAppraisalCandidateLocationNewDAO extends GenericDao<SelfAppraisalCandidateLocationNew, Long> {
 public List<Object[]> getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId,String type);
 public List<Object[]> getDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> designationIds);
 public Object[] getAllCandidateLocations(Long cadreId,Long categoryId);
 public List<Object[]> getLocationValuesOfCandidate(Long candidateId,Long categoryId);
 public List<Object[]> getLocationWiseCandidate(Long cadreId);
 public List<Object[]> getLocationValuesOfCandidate1(Long candidateId,Long categoryId,Long tourTypeId);
 public List<Object[]> getUniqueCandiateBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userTypeId);
 //constituency page
 public List<Object[]> getLocationWiseTourMemberDetails(Long locationTypeId,List<Long> locationValues,Long stateId);
 
}
