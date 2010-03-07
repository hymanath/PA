package com.itgrids.partyanalyst.service;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dto.ConstituencyWiseDataForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;

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
	}*/
	
	
	//@Test
	public void test(){
		ElectionWiseMandalPartyResultListVO firstList = partyBoothWiseResultsService.getPartyGenderWiseBoothVotesForMandal(21816l, "Town");
		System.out.println(firstList.getElectionWiseMandalPartyResultVOList().size());
		for(ElectionWiseMandalPartyResultVO eleTypeObj : firstList.getElectionWiseMandalPartyResultVOList()){
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
}
