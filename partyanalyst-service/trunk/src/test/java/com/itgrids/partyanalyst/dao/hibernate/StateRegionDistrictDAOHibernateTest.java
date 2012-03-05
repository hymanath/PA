package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDistrictDAO;
import com.itgrids.partyanalyst.model.StateRegionDistrict;

public class StateRegionDistrictDAOHibernateTest extends BaseDaoTestCase{
	private IStateRegionDistrictDAO stateRegionDistrictDAO;
	
	
	
	public IStateRegionDistrictDAO getStateRegionDistrictDAO() {
		return stateRegionDistrictDAO;
	}



	public void setStateRegionDistrictDAO(
			IStateRegionDistrictDAO stateRegionDistrictDAO) {
		this.stateRegionDistrictDAO = stateRegionDistrictDAO;
	}



	@SuppressWarnings("unchecked")
	public void test(){
		List<Long> list = stateRegionDistrictDAO.getConstituenciesCountByDistrictRegion(7l);	
		for(int i=0;i<list.size();i++)
		System.out.println(list.get(i));
	
	}
}
