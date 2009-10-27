package com.itgrids.partyanalyst.service;

import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.BoothResultVO;
import com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.impl.PartyBoothWiseResultsService;
import com.itgrids.partyanalyst.util.DummyConstituencies;
import com.itgrids.partyanalyst.util.DummyNominations;
import com.itgrids.partyanalyst.util.DummyPartyResultsData;

public class PartyBoothWiseResultsServiceTest {
	
	private INominationDAO nominationDAO;
	private IPartyDAO partyDAO;
	private PartyBoothWiseResultsService partyBoothWiseResultsService;

	@Before
	public void setUp() throws Exception {
		nominationDAO = EasyMock.createMock(INominationDAO.class);
		partyDAO = EasyMock.createMock(IPartyDAO.class);
		partyBoothWiseResultsService = new PartyBoothWiseResultsService();
		partyBoothWiseResultsService.setNominationDAO(nominationDAO);
		partyBoothWiseResultsService.setPartyDAO(partyDAO);
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
}
