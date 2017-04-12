package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;

public interface ICccDashboardService {   
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterList);
	public List<AlertVO> getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,String sortingType,
			List<Long> callCenterList);
	
	public List<GovtDepartmentVO> getDepartmentLevels();
	public List<GovtDepartmentVO> getLocationsBasedOnLevel(Long levelId);
	public List<GovtDepartmentVO> getDepartmentsByAlert(Long alertId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,
			List<Long> callCenterList);
	public List<IdAndNameVO> getNewsPapaerList();
	public List<IdAndNameVO> getChannelList();
	public List<IdAndNameVO> getDeptList(); 
	
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo);
	public List<GovtDepartmentVO> getAssignedOfficersDetailsForAlert(Long alertId);
	public List<AlertVO> getTotalAlertGroupByStatusForOneDept(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId);
	public List<AlertVO> getTotalAlertGroutByDeptThenStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId);
	public List<GovtDepartmentVO> getStatusWiseCommentsTracking(Long alertId);
	public List<AlertVO> getAlertCountLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> govtDepartmentId,Long lvlValue);
	public List<GovtDepartmentVO> getDistrictWiseTotalAlertsForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList,Long userId,List<Long> callCenterList);
	public List<GovtDepartmentVO> getStatusWiseDistrictTotalForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList,Long userId,List<Long> callCenterList);
	
	public List<GovtDepartmentVO> getMandalsForConstituency(Long constituencyId);
	public List<GovtDepartmentVO> getLebsForConstituency(Long constituencyId);
	public List<GovtDepartmentVO> getPanchayatsMandalId(Long mandalId,Long constituencyId);
	public List<GovtDepartmentVO> getInvolvedMembersInAlert(Long alertId);
	
	public List<AlertVO> getStatusWiseAlertDetails(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId, Long statusId);
	public List<GovtDepartmentVO> getAlertStatusForUser(Long userId);
	public List<GovtDepartmentVO> getAssignedDepartmentsForUser(Long userId);
	public List<IdAndNameVO> getGovtDeptLevelForDeptAndUser(Long departmentId,Long userId);
	public List<IdAndNameVO> getDeptIdAndNameListForUser(Long userId);
	public List<GovtDepartmentVO> getLevelsByDeptId(Long departmentId,Long userId);
	
	public List<GovtDepartmentVO> getAssignedDesignationsForUser(Long userId);
	public GovtDepartmentVO getAssignedLevelsForUser(Long userId,Long designationId);
	public List<GovtDepartmentVO> getSubLevelsForUser(Long userId,Long designationId);
	public List<GovtDepartmentVO> getSubOrdinatesAlertsOverView(Long designationId,Long levelId,String startDate,String endDate,Long userId);
	public String updatingAlertInformation(final AlertAssigningVO inputvo);
	public List<AlertVO> getTotalAlertByDeptForOfficer(String fromDateStr, String toDateStr,Long userId);
	public List<AlertVO> getTotalAlertByStatusForOfficer(String fromDateStr, String toDateStr,Long userId);
	
	public List<AlertCoreDashBoardVO> getSubOrdinateLocationWiseAlertDetails(Long designationId,Long levelId,Long levelValue,String fromDateStr,String toDateStr);

	public List<AlertCoreDashBoardVO> getTotalAlertByStatusForDeptWiseClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,String type,Long userId,
			List<Long> callCenterList);
	public List<GovtDepartmentVO> getDesigAndStatusWiseAlertsCounts(Long departmentId,Long stateId,String fromDateStr,String toDateStr,List<Long> printIdsList,List<Long> electronicIdsList,Long userId,List<Long> callCenterList);
	
	public List<AlertCoreDashBoardVO> getDesigAndStatusWiseAlertDetails(Long departmentId,Long stateId,String fromDateStr,String toDateStr,List<Long> printIdsList,
			List<Long> electronicIdsList,Long userId,Long designationId,Long statusId,List<Long> callCenterList);
	public List<AlertCoreDashBoardVO> getTotalAlertDtls(String fromDateStr, String toDateStr,Long userId,Long statusId,Long deptId,String type);
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,List<Long> callCenterList);
	public List<AlertCoreDashBoardVO> getTotalAlertDetailsGroupByDeptThenStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId,Long deptId, Long statusId);
	public List<AlertCoreDashBoardVO> getAlertCountDetailsLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> govtDepartmentId,Long lvlValue,Long locId,Long statusId);
	public List<IdAndNameVO> getDeptListForUser(Long userId);
	
	public String getAlertCategoryByAlert(Long alertId);
	public Long getRegionScopeIdBylevel(Long levelId);
	public List<IdAndNameVO> getChannelListForUser(Long userId);
	public String getDesignationForUser(Long userId);
	public List<AlertCoreDashBoardVO> getDepartmentAndDistrictWiseAlertsCountsAlerts(String startDateStr,String endDateStr,Long stateId,List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList,Long levelId,
			List<Long> regionScopeValues,String type,Long statusId,int startIndex,int endIndex,List<Long> callCenterList);
	
	public List<IdAndNameVO> getSubDesignationOfficersInfo(Long parentOfficerId);
	public List<IdAndNameVO> getSubDesignationsInfo(Long parentOfficerId,Long levelId);
	
} 
