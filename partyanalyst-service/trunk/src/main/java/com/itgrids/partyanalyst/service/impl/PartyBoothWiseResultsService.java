package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.BoothTotalVotesVO;
import com.itgrids.partyanalyst.dto.BoothTypeDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituencyWiseDataForMandalVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;
import com.itgrids.partyanalyst.dto.PartyResultsInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;

public class PartyBoothWiseResultsService implements IPartyBoothWiseResultsService{
	
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
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
	
	public ElectionWiseMandalPartyResultListVO getPartyGenderWiseBoothVotesForMandal(Long locationId, String locationType){
		ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO = new ElectionWiseMandalPartyResultListVO();
		List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList = new ArrayList<ElectionWiseMandalPartyResultVO>();
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
			List<Object[]> value = entry.getValue();
			electionWiseMandalPartyResultVO = getConstituencyWiseElectionResultInMandal(electionWiseMandalPartyResultVO, value);
			electionWiseMandalPartyResultVOList.add(electionWiseMandalPartyResultVO);
		}
		electionWiseMandalPartyResultListVO.setElectionWiseMandalPartyResultVOList(electionWiseMandalPartyResultVOList);
		
		return electionWiseMandalPartyResultListVO;
	}
	
	private ElectionWiseMandalPartyResultVO getConstituencyWiseElectionResultInMandal(
			ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO, List<Object[]> value) {
		
		Map<ConstituencyWiseDataForMandalVO, List<PartyGenderWiseVotesVO>> constituencyResultMap = 
			new LinkedHashMap<ConstituencyWiseDataForMandalVO, List<PartyGenderWiseVotesVO>>();
		
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
				Long partyId = partyGenderWiseVotesVO.getPartyID();
				partyWiseConstituencyResultList = partyWiseResultsMap.get(partyId);
				if(partyWiseConstituencyResultList == null)
					partyWiseConstituencyResultList = new ArrayList<PartyGenderWiseVotesVO>();
				partyWiseConstituencyResultList.add(partyGenderWiseVotesVO);
				partyWiseResultsMap.put(partyId, partyWiseConstituencyResultList);
			}
			getConstiteuncyWisePartyResults(constituencyWiseDataForMandalVO, partyWiseResultsMap);
			allConstituenciesInfo.add(constituencyWiseDataForMandalVO);
		}
		
		electionWiseMandalPartyResultVO.setConstituencyWiseDataForMandalVOs(allConstituenciesInfo);
		
		return electionWiseMandalPartyResultVO;
	}
		
	private void getConstiteuncyWisePartyResults(
			ConstituencyWiseDataForMandalVO constituencyWiseDataForMandalVO,
			Map<Long, List<PartyGenderWiseVotesVO>> partyWiseResultsMap) {
		List<PartyGenderWiseVotesVO> resultsOfAllPartiesInConstituency = new ArrayList<PartyGenderWiseVotesVO>();
		
		Long totalMaleInConstituency = 0l;
		Long totalFemaleInConstituency = 0l;
		Long totalFMInConstituency = 0l;
		
		Long allPartiesVotesInMandalForConstituency = 0l;
		
		for(Map.Entry<Long, List<PartyGenderWiseVotesVO>> entry:partyWiseResultsMap.entrySet()){
			List<PartyGenderWiseVotesVO> resultsOfAParty = entry.getValue();
			PartyGenderWiseVotesVO eashPartyResultInConstituency = new PartyGenderWiseVotesVO();
			
			Long totalMaleBoothVotes = 0L;
			Long totalFemaleBoothVotes = 0L;
			Long totalFMBoothVotes = 0L;
			
			eashPartyResultInConstituency.setCandidateID(resultsOfAParty.get(0).getCandidateID());
			eashPartyResultInConstituency.setCandidateNameWithStatus(resultsOfAParty.get(0).getCandidateNameWithStatus());
			eashPartyResultInConstituency.setRank(resultsOfAParty.get(0).getRank());
			eashPartyResultInConstituency.setPartyID(resultsOfAParty.get(0).getPartyID());
			eashPartyResultInConstituency.setPartyName(resultsOfAParty.get(0).getPartyName());
			
			for(PartyGenderWiseVotesVO resultsForPartyInBooth:resultsOfAParty){
				if(resultsForPartyInBooth.getMaleBoothResults() < 5 && resultsForPartyInBooth.getFemaleBoothResults() < 5)
					totalFMBoothVotes = totalFMBoothVotes + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				else if(resultsForPartyInBooth.getMaleBoothResults() < 5)
					totalFemaleBoothVotes = totalFemaleBoothVotes + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				else if(resultsForPartyInBooth.getFemaleBoothResults() < 5)
					totalMaleBoothVotes = totalMaleBoothVotes + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
				else
					totalFMBoothVotes = totalFMBoothVotes + resultsForPartyInBooth.getVotesEarnedInBoothForParty();
			}
			Long totalVotesForPartyInConstiForMandal = totalMaleBoothVotes + totalFemaleBoothVotes + totalFMBoothVotes;
			
			eashPartyResultInConstituency.setTotalVotesEarned(totalVotesForPartyInConstiForMandal);
			eashPartyResultInConstituency.setMaleBoothResults(totalMaleBoothVotes);
			eashPartyResultInConstituency.setFemaleBoothResults(totalFemaleBoothVotes);
			eashPartyResultInConstituency.setFmBoothResults(totalFMBoothVotes);
			
			totalMaleInConstituency = totalMaleInConstituency + totalMaleBoothVotes;
			totalFemaleInConstituency = totalFemaleInConstituency + totalFemaleBoothVotes;
			totalFMInConstituency = totalFMInConstituency + totalFMBoothVotes;
			
			
			allPartiesVotesInMandalForConstituency = allPartiesVotesInMandalForConstituency + totalVotesForPartyInConstiForMandal;
			resultsOfAllPartiesInConstituency.add(eashPartyResultInConstituency);
		}
		
		for(PartyGenderWiseVotesVO eachPartyInConsti:resultsOfAllPartiesInConstituency){
			String maleVotesPercentage = "";
			String femaleVotesPercentage = "";
			String fmVotesPercentage = "";
			String totalVotesPercentage = "";
			
			if(totalMaleInConstituency == 0){
				maleVotesPercentage = "-";
			}else{
				maleVotesPercentage = new BigDecimal((eachPartyInConsti.getMaleBoothResults()*100.0)/
						totalMaleInConstituency).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			if(totalFemaleInConstituency == 0){
				femaleVotesPercentage = "-";
			}else{
				femaleVotesPercentage = new BigDecimal((eachPartyInConsti.getFemaleBoothResults()*100.0)/
						totalFemaleInConstituency).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			if(totalFMInConstituency == 0){
				fmVotesPercentage = "-";
			}else{
				fmVotesPercentage = new BigDecimal((eachPartyInConsti.getFmBoothResults()*100.0)/
						totalFMInConstituency).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
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
		// 0- partyID, 1- partyName, 2-candidateID, 3- candidateName, 4-rank, 5-electionYear, 6-electionType
		//7-validVotes, 8-boothId, 9-partNo, 10-villageCovered, 11-maleVoters, 12-femaleVoters, 13-totalVoters,
		//14-votesEarned by candidate in the booth, 15-constituencyID, 16-constituencyName
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
	public static void main(String[] args) throws Exception{
		ElectionWiseMandalPartyResultVO obj1 = new ElectionWiseMandalPartyResultVO();
		ElectionWiseMandalPartyResultVO obj2 = new ElectionWiseMandalPartyResultVO();

		obj1.setElectionType("Assembly"); obj1.setElectionYear(2009L);
		obj2.setElectionType("Assembly"); obj2.setElectionYear(2009L);
		Map<ElectionWiseMandalPartyResultVO, String> raw =  new LinkedHashMap<ElectionWiseMandalPartyResultVO, String>();
		raw.put(obj1,"one");
		raw.put(obj2,"two");
		System.out.println(raw.size());
		System.out.println("val1="+raw.get(obj1));
		System.out.println("val2="+raw.get(obj2));
	}
}
