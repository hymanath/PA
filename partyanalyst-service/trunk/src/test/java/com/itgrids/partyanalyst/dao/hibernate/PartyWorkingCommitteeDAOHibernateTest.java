package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDAO;
import com.itgrids.partyanalyst.model.PartyWorkingCommittee;

public class PartyWorkingCommitteeDAOHibernateTest extends BaseDaoTestCase {

	IPartyWorkingCommitteeDAO partyWorkingCommitteeDAO;

	public IPartyWorkingCommitteeDAO getPartyWorkingCommitteeDAO() {
		return partyWorkingCommitteeDAO;
	}

	public void setPartyWorkingCommitteeDAO(
			IPartyWorkingCommitteeDAO partyWorkingCommitteeDAO) {
		this.partyWorkingCommitteeDAO = partyWorkingCommitteeDAO;
	} 
	
	public void testGetWorkingCommitteeForParty()
	{
		List<PartyWorkingCommittee> result=partyWorkingCommitteeDAO.getWorkingCommitteeForParty(15l);
			
		for(PartyWorkingCommittee partyWorkingCommittee: result)
		{
			System.out.println("ID:"+partyWorkingCommittee.getPartyWorkingCommitteeId());
			System.out.println("Name:"+partyWorkingCommittee.getCommitteeName());			
		}
	}
	
}
