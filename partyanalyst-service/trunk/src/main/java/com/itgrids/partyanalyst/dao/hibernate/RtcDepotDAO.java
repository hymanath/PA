package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.model.RtcDepot;

public class RtcDepotDAO extends GenericDaoHibernate<RtcDepot, Long> implements IRtcDepotDAO{

	public RtcDepotDAO() {
		super(RtcDepot.class);
		
	}

}
