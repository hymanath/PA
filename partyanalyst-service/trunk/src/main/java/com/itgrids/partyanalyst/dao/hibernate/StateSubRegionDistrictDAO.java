package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.model.StateSubRegionDistrict;

public class StateSubRegionDistrictDAO extends GenericDaoHibernate<StateSubRegionDistrict, Long> implements IStateSubRegionDistrictDAO{

	public StateSubRegionDistrictDAO() {
		super(StateSubRegionDistrict.class);
	}

}
