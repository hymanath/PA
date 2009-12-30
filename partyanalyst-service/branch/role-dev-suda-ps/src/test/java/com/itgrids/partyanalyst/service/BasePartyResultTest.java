package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List; 
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

 
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.util.DummyPartyResultsData;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
import com.itgrids.partyanalyst.utils.PartyInfoComparator;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;

import com.itgrids.partyanalyst.service.impl.BasePartyResultsServiceImpl;

public class BasePartyResultTest {

	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;

	public void setConstituencyElectionResultDAO(IConstituencyElectionResultDAO constituencyElectionResultDAO){
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public void setConstituencyElectionDAO(IConstituencyElectionDAO constituencyElectionDAO){
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO){
		this.nominationDAO = nominationDAO;
	}
	
	
	@Before
	public void init(){
		constituencyElectionResultDAO = EasyMock.createMock(IConstituencyElectionResultDAO.class);
		constituencyElectionDAO = EasyMock.createMock(IConstituencyElectionDAO.class);
		nominationDAO = EasyMock.createMock(INominationDAO.class);
	}
	
	@Test
	public void testConstituencyGrandTotalValidVotes(){
		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		List<ConstituencyElectionResult> listConstituencyElectionResult = DummyPartyResultsData.getConstituencyElectionResultList();
		
		EasyMock.expect(constituencyElectionResultDAO.findByConstituencyElections("1,2")).andReturn(listConstituencyElectionResult);
		EasyMock.replay(constituencyElectionResultDAO);
		service.setConstituencyElectionResultDAO(constituencyElectionResultDAO);
		Long actualResult = service.getConstituencyGrandTotalValidVotes("1,2");

		EasyMock.verify(constituencyElectionResultDAO);
		Assert.assertEquals(new Long(27000), actualResult);
	}
	
	@Test
	public void testCalculatePartyDetails(){
		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		int i =0;
		Long[] partyTotalVotes ={1000l, 250l, 165l, 850l, 500l};
		String[] shortNames = {"INC", "PRP", "IND", "TDP", "TRS"};
		Long[] winSeats = {8L,3L,1L,4L,3L};
		Map<String, PartyInfoVO> partyAndCompetetorsInfo = new HashMap<String, PartyInfoVO>();
		PartyInfoVO party1  = new PartyInfoVO();
		party1.setPartyShortName(shortNames[i]);
		party1.setPartyTotalVotes(partyTotalVotes[i]);
		party1.setSeatsWin(winSeats[i]);
		partyAndCompetetorsInfo.put(shortNames[i], party1);
		
		PartyInfoVO party2  = new PartyInfoVO();++i;
		party2.setPartyShortName(shortNames[i]);
		party2.setPartyTotalVotes(partyTotalVotes[i]);
		party2.setSeatsWin(winSeats[i]);
		partyAndCompetetorsInfo.put(shortNames[i], party2);
		
		PartyInfoVO party3  = new PartyInfoVO();++i;
		party3.setPartyShortName(shortNames[i]);
		party3.setPartyTotalVotes(partyTotalVotes[i]);
		party3.setSeatsWin(winSeats[i]);
		partyAndCompetetorsInfo.put(shortNames[i], party3);
		
		PartyInfoVO party4  = new PartyInfoVO();++i;
		party4.setPartyShortName(shortNames[i]);
		party4.setPartyTotalVotes(partyTotalVotes[i]);
		party4.setSeatsWin(winSeats[i]);
		partyAndCompetetorsInfo.put(shortNames[i], party4);
		
		PartyInfoVO party5  = new PartyInfoVO();++i;
		party5.setPartyShortName(shortNames[i]);
		party5.setPartyTotalVotes(partyTotalVotes[i]);
		party5.setSeatsWin(winSeats[i]);
		partyAndCompetetorsInfo.put(shortNames[i], party5);
		
		partyAndCompetetorsInfo.remove(party1.getPartyShortName());
		List<PartyInfoVO> actualResult = service.calculatePartyDetails(partyAndCompetetorsInfo, 1, 2765L, party1);
		System.out.println("actualResult.get(0).getPartyShortName():"+actualResult.get(0).getPartyShortName());
		System.out.println("actualResult.get(1).getPartyShortName():"+actualResult.get(1).getPartyShortName());
		Assert.assertEquals("INC", actualResult.get(0).getPartyShortName());
		Assert.assertEquals("TDP", actualResult.get(1).getPartyShortName());
	}

	@Test
	public void testGetConstituencyPartyResults(){
		/*public Map<String, PartyInfoVO> getConstituencyPartyResults(
		 List<ConstituencyElection> ConstituencyElectionList, String electionYear)*/
		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		Election election = new Election(new Long(1));
		election.setElectionYear("2009");
		List<ConstituencyElection> constituencyElectionList = DummyPartyResultsData.getConstituencyElectionList(election);
		for(ConstituencyElection constituencyElection: constituencyElectionList){
			Set<Nomination> set = constituencyElection.getNominations();
			List<Nomination> list = new ArrayList<Nomination>();
			list.addAll(set);
			EasyMock.expect(nominationDAO.findByConstituencyElection(constituencyElection.getConstiElecId())).andReturn(list);
		}
		EasyMock.replay(nominationDAO);
		service.setNominationDAO(nominationDAO);
		Map<String, PartyInfoVO> actual = service.getConstituencyPartyResults(constituencyElectionList, "2009");
		List<PartyInfoVO> list = new ArrayList<PartyInfoVO>();
		Set<String> keys = actual.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()){
			PartyInfoVO partyInfoVO = actual.get(itr.next());
			partyInfoVO.setPercentageOfVotes(new BigDecimal(0));
			list.add(partyInfoVO);
		}
		PartyInfoComparator comparator = new PartyInfoComparator();
		Collections.sort(list, comparator);

		Assert.assertEquals("INC", list.get(0).getPartyShortName());
	}
	
