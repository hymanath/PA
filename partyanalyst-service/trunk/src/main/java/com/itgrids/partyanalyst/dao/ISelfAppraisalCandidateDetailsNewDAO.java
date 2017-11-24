package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;

public interface ISelfAppraisalCandidateDetailsNewDAO extends GenericDao<SelfAppraisalCandidateDetailsNew, Long> {
 public List<Object[]> getCategoryWiseTourSubmittedLeader(String type,List<Long> monthYearIds,Set<Long> candiateIds);
 public List<Object[]> getToursSubmittedLeaderCntDesignationBy(List<Long> monthYearIds,Set<Long> candiateIds);
 public List<Object[]> getLeaderComplainceCntCategoryWise(List<Long> monthYearIds,String type,Long selfAppraisalCandiateId,Set<Long> candiateIds);
 public List<Object[]> getTourSubmitteedDtlsDesignationWise(Set<Long> candidateIdSet,List<Long> monthYearIds);
 public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId);
 public List<Object[]> getSubmittedToursLeadersDetails(List<Long> desigIds,List<Long> monthyearIds);
 public List<Object[]> getCategoryWiseLeaderTourSubmittedCnt(String type,List<Long> monthyearIds,List<Long> designationIds,List<Long> candiateIds);
 public List<Object[]> getCandidateComplainceCntCategoryWise(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId,List<Long> monthyearIds);
 public List<Object[]> getTourSubmitteedCandidates(Date fromDate,Date toDate,List<Long> designationIds,Long candidateId,List<Long> monthyearIds);
 public List<Object[]> getDateWiseTourSubmittedDetails(Date fromDate,Date toDate,Long candidateId,List<Long> monthyearIds);
 public void flushAndclearSession();
 public List<Object[]> getToursDetailsforCadre(Long tdpCadreId, List<String> monthYearStrList, Long designationId);
 public Long checkForExistingTourDetails(Long candidateId, Long tourCategoryId, Long tourTypeId,Long toursMonthId);
 public List<Object[]> getToursOverviewByCadre(Long tdpCadreId, Long tourMonthId);
 public List<Object[]> getProgramVistedLeaderDetails(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> monthYearIds,List<Long> designationIds);
 public List<Object[]> getCandiateComment(Long tdpCadreId);
 public List<Object[]> getUniqueTourSubmittedCandiate(Set<Long> tdpCadreIdSet,List<Long> monthYearIds);
 }
