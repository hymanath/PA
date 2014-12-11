package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ZebraPrintOnlineShip;


public interface IZebraPrintOnlineShipDAO extends GenericDao<ZebraPrintOnlineShip, Long>{
	public List<Object[]> getCadreShippingAddressDetials(String searchType,Long locationId);
}
