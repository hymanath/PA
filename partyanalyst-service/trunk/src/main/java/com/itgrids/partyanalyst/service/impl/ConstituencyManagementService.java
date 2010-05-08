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
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.hibernate.ElectionDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.HamletBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.HamletsListWithBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.MPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionDAO electionDAO;

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
	
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public List<VoterVO> getVoterInfo(Long hamletId, String year){
		List<Voter> voters = boothConstituencyElectionVoterDAO.findVotersByHamletAndElectionYear(hamletId, year);
		List<VoterVO> voterVOs = new ArrayList<VoterVO>();
		VoterVO voterVO = null;
		Long count = 1l;
		for(Voter voter:voters){
			voterVO = new VoterVO();
			voterVO.setSNO((count++)+"");
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
	
	/**
	 * retrieving all hamlets and corresponding booths and voters for specific revenue village and election
	 * @param revenueVillageID
	 * @param year
	 * @param electionType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HamletsListWithBoothsAndVotersVO getAllHamletBoothInfoForRevenueVillage(Long revenueVillageID, String year, String electionType){

		log.debug("ConstituencyManagementService.getAllHamletBoothInfoForRevenueVillage() started22...");
		HamletsListWithBoothsAndVotersVO hamletsListWithBoothsAndVotersVO = new HamletsListWithBoothsAndVotersVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			List years =electionDAO.findLatestElectionYear(electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				hamletsListWithBoothsAndVotersVO.setExceptionEncountered(ex);
				return hamletsListWithBoothsAndVotersVO;
			}/*
			Object[] columns = (Object[])years.get(0);
			year = columns[0].toString();*/
			year =(String)years.get(0);
		}
		List<HamletBoothsAndVotersVO> hamletsListWithBoothsAndVotersList = new ArrayList<HamletBoothsAndVotersVO>();
		log.debug("Total Hamlet Size="+hamletsListWithBoothsAndVotersList.size());
		List listHamletsVoters = boothConstituencyElectionVoterDAO.findTotalVotersForHamlet(revenueVillageID, year, electionType);
		log.debug("Total Hamlet Size="+listHamletsVoters.size());
		List listHamletBooths = boothConstituencyElectionVoterDAO.findHamletBoothsForRevenueVillage(revenueVillageID, year, electionType);
		log.debug("Total Booths including duplicate Size="+listHamletBooths.size());
		Map<String, StringBuilder> hamletBooths = new HashMap<String, StringBuilder>();
		int size = listHamletBooths.size();

		for(int i=0; i<size; i++){
			Object[] obj = (Object[]) listHamletBooths.get(i);
			String hamletName = obj[0].toString();
			String boothPartNo = obj[1].toString();
			String boothId = obj[2].toString();
			StringBuilder value = hamletBooths.get(hamletName);
			if(value==null){
				value= new StringBuilder();
			}
			value.append(IConstants.COMMA).append("<a href='boothReport.action?partNo="+boothPartNo+"&boothID="+boothId+"'>"+boothPartNo+"</a>");
			hamletBooths.put(hamletName, value);
		}
		
		size = listHamletsVoters.size();
		for(int i=0; i<size; i++){
			HamletBoothsAndVotersVO hamletBoothsAndVotersVO = new HamletBoothsAndVotersVO();
			Object[] obj = (Object[]) listHamletsVoters.get(i);
			String hamletId = obj[0].toString();
			String hamletName = obj[1].toString();
			String voters = obj[2].toString();
			hamletBoothsAndVotersVO.setHamletID(new Long(hamletId));
			hamletBoothsAndVotersVO.setHamletName(hamletName);
			hamletBoothsAndVotersVO.setHamletNameURL("<a href='hamletReport.action?hamletID="+hamletId+"&hamletName="+hamletName+"'>"+hamletName+"</a>");
			hamletBoothsAndVotersVO.setTotalVoters(new Long(voters));
			StringBuilder boothsLink = hamletBooths.get(hamletName);
			if(boothsLink!=null){
				hamletBoothsAndVotersVO.setBoothPartNos(boothsLink.substring(1));
			}
			hamletsListWithBoothsAndVotersList.add(hamletBoothsAndVotersVO);
		}
		log.debug("total size of hamlets::::"+hamletsListWithBoothsAndVotersList.size());
		hamletsListWithBoothsAndVotersVO.setHamletsListWithBoothsAndVoters(hamletsListWithBoothsAndVotersList);
		return hamletsListWithBoothsAndVotersVO;
	}
	
	/*public HamletBoothVotersListVO findAllBoothVotersForHamlet(Long hamletID, String year, String electionType){
		log.debug("constituencyManagementService.findAllBoothVotersForHamlet() started....");
		HamletBoothVotersListVO hamletBoothVotersListVO = new HamletBoothVotersListVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			List years =electionDAO.findLatestElectionYear(electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				hamletBoothVotersListVO.setExceptionEncountered(ex);
				return hamletBoothVotersListVO;
			}
			Object[] columns = (Object[])years.get(0);
			year = columns[0].toString();
			year =(String)years.get(0);
		}
		try{
			//model.boothConstituencyElection.boothConstituencyElectionId, model.boothConstituencyElection.booth.partNo, 
			//model.boothConstituencyElection.booth.partName, count(model.voter)
			List list = boothConstituencyElectionVoterDAO.findAllBoothVotersForHamlet(hamletID, year, electionType);
			log.debug("list.size():"+list.size());
			List<HamletBoothVotersVO> hamletBoothVotersListVOList = new ArrayList<HamletBoothVotersVO>();
			for(int i=0; i<list.size(); i++){
				HamletBoothVotersVO hamletBoothVotersVO = new HamletBoothVotersVO();
				Object[] obj = (Object[]) list.get(i);
				Long boothConstituencyElectionId = (Long)obj[0];
				String partNO = obj[1].toString();
				String partName = obj[2].toString();
				Long totalVoters = (Long)obj[3];
				log.debug(i+":::boothConstituencyElectionId:"+boothConstituencyElectionId+"partNO:"+partNO+"partName:"+partName+"totalVoters:"+totalVoters);
				hamletBoothVotersVO.setBoothConstituencyElectionId(boothConstituencyElectionId);
				hamletBoothVotersVO.setPartNO(partNO);
				hamletBoothVotersVO.setPartName(partName);
				hamletBoothVotersVO.setVoters(totalVoters);
				hamletBoothVotersListVOList.add(hamletBoothVotersVO);
			}
			hamletBoothVotersListVO.setHamletBoothVotersList(hamletBoothVotersListVOList);
			log.debug("hamletBoothVotersListVOList.size()::"+hamletBoothVotersListVOList.size());
		}catch(Exception ex){
			log.error("exception:::::::::::",ex);
			hamletBoothVotersListVO.setExceptionEncountered(ex);
		}
		if(hamletBoothVotersListVO.getHamletBoothVotersList()==null)
			log.debug("null null null;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
		log.debug("hamletBoothVotersListVOList.size()::"+hamletBoothVotersListVO.getHamletBoothVotersList().size());
		
		return hamletBoothVotersListVO;
	}*/
}
