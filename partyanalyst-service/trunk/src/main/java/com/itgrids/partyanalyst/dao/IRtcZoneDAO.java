package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RtcZone;

public interface IRtcZoneDAO extends GenericDao<RtcZone, Long>{

	public List<Object[]> getRtcZoneDetails();
}
