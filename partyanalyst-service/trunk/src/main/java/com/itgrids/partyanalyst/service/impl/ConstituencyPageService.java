/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyCensusDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;
import com.itgrids.partyanalyst.dao.IDelimitationVillageDAO;
import com.itgrids.partyanalyst.dao.IDelimitationWardDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dao.hibernate.ElectionDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateElectionVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidatePartyInfoVO;
import com.itgrids.partyanalyst.dto.CandidateWonVO;
import com.itgrids.partyanalyst.dto.CensusVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyNominationsVO;
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
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
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
import com.itgrids.partyanalyst.model.ConstituencyCensusDetails;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.VillageBoothElection;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.CandidatePartyInfoVOComparator;
import com.itgrids.partyanalyst.utils.ConstituencyOrMandalVOComparator;
import com.itgrids.partyanalyst.utils.ConstituencyOrMandalVOComparatorMandal;
import com.itgrids.partyanalyst.utils.ElectionDetailsVOComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyElectionResultComparator;
import com.itgrids.partyanalyst.utils.PartyResultVOComparatorByElectors;
import com.itgrids.partyanalyst.utils.SortByRankOnPartyElectionResultComparator;

public class ConstituencyPageService implements IConstituencyPageService {

	/*
	 * doc
	 * ConstituencyElectionResults
	 */
	private static final Logger log = Logger.getLogger(CrossVotingEstimationService.class);
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
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
	private IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO;
	private IStaticDataService staticDataService;		
	private IBoothResultDAO boothResultDAO; 
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IBoothDAO boothDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private ElectionDAO electionDAO;
	private ITehsilDAO tehsilDAO;
	private ICensusDAO censusDAO;
	private IDelimitationVillageDAO delimitationVillageDAO;
	private IDelimitationWardDAO delimitationWardDAO;
	private IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO;
	private ICandidateResultDAO candidateResultDAO;
	private List<SelectOptionVO> skippedMandals = new ArrayList<SelectOptionVO>(0);
	private IPanchayatHamletDAO panchayatHamletDAO;
	private IPanchayatDAO panchayatDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	
		
	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public List<SelectOptionVO> getSkippedMandals() {
		return skippedMandals;
	}

	public void setSkippedMandals(List<SelectOptionVO> skippedMandals) {
		this.skippedMandals = skippedMandals;
	}

	public IDelimitationVillageDAO getDelimitationVillageDAO() {
		return delimitationVillageDAO;
	}

	public void setDelimitationVillageDAO(
			IDelimitationVillageDAO delimitationVillageDAO) {
		this.delimitationVillageDAO = delimitationVillageDAO;
	}

	public IDelimitationWardDAO getDelimitationWardDAO() {
		return delimitationWardDAO;
	}

	public void setDelimitationWardDAO(IDelimitationWardDAO delimitationWardDAO) {
		this.delimitationWardDAO = delimitationWardDAO;
	}

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

	public ElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(ElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
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

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
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

	public ICensusDAO getCensusDAO() {
		return censusDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}

	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IConstituencyCensusDetailsDAO getConstituencyCensusDetailsDAO() {
		return constituencyCensusDetailsDAO;
	}

	public void setConstituencyCensusDetailsDAO(
			IConstituencyCensusDetailsDAO constituencyCensusDetailsDAO) {
		this.constituencyCensusDetailsDAO = constituencyCensusDetailsDAO;
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

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IDelimitationConstituencyTownDAO getDelimitationConstituencyTownDAO() {
		return delimitationConstituencyTownDAO;
	}

	public void setDelimitationConstituencyTownDAO(
			IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO) {
		this.delimitationConstituencyTownDAO = delimitationConstituencyTownDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults(Long constituencyId) {
		
		List<ConstituencyElectionResult> constituencyElectionResults = constituencyElectionResultDAO.findByConstituency(constituencyId);
		List<ConstituencyElectionResultsVO> constituencyElectionResultList = new ArrayList<ConstituencyElectionResultsVO>(0);
		ConstituencyElectionResultsVO constElecResultVO = null;
		Map<Long,SelectOptionVO> partiesList = new HashMap<Long,SelectOptionVO>();
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
				 if(partiesList.isEmpty() || !partiesList.containsKey(candidateWon.getPartyId())){
					 if(!candidateWon.getPartyShortName().equalsIgnoreCase("IND"))
					 partiesList.put(candidateWon.getPartyId(), new SelectOptionVO(candidateWon.getPartyId(),candidateWon.getPartyShortName()));
				 }
				 
				  for(Nomination nomination:nominationsList){
				    CandidateOppositionVO candidateOpposition = getCandidatesOpposition(nominationsList,rank);
				    rank++;
				    if(candidateOpposition!=null){
				    	candidateOppositionList.add(candidateOpposition);
				    	if(partiesList.isEmpty() || !partiesList.containsKey(candidateOpposition.getPartyId())){
				    		 if(!candidateOpposition.getPartyShortName().equalsIgnoreCase("IND"))
				    		 partiesList.put(candidateOpposition.getPartyId(), new SelectOptionVO(candidateOpposition.getPartyId(),candidateOpposition.getPartyShortName()));
				    	}
				    }
				  }
				  constElecResultVO.setCandidateResultsVO(candidateWon);
				  constElecResultVO.setCandidateOppositionList(candidateOppositionList);
				 
				 constituencyElectionResultList.add(constElecResultVO);
			 }
			 Collections.sort(constituencyElectionResultList,new ElectionDetailsVOComparator());
			 getPartyResultsOverviewForChart(partiesList,constituencyElectionResultList);
			 if(!partiesList.isEmpty()){
				 List<SelectOptionVO> partysListVO = new ArrayList<SelectOptionVO>();
				 Set<Long> partyIds = partiesList.keySet();
				 for(Long partyId:partyIds){
					 SelectOptionVO optionVO = partiesList.get(partyId);
					 partysListVO.add(optionVO);
				 }
				 constituencyElectionResultList.get(0).setPartiesList(partysListVO);
			 }
			 
			 
		 return constituencyElectionResultList;
		 }
		 return null;	
	}
	
	
	public void getPartyResultsOverviewForChart(Map<Long,SelectOptionVO> partiesList,List<ConstituencyElectionResultsVO> constituencyElectionResultList){
		
		log.debug("Inside getPartyResultsOverviewForChart Method ....");
		
		if(partiesList != null && !partiesList.isEmpty() && constituencyElectionResultList != null){
			
			Set<Long> mapKeys = partiesList.keySet();
			for(ConstituencyElectionResultsVO resultVO:constituencyElectionResultList){
				List<PartyResultsVO> partyResultsVO = new ArrayList<PartyResultsVO>();
				
				for(Long partyIds:mapKeys){
					Boolean flag = false;
					if(resultVO.getCandidateResultsVO() != null){
						if(resultVO.getCandidateResultsVO().getPartyId().equals(partyIds)){
							flag = true;
							PartyResultsVO partyRes = new PartyResultsVO();
							partyRes.setPartyId(partyIds);
							partyRes.setPartyName(resultVO.getCandidateResultsVO().getPartyShortName());
							partyRes.setPercentage(resultVO.getCandidateResultsVO().getVotesPercentage());
							partyRes.setVotesPercent(new BigDecimal(resultVO.getCandidateResultsVO().getVotesPercentage()));
							partyResultsVO.add(partyRes);
						}
					}
					if(flag == false){
						for(CandidateOppositionVO oppCand:resultVO.getCandidateOppositionList()){
							if(oppCand.getPartyId().equals(partyIds)){
								flag = true;
								PartyResultsVO partyRes = new PartyResultsVO();
								partyRes.setPartyId(partyIds);
								partyRes.setPartyName(oppCand.getPartyShortName());
								partyRes.setPercentage(oppCand.getVotesPercentage());
								partyRes.setVotesPercent(new BigDecimal(oppCand.getVotesPercentage()));
								partyResultsVO.add(partyRes);
							}
						}
					}
					if(flag == false){
						PartyResultsVO partyRes = new PartyResultsVO();
						partyRes.setPartyId(partyIds);
						SelectOptionVO partyMapValue = partiesList.get(partyIds);
						partyRes.setPartyName(partyMapValue.getName());
						partyRes.setPercentage("0");
						partyRes.setVotesPercent(new BigDecimal(0));
						partyResultsVO.add(partyRes);
					}
				}
				resultVO.setPartyResultsVO(partyResultsVO);
			}
		}
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
		Constituency constituency = constituencyDAO.get(constituencyId);
		String districtName ="";
		Long districtId = 0l;
		String electionType = "";
		Boolean hasAnalyzeData = false;
		List list = new ArrayList(0);
		Long electionYear=0l;
		if(constituency != null){
			
			if(constituency.getDistrict() != null){
				districtName = constituency.getDistrict().getDistrictName();
				districtId = constituency.getDistrict().getDistrictId();
			}
			
			electionType = constituency.getElectionScope().getElectionType().getElectionType();
			constituencyDetails.setConstituencyId(constituencyId);
			constituencyDetails.setDistrictId(districtId);
			constituencyDetails.setConstituencyName(StringUtils.capitalize(constituency.getName().toLowerCase()));
			constituencyDetails.setDistrictName(districtName);
			constituencyDetails.setStateName(constituency.getState().getStateName());
			constituencyDetails.setStartDate(constituency.getStartDate());
			constituencyDetails.setDeformDate(constituency.getDeformDate());
			constituencyDetails.setConstituencyType(constituency.getElectionScope().getElectionType().getElectionType());
			constituencyDetails.setArea_type(constituency.getAreaType());
			
			List<Long> constituencyIds = new ArrayList<Long>(0);
			constituencyIds.add(constituencyId);
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				electionYear = Long.parseLong(electionDAO.findLatestElectionAssemblyElectionYearForState(IConstants.ASSEMBLY_ELECTION_TYPE, constituency.getState().getStateId(),IConstants.ELECTION_SUBTYPE_MAIN)
						.get(0).toString()) ;
			}else{
				electionYear = Long.parseLong(electionDAO.findLatestParliamentElectionYear(IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN).get(0).toString()) ;
			}
			
			list = constituencyElectionDAO.getLatestReservationZone(constituencyIds,electionYear);
					
			for(int i=0;i<list.size();i++){
				Object[] params = (Object[])list.get(i);
				if(params[0]!=null){
					constituencyDetails.setReservation_zone(params[0].toString());
				}
			}
			
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType) && 
					(constituency.getStartDate()== null || StringUtils.isBlank(constituency.getStartDate().toString())) && 
					(constituency.getDeformDate() == null || StringUtils.isBlank(constituency.getDeformDate().toString())))
				hasAnalyzeData = true;
				
