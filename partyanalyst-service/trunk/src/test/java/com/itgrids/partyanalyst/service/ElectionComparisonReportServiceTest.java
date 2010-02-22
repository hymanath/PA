/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 18,2010.
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.service.impl.ElectionComparisonReportService;
import com.itgrids.partyanalyst.util.DummyElectionData;

public class ElectionComparisonReportServiceTest {
	
	private IElectionDAO electionDAO;
	private ElectionComparisonReportService electionComparisonReportService;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("  Called Init Method....");
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		ElectionComparisonReportService electionComparisonReportService = new ElectionComparisonReportService();
	}
	
	@Test
	public void testGetDistrictWiseElectionResultsForAParty(){
		
		System.out.println(" Running Test Case ...");
		Election electionYearOne = DummyElectionData.getElectionForAP2004();
	}
	
	@Test
	public void testGetElectionFromTypeAndYear(){
		
		EasyMock.expect(electionDAO.findByElectionTypeYearAndState(new Long(2),"2004",new Long(1),new Long(1))).andReturn(DummyElectionData.getElectionsList2004());
		EasyMock.replay(electionDAO);
		Election election = null;
		List<Election> elections = electionDAO.findByElectionTypeYearAndState(new Long(2),"2004",new Long(1),new Long(1));
		if(elections != null && elections.size() > 0)
			election = elections.get(0);
		Assert.assertEquals("2004", election.getElectionYear());
		Assert.assertEquals("Andhra Pradesh", election.getElectionScope().getState().getStateName());
	}

}
