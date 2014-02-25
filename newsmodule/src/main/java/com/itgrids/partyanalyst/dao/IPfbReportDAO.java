package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PfbReport;

public interface IPfbReportDAO extends GenericDao<PfbReport, Long>{

	public String getpfbDatils(Long userId,Long pfbId);
	
	public int deletePfdDetails(String key);
	
	public Long getPfdDetails(String key);
}
