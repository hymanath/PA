package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ReportFiles;

public interface IReportFilesDAO extends GenericDao<ReportFiles, Long>{
   
	public List<Object[]> getStateLvlReportDetails(Long newsReportId,Long userId);
	
	public List<Object[]> getStateLvlReportDetailsByKey(Long newsReportId,String key);
	
	public List<Object[]> getOtherLvlReportDetails(Long newsReportId,Long userId);
	
	public List<Object[]> getOtherLvlReportDetailsByKey(Long newsReportId,String key);
	
	public List<Object[]> getFileNewsReports(Long userId,Integer startIndex,Integer maxIndex);
	
    public Integer deleteReportFiles(Long newsReportId);
    public List<Object[]> getNationalAndInterNationalLvlReportDetails(Long newsReportId,Long userId);
    public List<Object[]> getNationalAndInterNationalLvlReportDetailsByKey(Long newsReportId,String key);
	
}
