package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.excel.BoothInfo;
import com.itgrids.partyanalyst.excel.ExcelReader;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothVillageCensus;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothPopulationService;

public class BoothPopulationService implements IBoothPopulationService{

	private ITehsilDAO tehsilDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothVillageCensusDAO boothVillageCensusDAO;
	
	public BoothPopulationService(){
		
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
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IBoothVillageCensusDAO getBoothVillageCensusDAO() {
		return boothVillageCensusDAO;
	}

	public void setBoothVillageCensusDAO(
			IBoothVillageCensusDAO boothVillageCensusDAO) {
		this.boothVillageCensusDAO = boothVillageCensusDAO;
	}

	public boolean readExcelFileAndPolpulate(String filePath, String electionYear, String electionType){
		List<BoothInfo> boothRecords = new ArrayList<BoothInfo>();
		ExcelReader excelReader = new ExcelReader();
		boothRecords = excelReader.readExcelFile(filePath);
		insertData(boothRecords, electionYear, electionType);
		return true;
	}
	
	private void insertData(List<BoothInfo> boothRecords, String electionYear, String electionType){
		for(int i=0; i<boothRecords.size(); i++){
			System.out.println("Record No :"+i);
			BoothInfo boothRecord = boothRecords.get(i);
			String tehsilName = boothRecord.getMandalName();
			String districtName = boothRecord.getDistrictName();
			String constituencyName = boothRecord.getConstituencyName();
			String censusCode = boothRecord.getCensusCode();
			String partNo = boothRecord.getPartNo();
			String partName = boothRecord.getPartName();
			String location = boothRecord.getLocation();
			String villagesCovered = boothRecord.getVillagesCovered();
			Double maleVoters = checkForBlank(boothRecord.getMaleVoters());
			Double femaleVoters = checkForBlank(boothRecord.getFemaleVoters());
			Double totalVoters = checkForBlank(boothRecord.getTotalVoters());
			
			if(StringUtils.isBlank(partName)&&(partNo.contains("-")||partNo.contains("."))){
				String [] nameAndNo = StringUtils.split(partNo.trim(), "-.");
				partNo = nameAndNo[0];
				partName = nameAndNo[1];
			}
			
			Booth booth = checkAndInsertBooth(new Long(partNo), partName, location, villagesCovered, maleVoters, femaleVoters, totalVoters, tehsilName, districtName);
			checkAndInsertBoothConstituencyElection(booth, electionYear, constituencyName, electionType, districtName);
			checkAndInsertBoothVillageCensus(booth,censusCode.trim());
		}
	}
	
	private Booth checkAndInsertBooth(Long partNo, String partName, String location, String villagesCovered, Double maleVoters, Double femaleVoters, Double totalVoters, String tehsilName, String districtName){
		Booth booth = null;
		List<Tehsil> tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtName);
		if(tehsils!=null && tehsils.size() == 1){
			Tehsil tehsil = tehsils.get(0);
			booth = boothDAO.findByTehsilAndPartNo(tehsilName, partNo);
			if(booth != null)
				return booth;
			else{
				booth = new Booth(partNo,partName,location,villagesCovered,tehsil,maleVoters,femaleVoters,totalVoters,null,null);
				System.out.println("Booth inserted With Tehsil:"+tehsilName+" and partNo:"+partNo);
				return boothDAO.save(booth);
			}
		}else{
			System.out.println("More than one Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtName);
		}
		return booth;
	}
	
	private BoothConstituencyElection checkAndInsertBoothConstituencyElection(Booth booth, String electionYear, String constituencyName, String electionType, String districtName){
		BoothConstituencyElection boothConstituencyElection = null;
		List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionType, districtName);
		if(constituencyElections!=null && constituencyElections.size() == 1){
			ConstituencyElection constituencyElection = constituencyElections.get(0);
			boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(booth.getPartNo(), constituencyElection.getConstiElecId());
			if(boothConstituencyElection != null)
				return boothConstituencyElection;		
			else{
				boothConstituencyElection = new BoothConstituencyElection(booth,constituencyElection,null);
				System.out.println("BoothConstituencyElection inserted With Booth Id:"+booth.getPartName()+" and ConstiElecId:"+constituencyElection.getConstiElecId());
				return boothConstituencyElectionDAO.save(boothConstituencyElection);
			}
		}else{
			System.out.println("More than one ConstituencyElections Exist With electionYear, constituencyName, electionType, districtName:"+electionYear+" "+constituencyName+" "+electionType+" "+districtName);
		}
		return boothConstituencyElection;
	}
	
	private BoothVillageCensus checkAndInsertBoothVillageCensus(Booth booth,String censusCode){
		BoothVillageCensus boothVillageCensus = null;
		String [] censusCodes = null;
		if(censusCode.contains("\n")||censusCode.contains(",")){
			censusCodes = StringUtils.split(censusCode, ",\n");
			System.out.println("No of census codes :"+censusCodes.length);
			for(int i=0; i < censusCodes.length; i++){
				Long censusCodeL = new Long(censusCodes[i]);
				if(checkBoothVillageCensus(booth, censusCodeL)){
					boothVillageCensus = new BoothVillageCensus(censusCodeL,booth);
					System.out.println("boothVillageCensus in for Inserted with id and censuscode:"+booth.getBoothId()+" "+censusCodeL);
					boothVillageCensusDAO.save(boothVillageCensus);
				}else{
					System.out.println("BoothVillageCensus already exists with id and censuscode:"+booth.getBoothId()+" "+censusCodeL);
					return boothVillageCensus;
				}
			}
		}else{
			Long censusCodeL = new Long(censusCode); 
			if(checkBoothVillageCensus(booth, new Long(censusCodeL))){
				boothVillageCensus = new BoothVillageCensus(new Long(censusCodeL),booth);
				System.out.println("boothVillageCensus out of for Inserted with id and censuscode:"+booth.getBoothId()+" "+censusCodeL);
				return boothVillageCensusDAO.save(boothVillageCensus);
			}else{
				System.out.println("BoothVillageCensus already exists with id and censuscode:"+booth.getBoothId()+" "+new Long(censusCodeL));
				return boothVillageCensus;
			}
		}
		return boothVillageCensus;
	}

	private boolean checkBoothVillageCensus(Booth booth, Long censusCode){
		boolean censusFlag = true;
		BoothVillageCensus boothVillageCensus = boothVillageCensusDAO.findByBoothAndCensusCode(booth.getBoothId(), censusCode);
		if(boothVillageCensus != null){
			System.out.println("BoothVillageCensus All Ready Exists With Booth Id:"+booth.getBoothId()+" and CensusCode:"+censusCode);
			censusFlag = false;
		}
		return censusFlag;
	}
	
	private Double checkForBlank(String svalue){
		Double dvalue = new Double(0);
		if(!StringUtils.isBlank(svalue))
			dvalue = new Double(svalue);
		return dvalue;
	}
	

}
