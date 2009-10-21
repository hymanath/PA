package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.util.MockData;

public class StaticDataServiceTest {

	IElectionDAO electionDAO;
	IElectionScopeDAO electionScopeDAO;
	IPartyDAO partyDAO;
	IDistrictDAO districtDAO;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		electionScopeDAO = EasyMock.createMock(IElectionScopeDAO.class);
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
		districtDAO = EasyMock.createMock(IDistrictDAO.class);
	}
	
	@Test
	public void testGetStates(){
		System.out.println("called testGetStates() method..........");
		StaticDataService service = new StaticDataService();
		 
		List<ElectionScope> electionScopes = MockData.getElectionScopes(new Long(1));
		
		service.setElectionScopeDAO(electionScopeDAO);
		
		EasyMock.expect(electionScopeDAO.findByPropertyElectionTypeId(new Long(1))).andReturn(electionScopes);
		EasyMock.replay(electionScopeDAO);
		List<SelectOptionVO> s = service.getStates(new Long(1));
		
		Assert.assertEquals(1, s.size());
	}
	
	@Test
	public void testGetElectionYears() {
		System.out.println("called testElectionYears() method..........");
		StaticDataService service = new StaticDataService();
		
		Set<String> expectedYears = new HashSet<String>();
		expectedYears.add("2004");
		expectedYears.add("2009");
		
		List<Election> elections = MockData.getElections();
		
		service.setElectionDAO(electionDAO);
		EasyMock.expect(electionDAO.findByPropertyTypeId(new Long(2))).andReturn(elections);
		EasyMock.replay(electionDAO);
		
		Set<String> actualYears = service.getElectionYears(new Long(2));
		
		Assert.assertEquals(expectedYears, actualYears);
	}
	
	@Test
	public void testGetDistrictsForStateId(){
		System.out.println("called testGetDistrictsForStateId() method..........");
		StaticDataService service = new StaticDataService();
		
		List<District> districts = MockData.getDistricts(new Long(1));
		
		service.setDistrictDAO(districtDAO);
		EasyMock.expect(districtDAO.findByStateId(new Long(1))).andReturn(districts);
		EasyMock.replay(districtDAO);
		
		List<SelectOptionVO> actual = service.getDistricts(new Long(1));
		Assert.assertEquals(23, actual.size());
		
	}
}
