package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreLocationInfoCount;

public interface ITdpCadreLocationInfoCountDAO extends GenericDao<TdpCadreLocationInfoCount, Long>{
	
	public List<Object[]> getAllLocationsTdpCadreCount(Long enrollmentYearId);
}
