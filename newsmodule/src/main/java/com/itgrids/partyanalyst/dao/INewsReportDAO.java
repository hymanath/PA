package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsReport;

public interface INewsReportDAO extends GenericDao<NewsReport, Long>{
	public List<Object[]> getNewsReports(Long userId);
}
