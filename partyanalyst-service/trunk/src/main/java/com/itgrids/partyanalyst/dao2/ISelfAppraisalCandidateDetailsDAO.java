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
	public List<Object[]> getCommendAndFilePathDtls(List<Long> cndIdListForCmtAndFile, Date fromDate, Date toDate);
	public List<Object[]> getSubmittedToursDetails(Date startDate, Date endDate, List<Long> CandidateIds);
	public List<Object[]> getCndWiseAndLocValWiseCountListForIncharge(Date fromDate, Date toDate);
	public Long geTtotalUniqueTour(List<Long> candidateIds,Date fromDate,Date toDate);
	public List<Object[]> getOwnToursCntDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,Long userTypeId);
	public List<Object[]> getInchargeToursCntDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,Long userTypeId);
	public List<Object[]> getToursSubmittedAndNoOfToursCntDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,Long userTypeId);
	
	public List<Object[]> getToursSubmittedCandidateCntAndNoOfToursDistrictWiseBsdOnUserAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType,Long userTypeId);
	public List<Object[]> getInchargeToursCntDistrictWiseBsdOnUsrAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType,Long userTypeId);
	public List<Object[]> getOwnToursCntDistrictWiseBsdOnUserAccssLvl(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType,Long userTypeId);
	public List<Object[]> getToursSubmittedCnddteAndNoOfToursCntByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getOwnToursCntByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getInchargeToursCntByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getOwnTourSubmittedCandiatesDesignationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> designationIds);
	public List<Object[]> getInchargeTourSubmittedCandiatesDesignationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> designationIds);
	public List<Object[]> getTourSubmittedCandidateDetailsByCandiateIds(List<Long> candiateIds,Date fromDate,Date toDate);
	
}