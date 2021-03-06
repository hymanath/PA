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
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.AlertsSummeryVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ClarificationDetailsCountVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.UserAddress;

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
	public List<AlertDataVO> getLocationWiseFilterAlertData(Long userId,LocationVO inputVO,Long assignedCadreId,Long involvedCandidateId,Long impactId,String verificationUserType);
	public String  setArticleDetailsIntoAlert(ActionableVO actionableVO);
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);     
	public List<AlertVO> getTotalAlertGroupByStatusThenCategory(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getAlertCountGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryLocationWise(String fromDateStr, String toDateStr, Long stateId, String Location,Long alertTypeId);
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId);
	public List<AlertOverviewVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId,String filterType,Long locationValue,List<Long> alertStatusIds,String sortingType,Long disctrictId);
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,Long alertTypeId, Long editionId);
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long alertCategoryId, Long activityMemberId, Long editionIds,String isActionType,Long alertActionTypeId,List<Long> impactScopeIds,Long alertVerificationStatusId);
	public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType,Long editionType,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType,Long editionType,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<BasicVO> getAlertImpactScope();
	public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,Long publicRepresentativeTypeId,List<Long> commitLvlIdList, String groupAssignType, String position, Long designationId,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public String updateCandidateStatusOfAlert(Long alertId,Long userId);
	public List<AlertOverviewVO> getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,String resultType,Long alertTypeId, Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public AlertOverviewVO getStateOrGhmcImpactLevelAlertStatusWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertType,Long editionType,List<Long> statusIds,Long districtId);
	public List<AlertCoreDashBoardVO> getAlertDtlsForPubRep(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId, Long cadreId, List<Long> alertStatusIds,Long alertType,Long editionType,Long districtId);
	public List<AlertVO> getMemForPartyCommitDesg(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,List<Long> commitLvlIdArr,Long commitTypeId,Long designationId,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<AlertCoreDashBoardVO> getAlertDtlsAssignedByPartyCommite(String fromDateStr,String toDateStr,Long stateId,List<Long> scopeIdList,Long activityMemberId,List<Long> commitLvlIdList,Long commitTypeId,Long designationId,Long cadreId,List<Long> alertStatusIds,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> enrollementYearIds);
	public List<AlertCoreDashBoardVO> getAlertDetailsTdpCadreWise(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long tdpCadreId, List<Long> alertStatusIds,String resultType,Long alertTypeId,Long editionTypeId,Long districtId);
	public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,List<Long> districtIdList,Long catId,Long alertTypeId, Long editionId,Long constituencyId,List<Long> alertStatusIds,String locationLevel,String publication,String publiationId,Long localElectionBoydId,String type,Long parliamentId);
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
    public List<ClarificationDetailsCountVO> getStatusAndCategoryWiseAlertsCount(Long stateId,String fromDate,String toDate,Long alertTypeId,Long userId,String verificationUserType);
    public List<AlertDataVO> getLocationLevelAlertClarificationData(Long userId,AlertInputVO vo);
    public String updateVerificationStatus(final Long alertId ,final String comments,final Long actionTypeStatusId,final Long userId,final Long assignedUserId, final Map<File,String> mapFiles);
    public AlertVerificationVO getAlertVerificationDtls(Long alertId);
    public List<AlertDataVO> getAllAlertsWithoutFilter(Long userId,AlertInputVO inputVO);
    public List<KeyValueVO> getDocumentsForAlert(Long alertId);
    public String saveAlertDocument(Long alertId,Long userId,final Map<File,String> documentMap);
    public List<AlertVerificationVO> getAlertTypeActionStatus(Long actionTypeId);
    public List<AlertCoreDashBoardVO> getAlertDetailsByAlertType(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId,Long activityMemberId,List<Long> impactScopeIds,List<Long> alertStatusIds,Long editionId,String type);
    public AlertVO getAlertDetailsForEdit(Long alertId);
    
    public List<AlertVO> getTotalAlertGroupByStatusForCentralMembers(String fromDateStr, String toDateStr, Long stateId,Long alertTypeId,Long tdpCadreId,String searchType);
    public List<AlertVO> getTotalAlertGroupByStatusThenCategoryForCentralMembers(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long tdpCadreId,String searchType);
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
    public List<AlertOverviewVO> getPublicationWiseAlert(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, List<Long> alertStatusIds,Long alertTypeId,Long editionId,String filterType,Long locationValue,String sortingType,Long disctrictId);
    //New call
    public AlertOverviewVO getStateImpactandItsSubLevelAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,String selectionType);
    public AlertOverviewVO getDistrictOrConstituencyImpactandItsSubLevelAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,Long locationValue,String sortingType,String resultType,Long disctrictId,String selectionType);
    public AlertOverviewVO getCorpGMCAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,Long districtId,String selectionType);
    public List<UserTypeVO> getAlertByUserTypeBasedOnAccessLevel(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds);
    public List<AlertOverviewVO> getDirectChildMemberAlertStatusWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds);
    public AlertOverviewVO getStateOrGHMCImpcatLevelAlertCntPublicationWise(Long activityMemberId,String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList,Long alertTypeId,Long editionId, List<Long> alertStatusIds,Long discrictId);
    public List<AlertTrackingVO> getAlertCallerDetailsByMobileNo(Long userId,String startdateStr,String endDateStr,String status,String mobileNo,Long departmentId);
    public List<KeyValueVO> getAlertIssueTypes();
    public List<KeyValueVO> getHamletsForPanchayat(Long panchayatId);
    public List<KeyValueVO> getAlertCallerTypes();
    public String createGrievanceAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles);
    public List<IdNameVO> getPanchayatDetailsByMandalId(Long tehsilId,String type);
    public CallCenterVO getTotalUserLogingDtls(String fromDateStr, String toDateStr);
    public List<IdNameVO> getAllMandalsByDistrictID(Long districtId);
    public List<AlertVO> getAlertDetailsByStatusId(Long alertStatusId,String mobileNo,String fromDateStr,String toDateStr,Long feedbackStattusId,Long categoryId);
    public List<AlertVO> getAlertCallerDetails(Long alertId);
    public String saveAlertStatusDetails(final AlertVO alertvo,final Long userId);
    public List<AlertVO> getFeedbackStatusDetails();
    public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue);
    public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
    public List<KeyValueVO> getAlertIssueSubTypes(Long alertIssueType);
    public List<KeyValueVO> getAlertStatusList(List<Object[]> list);
    public List<KeyValueVO> getStatusCount(Long locationId,String locationType,String searchType,String startDateStr,String endDateStr);
    public AlertCoreDashBoardVO getUserLogingDtls(Long userId, String fromDateStr, String toDateStr);
    public List<AlertsSummeryVO> getStatusWiseAlertsCountSummery(List<Integer> daysLst);
    public List<KeyValueVO> getStatusWiseViewWiseCounts(Long viewType,Long departmentId,Long locationId,String locationType,String searchType,String startDate,String endDate);

    public List<KeyValueVO> getRelatedDepartmentsForIssueType(Long issueTypeId);
    public List<KeyValueVO> getUrbanLocalitiesForMuncipality(Long lebId);
    public List<KeyValueVO> getUrbanBlocksForLocality(Long localityId);
    public List<IdNameVO> getAllLebsByDistrictID(Long districtId);
    public List<AlertsSummeryVO> getAlertEfficiencyList(List<Integer> daysLst, List<Long> departmentIds,List<Long> sourceIds,boolean includeProposal,List<Long> alertstatusIds);
    public List<AlertOverviewVO> getGrievanceReport(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType,Long locationId,Long statusId,String groupType);
    public KeyValueVO getAverageIssuePendingDays(String fromDateStr ,String toDateStr,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds);
    public List<AlertOverviewVO> getGrievanceReportBasedOnLocation(String fromDate,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String group,String pattern,String rangeType);
    public List<AlertCoreDashBoardVO> getGrievanceReportBasedOnLocationAndStatus(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId);
    public List<AlertCoreDashBoardVO> getGrievanceReportDtlsForBellowLocation(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType);
    public List<IdNameVO> getDeptListForGrievance();
    public List<AlertOverviewVO> getGrievanceReportDayWise(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType,Long LocationId,Long stsId,String groupType);
    public List<AlertOverviewVO> getTotalAlertGroupByCategoryThenStatus(String fromDateStr, String toDateStr, Long stateId, Long departmentId,Long sourceId,Long locationId,Long statusId);
    public List<IdNameVO> getDistrictList(String fromDateStr, String toDateStr, Long stateId, Long departmentId,Long sourceId);
    public List<IdNameVO> getAllDepts();
    public String saveSocialAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles);
    public List<AlertsSummeryVO> getAlertEfficiencyList1(List<Integer> daysLst, List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds,String startDate,String endDate);
    public List<AlertTrackingVO> getSocialAlertCallerDetails(Long userId,String startdateStr,String endDateStr,String status,String mobileNo,Long departmentId);
    public List<AlertVO> getSocialAlertDetailsByStatus(Long alertStatusId,String mobileNo,String fromDateStr,String toDateStr,Long feedbackStatusId,
			 Long deptId,Long categoryId,Long userId,Long smTypeId);
    public String changeVeificationStatusDetails(final AlertVO alertvo,final Long userId);
    public List<AlertTrackingVO> getSocialAlertFeedBackDetails(Long userId,String startdateStr,String endDateStr,String mobileNo,Long departmentId);
    public String saveAlertFeedbackStatusDetails(final AlertVO alertvo,final Long userId);
    public List<AlertOverviewVO> getAlertImpactLocationWiseLocationSubTemplate(List<Long> locationIds);
    
    public String updateDuplicateAlertCallerDetails(final GrievanceAlertVO inputVO,final Long userId);
    public List<AlertsSummeryVO> getAlertEfficiencyList2(List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds
			,String startDate,String endDate,int rangeValue);
    public List<IdNameVO> getAllCategoryForLocationWiseGrievance();
    public List<AlertCoreDashBoardVO> getFeedbackAlertDetails(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String type,String level);
    public List<AlertCoreDashBoardVO> getLocationWiseFeebBackAlert(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType,String type);
    public AlertOverviewVO getStateLevelAlertDetails(String fromDateStr, String toDateStr,Long stateId,Long departmentId,Long sourceId,String level);
    public List<AlertCoreDashBoardVO> getReopenCountDtls(String fromDateStr,String toDateStr, Long stateId, Long departmentId,Long sourceId, String groupType,Long reopenType, Long locationId);
    public List<AlertCoreDashBoardVO> getTotalAlertGroupByStatusForStateLvlGrievancePage(String fromDateStr,String toDateStr, Long stateId, Long departmentId,Long sourceId, Long statusId,String level);
    
    public String createMeekosamGrievanceAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles);
    public UserAddress saveUserAddressForMeekosamGrievanceAlert(final GrievanceAlertVO inputVO);
    public KeyValueVO getRegionScopeValuesForUser(Long userId,Long deptId);
    public AlertOverviewVO getAlertCntInRequiredFormatToExportToExcel(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType);
    public ResultStatus getSmsTdpCadreDetails();
    public List<AlertOverviewVO> getTdpCadreEnrollementYearIds();
    public List<AlertOverviewVO> getAlertVerificationUsers(Long verificationUserTypeId);
    public List<KeyValueVO> getIssueCategoryDetailsOfAlertType(Long alertTypeId);
    public List<AlertOverviewVO> getIssueCategoryDetailsOfAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,
			Long alertType, Long editionTypeId,List<Long> impactScopeIds,List<Long> alertStatusIds,String searchType);
    public List<AlertCoreDashBoardVO> getBriefIssueCategoryDetailsOfAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,
			Long alertType, Long editionTypeId,List<Long> impactScopeIds,List<Long> alertStatusIds,Long issueCategoryId);
    
    // IssueCategory Wise Info For CoreDashBoard Alerts(Party)
    public AlertOverviewVO getStateWiseAlertIssueCategoryDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId,
			Long editionTypeId,List<Long> alertStatusIds,Long districtId);
    public List<AlertOverviewVO> getLocationAndIssueCategoryWiseAlerts(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, 
    		Long activityMemberId, String group,Long alertTypeId,Long editionId,String filterType,Long locationValue,
    		List<Long> alertStatusIds,String sortingType,Long disctrictId);
    //clicking
    public List<AlertCoreDashBoardVO> getIssueCategoryWiseLocationAlertDetails(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,
			List<Long> districtIdList,Long alertTypeId, Long editionId,Long constituencyId,List<Long> alertStatusIds,String locationLevel,
			Long localElectionBodyId,String type,Long parliamentId,List<Long> issueCategoryIds);
    
    public ResultStatus updateAlertIssueCategory(Long categoryId,Long categoryTypeId,Long issueCategoryId);
			
}
