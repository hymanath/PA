package com.itgrids.partyanalyst.service;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.util.MockData;

public class CrossVotingEstimationServiceTest{
	
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;	
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	private StaticDataService staticDataService;	
	private CrossVotingEstimationService crossVotingEstimationService;

	@Before
	public void setUp() throws Exception {
		nominationDAO = EasyMock.createMock(INominationDAO.class);
		boothDAO = EasyMock.createMock(IBoothDAO.class);
		boothConstituencyElectionDAO = EasyMock.createMock(IBoothConstituencyElectionDAO.class);
		candidateBoothResultDAO = EasyMock.createMock(ICandidateBoothResultDAO.class);
		delimitationConstituencyAssemblyDetailsDAO = EasyMock.createMock(IDelimitationConstituencyAssemblyDetailsDAO.class);
		allianceGroupDAO = EasyMock.createMock(IAllianceGroupDAO.class);
		electionAllianceDAO = EasyMock.createMock(IElectionAllianceDAO.class);
		staticDataService = new StaticDataService();
		crossVotingEstimationService = new CrossVotingEstimationService();
		crossVotingEstimationService.setBoothConstituencyElectionDAO(boothConstituencyElectionDAO);
		crossVotingEstimationService.setBoothDAO(boothDAO);
		crossVotingEstimationService.setCandidateBoothResultDAO(candidateBoothResultDAO);
		crossVotingEstimationService.setDelimitationConstituencyAssemblyDetailsDAO(delimitationConstituencyAssemblyDetailsDAO);
		crossVotingEstimationService.setNominationDAO(nominationDAO);
		staticDataService.setAllianceGroupDAO(allianceGroupDAO);
		staticDataService.setElectionAllianceDAO(electionAllianceDAO);
	}
	
	/*@Test
	public void checkGetAssembliesForParliament(){
		EasyMock.expect(delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(new Long(408), new Long(2004))).andReturn(MockData.getConstituencies());
		EasyMock.replay(delimitationConstituencyAssemblyDetailsDAO);
		List<SelectOptionVO> list = crossVotingEstimationService.getAssembliesForParliament(new Long(408), new Long(2004));
		String[] names = {"Kavali","Atmakuru","Kovuru","Nellore City","Nellore Rural","Sarvepalli","Gudur","Sullurpeta","Venkatagiri","Udayagiri","Rapur","Allur","Nellore"};
		for(int i=0; i<list.size(); i++){
			Assert.assertEquals(list.get(i).getName(), names[i]);
		}		
		EasyMock.verify(delimitationConstituencyAssemblyDetailsDAO);
	}*/
	
}
