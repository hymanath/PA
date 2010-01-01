package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	/*private IProblemLocationDAO problemLocationDAO;
	private IProblemHistoryDAO problemHistoryDAO;*/
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	
	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}

	/*public IProblemLocationDAO getProblemLocationDAO() {
		return problemLocationDAO;
	}
	
	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}
	
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}
	
	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}*/
	
	public List<VoterVO> createProblem(Long hamletId, String year){
		List<Voter> voters = boothConstituencyElectionVoterDAO.findVotersByHamletAndElectiuonYear(hamletId, year);
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
	

}
