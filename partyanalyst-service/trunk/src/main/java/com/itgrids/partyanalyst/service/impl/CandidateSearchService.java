package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dto.CandidateElectionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateSearchService implements ICandidateSearchService{

	private ICandidateDAO candidateDAO;	
	private INominationDAO nominationDAO;
	private IPartyRebelCandidateDAO partyRebelCandidateDAO;
	private IElectionDAO electionDAO;
	
	
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
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public List<SelectOptionVO> getCandidateNamesAndIds(String electionType,
			Long stateId, String searchString) {
		List<SelectOptionVO> candidateNamesAndIdsList = null;
		System.out.println("======Start===============getCandidateNamesAndIds===============================");
		if(candidateNamesAndIdsList == null){
			List<SelectOptionVO> candidateNamesAndIds = new ArrayList<SelectOptionVO>();
			StringBuilder candidateName = null;
			//List candidates = nominationDAO.getAllCandidatesByElectionTypes("'"+IConstants.ASSEMBLY_ELECTION_TYPE+"','" 
				//	+IConstants.PARLIAMENT_ELECTION_TYPE+"'");
			List candidates = nominationDAO.getAllCandidatesByElectionTypeInState(electionType, stateId, searchString);
			for(int i=0; i<candidates.size(); i++){
				Object[] values = (Object[])candidates.get(i);
				candidateName = new StringBuilder();
				if(values[1] != null)
					candidateName.append(values[1]).append(" ");
				if(values[2] != null)
					candidateName.append(values[2]).append(" ");
				if(values[3] != null)
					candidateName.append(values[3]);
				candidateNamesAndIds.add(new SelectOptionVO((Long)values[0], candidateName.toString().trim().toUpperCase()));
			}
			candidateNamesAndIdsList = candidateNamesAndIds;
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
		
		CandidateVO candidateVO = extractNominationsForCandidate(candidateDAO.get(candidateId));
		candidateVOs.add(candidateVO);
		
		return candidateVOs;
	}
	
	public Long getTotalSearchCount(String searchText,String ConstType,Long stateId)
	{
		List<String> idlist = new ArrayList<String>();
		
		if(ConstType.equalsIgnoreCase(IConstants.MP))
		{
			idlist.add(IConstants.PARLIAMENT_ELECTION_TYPE);
		}
		if(ConstType.equalsIgnoreCase(IConstants.MLA))
		{
			idlist.add(IConstants.ASSEMBLY_ELECTION_TYPE);
		}
		
		String searchTextStr = "";

		if(searchText != null && searchText.trim().length() > 0)
		{
			StringTokenizer st = new StringTokenizer(searchText);
			searchTextStr = "(";
			while(st.hasMoreTokens())
			{
				String names = st.nextToken();
				searchTextStr += " model.candidate.lastname like '%"+names+"%'";
				searchTextStr += " or ";
			}
			
			searchTextStr = searchTextStr.substring(0,searchTextStr.length()-4);
			searchTextStr += ") and  ";
		}
		
		String electionIds = getLatestElectionIdForElectionType(idlist,ConstType,stateId);
		
		List<Long> totSearchCount = nominationDAO.totalSearchCount(searchTextStr,electionIds,stateId);
		
		return totSearchCount.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateVO> getCandidatesDetails(String searchText,String sortOption,String order,
											Integer startIndex,Integer maxResult,String ConstType,Long stateId){
		
		List<CandidateVO> candidateVOs = new ArrayList<CandidateVO>();
		
		String option = "";
		if(sortOption.equalsIgnoreCase("id"))
			option = "model.candidate.candidateId";
		else if(sortOption.equalsIgnoreCase("candidateName"))
			option = "model.candidate.lastname";
		else if(sortOption.equalsIgnoreCase("party"))
			option = "model.party.shortName";
		else if(sortOption.equalsIgnoreCase("year"))
			option = "model.constituencyElection.election.electionYear";
		else if(sortOption.equalsIgnoreCase("ConstituencyName"))
			option = "model.constituencyElection.constituency.name";
		else if(sortOption.equalsIgnoreCase("scope"))
			option = "model.constituencyElection.election.electionScope.electionType.electionType";
		else if(sortOption.equalsIgnoreCase("position"))
			option = "model.candidateResult.rank";	
		
		List<String> idlist = new ArrayList<String>();
		
		String electionIds = new String();
		
		if(ConstType.equalsIgnoreCase(IConstants.MP))
		{
			idlist.add(IConstants.PARLIAMENT_ELECTION_TYPE);
		}
		if(ConstType.equalsIgnoreCase(IConstants.MLA))
		{
			idlist.add(IConstants.ASSEMBLY_ELECTION_TYPE);
		}
		
		String searchTextStr = "";

		if(searchText != null && searchText.trim().length() > 0)
		{
			StringTokenizer st = new StringTokenizer(searchText);
			searchTextStr = "(";
			while(st.hasMoreTokens())
			{
				String names = st.nextToken();
				searchTextStr += " model.candidate.lastname like '%"+names+"%'";
				searchTextStr += " or ";
			}
			
			searchTextStr = searchTextStr.substring(0,searchTextStr.length()-4);
			searchTextStr += ") and  ";
		}
		
		electionIds = getLatestElectionIdForElectionType(idlist,ConstType,stateId);

		List<Object[]> candidates = nominationDAO.findByFirstMiddleAndLastNames(searchTextStr,option,order,startIndex,maxResult,electionIds);
		List<Long> totSearchCount = nominationDAO.totalSearchCount(searchTextStr,electionIds,stateId);
		
		Long count = new Long(startIndex);
		
		for(int i=0;i<candidates.size();i++)
		{
			CandidateVO candidateVO = new CandidateVO();
			Object[] params = (Object[])candidates.get(i);
			
			candidateVO.setId(++count);
			candidateVO.setCandidateName(params[0]!=null?params[0].toString():"");
			candidateVO.setParty(params[1]!=null?params[1].toString():"");
			candidateVO.setYear(params[2]!=null?params[2].toString():"");
			candidateVO.setConstituencyName(params[3]!=null?params[3].toString():"");
			candidateVO.setScope(params[4]!=null?params[4].toString():"");
			candidateVO.setPosition(params[5]!=null?params[5].toString():"");
			candidateVO.setCandidateId(params[6]!=null?(Long.parseLong(params[6].toString())):0l);
			candidateVOs.add(candidateVO);
			
		}
		
		candidateVOs.get(0).setTotalSearchCount(totSearchCount.get(0));
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
			try{
				if(nomination.getConstituencyElection().getElection().getElectionYear().equals(latestYearCandidatePartisipated))
				{
					partyName = nomination.getParty().getShortName();
					CandidateElectionVO candidateElectionVO = new CandidateElectionVO(nomination.getConstituencyElection().getConstituency().getName(),
										nomination.getCandidateResult().getRank().toString(),
										nomination.getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType());	
					candidateElectionVOs.add(candidateElectionVO);
				}	
			}catch (Exception e) {
				System.out.println("Exception Occured For Nomiantion Id::"+nomination.getNominationId()+" Cadidate:"+nomination.getCandidate().getLastname());
				e.printStackTrace();
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
	
	//Returns Latest ElectionIds for Election Types
	public String getLatestElectionIdForElectionType(List<String> electionTypes,String ConstType,Long stateId){
		StringBuilder electionIds = new StringBuilder();
		List rawData = null;
		Object[] values = null;
		try{
			for(String electionType:electionTypes){
				if(ConstType.equalsIgnoreCase(IConstants.MP))
				rawData = electionDAO.findLatestElectionIdForElectionType(electionType, IConstants.ELECTION_SUBTYPE_MAIN);
				
				else if(ConstType.equalsIgnoreCase(IConstants.MLA))
					rawData = electionDAO.findLatestElectionIdForElectionType(electionType, IConstants.ELECTION_SUBTYPE_MAIN,stateId);
				
				if(rawData.size() > 0)
					electionIds.append(",").append(((Object[])rawData.get(0))[0]);
			}
				
		}catch (Exception e) {
			
		}
		if(electionIds.length() > 0)
			return electionIds.toString().substring(1);
		else
			return "";
	}
	
}
