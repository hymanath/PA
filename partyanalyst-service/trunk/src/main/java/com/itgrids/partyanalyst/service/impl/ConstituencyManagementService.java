package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.MPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	private ICandidateResultDAO candidateResultDAO;

	private static final Logger log = Logger.getLogger(ConstituencyManagementService.class);
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	public List<VoterVO> getVoterInfo(Long hamletId, String year){
		List<Voter> voters = boothConstituencyElectionVoterDAO.findVotersByHamletAndElectionYear(hamletId, year);
		List<VoterVO> voterVOs = new ArrayList<VoterVO>();
		VoterVO voterVO = null;
		for(Voter voter:voters){
			voterVO = new VoterVO();
			voterVO.setVoterFirstName(voter.getFirstName()+ voter.getLastName());
			voterVO.setHouseNo(voter.getHouseNo());
			voterVO.setAge(voter.getAge());
			voterVO.setCast(voter.getCast());
			voterVO.setCastCatagery(voter.getCastCatagery()+" "+voter.getCastSubCatagery());
			voterVO.setGender(voter.getGender());
			voterVO.setRelativeFirstName(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
			voterVO.setRelationshipType(voter.getRelationshipType());
			voterVOs.add(voterVO);
		}
		
		return voterVOs;
	}
	
	public List<SelectOptionVO> getHamletsForTownship(Long townshipId){
		List<SelectOptionVO> hamlets = new ArrayList<SelectOptionVO>();
		List<Hamlet> hamletModels = hamletDAO.findByTownshipId(townshipId);
		SelectOptionVO hamlet = null;
		for(Hamlet hamletModel:hamletModels){
			hamlet = new SelectOptionVO(hamletModel.getHamletId(), hamletModel.getHamletName());
			hamlets.add(hamlet);
		}
		return hamlets;
	}
	
	public VoterCastInfoVO getVotersCastInfoForHamlet(Long hamletId, String year){		
		return caluculatePercentage(boothConstituencyElectionVoterDAO.findVotersCastInfoByHamletAndElectionYear(hamletId, year));
	}
	
	/**
	 * @param	parms
	 * @return  constituencyManagementVO  to the corresponding calling method.
	 */

	public VoterCastInfoVO caluculatePercentage(List parms){	
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		List<CastVO> castVOs = new ArrayList<CastVO>();
		Set<String> casts = new HashSet<String>(); 	
		CastVO castVO = null;
		
		Long totalVoters=0L;	
		Long maleVoters=0L;
		Long femaleVoters=0L;
		Long castCount = 0L;
		
		String prevCast = "";
		String cast = "";
		
		for(int i=0;i < parms.size();i++){
			Object[] voterInfo  = (Object[]) parms.get(i);
			totalVoters = totalVoters + (Long)voterInfo[0];			
			String gender = (String)voterInfo[1];
			prevCast = cast;
			cast = (String)voterInfo[2];
			if(gender.equalsIgnoreCase("m"))
				maleVoters = maleVoters + (Long)voterInfo[0];
			if(casts.add(cast)&& (!prevCast.equals(""))){
				castVO = new CastVO();
				castVO.setCastName(prevCast);
				castVO.setCastCount(castCount);
				castVOs.add(castVO);
				castCount = 0L;
			}
			castCount = castCount + (Long)voterInfo[0];
			
			//Adding the Last Cast Info To the List
			if(i == parms.size() - 1){
				castVO = new CastVO();
				castVO.setCastName(prevCast);
				castVO.setCastCount(castCount--);
				castVOs.add(castVO);
			}
		}
		
		femaleVoters = totalVoters-maleVoters;
		
		//Cast Percentage Calculation
		for(int i=0; i < castVOs.size(); i++){
			String castPercent = new BigDecimal((castVOs.get(i).getCastCount() * 100.0)/totalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			castVOs.get(i).setCastPercentage(castPercent);
		}
					
		voterCastInfoVO.setTotalVoters(totalVoters);
		voterCastInfoVO.setMaleVoters(maleVoters);
		voterCastInfoVO.setFemaleVoters(femaleVoters);
		voterCastInfoVO.setCastVOs(castVOs);
				
		return voterCastInfoVO;				
	}
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId,String year) {	
		List<Voter> voters = boothConstituencyElectionVoterDAO.findVotersGroupByHouseNoAndAgeForHamletAndYear(hamletId, year);
		Map<String, List<VoterVO>> voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		for(Voter voter : voters){
			houseNo = voter.getHouseNo();
			voterVO = new VoterVO();
			voterVO.setVoterFirstName(voter.getFirstName());
			voterVO.setVoterLastName(voter.getLastName());
			voterVO.setAge(voter.getAge());
			voterVO.setCast(voter.getCast());
			voterVOs = voterByHouseNoMap.get(houseNo);
			if(voterVOs ==null)
				voterVOs = new ArrayList<VoterVO>();
			voterVOs.add(voterVO);
			voterByHouseNoMap.put(houseNo, voterVOs);
			
		}
		for(Map.Entry<String, List<VoterVO>> entry:voterByHouseNoMap.entrySet()){
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterVOs = entry.getValue();
			if(voterVOs.size() == 0)
				continue;
			voterHouseInfoVO.setHouseNo(entry.getKey());
			voterHouseInfoVO.setCast(voterVOs.get(0).getCast());
			voterHouseInfoVO.setYounger(voterVOs.get(0).getVoterFirstName());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getVoterFirstName());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		
		return voterHouseInfoVOs;
	}
	/**
	 * retrieving the mptc election candidate results.
	 * @param mandalID
	 * @return
	 */
	public TotalMPTCMandalLeaderVO getMPTCElectionResultForMandal(Long mandalID){
		log.debug(" getMPTCElectionResultForMandal() start....");
		List result = candidateResultDAO.getMPTCElectionResultForMandal(mandalID);
		TotalMPTCMandalLeaderVO totalMPTCMandalLeaderVO = new TotalMPTCMandalLeaderVO();
		
		List<MPTCMandalLeaderVO> totalLeaders = totalMPTCMandalLeaderVO.getTotalLeaders();
		List<MPTCMandalLeaderVO> winners = totalMPTCMandalLeaderVO.getWinners();
		for(int i=0; i<result.size(); i++){
			Object[] obj = (Object[]) result.get(i);
			MPTCMandalLeaderVO mptcMandalLeaderVO = new MPTCMandalLeaderVO();
			mptcMandalLeaderVO.setElectionYear(obj[0].toString()); 
			mptcMandalLeaderVO.setRank(new Long(obj[1].toString()));

			mptcMandalLeaderVO.setMptcName(obj[2].toString());
			mptcMandalLeaderVO.setParty(obj[3].toString());
			mptcMandalLeaderVO.setCandidateName(obj[4].toString());
			mptcMandalLeaderVO.setCandidateEarnedVotes(formatedData(obj[5]));
			mptcMandalLeaderVO.setValidVotes(formatedData(obj[6]));
			
			totalLeaders.add(mptcMandalLeaderVO);
			if(mptcMandalLeaderVO.getRank().equals(new Long(1))){
				winners.add(mptcMandalLeaderVO);
			}
		}
		
		return totalMPTCMandalLeaderVO;
	}
	
	private Double formatedData(Object obj){
		Double result = new Double(0);
		try{
			result = new Double(obj.toString().trim());
		}catch(NumberFormatException ex){
			result = new Double(0);
		}
		return result;
	}
	
}
