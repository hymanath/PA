package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegIssue;

public interface ICadreRegIssueDAO extends GenericDao<CadreRegIssue, Long> {
	public List<Object[]> getTabUsersDetailsByVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal);
	public List<Object[]> getLastHourCounts(Long vendorId,Date currentDate,String locationType,Long locationVal);
	public List<Object[]> getcadreRegIssuesCounts(Long vendorId,String locationType,Long locationVal);
	public Long getTotalDataCollectorsCountsVendorAndLocation(Long vendorId,Date fromDate,Date toDate,String locationType,Long locationVal);
	public Long getActiveDataCollectorsCountsVendorAndLocation(Long vendorId,Date currentDate,String locationType,Long locationVal);
	public List<Object[]> getCadreIssueStatusCount(Date fromDate,Date toDate);
	public List<Object[]> getStatusWiseIssueTypeCount(Date fromDate,Date toDate);
}
