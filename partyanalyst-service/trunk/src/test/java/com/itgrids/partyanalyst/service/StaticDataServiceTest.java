package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.util.MockData;
import com.itgrids.partyanalyst.utils.IConstants;

public class StaticDataServiceTest {

	IElectionDAO electionDAO;
	IElectionScopeDAO electionScopeDAO;
	IPartyDAO partyDAO;
	IDistrictDAO districtDAO;
	IElectionAllianceDAO electionAllianceDAO;
	IConstituencyDAO constituencyDAO;
	INominationDAO nominationDAO;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		electionScopeDAO = EasyMock.createMock(IElectionScopeDAO.class);
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
		districtDAO = EasyMock.createMock(IDistrictDAO.class);
		electionAllianceDAO = EasyMock.createMock(IElectionAllianceDAO.class);
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		nominationDAO = EasyMock.createMock(INominationDAO.class);
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
	
	/*@Test
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
		
		List<String> actualYears = service.getElectionYears(new Long(2));
		
		Assert.assertEquals(expectedYears, actualYears);
	}*/
	
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
	
	@Test
	public void testGetAllianceParties(){
			System.out.println("called test...GetAllianceParties");
		StaticDataService service = new StaticDataService();
		service.setElectionAllianceDAO(electionAllianceDAO);
		List<ElectionAlliance> list = new ArrayList<ElectionAlliance>(0);
		EasyMock.expect(electionAllianceDAO.findByElectionYearAndType("2009", new Long(2))).andReturn(list);
		EasyMock.replay(electionAllianceDAO);
		List<Party> allianceParties = service.getAllianceParties("2009", new Long(2), new Long(1),1L);
		Assert.assertEquals(0, allianceParties.size());
	}
	
	@Test
	public void getAllCandidatesDetailsForConstituency(){
		StaticDataService service = new StaticDataService();
		service.setConstituencyDAO(constituencyDAO);
		 List result = MockData.getConstituenciesByConstituencyId(3382l,"2009",IConstants.ASSEMBLY_ELECTION_TYPE);
		 EasyMock.expect(constituencyDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(3382l)).andReturn(result);
		 EasyMock.replay(constituencyDAO);			 
		 ConstituencyElectionResultsVO actual = service.getAllCandidatesDetailsForConstituency(3382l,"2009",IConstants.ASSEMBLY_ELECTION_TYPE);
	}	
}
