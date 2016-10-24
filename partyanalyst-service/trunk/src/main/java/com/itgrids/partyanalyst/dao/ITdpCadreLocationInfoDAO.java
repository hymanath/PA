package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;

public interface ITdpCadreLocationInfoDAO extends GenericDao<TdpCadreLocationInfo,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO);
}
