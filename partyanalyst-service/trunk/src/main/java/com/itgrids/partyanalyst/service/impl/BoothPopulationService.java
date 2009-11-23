package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
import com.itgrids.partyanalyst.excel.CsvException;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothVillageCensus;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothPopulationService;
import com.itgrids.partyanalyst.service.IStaticDataService;

public class BoothPopulationService implements IBoothPopulationService{

	private ITehsilDAO tehsilDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothVillageCensusDAO boothVillageCensusDAO;
	private IStaticDataService staticDataService;
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

	public void setStaticDataService(
			IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
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

	public boolean readExcelFileAndPolpulate(String filePath, String electionYear, Long electionScopeId) throws CsvException{
		BoothDataExcelReader excelReader = new BoothDataExcelReader();
		System.out.println("-----------------------In readExcelFileAndPolpulate");
		excelReader.readExcelFile(filePath);
		List<BoothInfo> boothRecords = excelReader.getBoothsInfo();
		System.out.println("Total Records:"+boothRecords.size());
		insertData(boothRecords, electionYear, electionScopeId);
		return true;
	}
	
	private void insertData(List<BoothInfo> boothRecords, String electionYear, Long electionScopeId){
		System.out.println("-----------------------In insertData");
		for(int i=0; i<boothRecords.size(); i++){
			System.out.println("Record No :"+i);
			BoothInfo boothRecord = boothRecords.get(i);
			String tehsilName = boothRecord.getMandalName();
			Long districtId = boothRecord.getDistrictId();
			String constituencyName = boothRecord.getConstituencyName();
			String censusCode = boothRecord.getCensusCode();
			String partNo = boothRecord.getPartNo();
			String partName = boothRecord.getPartName();
			String location = boothRecord.getLocation();
			String villagesCovered = boothRecord.getVillagesCovered();
			Long maleVoters = boothRecord.getMaleVoters();
			Long femaleVoters = boothRecord.getFemaleVoters();
			Long totalVoters = boothRecord.getTotalVoters();
			
			if(StringUtils.isBlank(partName)&&(partNo.contains("-")||partNo.contains("."))){
				String [] nameAndNo = StringUtils.split(partNo.trim(), "-.");
				partNo = nameAndNo[0];
				partName = nameAndNo[1];
			}
			
			Booth booth = checkAndInsertBooth(partNo, partName, location, villagesCovered, maleVoters, femaleVoters, totalVoters, tehsilName, districtId);
			checkAndInsertBoothConstituencyElection(booth, electionYear, constituencyName, electionScopeId, districtId);
			checkAndInsertBoothVillageCensus(booth,censusCode.trim());
		}
	}
	
	private Booth checkAndInsertBooth(String partNo, String partName, String location, String villagesCovered, Long maleVoters, Long femaleVoters, Long totalVoters, String tehsilName, Long districtId){
		System.out.println("-----------------------In checkAndInsertBooth");
		Booth booth = null;
		List<Tehsil> tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
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
			System.out.println("More than one Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtId);
		}
		return booth;
	}
	
	private BoothConstituencyElection checkAndInsertBoothConstituencyElection(Booth booth, String electionYear, String constituencyName, Long electionScopeId, Long districtId){
		System.out.println("-----------------------In checkAndInsertBoothConstituencyElection");
		BoothConstituencyElection boothConstituencyElection = null;
		List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
		if(constituencyElections!=null && constituencyElections.size() == 1){
			ConstituencyElection constituencyElection = constituencyElections.get(0);
			boothConstituencyElection = boothConstituencyElectionDAO.findByBoothAndConstiuencyElection(booth.getPartNo(), constituencyElection.getConstiElecId());
			if(boothConstituencyElection != null)
				return boothConstituencyElection;		
			else{
				boothConstituencyElection = new BoothConstituencyElection(booth,constituencyElection,null,null);
				System.out.println("BoothConstituencyElection inserted With Booth Id:"+booth.getPartName()+" and ConstiElecId:"+constituencyElection.getConstiElecId());
				return boothConstituencyElectionDAO.save(boothConstituencyElection);
			}
		}else{
			System.out.println("More than one ConstituencyElections Exist With electionYear, constituencyName, electionType, districtName:"+electionYear+" "+constituencyName+" "+electionScopeId+" "+districtId);
		}
		return boothConstituencyElection;
	}
	
	private BoothVillageCensus checkAndInsertBoothVillageCensus(Booth booth,String censusCode){
		BoothVillageCensus boothVillageCensus = null;
		String [] censusCodes = null;
		if(censusCode.contains("\n")||censusCode.contains(",")||censusCode.contains(" ")){
			censusCodes = StringUtils.split(censusCode, ",\n ");
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
	

	public List getMandalAllElectionDetails(Long tehsilID, Long partyID, boolean allianceFlag){
		List<MandalAllElectionDetailsVO> mandalAllElectionDetails = new ArrayList<MandalAllElectionDetailsVO>();
		// 0 - election, 1 - totalvoters , 2 - validvotes, 3- rejectedvotes, 4-tenderedvotes
		List result = boothConstituencyElectionDAO.getAllElectionBoothVotersForMandal(tehsilID);
		for(int i=0; i<result.size(); i++){
			Object[] obj = (Object[]) result.get(i);
			MandalAllElectionDetailsVO objectVO = new MandalAllElectionDetailsVO();
			Election election = (Election)obj[0];
			objectVO.setElectionYear(election.getElectionYear());
			objectVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
			objectVO.setTotalVoters(new Long(obj[1].toString()));
			objectVO.setValidVoters(new Long(obj[2].toString()));
			objectVO.setRejectedVoters(new Long(obj[3].toString()));
			objectVO.setTenderedVoters(new Long(obj[4].toString()));
			objectVO.setElectionScopeID(election.getElectionScope().getElectionScopeId());
			objectVO.setElectionID(election.getElectionId());
			mandalAllElectionDetails.add(objectVO);
		}
		
		Iterator<MandalAllElectionDetailsVO> iterator =mandalAllElectionDetails.iterator();
		while(iterator.hasNext()){
			MandalAllElectionDetailsVO mandalAllElectionDetailsVO = iterator.next();

			StringBuilder sb = new StringBuilder();
			if(allianceFlag){
				List<SelectOptionVO> partyIDs = staticDataService.getAlliancePartiesAsVO(
						mandalAllElectionDetailsVO.getElectionYear(), 
						new Long(mandalAllElectionDetailsVO.getElectionType()), partyID);
				for(SelectOptionVO obj : partyIDs){
					sb.append(",").append(obj.getId());
				}
			}else{
				sb.append(",").append(partyID);
			}
			
			List temp = boothConstituencyElectionDAO.getPartyVotesByMandal(tehsilID, sb.substring(1), 
					mandalAllElectionDetailsVO.getElectionID());
			//0-firstName, 1-middlename, 2-lastname, 3-election, 4-votesearned, 5-partyId, 6-shortName
			int i = getAlliancePartyInfo(temp, partyID);
			Object[] obj = (Object[]) temp.get(i);
			StringBuilder name = new StringBuilder();
			if(obj[0]!=null){
				name.append(obj[0].toString()).append(" ");
			}
			if(obj[1]!=null){
				name.append(obj[1].toString()).append(" ");
			}
			if(obj[2]!=null){
				name.append(obj[2].toString());
			}
			mandalAllElectionDetailsVO.setPartyShortName(obj[6].toString());
			mandalAllElectionDetailsVO.setCandidateName(name.toString());
			Long totalPartyVotes = new Long(obj[4].toString());
			double partyPercentage = (totalPartyVotes*100)/mandalAllElectionDetailsVO.getValidVoters();//getTotalVoters();
			mandalAllElectionDetailsVO.setPartyVotesPercentage(new Double(partyPercentage).toString());
		}
		
		return mandalAllElectionDetails;
	}
	/**
	 * checking for rebels in the the List temp and sending the alliance party handler
	 * @param temp
	 * @param partyID
	 * @return
	 */
	private int getAlliancePartyInfo(List temp, Long partyID){
		int result = 0;
		if(temp.size()==0)
			return 0;
		long partyVotes = 0;
		for(int i=0; i<temp.size(); i++){
			Object[] obj = (Object[]) temp.get(i);
			if(partyID.equals(obj[5]))
				return i;
			long presentPartyVotes = new Long(obj[4].toString()).longValue();
			
			if(presentPartyVotes>partyVotes){
				partyVotes=presentPartyVotes;
				result = i;
			}						
		}
		return result;
		
	}
}
