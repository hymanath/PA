package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.model.ElectionAlliance;


public class ElectionAllianceDAOHibernateTest extends BaseDaoTestCase {
	
	private IElectionAllianceDAO electionAllianceDAO;

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO){
		this.electionAllianceDAO = electionAllianceDAO;
	}
	
	//@Test
	public void testFindByElectionYear(){
		List<ElectionAlliance> alliances = electionAllianceDAO.findByElectionYear("2009");
		Assert.assertEquals(4, alliances.size());
	}

	//@Test
	public void testHasAlliances(){
		List<ElectionAlliance> allianceList = electionAllianceDAO.findByElectionYear("2009");
		
	}
}
