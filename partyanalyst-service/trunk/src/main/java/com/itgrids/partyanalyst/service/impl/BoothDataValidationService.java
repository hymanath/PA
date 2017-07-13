 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
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
	  * @since 7-JULY-2017
	  */
	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothCount(InputVO inputVO) {
		List<BoothInchargeDetailsVO> resultList = new ArrayList<BoothInchargeDetailsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}

			Map<Long, BoothInchargeDetailsVO> locationBoothMap = new TreeMap<Long, BoothInchargeDetailsVO>(); ;
          
			if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				//Setting MANDAL Wise Booth Count
				Long locationId = 0l;
				 if(inputVO.getFilterValue() != null && inputVO.getFilterValue().longValue() > 0 && inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)){
		            	locationId = Long.valueOf(inputVO.getFilterValue().toString().substring(0,1));
		            	inputVO.setFilterValue(Long.valueOf(inputVO.getFilterValue().toString().substring(1)));
		          }
				 /*We are appending 1 prefix id for MANDAL and 2 for Local Election body.Because in single list
				  MANDAL And localElection Body is going to UI.So for identification purpose once user is selecting filter.
				  #locationId 0:first time call or except TEHSIL other filter selected 1: Once User select TEHSIL filter type  */
				 if(locationId == 0 || locationId == 1){
					    List<Object[]> notStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
						List<Object[]> startedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
						List<Object[]> completedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
						getLocationWiseBoothDtls(notStartedBoothObjLst,locationBoothMap,IConstants.TEHSIL,"NotStarted");
						getLocationWiseBoothDtls(startedBoothObjLst,locationBoothMap,IConstants.TEHSIL,"Started");
						getLocationWiseBoothDtls(completedBoothObjLst,locationBoothMap,IConstants.TEHSIL,"Completed");
						 
				 }
				
				//Setting LocationElectionBody Wise Booth Count
				 if(locationId == 0 || locationId == 2){
					inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
					List<Object[]> lclElctnBdyNotStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
					List<Object[]> lclElctnBdyStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
					List<Object[]> lclElctnBdyCompletedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
				
					getLocationWiseBoothDtls(lclElctnBdyNotStartedBoothObjLst,locationBoothMap,IConstants.LOCALELECTIONBODY, "NotStarted");
					getLocationWiseBoothDtls(lclElctnBdyStartedBoothObjLst,locationBoothMap,IConstants.LOCALELECTIONBODY, "Started");
					getLocationWiseBoothDtls(lclElctnBdyCompletedBoothObjLst,locationBoothMap,IConstants.LOCALELECTIONBODY, "Completed");
				 }
			}else{
				
				List<Object[]> notStartedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "NotStarted");
				List<Object[]> startedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
				List<Object[]> completedBoothObjLst = boothInchargeDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
				
				getLocationWiseBoothDtls(notStartedBoothObjLst,locationBoothMap,"NotStarted","OtherLocation");
				getLocationWiseBoothDtls(startedBoothObjLst,locationBoothMap,"Started","OtherLocation");
				getLocationWiseBoothDtls(completedBoothObjLst,locationBoothMap,"Completed","OtherLocation");
			}
			if (locationBoothMap != null && locationBoothMap.size() > 0) {
				resultList.addAll(locationBoothMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public void getLocationWiseBoothDtls(List<Object[]> objList,Map<Long,BoothInchargeDetailsVO> locationBoothMap,String type,String resultType){
		try{
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					BoothInchargeDetailsVO locationVO = locationBoothMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (locationVO == null ){
						locationVO = new BoothInchargeDetailsVO();
						if(type.equalsIgnoreCase(IConstants.TEHSIL)) {
							locationVO.setLocationIdStr("1"+commonMethodsUtilService.getStringValueForObject(param[0]));
						} else if (type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)) {
							locationVO.setLocationIdStr("2"+commonMethodsUtilService.getStringValueForObject(param[0]));
						} else {
							locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
						}
						locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.setBoothAddressVO(getBoothAddress(param));
						locationBoothMap.put(locationVO.getLocationId(), locationVO);
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
	  * @since 7-JULY-2017
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
					 inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
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
}
