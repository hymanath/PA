package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertInputsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;

public interface IAlertDAO extends GenericDao<Alert, Long> {
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> userTypeIds,Date startDate,Date endDate);
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> userTypeIds,AlertInputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getAlertsData(Long alertId);

	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId,Date fromDate2,Date toDate2,Long involvedCandidateId,Long impactId,List<Long> constIds,Long assignedUserId,String verificationUserType);
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByStatusThenCategory(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByImpactLevel(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByImpactLevelThenStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Alert> getAlertDetailsOfNewstype(Long alertCategoryType,Long alertTypeId);
	public int updateAlertStatusOfNews(Long alertCategoryType,Long alertStatusId);
	public List<Object[]> getLocationIdList(Date fromDate, Date toDate, Long stateId, String Location,Long alertTypeId);
	public List<Object[]> getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(Date fromDate, Date toDate, Long stateId, List<Long> locaionIds, String Location,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByLocation(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertType,List<Long> editionTypes,String filterType,Long locationValue,Long disctrictId,List<Long> alertStatusIds,List<Long> locationValues);
	//CoreDashBoard Alerts
	public List<Object[]> getOverAllAlertDetailsForCoreDashBoard(Date startDate,Date endDate,Long locationLevelId,List<Long> levelValues,List<Long> impactScopeIds);
	
	public List<Object[]> getAlertCntByAlertTypeBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<Object[]> getAlertCntByAlertStatusBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<Object[]> getAlertCntByAlertCategoryBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<Object[]> getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<Object[]> getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,List<Long> scopeIds,String type);
	public List<Object[]> getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,List<Long> scopeIds,String type);
	public List<Object[]> getTotalAlertGroupByPubRepThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step,List<Long> alertTypeList, List<Long> editionList,Long districtId,List<Long> alertStatusIds);
	public List<Object[]> getTotalAlertGroupByDist(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getAlertDtls(Date fromDate, Date toDate, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long alertCategoryId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,List<Long> alertImpactIds);
	
	public List<Object[]> getPublicRepresentativeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds);
	public List<Object[]> getPartyCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<Object[]> getProgramCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds);
	public List<Object[]> getAllAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds);
	
	public int updateCandidateStatusOfAlert(Long alertId,Long userId);
	public List<Object[]> getTotalAlertGroupByCandThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step, Long publicRepresentativeTypeId,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds);
	public List<Object[]> getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpBasicCommiteeIds,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<Object[]> getTdpCommitteeRolesByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpCommitteeLevelIds,Long tdpBasicCommitteeId,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<Object[]> getAlertDtlsForPubRep(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, Long publicRepresentativeTypeId, Long cadreId, List<Long> alertStatusIds,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId);
	public List<Object[]> getStateImpactLevelAlertCnt(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,Long districtId);
	public List<Object[]> getMemForPartyCommitDesg(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long tdpBasicCommitteeId, Long designationId, String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds);
	public List<Object[]> getAlertDtlsAssignedByPartyCommite(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long cadreId, Long tdpBasicCommitteeId, Long designationId,List<Long> alertStatusIds,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> enrollementYearIds);
	public List<Object[]> getAlertDetailsByCadreWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long tdpCadreId,List<Long> alertStatusIds,String resultType,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId);
	public List<Object[]> getDistrictAndStateImpactLevelWiseAlertDtls(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,List<Long> districtIdList,Long catId,List<Long> alertTypeList,List<Long> editionList,Long constituencyId,List<Long> alertStatusIds,String publicationType,Long publicationId,Long localElectionBodyId,String locationLevel,String type,List<Long> constituencyIds);
	public Date getAlertLastUpdatedTime();
	public Long getAlertStatusOfArticle(Long articleId);
	public List<Object[]> getAlertCreatedDate(Long alertCategoryTypeId);
	public int updateAlertStatusOfNewsForDelete(Long alertCategoryTypeId); 
	 
	public Object[] getSourceDtlsByAlertId(Long alertId);
	
	public List<Object[]> getAlertCntByAlertTypeBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getAlertCntByAlertStatusBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getAlertCntByAlertCategoryBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionList);
	public List<Object[]> getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getDistrictAndStateImpactLevelWiseAlertDtlsForOrganization(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long districtId,Long catId, List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getAlertDtlsForOrganization(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList);
	public List<Object[]> getVerificationDetails(Long alertId);
	
	public List<Object[]> getStateImpactLevelAlertCntForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,String groupType,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getTotalAlertGroupByLocationForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getTotalAlertGroupByLocationThenStatusForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getPublicRepresentativeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList);
	public List<Object[]> getPartyCommitteeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList);
	public List<Object[]> getProgramCommitteeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList);
	public List<Object[]> getAllAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList);
	public List<Object[]> getTotalAlertGroupByDistForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList);
	public List<Object[]> getAlertDtlsByAlertTypeId(Date fromDate, Date toDate, Long stateId, Long alertTypeId,Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> impactScopeIds,List<Long> alertStatusIds,List<Long> editionList,String type);
	
	public List<Object[]> getAlertDetailsForUpdate(Long alertId);
	public List<Object[]> getTotalAlertGroupByStatusForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId,List<Long> constIds);
	public List<Object[]> getTotalAlertGroupByStatusThenCategoryForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId,List<Long> constIds);
	public List<Object[]> getLocationLevelWiseAlertsDataForCentralMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,List<Long> consIds);
	public int deleteAlert(Long alertId);

	public List<Object[]> getTotalAlertGroupByStatusForGovt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> callCenterList);
	public List<Object[]> getTotalAlertGroupByStatusThenDepartmentForGovt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList);
	
	public List<Object[]> getDepartmentsByAlertId(Long alertId);
	public List<Object[]> getTotalAlertByStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,Long deptId,
			List<Long> callCenterList);
	public List<Object[]> getNewsPapaerList();
	public Long getAlertTypeByAlertTypeId(Long alertId);
	public int setDepartmentOfAlert(Long organizationId,String isMultiple,Long alertId);
	public Object[] getAlertStatus(Long alertId);
	public List<Object[]> getDistrictWiseTotalAlertsForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds);
	public List<Object[]> getStatusWiseTotalCountsForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds);
	public List<Object[]> getDistWiseTotalAlertsStatusForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds);
	public List<Object[]> getTotalAlertByStatusForDeptWiseClick(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,String type);
	public List<Object[]> getAlertDtls(Set<Long> alertSet);
	public List<Object[]> getDistrictIdAndNameByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionList);
	public Object[] getStateByStateId(Long stateId);
	public List<Object[]> getPublicationWiseAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String publicationType, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue,List<Long> alertStatusIds,Long districtId,List<Long> constituencyList);
	public Long getGovtDepartmentIdForAlert(Long alertId);
	
	public List<Object[]> getChannelListForUser();
	public List<Object[]> getRegionScopesByIds(List<Long> regionScopesIds);
	public List<Object[]> getImpactLevelByAlertCount(AlertInputsVO alertInputsVO,String impactLevelId,String type);
	public List<Object[]> getLocationWiseAssignedAndInvolveAlertCnt(AlertInputsVO alertInputsVO,String resultType,String type);
	public List<Object[]> getLocationAndImapctLevelWiseAssignedAndInvolveAlertCnt(AlertInputsVO alertInputsVO,String resultType);
	public List<Object[]> getStateOrGHMCImpcatLevelAlertCntPublicationWise(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String publicationType, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,Long discrictId);
	public List<Object[]> getTotalGovtPendingStatusAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,String type,List<Long> calCntrIdList,Long regionScopeId,List<Long> regionScopeVaues,List<Long> socailMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public Integer updateAlertPriority(Long alertId,Long priorityId,Long userId,Date date);
	public Object[] getAlertDetailsForSMS(Long alertId);
	public List<Object[]> getNoOFAlertCreatedList(Date startDate, Date endDate,Long userId);
	public List<Object[]> getAlertDetials(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long deptId,Long feedbackStattusId);
	public List<Object[]> getAlertCallerDetails(Long alertId);
	public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,String type);
	public List<Object[]> getTotalAlertByStatusNew(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getStatusCount(Long locationId,String locationType,String searchType,Date startDate,Date endDate);
	public List<Object[]> getCallerUserAlertDtls(Date fromDate, Date toDate, Long userId);
	public List<Object[]> getCallerDetailsForAlerts(List<Long> alertIdsList);
	public List<Object[]> getStatusWiseAlertsCountByDates(Date fromDate ,Date toDate);
	public List<Object[]> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,Date fromDate,Date toDate,Long statusId,Long deptId);
	public List<Object[]> getAlertDetials1(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long departmentId,Long feedbackStattusId,Long categoryId);
	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptId,Long statusId,Date fromDate,Date toDate, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,Long stateId,List<Long> socialMediaTypeIds);
	public List<Object[]> getDayWiseAlertsCounts(Long departmentId,Date fromDate,Date toDate);
	public Long getTotalAlertsByStatusIdsAndDates(Date prevDay,Date today,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds);
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,String step,Long locationId,Long statusId);
	public List<Object[]> getDifferenceTime(Date fromDate ,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds );
	public List<Long> getTotalAlertForGrievance(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,Long locationId,Long statusId);
	public List<Object[]> getAlertDtlsForGrievance(List<Long> alertSet);
	
	public List<Object[]> getDepartmentDetailsOfAlert(Long alertId);
	public List<Object[]> getTotalAlertGroupByBellowLocationThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String step, Long locationId);
	public List<Long> getGrievanceReportDtlsForBellowLocation(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType);
	public List<Object[]> getTotalAlertGroupByDateThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,String step,Long locationId,Long statusId);
	public List<Object[]> getDeptList(); 
	public List<Object[]> getTotalAlertGroupByCategoryThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String step,Long locationId,Long statusId);
	public List<Object[]> getTotalAlertByAlertStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long levelId,List<Long> levelValues,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Long> getPendingAlertCntByAlertCategory(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long alertCategoryId,List<Long> calCntrIdList,Long regionScopeId,List<Long> scopeValues,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getTotalAlertBySubTaskStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getSocialAlertDetials(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long departmentId,Long feedbackStattusId,
    		Long categoryId,Long userId,Long smTypeId);
	 public List<Object[]> getAllSocialMediaType();
	public List<Long> getTotalAlertByPendingStatusNew(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getAlertCallCenterType();
	public List<Object[]> getTotalAlertGroupByStatusForCentralAreaMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,List<Long> constIds);
	public List<Object[]> getTotalAlertGroupByStatusThenCategoryForCentralAreaMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,List<Long> consIds);
	public List<Object[]> getLocationLevelWiseAlertsDataForCentralAreaMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,List<Long> consIds);
	public List<Object[]> getTotalAlertGroupByCategoryThenFeedbackStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String step,Long locationId,Long statusId,String feedBackType,String reopen);
	public List<Object[]> getTotalAlertsDateWise(Date fromDate,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds);
	public List<Object[]> getDateWiseAlert(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,Long locationId);
	public List<Object[]> getAlertBasedOnRequiredParameter(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,List<Long> statusIds,String type,Long locationId);
	public List<Long> getFeedbackAlertIds(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,List<Long> statusIds,String type,Long locationId,Long feedBackStatusId,String level);
	public List<Object[]> getLocationWisefeedbackAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type, Long locationId,List<Long> statusIds);
	public List<Long> getLocationWiseFeebbackAlertIds(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type, Long locationId,List<Long> statusIds,Long feebStatusId,String areaType);
	public List<Object[]> getDifferenceDay(Date fromDate ,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds );
	public List<Object[]> getPendingAlertDtls(Set<Long> alertSet);
	public List<Object[]> getTotalAlertGroupByStatusForGrievancePage(Date fromDate, Date toDate, Long stateId,Long sourceId,Long deptId,String level);
	public List<Object[]> getStateLevalfeedbackAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String type,List<Long> statusIds,String level);
	public List<Object[]> getStateLevalReopenAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String level,String reopenType);
	public List<Object[]> getTotalReopenAlerts(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String reopenType);
	public List<Object[]> getLocationWiseReopenCount(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String reopenType, Long locationId);
	public List<Object[]> getDateWiseReopenAlertDtls(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,String reopenType,Long locationId);
	public List<Long> getReopenCountDtls(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,Long reopenType, Long locationId);
	public List<Long> getTotalAlertGroupByStatusForStateLvlGrievancePage(Date fromDate, Date toDate, Long stateId,Long sourceId,Long deptId,Long statusId,String level);
	public Alert getModal(Long alertId);
	public List<Object[]> getMondayGrievanceTypeList();
	public List<Object[]> getJanmabhoomiTypeList();
	public List<Object[]> getSpecialGrievanceTypeList();
	public List<Object[]> getGeneralGrievanceTypeList();
	public Long getPresentStatusOfAlert(Long alertId);
	public List<Object[]> getStatusWiseLocationAlert(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type,Set<Long> alertStatuIds);
	public List<Object[]> getAlertDetailsOfCategoryByStatusWise(Date fromDate , Date toDate,Long deptId,String year);
	public List<Object[]> getAlertFeedbackStatusDetails(Date fromDate,Date toDate,Long deptId,String year);
	public List<Long> getAlertsOfCategoryByStatusWise(Date fromDate , Date toDate,Long deptId,List<Long> statusIds,int stIndex,int endIndex,String year);
	public List<Long> getAlertsOfFeedbackStatus(Date fromDate,Date toDate,Long deptId,List<Long> statusIds,int stIndex,int endIndex,String year);
	public List<Object[]> getAlertsDataForAms(Long alertId);
	public List<Object[]> getLocationWiseAlertStatusCountsNew(Date fromDate , Date toDate,Long deptId,String year,Long locationTypeId,List<Long> locationValues,
 			Long searchLevelId,List<Long> searchLevelValues);
	public List<Object[]> getAlertImpactLevelWiseDetailsForConstituencyInfo(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type);
	public List<Object[]> getAlertStatusWiseDetailsForConstituencyInfo(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	public List<Object[]> getTotalAlertDetailsCount(Date fromDate,Date toDate,List<Long> locationvalues,List<Long> alertTypeIds,Long locationTypeId,String year);
	public List<Long> getLocationWiseAlertStatusDetailsInfo(Date fromDate , Date toDate,Long deptId,String year,Long locationTypeId,List<Long> locationValues,
	 		List<Long> statusIds,int startIndex,int endIndex);
	public List<Alert> getAlertDetailsOfNewstypeNew(Long alertCategoryType);
	public List<Object[]> getAlertCategaryAndImpactLevelWiseDetailsOverView(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type,
 			List<Long> alertCategeryIdsList,List<Long> statusIdsList,List<Long> impactIdsList,String otherCategory);
}