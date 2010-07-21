/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.Icon;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyOrMandalWiseElectionVO;
import com.itgrids.partyanalyst.dto.ConstituencyRevenueVillagesVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ElectionResultByLocationVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.VillageBoothInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.VillageBoothElection;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ConstituencyOrMandalComparatorForTotalVoters;
import com.itgrids.partyanalyst.utils.ConstituencyOrMandalVOComparator;
import com.itgrids.partyanalyst.utils.ElectionDetailsVOComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyResultVOComparatorByElectors;
import com.itgrids.partyanalyst.utils.PartyResultVOComparatorByName;
import com.itgrids.partyanalyst.utils.SortByRankOnPartyElectionResultComparator;

public class ConstituencyPageService implements IConstituencyPageService {

	/*
	 * doc
	 * ConstituencyElectionResults
	 */
	private static final Logger log = Logger.getLogger(CrossVotingEstimationService.class);
	private IConstituencyElectionResultObjectsDAO constituencyElectionResultObjectsDAO;
	private IConstituencyDAO constituencyDAO;
	private INominationDAO nominationDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private HamletAndBoothVO hamletAndBoothVO;
	private TransactionTemplate transactionTemplate;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO; 
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService; 
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IStaticDataService staticDataService;		
	private IBoothResultDAO boothResultDAO; 
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public HamletAndBoothVO getHamletAndBoothVO() {
		return hamletAndBoothVO;
	}

	public void setHamletAndBoothVO(HamletAndBoothVO hamletAndBoothVO) {
		this.hamletAndBoothVO = hamletAndBoothVO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public void setConstituencyElectionResultObjectsDAO(IConstituencyElectionResultObjectsDAO constituencyElectionResultObjectsDAO) {
		this.constituencyElectionResultObjectsDAO = constituencyElectionResultObjectsDAO;
	}

	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults(Long constituencyId) {
		
		List<ConstituencyElectionResult> constituencyElectionResults = constituencyElectionResultObjectsDAO.findConstituencyElectionResultObjects(constituencyId);
		List<ConstituencyElectionResultsVO> constituencyElectionResultList = new ArrayList<ConstituencyElectionResultsVO>(0);
		ConstituencyElectionResultsVO constElecResultVO = null;
		Election election = null;
		
		 if(constituencyElectionResults != null){
			 for(ConstituencyElectionResult result:constituencyElectionResults){
				 List<CandidateOppositionVO> candidateOppositionList = new ArrayList<CandidateOppositionVO>(0);
				 int rank = 2;
				 constElecResultVO = new ConstituencyElectionResultsVO();
				 election = result.getConstituencyElection().getElection();
				 constElecResultVO.setElectionId(election.getElectionId());
				 constElecResultVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
				 constElecResultVO.setElectionDate(result.getConstituencyElection().getElectionDate());
				 constElecResultVO.setElectionYear(election.getElectionYear());
				 List<Nomination> nominationsList = new ArrayList<Nomination>(result.getConstituencyElection().getNominations());
				 CandidateWonVO candidateWon = getCandidateWon(nominationsList);
				 
				  for(Nomination nomination:nominationsList){
				    CandidateOppositionVO candidateOpposition = getCandidatesOpposition(nominationsList,rank);
				    rank++;
				    if(candidateOpposition!=null)
				    	candidateOppositionList.add(candidateOpposition);
				  }
				  constElecResultVO.setCandidateResultsVO(candidateWon);
				  constElecResultVO.setCandidateOppositionList(candidateOppositionList);
				 
				 constituencyElectionResultList.add(constElecResultVO);
			 }
			 Collections.sort(constituencyElectionResultList,new ElectionDetailsVOComparator());
		 return constituencyElectionResultList;
		 }
	return null;	
	}
	
	public CandidateWonVO getCandidateWon(List<Nomination> nominationsList){
		CandidateWonVO candidateWon = null;
		CandidateResult candidateResult = null;
		for(Nomination nomination:nominationsList){
			candidateResult = nomination.getCandidateResult();
			if(candidateResult.getRank().equals(new Long(1))){
				candidateWon = new CandidateWonVO();	
				Candidate candidate = nomination.getCandidate();
				candidateWon.setCandidateId(candidate.getCandidateId());
				String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				candidateWon.setCandidateName(name);				
				candidateWon.setPartyId(nomination.getParty().getPartyId());
				candidateWon.setPartyName(nomination.getParty().getLongName());	
				candidateWon.setPartyShortName(nomination.getParty().getShortName());	
				String votes = candidateResult.getVotesEarned().toString();
				String votesEarn[] = StringUtils.split(votes, ".");
				candidateWon.setVotesEarned(votesEarn[0]);
				candidateWon.setVotesPercentage(candidateResult.getVotesPercengate());
				break;
			}
				
		}
		return candidateWon;
	}
	
	public CandidateOppositionVO getCandidatesOpposition(List<Nomination> nominationsList,int rank){
		
		CandidateResult candidateResult = null;
		CandidateOppositionVO candidateOpposition = null;
				
		for(Nomination nomination:nominationsList){
			candidateResult = nomination.getCandidateResult();
			
			if(candidateResult.getRank().equals(new Long(rank))){
				candidateOpposition = new CandidateOppositionVO();
				Candidate candidate = nomination.getCandidate();
				candidateOpposition.setCandidateId(candidate.getCandidateId());
				String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				candidateOpposition.setCandidateName(name);
				candidateOpposition.setPartyId(nomination.getParty().getPartyId());
				candidateOpposition.setPartyName(nomination.getParty().getLongName());
				candidateOpposition.setPartyShortName(nomination.getParty().getShortName());
				String votes = candidateResult.getVotesEarned().toString();
				String votesEarn[] = StringUtils.split(votes, ".");
				candidateOpposition.setVotesEarned(votesEarn[0]);
				candidateOpposition.setVotesPercentage(candidateResult.getVotesPercengate());
				break;
			}
	    }
		
		return candidateOpposition;
	}
	
	public ConstituencyInfoVO getConstituencyDetails(Long constituencyId){
		
		ConstituencyInfoVO constituencyDetails = new ConstituencyInfoVO();
		List<Constituency> constituency = constituencyDAO.findByConstituencyId(constituencyId);
		String districtName ="";
		if(constituency != null){
			for(Constituency result:constituency){
				if(result.getDistrict() != null)
					districtName = result.getDistrict().getDistrictName();
				constituencyDetails.setConstituencyName(result.getName());
				constituencyDetails.setDistrictName(districtName);
				constituencyDetails.setStateName(result.getState().getStateName());
				constituencyDetails.setStartDate(result.getStartDate());
				constituencyDetails.setDeformDate(result.getDeformDate());
				constituencyDetails.setConstituencyType(result.getElectionScope().getElectionType().getElectionType());
			}
			return constituencyDetails;
		}
		else
			return null;
	}
	
	public ResultWithExceptionVO getAllMandalLevelLeaders(Long tehsilId){
		ResultWithExceptionVO result = new ResultWithExceptionVO();
		ResultStatus resultStatus = new ResultStatus();
		List<InfluencingPeopleVO> influencingPeopleVOs = new ArrayList<InfluencingPeopleVO>();
		InfluencingPeopleVO influencingPeopleVO = null;
		try{
			List mandalLeadersInfo = nominationDAO.findLocalLeadersOfMandal(tehsilId);
			for(int i=0; i<mandalLeadersInfo.size(); i++){
				influencingPeopleVO = new InfluencingPeopleVO();
				Object[] values = (Object[])mandalLeadersInfo.get(i);
				String name = getCandidateFullName((Candidate)values[0]);
				String designation = (String)values[1];
				String party = (String)values[2];
				String year = (String)values[3];
				String constituencyName = (String)values[4];
				
				influencingPeopleVO.setPersonName(name);
				influencingPeopleVO.setDesignation(designation);
				influencingPeopleVO.setLocalArea(constituencyName);
				influencingPeopleVO.setYear(year);
				influencingPeopleVO.setParty(party);
				
				influencingPeopleVOs.add(influencingPeopleVO);
			}
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			if(log.isDebugEnabled()){
				log.debug("Exception Raised In getAllMandalLevelLeaders", e);
			}
		}
		result.setFinalResult(influencingPeopleVOs);
		result.setResultStatus(resultStatus);
		return result;
	}
	
	public String getCandidateFullName(Candidate candidate){
		String name = " ";
		if(StringUtils.isBlank(candidate.getFirstname())){
			name = name + candidate.getFirstname() + " ";
		}
		if(StringUtils.isBlank(candidate.getMiddlename())){
			name = name + candidate.getMiddlename() + " ";
		}
		if(StringUtils.isBlank(candidate.getLastname())){
			name = name + candidate.getLastname();
		}
		return name;
	}
	
	public MandalAndRevenueVillagesInfoVO getTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId){
		MandalAndRevenueVillagesInfoVO mandalWiseResult = new MandalAndRevenueVillagesInfoVO();
		List<ElectionResultByLocationVO> villagesInConstituencyVOList = new ArrayList<ElectionResultByLocationVO>();
		List<LocationWiseBoothDetailsVO> boothDataFromDB = null;
		ElectionResultByLocationVO villagesInConstituencyVO = null;
		LocationWiseBoothDetailsVO boothsAndHamletsInVillageVO = null;
		Map<ElectionResultByLocationVO, List<Object[]>> villagesInConstituencyMap = new HashMap<ElectionResultByLocationVO, List<Object[]>>();
		Map<LocationWiseBoothDetailsVO, Set<SelectOptionVO>> hamletsInVillageMap = null;
		Set<SelectOptionVO> boothNos = null;
		List<Object[]> rawData = null;
		Long totalVotersInVillage = new Long(0);
		Long polledVotes = 0l;
		try{
						
			List boothDetails = villageBoothElectionDAO.findTownshipWiseBoothDetailsForTehsil(tehsilId, electionId);

			for(Object[] values:(List<Object[]>)boothDetails){
				villagesInConstituencyVO = new ElectionResultByLocationVO();
				villagesInConstituencyVO.setConstituencyId((Long)values[0]);
				villagesInConstituencyVO.setConstituencyName(values[1].toString());
				rawData = villagesInConstituencyMap.get(villagesInConstituencyVO);
				
				if(rawData == null)
					rawData = new ArrayList<Object[]>();
					
				rawData.add(values);
				villagesInConstituencyMap.put(villagesInConstituencyVO, rawData);		
			}
			
			for(Map.Entry<ElectionResultByLocationVO, List<Object[]>> entry:villagesInConstituencyMap.entrySet()){
				villagesInConstituencyVO = entry.getKey();
				rawData = entry.getValue();
				polledVotes = 0l;
				totalVotersInVillage = 0l;
				hamletsInVillageMap = new LinkedHashMap<LocationWiseBoothDetailsVO, Set<SelectOptionVO>>();
				boothDataFromDB = new ArrayList<LocationWiseBoothDetailsVO>();
				for(Object[] values:rawData){
					boothsAndHamletsInVillageVO = new LocationWiseBoothDetailsVO();
					boothsAndHamletsInVillageVO.setLocationId((Long)values[2]);
					boothsAndHamletsInVillageVO.setLocationName(values[3].toString());
					boothNos = hamletsInVillageMap.get(boothsAndHamletsInVillageVO);
					
					if(boothNos == null){
						polledVotes = 0l;
						totalVotersInVillage = 0l;
						boothNos = new LinkedHashSet<SelectOptionVO>();	
					}
					
					polledVotes += (Long)values[7];
					totalVotersInVillage += (Long)values[6];
					boothsAndHamletsInVillageVO.setVotesPolled(polledVotes);
					boothsAndHamletsInVillageVO.setPopulation(totalVotersInVillage);
					boothNos.add(new SelectOptionVO((Long)values[4], values[5].toString()));
					hamletsInVillageMap.remove(boothsAndHamletsInVillageVO);
					hamletsInVillageMap.put(boothsAndHamletsInVillageVO, boothNos);
				}
				
				for(Map.Entry<LocationWiseBoothDetailsVO, Set<SelectOptionVO>> villageEntry:hamletsInVillageMap.entrySet()){
					boothsAndHamletsInVillageVO = villageEntry.getKey();
					boothsAndHamletsInVillageVO.setBooths(villageEntry.getValue());
					boothsAndHamletsInVillageVO.setHamletsOfTownship(createSelectOptionVoForRawList(hamletDAO.findHamletNamesByTownshipId(boothsAndHamletsInVillageVO.getLocationId())));
					boothDataFromDB.add(boothsAndHamletsInVillageVO);
				}
				
				villagesInConstituencyVO.setRevenueVillagesInfo(boothDataFromDB);
				villagesInConstituencyVOList.add(villagesInConstituencyVO);
			}
			
			mandalWiseResult.setPartiesResultsInVillages(villagesInConstituencyVOList);
			
		}catch(Exception e){
			mandalWiseResult.setExceptionEncountered(e);
			if(log.isDebugEnabled()){
				log.debug("Exception Occured ::", e);
			}
			e.printStackTrace();
		}		
		
		return mandalWiseResult;
	}
	
