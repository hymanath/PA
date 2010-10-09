package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothResultDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothResultDAO boothResultDAO;

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}
	
	/*public void testFindByBoothConstituencyElection(){
		List<BoothResult> list = boothResultDAO.findByBoothConstituencyElection(new Long(200));
		assertEquals(1, list.size());
	}
	
	public void testFindByConstituencyAndElection(){
		List<BoothResult> list = boothResultDAO.findByConstituencyAndElection("allur", "2004", new Long(2));
		assertEquals(1, list.size());
	}*/
	
	/*public void testGetAllPolledVotesByElectionsInDistrict(){
		List list = boothResultDAO.getAllPolledVotesByElectionsInDistrict(19l, "parliament");
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]);
	}*/
	
	public void testGetParliamentResultHappenedInAssembly(){
		List list = boothResultDAO.getParliamentResultHappenedInAssembly("Amberpet", 5l, 1l, "2009");
		System.out.println(list.get(0));
	}
	
	/*public void testGetAllPolledVotesForMandalsInAnElection(){
		List list = boothResultDAO.getAllPolledVotesForMandalsInAnElection("373, 835, 841, 1069", "2009", "Assembly");
		System.out.println(list);
		if(list.size() > 0)
			System.out.println(((Object[])list.get(0))[0]+"\t"+((Object[])list.get(0))[1]+"\t"+((Object[])list.get(0))[2]+"\t"+((Object[])list.get(0))[3]);
	}*/
	
	/*public void testGetMandalwiseValidVotesForAMappedConstituency(){
		List list = boothResultDAO.getMandalwiseValidVotesForAMappedConstituency(362l, "2004", IConstants.ASSEMBLY_ELECTION_TYPE);
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/


}
