package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreReport;

public interface ITdpCadreReportDAO extends GenericDao<TdpCadreReport, Long> {
	public List<Object[]> getCadreReportDetails(Long cadreId);
	public List<Object[]> getCadreReportDetailsByCadreList(List<Long> cadreIds);
}
