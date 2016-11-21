package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.TabLoginAuthVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface ICoreDashboardToursService {
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public List<List<ToursBasicVO>> getDesigWiseMemberDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId, String level);
	public List<ToursBasicVO> getDistrictWiseToursSubmitedDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
	public ToursBasicVO getTopPoorToursLocationDetails(Long activityMemberId,Long userTypeId,Long stateId,String fromDateStr,String toDateStr);
	public List<ToursBasicVO> getMemberDtlsForADesignation(List<Long> disigList,Long stateId,String fromDateStr,String toDateStr,Long activityMemberId);
	public List<ToursBasicVO> getDesignationLabelList(Long activityMemberId,Long userTypeId);
	public ToursBasicVO getDesignationDtls(Long activityMemberId, List<Long> desigIdList, String startDateStr, String endDateStr);
	public ToursBasicVO getLeaderAverageToursBasedOnAccessLevel(Long candidateId,Long stateId,String fromDateStr,String toDateStr,Long userTypeId);
	public List<ToursBasicVO> getTourSubmittedLeadersDetails(List<Long> designationIds,String isSubmitted,String fromDateStr,String toDateStr,Long acitvityMemberId,Long stateId,Long userTypeId);
	public List<TabLoginAuthVO> getTabLoginDetails(String cadreSurveyUserName);
	public List<TabLoginAuthVO> getTabUserDetails(String imeiNo);
	public String savingTabUserDetails(final Long loginUserId,final String userName,final String imeiNo);
}
