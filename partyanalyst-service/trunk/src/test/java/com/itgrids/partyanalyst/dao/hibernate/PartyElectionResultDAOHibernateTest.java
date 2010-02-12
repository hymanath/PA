/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionResult;

public class PartyElectionResultDAOHibernateTest extends BaseDaoTestCase {

	private IPartyElectionResultDAO partyElectionResultDAO;

	public void setPartyElectionResultDAO(
			IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}
	
	@Test
	public void testFindByElectionAndParty()
	{
		List<PartyElectionResult> partyElectionResults = partyElectionResultDAO.getByElectionAndParty(new Long(1), new Long(34));
		if(partyElectionResults != null && partyElectionResults.size() > 0){
			for(PartyElectionResult partyElecResult:partyElectionResults){
				Assert.assertEquals("170", partyElecResult.getTotalSeatsWon());
				System.out.println("votes percentage ::" + partyElecResult.getVotesPercentage());
			}
		}
		else if(partyElectionResults.equals(null) || partyElectionResults.size() == 0){
			System.out.println("No Results Available.....");
		}
	}
	
}
