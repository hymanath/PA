package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.BoothDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.excel.booth.BoothResultExcelVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultValueObject;
import com.itgrids.partyanalyst.excel.booth.CandidateBoothWiseResult;
import com.itgrids.partyanalyst.excel.booth.ConstituencyBoothBlock;
import com.itgrids.partyanalyst.excel.booth.ExcelBoothResultReader;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothResult;
import com.itgrids.partyanalyst.model.BoothVillageCensus;
import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothPopulationService implements IBoothPopulationService{

	private ITehsilDAO tehsilDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothResultDAO boothResultDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothDAO boothDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothVillageCensusDAO boothVillageCensusDAO;
	private INominationDAO nominationDAO;
	private static final Logger log = Logger.getLogger(DelimitationConstituencyMandalService.class);

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IBoothVillageCensusDAO getBoothVillageCensusDAO() {
		return boothVillageCensusDAO;
	}

	public void setBoothVillageCensusDAO(
			IBoothVillageCensusDAO boothVillageCensusDAO) {
		this.boothVillageCensusDAO = boothVillageCensusDAO;
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

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	public ResultWithExceptionVO readExcelAndPopulateBoothData(File filePath, String electionYear, Long electionScopeId) {
		ResultWithExceptionVO resultWithExceptionVO = new ResultWithExceptionVO();
		List<ConstituencyInfoVO> finalResult = new ArrayList<ConstituencyInfoVO>();
		long start = System.currentTimeMillis();
		try{
			BoothDataExcelReader excelReader = new BoothDataExcelReader();
			excelReader.readExcelFile(filePath);
			List<BoothDataUploadVO> boothData = excelReader.getBoothDataUploadVOs();
			List<Constituency> constituencies = null;
			Constituency constituency = null;
			Long districtId = null;
			Long totalBooths = 0l;
			Long year = Long.parseLong(electionYear);
			String constituencyName = "";
			for(BoothDataUploadVO boothDataUploadVO:boothData){
				districtId = boothDataUploadVO.getDistrictId();
				constituencyName = boothDataUploadVO.getConstituencyName();
				constituencies = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId(constituencyName, districtId, electionScopeId);
				if(constituencies.size() != 1){
					if(log.isDebugEnabled()){
						log.debug(constituencies.size()+" No Of Constituencies Exists with Constituency Name::"+constituencyName+" And District Id::"+districtId);
					}
					continue;
				}
				constituency = constituencies.get(0);
				totalBooths = (Long)boothDAO.findByConstituencyAndElectionYear(constituency.getConstituencyId(), year).get(0);
				if(totalBooths > 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Data Already Exists For ElectionYear::"+electionYear+" Constituency Name::"+constituencyName);
					}
					continue;
				}
				checkAndInsertBooth(constituency, boothDataUploadVO.getBooths(), districtId, year);
			}
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
			/*resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultVO.setResultPartial(true);*/
		}catch(Exception ex){
			ex.printStackTrace();
			/*resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);*/
		}
		long end = System.currentTimeMillis();
		System.out.println("Total Time Taken::"+(end - start)/1000);
		return resultWithExceptionVO;
	}
	
	private void checkAndInsertBooth(Constituency constituency, List<BoothInfo> boothRecords, Long districtId, Long year)throws Exception{
		Booth booth = null;
		String tehsilName = "";
		String partNo = "";
		String partName = "";
		String location = "";
		String villagesCovered = "";
		String censusCode = "";
		Long maleVoters = 0l;
		Long femaleVoters = 0l;
		Long totalVoters = 0l;
		Tehsil tehsil = null;
		List<Tehsil> tehsils = null;
		Long boothsCount = 0l;
		
		for(BoothInfo boothRecord:boothRecords){
			tehsils = null;
			tehsilName = boothRecord.getMandalName().trim();
			partNo = boothRecord.getPartNo();
			partName = boothRecord.getPartName();
			location = boothRecord.getLocation();
			villagesCovered = boothRecord.getVillagesCovered();
			censusCode = boothRecord.getCensusCode();
			maleVoters = checkAndAssignLong(boothRecord.getMaleVoters());
			femaleVoters = checkAndAssignLong(boothRecord.getFemaleVoters());
			totalVoters = checkAndAssignLong(boothRecord.getTotalVoters());
			if(tehsilName.length() != 0)
				tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
			if((tehsils!= null && tehsils.size() == 1) || tehsilName.length() == 0){
				if(tehsils != null)
					tehsil = tehsils.get(0);
				else
					tehsil = null;
				boothsCount = (Long)boothDAO.findByPartNoConstituencyIdAndYear(constituency.getConstituencyId(), year, partNo).get(0);
				if(boothsCount > 0){
					if(log.isDebugEnabled()){
						log.error(boothsCount+" No of Booths Exists with Part No:"+partNo+" And Constitency Name:"+constituency.getName()+
								" In the Year"+year);
					}
					continue;
				}
					
				booth = new Booth(partNo,partName,location,villagesCovered,tehsil,maleVoters,femaleVoters,
						totalVoters, constituency, year, null,null, null,null);
				booth = boothDAO.save(booth);
				checkAndInsertBoothVillageCensus(booth, censusCode);
			}else{
				if(log.isDebugEnabled()){
					log.error("More than one Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtId);
				}
			}
		}
		
	}
			
	private BoothVillageCensus checkAndInsertBoothVillageCensus(Booth booth,String censusCode)throws Exception{
		BoothVillageCensus boothVillageCensus = null;
		String [] censusCodes = null;
		if(censusCode.contains("\n")||censusCode.contains(",")||censusCode.contains(" ")){
			censusCodes = StringUtils.split(censusCode, ",\n ");
			for(int i=0; i < censusCodes.length; i++){
				Long censusCodeL = new Long(censusCodes[i]);
				if(censusCodeL.intValue() == 0)
					continue;
				if(checkBoothVillageCensus(booth, censusCodeL)){
					boothVillageCensus = new BoothVillageCensus(censusCodeL,booth);
					boothVillageCensusDAO.save(boothVillageCensus);
				}else{
					if(log.isDebugEnabled()){
						log.error("BoothVillageCensus already exists with id and censuscode:"+booth.getBoothId()+" "+censusCodeL);
					}
					return boothVillageCensus;
				}
			}
		}else{
			Long censusCodeL = new Long(censusCode); 
			if(censusCodeL.intValue() == 0)
				return null;
			if(checkBoothVillageCensus(booth, new Long(censusCodeL))){
				boothVillageCensus = new BoothVillageCensus(new Long(censusCodeL),booth);
				return boothVillageCensusDAO.save(boothVillageCensus);
			}else{
				if(log.isDebugEnabled()){
					log.error("BoothVillageCensus already exists with id and censuscode:"+booth.getBoothId()+" "+new Long(censusCodeL));
				}
				return boothVillageCensus;
			}
		}
		return boothVillageCensus;
	}

	private boolean checkBoothVillageCensus(Booth booth, Long censusCode)throws Exception{
		boolean censusFlag = true;
		List<BoothVillageCensus> boothVillageCensuses = boothVillageCensusDAO.findByBoothAndCensusCode(booth.getBoothId(), censusCode);
		if(boothVillageCensuses.size() > 0){
			censusFlag = false;
		}
		return censusFlag;
	}
	
	public Long checkAndAssignLong(String value)throws Exception{
		Long longVal = new Long(0);
		if(!StringUtils.isEmpty(value)){
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
			else
				if(log.isDebugEnabled()){
					log.error(value+" Is Not A Numaric Value");
				}
		}
		return longVal;
	}

	
	public ResultWithExceptionVO readExcelAndPopulateBoothResult(String electionYear, Long electionScopeId, File filePath){
		long start = System.currentTimeMillis();
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		ResultWithExceptionVO resultVO = new ResultWithExceptionVO();
		Boolean isParliament = false;
		try{
			
			if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionScopeDAO.get(electionScopeId).getElectionType().getElectionType()))
				isParliament = true;
			
			List<ConstituencyBoothBlock> list = excelBoothResultReader.readExcel(filePath, isParliament);
			String pcName = ""; 
			String acName = "";
			Long districtId = null;
			Long stateId = null;
			Long booths = null;
			Long boothResults = null;
			List<ConstituencyElection> constituencyElections = null;
			List<CandidateBoothWiseResult> candidateBoothResults = null;
			List<BoothResultValueObject> boothresults = null;
			ConstituencyElection constiElecObj = null;
			
			
			for(ConstituencyBoothBlock constituencyBlock:list){
				acName = constituencyBlock.getConstituencyName();
				pcName = constituencyBlock.getParliamentConstituencyName();
				districtId = constituencyBlock.getDistrictId();
				stateId = constituencyBlock.getStateId();
				
				booths = (Long)boothDAO.findbyConstituencyNameDistrictIdAndElectionYear(acName, districtId, Long.parseLong(electionYear)).get(0);
				
				if(booths == 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Data Doesn't Exists For ElectionYear::"+electionYear+" Constituency Name::"+acName);
					}
					continue;
				}
				
				boothResults = (Long)boothResultDAO.getParliamentResultHappenedInAssembly(acName, districtId, electionScopeId, electionYear).get(0);
				
				if(boothResults > 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Results Already Exists For ElectionYear::"+electionYear+" Constituency Name::"+acName);
					}
					continue;
				}
				if(isParliament)
					constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndState(electionYear, pcName, electionScopeId, stateId);
				else
					constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, acName, electionScopeId, districtId);
				
				if(constituencyElections == null || constituencyElections.size() > 1){
					if(log.isDebugEnabled()){
						log.error("Exists More than One ConstituencyElections For Constituency And Election Year::"+electionYear+","+acName);
					}
					continue;
				}
				
				candidateBoothResults = constituencyBlock.getCandidateResults();
				boothresults = constituencyBlock.getBoothResults();
				constiElecObj = constituencyElections.get(0);
				checkAndInsertBoothResult(acName, districtId, electionYear, constiElecObj, candidateBoothResults, boothresults);
				
			}
			
		}catch(IndexOutOfBoundsException ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultVO.setResultPartial(true);
			ex.printStackTrace();
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
			ex.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Total Time Taken::"+(end - start)/1000);
		return resultVO;
	}
	
	public void checkAndInsertBoothResult(String acName, Long districtId, String electionYear, ConstituencyElection constiElecObj, 
			List<CandidateBoothWiseResult> candidateResults, 
			List<BoothResultValueObject> boothresults)throws Exception{
		Map<String, BoothConstituencyElection> boothConstiPartNoMap = new HashMap<String, BoothConstituencyElection>();
		List<Nomination> nominations = null;
		Nomination nomination = null;
		List<BoothResultExcelVO> boothResultsForCandidate = null;
		
		checkAndInsertBoothConstituencyElectionAndResult(acName, districtId, electionYear, boothConstiPartNoMap, constiElecObj, boothresults);
			
		if(boothConstiPartNoMap.size() > 0)
			for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
				nominations = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , 
						constiElecObj.getConstiElecId());
				if(nominations.size() != 1){
					if(log.isDebugEnabled()){
						log.error("Exists More than One Or No Nominations for Part No:"+candidateBoothWiseResult.getCandidateName()+","+
								constiElecObj.getConstiElecId());
					}
					continue;
				}
				
				nomination = nominations.get(0);
				
				boothResultsForCandidate = candidateBoothWiseResult.getBoothresults();
				for(BoothResultExcelVO boothResultExcel:boothResultsForCandidate)
					if(boothConstiPartNoMap.get(boothResultExcel.getPartNo()) != null)
						candidateBoothResultDAO.save(new CandidateBoothResult(boothResultExcel.getVotesEarned(), 
								nomination, null, boothConstiPartNoMap.get(boothResultExcel.getPartNo())));
			}		
	}
				
	private void checkAndInsertBoothConstituencyElectionAndResult(String acName, Long districtId, String electionYear,
			Map<String, BoothConstituencyElection> boothConstiPartNoMap,
			ConstituencyElection constiElecObj,
			List<BoothResultValueObject> boothresults) {
		List<Booth> booths = null;
		Booth booth = null;
		BoothConstituencyElection boothConstituencyElection = null;
		for(BoothResultValueObject boothResult:boothresults){
			booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear(acName,
					 districtId, Long.parseLong(electionYear), boothResult.getPartNumber());
			
			if(booths.size() != 1)
				continue;
			booth = booths.get(0);
			boothConstituencyElection = boothConstituencyElectionDAO.save(new BoothConstituencyElection(booth, constiElecObj, null, null, null));
			boothResultDAO.save(new BoothResult(boothConstituencyElection, boothResult.getTotalNoOfValidVotes(), 
					boothResult.getRejectedVotes(), boothResult.getTenderedVotes()));
			boothConstiPartNoMap.put(boothResult.getPartNumber(), boothConstituencyElection);
		}
	}
	
}
