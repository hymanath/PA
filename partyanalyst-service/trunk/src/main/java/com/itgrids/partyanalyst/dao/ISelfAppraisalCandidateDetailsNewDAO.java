package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;

public interface ISelfAppraisalCandidateDetailsNewDAO extends GenericDao<SelfAppraisalCandidateDetailsNew, Long> {
 public List<Object[]> getCategoryWiseTourSubmittedLeader(Date fromDate,Date toDate,String type,List<Long> monthYearIds);
 public List<Object[]> getToursSubmittedLeaderCntDesignationBy(List<Long> monthYearIds,Set<Long> candiateIds);
 public List<Object[]> getLeaderComplainceCntCategoryWise(List<Long> monthYearIds,String type,Long selfAppraisalCandiateId,Set<Long> candiateIds);
 public List<Object[]> getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> monthYearIds,List<Long> designationIds);
 public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId);
 }
