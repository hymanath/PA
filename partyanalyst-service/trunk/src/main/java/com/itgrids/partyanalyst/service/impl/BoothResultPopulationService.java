package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.excel.booth.BoothResultExcelVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultValueObject;
import com.itgrids.partyanalyst.excel.booth.CandidateBoothWiseResult;
import com.itgrids.partyanalyst.excel.booth.ConstituencyBoothBlock;
import com.itgrids.partyanalyst.excel.booth.ExcelBoothResultReader;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IBoothResultPopulationService;

public class BoothResultPopulationService implements IBoothResultPopulationService{

	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothResultDAO boothResultDAO;
		
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}
	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}
	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}
	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public boolean readExcelAndInsertData(String electionYear, Long electionScopeId, String filePath){
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		try {
			System.out.println("In TRY block1");
			excelBoothResultReader.readExcel(filePath, false);
			System.out.println("In TRY block2");
		} catch (CsvException e) {
			System.out.println("In Catch block");
			e.printStackTrace();
		}
		List<ConstituencyBoothBlock> list = excelBoothResultReader.getConstituenciesBlocsks();
		
		System.out.println("Total Constituencies::"+list.size());
		
		for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ConstituenciBlock start@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			String constituencyName = assemblyConstituencyBlock.getConstituencyName();
			System.out.println("constituencyName "+constituencyName);
			Long districtId = assemblyConstituencyBlock.getDistrictId();
			System.out.println("districtId  "+districtId);
			List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
			if(constituencyElections == null || constituencyElections.size() > 1){
				System.out.println("#####ConstituencyElection has More than one records with electionYear, constituencyName, electionType, districtName"+electionYear+constituencyName+electionScopeId+districtId);
				return false;
			}
			List<CandidateBoothWiseResult> candidateBoothResults = assemblyConstituencyBlock.getCandidateResults();
			List<BoothResultValueObject> boothresults = assemblyConstituencyBlock.getBoothResults();
			System.out.println("constituencyElections.get(0)  "+constituencyElections.get(0).getConstiElecId());
			ConstituencyElection constiElecObj = constituencyElections.get(0);
			System.out.println("In readExcelAndInsertData---2"+constiElecObj.getConstiElecId());
			checkAndInsertBoothResult(constiElecObj, candidateBoothResults, boothresults);
			System.out.println(constituencyName+" Constituency with ConstituencyElection Id "+constiElecObj.getConstiElecId());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@ConstituenciBlock End@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		return false;
	}
	
	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothresults){
		System.out.println("Booth Result VO's#"+boothresults.size());
		for(BoothResultValueObject boothResult:boothresults){
			System.out.println("boothResult.getPartNumber(), constiElecObj.getConstiElecId()" + boothResult.getPartNumber()+" "+ constiElecObj.getConstiElecId());
			BoothConstituencyElection boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(boothResult.getPartNumber(), constiElecObj.getConstiElecId());
			System.out.println("boothConstituencyElection #"+boothConstituencyElection.getBoothConstituencyElectionId());
			List<BoothResult> boothResultModels = boothResultDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
			if(boothResultModels != null && boothResultModels.size()>0){
				System.out.println("Booth Result Already Exists With BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
				System.out.println("Total Booth Models::"+boothResultModels.size());
				System.out.println("Total Booth Model Id::"+boothResultModels.get(0).getBoothResultId());
			}
			BoothResult boothResultModel = new BoothResult(boothConstituencyElection, boothResult.getTotalNoOfValidVotes(), boothResult.getRejectedVotes(), boothResult.getTenderedVotes());
			System.out.println("boothResultDAO Inserted with Id:"+boothResultDAO.save(boothResultModel).getBoothResultId());
		}
		System.out.println("Total Candidate RESULT::"+candidateResults.size());
		for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("Candidate Name ::"+candidateBoothWiseResult.getCandidateName());
			Nomination nomination = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , constiElecObj.getConstiElecId());
			System.out.println("In checkAndInsertBoothResult Nomination"+nomination.getNominationId());
			List<BoothResultExcelVO> boothResultsForCandidate = candidateBoothWiseResult.getBoothresults();
			for(BoothResultExcelVO boothResultExcel:boothResultsForCandidate){
				BoothConstituencyElection boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(boothResultExcel.getPartNo(), constiElecObj.getConstiElecId());
				System.out.println("In checkAndInsertBoothResult boothConstituencyElection ::"+ boothConstituencyElection.getBoothConstituencyElectionId());
				insertCandidateBoothResult(nomination, boothConstituencyElection, boothResultExcel.getVotesEarned());
			}
		}
	}
			
	public CandidateBoothResult insertCandidateBoothResult(Nomination nomination, BoothConstituencyElection boothConstituencyElection, Long votesEarned){
		CandidateBoothResult candidateBoothResult = null;
		candidateBoothResult = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(nomination.getNominationId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(candidateBoothResult != null){
			System.out.println("Booth Result Already Exists With NominationId:"+nomination.getNominationId()+"And BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
			return candidateBoothResult;
		}
		candidateBoothResult = new CandidateBoothResult(votesEarned, nomination, null, boothConstituencyElection);
		return candidateBoothResultDAO.save(candidateBoothResult);
	}
	
}
