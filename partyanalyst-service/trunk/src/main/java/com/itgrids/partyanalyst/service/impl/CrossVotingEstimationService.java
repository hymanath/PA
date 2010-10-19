package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Constituency> constituencies = boothConstituencyElectionDAO.findConstituencyByElectionYearAndElectionScope(electionYear, 
				electionScopeId);
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
			boothConstituencyElections = boothConstituencyElectionDAO.findByConstituencyIdAndElectionYear(constituencyId, 
					parliamentId, electionYear.toString());
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
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, 
			Long pcId, String includeAliance){
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
		acCandidate.setRank(acNomination.getCandidateResult().getRank().toString());
		acCandidate.setParty(acNomination.getParty().getShortName());
		
		Nomination pcNomination = pcNominations.get(0);
		pcCandidate.setCandidateId(pcNomination.getCandidate().getCandidateId());
		pcCandidate.setCandidateName(pcNomination.getCandidate().getLastname());
		pcCandidate.setImage(pcNomination.getCandidate().getImage());
		pcCandidate.setRank(pcNomination.getCandidateResult().getRank().toString());
		pcCandidate.setParty(pcNomination.getParty().getShortName());
		
		crossVotingConsolidateVO.setTotalACPolledVotesInConstituency(acNomination.getConstituencyElection().
				getConstituencyElectionResult().getTotalVotesPolled().longValue());
		crossVotingConsolidateVO.setTotalPCPolledVotesInConstituency(pcNomination.getConstituencyElection().
				getConstituencyElectionResult().getTotalVotesPolled().longValue());
		crossVotingConsolidateVO.setTotalVotersInAC(acNomination.getConstituencyElection().getConstituencyElectionResult().
				getTotalVotes().longValue());
		crossVotingConsolidateVO.setTotalVotersInPC(pcNomination.getConstituencyElection().getConstituencyElectionResult().
				getTotalVotes().longValue());
		crossVotingConsolidateVO.setAcCandidateData(acCandidate);
		crossVotingConsolidateVO.setPcCandidateData(pcCandidate);
		List<CrossVotedMandalVO> mandals = getTehsilsForConstituency(crossVotingConsolidateVO, electionYear, acId, pcId, 
				acNomination.getNominationId(), pcNomination.getNominationId());
		crossVotingConsolidateVO.setMandals(mandals);
		return crossVotingConsolidateVO;
	}
	
	public List<CrossVotedMandalVO> getTehsilsForConstituency(CrossVotingConsolidateVO crossVotingConsolidateVO, 
			String electionYear, Long acId, Long pcId, Long acNominationId, Long pcNominationId){
		List<CrossVotedMandalVO> crossVotedMandalVOs = new ArrayList<CrossVotedMandalVO>();
		List tehsils = boothDAO.findTehsilsByElectionAndConstituency(electionYear, acId);
		if(tehsils.size() > 0)
			getGroupResultsIntoMandalOrLocalBodywise(tehsils, crossVotedMandalVOs, acNominationId, 
					pcNominationId, acId, false, false);
		List municipalities = boothDAO.findLocalBodiesByElectionAndConstituency(electionYear, acId, "'"+IConstants.MUNCIPLE_ELECTION_TYPE+"'");
		if(municipalities.size() > 0)
			getGroupResultsIntoMandalOrLocalBodywise(municipalities, crossVotedMandalVOs, acNominationId, 
					pcNominationId, acId, true, true);
		List greaterOrCorps = boothDAO.findLocalBodyWardsByElectionAndConstituency(electionYear, acId, "'"+IConstants.CORPORATION_ELECTION_TYPE+"','"+
				IConstants.GREATER_ELECTION_TYPE+"'");
		if(greaterOrCorps.size() > 0)
			getGroupResultsIntoMandalOrLocalBodywise(greaterOrCorps, crossVotedMandalVOs, acNominationId, 
					pcNominationId, acId, true, false);
		
		Long acVotesEarnedInConstituency = 0l;
		Long acValidVotesInConstituency = 0l;
		Long pcVotesEarnedInConstituency = 0l;
		Long pcValidVotesInConstituency = 0l;
		Long acpcDiffenceInConstituency = 0l;
		for(CrossVotedMandalVO mandalInfo:crossVotedMandalVOs){
			acVotesEarnedInConstituency += mandalInfo.getAcEarnedVotesInMandal();
			acValidVotesInConstituency += mandalInfo.getAcValidVotesInMandal();
			pcVotesEarnedInConstituency += mandalInfo.getPcEarnedVotesInMandal();
			pcValidVotesInConstituency += mandalInfo.getPcValidVotesInMandal();
		}
		
		for(CrossVotedMandalVO mandalInfo:crossVotedMandalVOs)
			mandalInfo.setPercentageImpactOnConstituency(new BigDecimal(mandalInfo.getEarnedVotesDiffernce()*100.0/acValidVotesInConstituency).
					setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
		acpcDiffenceInConstituency = acVotesEarnedInConstituency - pcVotesEarnedInConstituency;

		crossVotingConsolidateVO.getAcCandidateData().setVotesEarned(acVotesEarnedInConstituency);
		crossVotingConsolidateVO.getAcCandidateData().setPolledVotes(acValidVotesInConstituency);
		crossVotingConsolidateVO.getAcCandidateData().setVotesPercentage(new BigDecimal(acVotesEarnedInConstituency*100.0/acValidVotesInConstituency).
				setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		crossVotingConsolidateVO.getPcCandidateData().setVotesEarned(pcVotesEarnedInConstituency);
		crossVotingConsolidateVO.getPcCandidateData().setPolledVotes(pcValidVotesInConstituency);
		crossVotingConsolidateVO.getPcCandidateData().setVotesPercentage(new BigDecimal(pcVotesEarnedInConstituency*100.0/pcValidVotesInConstituency).
				setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		crossVotingConsolidateVO.setDifferenceInACAndPC(new BigDecimal(acpcDiffenceInConstituency*100.0/acValidVotesInConstituency).
				setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		crossVotingConsolidateVO.setImpactOfAssemblyOnParliament(new BigDecimal(acpcDiffenceInConstituency*100.0/crossVotingConsolidateVO.getTotalPCPolledVotesInConstituency()).
				setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
		
		return crossVotedMandalVOs;
	}
	
	public void getGroupResultsIntoMandalOrLocalBodywise(List tehsils, List<CrossVotedMandalVO> crossVotedMandalVOs, 
			Long acNominationId, Long pcNominationId, Long acId, Boolean isUrban, Boolean isMunicipal){
		Long tehsilId = 0l;
		String tehsilName = "";
		CrossVotedMandalVO crossVotedMandalVO = null;
		Map<Long, Object[]> assemblyInfoMap = null;
		Map<Long, Object[]> parliamentInfoMap = null;
		for(Object[] values:(List<Object[]>)tehsils){
			tehsilId = (Long)values[0];
			
			if(isUrban)
				tehsilName = values[1]+" "+values[2];
			else
				tehsilName = values[1].toString();
			
			crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalId(tehsilId);
			crossVotedMandalVO.setMandalName(tehsilName);
			
			if(!isUrban){
				List assemblyInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInMandalWithInConstituency(acNominationId, acId, tehsilId);
				List parliamentInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInMandalWithInConstituency(pcNominationId, acId, tehsilId);
				assemblyInfoMap = getConsolidatedMap(assemblyInfo);
				parliamentInfoMap = getConsolidatedMap(parliamentInfo);
			}else if(isMunicipal){
				List assemblyInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(acNominationId, acId, tehsilId);
				List parliamentInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(pcNominationId, acId, tehsilId);
				assemblyInfoMap = getConsolidatedMap(assemblyInfo);
				parliamentInfoMap = getConsolidatedMap(parliamentInfo);
			}else{
				List assemblyInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(acNominationId, acId, tehsilId);
				List parliamentInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(pcNominationId, acId, tehsilId);
				assemblyInfoMap = getConsolidatedMap(assemblyInfo);
				parliamentInfoMap = getConsolidatedMap(parliamentInfo);
			}
			crossVotedMandalVO.setCrossVotedBooths(getCrossVotingDetails(assemblyInfoMap, parliamentInfoMap, crossVotedMandalVO));
			crossVotedMandalVOs.add(crossVotedMandalVO);
		}
	}

	private Map<Long, Object[]> getConsolidatedMap(List assemblyInfo) {
		Map<Long, Object[]> resultsMap = new HashMap<Long, Object[]>();
		for(Object[] values:(List<Object[]>)assemblyInfo)
			resultsMap.put((Long)values[0], values);

		return resultsMap;
	}

	public List<CrossVotedBoothVO> getCrossVotingDetails(Map<Long, Object[]> assemblyInfoMap, Map<Long, Object[]> parliamentInfoMap,
			CrossVotedMandalVO crossVotedMandalVO){
		List<CrossVotedBoothVO> crossVotingInfoVOs = new ArrayList<CrossVotedBoothVO>();
		CrossVotedBoothVO crossVotedBoothVO = null;
		Object[] acRawData = null;
		Object[] pcRawData = null;
		Long totalVotersInMandal = 0l;
		Long acValidVotesInMandal = 0l;
		Long acEarnedVotesInMandal = 0l;
		Long pcEarnedVotesInMandal = 0l;
		Long pcValidVotesInMandal = 0l;
		Long totalVoters = 0l;
		Long acValidVotes = 0l;
		Long acEarnedVotes = 0l;
		Long pcValidVotes = 0l;
		Long pcEarnedVotes = 0l;
		String acPercentage = "";
		String pcPercentage = "";
		String percentageDiff = ""; 
		
		for(Map.Entry<Long, Object[]> entry:assemblyInfoMap.entrySet()){
			acRawData = entry.getValue();
			pcRawData = parliamentInfoMap.get(entry.getKey());
			totalVoters = (Long)acRawData[3];
			acValidVotes = (Long)acRawData[4];
			acEarnedVotes = (Long)acRawData[5];
			pcValidVotes = (Long)pcRawData[4];
			pcEarnedVotes = (Long)pcRawData[5];
			acPercentage = new BigDecimal(acEarnedVotes*100.0/acValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			pcPercentage = new BigDecimal(pcEarnedVotes*100.0/pcValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			percentageDiff = new BigDecimal(Double.parseDouble(acPercentage)-Double.parseDouble(pcPercentage)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			
			crossVotedBoothVO = new CrossVotedBoothVO();
			crossVotedBoothVO.setBoothId((Long)acRawData[0]);
			crossVotedBoothVO.setPartNO(acRawData[1].toString());			
			crossVotedBoothVO.setVillagesCovered(acRawData[2].toString());
			crossVotedBoothVO.setTotalVoters(totalVoters);
			crossVotedBoothVO.setAcValidVotes(acValidVotes);
			crossVotedBoothVO.setAcPercentage(acPercentage);
			crossVotedBoothVO.setAcVotesEarned(acEarnedVotes);
			crossVotedBoothVO.setPcValidVotes(pcValidVotes);
			crossVotedBoothVO.setPcPercentage(pcPercentage);
			crossVotedBoothVO.setPcVotesEarned(pcEarnedVotes);
			crossVotedBoothVO.setPercentageDifference(percentageDiff);
			crossVotedBoothVO.setPolledVotes((Long)acRawData[4]);
			crossVotingInfoVOs.add(crossVotedBoothVO);
			
			totalVotersInMandal = totalVotersInMandal + totalVoters;		
			acValidVotesInMandal = acValidVotesInMandal + acValidVotes;
			acEarnedVotesInMandal = acEarnedVotesInMandal + acEarnedVotes;
			pcValidVotesInMandal = pcValidVotesInMandal + pcValidVotes;
			pcEarnedVotesInMandal = pcEarnedVotesInMandal + pcEarnedVotes;
		}
		
		String acPercentageInMandal = new BigDecimal(acEarnedVotesInMandal*100.0/acValidVotesInMandal).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String pcPercentageInMandal = new BigDecimal(pcEarnedVotesInMandal*100.0/pcValidVotesInMandal).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String percentageDiffInMandal = new BigDecimal(Double.parseDouble(acPercentageInMandal)-Double.parseDouble(pcPercentageInMandal)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		crossVotedMandalVO.setEarnedVotesDiffernce(acEarnedVotesInMandal - pcEarnedVotesInMandal);
		crossVotedMandalVO.setAcEarnedVotesInMandal(acEarnedVotesInMandal);
		crossVotedMandalVO.setAcValidVotesInMandal(acValidVotesInMandal);
		crossVotedMandalVO.setAcPercentageInMandal(acPercentageInMandal);
		crossVotedMandalVO.setPcValidVotesInMandal(pcValidVotesInMandal);
		crossVotedMandalVO.setPcEarnedVotesInMandal(pcEarnedVotesInMandal);
		crossVotedMandalVO.setPcPercentageInMandal(pcPercentageInMandal);
		crossVotedMandalVO.setTotalVoters(totalVotersInMandal);
		crossVotedMandalVO.setPolledVotes(acValidVotesInMandal);
		crossVotedMandalVO.setPercentageDifferenceInMandal(percentageDiffInMandal);
		
		return crossVotingInfoVOs;
	}

}
