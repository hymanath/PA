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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultObjectsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
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
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MandalAndRevenueVillagesInfoVO;
import com.itgrids.partyanalyst.dto.PanchayathOrTownInfoVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private HamletAndBoothVO hamletAndBoothVO;
	private TransactionTemplate transactionTemplate;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;	
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO; 
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICensusDAO censusDAO;
	 
	
	public ICensusDAO getCensusDAO() {
		return censusDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
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

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
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
		try{		
			List panchayathBoothData = hamletBoothElectionDAO.findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(tehsilId, electionId);
			groupPanchayathOrTownWiseRawBoothData(panchayathBoothData, true, boothDataFromDB);
			List townsInTehsil = censusDAO.findAllRevenueVillagesInfoInMandal(IConstants.CENSUS_YEAR, tehsilId, "'"+IConstants.CENSUS_WARD_LEVEL+"'");
			StringBuilder townshipIds = new StringBuilder();
			for(Object[] towns:(List<Object[]>)townsInTehsil)
				townshipIds.append(IConstants.COMMA).append(towns[0]);
			if(townsInTehsil.size() != 0){
				List townsBoothData = villageBoothElectionDAO.findTownsBoothIdsInTehsilForElection(townshipIds.toString().substring(1), electionId);
				groupPanchayathOrTownWiseRawBoothData(townsBoothData, false, boothDataFromDB);
			}
			
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
	
	private void groupPanchayathOrTownWiseRawBoothData(List rawData, boolean isVillage,
			List<LocationWiseBoothDetailsVO> boothDataFromDB){
		Map<LocationWiseBoothDetailsVO, List<Object[]>> panchayathsMap = 
			new LinkedHashMap<LocationWiseBoothDetailsVO, List<Object[]>>();
		List<Object[]> boothsAndHamlets = null;
		LocationWiseBoothDetailsVO locationWiseBoothDetailsVO = null;
		Set<SelectOptionVO> boothNos = null;
		Set<SelectOptionVO> hamlets = null;
		Long totalVoters = null;
		Long totalVotesPolled = null;
		String townOrVillageName = "";
		
		for(Object[] values:(List<Object[]>)rawData){
			locationWiseBoothDetailsVO = new LocationWiseBoothDetailsVO();
			
			if(isVillage)
				townOrVillageName = values[0].toString()+" (P)";
			else
				townOrVillageName = values[0].toString()+" (M/C)";
			
			locationWiseBoothDetailsVO.setLocationName(townOrVillageName);
			boothsAndHamlets = panchayathsMap.get(locationWiseBoothDetailsVO);
			if(boothsAndHamlets == null)
				boothsAndHamlets = new ArrayList<Object[]>();
			boothsAndHamlets.add(values);
			panchayathsMap.put(locationWiseBoothDetailsVO, boothsAndHamlets);
		}
		
		for(Map.Entry<LocationWiseBoothDetailsVO, List<Object[]>> entry:panchayathsMap.entrySet()){
			locationWiseBoothDetailsVO = entry.getKey();
			boothsAndHamlets = entry.getValue();
			boothNos = new LinkedHashSet<SelectOptionVO>();
			hamlets = new LinkedHashSet<SelectOptionVO>();
			totalVoters = 0l;
			totalVotesPolled = 0l;
			for(Object[] booths:boothsAndHamlets){
				if(boothNos.add(new SelectOptionVO((Long)booths[3],booths[4].toString()))){
					totalVoters += (Long)booths[1];
					totalVotesPolled += (Long)booths[2];
				}
				if(isVillage)
					hamlets.add(new SelectOptionVO((Long)booths[5],booths[6].toString()));
			}
			locationWiseBoothDetailsVO.setBooths(boothNos);
			locationWiseBoothDetailsVO.setVotesPolled(totalVotesPolled);
			locationWiseBoothDetailsVO.setPopulation(totalVoters);
			if(isVillage)
				locationWiseBoothDetailsVO.setHamletsOfTownship(hamlets);
			
			boothDataFromDB.add(locationWiseBoothDetailsVO);
			
		}
	}
	
	public List<ConstituencyRevenueVillagesVO> getEelctionResultsInPanchayathsAndTownsInMandal(Long tehsilId, Long electionId){
		List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVOs = 
														new ArrayList<ConstituencyRevenueVillagesVO>();
		Map<ConstituencyRevenueVillagesVO, List<PanchayathOrTownInfoVO>> constituencyWithRevenueVillagesMap = 
			new LinkedHashMap<ConstituencyRevenueVillagesVO, List<PanchayathOrTownInfoVO>>();
		Map<LocationWiseBoothDetailsVO, List<Object[]>> panchayathResultsMap = null;
		LocationWiseBoothDetailsVO locationWiseBoothDetailsVO = null;
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = null;
		List<PanchayathOrTownInfoVO> panchayaths = null;
		PanchayathOrTownInfoVO panchayathOrTownInfoVO = null;
		List<Object[]> panchayathResults = null;
		
		//return Vo Declarations
		
		List<RevenueVillageElectionVO> revenueVillageElectionVOs = null;
		RevenueVillageElectionVO revenueVillageElectionVO = null;
		List<CandidatePartyInfoVO> candidateNamePartyAndStatus = null;
		CandidatePartyInfoVO candidatePartyInfoVO = null;
		List<PartyElectionResultVO> partyElectionResultVOs = null;
		PartyElectionResultVO partyElectionResultVO = null;
		
		List boothConstiIds = hamletBoothElectionDAO.findPanchayathBoothIdsInTehsilForElection(tehsilId, electionId);		
		Map<String, String> panchayathsAndBoothConstiIds = new HashMap<String, String>();
		String panchayath = "";
		for (int i = 0; i < boothConstiIds.size(); i++) 
		{
			Object[] obj = (Object[]) boothConstiIds.get(i);
			panchayath = obj[0].toString();
			String value = panchayathsAndBoothConstiIds.get(panchayath);
			StringBuilder ids = new StringBuilder();
			if(value==null){
				ids .append(obj[1].toString());
			}else{
				ids.append(value).append(IConstants.COMMA).append(obj[1].toString());
			}
			panchayathsAndBoothConstiIds.put(panchayath, ids.toString());
		}
		
		for(Map.Entry<String, String> entry:panchayathsAndBoothConstiIds.entrySet()){
			List list = candidateBoothResultDAO.getcandidatesResultsByBoothConstiIds(entry.getValue());
			for(Object[] rawData:(List<Object[]>)list){
				constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
				constituencyRevenueVillagesVO.setConstituencyId((Long)rawData[0]);
				constituencyRevenueVillagesVO.setConstituencyName(rawData[1].toString());
				panchayaths = constituencyWithRevenueVillagesMap.get(constituencyRevenueVillagesVO);
				if(panchayaths == null)
					panchayaths = new ArrayList<PanchayathOrTownInfoVO>();
				panchayathOrTownInfoVO = new PanchayathOrTownInfoVO();
				panchayathOrTownInfoVO.setLocationName(entry.getKey()+" (P)");
				panchayathOrTownInfoVO.setRawData(rawData);
				panchayaths.add(panchayathOrTownInfoVO);
				constituencyWithRevenueVillagesMap.put(constituencyRevenueVillagesVO, panchayaths);
			}
		}
		
		List townsInTehsil = censusDAO.findAllRevenueVillagesInfoInMandal(IConstants.CENSUS_YEAR, tehsilId, "'"+IConstants.CENSUS_WARD_LEVEL+"'");
		StringBuilder townshipIds = new StringBuilder();
		for(Object[] towns:(List<Object[]>)townsInTehsil)
			townshipIds.append(IConstants.COMMA).append(towns[0]);
		if(townsInTehsil.size() != 0){
			List towns = villageBoothElectionDAO.findTownshipAndBoothConstiElecIds(townshipIds.toString().substring(1), electionId);
			panchayathsAndBoothConstiIds = new LinkedHashMap<String, String>();
			for (int i = 0; i < boothConstiIds.size(); i++) 
			{
				Object[] obj = (Object[]) boothConstiIds.get(i);
				panchayath = obj[0].toString();
				String value = panchayathsAndBoothConstiIds.get(panchayath);
				StringBuilder ids = new StringBuilder();
				if(value==null){
					ids .append(obj[1].toString());
				}else{
					ids.append(value).append(IConstants.COMMA).append(obj[1].toString());
				}
				panchayathsAndBoothConstiIds.put(panchayath, ids.toString());
			}
			
			for(Map.Entry<String, String> entry:panchayathsAndBoothConstiIds.entrySet()){
				List list = candidateBoothResultDAO.getcandidatesResultsByBoothConstiIds(entry.getValue());
				for(Object[] rawData:(List<Object[]>)list){
					constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
					constituencyRevenueVillagesVO.setConstituencyId((Long)rawData[0]);
					constituencyRevenueVillagesVO.setConstituencyName(rawData[1].toString());
					panchayaths = constituencyWithRevenueVillagesMap.get(constituencyRevenueVillagesVO);
					if(panchayaths == null)
						panchayaths = new ArrayList<PanchayathOrTownInfoVO>();
					panchayathOrTownInfoVO = new PanchayathOrTownInfoVO();
					panchayathOrTownInfoVO.setLocationName(entry.getKey()+" (M/C)");
					panchayathOrTownInfoVO.setRawData(rawData);
					panchayaths.add(panchayathOrTownInfoVO);
					constituencyWithRevenueVillagesMap.put(constituencyRevenueVillagesVO, panchayaths);
				}
			}
		}
		
		for(Map.Entry<ConstituencyRevenueVillagesVO, List<PanchayathOrTownInfoVO>> entry:
			constituencyWithRevenueVillagesMap.entrySet()){
			
			revenueVillageElectionVOs = new ArrayList<RevenueVillageElectionVO>();
			
			constituencyRevenueVillagesVO = entry.getKey();
			panchayaths = entry.getValue();
			candidateNamePartyAndStatus = new ArrayList<CandidatePartyInfoVO>();
			panchayathResultsMap = new LinkedHashMap<LocationWiseBoothDetailsVO, List<Object[]>>();
			for(PanchayathOrTownInfoVO panchayathObj:panchayaths){
				locationWiseBoothDetailsVO = new LocationWiseBoothDetailsVO();
				locationWiseBoothDetailsVO.setLocationName(panchayathObj.getLocationName());
				panchayathResults = panchayathResultsMap.get(locationWiseBoothDetailsVO);
				if(panchayathResults == null)
					panchayathResults = new ArrayList<Object[]>();
				panchayathResults.add(panchayathObj.getRawData());
				panchayathResultsMap.put(locationWiseBoothDetailsVO, panchayathResults);				
			}
			
			int i=0;
			for(Map.Entry<LocationWiseBoothDetailsVO, List<Object[]>> finalResults:panchayathResultsMap.entrySet()){
				locationWiseBoothDetailsVO = finalResults.getKey();
				
				revenueVillageElectionVO = new RevenueVillageElectionVO();
				revenueVillageElectionVO.setTownshipName(locationWiseBoothDetailsVO.getLocationName());
				partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
				
				for(Object[] finalRaw:finalResults.getValue()){
					if(i == 0){
						candidatePartyInfoVO = new CandidatePartyInfoVO();
						candidatePartyInfoVO.setCandidateId((Long)finalRaw[2]);
						candidatePartyInfoVO.setCandidateName(finalRaw[3].toString());
						candidatePartyInfoVO.setParty(finalRaw[5].toString());
						candidatePartyInfoVO.setPartyId((Long)finalRaw[4]);
						candidatePartyInfoVO.setRank((Long)finalRaw[8]);
						candidateNamePartyAndStatus.add(candidatePartyInfoVO);
					}
				}
				i++;
				
				for(Object[] finalRaw:finalResults.getValue()){
					partyElectionResultVO = new PartyElectionResultVO();
					partyElectionResultVO.setPartyId((Long)finalRaw[4]);
					partyElectionResultVO.setPartyName(finalRaw[5].toString());
					partyElectionResultVO.setVotesEarned((Long)finalRaw[6]);
					partyElectionResultVO.setVotesPercentage(new BigDecimal((((Long)finalRaw[6])*100.0)/((Long)finalRaw[7]))
					.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					partyElectionResultVOs.add(partyElectionResultVO);
				}		
				
				revenueVillageElectionVO.setPartyElectionResultVOs(partyElectionResultVOs);
				revenueVillageElectionVOs.add(revenueVillageElectionVO);
			}
			constituencyRevenueVillagesVO.setRevenueVillageElectionVO(revenueVillageElectionVOs);
			constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
			constituencyRevenueVillagesVOs.add(constituencyRevenueVillagesVO);
		}
		
		
		return constituencyRevenueVillagesVOs;
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
	public ConstituencyRevenueVillagesVO getConstituencyElecResults(Long constituencyId, String electionYear){
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = null;
		List<ConstituencyOrMandalWiseElectionVO> constituencyElectionResults = new ArrayList<ConstituencyOrMandalWiseElectionVO>();
		ConstituencyOrMandalWiseElectionVO assemblyConstiElecVO = null;
		List<PartyElectionResultVO> partyElectionResultVOs = null;
		PartyElectionResultVO partyElectionResultVO = null;
		List<CandidatePartyInfoVO> candidateNamePartyAndStatus = new ArrayList<CandidatePartyInfoVO>();
		CandidatePartyInfoVO candidatePartyInfoVO = null;
		
		Constituency constituency = constituencyDAO.get(constituencyId);
		if(constituency.getElectionScope().getElectionType().getElectionType().
				equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			constituencyRevenueVillagesVO = getMandalElectionInfoForAConstituency(constituencyId, electionYear);
			constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
			constituencyRevenueVillagesVO.setConstituencyName(constituency.getName());
			constituencyRevenueVillagesVO.setElectionType(constituency.getElectionScope().
					getElectionType().getElectionType());
			return constituencyRevenueVillagesVO;
		}
		if(constituency.getElectionScope().getElectionType().getElectionType().
				equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
			constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
			constituencyRevenueVillagesVO.setConstituencyName(constituency.getName());
			constituencyRevenueVillagesVO.setElectionType(constituency.getElectionScope().
					getElectionType().getElectionType());
			List<SelectOptionVO> assemblies = getAssembliesForParliament(constituencyId, new Long(electionYear));
			int i=0;
			for(SelectOptionVO assembly:assemblies){	
				List partiesResults = candidateBoothResultDAO.findAssemblyWiseParliamentResultsForParties(assembly.getId(), constituencyId, electionYear);
				assemblyConstiElecVO = new ConstituencyOrMandalWiseElectionVO();
				partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
				assemblyConstiElecVO.setLocationId(assembly.getId());
				assemblyConstiElecVO.setLocationName(assembly.getName());
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
		}
		constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
		constituencyRevenueVillagesVO.setConstituencyOrMandalWiseElectionVO(constituencyElectionResults);
		return constituencyRevenueVillagesVO;
	}
	
	private List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear){
		List<Constituency> constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituencies(parliamentId, electionYear);
		List<SelectOptionVO> constituencyVOs = new ArrayList<SelectOptionVO>();
		List boothConstituencyElections = null;
		Long constituencyId;
		for(Constituency constituency:constituencies){
			constituencyId = constituency.getConstituencyId();
			boothConstituencyElections = boothConstituencyElectionDAO.findByConstituencyIdAndElectionYear(constituencyId, parliamentId, electionYear.toString());
			if(boothConstituencyElections == null || boothConstituencyElections.get(0).toString().equalsIgnoreCase("0"))
				continue;
			constituencyVOs.add(new SelectOptionVO(constituencyId, constituency.getName().toUpperCase()));
		}
		return constituencyVOs;
	}
	
	/** 
	 *This method returns a VO containing all the information regarding the
	 * parliament candidate voting trends in the assembly constitutency.
	 */
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
		List list = candidateBoothResultDAO.getMandalsForAConstituencyForAGivenYear(constituencyId,electionYear);
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			tehsilIds.append(",").append(Long.parseLong(parms[0].toString()));
		}
		if(log.isDebugEnabled())
			log.debug("Calling candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalByPaliamentWise() method..");
		for(int j=0;j<parliamentConstituencies.size();j++){
			constituencyID = Long.parseLong(parliamentConstituencies.get(j).toString());
			List candidateResult = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalByPaliamentWise(constituencyID,tehsilIds.substring(1),electionYear);
			constituencyRevenueVillagesVO = setDataForVOForCorrespondingAssemblyOrParliament(candidateResult,constituencyID,parliamentConstituenciesNames.get(constituencyID));
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
	 * assembly candidate voting trends in the assembly constitutency.
	 */
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear){	
		try{
		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(constituencyId,electionYear);
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
		if(log.isDebugEnabled()){
			log.debug("Calling setDataForVOForCorrespondingAssemblyOrParliament()");
		}
		constituencyRevenueVillagesVO = setDataForVOForCorrespondingAssemblyOrParliament(list,null,null);
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
	public ConstituencyRevenueVillagesVO setDataForVOForCorrespondingAssemblyOrParliament(List list,Long constituencyId,String constituencyName){
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
				candidateNamePartyAndStatus = getCandidateAndPartyDetails(mainTehsilId,list);
				constituencyOrMandalWiseElectionVO.add(constituencyOrMandalWiseElectionVo);
			}
			if(constituencyId!=null && constituencyName!=null){
				constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
				constituencyRevenueVillagesVO.setConstituencyName(constituencyName);
			}else{}			
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

}