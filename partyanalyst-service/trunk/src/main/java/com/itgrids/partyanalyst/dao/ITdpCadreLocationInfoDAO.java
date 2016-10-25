package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;

public interface ITdpCadreLocationInfoDAO extends GenericDao<TdpCadreLocationInfo,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	public List<Object[]> get2014TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId);
	public List<Object[]> get2014TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue);
	public List<Object[]> get2014TotalCadreCountBasedOnUserType(Long locationScopeId,List<Long> locationValue,Long stateId,Long userType);
	public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO);
}
