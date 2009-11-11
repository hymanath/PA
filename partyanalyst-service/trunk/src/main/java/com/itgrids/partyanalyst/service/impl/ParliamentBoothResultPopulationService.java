package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
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
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.service.IParliamentBoothResultPopulationService;

public class ParliamentBoothResultPopulationService implements IParliamentBoothResultPopulationService{

	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothResultDAO boothResultDAO;
	private INominationDAO nominationDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	
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
	
	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public void readExcel(String filePath, Long electionScopeId, String electionYear) throws CsvException{
		ExcelBoothResultReader excel = new ExcelBoothResultReader();
		excel.readExcel(filePath, true);
		List<ConstituencyBoothBlock> list = excel.getConstituenciesBlocsks();
		for(ConstituencyBoothBlock constituencyBoothBlock:list)
			insertParliamentBoothConstiElection(constituencyBoothBlock, electionScopeId, electionYear);
	}

	public void insertParliamentBoothConstiElection(ConstituencyBoothBlock constituencyBoothBlock, Long electionScopeId, String electionYear){
		String parliamentConstituencyName = constituencyBoothBlock.getParliamentConstituencyName();
		String assemblyConstituencyName = constituencyBoothBlock.getConstituencyName();
		Long stateId = constituencyBoothBlock.getStateId();
		Long districtId = constituencyBoothBlock.getDistrictId();
		List<CandidateBoothWiseResult> candidateResults = constituencyBoothBlock.getCandidateResults();
		List<BoothResultValueObject> boothResults = constituencyBoothBlock.getBoothResults();
		List<ConstituencyElection> assemblyConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, assemblyConstituencyName, new Long(2), districtId);
		List<Booth> booths = boothConstituencyElectionDAO.findBoothsByConstituencyElection(assemblyConstituencyElections.get(0).getConstiElecId());
		List<ConstituencyElection> parliamentConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndState(electionYear, parliamentConstituencyName, electionScopeId, stateId);
		List<BoothConstituencyElection> boothConstituencyElections = new ArrayList<BoothConstituencyElection>();
		for(Booth booth:booths){
			BoothConstituencyElection boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(booth.getPartNo(), parliamentConstituencyElections.get(0).getConstiElecId());
			if(boothConstituencyElection != null){
				System.out.println("boothConstituencyElection Already Exists with Id ::"+boothConstituencyElection.getBoothConstituencyElectionId());
				return;
			}
			else{
				boothConstituencyElection = new BoothConstituencyElection(booth, parliamentConstituencyElections.get(0), null, null);
				boothConstituencyElections.add(boothConstituencyElectionDAO.save(boothConstituencyElection));
			}
		}
		checkAndInsertBoothResult(parliamentConstituencyElections.get(0), candidateResults, boothResults, boothConstituencyElections);
	}

	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothResults, List<BoothConstituencyElection> boothConstituencyElections){
		for(int i =0 ; i<boothResults.size(); i++){
			List<BoothResult> boothResultModels = boothResultDAO.findByBoothConstituencyElection(boothConstituencyElections.get(i).getBoothConstituencyElectionId());
			if(boothResultModels != null && boothResultModels.size()>0){
				System.out.println("Booth Result Already Exists With BoothConstiElecId:"+boothConstituencyElections.get(i).getBoothConstituencyElectionId());
			}
			BoothResult boothResultModel = new BoothResult(boothConstituencyElections.get(i), boothResults.get(i).getTotalNoOfValidVotes(), boothResults.get(i).getRejectedVotes(), boothResults.get(i).getTenderedVotes());
			boothResultDAO.save(boothResultModel);
		}		
		for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
			Nomination nomination = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , constiElecObj.getConstiElecId());
			List<BoothResultExcelVO> boothResultsForCandidate = candidateBoothWiseResult.getBoothresults();
			for(int i=0; i<boothResultsForCandidate.size(); i++){
				BoothConstituencyElection boothConstituencyElection = boothConstituencyElections.get(i);
				insertCandidateBoothResult(nomination, boothConstituencyElection, boothResultsForCandidate.get(i).getVotesEarned());	
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
