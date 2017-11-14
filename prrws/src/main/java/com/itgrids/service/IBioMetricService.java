package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.BioMetricDashBoardDtlsVO;

public interface IBioMetricService {

	public BioMetricDashBoardDtlsVO getBioMetricDashboardOverViewDtls(String deptCode);
	public List<BioMetricDashBoardDtlsVO> getEmployeeAttendenceTimePeriodWise(String deptCode,String todayDate);
	public List<BioMetricDashBoardDtlsVO> getDateWiseEmployeeAttendenceDetails(String fromDate, String toDate,String deptCode);
	public List<BioMetricDashBoardDtlsVO> getEmployeeWiseAttendenceCount(String fromDate, String toDate,String deptCode);
	public BioMetricDashBoardDtlsVO getIndividualEmployeeAttendenceDetails(String fromDate, String toDate,String empId,String deptCode);
	public List<BioMetricDashBoardDtlsVO> getEmployeeDetailsByEmployeeType(String deptCode,String employeeType);
	public List<BioMetricDashBoardDtlsVO> getDateWisePresentEmployeeDetails(String deptCode,String employeeType,String fromDate,String toDate);
	public List<BioMetricDashBoardDtlsVO> getTimePeriodWiseEmployeeAttendenceDetails(String deptCode,String timePeriod);
}
