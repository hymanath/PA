package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Voter;

public class BoothConstituencyElectionVoterDAOHibernateTest extends BaseDaoTestCase{

	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	/*	public void testGetAll(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.getAll();
		assertEquals(1, list.size());
	}
	
	public void testSave(){
		BoothConstituencyElection boothConstituencyElection = new BoothConstituencyElection(new Long(2124));
		Voter voter = new Voter(new Long(2));
		BoothConstituencyElectionVoter boothConstituencyElectionVoter = new BoothConstituencyElectionVoter(new Long(1));
		boothConstituencyElectionVoter.setBoothConstituencyElection(boothConstituencyElection);
		boothConstituencyElectionVoter.setVoter(voter);
		boothConstituencyElectionVoter = boothConstituencyElectionVoterDAO.save(boothConstituencyElectionVoter);
		setComplete();
		Long id = boothConstituencyElectionVoter.getBoothConstituencyElectionVoterId();
		assertEquals(new Long(1), id);
	}
	
	public void testDelete(){
		boothConstituencyElectionVoterDAO.remove(new Long(3));
		setComplete();
	}*/
	
	public void testFindByBoothConstituencyElectionAndVoter(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElectionAndVoter(new Long(2346), new Long(1));
		assertEquals(1, list.size());	
	}
		
	public void testFindByBoothConstituencyElection(){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(new Long(2346));
		assertEquals(1, list.size());	
	}
	
	public void testFindVotersByHamletAndElectionYear(){
		List<Voter> list = boothConstituencyElectionVoterDAO.findVotersByHamletAndElectiuonYear(new Long(4), "2009");
		assertEquals(1, list.size());
	}
}
