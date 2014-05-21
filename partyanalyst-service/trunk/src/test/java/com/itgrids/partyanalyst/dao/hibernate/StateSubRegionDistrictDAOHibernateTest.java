package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;

public class StateSubRegionDistrictDAOHibernateTest extends BaseDaoTestCase{
	private IStateSubRegionDistrictDAO stateSubRegionDistrictDAO;

	public void setStateSubRegionDistrictDAO(
			IStateSubRegionDistrictDAO stateSubRegionDistrictDAO) {
		this.stateSubRegionDistrictDAO = stateSubRegionDistrictDAO;
	}
	
	public void testgetDistrictDetailsBySubRegionIds(){
		List<Long> ids = new ArrayList<Long>();
		ids.add(3L);
		ids.add(4L);
		//List<Object[]> list = stateSubRegionDistrictDAO.getDistrictDetailsBySubRegionIds(2L, ids);
		
		//System.out.println(list.size());
	}
}
