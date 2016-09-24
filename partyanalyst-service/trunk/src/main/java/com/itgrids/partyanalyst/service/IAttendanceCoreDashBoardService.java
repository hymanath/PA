/**
 * All abstract methods related to core dash board attendance goes here.
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.HolidayListVO;
import com.itgrids.partyanalyst.dto.IdNameVO;

/**
 * @author Swadhin Lenka
 * Date 09/20/2016
 *
 */
public interface IAttendanceCoreDashBoardService {
	public IdNameVO getAttendanceOverViewForPartyOffice(String fromDate, String toDate);  
	public List<IdNameVO> getAttendanceOverViewForPartyOfficeWise(String fromDate, String toDate);
	public List<IdNameVO> getAttendanceOverViewForPartyOfficeDeptWise(String fromDate, String toDate);
	public List<IdNameVO> getAttendeeDtlsOfficeWiseForDay (String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList);
	public List<IdNameVO> getAttendeeDtlsDeptWiseForDay (String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList, String status);
	public List<Long> getDeptIds();
	public List<List<IdNameVO>> getTopAbsentAndIregular(String fromDateStr, String toDateStr, List<Long> officeIdList, List<Long> deptIdList);
	public IdNameVO getAttendanceCountForMulitDate(Long officeId, Long deptId, String fromDateStr, String toDateStr);
	public IdNameVO getAttendanceCountForMulitDateTimeWise(Long officeId,Long deptId,String fromDate,String toDate);
	public List<IdNameVO> getAttendanceReportTimeToTime(Long officeId,Long  deptId,String  fromDate,String  toDate);
	public List<HolidayListVO> getDateWisePresentAbsentDtls(Long officeId,Long  deptId,String  fromDate,String  toDate);
	public IdNameVO getAttendanceCountForMulitDateForEmp(Long officeId, Long deptId, String fromDateStr, String toDateStr, Long cadreId);
	public IdNameVO getAttendanceCountForMulitDateTimeWiseForEmp(Long officeId,Long deptId,String fromDate,String toDate,Long cadreId);
	public List<HolidayListVO> getDateWisePresentAbsentDtlsForEmployee(Long officeId,Long  deptId,String  fromDate,String  toDate,Long cadreId);  
} 
