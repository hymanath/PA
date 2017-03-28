package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHealthReport;

public interface ITdpCadreHealthReportDAO extends GenericDao<TdpCadreHealthReport, Long>{
	public List<Object[]> getCadreHealthReport(Long tdpCadreId);

}
