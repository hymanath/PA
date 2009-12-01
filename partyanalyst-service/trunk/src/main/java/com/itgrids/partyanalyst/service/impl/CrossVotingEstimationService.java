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
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
			constituencyVOs.add(constituencyVO);
		}
		return constituencyVOs;
	}
	
	public List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear){
		List<Constituency> constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(parliamentId, electionYear);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies){
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
			constituencyVOs.add(constituencyVO);
		}
		return constituencyVOs;
	}
	
	//Getting Parties From CandidateBoothResult
	public List<SelectOptionVO> getPartiesForConstituencyAndElectionYearForBoothData(Long constituencyId, String electionYear){
		List<Party> list = candidateBoothResultDAO.findPartiesByConstituencyAndElectionYear(constituencyId, electionYear);
		List<SelectOptionVO> partyVOs = new ArrayList<SelectOptionVO>();
		for(Party party:list){
			SelectOptionVO delimitationConstituencyVO = new SelectOptionVO(party.getPartyId(), party.getShortName());
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
			if(log.isInfoEnabled()){
				log.debug("\n====================in True Block-----"+electionYear + partyId);
			}
			List<SelectOptionVO> alianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, new Long(2), partyId);
			
			if(alianceParties == null || alianceParties.size() == 0){
				crossVotingConsolidateVO.setHasAlliance(false);
				return crossVotingConsolidateVO;
			}
			List<Long> aliancePartyIds = new ArrayList<Long>();
			for(SelectOptionVO alianceParty:alianceParties){
				aliancePartyIds.add(alianceParty.getId());
			}
			if(log.isInfoEnabled()){
				log.debug("\n====================Aliance Parties Ids Size::-----"+aliancePartyIds.size());
			}
			pcNominations = nominationDAO.findByConstituencyPartyAndElectionYearIncludingAliance(aliancePartyIds, pcId, electionYear);
			if(log.isInfoEnabled()){
				log.debug("\n=================Nominations with aliance Size::-----"+pcNominations.size());
			}
		}else{
			pcNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, pcId, electionYear);
		}						
		if(((acNominations.size() != 1)||(pcNominations.size() != 1))&& includeAliance.equals("false")){
			if(pcNominations.size() == 0){
				crossVotingConsolidateVO.setPartyPartisipated(false);
				return crossVotingConsolidateVO;
			}
			if(log.isInfoEnabled()){
				log.debug("--------Exists More than One Nominations For the Give Cryteria-----");
			}	
		}
		Nomination acNomination = acNominations.get(0);
		acCandidate.setCandidateId(acNomination.getCandidate().getCandidateId());
		acCandidate.setCandidateName(acNomination.getCandidate().getLastname());
		acCandidate.setImage(acNomination.getCandidate().getImage());
		acCandidate.setRank(checkForCandidateWonOrLost(acNomination));
		acCandidate.setParty(acNomination.getParty().getLongName());
		acCandidate.setVotesPercentage(acNomination.getCandidateResult().getVotesPercengate());
		acCandidate.setVotesEarned(new Long(acNomination.getCandidateResult().getVotesEarned().longValue()));
		Nomination pcNomination = pcNominations.get(0);
		pcCandidate.setCandidateId(pcNomination.getCandidate().getCandidateId());
		pcCandidate.setCandidateName(pcNomination.getCandidate().getLastname());
		pcCandidate.setImage(pcNomination.getCandidate().getImage());
		pcCandidate.setRank(checkForCandidateWonOrLost(pcNomination));
		pcCandidate.setParty(pcNomination.getParty().getLongName());
		crossVotingConsolidateVO.setAcCandidateData(acCandidate);
		crossVotingConsolidateVO.setPcCandidateData(pcCandidate);
		List<CrossVotedMandalVO> mandals = getTehsilsForConstituency(acCandidate, pcCandidate, electionYear, partyId, acId, pcId, acNomination.getNominationId(), pcNomination.getNominationId());
		crossVotingConsolidateVO.setMandals(mandals);
		return crossVotingConsolidateVO;
	}
	
	public List<CrossVotedMandalVO> getTehsilsForConstituency(CrossVotedCandidateVO acCandidate, CrossVotedCandidateVO pcCandidate, String electionYear, Long partyId, Long assemblyConstituencyId, Long parliamentConstituencyId, Long acNominationId, Long pcNominationId){
		List<CrossVotedMandalVO> crossVotedMandalVOs = new ArrayList<CrossVotedMandalVO>();
		List<Tehsil> tehsils = boothDAO.findTehsilsByElectionAndConstituency(electionYear, assemblyConstituencyId);
		long beginTimeMillis = System.currentTimeMillis();
		Long acVotesEarnedInConstituency = acCandidate.getVotesEarned();
		Long pcVotesEarnedInConstituency = new Long(0);
		Long pcValidVotesInConstituency = new Long(0);
		CrossVotedMandalVO crossVotedMandalVO = null;
		for(Tehsil tehsil:tehsils){
			crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalName(tehsil.getTehsilName());
			List<CrossVotedBoothVO> crossVotedBooths = getCrossVotingDetails(tehsil.getTehsilId(), electionYear, crossVotedMandalVO, assemblyConstituencyId, parliamentConstituencyId, acNominationId, pcNominationId);
			pcVotesEarnedInConstituency = pcVotesEarnedInConstituency + crossVotedMandalVO.getPcEarnedVotesInMandal();
			pcValidVotesInConstituency = pcValidVotesInConstituency + crossVotedMandalVO.getPcValidVotesInMandal();
			crossVotedMandalVO.setCrossVotedBooths(crossVotedBooths);
			crossVotedMandalVOs.add(crossVotedMandalVO);
		}
		
		long endTimeMillis = System.currentTimeMillis();
		if(log.isInfoEnabled()){
			log.info("Total time taken:" + (endTimeMillis-beginTimeMillis)/1000);
			log.info("IN getTehsilsForConstituency "+tehsils.size());
		}	
		Long earnedVotesDifferenceInConstituency = acVotesEarnedInConstituency - pcVotesEarnedInConstituency;
		String acPercetageInConstituency = acCandidate.getVotesPercentage();
		String pcPercetageInConstituency = calculateVotesPercengate(pcValidVotesInConstituency, pcVotesEarnedInConstituency);
		acCandidate.setVotesPercentage(acPercetageInConstituency);
		pcCandidate.setVotesPercentage(pcPercetageInConstituency);
		Double percentageDifferenceInConstituency = Double.parseDouble(acPercetageInConstituency)-Double.parseDouble(pcPercetageInConstituency);
		calculateCrossVotingPercentageImpactOnMandals(earnedVotesDifferenceInConstituency, percentageDifferenceInConstituency, crossVotedMandalVOs);
		return crossVotedMandalVOs;
	}
	
	public void calculateCrossVotingPercentageImpactOnMandals(Long earnedVotesDifferenceInConstituency, Double percentageDifferenceInConstituencyRound, List<CrossVotedMandalVO> crossVotedMandalVOs) {
		for(CrossVotedMandalVO crossMandalVO:crossVotedMandalVOs){
			Long earnedVotesDifferenceInMandal = crossMandalVO.getEarnedVotesDiffernce();
			Double percentageImpactOnConstituency = (earnedVotesDifferenceInMandal.longValue()*percentageDifferenceInConstituencyRound.doubleValue())/earnedVotesDifferenceInConstituency.longValue();
			BigDecimal percentageImpactOnConstituencyRound = new BigDecimal(percentageImpactOnConstituency).setScale (2,BigDecimal.ROUND_HALF_UP);
			crossMandalVO.setPercentageImpactOnConstituency(percentageImpactOnConstituencyRound.toString());
		}
	}

	public List<CrossVotedBoothVO> getCrossVotingDetails(Long tehsilId, String electionYear, CrossVotedMandalVO crossVotedMandalVO, Long acId, Long pcId, Long acNominationId, Long pcNominationId){
		List<CrossVotedBoothVO> crossVotingInfoVOs = new ArrayList<CrossVotedBoothVO>();
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByElectionConstituencyAndTehsil(electionYear, tehsilId, acId);
		Long acValidVotesInMandal = new Long(0);
		Long acEarnedVotesInMandal = new Long(0);
		Long pcEarnedVotesInMandal = new Long(0);
		Long pcValidVotesInMandal = new Long(0);
		CrossVotedBoothVO crossVotedBoothVO = null;		
		for(BoothConstituencyElection boothConstituencyElection:list){
			crossVotedBoothVO = new CrossVotedBoothVO();
			crossVotedBoothVO.setPartNO(boothConstituencyElection.getBooth().getPartNo());
			crossVotedBoothVO.setVillagesCovered(boothConstituencyElection.getBooth().getvillagesCovered());
			Long acValidVotes = boothConstituencyElection.getBoothResult().getValidVotes();
			List<CandidateBoothResult> acCandidateBoothResults = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(acNominationId, boothConstituencyElection.getBoothConstituencyElectionId());
			Long acEarnedVotes = acCandidateBoothResults.get(0).getVotesEarned();
			String acPercentage = calculateVotesPercengate(acValidVotes, acEarnedVotes);	
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
			BigDecimal percentageDifferenceRound = new BigDecimal(percentageDifference).setScale (2,BigDecimal.ROUND_HALF_UP);			
			crossVotedBoothVO.setPercentageDifference(percentageDifferenceRound.toString());
			acValidVotesInMandal = acValidVotesInMandal + acValidVotes;
			acEarnedVotesInMandal = acEarnedVotesInMandal + acEarnedVotes;
			pcValidVotesInMandal = pcValidVotesInMandal + pcValidVotes;
			pcEarnedVotesInMandal = pcEarnedVotesInMandal + pcEarnedVotes;
			crossVotingInfoVOs.add(crossVotedBoothVO);
		}
		String acPercentageInMandal = calculateVotesPercengate(acValidVotesInMandal, acEarnedVotesInMandal);
		String pcPercentageInMandal = calculateVotesPercengate(pcValidVotesInMandal, pcEarnedVotesInMandal);
		Double percentageDifferenceInMandal = Double.parseDouble(acPercentageInMandal)-Double.parseDouble(pcPercentageInMandal);
		BigDecimal percentageDifferenceInMandalRound = new BigDecimal(percentageDifferenceInMandal).setScale (2,BigDecimal.ROUND_HALF_UP);
		crossVotedMandalVO.setEarnedVotesDiffernce(acEarnedVotesInMandal - pcEarnedVotesInMandal);
		crossVotedMandalVO.setAcEarnedVotesInMandal(acEarnedVotesInMandal);
		crossVotedMandalVO.setAcValidVotesInMandal(acValidVotesInMandal);
		crossVotedMandalVO.setAcPercentageInMandal(acPercentageInMandal);
		crossVotedMandalVO.setPcValidVotesInMandal(pcValidVotesInMandal);
		crossVotedMandalVO.setPcEarnedVotesInMandal(pcEarnedVotesInMandal);
		crossVotedMandalVO.setPcPercentageInMandal(pcPercentageInMandal);
		crossVotedMandalVO.setPercentageDifferenceInMandal(percentageDifferenceInMandalRound.toString());
		return crossVotingInfoVOs;
	}
	
	public String calculateVotesPercengate(Long validVotes, Long votesEarned){
		BigDecimal percentage = new BigDecimal(0.0);
		if((validVotes!=null && validVotes.longValue()>0) && (votesEarned!=null && votesEarned.longValue()>0)){
			percentage= new BigDecimal((votesEarned.floatValue()/validVotes.floatValue())*100).setScale (2,BigDecimal.ROUND_HALF_UP);
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
