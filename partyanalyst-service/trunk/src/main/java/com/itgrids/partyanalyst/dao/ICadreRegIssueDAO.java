package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssue;

public interface ICadreRegIssueDAO extends GenericDao<CadreRegIssue, Long> {
	public List<Object[]> getTabUsersDetailsByVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal);
	public List<Object[]> getLastHourCounts(Long vendorId,Date lastOneHourTime,Date today,String locationType,Long locationVal);
	public List<Object[]> getcadreRegIssuesCounts(Long vendorId,String locationType,Long locationVal,Date startDate,Date endDate);
	public Long getTotalDataCollectorsCountsVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal);
	public Long getActiveDataCollectorsCountsVendorAndLocation(Long vendorId,Date lastOneHourTime,Date today,String locationType,Long locationVal);
	public List<Object[]> getIssueStatusWiseCounts(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getIssueTypeWiseCounts(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long issueStatusId,Long vendorId,String locationType,Long locationVal);
	public List<Object[]> getIssuesCountsForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long vendorId,String locationType,Long locationVal);
	public Long getActiveUsersCount();
	public List<Object[]> getDistrictWiseIssueTypesCount(Date fromDate,Date toDate,Long statusTypeId,Long stateId);
	public List<Object[]> getLocationWiseDetailedOverViewDetails(Date fromDate,Date toDate,String locationType,Long locationVal);
	public List<Object[]> getLocationWiseDataVerifiedCounts(Date fromDate,Date toDate,String locationType,Long locationVal);
	public List<Object[]> getLocationWiseStatusWiseIssuesCounts(Date fromDate,Date toDate,String locationType,Long locationVal);
	
	
	public List<Object[]> getTabUsersDetailsByVendorAndLocationNew(Long cadreRegUserId,Date fromDate,Date toDate,Long constituencyId,Long userId,Long districtId,Long stateId);
	public List<Object[]> getLastHourCountsNew(Long cadreRegUserId,Date lastOneHourTime,Date today,Long constituencyId,Long userId,Long districtId,Long stateId);
	public List<Object[]> getcadreRegIssuesCountsNew(Long cadreRegUserId,Long constituencyId,Long userId,Date startDate,Date endDate,Long districtId,Long stateId);
	public Long getTotalDataCollectorsCountsVendorAndLocationNew(Long cadreRegUserId,Long constituencyId,Long userId,Date fromDate,Date toDate,Long districtId);
	public Long getActiveDataCollectorsCountsVendorAndLocationNew(Long cadreRegUserId,Long constituencyId,Long userId,Date lastOneHourTime,Date today,Long districtId);
	public List<Object[]> getIssuesForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long issueStatusId,Long cadreRegUserId,Long stateId);
	public List<Object[]> getIssuesCountsForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long cadreRegUserId,Long stateId);
	public List<Object[]> getLocationWiseIssuesCounts(Date fromDate,Date toDate,String locationType,Long locationVal);
	public List<Object[]> getTotalTabUsersDetailsByVendorAndLocationNew(Long cadreRegUserId,Date fromDate,Date toDate,Long constituencyId,Long userId,Long districtId,Long stateId);
	public Long getCadreRegUsersId(Long cadreRegUserId);
	public List<Object[]> getTotalCadreSurveyUsersTemplate(Long cadreRegUserId,Long constituencyId,Long userId,Long districtId,Long stateId);
}
