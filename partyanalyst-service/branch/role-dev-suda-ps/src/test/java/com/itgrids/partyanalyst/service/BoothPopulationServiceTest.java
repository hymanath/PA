package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.BoothPopulationService;

public class BoothPopulationServiceTest {
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IStaticDataService staticDataService;
	
	
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	@Before
	public void init(){
		boothConstituencyElectionDAO = EasyMock.createMock(IBoothConstituencyElectionDAO.class);
		staticDataService = EasyMock.createMock(IStaticDataService.class);
	}
	
	@Test
	public void testGetMandalAllElectionDetails(){
		BoothPopulationService service = new BoothPopulationService();
		Election el1 = new Election();
		el1.setElectionId(1L);
		el1.setElectionYear("2004");
		ElectionScope scope1 = new ElectionScope();
		ElectionType type1 = new ElectionType();
		type1.setElectionTypeId(1L);
		type1.setElectionType("Assembly");
		scope1.setElectionScopeId(1L);
		scope1.setElectionType(type1);
		el1.setElectionScope(scope1);

		Object[] obj1 = {el1, "11000","10000","1000","0"};		

		Election el2 = new Election();
		el2.setElectionId(2L);
		el2.setElectionYear("2009");

		el2.setElectionScope(scope1);
		Object[] obj2 = {el2, "11000","10000","1000","0"};
		
		List dao1DummyData = new ArrayList();
		dao1DummyData.add(obj1);
		dao1DummyData.add(obj2);
		
		EasyMock.expect(boothConstituencyElectionDAO.getAllElectionBoothVotersForMandal(new Long(1))).andReturn(dao1DummyData);
		
		//0-firstName, 1-middlename, 2-lastname, 3-election, 4-votesearned, 5-partyId, 6-shortName

		Object[] object1 = {"Ram","Kumar","Tenali", el1, "3500","1","INC"};
		List dao2DummyData1 = new ArrayList();
		dao2DummyData1.add(object1);
		EasyMock.expect(boothConstituencyElectionDAO.getPartyVotesByMandal(new Long(1),"1",new Long(1))).andReturn(dao2DummyData1);
		
		Object[] object2 = {"Janardhan","Kumar","Reddy", el2, "5000","1","INC"};
		List dao2DummyData2 = new ArrayList();
		dao2DummyData2.add(object2);
		EasyMock.expect(boothConstituencyElectionDAO.getPartyVotesByMandal(new Long(1),"1",new Long(2))).andReturn(dao2DummyData2);
		EasyMock.replay(boothConstituencyElectionDAO);
		service.setBoothConstituencyElectionDAO(boothConstituencyElectionDAO);
		List actual = service.getMandalAllElectionDetails(1L, 1L, false);
		MandalAllElectionDetailsVO actualObj1 = (MandalAllElectionDetailsVO) actual.get(0);
		MandalAllElectionDetailsVO actualObj2 = (MandalAllElectionDetailsVO) actual.get(1);
		System.out.println(actualObj1.getPartyVotesPercentage());
		System.out.println(actualObj2.getPartyVotesPercentage());
		Assert.assertEquals("35.0", actualObj1.getPartyVotesPercentage());
		Assert.assertEquals("50.0", actualObj2.getPartyVotesPercentage());
	}
}
