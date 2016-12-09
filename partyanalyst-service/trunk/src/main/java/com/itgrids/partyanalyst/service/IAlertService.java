package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;

public interface IAlertService {
	public List<BasicVO> getCandidatesByName(String candidateName);
	public String createAlert(final AlertVO inputVO,final Long userId);
	public List<BasicVO> getLocationLevelWiseAlerts(Long userId,String FromDate,String toDate);
	public List<AlertDataVO> getLocationLevelWiseAlertsData(Long userId,AlertInputVO inputVO);
	public String updateAlertStatus(final Long userId,final AlertVO inputVo);
	public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId);
	public List<BasicVO> getAlertType();
	public List<BasicVO> getAlertSourceForUser(Long userId);
	public List<AlertDataVO> getAlertCandidatesData(Long alertId);
	public ResultStatus saveAlertAssignedUser(AlertVO inputVO,Long userId);
	public List<AlertDataVO> getAlertsData(Long alertId);
	public List<IdNameVO> getMemberTypesList();
	public List<AlertDataVO> getAlertAssignedCandidates(Long alertId);
	public String deleteAlertAssignedCandidates(Long alertId,Long tdpCadreId);
	public List<StatusTrackingVO> getAlertAssignedCandidate(Long alertId);
	public List<AlertDataVO> getLocationWiseFilterAlertData(Long userId,LocationVO inputVO,Long assignedCadreId);
	public String  setArticleDetailsIntoAlert(ActionableVO actionableVO);
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByStatusThenCategory(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getAlertCountGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryLocationWise(String fromDateStr, String toDateStr, Long stateId, String Location,Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group);
	public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group);
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId);
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId);
	public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr);
	public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr);
	public List<BasicVO> getAlertImpactScope();
	public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId);
	public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds);
	public String updateCandidateStatusOfAlert(Long alertId,Long userId);
	public List<AlertOverviewVO> getOtherTypeAlertCandiateDtls(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds);
}