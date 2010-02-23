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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.BoothTotalVotesVO;
import com.itgrids.partyanalyst.dto.BoothTypeDetailsVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultListVO;
import com.itgrids.partyanalyst.dto.ElectionWiseMandalPartyResultVO;
import com.itgrids.partyanalyst.dto.PartyGenderWiseVotesVO;
import com.itgrids.partyanalyst.dto.PartyResultsInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IPartyBoothWiseResultsService;

public class PartyBoothWiseResultsService implements IPartyBoothWiseResultsService{
	
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
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
	

	public ElectionWiseMandalPartyResultListVO getPartyGenderWiseBoothVotesForMandal(Long tehsilID){
		ElectionWiseMandalPartyResultListVO electionWiseMandalPartyResultListVO = new ElectionWiseMandalPartyResultListVO();
		List<ElectionWiseMandalPartyResultVO> electionWiseMandalPartyResultVOList = new ArrayList<ElectionWiseMandalPartyResultVO>();
		
		List list = candidateBoothResultDAO.getPartyGenderWiseBoothVotesForMandal(tehsilID);
		
		// 0- partyID, 1- partyName, 2-candidateID, 3- candidateName, 4-rank, 5-electionYear, 6-electionType
		//7-validVotes, 8-boothId, 9-partNo, 10-villageCovered, 11-maleVoters, 12-femaleVoters, 13-totalVoters,
		//14-votesEarned by candidate in the booth
		
		//election type and year is populated in the vo object
		Map<ElectionWiseMandalPartyResultVO, List<Object[]>> rawData = getElectionWiseMandalPartyResults(list);

		Set<Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>>> electionBoothWiseData = rawData.entrySet();
		Iterator<Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>>> iterator = electionBoothWiseData.iterator();
		while(iterator.hasNext()){
			Map.Entry<ElectionWiseMandalPartyResultVO, List<Object[]>> entry = iterator.next();
			ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO = entry.getKey();
			List<Object[]> value = entry.getValue();
			electionWiseMandalPartyResultVO =getBoothPartyInfoForElectionResult(electionWiseMandalPartyResultVO, value);
			electionWiseMandalPartyResultVOList.add(electionWiseMandalPartyResultVO);
		}
		electionWiseMandalPartyResultListVO.setElectionWiseMandalPartyResultVOList(electionWiseMandalPartyResultVOList);
		
		return electionWiseMandalPartyResultListVO;
	}
	
