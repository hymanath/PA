package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IAlertManagementSystemService {
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId);
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId);
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType);
	public List<AlertVO> getDepartmentStatus();
	public List<AlertVO> getDepartmentScope();
	public List<IdAndNameVO> getDeptListForUser(Long userId);
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate);
	public ResultStatus updateComment(Long alertId,String comment,Long userId);
	public ResultStatus updateAlertPriority(Long alertId,Long priorityId,Long userId);
	public List<IdNameVO> getDepartmentLevels(Long departmentId);
	public List<IdNameVO> getParentLevelsOfLevel(Long departmentId,Long levelId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue);
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo);
	public String assigningSubTaskToOfficer(final AlertAssigningVO inputvo);
	public IdNameVO getGovtSubLevelInfo(Long departmentId,Long LevelId,Long levelValue);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId);
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId);
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long scopeId,String startDateStr,String fromDateStr,String type);
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingtype,Long levelId);
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType);
	public List<AlertVO> getGovtDepartmentDetails();
	public List<AlertVO> getGovtDeptScopeDetails();
	public  List<IdAndNameVO> getSubOrdinateLevels(Long designationId);
	public List<AlertCoreDashBoardVO> groupAlertsTimeWise(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs);
	public List<DistrictOfficeViewAlertVO> getSubOrdinateAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
			List<Long> desigIds,Long priorityId);
	public ResultStatus updateAlertDueDate(Long alertId ,String date,Long userId);
	public ResultStatus updateAlertStatusComment(Long alertId,Long statusId,String comment,Long userId);
	public ResultStatus uploadDocumentsForAlert(final Map<File, String> mapfiles,final Long alertId,final Long userId);
	public List<AlertTrackingVO> viewAlertHistory(Long alertId,Long userId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortType,String order);
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long scopeId,Long deptId,Long locatonLevelId,Long statusId);
	public List<IdNameVO> getDepartmentSubLevels(Long departmentId,Long parentLevelId);
	public List<IdNameVO> getChildLevelValuesForSubTask(Long departmentId,Long parentLevelId,List<Long> parentLevelValues,Long levelId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType);
	public List<IdNameVO> getDeptListForMultiLvl(Long userId);
	public List<AlertCoreDashBoardVO> getDistrictOfficerAlertDetails(List<Long> alertIdList);
	public List<AlertTrackingVO> getAlertStatusHistory(Long alertId);
	public List<GovtDepartmentVO> getAssignedOfficersDetails(Long alertId);
	public List<AlertCoreDashBoardVO> getDistrictOfficerScopesWiseAlerts(String fromDateStr, String toDateStr, Long stateId, Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType);
	public List<AlertVO> getAllDivisionDetails();	
	public List<AlertVO> getAllSubDivisionDetails();
}
