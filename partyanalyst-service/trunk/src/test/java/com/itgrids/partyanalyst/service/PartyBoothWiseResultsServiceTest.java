package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ConstituencyWiseDataForMandalVO;
import com.itgrids.partyanalyst.dto.ConstituencyWisePartyInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.impl.PartyBoothWiseResultsService;
import com.itgrids.partyanalyst.util.DummyConstituencies;
import com.itgrids.partyanalyst.util.DummyNominations;
import com.itgrids.partyanalyst.util.DummyPartyResultsData;


public class PartyBoothWiseResultsServiceTest extends BaseDaoTestCase{
	
	private IPartyBoothWiseResultsService partyBoothWiseResultsService;
	
	public IPartyBoothWiseResultsService getPartyBoothWiseResultsService() {
		return partyBoothWiseResultsService;
	}

	public void setPartyBoothWiseResultsService(
			IPartyBoothWiseResultsService partyBoothWiseResultsService) {
		this.partyBoothWiseResultsService = partyBoothWiseResultsService;
	}
	
	/*private INominationDAO nominationDAO;
	private PartyBoothWiseResultsService partyBoothWiseResultsService;

	@Before
	public void setUp() throws Exception {
		nominationDAO = EasyMock.createMock(INominationDAO.class);		
		partyBoothWiseResultsService = new PartyBoothWiseResultsService();
		partyBoothWiseResultsService.setNominationDAO(nominationDAO);		
	}
	
	@Test
	public void checkGetParties(){
		EasyMock.expect(partyDAO.getAll()).andReturn(DummyPartyResultsData.getParties());
		EasyMock.replay(partyDAO);
		List<SelectOptionVO> list = partyBoothWiseResultsService.getParties();
		for(SelectOptionVO obj:list){
			if(obj.getId() == 11)
				Assert.assertEquals("PPOI", obj.getName());
			if(obj.getId() == 6)
				Assert.assertEquals("PRP", obj.getName());
		}
		EasyMock.verify(partyDAO);
	}
	
	@Test
	public void checkGetConstituenciesForParty(){
		EasyMock.expect(nominationDAO.findConstitueniesByPartyAndElectionType(new Long(1), new Long(2), "2004")).andReturn(DummyConstituencies.getConstituencies());
		EasyMock.replay(nominationDAO);
		List<SelectOptionVO> list = partyBoothWiseResultsService.getConstituenciesForParty(new Long(1),  new Long(2), "2004");
		for(SelectOptionVO obj:list){
			if(obj.getId() == 1)
				Assert.assertEquals("Madanapalli", obj.getName());
			if(obj.getId() == 2)
				Assert.assertEquals("Nellore", obj.getName());
		}
		EasyMock.verify(nominationDAO);
	}
	
	@Test
	public void checkGetBoothWiseResultsForParty(){
		EasyMock.expect(nominationDAO.findByConstituencyPartyAndElectionYear(new Long(1), new Long(123), "2004")).andReturn(DummyNominations.getNominationsWithBoothResults());
		EasyMock.replay(nominationDAO);
		List<PartyBoothPerformanceVO> vos = partyBoothWiseResultsService.getBoothWiseResultsForParty(new Long(1), new Long(123), "2004");
		PartyBoothPerformanceVO vo = vos.get(0);
		Assert.assertEquals(vo.getCandidateName(), "Konedala Chiranjeevi");
		Assert.assertEquals(vo.getConstituencyName(), "Tirupathi");
		Assert.assertEquals(vo.getPartyName(), "PRP");
		Assert.assertEquals(vo.getElectionYear(), "2004");
		Assert.assertEquals(vo.getElectionType(), "Assembly");
		List<BoothResultVO> list = vo.getBoothResults();
		BoothResultVO boothResultVO = list.get(0);
		System.out.println(boothResultVO.getLocation());
		System.out.println(boothResultVO.getPartNo());
		System.out.println(boothResultVO.getTotalVoters());
		System.out.println(boothResultVO.getVotesEarned());
		System.out.println(boothResultVO.getPercentage());
		EasyMock.verify(nominationDAO);
	}
	*/
	/*
	//@Test
	public void test(){
		ElectionWiseMandalPartyResultListVO firstList = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(844l, "Mandal");
		System.out.println(firstList.getPartyWiseElectionResultsVOList().size());
		for(ElectionWiseMandalPartyResultVO eleTypeObj : firstList.getPartyWiseElectionResultsVOList()){
			System.out.println(eleTypeObj.getElectionType()+"\t"+eleTypeObj.getElectionYear()+"\t"+eleTypeObj.getPartyResultsVO().size());
			for(PartyResultsVO obj:eleTypeObj.getPartyResultsVO()){
				System.out.println(obj.getPartyName()+"\t"+obj.getPercentage()+"\t"+obj.getVotesEarned());
			}
		}
		
		for(PartyResultVO partyResultVO:firstList.getAllPartiesAllElectionResults()){
			System.out.println(partyResultVO.getPartyName());
			for(ElectionResultVO electionResultVO:partyResultVO.getElectionWiseResults()){
				System.out.println(electionResultVO.getElectionType()+"\t"+electionResultVO.getElectionYear()+"\t"+electionResultVO.getVotesEarned()+"\t"+electionResultVO.getPercentage());
			}
		}
		
		
		for(ElectionWiseMandalPartyResultVO eleTypeObj : firstList.getElectionWiseMandalPartyResultVOList()){
			
			System.out.println("Size......."+eleTypeObj.getPartyResultsVO().size());
			System.out.println(eleTypeObj.getElectionType()+eleTypeObj.getElectionYear()+":"+eleTypeObj.getConstituencyWiseDataForMandalVOs().size());
			List<ConstituencyWiseDataForMandalVO> consties = eleTypeObj.getConstituencyWiseDataForMandalVOs();
			for(ConstituencyWiseDataForMandalVO constiObj:consties){
				System.out.print(constiObj.getConstituencyName()+"--");
				System.out.println(constiObj.getPartyVotes().size());
				List<PartyGenderWiseVotesVO> parties = constiObj.getPartyVotes();
				for(PartyGenderWiseVotesVO party:parties){
					System.out.println(party.getPartyName()+"\t"+party.getRank()+"\t"+party.getCandidateNameWithStatus()+"\t"+party.getMaleBoothResults()+"\t"+party.getMaleBoothResultsPercentage()+"\t"+party.getFemaleBoothResults()+"\t"+party.getFemaleBoothResultsPercentage()+"\t"+party.getFmBoothResults()+"\t"+party.getFmBoothResultsPercentage()+"\t"+party.getTotalVotesEarned()+"\t"+party.getTotalVotesEarnedPercentage());
				}
			}
		}
	}
	*/
	/*public void testGetAllMPTCAndZPTCElectionsInfoInTehsil(){
		ElectionWiseMandalPartyResultListVO obj
		 = partyBoothWiseResultsService.getAllMPTCAndZPTCElectionsInfoInTehsil(844l);
		for(PartyResultVO partyResultVO:obj.getAllPartiesAllElectionResults()){
			System.out.println(partyResultVO.getPartyName());
			for(ElectionResultVO electionResultVO:partyResultVO.getElectionWiseResults()){
				System.out.println(electionResultVO.getElectionType()+"\t"+electionResultVO.getElectionYear()+"\t"+electionResultVO.getVotesEarned()+"\t"+electionResultVO.getPercentage());
			}
		}
		List<ElectionWiseMandalPartyResultVO> list = obj.getPartyWiseElectionResultsVOList();
		System.out.println(list.size());
		for(ElectionWiseMandalPartyResultVO eleObj:list){
			System.out.println(eleObj.getElectionType()+"--"+eleObj.getElectionYear());
			System.out.println("Total Parties::"+eleObj.getPartyResultsVO().size());
			List<PartyResultsVO> partyElections = eleObj.getPartyResultsVO();
			for(PartyResultsVO partyObj:partyElections){
				System.out.println(partyObj.getPartyName() +"\t"+partyObj.getSeatsParticipated()+"\t"+partyObj.getTotalSeatsWon()+"\t"+partyObj.getPercentage());
				List<ConstituencyWisePartyInfoVO> consties = partyObj.getConstituencyWisePatiesInfoVOs();
				for(ConstituencyWisePartyInfoVO constiObj:consties){
					System.out.println(constiObj.getConstituencyName()+"\t"+constiObj.getCandidateName()+"\t"+constiObj.getRank()+"\t"+constiObj.getPercentage()+"\t"+constiObj.getVotesEarned());
				}
			}
		}
		
		
	}
	
	
	public void testGetAllMPTCAndZPTCElectionsInfoInTehsilMapTest(){
		
		Map<PartyResultsVO, List<Object[]>> partiesInElectionMap = new LinkedHashMap<PartyResultsVO, List<Object[]>>();
		PartyResultsVO partyResultsVO = new PartyResultsVO();
		partyResultsVO.setPartyId(new Long(2));
		partyResultsVO.setPartyName("INC");
		
		Object[] objectArray = new Object[2];
		objectArray[0]="One";
		objectArray[1]="Two";
		
		Object[] objectArray2 = new Object[2];
		objectArray[0]="Threee";
		objectArray[1]="Four";

		
		List<Object[]> testObjects = new ArrayList<Object[]>();
		testObjects.add(objectArray);
		testObjects.add(objectArray2);

		PartyResultsVO partyResultsVO1 = new PartyResultsVO();
		partyResultsVO1.setPartyId(new Long(2));
		partyResultsVO1.setPartyName("INC");
		
		Object[] objectArray3 = new Object[2];
		objectArray[0]="Five";
		objectArray[1]="Six";
		

		
		List<Object[]> testObjects1 = new ArrayList<Object[]>();
		testObjects.add(objectArray3);
				
		
		partiesInElectionMap.put(partyResultsVO, testObjects);
		partiesInElectionMap.put(partyResultsVO1, testObjects1);
		
		
		PartyResultsVO partyResultsVO3 = new PartyResultsVO();
		partyResultsVO3.setPartyId(new Long(2));
		partyResultsVO3.setPartyName("INC");
		
		List<Object[]> list = partiesInElectionMap.get(partyResultsVO);
		
		System.out.println("List Size: "+list.size());
		
		List<Object[]> list1 = partiesInElectionMap.get(partyResultsVO1);
		
		System.out.println("List2 Size: "+list1.size());
		
		List<Object[]> list3 = partiesInElectionMap.get(partyResultsVO3);
		
		System.out.println("List3 Size: "+list3);
	}*/
	
	public void testGetBoothwiseResultsOfTwoElectionsForAConstituency(){
		ConstituencyVO constituencyVO = partyBoothWiseResultsService.getBoothwiseResultsOfTwoElectionsForAConstituency(30l);
		if(constituencyVO.getIsDataExists())
		for(PartyElectionVotersVO party:constituencyVO.getPartiesCombinedResults())
			System.out.println(party.getParty()+"\t"+party.getPresVotesEarned()+"\t"+party.getPresPercentage()+"\t"+party.getPrevVotesEarned()+"\t"+party.getPrevPercentage());
	}
}