	private ElectionWiseMandalPartyResultVO getBoothPartyInfoForElectionResult(ElectionWiseMandalPartyResultVO electionWiseMandalPartyResultVO, List<Object[]> value){
		// 0- partyID, 1- partyName, 2-candidateID, 3- candidateName, 4-rank, 5-electionYear, 6-electionType
		//7-validVotes, 8-boothId, 9-partNo, 10-villageCovered, 11-maleVoters, 12-femaleVoters, 13-totalVoters,
		//14-votesEarned by candidate in the booth
		

		Set<BoothTotalVotesVO> maleBoothVotes = new HashSet<BoothTotalVotesVO>();
		Set<BoothTotalVotesVO> femaleBoothVotes = new HashSet<BoothTotalVotesVO>();
		Set<BoothTotalVotesVO> maleFemaleBoothVotes = new HashSet<BoothTotalVotesVO>();
		Set<PartyGenderWiseVotesVO> partyVotes = new HashSet<PartyGenderWiseVotesVO>();
		BoothTypeDetailsVO boothTypeDetailsVO = new BoothTypeDetailsVO();
		Map<Long, PartyGenderWiseVotesVO> partyResults = new LinkedHashMap<Long, PartyGenderWiseVotesVO>();
		Set<PartyGenderWiseVotesVO> partyVotesList = new HashSet<PartyGenderWiseVotesVO>();
		for(Object[] obj: value){
			Long partyID = (Long) obj[0];
			PartyGenderWiseVotesVO partyGenderWiseVotesVO = partyResults.get(partyID);
			if(partyGenderWiseVotesVO==null){
				partyGenderWiseVotesVO = new PartyGenderWiseVotesVO();
				partyGenderWiseVotesVO.setPartyID(partyID);
				partyGenderWiseVotesVO.setTotalVotesEarned(0L);
				partyGenderWiseVotesVO.setMaleBoothResults(0L);
				partyGenderWiseVotesVO.setFemaleBoothResults(0L);
				partyGenderWiseVotesVO.setFmBoothResults(0L);
			}
			
			String partyName = obj[1].toString();	
			Long candidateID = (Long) obj[2]; 		
			String candidateName = obj[3].toString();
			Long rank = (Long) obj[4];
			if(rank.equals(new Long(1)))
				candidateName = candidateName + "(Winner)";
			
			
			Long boothValidVotes = (Long) obj[7];	
			Long boothID = (Long) obj[8];
			Long partNo = new Long(obj[9].toString());
			String villagesCovered = obj[10].toString();
			Long maleBoothVoters = (Long) obj[11];
			Long femaleBoothVoters = (Long) obj[12];
			Long bothBoothVoters = (Long) obj[13];
			Long candidateEarnedVotesInBooth = (Long) obj[14]; 

			BoothTotalVotesVO boothTotalVotesVO = new BoothTotalVotesVO();
			boothTotalVotesVO.setBoothID(boothID);
			boothTotalVotesVO.setPartNo(partNo);
			boothTotalVotesVO.setVillagesCovered(villagesCovered);
			boothTotalVotesVO.setMaleVotes(maleBoothVoters);
			boothTotalVotesVO.setFemaleVotes(maleBoothVoters);
			boothTotalVotesVO.setTotalVotes(maleBoothVoters);
			boothTotalVotesVO.setValidVotes(boothValidVotes);
			
			
			
			if(maleBoothVoters<5){
				femaleBoothVotes.add(boothTotalVotesVO);
				Long partyFemaleBoothResults = candidateEarnedVotesInBooth+partyGenderWiseVotesVO.getFemaleBoothResults();
				partyGenderWiseVotesVO.setFemaleBoothResults(partyFemaleBoothResults);
			} else if(femaleBoothVoters<5){
				maleBoothVotes.add(boothTotalVotesVO);
				Long partyMaleBoothResults = candidateEarnedVotesInBooth+partyGenderWiseVotesVO.getMaleBoothResults();
				partyGenderWiseVotesVO.setMaleBoothResults(partyMaleBoothResults);
			} else{
				maleFemaleBoothVotes.add(boothTotalVotesVO);
				Long partyMFBoothResults = candidateEarnedVotesInBooth+partyGenderWiseVotesVO.getFmBoothResults();
				partyGenderWiseVotesVO.setFmBoothResults(partyMFBoothResults);
			}
			

			partyGenderWiseVotesVO.setPartyName(partyName);
			partyGenderWiseVotesVO.setCandidateNameWithStatus(candidateName);
			partyGenderWiseVotesVO.setCandidateID(candidateID);
			Long partyTotalVotes = partyGenderWiseVotesVO.getTotalVotesEarned() + candidateEarnedVotesInBooth;
			partyGenderWiseVotesVO.setTotalVotesEarned(partyTotalVotes);
			partyResults.put(partyID,partyGenderWiseVotesVO);
			partyVotesList.add(partyGenderWiseVotesVO);
		}
		boothTypeDetailsVO.setFemaleBoothVotes(femaleBoothVotes);
		boothTypeDetailsVO.setMaleBoothVotes(maleBoothVotes);
		boothTypeDetailsVO.setMaleFemailBoothVotes(maleFemaleBoothVotes);
		
		electionWiseMandalPartyResultVO.setPartyVotes(partyVotes);
		electionWiseMandalPartyResultVO.setBoothTypeDetailsVO(boothTypeDetailsVO);
		
		electionWiseMandalPartyResultVO.setPartyVotes(partyVotesList);
		return electionWiseMandalPartyResultVO;		
	}
	
	private Map<ElectionWiseMandalPartyResultVO, List<Object[]>> getElectionWiseMandalPartyResults(List list){
		// 0- partyID, 1- partyName, 2-candidateID, 3- candidateName, 4-rank, 5-electionYear, 6-electionType
		//7-validVotes, 8-boothId, 9-partNo, 10-villageCovered, 11-maleVoters, 12-femaleVoters, 13-totalVoters,
		//14-votesEarned by candidate in the booth
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

		obj1.setElectionType("Assembly"); obj1.setElectionYear(2009L); obj1.setGenderBoothURL("url1");
		obj2.setElectionType("Assembly"); obj2.setElectionYear(2009L); obj2.setGenderBoothURL("url2");
		Map<ElectionWiseMandalPartyResultVO, String> raw =  new LinkedHashMap<ElectionWiseMandalPartyResultVO, String>();
		raw.put(obj1,"one");
		raw.put(obj2,"two");
		System.out.println(raw.size());
		System.out.println("val1="+raw.get(obj1));
		System.out.println("val2="+raw.get(obj2));
		System.out.println("url1="+obj1.getGenderBoothURL());
		System.out.println("url2="+obj2.getGenderBoothURL()); 
	}
}
