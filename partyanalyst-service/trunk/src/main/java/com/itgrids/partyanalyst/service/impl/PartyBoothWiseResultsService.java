package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.BoothResultVO;
import com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;

public class PartyBoothWiseResultsService implements IPartyBoothWiseResultsService{
	
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public List<SelectOptionVO> getParties(){
		List<SelectOptionVO> partyVOs = new ArrayList<SelectOptionVO>();
		List<Party> parties = partyDAO.getAll();
		for(Party party:parties){
			if(party.getShortName() != null){
				SelectOptionVO partyVO = new SelectOptionVO(party.getPartyId(), party.getShortName());
				partyVOs.add(partyVO);
			}
		}
		return partyVOs;
	}
	
	public List<SelectOptionVO> getConstituenciesForParty(Long partyId, Long electionTypeId, String electionYear){
		System.out.println("In getConstituenciesForParty::partyId, electionType, electionYear::"+partyId+","+electionTypeId+","+electionYear);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		long beginTimeMillis = System.currentTimeMillis();
		List<Constituency> constituencies = nominationDAO.findConstitueniesByPartyAndElectionType(partyId, electionTypeId, electionYear);
		long endTimeMillis = System.currentTimeMillis();
		System.out.println("beginTimeMillis:"+beginTimeMillis);
		System.out.println("endTimeMillis:"+endTimeMillis);
		System.out.println("Total time taken:" + (beginTimeMillis-endTimeMillis)/1000);
		for(Constituency constituency:constituencies){
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
			constituencyVOs.add(constituencyVO);
		}
		return constituencyVOs;
	}
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear){
		System.out.println("In getBoothWiseResultsForParty::constituencyId, electionYear::"+constituencyId+","+electionYear);
		List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
		List<Nomination> nominations  = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, constituencyId, electionYear);
		for(Nomination nomination:nominations){
			List<BoothResultVO> boothResultVOs = new ArrayList<BoothResultVO>();
			PartyBoothPerformanceVO partyBoothPerformanceVO = new PartyBoothPerformanceVO();
			partyBoothPerformanceVO.setPartyName(nomination.getParty().getShortName());
			partyBoothPerformanceVO.setCandidateName(nomination.getCandidate().getLastname());
			partyBoothPerformanceVO.setConstituencyName(nomination.getConstituencyElection().getConstituency().getName());
			partyBoothPerformanceVO.setElectionType(nomination.getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType());
			partyBoothPerformanceVO.setElectionYear(nomination.getConstituencyElection().getElection().getElectionYear());
			List<BoothResult> boothResults = new ArrayList<BoothResult>(nomination.getBoothResults());
			System.out.println("In getBoothWiseResultsForParty::"+boothResults.size());
			for(BoothResult boothResult:boothResults){
				Booth booth =  boothResult.getBoothConstituencyElection().getBooth();
				int totalVoters = booth.getTotalVoters().intValue();
				int votesEarned = boothResult.getVotesEarned().intValue();
				String percentage = calculateVotesPercengate(new Double(totalVoters), new Double(votesEarned));	
				BoothResultVO boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getvillagesCovered(), votesEarned, totalVoters, percentage, booth.getTehsil().getTehsilName());
				boothResultVOs.add(boothResultVO);
			}
			partyBoothPerformanceVO.setBoothResults(boothResultVOs);
			boothResultsForParties.add(partyBoothPerformanceVO);
		}
		return boothResultsForParties;
	}
	
	private String calculateVotesPercengate(Double validVotes, Double votesEarned){
		BigDecimal percengate= new BigDecimal(0.0);
		if((validVotes!=null && validVotes.doubleValue()>0) && (votesEarned!=null && votesEarned.doubleValue()>0)){
			percengate= new BigDecimal((votesEarned/validVotes)*100).setScale (2,BigDecimal.ROUND_HALF_UP);
		}
		return percengate.toString();
	}


}
