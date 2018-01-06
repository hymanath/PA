package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dto.TabLoginAuthVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursOverviewDtlsvO;

public interface ICoreDashboardToursService {
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public List<List<ToursBasicVO>> getDesigWiseMemberDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId, String level);
	public List<ToursBasicVO> getDistrictWiseToursSubmitedDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public ToursBasicVO getTopPoorToursLocationDetails(Long activityMemberId,Long userTypeId,Long stateId,String fromDateStr,String toDateStr);
	public List<ToursBasicVO> getMemberDtlsForADesignation(List<Long> disigList,Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,String outPutType);
	public List<ToursBasicVO> getDesignationLabelList(Long activityMemberId,Long userTypeId);
	public ToursBasicVO getDesignationDtls(Long activityMemberId, List<Long> desigIdList, String startDateStr, String endDateStr);
	public ToursBasicVO getLeaderAverageToursBasedOnAccessLevel(Long candidateId,Long stateId,String fromDateStr,String toDateStr,Long userTypeId);
	public List<ToursBasicVO> getTourSubmittedLeadersDetails(List<Long> designationIds,String isSubmitted,String fromDateStr,String toDateStr,Long acitvityMemberId,Long stateId,Long userTypeId);
	public List<TabLoginAuthVO> getDetailsByUserName(String userName);
	public List<TabLoginAuthVO> getUpdatedIMEINumberDetails(String imeiNo);
	public String savingTabUserDetails(final Long loginUserId,final String userName,final String imeiNo);
	public String updateUserORIMEIDetails(Long loginAuthId);
	//New Tour Service
	public ToursBasicVO getToursBasicOverviewDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public List<List<ToursBasicVO>> getDesignationWiseMembersDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public List<ToursBasicVO> getDesignationWiseAverageTourPerformanceDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId,List<Long> designationIds,String isFilterApply,String filterType,Double ownDistValue,Double ownCnsttuncyValue,Double ichargeDistrictValue,Double incharegeConstituencyValue,Double govtWorkValue,Double stateTourCategoryValue,Double anganwadiVisitValue,Double ownAreaValue,Double inchargeParliamentValue,Double complainceValue);
	public ToursBasicVO getIndividualPersonTourDetails(String fromDateStr,String toDateStr,Long selfAppraisalCandidateId);
	public List<ToursBasicVO> getTourLeaderDtlsBasedOnSelectionType(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId,List<Long> designationIds,String filterType,Long locationScopeId,Set<Long> locationValueSet,String type);
	//Candidate wise
	public ToursOverviewDtlsvO getCandiateWiseTourSubmittedDetails(Long stateId, String fromDateStr, String toDateStr,Long activityMemberId, Long userTypeId);
	public List<ToursOverviewDtlsvO> getCandaiteDetailsByType(Long stateId, String fromDateStr, String toDateStr,Long activityMemberId, Long userTypeId,String type);
	//individual candidate designation wise tour overview details
	public ToursBasicVO getIndividualCandidateDesignationWiseTourComplainceDetails(String fromDateStr, String toDateStr,final Long selfAppraisalCandidateId, String isCandidate,String filterType);
}
