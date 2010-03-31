package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;
import com.itgrids.partyanalyst.dto.CrossVotedCandidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CrossVotingEstimationService implements ICrossVotingEstimationService{

	private static final Logger log = Logger.getLogger(CrossVotingEstimationService.class);
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;	
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IStaticDataService staticDataService;
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}	
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}
	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	//Getting Constituencies From BoothConstituencyElection
	public List<SelectOptionVO> getConstituenciesForElectionYearAndScopeForBoothData(String electionYear, Long electionScopeId){
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		List<Constituency> constituencies = boothConstituencyElectionDAO.findConstituencyByElectionYearAndElectionScope(electionYear, electionScopeId);
		for(Constituency constituency:constituencies){
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName().toUpperCase());
			constituencyVOs.add(constituencyVO);
		}
		return constituencyVOs;
	}
	
	public List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear){
		List<Constituency> constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(parliamentId, electionYear);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		List boothConstituencyElections = null;
		Long constituencyId;
		for(Constituency constituency:constituencies){
			constituencyId = constituency.getConstituencyId();
			boothConstituencyElections = boothConstituencyElectionDAO.findByConstituencyIdAndElectionYear(constituencyId, parliamentId, electionYear.toString());
			if(boothConstituencyElections == null || boothConstituencyElections.get(0).toString().equalsIgnoreCase("0"))
				continue;
			constituencyVOs.add(new SelectOptionVO(constituencyId, constituency.getName().toUpperCase()));
		}
		return constituencyVOs;
	}
	
	//Getting Parties From CandidateBoothResult
	public List<SelectOptionVO> getPartiesForConstituencyAndElectionYearForBoothData(Long constituencyId, String electionYear){
		List<Party> list = candidateBoothResultDAO.findPartiesByConstituencyAndElectionYear(constituencyId, electionYear);
		List<SelectOptionVO> partyVOs = new ArrayList<SelectOptionVO>();
		for(Party party:list){
			if(party.getShortName().equals(IConstants.INDIPENDENT))
				continue;
			SelectOptionVO delimitationConstituencyVO = new SelectOptionVO(party.getPartyId(), party.getShortName().toUpperCase());
			partyVOs.add(delimitationConstituencyVO);
		}
		return partyVOs;
	}
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance){
		CrossVotingConsolidateVO crossVotingConsolidateVO = new CrossVotingConsolidateVO(); 
		CrossVotedCandidateVO acCandidate = new CrossVotedCandidateVO();
		CrossVotedCandidateVO pcCandidate = new CrossVotedCandidateVO();
		List<Nomination> acNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, acId, electionYear);
		List<Nomination> pcNominations;
		if(includeAliance.equals("true")){
			List<SelectOptionVO> alianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, new Long(2), partyId);
			
			if(alianceParties == null || alianceParties.size() == 0)
				pcNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, pcId, electionYear);
			else{
				List<Long> aliancePartyIds = new ArrayList<Long>();
				
				for(SelectOptionVO alianceParty:alianceParties)
					aliancePartyIds.add(alianceParty.getId());
				
				pcNominations = nominationDAO.findByConstituencyPartyAndElectionYearIncludingAliance(aliancePartyIds, pcId, electionYear);
			}
			
		}else{
			pcNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, pcId, electionYear);
		}		
		
		if(acNominations.size() != 1 || pcNominations.size() != 1){
			if(pcNominations.size() == 0){
				crossVotingConsolidateVO.setPartyPartisipated(false);
				return crossVotingConsolidateVO;
			}	
		}
		
		Nomination acNomination = acNominations.get(0);
		acCandidate.setCandidateId(acNomination.getCandidate().getCandidateId());
		acCandidate.setCandidateName(acNomination.getCandidate().getLastname());
		acCandidate.setImage(acNomination.getCandidate().getImage());
		acCandidate.setRank(checkForCandidateWonOrLost(acNomination));
		acCandidate.setParty(acNomination.getParty().getShortName());
		
		Nomination pcNomination = pcNominations.get(0);
		pcCandidate.setCandidateId(pcNomination.getCandidate().getCandidateId());
		pcCandidate.setCandidateName(pcNomination.getCandidate().getLastname());
		pcCandidate.setImage(pcNomination.getCandidate().getImage());
		pcCandidate.setRank(checkForCandidateWonOrLost(pcNomination));
		pcCandidate.setParty(pcNomination.getParty().getShortName());
		
		crossVotingConsolidateVO.setTotalACPolledVotesInConstituency(acNomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotesPolled().longValue());
		crossVotingConsolidateVO.setTotalPCPolledVotesInConstituency(pcNomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotesPolled().longValue());
		crossVotingConsolidateVO.setTotalVotersInAC(acNomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotes().longValue());
		crossVotingConsolidateVO.setTotalVotersInPC(pcNomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotes().longValue());
		crossVotingConsolidateVO.setAcCandidateData(acCandidate);
		crossVotingConsolidateVO.setPcCandidateData(pcCandidate);
		List<CrossVotedMandalVO> mandals = getTehsilsForConstituency(crossVotingConsolidateVO, electionYear, partyId, acId, pcId, acNomination.getNominationId(), pcNomination.getNominationId());
		crossVotingConsolidateVO.setMandals(mandals);
		return crossVotingConsolidateVO;
	}
	
	public List<CrossVotedMandalVO> getTehsilsForConstituency(CrossVotingConsolidateVO crossVotingConsolidateVO, String electionYear, Long partyId, Long assemblyConstituencyId, Long parliamentConstituencyId, Long acNominationId, Long pcNominationId){
		List<CrossVotedMandalVO> crossVotedMandalVOs = new ArrayList<CrossVotedMandalVO>();
		List<Tehsil> tehsils = boothDAO.findTehsilsByElectionAndConstituency(electionYear, assemblyConstituencyId);
		CrossVotedCandidateVO acCandidate = crossVotingConsolidateVO.getAcCandidateData();
		CrossVotedCandidateVO pcCandidate = crossVotingConsolidateVO.getPcCandidateData();
		Long totalPolledVotesInAC = new Long(0);
		Long acVotesEarnedInConstituency = new Long(0);
		Long acValidVotesInConstituency = new Long(0);
		Long pcVotesEarnedInConstituency = new Long(0);
		Long pcValidVotesInConstituency = new Long(0);
		CrossVotedMandalVO crossVotedMandalVO = null;
		
		for(Tehsil tehsil:tehsils){
			crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalName(tehsil.getTehsilName());
			List<CrossVotedBoothVO> crossVotedBooths = getCrossVotingDetails(tehsil.getTehsilId(), electionYear, crossVotedMandalVO, assemblyConstituencyId, parliamentConstituencyId, acNominationId, pcNominationId);
			pcVotesEarnedInConstituency = pcVotesEarnedInConstituency + crossVotedMandalVO.getPcEarnedVotesInMandal();
			pcValidVotesInConstituency = pcValidVotesInConstituency + crossVotedMandalVO.getPcValidVotesInMandal();
			acVotesEarnedInConstituency = acVotesEarnedInConstituency + crossVotedMandalVO.getAcEarnedVotesInMandal();
			acValidVotesInConstituency = acValidVotesInConstituency + crossVotedMandalVO.getAcValidVotesInMandal();
			totalPolledVotesInAC = totalPolledVotesInAC + crossVotedMandalVO.getPolledVotes();
			crossVotedMandalVO.setCrossVotedBooths(crossVotedBooths);
			crossVotedMandalVOs.add(crossVotedMandalVO);
		}
		
		String acPercetageInConstituency = calculateVotesPercengate(acValidVotesInConstituency, acVotesEarnedInConstituency);
		String pcPercetageInConstituency = calculateVotesPercengate(pcValidVotesInConstituency, pcVotesEarnedInConstituency);
		Double percentageDifferenceInConstituency = new Double(acPercetageInConstituency) - new Double(pcPercetageInConstituency);
		Double  impactOfAssemblyOnParliament = (totalPolledVotesInAC*percentageDifferenceInConstituency)/crossVotingConsolidateVO.getTotalPCPolledVotesInConstituency();
		crossVotingConsolidateVO.setDifferenceInACAndPC(new BigDecimal(percentageDifferenceInConstituency).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		crossVotingConsolidateVO.setImpactOfAssemblyOnParliament(new BigDecimal(impactOfAssemblyOnParliament).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		pcCandidate.setVotesPercentage(pcPercetageInConstituency);
		acCandidate.setVotesPercentage(acPercetageInConstituency);
		calculateCrossVotingPercentageImpactByMandals(totalPolledVotesInAC, crossVotedMandalVOs);
		return crossVotedMandalVOs;
	}
	
	public void calculateCrossVotingPercentageImpactByMandals(Long polledVotesInConstituency, List<CrossVotedMandalVO> crossVotedMandalVOs) {
		for(CrossVotedMandalVO crossMandalVO:crossVotedMandalVOs){
			Long polledVotesInMandal = crossMandalVO.getPolledVotes();
			Double crossVotingPercentageInMandal = new Double(crossMandalVO.getPercentageDifferenceInMandal());
			Double percentageImpactOnConstituency = (polledVotesInMandal.longValue()*crossVotingPercentageInMandal)/polledVotesInConstituency;
			BigDecimal percentageImpactOnConstituencyRound = new BigDecimal(percentageImpactOnConstituency).setScale(2,BigDecimal.ROUND_HALF_UP);
			crossMandalVO.setPercentageImpactOnConstituency(percentageImpactOnConstituencyRound.toString());
		}
	}

	public List<CrossVotedBoothVO> getCrossVotingDetails(Long tehsilId, String electionYear, CrossVotedMandalVO crossVotedMandalVO, Long acId, Long pcId, Long acNominationId, Long pcNominationId){
		List<CrossVotedBoothVO> crossVotingInfoVOs = new ArrayList<CrossVotedBoothVO>();
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByElectionConstituencyAndTehsil(electionYear, tehsilId, acId);
		Long totalVotersInMandal = new Long(0);
		Long acValidVotesInMandal = new Long(0);
		Long acEarnedVotesInMandal = new Long(0);
		Long pcEarnedVotesInMandal = new Long(0);
		Long pcValidVotesInMandal = new Long(0);
		Long acTotalVotesPolledInMandal = new Long(0);
		CrossVotedBoothVO crossVotedBoothVO = null;		
		for(BoothConstituencyElection boothConstituencyElection:list){
			crossVotedBoothVO = new CrossVotedBoothVO();
			crossVotedBoothVO.setPartNO(boothConstituencyElection.getBooth().getPartNo());			
			crossVotedBoothVO.setVillagesCovered(boothConstituencyElection.getBooth().getvillagesCovered());
			Long totalVoters = boothConstituencyElection.getBooth().getTotalVoters();
			Long acValidVotes = boothConstituencyElection.getBoothResult().getValidVotes();
			Long acRejectedVotes = boothConstituencyElection.getBoothResult().getRejectedVotes();
			Long acTenderedVotes = boothConstituencyElection.getBoothResult().getTenderedVotes();
			List<CandidateBoothResult> acCandidateBoothResults = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(acNominationId, boothConstituencyElection.getBoothConstituencyElectionId());
			Long acEarnedVotes = acCandidateBoothResults.get(0).getVotesEarned();
			String acPercentage = calculateVotesPercengate(acValidVotes, acEarnedVotes);
			crossVotedBoothVO.setTotalVoters(totalVoters);
			crossVotedBoothVO.setAcValidVotes(acValidVotes);
			crossVotedBoothVO.setAcPercentage(acPercentage);
			List<BoothConstituencyElection> parliamentBoothResults = boothConstituencyElectionDAO.findByElectionConstituencyAndBooth(boothConstituencyElection.getBooth().getBoothId(), electionYear, pcId);
			Long pcValidVotes = parliamentBoothResults.get(0).getBoothResult().getValidVotes();
			List<CandidateBoothResult> pcCandidateBoothResults = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(pcNominationId, parliamentBoothResults.get(0).getBoothConstituencyElectionId());
			Long pcEarnedVotes = pcCandidateBoothResults.get(0).getVotesEarned();
			String pcPercentage = calculateVotesPercengate(pcValidVotes, pcEarnedVotes);
			crossVotedBoothVO.setPcValidVotes(pcValidVotes);
			crossVotedBoothVO.setPcPercentage(pcPercentage);
			Double percentageDifference = Double.parseDouble(acPercentage)-Double.parseDouble(pcPercentage);
			BigDecimal percentageDifferenceRound = new BigDecimal(percentageDifference).setScale(2,BigDecimal.ROUND_HALF_UP);			
			crossVotedBoothVO.setPercentageDifference(percentageDifferenceRound.toString());
			totalVotersInMandal = totalVotersInMandal + totalVoters;		
			acValidVotesInMandal = acValidVotesInMandal + acValidVotes;
			acEarnedVotesInMandal = acEarnedVotesInMandal + acEarnedVotes;
			pcValidVotesInMandal = pcValidVotesInMandal + pcValidVotes;
			pcEarnedVotesInMandal = pcEarnedVotesInMandal + pcEarnedVotes;
			Long totalVotesPolled = acValidVotes + acRejectedVotes + acTenderedVotes;
			acTotalVotesPolledInMandal = acTotalVotesPolledInMandal + totalVotesPolled;
			crossVotedBoothVO.setPolledVotes(totalVotesPolled);
			crossVotingInfoVOs.add(crossVotedBoothVO);
		}
		String acPercentageInMandal = calculateVotesPercengate(acValidVotesInMandal, acEarnedVotesInMandal);
		String pcPercentageInMandal = calculateVotesPercengate(pcValidVotesInMandal, pcEarnedVotesInMandal);
		Double percentageDifferenceInMandal = Double.parseDouble(acPercentageInMandal)-Double.parseDouble(pcPercentageInMandal);
		BigDecimal percentageDifferenceInMandalRound = new BigDecimal(percentageDifferenceInMandal).setScale(2,BigDecimal.ROUND_HALF_UP);
		crossVotedMandalVO.setEarnedVotesDiffernce(acEarnedVotesInMandal - pcEarnedVotesInMandal);
		crossVotedMandalVO.setAcEarnedVotesInMandal(acEarnedVotesInMandal);
		crossVotedMandalVO.setAcValidVotesInMandal(acValidVotesInMandal);
		crossVotedMandalVO.setAcPercentageInMandal(acPercentageInMandal);
		crossVotedMandalVO.setPcValidVotesInMandal(pcValidVotesInMandal);
		crossVotedMandalVO.setPcEarnedVotesInMandal(pcEarnedVotesInMandal);
		crossVotedMandalVO.setPcPercentageInMandal(pcPercentageInMandal);
		crossVotedMandalVO.setTotalVoters(totalVotersInMandal);
		crossVotedMandalVO.setPolledVotes(acTotalVotesPolledInMandal);
		crossVotedMandalVO.setPercentageDifferenceInMandal(percentageDifferenceInMandalRound.toString());
		return crossVotingInfoVOs;
	}
	
	public String calculateVotesPercengate(Long validVotes, Long votesEarned){
		BigDecimal percentage = new BigDecimal(0.0);
		if((validVotes!=null && validVotes.longValue()>0) && (votesEarned!=null && votesEarned.longValue()>0)){
			percentage= new BigDecimal((votesEarned.floatValue()/validVotes.floatValue())*100).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return percentage.toString();
	}	
	
	private String checkForCandidateWonOrLost(Nomination nomination){
		if(nomination.getCandidateResult().getRank().intValue() == 1)
			return "WON";
		else
			return "LOST";
	}
}
