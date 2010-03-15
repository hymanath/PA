/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyWiseResultVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.service.impl.StateElectionResultsService;
import com.itgrids.partyanalyst.util.DummyCandidateResults;

public class StateElectionResultsServiceTest {

	private ICandidateResultObjectsDAO candidateResultObjectsDAO;
	private StateElectionResultsService stateElectionResultsService;
	
	
	@Before
	public void setUp() throws Exception{
		
		System.out.println("  Called Init Method....");
		candidateResultObjectsDAO = EasyMock.createMock(ICandidateResultObjectsDAO.class);
		stateElectionResultsService = new StateElectionResultsService();
		stateElectionResultsService.setCandidateResultObjectsDAO(candidateResultObjectsDAO);
	}
	
	@Test
	public void testGetStateElectionResults(){
		
		System.out.println(" Running Test Case ...");
		
		List<CandidateResult> candidateResults = DummyCandidateResults.getCandidateResults();
		
		  EasyMock.expect(candidateResultObjectsDAO.findCandidateResultObjects(new Long(10))).andReturn(candidateResults);
		  EasyMock.replay(candidateResultObjectsDAO);
		
		  StateElectionResultsVO electionResults = stateElectionResultsService.getStateElectionResults(new Long(10));
		
		     if(electionResults!=null){
		    	 
		    	 Assert.assertEquals("Assembly", electionResults.getElectionType());
		    	 Assert.assertEquals("2009",electionResults.getElectionYear());
		    	 Assert.assertEquals(new Long(10), electionResults.getElectionId());
		    	 
		    	 List<PartyWiseResultVO> partyResults = electionResults.getPartyResultsVO();
		    	 
		    	   for(PartyWiseResultVO Results:partyResults){
		    		   
		    		   Assert.assertEquals(new Long(10), Results.getPartyId());
		    		   Assert.assertEquals("Telangana Rashtra Samithi", Results.getPartyName());
		    		   Assert.assertEquals(1, Results.getTotalSeatsWon());
		    		   
		    	   }
		    }
	}
	
	
}
