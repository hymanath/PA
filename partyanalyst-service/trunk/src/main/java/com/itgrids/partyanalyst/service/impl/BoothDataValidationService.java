package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.BoothDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.excel.booth.CandidateBoothWiseResult;
import com.itgrids.partyanalyst.excel.booth.ConstituencyBoothBlock;
import com.itgrids.partyanalyst.excel.booth.ExcelBoothResultReader;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;

public class BoothDataValidationService implements IBoothDataValidationService{

	private ITehsilDAO tehsilDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	
	public BoothDataValidationService(){
		
	}
		
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public List<String> readBoothDataExcelFileAndPolpulate(File filePath, String electionYear, Long electionScopeId) throws CsvException{
		BoothDataExcelReader excelReader = new BoothDataExcelReader();
		excelReader.readExcelFile(filePath);
		List<String> corrections = new ArrayList<String>();
		List<BoothDataUploadVO> boothData = excelReader.getBoothDataUploadVOs();
		for(BoothDataUploadVO boothDataUploadVO:boothData){
			Long districtId = boothDataUploadVO.getDistrictId();
			String constituencyName = boothDataUploadVO.getConstituencyName();
			List<BoothInfo> boothsInConstituency = boothDataUploadVO.getBooths();
			corrections.add("#For The Constituency::"+constituencyName);
			List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
			if(constituencyElections.size() != 1 ){
				corrections.add("More than One or no ConstituencyElection Exists For electionYear:"+ electionYear+" Constituency Name:"+constituencyName+" and District Id:"+districtId);
				continue;
			}
			ConstituencyElection constituencyElection = constituencyElections.get(0);
			for(BoothInfo boothRecord:boothsInConstituency){
				checkAndInsertBooth(constituencyElection, boothRecord, districtId, constituencyName, corrections);
			}
		}
		return corrections;
	}
	
	public void checkAndInsertBooth(ConstituencyElection constituencyElection, BoothInfo boothRecord, Long districtId, String constituencyName, List<String> corrections){
		String tehsilName = boothRecord.getMandalName();
		String partNo = boothRecord.getPartNo();
		String partName = boothRecord.getPartName();
		String censusCode = boothRecord.getCensusCode();
		
		if(!StringUtils.isNumeric(partNo)){
			if(StringUtils.isBlank(partName)&&partNo.contains("-")||partNo.contains(".")){
				String [] nameAndNo = StringUtils.split(partNo.trim(), "-.");
				partNo = nameAndNo[0];
				partName = nameAndNo[1];
			}else {
				corrections.add(partNo+" Contains Different CharecterSet in "+constituencyName);
			}
		}
		
		if(!StringUtils.isNumeric(censusCode)){
			String[] censusCodes = null; 
			if(censusCode.contains("\n")||censusCode.contains(",")||censusCode.contains(" ")){
				censusCodes = StringUtils.split(censusCode, ",\n ");
				for(int i=0; i < censusCodes.length; i++){
					Long censusCodeL = new Long(censusCodes[i]);
				}
			}else{
				corrections.add(censusCode+" Contains Different CharecterSet in "+constituencyName+" With PartNo:"+partNo);
			}
		}
		
		Long maleVoters = checkAndAssignLong(boothRecord.getMaleVoters(),constituencyName,partNo, corrections);
		Long femaleVoters = checkAndAssignLong(boothRecord.getFemaleVoters(),constituencyName,partNo, corrections);
		Long totalVoters = checkAndAssignLong(boothRecord.getTotalVoters(),constituencyName,partNo, corrections);
		
		List<Tehsil> tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
		if(tehsils.size() != 1){
			corrections.add("More than one or no Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtId+" And Constituency "+constituencyName);
		}
	}

	public Long checkAndAssignLong(String value, String constituencyName, String partNo, List<String> corrections){
		Long longVal = new Long(0);
		if(!StringUtils.isEmpty(value)){
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
			else
				corrections.add("Voters :"+value+ "Contains Different CharecterSet in "+ constituencyName+" With PartNo :"+partNo);
		}
		return longVal;
	}
	
	public List<String> readAssemblyBoothResultExcelAndPopulate(File filePath, String electionYear, Long electionScopeId)throws CsvException{
		List<String> corrections = new ArrayList<String>();
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		excelBoothResultReader.readExcel(filePath, false);
		List<ConstituencyBoothBlock> list = excelBoothResultReader.getConstituenciesBlocsks();
		for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
			String constituencyName = assemblyConstituencyBlock.getConstituencyName();
			System.out.println("constituencyName "+constituencyName);
			Long districtId = assemblyConstituencyBlock.getDistrictId();
			System.out.println("districtId  "+districtId);
			corrections.add("#For The Constituency::"+constituencyName);
			List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
			if(constituencyElections.size() != 1){
				corrections.add("More than One or no ConstituencyElection Exists For electionYear:"+ electionYear+" Constituency Name:"+constituencyName+" and District Id:"+districtId);
				continue;
			}
			List<CandidateBoothWiseResult> candidateBoothResults = assemblyConstituencyBlock.getCandidateResults();
			ConstituencyElection constiElecObj = constituencyElections.get(0);
			checkAndInsertBoothResult(constiElecObj, candidateBoothResults, corrections);
		}	
		return corrections;
	}
	
	public void checkAndInsertBoothResult(ConstituencyElection constituencyElection, List<CandidateBoothWiseResult> candidateBoothResults, List<String> corrections){
		String constituencyName = constituencyElection.getConstituency().getName();
		for(CandidateBoothWiseResult candidateResult:candidateBoothResults){
			String candidateName = candidateResult.getCandidateName();
			List<Nomination> nominations = nominationDAO.findByConstituencyElectionAndCandidate(candidateName , constituencyElection.getConstiElecId());
			if(nominations.size() != 1){
				corrections.add("More Than One Or No Nominations Exists For::"+candidateName+" constituencyElectionId::"+constituencyElection.getConstiElecId());
				continue;
			}
		}
	}
	
	public List<String> readParliamentBoothResultExcelAndPopulate(File filePath, String electionYear, Long electionScopeId) throws CsvException{
		List<String> corrections = new ArrayList<String>();
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		excelBoothResultReader.readExcel(filePath, true);
		List<ConstituencyBoothBlock> list = excelBoothResultReader.getConstituenciesBlocsks();
		for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
			String pcName = assemblyConstituencyBlock.getParliamentConstituencyName();
			String acName = assemblyConstituencyBlock.getConstituencyName();
			Long districtId = assemblyConstituencyBlock.getDistrictId();
			Long stateId = assemblyConstituencyBlock.getStateId();
			corrections.add("#For The Constituency::"+acName);
			List<ConstituencyElection> acConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, acName, new Long(2), districtId);
			List<ConstituencyElection> pcConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndState(electionYear, pcName, electionScopeId, stateId);
			if(pcConstituencyElections.size() != 1){
				corrections.add("More than One or no ConstituencyElection Exists For electionYear:"+ electionYear+" Constituency Name:"+acName+" and District Id:"+districtId);
				if(acConstituencyElections.size() != 1)
					corrections.add("More than One or no ConstituencyElection Exists For electionYear:"+ electionYear+" Constituency Name:"+acName+" and District Id:"+districtId);
				continue;	
			}
			List<CandidateBoothWiseResult> candidateBoothResults = assemblyConstituencyBlock.getCandidateResults();
			ConstituencyElection constiElecObj = pcConstituencyElections.get(0);
			checkAndInsertBoothResult(constiElecObj, candidateBoothResults, corrections);
		}	
		return corrections;
	}
}
