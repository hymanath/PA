package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;

public interface IAlertsNewsPortalService {

	public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType);
	public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType, Long editionTypeId);
	public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long districtId,Long catId, Long alertTypeId, Long editionId);
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId, Long editionId,String isActionType,Long alertActionTypeId);
	
	public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIds,Long alertTypeId, Long editionId);
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId);
	public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId, String group,Long alertTypeId,Long editionId);
	public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIds,Long alertTypeId,Long editionTypeId);
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId,Long alertTypeId, Long editionId);
	
	public List<AlertCommentVO> getDepartmentWiseStatusWiseCounts(String fromDateStr,String toDateStr,Long stateId,String scopeIdList);
	
	public List<AlertDataVO> getAlertsData(Long alertId);
	public List<AlertDataVO> getAlertAssignedCandidates(Long alertId);
	public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId);
	public AlertVerificationVO getAlertVerificationDtls(Long alertId);
}
