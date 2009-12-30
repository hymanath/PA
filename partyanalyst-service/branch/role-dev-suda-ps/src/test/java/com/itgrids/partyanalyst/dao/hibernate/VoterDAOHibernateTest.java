package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.model.Voter;

public class VoterDAOHibernateTest extends BaseDaoTestCase{

	private IVoterDAO voterDAO;

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public void testGetAll(){
		List<Voter> voters = voterDAO.getAll();
		assertEquals(1, voters.size());
	}
	
	public void testSave(){
		Voter voter = new Voter(new Long(1));
		voter.setFirstName("Thaniga1");
		voter.setLastName("Mohan1");
		voter.setInsertionDate(null);
		voter = voterDAO.save(voter);
		//setComplete();
		Long id = voter.getVoterId();
		assertEquals(new Long(1), id);
	}
	
	public void testFindByVoterFirstNameLastNameAndVoterIdCardNo(){
		List<Voter> voters = voterDAO.findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo("LAKSHMI", "TAMBI", "ENKATESHWARLU", "", "AP191260270275");
		assertEquals(1, voters.size());
	}
}
