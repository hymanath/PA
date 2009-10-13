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
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.util.MockData;
public class PartyServiceTest {

	IConstituencyDAO constituencyDAO;
	IElectionDAO electionDAO;
	IStateDAO stateDAO;
	IPartyDAO partyDAO;
	
	@Before
	public void init(){
		System.out.println("called init method..........");
		constituencyDAO = EasyMock.createMock(IConstituencyDAO.class);
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		stateDAO = EasyMock.createMock(IStateDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
	}
	
	@Test
	public void testGetPartyPerformanceReport(){
		System.out.println("called testGetPartyPerformanceReport() method..........");
		PartyService service = new PartyService();
		 
		List<Constituency> constituencies = MockData.getConstituencies();
		List<Election> elections = MockData.getElections();
		List<State> states = MockData.getStates();

		/*State state = new State();
		state.setStateId(new Long(1));
		*/
		
		Party party = MockData.getParties().get(10);
		State state = MockData.getState();
		//EasyMock.expect(constituencyDAO.findByProperty("state_id", new Long(1))).andReturn(constituencies);
		EasyMock.expect(constituencyDAO.getAll()).andReturn(constituencies);
		EasyMock.expect(electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(2), new Long(1), new Long(1))).andReturn(elections);
		//EasyMock.expect(stateDAO.getAll()).andReturn(states);
		EasyMock.expect(stateDAO.get(new Long(1))).andReturn(state);
		EasyMock.replay(constituencyDAO);
		service.setConstituencyDAO(constituencyDAO);
		EasyMock.replay(electionDAO);
		service.setElectionDAO(electionDAO);
		EasyMock.replay(stateDAO);
		service.setStateDAO(stateDAO);//Pyramid Party of India
		EasyMock.expect(partyDAO.get(new Long(14))).andReturn(party);
		EasyMock.replay(partyDAO);
		service.setPartyDAO(partyDAO);
		
		/*PartyPerformanceReportVO valueObject = service.getPartyPerformanceReport("1",
				"Indian National Congress", "2009", "2", 0, new BigDecimal(35),
				new BigDecimal(5));*/ 
		PartyPerformanceReportVO valueObject = service.getPartyPerformanceReport("1",
				"14", "2009", "2","1", 0, new BigDecimal(35),
				new BigDecimal(5)); 
		Assert.assertNotNull(valueObject);
		Assert.assertNotNull(valueObject.getTotalPercentageOfVotesWon());
		Assert.assertNotNull(valueObject.getTotalPercentageOfVotesWonPreviousElection());
		Assert.assertNotNull(valueObject.getYear());
		Assert.assertNotNull(valueObject.getTotalSeatsWon()); 
		
		System.out.println("TotalPercentageOfVotesWon="+valueObject.getTotalPercentageOfVotesWon());
		System.out.println("TotalPercentageOfVotesWonPreviousElection="+valueObject.getTotalPercentageOfVotesWonPreviousElection());
		System.out.println("Year="+valueObject.getYear());
		System.out.println("TotalSeatsWon="+valueObject.getTotalSeatsWon()); 
		
		
		EasyMock.verify(constituencyDAO);
		EasyMock.verify(electionDAO);
		EasyMock.verify(stateDAO);
		EasyMock.verify(partyDAO);
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
		Map<String,String> result = service.partyVotesFlow(present,previous);
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
	public void testVotesFlow(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		Map<String, BigDecimal> map = service.votesFlow(constituencyList, "2009", "2");
		Assert.assertTrue(map.size()>0);
		
		/*Set set = map.entrySet();
		java.util.Iterator itr = set.iterator();
		System.out.println("********************************************************************************************");
		while(itr.hasNext()){
			Map.Entry entry = (Map.Entry)itr.next();
			System.out.println("key="+entry.getKey()+" value="+entry.getValue());
		}*/
		Assert.assertTrue(map.size()>0);
	}
	
	
	
	
	@Test
	public void testGetPartyConstituenciesData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		Map<String, List<ConstituencyPositionDetailVO>> partyData = service.getPartyConstituenciesData(
				constituencyList,"INC", "2009","2");
		Assert.assertNotNull(partyData);
		Assert.assertEquals(2, partyData.size());

		List<ConstituencyPositionDetailVO> listWinner = partyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> listLooser = partyData.get("PARTY_LOOSER");
		Assert.assertNotNull(listWinner);
		Assert.assertNotNull(listLooser);
		Assert.assertNotSame(0, listWinner.size()+listLooser.size());
		/*System.out.println("Winner Size="+listWinner.size());
		System.out.println("Looser Size="+listLooser.size());*/
	}

