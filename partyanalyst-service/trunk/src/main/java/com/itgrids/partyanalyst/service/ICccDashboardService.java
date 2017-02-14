package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;

public interface ICccDashboardService {   
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList);
	public List<AlertVO> getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList);
	
	public List<GovtDepartmentVO> getDepartmentLevels();
	public List<GovtDepartmentVO> getLocationsBasedOnLevel(Long levelId);
	public List<GovtDepartmentVO> getDepartmentsByAlert(Long alertId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId);
	public List<IdAndNameVO> getNewsPapaerList();
	public List<IdAndNameVO> getChannelList();
	public List<IdAndNameVO> getDeptList(); 
	
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo);
	public List<GovtDepartmentVO> getAssignedOfficersDetailsForAlert(Long alertId);
	public List<AlertVO> getTotalAlertGroupByStatusForOneDept(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId);
	public List<AlertVO> getTotalAlertGroutByDeptThenStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId);
	public List<GovtDepartmentVO> getStatusWiseCommentsTracking(Long alertId);
	public List<AlertVO> getAlertCountLocationWiseThenStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long govtDepartmentId,Long lvlValue);
	public List<GovtDepartmentVO> getDistrictWiseTotalAlertsForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList );
	public List<GovtDepartmentVO> getStatusWiseDistrictTotalForAlert(String startDateStr,String endDateStr,Long stateId,
			 List<Long> deptIdList,List<Long> paperIdList,List<Long> chanelIdList );
	
	public List<GovtDepartmentVO> getMandalsForConstituency(Long constituencyId);
	public List<GovtDepartmentVO> getLebsForConstituency(Long constituencyId);
	public List<GovtDepartmentVO> getPanchayatsMandalId(Long mandalId,Long constituencyId);
	public List<GovtDepartmentVO> getInvolvedMembersInAlert(Long alertId);
	
	public List<AlertVO> getStatusWiseAlertDetails(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, Long userId);
	public List<GovtDepartmentVO> getAlertStatusForUser(Long userId);
	public List<GovtDepartmentVO> getAssignedDepartmentsForUser(Long userId);
	public List<IdAndNameVO> getGovtDeptLevelForDeptAndUser(Long departmentId,Long userId);
	public List<IdAndNameVO> getDeptIdAndNameListForUser(Long userId);
} 
