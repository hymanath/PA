package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReport;

public interface ICustomReportDAO extends GenericDao<CustomReport, Long> {
	public List<Object[]> getTotalExpectedReports(Long customReportProgramId);
	public String getDescriptionForReportId(Long reportId);
	public CustomReport getmodelForCustomreportId(Long reportId) ;
	
}
