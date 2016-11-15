package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;

public interface ISelfAppraisalCandidateDetailsDAO extends GenericDao<SelfAppraisalCandidateDetails, Long> {
	public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,List<Long> desigId);
	public List<Object[]> getCandidateDtlsList(Date startDate, Date endDate,List<Long> candidateList);
	public List<Object[]> getMemberDtls(Long candidateDtlsId);
	public Long getTourCount(Long candidateId,List<Long> locValLst); 
	public List<Object[]> getCndWiseAndLocValWiseCountListForOwn(Date fromDate,Date toDate);
	public List<Object[]> getToursVisitedDetailsLocationWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String locationType,Long candiateId);
	public List<Object[]> getToursVisitedDetailsByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getCommendAndFilePathDtls(List<Long> cndIdListForCmtAndFile, Date fromDate, Date toDate);
	public List<Object[]> getSubmittedToursDetails(Date startDate, Date endDate, List<Long> desigIdList,Long userAccessLevelId, Set<Long> locationValueSet);
	public List<Object[]> getCndWiseAndLocValWiseCountListForIncharge(Date fromDate, Date toDate);
	public Long geTtotalUniqueTour(List<Long> candidateIds,Date fromDate,Date toDate,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getToursSubmittedLeaderDtlsDesignationWise(List<Long> designationIds,Date fromDate,Date toDate);
	public List<Object[]> getOwnToursCntDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getInchargeToursCntDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getToursSubmittedAndNoOfToursCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	
	public List<Object[]> getToursSubmittedCandidateCntAndNoOfToursDistrictWiseBsdOnUserAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType);
	public List<Object[]> getInchargeToursCntDistrictWiseBsdOnUsrAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType);
	public List<Object[]> getOwnToursCntDistrictWiseBsdOnUserAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType);
	
}
