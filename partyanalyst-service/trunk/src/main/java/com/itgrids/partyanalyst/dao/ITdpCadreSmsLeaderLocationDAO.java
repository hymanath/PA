package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreSmsLeaderLocation;

public interface ITdpCadreSmsLeaderLocationDAO extends GenericDao<TdpCadreSmsLeaderLocation, Long> {
	
	public List<Object[]> locationWiseRegistrationSMSTracking();

}