	public List<ConstituencyRevenueVillagesVO> getPartiesResultsInVillagesGroupByMandal(Long tehsilId, Long electionId) {

		List mandalWiseInfo = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(tehsilId, electionId);
		
		log.debug("Total Revenue Villages::"+mandalWiseInfo.size());
		
		//Group By Constituency		
		
		Map<ConstituencyRevenueVillagesVO, List<Object[]>> constituencyWithRevenueVillagesMap = 
			new LinkedHashMap<ConstituencyRevenueVillagesVO, List<Object[]>>();
		Map<RevenueVillageElectionVO, List<Object[]>> revenueVillagesWithResults = null;
		List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVOs = new ArrayList<ConstituencyRevenueVillagesVO>();
		List<RevenueVillageElectionVO> revenueVillageElectionVOs = null;	
		RevenueVillageElectionVO revenueVillageElectionVO = null;
		List<PartyElectionResultVO> revenueVillageParties = null;
		PartyElectionResultVO revenueVillageParty = null;
		List<Object[]> partyInfo = null;
		List<Object[]> revenueVillageInfo = null;
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = null;
		
		Long votesEarned = null;
		Long polledVotes = null;
		List<CandidatePartyInfoVO> candidates = null;
		
		for(int i=0; i<mandalWiseInfo.size(); i++){
			Object[] values = (Object[])mandalWiseInfo.get(i);
			constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
			constituencyRevenueVillagesVO.setConstituencyId((Long)values[0]);
			constituencyRevenueVillagesVO.setConstituencyName(values[1].toString());
			revenueVillageInfo = constituencyWithRevenueVillagesMap.get(constituencyRevenueVillagesVO);
			
			if(revenueVillageInfo == null)
				revenueVillageInfo = new ArrayList<Object[]>();
				
			revenueVillageInfo.add(values);
			constituencyWithRevenueVillagesMap.put(constituencyRevenueVillagesVO, revenueVillageInfo);
		}
		
		//Group By Revenue Villages
		
		for(Map.Entry<ConstituencyRevenueVillagesVO, List<Object[]>> entry:
			constituencyWithRevenueVillagesMap.entrySet()){
			
			constituencyRevenueVillagesVO = entry.getKey();
			
			revenueVillagesWithResults = new LinkedHashMap<RevenueVillageElectionVO, List<Object[]>>();
			candidates = new ArrayList<CandidatePartyInfoVO>();
			revenueVillageElectionVOs = new ArrayList<RevenueVillageElectionVO>();
			
			for(Object[] values:entry.getValue()){
				revenueVillageElectionVO = new RevenueVillageElectionVO();
				revenueVillageElectionVO.setTownshipId((Long)values[6]);
				revenueVillageElectionVO.setTownshipName(values[7].toString());
				partyInfo = revenueVillagesWithResults.get(revenueVillageElectionVO);
				
				if(partyInfo == null)
					partyInfo = new ArrayList<Object[]>();
				
					partyInfo.add(values);
				
				revenueVillagesWithResults.put(revenueVillageElectionVO, partyInfo);
			}
			
			int i = 0;	
			for(Map.Entry<RevenueVillageElectionVO, List<Object[]>> revenueVillage:
				revenueVillagesWithResults.entrySet()){
				
				revenueVillageElectionVO = revenueVillage.getKey();
				
				revenueVillageParties = new ArrayList<PartyElectionResultVO>(); 
				CandidatePartyInfoVO candidatePartyInfoVO = null;
				if(i == 0){
					for(Object[] values:revenueVillage.getValue()){
						candidatePartyInfoVO = new CandidatePartyInfoVO();
						candidatePartyInfoVO.setCandidateId((Long)values[2]);
						candidatePartyInfoVO.setCandidateName(values[3].toString());
						candidatePartyInfoVO.setPartyId((Long)values[4]);
						candidatePartyInfoVO.setParty(values[5].toString());
						candidatePartyInfoVO.setRank((Long)values[10]);
						
						candidates.add(candidatePartyInfoVO);
					}
					i++;
				}
				
				for(Object[] values:revenueVillage.getValue()){
					revenueVillageParty = new PartyElectionResultVO();
					votesEarned = (Long)values[8];
					polledVotes = (Long)values[9];
					revenueVillageParty.setVotesEarned(votesEarned);
					revenueVillageParty.setVotesPercentage(new BigDecimal((votesEarned*100.0)/polledVotes)
												.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					revenueVillageParties.add(revenueVillageParty);
				}
				
				revenueVillageElectionVO.setPartyElectionResultVOs(revenueVillageParties);
				revenueVillageElectionVOs.add(revenueVillageElectionVO);
			}
			constituencyRevenueVillagesVO.setRevenueVillageElectionVO(revenueVillageElectionVOs);
			constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidates);
			constituencyRevenueVillagesVOs.add(constituencyRevenueVillagesVO);	
		}		
		
		return constituencyRevenueVillagesVOs;
	}

