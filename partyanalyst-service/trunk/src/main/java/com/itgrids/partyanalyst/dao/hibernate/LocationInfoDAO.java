package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.model.LocationInfo;

public class LocationInfoDAO extends GenericDaoHibernate<LocationInfo, Long> implements ILocationInfoDAO{

	public LocationInfoDAO() {
		super(LocationInfo.class);
	}

}
