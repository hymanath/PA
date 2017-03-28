package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHouseSessionDAO;
import com.itgrids.partyanalyst.model.HouseSession;

public class HouseSessionDAO extends GenericDaoHibernate<HouseSession, Long> implements IHouseSessionDAO{

	public HouseSessionDAO() {
		super(HouseSession.class);
		
	}

}
