package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
	private static final Logger log = Logger.getLogger(ParliamentBoothResultPopulationService.class);
	
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
	
	public ResultStatus readExcel(String filePath, Long electionScopeId, String electionYear){
		ResultStatus resultVO = new ResultStatus();
		try{
			ExcelBoothResultReader excel = new ExcelBoothResultReader();
			excel.readExcel(filePath, true);
			List<ConstituencyBoothBlock> list = excel.getConstituenciesBlocsks();
			for(ConstituencyBoothBlock constituencyBoothBlock:list){
				String parliamentConstituencyName = constituencyBoothBlock.getParliamentConstituencyName();
				if(log.isDebugEnabled()){
					log.debug("=======================================In Parliament Constituency::"+parliamentConstituencyName+" And "+"Assembly :"+constituencyBoothBlock.getConstituencyName());
				}
				List<BoothResult> boothResults = boothResultDAO.findByConstituencyAndElection(parliamentConstituencyName, electionYear, electionScopeId);
				if(boothResults.size() > 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Results Already Exists For ElectionYear::"+electionYear+" Constituency Name::"+parliamentConstituencyName);
					}
					continue;
				}
				insertParliamentBoothConstiElection(constituencyBoothBlock, electionScopeId, electionYear);
			}
		}catch(IndexOutOfBoundsException ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultVO.setResultPartial(true);
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
		}
		return resultVO;
	}

	public void insertParliamentBoothConstiElection(ConstituencyBoothBlock constituencyBoothBlock, Long electionScopeId, String electionYear)throws Exception{
		String parliamentConstituencyName = constituencyBoothBlock.getParliamentConstituencyName();
		String assemblyConstituencyName = constituencyBoothBlock.getConstituencyName();
		Long stateId = constituencyBoothBlock.getStateId();
		Long districtId = constituencyBoothBlock.getDistrictId();
		List<CandidateBoothWiseResult> candidateResults = constituencyBoothBlock.getCandidateResults();
		List<BoothResultValueObject> boothResults = constituencyBoothBlock.getBoothResults();
		List<ConstituencyElection> assemblyConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, assemblyConstituencyName, new Long(2), districtId);
		List<ConstituencyElection> parliamentConstituencyElections = constituencyElectionDAO.findByConstituencyElectionAndState(electionYear, parliamentConstituencyName, electionScopeId, stateId);
		if(assemblyConstituencyElections.size() != 1 || parliamentConstituencyElections.size() != 1 ){
			if(log.isDebugEnabled()){
				log.debug("Exists More than One Parliament Or Assembly Constituency Election For :"+assemblyConstituencyName+","+parliamentConstituencyName);
			}
		}
		
		List<BoothConstituencyElection> boothConstituencyElections = new ArrayList<BoothConstituencyElection>();
		List<Booth> booths = boothConstituencyElectionDAO.findBoothsByConstituencyElection(assemblyConstituencyElections.get(0).getConstiElecId());		
		if(booths.size() == 0){
			if(log.isDebugEnabled()){
				log.debug("No Booth Data Exists For Constituency ::"+parliamentConstituencyName+"In Assembly Constituency::"+assemblyConstituencyName);
			}
			return;
		}
		for(Booth booth:booths){
			List<BoothConstituencyElection> boothConstituencyElectionModels = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(booth.getPartNo(), parliamentConstituencyElections.get(0).getConstiElecId());
			if(boothConstituencyElectionModels.size() > 0){
				if(log.isDebugEnabled()){
					log.debug("boothConstituencyElection Already Exists with Id ::"+boothConstituencyElectionModels.get(0).getBoothConstituencyElectionId());
				}
				return;
			}
			else{
				BoothConstituencyElection boothConstituencyElection = new BoothConstituencyElection(booth, parliamentConstituencyElections.get(0), null, null);
				boothConstituencyElections.add(boothConstituencyElectionDAO.save(boothConstituencyElection));
			}
		}
		if(boothConstituencyElections.size() < boothResults.size()){
			if(log.isDebugEnabled()){
				log.debug("Booth Data And Booth Results Data Are Not Same For Parliament Constituency:"+parliamentConstituencyName +" In Assembly Constituency::"+assemblyConstituencyName);
			}
			return;
		}
		if(log.isDebugEnabled()){
			log.debug("boothConstituencyElection List size ::"+boothConstituencyElections.size());
			log.debug("Parliament Constituency Name:"+parliamentConstituencyName+" And Assembly Constituency name::"+assemblyConstituencyName);
			log.debug("assemblyConstituencyElectionId and parliamentConstituencyElectionId are ::"+assemblyConstituencyElections.get(0).getConstiElecId()+","+parliamentConstituencyElections.get(0).getConstiElecId());
			
		}
		checkAndInsertBoothResult(parliamentConstituencyElections.get(0), candidateResults, boothResults, boothConstituencyElections);
	}

	
	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothResults, List<BoothConstituencyElection> boothConstituencyElections)throws Exception{
		
		for(int i =0 ; i<boothResults.size(); i++){
			List<BoothResult> boothResultModels = boothResultDAO.findByBoothConstituencyElection(boothConstituencyElections.get(i).getBoothConstituencyElectionId());
			if(boothResultModels.size()>0){
				if(log.isDebugEnabled()){
					log.debug("Booth Result Already Exists With BoothConstiElecId:"+boothConstituencyElections.get(i).getBoothConstituencyElectionId());
				}
				continue;
			}
			BoothResult boothResultModel = new BoothResult(boothConstituencyElections.get(i), boothResults.get(i).getTotalNoOfValidVotes(), boothResults.get(i).getRejectedVotes(), boothResults.get(i).getTenderedVotes());
			boothResultDAO.save(boothResultModel);
		}		
		for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
			List<Nomination> nominations  = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , constiElecObj.getConstiElecId());
			if(nominations.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("Exists More than One Or No Nominations for Part No:"+candidateBoothWiseResult.getCandidateName()+","+constiElecObj.getConstiElecId());
				}
				continue;
			}
			Nomination nomination = nominations.get(0);
			List<BoothResultExcelVO> boothResultsForCandidate = candidateBoothWiseResult.getBoothresults();
			for(int i=0; i<boothResultsForCandidate.size(); i++){
				BoothConstituencyElection boothConstituencyElection = boothConstituencyElections.get(i);
				insertCandidateBoothResult(nomination, boothConstituencyElection, boothResultsForCandidate.get(i).getVotesEarned());	
			}
		}
	}
			
	public CandidateBoothResult insertCandidateBoothResult(Nomination nomination, BoothConstituencyElection boothConstituencyElection, Long votesEarned)throws Exception{
		CandidateBoothResult candidateBoothResult = null;
		List<CandidateBoothResult> candidateBoothResults = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(nomination.getNominationId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(candidateBoothResults.size() > 0){
			if(log.isDebugEnabled()){
				log.debug("CandidateBoothResults Already Exists With NominationId:"+nomination.getNominationId()+"And BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
			}
			return null;
		}
		candidateBoothResult = new CandidateBoothResult(votesEarned, nomination, null, boothConstituencyElection);
		return candidateBoothResultDAO.save(candidateBoothResult);
	}

}
