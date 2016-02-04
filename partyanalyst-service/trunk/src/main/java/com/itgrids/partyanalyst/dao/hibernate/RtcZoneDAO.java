package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRtcZoneDAO;
import com.itgrids.partyanalyst.model.RtcZone;

public class RtcZoneDAO extends GenericDaoHibernate<RtcZone, Long> implements IRtcZoneDAO{

	public RtcZoneDAO() {
		super(RtcZone.class);
		
	}

}
