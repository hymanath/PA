package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.excel.BoothResultVO;
import com.itgrids.partyanalyst.excel.CandidateBoothWiseResult;
import com.itgrids.partyanalyst.excel.ExcelReaderForBoothResult;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;

public class BoothResultPopulationService implements IBoothResultPopulationService{

	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
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
	
	public boolean readExcelAndInsertData(String electionYear, String constituencyName, String electionType, String districtName, String filePath){
		ExcelReaderForBoothResult excelReaderForBoothResult = new ExcelReaderForBoothResult();
		List<CandidateBoothWiseResult> candidateResults = excelReaderForBoothResult.readExcelFile(filePath);
		List<ConstituencyElection> list = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionType, districtName);
		if(list == null || list.size() > 1){
			return false;
		}
		System.out.println("In readExcelAndInsertData---1");
		ConstituencyElection constiElecObj = list.get(0);
		System.out.println("In readExcelAndInsertData---2");
		checkAndInsertBoothResult(constiElecObj, candidateResults);
		System.out.println("In readExcelAndInsertData---3");
		return true;
	}
	
	public void checkAndInsertBoothResult(ConstituencyElection constiElecObj, List<CandidateBoothWiseResult> candidateResults){
		System.out.println("In checkAndInsertBoothResult---1");
		for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
			System.out.println("In checkAndInsertBoothResult---in for");
			Nomination nomination = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , constiElecObj.getConstiElecId());
			List<BoothResultVO> boothResults = candidateBoothWiseResult.getBoothresults();
			for(BoothResultVO boothResult:boothResults){
				System.out.println("In checkAndInsertBoothResult---in for BoothResultVO");
				BoothConstituencyElection boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(boothResult.getPartNo(), constiElecObj.getConstiElecId());
				insertBoothResult(nomination, boothConstituencyElection, boothResult.getVotesEarned());
			}
		}
	}
			
	
	public BoothResult insertBoothResult(Nomination nomination, BoothConstituencyElection boothConstituencyElection, Double votesEarned){
		System.out.println("In insertBoothResult---1");
		BoothResult boothResult = null;
		boothResult = boothResultDAO.findByNominationAndBoothConstituencyElection(nomination.getNominationId(), boothConstituencyElection.getBoothConstituencyElectionId());
		System.out.println("In insertBoothResult---2");
		if(boothResult != null){
			System.out.println("Booth Result Already Exists With NominationId:"+nomination.getNominationId()+"And BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
			return boothResult;
		}
		System.out.println("Booth Result inserted With NominationId:"+nomination.getNominationId()+"And BoothConstiElecId:"+boothConstituencyElection.getBoothConstituencyElectionId());
		boothResult = new BoothResult(votesEarned, nomination, boothConstituencyElection);
		return boothResultDAO.save(boothResult);
	}
}
