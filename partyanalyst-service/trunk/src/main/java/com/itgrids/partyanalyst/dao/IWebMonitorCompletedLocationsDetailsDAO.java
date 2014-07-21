package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.WebMonitorCompletedLocationsDetails;

public interface IWebMonitorCompletedLocationsDetailsDAO extends GenericDao<WebMonitorCompletedLocationsDetails,Long>{
	
	public Long getSurveyWMCompletedCountByConstId(Long scopeId,List<Long> boothIds);
	public List<Long> getPanchayatBoothsByConstituencyId(Long scopeId);

}
