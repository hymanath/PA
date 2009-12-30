package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionObjectsDAO;
import com.itgrids.partyanalyst.model.Election;

public class ElectionObjectsDAOHibernateTest extends BaseDaoTestCase {

	 private IElectionObjectsDAO electionObjectsDAO;

	public void setElectionObjectsDAO(IElectionObjectsDAO electionObjectsDAO) {
		this.electionObjectsDAO = electionObjectsDAO;
	}
	
	@Test
	public void testFindElectionObjects(){
		List<Election> elections =  electionObjectsDAO.findElections(new Long(1));
		if(elections!=null){
			for(Election election:elections){
				if(election.getElectionId().equals(new Long(1)))
					Assert.assertEquals("2004",election.getElectionYear());
				else if(election.getElectionId().equals(new Long(2)))
					Assert.assertEquals("2009", election.getElectionYear());
				else if(election.getElectionId().equals(new Long(3)))
					Assert.assertEquals("2004",election.getElectionYear());
				else if(election.getElectionId().equals(new Long(4)))
					Assert.assertEquals("2009", election.getElectionYear());
				
			}
			
		}
	}
}
