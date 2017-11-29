 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeSerialNoRangeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
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
import com.itgrids.partyanalyst.model.BoothIncharge;
import com.itgrids.partyanalyst.model.BoothInchargeCommittee;
import com.itgrids.partyanalyst.model.BoothInchargeSerialNoRange;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

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
	private IBoothInchargeSerialNoRangeDAO boothInchargeSerialNoRangeDAO;
	private IActivityMemberDAO activityMemberDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	private TransactionTemplate transactionTemplate = null;
	private ICadreCommitteeService cadreCommitteeService;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
	
	
	public ImageAndStringConverter getImageAndStringConverter() {
		return imageAndStringConverter;
	}

	public void setImageAndStringConverter(
			ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}

	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}

	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public IBoothInchargeSerialNoRangeDAO getBoothInchargeSerialNoRangeDAO() {
		return boothInchargeSerialNoRangeDAO;
	}

	public void setBoothInchargeSerialNoRangeDAO(
			IBoothInchargeSerialNoRangeDAO boothInchargeSerialNoRangeDAO) {
		this.boothInchargeSerialNoRangeDAO = boothInchargeSerialNoRangeDAO;
	}

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
    public void setActivityMemberDAO(IActivityMemberDAO activityMemberDAO) {
		this.activityMemberDAO = activityMemberDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}
    public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setBoothInchargeCommitteeDAO(
			IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
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
		   
		   List<Object[]> rangeObjList = boothInchargeSerialNoRangeDAO.getAllInchargeSerialNoRange();
		   
		   Map<String, BoothInchargeDetailsVO> locationBoothMap = new TreeMap<String, BoothInchargeDetailsVO>(); ;
		   Map<Long,Map<Long,Long>> locationSerialRangeWiseVoterMap = null;
		   
		     Long locationId = 0l;
		     //we are separating MANDAL and localElectionBody.
		     if(inputVO.getFilterLevel() != null && inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)){
					locationId = Long.valueOf(inputVO.getFilterValueList().get(0).toString().substring(0,1));
					String filterValues = inputVO.getFilterValueList().get(0).toString().substring(1);//in the case of TEHSIL taking id except first because we are appending while sending
					inputVO.getFilterValueList().clear();
		            inputVO.getFilterValueList().add(Long.valueOf(filterValues));
		            if(locationId==2l){////Local Election Body
		            	inputVO.setFilterLevel(IConstants.LOCALELECTIONBODY);
		            }
			    }
			if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				//Setting MANDAL Wise Booth Count
				 /*We are appending 1 prefix id for MANDAL and 2 for Local Election body.Because in single list
				  MANDAL And localElection Body is going to UI.So for identification purpose once user is selecting filter.
				  #locationId 0:first time call or except TEHSIL other filter selected 1: Once User select TEHSIL filter type.*/
				 if(locationId == 0 || locationId == 1){
					    
					    List<Object[]> notStartedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "TotalBooth");
						List<Object[]> startedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
						List<Object[]> completedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
						List<Object[]> voterCountObjLst     = boothInchargeDAO.getLocationSerialNoRangeWiseVoterCount(inputVO);
						
						locationSerialRangeWiseVoterMap = getVoterCountRangeWise(voterCountObjLst);
						getLocationWiseBoothDtls(notStartedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"TotalBooth",IConstants.TEHSIL);
						getLocationWiseBoothDtls(startedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"Started",IConstants.TEHSIL);
						getLocationWiseBoothDtls(completedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"Completed",IConstants.TEHSIL);
						 
				 }
				 Map<Long,Map<String,BoothInchargeDetailsVO>>  locationElectionBodyBoothDtlsMap = new HashMap<Long, Map<String,BoothInchargeDetailsVO>>(0);
				 Map<Long,Map<Long,Map<Long,Long>>> localEleAgeRangWiseCadreMap = null;
				//Setting LocationElectionBody Wise Booth Count
				 if(locationId == 0 || locationId == 2){
					inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
					List<Object[]> lclElctnBdyNotStartedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "TotalBooth");
					List<Object[]> lclElctnBdyStartedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
					List<Object[]> lclElctnBdyCompletedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
					List<Object[]> voterCountObjLst     = boothInchargeDAO.getLocationSerialNoRangeWiseVoterCount(inputVO);
					
					localEleAgeRangWiseCadreMap = getLocalElectionBodyVoterCountRangeWise(voterCountObjLst);
					getLocalEleBodyBoothDtls(lclElctnBdyNotStartedBoothObjLst,rangeObjList,locationElectionBodyBoothDtlsMap,localEleAgeRangWiseCadreMap,"TotalBooth",IConstants.LOCALELECTIONBODY);
					getLocalEleBodyBoothDtls(lclElctnBdyStartedBoothObjLst,rangeObjList,locationElectionBodyBoothDtlsMap,localEleAgeRangWiseCadreMap,"Started",IConstants.LOCALELECTIONBODY);
					getLocalEleBodyBoothDtls(lclElctnBdyCompletedBoothObjLst,rangeObjList,locationElectionBodyBoothDtlsMap,localEleAgeRangWiseCadreMap,"Completed",IConstants.LOCALELECTIONBODY);
					
					if(locationElectionBodyBoothDtlsMap .size() > 0 ){
					   for (Entry<Long, Map<String, BoothInchargeDetailsVO>> constituencyEntry : locationElectionBodyBoothDtlsMap.entrySet()) {//adding local election body into mandal
						    if(constituencyEntry.getValue() != null && constituencyEntry.getValue().size() > 0){
						    	 for(Entry<String, BoothInchargeDetailsVO> locaEleEntry:constituencyEntry.getValue().entrySet()){
						    		 resultList.add(locaEleEntry.getValue());
						    	 }
						    }
					   }	
					}
				 }
			}else{ //for other location 
				
				List<Object[]> notStartedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "TotalBooth");
				List<Object[]> startedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Started");
				List<Object[]> completedBoothObjLst = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothCount(inputVO, "Completed");
				List<Object[]> voterCountObjLst     = boothInchargeDAO.getLocationSerialNoRangeWiseVoterCount(inputVO);
				
				locationSerialRangeWiseVoterMap = getVoterCountRangeWise(voterCountObjLst);
				getLocationWiseBoothDtls(notStartedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"TotalBooth","OtherLocation");
				getLocationWiseBoothDtls(startedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"Started","OtherLocation");
				getLocationWiseBoothDtls(completedBoothObjLst,rangeObjList,locationBoothMap,locationSerialRangeWiseVoterMap,"Completed","OtherLocation");
			}
			if (locationBoothMap != null && locationBoothMap.size() > 0) {
				resultList.addAll(locationBoothMap.values());
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothCount() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public void getLocationWiseBoothDtls(List<Object[]> objList,List<Object[]> rangeObjList,Map<String,BoothInchargeDetailsVO> locationBoothMap,Map<Long,Map<Long,Long>> locationSerialRangeWiseVoterMap,String resultType,String type){
		try{
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					if(param[0] == null){
						continue;
					}
					String locationIdStr ="";
					String locationName = "";
					if(type.equalsIgnoreCase(IConstants.TEHSIL)) {
						locationIdStr="1"+commonMethodsUtilService.getStringValueForObject(param[0]);
						locationName = commonMethodsUtilService.getStringValueForObject(param[1]);
					} else if (type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)) {
						locationIdStr ="2"+commonMethodsUtilService.getStringValueForObject(param[0]);
						locationName = commonMethodsUtilService.getStringValueForObject(param[1])+" Munci/Corp/Greater City";
					} else {
						locationIdStr = commonMethodsUtilService.getStringValueForObject(param[0]);
						locationName = commonMethodsUtilService.getStringValueForObject(param[1]);
					}
						
					BoothInchargeDetailsVO locationVO = locationBoothMap.get(locationIdStr.trim());
					if (locationVO == null ){
						locationVO = new BoothInchargeDetailsVO();
						locationVO.setLocationIdStr(locationIdStr);
						locationVO.setLocationName(locationName);
						locationVO.setBoothAddressVO(getBoothAddress(param));
						//taking location wise serial range wise voter map and setting based on location. 
						Map<Long,Long> rangeWiseVoterCountMap = locationSerialRangeWiseVoterMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setSubList(getRangeList(rangeObjList,rangeWiseVoterCountMap));
						locationBoothMap.put(locationVO.getLocationIdStr().trim(), locationVO);
					}
					
					if(resultType.equalsIgnoreCase("TotalBooth")){
						locationVO.setTotalBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(resultType.equalsIgnoreCase("Started")){
						locationVO.setStartedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(resultType.equalsIgnoreCase("Completed")){
						locationVO.setCompletedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception occured at getLocationWiseBoothDtls() in BoothDataValidationService class",e);
		}
	}
	public void getLocalEleBodyBoothDtls(List<Object[]> objList,List<Object[]> rangeObjList,Map<Long,Map<String,BoothInchargeDetailsVO>>  locationElectionBodyBoothDtlsMap,Map<Long,Map<Long,Map<Long,Long>>> localEleAgeRangWiseCadreMap,String resultType,String type){
		try{
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					if(param[0] == null){
						continue;
					}
					Long constituencyId = commonMethodsUtilService.getLongValueForObject(param[9]);
					String locationIdStr ="2"+commonMethodsUtilService.getStringValueForObject(param[0]);
					String locationName = commonMethodsUtilService.getStringValueForObject(param[1])+" Munci/Corp/Greater City";
						
					Map<String,BoothInchargeDetailsVO> localEleMap = locationElectionBodyBoothDtlsMap.get(constituencyId);
					if (localEleMap == null ){
						localEleMap = new HashMap<String, BoothInchargeDetailsVO>(0);
						locationElectionBodyBoothDtlsMap.put(constituencyId, localEleMap);
					}
					BoothInchargeDetailsVO localEleBodyVO = localEleMap.get(locationIdStr.trim());
					 if(localEleBodyVO == null){
						 localEleBodyVO = new BoothInchargeDetailsVO();
						 localEleBodyVO.setLocationIdStr(locationIdStr);
						 localEleBodyVO.setLocationName(locationName);
						 localEleBodyVO.setBoothAddressVO(getBoothAddress(param));
						 localEleBodyVO.setSubList(getRangeList(rangeObjList,null));
						//taking location wise serial range wise voter map and setting based on location. 
						 Map<Long,Map<Long,Long>> localEleBodyMap = localEleAgeRangWiseCadreMap.get(commonMethodsUtilService.getLongValueForObject(constituencyId));
						 if(localEleBodyMap != null ){
							 Map<Long,Long> rangeWiseVoterCountMap = localEleBodyMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							  if(localEleBodyVO.getSubList() != null && localEleBodyVO.getSubList().size() > 0){
								   for(BoothInchargeDetailsVO rangeVO:localEleBodyVO.getSubList()){
									   if(rangeWiseVoterCountMap != null ){
										   rangeVO.setCount(rangeWiseVoterCountMap.get(rangeVO.getRoleId()));
									   }
								   }
							  }
						 }
						 localEleMap.put(locationIdStr.trim(), localEleBodyVO);
					 }
					if(resultType.equalsIgnoreCase("TotalBooth")){
						localEleBodyVO.setTotalBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(resultType.equalsIgnoreCase("Started")){
						localEleBodyVO.setStartedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(resultType.equalsIgnoreCase("Completed")){
						localEleBodyVO.setCompletedBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}
		}catch(Exception e){
			Log.error("Exception occured at getLocationWiseBoothDtls() in BoothDataValidationService class",e);
		}
	}
    public List<BoothInchargeDetailsVO> getRangeList(List<Object[]> rangeObjLst,Map<Long,Long> rangeWiseVoterCountMap){
    	List<BoothInchargeDetailsVO> rangeList = new ArrayList<BoothInchargeDetailsVO>(0);
    	try {
    		for (Object[] param : rangeObjLst) {
				BoothInchargeDetailsVO rangeVO = new BoothInchargeDetailsVO();
				 rangeVO.setRoleId(commonMethodsUtilService.getLongValueForObject(param[0]));//rangeId
				 rangeVO.setRoleName(commonMethodsUtilService.getStringValueForObject(param[1]));//range
				 if(commonMethodsUtilService.isMapValid(rangeWiseVoterCountMap)){
					 rangeVO.setCount(rangeWiseVoterCountMap.get(rangeVO.getRoleId()));//setting serial range wise voter count 
				 }
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
				if (param[15] == null) {//mucipality is null
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[11]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[12]));					
				} else {
					addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[15]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[16])+ " "+ commonMethodsUtilService.getStringValueForObject(param[18]));
				}
				addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[13]));
				addressVO.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[14]));
			}
		} catch (Exception e) {
			Log.error("Exception occured at getBoothAddress() in BoothDataValidationService class",e);
		}
		return addressVO;
	}
	public Map<Long,Map<Long,Long>> getVoterCountRangeWise(List<Object[]> objList) {
		Map<Long,Map<Long,Long>> locationSerialRangeWiseVoterMap = new HashMap<Long, Map<Long,Long>>(0);
		try {
			if(commonMethodsUtilService.isListOrSetValid(objList)) {
				for (Object[] param:objList) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					if(!locationSerialRangeWiseVoterMap.containsKey(locationId)){
						Map<Long,Long> rangeMap = new HashMap<Long, Long>(0);
						locationSerialRangeWiseVoterMap.put(locationId, rangeMap);
					}
					Map<Long,Long> rangeMap = locationSerialRangeWiseVoterMap.get(locationId);
					if(rangeMap != null){
						rangeMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}
		} catch(Exception e) {
			Log.error("Exception occured at getVoterCountRangeWise() in BoothDataValidationService class",e);
		}
		return locationSerialRangeWiseVoterMap;
	}
	public Map<Long,Map<Long,Map<Long,Long>>> getLocalElectionBodyVoterCountRangeWise(List<Object[]> objList) {
		Map<Long,Map<Long,Map<Long,Long>>> localEleAgeRangWiseCadreMap = new HashMap<Long, Map<Long,Map<Long,Long>>>(0);
		try {
			if(commonMethodsUtilService.isListOrSetValid(objList)) {
				for (Object[] param:objList) {
					Long constituencyId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long localEleBodyId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Map<Long,Map<Long,Long>> localEleMap = localEleAgeRangWiseCadreMap.get(constituencyId);
					if(localEleMap == null){
						localEleMap = new HashMap<Long, Map<Long,Long>>();
						localEleAgeRangWiseCadreMap.put(constituencyId, localEleMap);
					}
					Map<Long,Long> rangeMap = localEleMap.get(localEleBodyId);
					 if(rangeMap == null){
						 rangeMap = new HashMap<Long,Long>();
						 localEleMap.put(localEleBodyId, rangeMap);
					 }
					 rangeMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
		} catch(Exception e) {
			Log.error("Exception occured at getVoterCountRangeWise() in BoothDataValidationService class",e);
		}
		return localEleAgeRangWiseCadreMap;
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
			if(inputVO.getFilterLevel() != null && inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)){
				String filterValues = inputVO.getFilterValueList().get(0).toString().substring(1);//in the case of TEHSIL taking id except first because we are appending while sending
				inputVO.getFilterValueList().clear();
	            inputVO.getFilterValueList().add(Long.valueOf(filterValues));
		 	}
			List<Object[]> locationObjLst = boothInchargeRoleConditionMappingDAO.getLocationBasedOnSelection(inputVO);
			resultList = getLocationList(locationObjLst,"OtherLocation");
			if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				resultList = getLocationList(locationObjLst,IConstants.TEHSIL);
				inputVO.setLocationLevel(IConstants.LOCALELECTIONBODY);
				List<Object[]> localElectionBodyObjLst = boothInchargeRoleConditionMappingDAO.getLocationBasedOnSelection(inputVO);
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
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					}else if (type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)) {
						locationVO.setLocationIdStr("2"+commonMethodsUtilService.getStringValueForObject(param[0]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1])+" Munci/Corp/Greater City");
					} else {
						locationVO.setLocationIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					}
					locationList.add(locationVO);
				}
			}
		} catch(Exception e) {
			Log.error("Exception occured at getLocationList() in BoothDataValidationService class",e);
		}
		return locationList;
	}
	/**
	  * @param  InputVO inputVO 
	  * @return List<BoothAddressVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get booth details based on location.
	  * @since 15-JULY-2017
	  */
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
				 if(inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0 ){
					    locationId = Long.valueOf(inputVO.getFilterValueList().get(0).toString().substring(0,1));
		            	String filterValues = inputVO.getFilterValueList().get(0).toString().substring(1);
		            	inputVO.getFilterValueList().clear();
		            	inputVO.getFilterValueList().add(Long.valueOf(filterValues));
		          }
				 if(locationId == 2){
					 inputVO.setFilterLevel(IConstants.LOCALELECTIONBODY);
				 }
			}
			if(inputVO.getResultType() != null && inputVO.getResultType().equalsIgnoreCase("All")){
				inputVO.setResultType("NotStarted");
				List<Object[]> notStartedBoothDtlsObjList = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothDetails(inputVO);	
				List<BoothAddressVO> notStartedBoothList = getBoothDetails(notStartedBoothDtlsObjList);
				inputVO.setResultType("Started");
				List<Object[]> startedBoothDtlsObjList = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothDetails(inputVO);
				List<BoothAddressVO> startedBoothList = getBoothDetails(startedBoothDtlsObjList);
				inputVO.setResultType("Completed");
				List<Object[]> completedBoothDtlsObjList = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothDetails(inputVO);
				List<BoothAddressVO> completedBoothList = getBoothDetails(completedBoothDtlsObjList);
				//adding into final list
				resultList.addAll(notStartedBoothList);
				resultList.addAll(startedBoothList);
				resultList.addAll(completedBoothList);
			}else{
				 List<Object[]> boothDetailsObjList = boothInchargeRoleConditionMappingDAO.getLocationLevelWiseBoothDetails(inputVO);
	             resultList = getBoothDetails(boothDetailsObjList);
			}
			//getting boothRole Wise Added Member Count
			List<Object[]> addedMemberObjLst = boothInchargeDAO.getBoothRoleWiseAddedMemberCount(inputVO);
			//setting role wise booth added member count
			 setBoothRoleWiseAddedMemberCount(addedMemberObjLst,resultList);
			
			
			if(commonMethodsUtilService.isListOrSetValid(resultList)){
				
				List<Object[]> requiredRolesCountList = boothInchargeRoleConditionMappingDAO.getLocationLevelBoothWiserRequieredMembersDetails(inputVO,"rolesCount");
				List<Object[]> addedCountList = boothInchargeRoleConditionMappingDAO.getLocationLevelBoothWiserRequieredMembersDetails(inputVO,"addedCount");
				Map<Long,Long> requiredRolesMap = new HashMap<Long, Long>(0);
				Map<Long,Long> readyToFinalMap = new HashMap<Long, Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(requiredRolesCountList) && commonMethodsUtilService.isListOrSetValid(addedCountList)){
					for (Object[] param : requiredRolesCountList) {
							requiredRolesMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(addedCountList)){
					for (Object[] param : addedCountList) {
						if(requiredRolesMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
							Long maxCount = requiredRolesMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							Long addedCount = commonMethodsUtilService.getLongValueForObject(param[1]);
							if(addedCount != null && addedCount.longValue()>0 && maxCount != null && maxCount.longValue()>0 &&  addedCount.longValue() == maxCount.longValue()){
								readyToFinalMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
							}
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(readyToFinalMap)){
					for (BoothAddressVO boothAddressVO : resultList) {
							boothAddressVO.setTotalCount(requiredRolesMap.get(boothAddressVO.getBoothId()));
						if(boothAddressVO.getBoothId() != null && boothAddressVO.getBoothId().longValue()>0L && readyToFinalMap.get(boothAddressVO.getBoothId()) != null){
							boothAddressVO.setIsReadyToConfirm("yes");
						}
						if(boothAddressVO.getBoothId() != null && boothAddressVO.getBoothId().longValue()==1060048L)
							System.out.println(boothAddressVO.getBoothId());
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return resultList;
	}
	public void setBoothRoleWiseAddedMemberCount(List<Object[]> addedMemberObjLst,List<BoothAddressVO> resultList) {
		try {
			if(addedMemberObjLst != null && addedMemberObjLst.size() > 0){
				for (Object[] param : addedMemberObjLst) {
					Long boothId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long roleId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long cadreCount = commonMethodsUtilService.getLongValueForObject(param[2]);
					BoothAddressVO matchVO = getMatchVO(resultList,boothId);
					if(matchVO != null){
						if(roleId == 1l){//Convenor
							matchVO.setConvenerCount(cadreCount);
						}else if(roleId == 2l){//member
							matchVO.setMemberCount(cadreCount);
						}
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
	}
	public BoothAddressVO getMatchVO(List<BoothAddressVO> resultList,Long boothId){
		try {
			 if(resultList != null ){
				 for (BoothAddressVO boothAddressVO : resultList) {
					if(boothAddressVO.getBoothId().longValue() == boothId){
						return boothAddressVO;
					}
				}
			 }
		} catch (Exception e){
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return null;
	}
	public List<BoothAddressVO> getBoothDetails(List<Object[]> boothDetailsObjList){
		List<BoothAddressVO> boothDtlsList = new ArrayList<BoothAddressVO>(0);
		try{
			if (boothDetailsObjList != null && !boothDetailsObjList.isEmpty()) {
				for (Object[] param: boothDetailsObjList) {
					BoothAddressVO boothDetailsVO = getCompletedBoothDetails(param);
					boothDetailsVO.setIsReadyToConfirm("no");
					boothDtlsList.add(boothDetailsVO);
				}
			}
		}catch(Exception e){
			Log.error("Exception occured at getBoothDetails() in BoothDataValidationService class",e);
		}
		return boothDtlsList;
	}
	public BoothAddressVO getCompletedBoothDetails(Object[] param){
		BoothAddressVO boothDetailsVO = new BoothAddressVO();
		try{
			if(param != null && param.length > 0){
				boothDetailsVO.setBoothId(commonMethodsUtilService.getLongValueForObject(param[0]));
				boothDetailsVO.setBoothName(commonMethodsUtilService.getStringValueForObject(param[1]));
				String boothStatus = commonMethodsUtilService.getStringValueForObject(param[2]);
				boothDetailsVO.setIsConfirm(boothStatus);
				String status="";
				if (boothStatus.equalsIgnoreCase("N") && param[3]== null && param[4] == null) {//param[3] startedDate,param[4] completed date
					status="NotStarted";
				} else if(boothStatus.equalsIgnoreCase("N") && param[3] != null && param[4] == null) {
					status="Started";
				} else if(boothStatus.equalsIgnoreCase("Y") && param[3] != null && param[4] != null) {
					status="Completed";
				}
				boothDetailsVO.setStatus(status);
				boothDetailsVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[5]));
				boothDetailsVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[6]));
				boothDetailsVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
				boothDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
				boothDetailsVO.setParliamentConstituencyId(commonMethodsUtilService.getLongValueForObject(param[9]));
				boothDetailsVO.setParliamentConstituency(commonMethodsUtilService.getStringValueForObject(param[10]));
				boothDetailsVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[11]));
				boothDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[12]));
				if (param[17] == null) {//mucipality is null
					boothDetailsVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[13]));
					boothDetailsVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[14]));
				} else {
					boothDetailsVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[17]));
					boothDetailsVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[18])+ " "+ commonMethodsUtilService.getStringValueForObject(param[20]));
				
				}				
				boothDetailsVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[15]));
				boothDetailsVO.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[16]));
				
			}
		}catch (Exception e){
			Log.error("Exception occured at getCompletedBoothDetails() in BoothDataValidationService class",e);
		}
		return boothDetailsVO;
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
			
			 CadreCommitteeVO cadreCommitteeVO = cadreCommitteeService.getCadreVoterBthSerilNo(boothDAO.get(boothId).getConstituency().getConstituencyId(),boothId.toString(),"0");
		       boolean dontConfirm=false;
		       if(cadreCommitteeVO != null && commonMethodsUtilService.isListOrSetValid(cadreCommitteeVO.getCasteList())){ 
		         for (CadreCommitteeVO vo : cadreCommitteeVO.getCasteList()) {
		           if(vo.getAddedCount() != null && vo.getAddedCount().longValue()>1L)
		             dontConfirm=true;
		         }
		       }
		       if(dontConfirm){
		    	   resultVO.setStatus("Multiple members are added within the Serial No ranges mentioned.Please check once.");
		           return resultVO;
		       }
		       
			
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
	/**
	  * @param  Long boothId 
	  * @param Long boothInchargeEnrollmentId
	  * @param Long locationValue
	  * @return List<BoothInchargeDetailsVO>
	  * @author Srujana 
	  * @Description :This Service Method is used to boothInchargeRoleName wise count   . 
	  * @since 14-JULY-2017
	  */
	public List<BoothInchargeDetailsVO> gettingBoothInchargeRoleDetails(Long boothId,Long boothInchargeEnrollmentId,Long locationValue){
		List<BoothInchargeDetailsVO> returnList =new  ArrayList<BoothInchargeDetailsVO>();
		try{
			
		  List<Object[]>  boothInchargeMaxCount = boothInchargeRoleConditionMappingDAO.gettingBoothInchargeMaxCount(boothId,boothInchargeEnrollmentId,locationValue);
		  Map<Long,BoothInchargeDetailsVO> inchargeMapRoles = new HashMap<Long,BoothInchargeDetailsVO>();
			if(boothInchargeMaxCount != null && boothInchargeMaxCount.size()>0){
				
				for(Object[] param : boothInchargeMaxCount){
					BoothInchargeDetailsVO boothVo =new BoothInchargeDetailsVO();
					boothVo.setRoleMappingId(commonMethodsUtilService.getLongValueForObject(param[0]));
					boothVo.setMinMemberCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					boothVo.setMaxMemberCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					boothVo.setRoleId(commonMethodsUtilService.getLongValueForObject(param[3]));
					boothVo.setRoleName(commonMethodsUtilService.getStringValueForObject(param[4]));
					boothVo.setVacancyCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					inchargeMapRoles.put(commonMethodsUtilService.getLongValueForObject(param[0]), boothVo);
				}
			}
			List<Object[]> boothInchargeFinalCount =boothInchargeDAO.gettingBoothInchargeFinalCount(boothId,boothInchargeEnrollmentId,locationValue);
			   if(boothInchargeFinalCount != null && boothInchargeFinalCount.size()>0){
				   for(Object[] obj :boothInchargeFinalCount){  
					   BoothInchargeDetailsVO   boothVo = inchargeMapRoles.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
						  if(boothVo != null){
						  boothVo.setCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
						  Long count = boothVo.getMaxMemberCount()-boothVo.getCount();
						  boothVo.setVacancyCount(count);
					  }
				   }
			   }
			   if(commonMethodsUtilService.isMapValid(inchargeMapRoles) ){
					for (Map.Entry<Long, BoothInchargeDetailsVO> entrySet : inchargeMapRoles.entrySet()) {
							returnList.add(entrySet.getValue());
					}
				}
			
		}catch(Exception e){
			Log.error("Exception raised at boothInchargeRoleDetails in gettingBoothInchargeRoleDetails class", e);
		}
		return returnList;
	}
	
	/**
	  * @param  boothId,boothIncgRoleId,enrollmentIds
	  * @return String
	  * @author Hymavathi 
	  * @Description :This Service Method is used to Update the range ids in Booth Incharge table after each saving of member. 
	  * @since 14-JULY-2017
	  */
	public String updateRangeIdsOfBoothIncharge(Long boothId,Long boothIncgRoleId,List<Long> enrollmentIds){
		String status = "";
		try{
			
			List<Object[]> serialNosList = boothInchargeDAO.getBoothInchargeRangeIds(boothId,boothIncgRoleId,enrollmentIds);
			Map<Long,Long> memSerialNo = new HashMap<Long,Long>();
			Map<Long,Long> serialNoMap = new HashMap<Long,Long>();
			
			if(commonMethodsUtilService.isListOrSetValid(serialNosList)){
				for (Object[] objects : serialNosList) {
					memSerialNo.put(commonMethodsUtilService.getLongValueForObject(objects[0]), commonMethodsUtilService.getLongValueForObject(objects[1]));
					serialNoMap.put(commonMethodsUtilService.getLongValueForObject(objects[1]), commonMethodsUtilService.getLongValueForObject(objects[0]));
				}
			}
			List<BoothInchargeSerialNoRange> rangeIdList = boothInchargeSerialNoRangeDAO.getAll();
			
			List<Long> serialNos = new ArrayList<Long>();
			Set<Long> uniqSerialNos = null;
			Map<Long,Long> diffMap = null;
			if(commonMethodsUtilService.isMapValid(serialNoMap)){	
				uniqSerialNos =  serialNoMap.keySet();
				serialNos.addAll(uniqSerialNos);
				 Collections.sort(serialNos);
				 diffMap = commonMethodsUtilService.getAdjacentNumsDifference(serialNos);
			}
			
			
			
			if(commonMethodsUtilService.isMapValid(diffMap)){
				for (Map.Entry<Long, Long> serialDiff : diffMap.entrySet()) {
					for (BoothInchargeSerialNoRange rangeModal : rangeIdList) {
						Long boothInchgId = serialNoMap.get(serialDiff.getKey());
						if(rangeModal.getMinValue() <= serialDiff.getValue() && rangeModal.getMaxValue() >= serialDiff.getValue()){
							BoothIncharge boothIncharge = boothInchargeDAO.get(boothInchgId);
							boothIncharge.setBoothInchargeSerialNoRangeId(rangeModal.getBoothInchargeSerialNoRangeId());
							boothInchargeDAO.save(boothIncharge);
							status = "Updated Successfully";
						}
					}
				}
			}
			
			
		}catch (Exception e) {
			status = "Updation Failed";
			e.printStackTrace();
			Log.error("Exception raised at updateRangeIdsOfBoothIncharge in BoothDataValidationService class", e);
		}
		return status;
	}
	
	public String deleteRoleMemberDetails(final Long boothInchargeMappingId,final Long boothInchargeId,final Long userId,final Long boothId,final Long boothInchargeEnrollementId){
		   String status = "";	
		   try {
			   status = (String)transactionTemplate.execute(new TransactionCallback() {
				
				public Object doInTransaction(TransactionStatus arg0) {
					String status="";
					try {
						BoothIncharge  inchargeDetails = boothInchargeDAO.get(boothInchargeId);
						if(inchargeDetails != null){
							inchargeDetails.setIsDeleted("Y");
							inchargeDetails.setIsActive("N");
							inchargeDetails.setUpdatedBy(userId);
							inchargeDetails.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							boothInchargeDAO.save(inchargeDetails);
						}
						status = "delete Successfully";
						//updating startState and isConfirm Status of  booth.If all member is deleted 
						Long boothAddedMembCount =  boothInchargeDAO.getBoothTotalAddedMember(inchargeDetails.getBoothInchargeRoleConditionMapping().getBoothInchargeCommittee().getBoothId(), boothInchargeEnrollementId);
						if(boothAddedMembCount == null || boothAddedMembCount.longValue()==0l){
							//int updateCount = boothInchargeRoleConditionMappingDAO.updateBoothStarteDate(inchargeDetails.getBoothInchargeRoleConditionMapping().getBoothInchargeCommittee().getBoothId(), boothInchargeEnrollementId);
							BoothInchargeCommittee boothInchargeCommittee = boothInchargeCommitteeDAO.get(inchargeDetails.getBoothInchargeRoleConditionMapping().getBoothInchargeCommittee().getBoothInchargeCommitteeId());
							if(boothInchargeCommittee != null){
								boothInchargeCommittee.setStartDate(null);
								boothInchargeCommittee.setIsConfirmed("N");
								boothInchargeCommittee.setCompletedDate(null);
								boothInchargeCommitteeDAO.save(boothInchargeCommittee);
							}
						}
					} catch (Exception e){
						status = "delete Failed";
						e.printStackTrace();
						Log.error("Exception raised at deleteRoleMemberDetails in BoothDataValidationService class", e);
					}
					return status;
				}
			});
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception raised at deleteRoleMemberDetails in BoothDataValidationService class", e);
			}
			return status;
			
	}
	/**
	  * @param  Long userId 
	  * @return InputVO resultVO
	  * @author Santosh 
	  * @Description :This Service Method is used to get user access level and there values.. 
	  * @since 18-JULY-2017
	  */
	public InputVO getLoginUserDtls(Long userId) {
		InputVO resultVO = new InputVO();
		try {
			/*Long activityMemberId = activityMemberDAO.findActivityMemberIdByUserId(userId);
			if(activityMemberId != null && activityMemberId.longValue() > 0){
				List<Object[]> userobjList = activityMemberAccessLevelDAO.getMemberDetailsByActivityMemberId(activityMemberId);
				resultVO = getUserAccessLevelDtls(userobjList);
				return resultVO;
			}else{*/
				List<Long> districtList = userDistrictAccessInfoDAO.getDistrictIdsByUsrId(userId);
				if(districtList != null && districtList.size() > 0){
					resultVO.setAccessType("DISTRICT");
					resultVO.setUserId(userId);
					resultVO.getAccessValues().addAll(new HashSet<Long>(districtList));
				}else{
					List<Object[]> constituencyAccessObjLst = userConstituencyAccessInfoDAO.getConstituencyByUserId(userId);
					if(constituencyAccessObjLst != null && constituencyAccessObjLst.size() > 0){
						for (Object[] param : constituencyAccessObjLst) {
							 if(commonMethodsUtilService.getStringValueForObject(param[1]).equalsIgnoreCase("Parliament"))	{
								 resultVO.setAccessType("MP");
							 }else if(commonMethodsUtilService.getStringValueForObject(param[1]).equalsIgnoreCase("Assembly")){
								 resultVO.setAccessType("MLA");
							 }
							 resultVO.getAccessValues().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
					resultVO.setUserId(userId);
				}
			
		} catch(Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at getLoginUserDtls in BoothDataValidationService class", e);
		}
		return resultVO;
	}
	public InputVO getUserAccessLevelDtls(List<Object[]> objList) {
		InputVO resultVO = new InputVO();
		try {
			if(objList != null && objList.size() > 0){
			  for (Object[] param : objList) {
				  resultVO.setAccessType(commonMethodsUtilService.getStringValueForObject(param[1]));
				  resultVO.setAccessLevelId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  resultVO.getAccessValues().add(commonMethodsUtilService.getLongValueForObject(param[3]));
				  resultVO.setActivityMemberId(commonMethodsUtilService.getLongValueForObject(param[4]));
			  }
			}
		} catch(Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at getUserAccessLevelDtls in BoothDataValidationService class", e);
		}
		return resultVO;
	}

	public List<BoothInchargeDetailsVO> getBoothInchagesMappingRoles(){
		List<BoothInchargeDetailsVO> rolesList = new ArrayList<BoothInchargeDetailsVO>();
		try {
			Log.info("Enteted into getBoothInchagesMappingRoles of BoothDataValidationService class");
			List<Object[]> rolesObjLst = boothInchargeRoleConditionMappingDAO.getBoothInchargeRoles();
			if(rolesObjLst != null && rolesObjLst.size() >0){
				for(Object[] obj : rolesObjLst){
					BoothInchargeDetailsVO rolesVO = new BoothInchargeDetailsVO();
					rolesVO.setRoleId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					rolesVO.setRoleName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					rolesList.add(rolesVO);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured into getBoothInchagesMappingRoles of BoothDataValidationService class",e);
		}
		return rolesList;
	}
	/**
	  * @param  InputVO inputVO
	  * @return List<BoothAddressVO>
	  * @author Santosh 
	  * @Description :This Service Method is used to get cadre details based on booth or location. 
	  * @since 19-JULY-2017
	  */
    public List<BoothAddressVO> getLocationWiseCadreDetails(InputVO inputVO){
    	List<BoothAddressVO> resultList = new ArrayList<BoothAddressVO>(0);
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		if (inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0) {
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}
			
			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				Long locationId = 0l;
				 if(inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0 ){
					    locationId = Long.valueOf(inputVO.getFilterValueList().get(0).toString().substring(0,1));
		            	String filterValues = inputVO.getFilterValueList().get(0).toString().substring(1);
		            	inputVO.getFilterValueList().clear();
		            	inputVO.getFilterValueList().add(Long.valueOf(filterValues));
		          }
				 if(locationId == 2){
					 inputVO.setFilterLevel(IConstants.LOCALELECTIONBODY);
				 }
			}
			List<Object[]> voterDtlsObjLst = boothInchargeDAO.getLocationLevelWseCadreDetails(inputVO, "voter");
			List<Object[]> familyVoterDtlsObjList = boothInchargeDAO.getLocationLevelWseCadreDetails(inputVO, "familyVoter");
			List<BoothAddressVO> voterDtlsList = getCadreDetails(voterDtlsObjLst);
			List<BoothAddressVO> familyVoterDtlsList = getCadreDetails(familyVoterDtlsObjList);
			resultList.addAll(voterDtlsList);
			resultList.addAll(familyVoterDtlsList);
    		
    	} catch (Exception e){
    		Log.error("Exception Occured into getLocationWiseCadreDetails of BoothDataValidationService class",e);
    	}
    	return resultList;
    }
    public List<BoothAddressVO> getCadreDetails(List<Object[]> objList) {
    	List<BoothAddressVO> returnList = new ArrayList<BoothAddressVO>(0);
    	try {
    	    if (objList != null && objList.size() > 0) {
    			for (Object[] param:objList) {
    				BoothAddressVO boothDetailsVO = getCompletedBoothDetails(param);
    				boothDetailsVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[21]));
    				boothDetailsVO.setCadreName(commonMethodsUtilService.getStringValueForObject(param[22]+""+commonMethodsUtilService.getStringValueForObject(param[23])));
    				boothDetailsVO.setImage(commonMethodsUtilService.getStringValueForObject(param[24]));
    				boothDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[25]));
    				boothDetailsVO.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[26]));
    				boothDetailsVO.setSerialNo(commonMethodsUtilService.getLongValueForObject(param[27]));
    				boothDetailsVO.setRangeId(commonMethodsUtilService.getLongValueForObject(param[28]));
    				boothDetailsVO.setRange(commonMethodsUtilService.getStringValueForObject(param[29]));
    				boothDetailsVO.setBoothName(commonMethodsUtilService.getStringValueForObject(param[30]));
    				boothDetailsVO.setRole(commonMethodsUtilService.getStringValueForObject(param[31]));
    				returnList.add(boothDetailsVO);
    			}
    		}
    	} catch (Exception e) {
    		Log.error("Exception Occured into getCadreDetails of BoothDataValidationService class",e);
    	}
    	return returnList;
    }
    
    public List<BoothInchargeDetailsVO> getUserAccessLocatiosLIst(Long userId,Long accessLevelId, String accessLevelType){
    	List<BoothInchargeDetailsVO>  returnList = new ArrayList<BoothInchargeDetailsVO>(0);
    	try {
    		List<Object[]> accessLocationsList = null;
    		List<Long> locationIdsList = new ArrayList<Long>(0);
    		if(accessLevelType != null && accessLevelType.equalsIgnoreCase("MP")){
    			locationIdsList.add(accessLevelId);
    			accessLocationsList = userConstituencyAccessInfoDAO.findAssembliesConstituenciesByParliaments(locationIdsList);
    		}else if(accessLevelType != null && accessLevelType.equalsIgnoreCase("MLA")){
    			accessLocationsList = boothDAO.getConstituencyInfoByConstituencyIdElectionYearAndElectionType(accessLevelId);
    		}else if(accessLevelType != null && accessLevelType.equalsIgnoreCase("STATE")){
    			accessLocationsList = boothDAO.getAllAssemblyConstituenciesByStateTypeId(1L,1L,null);
    		}else if(accessLevelType != null && accessLevelType.equalsIgnoreCase("DISTRICT")){
    			accessLocationsList = boothDAO.getConstituenciesByDistrictId(accessLevelId);
    		}
			
			List<Long> accessAssemblyIdsList = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(accessLocationsList)){
				for (Object[] param : accessLocationsList) {
					accessAssemblyIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					returnList.add(new BoothInchargeDetailsVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured into getUserAccessLocatiosLIst of BoothDataValidationService class",e);
		}
    	return returnList;
    }
    
    public List<BoothInchargeDetailsVO> getCommitteeFinalizedBoothsListforUnlock(Long userId,List<Long> locationIdsList){
    	List<BoothInchargeDetailsVO>  returnList = new ArrayList<BoothInchargeDetailsVO>(0);
    	try {
			if(commonMethodsUtilService.isListOrSetValid(locationIdsList)){
				List<Object[]> boothCommitteeList =boothInchargeCommitteeDAO.getCommitteeFinalizedBoothsListforUnlock(locationIdsList);
				if(commonMethodsUtilService.isListOrSetValid(boothCommitteeList)){	
					for (Object[] param : boothCommitteeList) {
						returnList.add(new BoothInchargeDetailsVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured into getCommitteeFinalizedBoothsListforUnlock of BoothDataValidationService class",e);
		}
    	return returnList;
    }
    
    
    public String unlockBoothCommitteesByCommitteeIdsList(Long userId,List<Long> boothCommitteeIdsList){
    	String  status ="success";
    	try {
    		int effectedCommitteesCount = boothInchargeCommitteeDAO.unLockTheBoothInchargeCommittee(userId,boothCommitteeIdsList);
    		if(effectedCommitteesCount>0)
    			status="Total "+effectedCommitteesCount+" Booth Committee(s) Unlocked Successfully...";
    	} catch (Exception e) {
			Log.error("Exception Occured into getCommitteeFinalizedBoothsListforUnlock of BoothDataValidationService class",e);
			status ="failure";
		}
    	return status;
    }
    
    public List<BoothAddressVO> getBoothInchargeCommitteeDetailsByLocation(Long levelId,Long levelValue){
    	List<BoothAddressVO> returnList = new ArrayList<BoothAddressVO>(0);
    	try {  
			List<Long> levelValues = new ArrayList<Long>(0);
			if(levelId != null && levelId.longValue() == 10L){
				levelValues = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(levelValue);
				levelId = 4L;
			}else{
				levelValues.add(levelValue);
			}
			List<Object[]> list = boothInchargeCommitteeDAO.getLocationWiseBoothCommitteeDetails(levelId, levelValues);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					BoothAddressVO vo = new BoothAddressVO();
					vo.setDistrictName(obj[0] != null ? obj[0].toString():"");
					vo.setParliamentConstituency(obj[1] != null ? obj[1].toString():"");
					vo.setConstituencyName(obj[2] != null ? obj[2].toString():"");
					vo.setTehsilName(obj[3] != null ? obj[3].toString():"");
					vo.setLocalElectionBody(obj[4] != null ? obj[4].toString():"");
					vo.setPanchayat(obj[5] != null ? obj[5].toString():"");
					vo.setBoothId(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
					vo.setBoothName(obj[7] != null ? obj[7].toString():"");
					vo.setOwnBoothId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
					vo.setOwnBoothNo(obj[9] != null ? obj[9].toString():"");
					vo.setTdpCadreId(Long.valueOf(obj[10] != null ? obj[10].toString():"0"));
					vo.setImage(obj[11] != null ? obj[11].toString():"");
					
					String base64imageStr= imageAndStringConverter.convertImageToBase64String("http://www.mytdp.com/images/cadre_images/"+vo.getImage());
					if(base64imageStr != null)
						vo.setBase64imageStr(base64imageStr);
					
					vo.setMemberShipNo(obj[12] != null ? obj[12].toString():"");
					vo.setCadreName(obj[13] != null ? obj[13].toString():"");
					vo.setMobileNo(obj[14] != null ? obj[14].toString():"");
					vo.setRole(obj[16] != null ? obj[16].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			Log.error("Exception Occured into getBoothInchargeCommitteeDetailsByLocation of BoothDataValidationService class",e);
		}
    	return returnList;
    }
    
}
