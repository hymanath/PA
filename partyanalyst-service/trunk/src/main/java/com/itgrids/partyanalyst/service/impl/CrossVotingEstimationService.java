package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;
import com.itgrids.partyanalyst.dto.CrossVotedCandidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.CrossVotedBoothVOComparator;
import com.itgrids.partyanalyst.utils.CrossVotedMandalVOComparator;

public class CrossVotingEstimationService implements ICrossVotingEstimationService{

	private static final Logger log = Logger.getLogger(CrossVotingEstimationService.class);
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;	
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IStaticDataService staticDataService;
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
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
	
	
	public List<SelectOptionVO> getAssembliesForParliament(Long delimitationConstituencyId){
		List<Constituency> constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesByDelimitationConstituencyId(delimitationConstituencyId);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies){
			SelectOptionVO constituencyVO = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
			constituencyVOs.add(constituencyVO);
		}
		return constituencyVOs;
	}
	
	public List<SelectOptionVO> getParliamentConstituenciesForElectionYear(Long electionYear){
		List<DelimitationConstituency> list = delimitationConstituencyDAO.findByElectionScopeIdStateIdAndElectionYear(new Long(1), new Long(1), electionYear);
		List<SelectOptionVO> delimitationConstituencyVOs = new ArrayList<SelectOptionVO>();
		for(DelimitationConstituency delimitationConstituency:list){
			SelectOptionVO delimitationConstituencyVO = new SelectOptionVO(delimitationConstituency.getDelimitationConstituencyID(), delimitationConstituency.getConstituency().getName());
			delimitationConstituencyVOs.add(delimitationConstituencyVO);
		}
		return delimitationConstituencyVOs;
	}
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance){
		CrossVotingConsolidateVO crossVotingConsolidateVO = new CrossVotingConsolidateVO(); 
		CrossVotedCandidateVO acCandidate = new CrossVotedCandidateVO();
		CrossVotedCandidateVO pcCandidate = new CrossVotedCandidateVO();
		List<Nomination> acNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, acId, electionYear);
		List<Nomination> pcNominations;
		if(includeAliance.equals("true")){
			System.out.println("\n====================in True Block-----");
			if(log.isInfoEnabled()){
				log.debug("\n====================in True Block-----"+electionYear + partyId);
			}
			List<SelectOptionVO> alianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, new Long(2), partyId);
			System.out.println("\n====================Aliance Parties Size::-----"+alianceParties.size());
			if(log.isInfoEnabled()){
				log.debug("\n====================Aliance Parties Size::-----"+alianceParties.size());
			}
			
			if(alianceParties.size() == 0){
				crossVotingConsolidateVO.setHasAlliance(false);
				return crossVotingConsolidateVO;
			}
			List<Long> aliancePartyIds = new ArrayList<Long>();
			for(SelectOptionVO alianceParty:alianceParties){
				if(alianceParty.getId().equals(partyId))
					continue;
				aliancePartyIds.add(alianceParty.getId());

			}
			System.out.println("\n====================Aliance Parties Ids Size::-----"+aliancePartyIds.size()+" Id: "+aliancePartyIds.get(0));
			if(log.isInfoEnabled()){
				log.debug("\n====================Aliance Parties Ids Size::-----"+aliancePartyIds.size());
			}
			pcNominations = nominationDAO.findByConstituencyPartyAndElectionYearIncludingAliance(aliancePartyIds, pcId, electionYear);
			System.out.println("\n=================Nominations with aliance Size::-----"+pcNominations.size());
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
		acCandidate.setRank(acNomination.getCandidateResult().getRank().toString());
		acCandidate.setVotesPercentage(acNomination.getCandidateResult().getVotesPercengate());
		Nomination pcNomination = pcNominations.get(0);
		pcCandidate.setCandidateId(pcNomination.getCandidate().getCandidateId());
		pcCandidate.setCandidateName(pcNomination.getCandidate().getLastname());
		pcCandidate.setImage(pcNomination.getCandidate().getImage());
		pcCandidate.setRank(pcNomination.getCandidateResult().getRank().toString());
		pcCandidate.setVotesPercentage(pcNomination.getCandidateResult().getVotesPercengate());
		crossVotingConsolidateVO.setAcCandidateData(acCandidate);
		crossVotingConsolidateVO.setPcCandidateData(pcCandidate);
		List<CrossVotedMandalVO> mandals = getTehsilsForConstituency(electionYear, partyId, acId);
		CrossVotedMandalVOComparator comparator = new CrossVotedMandalVOComparator();
		Collections.sort(mandals, comparator);
		crossVotingConsolidateVO.setMandals(mandals);
		return crossVotingConsolidateVO;
	}
	
	public List<CrossVotedMandalVO> getTehsilsForConstituency(String electionYear, Long partyId, Long constituencyId){
		List<CrossVotedMandalVO> crossVotedMandalVOs = new ArrayList<CrossVotedMandalVO>();
		List<Tehsil> tehsils = boothDAO.findTehsilsByConstituencyElectionScopeAndElection(electionYear, constituencyId);
		long beginTimeMillis = System.currentTimeMillis();
		for(Tehsil tehsil:tehsils){
			CrossVotedMandalVO crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalName(tehsil.getTehsilName());
			List<CrossVotedBoothVO> crossVotedBooths = getCrossVotingDetails(tehsil.getTehsilId(), partyId, electionYear, crossVotedMandalVO);
			CrossVotedBoothVOComparator comparator  = new CrossVotedBoothVOComparator(); 
			Collections.sort(crossVotedBooths, comparator);
			crossVotedMandalVO.setCrossVotedBooths(crossVotedBooths);
			crossVotedMandalVOs.add(crossVotedMandalVO);
		}
		long endTimeMillis = System.currentTimeMillis();
		if(log.isInfoEnabled()){
			log.info("Total time taken:" + (endTimeMillis-beginTimeMillis)/1000);
			log.info("IN getTehsilsForConstituency "+tehsils.size());
		}	
		return crossVotedMandalVOs;
	}
	
	public List<CrossVotedBoothVO> getCrossVotingDetails(Long tehsilId, Long partyId, String electionYear, CrossVotedMandalVO crossVotedMandalVO){
		List<CrossVotedBoothVO> crossVotingInfoVOs = new ArrayList<CrossVotedBoothVO>();
		List<BoothConstituencyElection> list = boothConstituencyElectionDAO.findByTehsilElectionAndScope(electionYear, new Long(1), tehsilId);
		Long acValidVotesInMandal = new Long(0);
		Long acEarnedVotesInMandal = new Long(0);
		Long pcEarnedVotesInMandal = new Long(0);
		Long pcValidVotesInMandal = new Long(0);
		for(BoothConstituencyElection boothConstituencyElection:list){
			CrossVotedBoothVO obj = new CrossVotedBoothVO();
			obj.setPartNO(boothConstituencyElection.getBooth().getPartNo());
			obj.setVillagesCovered(boothConstituencyElection.getBooth().getvillagesCovered());
			Long pcValidVotes = boothConstituencyElection.getBoothResult().getValidVotes();
			List<CandidateBoothResult> pcCandidateBoothResults = candidateBoothResultDAO.findByBoothConstituencyElectionAndParty(boothConstituencyElection.getBoothConstituencyElectionId(), partyId);
			Long pcEarnedVotes = pcCandidateBoothResults.get(0).getVotesEarned();
			String pcPercentage = calculateVotesPercengate(pcValidVotes, pcEarnedVotes);	
			obj.setPcValidVotes(pcValidVotes);
			obj.setPcPercentage(pcPercentage);
			List<BoothConstituencyElection> assemblyBoothResults = boothConstituencyElectionDAO.findByBoothElectionAndScope(boothConstituencyElection.getBooth().getBoothId(), electionYear, new Long(2));
			Long acValidVotes = assemblyBoothResults.get(0).getBoothResult().getValidVotes();
			List<CandidateBoothResult> acCandidateBoothResults = candidateBoothResultDAO.findByBoothConstituencyElectionAndParty(assemblyBoothResults.get(0).getBoothConstituencyElectionId(), partyId);
			Long acEarnedVotes = acCandidateBoothResults.get(0).getVotesEarned();
			String acPercentage = calculateVotesPercengate(acValidVotes, acEarnedVotes);
			obj.setAcValidVotes(acValidVotes);
			obj.setAcPercentage(acPercentage);
			Double percentageDifference = Double.parseDouble(acPercentage)-Double.parseDouble(pcPercentage);
			BigDecimal percentageDifferenceRound = new BigDecimal(percentageDifference).setScale (2,BigDecimal.ROUND_HALF_UP);			
			obj.setPercentageDifference(percentageDifferenceRound.toString());
			acValidVotesInMandal = acValidVotesInMandal + acValidVotes;
			acEarnedVotesInMandal = acEarnedVotesInMandal + acEarnedVotes;
			pcValidVotesInMandal = pcValidVotesInMandal + pcValidVotes;
			pcEarnedVotesInMandal = pcEarnedVotesInMandal + pcEarnedVotes;
			crossVotingInfoVOs.add(obj);
		}
		String acPercentageInMandal = calculateVotesPercengate(acValidVotesInMandal, acEarnedVotesInMandal);
		String pcPercentageInMandal = calculateVotesPercengate(pcValidVotesInMandal, pcEarnedVotesInMandal);
		Double percentageDifferenceInMandal = Double.parseDouble(acPercentageInMandal)-Double.parseDouble(pcPercentageInMandal);
		BigDecimal percentageDifferenceInMandalRound = new BigDecimal(percentageDifferenceInMandal).setScale (2,BigDecimal.ROUND_HALF_UP);
		crossVotedMandalVO.setAcEarnedVotesInMandal(acEarnedVotesInMandal);
		crossVotedMandalVO.setAcValidVotesInMandal(acValidVotesInMandal);
		crossVotedMandalVO.setAcPercentageInMandal(acPercentageInMandal);
		crossVotedMandalVO.setPcValidVotesInMandal(pcValidVotesInMandal);
		crossVotedMandalVO.setPcEarnedVotesInMandal(pcEarnedVotesInMandal);
		crossVotedMandalVO.setPcPercentageInMandal(pcPercentageInMandal);
		crossVotedMandalVO.setPercentageDifferenceInMandal(percentageDifferenceInMandalRound.toString());
		return crossVotingInfoVOs;
	}
	
	private String calculateVotesPercengate(Long validVotes, Long votesEarned){
		BigDecimal percentage= new BigDecimal(0.0);
		if((validVotes!=null && validVotes.longValue()>0) && (votesEarned!=null && votesEarned.longValue()>0)){
			percentage= new BigDecimal((votesEarned.floatValue()/validVotes.floatValue())*100).setScale (2,BigDecimal.ROUND_HALF_UP);
		}
		return percentage.toString();
	}

	
	
}
