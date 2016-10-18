package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IFieldMonitoringService {
	
	public List<IdAndNameVO> getVendors(Long stateId);
	public List<IdAndNameVO> getVendorDistricts(Long stateId,Long vendorId);
	public List<IdAndNameVO> getVendorConstituencies(Long vendorId,Long districtId);
	public List<IdAndNameVO> getCadreRegIssueType();
	public ResultStatus saveFieldIssue(final FieldMonitoringIssueVO inputVO);
	public FieldMonitoringVO getDataCollectorsCounts(Long vendorId,String fromDateStr,String toDateStr,String locationType,Long locationVal);
	public FieldMonitoringVO getTabUsersDetailsByVendorAndLocation(Long vendorId,String fromDateStr,String toDateStr,String locationType,Long locationVal);
	public List<IdAndNameVO> getIssueTypeWiseCounts(String fromDateStr,String toDateStr);
	public List<IdAndNameVO> getIssueStatusWiseCounts(String fromDateStr,String toDateStr);
	public List<IdAndNameVO> getConstituencyByVendor(Long fieldVendorId);
	public List<FieldMonitoringIssueVO> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long issueStatusId);
	public FieldMonitoringVO getOverAllDataCollectorsDetails(String fromDateStr,String toDateStr);
}
