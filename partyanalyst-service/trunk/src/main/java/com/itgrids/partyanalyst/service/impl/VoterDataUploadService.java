package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.booth.VoterDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.VoterDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IVoterDataUploadService;

public class VoterDataUploadService implements IVoterDataUploadService{

	private IHamletDAO hamletDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IVoterDAO voterDAO;
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private ITehsilDAO tehsilDAO;
	private static final Logger log = Logger.getLogger(VoterDataUploadService.class);
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	} 
	
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	} 
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}
	
	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ResultStatus readExcelAndInsertData(File filePath, String electionYear, Long stateId, Long electionTypeId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			VoterDataExcelReader excelReader = new VoterDataExcelReader();
			List<VoterDataUploadVO> votersInfo = excelReader.readExcel(filePath);
			String constituencyName = "";
			String mandalName = "";
			String partNo = "";
			Long districtId = null;
			List<Tehsil> tehsils = null;
			List<BoothConstituencyElection> boothConstituencyElections = null;
			BoothConstituencyElection boothConstituencyElection = null;
			Tehsil tehsil = null;
			for(VoterDataUploadVO voterDataUploadVO : votersInfo){			
				constituencyName = voterDataUploadVO.getConstituencyName();
				districtId = voterDataUploadVO.getDistrictId();
				partNo = voterDataUploadVO.getPartNo();
				if(!voterDataUploadVO.getMandalName().equalsIgnoreCase(mandalName)){
					mandalName = voterDataUploadVO.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(mandalName, districtId);
				}				
				if(tehsils.size() != 1){
					if(log.isDebugEnabled()){
						log.debug("More than One Or No Tehsil Exists with Mandal:"+mandalName+" District Id:"+districtId);
					}
					continue;
				}				
				tehsil = tehsils.get(0);
				boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
				if(boothConstituencyElections.size() != 1){
					if(log.isDebugEnabled()){
						log.debug("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					}
					continue;
				}
				boothConstituencyElection = boothConstituencyElections.get(0);
				List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
				if(boothConstituencyElectionVoters.size() > 0){
					if(log.isDebugEnabled()){
						log.debug("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					}
					continue;
				}
					
				insertVoterAndBoothConstituencyElectionVoter(tehsil, boothConstituencyElection, voterDataUploadVO.getVoterVOs());	
			}
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultStatus.setResultPartial(true);
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		return resultStatus;
	}

	public void insertVoterAndBoothConstituencyElectionVoter(Tehsil tehsil, BoothConstituencyElection boothConstituencyElection, List<VoterVO> voterVOs)throws Exception{
		String townshipName = "";
		String hamletName = "";
		String voterFirstName = "";
		String voterlastName = "";
		String houseNo = "";
		String relationshipType = "";
		String relativeFirstName = "";
		String relativeLastName = "";
		String cast = "";
		String castCatagery = "";
		String castSubcatagery = "";
		String gender = "";
		String localArea = "";
		Long age = null;
		String voterIDCardNo = "";
		List<Hamlet> hamlets = null;
		Hamlet hamlet = null;
		Voter voter = null;
		for(VoterVO voterVO:voterVOs){
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				}
				continue;
			}
			hamlet = hamlets.get(0);
			voterFirstName = voterVO.getFirstName();
			voterlastName = voterVO.getVoterLastName();
			houseNo = voterVO.getHouseNo();
			relationshipType = voterVO.getRelationshipType();
			relativeFirstName = voterVO.getRelativeFirstName();
			relativeLastName = voterVO.getRelativeLastName();
			cast = voterVO.getCast();
			localArea = voterVO.getLocalArea();
			castCatagery = voterVO.getCastCatagery();
			castSubcatagery = voterVO.getCastSubCatagery();
			gender = voterVO.getGender();
			age = voterVO.getAge();
			voterIDCardNo = voterVO.getVoterIDCardNo();
			voter = new Voter(houseNo, voterFirstName, voterlastName, relationshipType,
					relativeFirstName, relativeLastName, cast, castCatagery, castSubcatagery, gender, 
					age, voterIDCardNo, localArea, null, hamlet, null);
			
			//Saving Voter Data and BoothConstituencyElectionVoter
			checkAndInsertBoothConstituencyElectionVoter(boothConstituencyElection, checkAndInsertVoterData(voter));
		}
		
	}
	
	private Voter checkAndInsertVoterData(Voter voter){
		List<Voter> voters = voterDAO.findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(voter.getFirstName(), 
				voter.getLastName(), voter.getRelativeFirstName(), voter.getRelativeLastName(), voter.getVoterIDCardNo());
		if(voters.size() > 0 ){
			if(log.isDebugEnabled()){
				log.debug("Voter Already Exists Name:"+voter.getFirstName()+" "+voter.getLastName()+" and Voter ID:"+voter.getVoterIDCardNo());
			}
			return voters.get(0);
		}
		return voter;
	}
	
	private void checkAndInsertBoothConstituencyElectionVoter(BoothConstituencyElection boothConstituencyElection, Voter voter){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElectionAndVoter(boothConstituencyElection.getBoothConstituencyElectionId(), voter.getVoterId());
		if(list.size() > 0 ){
			if(log.isDebugEnabled()){
				log.debug("BoothConstituencyElectionVoter Already Exists with BoothConstituencyElection ID:"+boothConstituencyElection.getBoothConstituencyElectionId()+" and Voter Id:"+voter.getVoterId());
			}
			return;
		}
		BoothConstituencyElectionVoter boothConstituencyElectionVoter = new BoothConstituencyElectionVoter(boothConstituencyElection, voter);
		boothConstituencyElectionVoterDAO.save(boothConstituencyElectionVoter);
	}
}
