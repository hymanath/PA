package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
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
				System.out.println("Cast:"+cast+"  "+"prevCast:"+prevCast);
				castVO = new CastVO();
				castVO.setCastName(prevCast);
				castVO.setCastCount(castCount--);
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
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId, String year){
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<String> houseNos = boothConstituencyElectionVoterDAO.findVoterHouseNosInHamlet(hamletId, year);
		List<Voter> voters = null;
		for(String houseNo:houseNos){
			voterHouseInfoVO = new VoterHouseInfoVO();
			voters = boothConstituencyElectionVoterDAO.findVotersByHouseNoAndHamlet(houseNo, hamletId, year);
			if(voters.size() == 0)
				continue;
			voterHouseInfoVO.setHouseNo(houseNo);
			voterHouseInfoVO.setCast(voters.get(0).getCast());
			voterHouseInfoVO.setYounger(voters.get(0).getFirstName());
			voterHouseInfoVO.setElder(voters.get(voters.size()-1).getFirstName());
			voterHouseInfoVO.setNumberOfPeople(voters.size());
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		return voterHouseInfoVOs;		
	}
	
	
}
