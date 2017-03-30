package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReportObserver;

public interface ICustomReportObserverDAO extends GenericDao<CustomReportObserver, Long>{
	public List<Object[]> getObserverDetails(Long programId);
	public List<Object[]> getObserversForAReport(Long reportId);
}


