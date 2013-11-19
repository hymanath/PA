package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ReportFiles;

public interface IReportFilesDAO extends GenericDao<ReportFiles, Long>{
   
	public List<Object[]> getStateLvlReportDetails(Long newsReportId,Long userId);
	
	public List<Long> getStateLvlReportFontDetails(Long newsReportId,Long userId);
	
	public List<Object[]> getOtherLvlReportDetails(Long newsReportId,Long userId);
	
	public List<Long> getOtherLvlReportFontDetails(Long newsReportId,Long userId);
}
