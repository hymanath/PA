package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CustomReportLocation;

public interface ICustomReportLocationDAO extends GenericDao<CustomReportLocation, Long>{
	public List<Object[]> getLocationDetails(Long programId);
}
