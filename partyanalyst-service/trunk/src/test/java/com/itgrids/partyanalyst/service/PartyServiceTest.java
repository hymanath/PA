package com.itgrids.partyanalyst.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionDisplayVO;
import com.itgrids.partyanalyst.dto.PartyPostionInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.impl.PartyService;
import com.itgrids.partyanalyst.service.IPartyService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.MockData;
public class PartyServiceTest {

	IConstituencyDAO constituencyDAO;
	IElectionDAO electionDAO;
	IStateDAO stateDAO;
	IPartyDAO partyDAO;
	IConstituencyElectionResultDAO constituencyElectionResultDAO;
	IStaticDataService staticDataService;
	List<Party> allianceParties;
	Party selectedParty;
	PartyPerformanceReportVO presentYearPartyPerformanceReportVO;
	PartyPerformanceReportVO previousYearPartyPerformanceReportVO;
	IPartyService partyService;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		stateDAO = EasyMock.createMock(IStateDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
		constituencyElectionResultDAO = EasyMock.createMock(IConstituencyElectionResultDAO.class);
		staticDataService = EasyMock.createMock(IStaticDataService.class);
		allianceParties = MockData.getAllianceParties();
		selectedParty = MockData.getParties().get(14);
		presentYearPartyPerformanceReportVO = new PartyPerformanceReportVO();
		previousYearPartyPerformanceReportVO = new PartyPerformanceReportVO();
		partyService =  EasyMock.createMock(IPartyService.class);
	}
	
	@Test
	public void testGetPartyPerformanceReport(){
		System.out.println("called testGetPartyPerformanceReport() method..........");
		PartyService service = new PartyService();
		boolean hasAlliances = true;
		List<Constituency> constituencies = MockData.getConstituencies();
		List<Election> elections = MockData.getElections();
		State state = MockData.getStates().get(0);
		List<ConstituencyElectionResult> elecResults = MockData.getConstituencyElectionResults();
		Long electionType = new Long(2); 
		String year = "2009";
		String prevYear = "2004";
	    boolean includeAllianceParties= true;
		
		Party expParty = MockData.getParties().get(7);
		
		EasyMock.expect(partyDAO.get(new Long(11))).andReturn(expParty);
		EasyMock.replay(partyDAO);
		service.setPartyDAO(partyDAO);
		
		EasyMock.expect(stateDAO.get(new Long(1))).andReturn(state);
		EasyMock.replay(stateDAO);
		service.setStateDAO(stateDAO);
		
		EasyMock.expect(electionDAO.findPreviousElectionYear(year, electionType, state.getStateId(), new Long(1))).andReturn(prevYear);
		EasyMock.replay(electionDAO);
		service.setElectionDAO(electionDAO);
		
		EasyMock.expect(constituencyElectionResultDAO.findByElectionTypeId_Year_StateId(electionType, year, new Long(1))).andReturn(elecResults);
		EasyMock.expect(constituencyElectionResultDAO.findByElectionTypeId_Year_StateId(electionType, prevYear, new Long(1))).andReturn(elecResults);
		EasyMock.replay(constituencyElectionResultDAO);
		service.setConstituencyElectionResultDAO(constituencyElectionResultDAO);
				
		if(hasAlliances) {
			EasyMock.expect(staticDataService.getAllianceParties(year, new Long(2), new Long(11))).andReturn(allianceParties);
			EasyMock.replay(staticDataService);
			service.setStaticDataService(staticDataService);
		} 
		
		PartyPerformanceReportVO presentYearPartyPerformanceReportVO = new PartyPerformanceReportVO();
		presentYearPartyPerformanceReportVO.setAllianceParties(allianceParties);
//		presentYearPartyPerformanceReportVO.setPartyWinners(partyWinners)
	//	EasyMock.expect(partyService.getElectionData(year, electionType, includeAllianceParties, selectedParty, false)).andReturn(//)
		
		//PartyPerformanceReportVO previousYearPartyPerformanceReportVO = service.getElectionData(prevYear, electionType, includeAllianceParties, selectedParty, true);
				
		List<ConstituencyPositionDetailVO> presentPartyWinners = presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> presentPartyLoosers = presentYearPartyPerformanceReportVO.getPartyLosers();				
		
		
		
		
		/*PartyPerformanceReportVO valueObject = service.getPartyPerformanceReport("1",
				"Indian National Congress", "2009", "2", 0, new BigDecimal(35),
				new BigDecimal(5));*/ 
		
		PartyPerformanceReportVO valueObject = service.getPartyPerformanceReport("1", "1", "14", "2009", "2","1", 0, new BigDecimal(35),
				new BigDecimal(5), hasAlliances,"1"); 
		Assert.assertNotNull(valueObject);
		Assert.assertNotNull(valueObject.getTotalPercentageOfVotesWon());
		Assert.assertNotNull(valueObject.getTotalPercentageOfVotesWonPreviousElection());
		Assert.assertNotNull(valueObject.getYear());
		Assert.assertNotNull(valueObject.getTotalSeatsWon()); 
		
		System.out.println("TotalPercentageOfVotesWon="+valueObject.getTotalPercentageOfVotesWon());
		System.out.println("TotalPercentageOfVotesWonPreviousElection="+valueObject.getTotalPercentageOfVotesWonPreviousElection());
		System.out.println("Year="+valueObject.getYear());
		System.out.println("TotalSeatsWon="+valueObject.getTotalSeatsWon()); 
		
		
		EasyMock.verify(constituencyElectionResultDAO);
		EasyMock.verify(electionDAO);
		EasyMock.verify(stateDAO);
		//EasyMock.verify(partyDAO);
		
	}
	

