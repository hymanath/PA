/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 21,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dto.GenericUploadDataVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IGenericUploadDataService;
import com.itgrids.partyanalyst.service.IGenericUploadService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Sai Krishna
 *
 */
public class GenericUploadService implements IGenericUploadService {

	private IWardDAO wardDAO;
	private IStateDAO stateDAO;
	private IBoothDAO boothDAO;
	private ITehsilDAO tehsilDAO;
	private IHamletDAO hamletDAO;
	private ICountryDAO countryDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ILocalElectionBodyWardDAO localElectionBodyWardDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	private File uploadFile;
	private Integer totalNoOfSheets;
	private HSSFWorkbook uploadWorkbook;
	private TransactionTemplate transactionTemplate;
	
	private Long wardId;
	private Long boothId;
	private Long stateId;
	private Long tehsilId;
	private Long hamletId;
	private Long countryId;
	private Long districtId;
	private Long townshipId;
	private Long constituencyId;
	private Long localelectionBodyId;	
	private Map<String,Long> regionsMap;
	private GenericUploadDataVO genericUploadDataVO;
	
	private Long userId;
	private String module;
		
	private static Logger log = Logger.getLogger(GenericUploadService.class);
	
	private IGenericUploadDataService genericUploadDataService;

	/**
	 * @return the wardDAO
	 */
	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	/**
	 * @param wardDAO the wardDAO to set
	 */
	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	/**
	 * @return the stateDAO
	 */
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	/**
	 * @return the boothDAO
	 */
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	/**
	 * @param boothDAO the boothDAO to set
	 */
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	/**
	 * @return the tehsilDAO
	 */
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	/**
	 * @param tehsilDAO the tehsilDAO to set
	 */
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	/**
	 * @return the hamletDAO
	 */
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	/**
	 * @param hamletDAO the hamletDAO to set
	 */
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	/**
	 * @return the countryDAO
	 */
	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	/**
	 * @param countryDAO the countryDAO to set
	 */
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	/**
	 * @return the districtDAO
	 */
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	/**
	 * @param districtDAO the districtDAO to set
	 */
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	/**
	 * @return the townshipDAO
	 */
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	/**
	 * @param townshipDAO the townshipDAO to set
	 */
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	/**
	 * @return the constituencyDAO
	 */
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	/**
	 * @param constituencyDAO the constituencyDAO to set
	 */
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	/**
	 * @return the localElectionBodyDAO
	 */
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	/**
	 * @param localElectionBodyDAO the localElectionBodyDAO to set
	 */
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	/**
	 * @return the localElectionBodyWardDAO
	 */
	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	/**
	 * @param localElectionBodyWardDAO the localElectionBodyWardDAO to set
	 */
	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}

	/**
	 * @return the uploadFile
	 */
	public File getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * @return the uploadWorkbook
	 */
	public HSSFWorkbook getUploadWorkbook() {
		return uploadWorkbook;
	}

	/**
	 * @param uploadWorkbook the uploadWorkbook to set
	 */
	public void setUploadWorkbook(HSSFWorkbook uploadWorkbook) {
		this.uploadWorkbook = uploadWorkbook;
	}

	/**
	 * @return the wardId
	 */
	public Long getWardId() {
		return wardId;
	}

	/**
	 * @param wardId the wardId to set
	 */
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}

	/**
	 * @return the boothId
	 */
	public Long getBoothId() {
		return boothId;
	}

	/**
	 * @param boothId the boothId to set
	 */
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the tehsilId
	 */
	public Long getTehsilId() {
		return tehsilId;
	}

	/**
	 * @param tehsilId the tehsilId to set
	 */
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	/**
	 * @return the hamletId
	 */
	public Long getHamletId() {
		return hamletId;
	}

	/**
	 * @param hamletId the hamletId to set
	 */
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	/**
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the districtId
	 */
	public Long getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the townshipId
	 */
	public Long getTownshipId() {
		return townshipId;
	}

	/**
	 * @param townshipId the townshipId to set
	 */
	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}

	/**
	 * @return the constituencyId
	 */
	public Long getConstituencyId() {
		return constituencyId;
	}

	/**
	 * @param constituencyId the constituencyId to set
	 */
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	/**
	 * @return the localelectionBodyId
	 */
	public Long getLocalelectionBodyId() {
		return localelectionBodyId;
	}

	/**
	 * @param localelectionBodyId the localelectionBodyId to set
	 */
	public void setLocalelectionBodyId(Long localelectionBodyId) {
		this.localelectionBodyId = localelectionBodyId;
	}

	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/**
	 * @param transactionTemplate the transactionTemplate to set
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @return the genericUploadDataService
	 */
	public IGenericUploadDataService getGenericUploadDataService() {
		return genericUploadDataService;
	}

	/**
	 * @param genericUploadDataService the genericUploadDataService to set
	 */
	public void setGenericUploadDataService(
			IGenericUploadDataService genericUploadDataService) {
		this.genericUploadDataService = genericUploadDataService;
	}

	/**
	 * @return the totalNoOfSheets
	 */
	public Integer getTotalNoOfSheets() {
		return totalNoOfSheets;
	}

	/**
	 * @param totalNoOfSheets the totalNoOfSheets to set
	 */
	public void setTotalNoOfSheets(Integer totalNoOfSheets) {
		this.totalNoOfSheets = totalNoOfSheets;
	}
	
	
	/**
	 * @return the regionsMap
	 */
	public Map<String, Long> getRegionsMap() {
		return regionsMap;
	}

	/**
	 * @param regionsMap the regionsMap to set
	 */
	public void setRegionsMap(Map<String, Long> regionsMap) {
		this.regionsMap = regionsMap;
	}

	/**
	 * @return the genericUploadDataVO
	 */
	public GenericUploadDataVO getGenericUploadDataVO() {
		return genericUploadDataVO;
	}

	/**
	 * @param genericUploadDataVO the genericUploadDataVO to set
	 */
	public void setGenericUploadDataVO(GenericUploadDataVO genericUploadDataVO) {
		this.genericUploadDataVO = genericUploadDataVO;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * Obtains HSSFWorkbook From Given Input Excel File.
	 * @param excelFile
	 * @throws Exception
	 */
	private void obtainHSSFWorkbookForUploadExcel() throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Creating HSSFWorkbook From Input Excel ..");
		
		if(!uploadFile.exists()){
			StringBuilder errorStr = new StringBuilder();
			errorStr.append("Given Input Excel File Doesn't Exist / Invalid Excel File ..");
			log.error(errorStr.toString());
			throw new FileNotFoundException(errorStr.toString());
		}
		
		//Create a new input stream and instantiate HSSFWorkbook. 
		InputStream myExcelFile = new FileInputStream(uploadFile);
		this.uploadWorkbook     = new HSSFWorkbook(myExcelFile);
		this.totalNoOfSheets    = this.uploadWorkbook.getNumberOfSheets();
		
		if(log.isDebugEnabled())
			log.debug("HSSFWorkbook Instantiated ..");
	}

	/**
	 * This Method Reads The Data From Excel File and Set them To VO.
	 * @param uploadFile
	 * @return UploadDataErrorMessageVO - which contains upload status
	 */
	public UploadDataErrorMessageVO interpretDataInExcelAndSetToVO(
			File uploadFile,String module,Long countryId,Long userId) {
		
		if(log.isDebugEnabled())
			log.debug("Started To Get HSSFWorkbook From Input Excel File ..");
		
		UploadDataErrorMessageVO uploadDataStatusVO = new UploadDataErrorMessageVO();
		try{
			
			this.userId = userId;
			this.module = module;
			this.countryId = countryId;
			this.uploadFile = uploadFile;
			this.genericUploadDataVO = new GenericUploadDataVO();
			this.genericUploadDataVO.setCanSave(false);
			
			instantiateRegionsMap();
			obtainHSSFWorkbookForUploadExcel();
			readAndPersistExcelData(uploadDataStatusVO);
			uploadDataStatusVO.setUploadStatus("Upload Finished Successfully ..");
			
		}catch(Exception ex){
			ex.printStackTrace();
			log.error("Exception Raised While Reading The Excel data :" + ex);
			uploadDataStatusVO.setExceptionEncountered(ex);
			uploadDataStatusVO.setResultPartial(true);
			uploadDataStatusVO.setExceptionMsg(ex.getMessage());
			uploadDataStatusVO.setUploadStatus("Upload Failed Due To Errors ..");
		}
		
	 return uploadDataStatusVO;
	}
	
	/**
	 * Method Reads Data Row By Row From Each Sheet In Workbook and Persist the Data.
	 * @param uploadDataStatusVO
	 * @throws Exception
	 */
	private void readAndPersistExcelData(UploadDataErrorMessageVO uploadDataStatusVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Reading Excel Data From HSSFWorkbook ..");
		
		try{
			uploadDataStatusVO.setTotalSheets(totalNoOfSheets);
			uploadDataStatusVO.setTotalRowsExecuted(0);
			
			//Iterating thru all sheets in workbook
			for(int sheetIndex = 0 ; sheetIndex<totalNoOfSheets ; sheetIndex++){
				
				HSSFSheet currentSheet = this.uploadWorkbook.getSheetAt(sheetIndex);
				Integer totalNoOfRows = currentSheet.getLastRowNum();
				Integer sheetCount = sheetIndex+1;
				
				if(log.isDebugEnabled()){
					log.debug("Reading Sheet " + sheetCount + " ..");
					log.debug(totalNoOfRows + " Rows Of Data is Available To Read ..");
				}
				
				//read data in current sheet
				uploadDataStatusVO.setCurrentSheet(sheetCount);
				readAndSaveDataInCurrentSheet(currentSheet,totalNoOfRows,sheetCount,uploadDataStatusVO);
				
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Read Data From Current Sheet And Save TO VO 
	 * @param currentSheet
	 * @param totRows
	 * @param uploadDataStatusVO
	 * @throws Exception
	 */
	private void readAndSaveDataInCurrentSheet(HSSFSheet currentSheet,Integer totRows,Integer sheetCount,UploadDataErrorMessageVO uploadDataStatusVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Reading Sheet " + sheetCount + " ..");
		
		try{
			//Map To Hold The Excel Sheet Headers Information
			Map<String,Integer> excelHeaderInfo = new HashMap<String,Integer>();
			
			for(int rowIndex=0;rowIndex<=totRows;rowIndex++){
				
				uploadDataStatusVO.setTotalRowsExecuted(uploadDataStatusVO.getTotalRowsExecuted()+1);
				
				HSSFRow row = currentSheet.getRow(rowIndex);
				
				//save data if it is last record..
				//if last row in sheet and check wheather ready to save
				if(rowIndex == totRows){
					
					if(this.genericUploadDataVO != null && this.genericUploadDataVO.getCanSave() != null && this.genericUploadDataVO.getCanSave()){
						
						if(log.isDebugEnabled())
							log.debug("Last Record Is Ready To Save ..");
						saveResult();
						break;
					}
				}
				
				//Check for blank row
				Boolean isBlankRow = checkForBlankRow(row);
				if(isBlankRow)
					continue;
				//Check for region details
				Boolean isRegion   = checkWheatherRowContainsRegionData(row);
				if(isRegion)
					continue;
				//Check for header data
				Boolean isHeader   = checkWheatherRowContainsHeaderData(row,excelHeaderInfo);
				if(isHeader)
					continue;
				
				Boolean isResultData = checkForResultDataToSave(row,excelHeaderInfo,rowIndex,totRows);
				//Record Details Are Available and Ready To Save
				if(isResultData){
					//call service method to save cadre details...
					saveResult();
					
					//set genericUploadDataVO canSave method to false after saving data
					this.genericUploadDataVO = new GenericUploadDataVO();
					this.genericUploadDataVO.setCanSave(false);
				}
				
				Boolean isRowStarted = checkForRowStartedWithDataToSave(row,excelHeaderInfo);
				//Save data row started
				if(isRowStarted){
					
					if(log.isDebugEnabled())
						log.debug("Started Setting " + this.module +" Data To VO ..");
					this.genericUploadDataVO.setCanSave(true);
				}				
				//set row data to VO
				setExcelDataToBeSavedToVO(row,excelHeaderInfo);
				
				continue;			
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Display the result before saving
	 */
	private void saveResult() throws Exception{
		
		try{	
			if(this.genericUploadDataVO != null && this.genericUploadDataVO.getCanSave() != null && this.genericUploadDataVO.getCanSave()){
				
				if(log.isDebugEnabled())
					log.debug("Result Is Ready To Save ..");
				
				this.genericUploadDataVO.setStateId(this.stateId);
				this.genericUploadDataVO.setDistrictId(this.districtId);
				this.genericUploadDataVO.setConstituencyId(this.constituencyId);
				this.genericUploadDataVO.setLocalBodyId(this.localelectionBodyId);
				this.genericUploadDataVO.setMandalId(this.tehsilId);
				this.genericUploadDataVO.setVillageId(this.townshipId);
				this.genericUploadDataVO.setWardId(this.wardId);
				this.genericUploadDataVO.setBoothId(this.boothId);
				
				//call save service
				ResultStatus rs = genericUploadDataService.saveGenericUploadData(this.userId, this.genericUploadDataVO, this.module);
				if(rs.getResultCode() == ResultCodeMapper.FAILURE)
					throw new Exception(rs.getExceptionEncountered());
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Read And Set Excel Data To VO
	 * @param row
	 * @param excelHeaderInfo
	 * @throws Exception
	 */
	private void setExcelDataToBeSavedToVO(HSSFRow row,Map<String,Integer> excelHeaderInfo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Setting Excel Data To VO ..");
		
		try{
			Integer familyCellNO = 0;
					
			if(!excelHeaderInfo.isEmpty()){
				
				if(excelHeaderInfo.containsKey("familyMembers"))
					familyCellNO = excelHeaderInfo.get("familyMembers");
							
				
				//Iterate Thru Headers and fill appropriate data to VO
				for(String headers:excelHeaderInfo.keySet()){
					
					if(headers.equalsIgnoreCase(IConstants.SNO))
						continue;
					
					Integer cellNO = excelHeaderInfo.get(headers);
					HSSFCell dataCell = row.getCell(cellNO);
					
					if(dataCell == null || dataCell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
						continue;
					
					//check and save name in VO
	                if(headers.equalsIgnoreCase(IConstants.NAME)){
						
	                	checkAndSaveNameDetailsInVO(row,cellNO,this.module,familyCellNO);
						continue;
					}else if(headers.equalsIgnoreCase("DOB")){
						
						checkAndSaveDOBDetails(row,cellNO,this.module,familyCellNO);
						continue;
					}
					
					checkAndSaveCellData(dataCell,headers);
				}
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Check and save DOB details
	 * @param row
	 * @param cellNO
	 * @param module
	 * @throws Exception
	 */
	private void checkAndSaveDOBDetails(HSSFRow row,Integer cellNO,String module,Integer dobCellNO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check and Save DOB Details to VO ..");
		
		try{
		Boolean isData = false;
		HSSFCell cellData = row.getCell(cellNO);
		
		if(cellData != null && cellData.getCellType() != HSSFCell.CELL_TYPE_BLANK){
			
			//check wheather data is for cadre family member details
			if(module.equalsIgnoreCase(IConstants.CADRE)){
				
				HSSFCell nameTypeCell = row.getCell(dobCellNO);
				if(nameTypeCell != null && nameTypeCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && nameTypeCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					
					String nameTypeCellVal = nameTypeCell.getRichStringCellValue().toString();
					
					if(nameTypeCellVal.equalsIgnoreCase("headOfFamily")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setDateOfBirth(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setAge(age.longValue());
						}
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("father")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setFatherOrSpouseDob(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setFatherOrSpouseAge(age.longValue());
						}
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("spouse")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setFatherOrSpouseDob(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setFatherOrSpouseAge(age.longValue());
						}
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("firstChild")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setFirstChildDob(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setFirstChildAge(age.longValue());
						}
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("secondChild")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setSecondChildDob(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setSecondChildAge(age.longValue());
						}
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("thirdChild")){
						
						if(HSSFDateUtil.isCellDateFormatted(cellData))
							this.genericUploadDataVO.setThirdChildDob(cellData.getDateCellValue());
						else{
							Double age = cellData.getNumericCellValue();
							this.genericUploadDataVO.setThirdChildAge(age.longValue());
						}
						isData = true;
						
					}
				}
				
			}
			//in remaining cases
			if(!isData){
				
				if(cellData.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					
					if(HSSFDateUtil.isCellDateFormatted(cellData)){
						this.genericUploadDataVO.setDateOfBirth(cellData.getDateCellValue());
					}
				    else{
					Double age = cellData.getNumericCellValue();
					this.genericUploadDataVO.setAge(age.longValue());
				    }
				}
			}
				
		}
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	/**
	 * Handle Name As Specific case inorder to manage it in different ways in different modules
	 * @param row
	 * @param cellNO
	 * @param module
	 * @throws Exception
	 */
	private void checkAndSaveNameDetailsInVO(HSSFRow row,Integer cellNO,String module,Integer nameCellNo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check and Save Name in VO ..");
		
		try{
		Boolean isData = false;
		HSSFCell cellData = row.getCell(cellNO);
				
		if(cellData != null && cellData.getCellType() != HSSFCell.CELL_TYPE_BLANK){
			
			String cellValue = cellData.getRichStringCellValue().toString();
			
			//check wheather data is for cadre family member details
			if(module.equalsIgnoreCase(IConstants.CADRE)){
				
				HSSFCell nameTypeCell = row.getCell(nameCellNo);
				if(nameTypeCell != null && nameTypeCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && nameTypeCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					
					String nameTypeCellVal = nameTypeCell.getRichStringCellValue().toString();
					
					if(nameTypeCellVal.equalsIgnoreCase("headOfFamily")){
						
						this.genericUploadDataVO.setHeadOfFamily(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("father")){
						
						this.genericUploadDataVO.setFather(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("spouse")){
						
						this.genericUploadDataVO.setSpouse(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("firstChild")){
						
						this.genericUploadDataVO.setFirstChild(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("secondChild")){
						
						this.genericUploadDataVO.setSecondChild(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("thirdChild")){
						
						this.genericUploadDataVO.setThirdChild(cellValue);
						isData = true;
						
					}else if(nameTypeCellVal.equalsIgnoreCase("fourthChild")){
						
						this.genericUploadDataVO.setFourthChild(cellValue);
						isData = true;
					}
				}
					
			}
			
			//in remaining cases
			if(!isData)
				this.genericUploadDataVO.setName(cellValue);
		}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Method Checks Cell Data And Sets To VO Appropriate Field
	 * @param dataCell
	 * @param header
	 */
	@SuppressWarnings("unchecked")
	private void checkAndSaveCellData(HSSFCell dataCell,String header) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check And Set Cell Data To VO ..");
		
		try{
		if(dataCell != null && dataCell.getCellType() != HSSFCell.CELL_TYPE_BLANK){
			
			Double doubleValue = 0D;
			String cellValueToSave = "";
			Date dateCellValue = new Date();
			
			//using reflection mechanism to set data to appropriate fields in VO
			Class cls   = this.genericUploadDataVO.getClass();
			Field field = cls.getField(header);
			
			//check data type in cell
			switch(dataCell.getCellType()){
			
			                                
			case HSSFCell.CELL_TYPE_STRING ://case where data is string type
				                            if(log.isDebugEnabled())
				                                 log.debug("Cell Data Under " + header + " Is String ..");
			                                cellValueToSave = dataCell.getRichStringCellValue().toString();
			                                break;
			                                 	                                
			case HSSFCell.CELL_TYPE_NUMERIC ://case where data is numeric type
				                             if(log.isDebugEnabled())
                                             log.debug("Cell Data Under " + header + " Is Numeric ..");
			                                 if(HSSFDateUtil.isCellDateFormatted(dataCell)){
			                                	 dateCellValue   = dataCell.getDateCellValue();
			                                	 cellValueToSave = dateCellValue.toString();
			                                 }
			                                 else{
			                                				                                	
			                                	 doubleValue     = dataCell.getNumericCellValue();
			                                	 Long longVal    = doubleValue.longValue();
			                                	 cellValueToSave = longVal.toString();
			                                 }
			                                 break;
			                                 
			case HSSFCell.CELL_TYPE_BLANK : break;
			
			}
			
			//save cell data to VO appropriate field
			if(!"".equalsIgnoreCase(cellValueToSave))
				field.set(this.genericUploadDataVO, cellValueToSave);
		}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Check for wheather row started with save data record
	 * @param row
	 * @param excelHeaderInfo
	 * @return
	 * @throws Exception
	 */
	private Boolean checkForRowStartedWithDataToSave(HSSFRow row,Map<String,Integer> excelHeaderInfo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Checking Wheather Save Data Row Started Or Not ..");
		Boolean isRowDataStart = false;
		try{
		
        if(!excelHeaderInfo.isEmpty()){
			
			Integer snoCellNo = excelHeaderInfo.get(IConstants.SNO);
			HSSFCell cell = row.getCell(snoCellNo);
			
			if(cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK && cell.getNumericCellValue() >= 1){
				
                if(this.genericUploadDataVO != null && this.genericUploadDataVO.getCanSave() != null && !this.genericUploadDataVO.getCanSave()){
					
					if(log.isDebugEnabled())
						log.debug("Record Is Ready To Read And Set To VO ..");
				 return true;
				}
			}
		}
		}catch(Exception ex){
			throw ex;
		}
		
	 return isRowDataStart;
	}
	
	/**
	 * Check For Data To Save Or Not
	 * @param row
	 * @return
	 * @throws Exception
	 */
	private Boolean checkForResultDataToSave(HSSFRow row,Map<String,Integer> excelHeaderInfo,Integer rowIndex,Integer totRows) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Checking wheather To Save Data Or Not ..");
		Boolean isResultData = false;
		try{
		
		
		//check wheather data is ready to save
		if(!excelHeaderInfo.isEmpty()){
			
			Integer snoCellNo = excelHeaderInfo.get(IConstants.SNO);
			HSSFCell cell = row.getCell(snoCellNo);
			
			if(cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK && cell.getNumericCellValue() >= 1){
				
				if(this.genericUploadDataVO != null && this.genericUploadDataVO.getCanSave() != null && this.genericUploadDataVO.getCanSave()){
					
					if(log.isDebugEnabled())
						log.debug("Record Is Ready To Save ..");
					return true;
				}
				
			}
		}
		}catch(Exception ex){
			throw ex;
		}
	 
	 return isResultData;	
	}
	
	/**
	 * Check wheather row is blank or not
	 * @param row
	 * @return
	 * @throws Exception
	 */
	private Boolean checkForBlankRow(HSSFRow row) throws Exception{
		
		Boolean isBlank=true;
		
		try{
			
			if(row == null)
				return true;
		
	        for(int i=0;i<=4;i++){
				HSSFCell cell  = row.getCell(i);
				if(cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					return false;
			}
		}catch(Exception ex){
			throw ex;
		}
		
	 return isBlank;
	}
	
	/**
	 * Check Wheather the row data is region details or not and returns true if it has region data
	 * @param row
	 * @return Boolean value
	 */
	private Boolean checkWheatherRowContainsRegionData(HSSFRow row) throws Exception{
		
		Boolean isRegion = false;
		
		try{
			
			HSSFCell cell0  = row.getCell(0);
			HSSFCell cell1  = row.getCell(1);
			HSSFCell cell2  = row.getCell(2);
			HSSFCell cell3  = row.getCell(3);
			HSSFCell cell4  = row.getCell(4);
				
			//is executed if not region field
			if(cell2 != null && cell2.getCellType() != HSSFCell.CELL_TYPE_BLANK || cell3 != null && cell3.getCellType() != HSSFCell.CELL_TYPE_BLANK || cell4 != null && cell4.getCellType() != HSSFCell.CELL_TYPE_BLANK)
				return isRegion;
			
			//check for region field
			if(cell0 != null && cell0.getCellType() == HSSFCell.CELL_TYPE_STRING && cell1 != null && cell1.getCellType() == HSSFCell.CELL_TYPE_STRING || cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				
				String regionType = cell0.getRichStringCellValue().toString();
				String regionName = "";
				
				if(cell1.getCellType() == HSSFCell.CELL_TYPE_STRING)
					regionName = cell1.getRichStringCellValue().toString();
				
				else if(cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					
					Double cellVal     = cell1.getNumericCellValue();
					Long cellLongvalue = cellVal.longValue();
					regionName         = cellLongvalue.toString();
				}
				
				if(regionsMap.containsKey(regionType)){
					getRegionDetailsByRegionName(regionType,regionName);
					
					//check wheather to save data
					if(this.genericUploadDataVO != null && this.genericUploadDataVO.getCanSave() != null && this.genericUploadDataVO.getCanSave()){
						
						//call service method to save data
						saveResult();
						
						//set genericUploadDataVO canSave method to false after saving data
						this.genericUploadDataVO = new GenericUploadDataVO();
						this.genericUploadDataVO.setCanSave(false);
					}
				 return true;
				}
			}
        }catch(Exception ex){
			
			throw ex;
		}
			
	 return isRegion;
	}
	
	/**
	 *  Check Wheather the row data is region details or not and returns true if it has header data
	 * @param row
	 * @return Boolean value
	 */
	private Boolean checkWheatherRowContainsHeaderData(HSSFRow row,Map<String,Integer> excelHeaderInfo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Checking Wheather The Row Contains Header Data ..");
		Boolean isHeader = false;
		
		try{
		
		
			int i=0;
			//To get column which begins with data without blank
			for(i=0;i<=row.getLastCellNum();i++){
				HSSFCell cell  = row.getCell(i);
				if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
					continue;
				else if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					break;
			}
			
			if(i < row.getLastCellNum()){
				
				HSSFCell cell0  = row.getCell(i);
				
				//check for cell data is not blank
				if(cell0 != null && cell0.getCellType()!= HSSFCell.CELL_TYPE_BLANK){
					
					if(cell0.getCellType() == HSSFCell.CELL_TYPE_STRING){
						
						String cellValue = cell0.getRichStringCellValue().getString();
						if(cellValue.equalsIgnoreCase(IConstants.SNO)){
							
							isHeader = true;	
							if(log.isDebugEnabled())
								log.debug("Header Data Found ..");
							
							//Iterate Until Header Data Is Available
							while(isHeader){
								
								//add header data to header map
								excelHeaderInfo.put(cellValue, i);
								cell0  = row.getCell(++i);
								
								if(cell0 == null || cell0.getCellType() == HSSFCell.CELL_TYPE_BLANK)
									break;
								
								cellValue = cell0.getRichStringCellValue().getString();
							}
							
						}
					}
				}
			}
		}catch(Exception ex){
			
			throw ex;
		}
		
	 return isHeader;
	}
	
	/**
	 * Method Gets The Details Of Region By Region Name
	 * @param row
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getRegionDetailsByRegionName(String regionType,String regionName) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started To Get " + regionName + " " + regionType + " Details ..");
		
		try{
		//If Region Is Country
		if(regionType.equalsIgnoreCase(IConstants.COUNTRY)){
			getCountryRegionDetails(regionType,regionName);
		}
			
		else if(regionType.equalsIgnoreCase(IConstants.STATE))
			getStateRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.DISTRICT))
			getDistrictRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			getConstituencyRegionDetails(regionType,regionName);
					
		else if(regionType.equalsIgnoreCase(IConstants.TEHSIL) || regionType.equalsIgnoreCase(IConstants.MANDAL))
			getMandalRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.TOWNSHIP) || regionType.equalsIgnoreCase(IConstants.VILLAGE))
			getVillageRegionDetails(regionType,regionName);
					
		else if(regionType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) || regionType.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
			getLocalBodyRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.WARD))
			getWardRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.HAMLET))			
			getHamletRegionDetails(regionType,regionName);
			
		else if(regionType.equalsIgnoreCase(IConstants.BOOTH))
			getBoothRegionDetails(regionType,regionName);
		}catch(Exception ex){
		
			throw ex;
		}
			
	}
	
	@SuppressWarnings("unchecked")
	private void getCountryRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			
			List countryLst = countryDAO.getCountryIdByCountryName(regionName);
			this.countryId  = getIdFromList(countryLst,regionName,regionType);
			this.regionsMap.put(regionType, this.countryId);
		}
		catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getStateRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
				
			List stateLst = stateDAO.findStateIdByNameAndCountryId(regionName, this.countryId);
			this.stateId  = getIdFromList(stateLst,regionName,regionType);
			this.regionsMap.put(regionType, this.stateId);
		
		}catch(Exception ex){
			
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getDistrictRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			List distLst    = districtDAO.getDistrictIdByStateIdAndDistrictName(this.stateId, regionName);
			this.districtId = getIdFromList(distLst,regionName,regionType);
			this.regionsMap.put(regionType, this.districtId);
		}
		catch(Exception ex){
			
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getConstituencyRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			
		//List constiLst      = constituencyDAO.findByConstituencyIdConstituencyNameAndDistrictId(regionName, this.districtId,IConstants.ASSEMBLY_ELECTION_TYPE); 
		List constiLst      =	delimitationConstituencyDAO.getLatestConstituencyByConstituencyNameAndDistrictIdAndElectionType(regionName, this.districtId, IConstants.ASSEMBLY_ELECTION_TYPE);
		this.constituencyId = getIdFromList(constiLst,regionName,regionType);
		this.regionsMap.put(regionType, this.constituencyId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getMandalRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			
			List mandLst  = tehsilDAO.findTehsilIdByTehsilNameAndDistrict(regionName, this.districtId);
			this.tehsilId = getIdFromList(mandLst,regionName,regionType);
			this.regionsMap.put(regionType, this.tehsilId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getVillageRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			
			List villLst    = townshipDAO.findTownshipIdByTownshipNameAndTehsilId(regionName, this.tehsilId);
			this.townshipId = getIdFromList(villLst,regionName,regionType);
			this.regionsMap.put(regionType, this.townshipId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getLocalBodyRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			List localElecLst        = localElectionBodyDAO.getLocalElectionBodyIdByNameAndDistrictId(regionName, this.districtId);
			this.localelectionBodyId = getIdFromList(localElecLst,regionName,regionType);
			this.regionsMap.put(regionType, this.localelectionBodyId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getWardRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			//check for ward details in localelectionbodyward table
	 		List wardList = localElectionBodyWardDAO.findWardIdByWardNameAndLocalelectionId(regionName, this.localelectionBodyId);
			if(wardList != null && wardList.size() > 0){
				
				this.wardId  = getIdFromList(wardList,regionName,regionType);
				this.regionsMap.put(regionType, this.wardId);
			}
			
			//ward details not avbl in localelectionbodyward table then go for ward(constituency) table
			else{
				
				List wardLst = constituencyDAO.getWardIdByWardNameAndLocalBodyId(regionName, this.localelectionBodyId);
				this.wardId  = getIdFromList(wardLst,regionName,regionType);
				this.regionsMap.put(regionType, this.wardId);
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void getHamletRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			List hamLst   = hamletDAO.findByHamletNameAndTownshipId(regionName, this.townshipId);
			this.hamletId = getIdFromList(hamLst,regionName,regionType);
			this.regionsMap.put(regionType, this.hamletId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void getBoothRegionDetails(String regionType,String regionName) throws Exception{
		
		try{
			
			List boothsLst = boothDAO.findBoothIdPartNoConstituencyIdAndYear(this.constituencyId, 2009L, regionName);
			this.boothId   = getIdFromList(boothsLst,regionName,regionType);
			this.regionsMap.put(regionType, this.boothId);
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Long getIdFromList(List list,String regionName,String regionType) throws Exception{
		Long regId = 0L;
		
		try{
			if(list != null){
				
				//if more regions exists
				if(list.size() == 0 || list.size() > 1){
					throw new Exception("No or More Than One " + regionType + " Details Exists For " + regionName);
				} 
				
				if(list.size() == 1){
					Object value = (Object)list.get(0);
					regId = (Long)value;
				}
			}
			
			if(list == null || regId.equals(0L)){
				throw new Exception("NO " + regionType + " Details For " + regionName);
			}
		
		}catch(Exception ex){
			throw ex;
		}
		
	 return regId;
	}
	
	/**
	 * Instantiate Regions Map With Region Static Details
	 */
	private void instantiateRegionsMap() throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Instantiating Regions Map ..");
		Map<String,Long> regionMap = new HashMap<String,Long>();
		regionMap.put(IConstants.COUNTRY,      0L);
		regionMap.put(IConstants.STATE,        0L);
		regionMap.put(IConstants.DISTRICT,     0L);
		regionMap.put(IConstants.CONSTITUENCY, 0L);
		regionMap.put(IConstants.TEHSIL,       0L);
		regionMap.put(IConstants.MANDAL,       0L);
		regionMap.put(IConstants.VILLAGE,      0L);
		regionMap.put(IConstants.MUNCPAL_CORP, 0L);
		regionMap.put(IConstants.TOWNSHIP,     0L);
		regionMap.put(IConstants.WARD,         0L);
		regionMap.put(IConstants.HAMLET,       0L);
		regionMap.put(IConstants.BOOTH,        0L);
		
	 this.regionsMap = regionMap;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	
    	
	
}
