package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStateSubRegionDAO;
import com.itgrids.partyanalyst.model.StateSubRegion;

public class StateSubRegionDAO extends GenericDaoHibernate<StateSubRegion, Long>implements IStateSubRegionDAO{

	public StateSubRegionDAO() {
		super(StateSubRegion.class);
	}

}