	@Test
	public void testPartyVotesFlow(){
		Map<String, BigDecimal> present = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> previous = new HashMap<String, BigDecimal>();
		present.put("Party1", new BigDecimal(12));
		present.put("Party2", new BigDecimal(30));
		present.put("Party3", new BigDecimal(10));
		present.put("Party4", new BigDecimal(15));
		present.put("Party5", new BigDecimal(5));
		present.put("Party6", new BigDecimal(5));
		present.put("Party7", new BigDecimal(4));
		present.put("Party8", new BigDecimal(6));
		present.put("Party9", new BigDecimal(13));

		previous.put("Party1", new BigDecimal(30));
		//previous.put("Party2", new BigDecimal(15));
		previous.put("Party3", new BigDecimal(10));
		previous.put("Party4", new BigDecimal(7));
		previous.put("Party5", new BigDecimal(8));
		previous.put("Party6", new BigDecimal(5));
		previous.put("Party7", new BigDecimal(3));
		previous.put("Party8", new BigDecimal(12));
		previous.put("Party9", new BigDecimal(10));
		

		PartyService service = new PartyService();
		Map<String,String> result = service.partyVotesFlow(present,previous, true,null,null);
	/*	Set set = result.entrySet();
		java.util.Iterator itr = set.iterator();
		System.out.println("********************************************************************************************");
		while(itr.hasNext()){
			Map.Entry entry = (Map.Entry)itr.next();
			System.out.println("key="+entry.getKey()+" value="+entry.getValue());
		}*/
		Assert.assertEquals(4, result.size());
		
	}
	

	
	
	
	@Test
	public void testGetPartyConstituenciesData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		List<ConstituencyElectionResult> elecResults = MockData.getConstituencyElectionResults();
		List electionResults = null;
		
		service.getPartyConstituenciesData(electionResults, selectedParty, true, presentYearPartyPerformanceReportVO);
		
