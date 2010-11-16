/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 9, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.impl.PartyInfluenceService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.util.MockDataForPartyInfluence;

public class PartyInfluenceServiceTest extends BaseDaoTestCase{

	/*private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IStaticDataService staticDataService;
	private PartyInfluenceService  partyInfluenceService;
	
	@Before
	public void setUp() throws Exception{
		
		System.out.println("  Called Init Method....");
		nominationDAO = EasyMock.createMock(INominationDAO.class);
		partyInfluenceService = new PartyInfluenceService();
		partyInfluenceService.setNominationDAO(nominationDAO);
		partyInfluenceService.setStaticDataService(staticDataService);
	}
	
	@Test
	public void testGetNominationsForAPartyForAnElection(){
		
		System.out.println(" Running Test Case ...");
		
		List<Nomination> nominationsForPartyForYearOne = MockDataForPartyInfluence.getNominationsForPartyForYearOne();
		List<Long> partys = new ArrayList<Long>();
		partys.add(new Long(1));
		
		EasyMock.expect(nominationDAO.findByElectionIdAndPartys(new Long(2),partys)).andReturn(nominationsForPartyForYearOne);
		EasyMock.replay(nominationDAO);
		
		List<Nomination> nomination = partyInfluenceService.getNominationsForAPartyForAnElection(new Long(2),new Long(1),"2009",new Long(2),false); 
		
		 if(nomination != null){
			 System.out.println("Successfully Completed DAO");
			 
			 for(Nomination result:nomination){
				 
				 if(result.getNominationId().equals(new Long(1))){
					 Assert.assertEquals("Katam Reddy Vishnuvardhan Reddy", result.getCandidate().getLastname());
					 Assert.assertEquals(new Double(50192.0), result.getCandidateResult().getVotesEarned());
					 Assert.assertEquals("32.45", result.getCandidateResult().getVotesPercengate());
					 
				 }
				     
				 else if(result.getNominationId().equals(new Long(2))){
					 Assert.assertEquals("Polamreddy Srinivasulu Reddy", result.getCandidate().getLastname());
					 Assert.assertEquals(new Double(65768.0	), result.getCandidateResult().getVotesEarned());
					 Assert.assertEquals("38.91", result.getCandidateResult().getVotesPercengate());
				 }
				    
				 else if(result.getNominationId().equals(new Long(3))){
					 Assert.assertEquals("Karunakar Reddy Bhumana", result.getCandidate().getLastname());
					 Assert.assertEquals(new Double(40379.0), result.getCandidateResult().getVotesEarned());
					 Assert.assertEquals("31.64", result.getCandidateResult().getVotesPercengate());
				 }
			 }
		 }
	}*/

	private IPartyInfluenceService partyInfluenceService;

	public IPartyInfluenceService getPartyInfluenceService() {
		return partyInfluenceService;
	}

	public void setPartyInfluenceService(
			IPartyInfluenceService partyInfluenceService) {
		this.partyInfluenceService = partyInfluenceService;
	}
	
	public void testInfluenceReport(){
		Long start = System.currentTimeMillis();
		partyInfluenceService.getPartyInfluenceReportResults(2l, 361l, 661l, "2009", true, 1l);
		Long end = System.currentTimeMillis();
		System.out.println("============= Total Time Taken For Party Influence Report::"+(end-start)/1000);
	}
	
}
