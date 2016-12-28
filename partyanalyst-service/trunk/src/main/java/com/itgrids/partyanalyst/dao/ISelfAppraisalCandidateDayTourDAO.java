package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDayTour;

public interface ISelfAppraisalCandidateDayTourDAO extends GenericDao<SelfAppraisalCandidateDayTour, Long> {
	public List<Object[]> getToursSubmittedLeaderCntDesignationBy(Date fromDate,Date toDate);
	public List<Object[]> getLeaderComplainceCnt(Date fromDate,Date toDate);
	public List<Object[]> getLeaderComplainceCntCategoryWise(Date fromDate,Date toDate,String type,Long selfAppraisalCandidateid);
	public List<Object[]> getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,Date fromDate,Date toDate,List<Long> designationIds);
	public List<Object[]> getDateWiseTourSubmittedDetails(Date fromDate,Date toDate,Long candidateId);
	public List<Object[]> getCategoryWiseTourSubmittedLeader(Date fromDate,Date toDate,String type);
	 
	 public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,List<Long> desigIds);
	 public List<Object[]> getCandidateWiseTargetCompletedDays(Date fromDate,Date toDate,List<Long> desigIds);
}