		List<ConstituencyPositionDetailVO> listWinner = presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> listLooser = presentYearPartyPerformanceReportVO.getPartyLosers();
		Assert.assertNotNull(listWinner);
		Assert.assertNotNull(listLooser);
		Assert.assertSame(0, listWinner.size()+listLooser.size());
		System.out.println("Winner Size="+listWinner.size());
		System.out.println("Looser Size="+listLooser.size());
		
	}

	@Test
	public void testgetListLostByDroppingVotes(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		List<ConstituencyElectionResult> elecResults = MockData.getConstituencyElectionResults();
		List electionResults = null;
		
		service.getPartyConstituenciesData(
				electionResults, selectedParty, true, presentYearPartyPerformanceReportVO);
		List<ConstituencyPositionDetailVO> presentWinners =  presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> presentLoosers = presentYearPartyPerformanceReportVO.getPartyLosers();

		service.getPartyConstituenciesData(
				electionResults, selectedParty, true, previousYearPartyPerformanceReportVO);
		List<ConstituencyPositionDetailVO> previousWinners = previousYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> previousLoosers = previousYearPartyPerformanceReportVO.getPartyLosers();
		
		List<ConstituencyPositionDetailVO> previousData = new ArrayList<ConstituencyPositionDetailVO>();
		previousData.addAll(previousWinners);
		previousData.addAll(previousLoosers);
		
		List<ConstituencyPositionDetailVO> list = service.getListLostByDroppingVotes(presentLoosers, previousData);
		
		Assert.assertTrue(0==list.size());
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testSwingDifferenceWinData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		List<ConstituencyElectionResult> elecResults = MockData.getConstituencyElectionResults();
		List electionResults = null;
		/*Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		*/
		Map<String, BigDecimal> presentElectionPartyVotePerc = new HashMap<String, BigDecimal>();
 		Map<String, BigDecimal> previousElectionPartyVotePerc = new HashMap<String, BigDecimal>();
 		
 		BigDecimal presentElectionTotalPercentageOfVotesWon = new BigDecimal(0);
		BigDecimal prevElectionTotalPercentageOfVotesWon = new BigDecimal(0);
		
		service.getPartyConstituenciesData(
				electionResults, selectedParty, true, presentYearPartyPerformanceReportVO);
		List<ConstituencyPositionDetailVO> presentWinners = presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> presentLoosers = presentYearPartyPerformanceReportVO.getPartyLosers();

		service.getPartyConstituenciesData(
				electionResults, selectedParty, true, previousYearPartyPerformanceReportVO);
		List<ConstituencyPositionDetailVO> previousWinners = previousYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> previousLoosers = previousYearPartyPerformanceReportVO.getPartyLosers();
		
		List<ConstituencyPositionDetailVO> previousData = new ArrayList<ConstituencyPositionDetailVO>();
		previousData.addAll(previousWinners);
		previousData.addAll(previousLoosers);
		
		Map<String, List<ConstituencyPositionDetailVO>> swingDataWin = service
				.swingDifferenceWinData(presentWinners, previousData);
		List<ConstituencyPositionDetailVO> positiveSwingWin = swingDataWin.get("POSITIVE_SWING");
		List<ConstituencyPositionDetailVO> negativeSwingWin = swingDataWin.get("NEGATIVE_SWING"); 

		Map<String, List<ConstituencyPositionDetailVO>> swingDataLost = service
				.swingDifferenceWinData(presentLoosers, previousData);
		List<ConstituencyPositionDetailVO> positiveSwingLost = swingDataLost.get("POSITIVE_SWING");
		List<ConstituencyPositionDetailVO> negativeSwingLost = swingDataLost.get("NEGATIVE_SWING");
		
		Assert.assertFalse(presentWinners.size() < positiveSwingWin.size()+negativeSwingWin.size());
		Assert.assertFalse(presentLoosers.size() < positiveSwingLost.size()+negativeSwingLost.size());
	}
	
	
	@Test
	public void testMarginDifferenceWinData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		List<ConstituencyElectionResult> elecResults = MockData.getConstituencyElectionResults();
		List electionResults = null;
		
		service.getPartyConstituenciesData(
				electionResults, selectedParty, true, presentYearPartyPerformanceReportVO);
		List<ConstituencyPositionDetailVO> listWinner = presentYearPartyPerformanceReportVO.getPartyWinners();
		List<ConstituencyPositionDetailVO> listLooser = presentYearPartyPerformanceReportVO.getPartyLosers();
		Map<String, List<ConstituencyPositionDetailVO>> marginDiffDataWin = service
				.marginDifferenceWinData(listWinner, 1, 5);
		List<ConstituencyPositionDetailVO> majorDiffWin = marginDiffDataWin.get("MAJOR_DIFF");
		List<ConstituencyPositionDetailVO> minorDiffWin = marginDiffDataWin.get("MINOR_DIFF"); 

		Map<String, List<ConstituencyPositionDetailVO>> marginDiffDataLost = service
				.marginDifferenceWinData(listLooser, 1, 5);
		List<ConstituencyPositionDetailVO> majorDiffLost = marginDiffDataLost.get("MAJOR_DIFF");
		List<ConstituencyPositionDetailVO> minorDiffLost = marginDiffDataLost.get("MINOR_DIFF");
		
		Assert.assertFalse(listWinner.size() < minorDiffWin.size()+majorDiffWin.size());
		Assert.assertFalse(listLooser.size() < minorDiffLost.size()+majorDiffLost.size());
	}
	
	@Test
	public void testPreviousYear(){//String electionTypeID, String stateID){
		PartyService service = new PartyService();
		List<Election> elections = MockData.getElections();
		EasyMock.expect(electionDAO.findPreviousElectionYear("2009", new Long(2), new Long(1), new Long(1))).andReturn("2004");
		EasyMock.replay(electionDAO);
		service.setElectionDAO(electionDAO);
		String prevYear = electionDAO.findPreviousElectionYear("2009", new Long(2), new Long(1), new Long(1));
		Assert.assertEquals("2004", prevYear);
		
	}
	/*
	@After
	public void destroy(){
		System.out.println("called destroy method..........");
		//TransactionSynchronizationManager.unbindResource(constituencyDAO.getConstituencySessionFactory());
		//SessionFactoryUtils.releaseSession(constituencyDAO.getConstituencySession(), constituencyDAO.getConstituencySessionFactory());

	}
	*/
	
	@Test
	public void testPartyParticipatedNomination(){
		Set<Nomination> nominations = MockData.getNominationList();
		PartyService service = new PartyService();
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		SelectOptionVO obj = new SelectOptionVO(); obj.setId(13L);
		parties.add(obj);
		Nomination nomination = service.partyParticipatedNomination(nominations, parties, 3,13L,true);
		Assert.assertNull(nomination);
		SelectOptionVO obj1 = new SelectOptionVO(); obj1.setId(3L);
		parties.add(obj1);
		nomination = service.partyParticipatedNomination(nominations, parties, 3,13L,true);
		Assert.assertNotNull(nomination);
	}

	@Test
	public void testGetAlliancePartiesAsVOWithAlliances(){
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		parties.add(new SelectOptionVO(1L,"INC"));
		parties.add(new SelectOptionVO(2L,"TRS"));
		parties.add(new SelectOptionVO(2L,"PRP"));
		EasyMock.expect(staticDataService.getAlliancePartiesAsVO("2009", 1L, 1L)).andReturn(parties);
		EasyMock.replay(staticDataService);
		PartyService service = new PartyService();
		service.setStaticDataService(staticDataService);
		List<SelectOptionVO> actualResult = service.getAlliancePartiesAsVO(2009L, 1L, 1L, true);
		Assert.assertTrue(actualResult.size()>1);
	}

	@Test
	public void testGetAlliancePartiesAsVOWithOutAlliances(){
		Party party = new Party(1L);
		party.setShortName("PRP");
		EasyMock.expect(partyDAO.get(1L)).andReturn(party);
		EasyMock.replay(partyDAO);
		PartyService service = new PartyService();
		service.setPartyDAO(partyDAO);
		List<SelectOptionVO> actualResult = service.getAlliancePartiesAsVO(2009L, 1L, 1L, false);
		Assert.assertEquals(1, actualResult.size());
	}

	@Test
	public void testGetPartyPositionDisplayVO(){
		ConstituencyElection ce = MockData.createConstituencyElection();
		PartyService service = new PartyService();
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		SelectOptionVO obj1 = new SelectOptionVO(); obj1.setId(1L);obj1.setName("INC");
		SelectOptionVO obj2 = new SelectOptionVO(); obj2.setId(5L);obj1.setName("MIM");
		parties.add(obj1);parties.add(obj2);
		PartyPositionDisplayVO voObject = service.getPartyPositionDisplayVO(ce, parties, 5L, 5,true);
		//Assert.assertNotNull(voObject);
		Assert.assertEquals(4, voObject.getOppPartyPositionInfoList().size());
	}
	
	@Test
	public void testGetNthPositionPartyDetails(){
		Election election = new Election(1L);
		List<Election> elections = new ArrayList<Election>();
		election.setElectionYear("2009");
		elections.add(election);
		EasyMock.expect(electionDAO.findByPropertyTypeId_CountryId_StateId(1L,1L,1L)).andReturn(elections);
		EasyMock.replay(electionDAO);
		
		List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		SelectOptionVO obj1 = new SelectOptionVO(); obj1.setId(1L);obj1.setName("INC");
		SelectOptionVO obj2 = new SelectOptionVO(); obj2.setId(5L);obj1.setName("MIM");
		parties.add(obj1);parties.add(obj2);
		EasyMock.expect(staticDataService.getAlliancePartiesAsVO("2009",1L,1L)).andReturn(parties);
		ConstituencyElection ce = MockData.createConstituencyElection();
		List<ConstituencyElection> ceList = new ArrayList<ConstituencyElection>();
		ceList.add(ce);
		
		EasyMock.expect(staticDataService.getConstituencyElections(1L,null,null)).andReturn(ceList);
		EasyMock.replay(staticDataService);
		PartyService service = new PartyService();
		service.setElectionDAO(electionDAO);
		service.setStaticDataService(staticDataService);
		
		List<PartyPositionDisplayVO> actualResult = service.getNthPositionPartyDetails(1L, 1L, null, 2009L, 1L, true, 5,"3");
		Assert.assertEquals(1, actualResult.size());
		
		
	}

}
