package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.model.Nomination;

public class NominationDAOHibernateTest extends BaseDaoTestCase {
	
	private INominationDAO nominationDAO;
	
	public void setNominationDAO(INominationDAO nominationDAO){
		this.nominationDAO = nominationDAO;
	}

	public void testFindByConstituencyElection(){
		List<Nomination> actualResult = nominationDAO.findByConstituencyElection(new Long(1));
		for(Nomination nomination: actualResult)
			System.out.println("NominationID=:"+nomination.getNominationId()+" partyID=:"+nomination.getParty().getPartyId());
		Assert.assertEquals(10, actualResult.size());
	}
}