	@Test
	public void testgetListLostByDroppingVotes(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		Map<String, List<ConstituencyPositionDetailVO>> presentPartyData = service.getPartyConstituenciesData(
				constituencyList,"INC", "2009","2");
		List<ConstituencyPositionDetailVO> presentWinners = presentPartyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> presentLoosers = presentPartyData.get("PARTY_LOOSER");

		Map<String, List<ConstituencyPositionDetailVO>> previousPartyData = service.getPartyConstituenciesData(
				constituencyList,"INC", "2004","2");
		List<ConstituencyPositionDetailVO> previousWinners = previousPartyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> previousLoosers = previousPartyData.get("PARTY_LOOSER");
		
		List<ConstituencyPositionDetailVO> previousData = new ArrayList<ConstituencyPositionDetailVO>();
		previousData.addAll(previousWinners);
		previousData.addAll(previousLoosers);
		
		List<ConstituencyPositionDetailVO> list = service.getListLostByDroppingVotes(presentLoosers, previousData);
		
		Assert.assertFalse(0==list.size());
		Assert.assertEquals(5, list.size());
	}

	@Test
	public void testGetPartyPosition(){
		List<ConstituencyPositionDetailVO> partyLoosers = new ArrayList<ConstituencyPositionDetailVO>();
		ConstituencyPositionDetailVO object1 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object2 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object3 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object4 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object5 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object6 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object7 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object8 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object9 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object10 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object11 = new ConstituencyPositionDetailVO();
		ConstituencyPositionDetailVO object12 = new ConstituencyPositionDetailVO();
		partyLoosers.add(object1);		partyLoosers.add(object2);		partyLoosers.add(object3);
		partyLoosers.add(object4);		partyLoosers.add(object5);		partyLoosers.add(object6);
		partyLoosers.add(object7);		partyLoosers.add(object8);		partyLoosers.add(object9);
		partyLoosers.add(object10);		partyLoosers.add(object11);		partyLoosers.add(object12);
		PartyService service = new PartyService();
		object1.setRank(11);	object2.setRank(11);	object3.setRank(11);
		object4.setRank(11);	object5.setRank(11);	object6.setRank(11);
		object7.setRank(11);	object8.setRank(11);	object9.setRank(11);
		object10.setRank(11);	object11.setRank(11);	object12.setRank(11);
		int[] result = service.getPartyPosition(partyLoosers);
		Assert.assertEquals(12, result[3]);
		Assert.assertEquals(0, result[0]);
		Assert.assertEquals(0, result[1]);
		Assert.assertEquals(0, result[2]);

		object1.setRank(2);	object2.setRank(2);	object3.setRank(2);
		object4.setRank(2);	object5.setRank(2);	object6.setRank(2);
		object7.setRank(2);	object8.setRank(2);	object9.setRank(2);
		object10.setRank(2);	object11.setRank(2);	object12.setRank(2);
		result = service.getPartyPosition(partyLoosers);
		Assert.assertEquals(12, result[0]);
		Assert.assertEquals(0, result[3]);
		Assert.assertEquals(0, result[1]);
		Assert.assertEquals(0, result[2]);
		
		object1.setRank(3);	object2.setRank(3);	object3.setRank(3);
		object4.setRank(3);	object5.setRank(3);	object6.setRank(3);
		object7.setRank(3);	object8.setRank(3);	object9.setRank(3);
		object10.setRank(3);	object11.setRank(3);	object12.setRank(3);
		result = service.getPartyPosition(partyLoosers);
		Assert.assertEquals(12, result[1]);
		Assert.assertEquals(0, result[0]);
		Assert.assertEquals(0, result[3]);
		Assert.assertEquals(0, result[2]);
		
		object1.setRank(4);	object2.setRank(4);	object3.setRank(4);
		object4.setRank(4);	object5.setRank(4);	object6.setRank(4);
		object7.setRank(4);	object8.setRank(4);	object9.setRank(4);
		object10.setRank(4);	object11.setRank(4);	object12.setRank(4);
		result = service.getPartyPosition(partyLoosers);
		Assert.assertEquals(12, result[2]);
		Assert.assertEquals(0, result[0]);
		Assert.assertEquals(0, result[1]);
		Assert.assertEquals(0, result[3]);
		

		object1.setRank(4);	object2.setRank(3);	object3.setRank(4);
		object4.setRank(3);	object5.setRank(2);	object6.setRank(3);
		object7.setRank(4);	object8.setRank(5);	object9.setRank(5);
		object10.setRank(5);	object11.setRank(2);	object12.setRank(2);
		result = service.getPartyPosition(partyLoosers);
		Assert.assertEquals(3, result[0]);
		Assert.assertEquals(3, result[1]);
		Assert.assertEquals(3, result[2]);
		Assert.assertEquals(3, result[3]);
	}

