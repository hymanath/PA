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
	public void testFindByElectionYearAndType(){
		List<ElectionAlliance> alliances = electionAllianceDAO.findByElectionYearAndType("2009", new Long(2));
		Assert.assertEquals(6, alliances.size());
	}

	
}
