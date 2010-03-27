package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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

	/*public void testFindTownshipWiseBoothDetailsForTehsil(){
		List list = villageBoothElectionDAO.findTownshipWiseBoothDetailsForTehsil(835l, 1l);
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]);
	}*/
	
	public void testFindElectionsForElectionType(){
		List list = villageBoothElectionDAO.findElectionsForElectionType(1l);
		System.out.println(list.size());
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
	
	/*public void testSave(){
		Hamlet hamlet = new Hamlet(new Long(1));
		BoothConstituencyElection obj = new BoothConstituencyElection(new Long(1));
		villageBoothElectionDAO.save(new VillageBoothElection(null, obj));
		setComplete();
	}*/
	/*public void testAll(){
		List list = villageBoothElectionDAO.findByTownshipAndBoothConstituencyElection(23232l, 2458l);
		System.out.println(list.size());
	}*/
}
