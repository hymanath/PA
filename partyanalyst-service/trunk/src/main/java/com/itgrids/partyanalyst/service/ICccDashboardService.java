package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;

public interface ICccDashboardService {   
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList);
	public List<AlertVO> getTotalAlertGroupByStatusThenDepartment(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList);
	
	public List<GovtDepartmentVO> getDepartmentLevels();
	public List<GovtDepartmentVO> getLocationsBasedOnLevel(Long levelId);
	public List<GovtDepartmentVO> getDepartmentsByAlert(Long alertId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId);
} 
