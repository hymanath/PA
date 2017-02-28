package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.AlertClarificationVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ClarificationDetailsCountVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;

public interface IAlertService {
	public List<BasicVO> getCandidatesByName(String candidateName);
	public String createAlert(final AlertVO inputVO,final Long userId, Map<File,String> mapfiles);
	public List<BasicVO> getLocationLevelWiseAlerts(Long userId,String FromDate,String toDate);
	public List<AlertDataVO> getLocationLevelWiseAlertsData(Long userId,AlertInputVO inputVO);
	public String updateAlertStatus(final Long userId,final AlertVO inputVo,final List<String> fileNames);
	public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId);
	public List<BasicVO> getAlertType();
	public List<BasicVO> getAlertSourceForUser(Long userId);
	public List<AlertDataVO> getAlertCandidatesData(Long alertId);
	public ResultStatus saveAlertAssignedUser(AlertVO inputVO,Long userId);
	public List<AlertDataVO> getAlertsData(Long alertId);
	public List<IdNameVO> getMemberTypesList();
	public List<AlertDataVO> getAlertAssignedCandidates(Long alertId);
	public String deleteAlertAssignedCandidates(Long alertId,Long tdpCadreId);
	public List<AlertDataVO> getLocationWiseFilterAlertData(Long userId,LocationVO inputVO,Long assignedCadreId,Long involvedCandidateId,Long impactId);
	public String  setArticleDetailsIntoAlert(ActionableVO actionableVO);
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);     
	public List<AlertVO> getTotalAlertGroupByStatusThenCategory(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getAlertCountGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryLocationWise(String fromDateStr, String toDateStr, Long stateId, String Location,Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId);
	public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId,String filterType,List<Long> districtList);
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,Long alertTypeId, Long editionId);
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId, Long editionIds,String isActionType,Long alertActionTypeId);
	public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType,Long editionType);
	public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType,Long editionType);
	public List<BasicVO> getAlertImpactScope();
	public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,Long publicRepresentativeTypeId,List<Long> commitLvlIdList, String groupAssignType, String position, Long designationId,Long alertTypeId,Long editionTypeId,Long districtId);
	public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionTypeId,Long districtId);
	public String updateCandidateStatusOfAlert(Long alertId,Long userId);
	public List<AlertOverviewVO> getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,String resultType,Long alertTypeId, Long editionTypeId,Long districtId);
	public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertType,Long editionType);
	public List<AlertCoreDashBoardVO> getAlertDtlsForPubRep(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId, Long cadreId, Long statusId,Long alertType,Long editionType,Long districtId);
	public List<AlertVO> getMemForPartyCommitDesg(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,List<Long> commitLvlIdArr,Long commitTypeId,Long designationId,Long alertTypeId,Long editionTypeId,Long districtId);
	public List<AlertCoreDashBoardVO> getAlertDtlsAssignedByPartyCommite(String fromDateStr,String toDateStr,Long stateId,List<Long> scopeIdList,Long activityMemberId,List<Long> commitLvlIdList,Long commitTypeId,Long designationId,Long cadreId,Long statusId,Long alertTypeId,Long editionTypeId,Long districtId);
	public List<AlertCoreDashBoardVO> getAlertDetailsTdpCadreWise(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long tdpCadreId, Long statusId,String resultType,Long alertTypeId,Long editionTypeId,Long districtId);
	public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,List<Long> districtIdList,Long catId,Long alertTypeId, Long editionId,Long constituencyId,Long statusId,String locationLevel,String publication,String publiationId);
    public String getAlertLastUpdatedTime();
    public String getAlertStatusOfArticle(Long articleId);
    public AlertVO getAlertDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long alertTypeId);
    public AlertVO getCandidateAlertDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long alertTypeId,Long categoryId,Long statusId);
   // public List<AlertVO> getAlertClarificationStatus(Long alertId);
    //public AlertVO getAlertClarificationComments(Long alertId);
    public ResultStatus uploadAlertsDocs(final Long userId,final Long alertId,final List<String> fileNamesList);
    public AlertClarificationVO getClarificationDetails(Long alertId);
    public String saveClarificationRequiredStatus(Long userId,String status,Long alertId,String remarks);
    public String removeAlertComment(Long commentId);
    public String removeAlertDocument(Long documentId);
    public List<ClarificationDetailsCountVO> getStatusAndCategoryWiseAlertsCount(Long stateId,String fromDate,String toDate,Long alertTypeId);
    public List<AlertDataVO> getLocationLevelAlertClarificationData(Long userId,AlertInputVO vo);
    public String updateVerificationStatus(final Long alertId ,final String comments,final Long actionTypeStatusId,final Long userId, final Map<File,String> mapFiles);
    public AlertVerificationVO getAlertVerificationDtls(Long alertId);
    public List<AlertDataVO> getAllAlertsWithoutFilter(Long userId,AlertInputVO inputVO);
    public List<KeyValueVO> getDocumentsForAlert(Long alertId);
    public String saveAlertDocument(Long alertId,Long userId,final Map<File,String> documentMap);
    public List<AlertVerificationVO> getAlertTypeActionStatus(Long actionTypeId);
    public List<AlertCoreDashBoardVO> getAlertDetailsByAlertType(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId,Long activityMemberId);
    public AlertVO getAlertDetailsForEdit(Long alertId);
    
    public List<AlertVO> getTotalAlertGroupByStatusForCentralMembers(String fromDateStr, String toDateStr, Long stateId,Long alertTypeId,Long tdpCadreId);
    public List<AlertVO> getTotalAlertGroupByStatusThenCategoryForCentralMembers(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long tdpCadreId);
    public List<AlertDataVO> getAlertAssignedCandidatesForCentralMembers(Long tdpCadreId);
    public List<AlertDataVO> getLocationLevelWiseAlertsDataForCentralMembers(Long userId,AlertInputVO inputVO);
    public List<AlertDataVO> getAllAlertsWithoutFilterForCentralMembers(Long userId,AlertInputVO inputVO);
    public String editAlert(final AlertVO inputVO,final Long userId, final Map<File,String> mapFiles);
    public String deleteAlert(Long alertId);
    public List<AlertVO> getAlertStatusByAlertTypeId(Long alertTypeId,Long alertId);
    public List<StatusTrackingVO> getAlertAssignedCandidate(Long alertId);  
    public List<StatusTrackingVO> getAlertInvolvedCandidate(Long cadreId,Long stateId,Long alertTypeId,String fromDateStr,String toDateStr);
    public List<AlertOverviewVO> getDistrictListByStateId(Long stateId,Long activityMemberId,Long userTypeId,String fromDateStr,String toDateStr,Long alertTypeId,Long editionId);
    public List<AlertOverviewVO> getAlertStatus(Long alertTypeId);
    public List<StatusTrackingVO> getAlertAssignedCandidateForDashBoard(Long alertId,Long stateId,Long alertTypeId,String fromDateStr,String toDateStr);
    public List<AlertOverviewVO> getPublicationWiseAlert(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long alertStatusId,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds);
}