	public ConstituencyRevenueVillagesVO getRevenuevillagesWiseElectionResultsOfPartyInMandal(Long partyId, Long tehsilId){
		
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
		PartyElectionResultVO resultInVillage = null;
		List<PartyElectionResultVO> resultsInVillage = null;
		ElectionWiseMandalPartyResultVO electionInfo = null;
		List<ElectionWiseMandalPartyResultVO> allElectionsOfPartyByVillages = new ArrayList<ElectionWiseMandalPartyResultVO>();
		Map<ElectionWiseMandalPartyResultVO, List<PartyElectionResultVO>> villagesInfo = 
			new LinkedHashMap<ElectionWiseMandalPartyResultVO, List<PartyElectionResultVO>>();
		
		try{
			List list = candidateBoothResultDAO.findPartyResultsInAllElectionsByRevenueVillagesInMandal(tehsilId, partyId);
			for(Object[] values:(List<Object[]>)list){
				electionInfo = new ElectionWiseMandalPartyResultVO();
				electionInfo.setElectionId((Long)values[0]);
				electionInfo.setElectionYear(new Long(values[2].toString()));
				electionInfo.setElectionType(values[1].toString());
				
				resultsInVillage = villagesInfo.get(electionInfo);
				if(resultsInVillage == null)
					resultsInVillage = new ArrayList<PartyElectionResultVO>();
				resultInVillage = new PartyElectionResultVO();
				resultInVillage.setTownshipId((Long)values[3]);
				resultInVillage.setTownshipName(values[4].toString());
				resultInVillage.setValidVotes((Long)values[6]);
				resultInVillage.setVotesEarned((Long)values[5]);
				if((Long)values[6] - (Long)values[5] >= 0)
					resultInVillage.setVotesPercentage(new BigDecimal((Long)values[5] * 100.0/(Long)values[6]).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				else
					resultInVillage.setVotesPercentage("0");
				resultInVillage.setConstiId((Long)values[7]);
				resultInVillage.setConstiName(values[8].toString());
				resultsInVillage.add(resultInVillage);
				villagesInfo.put(electionInfo, resultsInVillage);
			}

			for(Map.Entry<ElectionWiseMandalPartyResultVO, List<PartyElectionResultVO>> entry:villagesInfo.entrySet()){
				electionInfo = entry.getKey();
				electionInfo.setPartyResultsInElection(entry.getValue());
				allElectionsOfPartyByVillages.add(electionInfo);
			}
			
			constituencyRevenueVillagesVO.setElectionInfoByLocations(allElectionsOfPartyByVillages);
			
		}catch(Exception ex){
			constituencyRevenueVillagesVO.setExceptionEncountered(ex);
			ex.printStackTrace();
		}
		
		return constituencyRevenueVillagesVO;
		
	}
	
	public List<SelectOptionVO> createSelectOptionVoForRawList(List objects){
		List<SelectOptionVO> namesAndIds = new ArrayList<SelectOptionVO>();
		for(int i=0; i<objects.size(); i++){
			Object[] values = (Object[])objects.get(i);
			namesAndIds.add(new SelectOptionVO((Long)values[0], values[1].toString()));
		}
		return namesAndIds;
	}
	
	public List<PartyVotesEarnedVO> getTownshipWiseElectionsForTehsil(Long townshipId, Long electionId){
		List mandalWiseInfo = candidateBoothResultDAO.findBoothResultsForTownshipAndElection(townshipId, electionId);
		List<PartyVotesEarnedVO> partyResults = new ArrayList<PartyVotesEarnedVO>();
		PartyVotesEarnedVO partyVotesEarnedVO = null;
		for(int i=0; i<mandalWiseInfo.size(); i++){
			partyVotesEarnedVO = new PartyVotesEarnedVO();
			Object[] values = (Object[])mandalWiseInfo.get(i);
			partyVotesEarnedVO.setPartyId((Long)values[0]);
			partyVotesEarnedVO.setPartyName(values[1].toString());
			partyVotesEarnedVO.setVotesEarned((Long)values[2]);
			partyResults.add(partyVotesEarnedVO);
		}
		return partyResults;
	}
	
	public ResultWithExceptionVO saveAndUpdateHamletAndBoothInfo(HamletAndBoothVO hamletWithBoothId){
		hamletAndBoothVO = hamletWithBoothId;
		ResultWithExceptionVO resultWithExceptionVO = (ResultWithExceptionVO)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus resultStatus = new ResultStatus(); 
				List<VillageBoothInfoVO> villagesInfo = new ArrayList<VillageBoothInfoVO>();
				try{
					VillageBoothInfoVO villageBoothInfoVO;
					Long townshipId = hamletAndBoothVO.getHamletId();
					Township township = townshipDAO.get(townshipId);
					BoothConstituencyElection boothConstituencyElection;
					VillageBoothElection villageBoothElection;
					List<Long> boothIds = hamletAndBoothVO.getBoothConstituencyElectionIds();
					
					for(Long boothId:boothIds){
						boothConstituencyElection = boothConstituencyElectionDAO.get(boothId);
						villageBoothElection = villageBoothElectionDAO.save(new VillageBoothElection(township, boothConstituencyElection));
						villageBoothInfoVO = new VillageBoothInfoVO();
						villageBoothInfoVO.setVillageBoothElectionId(villageBoothElection.getVillageBoothElectionId());
						villageBoothInfoVO.setPartNo(boothConstituencyElection.getBooth().getPartNo());
						villageBoothInfoVO.setVillagesCovered(boothConstituencyElection.getBooth().getvillagesCovered());
						villageBoothInfoVO.setVillageName(villageBoothElection.getTownship().getTownshipName());
						villageBoothInfoVO.setConstituencyName(boothConstituencyElection.getConstituencyElection().getConstituency().getName());
						villagesInfo.add(villageBoothInfoVO);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
					resultStatus.setExceptionEncountered(e);
				}
				hamletAndBoothVO = null;
				return new ResultWithExceptionVO(villagesInfo, resultStatus);
			}
			
		});
		return resultWithExceptionVO;
	}
	
	
	public ResultWithExceptionVO deleteVillageBoothElectionRecord(HamletAndBoothVO villageBoothElectionId){
		hamletAndBoothVO = villageBoothElectionId;
		ResultWithExceptionVO result = (ResultWithExceptionVO)transactionTemplate.execute(new TransactionCallback(){

			public Object doInTransaction(TransactionStatus status) {
				ResultStatus resultStatus = new ResultStatus();
				try{
					villageBoothElectionDAO.remove(hamletAndBoothVO.getHamletId());
				}catch(Exception e){
					e.printStackTrace();
					resultStatus.setExceptionEncountered(e);
				}			
				return new ResultWithExceptionVO(null, resultStatus);
			}
			
		});
		return result;
	}
	
	public ConstituencyVO getVotersInfoInMandalsForConstituency(Long constituencyId)
	{
		ConstituencyVO constituencyVO = new ConstituencyVO();
		Constituency constituency = constituencyDAO.get(constituencyId);
		constituencyVO.setId(constituencyId);
		constituencyVO.setName(constituency.getName());
		constituencyVO.setElectionType(constituency.getElectionScope().getElectionType().getElectionType());
		
		if(constituencyVO.getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			getAssembliesVotersInfoOfParliament(constituencyVO);
			return constituencyVO;
		}
		
		List mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(constituencyId);
		List<VotersWithDelimitationInfoVO> votersWithDelimitationInfoVOList = new ArrayList<VotersWithDelimitationInfoVO>();
		List<VotersInfoForMandalVO> votersInfoForMandalList = null;
		
		 
		Map<String, String> mandalsIdsYear = new HashMap<String, String>();
		Map<String, List<String>> isPartialByYear = new HashMap<String, List<String>>();
		List<String> isPartialInfoForMandal = null;
		for (int i = 0; i < mandalsList.size(); i++) 
		{
			Object[] obj = (Object[]) mandalsList.get(i);			
			String year = obj[2].toString();
			String partialData = obj[3].toString();
			isPartialInfoForMandal = isPartialByYear.get(year);
			if(isPartialInfoForMandal == null)
				isPartialInfoForMandal = new ArrayList<String>();
			isPartialInfoForMandal.add(partialData);
			
			isPartialByYear.put(year, isPartialInfoForMandal);	
						
			String value = mandalsIdsYear.get(year);
			StringBuilder ids = new StringBuilder();
			if(value==null){
				ids .append(obj[0].toString());
			}else{
				ids.append(value).append(IConstants.COMMA).append(obj[0].toString());
			}
			mandalsIdsYear.put(year, ids.toString());
			
		}	
		
		Set<String>  keys = mandalsIdsYear.keySet();
		for(String key:keys)
		{	
			votersInfoForMandalList = new ArrayList<VotersInfoForMandalVO>();
			VotersWithDelimitationInfoVO votersWithDelimitationInfoVO = new VotersWithDelimitationInfoVO();
			votersWithDelimitationInfoVO.setYear(key);
			
			String value = mandalsIdsYear.get(key);
			List<String> partailData = isPartialByYear.get(key);
			List votersList = boothConstituencyElectionDAO.findVoterInformationByMandalIdsAndDelimitationYear(value, key, constituencyId);
			
			for(int j = 0;j<votersList.size();j++)
			{
				VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
				
				Object[] vObj = (Object[]) votersList.get(j);
				votersInfo.setMandalId( ((Long)vObj[0]).toString());
				votersInfo.setMandalName(vObj[1].toString());
				votersInfo.setTotalMaleVoters(vObj[2].toString());
				votersInfo.setTotalFemaleVoters(vObj[3].toString());
				votersInfo.setTotalVoters(vObj[4].toString());
				
				Object[] obj = (Object[]) mandalsList.get(j);
				
				if(partailData.get(j).equalsIgnoreCase("1"))
					votersInfo.setIsPartial("NO");
				else if(partailData.get(j).equalsIgnoreCase("0"))
					votersInfo.setIsPartial("Yes");
				else
					votersInfo.setIsPartial("Data Not found");
				
				
				votersInfoForMandalList.add(votersInfo);
			}
			votersWithDelimitationInfoVO.setVotersInfoForMandalVO(votersInfoForMandalList);
			votersWithDelimitationInfoVOList.add(votersWithDelimitationInfoVO);
		}
		
		constituencyVO.setAssembliesOfParliamentInfo(votersWithDelimitationInfoVOList);
		return constituencyVO;
		
	}
		
	public List<CandidateInfoForConstituencyVO> extractCandidateNPartyDataFromList(List candidateList)
	{
		List<CandidateInfoForConstituencyVO> candidateInfoList = new ArrayList<CandidateInfoForConstituencyVO>();
		
		for(int i=0;i<candidateList.size();i++)
		{
			String candidateFullName = "";
			CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
			Object[] values = (Object[]) candidateList.get(i);	
			candidateInfo1.setConstituencyId((Long) values[0]);
			candidateInfo1.setConstituencyName(values[1].toString());
			candidateInfo1.setCandidateId((Long) values[2]);
			
			if(!StringUtils.isBlank((String)values[3]))
				candidateFullName = candidateFullName + ((String)values[3]) + " ";
			if(!StringUtils.isBlank((String)values[4]))
				candidateFullName = candidateFullName + ((String)values[4]) + " ";
			if(!StringUtils.isBlank((String)values[5]))
				candidateFullName = candidateFullName + ((String)values[5]);
			
			candidateInfo1.setCandidateName(candidateFullName);
			candidateInfo1.setPartyId((Long) values[6]);
			candidateInfo1.setParty(values[7].toString());
			candidateInfo1.setConstituencyType(values[9].toString());
			if(values[8] == null || values[8].toString().length() == 0)
				candidateInfo1.setDeformDate("");
			else
				candidateInfo1.setDeformDate(values[9].toString());
			if(values[10] != null){
				candidateInfo1.setPartyFlag(values[10].toString());
			}
			candidateInfo1.setLatestElecYear(values[11].toString());
			candidateInfoList.add(candidateInfo1);
		}
		
		return candidateInfoList;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId)
	{	
		String electionType = "";
		String deformDate = "";
		
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO ();
		
		/**
		 * DAO method call to get the election type and delimitation info.If delimitation info is null returning null.
		 */
		List constituencyTypeDetails = constituencyDAO.getConstituencyTypeAndDelimitationInfoByConstituencyId(constituencyId);
		if(constituencyTypeDetails != null && constituencyTypeDetails.size()>0)
		{
			Object[] obj = (Object[])constituencyTypeDetails.get(0);
			electionType = (String)obj[0];
			if(obj[1]!=null)
			 deformDate = (String)obj[1].toString();
		}
		
		if(!deformDate.equalsIgnoreCase("") || deformDate == null)
			return null;
		
		//---------------
		
		
		List candidateList = nominationDAO.getCandidateNPartyInfo(constituencyId.toString(), electionType, 1L);
		if(candidateList.size() == 0)
			return null;
		List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList);
		
		
		if(electionType.equalsIgnoreCase("Assembly")){
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);
				
			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			Object[] listData = (Object[]) list.get(0);
			Long asemblyId = (Long) listData[0];
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(extractCandidateNPartyDataFromList(nominationDAO.getParliamentCandidateNPartyInfo(asemblyId, IConstants.PARLIAMENT_ELECTION_TYPE, 1L)).get(0));
			
		}
		else if(electionType.equalsIgnoreCase("Parliament")){
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfoList.get(0));
			
			List assembliesData = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(constituencyId);
			
			StringBuilder idString = new StringBuilder();
			for(int j = 0 ; j < assembliesData.size() ; j++)
			{
				Object[] ids = (Object[]) assembliesData.get(j);
				idString.append(IConstants.COMMA).append((Long)ids[0]);
				
			}	
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(extractCandidateNPartyDataFromList(nominationDAO.getCandidateNPartyInfo(idString.substring(1), IConstants.ASSEMBLY_ELECTION_TYPE, 1L)));
		}
		
		return candidateDetailsForConstituencyTypesVO;
	}

	/**
	 * This Method Returns Election Results Of a Parliament Constituency (Assembly Constituencies Wise)
	 * And Assembly Constituency Election Results (Mandal Wise)
	 */
	public ConstituencyRevenueVillagesVO getConstituencyElecResults(Long constituencyId, String electionYear,Boolean includeOthers){
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = null;
		List<ConstituencyOrMandalWiseElectionVO> constituencyElectionResults = new ArrayList<ConstituencyOrMandalWiseElectionVO>();
		List<SelectOptionVO> missingConstituencies = null;
		ConstituencyOrMandalWiseElectionVO assemblyConstiElecVO = null;
		List<PartyElectionResultVO> partyElectionResultVOs = null;
		PartyElectionResultVO partyElectionResultVO = null;
		List<CandidatePartyInfoVO> candidateNamePartyAndStatus = new ArrayList<CandidatePartyInfoVO>();
		CandidatePartyInfoVO candidatePartyInfoVO = null;
		
		Constituency constituency = constituencyDAO.get(constituencyId);
		if(constituency.getElectionScope().getElectionType().getElectionType().
				equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			constituencyRevenueVillagesVO = getMandalElectionInfoForAConstituency(constituencyId, electionYear,IConstants.ASSEMBLY_ELECTION_TYPE,includeOthers);
			constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
			constituencyRevenueVillagesVO.setConstituencyName(constituency.getName());
			constituencyRevenueVillagesVO.setElectionType(constituency.getElectionScope().
					getElectionType().getElectionType());
			return constituencyRevenueVillagesVO;
		}
		
		if(constituency.getElectionScope().getElectionType().getElectionType().
				equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			boolean isDataInsufficient = false;
			constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
			missingConstituencies = new ArrayList<SelectOptionVO>();
			constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
			constituencyRevenueVillagesVO.setConstituencyName(constituency.getName());
			constituencyRevenueVillagesVO.setElectionType(constituency.getElectionScope().
					getElectionType().getElectionType());
			List<SelectOptionVO> assemblies = getAssembliesForParliament(constituencyId, new Long(electionYear));
			int i=0;
			
			for(SelectOptionVO assembly:assemblies){	
				List partiesResults = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForParties(assembly.getId(), constituencyId, electionYear);
				System.out.println(assembly.getName()+" Parties Results::"+partiesResults.size());
				assemblyConstiElecVO = new ConstituencyOrMandalWiseElectionVO();
				partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
				assemblyConstiElecVO.setLocationId(assembly.getId());
				assemblyConstiElecVO.setLocationName(assembly.getName());
				
				if(partiesResults.size() == 0){
					isDataInsufficient = true;
					missingConstituencies.add(new SelectOptionVO(assembly.getId(), assembly.getName()));
					continue;
				}	
				
				for(Object[] values:(List<Object[]>)partiesResults){
					if(i == 0){
						candidatePartyInfoVO = new CandidatePartyInfoVO();
						candidatePartyInfoVO.setPartyId((Long)values[0]);
						candidatePartyInfoVO.setParty(values[1].toString());
						candidatePartyInfoVO.setRank((Long)values[2]);
						candidateNamePartyAndStatus.add(candidatePartyInfoVO);
					}
					partyElectionResultVO = new PartyElectionResultVO();
					partyElectionResultVO.setVotesEarned((Long)values[3]);
					partyElectionResultVO.setVotesPercentage(new BigDecimal(((Long)values[3])*100.0/(Long)values[4])
																	.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					partyElectionResultVOs.add(partyElectionResultVO);
				}
				assemblyConstiElecVO.setPartyElectionResultVOs(partyElectionResultVOs);
				constituencyElectionResults.add(assemblyConstiElecVO);
				i++;
			}
			
			Long totalValidVotes = ((Double)constituencyElectionDAO.findTotalValidVotesInConstituencyElection(
					constituency.getConstituencyId(), electionYear).get(0)).longValue();
			
			Long boothWiseValidVotes = (Long)boothConstituencyElectionDAO.findTotalBoothWiseValidVotesForConstituencyElection(
					constituency.getConstituencyId(), electionYear).get(0);
			
			constituencyRevenueVillagesVO.setIsDataInsufficient(isDataInsufficient);
			constituencyRevenueVillagesVO.setMissingConstituencies(missingConstituencies);
			
			//Calculating the Parties Results Of Missing Assembly Constituencies And Postal Ballet Votes
			
			Long totalVotesEarnedForEachPartyBoothWise = 0l;
			List partiesInfoInPC = nominationDAO.findCandidatesInfoByConstituencyAndElectionYear(constituencyId, electionYear);
			Long partyBaletVotes = 0l;
			Long totalBaletVotes = totalValidVotes - boothWiseValidVotes;
			
			
				partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
				assemblyConstiElecVO = new ConstituencyOrMandalWiseElectionVO();
				assemblyConstiElecVO.setLocationName(IConstants.OTHERS);
				
				if(partiesInfoInPC.size() != candidateNamePartyAndStatus.size()){
					//Exception
				}
					
				for(i=0; i<candidateNamePartyAndStatus.size(); i++){
					Object[] values = (Object[])partiesInfoInPC.get(i);
					totalVotesEarnedForEachPartyBoothWise = 0l;			
					
					for(int j=0; j<constituencyElectionResults.size(); j++)					
						totalVotesEarnedForEachPartyBoothWise += constituencyElectionResults.get(j).
									getPartyElectionResultVOs().get(i).getVotesEarned();
					
					partyBaletVotes = ((Double)values[2]).longValue() - totalVotesEarnedForEachPartyBoothWise;
					if(partyBaletVotes<0){
						partyBaletVotes = 0l; //extra information need to be added 
						log.debug("Other votes contains a negative value.");
					}
					partyElectionResultVO = new PartyElectionResultVO();
					partyElectionResultVO.setVotesEarned(partyBaletVotes);
					if(totalBaletVotes > 0 && (partyBaletVotes < totalBaletVotes))
						partyElectionResultVO.setVotesPercentage(new BigDecimal(partyBaletVotes*100.0/totalBaletVotes)
																	.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					else{
						log.error("Valid Votes Are Less Than Candidate Votes:totalBaletVotes-"+totalBaletVotes+" Party Votes-"+partyBaletVotes+" Party Name-"+values[1]);
						partyElectionResultVO.setVotesPercentage("0");	
					}
					
					partyElectionResultVOs.add(partyElectionResultVO);
				}
					
				assemblyConstiElecVO.setPartyElectionResultVOs(partyElectionResultVOs);
				
				constituencyElectionResults.add(assemblyConstiElecVO);
		}
		
		constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
		constituencyRevenueVillagesVO.setConstituencyOrMandalWiseElectionVO(constituencyElectionResults);
		return constituencyRevenueVillagesVO;
	}
	
	private List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear){
		List<Constituency> constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(parliamentId, electionYear);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
			constituencyVOs.add(new SelectOptionVO(constituency.getConstituencyId(), constituency.getName().toUpperCase()));
		return constituencyVOs;
	}
	
	/** 
	 *This method returns a VO containing all the information regarding the
	 * parliament candidate voting trends in the assembly constitutency.
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyRevenueVillagesVO> getMandalElectionInfoForAParliamentConstituency(Long constituencyId,String electionYear){
		try{
			if(log.isDebugEnabled())
				log.debug("Calling delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear() method..");
		List result = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(constituencyId, Long.parseLong(electionYear));
		List<Long> parliamentConstituencies = new ArrayList<Long>(0);
		Map<Long,String> parliamentConstituenciesNames = new HashMap<Long,String>(0);
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();	
		List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVo = new ArrayList<ConstituencyRevenueVillagesVO>(0);
		StringBuilder tehsilIds = new StringBuilder();
		Long constituencyID=null;
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			parliamentConstituencies.add(Long.parseLong(parms[0].toString()));
			parliamentConstituenciesNames.put(Long.parseLong(parms[0].toString()), parms[1].toString());
		}
		if(log.isDebugEnabled())
			log.debug("Calling candidateBoothResultDAO.getMandalsForAConstituencyForAGivenYear() method..");		
		List list = delimitationConstituencyMandalDAO.getMandalDetailsForAConstituency(constituencyId,Long.parseLong(electionYear));  //Check code here
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			tehsilIds.append(",").append(Long.parseLong(parms[0].toString()));
		}
		if(log.isDebugEnabled())
			log.debug("Calling candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalByPaliamentWise() method..");
		for(int j=0;j<parliamentConstituencies.size();j++){
			constituencyID = Long.parseLong(parliamentConstituencies.get(j).toString());
			List candidateResult = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalByPaliamentWise(constituencyID,tehsilIds.substring(1),electionYear);
			constituencyRevenueVillagesVO = setDataForVOForCorrespondingAssemblyOrParliament(candidateResult,constituencyID,parliamentConstituenciesNames.get(constituencyID),electionYear,IConstants.PARLIAMENT_ELECTION_TYPE,true);
			constituencyRevenueVillagesVo.add(constituencyRevenueVillagesVO);
		}		
		return constituencyRevenueVillagesVo;
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getMandalElectionInfoForAParliamentConstituency() method of Constituency Page Service.");
			}
			return null;
		}
	}
	
	/** 
	 *This method returns a VO containing all the information regarding the
	 * assembly candidate voting trends in the assembly constituency.
	 */
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear,String electionType,Boolean includeOthers){	
		try{
		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(constituencyId,electionYear);
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
		if(log.isDebugEnabled()){
			log.debug("Calling setDataForVOForCorrespondingAssemblyOrParliament()");
		}
		constituencyRevenueVillagesVO = setDataForVOForCorrespondingAssemblyOrParliament(list,constituencyId,null,electionYear,electionType,includeOthers);
		return constituencyRevenueVillagesVO;		
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getMandalElectionInfoForAConstituency() method of Constituency Page Service.");
			}
			return null;
		}		
	}
	/**
	 * This method takes the result obtained from the DAO call and calls other methods for processing and sets data into VO.
	 * @param list
	 * @param constituencyId
	 * @param constituencyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyRevenueVillagesVO setDataForVOForCorrespondingAssemblyOrParliament(List list,final Long constituencyId,String constituencyName,String electionYear,String electionType,Boolean includeOthers){
		if(log.isDebugEnabled()){
			log.debug("Entered into setDataForVOForCorrespondingAssemblyOrParliament() method..");
		}
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
		List<CandidatePartyInfoVO> candidateNamePartyAndStatus = new ArrayList<CandidatePartyInfoVO>(0);
		List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO = new ArrayList<ConstituencyOrMandalWiseElectionVO>(0);
		Long tehsilId = -1l,mainTehsilId=0l,totalVotes=0l;
		Map<Long,Long> totalVotesForAMandal = new HashMap<Long,Long>(0);
		Map<Long,String> partyNameAndRank = new HashMap<Long,String>(0);
		Map<Long,String> tehsilNameAndIds = new HashMap<Long,String>(0);
		List<PartyElectionResultVO> partyElectionResultVOs = new ArrayList<PartyElectionResultVO>(0);
		List<PartyElectionResultVO> partyVotes = new ArrayList<PartyElectionResultVO>(0);
		List<Long> tehsilIds = new ArrayList<Long>(0);		
		try{
			for(int i=0; i<list.size(); i++){
				Object[] parms = (Object[])list.get(i);
				if(tehsilId == -1){
					tehsilId = (Long)parms[1];
					totalVotes += (Long)parms[5];
					mainTehsilId = tehsilId;
					tehsilIds.add(tehsilId);
				}else{
					PartyElectionResultVO partyElectionResultVo = new PartyElectionResultVO();
					if(tehsilId==Long.parseLong(parms[1].toString())){					
						totalVotes += (Long)parms[5];
						partyElectionResultVo.setTotalVotes((Long)parms[5]);
						totalVotesForAMandal.put(tehsilId, totalVotes);
					}else{
						partyElectionResultVOs.add(partyElectionResultVo);						
						tehsilId = (Long)parms[1];						
						tehsilIds.add(tehsilId);
						totalVotes = 0l;
						totalVotes += (Long)parms[5];
					}
				}  
				partyNameAndRank.put((Long)parms[2],parms[4].toString());
				tehsilNameAndIds.put((Long)parms[1],parms[0].toString());
			}
		
			for(int i=0; i<tehsilIds.size(); i++){				
				ConstituencyOrMandalWiseElectionVO constituencyOrMandalWiseElectionVo = new ConstituencyOrMandalWiseElectionVO();
				if(log.isDebugEnabled()){
					log.debug("Calling caluculatePercentage()");
				}
				partyVotes = caluculatePercentage(tehsilIds.get(i),list,totalVotesForAMandal.get(tehsilIds.get(i)));	
				constituencyOrMandalWiseElectionVo.setLocationId(tehsilIds.get(i));
				constituencyOrMandalWiseElectionVo.setLocationName(tehsilNameAndIds.get(tehsilIds.get(i)));
				constituencyOrMandalWiseElectionVo.setTotalPolledVotes(totalVotesForAMandal.get(tehsilIds.get(i)));
				constituencyOrMandalWiseElectionVo.setPartyElectionResultVOs(partyVotes);
				if(log.isDebugEnabled()){
					log.debug("Calling getCandidateAndPartyDetails()");
				}
				
				if(!includeOthers){
					try{
					List totalVoters = boothConstituencyElectionDAO.getTotalVotersInAnElectionInMandal(tehsilIds.get(i),electionType,electionYear);
					if(totalVoters != null){
						Object params = (Object)totalVoters.get(0);
						constituencyOrMandalWiseElectionVo.setTotalVoters((Long)params);
					}
					}
					catch(Exception ex){
						ex.printStackTrace();
						log.debug(" Exception :" + ex);
					}
				}
				candidateNamePartyAndStatus = getCandidateAndPartyDetails(mainTehsilId,list);
				constituencyOrMandalWiseElectionVO.add(constituencyOrMandalWiseElectionVo);
			}
			if(constituencyOrMandalWiseElectionVO != null && constituencyOrMandalWiseElectionVO.size() > 0)
			    Collections.sort(constituencyOrMandalWiseElectionVO, new ConstituencyOrMandalVOComparator());
			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) && includeOthers){
				ConstituencyOrMandalWiseElectionVO constituencyOrMandalWiseElectionVo = new ConstituencyOrMandalWiseElectionVO();			
				constituencyOrMandalWiseElectionVo.setLocationId(tehsilIds.size()+1l);
				constituencyOrMandalWiseElectionVo.setLocationName(IConstants.OTHERS);
				constituencyOrMandalWiseElectionVo.setTotalPolledVotes(0l);
				constituencyOrMandalWiseElectionVo.setPartyElectionResultVOs(OtherVotesDataForAConstituency(constituencyId,electionYear,electionType
				));
				constituencyOrMandalWiseElectionVO.add(constituencyOrMandalWiseElectionVo);
			}			
			if(constituencyId!=null && constituencyName!=null){
				constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
				constituencyRevenueVillagesVO.setConstituencyName(constituencyName);
			}else{}			
			if(!includeOthers){
				Collections.sort(constituencyOrMandalWiseElectionVO, new ConstituencyOrMandalComparatorForTotalVoters());
			}
			constituencyRevenueVillagesVO.setConstituencyOrMandalWiseElectionVO(constituencyOrMandalWiseElectionVO);		
			constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
				
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getMandalElectionInfoForAConstituency() method of Constituency Page Service.");
			}
			return null;
		}
			return constituencyRevenueVillagesVO;
	}
	
	/**
	 * This method calculates all the Missing Votes For an Assembly(i.e., Postal Ballot Votes).
	 * @param constituencyId
	 * @param electionYear
	 * @param electionType
	 * @return
	 */
	public List<PartyElectionResultVO> OtherVotesDataForAConstituency(Long constituencyId,String electionYear,String electionType){
		Map<Long,Double> candidateIdAndTheirVotes = new HashMap<Long,Double>(0);
		SortedMap<Long,Double> otherVotes = new TreeMap<Long,Double>();	
		SortedMap<Long,Long> candidateIdAndTheirRanks = new TreeMap<Long,Long>();
		SortedMap<Long,Long> candidateIdAndTheirParty = new TreeMap<Long,Long>();
		List<PartyElectionResultVO> partyElectionResultVOs = new ArrayList<PartyElectionResultVO>(0);
		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(constituencyId,electionYear);
		List nominationResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(constituencyId.toString(),electionYear,electionType);
		ListIterator it = nominationResult.listIterator();
		while(it.hasNext()){
			Object[] parms = (Object[])it.next();
			candidateIdAndTheirVotes.put(Long.parseLong(parms[0].toString()), (Double)parms[2]);
			candidateIdAndTheirRanks.put(Long.parseLong(parms[0].toString()), Long.parseLong(parms[4].toString()));
		}		
		Long votes=0l;
		Long candidateId =0l;
		Double differenceVotes = 0d,totalDifferenceVotes=0d;		
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);		
			votes = (Long)parms[5];
			candidateId = Long.parseLong(parms[6].toString());
			differenceVotes = (candidateIdAndTheirVotes.get(Long.parseLong(parms[6].toString())))-votes;
			candidateIdAndTheirVotes.put(candidateId, differenceVotes);	
			candidateIdAndTheirParty.put(candidateId, Long.parseLong(parms[7].toString()));
			otherVotes.put(candidateId,differenceVotes);
		}
		Collection<Double> otherVotersSet = otherVotes.values();
		Iterator iterate = otherVotersSet.iterator();
		while(iterate.hasNext()){
			totalDifferenceVotes += (Double)iterate.next();
		}	
		for(Map.Entry<Long,Double> votesPopulate:otherVotes.entrySet()){
			PartyElectionResultVO partyVotes = new PartyElectionResultVO(); 
			Long votesEarned =new Double(votesPopulate.getValue()).longValue();
			if(votesEarned<0){
				partyVotes.setVotesEarned(0l);
				log.fatal("========================");
				log.fatal("========================");
				log.fatal("Invalid Data......"); 
				log.fatal("========================");
				log.fatal("========================");
			}else{
				partyVotes.setVotesEarned(votesEarned);		
				partyVotes.setPartyId(candidateIdAndTheirParty.get(votesPopulate.getKey()));
				log.debug("votes Earned:"+partyVotes.getVotesEarned());
				log.debug("votes party:"+partyVotes.getPartyId());
			}
			if((votesEarned*100)/totalDifferenceVotes<0){
				partyVotes.setVotesPercentage("0");
				log.fatal("========================");
				log.fatal("========================");
				log.fatal("Invalid Data......");
				log.fatal("========================");
				log.fatal("========================");
			}else{
				partyVotes.setVotesPercentage(new BigDecimal((votesEarned*100)/totalDifferenceVotes).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			}			
			partyVotes.setRank(candidateIdAndTheirRanks.get(votesPopulate.getKey()));
			partyElectionResultVOs.add(partyVotes);
		}
		Collections.sort(partyElectionResultVOs, new SortByRankOnPartyElectionResultComparator());
		Collections.reverse(partyElectionResultVOs);		
		return partyElectionResultVOs;	
	}
	
	/**
	 * This method sets all the details regarding the candidate and partys in the candidate party info vo.
	 * @param tehsilId
	 * @param result
	 * @return
	 */
	public List<CandidatePartyInfoVO> getCandidateAndPartyDetails(Long tehsilId,List result) {
		if(log.isDebugEnabled()){
			log.debug("Entered into getCandidateAndPartyDetails() method of Constituency Page Service.");
		}
		List<CandidatePartyInfoVO> candidatePartyInfoVO = new ArrayList<CandidatePartyInfoVO>(0);
		try{
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			if(tehsilId==Long.parseLong(parms[1].toString())){
				CandidatePartyInfoVO candidatePartyInfoVo = new CandidatePartyInfoVO();
				candidatePartyInfoVo.setCandidateId((Long)parms[6]);
				candidatePartyInfoVo.setCandidateName(parms[3].toString());
				candidatePartyInfoVo.setPartyId((Long)parms[7]);
				candidatePartyInfoVo.setParty(parms[4].toString());
				candidatePartyInfoVo.setRank((Long)parms[2]);
				candidatePartyInfoVO.add(candidatePartyInfoVo);
				}else{	
					return candidatePartyInfoVO;
				}
			}
		}catch(Exception e){
				e.printStackTrace();
				if(log.isDebugEnabled()){
					log.debug("Exception raised in getCandidateAndPartyDetails() method of Constituency Page Service.");
				}
				return null;
			}
		return candidatePartyInfoVO;
	}
	/**
	 * This method caluculates the Percentage of votes for all the candidates in a mandal
	 * @param tehsilId
	 * @param result
	 * @param totalVotes
	 * @return
	 */
	public List<PartyElectionResultVO> caluculatePercentage(Long tehsilId,List result,Long totalVotes){
		if(log.isDebugEnabled()){
			log.debug("Entered into caluculatePercentage() method of Constituency Page Service.");
		}
		List<PartyElectionResultVO> partyVotes = new ArrayList<PartyElectionResultVO>(0); 
		try{
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				if(tehsilId==Long.parseLong(parms[1].toString())){				
					if(parms[5]!= null && totalVotes != null){
						PartyElectionResultVO partyElectionResultVO =new PartyElectionResultVO();
						partyElectionResultVO.setVotesEarned((Long)parms[5]);
						partyElectionResultVO.setVotesPercentage(new BigDecimal(((Long)parms[5]*100.0)/totalVotes).setScale(2,BigDecimal.ROUND_HALF_UP).toString());		
						partyVotes.add(partyElectionResultVO);	
					}								
				}else{			
				}
			}	
	}catch(Exception e){
		e.printStackTrace();
		if(log.isDebugEnabled()){
			log.debug("Exception raised in caluculatePercentage() method of Constituency Page Service.");
		}
		return null;
	}
		return partyVotes;		
	}
	
	//Need To be Refactored For Alliance Parties 
	@SuppressWarnings("unchecked")
	public List<PartyResultVO> getMandalsResultsInAnElection(String mandalIds, String electionYear, String electionType){
		List validVotes = boothResultDAO.getAllPolledVotesForMandalsInAnElection(mandalIds, electionYear, electionType);
		List<PartyResultVO> partiesResultsInMandals = null;
		List<PartyResultVO> finalResults = new ArrayList<PartyResultVO>();
		PartyResultVO partyResultVO = null;
		Map<Long, Long> mandalAndValidVotesMap = new LinkedHashMap<Long, Long>();
		for(Object[] values:(List<Object[]>)validVotes)
			mandalAndValidVotesMap.put((Long)values[1], (Long)values[3]);
		List partiesResults = candidateBoothResultDAO.getResultsForElectionForAllMandalsAndParties(mandalIds, electionYear, electionType);
		
		//Mandalwise All Parties Info
		Map<Long, List<PartyResultVO>> mandalwisePartiesMap = new HashMap<Long, List<PartyResultVO>>();
		
		//Party Name and Votes Earned Map With In A Mandal
		Map<String, Long> partyVotesEarnedMap = new HashMap<String, Long>();
		
		Long mandalId = 0l;
		String partyName = "";
		Long votesEarned = 0l;
		Float validVotesPercent = 0.0f;
		try{
			for(Object[] values:(List<Object[]>)partiesResults){
				partyName = values[2].toString();
				votesEarned = partyVotesEarnedMap.get(partyName);
				if(votesEarned == null)
					votesEarned = (Long)values[3];
				else
					votesEarned += (Long)values[3];
				
				partyVotesEarnedMap.put(partyName, votesEarned);
				
				mandalId = (Long)values[0];
				partiesResultsInMandals = mandalwisePartiesMap.get(mandalId);
				if(partiesResultsInMandals == null)
					partiesResultsInMandals = new ArrayList<PartyResultVO>();
				partyResultVO = new PartyResultVO();
				partyResultVO.setConstituencyName(values[1].toString());
				partyResultVO.setPartyName(values[2].toString());
				partyResultVO.setVotesPercent(new BigDecimal((Long)values[3]*100.0/mandalAndValidVotesMap.get(values[0])).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				partiesResultsInMandals.add(partyResultVO);
				mandalwisePartiesMap.put(mandalId, partiesResultsInMandals);
			}
			
			String grp1 = "";
			String grp2 = "";
			
			if(partyVotesEarnedMap.get(IConstants.BJP) != null && partyVotesEarnedMap.get(IConstants.TDP) != null){
				if(partyVotesEarnedMap.get(IConstants.TDP) > partyVotesEarnedMap.get(IConstants.BJP))
					grp1 = IConstants.TDP;
				else
					grp1 = IConstants.BJP;	
			}else{
				if(partyVotesEarnedMap.get(IConstants.BJP) == null)
					grp1 = IConstants.TDP;
				else
					grp1 = IConstants.BJP;
			}
			
			if(partyVotesEarnedMap.get(IConstants.INC) != null && partyVotesEarnedMap.get(IConstants.TRS) != null){
				if(partyVotesEarnedMap.get(IConstants.INC) > partyVotesEarnedMap.get(IConstants.TRS))
					grp2 = IConstants.INC;
				else
					grp2 = IConstants.TRS;	
			}else{
				if(partyVotesEarnedMap.get(IConstants.TRS) == null)
					grp2 = IConstants.INC;
				else
					grp2 = IConstants.TRS;
			}
					
			Float votesPercent = 0.0f;
			List totalVoters = null;
			Long totalVotersLong = 0l;
			String mandalName = "";
			//Calculating And Grouping Alliance Parties Percentages
			for(Map.Entry<Long, List<PartyResultVO>> entry:mandalwisePartiesMap.entrySet()){
				partiesResultsInMandals = entry.getValue();
				totalVoters = boothConstituencyElectionDAO.getTotalVotersInAnElectionInMandal(entry.getKey(),electionType,IConstants.PRESENT_ELECTION_YEAR);
				totalVotersLong = (Long)totalVoters.get(0);
				votesPercent = 0.0f;
				validVotesPercent = 0.0f;
				for(PartyResultVO party:partiesResultsInMandals){
					mandalName = party.getConstituencyName();
					if(party.getPartyName().equals(IConstants.INC) || party.getPartyName().equals(IConstants.TRS)){
						votesPercent += new Float(party.getVotesPercent());
						continue;
					}
					if(party.getPartyName().equals(IConstants.BJP) || party.getPartyName().equals(IConstants.TDP)){
						validVotesPercent += new Float(party.getVotesPercent());
						continue;
					}
					party.setElectors(totalVotersLong);
					finalResults.add(party);
				}				
				finalResults.add(getPartyResultVOObjWithPercent(grp2, votesPercent.toString(), totalVotersLong, mandalName));
				finalResults.add(getPartyResultVOObjWithPercent(grp1, validVotesPercent.toString(), totalVotersLong, mandalName));
			}
		}catch (Exception e) {
			log.debug("Exception Occured While Processing Mandal Data");
			e.printStackTrace();
		}

		if(finalResults != null && finalResults.size() > 0)
			Collections.sort(finalResults, new PartyResultVOComparatorByElectors());

		return finalResults;
	}
	
	private PartyResultVO getPartyResultVOObjWithPercent(String partyName, String percenatge, Long electorsInMandal, String mandalName){
		PartyResultVO partyResultVO = new PartyResultVO();
		partyResultVO.setPartyName(partyName);
		partyResultVO.setVotesPercent(percenatge);
		partyResultVO.setElectors(electorsInMandal);
		partyResultVO.setConstituencyName(mandalName);
		return partyResultVO;
	}
	
	@SuppressWarnings("unchecked")
	public void getAssembliesVotersInfoOfParliament(ConstituencyVO constituencyVO){
		List list = delimitationConstituencyAssemblyDetailsDAO.getAllAssembliesOfParliament(constituencyVO.getId());
		Map<String, String> yearAndIdsMap = new HashMap<String,String>();
		String acId = null;
		String year = "";
		List<VotersWithDelimitationInfoVO> votersWithDelimitationInfoVOs = new ArrayList<VotersWithDelimitationInfoVO>();
		List<VotersInfoForMandalVO> votersInfoForAcVOs = null;
		VotersWithDelimitationInfoVO votersWithDelimitationInfoVO = null;
		VotersInfoForMandalVO votersInfoForAcVO = null;
		
		for(Object[] values:(List<Object[]>)list){
			acId = values[0].toString();
			year = values[2].toString();
			String value = yearAndIdsMap.get(year);
			StringBuilder ids = new StringBuilder();
			if(value==null){
				ids .append(acId);
			}else{
				ids.append(value).append(IConstants.COMMA).append(acId);
			}
			yearAndIdsMap.put(year, ids.toString());
		}
		
		for(Map.Entry<String, String> entry:yearAndIdsMap.entrySet()){
			List acVotersInfo = boothConstituencyElectionDAO.findAssemblyConstituenciesDetailsForParliament(entry.getValue(), entry.getKey());
			votersWithDelimitationInfoVO = new VotersWithDelimitationInfoVO();
			votersInfoForAcVOs = new ArrayList<VotersInfoForMandalVO>();
			votersWithDelimitationInfoVO.setYear(entry.getKey());
			for(Object[] values:(List<Object[]>)acVotersInfo){
				votersInfoForAcVO = new VotersInfoForMandalVO();
				votersInfoForAcVO.setMandalId(values[0].toString());
				votersInfoForAcVO.setMandalName(values[1].toString());
				votersInfoForAcVO.setTotalMaleVoters(values[2].toString());
				votersInfoForAcVO.setTotalFemaleVoters(values[3].toString());
				votersInfoForAcVO.setTotalVoters(values[4].toString());
				votersInfoForAcVOs.add(votersInfoForAcVO);
			}
			votersWithDelimitationInfoVO.setVotersInfoForMandalVO(votersInfoForAcVOs);
			votersWithDelimitationInfoVOs.add(votersWithDelimitationInfoVO);
		}
		constituencyVO.setAssembliesOfParliamentInfo(votersWithDelimitationInfoVOs);
	}

	/** 
	 * This method is used to get all party voting trendz for a zptc or mptc for a particular election year. 
	 */
	public List<TeshilPartyInfoVO> getPartyWiseZptcOrMptcElectionDataForAConstituency(Long constituencyId,String electionYear,String electionType,String constituencyType){	
		try{
			if(log.isDebugEnabled()){
				log.debug("Inside getPartyWiseZptcOrMptcElectionDataForAConstituency() method");
				log.debug("Received Parameters are--->");
				log.debug(constituencyId);
				log.debug(electionType);
				log.debug(electionYear);
				
			}
			StringBuilder tehsilIds = new StringBuilder();
			List<TeshilPartyInfoVO> teshilPartyInfoVO = new ArrayList<TeshilPartyInfoVO>();
			List<TeshilPartyInfoVO> allteshilPartyInfo = new ArrayList<TeshilPartyInfoVO>();
			if(log.isDebugEnabled())
				log.debug("Making delimitationConstituencyMandalService.getMandalsForDelConstituency DAO Call...");		
			if(constituencyType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
				List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();
				List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId); //Check code here
				ListIterator it = list.listIterator();
				while(it.hasNext()){
					Object[] parms = (Object[])it.next();
					tehsilIds.append(",").append(new Long(parms[0].toString()));
				}
			}else{
				List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();
				List assemblyList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesIdsAndNames(constituencyId,Long.parseLong(delimitationYear.get(0).toString()));
				for(int i=0;i<assemblyList.size();i++){
					Object[] assemblyListParms = (Object[])assemblyList.get(i);
					List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(Long.parseLong(assemblyListParms[0].toString())); //Check code here
					ListIterator it = list.listIterator();
					while(it.hasNext()){
						Object[] parms = (Object[])it.next();
						tehsilIds.append(",").append(new Long(parms[0].toString()));
					}				
				}				
			}				
			teshilPartyInfoVO = getTehsilPartyInfoForAConstituency(tehsilIds,electionYear,electionType,constituencyId);
			allteshilPartyInfo.addAll(teshilPartyInfoVO); 
			return allteshilPartyInfo;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public MandalAllElectionDetailsVO getAllTehsilElectionLevelWinnersForAConstituency(Long constituencyId,String candidateDetailsType,Long partyId,String electionType,String electionYear,String constituencyType){
		List<MandalAllElectionDetailsVO> allVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		List<MandalAllElectionDetailsVO> winningCandidateVotersDetails = new ArrayList<MandalAllElectionDetailsVO>(0);
		MandalAllElectionDetailsVO mandalAllElectionDetailsVo = new MandalAllElectionDetailsVO();
		Long winnerRank =1l,successorRank=2l;
		List successorCandidate,winningCandidate,allCandidates;
		int flag=0;
		StringBuilder tehsilIds = new StringBuilder();
				
		if(constituencyType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();
			List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId); //Check code here
			ListIterator it = list.listIterator();
			while(it.hasNext()){
				Object[] parms = (Object[])it.next();
				tehsilIds.append(",").append(new Long(parms[0].toString()));
			}
		}else{
			List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();
			List assemblyList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesIdsAndNames(constituencyId,Long.parseLong(delimitationYear.get(0).toString()));
			for(int i=0;i<assemblyList.size();i++){
				Object[] assemblyListParms = (Object[])assemblyList.get(i);
				List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(Long.parseLong(assemblyListParms[0].toString())); //Check code here
				ListIterator it = list.listIterator();
				while(it.hasNext()){
					Object[] parms = (Object[])it.next();
					tehsilIds.append(",").append(new Long(parms[0].toString()));
				}				
			}				
		}	
		
		if(candidateDetailsType.equalsIgnoreCase("winners")){
			successorCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,successorRank,partyId,electionType,electionYear);
			winningCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,winnerRank,partyId,electionType,electionYear);
			allVotersDetails = staticDataService.populateElectionsData(winningCandidate,successorCandidate,0,0,electionType);				
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);					
		}else if(candidateDetailsType.equalsIgnoreCase("allCandidates")){
			allCandidates = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,0l,partyId,electionType,electionYear);
			successorCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,successorRank,partyId,electionType,electionYear);
			winningCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,winnerRank,partyId,electionType,electionYear);
			winningCandidateVotersDetails = staticDataService.populateElectionsData(winningCandidate,successorCandidate,flag,0,electionType);
			allVotersDetails = staticDataService.populateElectionsDataForAllCandidates(winningCandidate,allCandidates,0,electionType);
			if(winningCandidateVotersDetails!=null){
				allVotersDetails.addAll(allVotersDetails.size(),winningCandidateVotersDetails);
			}
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);
		}else if(candidateDetailsType.equalsIgnoreCase("partyWise")){	
			successorCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,successorRank,partyId,electionType,electionYear);
			winningCandidate = getMandalLevelElectionCandidateDetailsForAConstituency(tehsilIds.substring(1),candidateDetailsType,winnerRank,partyId,electionType,electionYear);
			allVotersDetails = staticDataService.populateElectionsData(winningCandidate,successorCandidate,0,0,electionType);				
			mandalAllElectionDetailsVo.setAllVotersDetails(allVotersDetails);		
		}
		List result = nominationDAO.getPartysInfoForATehsilForAParticularElectionYear(electionType,tehsilIds.substring(1),electionYear);
		List<SelectOptionVO> constituencyWiseTehsilParties = new ArrayList<SelectOptionVO>();	
		SelectOptionVO select = new SelectOptionVO();
		select.setId(0l);
		select.setName("select");
		constituencyWiseTehsilParties.add(select);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			SelectOptionVO tehsilParties = new SelectOptionVO();
			tehsilParties.setId(new Long(parms[4].toString()));
			tehsilParties.setName(parms[0].toString());
			constituencyWiseTehsilParties.add(tehsilParties);
		}
	
		mandalAllElectionDetailsVo.setPartyInfo(constituencyWiseTehsilParties);
		return mandalAllElectionDetailsVo;	
	}				
	

	/**
	 * This method builds query dynamically to get allCandidates,winners,and candidates that have participated in that 
	 * particular constituency.
	 * 
	 * @param tehsilIds
	 * @param candidateDetailsType
	 * @param rank
	 * @param partyId
	 * @param electionType
	 * @param electionYear
	 * @return
	 */
	public List getMandalLevelElectionCandidateDetailsForAConstituency(String tehsilIds,String candidateDetailsType,Long rank,Long partyId,String electionType,String electionYear){
		try{
				if(log.isDebugEnabled()){
					log.debug("Entered in to getMandalLevelElectionCandidateDetailsForAConstituency() method...");
				}		
				Object[] parm = {electionType,electionYear}; 
				StringBuilder sb = new StringBuilder();		
				sb.append(" select model.party.partyFlag, model.constituencyElection.election.electionYear,");
				sb.append(" upper(model.candidate.lastname),upper(model.constituencyElection.constituency.tehsil.tehsilName),");
				sb.append(" model.constituencyElection.constituency.electionScope.electionType.electionType," );
				sb.append(" model.constituencyElection.constituency.tehsil.tehsilId," );
				sb.append(" model.candidateResult.votesEarned," );
				sb.append(" model.constituencyElection.constituencyElectionResult.validVotes,model.candidateResult.rank,model.constituencyElection.constituency.constituencyId," );
				sb.append(" model.candidateResult.votesPercengate,model.party.longName,model.party.partyId," );
				sb.append(" model.constituencyElection.reservationZone,model.constituencyElection.constituency.name");
				sb.append(" from Nomination model where model.constituencyElection.constituency.tehsil.tehsilId  in (  "  + tehsilIds );
				sb.append(" ) and model.constituencyElection.constituency.electionScope.electionType.electionType =  ?" );
				sb.append(" and model.constituencyElection.election.electionYear =  ?" );
				if(candidateDetailsType.equalsIgnoreCase("winners")){
					sb.append(" and model.candidateResult.rank = ").append(rank);
				}else if(candidateDetailsType.equalsIgnoreCase("partyWise")){	
					if(partyId!=0l){
						sb.append(" and model.party.partyId = ").append(partyId);
					}else{}
					
				}else if(candidateDetailsType.equalsIgnoreCase("allCandidates")){
					if(rank!=0l){
						sb.append(" and model.candidateResult.rank = ").append(rank);
					}else{}
				}
				
				if(log.isDebugEnabled()){
					log.debug("Making nominationDAO.getTehsilLevelElectionDetailsForAGivenConstituency().. Call");
				}		
				List result = nominationDAO.getTehsilLevelElectionDetailsForAGivenConstituency(sb.toString(),parm);
				return result;
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception occured in retriving the data...");
			return null;
		}
	}
	
	/**
	 * This method  calculates all the party details and return the information back to the calling method.
	 * @param tehsilIds
	 * @param electionYear
	 * @param electionType
	 * @return
	 */
	public List<TeshilPartyInfoVO> getTehsilPartyInfoForAConstituency(StringBuilder tehsilIds,String electionYear,String electionType,Long constituencyId){
	
		try{
			log.debug("Entered in to getTehsilPartyInfoForAConstituency() method..");
			BigDecimal percentage = new BigDecimal(0.0);
			List<TeshilPartyInfoVO> teshilPartyInfoVO = new ArrayList<TeshilPartyInfoVO>(0);
			Float totalVotes=null;
			String totalVotersInConstituency=null;
			Long winningCandidateRank=1l;
			Map<String,Long> winningSeats =  new HashMap<String,Long>(0);
			log.debug("Making nominationDAO.findSeatsWonByAPartyInMandalForAnElectionYear() DAO call..");
			List seatWon = nominationDAO.findSeatsWonByAPartyInMandalForAnElectionYear(tehsilIds.substring(1),electionYear,electionType,winningCandidateRank);
			
			Long totalSeats = 0l;
			for(int i=0;i<seatWon.size();i++){
				Object[] parms = (Object[])seatWon.get(i);
				winningSeats.put(parms[0].toString(), Long.parseLong(parms[1].toString()));
				totalSeats+=Long.parseLong(parms[1].toString());
			}
						
			log.debug("Making constituencyElectionDAO.getTotalValidVotesForATehsilForAParticularElectionYear() DAO call..");
			List totalValidVotes = constituencyElectionDAO.getTotalValidVotesForATehsilForAParticularElectionYear(electionType,tehsilIds.substring(1),electionYear);
			if(totalValidVotes.get(0)!=null){
				totalVotes = Float.parseFloat(totalValidVotes.get(0).toString());
			}else{
				totalVotes = 1.0f;
			}
			
			log.debug("Making constituencyElectionDAO.getTotalVotersForATehsilForAParticularElectionYear() DAO call..");
			List totalVoters = constituencyElectionDAO.getTotalVotersForATehsilForAParticularElectionYear(electionType,tehsilIds.substring(1),electionYear);
			if(totalVoters.get(0)!=null){
				totalVotersInConstituency = new Long(((Double)totalVoters.get(0)).longValue()).toString();
			}else{
				totalVotersInConstituency = "--";
			}
			
			
			log.debug("Making nominationDAO.getPartysInfoForATehsilForAParticularElectionYear() DAO call..");
			List result = nominationDAO.getPartysInfoForATehsilForAParticularElectionYear(electionType,tehsilIds.substring(1),electionYear);
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				TeshilPartyInfoVO teshilPartyInfoVo = new TeshilPartyInfoVO();
					teshilPartyInfoVo.setPartyName(parms[0].toString());
					teshilPartyInfoVo.setPartyId(Long.parseLong(parms[4].toString()));
					teshilPartyInfoVo.setParticipatedSeats(Long.parseLong(parms[1].toString()));
					percentage= new BigDecimal((Float.parseFloat(parms[2].toString())/totalVotes)*100).setScale(2,BigDecimal.ROUND_HALF_UP);
					teshilPartyInfoVo.setPercentageOfVotesWonByParty(Float.parseFloat(percentage.toString()));
					teshilPartyInfoVo.setTotalSeats(totalSeats);
					teshilPartyInfoVo.setTotalVotersInConstituency(totalVotersInConstituency);
					teshilPartyInfoVo.setTotalPolledVotes(totalVotes);					 	
					if(winningSeats.get(parms[0].toString()) != null){
						teshilPartyInfoVo.setSeatsWonByParty(Long.parseLong(winningSeats.get(parms[0].toString()).toString()));
					}else{
						teshilPartyInfoVo.setSeatsWonByParty(0L);
					}					
					teshilPartyInfoVo.setChartName("PartyTrendsInConstituency_"+constituencyId+"forElectionType"+electionType+"andElectionYear"+electionYear+"_piechart"+".png");
					teshilPartyInfoVO.add(teshilPartyInfoVo);
			}		
			return teshilPartyInfoVO;
		}catch(Exception e){
			log.error("Exception raised please check the log for details"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTransferVO getPreviousAndPresentElectionYearsGraphsForAConstituency(Long constituencyId){
		DataTransferVO charts = new DataTransferVO();
  		ConstituencyRevenueVillagesVO presentChart = getConstituencyElecResults(constituencyId, IConstants.PRESENT_ELECTION_YEAR, false);
  		List mandalwiseValidVotes = boothResultDAO.getMandalwiseValidVotesForAMappedConstituency(constituencyId, 
  				IConstants.PREVIOUS_ELECTION_YEAR, IConstants.ASSEMBLY_ELECTION_TYPE);
  		List mandalwisePartiesResults = candidateBoothResultDAO.getAllPartiesResultsByMandalsMappedConstituency(constituencyId, 
  				IConstants.PREVIOUS_ELECTION_YEAR, IConstants.ASSEMBLY_ELECTION_TYPE);
  		Map<Long, Long> mandalIdAndVVotesMap = new HashMap<Long, Long>();
  		for(Object[] values:(List<Object[]>)mandalwiseValidVotes)
  			mandalIdAndVVotesMap.put((Long)values[0], (Long)values[2]);
  		
  		List<PartyResultVO> partiesMandalResults = new ArrayList<PartyResultVO>();
  		PartyResultVO partyResultVO = null;
  		Long validVotes = 0l;
  		Long votesEarned = 0l;
  		try {
  			for(Object[] values:(List<Object[]>)mandalwisePartiesResults){
  	  			partyResultVO = new PartyResultVO();
  	  			partyResultVO.setTehsilName(values[1].toString());
  	  			partyResultVO.setPartyName(values[3].toString());
  	  			validVotes = mandalIdAndVVotesMap.get((Long)values[0]);
  	  			votesEarned = (Long)values[5];
  	  			partyResultVO.setVotesPercent(new BigDecimal(votesEarned*100.0/validVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
  	  			partiesMandalResults.add(partyResultVO);
  	  		}
		} catch (Exception e) {
			charts.setExceptionEncountered(e);
		}
  		
  		charts.setPresentYearChart(presentChart);
  		charts.setPreviousYearChart(partiesMandalResults);
  		return charts;
  	}
		
}