	@Test
	public void testGetTotalPercentageOfVotesWon(){
		
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		BigDecimal percentage = service.getTotalPercentageOfVotesWon(constituencyList,"Indian National Congress", "2009","2");
		Assert.assertNotNull(percentage);
		Assert.assertFalse(percentage.doubleValue()<0);
		Assert.assertFalse(percentage.doubleValue()>100);
	}
	
	@Test
	public void testSwingDifferenceWinData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		Map<String, List<ConstituencyPositionDetailVO>> presentPartyData = service.getPartyConstituenciesData(
				constituencyList,"Indian National Congress", "2009","2");
		List<ConstituencyPositionDetailVO> presentWinners = presentPartyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> presentLoosers = presentPartyData.get("PARTY_LOOSER");

		Map<String, List<ConstituencyPositionDetailVO>> previousPartyData = service.getPartyConstituenciesData(
				constituencyList,"Indian National Congress", "2004","2");
		List<ConstituencyPositionDetailVO> previousWinners = previousPartyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> previousLoosers = previousPartyData.get("PARTY_LOOSER");
		
		List<ConstituencyPositionDetailVO> previousData = new ArrayList<ConstituencyPositionDetailVO>();
		previousData.addAll(previousWinners);
		previousData.addAll(previousLoosers);
		
		Map<String, List<ConstituencyPositionDetailVO>> swingDataWin = service
				.swingDifferenceWinData(presentWinners, previousData,1, 5);
		List<ConstituencyPositionDetailVO> positiveSwingWin = swingDataWin.get("POSITIVE_SWING");
		List<ConstituencyPositionDetailVO> negativeSwingWin = swingDataWin.get("NEGATIVE_SWING"); 

		Map<String, List<ConstituencyPositionDetailVO>> swingDataLost = service
				.swingDifferenceWinData(presentLoosers, previousData,1, 5);
		List<ConstituencyPositionDetailVO> positiveSwingLost = swingDataLost.get("POSITIVE_SWING");
		List<ConstituencyPositionDetailVO> negativeSwingLost = swingDataLost.get("NEGATIVE_SWING");
		
		Assert.assertFalse(presentWinners.size() < positiveSwingWin.size()+negativeSwingWin.size());
		Assert.assertFalse(presentLoosers.size() < positiveSwingLost.size()+negativeSwingLost.size());
	}
	
	
	@Test
	public void testMarginDifferenceWinData(){
		PartyService service = new PartyService();
		List<Constituency> constituencies = MockData.getConstituencies();
		Set<Constituency>  constituencyList = new HashSet<Constituency>();
		constituencyList.addAll(constituencies);
		Map<String, List<ConstituencyPositionDetailVO>> partyData = service.getPartyConstituenciesData(
				constituencyList,"Indian National Congress", "2009","2");
		List<ConstituencyPositionDetailVO> listWinner = partyData.get("PARTY_WINNER");
		List<ConstituencyPositionDetailVO> listLooser = partyData.get("PARTY_LOOSER");
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
	public void testElectionYears(){//String electionTypeID, String stateID){
		PartyService service = new PartyService();
		List<Election> elections = MockData.getElections();
		EasyMock.expect(electionDAO.findByPropertyTypeId_CountryId_StateId(new Long(2), new Long(1), new Long(1))).andReturn(elections);
		EasyMock.replay(electionDAO);
		service.setElectionDAO(electionDAO);
		List<String> list = service.getElectionYears("2", "1", "1");
		Assert.assertNotNull(list);
		for(String str: list)
			System.out.println(str);
		Assert.assertNotSame(0,list.size());
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
	public void testPreviousYear(){//List<String> years, String presentYear){
		PartyService service = new PartyService();
		List<String> list = new ArrayList<String>();
		list.add("1990");
		list.add("1720");
		list.add("1111");
		list.add("2000");
		list.add("2009");
		String result = service.getPreviousYear(list, "2009");
		Assert.assertNotNull(result);
		Assert.assertEquals("2000", result);
		result = service.getPreviousYear(list, "1111");
		Assert.assertEquals(new String(), result);
	}
}
