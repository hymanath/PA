package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;
import com.itgrids.partyanalyst.dto.CrossVotedCandidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.CrossVotingVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
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
	private	IUserCountryAccessInfoDAO userCountryAccessInfoDAO;
	private	IUserStateAccessInfoDAO userStateAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO  constituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public IUserCountryAccessInfoDAO getUserCountryAccessInfoDAO() {
		return userCountryAccessInfoDAO;
	}

	public void setUserCountryAccessInfoDAO(
			IUserCountryAccessInfoDAO userCountryAccessInfoDAO) {
		this.userCountryAccessInfoDAO = userCountryAccessInfoDAO;
	}

	public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
		return userStateAccessInfoDAO;
	}

	public void setUserStateAccessInfoDAO(
			IUserStateAccessInfoDAO userStateAccessInfoDAO) {
		this.userStateAccessInfoDAO = userStateAccessInfoDAO;
	}

	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
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
	
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
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
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), WordUtils.capitalize(constituency.getName().toLowerCase()));
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
			constituencyVOs.add(new SelectOptionVO(constituencyId, WordUtils.capitalize(constituency.getName().toLowerCase())));
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
			List<SelectOptionVO> alianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, new Long(2), partyId,0L);
			
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
		
		//Calculating Unmapped Booths Results Info For Urban Areas.
		calculateUnmappedBoothsResults(crossVotedMandalVOs, acNominationId, pcNominationId, acId, electionYear);
		
		Long acVotesEarnedInConstituency = 0l;
		Long acValidVotesInConstituency = 0l;
		Long pcVotesEarnedInConstituency = 0l;
		Long pcValidVotesInConstituency = 0l;
		Long acpcDiffenceInConstituency = 0l;
		for(CrossVotedMandalVO mandalInfo:crossVotedMandalVOs){
			if(mandalInfo.getAcEarnedVotesInMandal()!=null)
			   acVotesEarnedInConstituency += mandalInfo.getAcEarnedVotesInMandal();
			if(mandalInfo.getAcValidVotesInMandal() !=null)
			  acValidVotesInConstituency += mandalInfo.getAcValidVotesInMandal();
			if(mandalInfo.getPcEarnedVotesInMandal()!=null)
			  pcVotesEarnedInConstituency += mandalInfo.getPcEarnedVotesInMandal();
			if(mandalInfo.getPcValidVotesInMandal() !=null)
			 pcValidVotesInConstituency += mandalInfo.getPcValidVotesInMandal();
		}
		
		for(CrossVotedMandalVO mandalInfo:crossVotedMandalVOs)
		{
			if(acValidVotesInConstituency !=null && mandalInfo.getEarnedVotesDiffernce()!=null)
			{
				mandalInfo.setPercentageImpactOnConstituency(new BigDecimal(mandalInfo.getEarnedVotesDiffernce()*100.0/acValidVotesInConstituency).
						setScale(2, BigDecimal.ROUND_HALF_UP).toString());				
			}
		}
			
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
	
	private void calculateUnmappedBoothsResults(
			List<CrossVotedMandalVO> crossVotedMandalVOs, Long acNominationId,
			Long pcNominationId, Long acId, String electionYear) {
		List acUnmappedInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(acNominationId, acId, electionYear);
		List pcUnmappedInfo = candidateBoothResultDAO.getBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(pcNominationId, acId, electionYear);
		if((acUnmappedInfo.size() == pcUnmappedInfo.size()) && acUnmappedInfo.size() > 0){
			CrossVotedMandalVO crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalId(0l);
			crossVotedMandalVO.setMandalName("Unmapped Booths");
			Map<Long, Object[]> assemblyInfoMap = getConsolidatedMap(acUnmappedInfo);
			Map<Long, Object[]> parliamentInfoMap = getConsolidatedMap(pcUnmappedInfo);
			crossVotedMandalVO.setCrossVotedBooths(getCrossVotingDetails(assemblyInfoMap, parliamentInfoMap, crossVotedMandalVO));
			crossVotedMandalVOs.add(crossVotedMandalVO);	
		}
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
			
			if((assemblyInfoMap.size() == parliamentInfoMap.size()) && parliamentInfoMap.size() > 0)
				crossVotedMandalVO.setCrossVotedBooths(getCrossVotingDetails(assemblyInfoMap, parliamentInfoMap, crossVotedMandalVO));
			else
				crossVotedMandalVO.setCrossVotedBooths(new ArrayList<CrossVotedBoothVO>());
			
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
			CrossVotedMandalVO crossVotedMandalVO)
	{
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
		try{
		for(Map.Entry<Long, Object[]> entry:assemblyInfoMap.entrySet()){
			
			acRawData = entry.getValue();
			pcRawData = parliamentInfoMap.get(entry.getKey());
			totalVoters = (Long)acRawData[3];
			acValidVotes = (Long)acRawData[4];
			acEarnedVotes = (Long)acRawData[5];
			pcValidVotes = (Long)pcRawData[4];
			pcEarnedVotes = (Long)pcRawData[5];
			
			try
			{
				acPercentage = new BigDecimal(acEarnedVotes*100.0/acValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				pcPercentage = new BigDecimal(pcEarnedVotes*100.0/pcValidVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				percentageDiff = new BigDecimal(Double.parseDouble(acPercentage)-Double.parseDouble(pcPercentage)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}catch (Exception e) {
				percentageDiff = "0.0";
				log.error("Exception Occured - "+e);
			}
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
		}catch(Exception e){
			log.error("Exception Occured in getCrossVotingDetails(), Exception is - "+e);
			return crossVotingInfoVOs;
		}
		return crossVotingInfoVOs;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getConstituenciesForElectionYearAndTypeWithUserAccess(Long userId,Long electionYear,Long electionTypeId)
	{
		try{
			
			List<SelectOptionVO> constituenciesList = new ArrayList<SelectOptionVO>(0);
			
			List<Object[]> countryList = (List<Object[]>)userCountryAccessInfoDAO.findByUser(userId);
			List<Object[]> stateList = (List<Object[]>)userStateAccessInfoDAO.findByUser(userId);
			List<Object[]> districtList = (List<Object[]>)userDistrictAccessInfoDAO.findByUser(userId);
			List<Object[]> conList = (List<Object[]>)userConstituencyAccessInfoDAO.findByUser(userId);
			
			if(countryList != null && countryList.size() > 0 )
			{
				Long countryId = (Long)(countryList.get(0)[0]);
				List<Object[]>constList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInCountry(electionTypeId,countryId, electionYear);
				
				SelectOptionVO selectOptionVO = null;
				for(Object[] param : constList)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)param[0]);
					selectOptionVO.setName(param[1].toString());
					constituenciesList.add(selectOptionVO);
				}
			}
			
			if(stateList != null && stateList.size() > 0)
			{
				for(Object[] stList : stateList)
				{
					Long stateId = (Long)stList[0];
					List<Object[]>constList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInState(electionTypeId,stateId, electionYear);
					SelectOptionVO selectOptionVO = null;
					for(Object[] param : constList)
					{
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)param[0]);
						selectOptionVO.setName(param[1].toString());
						constituenciesList.add(selectOptionVO);
					}
				}
			}
			
			if(districtList != null && districtList.size() > 0)
			{
				for(Object[] disList : districtList)
				{
					Long districtId = (Long)disList[0];
					List<Object[]>constList = null;
					
					if(electionTypeId.intValue() == 1)
						constList = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(districtId, electionYear);
							
					else if(electionTypeId.intValue() == 2)
						constList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYearInADistrict(electionTypeId,districtId, electionYear);
									
					SelectOptionVO selectOptionVO = null;
					for(Object[] param : constList)
					{
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)param[0]);
						selectOptionVO.setName(param[1].toString());
						constituenciesList.add(selectOptionVO);
					}
				}
			}
			
			if(conList != null && conList.size() > 0)
			{
				if(electionTypeId.intValue() == 1)
				{
					for(Object[] consList : conList)
					{
						Long constuencyId = (Long)consList[0];
						List<Object[]>constList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeAndYear(electionTypeId,constuencyId,electionYear);
						
						SelectOptionVO selectOptionVO = null;
						for(Object[] param : constList)
						{
							selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)param[0]);
							selectOptionVO.setName(param[1].toString());
							constituenciesList.add(selectOptionVO);
						}
					}
				}
				
				else if(electionTypeId.intValue() == 2)
				{
					for(Object[] consList : conList)
					{
						if(consList[2].toString().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
						{
							Long pcId = (Long)consList[0];
							List<Object[]> constList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(pcId);
							SelectOptionVO selectOptionVO = null;
							for(Object[] param : constList)
							{
								selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId((Long)param[0]);
								selectOptionVO.setName(param[1].toString());
								constituenciesList.add(selectOptionVO);
							}
						}
						else if(consList[2].toString().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
							constituenciesList.add(new SelectOptionVO((Long)consList[0],consList[1].toString()));
						
					}
				}
			}
						
			return constituenciesList;
		}catch(Exception e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getElectionYearsForBoothResult()
	{
		try{
			List<String> yearList = new ArrayList<String>(0);
			List<Object> list = boothConstituencyElectionDAO.getElectionYears();
			
			for(Object obj : list)
				yearList.add(obj.toString());
			
			return yearList;
		}catch(Exception e)
		{
			return null;
		}
	}

	public List<SelectOptionVO> getAllOptions(String type,Long stateId,Long electionType,Long electionId){
		
		List<Object[]> data = null;
		List<SelectOptionVO> options = new ArrayList<SelectOptionVO>();
		try{
		  if(type.equalsIgnoreCase("states")){
			
			data = boothConstituencyElectionDAO.getBoothResultsContainStates();
			
		  }else if(type.equalsIgnoreCase("years")){
			
			data = boothConstituencyElectionDAO.getElectionYears(stateId,electionType);
			
		  }else if(type.equalsIgnoreCase("constituencies")){
			
			data = boothConstituencyElectionDAO.getConstituencies(electionId);
			
		  }
		
		   if(!data.isEmpty()){
			   SelectOptionVO selectOptionVO = null;
			   for(Object[] option:data){
				   selectOptionVO = new SelectOptionVO();
				   selectOptionVO.setId((Long)option[0]);
				   selectOptionVO.setName(option[1] != null? option[1].toString():"");
				   options.add(selectOptionVO);
			   }
		   }
		}catch(Exception e){
			log.error("Exception rised in getAllOptions method : "+e);
		}
		return options;
	}
	
	public List<SelectOptionVO> getTehsilsForConstituencies(List<Long> constituenyIds)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		String constituencyType = "";
		Map<Long,String> constituencyMap = new HashMap<Long, String>();//Map<constituencyId,constituencyType>
		List<Long> ruralConstituencyIds = new ArrayList<Long>();
		List<Long> urbanConstituencyIds = new ArrayList<Long>();
		List<Long> ruralUrbanConstituencyIds = new ArrayList<Long>();
		List<Object[]> constituenyList = constituencyDAO.getConstituencyTypeByConstituencyList(constituenyIds);
		if(constituenyList != null && constituenyList.size() >0)
		{
			for (Object[] parms : constituenyList) {
				constituencyMap.put((Long)parms[0], parms[1].toString());
			}
		}
		if(constituenyIds != null && constituenyIds.size() > 0)
		{
			for (Long constituencyId : constituenyIds) {
				if(constituencyId != 0)
				{
					constituencyType = constituencyMap.get(constituencyId);
					if(constituencyType.equalsIgnoreCase(IConstants.RURAL))
					{
						ruralConstituencyIds.add(constituencyId);
					}
					else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
					{
						urbanConstituencyIds.add(constituencyId);
					}
					else
					{
						ruralUrbanConstituencyIds.add(constituencyId);
					}
				}
				
			}
		}
		if(ruralConstituencyIds != null && ruralConstituencyIds.size() >0)
		{
			List<Object[]> ruralConstituencyList = delimitationConstituencyMandalDAO.getTehsilsForRuralConstituencyes(ruralConstituencyIds,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
			if(ruralConstituencyList != null && ruralConstituencyList.size() > 0)
			{
				for (Object[] parms : ruralConstituencyList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
		}
		
		if(urbanConstituencyIds != null && urbanConstituencyIds.size() > 0)
		{
			List<Object[]> urbanConstituencyList = assemblyLocalElectionBodyDAO.getTehsilsForUrbanConstituency(urbanConstituencyIds,IConstants.PRESENT_ELECTION_YEAR);
			if(urbanConstituencyList != null && urbanConstituencyList.size() > 0)
			{
				for (Object[] parms : urbanConstituencyList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
		}
		
		if(ruralUrbanConstituencyIds != null && ruralUrbanConstituencyIds.size() >0)
		{
			List<Object[]> ruralConstituencyList = delimitationConstituencyMandalDAO.getTehsilsForRuralConstituencyes(ruralUrbanConstituencyIds,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
			if(ruralConstituencyList != null && ruralConstituencyList.size() > 0)
			{
				for (Object[] parms : ruralConstituencyList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
			
			List<Object[]> urbanConstituencyList = assemblyLocalElectionBodyDAO.getTehsilsForUrbanConstituency(ruralUrbanConstituencyIds,IConstants.PRESENT_ELECTION_YEAR);
			if(urbanConstituencyList != null && urbanConstituencyList.size() > 0)
			{
				for (Object[] parms : urbanConstituencyList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
			}
		}
		Collections.sort(returnList,sortIds);
		return returnList;
	}
	
	 public static Comparator<SelectOptionVO> sortIds = new Comparator<SelectOptionVO>()
    {
   
        public int compare(SelectOptionVO selectOptionVO1, SelectOptionVO selectOptionVO2)
        {
            return (selectOptionVO1.getId().intValue()) - (selectOptionVO2.getId().intValue());
        }
    };
    
    public List<SelectOptionVO> getPanchayatsForConstituencyList(List<Long> tehsilIds)
    {
    	List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
    	if(tehsilIds != null && tehsilIds.size() > 0)
    	{
    		List<Object[]> tehsilList = panchayatDAO.getPanchaytsForConstituencyList(tehsilIds);
    		if(tehsilList != null && tehsilList.size() > 0)
    		{
    			for (Object[] parms : tehsilList) {
    				SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnList.add(selectOptionVO);
				}
    		}
    	}
    	Collections.sort(returnList,sortIds);
    	return returnList;
    }
    
    public List<SelectOptionVO> getBoothsForConstituencyList(List<Long> constituencyIds)
    {
    	List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
    	if(constituencyIds != null && constituencyIds.size() > 0)
    	{
    		List<Object[]> boothsList = boothDAO.getBoothsForConstituencyList(constituencyIds);
    		if(boothsList != null && boothsList.size() > 0)
    		{
    			for (Object[] parms : boothsList) {
    				SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[2].toString()+"-"+ parms[1].toString());
					returnList.add(selectOptionVO);
				}
    		}
    	}
    	Collections.sort(returnList,sortIds);
    	return returnList;
    }
    
    public CrossVotingVO getElectionYearsForCrossVotingAnalysis(List<Long> assemblyIds){
    	CrossVotingVO returnValue = new CrossVotingVO();
    	try{
	    	List<SelectOptionVO> years = getAllElectionYearsForCrossVoting(assemblyIds);
	    	if(years != null && years.size() > 0){
		    	List<SelectOptionVO> parliamentVos = getAllParliamentConstituenciesForCrossVoting(assemblyIds,years.get(0).getName());
		    	 List<SelectOptionVO> assemblyVos = new ArrayList<SelectOptionVO>();
		    	 List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
		    	if(parliamentVos.size() > 0)
		    	  assemblyVos = getAllAssemblyConstituenciesForCrossVoting(assemblyIds,parliamentVos.get(0).getId(),years.get(0).getName());
		    	if(assemblyVos.size() > 0)
		    	  partiesList = getPartiesForConstituencyAndElectionYearForBoothData(assemblyVos.get(0).getId(),years.get(0).getName());
		    	
		    	years.add(0,new SelectOptionVO(0l,"Select Year"));
		    	returnValue.setYearsList(years);
		    	
		    	parliamentVos.add(0,new SelectOptionVO(0l,"Select Location"));
		    	returnValue.setParliamentLists(parliamentVos);
		    	
		    	assemblyVos.add(0,new SelectOptionVO(0l,"Select Location"));
		    	returnValue.setAssemblyList(assemblyVos);
		    	
		    	if(partiesList != null)
			      partiesList.add(0,new SelectOptionVO(0l,"Select Party"));
			      returnValue.setPartiesList(partiesList);
	    	}
    	}catch(Exception e){
    		log.error("Exception rised in getElectionYearsForCrossVotingAnalysis method : ",e);
    	}
    	return returnValue;
    }
    
   /* public List<SelectOptionVO> getParliamentConstisByElectionYear(String year,List<Long> parliamentIds){
    	List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
    	try{
	    	List<Object[]> parliamentConstis = boothConstituencyElectionDAO.getParliamentConstisByElectionYear(parliamentIds, year);
	    	for(Object[] parliament : parliamentConstis){
	    		SelectOptionVO vo = new SelectOptionVO();
	    		vo.setId((Long)parliament[0]);
	    		vo.setName(parliament[1]!= null?parliament[1].toString():"");
	    		returnVal.add(vo);
	    	}
    	}catch(Exception e){
    		log.error("Exception rised in getParliamentConstisByElectionYear method : ",e);
    	}
    	return returnVal;
    }
    
    public List<SelectOptionVO> getAssemblyConstisByElectionYear(String year,Long parliamentId,Map<Long,List<SelectOptionVO>> parliamentMap){
    	List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
    	try{
	    	List<SelectOptionVO> assemblyList = parliamentMap.get(parliamentId);
	    	List<Long> assemblyIds = new ArrayList<Long>();
	    	List<Long> assemblyIds1 = new ArrayList<Long>();
	    	for(SelectOptionVO assembly:assemblyList){
	    		if(assembly.getId() != 0){
	    			assemblyIds.add(assembly.getId());
	    		}
	    	}
	    	List<SelectOptionVO> constis = getAssembliesForParliament(parliamentId, new Long(year.trim()));
	    	for(SelectOptionVO assembly:constis){
	    		assemblyIds1.add(assembly.getId());
	    	}
	    	assemblyIds.retainAll(assemblyIds1);
	    	if(assemblyIds.size() > 0){
		    	List<Object[]> assemblyConstis = boothConstituencyElectionDAO.getParliamentConstisByElectionYear(assemblyIds, year);
		    	for(Object[] assembly : assemblyConstis){
		    		SelectOptionVO vo = new SelectOptionVO();
		    		vo.setId((Long)assembly[0]);
		    		vo.setName(assembly[1]!= null?assembly[1].toString():"");
		    		returnVal.add(vo);
		    	}
	    	}
    	}catch(Exception e){
    		log.error("Exception rised in getAssemblyConstisByElectionYear method : ",e);
    	}
    	return returnVal;
    	
    }*/
       
    public List<SelectOptionVO> getAllElectionYearsForCrossVoting(List<Long> assemblyIds){
    	List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
    	SelectOptionVO vo = null;
    	try{
    		List<Long> parliamentIds = getAllParliamentsByAssemblyIds(assemblyIds);
    		if(parliamentIds.size() > 0){
	    		List<String> assemblyYears = boothConstituencyElectionDAO.getAllElectionYearsByConstiIds(assemblyIds);
		    	List<String> parliamentYears = boothConstituencyElectionDAO.getAllElectionYearsByConstiIds(parliamentIds);
		    	assemblyYears.retainAll(parliamentYears);
	    		for(String year : assemblyYears){
		    		vo = new SelectOptionVO();
		    		vo.setId(new Long(year.trim()));
		    		vo.setName(year);
		    		returnVal.add(vo);
		    	}
    		}
    	}catch(Exception e){
    		log.error("Exception rised in getAllElectionYearsForCrossVoting method : ",e);
    	}	
    	return returnVal;
    }
    
    public List<Long> getAllParliamentsByAssemblyIds(List<Long> assemblyIds){
    	List<Long> parliamentConstisList = null;    	
    	try{
    		 parliamentConstisList = delimitationConstituencyAssemblyDetailsDAO.findAllParliamentForAssembly(assemblyIds);    		
    	}catch(Exception e){
    		log.error("Exception rised in getAllParliamentsByAssemblyIds method : ",e);
    	}
    	if(parliamentConstisList == null)
    		parliamentConstisList = new ArrayList<Long>();
    	return parliamentConstisList;
    }
    
    public List<SelectOptionVO> getAllParliamentConstituenciesForCrossVoting(List<Long> assemblyIds,String electionYear){
    	List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
    	SelectOptionVO vo = null;
    	try{
    		List<Long> assemblyList = boothConstituencyElectionDAO.getAllAssemblyConstisByYearsAndConstiIds(electionYear,assemblyIds);
    		if(assemblyList != null && assemblyList.size() > 0){
		    	List<Long> parliamentList = delimitationConstituencyAssemblyDetailsDAO.findAllParliamentForAssembliesForTheGivenYear(assemblyList,Long.valueOf(electionYear.trim()));
		    	if(parliamentList != null && parliamentList.size() > 0){
			    	List<Object[]> parliamentConstituencies = boothConstituencyElectionDAO.getAllConstisByYearsAndConstiIds(electionYear,parliamentList);
		    		for(Object[] parliament : parliamentConstituencies){
			    		 vo = new SelectOptionVO();
			    		vo.setId((Long)parliament[0]);
			    		vo.setName(parliament[1]!= null?parliament[1].toString():"");
			    		returnVal.add(vo);
			    	}
		    	}
    		}
    	}catch(Exception e){
    		log.error("Exception rised in getAllParliamentConstituenciesForCrossVoting method : ",e);
    	}   	
    	return returnVal;    
    }
    
    public List<SelectOptionVO> getAllAssemblyConstituenciesForCrossVoting(List<Long> assemblyIds,Long parliamentId,String electionYear){
    	List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
    	SelectOptionVO vo = null;
    	try{
	    	List<Long> assembliesList = delimitationConstituencyAssemblyDetailsDAO.findAllAssembliesForParliamentForTheGivenYear(assemblyIds,parliamentId,Long.valueOf(electionYear.trim()));
	    	if(assembliesList != null && assembliesList.size() > 0){
		    	List<Object[]> assemblyConstituencies = boothConstituencyElectionDAO.getAllConstisByYearsAndConstiIds(electionYear,assembliesList);
	    		for(Object[] assembly : assemblyConstituencies){
		    		 vo = new SelectOptionVO();
		    		 vo.setId((Long)assembly[0]);
		    		 vo.setName(assembly[1]!= null?assembly[1].toString():"");
		    		 returnVal.add(vo);
		    	}
	    	}
    	}catch(Exception e){
    		log.error("Exception rised in getAllAssemblyConstituenciesForCrossVoting method : ",e);
    	}
    	return returnVal;   
    }
}
