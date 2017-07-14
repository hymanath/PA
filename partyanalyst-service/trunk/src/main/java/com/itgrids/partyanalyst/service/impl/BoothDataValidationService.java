 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.booth.VoterDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.VoterDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothDataValidationService implements IBoothDataValidationService{

	private ITehsilDAO tehsilDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	private IPublicationDateDAO publicationDateDAO;
	private IBoothDAO boothDAO;
	private IBoothInchargeDAO boothInchargeDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IBoothInchargeRoleConditionMappingDAO boothInchargeRoleConditionMappingDAO;
	
	
	
	public IBoothInchargeRoleConditionMappingDAO getBoothInchargeRoleConditionMappingDAO() {
		return boothInchargeRoleConditionMappingDAO;
	}

	public void setBoothInchargeRoleConditionMappingDAO(
			IBoothInchargeRoleConditionMappingDAO boothInchargeRoleConditionMappingDAO) {
		this.boothInchargeRoleConditionMappingDAO = boothInchargeRoleConditionMappingDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public BoothDataValidationService(){
		
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public void setBoothInchargeDAO(IBoothInchargeDAO boothInchargeDAO) {
		this.boothInchargeDAO = boothInchargeDAO;
	}
    public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(File filePath,
			String electionYear, Long stateId, Long electionTypeId , String publicatonDateId) {
		ResultStatus resultStatus = new ResultStatus();
		List<String> corrections = new ArrayList<String>();
		List<Exception> exceptions = new ArrayList<Exception>();
		UploadDataErrorMessageVO uploadDataErrorMessageVO = new UploadDataErrorMessageVO(); 
		try{
			VoterDataExcelReader excelReader = new VoterDataExcelReader();
			List<VoterDataUploadVO> votersInfo = excelReader.readExcel(filePath);
			String constituencyName = "";
			String mandalName = "";
			String partNo = "";
			Long districtId = null;
			List<Tehsil> tehsils = null;
			List<BoothConstituencyElection> boothConstituencyElections = null;
			BoothConstituencyElection boothConstituencyElection = null;
			Booth booth = null;
			Tehsil tehsil = null;
			
			for(VoterDataUploadVO voterDataUploadVO : votersInfo){			
				constituencyName = voterDataUploadVO.getConstituencyName();
				districtId = voterDataUploadVO.getDistrictId();
				partNo = voterDataUploadVO.getPartNo();
				
				corrections.add("#For The Constituency:"+constituencyName+" and Part NO:"+partNo);
				
				if(!voterDataUploadVO.getMandalName().equalsIgnoreCase(mandalName)){
					mandalName = voterDataUploadVO.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(mandalName, districtId);
				}				
				if(tehsils.size() != 1){					
					corrections.add("More than One Or No Tehsil Exists with Mandal:"+mandalName+" District Id:"+districtId);
					continue;
				}				
				tehsil = tehsils.get(0);
				
			 Long publicationDateId = new Long(publicatonDateId);
			 
			 
				if (electionYear != null && !electionYear.equalsIgnoreCase("0")
						&& electionTypeId != null && electionTypeId.longValue() != 0 
						&& publicationDateId != null && publicationDateId.longValue() != 0) {
				 
				 List<Booth> booths = boothDAO.findByPublicationDateConstituencyAndPartNo(stateId,
							districtId, constituencyName,publicationDateId, partNo);
				 
				 if(booths != null && booths.size() < 1){
					 
					 booth = null;						
					 corrections.add(" No Publication Exists with publishing date:"+publicationDateId);
						
						//continue;
					}else						
						booth = booths.get(0);
				 
				 
				 boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
					if(boothConstituencyElections.size() != 1){						
						corrections.add("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						
						//continue;
					}
					
					if(boothConstituencyElections.size() == 1 )
					  boothConstituencyElection = boothConstituencyElections.get(0);
					else						
						boothConstituencyElection = null;
					
					List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
					if(boothConstituencyElectionVoters.size() > 0){
						boothConstituencyElection = null;
						
						corrections.add("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					
						//continue;
					}
					
					insertVoterAndBoothPublicationVoter(tehsil,
							booth,  voterDataUploadVO.getVoterVOs(), corrections);
				 
				 
			 }				
			 else if(publicationDateId!= null && publicationDateId.longValue() != 0){
				
					List<Booth> booths = boothDAO.findByPublicationDateConstituencyAndPartNo(stateId,
							districtId, constituencyName,publicationDateId, partNo);	
					
					
					if(booths != null && booths.size() < 1){						
						
						corrections.add(" No Publication Exists with publishing date:"+publicationDateId);
						
						continue;
					}
					
					insertVoterAndBoothPublicationVoter(tehsil,
							booth,  voterDataUploadVO.getVoterVOs(), corrections);
					
					
				 }				
              else if (electionYear != null
						&& !electionYear.equalsIgnoreCase("0")
						&& electionTypeId != null
						&& electionTypeId.longValue() != 0) {
					
					boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
					if(boothConstituencyElections.size() != 1){
						
						corrections.add("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
					
						continue;
					}
					boothConstituencyElection = boothConstituencyElections.get(0);
					List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
					if(boothConstituencyElectionVoters.size() > 0){
						
						corrections.add("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						
						continue;
					}
					
					insertVoterAndBoothConstituencyElectionVoter(tehsil,
							boothConstituencyElection,
							voterDataUploadVO.getVoterVOs(), corrections);						
					
				}
			}
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultStatus.setResultPartial(true);
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		uploadDataErrorMessageVO.setCorrections(corrections);
		uploadDataErrorMessageVO.setExceptions(exceptions);
		return uploadDataErrorMessageVO;
	}
	
	

	/*public UploadDataErrorMessageVO readVoterExcelDataAndValidate(
			File filePath, String electionYear, Long stateId,
			Long electionTypeId , String publicatonDateId) throws CsvException {
		List<String> corrections = new ArrayList<String>();
		List<Exception> exceptions = new ArrayList<Exception>();
		VoterDataExcelReader voterDataExcelReader = new VoterDataExcelReader();
		List<VoterDataUploadVO> votersInfo = voterDataExcelReader.readExcel(filePath);
		String constituencyName = "";
		String mandalName = "";
		String partNo = "";
		Long districtId = null;
		List<Tehsil> tehsils = null;
		
		Long publicationDateId = new Long(publicatonDateId);
		
		List<BoothConstituencyElection> boothConstituencyElections = null;
		BoothConstituencyElection boothConstituencyElection = null;
		Tehsil tehsil = null;
		UploadDataErrorMessageVO uploadDataErrorMessageVO = new UploadDataErrorMessageVO(); 
		for(VoterDataUploadVO voterDataUploadVO : votersInfo){	
			try{
				constituencyName = voterDataUploadVO.getConstituencyName();
				districtId = voterDataUploadVO.getDistrictId();
				partNo = voterDataUploadVO.getPartNo();
				corrections.add("#For The Constituency:"+constituencyName+" and Part NO:"+partNo);
				if(!voterDataUploadVO.getMandalName().equalsIgnoreCase(mandalName)){
					mandalName = voterDataUploadVO.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(mandalName, districtId);
				}				
				if(tehsils.size() != 1){
					corrections.add("More than One Or No Tehsil Exists with Mandal:"+mandalName+" District Id:"+districtId);
					continue;
				}
				
				tehsil = tehsils.get(0);
				if(publicationDateId!= null){
					
					List<Booth> booths = boothDAO.findByPublicationDateConstituencyAndPartNo(stateId,
							districtId, constituencyName,publicationDateId, partNo);	
					
					
					if(booths != null && booths.size() < 1){
						
							corrections.add(" No Publication Exists with publishing date:"+publicationDateId);						
						    continue;
					}
					
					insertVoterAndBoothPublicationVoter(tehsil ,booths.get(0), voterDataUploadVO.getVoterVOs(),corrections);
				
				}
				
				
				if(electionTypeId != null && electionYear != null){
				
					boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
					if(boothConstituencyElections.size() != 1){
						corrections.add("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						continue;
					}
					boothConstituencyElection = boothConstituencyElections.get(0);
					List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
					if(boothConstituencyElectionVoters.size() > 0){
						corrections.add("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						continue;
					}
						
					insertVoterAndBoothConstituencyElectionVoter(tehsil, boothConstituencyElection, voterDataUploadVO.getVoterVOs(), corrections);
					
				}
			}catch(Exception ex){
				exceptions.add(ex);
				ex.printStackTrace();
			}						
		}		
		uploadDataErrorMessageVO.setCorrections(corrections);
		uploadDataErrorMessageVO.setExceptions(exceptions);
		return uploadDataErrorMessageVO;
	}
	*/
	
	private void insertVoterAndBoothPublicationVoter(Tehsil tehsil,
			Booth booth, List<VoterVO> voterVOs, List<String> corrections)
			throws Exception {
		
		String townshipName = "";
		String hamletName = "";
		List<Hamlet> hamlets = null;
		
		
		for(VoterVO voterVO:voterVOs){

			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				System.out.println("townshipName ::"+townshipName+" hamletName ::"+hamletName);
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				corrections.add("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				continue;
			}			
		}
		
	}

	private void insertVoterAndBoothConstituencyElectionVoter(Tehsil tehsil,BoothConstituencyElection boothConstituencyElection,
			List<VoterVO> voterVOs, List<String> corrections)throws Exception {
		String townshipName = "";
		String hamletName = "";
		List<Hamlet> hamlets = null;
		for(VoterVO voterVO:voterVOs){
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				System.out.println("townshipName ::"+townshipName+" hamletName ::"+hamletName);
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				corrections.add("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				continue;
			}
		}
		
	}
	/**
	  * @param  InputVO inputVO 
	  * @return List<BoothInchargeDetailsVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to booth count location wise 
	  * @since 12-JULY-2017
	  */
	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothCount(InputVO inputVO) {
		List<BoothInchargeDetailsVO> resultList = new ArrayList<BoothInchargeDetailsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}
			 List<Object[]> rangeObjList = new ArrayList<Object[]>(){{add(new Object[]{1l,"0-10"});add(new Object[]{2l,"11-20"});add(new Object[]{3l,"21-30"});add(new Object[]{4l,"31-40"});
	          add(new Object[]{5l,"41-50"});add(new Object[]{6l,"51-60"});add(new Object[]{7l,"61-70"});add(new Object[]{8l,"71-80"});add(new Object[]{9l,"81-90"});add(new Object[]{10l,"91-100"});add(new Object[]{11l,"100 Above"});}};
	         
           Map<String, BoothInchargeDetailsVO> locationBoothMap = new TreeMap<String, BoothInchargeDetailsVO>(); ;
          
			if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				//Setting MANDAL Wise Booth Count
				Long locationId = 0l;
				 if(inputVO.getFilterValue() != null && inputVO.getFilterValue().longValue() > 0 && inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)){
		            	locationId = Long.valueOf(inputVO.getFilterValue().toString().substring(0,1));
		            	inputVO.setFilterValue(Long.valueOf(inputVO.getFilterValue().toString().substring(1)));
		          }
				 /*We are appending 1 prefix id for MANDAL and 2 for Local Election body.Because in single list
				  MANDAL And localElection Body is going to UI.So for identification purpose once user is selecting filter.
				  #locationId 0:first time call or except TEHSIL other filter selected 1: Once User select TEHSIL filter type.*/
				 if(locationId == 0 || locationId == 1){
					    List<Object[]> notStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
						List<Object[]> startedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
						List<Object[]> completedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
						getLocationWiseBoothDtls(notStartedBoothObjLst,rangeObjList,locationBoothMap,"NotStarted",IConstants.TEHSIL);
						getLocationWiseBoothDtls(startedBoothObjLst,rangeObjList,locationBoothMap,"Started",IConstants.TEHSIL);
						getLocationWiseBoothDtls(completedBoothObjLst,rangeObjList,locationBoothMap,"Completed",IConstants.TEHSIL);
						 
				 }
				
				//Setting LocationElectionBody Wise Booth Count
				 if(locationId == 0 || locationId == 2){
					inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
					List<Object[]> lclElctnBdyNotStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
					List<Object[]> lclElctnBdyStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
					List<Object[]> lclElctnBdyCompletedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
				
					getLocationWiseBoothDtls(lclElctnBdyNotStartedBoothObjLst,rangeObjList,locationBoothMap,"NotStarted",IConstants.LOCALELECTIONBODY);
					getLocationWiseBoothDtls(lclElctnBdyStartedBoothObjLst,rangeObjList,locationBoothMap,"Started",IConstants.LOCALELECTIONBODY);
					getLocationWiseBoothDtls(lclElctnBdyCompletedBoothObjLst,rangeObjList,locationBoothMap,"Completed",IConstants.LOCALELECTIONBODY);
				 }
			}else{
				
				List<Object[]> notStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
				List<Object[]> startedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
				List<Object[]> completedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
				
				getLocationWiseBoothDtls(notStartedBoothObjLst,rangeObjList,locationBoothMap,"NotStarted","OtherLocation");
				getLocationWiseBoothDtls(startedBoothObjLst,rangeObjList,locationBoothMap,"Started","OtherLocation");
				getLocationWiseBoothDtls(completedBoothObjLst,rangeObjList,locationBoothMap,"Completed","OtherLocation");
			}
			if (locationBoothMap != null && locationBoothMap.size() > 0) {
				resultList.addAll(locationBoothMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public void getLocationWiseBoothDtls(List<Object[]> objList,List<Object[]> rangeObjList,Map<String,BoothInchargeDetailsVO> locationBoothMap,String resultType,String type){
		try{
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					if(param[0] == null){
						continue;
					}
						
					BoothInchargeDetailsVO locationVO = locationBoothMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
					if (locationVO == null ){
						locationVO = new BoothInchargeDetailsVO();
						if(type.equalsIgnoreCase(IConstants.TEHSIL)) {
							locationVO.setLocationIdStr("1"+commonMethodsUtilService.getStringValueForObject(param[0]));
						} else if (type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)) {
							locationVO.setLocationIdStr("2"+commonMethodsUtilService.getStringValueForObject(param[0]));
						} else {
							locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
						}
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.setBoothAddressVO(getBoothAddress(param));
						locationVO.setSubList(getRangeList(rangeObjList));
						locationBoothMap.put(locationVO.getLocationIdStr(), locationVO);
					}
					if(resultType.equalsIgnoreCase("NotStarted")){
						locationVO.setNotStartedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setTotalBoothCount(locationVO.getTotalBoothCount()+locationVO.getNotStartedBoothCount());
					}else if(resultType.equalsIgnoreCase("Started")){
						locationVO.setStartedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setTotalBoothCount(locationVO.getTotalBoothCount()+locationVO.getStartedBoothCount());
					}else if(resultType.equalsIgnoreCase("Completed")){
						locationVO.setCompletedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setTotalBoothCount(locationVO.getTotalBoothCount()+locationVO.getCompletedBoothCount());
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception occured at getLocationWiseBoothDtls() in BoothDataValidationService class",e);
		}
	}
    public List<BoothInchargeDetailsVO> getRangeList(List<Object[]> rangeObjLst){
    	List<BoothInchargeDetailsVO> rangeList = new ArrayList<BoothInchargeDetailsVO>(0);
    	try {
    		for (Object[] param : rangeObjLst) {
				BoothInchargeDetailsVO rangeVO = new BoothInchargeDetailsVO();
				 rangeVO.setRoleId(commonMethodsUtilService.getLongValueForObject(param[0]));//rangeId
				 rangeVO.setRoleName(commonMethodsUtilService.getStringValueForObject(param[1]));//range
				 rangeList.add(rangeVO);
			}
    	} catch(Exception e) {
    		Log.error("Exception occured at getRangeList() in BoothDataValidationService class",e);
    	}
    	return rangeList;
    }
	public BoothAddressVO getBoothAddress(Object[] param) {
		BoothAddressVO addressVO = new BoothAddressVO();
		try {
			if (param != null && param.length > 0) {
				addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[3]));
				addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[4]));
				addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[5]));
				addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[6]));
				addressVO.setParliamentConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
				addressVO.setParliamentConstituency(commonMethodsUtilService.getStringValueForObject(param[8]));
				addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[9]));
				addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[10]));
				if (param[11] == null) {
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[15]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[16])+ " "+ commonMethodsUtilService.getStringValueForObject(param[18]));
				} else {
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[11]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[12]));
				}
			}
		} catch (Exception e) {
			Log.error("Exception occured at getBoothAddress() in BoothDataValidationService class",e);
		}
		return addressVO;
	}
	/**
	  * @param  InputVO inputVO 
	  * @return List<BoothInchargeDetailsVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get location based on selection. 
	  * @since 12-JULY-2017
	  */
	public List<BoothInchargeDetailsVO> getLocationBasedOnSelection(InputVO inputVO) {
		List<BoothInchargeDetailsVO> resultList = new ArrayList<BoothInchargeDetailsVO>();
		try{
			List<Object[]> locationObjLst = boothInchargeDAO.getLocationBasedOnSelection(inputVO);
			resultList = getLocationList(locationObjLst,"OtherLocation");
			if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				resultList = getLocationList(locationObjLst,IConstants.TEHSIL);
				inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
				List<Object[]> localElectionBodyObjLst = boothInchargeDAO.getLocationBasedOnSelection(inputVO);
				List<BoothInchargeDetailsVO> locationElectionBodyList = getLocationList(localElectionBodyObjLst,IConstants.LOCALELECTIONBODY);
				resultList.addAll(locationElectionBodyList);
			}
		}catch(Exception e){
			Log.error("Exception occured at getLocationBasedOnSelection() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public List<BoothInchargeDetailsVO> getLocationList(List<Object[]> locationObjLst,String type){
		List<BoothInchargeDetailsVO> locationList = new ArrayList<BoothInchargeDetailsVO>();
		try{
			if (locationObjLst != null && locationObjLst.size() > 0) {
				for (Object[] param:locationObjLst) {
					if(param[0] == null){
						continue;
					}
					BoothInchargeDetailsVO locationVO = new BoothInchargeDetailsVO();
					if(type.equalsIgnoreCase(IConstants.TEHSIL)) {
						locationVO.setLocationIdStr("1"+commonMethodsUtilService.getStringValueForObject(param[0]));
					} else if (type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)) {
						locationVO.setLocationIdStr("2"+commonMethodsUtilService.getStringValueForObject(param[0]));
					} else {
						locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					}
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationList.add(locationVO);
				}
			}
		} catch(Exception e) {
			Log.error("Exception occured at getLocationList() in BoothDataValidationService class",e);
		}
		return locationList;
	}
	
	public List<BoothAddressVO> getLocationLevelWiseBoothDetails(InputVO inputVO) {
		List<BoothAddressVO> resultList = new ArrayList<BoothAddressVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}
			
			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				Long locationId = 0l;
				 if(inputVO.getFilterValue() != null && inputVO.getFilterValue().longValue() > 0 ){
		            	locationId = Long.valueOf(inputVO.getFilterValue().toString().substring(0,1));
		            	inputVO.setFilterValue(Long.valueOf(inputVO.getFilterValue().toString().substring(1)));
		          }
				 if(locationId == 2){
					 inputVO.setFilterLevel(IConstants.LOCALELECTIONBODY);
				 }
			}
			      
			List<Object[]> boothDetailsObjList = boothInchargeDAO.getLocationLevelWiseBoothDetails(inputVO);
			if(boothDetailsObjList != null && !boothDetailsObjList.isEmpty()) {
			
				for(Object[] param: boothDetailsObjList){
					BoothAddressVO boothDetailsVO=new BoothAddressVO();
					boothDetailsVO.setBoothId(commonMethodsUtilService.getLongValueForObject(param[0]));
					boothDetailsVO.setBoothName(commonMethodsUtilService.getStringValueForObject(param[1]));
					boothDetailsVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[2]));
					boothDetailsVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[3]));
					boothDetailsVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[4]));
					boothDetailsVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[5]));
					boothDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[6]));
					boothDetailsVO.setParliamentConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
					boothDetailsVO.setParliamentConstituency(commonMethodsUtilService.getStringValueForObject(param[8]));
					boothDetailsVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[9]));
					boothDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[10]));
					if (param[11] == null) {
						boothDetailsVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[15]));
						boothDetailsVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[16])+ " "+ commonMethodsUtilService.getStringValueForObject(param[18]));
					} else {
						boothDetailsVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[11]));
						boothDetailsVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[12]));
					}				
					
					resultList.add(boothDetailsVO);
				}
				
			}
			
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public List<IdAndNameVO> getBoothInchargeRoles(Long boothId,List<Long> enrollmentYrIds){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try{
			Map<Long,IdAndNameVO> boothRolesMap = new HashMap<Long,IdAndNameVO>();
			List<Object[]> roles = boothInchargeRoleConditionMappingDAO.getBoothInchargeRolesWithMinMAxCount(boothId,enrollmentYrIds);
			
			if(commonMethodsUtilService.isListOrSetValid(roles)){
				for (Object[] objects : roles) {
					IdAndNameVO roleVo = new IdAndNameVO();
					roleVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					roleVo.setName(commonMethodsUtilService.getStringValueForObject(objects[4]));
					roleVo.setTsNow(commonMethodsUtilService.getLongValueForObject(objects[1]));//min mem Count
					roleVo.setTsTotal(commonMethodsUtilService.getLongValueForObject(objects[2]));//max mem Count
					
					boothRolesMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), roleVo);
				}
			}
			List<Object[]> rolesCount = null;
			if(commonMethodsUtilService.isMapValid(boothRolesMap)){
				rolesCount = boothInchargeDAO.getBoothInchargeCountByRoleIds(boothRolesMap.keySet(),enrollmentYrIds);
			}
			List<Long> savedRoles = new ArrayList<Long>();
			if(commonMethodsUtilService.isMapValid(boothRolesMap)){
				if(commonMethodsUtilService.isListOrSetValid(rolesCount)){
					for (Object[] objects : rolesCount) {
						savedRoles.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						IdAndNameVO roleVO = boothRolesMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
						if(roleVO != null){
							Long memCount = (Long)objects[1];
							if(memCount < roleVO.getTsTotal()){
								returnList.add(roleVO);
							}
						}
					}
				}else{
					returnList.addAll(boothRolesMap.values());
				}
			}
			
			//Till not Saved With roleId for BoothIncharge Table Then adding those roles to returnList 
			if(commonMethodsUtilService.isMapValid(boothRolesMap) && savedRoles != null && savedRoles.size()>0){
				for (Map.Entry<Long, IdAndNameVO> entrySet : boothRolesMap.entrySet()) {
					//IdAndNameVO vo = getMatchedVOById(returnList,entrySet.getKey());
					if(!savedRoles.contains(entrySet.getKey())){
						returnList.add(entrySet.getValue());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception occured at getBoothInchargeRoles() in BoothDataValidationService class",e);
		}
		
		return returnList;
	}
	
	public IdAndNameVO getMatchedVOById(List<IdAndNameVO> list,Long id)
	{
		IdAndNameVO returnVO = null;
		try {
			
			if(list != null && list.size()>0)
			{
				for (IdAndNameVO vo : list)
				{
					if(vo.getId().longValue() == id.longValue())
					{
						return vo;
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised in getMatchedVOById", e);
		}
		return returnVO;
	}
	/**
	  * @param  Long boothId 
	  * @param Long boothInchargeEnrollmentId
	  * @return BoothInchargeDetailsVO
	  * @author Santosh 
	  * @Description :This Service Method is used update booth status.if required condition satisfied. 
	  * @since 13-JULY-2017
	  */
	public BoothInchargeDetailsVO validateBoothToMakeConfirm(Long boothId,Long boothInchargeEnrollmentId){
		BoothInchargeDetailsVO resultVO = new BoothInchargeDetailsVO();
		try {
			List<Object[]> requiredBoothMemberObjLst = boothInchargeRoleConditionMappingDAO. getBoothMinMaxRequiredMemberRoleWise(boothId, boothInchargeEnrollmentId);
			List<Object[]> addedBoothMemberObjLst = boothInchargeDAO.getAddedMemberInBoothRoleWise(boothId, boothInchargeEnrollmentId);
			Map<Long,BoothInchargeDetailsVO> boothDtlsMap = getBoothRequiredData(requiredBoothMemberObjLst);
			
			//Setting added member role wise 
			if(addedBoothMemberObjLst != null && !addedBoothMemberObjLst.isEmpty() ){
				for (Object[] param : addedBoothMemberObjLst) {
					if(boothDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
						boothDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setAddedMemberCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}
			//checking Is that added member is equal to booth required minMember or not.
			boolean flag = Boolean.TRUE;
			if(!boothDtlsMap.isEmpty()){
				for (Entry<Long, BoothInchargeDetailsVO> roleEntry : boothDtlsMap.entrySet()) {
					if(roleEntry.getValue().getAddedMemberCount() < roleEntry.getValue().getMinMemberCount()){
						flag = Boolean.FALSE;
					}
				}
			}
			//Change Booth Status
			resultVO.setStatus("Faliure");
			if(flag){
				int rowUpdateCount = updateBoothStatus(boothId,boothInchargeEnrollmentId);	
				if(rowUpdateCount != 0){
					resultVO.setStatus("Success");
				}
			}
			resultVO.setSubList(new ArrayList<BoothInchargeDetailsVO>(boothDtlsMap.values()));
		} catch (Exception e) {
			Log.error("Exception raised at validateBoothToMakeConfirm in BoothDataValidationService class", e);

		}
		return resultVO;	
	}
	public Map<Long,BoothInchargeDetailsVO> getBoothRequiredData(List<Object[]> objList){
		Map<Long,BoothInchargeDetailsVO> boothDtlsMap = new HashMap<Long, BoothInchargeDetailsVO>(0);
		try {
			if (objList != null && !objList.isEmpty()) {
				for (Object[] param : objList) {
					  BoothInchargeDetailsVO boothRoleVO = new BoothInchargeDetailsVO();
						  boothRoleVO.setRoleId(commonMethodsUtilService.getLongValueForObject(param[0]));
						  boothRoleVO.setRoleName(commonMethodsUtilService.getStringValueForObject(param[1]));
						  boothRoleVO.setMinMemberCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						  boothRoleVO.setMaxMemberCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						  boothDtlsMap.put(boothRoleVO.getRoleId(), boothRoleVO);
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getBoothRequiredData in BoothDataValidationService class", e);
		}
		return boothDtlsMap;
	}
	public int updateBoothStatus(Long boothId,Long boothInchargeEnrollmentId){
		try {
			DateUtilService date = new DateUtilService();
			int updateRowCnt = boothInchargeRoleConditionMappingDAO.updateBoothStatus(boothId, boothInchargeEnrollmentId, date.getCurrentDateAndTime());
		    return updateRowCnt;
		} catch(Exception e) {
			Log.error("Exception raised at updateBoothStatus in BoothDataValidationService class", e);
			return 0;
		}
	}

}
