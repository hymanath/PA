package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
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
	private static final Logger log = Logger.getLogger(BoothResultPopulationService.class);

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
	
	public ResultStatus readExcelAndInsertData(String electionYear, Long electionScopeId, String filePath){
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		ResultStatus resultVO = new ResultStatus();
		try{
			excelBoothResultReader.readExcel(filePath, false);
			List<ConstituencyBoothBlock> list = excelBoothResultReader.getConstituenciesBlocsks();
			if(log.isDebugEnabled()){
				log.info("Total Constituencies::"+list.size());
			}		
			for(ConstituencyBoothBlock assemblyConstituencyBlock:list){
				String constituencyName = assemblyConstituencyBlock.getConstituencyName();
				Long districtId = assemblyConstituencyBlock.getDistrictId();
				if(log.isDebugEnabled()){
					log.info("constituencyName "+constituencyName);
					log.info("districtId  "+districtId);
				}	
				List<BoothResult> boothResults = boothResultDAO.findByConstituencyAndElection(constituencyName, electionYear, electionScopeId);
				if(boothResults.size() > 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Results Already Exists For ElectionYear::"+electionYear+" Constituency Name::"+constituencyName);
					}
					continue;
				}
				List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
				if(constituencyElections == null || constituencyElections.size() > 1){
					if(log.isDebugEnabled()){
						log.error("Exists More than One ConstituencyElections For Constituency And Election Year::"+electionYear+","+constituencyName);
					}
					return resultVO;
				}
				List<CandidateBoothWiseResult> candidateBoothResults = assemblyConstituencyBlock.getCandidateResults();
				List<BoothResultValueObject> boothresults = assemblyConstituencyBlock.getBoothResults();
				ConstituencyElection constiElecObj = constituencyElections.get(0);
				checkAndInsertBoothResult(constiElecObj, candidateBoothResults, boothresults);
				if(log.isDebugEnabled()){
					log.debug("====================================Before Pecentage Calculation=====================================");
				}
				calculatePecentages(constiElecObj.getConstiElecId());
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
	
	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothresults)throws Exception{
			for(BoothResultValueObject boothResult:boothresults){
				List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(boothResult.getPartNumber(), constiElecObj.getConstiElecId());
				if(boothConstituencyElections.size() != 1){
					if(log.isDebugEnabled()){
						log.error("Exists More than One Or No BoothConstituencyElections for Part No:"+boothResult.getPartNumber()+",ConstiElecId:"+constiElecObj.getConstiElecId());
					}
				}
				BoothConstituencyElection boothConstituencyElection = boothConstituencyElections.get(0);
				List<BoothResult> boothResultModels = boothResultDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
				if(boothResultModels.size()>0){
					if(log.isDebugEnabled()){
						log.error("Exists More than One Or No BoothResults for Part No:"+boothConstituencyElection.getBoothConstituencyElectionId());
					}
				}
				BoothResult boothResultModel = new BoothResult(boothConstituencyElection, boothResult.getTotalNoOfValidVotes(), boothResult.getRejectedVotes(), boothResult.getTenderedVotes());
				System.out.println("boothResultDAO Inserted with Id:"+boothResultDAO.save(boothResultModel).getBoothResultId());
			}
			
			for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
				List<Nomination> nominations = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , constiElecObj.getConstiElecId());
				if(nominations.size() != 1){
					if(log.isDebugEnabled()){
						log.error("Exists More than One Or No Nominations for Part No:"+candidateBoothWiseResult.getCandidateName()+","+constiElecObj.getConstiElecId());
					}
				}
				Nomination nomination = nominations.get(0);
				List<BoothResultExcelVO> boothResultsForCandidate = candidateBoothWiseResult.getBoothresults();
				for(BoothResultExcelVO boothResultExcel:boothResultsForCandidate){
					List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(boothResultExcel.getPartNo(), constiElecObj.getConstiElecId());
					if(boothConstituencyElections.size() != 1){
						if(log.isDebugEnabled()){
							log.error("Exists More than One Or No BoothConstituencyElections for Part No:"+boothResultExcel.getPartNo()+",ConstiElecId:"+constiElecObj.getConstiElecId());
						}
					}
					BoothConstituencyElection boothConstituencyElection = boothConstituencyElections.get(0);
					insertCandidateBoothResult(nomination, boothConstituencyElection, boothResultExcel.getVotesEarned());
				}
			}		
	}
				
	public CandidateBoothResult insertCandidateBoothResult(Nomination nomination, BoothConstituencyElection boothConstituencyElection, Long votesEarned)throws Exception{
		List<CandidateBoothResult> candidateBoothResults = candidateBoothResultDAO.findByNominationAndBoothConstituencyElection(nomination.getNominationId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(candidateBoothResults.size() > 0){
			if(log.isDebugEnabled()){
				log.error("CandidateBoothResults Already Exists With NominationId:"+nomination.getNominationId()+"And BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
			}
		}
		CandidateBoothResult candidateBoothResult = new CandidateBoothResult(votesEarned, nomination, null, boothConstituencyElection);
		return candidateBoothResultDAO.save(candidateBoothResult);
	}
	
	public void calculatePecentages(Long constituencyElectionId){
		if(log.isDebugEnabled()){
			log.debug("====================================In Pecentage Calculation=====================================");
		}
		List<CandidateBoothResult> candidateBoothResults = candidateBoothResultDAO.findByConstituencyElection(constituencyElectionId);
		for(CandidateBoothResult candidateBoothResult:candidateBoothResults){
			Long validVotes = candidateBoothResult.getVotesEarned();
			Long votesEarned = candidateBoothResult.getBoothConstituencyElection().getBoothResult().getValidVotes();
			String percentage = calculateVotesPercengate(validVotes, votesEarned);
			candidateBoothResult.setPercentage(percentage);
			candidateBoothResultDAO.save(candidateBoothResult);
		}
	}
	
	private String calculateVotesPercengate(Long validVotes, Long votesEarned){
		BigDecimal percentage= new BigDecimal(0.0);
		if((validVotes!=null && validVotes.longValue()>0) && (votesEarned!=null && votesEarned.longValue()>0)){
			percentage= new BigDecimal((votesEarned.floatValue()/validVotes.floatValue())*100).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		return percentage.toString();
	}
}
