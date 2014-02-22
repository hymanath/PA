package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHouseHoldVoterDAO;

public class HouseHoldVoterDAOHibernateTest extends BaseDaoTestCase{
	
	private IHouseHoldVoterDAO houseHoldVoterDAO;
	
	public IHouseHoldVoterDAO getHouseHoldVoterDAO() {
		return houseHoldVoterDAO;
	}


	public void test(){
		//System.out.println("hhOptionsDAO");
		
		Long houseHoldId= houseHoldVoterDAO.getHouseHoldIdForVoter(6979790l);
		System.out.println(houseHoldId);
	}

}
