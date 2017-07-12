 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
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

	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothDetails(InputVO inputVO) {
		List<BoothInchargeDetailsVO> resultList = new ArrayList<BoothInchargeDetailsVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(inputVO.getFromDateStr() != null && inputVO.getFromDateStr().length() > 0 && inputVO.getToDateStr() != null && inputVO.getToDateStr().length() > 0){
				inputVO.setFromDate(sdf.parse(inputVO.getFromDateStr()));
				inputVO.setToDate(sdf.parse(inputVO.getToDateStr()));
			}
			List<Object[]> rtrnObjLst = boothInchargeDAO.getLocationLevelWiseBoothDetails(inputVO,"");
			if (rtrnObjLst != null && rtrnObjLst.size() > 0) {
				for (Object[] param : rtrnObjLst) {
					BoothInchargeDetailsVO locationVO = new BoothInchargeDetailsVO();
					locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					locationVO.setTotalBoothCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					resultList.add(locationVO);
				}
			}
		} catch (Exception e) {
			Log.error("Exception occured at getLocationLevelWiseBoothDetails() in BoothDataValidationService class",e);
		}
		return resultList;
	}
}
