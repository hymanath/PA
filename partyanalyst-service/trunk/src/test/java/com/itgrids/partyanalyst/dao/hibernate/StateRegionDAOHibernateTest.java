package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.model.StateRegion;

public class StateRegionDAOHibernateTest extends BaseDaoTestCase{
	
	private IStateRegionDAO stateRegionDAO;

	public IStateRegionDAO getStateRegionDAO() {
		return stateRegionDAO;
	}

	public void setStateRegionDAO(IStateRegionDAO stateRegionDAO) {
		this.stateRegionDAO = stateRegionDAO;
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testgetStateRegionByType(){
		List sg = stateRegionDAO.getStateRegionByType(1l);
		
		for(int i=0;i<sg.size();i++){
			Object[] result = (Object[])sg.get(i);
			System.out.println(result[0]+"\t"+result[1]);
		}
		
		
	}
	
	@Test
	public void testGetTotalRegionsCountInAState(){
		
		Long expValue = 3L;
		Long countValue = stateRegionDAO.getTotalRegionsInAState(1L);
		
		Assert.assertEquals(expValue, countValue);
	}
}
