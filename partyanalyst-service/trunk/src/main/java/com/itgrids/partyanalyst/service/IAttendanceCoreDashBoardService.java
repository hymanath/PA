/**
 * All abstract methods related to core dash board attendance goes here.
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

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
}