			constituencyDetails.setHasAnalize(hasAnalyzeData);
			return constituencyDetails;
		}
		else
			return null;
	}
	
	public ResultWithExceptionVO getAllMandalLevelLeaders(Long tehsilId){
		ResultWithExceptionVO result = new ResultWithExceptionVO();
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
			result.setExceptionEncountered(e);
			if(log.isDebugEnabled()){
				log.debug("Exception Raised In getAllMandalLevelLeaders", e);
			}
		}
		result.setFinalResult(influencingPeopleVOs);
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
	
	public MandalAndRevenueVillagesInfoVO getPanchayatWiseBoothDetailsForTehsil(Long tehsilId, Long electionId){
		MandalAndRevenueVillagesInfoVO mandalWiseResult = new MandalAndRevenueVillagesInfoVO();
		List<ElectionResultByLocationVO> panchayatsInConstituencyVOList = new ArrayList<ElectionResultByLocationVO>();
		Map<ElectionResultByLocationVO, List<Object[]>> panchayatsInConstituencyMap = new HashMap<ElectionResultByLocationVO, List<Object[]>>();
		List<LocationWiseBoothDetailsVO> panchayatWiseBoothDetails = null;
		ElectionResultByLocationVO constituencyWisePanchayats = null;
		LocationWiseBoothDetailsVO boothsHamletsInAPanchayat = null;
		List<Object[]> rawData = null;
		Set<SelectOptionVO> boothNos = null;
		List<SelectOptionVO> hamletsOfAPanchayat = null;
		
		try{
			
			List<Object[]> boothDetails = hamletBoothElectionDAO.getPanchayatWiseBoothDetails(tehsilId,electionId); 
			
			if(boothDetails != null && boothDetails.size() > 0)
			{
				for(Object[] params : boothDetails)
				{
					constituencyWisePanchayats = new ElectionResultByLocationVO();
					constituencyWisePanchayats.setConstituencyId((Long)params[0]);
					constituencyWisePanchayats.setConstituencyName(params[1] != null ? params[1].toString():"");
					rawData = panchayatsInConstituencyMap.get(constituencyWisePanchayats);
					
					if(rawData == null)
						rawData = new ArrayList<Object[]>();
						
					rawData.add(params);
					panchayatsInConstituencyMap.put(constituencyWisePanchayats, rawData);	
				}
				
				for(Map.Entry<ElectionResultByLocationVO, List<Object[]>> entry :panchayatsInConstituencyMap.entrySet())
				{
					constituencyWisePanchayats = entry.getKey();
					rawData = entry.getValue();
					panchayatWiseBoothDetails = new ArrayList<LocationWiseBoothDetailsVO>(0);
					for(Object[] obj:rawData)
					{
						boothsHamletsInAPanchayat = checkForPanchayatObj(panchayatWiseBoothDetails,(Long)obj[2]);
						if(boothsHamletsInAPanchayat == null)
						{
							boothsHamletsInAPanchayat = new LocationWiseBoothDetailsVO();
							boothsHamletsInAPanchayat.setLocationId((Long)obj[2]);
							boothsHamletsInAPanchayat.setLocationName(obj[3] != null ? obj[3].toString() : "");
							
							hamletsOfAPanchayat = new ArrayList<SelectOptionVO>(0);
							boothNos = new LinkedHashSet<SelectOptionVO>(0);
							
							hamletsOfAPanchayat.add(new SelectOptionVO((Long)obj[4],obj[5] != null ? obj[5].toString() : ""));
							boothNos.add(new SelectOptionVO((Long)obj[6],obj[7] != null ? obj[7].toString() : ""));
							
							boothsHamletsInAPanchayat.setBooths(boothNos);
							boothsHamletsInAPanchayat.setHamletsOfTownship(getHamletsOfAPanchayat((Long)obj[2]));
							
							boothsHamletsInAPanchayat.setPopulation((Long)obj[8]);
							boothsHamletsInAPanchayat.setVotesPolled((Long)obj[9]);
							
							panchayatWiseBoothDetails.add(boothsHamletsInAPanchayat);
						}
						else
						{
							SelectOptionVO booths  = new SelectOptionVO((Long)obj[6],obj[7] != null ? obj[7].toString() : "");
							SelectOptionVO hamlets = new SelectOptionVO((Long)obj[4],obj[5] != null ? obj[5].toString() : "");
							
							if(!boothsHamletsInAPanchayat.getBooths().contains(booths))
								boothsHamletsInAPanchayat.getBooths().add(booths);
							
							if(!boothsHamletsInAPanchayat.getHamletsOfTownship().contains(hamlets))
								boothsHamletsInAPanchayat.getHamletsOfTownship().add(hamlets);
							
							boothsHamletsInAPanchayat.setPopulation((Long)obj[8] + boothsHamletsInAPanchayat.getPopulation());
							boothsHamletsInAPanchayat.setVotesPolled((Long)obj[9] + boothsHamletsInAPanchayat.getVotesPolled());
						}
					}
					constituencyWisePanchayats.setRevenueVillagesInfo(panchayatWiseBoothDetails);
					panchayatsInConstituencyVOList.add(constituencyWisePanchayats);
				}
				mandalWiseResult.setPartiesResultsInVillages(panchayatsInConstituencyVOList);
			}	  
			else
			{
				mandalWiseResult.setResultCode(ResultCodeMapper.FAILURE);
				mandalWiseResult.setResultPartial(true);
			}
			
		}catch(Exception e){
			mandalWiseResult.setExceptionEncountered(e);
			mandalWiseResult.setResultCode(ResultCodeMapper.FAILURE);
			if(log.isDebugEnabled()){
				log.debug("Exception Occured ::", e);
			}
		}		
		
		return mandalWiseResult;
	}
	
	
	public List<SelectOptionVO> getHamletsOfAPanchayat(Long panchayatId)
	{
		try{
			List<SelectOptionVO> hamletsList = null;
			List<Object[]> list = panchayatHamletDAO.getHamletsOfAPanchayat(panchayatId);
			
			if(list != null && list.size() > 0)
			{
				hamletsList = new ArrayList<SelectOptionVO>(0);
				for(Object[] params : list)
					hamletsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			}
			return hamletsList;
		}catch (Exception e) {
			log.error("Error Occured in getHamletsOfAPanchayat(), Error is - "+e); 
			return null;
		}
	}
	public LocationWiseBoothDetailsVO checkForPanchayatObj(List<LocationWiseBoothDetailsVO> list , Long panchayatId)
	{
		for(LocationWiseBoothDetailsVO locVO : list)
		{
			if(locVO.getLocationId().longValue() == panchayatId.longValue())
				return locVO;
		}
		return null;
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
					if(polledVotes > 0)
						revenueVillageParty.setVotesPercentage(new BigDecimal((votesEarned*100.0)/polledVotes)
												.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					else
						revenueVillageParty.setVotesPercentage("0.0");
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
	
	
	public List<ConstituencyRevenueVillagesVO> getPartiesResultsInPanchayatsGroupByMandal(Long tehsilId, Long electionId) {

		MandalAndRevenueVillagesInfoVO mandalAndRevenueVillagesInfoVO = getPanchayatWiseBoothDetailsForTehsil(tehsilId, electionId);
		
		Map<ConstituencyRevenueVillagesVO, ElectionResultByLocationVO> constituencyWithPanchayatMap = 
			new LinkedHashMap<ConstituencyRevenueVillagesVO, ElectionResultByLocationVO>();
		List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVOs = new ArrayList<ConstituencyRevenueVillagesVO>();
		List<RevenueVillageElectionVO> revenueVillageElectionVOs = null;	
		RevenueVillageElectionVO revenueVillageElectionVO = null;
		List<PartyElectionResultVO> revenueVillageParties = null;
		PartyElectionResultVO revenueVillageParty = null;
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO = null;
		
		Long votesEarned = null;
		Long polledVotes = null;
		List<CandidatePartyInfoVO> candidates = null;
		
		
		for(ElectionResultByLocationVO byLocationVO : mandalAndRevenueVillagesInfoVO.getPartiesResultsInVillages())
		{
			constituencyRevenueVillagesVO = new ConstituencyRevenueVillagesVO();
			constituencyRevenueVillagesVO.setConstituencyId(byLocationVO.getConstituencyId());
			constituencyRevenueVillagesVO.setConstituencyName(byLocationVO.getConstituencyName());
			constituencyWithPanchayatMap.put(constituencyRevenueVillagesVO, byLocationVO);
		}
		
		//Group By Revenue Villages
		
		for(Map.Entry<ConstituencyRevenueVillagesVO,ElectionResultByLocationVO> entry : constituencyWithPanchayatMap.entrySet())
		{
			constituencyRevenueVillagesVO = entry.getKey();
			candidates = new ArrayList<CandidatePartyInfoVO>();
			revenueVillageElectionVOs = new ArrayList<RevenueVillageElectionVO>();
			
			for(LocationWiseBoothDetailsVO locationWiseBoothDetailsVO :entry.getValue().getRevenueVillagesInfo())
			{
				revenueVillageElectionVO = new RevenueVillageElectionVO();
				revenueVillageElectionVO.setTownshipId(locationWiseBoothDetailsVO.getLocationId());
				revenueVillageElectionVO.setTownshipName(locationWiseBoothDetailsVO.getLocationName());
				
				revenueVillageParties = new ArrayList<PartyElectionResultVO>(); 
				candidates = getPanchayatWiseElectionsForPartiesInATehsil(getListFromSetOfSelectOptionVO(locationWiseBoothDetailsVO.getBooths()),electionId);
				for(CandidatePartyInfoVO values:candidates)
				{
					revenueVillageParty = new PartyElectionResultVO();
					votesEarned = values.getVotesEarned();
					polledVotes = locationWiseBoothDetailsVO.getVotesPolled();
					revenueVillageParty.setVotesEarned(votesEarned);
					if(polledVotes > 0)
						revenueVillageParty.setVotesPercentage(new BigDecimal((votesEarned*100.0)/polledVotes)
												.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					else
						revenueVillageParty.setVotesPercentage("0.0");
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
	
	public List<PartyVotesEarnedVO> getPanchayatWiseElectionsForTehsil(String boothIdStr,Long electionId)
	{
		StringTokenizer str = new StringTokenizer(boothIdStr,",");
		List<Long> boothIdList = new ArrayList<Long>(0);
		while(str.hasMoreTokens())
		{
			boothIdList.add(Long.parseLong(str.nextToken()));
		}
		List<Object[]> list = candidateBoothResultDAO.findBoothResultsForBoothsAndElection(boothIdList,electionId);
		List<PartyVotesEarnedVO> partyResults = new ArrayList<PartyVotesEarnedVO>();
		PartyVotesEarnedVO partyVotesEarnedVO = null;
		for(Object[] params : list)
		{
			partyVotesEarnedVO = new PartyVotesEarnedVO();
			partyVotesEarnedVO.setPartyId((Long)params[0]);
			partyVotesEarnedVO.setPartyName(params[1].toString());
			partyVotesEarnedVO.setVotesEarned((Long)params[2]);
			partyResults.add(partyVotesEarnedVO);
		}
		return partyResults;
	}
	
	public List<CandidatePartyInfoVO> getPanchayatWiseElectionsForPartiesInATehsil(List<Long> boothIdList,Long electionId)
	{
		List<Object[]> list = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionWithParty(boothIdList,electionId);
		List<CandidatePartyInfoVO> partyResults = new ArrayList<CandidatePartyInfoVO>();
		CandidatePartyInfoVO candidatePartyInfoVO = null;
		for(Object[] params : list)
		{
			candidatePartyInfoVO = new CandidatePartyInfoVO();
			candidatePartyInfoVO.setPartyId((Long)params[0]);
			candidatePartyInfoVO.setParty(params[1].toString());
			candidatePartyInfoVO.setVotesEarned((Long)params[2]);
			candidatePartyInfoVO.setCandidateId((Long)params[3]);
			candidatePartyInfoVO.setCandidateName(params[4].toString());
			candidatePartyInfoVO.setRank((Long)params[5]);
			partyResults.add(candidatePartyInfoVO);
		}
		return partyResults;
	}
	
	List<Long> getListFromSetOfSelectOptionVO(Set<SelectOptionVO> set)
	{
		List<Long> list = new ArrayList<Long>();
		
		for(SelectOptionVO selectOptionVO : set)
			list.add(selectOptionVO.getId());
		return list;
	}
	
	public ResultWithExceptionVO saveAndUpdateHamletAndBoothInfo(HamletAndBoothVO hamletWithBoothId){
		hamletAndBoothVO = hamletWithBoothId;
		ResultWithExceptionVO resultWithExceptionVO = (ResultWithExceptionVO)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status) {
				ResultWithExceptionVO resultWithExceptionVO = new ResultWithExceptionVO();
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
						villageBoothInfoVO.setVillagesCovered(boothConstituencyElection.getBooth().getVillagesCovered());
						villageBoothInfoVO.setVillageName(villageBoothElection.getTownship().getTownshipName());
						villageBoothInfoVO.setConstituencyName(boothConstituencyElection.getConstituencyElection().getConstituency().getName());
						villagesInfo.add(villageBoothInfoVO);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
					resultWithExceptionVO.setExceptionEncountered(e);
				}
				hamletAndBoothVO = null;
				resultWithExceptionVO.setFinalResult(villagesInfo);
				return resultWithExceptionVO;
			}
			
		});
		return resultWithExceptionVO;
	}
	
	
	public ResultWithExceptionVO deleteVillageBoothElectionRecord(HamletAndBoothVO villageBoothElectionId){
		hamletAndBoothVO = villageBoothElectionId;
		ResultWithExceptionVO result = (ResultWithExceptionVO)transactionTemplate.execute(new TransactionCallback(){

			public Object doInTransaction(TransactionStatus status) {
				ResultWithExceptionVO result = new ResultWithExceptionVO();
				try{
					villageBoothElectionDAO.remove(hamletAndBoothVO.getHamletId());
				}catch(Exception e){
					e.printStackTrace();
					result.setExceptionEncountered(e);
				}			
				return result;
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
		
		List<VotersWithDelimitationInfoVO> votersWithDelimitationInfoVOList = new ArrayList<VotersWithDelimitationInfoVO>(0);
		List<VotersWithDelimitationInfoVO> votersWithDelimitationBasicInfoVOList = new ArrayList<VotersWithDelimitationInfoVO>(0);
		
		List<VotersInfoForMandalVO> votersInfoForMandalList = null;
		List<VotersInfoForMandalVO> votersBasicInfoForMandalList = null;
		List<MandalVO> localElectionsInfo = new ArrayList<MandalVO>(0);
		 
		Map<String, String> mandalsIdsYear = new LinkedHashMap<String, String>(0);
		Map<String, List<String>> isPartialByYear = new LinkedHashMap<String, List<String>>(0);
		List<String> isPartialInfoForMandal = null;
		
		for (int i = 0; i < mandalsList.size(); i++) 
		{
			Object[] obj = (Object[]) mandalsList.get(i);	
			MandalVO mandalVO = new MandalVO();
			mandalVO.setId(new Long(obj[0].toString()));
			mandalVO.setName(obj[1].toString());
			
			String year = obj[2].toString();
			mandalVO.setElectionYear(year);
			String partialData = obj[3].toString();
			mandalVO.setIsPartial(obj[3].toString().equalsIgnoreCase("1")?"NO":"YES");
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
			localElectionsInfo.add(mandalVO);
		}	
		
		constituencyVO.setLocalElectionsInfo(localElectionsInfo);
		
		Set<String>  keys = mandalsIdsYear.keySet();
		
		for(String year:keys)
		{	
			votersInfoForMandalList = new ArrayList<VotersInfoForMandalVO>(0);
			votersBasicInfoForMandalList = new ArrayList<VotersInfoForMandalVO>(0);
			
			VotersWithDelimitationInfoVO votersWithDelimitationInfoVO = new VotersWithDelimitationInfoVO();
			VotersWithDelimitationInfoVO votersWithDelimitationBasicInfoVO = new VotersWithDelimitationInfoVO();
			votersWithDelimitationInfoVO.setYear(year);
			votersWithDelimitationBasicInfoVO.setYear(year);
			
			for(MandalVO result: localElectionsInfo){
				VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
				votersInfo.setMandalId(result.getId().toString());
				votersInfo.setMandalName(result.getName());
				votersInfo.setIsPartial(result.getIsPartial());
				if(year.equalsIgnoreCase(result.getElectionYear())){
					votersBasicInfoForMandalList.add(votersInfo);
				}
			}
			
			votersWithDelimitationBasicInfoVO.setVotersBasicInfoForMandalVO(votersBasicInfoForMandalList);
			votersWithDelimitationBasicInfoVOList.add(votersWithDelimitationBasicInfoVO);
			constituencyVO.setAssembliesOfParliamentBasicInfo(votersWithDelimitationBasicInfoVOList);
			
			String mandalIds = mandalsIdsYear.get(year);
			List<String> partailData = isPartialByYear.get(year);
			List votersList = boothDAO.findVoterInformationByMandalIdsAndDelimitationYear(mandalIds, year, constituencyId);
			
			if(votersList.size() == 0){
				if(calculateVotersForLocalElectionBodies(constituencyId, 
					votersWithDelimitationInfoVO, Long.parseLong(year.trim())))
						votersWithDelimitationInfoVOList.add(votersWithDelimitationInfoVO);
				
				continue;
			}
				
			
			for(int j = 0;j<votersList.size();j++)
			{
				VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
				
				Object[] vObj = (Object[]) votersList.get(j);
				votersInfo.setMandalId( ((Long)vObj[0]).toString());
				votersInfo.setMandalName(vObj[1].toString().toUpperCase()+" "+IConstants.MANDAL);
				votersInfo.setTotalMaleVoters(vObj[2].toString());
				votersInfo.setTotalFemaleVoters(vObj[3].toString());
				votersInfo.setTotalVoters(vObj[4].toString());
				votersInfo.setTotVoters(new BigDecimal(vObj[4].toString()));
				votersInfo.setIsMandal(true);
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
			calculateVotersForLocalElectionBodies(constituencyId, votersWithDelimitationInfoVO, Long.parseLong(year.trim()));
			votersWithDelimitationInfoVOList.add(votersWithDelimitationInfoVO);

		}
		
		constituencyVO.setAssembliesOfParliamentInfo(votersWithDelimitationInfoVOList);
		return constituencyVO;
		
	}
		
	private Boolean calculateVotersForLocalElectionBodies(Long constituencyId, VotersWithDelimitationInfoVO votersWithDelimitationInfoVO, Long year) {
		VotersInfoForMandalVO votersInfoInTown = null;
		List<VotersInfoForMandalVO> votersInfoInTowns = new ArrayList<VotersInfoForMandalVO>();
		votersWithDelimitationInfoVO.setYear(IConstants.DELIMITATION_YEAR+"");
		//votersWithDelimitationInfoVO.setYear(year.toString());
		List beforeDelimLocalBodies = boothDAO.findVotersInfoForConstituencyInAnYearByLocalElectionBody(constituencyId, 
				year, "'"+IConstants.MUNCIPLE_ELECTION_TYPE+"','"+IConstants.CORPORATION_ELECTION_TYPE+"'");
		List beforeDelimGMCs = boothDAO.findVotersInfoForConstituencyInAnYearByLocalElectionBodyWard(constituencyId, 
				year, IConstants.GREATER_ELECTION_TYPE);
		
		if(beforeDelimGMCs!= null && beforeDelimGMCs.size()!=0){
			votersWithDelimitationInfoVO.setType("Wards");
		}else{
			votersWithDelimitationInfoVO.setType("");
		}
		if(beforeDelimLocalBodies.size() == 0 && beforeDelimGMCs.size() == 0){
			return false;
		}
			
		
		for(Object[] values:(List<Object[]>)beforeDelimLocalBodies){
			votersInfoInTown = new VotersInfoForMandalVO();
			votersInfoInTown.setMandalId( ((Long)values[0]).toString());
			votersInfoInTown.setMandalName(values[1].toString().toUpperCase() + " " +values[5]);
			votersInfoInTown.setTotalMaleVoters(values[2].toString());
			votersInfoInTown.setTotalFemaleVoters(values[3].toString());
			votersInfoInTown.setTotalVoters(values[4].toString());
			votersInfoInTown.setTotVoters(new BigDecimal(values[4].toString()));
			votersInfoInTowns.add(votersInfoInTown);
			
		}
		
		for(Object[] values:(List<Object[]>)beforeDelimGMCs){
			votersInfoInTown = new VotersInfoForMandalVO();
			votersInfoInTown.setMandalId( ((Long)values[0]).toString());
			votersInfoInTown.setMandalName(values[5] + " " + values[1]);
			votersInfoInTown.setTotalMaleVoters(values[2].toString());
			votersInfoInTown.setTotalFemaleVoters(values[3].toString());
			votersInfoInTown.setTotalVoters(values[4].toString());
			votersInfoInTown.setTotVoters(new BigDecimal(values[4].toString()));
			votersInfoInTowns.add(votersInfoInTown);
		}
		
		if(votersWithDelimitationInfoVO.getVotersInfoForMandalVO()!=null)
			votersInfoInTowns.addAll(0, votersWithDelimitationInfoVO.getVotersInfoForMandalVO());
		
		votersWithDelimitationInfoVO.setVotersInfoForMandalVO(votersInfoInTowns);
		return true;
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
	
	@SuppressWarnings("unchecked")
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId)
	{	
		String electionType = "";
		String deformDate = "";
		
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO ();
		
		//Constituency consti = constituencyDAO.get(constituencyId);
		/**
		 * DAO method call to get the election type and delimitation info.If delimitation info is null returning null.
		 */
		List constituencyTypeDetails = constituencyDAO.getConstituencyTypeAndDelimitationInfoByConstituencyId(constituencyId);
		if(constituencyTypeDetails != null && constituencyTypeDetails.size()>0)
		{
			Object[] obj = (Object[])constituencyTypeDetails.get(0);
			electionType = (String)obj[0];
			if(obj[1]!=null)
			 deformDate = obj[1].toString();
		}
		log.info(" Election Type :" + electionType);
		log.info(" Deform Date :" + deformDate);
		if(!deformDate.equalsIgnoreCase("") || deformDate == null)
			return null;
		
		//---------------
		List candidateList = null;
		if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		    candidateList = nominationDAO.getCandidateAndPartyInfoForParliament(constituencyId,electionType, 1L);
		else{
			/*Long stateID = consti.getElectionScope().getState().getStateId();
			candidateList = nominationDAO.getCandidateNPartyInfo(constituencyId.toString(), electionType, 1L, IConstants.ELECTION_SUBTYPE_MAIN, stateID);*/
			candidateList = nominationDAO.getCandidateAndPartyInfo(constituencyId, electionType,1L);
		}
		
		
		log.info("Nomination List :" + candidateList.size());
		if(candidateList.size() == 0)
			return null;
		List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList);
		log.info("Candidate Info :" + candidateInfoList.size());
		 
		if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);
				
			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			if(list != null && list.size() > 0){
			Object[] listData = (Object[]) list.get(0);
			Long asemblyId = (Long) listData[0];
			List result = nominationDAO.getParliamentCandidateNPartyInfo(asemblyId, IConstants.PARLIAMENT_ELECTION_TYPE, 1L);
				if(result.size()!=0){
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(extractCandidateNPartyDataFromList(result).get(0));
				}else{
					log.error("Assembly candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
				}			
			}else{
				candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
			}
			
		}
		else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfoList.get(0));
			
			List assembliesData = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(constituencyId);
			/*Constituency assemConsti = null;*/
			
			if(assembliesData != null && assembliesData.size() > 0)
			{
				/*StringBuilder idString = new StringBuilder();
				for(int j = 0 ; j < assembliesData.size() ; j++)
				{
					Object[] ids = (Object[]) assembliesData.get(j);
					idString.append(IConstants.COMMA).append((Long)ids[0]);
					
					if(j == 0)
						assemConsti = constituencyDAO.get((Long)ids[0]);				
				}	
				
				if(idString.length() > 0)
				{
					Long stateId = 0L;
					if(assemConsti != null)
					stateId = assemConsti.getElectionScope().getState().getStateId();
					List result = nominationDAO.getCandidateNPartyInfo(idString.substring(1), IConstants.ASSEMBLY_ELECTION_TYPE, 1L, IConstants.ELECTION_SUBTYPE_MAIN,stateId);*/
					
					List<Object[]> result = new ArrayList<Object[]>(0);
					for(int j = 0 ; j < assembliesData.size() ; j++)
					{
						Long constId = (Long)((Object[])assembliesData.get(j))[0];
						result.add(nominationDAO.getCandidateAndPartyInfo(constId,IConstants.ASSEMBLY_ELECTION_TYPE, 1L).get(0));
						
					}
					if(result.size()!=0){
						candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(extractCandidateNPartyDataFromList(result));
					}else{
						log.error("Parliament candidate data for this constituency is not present");
						candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
					}			    
				/*}*/
			}else{
				candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
			}
		}
		
		return candidateDetailsForConstituencyTypesVO;
	}

	/**
	 * This Method Returns Election Results Of a Parliament Constituency (Assembly Constituencies Wise)
	 * And Assembly Constituency Election Results (Mandal Wise)
	 */
	@SuppressWarnings("unchecked")
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
			constituencyRevenueVillagesVO = getMandalElectionInfoForAConstituency(constituencyId, electionYear,IConstants.ASSEMBLY_ELECTION_TYPE, includeOthers);
			if(constituencyRevenueVillagesVO != null){
				constituencyRevenueVillagesVO.setConstituencyId(constituencyId);
				constituencyRevenueVillagesVO.setConstituencyName(constituency.getName());
				constituencyRevenueVillagesVO.setElectionType(constituency.getElectionScope().
						getElectionType().getElectionType());	
				constituencyRevenueVillagesVO.setElectionYear(electionYear);
				constituencyRevenueVillagesVO.setAreaType(constituency.getAreaType());
			}
			
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
			constituencyRevenueVillagesVO.setElectionYear(electionYear);
			constituencyRevenueVillagesVO.setAreaType(constituency.getAreaType());
			
			List<SelectOptionVO> assemblies = getAssembliesForParliament(constituencyId, new Long(electionYear));
			int i=0;
			
			for(SelectOptionVO assembly:assemblies){	
				List partiesResults = candidateBoothResultDAO.findAssemblyRegionResultsForPartiesForAConstituency(assembly.getId(), constituencyId, electionYear);
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
					partyElectionResultVO.setVotesPercent(new BigDecimal(((Long)values[3])*100.0/(Long)values[4])
																	.setScale(2,BigDecimal.ROUND_HALF_UP));
					
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
			
			if(includeOthers){
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
					if(totalBaletVotes > 0 && (partyBaletVotes < totalBaletVotes)){
						partyElectionResultVO.setVotesPercentage(new BigDecimal(partyBaletVotes*100.0/totalBaletVotes)
																	.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
					}
					else{
						log.error("Valid Votes Are Less Than Candidate Votes:totalBaletVotes-"+totalBaletVotes+" Party Votes-"+partyBaletVotes+" Party Name-"+values[1]);
						partyElectionResultVO.setVotesPercentage("0");	
					}
					
					partyElectionResultVOs.add(partyElectionResultVO);
				}
					
				assemblyConstiElecVO.setPartyElectionResultVOs(partyElectionResultVOs);
				
				constituencyElectionResults.add(assemblyConstiElecVO);	
			}
			
		}
		
		constituencyRevenueVillagesVO.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
		Collections.sort(constituencyElectionResults,new ConstituencyOrMandalVOComparatorMandal());
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
	 * This method returns a VO containing all the information regarding the
	 * assembly candidate voting trends in the assembly constituency.
	 */
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAConstituency(Long constituencyId,String electionYear,String electionType, 
			Boolean includeOthers){	
		try{
			Constituency constituency = constituencyDAO.get(constituencyId);
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVOMain = null;
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVOMunicipal = null;
			List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandal(constituencyId, electionYear, electionType);
			
			if(list.size() > 0)
				constituencyRevenueVillagesVOMain = setDataForVOForCorrespondingAssemblyOrParliament(list,constituencyId,
					null, electionYear, electionType, false, true);
			
			if(!IConstants.CONST_TYPE_URBAN.equalsIgnoreCase(constituency.getAreaType())){
				List listMuncipalInfo = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByLocalElectionBody(constituencyId, electionYear, electionType, 
						"'"+IConstants.MUNCIPLE_ELECTION_TYPE+"','"+IConstants.CORPORATION_ELECTION_TYPE+"'");
				getAssemblyOrParliamentResultsLEBswise(listMuncipalInfo, constituencyRevenueVillagesVOMain, constituencyRevenueVillagesVOMunicipal, 
						constituencyId, electionYear, electionType, true);
			}else{
				List listGeaterInfo = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByLocalElectionBodyWard(constituencyId, electionYear, electionType, 
						IConstants.CORPORATION_ELECTION_TYPE);
				getAssemblyOrParliamentResultsLEBswise(listGeaterInfo, constituencyRevenueVillagesVOMain, constituencyRevenueVillagesVOMunicipal, 
						constituencyId, electionYear, electionType, false);
			}
			
			List listGeaterInfo = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByLocalElectionBodyWard(constituencyId, electionYear, electionType, 
					IConstants.GREATER_ELECTION_TYPE);
			constituencyRevenueVillagesVOMain = getAssemblyOrParliamentResultsLEBswise(listGeaterInfo, constituencyRevenueVillagesVOMain, constituencyRevenueVillagesVOMunicipal, 
					constituencyId, electionYear, electionType, false);
			
			if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				List result = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(constituencyId, Long.parseLong(electionYear));
				String pcNames = "";
				for(Object[] values:(List<Object[]>)result)
					pcNames += ","+values[1];
				if(constituencyRevenueVillagesVOMain != null){
					constituencyRevenueVillagesVOMain.setConstituencyId((Long)((Object[])result.get(0))[0]);
					constituencyRevenueVillagesVOMain.setConstituencyName(pcNames.substring(1));
					constituencyRevenueVillagesVOMain.setElectionType(electionType);
					constituencyRevenueVillagesVOMain.setElectionYear(electionYear);
				}
			}
			
			//Others Calculation
			if(constituencyRevenueVillagesVOMain != null && includeOthers){
				ConstituencyOrMandalWiseElectionVO constituencyOrMandalWiseElectionVo = new ConstituencyOrMandalWiseElectionVO();
				constituencyOrMandalWiseElectionVo.setLocationName(IConstants.OTHERS);
				constituencyOrMandalWiseElectionVo.setTotalPolledVotes(0l);
				constituencyOrMandalWiseElectionVo.setPartyElectionResultVOs(OtherVotesDataForAnAssembly(constituencyRevenueVillagesVOMain.
						getConstituencyOrMandalWiseElectionVO(), constituencyId, electionYear, electionType));
				constituencyRevenueVillagesVOMain.getConstituencyOrMandalWiseElectionVO().add(constituencyOrMandalWiseElectionVo);	
			}
			
			return constituencyRevenueVillagesVOMain;		
		}catch(Exception e){
			e.printStackTrace();
			if(log.isDebugEnabled()){
				log.debug("Exception raised in getMandalElectionInfoForAConstituency() method of Constituency Page Service.");
			}
			return null;
		}		
	}
	
	private ConstituencyRevenueVillagesVO getAssemblyOrParliamentResultsLEBswise(List listMuncipalInfo, ConstituencyRevenueVillagesVO constituencyRevenueVillagesVOMain,
			ConstituencyRevenueVillagesVO constituencyRevenueVillagesVOMunicipal, Long constituencyId, String electionYear, String electionType,
			Boolean showLink){
		if(listMuncipalInfo.size() > 0){
			if(constituencyRevenueVillagesVOMain != null){
				constituencyRevenueVillagesVOMunicipal = setDataForVOForCorrespondingAssemblyOrParliament(listMuncipalInfo,constituencyId, null, 
						electionYear, electionType, true, showLink);
				constituencyRevenueVillagesVOMain.getConstituencyOrMandalWiseElectionVO().addAll(0,constituencyRevenueVillagesVOMunicipal.
						getConstituencyOrMandalWiseElectionVO());
			}
			else
				constituencyRevenueVillagesVOMain = setDataForVOForCorrespondingAssemblyOrParliament(listMuncipalInfo,constituencyId, null, 
						electionYear, electionType, true, showLink);
		}
		
		return constituencyRevenueVillagesVOMain;
	}
	
	/**
	 * This method takes the result obtained from the DAO call and calls other methods for processing and sets data into VO.
	 * @param list
	 * @param constituencyId
	 * @param constituencyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyRevenueVillagesVO setDataForVOForCorrespondingAssemblyOrParliament(List list, Long constituencyId, String constituencyName, 
			String electionYear,String electionType, Boolean isUrban, Boolean showLink){
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
		String tehsilOrLocalBodyName = "";
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
				
				if(isUrban)
					tehsilOrLocalBodyName = parms[8].toString() + " " + parms[0].toString();
				else
					tehsilOrLocalBodyName = parms[0].toString();
				
				tehsilNameAndIds.put((Long)parms[1],tehsilOrLocalBodyName);
			}
		
			for(int i=0; i<tehsilIds.size(); i++){				
				ConstituencyOrMandalWiseElectionVO constituencyOrMandalWiseElectionVo = new ConstituencyOrMandalWiseElectionVO();
				if(log.isDebugEnabled()){
					log.debug("Calling caluculatePercentage()");
				}
				partyVotes = caluculatePercentage(tehsilIds.get(i),list,totalVotesForAMandal.get(tehsilIds.get(i)));	
				constituencyOrMandalWiseElectionVo.setLocationId(tehsilIds.get(i));
				constituencyOrMandalWiseElectionVo.setLocationName(tehsilNameAndIds.get(tehsilIds.get(i)));
				constituencyOrMandalWiseElectionVo.setShowLink(showLink);
				constituencyOrMandalWiseElectionVo.setIsUrban(isUrban);
				constituencyOrMandalWiseElectionVo.setTotalPolledVotes(totalVotesForAMandal.get(tehsilIds.get(i)));
				constituencyOrMandalWiseElectionVo.setPartyElectionResultVOs(partyVotes);
				if(log.isDebugEnabled()){
					log.debug("Calling getCandidateAndPartyDetails()");
				}
				
				candidateNamePartyAndStatus = getCandidateAndPartyDetails(mainTehsilId,list);
				constituencyOrMandalWiseElectionVO.add(constituencyOrMandalWiseElectionVo);
			}
			if(constituencyOrMandalWiseElectionVO != null && constituencyOrMandalWiseElectionVO.size() > 0)
			    Collections.sort(constituencyOrMandalWiseElectionVO, new ConstituencyOrMandalVOComparator());

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
	 * This method calculates all the Missing Votes For an Assembly(i.e., Postal Ballot Votes).
	 * @param constituencyId
	 * @param electionYear
	 * @param electionType
	 * @return
	 */
	public List<PartyElectionResultVO> OtherVotesDataForAConstituency(Long constituencyId, String electionYear, String electionType){
		Map<Long,Double> partyIdAndTheirVotes = new HashMap<Long,Double>(0);
		List<PartyElectionResultVO> partyElectionResultVOs = new ArrayList<PartyElectionResultVO>(0);
		PartyElectionResultVO partyVotes = null; 
		List list = candidateBoothResultDAO.findAssemblyRegionResultsForPartiesForAConstituency(constituencyId, constituencyId, electionYear);
		List nominationResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(constituencyId.toString(),electionYear,electionType);
		Long constiVotesEarned = 0l;
		Long votesDiff = 0l;
		Long othersSum = 0l;
		for(Object[] values: (List<Object[]>)nominationResult)
			partyIdAndTheirVotes.put((Long)values[4], (Double)values[2]);
		
		for(Object[] values: (List<Object[]>)list){
			partyVotes = new PartyElectionResultVO();
			partyVotes.setPartyId(Long.parseLong(values[0].toString()));
			partyVotes.setRank(Long.parseLong(values[2].toString()));
			constiVotesEarned = partyIdAndTheirVotes.get(values[2]).longValue();
			votesDiff = constiVotesEarned - Long.parseLong(values[3].toString());
			if(votesDiff < 0){
				partyVotes.setVotesEarned(0l);
				partyVotes.setVotesPercentage("0");
				votesDiff = 0l;
			}
			else
				partyVotes.setVotesEarned(votesDiff);
			othersSum += votesDiff;
			partyElectionResultVOs.add(partyVotes);
		}
		
		//Calculate Percentage
		for(PartyElectionResultVO electionResultVO:partyElectionResultVOs){
			if(electionResultVO.getVotesEarned() > 0)
				electionResultVO.setVotesPercentage(new BigDecimal((electionResultVO.getVotesEarned()*100.0)/othersSum).
						setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
			
		
		Collections.sort(partyElectionResultVOs, new SortByRankOnPartyElectionResultComparator());
		Collections.reverse(partyElectionResultVOs);		
		return partyElectionResultVOs;	
	}
	
	public List<PartyElectionResultVO> OtherVotesDataForAnAssembly(List<ConstituencyOrMandalWiseElectionVO> partiesResultlist, 
			Long constituencyId, String electionYear, String electionType){
		Map<Long,Long> partyIdAndTheirVotes = new HashMap<Long,Long>(0);
		Long totalVotesEarned = 0l;
		Long votesEarnedBoothwise = 0l;
		Long votesDiff = 0l;
		Long othersSum = 0l;
		PartyElectionResultVO partyVotes = null;
		List<PartyElectionResultVO> partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
		for(int i=0; i < partiesResultlist.get(0).getPartyElectionResultVOs().size(); i++){
			totalVotesEarned = 0l;
			for(int j=0; j < partiesResultlist.size(); j++)
				totalVotesEarned += partiesResultlist.get(j).getPartyElectionResultVOs().get(i).getVotesEarned();
			partyIdAndTheirVotes.put(partiesResultlist.get(0).getPartyElectionResultVOs().get(i).getRank(), totalVotesEarned);
		}
			
		List nominationResult = nominationDAO.getCandidatesInfoForTheGivenConstituency(constituencyId.toString(),electionYear,electionType);
		for(Object[] values: (List<Object[]>)nominationResult){
			partyVotes = new PartyElectionResultVO();
			partyVotes.setPartyId((Long)values[5]);
			partyVotes.setRank(Long.parseLong(values[4].toString()));
			votesEarnedBoothwise = partyIdAndTheirVotes.get(values[4]);
			if(votesEarnedBoothwise != null)
				votesDiff =  ((Double)values[2]).longValue() - votesEarnedBoothwise;
			if(votesDiff < 0){
				partyVotes.setVotesEarned(0l);
				partyVotes.setVotesPercentage("0");
				votesDiff = 0l;
			}
			else
				partyVotes.setVotesEarned(votesDiff);
			othersSum += votesDiff;
			partyElectionResultVOs.add(partyVotes);
		}
		
		for(PartyElectionResultVO electionResultVO:partyElectionResultVOs){
			if(electionResultVO.getVotesEarned() > 0)
				electionResultVO.setVotesPercentage(new BigDecimal((electionResultVO.getVotesEarned()*100.0)/othersSum).
						setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		
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
						PartyElectionResultVO partyElectionResultVO = new PartyElectionResultVO();
						partyElectionResultVO.setRank((Long)parms[2]);
						partyElectionResultVO.setPartyId((Long)parms[7]);
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
	
	@SuppressWarnings("unchecked")
	public ConstituencyRevenueVillagesVO getMandalsResultsInAnElectionForChart(String mandalIds, String electionYear, String electionType){
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
		Map<String,String> partyNames = new HashMap<String,String>();
		ConstituencyRevenueVillagesVO finalVOReturn = new ConstituencyRevenueVillagesVO();
		List<CandidatePartyInfoVO> candidateNamePartyAndStatus = new ArrayList<CandidatePartyInfoVO>();
		//Party Name and Votes Earned Map With In A Mandal
		Map<String, Long> partyVotesEarnedMap = new HashMap<String, Long>();
		
		Long mandalId = 0l;
		String partyName = "";
		Long votesEarned = 0l;
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
				
				if(partyNames.isEmpty()){
					partyNames.put(partyResultVO.getPartyName(),partyResultVO.getPartyName());
				}
				if(!partyNames.containsKey(partyResultVO.getPartyName()))
					partyNames.put(partyResultVO.getPartyName(),partyResultVO.getPartyName());
				partyResultVO.setVotesPercent(new BigDecimal((Long)values[3]*100.0/mandalAndValidVotesMap.get(values[0])).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				partiesResultsInMandals.add(partyResultVO);
				mandalwisePartiesMap.put(mandalId, partiesResultsInMandals);
			}
			
			List totalVoters = null;
			Long totalVotersLong = 0l;
			List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO = new ArrayList<ConstituencyOrMandalWiseElectionVO>();
			for(Map.Entry<Long, List<PartyResultVO>> entry:mandalwisePartiesMap.entrySet()){
				partiesResultsInMandals = entry.getValue();
				totalVoters = boothConstituencyElectionDAO.getTotalVotersInAnElectionInMandal(entry.getKey(),electionType,IConstants.PRESENT_ELECTION_YEAR);
				totalVotersLong = (Long)totalVoters.get(0);
				
				ConstituencyOrMandalWiseElectionVO mandalVO = new ConstituencyOrMandalWiseElectionVO();
				List<PartyElectionResultVO> partyElectionResultVOs = new ArrayList<PartyElectionResultVO>();
				
				String mandalName="";
				mandalVO.setLocationId(entry.getKey());
				
				Set<String> mapKeys = partyNames.keySet();
				System.out.println(" ............... Map Size :" + mapKeys);
				for(String partyNm:mapKeys){
				  Boolean flag = false;
				  PartyElectionResultVO resultVO = new PartyElectionResultVO();
			 	  for(PartyResultVO party:partiesResultsInMandals){
			 			 		
					party.setElectors(totalVotersLong);
					finalResults.add(party);
					mandalName = party.getConstituencyName();
					if(partyNm.equalsIgnoreCase(party.getPartyName())){
						flag = true;
						resultVO.setPartyName(partyNm);
						resultVO.setVotesPercent(new BigDecimal(party.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
				  }	
			 	  if(flag == false){
			 		 resultVO.setPartyName(partyNm);
			 		 resultVO.setVotesPercent(null);
			 	  }
			 	 partyElectionResultVOs.add(resultVO);
				}
				mandalVO.setLocationName(mandalName);
				mandalVO.setTotalVoters(totalVotersLong);
				Collections.sort(partyElectionResultVOs, new PartyElectionResultComparator());
				mandalVO.setPartyElectionResultVOs(partyElectionResultVOs);
				
				
				constituencyOrMandalWiseElectionVO.add(mandalVO);
			}
			Set<String> mapKeys = partyNames.keySet();
			for(String pty:mapKeys){
				CandidatePartyInfoVO partyDetails = new CandidatePartyInfoVO();
				partyDetails.setParty(pty);
				candidateNamePartyAndStatus.add(partyDetails);
			}
			Collections.sort(candidateNamePartyAndStatus, new CandidatePartyInfoVOComparator());
			finalVOReturn.setCandidateNamePartyAndStatus(candidateNamePartyAndStatus);
			
			//Collections.sort(constituencyOrMandalWiseElectionVO, new ConstituencyOrMandalVOComparatorTotVoters());
			Collections.sort(constituencyOrMandalWiseElectionVO, new ConstituencyOrMandalVOComparatorMandal());
			finalVOReturn.setConstituencyOrMandalWiseElectionVO(constituencyOrMandalWiseElectionVO);
		}catch (Exception e) {
			log.debug("Exception Occured While Processing Mandal Data");
			e.printStackTrace();
		}

		if(finalResults != null && finalResults.size() > 0)
			Collections.sort(finalResults, new PartyResultVOComparatorByElectors());

		return finalVOReturn;
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
			
			List totalVoters = null;
			Long totalVotersLong = 0l;

			for(Map.Entry<Long, List<PartyResultVO>> entry:mandalwisePartiesMap.entrySet()){
				partiesResultsInMandals = entry.getValue();
				totalVoters = boothConstituencyElectionDAO.getTotalVotersInAnElectionInMandal(entry.getKey(),electionType,IConstants.PRESENT_ELECTION_YEAR);
				totalVotersLong = (Long)totalVoters.get(0);
				for(PartyResultVO party:partiesResultsInMandals){
					party.setElectors(totalVotersLong);
					finalResults.add(party);
				}				
			}
			
		}catch (Exception e) {
			log.debug("Exception Occured While Processing Mandal Data");
			e.printStackTrace();
		}

		if(finalResults != null && finalResults.size() > 0)
			Collections.sort(finalResults, new PartyResultVOComparatorByElectors());

		return finalResults;
	}
	
	/*private PartyResultVO getPartyResultVOObjWithPercent(String partyName, String percenatge, Long electorsInMandal, String mandalName){
		PartyResultVO partyResultVO = new PartyResultVO();
		partyResultVO.setPartyName(partyName);
		partyResultVO.setVotesPercent(percenatge);
		partyResultVO.setElectors(electorsInMandal);
		partyResultVO.setConstituencyName(mandalName);
		return partyResultVO;
	}*/
	
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
			List acVotersInfo = boothDAO.findAssemblyConstituenciesDetailsForParliament(entry.getValue(), entry.getKey());
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
				votersInfoForAcVO.setTotVoters(new BigDecimal(values[4].toString()));
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
	public List<TeshilPartyInfoVO> getPartyWiseZptcOrMptcElectionDataForAConstituency(Long constituencyId,String electionYear,
			String electionType,String constituencyType){	
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
			if(tehsilIds.toString().trim().length() > 0)
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
  		ConstituencyRevenueVillagesVO latestChart = getConstituencyElecResults(constituencyId, IConstants.LATEST_BYE_ELECTION_YEAR, false);
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
  		if(latestChart != null && latestChart.getConstituencyOrMandalWiseElectionVO() != null && latestChart.getConstituencyOrMandalWiseElectionVO().size() > 0)
  		charts.setLatestYearChart(latestChart);
  		charts.setPreviousYearChart(partiesMandalResults);
  		return charts;
  	}
	
	/**
	 * This method give Mandal Wise Election Result when we pass ConstituencyId,ElectionYear,ElectionType
	 * 
	 * @author kamalakar Dandu
	 * @param Long constituencyId
	 * @param String ElectionYear
 	 * @param String ElectionType
	 * @return ConstituencyRevenueVillagesVO
	 * 
	 */
	
	public ConstituencyRevenueVillagesVO getMandalElectionInfoForAssemblyConstituencyForCensus(Long constituencyId,String electionYear,String electionType)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the constituencyPageService.getMandalElectionInfoForAssemblyConstituencyForCensus().. Call");
			}
		ConstituencyRevenueVillagesVO constituencyRevenueVillagesVOMain = null;
		List list = candidateBoothResultDAO.getCandidatesResultsForElectionAndConstituencyByMandalWise(constituencyId, electionYear, electionType);
		
		//Here we set the result to the VO
		if(list.size() > 0)
			constituencyRevenueVillagesVOMain = setDataForVOForCorrespondingAssemblyOrParliament(list,constituencyId,
				null, electionYear, electionType, false, true);
		
		//Here we are getting Total votesPercent in String format,so we are converting into Big Decimal
		if(constituencyRevenueVillagesVOMain != null)
		{
			for(ConstituencyOrMandalWiseElectionVO constituencyOrMandalWiseElectionVO:constituencyRevenueVillagesVOMain.getConstituencyOrMandalWiseElectionVO())
			{
				for(PartyElectionResultVO partyElectionResultVO:constituencyOrMandalWiseElectionVO.getPartyElectionResultVOs())
				{
					partyElectionResultVO.setVotesPercent(new BigDecimal(partyElectionResultVO.getVotesPercentage()));
				}
				
			}
			return constituencyRevenueVillagesVOMain;
		}
	}catch(Exception ex)
	{
		log.debug("Exception Occured In the constituencyPageService.getMandalElectionInfoForAssemblyConstituencyForCensus().... ");
		log.error("Exception raised please check the log for details"+ex);
		return null;
	}
		return null;
	}
	/**
	 * This method give all Census Details of a Constituency when we pass Constituency Id,Delimitation Year,Census Year
	 * 
	 * @author kamalakar Dandu
	 * @param Long constituencyId
	 * @param Long Delimitation Year
 	 * @param Long Census Year
	 * @return List<CensusVO>
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<CensusVO> getCensusDetailsForAssemblyConstituency(Long constituencyId,Long delimitationYear,Long censusYear)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the constituencyPageService.getCensusDetailsForAssemblyConstituency().. Call");
			}	
			//Getting Delimitation Constituency Id
			List<Object> delimConstList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDForCensus(constituencyId,delimitationYear);
			
			if(delimConstList != null && delimConstList.size() > 0 )
			{
				Long delimitationConstituencyId = (Long) delimConstList.get(0);
				
				List<CensusVO> censusVOList = new ArrayList<CensusVO>();
								
				//Here we are getting Latest Tehsil to the Delimitation Constituency
				List<Object[]> delimConMandals = delimitationConstituencyMandalDAO.getLatestMandalsForAConstituency(delimitationConstituencyId);
				
				if(delimConMandals != null && delimConMandals.size() > 0)
				{
					//Here we are getting census details to each tehsil in the Constituency.
					for(Object[] params:delimConMandals)
					{
						CensusVO  censusVO = getCensusDetailsForATehsil(params,censusYear,delimitationConstituencyId);
						
						//If census is not present to the Tehsil then it retuns null,that we are ommitting
						if(censusVO != null)
						censusVOList.add(censusVO);
					}
				}
				 //Here we are passing the CensusVO list to calculate and return the Calculated list
				  return calculateCensusPercentage(censusVOList);
			  }
			
			else
			{
				if(log.isDebugEnabled()){
					log.debug("There No Delimitaition Constituency Id for this Constituency......and There is no possibility of getting Census Details");
				}	
				return null;
			}
			
		}
		catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.getMandalElectionInfoForAssemblyConstituencyForCensus().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
		
	}
	
	 /**
	 * 
	 * This Method will give Census Details  for A Tehsil when we pass Object Array which contains Tehsil Id
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public CensusVO getCensusDetailsForATehsil(Object[] params,Long censusYear,Long delimitationConstituencyId)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("Entered Into constituencyPageService.getCensusDetailsForATehsil() Method .....");
			}
			
			Long dcmId         = (Long)params[0];
			Long mandalId      = (Long)params[1];
			String mandalName  = params[2].toString();
			boolean isPartial  = params[3].toString().equalsIgnoreCase("0");
			
			Tehsil tehsil   = tehsilDAO.get(mandalId);
			Long districtId =  tehsil.getDistrict().getDistrictId();
			Long stateId    =  tehsil.getDistrict().getState().getStateId();
			
			//if mandal is partial then calculate census for mandal sub regions
			if(isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial Block of constituencyPageService.getCensusDetailsForATehsil() Method .....");
				}
			//For villages
			CensusVO censusVOVillage = getVillageWiseCensusDetailsForPartialTehsil(stateId,districtId,dcmId,censusYear);
			//For Towns		
			List<CensusVO> censusVOTown = getCensusDetailsOfATowmInAPartialTehsil(delimitationConstituencyId,mandalId,censusYear);
			
			
			//Here we are add the villages wise census and towns wise(if town is partial ward wise census)to a single mandal census VO 
			if(censusVOTown != null && censusVOTown.size() > 0)
			{
				if(censusVOVillage != null)
				{
					censusVOTown.add(censusVOVillage);
				}
				CensusVO censusVOTehsil	= addCensusDataToSingleVO(censusVOTown);
				censusVOTehsil.setTehsilId(mandalId);
				censusVOTehsil.setLocationName(mandalName);
				
				return censusVOTehsil;
			}
			else
			{
				if(censusVOVillage != null)
				{
					censusVOVillage.setTehsilId(mandalId);
					censusVOVillage.setLocationName(mandalName);
					return censusVOVillage;
				}
			}
			
		}
			//mandal is full
			else if(!isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial false Block of constituencyPageService.getCensusDetailsForATehsil() Method .....");
				}
				List<Object[]> list = censusDAO.findMandalWiseCensusDetails(stateId,districtId,mandalId,censusYear,IConstants.TEHSIL_LEVEL);
				
				if(list != null && list.size() > 0)
				{
				   CensusVO censusVO = setCensusDetailsToVO(list.get(0));
				   censusVO.setTehsilId(mandalId);
				   censusVO.setLocationName(mandalName);
				   return censusVO;
				}
			}
				
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.getCensusDetailsForATehsil().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
    return null;
   }
	 /**
	 * 
	 * This Method will give Census Details  for Villages in the Tehsil if it is partial
	 * 
	 * @author kamalakar Dandu
	 * @param Long stateId
	 * @param Long districtId
	 * @param Long dcmId
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	@SuppressWarnings("unchecked")
	public CensusVO getVillageWiseCensusDetailsForPartialTehsil(Long stateId,Long districtId,Long dcmId,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered Into constituencyPageService.getVillageWiseCensusDetailsForPartialTehsil() Method .....");
		}
			
		//Here we are getting String as return type which contains comma separeted Village ids 
		String villageIdStr = getVillageIdsOfAPartialTehsil(dcmId);
		
		if((villageIdStr != null) && !(villageIdStr.trim().isEmpty()) )
		{
			
		List<Object[]> villCensus = censusDAO.findCensusDetailsForAPartialMandal(stateId,districtId,censusYear,IConstants.CENSUS_VILLAGE_LEVEL,villageIdStr);
		
		if(villCensus != null && villCensus.size() > 0)
		{
			Object [] details = villCensus.get(0);
			CensusVO censusVO = new CensusVO();
			censusVO = setCensusDetailsToVO(details);
			return censusVO;
		}
	   }
	  }catch(Exception ex)
		{
		  log.debug("Exception Occured In the constituencyPageService.getVillageWiseCensusDetailsForPartialTehsil().... ");
		  log.error("Exception raised please check the log for details"+ex);
		  return null;
		}
		return null;
  }
	
	 /**
	 * 
	 * This Method will give Census Details  for Towns in the Tehsil if it is partial
	 * 
	 * @author kamalakar Dandu
	 * @param Long delimitationConstituencyId
	 * @param Long mandalId
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	public List<CensusVO> getCensusDetailsOfATowmInAPartialTehsil(Long delimitationConstituencyId,Long mandalId,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered Into constituencyPageService.getCensusDetailsOfATowmInAPartialTehsil() Method .....");
		}
		//Here we are getting Latest towns for Delimitation Constituency
		List<Object[]> towns = getLatestTownsForATehsil(delimitationConstituencyId,mandalId);
		
		if(towns != null && towns.size() > 0)
		{
			List<CensusVO> townCensusVOList = new ArrayList<CensusVO>();
			for(Object[] param:towns)
			{
				//Here we are getting Census Details For a town
				CensusVO  censusVO = getCensusDetailsForATown(param,censusYear);
				
				//If census is not present to the town then it retuns null,that we are ommitting
				if(censusVO != null)
				townCensusVOList.add(censusVO);
			}
			
			return townCensusVOList;
		}
	}catch(Exception ex)
	{
		log.debug("Exception Occured In the constituencyPageService.getCensusDetailsOfATowmInAPartialTehsil().... ");
		log.error("Exception raised please check the log for details"+ex);
		return null;
	}
		return null;
		
		
	}
	
	
	 /**
	 * 
	 * This Method will give Latest Towns for A DelimitationConstituency when we pass delimitationConstituencyId as Parameter
	 * 
	 * @author kamalakar Dandu
	 * @param Long delimitationConstituencyId
	 * @return List<Object[]>
	 * 
	 *  
	 */
	public List<Object[]> getLatestTownsForADelimitationConstituency(Long delimitationConstituencyId)
	{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.getLatestTownsForADelimitationConstituency() with Delimitation ConstituencyId "+delimitationConstituencyId);
		}
		
		try
		{
		List<Object[]> delimConTowns = delimitationConstituencyTownDAO.getLatestTownsForAConstituency(delimitationConstituencyId);
		
		if(delimConTowns != null && delimConTowns.size() > 0)
		{
			return delimConTowns;
		}
		else
		{
			if(log.isDebugEnabled()){
				log.debug("In this "+delimitationConstituencyId+" Delimitation Constituency, There are no Towns Exists..... ");
			}
			return null;
		}
	  }catch(Exception e){
		  log.debug("Exception Occured In the constituencyPageService.getLatestTownsForADelimitationConstituency() with Delimitation ConstituencyId "+delimitationConstituencyId);
		  log.error("Exception raised please check the log for details"+e);
		  e.printStackTrace();
		  return null;
	  }
	}
	
	 /**
	 * 
	 * This Method will give Census Details  for A Town when we pass Object Array which contains Town Id
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public CensusVO getCensusDetailsForATown(Object[] params,Long censusYear)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("Entered Into constituencyPageService.getCensusDetailsForATown() Method .....");
			}	
			
			//we are setting the Location Details
			Long dctId = (Long)params[0];
			Long townshipId = (Long)params[1];
			boolean isPartial  = params[2].toString().equalsIgnoreCase("1");
			
			Township township = townshipDAO.get(townshipId);
			Long districtId =  township.getTehsil().getDistrict().getDistrictId();
			Long stateId =  township.getTehsil().getDistrict().getState().getStateId();
			
			if(isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial Block of constituencyPageService.getCensusDetailsForATown() Method .....");
				}
				
				//Here we are getting String as return type which contains comma separeted ward ids 
				String wardIdStr = getWardIdsOfAPartialTownship(dctId);
				
				if((wardIdStr != null) && (!wardIdStr.isEmpty()))
				{
				//Here we are getting census details of the total wards group By   
				List<Object[]> wardCensus = censusDAO.findCensusDetailsForAPartialTown(stateId,districtId,censusYear,IConstants.WARD,wardIdStr);
					
				if(wardCensus != null && wardCensus.size() > 0)
				{
					return setCensusDetailsToVO(wardCensus.get(0));
				}
				
			   }
			}
			else
			{
				if(log.isDebugEnabled()){
					log.debug("The Town is full in the Constituency and Entered to partial false Block of constituencyPageService.getCensusDetailsForATown() Method .....");
				}
				//Here we are getting census details of the Township
				List<Object[]> list = censusDAO.findTownshipWiseCensusDetails(stateId,districtId,townshipId,censusYear,IConstants.TOWN);
				if(list != null && list.size() > 0)
				{
				   return  setCensusDetailsToVO(list.get(0));
				}
		  }
			
		  return null;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.getCensusDetailsForATown().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
	}
	
	/**
	 * 
	 * This Method will set Census Details to the CensusVO
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @return CensusVO
	 * 
	 */
	
	public CensusVO setCensusDetailsToVO(Object[] details)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered isPartial Block of constituencyPageService.setCensusDetailsToVO() Method .....");
		}
		
		CensusVO censusVO = new CensusVO();
	
		censusVO.setTotalPopulation((Long)details[0]);
		censusVO.setPopulationSC((Long)details[1]);
		censusVO.setPopulationST((Long)details[2]);
		censusVO.setLiterates((Long)details[3]);
		censusVO.setIlliterates((Long)details[4]);
		censusVO.setWorkingPeople((Long)details[5]);
		censusVO.setNonWorkingPeople((Long)details[6]);
		
		return censusVO;
		}
		catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.setCensusDetailsToVO().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
		
	}
	
	/**
	 * 
	 * This Method will give comma separated String which contains Ward Ids
	 * 
	 * @author kamalakar Dandu
	 * @param Long Delimitation Constituency Town Id
	 * @return String
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getWardIdsOfAPartialTownship(Long dctId)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered isPartial Block of constituencyPageService.getWardIdsOfAPartialTownship() Method .....");
		}
		//Here we are getting wards of a Township
		List<Object[]> list = delimitationWardDAO.getWardsFromPartialTownship(dctId);
		StringBuilder wardIds = new StringBuilder();
		
		for(int i=0;i<list.size();i++ )
		{
			Object obj = list.get(i);
			wardIds.append(obj.toString());
			wardIds.append(",");
		}
		
		//Here we are removing the "," which is present at the end
		if(wardIds.length() > 0)
		{
			String wardIdStr = wardIds.substring(0, wardIds.length()-1);
			return wardIdStr;
		}
		else
		{
			return null;
		}
	 }
		catch(Exception ex){
			
			log.debug("Exception Occured In the constituencyPageService.getWardIdsOfAPartialTownship().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
	}
			
	/**
	 * 
	 * This Method will give comma separated String which contains Village Ids
	 * 
	 * @author kamalakar Dandu
	 * @param Long Delimitation Constituency Mandal Id
	 * @return String
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public String getVillageIdsOfAPartialTehsil(Long dcmId)
	{
		try{
		if(log.isDebugEnabled()){
			log.debug("Entered isPartial Block of constituencyPageService.getVillageIdsOfAPartialTehsil() Method .....");
		}
		List<Object[]> list = delimitationVillageDAO.getVillagesFromPartialMandal(dcmId);

		StringBuilder villageIds = new StringBuilder();
		
		for(int i=0;i<list.size();i++ )
		{
			Object obj = list.get(i);
			villageIds.append(obj.toString());
			villageIds.append(",");
		}
		if(villageIds.length() > 0)
		{
			String villageIdStr = villageIds.substring(0, villageIds.length()-1);
			return villageIdStr;
		}
		else
		{
			return null;
		}
	  }catch(Exception ex){
		  log.debug("Exception Occured In the constituencyPageService.getVillageIdsOfAPartialTehsil().... ");
		  log.error("Exception raised please check the log for details"+ex);
		  return null;
	  }
	}
	
	 /**
	 * 
	 * This Method will give Latest Towns for A DelimitationConstituency when we pass delimitationConstituencyId as Parameter
	 * 
	 * @author kamalakar Dandu
	 * @param Long delimitationConstituencyId
	 * @param Long tehsilId
	 * @return List<Object[]>
	 * 
	 *  
	 */
	
	public List<Object[]> getLatestTownsForATehsil(Long delimitationConstituencyId,Long tehsilId)
	{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.getLatestTownsForATehsil("+delimitationConstituencyId+" "+tehsilId+")");
		}
		
		try
		{
		List<Object[]> towns = delimitationConstituencyTownDAO.getLatestTownsForATehsil(delimitationConstituencyId, tehsilId);
		
		if(towns != null && towns.size() > 0)
		{
			return towns;
		}
		else
		{
			if(log.isDebugEnabled()){
				log.debug("In this "+delimitationConstituencyId+" Delimitation Constituency, There are no Towns Exists..... ");
			}
		}
	  }catch(Exception e){
		  log.debug("Exception Occured In the constituencyPageService.getLatestTownsForATehsil("+delimitationConstituencyId+" "+tehsilId+")");
		  log.error("Exception raised please check the log for details"+e);
		  e.printStackTrace();
	  }
		return null;
	}
	/**
	 * 
	 * This Method add List of census Details to a single VO
	 * 
	 * @author kamalakar Dandu
	 * @param List<CensusVO>
	 * @return CensusVO
	 * 
	 *  
	 */
	
	public CensusVO addCensusDataToSingleVO(List<CensusVO> censusVOList)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.addCensusDataToSingleVO() method ......");
		}
		if(censusVOList.size() == 0)
			return null;
		else
		{
			CensusVO censusVO = new CensusVO();
			
			Long totalPopulation = 0l;
			Long totalSCPopulation = 0l;
			Long totalSTPopulation = 0l;
			Long literates = 0l;
			Long illiterates = 0l;
			Long workingPeople = 0l;
			Long nonWorkingPeople = 0l;
			
			for(CensusVO cenVO:censusVOList)
			{
				totalPopulation   += cenVO.getTotalPopulation();
				totalSCPopulation += cenVO.getPopulationSC();
				totalSTPopulation += cenVO.getPopulationST();
				literates         += cenVO.getLiterates();
				illiterates       += cenVO.getIlliterates();
				workingPeople     += cenVO.getWorkingPeople();
				nonWorkingPeople  += cenVO.getNonWorkingPeople();
			}
			censusVO.setTotalPopulation(totalPopulation);
			censusVO.setPopulationSC(totalSCPopulation);
			censusVO.setPopulationST(totalSTPopulation);
			censusVO.setLiterates(literates);
			censusVO.setIlliterates(illiterates);
			censusVO.setWorkingPeople(workingPeople);
			censusVO.setNonWorkingPeople(nonWorkingPeople);
			
			return censusVO;
		}
	}catch(Exception e){
		  log.debug("Exception Occured In the constituencyPageService.addCensusDataToSingleVO() method .........");
		  log.error("Exception raised please check the log for details"+e);
		  return null;
	  }
	}
	
	
	 /**
	 * 
	 * This Method will Calculate Percentages of the Census Details 
	 * 
	 * @author kamalakar Dandu
	 * @param List<CensusVO>
	 * @return List<CensusVO>
	 * 
	 *  
	 */
	
	public  List<CensusVO> calculateCensusPercentage(List<CensusVO> censusVOList)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.calculateCensusPercentage()");
		}
		if(censusVOList.size() > 0)
		{
		//Here we are Counting the Census Percentage
		for(CensusVO cenVO:censusVOList)
		{
			cenVO.setTotPopPercent(new BigDecimal((cenVO.getTotalPopulation()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setTotalPopulationPercentage(cenVO.getTotPopPercent().toString());
			cenVO.setPopulationSCPercent(new BigDecimal((cenVO.getPopulationSC()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setPopulationSCPercentage(cenVO.getPopulationSCPercent().toString());
			cenVO.setPopulationSTPercent(new BigDecimal((cenVO.getPopulationST()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setPopulationSTPercentage(cenVO.getPopulationSTPercent().toString());
			cenVO.setLiteratesPercent(new BigDecimal((cenVO.getLiterates()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setLiteratesPercentage(cenVO.getLiteratesPercent().toString());
			cenVO.setIlliteratesPercent(new BigDecimal((cenVO.getIlliterates()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setIlliteratesPercentage(cenVO.getIlliteratesPercent().toString());
			cenVO.setWorkingPeoplePercent(new BigDecimal((cenVO.getWorkingPeople()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setWorkingPeoplePercentage(cenVO.getWorkingPeoplePercent().toString());
			cenVO.setNonWorkingPeoplePercent(new BigDecimal((cenVO.getNonWorkingPeople()*100.0)/cenVO.getTotalPopulation()).setScale(2, BigDecimal.ROUND_HALF_UP));
			cenVO.setNonWorkingPeoplePercentage(cenVO.getNonWorkingPeoplePercent().toString());
		}
		return censusVOList;
		}
		else
		{
			return null;
		}
	  }catch(Exception e){
		  log.debug("Exception Occured In the constituencyPageService.calculateCensusPercentage().......");
		  log.error("Exception raised please check the log for details"+e);
		  return null;
	  }
	}
	 
	/**
	 * 
	 * This Method will give Census Details of a Parliament Constituency 
	 * 
	 * @author kamalakar Dandu
	 * @param ConstituencyId
	 * @return List<CensusVO>
	 * 
	 *  
	 */
	public List<CensusVO> getCensusDetailsForAParliamentConstituency(Long parliamentId,Long electionYear,Long delimitationYear,Long censusYear)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the constituencyPageService.getCensusDetailsForAParliamentConstituency()");
			}
			List<CensusVO> censusVOParliament = new ArrayList<CensusVO>();
			
			//Here we are getting Assemblies in a Parliament Constituency...
			List<SelectOptionVO> assemblies = getAssembliesForParliament(parliamentId,electionYear);
			
			for(SelectOptionVO assembly:assemblies)
			{
				boolean isExists = checkForConstituencyExistance(assembly.getId());
				
				if(isExists)
				{
					List<Object[]> cenList = findConstituencyWiseCensusDetails(assembly.getId(),censusYear);
					
					if(cenList != null && cenList.size() > 0)
					{
						CensusVO censusVO = setCensusDetailsToVO(cenList.get(0));
						censusVO.setLocationName(assembly.getName());
						censusVO.setLocationId(assembly.getId());
						censusVOParliament.add(censusVO);
					}
				}
				else
				{ 												
					CensusVO censusMainVO = getCompleteCensusDetailsForAnAssemblyConstituency(assembly.getId(),delimitationYear,censusYear);

					String result = saveCensusToConstituencyCensusDetails(censusMainVO,assembly.getId(),censusYear,IConstants.FALSE);
					
					if(result.equalsIgnoreCase(IConstants.SUCCESS))
					{
						List<Object[]> cenList = findConstituencyWiseCensusDetails(assembly.getId(),censusYear);
						
						if(cenList != null && cenList.size() > 0)
						{
							CensusVO censusVO = setCensusDetailsToVO(cenList.get(0));
							censusVO.setLocationName(assembly.getName());
							censusVO.setLocationId(assembly.getId());
							censusVOParliament.add(censusVO);
						}
					}
				}
			}
			return calculateCensusPercentage(censusVOParliament);
			
		}catch(Exception ex){
			log.debug("Exception Occured In the constituencyPageService.getCensusDetailsForAParliamentConstituency().......");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	
	}
	/**
	 * 
	 * This Method will remove the Assembly constituencies from census VO List, in which Election result is not available
	 * 
	 * @author kamalakar Dandu
	 * @param List<CensusVO> censusVOList
	 * @param ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO
	 * @return List<CensusVO>
	 * 
	 *  
	 */
	public List<CensusVO> removeMissingConstituencies(List<CensusVO> censusVOList,ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the constituencyPageService.removeMissingConstituencies() method ........");
			}

		//Here we are Itarates the List of Missing Constituencies
		for(SelectOptionVO missing :constituencyRevenueVillagesVO.getMissingConstituencies())
		{
			//Here we are searching for missing Consttuency id in the census VO List and removing that census VO.
			for(CensusVO censusVO:censusVOList)
			{
				if(censusVO.getLocationId() ==  missing.getId())
				{
					censusVOList.remove(censusVO);
				}
			}
		}
		
		return censusVOList;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.removeMissingConstituencies() method ........");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	}
	
	/**
	 * 
	 * This Method will add census VOs to list as per the order of Constituencies in the Election result 
	 * 
	 * @author kamalakar Dandu
	 * @param List<CensusVO> censusVOList
	 * @param ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO
	 * @return List<CensusVO>
	 * 
	 *  
	 */
	
	public List<CensusVO> setCensusVO(List<CensusVO> censusVOList,ConstituencyRevenueVillagesVO constituencyRevenueVillagesVO)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("In the constituencyPageService.setCensusVO() method ........");
			}
			
		List<CensusVO> censusVO = new ArrayList<CensusVO>();
		//Here we are setting the Census VOs as per the order of Constituencies in the Election result
		for(ConstituencyOrMandalWiseElectionVO resultVO :constituencyRevenueVillagesVO.getConstituencyOrMandalWiseElectionVO())
		{
			for(CensusVO cen:censusVOList)
			{
				if(resultVO.getLocationId() == cen.getLocationId())
				  censusVO.add(cen);		
			}
		}
		return censusVO;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.setCensusVO() method ........");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	}

	/**
	 * This Method will Check the availability of given Constituency in Constituency_census_details Table. 
	 * 
	 * @author kamalakar Dandu
	 * @param Long constituencyId
	 * @return boolean
	 * 
	 */
	
	public boolean checkForConstituencyExistance(Long constituencyId)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.setCensusVO() method ........");
		}
		//Here we are getting Constitiency Id
		List<Long>list = constituencyCensusDetailsDAO.checkForConstituencyExistance(constituencyId);
		
		if(list != null && list.size() > 0)
		{
			Long id = list.get(0);
			
			if(id.equals(constituencyId))
			{
				return true;
			}
		}
		}catch(Exception ex){
			log.debug("Exception Occured In the constituencyPageService.checkForConstituencyExistance() method ........");
			log.error("Exception raised please check the log for details "+ex);
			return false;
		}
		return false;
	}

	/**
	 * This Method will give Census details of a Constituency from Constituency_census_details Table. 
	 * @author kamalakar Dandu
	 * @param Long constituencyId
	 * @param Long censusYear
	 * @return boolean
	 */
	
	public List<Object[]> findConstituencyWiseCensusDetails(Long constituencyId,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.findConstituencyWiseCensusDetails() method ........");
		}
		Long stateId = constituencyDAO.getStateIdByConstituencyId(constituencyId).get(0);
		
		//Here we are getting Census details of a Connstituency
		List<Object[]> list = constituencyCensusDetailsDAO.findConstituencyWiseCensusDetails(stateId,constituencyId,censusYear);
		
		if(list != null && list.size() > 0)
		{
			return list;
		}
		}catch(Exception ex){
			log.debug("Exception Occured In the constituencyPageService.findConstituencyWiseCensusDetails() method ........");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
		return null;
	}
	
	 /**
	 * 
	 * This Method will give Census Details  for A Tehsil when we pass Object Array which contains Tehsil Id
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @param Long censusYear
	 * @param delimitationConstituencyId
	 * @return CensusVO
	 * 
	 */
	
	public CensusVO getCompleteCensusDetailsForATehsil(Object[] params,Long censusYear,Long delimitationConstituencyId)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("Entered Into constituencyPageService.getCompleteCensusDetailsForATehsil() Method .....");
			}
			
			Long dcmId         = (Long)params[0];
			Long mandalId      = (Long)params[1];
			String mandalName  = params[2].toString();
			boolean isPartial  = params[3].toString().equalsIgnoreCase("0");
			
			Tehsil tehsil   = tehsilDAO.get(mandalId);
			Long districtId =  tehsil.getDistrict().getDistrictId();
			Long stateId    =  tehsil.getDistrict().getState().getStateId();
			
			//if mandal is partial then calculate census for mandal sub regions
			if(isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial Block of constituencyPageService.getCompleteCensusDetailsForATehsil() Method .....");
				}
			//For villages
			CensusVO censusVOVillage = getVillageWiseCompleteCensusDetailsForPartialTehsil(stateId,districtId,dcmId,censusYear);
			//For Towns		
			List<CensusVO> censusVOTown = getCompleteCensusDetailsOfATownInAPartialTehsil(delimitationConstituencyId,mandalId,censusYear);
			
			
			//Here we are add the villages wise census and towns wise(if town is partial ward wise census)to a single mandal census VO 
			if(censusVOTown != null && censusVOTown.size() > 0)
			{
				if(censusVOVillage != null)
				{
					censusVOTown.add(censusVOVillage);
				}
				CensusVO censusVOTehsil	= addCompleteCensusDataToSingleVO(censusVOTown);
				censusVOTehsil.setTehsilId(mandalId);
				censusVOTehsil.setLocationName(mandalName);
				
				return censusVOTehsil;
			}
			else
			{
				if(censusVOVillage != null)
				{
					censusVOVillage.setTehsilId(mandalId);
					censusVOVillage.setLocationName(mandalName);
					return censusVOVillage;
				}
			}
			
		}
			//mandal is full
			else if(!isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial false Block of constituencyPageService.getCompleteCensusDetailsForATehsil() Method .....");
				}
				List<Object[]> list = censusDAO.findMandalWiseCompleteCensusDetails(stateId,districtId,mandalId,censusYear,IConstants.TEHSIL_LEVEL);
				
				if(list != null && list.size() > 0)
				{
				   CensusVO censusVO = setCompleteCensusDetailsToVO(list.get(0));
				   censusVO.setTehsilId(mandalId);
				   censusVO.setLocationName(mandalName);
				   
				   return censusVO;
				}
			}
				
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.getCompleteCensusDetailsForATehsil().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
    return null;
   }
	
	 /**
	 * 
	 * This Method will Set the Data to Census VO when we pass Object[] Array.
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @return CensusVO
	 * 
	 */
	
	public CensusVO setCompleteCensusDetailsToVO(Object[] details)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered isPartial Block of constituencyPageService.setCompleteCensusDetailsToVO() Method .....");
		}
		
		CensusVO censusVO = new CensusVO();
		
		censusVO.setHouseHolds((Long)details[0]);
		censusVO.setTotalPopulation((Long)details[1]);
		censusVO.setMalePopulation((Long)details[2]);
		censusVO.setFemalePopulation((Long)details[3]);
		censusVO.setPopulationUnderSix((Long)details[4]);
		censusVO.setMaleUnderSix((Long)details[5]);
		censusVO.setFemaleUnderSix((Long)details[6]);
		censusVO.setPopulationSC((Long)details[7]);
		censusVO.setMaleSC((Long)details[8]);
		censusVO.setFemaleSC((Long)details[9]);
		censusVO.setPopulationST((Long)details[10]);
		censusVO.setMaleST((Long)details[11]);
		censusVO.setFemaleST((Long)details[12]);
		censusVO.setPopulationLiterates((Long)details[13]);
		censusVO.setMaleLiterates((Long)details[14]);
		censusVO.setFemaleLiterates((Long)details[15]);
		censusVO.setPopulationIlliterates((Long)details[16]);
		censusVO.setMaleIlliterates((Long)details[17]);
		censusVO.setFemaleIlliterates((Long)details[18]);
		censusVO.setWorkingPopulation((Long)details[19]);
		censusVO.setWorkingMale((Long)details[20]);
		censusVO.setWorkingFemale((Long)details[21]);
		censusVO.setMainWorkPopulation((Long)details[22]);
		censusVO.setMainWorkMale((Long)details[23]);
		censusVO.setMainWorkFemale((Long)details[24]);
		censusVO.setMainCLPopulation((Long)details[25]);
		censusVO.setMainCLMale((Long)details[26]);
		censusVO.setMainCLFemale((Long)details[27]);
		censusVO.setMainALPopulation((Long)details[28]);
		censusVO.setMainALMale((Long)details[29]);
		censusVO.setMainALFemale((Long)details[30]);
		censusVO.setMainHHPopulation((Long)details[31]);
		censusVO.setMainHHMale((Long)details[32]);
		censusVO.setMainHHFemale((Long)details[33]);
		censusVO.setMainOTPopulation((Long)details[34]);
		censusVO.setMainOTMale((Long)details[35]);
		censusVO.setMainOTFemale((Long)details[36]);
		censusVO.setMargWorkPopulation((Long)details[37]);
		censusVO.setMargWorkMale((Long)details[38]);
		censusVO.setMargWorkFemale((Long)details[39]);
		censusVO.setMargCLPopulation((Long)details[40]);
		censusVO.setMargCLMale((Long)details[41]);
		censusVO.setMargCLFemale((Long)details[42]);
		censusVO.setMargALPopulation((Long)details[43]);
		censusVO.setMargALMale((Long)details[44]);
		censusVO.setMargALFemale((Long)details[45]);
		censusVO.setMargHHPopulation((Long)details[46]);
		censusVO.setMargHHMale((Long)details[47]);
		censusVO.setMargHHFemale((Long)details[48]);
		censusVO.setMargOTPopulation((Long)details[49]);
		censusVO.setMargOTMale((Long)details[50]);
		censusVO.setMargOTFemale((Long)details[51]);
		censusVO.setNonWorkingPopulation((Long)details[52]);
		censusVO.setNonWorkingMale((Long)details[53]);
		censusVO.setNonWorkingFemale((Long)details[54]);
		censusVO.setSexRatio((Double)details[55]);
		censusVO.setSexRatioSC((Double)details[56]);
		censusVO.setSexRatioST((Double)details[57]);
		censusVO.setHouseHoldsSize((Double)details[58]);
		censusVO.setPercentageSC((Double)details[59]);
		censusVO.setPercentageST((Double)details[60]);
		censusVO.setSexRatioUnderSix((Double)details[61]);
		censusVO.setMaleLiteratureRate((Double)details[62]);
		censusVO.setFemaleLiteratureRate((Double)details[63]);
		censusVO.setGenderGap((Double)details[64]);
		censusVO.setPopLiteraturePercentage((Double)details[65]);
		censusVO.setMaleLiteraturePercentage((Double)details[66]);
		censusVO.setFemaleLiteraturePercentage((Double)details[67]);
		censusVO.setTotalPopPercentage((Double)details[68]);
		censusVO.setTotalWorkingPopPercentage((Double)details[69]);
		censusVO.setTotalWorkingMalePercentage((Double)details[70]);
		censusVO.setTotalWorkingFemalePercentage((Double)details[71]);
		censusVO.setTotalMainPopPercentage((Double)details[72]);
		censusVO.setTotalMainMalePercentage((Double)details[73]);
		censusVO.setTotalMainFemalePercentage((Double)details[74]);
		censusVO.setTotalMargPopPercentage((Double)details[75]);
		censusVO.setTotalMargMalePercentage((Double)details[76]);
		censusVO.setTotalMargFemalePercentage((Double)details[77]);
		censusVO.setNonWorkingPopPercentage((Double)details[78]);
		censusVO.setNonWorkingMalePercentage((Double)details[79]);
		censusVO.setNonWorkingFemalePercentage((Double)details[80]);
		censusVO.setPopCLPercentage((Double)details[81]);
		censusVO.setMaleCLPercentage((Double)details[82]);
		censusVO.setFemaleCLPercentage((Double)details[83]);
		censusVO.setPopALPercentage((Double)details[84]);
		censusVO.setMaleALPercentage((Double)details[85]);
		censusVO.setFemaleALPercentage((Double)details[86]);
		censusVO.setPopHHPercentage((Double)details[87]);
		censusVO.setMaleHHPercentage((Double)details[88]);
		censusVO.setFemaleHHPercentage((Double)details[89]);
		censusVO.setPopOWPercentage((Double)details[90]);
		censusVO.setMaleOWPercentage((Double)details[91]);
		censusVO.setFemaleOWPercentage((Double)details[92]);
		censusVO.setMainMargCLPopulation((Long)details[93]);
		censusVO.setMainMargCLMale((Long)details[94]);
		censusVO.setMainMargCLFemale((Long)details[95]);
		censusVO.setMainMargALPopulation((Long)details[96]);
		censusVO.setMainMargALMale((Long)details[97]);
		censusVO.setMainMargALFemale((Long)details[98]);
		censusVO.setMainMargHHPopulation((Long)details[99]);
		censusVO.setMainMargHHMale((Long)details[100]);
		censusVO.setMainMargHHFemale((Long)details[101]);
		censusVO.setMainMargOWPopulation((Long)details[102]);
		censusVO.setMainMargOWMale((Long)details[103]);
		censusVO.setMainMargOWFemale((Long)details[104]);
		censusVO.setWpr((Double)details[105]);

		return censusVO;
		}
		catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.setCompleteCensusDetailsToVO().... ");
			log.error("Exception raised please check the log for details"+ex);
			return null;
		}
		
	}
	 /**
	 * 
	 * This Method will give Complete Census Details  for Villages in the Tehsil if it is partial
	 * 
	 * @author kamalakar Dandu
	 * @param Long stateId
	 * @param Long districtId
	 * @param Long dcmId
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	@SuppressWarnings("unchecked")
	public CensusVO getVillageWiseCompleteCensusDetailsForPartialTehsil(Long stateId,Long districtId,Long dcmId,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered Into constituencyPageService.getVillageWiseCompleteCensusDetailsForPartialTehsil() Method .....");
		}
			
		//Here we are getting String as return type which contains comma separeted Village ids 
		String villageIdStr = getVillageIdsOfAPartialTehsil(dcmId);
		
		if((villageIdStr != null) && !(villageIdStr.trim().isEmpty()) )
		{
		int noOfParts = findNoOfParts(villageIdStr);
		
		List<Object[]> villCensus = censusDAO.findCompleteCensusDetailsForAPartialMandal(stateId,districtId,censusYear,IConstants.CENSUS_VILLAGE_LEVEL,villageIdStr);

		if(villCensus != null && villCensus.size() > 0)
		{
			Object [] details = villCensus.get(0);
			CensusVO censusVO = new CensusVO();
			censusVO = setSumCompleteCensusDetailsToVO(details,noOfParts);
			return censusVO;
		}
	   }
	  }catch(Exception ex)
		{
		  log.debug("Exception Occured In the constituencyPageService.getVillageWiseCompleteCensusDetailsForPartialTehsil().... ");
		  log.error("Exception raised please check the log for details "+ex);
		  return null;
		}
		return null;
  }
	 /**
	 * 
	 * This Method will give Total Number of Tokens(VillageIds/WardIds) in Given String which contains comma separated Ids
	 * @author kamalakar Dandu
	 * @param String 
	 * @return int
	 */
	
	public int findNoOfParts(String str)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered Into constituencyPageService.findNoOfParts() Method .....");
		}
		if(str.length() > 0)
		{
			StringTokenizer st = new StringTokenizer(str,","); 
			int tokens = st.countTokens();
			
			if(log.isDebugEnabled())
				log.debug("Exited from constituencyPageService.findNoOfParts() Method .....");
			return tokens;
		}
		}catch(Exception ex)
		{
		  log.debug("Exception Occured In the constituencyPageService.findNoOfParts().... ");
		  log.error("Exception raised please check the log for details "+ex);
		  return 0;
		}
		return 0;
	}
	 /**
	 * 
	 * This Method will Set the Data to Census VO when we pass Object[] Array and Divides the Percentages.
	 * 
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @param int
	 * @return CensusVO
	 * 
	 */
	public CensusVO setSumCompleteCensusDetailsToVO(Object[] details,int parts)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered into constituencyPageService.setSumCompleteCensusDetailsToVO() Method .....");
		}
		
		if(details == null || parts == 0 || details.length == 0)
		{
			return null;
		}
		
		CensusVO censusVO = new CensusVO();
		
		censusVO.setHouseHolds((Long)details[0]);
		censusVO.setTotalPopulation((Long)details[1]);
		censusVO.setMalePopulation((Long)details[2]);
		censusVO.setFemalePopulation((Long)details[3]);
		censusVO.setPopulationUnderSix((Long)details[4]);
		censusVO.setMaleUnderSix((Long)details[5]);
		censusVO.setFemaleUnderSix((Long)details[6]);
		censusVO.setPopulationSC((Long)details[7]);
		censusVO.setMaleSC((Long)details[8]);
		censusVO.setFemaleSC((Long)details[9]);
		censusVO.setPopulationST((Long)details[10]);
		censusVO.setMaleST((Long)details[11]);
		censusVO.setFemaleST((Long)details[12]);
		censusVO.setPopulationLiterates((Long)details[13]);
		censusVO.setMaleLiterates((Long)details[14]);
		censusVO.setFemaleLiterates((Long)details[15]);
		censusVO.setPopulationIlliterates((Long)details[16]);
		censusVO.setMaleIlliterates((Long)details[17]);
		censusVO.setFemaleIlliterates((Long)details[18]);
		censusVO.setWorkingPopulation((Long)details[19]);
		censusVO.setWorkingMale((Long)details[20]);
		censusVO.setWorkingFemale((Long)details[21]);
		censusVO.setMainWorkPopulation((Long)details[22]);
		censusVO.setMainWorkMale((Long)details[23]);
		censusVO.setMainWorkFemale((Long)details[24]);
		censusVO.setMainCLPopulation((Long)details[25]);
		censusVO.setMainCLMale((Long)details[26]);
		censusVO.setMainCLFemale((Long)details[27]);
		censusVO.setMainALPopulation((Long)details[28]);
		censusVO.setMainALMale((Long)details[29]);
		censusVO.setMainALFemale((Long)details[30]);
		censusVO.setMainHHPopulation((Long)details[31]);
		censusVO.setMainHHMale((Long)details[32]);
		censusVO.setMainHHFemale((Long)details[33]);
		censusVO.setMainOTPopulation((Long)details[34]);
		censusVO.setMainOTMale((Long)details[35]);
		censusVO.setMainOTFemale((Long)details[36]);
		censusVO.setMargWorkPopulation((Long)details[37]);
		censusVO.setMargWorkMale((Long)details[38]);
		censusVO.setMargWorkFemale((Long)details[39]);
		censusVO.setMargCLPopulation((Long)details[40]);
		censusVO.setMargCLMale((Long)details[41]);
		censusVO.setMargCLFemale((Long)details[42]);
		censusVO.setMargALPopulation((Long)details[43]);
		censusVO.setMargALMale((Long)details[44]);
		censusVO.setMargALFemale((Long)details[45]);
		censusVO.setMargHHPopulation((Long)details[46]);
		censusVO.setMargHHMale((Long)details[47]);
		censusVO.setMargHHFemale((Long)details[48]);
		censusVO.setMargOTPopulation((Long)details[49]);
		censusVO.setMargOTMale((Long)details[50]);
		censusVO.setMargOTFemale((Long)details[51]);
		censusVO.setNonWorkingPopulation((Long)details[52]);
		censusVO.setNonWorkingMale((Long)details[53]);
		censusVO.setNonWorkingFemale((Long)details[54]);
		censusVO.setSexRatio(roundTwoDecimals(details[55])/parts);
		censusVO.setSexRatioSC(roundTwoDecimals(details[56])/parts);
		censusVO.setSexRatioST(roundTwoDecimals(details[57])/parts);
		censusVO.setHouseHoldsSize(roundTwoDecimals(details[58])/parts);
		censusVO.setPercentageSC(roundTwoDecimals(details[59])/parts);
		censusVO.setPercentageST(roundTwoDecimals(details[60])/parts);
		censusVO.setSexRatioUnderSix(roundTwoDecimals(details[61])/parts);
		censusVO.setMaleLiteratureRate(roundTwoDecimals(details[62])/parts);
		censusVO.setFemaleLiteratureRate(roundTwoDecimals(details[63])/parts);
		censusVO.setGenderGap(roundTwoDecimals(details[64])/parts);
		censusVO.setPopLiteraturePercentage(roundTwoDecimals(details[65])/parts);
		censusVO.setMaleLiteraturePercentage(roundTwoDecimals(details[66])/parts);
		censusVO.setFemaleLiteraturePercentage(roundTwoDecimals(details[67])/parts);
		censusVO.setTotalPopPercentage(roundTwoDecimals(details[68])/parts);
		censusVO.setTotalWorkingPopPercentage(roundTwoDecimals(details[69])/parts);
		censusVO.setTotalWorkingMalePercentage(roundTwoDecimals(details[70])/parts);
		censusVO.setTotalWorkingFemalePercentage(roundTwoDecimals(details[71])/parts);
		censusVO.setTotalMainPopPercentage(roundTwoDecimals(details[72])/parts);
		censusVO.setTotalMainMalePercentage(roundTwoDecimals(details[73])/parts);
		censusVO.setTotalMainFemalePercentage(roundTwoDecimals(details[74])/parts);
		censusVO.setTotalMargPopPercentage(roundTwoDecimals(details[75])/parts);
		censusVO.setTotalMargMalePercentage(roundTwoDecimals(details[76])/parts);
		censusVO.setTotalMargFemalePercentage(roundTwoDecimals(details[77])/parts);
		censusVO.setNonWorkingPopPercentage(roundTwoDecimals(details[78])/parts);
		censusVO.setNonWorkingMalePercentage(roundTwoDecimals(details[79])/parts);
		censusVO.setNonWorkingFemalePercentage(roundTwoDecimals(details[80])/parts);
		censusVO.setPopCLPercentage(roundTwoDecimals(details[81])/parts);
		censusVO.setMaleCLPercentage(roundTwoDecimals(details[82])/parts);
		censusVO.setFemaleCLPercentage(roundTwoDecimals(details[83])/parts);
		censusVO.setPopALPercentage(roundTwoDecimals(details[84])/parts);
		censusVO.setMaleALPercentage(roundTwoDecimals(details[85])/parts);
		censusVO.setFemaleALPercentage(roundTwoDecimals(details[86])/parts);
		censusVO.setPopHHPercentage(roundTwoDecimals(details[87])/parts);
		censusVO.setMaleHHPercentage(roundTwoDecimals(details[88])/parts);
		censusVO.setFemaleHHPercentage(roundTwoDecimals(details[89])/parts);
		censusVO.setPopOWPercentage(roundTwoDecimals(details[90])/parts);
		censusVO.setMaleOWPercentage(roundTwoDecimals(details[91])/parts);
		censusVO.setFemaleOWPercentage(roundTwoDecimals(details[92])/parts);
		censusVO.setMainMargCLPopulation((Long)details[93]);
		censusVO.setMainMargCLMale((Long)details[94]);
		censusVO.setMainMargCLFemale((Long)details[95]);
		censusVO.setMainMargALPopulation((Long)details[96]);
		censusVO.setMainMargALMale((Long)details[97]);
		censusVO.setMainMargALFemale((Long)details[98]);
		censusVO.setMainMargHHPopulation((Long)details[99]);
		censusVO.setMainMargHHMale((Long)details[100]);
		censusVO.setMainMargHHFemale((Long)details[101]);
		censusVO.setMainMargOWPopulation((Long)details[102]);
		censusVO.setMainMargOWMale((Long)details[103]);
		censusVO.setMainMargOWFemale((Long)details[104]);
		censusVO.setWpr(roundTwoDecimals(details[105])/parts);
		
		return censusVO;
		}
		catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.setSumCompleteCensusDetailsToVO().... ");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	}
	
	public List<CensusVO> getCompleteCensusDetailsOfATownInAPartialTehsil(Long delimitationConstituencyId,Long mandalId,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("Entered Into constituencyPageService.getCensusDetailsOfATowmInAPartialTehsil() Method .....");
		}
		//Here we are getting Latest towns for Delimitation Constituency
		List<Object[]> towns = getLatestTownsForATehsil(delimitationConstituencyId,mandalId);
		
		if(towns != null && towns.size() > 0)
		{
			List<CensusVO> townCensusVOList = new ArrayList<CensusVO>();
			for(Object[] param:towns)
			{
				//Here we are getting Census Details For a town
				CensusVO  censusVO = getCompleteCensusDetailsForATown(param,censusYear);
				
				//If census is not present to the town then it retuns null,that we are ommitting
				if(censusVO != null)
				townCensusVOList.add(censusVO);
			}
			
			return townCensusVOList;
		}
	}catch(Exception ex)
	{
		log.debug("Exception Occured In the constituencyPageService.getCensusDetailsOfATowmInAPartialTehsil().... ");
		log.error("Exception raised please check the log for details "+ex);
		return null;
	}
		return null;
		
		
	}
	/**
	 * This Method will give Census Details  for A Town when we pass Object Array which contains Town Id and Census Year.
	 * @author kamalakar Dandu
	 * @param Object[] 
	 * @param Long censusYear
	 * @return CensusVO
	 * 
	 */
	public CensusVO getCompleteCensusDetailsForATown(Object[] params,Long censusYear)
	{
		try
		{
			if(log.isDebugEnabled()){
				log.debug("Entered Into constituencyPageService.getCompleteCensusDetailsForATown() Method .....");
			}	
			
			//we are setting the Location Details
			Long dctId = (Long)params[0];
			Long townshipId = (Long)params[1];
			boolean isPartial  = params[2].toString().equalsIgnoreCase("1");
			
			Township township = townshipDAO.get(townshipId);
			Long districtId =  township.getTehsil().getDistrict().getDistrictId();
			Long stateId =  township.getTehsil().getDistrict().getState().getStateId();
			
			if(isPartial)
			{
				if(log.isDebugEnabled()){
					log.debug("Entered isPartial Block of constituencyPageService.getCensusDetailsForATown() Method .....");
				}
				
				//Here we are getting String as return type which contains comma separeted ward ids 
				String wardIdStr = getWardIdsOfAPartialTownship(dctId);
				int noOfParts = findNoOfParts(wardIdStr);
				if((wardIdStr != null) && (!wardIdStr.isEmpty()))
				{
				//Here we are getting census details of the total wards group By   
				List<Object[]> wardCensus = censusDAO.findCompleteCensusDetailsForAPartialTown(stateId,districtId,censusYear,IConstants.WARD,wardIdStr);
					
				if(wardCensus != null && wardCensus.size() > 0)
				{
					return setSumCompleteCensusDetailsToVO(wardCensus.get(0),noOfParts);
				}
				
			   }
			}
			else
			{
				if(log.isDebugEnabled()){
					log.debug("The Town is full in the Constituency and Entered to partial false Block of constituencyPageService.getCensusDetailsForATown() Method .....");
				}
				//Here we are getting census details of the Township
				List<Object[]> list = censusDAO.findTownshipWiseCompleteCensusDetails(stateId,districtId,townshipId,censusYear,IConstants.TOWN);
				if(list != null && list.size() > 0)
				{
				   return  setCompleteCensusDetailsToVO(list.get(0));
				}
		  }
			
		  return null;
		}catch(Exception ex)
		{
			log.debug("Exception Occured In the constituencyPageService.getCompleteCensusDetailsForATown().... ");
			log.error("Exception raised please check the log for details "+ex);
			return null;
		}
	}
	
	/**
	 * 
	 * This Method add List of census Details to a single VO
	 * 
	 * @author kamalakar Dandu
	 * @param List<CensusVO>
	 * @return CensusVO
	 * 
	 *  
	 */
	public CensusVO addCompleteCensusDataToSingleVO(List<CensusVO> censusVOList)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.addCompleteCensusDataToSingleVO() method ......");
		}
		if(censusVOList.size() == 0)
			return null;
		else
		{
			CensusVO censusVO = new CensusVO();
			
			int count = 0;		
			for(CensusVO cenVO:censusVOList)
			{
				++count;
				censusVO.setHouseHolds((censusVO.getHouseHolds()== null ? 0:censusVO.getHouseHolds())+ checkForNull(cenVO.getHouseHolds()));
				censusVO.setTotalPopulation((censusVO.getTotalPopulation()== null ? 0:censusVO.getTotalPopulation())+ checkForNull(cenVO.getTotalPopulation()));
				censusVO.setMalePopulation((censusVO.getMalePopulation()== null ? 0:censusVO.getMalePopulation())+ checkForNull(cenVO.getMalePopulation()));
				censusVO.setFemalePopulation((censusVO.getFemalePopulation()== null ? 0:censusVO.getFemalePopulation())+ checkForNull(cenVO.getFemalePopulation()));
				censusVO.setPopulationUnderSix((censusVO.getPopulationUnderSix()== null ? 0:censusVO.getPopulationUnderSix())+ checkForNull(cenVO.getPopulationUnderSix()));
				censusVO.setMaleUnderSix((censusVO.getMaleUnderSix()== null ? 0:censusVO.getMaleUnderSix())+ checkForNull(cenVO.getMaleUnderSix()));
				censusVO.setFemaleUnderSix((censusVO.getFemaleUnderSix()== null ? 0:censusVO.getFemaleUnderSix())+ checkForNull(cenVO.getFemaleUnderSix()));
				censusVO.setPopulationSC((censusVO.getPopulationSC()== null ? 0:censusVO.getPopulationSC())+checkForNull(cenVO.getPopulationSC()));
				censusVO.setMaleSC((censusVO.getMaleSC()== null ? 0:censusVO.getMaleSC())+ checkForNull(cenVO.getMaleSC()));
				censusVO.setFemaleSC((censusVO.getFemaleSC()== null ? 0:censusVO.getFemaleSC())+ checkForNull(cenVO.getFemaleSC()));
				censusVO.setPopulationST((censusVO.getPopulationST()== null ? 0:censusVO.getPopulationST())+ checkForNull(cenVO.getPopulationST()));
				censusVO.setMaleST((censusVO.getMaleST()== null ? 0:censusVO.getMaleST())+ checkForNull(cenVO.getMaleST()));
				censusVO.setFemaleST((censusVO.getFemaleST()== null ? 0:censusVO.getFemaleST())+ checkForNull(cenVO.getFemaleST()));
				censusVO.setPopulationLiterates((censusVO.getPopulationLiterates()== null ? 0:censusVO.getPopulationLiterates())+ checkForNull(cenVO.getPopulationLiterates()));
				censusVO.setMaleLiterates((censusVO.getMaleLiterates()== null ? 0:censusVO.getMaleLiterates())+ checkForNull(cenVO.getMaleLiterates()));
				censusVO.setFemaleLiterates((censusVO.getFemaleLiterates()== null ? 0:censusVO.getFemaleLiterates())+ checkForNull(cenVO.getFemaleLiterates()));
				censusVO.setPopulationIlliterates((censusVO.getPopulationIlliterates()== null ? 0:censusVO.getPopulationIlliterates())+ checkForNull(cenVO.getPopulationIlliterates()));
				censusVO.setMaleIlliterates((censusVO.getMaleIlliterates()== null ? 0:censusVO.getMaleIlliterates())+ checkForNull(cenVO.getMaleIlliterates()));
				censusVO.setFemaleIlliterates((censusVO.getFemaleIlliterates()== null ? 0:censusVO.getFemaleIlliterates())+ checkForNull(cenVO.getFemaleIlliterates()));
				censusVO.setWorkingPopulation((censusVO.getWorkingPopulation()== null ? 0:censusVO.getWorkingPopulation())+ checkForNull(cenVO.getWorkingPopulation()));
				censusVO.setWorkingMale((censusVO.getWorkingMale()== null ? 0:censusVO.getWorkingMale())+ checkForNull(cenVO.getWorkingMale()));
				censusVO.setWorkingFemale((censusVO.getWorkingFemale()== null ? 0:censusVO.getWorkingFemale())+ checkForNull(cenVO.getWorkingFemale()));
				censusVO.setMainWorkPopulation((censusVO.getMainWorkPopulation()== null ? 0:censusVO.getMainWorkPopulation())+ checkForNull(cenVO.getMainWorkPopulation()));
				censusVO.setMainWorkMale((censusVO.getMainWorkMale()== null ? 0:censusVO.getMainWorkMale())+ checkForNull(cenVO.getMainWorkMale()));
				censusVO.setMainWorkFemale((censusVO.getMainWorkFemale()== null ? 0:censusVO.getMainWorkFemale())+ checkForNull(cenVO.getMainWorkFemale()));
				censusVO.setMainCLPopulation((censusVO.getMainCLPopulation()== null ? 0:censusVO.getMainCLPopulation())+ checkForNull(cenVO.getMainCLPopulation()));
				censusVO.setMainCLMale((censusVO.getMainCLMale()== null ? 0:censusVO.getMainCLMale())+ checkForNull(cenVO.getMainCLMale()));
				censusVO.setMainCLFemale((censusVO.getMainCLFemale()== null ? 0:censusVO.getMainCLFemale())+ checkForNull(cenVO.getMainCLFemale()));
				censusVO.setMainALPopulation((censusVO.getMainALPopulation()== null ? 0:censusVO.getMainALPopulation())+ checkForNull(cenVO.getMainALPopulation()));
				censusVO.setMainALMale((censusVO.getMainALMale()== null ? 0:censusVO.getMainALMale())+ checkForNull(cenVO.getMainALMale()));
				censusVO.setMainALFemale((censusVO.getMainALFemale()== null ? 0:censusVO.getMainALFemale())+ checkForNull(cenVO.getMainALFemale()));
				censusVO.setMainHHPopulation((censusVO.getMainHHPopulation()== null ? 0:censusVO.getMainHHPopulation())+ checkForNull(cenVO.getMainHHPopulation()));
				censusVO.setMainHHMale((censusVO.getMainHHMale()== null ? 0:censusVO.getMainHHMale())+ checkForNull(cenVO.getMainHHMale()));
				censusVO.setMainHHFemale((censusVO.getMainHHFemale()== null ? 0:censusVO.getMainHHFemale())+ checkForNull(cenVO.getMainHHFemale()));
				censusVO.setMainOTPopulation((censusVO.getMainOTPopulation()== null ? 0:censusVO.getMainOTPopulation())+ checkForNull(cenVO.getMainOTPopulation()));
				censusVO.setMainOTMale((censusVO.getMainOTMale()== null ? 0:censusVO.getMainOTMale())+ checkForNull(cenVO.getMainOTMale()));
				censusVO.setMainOTFemale((censusVO.getMainOTFemale()== null ? 0:censusVO.getMainOTFemale())+ checkForNull(cenVO.getMainOTFemale()));
				censusVO.setMargWorkPopulation((censusVO.getMargWorkPopulation()== null ? 0:censusVO.getMargWorkPopulation())+ checkForNull(cenVO.getMargWorkPopulation()));
				censusVO.setMargWorkMale((censusVO.getMargWorkMale()== null ? 0:censusVO.getMargWorkMale())+ checkForNull(cenVO.getMargWorkMale()));
				censusVO.setMargWorkFemale((censusVO.getMargWorkFemale()== null ? 0:censusVO.getMargWorkFemale())+ checkForNull(cenVO.getMargWorkFemale()));
				censusVO.setMargCLPopulation((censusVO.getMargCLPopulation()== null ? 0:censusVO.getMargCLPopulation())+ checkForNull(cenVO.getMargCLPopulation()));
				censusVO.setMargCLMale((censusVO.getMargCLMale()== null ? 0:censusVO.getMargCLMale())+ checkForNull(cenVO.getMargCLMale()));
				censusVO.setMargCLFemale((censusVO.getMargCLFemale()== null ? 0:censusVO.getMargCLFemale())+ checkForNull(cenVO.getMargCLFemale()));
				censusVO.setMargALPopulation((censusVO.getMargALPopulation()== null ? 0:censusVO.getMargALPopulation())+ checkForNull(cenVO.getMargALPopulation()));
				censusVO.setMargALMale((censusVO.getMargALMale()== null ? 0:censusVO.getMargALMale())+ checkForNull(cenVO.getMargALMale()));
				censusVO.setMargALFemale((censusVO.getMargALFemale()== null ? 0:censusVO.getMargALFemale())+ checkForNull(cenVO.getMargALFemale()));
				censusVO.setMargHHPopulation((censusVO.getMargHHPopulation()== null ? 0:censusVO.getMargHHPopulation())+ checkForNull(cenVO.getMargHHPopulation()));
				censusVO.setMargHHMale((censusVO.getMargHHMale()== null ? 0:censusVO.getMargHHMale())+ checkForNull(cenVO.getMargHHMale()));
				censusVO.setMargHHFemale((censusVO.getMargHHFemale()== null ? 0:censusVO.getMargHHFemale())+ checkForNull(cenVO.getMargHHFemale()));
				censusVO.setMargOTPopulation((censusVO.getMargOTPopulation()== null ? 0:censusVO.getMargOTPopulation())+ checkForNull(cenVO.getMargOTPopulation()));
				censusVO.setMargOTMale((censusVO.getMargOTMale()== null ? 0:censusVO.getMargOTMale())+ checkForNull(cenVO.getMargOTMale()));
				censusVO.setMargOTFemale((censusVO.getMargOTFemale()== null ? 0:censusVO.getMargOTFemale())+ checkForNull(cenVO.getMargOTFemale()));
				censusVO.setNonWorkingPopulation((censusVO.getNonWorkingPopulation()== null ? 0:censusVO.getNonWorkingPopulation())+ checkForNull(cenVO.getNonWorkingPopulation()));
				censusVO.setNonWorkingMale((censusVO.getNonWorkingMale()== null ? 0:censusVO.getNonWorkingMale())+ checkForNull(cenVO.getNonWorkingMale()));
				censusVO.setNonWorkingFemale((censusVO.getNonWorkingFemale()== null ? 0:censusVO.getNonWorkingFemale())+ checkForNull(cenVO.getNonWorkingFemale()));
				censusVO.setSexRatio((censusVO.getSexRatio()== null ? 0.0:censusVO.getSexRatio())+ checkForNull(cenVO.getSexRatio()));
				censusVO.setSexRatioSC((censusVO.getSexRatioSC()== null ? 0.0:censusVO.getSexRatioSC())+ checkForNull(cenVO.getSexRatioSC()));
				censusVO.setSexRatioST((censusVO.getSexRatioST()== null ? 0.0:censusVO.getSexRatioST())+ checkForNull(cenVO.getSexRatioST()));
				censusVO.setHouseHoldsSize((censusVO.getHouseHoldsSize()== null ? 0.0:censusVO.getHouseHoldsSize())+ checkForNull(cenVO.getHouseHoldsSize()));
				censusVO.setPercentageSC((censusVO.getPercentageSC()== null ? 0.0:censusVO.getPercentageSC())+ checkForNull(cenVO.getPercentageSC()));
				censusVO.setPercentageST((censusVO.getPercentageST()== null ? 0.0:censusVO.getPercentageST())+ checkForNull(cenVO.getPercentageST()));
				censusVO.setSexRatioUnderSix((censusVO.getSexRatioUnderSix()== null ? 0.0:censusVO.getSexRatioUnderSix())+ checkForNull(cenVO.getSexRatioUnderSix()));
				censusVO.setMaleLiteratureRate((censusVO.getMaleLiteratureRate()== null ? 0.0:censusVO.getMaleLiteratureRate())+ checkForNull(cenVO.getMaleLiteratureRate()));
				censusVO.setFemaleLiteratureRate((censusVO.getFemaleLiteratureRate()== null ? 0.0:censusVO.getFemaleLiteratureRate())+ checkForNull(cenVO.getFemaleLiteratureRate()));
				censusVO.setGenderGap((censusVO.getGenderGap()== null ? 0.0:censusVO.getGenderGap())+ checkForNull(cenVO.getGenderGap()));
				censusVO.setPopLiteraturePercentage((censusVO.getPopLiteraturePercentage()== null ? 0.0:censusVO.getPopLiteraturePercentage())+ checkForNull(cenVO.getPopLiteraturePercentage()));
				censusVO.setMaleLiteraturePercentage((censusVO.getMaleLiteraturePercentage()== null ? 0.0:censusVO.getMaleLiteraturePercentage())+ checkForNull(cenVO.getMaleLiteraturePercentage()));
				censusVO.setFemaleLiteraturePercentage((censusVO.getFemaleLiteraturePercentage()== null ? 0.0:censusVO.getFemaleLiteraturePercentage())+ checkForNull(cenVO.getFemaleLiteraturePercentage()));
				censusVO.setTotalPopPercentage((censusVO.getTotalPopPercentage()== null ? 0.0:censusVO.getTotalPopPercentage())+ checkForNull(cenVO.getTotalPopPercentage()));
				censusVO.setTotalWorkingPopPercentage((censusVO.getTotalWorkingPopPercentage()== null ? 0.0:censusVO.getTotalWorkingPopPercentage())+ checkForNull(cenVO.getTotalWorkingPopPercentage()));
				censusVO.setTotalWorkingMalePercentage((censusVO.getTotalWorkingMalePercentage()== null ? 0.0:censusVO.getTotalWorkingMalePercentage())+ checkForNull(cenVO.getTotalWorkingMalePercentage()));
				censusVO.setTotalWorkingFemalePercentage((censusVO.getTotalWorkingFemalePercentage()== null ? 0.0:censusVO.getTotalWorkingFemalePercentage())+ checkForNull(cenVO.getTotalWorkingFemalePercentage()));
				censusVO.setTotalMainPopPercentage((censusVO.getTotalMainPopPercentage()== null ? 0.0:censusVO.getTotalMainPopPercentage())+ checkForNull(cenVO.getTotalMainPopPercentage()));
				censusVO.setTotalMainMalePercentage((censusVO.getTotalMainMalePercentage()== null ? 0.0:censusVO.getTotalMainMalePercentage())+ checkForNull(cenVO.getTotalMainMalePercentage()));
				censusVO.setTotalMainFemalePercentage((censusVO.getTotalMainFemalePercentage()== null ? 0.0:censusVO.getTotalMainFemalePercentage())+ checkForNull(cenVO.getTotalMainFemalePercentage()));
				censusVO.setTotalMargPopPercentage((censusVO.getTotalMargPopPercentage()== null ? 0.0:censusVO.getTotalMargPopPercentage())+ checkForNull(cenVO.getTotalMargPopPercentage()));
				censusVO.setTotalMargMalePercentage((censusVO.getTotalMargMalePercentage()== null ? 0.0:censusVO.getTotalMargMalePercentage())+ checkForNull(cenVO.getTotalMargMalePercentage()));
				censusVO.setTotalMargFemalePercentage((censusVO.getTotalMargFemalePercentage()== null ? 0.0:censusVO.getTotalMargFemalePercentage())+ checkForNull(cenVO.getTotalMargFemalePercentage()));
				censusVO.setNonWorkingPopPercentage((censusVO.getNonWorkingPopPercentage()== null ? 0.0:censusVO.getNonWorkingPopPercentage())+ checkForNull(cenVO.getNonWorkingPopPercentage()));
				censusVO.setNonWorkingMalePercentage((censusVO.getNonWorkingMalePercentage()== null ? 0.0:censusVO.getNonWorkingMalePercentage())+ checkForNull(cenVO.getNonWorkingMalePercentage()));
				censusVO.setNonWorkingFemalePercentage((censusVO.getNonWorkingFemalePercentage()== null ? 0.0:censusVO.getNonWorkingFemalePercentage())+ checkForNull(cenVO.getNonWorkingFemalePercentage()));
				censusVO.setPopCLPercentage((censusVO.getPopCLPercentage()== null ? 0.0:censusVO.getPopCLPercentage())+ checkForNull(cenVO.getPopCLPercentage()));
				censusVO.setMaleCLPercentage((censusVO.getMaleCLPercentage()== null ? 0.0:censusVO.getMaleCLPercentage())+ checkForNull(cenVO.getMaleCLPercentage()));
				censusVO.setFemaleCLPercentage((censusVO.getFemaleCLPercentage()== null ? 0.0:censusVO.getFemaleCLPercentage())+ checkForNull(cenVO.getFemaleCLPercentage()));
				censusVO.setPopALPercentage((censusVO.getPopALPercentage()== null ? 0.0:censusVO.getPopALPercentage())+ checkForNull(cenVO.getPopALPercentage()));
				censusVO.setMaleALPercentage((censusVO.getMaleALPercentage()== null ? 0.0:censusVO.getMaleALPercentage())+ checkForNull(cenVO.getMaleALPercentage()));
				censusVO.setFemaleALPercentage((censusVO.getFemaleALPercentage()== null ? 0.0:censusVO.getFemaleALPercentage())+ checkForNull(cenVO.getFemaleALPercentage()));
				censusVO.setPopHHPercentage((censusVO.getPopHHPercentage()== null ? 0.0:censusVO.getPopHHPercentage())+ checkForNull(cenVO.getPopHHPercentage()));
				censusVO.setMaleHHPercentage((censusVO.getMaleHHPercentage()== null ? 0.0:censusVO.getMaleHHPercentage())+ checkForNull(cenVO.getMaleHHPercentage()));
				censusVO.setFemaleHHPercentage((censusVO.getFemaleHHPercentage()== null ? 0.0:censusVO.getFemaleHHPercentage())+ checkForNull(cenVO.getFemaleHHPercentage()));
				censusVO.setPopOWPercentage((censusVO.getPopOWPercentage()== null ? 0.0:censusVO.getPopOWPercentage())+ checkForNull(cenVO.getPopOWPercentage()));
				censusVO.setMaleOWPercentage((censusVO.getMaleOWPercentage()== null ? 0.0:censusVO.getMaleOWPercentage())+ checkForNull(cenVO.getMaleOWPercentage()));
				censusVO.setFemaleOWPercentage((censusVO.getFemaleOWPercentage()== null ? 0.0:censusVO.getFemaleOWPercentage())+ checkForNull(cenVO.getFemaleOWPercentage()));
				censusVO.setMainMargCLPopulation((censusVO.getMainMargCLPopulation()== null ? 0:censusVO.getMainMargCLPopulation())+ checkForNull(cenVO.getMainMargCLPopulation()));
				censusVO.setMainMargCLMale((censusVO.getMainMargCLMale()== null ? 0:censusVO.getMainMargCLMale())+ checkForNull(cenVO.getMainMargCLMale()));
				censusVO.setMainMargCLFemale((censusVO.getMainMargCLFemale()== null ? 0:censusVO.getMainMargCLFemale())+ checkForNull(cenVO.getMainMargCLFemale()));
				censusVO.setMainMargALPopulation((censusVO.getMainMargALPopulation()== null ? 0:censusVO.getMainMargALPopulation())+ checkForNull(cenVO.getMainMargALPopulation()));
				censusVO.setMainMargALMale((censusVO.getMainMargALMale()== null ? 0:censusVO.getMainMargALMale())+ checkForNull(cenVO.getMainMargALMale()));
				censusVO.setMainMargALFemale((censusVO.getMainMargALFemale()== null ? 0:censusVO.getMainMargALFemale())+ checkForNull(cenVO.getMainMargALFemale()));
				censusVO.setMainMargHHPopulation((censusVO.getMainMargHHPopulation()== null ? 0:censusVO.getMainMargHHPopulation())+ checkForNull(cenVO.getMainMargHHPopulation()));
				censusVO.setMainMargHHMale((censusVO.getMainMargHHMale()== null ? 0:censusVO.getMainMargHHMale())+ checkForNull(cenVO.getMainMargHHMale()));
				censusVO.setMainMargHHFemale((censusVO.getMainMargHHFemale()== null ? 0:censusVO.getMainMargHHFemale())+ checkForNull(cenVO.getMainMargHHFemale()));
				censusVO.setMainMargOWPopulation((censusVO.getMainMargOWPopulation()== null ? 0:censusVO.getMainMargOWPopulation())+ checkForNull(cenVO.getMainMargOWPopulation()));
				censusVO.setMainMargOWMale((censusVO.getMainMargOWMale()== null ? 0:censusVO.getMainMargOWMale())+ checkForNull(cenVO.getMainMargOWMale()));
				censusVO.setMainMargOWFemale((censusVO.getMainMargOWFemale()== null ? 0:censusVO.getMainMargOWFemale())+ checkForNull(cenVO.getMainMargOWFemale()));
				censusVO.setWpr((censusVO.getWpr()== null ? 0.0:censusVO.getWpr())+ checkForNull(cenVO.getWpr()));
				
			}
			return setPercentageOfCensusVO(censusVO,count);		
		}
	}catch(Exception e){
		  log.debug("Exception Occured In the constituencyPageService.addCompleteCensusDataToSingleVO() method .........");
		  log.error("Exception raised please check the log for details "+e);
		  return null;
	  }
	}
	
	/**
	 * This Method sets the percentage of Census Details of a Constituency.
	 * @author kamalakar Dandu
	 * @param CensusVO censusVO
	 * @param int count
	 * @return CensusVO
	 */
	public CensusVO setPercentageOfCensusVO(CensusVO censusVO,int count)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.setPercentageOfCensusVO() method ......");
		}
		censusVO.setSexRatio(censusVO.getSexRatio()/count);
		censusVO.setSexRatioSC(censusVO.getSexRatioSC()/count);
		censusVO.setSexRatioST(censusVO.getSexRatioST()/count);
		censusVO.setHouseHoldsSize(censusVO.getHouseHoldsSize()/count);
		censusVO.setPercentageSC(roundTwoDecimals(censusVO.getPopulationSC()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setPercentageST(roundTwoDecimals(censusVO.getPopulationST()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setSexRatioUnderSix(censusVO.getSexRatioUnderSix()/count);
		censusVO.setMaleLiteratureRate(censusVO.getMaleLiteratureRate()/count);
		censusVO.setFemaleLiteratureRate(censusVO.getFemaleLiteratureRate()/count);
		censusVO.setGenderGap(censusVO.getGenderGap()/count);
		censusVO.setPopLiteraturePercentage(roundTwoDecimals(censusVO.getPopulationLiterates()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setMaleLiteraturePercentage(roundTwoDecimals(censusVO.getMaleLiterates()*(100.0)/censusVO.getMalePopulation()));
		censusVO.setFemaleLiteraturePercentage(roundTwoDecimals(censusVO.getFemaleLiterates()*(100.0)/censusVO.getFemalePopulation()));
		censusVO.setTotalPopPercentage(roundTwoDecimals(censusVO.getTotalPopulation()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setTotalWorkingPopPercentage(roundTwoDecimals(censusVO.getWorkingPopulation()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setTotalWorkingMalePercentage(roundTwoDecimals(censusVO.getWorkingMale()*(100.0)/censusVO.getMalePopulation()));
		censusVO.setTotalWorkingFemalePercentage(roundTwoDecimals(censusVO.getWorkingFemale()*(100.0)/censusVO.getFemalePopulation()));
		censusVO.setTotalMainPopPercentage(roundTwoDecimals(censusVO.getMainWorkPopulation()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setTotalMainMalePercentage(roundTwoDecimals(censusVO.getMainWorkMale()*(100.0)/censusVO.getMalePopulation()));
		censusVO.setTotalMainFemalePercentage(roundTwoDecimals(censusVO.getMainWorkFemale()*(100.0)/censusVO.getFemalePopulation()));
		censusVO.setTotalMargPopPercentage(censusVO.getTotalMargPopPercentage()/count);
		censusVO.setTotalMargMalePercentage(censusVO.getTotalMargMalePercentage()/count);
		censusVO.setTotalMargFemalePercentage(censusVO.getTotalMargFemalePercentage()/count);
		censusVO.setNonWorkingPopPercentage(roundTwoDecimals(censusVO.getNonWorkingPopulation()*(100.0)/censusVO.getTotalPopulation()));
		censusVO.setNonWorkingMalePercentage(roundTwoDecimals(censusVO.getNonWorkingMale()*(100.0)/censusVO.getMalePopulation()));
		censusVO.setNonWorkingFemalePercentage(roundTwoDecimals(censusVO.getNonWorkingFemale()*(100.0)/censusVO.getFemalePopulation()));
		censusVO.setPopCLPercentage(censusVO.getPopCLPercentage()/count);
		censusVO.setMaleCLPercentage(censusVO.getMaleCLPercentage()/count);
		censusVO.setFemaleCLPercentage(censusVO.getFemaleCLPercentage()/count);
		censusVO.setPopALPercentage(censusVO.getPopALPercentage()/count);
		censusVO.setMaleALPercentage(censusVO.getMaleALPercentage()/count);
		censusVO.setFemaleALPercentage(censusVO.getFemaleALPercentage()/count);
		censusVO.setPopHHPercentage(censusVO.getPopHHPercentage()/count);
		censusVO.setMaleHHPercentage(censusVO.getMaleHHPercentage()/count);
		censusVO.setFemaleHHPercentage(censusVO.getFemaleHHPercentage()/count);
		censusVO.setPopOWPercentage(censusVO.getPopOWPercentage()/count);
		censusVO.setMaleOWPercentage(censusVO.getMaleOWPercentage()/count);
		censusVO.setFemaleOWPercentage(censusVO.getFemaleOWPercentage()/count);
		censusVO.setWpr(censusVO.getWpr()/count);
		
		return censusVO;
		
		}catch(Exception e){
			  log.debug("Exception Occured In the constituencyPageService.setPercentageOfCensusVO() method .........");
			  log.error("Exception raised please check the log for details "+e);
			  return null;
	  }
	}
	
	/**
	 * This Method Save the Census Data to Constituency_Census_Details
	 * @author kamalakar Dandu
	 * @param CensusVO censusVO
	 * @param Long constituencyId
	 * @param Long censusYear
	 * @return CensusVO
	 */
	
	public String saveCensusToConstituencyCensusDetails(final CensusVO censusVO,final Long constituencyId,final Long censusYear,final String update)
	{
			transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status)
				{
					ConstituencyCensusDetails constituencyCensusDetails = null;
					
					if(update.equalsIgnoreCase(IConstants.TRUE) && checkForConstituencyExistance(constituencyId))
						constituencyCensusDetails = constituencyCensusDetailsDAO.getCensusConstituencyByConstituencyId(constituencyId).get(0);
					
					else
						constituencyCensusDetails = new ConstituencyCensusDetails();
					
					Long stateId = constituencyDAO.getStateIdByConstituencyId(constituencyId).get(0);
								
					try{
						constituencyCensusDetails.setStateId(stateId);
						constituencyCensusDetails.setConstituencyId(constituencyId);
						constituencyCensusDetails.setLevel(IConstants.CONSTITUENCY);
						constituencyCensusDetails.setYear(censusYear);
						constituencyCensusDetails.setTru(IConstants.TOTAL);
						constituencyCensusDetails.setHouseHolds(censusVO.getHouseHolds());
						constituencyCensusDetails.setTotalPopulation(censusVO.getTotalPopulation());
						constituencyCensusDetails.setTotalMalePopulation(censusVO.getMalePopulation());
						constituencyCensusDetails.setTotalFemalePopulation(censusVO.getFemalePopulation());
						constituencyCensusDetails.setPopulationUnderSix(censusVO.getPopulationUnderSix());
						constituencyCensusDetails.setMaleUnderSix(censusVO.getMaleUnderSix());
						constituencyCensusDetails.setFemaleUnderSix(censusVO.getFemaleUnderSix());
						constituencyCensusDetails.setPopulationSC(censusVO.getPopulationSC());
						constituencyCensusDetails.setMaleSC(censusVO.getMaleSC());
						constituencyCensusDetails.setFemaleSC(censusVO.getFemaleSC());
						constituencyCensusDetails.setPopulationST(censusVO.getPopulationST());
						constituencyCensusDetails.setMaleST(censusVO.getMaleST());
						constituencyCensusDetails.setFemaleST(censusVO.getFemaleST());
						constituencyCensusDetails.setPopulationLiterates(censusVO.getPopulationLiterates());
						constituencyCensusDetails.setMaleLiterates(censusVO.getMaleLiterates());
						constituencyCensusDetails.setFemaleLiterates(censusVO.getFemaleLiterates());
						constituencyCensusDetails.setPopulationIlliterates(censusVO.getPopulationIlliterates());
						constituencyCensusDetails.setMaleIlliterates(censusVO.getMaleIlliterates());
						constituencyCensusDetails.setFemaleIlliterates(censusVO.getFemaleIlliterates());
						constituencyCensusDetails.setWorkingPopulation(censusVO.getWorkingPopulation());
						constituencyCensusDetails.setWorkingMale(censusVO.getWorkingMale());
						constituencyCensusDetails.setWorkingFemale(censusVO.getWorkingFemale());
						constituencyCensusDetails.setMainWorkPopulation(censusVO.getMainWorkPopulation());
						constituencyCensusDetails.setMainWorkMale(censusVO.getMainWorkMale());
						constituencyCensusDetails.setMainWorkFemale(censusVO.getMainWorkFemale());
						constituencyCensusDetails.setMainCLPopulation(censusVO.getMainCLPopulation());
						constituencyCensusDetails.setMainCLMale(censusVO.getMainCLMale());
						constituencyCensusDetails.setMainCLFemale(censusVO.getMainCLFemale());
						constituencyCensusDetails.setMainALPopulation(censusVO.getMainALPopulation());
						constituencyCensusDetails.setMainALMale(censusVO.getMainALMale());
						constituencyCensusDetails.setMainALFemale(censusVO.getMainALFemale());
						constituencyCensusDetails.setMainHHPopulation(censusVO.getMainHHPopulation());
						constituencyCensusDetails.setMainHHMale(censusVO.getMainHHMale());
						constituencyCensusDetails.setMainHHFemale(censusVO.getMainHHFemale());
						constituencyCensusDetails.setMainOTPopulation(censusVO.getMainOTPopulation());
						constituencyCensusDetails.setMainOTMale(censusVO.getMainOTMale());
						constituencyCensusDetails.setMainOTFemale(censusVO.getMainOTFemale());
						constituencyCensusDetails.setMargWorkPopulation(censusVO.getMargWorkPopulation());
						constituencyCensusDetails.setMargWorkMale(censusVO.getMargWorkMale());
						constituencyCensusDetails.setMargWorkFemale(censusVO.getMargWorkFemale());
						constituencyCensusDetails.setMargCLPopulation(censusVO.getMargCLPopulation());
						constituencyCensusDetails.setMargCLMale(censusVO.getMargCLMale());
						constituencyCensusDetails.setMargCLFemale(censusVO.getMargCLFemale());
						constituencyCensusDetails.setMargALPopulation(censusVO.getMargALPopulation());
						constituencyCensusDetails.setMargALMale(censusVO.getMargALMale());
						constituencyCensusDetails.setMargALFemale(censusVO.getMargALFemale());
						constituencyCensusDetails.setMargHHPopulation(censusVO.getMargHHPopulation());
						constituencyCensusDetails.setMargHHMale(censusVO.getMargHHMale());
						constituencyCensusDetails.setMargHHFemale(censusVO.getMargHHFemale());
						constituencyCensusDetails.setMargOTPopulation(censusVO.getMargHHPopulation());
						constituencyCensusDetails.setMargOTMale(censusVO.getMargHHMale());
						constituencyCensusDetails.setMargOTFemale(censusVO.getMargHHFemale());
						constituencyCensusDetails.setNonWorkingPopulation(censusVO.getNonWorkingPopulation());
						constituencyCensusDetails.setNonWorkingMale(censusVO.getNonWorkingMale());
						constituencyCensusDetails.setNonWorkingFemale(censusVO.getNonWorkingFemale());
						constituencyCensusDetails.setSexRatio(censusVO.getSexRatio());
						constituencyCensusDetails.setSexRatioSC(censusVO.getSexRatioSC());
						constituencyCensusDetails.setSexRatioST(censusVO.getSexRatioST());
						constituencyCensusDetails.setSexRatioUnderSix(censusVO.getSexRatioUnderSix());
						constituencyCensusDetails.setHouseHoldsSize(censusVO.getHouseHoldsSize());
						constituencyCensusDetails.setPercentageSC(censusVO.getPercentageSC());
						constituencyCensusDetails.setPercentageST(censusVO.getPercentageST());
						constituencyCensusDetails.setMaleLiteratureRate(censusVO.getMaleLiteratureRate());
						constituencyCensusDetails.setFemaleLiteratureRate(censusVO.getFemaleLiteratureRate());
						constituencyCensusDetails.setGenderGap(censusVO.getGenderGap());
						constituencyCensusDetails.setPopLiteraturePercentage(censusVO.getPopLiteraturePercentage());
						constituencyCensusDetails.setMaleLiteraturePercentage(censusVO.getMaleLiteraturePercentage());
						constituencyCensusDetails.setFemaleLiteraturePercentage(censusVO.getFemaleLiteraturePercentage());
						constituencyCensusDetails.setTotalPopPercentage(censusVO.getTotalPopPercentage());
						constituencyCensusDetails.setTotalWorkingPopPercentage(censusVO.getTotalWorkingPopPercentage());
						constituencyCensusDetails.setTotalWorkingMalePercentage(censusVO.getTotalWorkingMalePercentage());
						constituencyCensusDetails.setTotalWorkingFemalePercentage(censusVO.getTotalWorkingFemalePercentage());
						constituencyCensusDetails.setTotalMainPopPercentage(censusVO.getTotalMainPopPercentage());
						constituencyCensusDetails.setTotalMainMalePercentage(censusVO.getTotalMainMalePercentage());
						constituencyCensusDetails.setTotalMainFemalePercentage(censusVO.getTotalMainFemalePercentage());
						constituencyCensusDetails.setTotalMargPopPercentage(censusVO.getTotalMargPopPercentage());
						constituencyCensusDetails.setTotalMargMalePercentage(censusVO.getTotalMargMalePercentage());
						constituencyCensusDetails.setTotalMargFemalePercentage(censusVO.getTotalMargFemalePercentage());
						constituencyCensusDetails.setNonWorkingPopPercentage(censusVO.getNonWorkingPopPercentage());
						constituencyCensusDetails.setNonWorkingMalePercentage(censusVO.getNonWorkingMalePercentage());
						constituencyCensusDetails.setNonWorkingFemalePercentage(censusVO.getNonWorkingFemalePercentage());
						constituencyCensusDetails.setPopCLPercentage(censusVO.getPopCLPercentage());
						constituencyCensusDetails.setMaleCLPercentage(censusVO.getMaleCLPercentage());
						constituencyCensusDetails.setFemaleCLPercentage(censusVO.getFemaleCLPercentage());
						constituencyCensusDetails.setPopALPercentage(censusVO.getPopALPercentage());
						constituencyCensusDetails.setMaleALPercentage(censusVO.getMaleALPercentage());
						constituencyCensusDetails.setFemaleALPercentage(censusVO.getFemaleALPercentage());
						constituencyCensusDetails.setPopHHPercentage(censusVO.getPopHHPercentage());
						constituencyCensusDetails.setMaleHHPercentage(censusVO.getMaleHHPercentage());
						constituencyCensusDetails.setFemaleHHPercentage(censusVO.getFemaleHHPercentage());
						constituencyCensusDetails.setPopOWPercentage(censusVO.getPopOWPercentage());
						constituencyCensusDetails.setMaleOWPercentage(censusVO.getMaleOWPercentage());
						constituencyCensusDetails.setFemaleOWPercentage(censusVO.getFemaleOWPercentage());
						constituencyCensusDetails.setMainMargCLPopulation(censusVO.getMainMargCLPopulation());
						constituencyCensusDetails.setMainMargCLMale(censusVO.getMainMargCLMale());
						constituencyCensusDetails.setMainMargCLFemale(censusVO.getMainMargCLFemale());
						constituencyCensusDetails.setMainMargALPopulation(censusVO.getMainMargALPopulation());
						constituencyCensusDetails.setMainMargALMale(censusVO.getMainMargALMale());
						constituencyCensusDetails.setMainMargALFemale(censusVO.getMainMargALFemale());
						constituencyCensusDetails.setMainMargHHPopulation(censusVO.getMainMargHHPopulation());
						constituencyCensusDetails.setMainMargHHMale(censusVO.getMainMargHHMale());
						constituencyCensusDetails.setMainMargHHFemale(censusVO.getMainMargHHFemale());
						constituencyCensusDetails.setMainMargOWPopulation(censusVO.getMainMargOWPopulation());
						constituencyCensusDetails.setMainMargOWMale(censusVO.getMainMargOWMale());
						constituencyCensusDetails.setMainMargOWFemale(censusVO.getMainMargOWFemale());
						constituencyCensusDetails.setWpr(censusVO.getWpr());
						
						constituencyCensusDetails = constituencyCensusDetailsDAO.save(constituencyCensusDetails);
																		
					 }catch(Exception ex){
						log.error("Exception Raised :" + ex);
						return null;
					}
					return constituencyCensusDetails;
					
				}
			});
		return IConstants.SUCCESS;
    	}
	
	public CensusVO getCompleteCensusDetailsForAnAssemblyConstituency(Long constituencyId,Long delimitationYear,Long censusYear)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.getCompleteCensusDetailsForAnAssemblyConstituency() method ......");
		}
		
		List<Object> delimConstList = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyIDForCensus(constituencyId,delimitationYear);
		
		if(delimConstList != null && delimConstList.size() > 0 )
		{
			Long delimitationConstituencyId = (Long) delimConstList.get(0);
			
			List<CensusVO> censusVOList = new ArrayList<CensusVO>();
			
			List<Object[]> delimConMandals = delimitationConstituencyMandalDAO.getLatestMandalsForAConstituency(delimitationConstituencyId);
			
			if(delimConMandals != null && delimConMandals.size() > 0)
			{
				//Here we are getting census details to each tehsil in the Constituency.
				for(Object[] params:delimConMandals)
				{
					CensusVO  censusVO = getCompleteCensusDetailsForATehsil(params,censusYear,delimitationConstituencyId);
					
					//If census is not present to the Tehsil then it retuns null,that we are ommitting
					if(censusVO != null)
					censusVOList.add(censusVO);
					
					else
					{
						skippedMandals.add(new SelectOptionVO((Long)params[1],params[2].toString()));
					}
				}
			}
			
			if(censusVOList != null && censusVOList.size() > 0)
			{
				return addCompleteCensusDataToSingleVO(censusVOList);
			}
		}
		return null;
		}catch(Exception e){
			  log.debug("Exception Occured In the constituencyPageService.getCompleteCensusDetailsForAnAssemblyConstituency() method .........");
			  log.error("Exception raised please check the log for details "+e);
			  return null;
		  }
		
	}
	
	@SuppressWarnings("unchecked")
	public CensusVO mapConstituencyWiseCensusDetails(Long stateId,Long districtId,Long delimitationYear,Long censusYear,String mappingLevel,String update)
	{
		try
		{
		if(log.isDebugEnabled()){
			log.debug("In the constituencyPageService.mapConstituencyWiseCensusDetails() method ......");
		}
		
		List<Object[]> constituencyList = new ArrayList<Object[]>(0);
		CensusVO resultVO = new CensusVO();
		
		if(mappingLevel.equalsIgnoreCase(IConstants.STATE))
			constituencyList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInState(2l,stateId);
		
		else if(mappingLevel.equalsIgnoreCase(IConstants.DISTRICT) && districtId != null)
			constituencyList = delimitationConstituencyDAO.getLatestConstituenciesByElectionTypeInDistrict(2l,districtId);
		
		List<SelectOptionVO> exists = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> unmap = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> map = new ArrayList<SelectOptionVO>(0);
		
		for(Object[] details:constituencyList)
		{
			Long constituencyId     = (Long) details[0];
			String constituencyName = details[1].toString();
			
			boolean isExists = checkForConstituencyExistance(constituencyId);
			
			if(isExists && update.equalsIgnoreCase(IConstants.FALSE))
			{
				if(log.isDebugEnabled()){
					log.debug(constituencyName+" is already existed in the Constituency census Deatails Table");
				}
				exists.add(new SelectOptionVO(constituencyId,constituencyName));
			}
			else
			{
				CensusVO censusMainVO = getCompleteCensusDetailsForAnAssemblyConstituency(constituencyId,delimitationYear,censusYear);
				
				if(censusMainVO != null)
				{
					String result = saveCensusToConstituencyCensusDetails(censusMainVO,constituencyId,censusYear,update);
				
					if(log.isDebugEnabled()){
					log.debug(constituencyName+" is saved in the Constituency census Deatails Table");
					}
					map.add(new SelectOptionVO(constituencyId,constituencyName));
				}
				else
				{
					log.debug(constituencyName+" is not saved in the Constituency census Deatails Table");
					unmap.add(new SelectOptionVO(constituencyId,constituencyName));
				}
			}
			
			resultVO.setExistedConstituencies(exists);
			resultVO.setMapeedConstituencies(map);
			resultVO.setUnMapeedConstituencies(unmap);
		}
		
		if(skippedMandals != null && skippedMandals.size() > 0 && log.isDebugEnabled())
		{
			for(SelectOptionVO result : skippedMandals)
			{
				log.debug("-------------------------  Skipped Mandals -----------------------");
				log.debug("== Mandal ID -- "+result.getId()+"     == Mandal Name--"+result.getName() +"  ");
				log.debug("------------------------------------------------------------------");
			}
			skippedMandals = new ArrayList<SelectOptionVO>(0);
		}
		
		return resultVO;
		
		}catch(Exception e){
			  log.debug("Exception Occured In the constituencyPageService.mapConstituencyWiseCensusDetails() method .........");
			  log.error("Exception raised please check the log for details "+e);
			  return null;
	   }
	}
	
	Double roundTwoDecimals(Double d) 
	{
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
    }
	
	Double roundTwoDecimals(Object obj) 
	{
		Double d = obj != null ? (Double)obj : 0.00;
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
    }
	
	Long checkForNull(Long value)
	{
		return value == null ? 0l : value;
	}
	
	Double checkForNull(Double value)
	{
		return value == null ? 0.0 : value;
	}

	/**
	 * Method to get complete candidate nomination details in a constituency for latest election
	 * @param constituencyId
	 * 
	 * @return ConstituencyNominationsVO
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyNominationsVO getCandidateNominationCompleteDetailsInConstituencyForLatestElection(
			Long constituencyId) {
		
		if(log.isDebugEnabled())
			log.debug("Entered To Get Candidate Nominations In a Constituency ..");
		
		ResultStatus rs = new ResultStatus();
		rs.setExceptionMsg("Executed Successfully ..");
		ConstituencyNominationsVO constituencyNominationsVO = null;
		try{
			
			//Check and Get Recent Election Happened In Constituency
			List electionDetails = constituencyElectionDAO.findAllElectionsByConstituencyId(constituencyId);
			if(electionDetails != null && electionDetails.size() > 0){
				Object[] values = (Object[])electionDetails.get(0);
				
			//call method to get candidate nominations
			constituencyNominationsVO = getCandidateNominations((Long)values[0]);
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			log.error("Exception Raised While Retrieving Nomination Details :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			constituencyNominationsVO.setResultstatus(rs);
		}
		
	 return constituencyNominationsVO;
	}
	
	/**
	 * Method that returns Candidate Nominations In A Constituency in an Election 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyNominationsVO getCandidateNominations(Long constiElecId){
		
		ConstituencyNominationsVO constituencyNominationsVO = new ConstituencyNominationsVO();
		
		if(log.isDebugEnabled())
			log.debug("Entered To start DAO Call ..");
		
		List resultsList = nominationDAO.getCandidateNominationDetailsInAnElection(constiElecId);
		
		if(log.isDebugEnabled())
			log.debug("Entered To Set Data to VO .. ");
		
		List<CandidateInfoForConstituencyVO> candidateNominations = new ArrayList<CandidateInfoForConstituencyVO>();
		
		Iterator lstItr = resultsList.listIterator();
		while(lstItr.hasNext()){
			
			Object[] values = (Object[])lstItr.next();
			
			CandidateInfoForConstituencyVO detailsVO = new CandidateInfoForConstituencyVO();
			
			detailsVO.setCandidateId((Long)values[7]);
			detailsVO.setCandidateName((String)values[9]);
			detailsVO.setParty((String)values[2]);
			detailsVO.setGender((String)values[10]);
			detailsVO.setEducation((String)values[11]);
			
			Double assets = (Double)values[4];
			Double liabilities = (Double)values[5];
			
			if(assets != null && !assets.equals(0D))
				detailsVO.setAssets(new BigDecimal(assets));
			if(liabilities != null && !liabilities.equals(0D))
				detailsVO.setLiabilities(new BigDecimal(liabilities));
			
			candidateNominations.add(detailsVO);
			
		}
		
		//set candidate nominations list to main VO
		constituencyNominationsVO.setCandidateNominations(candidateNominations);
		constituencyNominationsVO.setConstituencyName((((Object[])resultsList.get(0))[12]).toString());
		constituencyNominationsVO.setElectionType((((Object[])resultsList.get(0))[13]).toString());
		constituencyNominationsVO.setElectionYear((((Object[])resultsList.get(0))[14]).toString());
			
		return constituencyNominationsVO;
	}
	
}