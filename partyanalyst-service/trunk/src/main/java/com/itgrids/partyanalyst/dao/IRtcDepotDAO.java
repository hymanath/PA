package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RtcDepot;

public interface IRtcDepotDAO extends GenericDao<RtcDepot, Long>{
	public List<Object[]> getDepotsOfRegion(Long regionId);
	public List<Object[]> getDepotsOfAllRegions(List<Long> regionIds);
	public Object[] getRegionAndZoneByDepotId(Long depotId);
	public List<Object[]> getAllDepots();
}
