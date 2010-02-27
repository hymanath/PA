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

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.ITownshipElectionPartyResultDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalTownshipWiseBoothDetailsVO;
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
	
	public ResultWithExceptionVO getTownshipWiseBoothDetailsForTehsil(Long tehsilId){
		ResultStatus resultStatus = new ResultStatus();
		List<LocationWiseBoothDetailsVO> boothDataFromDB = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO locationWiseBoothDetailsVO = null;
		Set<String> hamlets = null;
		Set<String> boothNos = null;
		String townshipName = "";
		Long townshipId = null;
		Long totalVoters = new Long(0);
		
		try{
			List boothDetails = boothConstituencyElectionVoterDAO.findTownshipWiseBoothDetailsForTehsil(tehsilId);
			if(boothDetails==null || boothDetails.size()==0){
				boothDetails = villageBoothElectionDAO.findTownshipWiseBoothDetailsForTehsil(tehsilId);
				log.debug("Narender boothDetails.size():::"+boothDetails.size());
			}
			for(int i=0; i<boothDetails.size(); i++){
				locationWiseBoothDetailsVO = new LocationWiseBoothDetailsVO();
				Object[] values = (Object[])boothDetails.get(i);
				
				if((townshipName).equals(values[0])){
					if(boothNos.add((String)values[1]))
						totalVoters = totalVoters + (Long)values[2];
					hamlets.add((String)values[4]);
				}else{
					locationWiseBoothDetailsVO.setLocationId(townshipId);
					locationWiseBoothDetailsVO.setLocationName(townshipName);
					locationWiseBoothDetailsVO.setBooths(boothNos);
					locationWiseBoothDetailsVO.setPopulation(totalVoters);
					locationWiseBoothDetailsVO.setSubLocations(hamlets);
					locationWiseBoothDetailsVO.setHamletsOfTownship(hamletDAO.findHamletNamesByTownshipId(townshipId));
					boothDataFromDB.add(locationWiseBoothDetailsVO);
					
					hamlets = new HashSet<String>(0);
					boothNos = new HashSet<String>(0);
					townshipName = (String)values[0];
					boothNos.add((String)values[1]);
					totalVoters = (Long)values[2];
					hamlets.add((String)values[4]);
					townshipId = (Long)values[5];
				}
				
				if(i == boothDetails.size()-1){
					locationWiseBoothDetailsVO.setLocationName(townshipName);
					locationWiseBoothDetailsVO.setBooths(boothNos);
					locationWiseBoothDetailsVO.setPopulation(totalVoters);
					locationWiseBoothDetailsVO.setSubLocations(hamlets);
					locationWiseBoothDetailsVO.setHamletsOfTownship(hamletDAO.findHamletNamesByTownshipId(townshipId));
					boothDataFromDB.add(locationWiseBoothDetailsVO);
				}
					
			}
			boothDataFromDB.remove(0);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			if(log.isDebugEnabled()){
				log.debug("Exception Occured Due To ", e);
			}
		}		
		return new ResultWithExceptionVO(boothDataFromDB, resultStatus);
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
						villageBoothElection = villageBoothElectionDAO.save(new VillageBoothElection(boothConstituencyElection, null, township));
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
			townshipBoothDetailsVO.setValidVoters(validVoters);
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
		for (int i = 0; i < mandalsList.size(); i++) 
		{
			Object[] obj = (Object[]) mandalsList.get(i);			
			String year = obj[2].toString();
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
				
				votersInfoForMandalList.add(votersInfo);
			}
			votersWithDelimitationInfoVO.setVotersInfoForMandalVO(votersInfoForMandalList);
			votersWithDelimitationInfoVOList.add(votersWithDelimitationInfoVO);
		}
		
		return votersWithDelimitationInfoVOList;
		
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
