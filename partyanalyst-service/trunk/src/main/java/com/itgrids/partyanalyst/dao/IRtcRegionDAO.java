package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RtcRegion;

public interface IRtcRegionDAO extends GenericDao<RtcRegion, Long>{
	public List<Object[]> getRegionsOfZone(Long zoneId);
	public List<Object[]> getAllRegionsWithZone();
}
