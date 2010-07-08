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
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.ConstituencyWiseDataForMandalVO;
import com.itgrids.partyanalyst.dto.ConstituencyWisePartyInfoVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyResultsInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.utils.ElectionResultTypeComparator;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SelectOptionVOComparator;

public class PartyBoothWiseResultsService implements IPartyBoothWiseResultsService{
	
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IElectionDAO electionDAO;
	private IAllianceGroupDAO allianceGroupDAO;
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
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear){
		System.out.println("In getBoothWiseResultsForParty::constituencyId, electionYear::"+constituencyId+","+electionYear);
		List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
		List<Nomination> nominations  = nominationDAO.findByConstituencyPartyAndElectionYear(partyId, constituencyId, electionYear);
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
			List<CandidateBoothResult> candidateboothResults = new ArrayList<CandidateBoothResult>(nomination.getCandidateBoothResults());
			System.out.println("In getBoothWiseResultsForParty::"+candidateboothResults.size());
			for(CandidateBoothResult candidateBoothResult:candidateboothResults){
				Booth booth =  candidateBoothResult.getBoothConstituencyElection().getBooth();
				int totalValidVotes = candidateBoothResult.getBoothConstituencyElection().getBoothResult().getValidVotes().intValue();
				int votesEarned = candidateBoothResult.getVotesEarned().intValue();
				String percentage = calculateVotesPercengate(new Double(totalValidVotes), new Double(votesEarned));	
				BoothResultVO boothResultVO = new BoothResultVO(booth.getPartNo(), booth.getLocation(), booth.getvillagesCovered(), votesEarned, totalValidVotes, percentage, booth.getTehsil().getTehsilName());
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
		ResultStatus resultStatus = new ResultStatus();
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
			boothPageInfo.setVillagesCovered(booth.getvillagesCovered());
			boothPageInfo.setMandal(booth.getTehsil().getTehsilName());
			
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
				electionInfoVO.setPartyResults(entry.getValue());
				electionsInBooth.add(electionInfoVO);
			}
			
			boothPageInfo.setElections(electionsInBooth);
			
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			if(log.isDebugEnabled())
				log.debug("Exception Encountered::", e);
		}
		return new ResultWithExceptionVO(boothPageInfo, resultStatus);
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
		
		for(PartyResultsVO objParty:allPartyResults){
			objParty.setPercentage(new BigDecimal((objParty.getVotesEarned()*100.0)/
					allPartiesVotesInMandal).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		}
		
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
		
		List alliance = null;
		List result = null;
		for(PartyResultVO party:allElectionResults){
			partyElecs1 = new HashSet<String>();
			partyPecentages = new ArrayList<Float>();
			for(ElectionResultVO ele:party.getElectionWiseResults()){
				ele.setElectionYearAndType(ele.getElectionYear()+" "+ele.getElectionType());
				result = electionDAO.findElectionIdByElectionTypeAndYear(ele.getElectionType(),ele.getElectionYear(),1l);					
				if(result.size() > 0)
					alliance = allianceGroupDAO.findAlliancePartiesByElectionAndParty(Long.parseLong(result.get(0).toString()),party.getPartyId());
				ele.setHasAlliance((alliance.size()>0 && result.size() > 0)?"true":"false");
				partyElecs1.add(ele.getElectionYear()+" "+ele.getElectionType());
				allElecTypes.add(ele.getElectionYear()+" "+ele.getElectionType());
				partyPecentages.add(new Float(ele.getPercentage()));
			}
			Collections.sort(partyPecentages);
			party.setRange(partyPecentages.get(0)+"-"+partyPecentages.get(partyPecentages.size()-1));
			party.setElections(partyElecs1);
		}
		
		for(String elecType:allElecTypes)
			electionsHeading.add(new SelectOptionVO(0l, elecType));
		
		Collections.sort(electionsHeading, new SelectOptionVOComparator());
		
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
			Collections.sort(elections, new ElectionResultTypeComparator());
			party1.setElectionWiseResults(elections);
		}
	
		Set<String> staticParties = new LinkedHashSet<String>();
		staticParties.add("INC");staticParties.add("TDP");staticParties.add("PRP");staticParties.add("TRS");staticParties.add("BJP");
		List<PartyResultVO> fixedParties = new ArrayList<PartyResultVO>();
		for(PartyResultVO party: allElectionResults)
			if(staticParties.contains(party.getPartyName()))
				fixedParties.add(party);
		
		electionWiseMandalPartyResultListVO.setElections(electionsHeading);
		electionWiseMandalPartyResultListVO.setAllPartiesAllElectionResults(fixedParties);
		return electionWiseMandalPartyResultListVO;
		
	}
	
}
