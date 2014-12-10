package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IZebraPrintOnlineShipDAO;
import com.itgrids.partyanalyst.model.ZebraPrintOnlineShip;



public class ZebraPrintOnlineShipDAO extends GenericDaoHibernate<ZebraPrintOnlineShip, Long> implements IZebraPrintOnlineShipDAO{

	public ZebraPrintOnlineShipDAO() {
		super(ZebraPrintOnlineShip.class);
	}
}
