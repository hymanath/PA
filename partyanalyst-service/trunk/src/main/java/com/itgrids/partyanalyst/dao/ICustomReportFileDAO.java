package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReportFile;

public interface ICustomReportFileDAO extends GenericDao<CustomReportFile, Long> {
	public List<Object[]> getFileDetails(Long programId);
	public List<Object[]>  getFileForAReport(Long reportId);
	public List<Object[]> getFileDetailsForReportId(Long programId,String type);
	public Integer updateCustomReportFileDetails(Long fileId);
	public List<Long> getSubmittedCustomReports(Long reportId);
}
