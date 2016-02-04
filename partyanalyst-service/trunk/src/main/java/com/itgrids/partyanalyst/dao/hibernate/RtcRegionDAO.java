package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRtcRegionDAO;
import com.itgrids.partyanalyst.model.RtcRegion;

public class RtcRegionDAO extends GenericDaoHibernate<RtcRegion, Long> implements IRtcRegionDAO{

	public RtcRegionDAO() {
		super(RtcRegion.class);
		
	}

}
