package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.VillageBoothElection;

public class VillageBoothElectionDAOHibernateTest extends BaseDaoTestCase{

	private IVillageBoothElectionDAO villageBoothElectionDAO;
	
	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	/*public void testGetAll(){
		List<VillageBoothElection> list = villageBoothElectionDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}*/
	
	/*public void testDelete(){
		villageBoothElectionDAO.remove(new Long(60));
		setComplete();
		assertEquals(true, true);
	}*/
	
	public void testSave(){
		Hamlet hamlet = new Hamlet(new Long(1));
		BoothConstituencyElection obj = new BoothConstituencyElection(new Long(1));
		villageBoothElectionDAO.save(new VillageBoothElection(obj, hamlet, null));
		setComplete();
	}
}
