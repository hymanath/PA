package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DataMonitoringVerificationVO;
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
	public List<IdAndNameVO> getIssueStatusWiseCounts(String fromDateStr,String toDateStr,String task);
	public List<IdAndNameVO> getConstituencyByVendor(Long fieldVendorId);
	public List<FieldMonitoringIssueVO> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long issueStatusId,Long vendorId,String locationType,Long locationVal);
	public FieldMonitoringVO getOverAllDataCollectorsDetails(String fromDateStr,String toDateStr);
	public ResultStatus updateStatusToACadreRegIssue(final Long cadreRegIssueId,final String description,final Long newStatusId,final Long loginUserId);
	public List<FieldMonitoringIssueVO> trackingRegIssueByRegIssueId(Long cadreRegIssueId);
	public List<FieldMonitoringVO> getStatusWiseIssuesDetails(String fromDateStr,String toDateStr,Long issueTypeId,Long statusTypeId);
	public List<IdAndNameVO> getCadreRegIssueStatusType();
	public List<FieldMonitoringIssueVO> getIssuesCountsForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long vendorId,String locationType,Long locationVal);
	public List<IdAndNameVO> getDistrictWiseIssueTypesCount(String fromDateStr,String toDateStr,Long statusTypeId,List<Long> stateIds);
	public List<DataMonitoringVerificationVO> getLocationWiseDetailedOverViewDetails(String fromDateStr,String toDateStr,String locationType,Long locationVal);
}
