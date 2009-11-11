package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dto.CandidateElectionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.service.ICandidateSearchService;

public class CandidateSearchService implements ICandidateSearchService{

	private ICandidateDAO candidateDAO;	
	private List<SelectOptionVO> candidateNamesAndIdsList;
	private INominationDAO nominationDAO;
	private IPartyRebelCandidateDAO partyRebelCandidateDAO;
	
	
	public void setPartyRebelCandidateDAO(
			IPartyRebelCandidateDAO partyRebelCandidateDAO) {
		this.partyRebelCandidateDAO = partyRebelCandidateDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	
	public List<SelectOptionVO> getCandidateNamesAndIds() {
		if(candidateNamesAndIdsList == null){
			List<SelectOptionVO> candidateNamesAndIds = new ArrayList<SelectOptionVO>();
			List<Candidate> candidates = candidateDAO.getAll();
			System.out.println(candidates);
			for(Candidate candidate:candidates){
				SelectOptionVO candidateNameAndId = new SelectOptionVO();
				candidateNameAndId.setId(candidate.getCandidateId());
				candidateNameAndId.setName(getCandidateFullName(candidate));			
				candidateNamesAndIds.add(candidateNameAndId);
			}
			candidateNamesAndIdsList = candidateNamesAndIds;
			System.out.println("Entered into if loop for getCandidateNames");
		}else
			System.out.println("Entered into else loop for getCandidateNames");
		return candidateNamesAndIdsList;
	}
	
	public List<SelectOptionVO> getNominatedPartyCandidates(Long stateId, Long partyId, Long electionId) {
		List<Nomination> nominations = nominationDAO.findByStatePartyAndElectionId(stateId, electionId, partyId);
		List<PartyRebelCandidate> rebelCandidates = partyRebelCandidateDAO.findByPartyIdAndElectionId(partyId, electionId);

		List<SelectOptionVO> candidatesList = new ArrayList<SelectOptionVO>();
		List<Long> rebelIds = new ArrayList<Long>();
		
		for(PartyRebelCandidate cand : rebelCandidates) {
			rebelIds.add(cand.getCandidate().getCandidateId());
		}
		
		for(Nomination nomination : nominations) {
			Candidate candidate = nomination.getCandidate();
			if(!rebelIds.contains(candidate.getCandidateId())) {
				candidatesList.add(new SelectOptionVO(candidate.getCandidateId(), candidate.getLastname()));
			}
		}
		return candidatesList;
	}
	
	public List<CandidateVO> getCandidatesDetails(Long candidateId, String name){
		List<CandidateVO> candidateVOs = new ArrayList<CandidateVO>();
		if(candidateId.longValue() != 0)
		{
			CandidateVO candidateVO = extractNominationsForCandidate(candidateDAO.get(candidateId));
			candidateVOs.add(candidateVO);
		}
		else{
			String firstAndLastNames [] = StringUtils.delimitedListToStringArray(StringUtils.trimWhitespace(name), " ");
			List<Candidate> candidates = candidateDAO.findByFirstMiddleAndLastNames(firstAndLastNames);
					
			for(Candidate candidate:candidates)
			{
				CandidateVO candidateVO = extractNominationsForCandidate(candidate);
				candidateVOs.add(candidateVO);
			}
		}
		return candidateVOs;
	}
	
	public Long getLastestYear(List <Nomination> nominations){
		List<Long> years = new ArrayList<Long>();
		for(Nomination nomination:nominations){
			years.add(new Long(nomination.getConstituencyElection().getElection().getElectionYear()));
		}
		
		if(years.size() > 0)
			return Collections.max(years);
		else
			return new Long(0);
	}
		
	public CandidateVO extractNominationsForCandidate(Candidate candidate){
		List<CandidateElectionVO> candidateElectionVOs = new ArrayList<CandidateElectionVO>();
		List <Nomination> nominations = new ArrayList<Nomination>(candidate.getNominations());
		
		String candidateName = getCandidateFullName(candidate);
		String image = candidate.getImage();
		String latestYearCandidatePartisipated = getLastestYear(nominations).toString();
		String partyName = null;
		for(Nomination nomination:nominations)
		{
			if(nomination.getConstituencyElection().getElection().getElectionYear().equals(latestYearCandidatePartisipated))
			{
				partyName = nomination.getParty().getShortName();
				CandidateElectionVO candidateElectionVO = new CandidateElectionVO(nomination.getConstituencyElection().getConstituency().getName(),
									nomination.getCandidateResult().getRank().toString(),
									nomination.getConstituencyElection().getConstituency().getElectionScope().getElectionType().getElectionType());	
				candidateElectionVOs.add(candidateElectionVO);
			}
		}
		CandidateVO candidateVO = new CandidateVO(candidate.getCandidateId(), candidateName, image, latestYearCandidatePartisipated, candidateElectionVOs, partyName);
		
		return candidateVO;
	}
	
	public String getCandidateFullName(Candidate candidate){
		String name = " ";
		if(StringUtils.hasText(candidate.getFirstname())){
			name = name + candidate.getFirstname() + " ";
		}
		if(StringUtils.hasText(candidate.getMiddlename())){
			name = name + candidate.getMiddlename() + " ";
		}
		if(StringUtils.hasText(candidate.getLastname())){
			name = name + candidate.getLastname() + " ";
		}
		return StringUtils.trimWhitespace(name);
	}
	
}
