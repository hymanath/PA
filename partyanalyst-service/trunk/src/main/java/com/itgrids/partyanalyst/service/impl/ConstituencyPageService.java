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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.ITownshipElectionPartyResultDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.MandalTownshipWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TownshipBoothDetailsVO;
import com.itgrids.partyanalyst.dto.TownshipPartyResultsVO;
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
import com.itgrids.partyanalyst.model.TownshipElectionPartyResult;
import com.itgrids.partyanalyst.model.VillageBoothElection;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private HamletAndBoothVO hamletAndBoothVO;
	private TransactionTemplate transactionTemplate;
	private ITownshipElectionPartyResultDAO townshipElectionPartyResultDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO; 
	
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

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
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

	public void setTownshipElectionPartyResultDAO(
			ITownshipElectionPartyResultDAO townshipElectionPartyResultDAO) {
		this.townshipElectionPartyResultDAO = townshipElectionPartyResultDAO;
	}

	
	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
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
		List<LocationWiseBoothDetailsVO> boothDataFromDB = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO locationWiseBoothDetailsVO = null;
		Set<SelectOptionVO> boothNos = null;
		String townshipName = "";
		Long townshipId = null;
		Long totalVoters = new Long(0);
		Long validVotes = 0l;
		
		try{
			
			List mandalWiseInfo = candidateBoothResultDAO.findMandalWisePartiesResultsForElection(tehsilId, electionId);
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
			
			mandalWiseResult.setMandalWisePartyVotes(partyResults);
			
			List boothDetails = boothConstituencyElectionVoterDAO.findTownshipWiseBoothDetailsForTehsil(tehsilId, electionId);
			if(boothDetails==null || boothDetails.size()==0){
				boothDetails = villageBoothElectionDAO.findTownshipWiseBoothDetailsForTehsil(tehsilId);
			}
			for(int i=0; i<boothDetails.size(); i++){
				locationWiseBoothDetailsVO = new LocationWiseBoothDetailsVO();
				Object[] values = (Object[])boothDetails.get(i);
				
				if((townshipName).equals(values[1])){
					if(boothNos.add(new SelectOptionVO((Long)values[2], values[3].toString()))){
						totalVoters = totalVoters + (Long)values[4];
						validVotes = validVotes + (Long)values[5];
					}			
				}else{
					locationWiseBoothDetailsVO.setLocationId(townshipId);
					locationWiseBoothDetailsVO.setLocationName(townshipName);
					locationWiseBoothDetailsVO.setBooths(boothNos);
					locationWiseBoothDetailsVO.setPopulation(totalVoters);
					locationWiseBoothDetailsVO.setHamletsOfTownship(createSelectOptionVoForRawList(hamletDAO.findHamletNamesByTownshipId(townshipId)));
					boothDataFromDB.add(locationWiseBoothDetailsVO);
					
					boothNos = new HashSet<SelectOptionVO>(0);
					townshipName = (String)values[1];
					boothNos.add(new SelectOptionVO((Long)values[2], values[3].toString()));
					totalVoters = (Long)values[4];
					townshipId = (Long)values[0];
				}
				
				if(i == boothDetails.size()-1){
					locationWiseBoothDetailsVO.setLocationName(townshipName);
					locationWiseBoothDetailsVO.setBooths(boothNos);
					locationWiseBoothDetailsVO.setPopulation(totalVoters);
					locationWiseBoothDetailsVO.setHamletsOfTownship(createSelectOptionVoForRawList(hamletDAO.findHamletNamesByTownshipId(townshipId)));
					boothDataFromDB.add(locationWiseBoothDetailsVO);
				}
					
			}
			boothDataFromDB.remove(0);
			
		}catch(Exception e){
			mandalWiseResult.setExceptionEncountered(e);
			if(log.isDebugEnabled()){
				log.debug("Exception Occured ::", e);
			}
			e.printStackTrace();
		}		
		mandalWiseResult.setRevenueVillagesInfo(boothDataFromDB);
		return mandalWiseResult;
	}
	
	public List<SelectOptionVO> createSelectOptionVoForRawList(List objects){
		List<SelectOptionVO> namesAndIds = new ArrayList<SelectOptionVO>();
		for(int i=0; i<objects.size(); i++){
			Object[] values = (Object[])objects.get(i);
			namesAndIds.add(new SelectOptionVO((Long)values[0], values[1].toString()));
		}
		return namesAndIds;
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
						villageBoothElection = villageBoothElectionDAO.save(new VillageBoothElection(boothConstituencyElection, township));
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
	
	public MandalTownshipWiseBoothDetailsVO getTownshipVotesByTehsil(Long electionID, Long tehsilID, Long partyID){
		//0-townshipID, 1-townshipName, 2-totalVoters, 3-validVoters, 4-boothID, 5-partNo, 6-hamletId, 7-hamletName	
		MandalTownshipWiseBoothDetailsVO mandalTownshipWiseBoothDetailsVO = new MandalTownshipWiseBoothDetailsVO();
		List<TownshipPartyResultsVO> userPartyVotesTownshipWise = new ArrayList<TownshipPartyResultsVO>();
		List<TownshipPartyResultsVO> allPartyWiseVotesForMandal = new ArrayList<TownshipPartyResultsVO>();
		try{
			List rawData = boothConstituencyElectionVoterDAO.getTownshipVotesByTehsil(electionID, tehsilID);
			
			if(rawData==null || rawData.size()==0){
				
			}
			Map<String, TownshipBoothDetailsVO> townshipInfo = getVotersRawDataForAllTownship(rawData);
			Set<Entry<String, TownshipBoothDetailsVO>> set = townshipInfo.entrySet();
			Iterator<Entry<String, TownshipBoothDetailsVO>> iterator = set.iterator();
			Long mandalTotalVoters = 0L;
			Long mandalValidVoters = 0L;
			List<TownshipBoothDetailsVO> townshipBoothDetailsVOList = new ArrayList<TownshipBoothDetailsVO>();
			Map<Long, TownshipPartyResultsVO> partyResultsForMandal = new HashMap<Long, TownshipPartyResultsVO>();
			while(iterator.hasNext()){
				List<TownshipPartyResultsVO> townshipPartyResultsVOs = new ArrayList<TownshipPartyResultsVO>();
				Entry<String, TownshipBoothDetailsVO> entry = iterator.next();
				TownshipBoothDetailsVO value = entry.getValue();
				mandalTotalVoters = mandalTotalVoters + value.getTotalVoters();
				mandalValidVoters = mandalValidVoters + value.getValidVoters();
				List<TownshipElectionPartyResult> townshipElectionPartyResults = townshipElectionPartyResultDAO.findByTownshipIDElectionID(value.getTownshipID(), electionID);
				
				if(townshipElectionPartyResults==null ||townshipElectionPartyResults.size()==0){
					townshipElectionPartyResults = saveTownshipElectionPartyResult(electionID,value.getTownshipID());
				}
				if(townshipElectionPartyResults!=null && townshipElectionPartyResults.size()!=0){
					townshipPartyResultsVOs = convertModel2TownshipBoothDetailsVO(townshipElectionPartyResults,value.getTownshipID(),value.getTownshipName());
				}
				
				for(TownshipPartyResultsVO townshipPartyResultsVO : townshipPartyResultsVOs){
					Long party = townshipPartyResultsVO.getPartyID();
					if(partyID!=null && partyID.equals(party)){
						userPartyVotesTownshipWise.add(townshipPartyResultsVO);
					}
					TownshipPartyResultsVO data = partyResultsForMandal.get(party);
					if(data == null){
						data = townshipPartyResultsVO;
					}else{
						data.setVotesEarned(data.getVotesEarned() + townshipPartyResultsVO.getVotesEarned());
					}
					partyResultsForMandal.put(party,data);
				}
				townshipBoothDetailsVOList.add(value);
			}
			//allPartyVotesForMandal
			Set<Entry<Long, TownshipPartyResultsVO>> partySet = partyResultsForMandal.entrySet();
			Iterator<Entry<Long, TownshipPartyResultsVO>> iter = partySet.iterator();
			
			while(iter.hasNext()){
				Entry<Long, TownshipPartyResultsVO> entry = iter.next();
				TownshipPartyResultsVO voObj = entry.getValue();
				String percentage = new BigDecimal((voObj.getVotesEarned()*100)/mandalValidVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
				voObj.setPartyVotesInfo(voObj.getVotesEarned() + " (" + percentage + ")");
				allPartyWiseVotesForMandal.add(voObj);
			}
			if(allPartyWiseVotesForMandal!=null && allPartyWiseVotesForMandal.size()>0){
				TownshipPartyResultsVO townshipPartyResultsVO = allPartyWiseVotesForMandal.get(0);
				mandalTownshipWiseBoothDetailsVO.setConstituencyID(townshipPartyResultsVO.getCandidateID());
				mandalTownshipWiseBoothDetailsVO.setConstituencyName(townshipPartyResultsVO.getCandidateName());
			}
			mandalTownshipWiseBoothDetailsVO.setTownshipBoothDetailsVOs(townshipBoothDetailsVOList);
			mandalTownshipWiseBoothDetailsVO.setMandalTotalVoters(mandalTotalVoters);
			mandalTownshipWiseBoothDetailsVO.setMandalValidVoters(mandalValidVoters);
			mandalTownshipWiseBoothDetailsVO.setAllPartyWiseVotesForMandal(allPartyWiseVotesForMandal);
			mandalTownshipWiseBoothDetailsVO.setUserPartyVotesTownshipWise (userPartyVotesTownshipWise);
		}catch(Exception e){
			mandalTownshipWiseBoothDetailsVO.setExceptionEncountered(e);
		}
		
		return mandalTownshipWiseBoothDetailsVO;
	}
	
	private List<TownshipPartyResultsVO> convertModel2TownshipBoothDetailsVO(List<TownshipElectionPartyResult> townshipElectionPartyResultList,
			Long townshipID, String townshipName){
		List<TownshipPartyResultsVO> townshipPartyResultsVOs = new ArrayList<TownshipPartyResultsVO>();
		//Long allPartiesEarnedVotes = 0L;
		for(TownshipElectionPartyResult townshipElectionPartyResult: townshipElectionPartyResultList){
			Nomination nomination = townshipElectionPartyResult.getNomination();
			Township township = townshipElectionPartyResult.getTownship();
			Long votesEarned = townshipElectionPartyResult.getVotesEarned();
			if(votesEarned==null)
				votesEarned = 0L;
			//allPartiesEarnedVotes = allPartiesEarnedVotes + votesEarned;
			TownshipPartyResultsVO townshipPartyResultsVO = new TownshipPartyResultsVO();
			townshipPartyResultsVO.setCandidateID(nomination.getCandidate().getCandidateId());
			townshipPartyResultsVO.setCandidateName(nomination.getCandidate().getLastname());
			Constituency constituency = nomination.getConstituencyElection().getConstituency();
			

			townshipPartyResultsVO.setConstituencyID(constituency.getConstituencyId());
			townshipPartyResultsVO.setConstituencyName(constituency.getName());
			townshipPartyResultsVO.setRank(nomination.getCandidateResult().getRank());
			townshipPartyResultsVO.setPartyID(nomination.getParty().getPartyId());
			townshipPartyResultsVO.setPartyName(nomination.getParty().getShortName());
			//townshipPartyResultsVO.setPartyVotesInfo(votesEarned.toString());
			townshipPartyResultsVO.setVotesEarned(votesEarned);
			townshipPartyResultsVO.setTownshipID(townshipID);
			townshipPartyResultsVO.setTownshipName(townshipName);
			townshipPartyResultsVOs.add(townshipPartyResultsVO);
			
		}
		/*for(TownshipPartyResultsVO townshipPartyResultsVO : townshipPartyResultsVOs){
			Long votesEarned = new Long(townshipPartyResultsVO.getPartyVotesInfo());
			townshipPartyResultsVO.setPartyVotesInfo(new BigDecimal(((votesEarned*100)/allPartiesEarnedVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}*/
		return townshipPartyResultsVOs;
	}
	private Map<String, TownshipBoothDetailsVO> getVotersRawDataForAllTownship(List rawData){
		Map<String, TownshipBoothDetailsVO> townshipInfo = new LinkedHashMap<String, TownshipBoothDetailsVO>();
		int size = rawData.size();
		for(int i=0; i<size; i++){
			//0-townshipID, 1-townshipName, 2-totalVoters, 3-validVoters, 
			//4-boothID, 5-partNo, 6-hamletId, 7-hamletName
			Object[] data = (Object[]) rawData.get(i);
			Long townshipID = (Long) data[0];
			String townshipName = data[1].toString();
			Long totalVoters = (Long) data[2];
			Long validVoters = (Long) data[3];
			Long boothID = (Long) data[4];
			String partNo = data[5].toString();
			Long hamletId = (Long) data[6];
			String hamletName = data[7].toString();
			TownshipBoothDetailsVO townshipBoothDetailsVO = townshipInfo.get(townshipName);
			if(townshipBoothDetailsVO==null){
				townshipBoothDetailsVO = new TownshipBoothDetailsVO();
				townshipBoothDetailsVO.setTownshipID(townshipID);
				townshipBoothDetailsVO.setTownshipName(townshipName);
				townshipBoothDetailsVO.setTotalVoters(totalVoters);
				townshipBoothDetailsVO.setValidVoters(0L);
				townshipBoothDetailsVO.setBooths(new HashSet<SelectOptionVO>());
				townshipBoothDetailsVO.setHamlets(new HashSet<SelectOptionVO>());
			}
			SelectOptionVO booth = new SelectOptionVO(boothID,partNo);
			SelectOptionVO hamlet = new SelectOptionVO(hamletId,hamletName);
			Set<SelectOptionVO> booths = townshipBoothDetailsVO.getBooths();
			Set<SelectOptionVO> hamlets = townshipBoothDetailsVO.getHamlets();
			booths.add(booth);
			hamlets.add(hamlet);
			validVoters = validVoters + townshipBoothDetailsVO.getValidVoters();
			totalVoters = totalVoters + townshipBoothDetailsVO.getTotalVoters();
			townshipBoothDetailsVO.setValidVoters(validVoters);
			townshipBoothDetailsVO.setValidVoters(totalVoters);
			townshipInfo.put(townshipName, townshipBoothDetailsVO);
		}
		return townshipInfo;
	}
	private Long tempElectionID;
	private Long tempTownshipID;
	@SuppressWarnings("unchecked")
	public List<TownshipElectionPartyResult> saveTownshipElectionPartyResult(Long electionID, Long townshipID) throws Exception{
		tempElectionID = electionID;
		tempTownshipID=townshipID;
		List<TownshipElectionPartyResult> townshipElectionPartyResultList = 
			(List<TownshipElectionPartyResult>) transactionTemplate.execute(
				new TransactionCallback() {
					public Object doInTransaction(TransactionStatus txStatus) {
						List<TownshipElectionPartyResult> townshipElectionPartyResultList = new ArrayList<TownshipElectionPartyResult>();
						try{
							List list = candidateBoothResultDAO.getElectionPartyResultsForTownship(tempElectionID,tempTownshipID);//21815L);
							Township township = townshipDAO.get(tempTownshipID);
							int size = list.size();
							for(int i=0; i<size;i++){
								Object[] obj = (Object[])list.get(i);
								Nomination nomination = (Nomination) obj[0];
								TownshipElectionPartyResult townshipElectionPartyResult = new TownshipElectionPartyResult();
								townshipElectionPartyResult.setNomination(nomination);
								townshipElectionPartyResult.setTownship(township);
								townshipElectionPartyResult.setVotesEarned(new Long(obj[1].toString()));
								townshipElectionPartyResult=townshipElectionPartyResultDAO.save(townshipElectionPartyResult);
								townshipElectionPartyResultList.add(townshipElectionPartyResult);
							}
						}catch(Exception e){
							txStatus.setRollbackOnly();
						}
						
						return townshipElectionPartyResultList;
					}
				}
		);
		tempElectionID = null;
		tempTownshipID=null;
		
		return townshipElectionPartyResultList;
	}
	
	
	public List<VotersWithDelimitationInfoVO> getVotersInfoInMandalsForConstituency(Long constituencyId)
	{
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
				votersInfo.setMandalId( vObj[0].toString());
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
		
		return votersWithDelimitationInfoVOList;
		
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
			candidateInfoList.add(candidateInfo1);
		}
		
		return candidateInfoList;
	}
	
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId,String electionType)
	{
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO ();
		
		
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
	public List<TownshipPartyResultsVO> getTownshipPartyResults(Long townshipID, Long electionID){
		String townshipName = new String();
		List<TownshipElectionPartyResult> townshipElectionPartyResults = townshipElectionPartyResultDAO.findByTownshipIDElectionID(townshipID, electionID);
		if(townshipElectionPartyResults!=null && townshipElectionPartyResults.size()>0){
			TownshipElectionPartyResult model = townshipElectionPartyResults.get(0);
			townshipName = model.getTownship().getTownshipName();
		}
		List<TownshipPartyResultsVO> results =convertModel2TownshipBoothDetailsVO(townshipElectionPartyResults, townshipID, townshipName);
		return results;
	}

}
