package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.BoothPanelVO;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
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
}
