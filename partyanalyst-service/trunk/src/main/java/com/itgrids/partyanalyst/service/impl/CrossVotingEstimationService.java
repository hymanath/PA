package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CrossVotedBoothVO;
import com.itgrids.partyanalyst.dto.CrossVotedCandidateVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;

public class CrossVotingEstimationService implements ICrossVotingEstimationService{

	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;	
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private INominationDAO nominationDAO;
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
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
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId){
		CrossVotingConsolidateVO crossVotingConsolidateVO = new CrossVotingConsolidateVO(); 
		CrossVotedCandidateVO acCandidate = new CrossVotedCandidateVO();
		CrossVotedCandidateVO pcCandidate = new CrossVotedCandidateVO();
		List<Nomination> acNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, acId, electionYear);
		List<Nomination> pcNominations = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, pcId, electionYear);
		if((acNominations.size() > 1 || acNominations == null)&&(pcNominations.size() > 1 || pcNominations == null)){
			System.out.println("--------Exists More than One Nominations For the Give Cryteria-----");
		}
		Nomination acNomination = acNominations.get(0);
		acCandidate.setCandidateName(acNomination.getCandidate().getLastname());
		acCandidate.setImage(acNomination.getCandidate().getImage());
		acCandidate.setRank(acNomination.getCandidateResult().getRank().toString());
		acCandidate.setVotesPercentage(acNomination.getCandidateResult().getVotesPercengate());
		Nomination pcNomination = pcNominations.get(0);
		pcCandidate.setCandidateName(pcNomination.getCandidate().getLastname());
		pcCandidate.setImage(pcNomination.getCandidate().getImage());
		pcCandidate.setRank(pcNomination.getCandidateResult().getRank().toString());
		pcCandidate.setVotesPercentage(pcNomination.getCandidateResult().getVotesPercengate());
		crossVotingConsolidateVO.setAcCandidateData(acCandidate);
		crossVotingConsolidateVO.setPcCandidateData(pcCandidate);
		crossVotingConsolidateVO.setMandals(getTehsilsForConstituency(electionYear, partyId, acId));
		return crossVotingConsolidateVO;
	}
	
	public List<CrossVotedMandalVO> getTehsilsForConstituency(String electionYear, Long partyId, Long constituencyId){
		List<CrossVotedMandalVO> crossVotedMandalVOs = new ArrayList<CrossVotedMandalVO>();
		List<Tehsil> tehsils = boothDAO.findTehsilsByConstituencyElectionScopeAndElection(electionYear, constituencyId);
		long beginTimeMillis = System.currentTimeMillis();
		for(Tehsil tehsil:tehsils){
			CrossVotedMandalVO crossVotedMandalVO = new CrossVotedMandalVO();
			crossVotedMandalVO.setMandalName(tehsil.getTehsilName());
			crossVotedMandalVO.setCrossVotedBooths(getCrossVotingDetails(tehsil.getTehsilId(), partyId, electionYear, crossVotedMandalVO));
			crossVotedMandalVOs.add(crossVotedMandalVO);
		}
		long endTimeMillis = System.currentTimeMillis();
		System.out.println("beginTimeMillis:"+beginTimeMillis);
		System.out.println("endTimeMillis:"+endTimeMillis);
		System.out.println("Total time taken:" + (endTimeMillis-beginTimeMillis)/1000);
		System.out.println("IN getTehsilsForConstituency "+tehsils.size());
		return crossVotedMandalVOs;
	}
	
	public List<CrossVotedBoothVO> getCrossVotingDetails(Long tehsilId, Long partyId, String electionYear, CrossVotedMandalVO crossVotedMandalVO){
		System.out.println("IN getCrossVotingDetails mandal Id::"+tehsilId);
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
			//System.out.println("Percentage::"+percentage);
		}
		return percentage.toString();
	}

	
}
