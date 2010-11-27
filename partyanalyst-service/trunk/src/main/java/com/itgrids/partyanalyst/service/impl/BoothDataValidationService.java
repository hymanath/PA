 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.VoterDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.VoterDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;

public class BoothDataValidationService implements IBoothDataValidationService{

	private ITehsilDAO tehsilDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	
	public BoothDataValidationService(){
		
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(File filePath, String electionYear, Long stateId, Long electionTypeId)throws CsvException{
		List<String> corrections = new ArrayList<String>();
		List<Exception> exceptions = new ArrayList<Exception>();
		VoterDataExcelReader voterDataExcelReader = new VoterDataExcelReader();
		List<VoterDataUploadVO> votersInfo = voterDataExcelReader.readExcel(filePath);
		String constituencyName = "";
		String mandalName = "";
		String partNo = "";
		Long districtId = null;
		List<Tehsil> tehsils = null;
		List<BoothConstituencyElection> boothConstituencyElections = null;
		BoothConstituencyElection boothConstituencyElection = null;
		Tehsil tehsil = null;
		UploadDataErrorMessageVO uploadDataErrorMessageVO = new UploadDataErrorMessageVO(); 
		for(VoterDataUploadVO voterDataUploadVO : votersInfo){	
			try{
				constituencyName = voterDataUploadVO.getConstituencyName();
				districtId = voterDataUploadVO.getDistrictId();
				partNo = voterDataUploadVO.getPartNo();
				corrections.add("#For The Constituency:"+constituencyName+" and Part NO:"+partNo);
				if(!voterDataUploadVO.getMandalName().equalsIgnoreCase(mandalName)){
					mandalName = voterDataUploadVO.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(mandalName, districtId);
				}				
				if(tehsils.size() != 1){
					corrections.add("More than One Or No Tehsil Exists with Mandal:"+mandalName+" District Id:"+districtId);
					continue;
				}				
				tehsil = tehsils.get(0);
				boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
				if(boothConstituencyElections.size() != 1){
					corrections.add("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					continue;
				}
				boothConstituencyElection = boothConstituencyElections.get(0);
				List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
				if(boothConstituencyElectionVoters.size() > 0){
					corrections.add("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					continue;
				}
					
				insertVoterAndBoothConstituencyElectionVoter(tehsil, boothConstituencyElection, voterDataUploadVO.getVoterVOs(), corrections);
			}catch(Exception ex){
				exceptions.add(ex);
				ex.printStackTrace();
			}						
		}		
		uploadDataErrorMessageVO.setCorrections(corrections);
		uploadDataErrorMessageVO.setExceptions(exceptions);
		return uploadDataErrorMessageVO;
	}

	private void insertVoterAndBoothConstituencyElectionVoter(Tehsil tehsil,BoothConstituencyElection boothConstituencyElection,
			List<VoterVO> voterVOs, List<String> corrections)throws Exception {
		String townshipName = "";
		String hamletName = "";
		List<Hamlet> hamlets = null;
		for(VoterVO voterVO:voterVOs){
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				System.out.println("townshipName ::"+townshipName+" hamletName ::"+hamletName);
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				corrections.add("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				continue;
			}
		}
		
	}
}
