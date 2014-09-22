package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
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
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.BaseCandidateResultVO;
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
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;
import com.itgrids.partyanalyst.service.IPdfReportsService;
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
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	@Autowired
	IPdfReportsService pdfReportsService ;
	
	@Autowired
	IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
	
	@Autowired
	ILocalElectionBodyDAO localElectionBodyDAO;
	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}
	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
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
		List candidateWin=nominationDAO.findCandidateNamePartyByConstituencyAndElection(constituencyId.toString(), electionYear);
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
			partyBoothPerformanceVO.setRank(nomination.getCandidateResult().getRank());
			partyBoothPerformanceVO.setWonCandidate(candidateWin);
			partyBoothPerformanceVO.setMarginVotes(nomination.getCandidateResult().getMarginVotes().longValue());
			
			
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
				boothResultVO.setBoothId(booth.getBoothId());
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
				if(resultsForPartyInBooth.getMaleBoothResults() != null && resultsForPartyInBooth.getMaleBoothResults() < 5 && resultsForPartyInBooth.getFemaleBoothResults() < 5){
					if(i == 0)
						totalMaleOrFemaleVotersInConstiMandal += resultsForPartyInBooth.getTotalVotesEarned();
					totalFMBoothVotesForParty = totalFMBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();	
				}
				else if(resultsForPartyInBooth.getMaleBoothResults() != null && resultsForPartyInBooth.getMaleBoothResults() < 5){
					if(i == 0)
						totalFemaleVotersInConstiteuncyForMandal += resultsForPartyInBooth.getFemaleBoothResults();	
					totalFemaleBoothVotesForParty = totalFemaleBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
					
				}
				else if(resultsForPartyInBooth.getFemaleBoothResults() != null && resultsForPartyInBooth.getFemaleBoothResults() < 5){
					if(i == 0)
						totalMaleVotersInConstiteuncyForMandal += resultsForPartyInBooth.getMaleBoothResults();	
					totalMaleBoothVotesForParty = totalMaleBoothVotesForParty + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				}
				else{
					if(i == 0 && resultsForPartyInBooth.getTotalVotesEarned() != null)
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
		try{
		
		List<MandalAllElectionDetailsVO> tempList = null;
		
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
			
			/*List temp = boothConstituencyElectionDAO.getPartyVotesByMandal(tehsilID, sb.substring(1), 
					mandalAllElectionDetailsVO.getElectionID());*/
			List temp =  boothConstituencyElectionDAO.getPartyVotesByMandalWithRankDetails(tehsilID, sb.substring(1), 
					mandalAllElectionDetailsVO.getElectionID()); 
			//0-firstName, 1-middlename, 2-lastname, 3-election, 4-votesearned, 5-partyId, 6-shortName
			if(temp.size()==0){
				iterator.remove();
				continue;
			}
			
			Long count = 0L;
				for(int i=0; i<temp.size(); i++){
					Object[] obj = (Object[]) temp.get(i);
					Long presentPartyVotes = new Long(obj[4].toString()).longValue();
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
						if(count==0L){
							mandalAllElectionDetailsVO.setPartyShortName(obj[6].toString());
							mandalAllElectionDetailsVO.setCandidateName(name.toString());
							mandalAllElectionDetailsVO.setPartyId(new Long(obj[5].toString()));
							Long totalPartyVotes = new Long(obj[4].toString());
							String partyPercentage = calculateVotesPercengate(mandalAllElectionDetailsVO.getValidVoters(),totalPartyVotes);//getTotalVoters();
							
							mandalAllElectionDetailsVO.setTotalVotersEarned(totalPartyVotes);
							mandalAllElectionDetailsVO.setRank(obj[7].toString());
							
							mandalAllElectionDetailsVO.setPartyVotesPercentage(partyPercentage);	
							
						}
						else{
							tempList = new ArrayList<MandalAllElectionDetailsVO>();
							MandalAllElectionDetailsVO detailsVO = new MandalAllElectionDetailsVO();
							detailsVO.setElectionYear(mandalAllElectionDetailsVO.getElectionYear());
							detailsVO.setElectionType(mandalAllElectionDetailsVO.getElectionType());
							detailsVO.setElectionTypeID(mandalAllElectionDetailsVO.getElectionTypeID());
							detailsVO.setTotalVoters(mandalAllElectionDetailsVO.getTotalVoters());
							detailsVO.setValidVoters(mandalAllElectionDetailsVO.getValidVoters());
							detailsVO.setRejectedVoters(mandalAllElectionDetailsVO.getRejectedVoters());
							detailsVO.setTenderedVoters(mandalAllElectionDetailsVO.getTenderedVoters());
							detailsVO.setElectionScopeID(mandalAllElectionDetailsVO.getElectionScopeID());
							detailsVO.setElectionID(mandalAllElectionDetailsVO.getElectionID());
							detailsVO.setPartyShortName(obj[6].toString());
							detailsVO.setCandidateName(name.toString());
							detailsVO.setPartyId(new Long(obj[5].toString()));
							Long totalPartyVotes = new Long(obj[4].toString());
							String partyPercentage = calculateVotesPercengate(mandalAllElectionDetailsVO.getValidVoters(),totalPartyVotes);//getTotalVoters();
							
							detailsVO.setTotalVotersEarned(totalPartyVotes);
							detailsVO.setRank(obj[7].toString());
							
							detailsVO.setPartyVotesPercentage(partyPercentage);
							
							tempList.add(detailsVO);
							
						}
						count ++;
							
				}
			
			/* int i = getAlliancePartyInfo(temp, partyID);
			
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
			
			mandalAllElectionDetailsVO.setTotalVotersEarned(totalPartyVotes);
			mandalAllElectionDetailsVO.setRank(obj[7].toString());
			
			mandalAllElectionDetailsVO.setPartyVotesPercentage(partyPercentage);*/

		}
		
		if(tempList != null && tempList.size() > 0)
			mandalAllElectionDetails.addAll(tempList);
		
		getMPTCDetailsForMandal(tehsilID, partyID, mandalAllElectionDetails);
		Collections.sort(mandalAllElectionDetails, new MandalVotingTrendsComparator());
		return mandalAllElectionDetails;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getMandalAllElectionDetails() method, Exception - "+e);
			return mandalAllElectionDetails;
		}
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
			//List mptcInfo = nominationDAO.findMPTCInfoByElectionTypeTehsilAndParty(tehsilID, partyId);
			List mptcInfo = nominationDAO.findMPTCInfoByElectionTypeTehsilAndPartyWithGainedVotes(tehsilID, partyId);
			
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO = null;
			String partyName = "";
			String electionYear = "";
			Double totalVoters = null;
			Double validVotes = null;
			Double votesGainedByParty = null;
			String partyVotesPercentage = "";
			String candidateName = "";
			String electionType = "";
			Double votersEarned = null;
			
			for(int i=0; i < mptcInfo.size(); i++){
				mandalAllElectionDetailsVO = new MandalAllElectionDetailsVO();
				Object[] values = (Object[]) mptcInfo.get(i);
				partyName = (String)values[0];
				electionYear = (String)values[1];
				//totalVoters = (Double)values[2];
				validVotes = (Double)values[3];
				votesGainedByParty = (Double)values[4];
				candidateName = (String)values[5];
				electionType = (String)values[6];
				votersEarned = (Double)values[7];
				
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
					mandalAllElectionDetailsVO.setCandidateName(candidateName);
					mandalAllElectionDetailsVO.setElectionType(IConstants.MPTC_ELECTION_TYPE);
				}				
				mandalAllElectionDetailsVO.setPartyShortName(partyName);
				mandalAllElectionDetailsVO.setElectionYear(electionYear);
				mandalAllElectionDetailsVO.setTotalVoters(totalVoters.longValue());
				mandalAllElectionDetailsVO.setValidVoters(validVotes.longValue());
				mandalAllElectionDetailsVO.setPartyVotesPercentage(partyVotesPercentage);
				mandalAllElectionDetailsVO.setTotalVotersEarned(votersEarned.longValue());
				mandalAllElectionDetailsVO.setRank(values[8].toString());
				mandalAllElectionDetails.add(mandalAllElectionDetailsVO);
			}
			
			
			Map<String , Double> map = new HashMap<String, Double>();
			Map<String , Integer> noOfRecords = new HashMap<String, Integer>();
			
			Long earnedVotes = 0L;
			
			for(MandalAllElectionDetailsVO vo:mandalAllElectionDetails)
			{
				if(vo.getElectionType().equalsIgnoreCase(IConstants.MPTC))
				{
					Double count = 0.0;
					if(map.get(vo.getElectionYear()) != null)
					{
						count = map.get(vo.getElectionYear()) ;					
						map.put(vo.getElectionYear(), count + Double.parseDouble(vo.getPartyVotesPercentage()));
						
						noOfRecords.put(vo.getElectionYear(), noOfRecords.get(vo.getElectionYear()) +1);
					}else{
						map.put(vo.getElectionYear(), Double.parseDouble(vo.getPartyVotesPercentage()));
						
						noOfRecords.put(vo.getElectionYear(), 1);

					}
					
				}
			}
			
			
			
			for(MandalAllElectionDetailsVO vo:mandalAllElectionDetails)
			{	
				if(vo.getElectionType().equalsIgnoreCase(IConstants.MPTC))
				{
					Double percent = map.get(vo.getElectionYear()) / noOfRecords.get(vo.getElectionYear());
				     vo.setVotesGainedPercent(percent.toString());
				}
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
	
	public PartyBoothPerformanceVO getVotingPercentagWiseBoothResult(List<PartyBoothPerformanceVO> performanceVOList,boolean isPollingPercentage,String path)
	{
		
			PartyBoothPerformanceVO partyBoothPerformance = new PartyBoothPerformanceVO();
		
			if(performanceVOList != null && performanceVOList.size()>0)
			{
				for (PartyBoothPerformanceVO performanceVO : performanceVOList) 
				{
					
					try
					{
						
						if(performanceVO != null)
						{
							PartyBoothPerformanceVO partyPerformanceVO = new PartyBoothPerformanceVO();
							
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
								{
									if(boothResultVO != null && boothResultVO.getPollingPercentage() != null)
									percentage = Double.parseDouble(boothResultVO.getPollingPercentage());
								}
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
							Map<String,List<SelectOptionVO>> boothsMap=new HashMap<String, List<SelectOptionVO>>();
							for(Map.Entry<String,List<BoothResultVO>> entry : resultMap.entrySet())
							{
								resultVO = new BoothResultVO();
								resultVO.setLocation(entry.getKey());
								resultVO.setVotesEarned(entry.getValue().size());
								
								List<BoothResultVO> list = entry.getValue();
								double total = 0.0d;
								double earned = 0.0d;
						
								List<SelectOptionVO> booths=new ArrayList<SelectOptionVO>();
								if(isPollingPercentage)
									for(BoothResultVO brVO : list)
									{
										SelectOptionVO boothsdtllist=new SelectOptionVO();
										total += brVO.getTotalVoters();
										earned += brVO.getVotesEarned();
										boothsdtllist.setPartno(brVO.getPartNo());
										boothsdtllist.setId(brVO.getBoothId());
										String loc=brVO.getLocation();
										boothsdtllist.setLocation(loc.replace("'"," ").replace("\"", " ").replace("\r", ""));
										String vill_co=brVO.getVillagesCovered();
										String villages_co=vill_co.replace("'"," ").replace("\"", " ").replace("\r", " ");
										boothsdtllist.setVillageCovered(villages_co);
										booths.add(boothsdtllist);
									}
								else
									for(BoothResultVO brVO : list)
									{
										SelectOptionVO boothsdtllist=new SelectOptionVO();
										total += brVO.getTotalBoothVoters();
										earned += brVO.getTotalVoters();
										boothsdtllist.setPartno(brVO.getPartNo());
										boothsdtllist.setId(brVO.getBoothId());
										String loc=brVO.getLocation();
										boothsdtllist.setLocation(loc.replace("'"," ").replace("\"", " ").replace("\r", ""));
										String vill_co=brVO.getVillagesCovered();
										String villages_co=vill_co.replace("'"," ").replace("\"", " ").replace("\r", "");
										boothsdtllist.setVillageCovered(villages_co);
										//boothsdtllist.setVillageCovered((brVO.getVillagesCovered()).replace("'"," ").replace("\"", " ").replace("\\r|\\n", ""));
										booths.add(boothsdtllist);
									}
								
								if(list.size() > 0){
									if(total > 0)
								resultVO.setPercentage((new BigDecimal((earned*100)/total).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
								boothsMap.put(entry.getKey(), booths);
								}
								else
								resultVO.setPercentage("--");
								
								perWiseboothResults.add(resultVO);
							}
							if(isPollingPercentage){
								partyPerformanceVO.setPartyName(performanceVO.getPartyName());
								partyPerformanceVO.setPerWiseboothResults(perWiseboothResults);
								partyPerformanceVO.setBoothsMap(boothsMap);
							}
							else{
								partyPerformanceVO.setPartyName(performanceVO.getPartyName());
								partyPerformanceVO.setPartyPerWiseboothResults(perWiseboothResults);
								partyPerformanceVO.setBoothsMap1(boothsMap);
							}
							
							partyBoothPerformance.getPartyBoothPerformanceVOList().add(partyPerformanceVO);
						}
							
					}					
				catch(Exception e){
					log.error("Exception occured at "+e);
				}
					if(path != null)
					{
						if(performanceVO != null)
						{
							Document document = null;
							  try
							  {
								   document = new Document();
			
					    			//Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
					    	    	String constituenyName =performanceVO.getConstituencyName().toUpperCase();
					    	    	//String districtName = values[1].toString().toUpperCase();
					    	    	//Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
					    		    String filePath = "VMR"+"/"+constituenyName+""+performanceVO.getPartyName()+" Booth Result.pdf";
					    		    String FILE = path+filePath;
					    		    File file  = new File(FILE);
					    		    file.createNewFile();
					    		    PdfWriter.getInstance(document, new FileOutputStream(FILE));
					    			document.open();
					    			pdfReportsService.generatepdfForBoothResult(document,performanceVO.getBoothResults());
					    			document.close();

					    			
					    			performanceVO.setUrl(filePath);
					    			
							  } 
							  catch (Exception e)
							  {
								
							  }
							  finally
							  {
								  if(document != null)
								  document.close();
							  }
						}
					
						  
					}
					
			}
		}
		
		return partyBoothPerformance;
	}
	
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResult(PartyBoothPerformanceVO performanceVO,boolean isPollingPercentage,String path)
	{
		try
		{
			if(performanceVO != null)
			{
				Map<String,List<BoothResultVO>> resultMap = new LinkedHashMap<String,List<BoothResultVO>>();
				//Map<String,Long> partyCountMap = new HashMap<String, Long>();
				Map<String,Map<String,Long>> partyWiseCountInRangeMap = new HashMap<String, Map<String,Long>>();
				Map<String,Long> partyCountMap = new HashMap<String, Long>();
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
					{
						if(boothResultVO != null && boothResultVO.getPollingPercentage() != null)
						percentage = Double.parseDouble(boothResultVO.getPollingPercentage());
					}
					else
						percentage = Double.parseDouble(boothResultVO.getPercentage());
					
					Long partyCount = 1L;
					if(percentage >= 0 && percentage < 5)
					{
						List<BoothResultVO> boothList = resultMap.get("Below-5");

						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("Below-5", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("Below-5") == null)
							{
								partyCountMap.put("Below-5", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("Below-5");
								partyCountMap.put("Below-5", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
							
												
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("Below-5", boothList);
					}
					else if(percentage >= 5 && percentage < 10)
					{
						List<BoothResultVO> boothList = resultMap.get("5-10");


						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("5-10", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("5-10") == null)
							{
								partyCountMap.put("5-10", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("5-10");
								partyCountMap.put("5-10", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
							
												
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("5-10", boothList);
					}
					else if(percentage >= 10 && percentage < 20)
					{
						List<BoothResultVO> boothList = resultMap.get("10-20");
						


						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("10-20", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("10-20") == null)
							{
								partyCountMap.put("10-20", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("10-20");
								partyCountMap.put("10-20", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
							
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("10-20", boothList);
					}
					else if(percentage >= 20 && percentage < 30)
					{
						List<BoothResultVO> boothList = resultMap.get("20-30");


						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("20-30", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("20-30") == null)
							{
								partyCountMap.put("20-30", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("20-30");
								partyCountMap.put("20-30", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
							
						
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("20-30", boothList);
					}
					else if(percentage >= 30 && percentage < 40)
					{
						List<BoothResultVO> boothList = resultMap.get("30-40");


						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("30-40", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("30-40") == null)
							{
								partyCountMap.put("30-40", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("30-40");
								partyCountMap.put("30-40", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
												
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("30-40", boothList);
					}
					else if(percentage >= 40 && percentage < 50)
					{
						List<BoothResultVO> boothList = resultMap.get("40-50");
						
						

						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("40-50", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("40-50") == null)
							{
								partyCountMap.put("40-50", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("40-50");
								partyCountMap.put("40-50", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}	
						
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("40-50", boothList);
					}
					else if(percentage >= 50 && percentage < 60)
					{
						List<BoothResultVO> boothList = resultMap.get("50-60");
										

						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("50-60", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("50-60") == null)
							{
								partyCountMap.put("50-60", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("50-60");
								partyCountMap.put("50-60", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						
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

						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("70-80", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("70-80") == null)
							{
								partyCountMap.put("70-80", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("70-80");
								partyCountMap.put("70-80", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("70-80", boothList);
					}
					else if(percentage >= 80 && percentage < 90)
					{
						List<BoothResultVO> boothList = resultMap.get("80-90");
						
						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("80-90", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("80-90") == null)
							{
								partyCountMap.put("80-90", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("80-90");
								partyCountMap.put("80-90", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						
						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("80-90", boothList);
					}
					else if(percentage >= 90)
					{
						List<BoothResultVO> boothList = resultMap.get("Above-90");


						if(partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null)
						{
							partyCountMap = new HashMap<String, Long>();
							partyCountMap.put("Above-90", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						else
						{
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());
							
							if(partyCountMap.get("Above-90") == null)
							{
								partyCountMap.put("Above-90", partyCount);
							}
							else
							{
								partyCount = partyCountMap.get("Above-90");
								partyCountMap.put("Above-90", partyCount+1);
							}
							
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(), partyCountMap);
						}
						

						if(boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("Above-90", boothList);
					}
					
				}
				
				List<BoothResultVO> perWiseboothResults = new ArrayList<BoothResultVO>(0);
				BoothResultVO resultVO = null;
				Map<String,List<SelectOptionVO>> boothsMap=new HashMap<String, List<SelectOptionVO>>();
				for(Map.Entry<String,List<BoothResultVO>> entry : resultMap.entrySet())
				{
					resultVO = new BoothResultVO();
					resultVO.setLocation(entry.getKey());
					resultVO.setVotesEarned(entry.getValue().size());
					resultVO.setMessage(performanceVO.getPartyName());
					List<BoothResultVO> list = entry.getValue();

					List<BoothResultVO> boothResultVOList1 = null;
					if(partyWiseCountInRangeMap != null && partyWiseCountInRangeMap.size()>0)
					{
						boothResultVOList1 = new ArrayList<BoothResultVO>();
						for (String partyName : partyWiseCountInRangeMap.keySet()) 
						{
								Map<String,Long> tempMap = partyWiseCountInRangeMap.get(partyName);
								if(tempMap.get(entry.getKey()) != null)
								{
									BoothResultVO vo = new BoothResultVO();
									vo.setWonParty(partyName);
									vo.setResultState(tempMap.get(entry.getKey()));
									
									boothResultVOList1.add(vo);
								}
						}
						
						resultVO.setBoothResultVOList(boothResultVOList1);
					}
					
					double total = 0.0d;
					double earned = 0.0d;
					
					
					List<SelectOptionVO> booths=new ArrayList<SelectOptionVO>();
					if(isPollingPercentage)
						for(BoothResultVO brVO : list)
						{							
							SelectOptionVO boothsdtllist=new SelectOptionVO();
							total += brVO.getTotalVoters();
							earned += brVO.getVotesEarned();
							boothsdtllist.setPartno(brVO.getPartNo());
							boothsdtllist.setId(brVO.getBoothId());
							String loc=brVO.getLocation();
							boothsdtllist.setLocation(loc.replace("'"," ").replace("\"", " ").replace("\r", ""));
							String vill_co=brVO.getVillagesCovered();
							String villages_co=vill_co.replace("'"," ").replace("\"", " ").replace("\r", " ");
							boothsdtllist.setVillageCovered(villages_co);
							booths.add(boothsdtllist);
						}
					else
						for(BoothResultVO brVO : list)
						{
							SelectOptionVO boothsdtllist=new SelectOptionVO();
							total += brVO.getTotalBoothVoters();
							earned += brVO.getTotalVoters();
							boothsdtllist.setPartno(brVO.getPartNo());
							boothsdtllist.setId(brVO.getBoothId());
							String loc=brVO.getLocation();
							boothsdtllist.setLocation(loc.replace("'"," ").replace("\"", " ").replace("\r", ""));
							String vill_co=brVO.getVillagesCovered();
							String villages_co=vill_co.replace("'"," ").replace("\"", " ").replace("\r", "");
							boothsdtllist.setVillageCovered(villages_co);
							//boothsdtllist.setVillageCovered((brVO.getVillagesCovered()).replace("'"," ").replace("\"", " ").replace("\\r|\\n", ""));
							booths.add(boothsdtllist);
						}
					
					if(list.size() > 0){
						if(total > 0)
					resultVO.setPercentage((new BigDecimal((earned*100)/total).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
					boothsMap.put(entry.getKey(), booths);
					}
					else
					resultVO.setPercentage("--");
					
					perWiseboothResults.add(resultVO);
				}
				if(isPollingPercentage){
					performanceVO.setPerWiseboothResults(perWiseboothResults);
				    performanceVO.setBoothsMap(boothsMap);
				}
				else{
					performanceVO.setPartyPerWiseboothResults(perWiseboothResults);
					performanceVO.setBoothsMap1(boothsMap);
				}
			}
		}
		catch(Exception e){
			log.error("Exception occured at "+e);
		}
			if(path != null)
			{
				if(performanceVO != null)
				{
					Document document = null;
					  try
					  {
						   document = new Document();
	
			    			//Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
			    	    	String constituenyName =performanceVO.getConstituencyName().toUpperCase();
			    	    	//String districtName = values[1].toString().toUpperCase();
			    	    	//Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
			    		    String filePath = "VMR"+"/"+constituenyName+" Booth Result.pdf";
			    		    String FILE = path+filePath;
			    		    File file  = new File(FILE);
			    		    file.createNewFile();
			    		    PdfWriter.getInstance(document, new FileOutputStream(FILE));
			    			document.open();
			    			pdfReportsService.generatepdfForBoothResult(document,performanceVO.getBoothResults());
			    			document.close();

			    			
			    			performanceVO.setUrl(filePath);
			    			
					  } 
					  catch (Exception e)
					  {
						
					  }
					  finally
					  {
						  if(document != null)
						  document.close();
					  }
				}
			
				  
			}
		return performanceVO;
	}
	
	
	public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId,List<String> electionYear)
	{
		String electionYear1 = (String) electionYear.get(0);
		System.out.println("In getBoothWiseResultsForParty::constituencyId, electionYear::"+constituencyId+","+electionYear1);
		List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
		
		List<Long> constiIds = new ArrayList<Long>();
		List<DelimitationConstituency> parliConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyId,Long.valueOf(electionYear1));
		
		if(parliConstituency != null && parliConstituency.size()>0)
		{
			DelimitationConstituency delimitationConstituency = (DelimitationConstituency) parliConstituency.get(0);
			List<Constituency> assemblList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesByDelimitationConstituencyId(delimitationConstituency.getDelimitationConstituencyID());
			
			if(assemblList != null && assemblList.size()>0)
			{

				for (Constituency constituency : assemblList)
				{
					constiIds.add(constituency.getConstituencyId());
				}
			}
			else
			{
				constiIds.add(constituencyId);
			}
			
		}
		else
		{
			constiIds.add(constituencyId);
		}
		
		List<Long> electionIds = electionDAO.getElectionDetailsByYearAndElectionType(electionYear1, IConstants.ASSEMBLY_ELECTION_TYPE_ID,1L);
		List<Object[]> wincandidatesList = nominationDAO.findWonCandidateInConstituency(constiIds,electionYear1,electionIds);
		
		List<BaseCandidateResultVO> basicWonCandidateVOList = new ArrayList<BaseCandidateResultVO>(0);
		
		if(wincandidatesList != null && wincandidatesList.size()>0)
		{
			for (Object[] param : wincandidatesList) 
			{
				BaseCandidateResultVO vo = new BaseCandidateResultVO();
				
				vo.setCandidateId(param[0] != null ?(Long) param[0]:0L);
				vo.setCandidateName(param[1] != null ? param[1].toString():"");
				vo.setPartyId(param[2] != null ?(Long) param[2]:0L);
				vo.setPartyName(param[3] != null ? param[3].toString():"");
				vo.setReservation(param[4] != null ? param[4].toString():"");
				vo.setVotesEarned(param[5] != null ? Double.valueOf(param[5].toString()):Double.valueOf("0"));
				vo.setMarginVotes(param[6] != null ? Double.valueOf(param[6].toString()):Double.valueOf("0"));
				vo.setVotesPercengate(param[7] != null ? param[7].toString():"0");
				vo.setRank(1L);
				vo.setWonStatus("won");
				
				basicWonCandidateVOList.add(vo);
			}
		}
		
		List<Object[]> boothWiseWonPartyList = candidateBoothResultDAO.findboothWiseResultsForNominators(constituencyId,electionIds);
		
		Map<Long,String> boothwiseWonPartyMap = new HashMap<Long, String>();
		
		if(boothWiseWonPartyList != null && boothWiseWonPartyList.size()>0)
		{
			for (int i=0; i<boothWiseWonPartyList.size();i++ ) 
			{
				Object[] param = boothWiseWonPartyList.get(i);
				if(boothwiseWonPartyMap.get(param[1] != null ? Long.valueOf(param[1].toString().trim()):0L) == null)
				{
					boothwiseWonPartyMap.put(param[1] != null ? Long.valueOf(param[1].toString().trim()):0L, param[2] != null ? param[2].toString():"");
				}
			
			}
		}
			
			
		//List<Object[]> nominations  = nominationDAO.findByConstituencyPartyAndElectionsYears(partyIds, constiIds, electionYear1);
		
		List<Object[]> nominations  = nominationDAO.findByConstituencyPartyInfoAndElectionsYears(partyIds, constiIds, electionYear1);
		//List candidateWin=nominationDAO.findCandidateNamePartyByConstituenciAndElections(constituencyId.toString(), electionYears);
		//List candidateWin=nominationDAO.findCandidateNamesPartyByConstituenciAndElections(constiIds, electionYear1);
		
		BoothResultVO boothResultVO = null;
		if(nominations != null && nominations.size()>0)
		{
			//Map<Long,String> percentageBoothWiseMap = new HashMap<Long, String>();
			//Map<Long,String> partycountBoothWiseMap = new HashMap<Long, String>();
			
			for(Object[] param:nominations)
			{
				//Nomination nomination = param[1] != null ? (Nomination) param[1]:null;

					List<BoothResultVO> boothResultVOs = new ArrayList<BoothResultVO>();
					PartyBoothPerformanceVO partyBoothPerformanceVO = new PartyBoothPerformanceVO();
					
					partyBoothPerformanceVO.setPartyName(param[1] != null ? param[1].toString():"");
					partyBoothPerformanceVO.setCandidateName(param[2] != null ? param[2].toString():"");
					partyBoothPerformanceVO.setConstituencyName(param[3] != null ? param[3].toString():"");
					partyBoothPerformanceVO.setElectionType(param[4] != null ? param[4].toString():"");
					partyBoothPerformanceVO.setElectionYear(param[5] != null ? param[5].toString():"");
					partyBoothPerformanceVO.setTotalVotes(param[14] != null ? Double.valueOf(param[14].toString()).longValue():0L);
					partyBoothPerformanceVO.setTotalValidVotes(param[7] != null ? Double.valueOf(param[7].toString()).intValue():0);
					partyBoothPerformanceVO.setVotingPercentage(param[8] != null ? param[8].toString():"");
					partyBoothPerformanceVO.setVotesGained(param[9] != null ? Double.valueOf(param[9].toString()).intValue():0);
					partyBoothPerformanceVO.setPercentage(param[10] != null ? param[10].toString():"");
					partyBoothPerformanceVO.setRank(param[11] != null ? Long.valueOf(param[11].toString()):0L);
					partyBoothPerformanceVO.setWonCandidate(basicWonCandidateVOList);
					partyBoothPerformanceVO.setMarginVotes(param[12] != null ? Double.valueOf(param[12].toString()).longValue():0L);
					
					/*
					partyBoothPerformanceVO.setPartyName(nomination.getParty().getShortName() != null ? nomination.getParty().getShortName():"");
					partyBoothPerformanceVO.setCandidateName(nomination.getCandidate().getLastname() != null ? nomination.getCandidate().getLastname() :"");
					partyBoothPerformanceVO.setConstituencyName(nomination.getConstituencyElection().getConstituency().getName() != null ?nomination.getConstituencyElection().getConstituency().getName() :"");
					partyBoothPerformanceVO.setElectionType(nomination.getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType() != null ? nomination.getConstituencyElection().getElection().getElectionScope().getElectionType().getElectionType():"");
					partyBoothPerformanceVO.setElectionYear(nomination.getConstituencyElection().getElection().getElectionYear() != null ? nomination.getConstituencyElection().getElection().getElectionYear() :"");
					partyBoothPerformanceVO.setVotesGained(nomination.getCandidateResult().getVotesEarned() != null ? nomination.getCandidateResult().getVotesEarned().intValue():0);
					partyBoothPerformanceVO.setTotalValidVotes(nomination.getConstituencyElection().getConstituencyElectionResult().getValidVotes() != null ?nomination.getConstituencyElection().getConstituencyElectionResult().getValidVotes().intValue():0);
					partyBoothPerformanceVO.setPercentage(nomination.getCandidateResult().getVotesPercengate() != null ? nomination.getCandidateResult().getVotesPercengate():"");
		        	partyBoothPerformanceVO.setTotalVotes(nomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotes() != null ? nomination.getConstituencyElection().getConstituencyElectionResult().getTotalVotes().longValue(): 0L);
					partyBoothPerformanceVO.setVotingPercentage(nomination.getConstituencyElection().getConstituencyElectionResult().getVotingPercentage() != null ? nomination.getConstituencyElection().getConstituencyElectionResult().getVotingPercentage():"");
					partyBoothPerformanceVO.setRank(nomination.getCandidateResult().getRank() != null ? nomination.getCandidateResult().getRank():0L);
					partyBoothPerformanceVO.setWonCandidate(basicWonCandidateVOList);
					partyBoothPerformanceVO.setMarginVotes(nomination.getCandidateResult().getMarginVotes().longValue());
					*/
					
					
					//List<CandidateBoothResult> candidateboothResults = new ArrayList<CandidateBoothResult>(nomination.getCandidateBoothResults() != null ? nomination.getCandidateBoothResults():null);
					
					List<Object[]> candidateboothResults = candidateBoothResultDAO.findboothWiseResultsForCandidate(constituencyId,param[13] != null ? Long.valueOf(param[13].toString()):0L);
					System.out.println("In getBoothWiseResultsForParty::"+candidateboothResults.size());
					
					if(candidateboothResults != null && candidateboothResults.size()>0)
					{
						for(Object[] param1:candidateboothResults)
						{
								int totalValidVotes = param1[4] != null ? Integer.valueOf(param1[4].toString()) :0;
								int votesEarned =  param1[8] != null ? Integer.valueOf(param1[8].toString()) :0;
								int totalVoters = param1[3] != null ? Integer.valueOf(param1[3].toString()) :0;
								
								String percentage  = calculateVotesPercengate(new Double(totalValidVotes), new Double(votesEarned));	
								String pollPercent = calculateVotesPercengate(new Double(totalVoters),new Double(totalValidVotes));
								
							/*	if(percentageBoothWiseMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()) :0L)== null)
								{
									percentageBoothWiseMap.put(param1[1] != null ? Long.valueOf(param1[1].toString().trim()) :0L, percentage); // boothPercentage
									partycountBoothWiseMap.put(param1[1] != null ? Long.valueOf(param1[1].toString().trim()) :0L, param[1] != null ? param[1].toString():"");
								}
								else
								{
									String existingPercentage = percentageBoothWiseMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()) :0L);
									
									if(Double.valueOf(existingPercentage) < Double.valueOf(percentage))
									{
										partycountBoothWiseMap.put(param1[1] != null ? Long.valueOf(param1[1].toString().trim()) :0L, param[1] != null ? param[1].toString():"");
									}
									
								}*/
								
							/*	if(param1[9] != null)
								{
									BoothLocalBodyWard boothLocalBodyWard = boothLocalBodyWardDAO.get(param1[9] != null ? (Long)param1[9] :0L);
									
									boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
											param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString() :"", 
											votesEarned, totalValidVotes, percentage, boothLocalBodyWard.getLocalBodyWard().getLocalElectionBody().
											getName()+" "+boothLocalBodyWard.getLocalBodyWard().getName(), false,pollPercent);
								}*/
								if(param1[6] != null)
								{
									LocalElectionBody localElectionBody = localElectionBodyDAO.get(param1[6] != null ? (Long)param1[6] :0L);
									boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
											param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
											votesEarned, totalValidVotes, percentage, localElectionBody.getName()+" "+
													localElectionBody.getElectionType().getElectionType(), false,pollPercent);
								}
										
								else if(param1[7] != null)
								{
									Tehsil tehsil = (Tehsil) param1[7];
									boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
											param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
											votesEarned, totalValidVotes, percentage, tehsil.getTehsilName(), false,pollPercent);
								}
									
								else
								{
									boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
											param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
											votesEarned, totalValidVotes, percentage, "", true,pollPercent);
								}
									
								boothResultVO.setTotalBoothVoters(totalVoters);
								boothResultVO.setBoothId(param1[0] != null ? (Long)param1[0] :0L);
								boothResultVO.setMessage(param[1] != null ? param[1].toString():"");
								boothResultVO.setWonParty(boothwiseWonPartyMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()):0L) != null? boothwiseWonPartyMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()):0L):"");
								boothResultVOs.add(boothResultVO);
								partyBoothPerformanceVO.setBoothResults(boothResultVOs);
							
						}
					}
					
					//partycountBoothWiseMap
					
				/*	if(partycountBoothWiseMap != null && partycountBoothWiseMap.size()>0)
					{						
						for (Long boothNo : partycountBoothWiseMap.keySet()) 
						{
							BoothResultVO boothVO = getMatchedVOByPartNO(partyBoothPerformanceVO.getBoothResults(),boothNo);
							
							if(boothVO != null)
							{
								boothVO.setWonParty(partycountBoothWiseMap.get(boothNo));
							}
						}
					}*/
					
					/*if(candidateboothResults != null && candidateboothResults.size()>0)
					{
						for(Object[] param1:candidateboothResults)
						{
							Booth booth = param1[0] != null ? (Booth) param1[0] :null;
							
							if(booth != null)
							{
								int totalValidVotes = param1[1] != null ? Integer.valueOf(param1[1].toString()) :0;
								int votesEarned =  param1[2] != null ? Integer.valueOf(param1[2].toString()) :0;
								int totalVoters = booth.getTotalVoters().intValue();
								
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
								boothResultVO.setBoothId(booth.getBoothId());
								boothResultVO.setMessage(nomination.getParty().getShortName() != null ? nomination.getParty().getShortName():"");
								boothResultVOs.add(boothResultVO);
								partyBoothPerformanceVO.setBoothResults(boothResultVOs);
							}
							
						}
						
						
						
					}*/
					boothResultsForParties.add(partyBoothPerformanceVO);
				
			}
		}
		
		return boothResultsForParties;
	
	}
	
	public BoothResultVO getMatchedVOByPartNO(List<BoothResultVO> boothWiseResultVOList,Long boothNo)
	{
		BoothResultVO resultVO = null;
		try {
			
			if(boothWiseResultVOList != null && boothWiseResultVOList.size()>0)
			{
				for (BoothResultVO boothResultVO : boothWiseResultVOList) 
				{
					if(Long.valueOf(boothResultVO.getPartNo()) == boothNo.longValue())
					{
						return boothResultVO;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	public SelectOptionVO getStateWiseDetailByStateType(Long stateTypeId, String electionType,Long electionYear,Long constituencyId)
	{
		SelectOptionVO returnVO = new SelectOptionVO();
		try {
			List<Object[]> AconstituencyList = null;
			List<Object[]> PconstituencyList = null;
			List<SelectOptionVO> constituencyVOList = null;	
			List<SelectOptionVO> electionYrs = null;
			List<Long> constiIds = null;
			Long electionScopeId = 2L;

			if(constituencyId == null)
			{
				AconstituencyList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId,1L,electionYear); // stateId = 1L
	
				if(AconstituencyList != null && AconstituencyList.size()>0)
				{
					constituencyVOList = new ArrayList<SelectOptionVO>();
					constiIds = new ArrayList<Long>();
					for (Object[] param : AconstituencyList)
					{
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						vo.setName(param[1] != null ? param[1].toString():"");					
						constituencyVOList.add(vo);
						
						constiIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					}
				}			
			}
			else
			{
				constiIds = new ArrayList<Long>();
				constiIds.add(constituencyId);
			}
			
			if(electionYear == null)
			{
				if(electionType.equalsIgnoreCase("Parliament"))
				{					
					
					List<DelimitationConstituency> parliConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyId,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
					
					if(parliConstituency != null && parliConstituency.size()>0)
					{
						DelimitationConstituency delimitationConstituency = (DelimitationConstituency) parliConstituency.get(0);
						List<Constituency> assemblList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesByDelimitationConstituencyId(delimitationConstituency.getDelimitationConstituencyID());
						
						if(assemblList != null && assemblList.size()>0)
						{
							constituencyVOList = new ArrayList<SelectOptionVO>();
							constiIds = new ArrayList<Long>();
							for (Constituency constituency : assemblList)
							{
								constiIds.add(constituency.getConstituencyId());
							}
						}
						
					}
				
					electionYear = Long.valueOf(IConstants.LATEST_ELECTION_YEAR);
				}
				List<Object[]> electionYears = constituencyElectionDAO.getElectionYearsForConstitenciesAndElectionType(electionScopeId,constiIds);
				electionYrs = new ArrayList<SelectOptionVO>();
				if(electionYears != null && electionYears.size()>0)
				{
					for (Object[] param : electionYears) 
					{
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						vo.setName(param[1] != null ? param[1].toString():"");		
						
						electionYrs.add(vo);
					}
				}
			}
			
			if(constituencyId == null)
			{
				if(electionType.equalsIgnoreCase("Parliament"))
				{
					electionScopeId = 1L;
					
					if(stateTypeId == 0)
					{
						PconstituencyList = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(0L);							
					}
					else
					{
						//PconstituencyList= delimitationConstituencyAssemblyDetailsDAO.findAllParliamentDetailsAssembliesForTheGivenYear(constiIds,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
						PconstituencyList= delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);
					}
				}
			
				if(PconstituencyList != null && PconstituencyList.size()>0)
				{
					constituencyVOList = new ArrayList<SelectOptionVO>();
					constiIds = new ArrayList<Long>();
					for (Object[] param : PconstituencyList)
					{
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						vo.setName(param[1] != null ? param[1].toString():"");					
						constituencyVOList.add(vo);
						
						constiIds.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					}
				}
			}
			
			List<Long> staticPartyList = new ArrayList<Long>();
	
			staticPartyList.add(362L);  // INC
			staticPartyList.add(163L);  // BJP
			
			
			staticPartyList.add(872L);  // TDP
			staticPartyList.add(1117L); // YSRC 
			staticPartyList.add(514L);  // LSP
			//staticPartyList.add(239L);  // BSP
			staticPartyList.add(662L);  // PRP			
			//staticPartyList.add(1712L); // JSP
			staticPartyList.add(265L);  // CPI
			staticPartyList.add(269L);  // CPM	
			
			if(stateTypeId != 1L)
			{
				staticPartyList.add(886L);  // TRS
				staticPartyList.add(72L);   // AIMIM
			}
			
			
			List<Object[]> partyList = nominationDAO.getPartyInfoParticipatedInAElection(Long.valueOf(IConstants.LATEST_ELECTION_YEAR),constiIds,staticPartyList,electionScopeId);
			
			List<SelectOptionVO> partyDEtails = new ArrayList<SelectOptionVO>();
			
			if(partyList != null && partyList.size()>0)
			{
				for (Object[] param : partyList) 
				{
					
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					vo.setName(param[1] != null ? param[1].toString():"");		
					
					partyDEtails.add(vo);
				}
			}
			
			returnVO.setSelectOptionsList(constituencyVOList);
			returnVO.setSelectOptionsList1(electionYrs);
			returnVO.setSelectOptionsList2(partyDEtails);
			
			
		} catch (Exception e) {
			log.error(" exception occured in getStateWiseDetailByStateType() in PartyBoothWiseResultsService class. ",e);
		}
		
		return returnVO;
	}
	
	public SelectOptionVO getPartyDetailsForConstituencyAction(Long electionYear, Long constituencyId)
	{
		SelectOptionVO returnVO = new SelectOptionVO();
		List<SelectOptionVO>  returnList = null;
		
		try {
			List<Long> constiIds = new ArrayList<Long>();
			List<DelimitationConstituency> parliConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyId,electionYear);
			
			if(parliConstituency != null && parliConstituency.size()>0)
			{
				DelimitationConstituency delimitationConstituency = (DelimitationConstituency) parliConstituency.get(0);
				List<Constituency> assemblList = delimitationConstituencyAssemblyDetailsDAO.findAssemblyConstituenciesByDelimitationConstituencyId(delimitationConstituency.getDelimitationConstituencyID());
				
				if(assemblList != null && assemblList.size()>0)
				{

					for (Constituency constituency : assemblList)
					{
						constiIds.add(constituency.getConstituencyId());
					}
				}
				else
				{
					constiIds.add(constituencyId);
				}
				
			}
			else
			{
				constiIds.add(constituencyId);
			}
			
			
			List<Long> staticPartyList = new ArrayList<Long>();
			
			staticPartyList.add(362L);  // INC
			staticPartyList.add(163L);  // BJP
			
			
			staticPartyList.add(872L);  // TDP
			staticPartyList.add(1117L); // YSRC 
			staticPartyList.add(514L);  // LSP
			//staticPartyList.add(239L);  // BSP
			staticPartyList.add(662L);  // PRP			
			//staticPartyList.add(1712L); // JSP
			staticPartyList.add(265L);  // CPI
			staticPartyList.add(269L);  // CPM				
			
			staticPartyList.add(886L);  // TRS
			staticPartyList.add(72L);   // AIMIM
			
			List<Object[]> partyList = nominationDAO.findPartiesByConstituencListAndElection(constiIds,electionYear.toString());
			
			if(partyList != null && partyList.size()>0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				
				for (Object[] param : partyList)
				{
					if(staticPartyList.contains(param[0] != null ? (Long) param[0] :0L))
					{
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(param[0] != null ? (Long) param[0] :0L);
						vo.setName(param[1] != null ? param[1].toString():"");		
						
						returnList.add(vo);
					}
				}
			}
			returnVO.setSelectOptionsList(returnList);
		} catch (Exception e) {
			log.error(" exception occured in getPartyDetailsForConstituencyAction() in PartyBoothWiseResultsService class. ",e);
		}
		return returnVO;
	}
	
	public PartyBoothPerformanceVO segrigateBoothWiseResults(List<PartyBoothPerformanceVO> partyBoothPerformanceVOList)
	{
		PartyBoothPerformanceVO returnVO = new PartyBoothPerformanceVO();
		try {
			

			List<BoothResultVO> percentageResultList = null;
			List<BoothResultVO> partypercentageResultList = null;
			Map<String,List<BoothResultVO>> percentageRangeWiseMap = new LinkedHashMap<String, List<BoothResultVO>>();
			Map<String,List<BoothResultVO>> partyPercentageRangeWiseMap = new LinkedHashMap<String, List<BoothResultVO>>();
			
			if(partyBoothPerformanceVOList != null && partyBoothPerformanceVOList.size()>0)
			{
				List<PartyBoothPerformanceVO> candidatesList = new ArrayList<PartyBoothPerformanceVO>();
				Map<Long,List<BoothResultVO>> boothResultsMap = new TreeMap<Long, List<BoothResultVO>>();
				List<BoothResultVO> boothWiseResults = new ArrayList<BoothResultVO>();
				
				for (PartyBoothPerformanceVO vo: partyBoothPerformanceVOList) 
				{					
					PartyBoothPerformanceVO candidateVO = new PartyBoothPerformanceVO();
					
					candidateVO.setCandidateName(vo.getCandidateName());
					candidateVO.setTotalVotes(vo.getTotalVotes());
					candidateVO.setTotalValidVotes(vo.getTotalValidVotes());
					candidateVO.setVotingPercentage(vo.getVotingPercentage());
					candidateVO.setVotesGained(vo.getVotesGained());
					candidateVO.setPercentage(vo.getPercentage());
					candidateVO.setPartyName(vo.getPartyName());
					candidateVO.setRank(vo.getRank());
					candidateVO.setMarginVotes(vo.getMarginVotes());
					candidateVO.setWonCandidate(vo.getWonCandidate());
					candidatesList.add(candidateVO);
					
					
					
					
					if(vo.getBoothResults() != null && vo.getBoothResults().size()>0)
					{
						List<BoothResultVO> boothwiseResultVOList = null;
						 
						for (BoothResultVO boothResultVO1 : vo.getBoothResults())
						{
							boothwiseResultVOList = new ArrayList<BoothResultVO>();
							
							if(boothResultsMap.get(Long.valueOf(boothResultVO1.getPartNo())) == null)									
							{
								boothResultsMap.put(Long.valueOf(boothResultVO1.getPartNo()), boothwiseResultVOList);
							}
							else
							{								
								boothwiseResultVOList = boothResultsMap.get(Long.valueOf(boothResultVO1.getPartNo()));
							}
							
							boothwiseResultVOList.add(boothResultVO1);
						}
					}
					
				
					if(vo.getPartyPerWiseboothResults() != null && vo.getPartyPerWiseboothResults().size()>0)
					{
						for (BoothResultVO vo1  : vo.getPartyPerWiseboothResults()) 
						{
							
									BoothResultVO vo2 = new BoothResultVO();
									
									List<BoothResultVO> voList = new ArrayList<BoothResultVO>();
									
									if(percentageRangeWiseMap.get(vo1.getLocation()) == null)
									{
										percentageRangeWiseMap.put(vo1.getLocation(), voList);
									}
									else
									{
										voList = percentageRangeWiseMap.get(vo1.getLocation());
									}
									
									vo2.setPercentage(vo1.getPercentage());
									vo2.setVotesEarned(vo1.getVotesEarned());
									vo2.setLocation(vo1.getLocation());
									vo2.setMessage(vo1.getMessage());
									
									vo2.setBoothResultVOList(vo1.getBoothResultVOList());	
									
									BoothResultVO partyVO = getMatchedVOByPartyName(vo1.getBoothResultVOList(),vo1.getMessage());
									if(partyVO != null)
									{
										vo2.setWonParty(partyVO.getWonParty());
										vo2.setResultState(partyVO.getResultState());
									}

									
									voList.add(vo2);
							
						}
					}
					
					if(vo.getPerWiseboothResults() != null && vo.getPerWiseboothResults().size()>0)
					{
						for (BoothResultVO vo1  : vo.getPerWiseboothResults()) 
						{							
							BoothResultVO vo2 = new BoothResultVO();
							
							List<BoothResultVO> voList = new ArrayList<BoothResultVO>();
							
							if(partyPercentageRangeWiseMap.get(vo1.getLocation()) == null)
							{
								partyPercentageRangeWiseMap.put(vo1.getLocation(), voList);
							}
							else
							{
								voList = partyPercentageRangeWiseMap.get(vo1.getLocation());
							}
							
							vo2.setPercentage(vo1.getPercentage());
							vo2.setVotesEarned(vo1.getVotesEarned());
							vo2.setLocation(vo1.getLocation());
							vo2.setMessage(vo1.getMessage());
							
							vo2.setBoothResultVOList(vo1.getBoothResultVOList());	
							
							BoothResultVO partyVO = getMatchedVOByPartyName(vo1.getBoothResultVOList(),vo1.getMessage());
							if(partyVO != null)
							{
								vo2.setWonParty(partyVO.getWonParty());
								vo2.setResultState(partyVO.getResultState());
							}

							
							voList.add(vo2);
								
						}
					}
				
					if(partyPercentageRangeWiseMap != null && partyPercentageRangeWiseMap.size()>0)
					{
						partypercentageResultList = new ArrayList<BoothResultVO>();
						for (String locationName : partyPercentageRangeWiseMap.keySet()) 
						{
							BoothResultVO vo3 = new BoothResultVO();
							vo3.setLocation(locationName);
							vo3.setBoothResultVOList(partyPercentageRangeWiseMap.get(locationName));
							
							partypercentageResultList.add(vo3);
						}
					}

						
					if(percentageRangeWiseMap != null && percentageRangeWiseMap.size()>0)
					{
						 percentageResultList = new ArrayList<BoothResultVO>();
						for (String locationName : percentageRangeWiseMap.keySet()) 
						{
							BoothResultVO vo3 = new BoothResultVO();
							vo3.setLocation(locationName);
							vo3.setBoothResultVOList(percentageRangeWiseMap.get(locationName));
							
							percentageResultList.add(vo3);
						}
					}
					
					
					returnVO.setPartyBoothPerformanceVOList(candidatesList);					
					returnVO.setPartyPerWiseboothResults(partypercentageResultList);
					returnVO.setPerWiseboothResults(percentageResultList);
			}
				if(boothResultsMap != null && boothResultsMap.size()>0)
				{
					for (Long boothNo : boothResultsMap.keySet()) 
					{
						BoothResultVO BoothwisePartyResultVO = new BoothResultVO();
						List<BoothResultVO> BoothwisePartyResultList = boothResultsMap.get(boothNo);
						BoothwisePartyResultVO.setPartNo(boothNo.toString());
						BoothwisePartyResultVO.setBoothResultVOList(BoothwisePartyResultList);
						
						boothWiseResults.add(BoothwisePartyResultVO);
					}
				}
						
				returnVO.setBoothResults(boothWiseResults);
			}
			
		} catch (Exception e) {
			log.error(" exception occured in segrigateBoothWiseResults() in PartyBoothWiseResultsService class. ",e);
		}
		
		return returnVO;
	}
	
	public BoothResultVO getMatchedVOByPartyName(List<BoothResultVO> list, String PartyName)
	{
		BoothResultVO returnVO = null;
		try {
			
			if(list !=null && list.size()>0)
			{
				for (BoothResultVO boothResultVO : list) {
					if(boothResultVO.getMessage().equalsIgnoreCase(PartyName))
					{
						return boothResultVO;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return returnVO;
	}
	 
	public SelectOptionVO getAssemblyDetailsForParliamnt(Long electionYear, Long parliamentCosntiID)
	{
		SelectOptionVO returnVO = new SelectOptionVO();
		try {
			List<SelectOptionVO> assemblDetails = null;
			List<Object[]> assemblyList = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyDetailsOfParliament(parliamentCosntiID,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
			
			if(assemblyList != null && assemblyList.size()>0)
			{
				assemblDetails = new ArrayList<SelectOptionVO>();
				
				for (Object[] param : assemblyList)
				{					
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(param[0] != null ? (Long) param[0] :0L);
						vo.setName(param[1] != null ? param[1].toString():"");		
						
						assemblDetails.add(vo);
				}
			}
			returnVO.setSelectOptionsList(assemblDetails);
			
		} catch (Exception e) {
			log.error(" exception occured in segrigateBoothWiseResults() in PartyBoothWiseResultsService class. ",e);
		}
		return returnVO;
	}
	
}
