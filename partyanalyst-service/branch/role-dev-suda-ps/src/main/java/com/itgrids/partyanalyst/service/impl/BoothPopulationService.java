package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.BoothDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.BoothInfo;
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
	private static final Logger log = Logger.getLogger(DelimitationConstituencyMandalService.class);
	
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

	public ResultStatus readExcelFileAndPolpulate(File filePath, String electionYear, Long electionScopeId) {
		ResultStatus resultVO = new ResultStatus();
		try{
			BoothDataExcelReader excelReader = new BoothDataExcelReader();
			excelReader.readExcelFile(filePath);
			List<BoothDataUploadVO> boothData = excelReader.getBoothDataUploadVOs();
			for(BoothDataUploadVO boothDataUploadVO:boothData){
				Long districtId = boothDataUploadVO.getDistrictId();
				String constituencyName = boothDataUploadVO.getConstituencyName();
				List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByConstituencyAndElection(constituencyName, electionYear, electionScopeId);
				if(boothConstituencyElections.size() > 0){
					if(log.isDebugEnabled()){
						log.debug("Booth Data Already Exists For ElectionYear::"+electionYear+" Constituency Name::"+constituencyName);
					}
					continue;
				}
				List<BoothInfo> boothsInConstituency = boothDataUploadVO.getBooths();
				List<ConstituencyElection> constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, constituencyName, electionScopeId, districtId);
				if(constituencyElections == null || constituencyElections.size() != 1 ){
					if(log.isDebugEnabled()){
						log.info("Exists More than One ConstituencyElections For Constituency And Election Year::"+electionYear+","+constituencyName);
						throw new Exception("No Data Found For ConstituencyElection Id::"+constituencyElections.get(0).getConstiElecId());
					}
				}
				for(BoothInfo boothRecord:boothsInConstituency){
					checkAndInsertBooth(constituencyElections.get(0), boothRecord, districtId);
				}
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
	
	private Booth checkAndInsertBooth(ConstituencyElection constituencyElection, BoothInfo boothRecord, Long districtId)throws Exception{
		Booth booth = null;
		BoothConstituencyElection boothConstituencyElection = null;
			String tehsilName = boothRecord.getMandalName();
			String partNo = boothRecord.getPartNo();
			String partName = boothRecord.getPartName();
			String location = boothRecord.getLocation();
			String villagesCovered = boothRecord.getVillagesCovered();
			String censusCode = boothRecord.getCensusCode();
			Long maleVoters = checkAndAssignLong(boothRecord.getMaleVoters());
			Long femaleVoters = checkAndAssignLong(boothRecord.getFemaleVoters());
			Long totalVoters = checkAndAssignLong(boothRecord.getTotalVoters());
			
			if(StringUtils.isBlank(partName)&&(partNo.contains("-")||partNo.contains("."))){
				String [] nameAndNo = StringUtils.split(partNo.trim(), "-.");
				partNo = nameAndNo[0];
				partName = nameAndNo[1];
			}
			
			List<Tehsil> tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
			if(tehsils!=null && tehsils.size() == 1){
				Tehsil tehsil = tehsils.get(0);
				List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByConstituencyElectionTehsilAndPartNo(constituencyElection.getConstiElecId(), tehsil.getTehsilId(), partNo);
				if(boothConstituencyElections.size() > 0){
					if(log.isDebugEnabled()){
						log.error("Exists More than One ConstituencyElections For ConstituencyElection,Tehsil and Part No::"+constituencyElection.getConstiElecId()+","+tehsil.getTehsilId()+","+partNo);
					}
				}
				else{
					booth = new Booth(partNo,partName,location,villagesCovered,tehsil,maleVoters,femaleVoters,totalVoters,null,null);
					booth = boothDAO.save(booth);
					boothConstituencyElection = new BoothConstituencyElection(booth, constituencyElection, null, null);
					boothConstituencyElectionDAO.save(boothConstituencyElection);
					checkAndInsertBoothVillageCensus(booth, censusCode);
				}
			}else{
				if(log.isDebugEnabled()){
					log.error("More than one Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtId);
				}
			}
		return booth;	
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

	public List getMandalAllElectionDetails(Long tehsilID, Long partyID, boolean allianceFlag){
		List<MandalAllElectionDetailsVO> mandalAllElectionDetails = new ArrayList<MandalAllElectionDetailsVO>();
		// 0 - election, 1 - totalvoters , 2 - validvotes, 3- rejectedvotes, 4-tenderedvotes
		List result = boothConstituencyElectionDAO.getAllElectionBoothVotersForMandal(tehsilID);
		if(result.size()==0)
			return mandalAllElectionDetails;
		for(int i=0; i<result.size(); i++){
			Object[] obj = (Object[]) result.get(i);
			MandalAllElectionDetailsVO objectVO = new MandalAllElectionDetailsVO();
			Election election = (Election)obj[0];
			objectVO.setElectionYear(election.getElectionYear());
			objectVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
			objectVO.setElectionTypeID(election.getElectionScope().getElectionType().getElectionTypeId());
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
						mandalAllElectionDetailsVO.getElectionYear(), mandalAllElectionDetailsVO.getElectionTypeID(), partyID);
				if(partyIDs==null){
					sb.append(",").append(partyID);
				}else{	
					for(SelectOptionVO obj : partyIDs){
						sb.append(",").append(obj.getId());
					}
				} 
			}else{
				sb.append(",").append(partyID);
			}
			
			List temp = boothConstituencyElectionDAO.getPartyVotesByMandal(tehsilID, sb.substring(1), 
					mandalAllElectionDetailsVO.getElectionID());
			//0-firstName, 1-middlename, 2-lastname, 3-election, 4-votesearned, 5-partyId, 6-shortName
			if(temp.size()==0){
				iterator.remove();
				continue;
			}
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
		/*if(temp.size()==0)
			return 0;*/
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