	@Test
	public void testGetConstituencyGrandTotalValidVotes(){
		List<ConstituencyElectionResult> constElectionResult = new ArrayList<ConstituencyElectionResult>();
		ConstituencyElectionResult obj1 = new ConstituencyElectionResult();obj1.setConstiElecResultId(1l);obj1.setValidVotes(125d);
		ConstituencyElectionResult obj2 = new ConstituencyElectionResult();obj2.setConstiElecResultId(2l); obj2.setValidVotes(225d);
		ConstituencyElectionResult obj3 = new ConstituencyElectionResult();obj3.setConstiElecResultId(3l);obj3.setValidVotes(325d);
		ConstituencyElectionResult obj4 = new ConstituencyElectionResult();obj4.setConstiElecResultId(4l);obj4.setValidVotes(425d);
		ConstituencyElectionResult obj5 = new ConstituencyElectionResult();obj5.setConstiElecResultId(5l);obj5.setValidVotes(175d);
		ConstituencyElectionResult obj6 = new ConstituencyElectionResult();obj6.setConstiElecResultId(6l);obj6.setValidVotes(225d);
		constElectionResult.add(obj1);
		constElectionResult.add(obj2);
		constElectionResult.add(obj3);
		constElectionResult.add(obj4);
		constElectionResult.add(obj5);
		constElectionResult.add(obj6);
		EasyMock.expect(constituencyElectionResultDAO.findByConstituencyElections("1,2,3,4,5,6")).andReturn(constElectionResult);
		EasyMock.replay(constituencyElectionResultDAO);

		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		service.setConstituencyElectionResultDAO(constituencyElectionResultDAO);
		Long actual = service.getConstituencyGrandTotalValidVotes("1,2,3,4,5,6");
		Assert.assertEquals(new Long(1500), actual);
		
	}
	
	@Test
	public void testGetPartyAndCompetetorsInfo(){
		Election election = new Election(new Long(1));
		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		List<ConstituencyElection> dummyData = DummyPartyResultsData.getConstituencyElectionList( election);
		EasyMock.expect(constituencyElectionDAO.findByElection(election.getElectionId())).andReturn(dummyData);
		EasyMock.replay(constituencyElectionDAO);

		
		for(ConstituencyElection constituencyElection: dummyData){
			Set<Nomination> set = constituencyElection.getNominations();
			List<Nomination> list = new ArrayList<Nomination>();
			list.addAll(set);
			EasyMock.expect(nominationDAO.findByConstituencyElection(constituencyElection.getConstiElecId())).andReturn(list);
		}
		EasyMock.replay(nominationDAO);
		service.setNominationDAO(nominationDAO);
		
		
		
		
		List<ConstituencyElectionResult> constElectionResult = new ArrayList<ConstituencyElectionResult>();
		ConstituencyElectionResult obj1 = new ConstituencyElectionResult();obj1.setConstiElecResultId(1l);obj1.setValidVotes(1250d);
		ConstituencyElectionResult obj2 = new ConstituencyElectionResult();obj2.setConstiElecResultId(2l);obj2.setValidVotes(2250d);
		ConstituencyElectionResult obj3 = new ConstituencyElectionResult();obj3.setConstiElecResultId(3l);obj3.setValidVotes(3250d);
		ConstituencyElectionResult obj4 = new ConstituencyElectionResult();obj4.setConstiElecResultId(4l);obj4.setValidVotes(4250d);
		ConstituencyElectionResult obj5 = new ConstituencyElectionResult();obj5.setConstiElecResultId(5l);obj5.setValidVotes(1750d);
		constElectionResult.add(obj1);
		constElectionResult.add(obj2);
		constElectionResult.add(obj3);
		constElectionResult.add(obj4);
		constElectionResult.add(obj5);
		EasyMock.expect(constituencyElectionResultDAO.findByConstituencyElections("1,2,3,4,5")).andReturn(constElectionResult);
		EasyMock.replay(constituencyElectionResultDAO);
		service.setConstituencyElectionDAO(constituencyElectionDAO);
		service.setConstituencyElectionResultDAO(constituencyElectionResultDAO);
		List<PartyInfoVO> actual = service.getPartyAndCompetetorsInfo(election,"TDP",null,null,3,ElectionScopeLevelEnum.STATE_LEVEL);
		Assert.assertEquals("INC", actual.get(1).getPartyShortName());	
	}
	
	
	@Test
	public void testPercentage(){
		BasePartyResultsServiceImpl service = new BasePartyResultsServiceImpl();
		PartyInfoVO info = new PartyInfoVO();
		info.setPartyTotalVotes(100L);
		BigDecimal result = service.getPercentage(info, 200L);
		System.out.println("--------------------------------------------------------------------");
		System.out.println(result);
		Assert.assertEquals(new BigDecimal(50), result);
		
	}
	
}
