package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;

public class BoothConstituencyElectionDAOTest extends BaseDaoTestCase{

	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
	public void testFindByTehsil(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByTehsilElectionAndScope("2004", new Long(10), new Long(835));
		System.out.println("Total Booth Constituency Elections:"+ list.size());
	}
	
	public void testFindBoothsForConstituencyElection(){
		List<Booth> list = boothConstituencyElectionDAO.findBoothsByConstituencyElection(new Long(1058));
		assertEquals(256, list.size());
	}
	
	public void testFindByTehsilElectionAndScope(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByTehsilElectionAndScope("2009", new Long(1), new Long(844));
		assertEquals(list.size(), 5);
	}
	
	public void testFindByBoothElectionAndScope(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothElectionAndScope(new Long(4500), "2009", new Long(1));
		assertEquals(list.size(), 5);
	}
	
	public void testFindByBoothAndConstiuencyElection(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection("200", new Long(1063));
		assertEquals(list.size(), 5);
	}
	
	public void testFindByConstituencyAndElection(){
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByConstituencyAndElection("kavali", "2004", new Long(2));
		assertEquals(list.size(), 5);
	}
	
}
