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
	public List<Object[]> getIssueStatusWiseCounts(Date fromDate,Date toDate);
	public List<Object[]> getIssueTypeWiseCounts(Date fromDate,Date toDate);
	public List<Object[]> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long issueStatusId,Long vendorId,String locationType,Long locationVal);
	public List<Object[]> getIssuesCountsForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,Date fromDate,Date toDate,Long vendorId,String locationType,Long locationVal);
	public Long getActiveUsersCount(Date fromDate,Date toDate);
	public List<Object[]> getDistrictWiseIssueTypesCount(Date fromDate,Date toDate,Long statusTypeId,List<Long> stateIds);
}
