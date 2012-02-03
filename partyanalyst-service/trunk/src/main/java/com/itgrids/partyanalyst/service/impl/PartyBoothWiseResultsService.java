package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.ConstituencyWiseDataForMandalVO;
import com.itgrids.partyanalyst.dto.ConstituencyWisePartyInfoVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ElectionResultTypeComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.MandalVotingTrendsComparator;
import com.itgrids.partyanalyst.utils.PartyResultsVOComparator;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;

public class PartyBoothWiseResultsService implements IPartyBoothWiseResultsService{
	
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IElectionDAO electionDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IStaticDataService staticDataService;
	private ITehsilDAO tehsilDAO;
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	private static final Logger log = Logger.getLogger(PartyBoothWiseResultsService.class);
	
	public INominationDAO getNominationDAO() {
		
		return nominationDAO;
	}
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}
	
	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}
	
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}
	
	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}
	
	public IConstituencyElectionResultDAO getConstituencyElectionResultDAO() {
		return constituencyElectionResultDAO;
	}
	public void setConstituencyElectionResultDAO(
			IConstituencyElectionResultDAO constituencyElectionResultDAO) {
		this.constituencyElectionResultDAO = constituencyElectionResultDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear){
		System.out.println("In getBoothWiseResultsForParty::constituencyId, electionYear::"+constituencyId+","+electionYear);
		List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
		List<Nomination> nominations  = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, constituencyId, electionYear);
		BoothResultVO boothResultVO = null;
		for(Nomination nomination:nominations){
			List<BoothResultVO> boothResultVOs = new ArrayList<BoothResultVO>();
			PartyBoothPerformanceVO partyBoothPerformanceVO = new PartyBoothPerformanceVO();
			partyBoothPerformanceVO.setPartyName(nomination.getParty().getShortName());
			partyBoothPerformanceVO.setCandidateName(nomination.getCandidate().getLastname());
			partyBoothPerformanceVO.setConstituencyName(nomination.getConstituencyElection().getConstituency().getName());
			partyBoothPerformanceVO.setElectionType(nomination.getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType());
			partyBoothPerformanceVO.setElectionYear(nomination.getConstituencyElection().getElection().getElectionYear());
			partyBoothPerformanceVO.setVotesGained(nomination.getCandidateResult().getVotesEarned().intValue());
			partyBoothPerformanceVO.setTotalValidVotes(nomination.getConstituencyElection().getConstituencyElectionResult().getValidVotes().intValue());
			partyBoothPerformanceVO.setPercentage(nomination.getCandidateResult().getVotesPercengate());
        	partyBoothPerformanceVO.setTotalVotes(nomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotes().longValue());
			partyBoothPerformanceVO.setVotingPercentage(nomination.getConstituencyElection().getConstituencyElectionResult().getVotingPercentage());
			List<CandidateBoothResult> candidateboothResults = new ArrayList<CandidateBoothResult>(nomination.getCandidateBoothResults());
			System.out.println("In getBoothWiseResultsForParty::"+candidateboothResults.size());
			for(CandidateBoothResult candidateBoothResult:candidateboothResults){
				Booth booth =  candidateBoothResult.getBoothConstituencyElection().getBooth();
				int totalValidVotes = candidateBoothResult.getBoothConstituencyElection().getBoothResult().getValidVotes().intValue();
				int votesEarned = candidateBoothResult.getVotesEarned().intValue();
				int totalVoters = candidateBoothResult.getBoothConstituencyElection().getBooth().getTotalVoters().intValue();
				
				String percentage  = calculateVotesPercengate(new Double(totalValidVotes), new Double(votesEarned));	
				String pollPercent = calculateVotesPercengate(new Double(totalVoters),new Double(totalValidVotes));
				
				if(booth.getBoothLocalBodyWard() != null)
					boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getVillagesCovered(), 
							votesEarned, totalValidVotes, percentage, booth.getBoothLocalBodyWard().getLocalBodyWard().getLocalElectionBody().
							getName()+" "+booth.getBoothLocalBodyWard().getLocalBodyWard().getName(), false,pollPercent);
				else
					if(booth.getLocalBody() != null)
						boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getVillagesCovered(), 
								votesEarned, totalValidVotes, percentage, booth.getLocalBody().getName()+" "+
								booth.getLocalBody().getElectionType().getElectionType(), false,pollPercent);
				else
				if(booth.getTehsil() != null)
					boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getVillagesCovered(), 
							votesEarned, totalValidVotes, percentage, booth.getTehsil().getTehsilName(), false,pollPercent);
				else
					boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getVillagesCovered(), 
							votesEarned, totalValidVotes, percentage, "", true,pollPercent);
				boothResultVO.setTotalBoothVoters(totalVoters);
				boothResultVOs.add(boothResultVO);
			}
			partyBoothPerformanceVO.setBoothResults(boothResultVOs);
			boothResultsForParties.add(partyBoothPerformanceVO);
		}
		return boothResultsForParties;
	}
	
	private String calculateVotesPercengate(Double validVotes, Double votesEarned){
		BigDecimal percengate= new BigDecimal(0.0);
		if((validVotes!=null && validVotes.doubleValue()>0) && (votesEarned!=null && votesEarned.doubleValue()>0)){
			percengate= new BigDecimal((votesEarned/validVotes)*100).setScale (2,BigDecimal.ROUND_HALF_UP);
		}
		return percengate.toString();
	}
	
	/** 
	 * Provides Election Results Of A Booth To Display in A Pop Up Panel
	 */
	public ResultWithExceptionVO getBoothPageInfo(Long boothId){
		ResultWithExceptionVO resultStatus = new ResultWithExceptionVO();
		List<ElectionInfoVO> electionsInBooth = new ArrayList<ElectionInfoVO>();
		Map<String, List<PartyResultsInfoVO>> partiesInfoMap = new HashMap<String, List<PartyResultsInfoVO>>();
		List<PartyResultsInfoVO> partyResults = null;
		PartyResultsInfoVO partyResultsInfoVO = null;
		ElectionInfoVO electionInfoVO = null;
		BoothPanelVO boothPageInfo = new BoothPanelVO();
		
		try{
			List boothElectionInfo = candidateBoothResultDAO.findBoothWisePartiesAllElectionResultsByBooth(boothId);			
			if(boothElectionInfo.size() == 0)
				throw new Exception("Data Not Found");
			
			Booth booth = (Booth)((Object[])boothElectionInfo.get(0))[6];
			boothPageInfo.setPartNo(booth.getPartNo());
			boothPageInfo.setLocation(booth.getLocation());
			boothPageInfo.setMaleVoters(booth.getMaleVoters());
			boothPageInfo.setFemaleVoters(booth.getFemaleVoters());
			boothPageInfo.setTotalVoters(booth.getTotalVoters());
			boothPageInfo.setVillagesCovered(booth.getVillagesCovered());
			if(booth.getLocalBody() != null || booth.getTehsil() != null)
				boothPageInfo.setMandal(booth.getLocalBody() != null?(booth.getLocalBody().getName()+" "+booth.getLocalBody().
					getElectionType().getElectionType()).toUpperCase():(booth.getTehsil().getTehsilName() +" "+IConstants.TEHSIL).toUpperCase());
			else
				boothPageInfo.setMandal("");
			
			if(booth.getBoothLocalBodyWard() != null)
				boothPageInfo.setWardInfo(booth.getBoothLocalBodyWard().getLocalBodyWard().getName());
			
			String partyName = "";
			Long candidateId = null;
			String candidateFullName = null;
			String candidateFullNameWithStatus = null;
			String electionType = "";
			String constituencyName = "";
			Long constituencyId = null;
			String electionYear = "";
			Long votesEarned = null;
			
			for(int i=0; i<boothElectionInfo.size(); i++){
				
				candidateFullName = "";
				candidateFullNameWithStatus = "";
				
				Object[] values = (Object[])boothElectionInfo.get(i);
				partyName = (String)values[0];
				candidateId = (Long)values[1];
				
				if(!StringUtils.isBlank((String)values[2]))
					candidateFullName = candidateFullName + ((String)values[2]) + " ";
				if(!StringUtils.isBlank((String)values[3]))
					candidateFullName = candidateFullName + ((String)values[3]) + " ";
				if(!StringUtils.isBlank((String)values[4]))
					candidateFullName = candidateFullName + ((String)values[4]);
				
				if(((Long)values[5]) == 1)
					candidateFullNameWithStatus = candidateFullName + " " +"(WON)";
				else
					candidateFullNameWithStatus = candidateFullName + " "+"("+values[5]+")";
				electionType = (String)values[7];
				constituencyName = (String)values[8];
				constituencyId = (Long)values[9];
				electionYear = (String)values[10];
				votesEarned = (Long)values[11];
				
				partyResultsInfoVO = new PartyResultsInfoVO();
				partyResultsInfoVO.setCandidateId(candidateId);
				partyResultsInfoVO.setCandidateName(candidateFullNameWithStatus);
				partyResultsInfoVO.setPartyName(partyName);
				partyResultsInfoVO.setVotesEarned(votesEarned);
				partyResults = partiesInfoMap.get(electionType+"_"+constituencyName+"_"+constituencyId+"_"+electionYear);
				
				if(partyResults == null)
					partyResults = new ArrayList<PartyResultsInfoVO>();
				
				partyResults.add(partyResultsInfoVO);
				partiesInfoMap.put(electionType+"_"+constituencyName+"_"+constituencyId+"_"+electionYear, partyResults);
				
			}
			
			for(Map.Entry<String, List<PartyResultsInfoVO>> entry:partiesInfoMap.entrySet()){
				electionInfoVO = new ElectionInfoVO();
				String[] params = StringUtils.split(entry.getKey(), "_");
				electionInfoVO.setConstituencyId(params[2]);
				electionInfoVO.setConstituencyName(params[1]);
				electionInfoVO.setElectionTypeYear(params[0]+" "+params[3]);
				electionInfoVO.setElectionType(params[0].toString());
				electionInfoVO.setPartyResults(entry.getValue());
				electionsInBooth.add(electionInfoVO);
			}
			
			boothPageInfo.setElections(electionsInBooth);
			
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			if(log.isDebugEnabled())
				log.debug("Exception Encountered::", e);
		}
		resultStatus.setFinalResult(boothPageInfo);
		return resultStatus;
	}
	
	/**
	 * Returns All Tehsil wise or Revenue Village wise Assembly and Parliament Elections Results
	 */
	
	public ElectionWiseMandalPartyResultListVO getPartyGenderWiseBoothVotesForMandal(Long locationId, String locationType){
		ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO = new ElectionWiseMandalPartyResultListVO();
		List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList = new ArrayList<ElectionWiseMandalPartyResultVO>();
		List<ElectionWiseMandalPartyResultVO> partyWiseElectionResultVOList = new ArrayList<ElectionWiseMandalPartyResultVO>();
		List list = null;
		if(locationType.equalsIgnoreCase("Mandal"))
			list = candidateBoothResultDAO.getPartyGenderWiseBoothVotesForMandal(locationId);
		else
			list = candidateBoothResultDAO.findAllElectionsInfoInRevenueVillage(locationId);
		
		// 0- partyID, 1- partyName, 2-candidateID, 3- candidateName, 4-rank, 5-electionYear, 6-electionType
		//7-validVotes, 8-boothId, 9-partNo, 10-villageCovered, 11-maleVoters, 12-femaleVoters, 13-totalVoters,
		//14-votesEarned by candidate in the booth, 15-constituencyID, 16-constituencyName
		//election type and year is populated in the vo object
				
		Map<ElectionWiseMandalPartyResultVO, List<Object[]>> rawData = getElectionWiseMandalPartyResults(list);
		
		Set<Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>>> electionBoothWiseData = rawData.entrySet();

		Iterator<Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>>> iterator = electionBoothWiseData.iterator();
		while(iterator.hasNext()){
			Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>> entry = iterator.next();
			ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO = entry.getKey();
			ElectionWiseMandalPartyResultVO partyWiseElectionResultsVO = entry.getKey();
			List<Object[]> results = entry.getValue();
			
			//Grouped By Election Type, Year And Party 
			partyWiseElectionResultsVO = getPartyWiseElectionResults(partyWiseElectionResultsVO, results);
			//Grouped By Election Type, Year And Constituency
			electionWiseMandalPartyResultVO = getConstituencyWiseElectionResultInMandal(electionWiseMandalPartyResultVO, results);
			
			electionWiseMandalPartyResultVOList.add(electionWiseMandalPartyResultVO);
			partyWiseElectionResultVOList.add(partyWiseElectionResultsVO);
		}
		electionWiseMandalPartyResultListVO.setElectionWiseMandalPartyResultVOList(electionWiseMandalPartyResultVOList);
		
		//Parties Wise Results In All AC PC Elections Data Provider For Chart 
		List<PartyResultVO> partyResultsForChart = generatingPartyWiseResultsFromVO(partyWiseElectionResultVOList);
		electionWiseMandalPartyResultListVO.setAllPartiesAllElectionResults(partyResultsForChart);
		return electionWiseMandalPartyResultListVO;
	}
	
	/**
	 * Provides Parties wise consolidated Information about One Election 
	 * @param electionWiseMandalPartyResultVO
	 * @param results
	 * @return
	 */
	
	private ElectionWiseMandalPartyResultVO getPartyWiseElectionResults(
			ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO,
			List<Object[]> results) {
		Map<PartyResultsVO, List<Object[]>> partyWiseResultsMap = new LinkedHashMap<PartyResultsVO, List<Object[]>>();
		List<PartyResultsVO> allParitesInElection = new ArrayList<PartyResultsVO>();
		PartyResultsVO partyResultsVO = null;
		List<Object[]> partyResult = null;
		Long partyEarnedVotes = null;
		Long allPartiesEarnedVotes = 0l;
		for(Object[] values:results){
			partyResultsVO = new PartyResultsVO();
			partyResultsVO.setPartyId((Long)values[0]);
			partyResultsVO.setPartyName(values[1].toString());
			partyResult = partyWiseResultsMap.get(partyResultsVO);
			if(partyResult == null)
				partyResult = new ArrayList<Object[]>();
			partyResult.add(values);
			partyWiseResultsMap.put(partyResultsVO, partyResult);
		}
		
		for(Map.Entry<PartyResultsVO, List<Object[]>> partyInElection:partyWiseResultsMap.entrySet()){
			partyResultsVO = partyInElection.getKey();
			partyResult = partyInElection.getValue();
			partyEarnedVotes = 0l;
			for(Object[] values:partyResult){
				partyEarnedVotes += (Long)values[14];
				allPartiesEarnedVotes += (Long)values[14];
			}
			partyResultsVO.setVotesEarned(partyEarnedVotes);
			allParitesInElection.add(partyResultsVO);
		}
		
		for(PartyResultsVO percentCalcVO:allParitesInElection){
			String percentage = new BigDecimal((percentCalcVO.getVotesEarned()*100.0)/
					allPartiesEarnedVotes).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			percentCalcVO.setPercentage(percentage);
		}
		electionWiseMandalPartyResultVO.setPartyResultsVO(allParitesInElection);
		return electionWiseMandalPartyResultVO;
	}
	
	/**
	 * Converts Data Grouped By Election Type and Year To Data Grouped By Parties
	 * for all Elections
	 * @param partyWiseElectionResultVOList
	 * @return
	 */
	
	public List<PartyResultVO> generatingPartyWiseResultsFromVO(List<ElectionWiseMandalPartyResultVO> 
		   partyWiseElectionResultVOList){
		Map<PartyResultVO, List<ElectionResultVO>>  allPartiesInElecsMap = 
			new LinkedHashMap<PartyResultVO, List<ElectionResultVO>>(); 
		List<PartyResultVO> partyResults = new ArrayList<PartyResultVO>();
		PartyResultVO eachPartyInfo = null;
		List<ElectionResultVO> electionsOfParty = null;
		ElectionResultVO partyElecReuslt = null;
		for(ElectionWiseMandalPartyResultVO eachElec:partyWiseElectionResultVOList){
			for(PartyResultsVO party:eachElec.getPartyResultsVO()){
				eachPartyInfo = new PartyResultVO();
				eachPartyInfo.setPartyId(party.getPartyId());
				eachPartyInfo.setPartyName(party.getPartyName());
				electionsOfParty = allPartiesInElecsMap.get(eachPartyInfo);
				if(electionsOfParty == null)
					electionsOfParty = new ArrayList<ElectionResultVO>();
				partyElecReuslt = new ElectionResultVO();
				partyElecReuslt.setElectionType(eachElec.getElectionType());
				partyElecReuslt.setElectionYear(eachElec.getElectionYear().toString());
				partyElecReuslt.setVotesEarned(party.getVotesEarned());
				partyElecReuslt.setPercentage(party.getPercentage());
				electionsOfParty.add(partyElecReuslt);
				allPartiesInElecsMap.put(eachPartyInfo, electionsOfParty);
			}		
		}
		
		for(Map.Entry<PartyResultVO, List<ElectionResultVO>> entry:allPartiesInElecsMap.entrySet()){
			eachPartyInfo = entry.getKey();
			eachPartyInfo.setElectionWiseResults(entry.getValue());
			partyResults.add(eachPartyInfo);
		}
		
		return partyResults;
	}
	
	/**
	 * Groups one Election Data By Constituencies Wise In a Mandal/Township 
	 * @param electionWiseMandalPartyResultVO
	 * @param value
	 * @return
	 */
	
	private ElectionWiseMandalPartyResultVO getConstituencyWiseElectionResultInMandal(
			ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO, List<Object[]> value) {
		
		Map<ConstituencyWiseDataForMandalVO, List<PartyGenderWiseVotesVO>> constituencyResultMap = 
			new LinkedHashMap<ConstituencyWiseDataForMandalVO, List<PartyGenderWiseVotesVO>>();
		

		Long totalVotersInMandalForElection = 0l;
		Long maleVotersInMandalForElection = 0l;
		Long femaleVotersInMandalForElection = 0l;
		Long polledVotesInMandalForElection = 0l;
		
		
		for(int i=0; i<value.size(); i++){
			Object[] params = (Object[])value.get(i);
			ConstituencyWiseDataForMandalVO constituencyWiseDataForMandalVO = new ConstituencyWiseDataForMandalVO();
			constituencyWiseDataForMandalVO.setConstituencyId((Long)params[15]);
			constituencyWiseDataForMandalVO.setConstituencyName(params[16].toString());
			
			List<PartyGenderWiseVotesVO> constituencyFullList = constituencyResultMap.get(constituencyWiseDataForMandalVO);
			if(constituencyFullList == null)
				constituencyFullList = new ArrayList<PartyGenderWiseVotesVO>();
			
			PartyGenderWiseVotesVO partyGenderWiseVotesVO = new PartyGenderWiseVotesVO();
			partyGenderWiseVotesVO.setPartyID((Long)params[0]);
			partyGenderWiseVotesVO.setPartyName(params[1].toString());
			partyGenderWiseVotesVO.setCandidateNameWithStatus(params[3].toString());
			partyGenderWiseVotesVO.setCandidateID((Long)params[2]);
			partyGenderWiseVotesVO.setRank((Long)params[4]);
			partyGenderWiseVotesVO.setMaleBoothResults((Long)params[11]);
			partyGenderWiseVotesVO.setFemaleBoothResults((Long)params[12]);
			partyGenderWiseVotesVO.setTotalVotesEarned((Long)params[13]);
			partyGenderWiseVotesVO.setVotesEarnedInBoothForParty((Long)params[14]);
			
			constituencyFullList.add(partyGenderWiseVotesVO);
			constituencyResultMap.put(constituencyWiseDataForMandalVO, constituencyFullList);
		}
		
		List<ConstituencyWiseDataForMandalVO> allConstituenciesInfo = new ArrayList<ConstituencyWiseDataForMandalVO>();
		
		for(Map.Entry<ConstituencyWiseDataForMandalVO, List<PartyGenderWiseVotesVO>> entry:constituencyResultMap.entrySet()){
			ConstituencyWiseDataForMandalVO constituencyWiseDataForMandalVO = entry.getKey();
			List<PartyGenderWiseVotesVO> constituencyResultList = entry.getValue();
			Map<Long, List<PartyGenderWiseVotesVO>> partyWiseResultsMap = new LinkedHashMap<Long, List<PartyGenderWiseVotesVO>>();
			List<PartyGenderWiseVotesVO> partyWiseConstituencyResultList = null;
			for(PartyGenderWiseVotesVO partyGenderWiseVotesVO:constituencyResultList){
				Long rank = partyGenderWiseVotesVO.getRank();
				partyWiseConstituencyResultList = partyWiseResultsMap.get(rank);
				if(partyWiseConstituencyResultList == null)
					partyWiseConstituencyResultList = new ArrayList<PartyGenderWiseVotesVO>();
				partyWiseConstituencyResultList.add(partyGenderWiseVotesVO);
				partyWiseResultsMap.put(rank, partyWiseConstituencyResultList);
			}
			getConstiteuncyWisePartyResults(constituencyWiseDataForMandalVO, partyWiseResultsMap);
			allConstituenciesInfo.add(constituencyWiseDataForMandalVO);
		}
		
		for(ConstituencyWiseDataForMandalVO constituencyInfoInMandal:allConstituenciesInfo){
			totalVotersInMandalForElection += constituencyInfoInMandal.getCommonTotalVotersInMandalAndConstituency();
			maleVotersInMandalForElection += constituencyInfoInMandal.getCommonMaleVotersInMandalAndConstituency();
			femaleVotersInMandalForElection += constituencyInfoInMandal.getCommonFemaleVotersInMandalAndConstituency();
			polledVotesInMandalForElection += constituencyInfoInMandal.getTotalPolledVotes();
		}
		
		electionWiseMandalPartyResultVO.setMaleVoters(maleVotersInMandalForElection);
		electionWiseMandalPartyResultVO.setFemaleVoters(femaleVotersInMandalForElection);
		electionWiseMandalPartyResultVO.setTotalVotersInMandal(totalVotersInMandalForElection);
		electionWiseMandalPartyResultVO.setPolledVotes(polledVotesInMandalForElection);
		electionWiseMandalPartyResultVO.setConstituencyWiseDataForMandalVOs(allConstituenciesInfo);
		
		return electionWiseMandalPartyResultVO;
	}
		
	private void getConstiteuncyWisePartyResults(
			ConstituencyWiseDataForMandalVO constituencyWiseDataForMandalVO,
			Map<Long, List<PartyGenderWiseVotesVO>> partyWiseResultsMap) {
		
		List<PartyGenderWiseVotesVO> resultsOfAllPartiesInConstituency = new ArrayList<PartyGenderWiseVotesVO>();
		
		Long totalMaleVotersInConstiteuncyForMandal = 0l;
		Long totalFemaleVotersInConstiteuncyForMandal = 0l;
		Long totalMaleOrFemaleVotersInConstiMandal = 0l;
		
		Long totalMaleVotesPolledInConstituencyForMandal = 0l;
		Long totalFemaleVotesPolledInConstituencyForMandal = 0l;
		Long totalFMVotesPolledInConstituencyForMandal = 0l;
				
		Long allPartiesVotesInMandalForConstituency = 0l;
		
		int i=0;
		
		for(Map.Entry<Long, List<PartyGenderWiseVotesVO>> entry:partyWiseResultsMap.entrySet()){
			List<PartyGenderWiseVotesVO> resultsOfAParty = entry.getValue();
			PartyGenderWiseVotesVO eashPartyResultInConstituency = new PartyGenderWiseVotesVO();
			
			Long totalMaleBoothVotesForParty = 0L;
			Long totalFemaleBoothVotesForParty = 0L;
			Long totalFMBoothVotesForParty = 0L;
			
			eashPartyResultInConstituency.setCandidateID(resultsOfAParty.get(0).getCandidateID());
			eashPartyResultInConstituency.setCandidateNameWithStatus(resultsOfAParty.get(0).getCandidateNameWithStatus());
			eashPartyResultInConstituency.setRank(resultsOfAParty.get(0).getRank());
			eashPartyResultInConstituency.setPartyID(resultsOfAParty.get(0).getPartyID());
			eashPartyResultInConstituency.setPartyName(resultsOfAParty.get(0).getPartyName());
			
			
			for(PartyGenderWiseVotesVO resultsForPartyInBooth:resultsOfAParty){
				if(resultsForPartyInBooth.getMaleBoothResults() < 5 && resultsForPartyInBooth.getFemaleBoothResults() < 5){
					if(i == 0)
						totalMaleOrFemaleVotersInConstiMandal += resultsForPartyInBooth.getTotalVotesEarned();
					totalFMBoothVotesForParty = totalFMBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();	
				}
				else if(resultsForPartyInBooth.getMaleBoothResults() < 5){
					if(i == 0)
						totalFemaleVotersInConstiteuncyForMandal += resultsForPartyInBooth.getFemaleBoothResults();	
					totalFemaleBoothVotesForParty = totalFemaleBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
					
				}
				else if(resultsForPartyInBooth.getFemaleBoothResults() < 5){
					if(i == 0)
						totalMaleVotersInConstiteuncyForMandal += resultsForPartyInBooth.getMaleBoothResults();	
					totalMaleBoothVotesForParty = totalMaleBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				}
				else{
					if(i == 0)
						totalMaleOrFemaleVotersInConstiMandal +=  resultsForPartyInBooth.getTotalVotesEarned();	
					totalFMBoothVotesForParty = totalFMBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				}
				
			}
			
			i++;
			
			Long totalVotesForPartyInConstiForMandal = totalMaleBoothVotesForParty + totalFemaleBoothVotesForParty + totalFMBoothVotesForParty;
			
			eashPartyResultInConstituency.setTotalVotesEarned(totalVotesForPartyInConstiForMandal);
			eashPartyResultInConstituency.setMaleBoothResults(totalMaleBoothVotesForParty);
			eashPartyResultInConstituency.setFemaleBoothResults(totalFemaleBoothVotesForParty);
			eashPartyResultInConstituency.setFmBoothResults(totalFMBoothVotesForParty);
			
			totalMaleVotesPolledInConstituencyForMandal += totalMaleBoothVotesForParty;
			totalFemaleVotesPolledInConstituencyForMandal += totalFemaleBoothVotesForParty;
			totalFMVotesPolledInConstituencyForMandal += totalFMBoothVotesForParty;
			
			
			allPartiesVotesInMandalForConstituency += totalVotesForPartyInConstiForMandal;
			resultsOfAllPartiesInConstituency.add(eashPartyResultInConstituency);
		}
		
		constituencyWiseDataForMandalVO.setCommonMaleVotersInMandalAndConstituency(totalMaleVotersInConstiteuncyForMandal);
		constituencyWiseDataForMandalVO.setCommonFemaleVotersInMandalAndConstituency(totalFemaleVotersInConstiteuncyForMandal);
		constituencyWiseDataForMandalVO.setCommonMaleOrFemaleVoters(totalMaleOrFemaleVotersInConstiMandal);
		constituencyWiseDataForMandalVO.setCommonTotalVotersInMandalAndConstituency(totalMaleVotersInConstiteuncyForMandal +
				totalFemaleVotersInConstiteuncyForMandal + totalMaleOrFemaleVotersInConstiMandal);
		
		constituencyWiseDataForMandalVO.setMalePolledVotes(totalMaleVotesPolledInConstituencyForMandal);
		constituencyWiseDataForMandalVO.setFemalePolledVotes(totalFemaleVotesPolledInConstituencyForMandal);
		constituencyWiseDataForMandalVO.setMaleOrFemaleValidVotes(totalFMVotesPolledInConstituencyForMandal);
		constituencyWiseDataForMandalVO.setTotalPolledVotes(allPartiesVotesInMandalForConstituency);
		
		for(PartyGenderWiseVotesVO eachPartyInConsti:resultsOfAllPartiesInConstituency){
			String maleVotesPercentage = "";
			String femaleVotesPercentage = "";
			String fmVotesPercentage = "";
			String totalVotesPercentage = "";
			
			if(totalMaleVotesPolledInConstituencyForMandal == 0){
				maleVotesPercentage = "-";
			}else{
				maleVotesPercentage = new BigDecimal((eachPartyInConsti.getMaleBoothResults()*100.0)/
						totalMaleVotesPolledInConstituencyForMandal).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			if(totalFemaleVotesPolledInConstituencyForMandal == 0){
				femaleVotesPercentage = "-";
			}else{
				femaleVotesPercentage = new BigDecimal((eachPartyInConsti.getFemaleBoothResults()*100.0)/
						totalFemaleVotesPolledInConstituencyForMandal).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			if(totalFMVotesPolledInConstituencyForMandal == 0){
				fmVotesPercentage = "-";
			}else{
				fmVotesPercentage = new BigDecimal((eachPartyInConsti.getFmBoothResults()*100.0)/
						totalFMVotesPolledInConstituencyForMandal).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			if(allPartiesVotesInMandalForConstituency == 0){
				totalVotesPercentage = "-";
			}else{
				totalVotesPercentage = new BigDecimal((eachPartyInConsti.getTotalVotesEarned()*100.0)/
						allPartiesVotesInMandalForConstituency).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
					
			eachPartyInConsti.setMaleBoothResultsPercentage(maleVotesPercentage);
			eachPartyInConsti.setFemaleBoothResultsPercentage(femaleVotesPercentage);
			eachPartyInConsti.setFmBoothResultsPercentage(fmVotesPercentage);
			eachPartyInConsti.setTotalVotesEarnedPercentage(totalVotesPercentage);
		}
		
		constituencyWiseDataForMandalVO.setPartyVotes(resultsOfAllPartiesInConstituency);
	}
	
	private Map<ElectionWiseMandalPartyResultVO, List<Object[]>> getElectionWiseMandalPartyResults(List list){
		int size = list.size();
		Map<ElectionWiseMandalPartyResultVO, List<Object[]>> rawData =  
			new LinkedHashMap<ElectionWiseMandalPartyResultVO, List<Object[]>>();
		for(int i=0; i<size; i++){
			Object[] obj = (Object[]) list.get(i);
			ElectionWiseMandalPartyResultVO key =new ElectionWiseMandalPartyResultVO();
			key.setElectionType(obj[6].toString());
			key.setElectionYear(new Long(obj[5].toString()));
			
			List<Object[]> value = rawData.get(key);
			if(value==null){
				value = new ArrayList<Object[]>();
			}
			value.add(obj);
			rawData.put(key,value);			
		}
		
		return rawData;
	}
	
	/**
	 * Method That Returns All Mptc and Zptc Elections Happened in Mandal
	 * @param tehsilId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ElectionWiseMandalPartyResultListVO getAllMPTCAndZPTCElectionsInfoInTehsil(Long tehsilId){
		ElectionWiseMandalPartyResultListVO mptcZptcElectionResultListVO = new ElectionWiseMandalPartyResultListVO();
		List<ElectionWiseMandalPartyResultVO> allElectionsInfo = new ArrayList<ElectionWiseMandalPartyResultVO>();
		List list = nominationDAO.findAllMptcAndZptcElectionsInfoInMandal(tehsilId);
		Map<ElectionWiseMandalPartyResultVO, List<Object[]>> mandalElectionsMap = getElectionWiseMandalPartyResults(list);
		ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO = null;
		for(Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>> entry:mandalElectionsMap.entrySet()){
			Map<PartyResultsVO, List<Object[]>> partiesInElectionMap = new LinkedHashMap<PartyResultsVO, List<Object[]>>();			
			List<Object[]> partyResults = null;
			PartyResultsVO partyResultsVO = null;
			electionWiseMandalPartyResultVO = entry.getKey();
			for(Object[] values:entry.getValue()){
				partyResultsVO = new PartyResultsVO();
				partyResultsVO.setPartyId((Long)values[2]);
				partyResultsVO.setPartyName(values[3].toString());
				partyResults = partiesInElectionMap.get(partyResultsVO);
				if(partyResults == null)
					partyResults = new ArrayList<Object[]>();
				partyResults.add(values);
				partiesInElectionMap.put(partyResultsVO, partyResults);
			}
			
			List<PartyResultsVO> allPartiesResults = getAllPartiesResultsInMandal(partiesInElectionMap);
			electionWiseMandalPartyResultVO.setPartyResultsVO(allPartiesResults);
			allElectionsInfo.add(electionWiseMandalPartyResultVO);
		}
		List<PartyResultVO> partiesWiseInfo = generatingPartyWiseResultsFromVO(allElectionsInfo);
		mptcZptcElectionResultListVO.setAllPartiesAllElectionResults(partiesWiseInfo);
		mptcZptcElectionResultListVO.setPartyWiseElectionResultsVOList(allElectionsInfo);
		return mptcZptcElectionResultListVO;
	}
	
	private List<PartyResultsVO> getAllPartiesResultsInMandal(Map<PartyResultsVO, List<Object[]>> partiesInElectionMap) {
		List<PartyResultsVO> allPartyResults = new ArrayList<PartyResultsVO>();
		List<ConstituencyWisePartyInfoVO> constituencyWisePatiesInfoVOs = null;
		ConstituencyWisePartyInfoVO constituencyWisePartyInfoVO = null;
		Long allPartiesVotesInMandal = 0l;
		for(Map.Entry<PartyResultsVO, List<Object[]>> entry:partiesInElectionMap.entrySet()){
			PartyResultsVO partyResultsVO = entry.getKey();
			List<Object[]> valuesList = entry.getValue();
			partyResultsVO.setSeatsParticipated(valuesList.size());
			constituencyWisePatiesInfoVOs = new ArrayList<ConstituencyWisePartyInfoVO>();
			int seatsWon = 0;
			Long partyVotesInMandal = 0l;
			Long totalPolledVotes = 0l;
			for(Object[] values:valuesList){
				constituencyWisePartyInfoVO = new ConstituencyWisePartyInfoVO();
				constituencyWisePartyInfoVO.setConstituencyId((Long)values[13]);
				constituencyWisePartyInfoVO.setConstituencyName(values[9].toString());
				constituencyWisePartyInfoVO.setCandidateId((Long)values[11]);
				constituencyWisePartyInfoVO.setCandidateName(values[10].toString());
				constituencyWisePartyInfoVO.setRank((Long)values[1]);
				if((Long)values[1] == 1)
					seatsWon++;
				constituencyWisePartyInfoVO.setVotesEarned(((Double)values[4]).longValue());
				partyVotesInMandal = partyVotesInMandal + ((Double)values[4]).longValue();
				totalPolledVotes += ((Double)values[7]).longValue();
				constituencyWisePartyInfoVO.setPercentage(values[12].toString());
				constituencyWisePatiesInfoVOs.add(constituencyWisePartyInfoVO);
			}
			allPartiesVotesInMandal = allPartiesVotesInMandal + partyVotesInMandal;
			partyResultsVO.setTotalSeatsWon(seatsWon);
			partyResultsVO.setVotesEarned(partyVotesInMandal);
			partyResultsVO.setTotalPolledVotes(totalPolledVotes);
			partyResultsVO.setConstituencyWisePatiesInfoVOs(constituencyWisePatiesInfoVOs);
			allPartyResults.add(partyResultsVO);
		}
		
		for(PartyResultsVO objParty:allPartyResults)
			if(objParty.getVotesEarned() == 0 && allPartiesVotesInMandal == 0)
				objParty.setPercentage("100");
			else
				objParty.setPercentage(new BigDecimal((objParty.getVotesEarned()*100.0)/
					allPartiesVotesInMandal).setScale(2,BigDecimal.ROUND_HALF_UP).toString());			
		
		return allPartyResults;
	}
	
	//Returns all AC,PC,MPTC,ZPTC ELECTIONS results of a Mandal
	public ElectionWiseMandalPartyResultListVO getAllElectionsResultsInAMandal(Long mandalId){
		ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO = new ElectionWiseMandalPartyResultListVO();
		List<PartyResultVO> acPcElectionResultsForParties = getPartyGenderWiseBoothVotesForMandal(mandalId, "Mandal").getAllPartiesAllElectionResults();
		List<PartyResultVO> mptcZptcElectionResultsForParties = getAllMPTCAndZPTCElectionsInfoInTehsil(mandalId).getAllPartiesAllElectionResults();
		List<PartyResultVO> allElectionResults = new ArrayList<PartyResultVO>();
		List<ElectionResultVO> elections = null;
		
		Map<PartyResultVO, List<ElectionResultVO>> resultMap = new HashMap<PartyResultVO, List<ElectionResultVO>>();
		
		for(PartyResultVO partyResultVO:acPcElectionResultsForParties)
			resultMap.put(partyResultVO, partyResultVO.getElectionWiseResults());
		
		for(PartyResultVO partyResultVO:mptcZptcElectionResultsForParties){
			elections = resultMap.get(partyResultVO);
			if(elections == null)
				resultMap.put(partyResultVO, elections);
			else
				elections.addAll(partyResultVO.getElectionWiseResults());
		}
		
		for(Map.Entry<PartyResultVO, List<ElectionResultVO>> entry:resultMap.entrySet())
			allElectionResults.add(entry.getKey());
		
		for(int i=0; i<allElectionResults.size(); i++)
			if(!IConstants.BYE_ELECTIONS_STATIC_PARTIES.contains(allElectionResults.get(i).getPartyName()))
				allElectionResults.remove(i);
		
		Set<String> partyElecs1 = null;//Only particular party participated Elections
		Set<String> allElecTypes = new HashSet<String>();////All parties participated Elections
		List<SelectOptionVO> electionsHeading = new LinkedList<SelectOptionVO>(); 
		List<Float> partyPecentages = null;
		String elecYearAndType = "";
		List alliance = null;
		List result = null;
		for(PartyResultVO party:allElectionResults){
			partyElecs1 = new HashSet<String>();
			partyPecentages = new ArrayList<Float>();
			for(ElectionResultVO ele:party.getElectionWiseResults()){
				if("ZPTC".equalsIgnoreCase(ele.getElectionType()) || "MPTC".equalsIgnoreCase(ele.getElectionType()))
					elecYearAndType = ele.getElectionYear()+" ."+ele.getElectionType();	
				else
					elecYearAndType = ele.getElectionYear()+" "+ele.getElectionType();
				ele.setElectionYearAndType(elecYearAndType);
				
				if(ele.getElectionType().equals(IConstants.PARLIAMENT_ELECTION_TYPE))
  					result = electionDAO.findElectionIdByParliamentElectionTypeAndYear(ele.getElectionType(), ele.getElectionYear());
		        else
				    result = electionDAO.findElectionIdByElectionTypeAndYear(ele.getElectionType(),ele.getElectionYear(),1l);	
				
				if(result.size() > 0)
					alliance = allianceGroupDAO.findAlliancePartiesByElectionAndParty(Long.parseLong(result.get(0).toString()),party.getPartyId());
				ele.setHasAlliance((alliance.size()>0 && result.size() > 0)?"true":"false");
				partyElecs1.add(elecYearAndType);
				allElecTypes.add(elecYearAndType);
				partyPecentages.add(new Float(ele.getPercentage()));
			}
			Collections.sort(partyPecentages);
			party.setRange(partyPecentages.get(0)+"-"+partyPecentages.get(partyPecentages.size()-1));
			party.setElections(partyElecs1);
		}
		
		//Heading Data Collection
		for(String elecType:allElecTypes)
			electionsHeading.add(new SelectOptionVO(0l, elecType));
		
		Collections.sort(electionsHeading, new SelectOptionVOComparator());//Heading Data Sorting
		
		//Adding Party Not Participated Election Info with -ve Percentage
		ElectionResultVO noElec = null;
		for(PartyResultVO party1:allElectionResults){
			elections = new ArrayList<ElectionResultVO>();
			for(SelectOptionVO elec1:electionsHeading){
				if(!party1.getElections().contains(elec1.getName())){
					noElec = new ElectionResultVO();
					noElec.setElectionYearAndType(elec1.getName());
					noElec.setPercentage("-1");
					elections.add(noElec);
				}
			}
			if(elections.size() > 0)
				party1.getElectionWiseResults().addAll(party1.getElectionWiseResults().size()-1, elections);
			elections = party1.getElectionWiseResults();
			Collections.sort(elections, new ElectionResultTypeComparator());//Data Sorting Same As Heading Order
			party1.setElectionWiseResults(elections);
		}
	
		Set<String> staticParties = new LinkedHashSet<String>();
		//Extra Parties(Others) Collection
		staticParties.add("INC");staticParties.add("TDP");staticParties.add("PRP");staticParties.add("TRS");staticParties.add("BJP");
		List<PartyResultVO> fixedParties = new ArrayList<PartyResultVO>();
		List<PartyResultVO> otherParties = new ArrayList<PartyResultVO>();
		for(PartyResultVO party: allElectionResults)
			if(staticParties.contains(party.getPartyName()))
				fixedParties.add(party);
			else
				otherParties.add(party);
		
		//Calculating Other Parties Percentage 
		Map<String, Float> electionPercenatgeMap = new LinkedHashMap<String, Float>();//Map that contains Election and Percentage of Others  
		
		Float percenatage = null;
		
		for(PartyResultVO party: otherParties)
			for(ElectionResultVO ele:party.getElectionWiseResults()){
				percenatage = electionPercenatgeMap.get(ele.getElectionYearAndType());
				if(percenatage != null && percenatage.intValue() >= 0){
					percenatage += new Float(ele.getPercentage());
					electionPercenatgeMap.put(ele.getElectionYearAndType(), percenatage);
				}else if(percenatage == null)
					electionPercenatgeMap.put(ele.getElectionYearAndType(), 0.0f);
			}
		
		PartyResultVO othersParty = new PartyResultVO();
		othersParty.setPartyName(IConstants.OTHERS);
		elections = new ArrayList<ElectionResultVO>();
		ElectionResultVO elecInfo = null;
		String[] yearType = null;
		for(Map.Entry<String, Float> entry:electionPercenatgeMap.entrySet()){
			elecInfo = new ElectionResultVO();
			yearType = StringUtils.split(entry.getKey()," .");
			elecInfo.setElectionYear(yearType[0]);
			elecInfo.setElectionType(yearType[1]);
			elecInfo.setElectionYearAndType(entry.getKey());
			elecInfo.setPercentage(entry.getValue().toString());
			elections.add(elecInfo);
		}
		Collections.sort(elections, new ElectionResultTypeComparator());
		othersParty.setElectionWiseResults(elections);
		fixedParties.add(othersParty);
		
		//Removing Dot(.) from ZPTC
		for(SelectOptionVO obj:electionsHeading){
			if(obj.getName().contains("."))
				obj.setName(StringUtils.remove(obj.getName(), "."));
			if(StringUtils.contains(obj.getName(), IConstants.ASSEMBLY_ELECTION_TYPE))
				obj.setName(StringUtils.replace(obj.getName(), IConstants.ASSEMBLY_ELECTION_TYPE, "AC"));
			else if(StringUtils.contains(obj.getName(), IConstants.PARLIAMENT_ELECTION_TYPE))
				obj.setName(StringUtils.replace(obj.getName(), IConstants.PARLIAMENT_ELECTION_TYPE, "PC"));
		}
		
		electionWiseMandalPartyResultListVO.setElections(electionsHeading);
		electionWiseMandalPartyResultListVO.setAllPartiesAllElectionResults(fixedParties);
		return electionWiseMandalPartyResultListVO;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService#getMPTCandZPTCResultsInAMandalForAElection(java.lang.Long, java.lang.String, java.lang.String)
	 * This Method Processes and Returns List of all party results in a particular MPTC & ZPTC election 
	 * @Input tehsilId and election type and election year
	 * @Output partys results list in selected tehsil
	 */
	public List<TeshilPartyInfoVO> getMPTCandZPTCResultsInAMandalForAElection(
			Long tehsilId, String electionType, String electionYear) {
		
		List totalVotersAndValidVotes = constituencyElectionResultDAO.getTotalVotesAndValidVotesForMPTCZPTC(tehsilId, electionType, electionYear);
		List<TeshilPartyInfoVO> tehsilPartyInfoVOList = null;
		
		if(tehsilId != null && electionType != null && electionYear != null){
			
			try{
				ElectionWiseMandalPartyResultListVO tehsilResultsVO = getAllMPTCAndZPTCElectionsInfoInTehsil(tehsilId);
				if(tehsilResultsVO != null){
					
			     log.debug(" Mandal Results Size :" + tehsilResultsVO.getPartyWiseElectionResultsVOList());
				 for(ElectionWiseMandalPartyResultVO allElec:tehsilResultsVO.getPartyWiseElectionResultsVOList()){
								 					 
					 if(electionType.equalsIgnoreCase(allElec.getElectionType()) &&
							 electionYear.equalsIgnoreCase(allElec.getElectionYear().toString()))
					     tehsilPartyInfoVOList = getPartyResultsInTehsilInElection(allElec);
								 
					 }
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
				log.debug("Exception Raised :" + ex);
				tehsilPartyInfoVOList = new ArrayList<TeshilPartyInfoVO>();
				TeshilPartyInfoVO tehsilResult = new TeshilPartyInfoVO();
				
				ResultStatus resultStatus = new ResultStatus();
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				
				tehsilResult.setResultStatus(resultStatus);
				tehsilPartyInfoVOList.add(tehsilResult);
			}
		}
		List<Object[]> values = (List<Object[]>)totalVotersAndValidVotes;
		if(values.size() > 0){
			if(values.get(0)[0] != null)
				tehsilPartyInfoVOList.get(0).setTotalVotersInConstituency(new Long(((Double)values.get(0)[0]).longValue()).toString());
			else
				tehsilPartyInfoVOList.get(0).setTotalVotersInConstituency("--");
			tehsilPartyInfoVOList.get(0).setTotalPolledVotes(((Double)values.get(0)[1]).floatValue());
			tehsilPartyInfoVOList.get(0).setTotalSeats((Long)values.get(0)[2]);
		}
	 return tehsilPartyInfoVOList;
	}
	
	public List<TeshilPartyInfoVO> getPartyResultsInTehsilInElection(ElectionWiseMandalPartyResultVO elecResInMandal){
		List<TeshilPartyInfoVO> tehsilPartyInfoVOList = null;
		
		if(elecResInMandal != null){
			
			try{
			tehsilPartyInfoVOList = new ArrayList<TeshilPartyInfoVO>();
			Float othersPercent = new Float(0);
			
			for(PartyResultsVO partyResult:elecResInMandal.getPartyResultsVO()){
				TeshilPartyInfoVO tehsilPartyRes = new TeshilPartyInfoVO();
				
				tehsilPartyRes.setPartyName(partyResult.getPartyName());
				tehsilPartyRes.setParticipatedSeats(new Long(partyResult.getSeatsParticipated()));
				tehsilPartyRes.setSeatsWonByParty(new Long(partyResult.getTotalSeatsWon()));
				tehsilPartyRes.setPercentageOfVotesWonByParty(new BigDecimal(partyResult.getPercentage()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
												
				tehsilPartyInfoVOList.add(tehsilPartyRes);
				
				if(!partyResult.getPartyName().equals(IConstants.TRS) && 
						!partyResult.getPartyName().equals(IConstants.INC) && 
						!partyResult.getPartyName().equals(IConstants.TDP) && 
						!partyResult.getPartyName().equals(IConstants.BJP))
					othersPercent+=tehsilPartyRes.getPercentageOfVotesWonByParty();
			}
			if(tehsilPartyInfoVOList != null && tehsilPartyInfoVOList.size() > 0)
				tehsilPartyInfoVOList.get(0).setOthersPercent(new BigDecimal(othersPercent).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			}
			catch(Exception ex){
				ex.printStackTrace();
				log.debug("Exception Raised :" + ex);
			}
		}
	 return tehsilPartyInfoVOList;
	}
	
	public ConstituencyVO getBoothwiseResultsOfTwoElectionsForAConstituency(Long constituencyId){
		ConstituencyVO constituencyVO = new ConstituencyVO();
		List<PartyElectionVotersVO> partyFinalResults = new ArrayList<PartyElectionVotersVO>();
		PartyElectionVotersVO partyElectionVotersVO = null;
		
		Constituency constituency = constituencyDAO.get(constituencyId);
		constituencyVO.setId(constituency.getConstituencyId());
		constituencyVO.setName(constituency.getName());
		List presentPartiesResults = null;
		List previousPartiesResults = null;
		List partNos = candidateBoothResultDAO.getPartNosOfAnElectionForAConstituency(constituencyId, "2010");
		
		if(partNos.size() == 0){
			constituencyVO.setIsDataExists(false);
			return constituencyVO;
		}

		presentPartiesResults = candidateBoothResultDAO.getBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(partNos, "2010", constituencyId);
		previousPartiesResults = candidateBoothResultDAO.getBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(partNos, "2009", constituencyId);	
		
		if(presentPartiesResults.size() == 0 || previousPartiesResults.size() == 0){
			constituencyVO.setIsDataExists(false);
			return constituencyVO;
		}
		
		Set<String> allStaticParties = new HashSet<String>();
		List<String> allStaticPartiesList = new ArrayList<String>();
		Set<String> presentStaticParties = new HashSet<String>();
		Set<String> previousStaticParties = new HashSet<String>();
		Boolean includeINDs = false;
		
		previousStaticParties.add("INC");previousStaticParties.add("PRP");
		previousStaticParties.add("TRS");previousStaticParties.add("BJP");
		previousStaticParties.add("TDP");
		
		presentStaticParties.add("INC");presentStaticParties.add("TDP");
		
		if("SIRCILLA".equalsIgnoreCase(constituency.getName()))
			includeINDs = true;

		if("Nizamabad Urban".equalsIgnoreCase(constituency.getName()))
			presentStaticParties.add("BJP");
		else
			presentStaticParties.add("TRS");
			
		List<PartyResultsVO> presentYearResults = getPartieswiseResultsWithRawData(presentPartiesResults, presentStaticParties, false);
		List<PartyResultsVO> previousYearResults = getPartieswiseResultsWithRawData(previousPartiesResults, previousStaticParties, includeINDs);
		
		if("SIRCILLA".equalsIgnoreCase(constituency.getName()))
			previousStaticParties.add("IND");
		
		allStaticParties.addAll(presentStaticParties);
		allStaticParties.addAll(previousStaticParties);
		allStaticPartiesList.addAll(allStaticParties);
		
		Collections.sort(allStaticPartiesList);
		
		allStaticPartiesList.add(IConstants.OTHERS);
		Long presVotesEarned;
		Long prevVotesEarned;
		String presPercentage;
		String prevPercentage;
		for(String partyName:allStaticPartiesList){
			partyElectionVotersVO = new PartyElectionVotersVO();
			partyElectionVotersVO.setParty(partyName);
			presVotesEarned = 0l;
			prevVotesEarned = 0l;
			presPercentage = "-1";
			prevPercentage = "-1";
			for(PartyResultsVO presentInfo:presentYearResults)
				if(partyName.equalsIgnoreCase(presentInfo.getPartyName())){
					presVotesEarned = presentInfo.getVotesEarned();
					presPercentage = presentInfo.getPercentage();
					break;
				}
			
			for(PartyResultsVO presentInfo:previousYearResults)
				if(partyName.equalsIgnoreCase(presentInfo.getPartyName())){
					prevVotesEarned = presentInfo.getVotesEarned();
					prevPercentage = presentInfo.getPercentage();
					break;
				}
			
			partyElectionVotersVO.setPresPercentage(presPercentage);
			partyElectionVotersVO.setPresVotesEarned(presVotesEarned);
			partyElectionVotersVO.setPrevPercentage(prevPercentage);
			partyElectionVotersVO.setPrevVotesEarned(prevVotesEarned);
			partyFinalResults.add(partyElectionVotersVO);
		}
		
		constituencyVO.setPartiesCombinedResults(partyFinalResults);
		constituencyVO.setPresentYearResults(presentYearResults);
		constituencyVO.setPreviousYearResults(previousYearResults);
		
		return constituencyVO;
	}
	
	private List<PartyResultsVO> getPartieswiseResultsWithRawData(
			List presentPartiesResults, Set<String> staticParties, Boolean includeINDs) {
		List<PartyResultsVO> parties = new ArrayList<PartyResultsVO>();
		PartyResultsVO partyResultsVO = null;
		Long othersVotesEarned = 0l;
		Long validVotes = (Long)((List<Object[]>)presentPartiesResults).get(0)[5];
		int i=0;
		for(Object[] values:(List<Object[]>)presentPartiesResults ){
			partyResultsVO = new PartyResultsVO();
			partyResultsVO.setPartyName(values[3].toString());
			partyResultsVO.setVotesEarned((Long)values[4]);
			partyResultsVO.setPercentage(new BigDecimal((Long)values[4] * 100.0/ validVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			if(includeINDs && "IND".equalsIgnoreCase(values[3].toString()) && i == 0){
				parties.add(partyResultsVO);
				i++;
				continue;
			}
			
			if(!staticParties.contains(values[3].toString())){
				othersVotesEarned += (Long)values[4];
				continue;
			}
			parties.add(partyResultsVO);
		}
		
		Collections.sort(parties, new PartyResultsVOComparator());
		
		partyResultsVO = new PartyResultsVO();
		partyResultsVO.setPartyName(IConstants.OTHERS);
		partyResultsVO.setVotesEarned(othersVotesEarned);
		partyResultsVO.setPercentage(new BigDecimal(othersVotesEarned * 100.0/ validVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		parties.add(partyResultsVO);
		
		return parties;
	}
	
	public List getMandalAllElectionDetails(Long tehsilID, Long partyID, boolean allianceFlag){
		List<MandalAllElectionDetailsVO> mandalAllElectionDetails = new ArrayList<MandalAllElectionDetailsVO>();
		// 0 - election, 1 - totalvoters , 2 - validvotes, 3- rejectedvotes, 4-tenderedvotes
		List result = boothConstituencyElectionDAO.getAllElectionBoothVotersForMandal(tehsilID);
		
		Long stateId = 0L;
		Tehsil tehsil = tehsilDAO.get(tehsilID);
		stateId = tehsil.getDistrict().getState().getStateId();
		
		if(result.size()==0)
			return mandalAllElectionDetails;
		for(int i=0; i<result.size(); i++){
			Object[] obj = (Object[]) result.get(i);
			MandalAllElectionDetailsVO objectVO = new MandalAllElectionDetailsVO();
			Election election = (Election)obj[0];
			objectVO.setElectionYear(election.getElectionYear());
			objectVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
			objectVO.setElectionTypeID(election.getElectionScope().getElectionType().getElectionTypeId());
			objectVO.setTotalVoters(new Long(obj[1].toString()));
			objectVO.setValidVoters(new Long(obj[2].toString()));
			objectVO.setRejectedVoters(new Long(obj[3].toString()));
			objectVO.setTenderedVoters(new Long(obj[4].toString()));
			objectVO.setElectionScopeID(election.getElectionScope().getElectionScopeId());
			objectVO.setElectionID(election.getElectionId());
			mandalAllElectionDetails.add(objectVO);
		}
		
		Iterator<MandalAllElectionDetailsVO> iterator =mandalAllElectionDetails.iterator();
		
		while(iterator.hasNext()){
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO = iterator.next();

			StringBuilder sb = new StringBuilder();
			if(allianceFlag){
				List<SelectOptionVO> partyIDs = staticDataService.getAlliancePartiesAsVO(
						mandalAllElectionDetailsVO.getElectionYear(), mandalAllElectionDetailsVO.getElectionTypeID(), partyID,stateId);
				if(partyIDs==null){
					sb.append(",").append(partyID);
				}else{	
					for(SelectOptionVO obj : partyIDs){
						sb.append(",").append(obj.getId());
					}
				} 
			}else{
				sb.append(",").append(partyID);
			}
			
			List temp = boothConstituencyElectionDAO.getPartyVotesByMandal(tehsilID, sb.substring(1), 
					mandalAllElectionDetailsVO.getElectionID());
			//0-firstName, 1-middlename, 2-lastname, 3-election, 4-votesearned, 5-partyId, 6-shortName
			if(temp.size()==0){
				iterator.remove();
				continue;
			}
			int i = getAlliancePartyInfo(temp, partyID);
			
			Object[] obj = (Object[]) temp.get(i);
			StringBuilder name = new StringBuilder();
			if(obj[0]!=null){
				name.append(obj[0].toString()).append(" ");
			}
			if(obj[1]!=null){
				name.append(obj[1].toString()).append(" ");
			}
			if(obj[2]!=null){
				name.append(obj[2].toString());
			}
			mandalAllElectionDetailsVO.setPartyShortName(obj[6].toString());
			mandalAllElectionDetailsVO.setCandidateName(name.toString());
			mandalAllElectionDetailsVO.setPartyId(new Long(obj[5].toString()));
			Long totalPartyVotes = new Long(obj[4].toString());
			String partyPercentage = calculateVotesPercengate(mandalAllElectionDetailsVO.getValidVoters(),totalPartyVotes);//getTotalVoters();
			mandalAllElectionDetailsVO.setPartyVotesPercentage(partyPercentage);
		}
		
		getMPTCDetailsForMandal(tehsilID, partyID, mandalAllElectionDetails);
		Collections.sort(mandalAllElectionDetails, new MandalVotingTrendsComparator());
		return mandalAllElectionDetails;
	}
	/**
	 * checking for rebels in the the List temp and sending the alliance party handler
	 * @param temp
	 * @param partyID
	 * @return
	 */
	private int getAlliancePartyInfo(List temp, Long partyID){
		int result = 0;
		/*if(temp.size()==0)
			return 0;*/
		long partyVotes = 0;
		for(int i=0; i<temp.size(); i++){
			Object[] obj = (Object[]) temp.get(i);
			if(partyID.equals(obj[5]))
				return i;
			long presentPartyVotes = new Long(obj[4].toString()).longValue();
			
			if(presentPartyVotes>partyVotes){
				partyVotes=presentPartyVotes;
				result = i;
			}						
		}
		return result;	
	}
	
	/**
	 * getting All the details of MPTC candidates Participated In A Mandal For All Election Years 
	 * 
	 */
	public void getMPTCDetailsForMandal(Long tehsilID, Long partyId, List<MandalAllElectionDetailsVO> mandalAllElectionDetails){
		ResultStatus resultVO = new ResultStatus();
		try{
			List mptcInfo = nominationDAO.findMPTCInfoByElectionTypeTehsilAndParty(tehsilID, partyId);
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO = null;
			String partyName = "";
			String electionYear = "";
			Double totalVoters = null;
			Double validVotes = null;
			Double votesGainedByParty = null;
			String partyVotesPercentage = "";
			String candidateName = "";
			String electionType = "";
			for(int i=0; i < mptcInfo.size(); i++){
				mandalAllElectionDetailsVO = new MandalAllElectionDetailsVO();
				Object[] values = (Object[]) mptcInfo.get(i);
				partyName = (String)values[0];
				electionYear = (String)values[1];
				totalVoters = (Double)values[2];
				validVotes = (Double)values[3];
				votesGainedByParty = (Double)values[4];
				candidateName = (String)values[5];
				electionType = (String)values[6];
				
				if(validVotes == null || validVotes == 0){
					List votes = nominationDAO.findValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear(IConstants.MPTC_ELECTION_TYPE, electionYear, tehsilID);
					validVotes = (Double)votes.get(0);
				}
				if(totalVoters == null)
					totalVoters = 0D;
				if(validVotes == null)
					validVotes = 0D;
				if(validVotes == null)
					validVotes = 0D;
				partyVotesPercentage = calculateVotesPercengate(validVotes.longValue(), votesGainedByParty.longValue());
				
				if(electionType.equalsIgnoreCase(IConstants.ZPTC)){
					mandalAllElectionDetailsVO.setCandidateName(candidateName);
					mandalAllElectionDetailsVO.setElectionType(IConstants.ZPTC_ELECTION_TYPE);
				}
				else{
					mandalAllElectionDetailsVO.setCandidateName(IConstants.MPTC_ELECTION_TYPE);
					mandalAllElectionDetailsVO.setElectionType(IConstants.MPTC_ELECTION_TYPE);
				}				
				mandalAllElectionDetailsVO.setPartyShortName(partyName);
				mandalAllElectionDetailsVO.setElectionYear(electionYear);
				mandalAllElectionDetailsVO.setTotalVoters(totalVoters.longValue());
				mandalAllElectionDetailsVO.setValidVoters(validVotes.longValue());
				mandalAllElectionDetailsVO.setPartyVotesPercentage(partyVotesPercentage);
				mandalAllElectionDetails.add(mandalAllElectionDetailsVO);
			}
		}catch(IndexOutOfBoundsException ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultVO.setResultPartial(true);
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
		}
				
	}
	
	
	public String calculateVotesPercengate(Long validVotes, Long votesEarned){
		BigDecimal percentage = new BigDecimal(0.0);
		if((validVotes!=null && validVotes.longValue()>0) && (votesEarned!=null && votesEarned.longValue()>0)){
			percentage= new BigDecimal((votesEarned.floatValue()/validVotes.floatValue())*100).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return percentage.toString();
	}
	
	public String getAlliancePartyIds(String electionYear, Long electionTypeId, Long partyId, boolean hasAlliance){
		StringBuilder sb = new StringBuilder();
		if(hasAlliance){
			List<SelectOptionVO> partyIDs = staticDataService.getAlliancePartiesAsVO(electionYear, electionTypeId, partyId,0L);
			if(partyIDs==null){
				sb.append(",").append(partyId);
			}else{	
				for(SelectOptionVO obj : partyIDs){
					sb.append(",").append(obj.getId());
				}
			} 
		}else{
			sb.append(",").append(partyId);
		} 
		return sb.toString().substring(1);
	}
	
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResult(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage)
	{
		try
		{
			if(performanceVO != null)
			{
				Map<String,List<BoothResultVO>> resultMap = new LinkedHashMap<String,List<BoothResultVO>>();
				
				resultMap.put("Below-5", new ArrayList<BoothResultVO>(0));
				resultMap.put("5-10", new ArrayList<BoothResultVO>(0));
				resultMap.put("10-20", new ArrayList<BoothResultVO>(0));
				resultMap.put("20-30", new ArrayList<BoothResultVO>(0));
				resultMap.put("30-40", new ArrayList<BoothResultVO>(0));
				resultMap.put("40-50", new ArrayList<BoothResultVO>(0));
				resultMap.put("50-60", new ArrayList<BoothResultVO>(0));
				resultMap.put("60-70", new ArrayList<BoothResultVO>(0));
				resultMap.put("70-80", new ArrayList<BoothResultVO>(0));
				resultMap.put("80-90", new ArrayList<BoothResultVO>(0));
				resultMap.put("Above-90", new ArrayList<BoothResultVO>(0));
				
				for(BoothResultVO boothResultVO :performanceVO.getBoothResults())
				{
					Double percentage = null;
					if(isPollingPercentage)
						percentage = Double.parseDouble(boothResultVO.getPollingPercentage());
					else
						percentage = Double.parseDouble(boothResultVO.getPercentage());
					
					if(percentage >= 0 && percentage < 5)
					{
						List<BoothResultVO> boothList = resultMap.get("Below-5");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("Below-5", boothList);
					}
					else if(percentage >= 5 && percentage < 10)
					{
						List<BoothResultVO> boothList = resultMap.get("5-10");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("5-10", boothList);
					}
					else if(percentage >= 10 && percentage < 20)
					{
						List<BoothResultVO> boothList = resultMap.get("10-20");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("10-20", boothList);
					}
					else if(percentage >= 20 && percentage < 30)
					{
						List<BoothResultVO> boothList = resultMap.get("20-30");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("20-30", boothList);
					}
					else if(percentage >= 30 && percentage < 40)
					{
						List<BoothResultVO> boothList = resultMap.get("30-40");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("30-40", boothList);
					}
					else if(percentage >= 40 && percentage < 50)
					{
						List<BoothResultVO> boothList = resultMap.get("40-50");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("40-50", boothList);
					}
					else if(percentage >= 50 && percentage < 60)
					{
						List<BoothResultVO> boothList = resultMap.get("50-60");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("50-60", boothList);
					}
					else if(percentage >= 60 && percentage < 70)
					{
						List<BoothResultVO> boothList = resultMap.get("60-70");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("60-70", boothList);
					}
					else if(percentage >= 70 && percentage < 80)
					{
						List<BoothResultVO> boothList = resultMap.get("70-80");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("70-80", boothList);
					}
					else if(percentage >= 80 && percentage < 90)
					{
						List<BoothResultVO> boothList = resultMap.get("80-90");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("80-90", boothList);
					}
					else if(percentage >= 90)
					{
						List<BoothResultVO> boothList = resultMap.get("Above-90");
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("Above-90", boothList);
					}
					
				}
				
				List<BoothResultVO> perWiseboothResults = new ArrayList<BoothResultVO>(0);
				BoothResultVO resultVO = null;
				
				for(Map.Entry<String,List<BoothResultVO>> entry : resultMap.entrySet())
				{
					resultVO = new BoothResultVO();
					resultVO.setLocation(entry.getKey());
					resultVO.setVotesEarned(entry.getValue().size());
					
					List<BoothResultVO> list = entry.getValue();
					double total = 0.0d;
					double earned = 0.0d;
					
					if(isPollingPercentage)
						for(BoothResultVO brVO : list)
						{
							total += brVO.getTotalVoters();
							earned += brVO.getVotesEarned();
						}
					else
						for(BoothResultVO brVO : list)
						{
							total += brVO.getTotalBoothVoters();
							earned += brVO.getTotalVoters();
						}
					
					if(list.size() > 0)
					resultVO.setPercentage((new BigDecimal((earned*100)/total).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
					else
					resultVO.setPercentage("--");
					
					perWiseboothResults.add(resultVO);
				}
				if(isPollingPercentage)
					performanceVO.setPerWiseboothResults(perWiseboothResults);
				else
					performanceVO.setPartyPerWiseboothResults(perWiseboothResults);
			}
		}
		catch(Exception e){
			log.error("Exception occured at "+e);
		}
		return performanceVO;
	}
	
	
}
