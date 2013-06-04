/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13,2010
 */
package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IDelimitationMappingsUploadService;

/**
 * @author Sai Krishna
 *
 */
public class DelimitationMappingsUploadService implements
		IDelimitationMappingsUploadService {/*

	private IWardDAO wardDAO;
	private IStateDAO stateDAO;
	private IBlockDAO blockDAO;
	private ITehsilDAO tehsilDAO;
	private ICountryDAO countryDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationYearDAO delimitationYearDAO;
	private IDelimitationWardDAO delimitationWardDAO;
	private IDelimitationBlockDAO delimitationBlockDAO;
	private IDelimitationVillageDAO delimitationVillageDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	
	
	private TransactionTemplate transactionTemplate;
	
	private File uploadFile;
	private String country;
	private Country countryObj;
	private int totalNoOfSheets;
	private String delimitationYear;
	private HSSFWorkbook uploadWorkbook;
	private DelimitationYear delimitationYearObj;
	private DelimitationMappingUploadVO delimitationMappingsUploadVO;
	
	private String recentTown="";
	private String recentWard="";
	private String recentBlock="";
	private String recentMandal="";
	private String recentVillage="";
		
	private static Logger log = Logger.getLogger(DelimitationMappingsUploadService.class);
		
	*//**
	 * @return the country
	 *//*
	public String getCountry() {
		return country;
	}

	*//**
	 * @param country the country to set
	 *//*
	public void setCountry(String country) {
		this.country = country;
	}

	*//**
	 * @return the countryObj
	 *//*
	public Country getCountryObj() {
		return countryObj;
	}

	*//**
	 * @param countryObj the countryObj to set
	 *//*
	public void setCountryObj(Country countryObj) {
		this.countryObj = countryObj;
	}

	*//**
	 * @return the totalNoOfSheets
	 *//*
	public int getTotalNoOfSheets() {
		return totalNoOfSheets;
	}

	*//**
	 * @param totalNoOfSheets the totalNoOfSheets to set
	 *//*
	public void setTotalNoOfSheets(int totalNoOfSheets) {
		this.totalNoOfSheets = totalNoOfSheets;
	}

	*//**
	 * @return the delimitationYear
	 *//*
	public String getDelimitationYear() {
		return delimitationYear;
	}

	*//**
	 * @param delimitationYear the delimitationYear to set
	 *//*
	public void setDelimitationYear(String delimitationYear) {
		this.delimitationYear = delimitationYear;
	}

	*//**
	 * @return the blockDAO
	 *//*
	public IBlockDAO getBlockDAO() {
		return blockDAO;
	}

	*//**
	 * @param blockDAO the blockDAO to set
	 *//*
	public void setBlockDAO(IBlockDAO blockDAO) {
		this.blockDAO = blockDAO;
	}

	*//**
	 * @return the delimitationWardDAO
	 *//*
	public IDelimitationWardDAO getDelimitationWardDAO() {
		return delimitationWardDAO;
	}

	*//**
	 * @param delimitationWardDAO the delimitationWardDAO to set
	 *//*
	public void setDelimitationWardDAO(IDelimitationWardDAO delimitationWardDAO) {
		this.delimitationWardDAO = delimitationWardDAO;
	}

	*//**
	 * @return the delimitationBlockDAO
	 *//*
	public IDelimitationBlockDAO getDelimitationBlockDAO() {
		return delimitationBlockDAO;
	}

	*//**
	 * @param delimitationBlockDAO the delimitationBlockDAO to set
	 *//*
	public void setDelimitationBlockDAO(IDelimitationBlockDAO delimitationBlockDAO) {
		this.delimitationBlockDAO = delimitationBlockDAO;
	}

	*//**
	 * @return the delimitationVillageDAO
	 *//*
	public IDelimitationVillageDAO getDelimitationVillageDAO() {
		return delimitationVillageDAO;
	}

	*//**
	 * @param delimitationVillageDAO the delimitationVillageDAO to set
	 *//*
	public void setDelimitationVillageDAO(
			IDelimitationVillageDAO delimitationVillageDAO) {
		this.delimitationVillageDAO = delimitationVillageDAO;
	}

	*//**
	 * @return the delimitationConstituencyDAO
	 *//*
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	*//**
	 * @param delimitationConstituencyDAO the delimitationConstituencyDAO to set
	 *//*
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	*//**
	 * @return the delimitationConstituencyTownDAO
	 *//*
	public IDelimitationConstituencyTownDAO getDelimitationConstituencyTownDAO() {
		return delimitationConstituencyTownDAO;
	}

	*//**
	 * @param delimitationConstituencyTownDAO the delimitationConstituencyTownDAO to set
	 *//*
	public void setDelimitationConstituencyTownDAO(
			IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO) {
		this.delimitationConstituencyTownDAO = delimitationConstituencyTownDAO;
	}

	*//**
	 * @return the delimitationConstituencyMandalDAO
	 *//*
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	*//**
	 * @param delimitationConstituencyMandalDAO the delimitationConstituencyMandalDAO to set
	 *//*
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	*//**
	 * @return the uploadFile
	 *//*
	public File getUploadFile() {
		return uploadFile;
	}

	*//**
	 * @param uploadFile the uploadFile to set
	 *//*
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	*//**
	 * @return the uploadWorkbook
	 *//*
	public HSSFWorkbook getUploadWorkbook() {
		return uploadWorkbook;
	}

	*//**
	 * @param uploadWorkbook the uploadWorkbook to set
	 *//*
	public void setUploadWorkbook(HSSFWorkbook uploadWorkbook) {
		this.uploadWorkbook = uploadWorkbook;
	}

	*//**
	 * @return the delimitationYearObj
	 *//*
	public DelimitationYear getDelimitationYearObj() {
		return delimitationYearObj;
	}

	*//**
	 * @param delimitationYearObj the delimitationYearObj to set
	 *//*
	public void setDelimitationYearObj(DelimitationYear delimitationYearObj) {
		this.delimitationYearObj = delimitationYearObj;
	}

	*//**
	 * @return the wardDAO
	 *//*
	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	*//**
	 * @param wardDAO the wardDAO to set
	 *//*
	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	*//**
	 * @return the stateDAO
	 *//*
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	*//**
	 * @param stateDAO the stateDAO to set
	 *//*
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	*//**
	 * @return the tehsilDAO
	 *//*
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	*//**
	 * @param tehsilDAO the tehsilDAO to set
	 *//*
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	*//**
	 * @return the countryDAO
	 *//*
	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	*//**
	 * @param countryDAO the countryDAO to set
	 *//*
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	*//**
	 * @return the districtDAO
	 *//*
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	*//**
	 * @param districtDAO the districtDAO to set
	 *//*
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	*//**
	 * @return the townshipDAO
	 *//*
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	*//**
	 * @param townshipDAO the townshipDAO to set
	 *//*
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	*//**
	 * @return the constituencyDAO
	 *//*
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	*//**
	 * @param constituencyDAO the constituencyDAO to set
	 *//*
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	*//**
	 * @return the delimitationYearDAO
	 *//*
	public IDelimitationYearDAO getDelimitationYearDAO() {
		return delimitationYearDAO;
	}

	*//**
	 * @param delimitationYearDAO the delimitationYearDAO to set
	 *//*
	public void setDelimitationYearDAO(IDelimitationYearDAO delimitationYearDAO) {
		this.delimitationYearDAO = delimitationYearDAO;
	}

	*//**
	 * @return the delimitationMappingsUploadVO
	 *//*
	public DelimitationMappingUploadVO getDelimitationMappingsUploadVO() {
		return delimitationMappingsUploadVO;
	}

	*//**
	 * @param delimitationMappingsUploadVO the delimitationMappingsUploadVO to set
	 *//*
	public void setDelimitationMappingsUploadVO(
			DelimitationMappingUploadVO delimitationMappingsUploadVO) {
		this.delimitationMappingsUploadVO = delimitationMappingsUploadVO;
	}

	*//**
	 * @return the recentTown
	 *//*
	public String getRecentTown() {
		return recentTown;
	}

	*//**
	 * @param recentTown the recentTown to set
	 *//*
	public void setRecentTown(String recentTown) {
		this.recentTown = recentTown;
	}

	*//**
	 * @return the recentWard
	 *//*
	public String getRecentWard() {
		return recentWard;
	}

	*//**
	 * @param recentWard the recentWard to set
	 *//*
	public void setRecentWard(String recentWard) {
		this.recentWard = recentWard;
	}

	*//**
	 * @return the recentBlock
	 *//*
	public String getRecentBlock() {
		return recentBlock;
	}

	*//**
	 * @param recentBlock the recentBlock to set
	 *//*
	public void setRecentBlock(String recentBlock) {
		this.recentBlock = recentBlock;
	}

	*//**
	 * @return the recentMandal
	 *//*
	public String getRecentMandal() {
		return recentMandal;
	}

	*//**
	 * @param recentMandal the recentMandal to set
	 *//*
	public void setRecentMandal(String recentMandal) {
		this.recentMandal = recentMandal;
	}

	*//**
	 * @return the recentVillage
	 *//*
	public String getRecentVillage() {
		return recentVillage;
	}

	*//**
	 * @param recentVillage the recentVillage to set
	 *//*
	public void setRecentVillage(String recentVillage) {
		this.recentVillage = recentVillage;
	}

	*//**
	 * @return the transactionTemplate
	 *//*
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	*//**
	 * @param transactionTemplate the transactionTemplate to set
	 *//*
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	*//**
	 * reads delimitation mappings data from Excel file
	 * @param excelFile - excel file which is to be read
	 * @param delimitationYear - year of delimitation happened
	 * @param country - country for which delimitation happened
	 * @return delimitationMappingUploadVO - status representing the persisted data
	 *//*
	public DelimitationMappingUploadVO fetchDelimitationDataFromExcel(
			File excelFile,String delimitationYear,String country,final Boolean isValidate) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing 'fetchDelimitationDataFromExcel' Method ..");
		
		this.country    = country;
		this.uploadFile = excelFile;
		this.delimitationYear = delimitationYear;
		
		DelimitationMappingUploadVO delimitationMappingUploadVO = (DelimitationMappingUploadVO)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus txstatus) {
				
				DelimitationMappingUploadVO mappingsUploadVO = new DelimitationMappingUploadVO();
				List<DelimitationUploadValidationVO> delimValidations = new ArrayList<DelimitationUploadValidationVO>();
				mappingsUploadVO.setDelimitationUploadValidationVO(delimValidations);
				
				try{
					
					obtainHSSFWorkbookForUploadExcel();
					readExcelAndPersistData(mappingsUploadVO,isValidate);
					
				}catch(Exception exc){
					exc.printStackTrace();
					log.error("Exception Occured in Delimitation Mappings Saving Process :"+ exc);
					//txstatus.setRollbackOnly();
					mappingsUploadVO.setExceptionEncountered(exc);
					mappingsUploadVO.setExceptionMsg(exc.getMessage());
					return mappingsUploadVO;
				}
			 return mappingsUploadVO;
			}
			
		});
		
	 return mappingsUploadVO;
	}
	
	*//**
	 * Reads the data from Excel Sheets and persist the data to Database
	 * @param delimitationMappingUploadVO
	 *//*
	public void readExcelAndPersistData(DelimitationMappingUploadVO delimitationMappingUploadVO,Boolean isValidate) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Into Block To Read Excel Data For Persisting ..");
		
		checkAndInsertDelimitationYearData();
			
		//Iterating thru all sheets in workbook
		for(int sheetIndex = 0 ; sheetIndex<totalNoOfSheets ; sheetIndex++){
			
			delimitationMappingUploadVO.setCurrentSheet(sheetIndex);
			
			//Map To Hold The Excel Sheet Headers Information
			Map<String,Integer> excelHeaderInfo = new HashMap<String,Integer>();
			HSSFSheet currentSheet = this.uploadWorkbook.getSheetAt(sheetIndex);
			
			//Method to get header info in current sheet
			Integer headerRow = getHeaderInfoFromSheet(currentSheet,excelHeaderInfo);
			
			//read sheet and saving the mapping details to VO
			checkAndInsertDelimitationMappingsData(headerRow,sheetIndex,currentSheet,excelHeaderInfo,delimitationMappingUploadVO,isValidate);
			
		}
		
	}
	
	*//**
	 * Obtains HSSFWorkbook from given input Excel file
	 * @param excelFile
	 *//*
	private void obtainHSSFWorkbookForUploadExcel() throws Exception{
		
		if(!uploadFile.exists()){
			StringBuilder errorStr = new StringBuilder();
			errorStr.append("Given Input Excel File Doesn't Exist / Invalid Excel File ..");
			log.error(errorStr.toString());
			throw new FileNotFoundException(errorStr.toString());
		}
		
		//Create a new input stream and construct an instance of HSSFWorkbook. 
		InputStream myExcelFile = new FileInputStream(uploadFile);
		this.uploadWorkbook     = new HSSFWorkbook(myExcelFile);
		this.totalNoOfSheets    = this.uploadWorkbook.getNumberOfSheets();
	}
	
	*//**
	 * Checks for availability of delimitation year data in DelimitationYear entity and inserts if it doesn't exists.
	 * @param delimitationYear
	 *//*
	private void checkAndInsertDelimitationYearData() throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Trying To Check Wheather Delimitation Year Exists In DelimitationYear Entity Or Not ..");
		
		List<DelimitationYear> delimitationYearObjList = delimitationYearDAO.findByDelimitationYear(this.delimitationYear);
		
		if(delimitationYearObjList != null && delimitationYearObjList.size() > 0){
			
			if(log.isInfoEnabled())
				log.info("Delimitation Year Exists In DelimitationYear Entity ..");
			this.delimitationYearObj = delimitationYearObjList.get(0);
			
		}else{
			
			if(log.isDebugEnabled())
				log.debug("Delimitation Year Doesn't Exists In DelimitationYear Entity Trying To Save ..");
			
			setCountryObject();
			DelimitationYear delimYear  = new DelimitationYear();
			delimYear.setYear(this.delimitationYear);
			delimYear.setUpdatedDate(new Date());
			delimYear.setCountry(this.countryObj);
			delimYear.setDescription("Delimitation Happened In "+this.delimitationYear);
			
			//Saving the Delimitation Year Details
			delimitationYearDAO.save(delimYear);
			this.delimitationYearObj = delimYear;
			
			if(log.isInfoEnabled())
				log.info("Delimitation Year Doesn't Exists In DelimitationYear Entity Trying To Save ..");
		}
	}
	
	*//**
	 * Method To Obtain Country Object Based On Country Name
	 *//*
	private void setCountryObject() throws Exception{
		
		List<Country> countryList = countryDAO.findByCountryName(this.country);
		if(countryList == null || countryList.size() == 0)
			throw new Exception("Country " + this.country + " Details Are Not Available ..");
		
		this.countryObj = countryList.get(0);
	}
	
	*//**
	 * Method Gets The Sheet Header Info and holds in a map
	 * @param currentSheet - current running sheet.
	 * @param excelHeaderInfo
	 * @return startingRow - Row no where header details are available.
	 *//*
	private Integer getHeaderInfoFromSheet(HSSFSheet currentSheet,Map<String,Integer> excelHeaderInfo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Getting Header Details From Sheet ..");
		
		int startingRow  = 0;
		Boolean isHeader = false;
		List<String> headers = new ArrayList<String>();
		List<String> mandatoryHeaders = new ArrayList<String>();
		
		//add constants to headers
		headers.add(IConstants.SNO);
		headers.add(IConstants.TRU);
		headers.add(IConstants.SCOPE);
		mandatoryHeaders.add(IConstants.REGION_LEVEL);
		mandatoryHeaders.add(IConstants.REGION_NAME);
				
		//Iterative until Header is found
		do{
			HSSFRow row   = currentSheet.getRow(startingRow++); //Row At Given Index
			HSSFCell header1 = row.getCell(0);                 //Cell At Given Index 0
			HSSFCell header2 = row.getCell(1);                 //Cell At Given Index 1
			HSSFCell header3 = row.getCell(2);                 //Cell At Given Index 2
			HSSFCell header4 = row.getCell(3);                 //Cell At Given Index 3
			
			//Check for Header Info
			if(header1 != null && header1.getCellType() == HSSFCell.CELL_TYPE_BLANK && 
					header2 != null && header2.getCellType() == HSSFCell.CELL_TYPE_BLANK &&
					header3 != null && header3.getCellType() == HSSFCell.CELL_TYPE_BLANK &&
					header4 != null && header4.getCellType() == HSSFCell.CELL_TYPE_BLANK){
				isHeader = false;
			}else{
				
				//Check for mandatory headers which are compulsory
				getMandatoryHeaderDetailsAndSetToHeadersMap(mandatoryHeaders,row,excelHeaderInfo);
				
				//ADD Normal Headers
				getOptionalHeaderDetailsAndSetToHeadersMap(headers,row,excelHeaderInfo);
				
				isHeader = true;
			}
			
		}while(!isHeader);
		
		for(String headeMap:excelHeaderInfo.keySet()){
			System.out.println(headeMap + " -- >" + excelHeaderInfo.get(headeMap));
		}
		
	 return startingRow;
		
	}
	
	*//**
	 * Method TO Get Mandatory Header Details From Input Excel and Add to Header Details Map
	 * @param mandatoryHeaders
	 * @param row
	 * @param excelHeaderInfo
	 *//*

	private void getMandatoryHeaderDetailsAndSetToHeadersMap(List<String> mandatoryHeaders,HSSFRow row,Map<String,Integer> excelHeaderInfo) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Trying To Get Mandatory Header Details From Excel ..");
		
		//Check for mandatory headers which are compulsory
		for(String header : mandatoryHeaders){
			boolean notAvailable = true;
						
			for(int i=0; i<row.getLastCellNum();i++){
				HSSFCell cellContent = row.getCell(i);
				if(cellContent != null && header.equalsIgnoreCase(cellContent.getRichStringCellValue().toString())){
					excelHeaderInfo.put(header,i);
					notAvailable = false;
					break;
					
				}
			}
			
			if(notAvailable){
				throw new Exception(header+" Column Name is Not available in the excel sheet file name :");
			}
		}
	}
	
	*//**
	 * Method TO Get Optional Header Details From Input Excel and Add to Header Details Map
	 * @param headers
	 * @param row
	 * @param excelHeaderInfo
	 *//*

	private void getOptionalHeaderDetailsAndSetToHeadersMap(List<String> headers,HSSFRow row,Map<String,Integer> excelHeaderInfo){
		
		if(log.isDebugEnabled())
			log.debug("Trying To Get Optional Header Details From Excel ..");
		
		for(String header:headers){
         			
			for(int i=0; i<row.getLastCellNum();i++){
				HSSFCell cellContent = row.getCell(i);
				
				if(cellContent != null && header.equalsIgnoreCase(cellContent.getRichStringCellValue().toString())){
					excelHeaderInfo.put(header,i);
					break;
				}
			}
		}
	}
	
	*//**
	 * Check wheather row is blank or not
	 * @param row
	 * @return
	 * @throws Exception
	 *//*
	private Boolean checkForBlankRow(HSSFRow row) throws Exception{
		
		Boolean isBlank=true;
		
		try{
		
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
	
	*//**
	 * Process The Excel Sheet and Insert Delimitation Mapping Details To Corresponding Entities
	 * @param headerRow
	 * @param currentSheet
	 * @param excelHeaderInfo
	 * @param delimitationMappingUploadVO
	 *//*
	private void checkAndInsertDelimitationMappingsData(Integer headerRow,Integer sheetNo,HSSFSheet currentSheet,Map<String,Integer> excelHeaderInfo,DelimitationMappingUploadVO delimitationMappingUploadVO,Boolean isValidate) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Starting The Reading Process Inside checkAndInsertDelimitationMappingsData Method ..");
		Map<String,String> regionLevelMap = new HashMap<String,String>();
		
		String stateName = "";
		String districtName = "";
		String constituencyName = "";
		State stateObj = new State();
		District districtObj = new District();
		Constituency constituencyObj = new Constituency();
		Integer totalRowsInSheet = currentSheet.getLastRowNum();
		
		if(log.isInfoEnabled())
			log.info("Data Available In " + totalRowsInSheet + " Rows In Sheet " + sheetNo);
		
				
		//Map<mandalName/TownName ,Map<Village/Ward,villageName/WardName>>
		Map<String,MandalSubRegionsVO> mandalsMap = new HashMap<String,MandalSubRegionsVO>();
		Map<String,MandalSubRegionsVO> townsMap   = new HashMap<String,MandalSubRegionsVO>();
		
		
		//Navigate Thru Data Available rows In sheet 
		for(int row=headerRow; row<=totalRowsInSheet+1; row++){
			
			
			//last row
			if(row == totalRowsInSheet+1){
				
				List<DelimitationUploadValidationVO> delimitationValidations = delimitationMappingUploadVO.getDelimitationUploadValidationVO();
				delimitationValidations.add(saveConstituencyMappingDetails(mandalsMap,townsMap,stateObj,districtObj,constituencyObj,isValidate));
				delimitationMappingUploadVO.setDelimitationUploadValidationVO(delimitationValidations);
				
				//delimitationMappingUploadVO.getMappedConstituencies().add(constituencyName);
				Set<String> mappedConstituencies = delimitationMappingUploadVO.getMappedConstituencies();
				if(mappedConstituencies == null || mappedConstituencies.size() == 0){
					mappedConstituencies = new HashSet<String>();
					mappedConstituencies.add(constituencyName);
				}else if(mappedConstituencies != null && mappedConstituencies.size() > 0){
					mappedConstituencies.add(constituencyName);
				}
				
				delimitationMappingUploadVO.setMappedConstituencies(mappedConstituencies);
				if(log.isInfoEnabled())
					log.info(" Mapped " + constituencyName + " Constituency Sub Regions successfully ..");
				break;
			}
			
			HSSFRow sheetRow = currentSheet.getRow(row);
			delimitationMappingUploadVO.setCurrentRow(row+1);
			
			//Check for blank row
			if(sheetRow != null){
			Boolean isBlankRow = checkForBlankRow(sheetRow);
			if(isBlankRow)
				continue;
			}
			
			//check for state details in row
			Boolean isState  = checkForState(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,stateName);
			if(isState){
				stateObj = getStateObjectByStateName(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,stateName);
				stateName = stateObj.getStateName();
				regionLevelMap.put(IConstants.STATE, stateName);
				continue;
			}
			
			//check for district details in row
			Boolean isDistrict = checkForDistrict(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,districtName,districtObj);
			if(isDistrict){
				districtObj = getDistrictObjectByDistrictNameAndState(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,districtName,stateObj.getStateId());
				districtName = districtObj.getDistrictName();
				regionLevelMap.put(IConstants.DISTRICT, districtName);
				continue;
			}
			
			//check for constituency details in row
			Boolean isConstituency = checkForConstituency(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,constituencyName,constituencyObj,districtObj.getDistrictId(),stateObj.getStateId());
			if(isConstituency){
				
												
				if(mandalsMap.isEmpty() && townsMap.isEmpty()){
					
					if(log.isInfoEnabled())
						log.info(" Started Mapping " + constituencyName + " Constituency ..");
					constituencyObj = getConstituencyByConstituencyNameAndDistrictId(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,constituencyName,districtObj.getDistrictId(),stateObj.getStateId());
					constituencyName = constituencyObj.getName();
					regionLevelMap.put(IConstants.CONSTITUENCY, constituencyName);

					continue;
				}else{
					 
					//saving the mapped regions for a constituency
					List<DelimitationUploadValidationVO> delimitationValidations = delimitationMappingUploadVO.getDelimitationUploadValidationVO();
					delimitationValidations.add(saveConstituencyMappingDetails(mandalsMap,townsMap,stateObj,districtObj,constituencyObj,isValidate));
					delimitationMappingUploadVO.setDelimitationUploadValidationVO(delimitationValidations);
					
					mandalsMap = new HashMap<String,MandalSubRegionsVO>();
					townsMap   =  new HashMap<String,MandalSubRegionsVO>();
					
					//delimitationMappingUploadVO.getMappedConstituencies().add(constituencyName);
					Set<String> mappedConstituencies = delimitationMappingUploadVO.getMappedConstituencies();
					if(mappedConstituencies == null || mappedConstituencies.size() == 0){
						mappedConstituencies = new HashSet<String>();
						mappedConstituencies.add(constituencyName);
					}else if(mappedConstituencies != null && mappedConstituencies.size() > 0){
						mappedConstituencies.add(constituencyName);
					}
					
					delimitationMappingUploadVO.setMappedConstituencies(mappedConstituencies);
					if(log.isInfoEnabled())
						log.info(" Mapped " + constituencyName + " Constituency Sub Regions successfully ..");
					
					constituencyObj = getConstituencyByConstituencyNameAndDistrictId(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),sheetRow,constituencyName,districtObj.getDistrictId(),stateObj.getStateId());
					constituencyName = constituencyObj.getName();
					regionLevelMap.put(IConstants.CONSTITUENCY, constituencyName);
					
					continue;
				}
			}
			
			//Map Regions to VO
			Map<String,String> regionMap = mapSubRegionsDetailsToConstituency(excelHeaderInfo.get(IConstants.REGION_LEVEL),excelHeaderInfo.get(IConstants.REGION_NAME),excelHeaderInfo.get(IConstants.SCOPE),sheetRow,stateObj,districtObj,constituencyObj,mandalsMap,townsMap);
			
			if(regionMap.containsKey(IConstants.MANDAL))
				recentMandal = regionMap.get(IConstants.MANDAL);
			else if(regionMap.containsKey("TOWN"))
				recentTown = regionMap.get("TOWN");
			else if(regionMap.containsKey(IConstants.VILLAGE))
				recentVillage = regionMap.get(IConstants.VILLAGE);
			else if(regionMap.containsKey(IConstants.WARD))
				recentWard = regionMap.get(IConstants.WARD);
			else if(regionMap.containsKey(IConstants.BLOCK))
				recentBlock = regionMap.get(IConstants.BLOCK);
			
		}
	}
	
	*//**
	 * Mapping the sub regions details to constituency
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param state
	 * @param district
	 * @param constituency
	 * @param mandalsMap
	 * @param townsMap
	 *//*
	private Map<String,String> mapSubRegionsDetailsToConstituency(Integer levelColumnNo,Integer regionColumnNo,Integer scopeColumnNo,HSSFRow row,State state,District district,Constituency constituency,
			Map<String,MandalSubRegionsVO> mandalsMap,Map<String,MandalSubRegionsVO> townsMap) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Mapping SubRegions Inside mapSubRegionsDetailsToConstituency Method ..");
		
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		HSSFCell scopeCell      = row.getCell(scopeColumnNo);
		Boolean isPartial = false;
		Map<String,String> regionMap = new HashMap<String,String>();
		
		if(levelCell == null){
			throw new Exception("No Data At Column " + levelColumnNo + " In Row " +  row.getRowNum() + " For " + constituency.getName() + " Constituency ..");
		}
		
		if(regionNameCell == null){
			throw new Exception("No Data At Column " + levelColumnNo + " In Row " +  row.getRowNum() + " For " + constituency.getName() + " Constituency ..");
		}
		
		if(levelCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || levelCell.getCellType() != HSSFCell.CELL_TYPE_STRING || 
				regionNameCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || regionNameCell.getCellType() != HSSFCell.CELL_TYPE_STRING){
			throw new Exception("Invalid / No Data In Column " + levelColumnNo + " , " + regionColumnNo);
		}
			
		
		if(scopeCell != null && scopeCell.getCellType() != HSSFCell.CELL_TYPE_BLANK){
			if(scopeCell.getCellType() == HSSFCell.CELL_TYPE_STRING && scopeCell.getRichStringCellValue().toString().equalsIgnoreCase("Part")){
				isPartial = true;
			}
		}
			
			
			
		if(levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.MANDAL)){
			
			this.recentMandal = regionNameCell.getRichStringCellValue().toString();
			regionMap.put(IConstants.MANDAL, this.recentMandal);
			
			MandalSubRegionsVO mandalRegion = new MandalSubRegionsVO();
			mandalRegion.setMandalName(this.recentMandal);
			mandalRegion.setIsPartial(isPartial);
						
			mandalsMap.put(recentMandal, mandalRegion);
			
		}else if(levelCell.getRichStringCellValue().toString().equalsIgnoreCase("TOWN")){
			
			this.recentTown = regionNameCell.getRichStringCellValue().toString();
			regionMap.put("TOWN",this.recentTown);

			MandalSubRegionsVO townRegion = new MandalSubRegionsVO();
			townRegion.setMandalName(this.recentTown);
			townRegion.setIsPartial(isPartial);
			townRegion.setMandalForTown(this.recentMandal);
						
			townsMap.put(recentTown, townRegion);
			
		}else if(levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.VILLAGE)){
			
			this.recentVillage = regionNameCell.getRichStringCellValue().toString();
			regionMap.put(IConstants.VILLAGE,this.recentVillage);
			
			SelectOptionVO village = new SelectOptionVO();
			village.setName(this.recentVillage);
			
			List<SelectOptionVO> villagesInMandal = null;
			
			MandalSubRegionsVO mandalRegion = mandalsMap.get(this.recentMandal);
			villagesInMandal = mandalRegion.getVillagesList();
			
			if(villagesInMandal != null && villagesInMandal.size() > 0){
				villagesInMandal.add(village);
			}else{
				villagesInMandal = new ArrayList<SelectOptionVO>();
				villagesInMandal.add(village);
			}
			
			mandalRegion.setVillagesList(villagesInMandal);
			mandalsMap.put(recentMandal, mandalRegion);
			
		}else if(levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.WARD)){
			
			this.recentWard = regionNameCell.getRichStringCellValue().toString();
			regionMap.put(IConstants.WARD,this.recentWard);
			
			SelectOptionVO ward = new SelectOptionVO();
			ward.setName(recentWard);
			
			List<SelectOptionVO> wardsInTown = null;
			
			MandalSubRegionsVO townRegion = townsMap.get(this.recentTown);
			wardsInTown = townRegion.getVillagesList();
			
			if(wardsInTown != null && wardsInTown.size() > 0){
				wardsInTown.add(ward);
			}else{
				wardsInTown = new ArrayList<SelectOptionVO>();
				wardsInTown.add(ward);
			}
			
			townRegion.setVillagesList(wardsInTown);
			townsMap.put(recentTown, townRegion);
			
		}else if(levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.BLOCK)){
			
			this.recentBlock = regionNameCell.getRichStringCellValue().toString();
			regionMap.put(IConstants.BLOCK,this.recentBlock);
			SelectOptionVO block = new SelectOptionVO(1L,this.recentBlock);
			
           	//get recent town data		
			MandalSubRegionsVO townRegion = townsMap.get(this.recentTown);
			Map<String,List<SelectOptionVO>> blocksInWards = townRegion.getBlocksInWards();
			
			//if blocks map is empty
			if(blocksInWards == null || blocksInWards.isEmpty()){
				
				List<SelectOptionVO> blocksLst = new ArrayList<SelectOptionVO>();
				
				blocksInWards = new HashMap<String,List<SelectOptionVO>>();
				blocksLst.add(block);
				
				blocksInWards.put(this.recentWard, blocksLst);
				
			}else{
				
				//if blocks for ward exists
				if(blocksInWards.containsKey(this.recentWard)){
					
					List<SelectOptionVO> blocksLst = blocksInWards.get(this.recentWard);
					blocksLst.add(block);
					blocksInWards.put(this.recentWard, blocksLst);
					
				}
				//if blocks with ward doesn't exist
				else {
					
					List<SelectOptionVO> blocksLst = new ArrayList<SelectOptionVO>();
					blocksLst.add(block);
					
					blocksInWards.put(this.recentWard, blocksLst);
				}
				
			}
			townRegion.setBlocksInWards(blocksInWards);
			townsMap.put(this.recentTown, townRegion);
			
		}
		
	 return regionMap;
	}
	
	*//**
	 * Persisting The Mapping Details
	 * @param mandalsMap
	 * @param townsMap
	 * @param stateObj
	 * @param districtObj
	 * @param constituencyObj
	 *//*
	private DelimitationUploadValidationVO saveConstituencyMappingDetails(final Map<String,MandalSubRegionsVO> mandalsMap,final Map<String,MandalSubRegionsVO> townsMap,
			final State stateObj,final District districtObj,final Constituency constituencyObj,Boolean isValidate) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Persisting The Mapped Details For "+constituencyObj.getName() + " .. ");
		
		final String delimitationYear = this.delimitationYear;
		final DelimitationYear delimYearObj = this.delimitationYearObj;
		final Boolean isValidat = isValidate;
		
		DelimitationUploadValidationVO delimitationUploadValidationVO = (DelimitationUploadValidationVO)transactionTemplate.execute(new TransactionCallback(){
			
			public Object doInTransaction(TransactionStatus txstatus){
		
				DelimitationUploadValidationVO delimitationUploadVO = new DelimitationUploadValidationVO();
				
				try{
					
				DelimitationConstituency delimitationConsti = null;
				List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyObj.getConstituencyId(), new Long(delimitationYear));
				
				if(delimitationConstituency != null && delimitationConstituency.size() > 1){
					throw new Exception("More Than One Delimitation Mapping Constituency Exists for " + constituencyObj.getName());
				}
				//DelimitationConstituency is available
				else if(delimitationConstituency != null && delimitationConstituency.size() == 1){
					delimitationConsti = delimitationConstituency.get(0);
				}
				//save DelimitationConstituency Details
				else{
					DelimitationConstituency delimconsti = new DelimitationConstituency();
					delimconsti.setConstituency(constituencyObj);
					delimconsti.setYear(new Long(delimitationYear));
					delimconsti.setDelimitationYear(delimYearObj);
					delimitationConsti = delimitationConstituencyDAO.save(delimconsti);
				}
				
				//save delimitation constituency mandal and its sub region details
				checkAndSaveDelimitationConstituencyMandalAndSubRegions(stateObj,districtObj,constituencyObj,mandalsMap,delimitationConsti,delimitationUploadVO,isValidat);
				
				//save delimitation constituency mandal and its sub region details
				if(!isValidat)
				checkAndSaveDelimitationConstituencyTownsAndSubRegions(stateObj,districtObj,constituencyObj,townsMap,delimitationConsti);
				
				}catch(Exception ex){
					
					log.error("Exception Raised While Saving Data : " + ex);
					txstatus.setRollbackOnly();
										
				 return delimitationUploadVO;
				}
				
			 return delimitationUploadVO;
			}
		
		});
		
	 return delimitationUploadValidationVO;
	}
	
	*//**
	 * Check and Save Delimitation Constituency Mandal Mappings 
	 * @param stateObj
	 * @param districtObj
	 * @param constituencyObj
	 * @param mandalsMap
	 * @param delimitationConstituency
	 *//*
	private void checkAndSaveDelimitationConstituencyMandalAndSubRegions(State stateObj,District districtObj,Constituency constituencyObj,
			Map<String,MandalSubRegionsVO> mandalsMap,DelimitationConstituency delimitationConstituency,DelimitationUploadValidationVO delimitationUploadVO,Boolean isValidate) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Saving Mandal And Its Sub Regions Delimitation Details ..");
		
		delimitationUploadVO.setState(stateObj.getStateName());
		delimitationUploadVO.setDistrict(districtObj.getDistrictName());
		delimitationUploadVO.setConstituency(constituencyObj.getName());
				
		if(!mandalsMap.isEmpty() && mandalsMap.size() > 0){
			
			Set<String> mandalNames = mandalsMap.keySet();
			for(String mandal:mandalNames){
				
				//Check for Mandal
				MandalSubRegionsVO mandalData = mandalsMap.get(mandal);
				List<Tehsil> tehsilsList = tehsilDAO.findByTehsilNameAndDistrict(mandal, districtObj.getDistrictId());
				Tehsil tehsilObj = null;
				
				if(tehsilsList != null && tehsilsList.size() > 1){
					throw new Exception("More Than One Mandal Exists With Name " + mandal +" In " + districtObj.getDistrictName() + " District");
				}else if(tehsilsList != null && tehsilsList.size() == 1){
					tehsilObj = tehsilsList.get(0);
				}else{
					throw new Exception("No Mandal Exists With Name " + mandal +" In " + districtObj.getDistrictName() + " District");
				}
				
				//Check And Insert Delimitation Constituency Tehsil
				if(!isValidate){
					List<DelimitationConstituencyMandal> delimConstiMandalList = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(delimitationConstituency.getDelimitationConstituencyID(), tehsilObj.getTehsilId());
					DelimitationConstituencyMandal delimitationMandal = null;
					
					if(delimConstiMandalList != null && delimConstiMandalList.size() > 1){
						throw new Exception("More Than One Delimitation Constituency Mandal Exists for " + mandal +" Mandal In " + districtObj.getDistrictName() + " District");
					}else if(delimConstiMandalList != null && delimConstiMandalList.size() == 1){
						delimitationMandal = delimConstiMandalList.get(0);
					}else{
						
						DelimitationConstituencyMandal delimMandal = new DelimitationConstituencyMandal();
						delimMandal.setDelimitationConstituency(delimitationConstituency);
						delimMandal.setTehsil(tehsilObj);
						Boolean isPartial = mandalData.getIsPartial();
						if(isPartial)
						    delimMandal.setIsPartial(getStringFromAsciiChar(0));
						else
							delimMandal.setIsPartial(getStringFromAsciiChar(1));
						delimitationMandal = delimitationConstituencyMandalDAO.save(delimMandal);
					}
					
					//check for villages
					if(mandalData.getVillagesList() != null && mandalData.getVillagesList().size() > 0){
						saveDelimitationVillagesDetails(delimitationMandal,mandalData.getVillagesList());
					}
				}else{
					
					validateDelimitationTehsilsData(delimitationConstituency.getDelimitationConstituencyID(), tehsilObj.getTehsilId(),delimitationUploadVO,mandalData);
				}
			}
		}
	}
	
	*//**
	 * Returns String from Given Ascii Character
	 * @param i
	 * @return
	 *//*
	private String getStringFromAsciiChar(int i){
		
		String aChar = new Character((char) i).toString();
		
	 return aChar;
	}
	
	
	*//**
	 * validate Delimitation mandals data before uploading 
	 * @param delimConstituencyId
	 * @param tehsilId
	 * @param delimitationUploadVO
	 *//*
	private void validateDelimitationTehsilsData(Long delimConstituencyId,Long tehsilId,DelimitationUploadValidationVO delimitationUploadVO,MandalSubRegionsVO mandalData){
		
		if(log.isDebugEnabled())
			log.debug("Entered Block To Validate Delimitation Mandals Data ..");
		
		//Existing Mandal
		List<DelimitationConstituencyMandal> delimConstiMandalList = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(delimConstituencyId, tehsilId);
		List<SelectOptionVO> existingMandals = null;
		List<SelectOptionVO> mandalsToSave = null;
		existingMandals = delimitationUploadVO.getExistingMandals();
		mandalsToSave = delimitationUploadVO.getMandalsToSave();
		
		if(delimConstiMandalList != null && delimConstiMandalList.size() > 0){
			
			for(DelimitationConstituencyMandal dcm:delimConstiMandalList){
				
				Long isPartial = 0L;
				if(dcm.getIsPartial().equalsIgnoreCase("0"))
					isPartial = 1L;
				SelectOptionVO option = new SelectOptionVO(isPartial,dcm.getTehsil().getTehsilName());
				if(existingMandals == null || existingMandals.size() == 0)
					existingMandals = new ArrayList<SelectOptionVO>();
					
				existingMandals.add(option);			
				delimitationUploadVO.setExistingMandals(existingMandals);
								
			}
			
		}else{
				
			SelectOptionVO option = new SelectOptionVO(0L,"N/A");
						
			if(existingMandals == null || existingMandals.size() == 0)
				existingMandals = new ArrayList<SelectOptionVO>();
				
			existingMandals.add(option);			
			delimitationUploadVO.setExistingMandals(existingMandals);
		}
		
		//Mandal To Save
		Long isPartial = 0L;
		if(mandalData.getIsPartial())
			isPartial = 1L;
		SelectOptionVO option = new SelectOptionVO(isPartial,mandalData.getMandalName());
		
		if(mandalsToSave == null || mandalsToSave.size() == 0)
			mandalsToSave = new ArrayList<SelectOptionVO>();
		
		mandalsToSave.add(option);
		delimitationUploadVO.setMandalsToSave(mandalsToSave);
		
	}
	
	*//**
	 * Save Delimitation Villages Details
	 * @param delimitationMandal
	 * @param villagesList
	 *//*
	private void saveDelimitationVillagesDetails(DelimitationConstituencyMandal delimitationMandal,List<SelectOptionVO> villagesList) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Block To Save Delimitation Villages Info ..");
		
		for(SelectOptionVO township:villagesList){
			
			Township townshipObj = null;
			List<Township> townshipList = townshipDAO.findByTownshipNameAndTehsilId(township.getName(), delimitationMandal.getTehsil().getTehsilId());
			
			if(townshipList != null && townshipList.size() > 1){
				throw new Exception("More than One Township Exits With Name :" + township.getName() + " In " + delimitationMandal.getTehsil().getTehsilName() + " District");
			}else if(townshipList != null && townshipList.size() == 1){
				townshipObj = townshipList.get(0);
			}else{
				throw new Exception("No Township Exits With Name :" + township.getName() + " In " + delimitationMandal.getTehsil().getTehsilName() + " District");
			}
			
			//check and save delimitation village details
			@SuppressWarnings("unused")
			DelimitationVillage delimVillageObj = null;
			List<DelimitationVillage> delimitationVillage = delimitationVillageDAO.findByDelimitationMandalAndTownship(delimitationMandal.getDcm_id(), townshipObj.getTownshipId());
			
			if(delimitationVillage != null && delimitationVillage.size() > 1){
				throw new Exception("More Than One Village Exists With Name " + township.getName() + " In " + delimitationMandal.getTehsil().getTehsilName() + " Mandal");
			}else if(delimitationVillage != null && delimitationVillage.size()  == 1){
				delimVillageObj = delimitationVillage.get(0);
			}else{
				DelimitationVillage delimVillageObject = new DelimitationVillage();
				delimVillageObject.setDelimitationConstituencyMandal(delimitationMandal);
				delimVillageObject.setTownship(townshipObj);
				
				delimitationVillageDAO.save(delimVillageObject);
			}
		}
	}
	
	*//**
	 * Save Delimitation wards Details
	 * @param delimitationTown
	 * @param wardsList
	 *//*
	private void saveDelimitationWardDetails(DelimitationConstituencyTown delimitationTown,List<SelectOptionVO> wardsList,Map<String,List<SelectOptionVO>> blocksInWards) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Block To Save Delimitation Ward Info ..");
		
         for(SelectOptionVO ward:wardsList){
			
        	Boolean hasBlocks = false;
			Ward wardObj = null;
			List<Ward> wardList = wardDAO.findByWardNameAndTownship(ward.getName(), delimitationTown.getTownship().getTownshipId());
			
			if(wardList != null && wardList.size() > 1){
				throw new Exception("More than One Ward Exits With Name :" + ward.getName() + " In " + delimitationTown.getTownship().getTownshipName() + " Township ..");
			}else if(wardList != null && wardList.size() == 1){
				wardObj = wardList.get(0);
			}else{
				throw new Exception("No Ward Exits With Name :" + ward.getName() + " In " + delimitationTown.getTownship().getTownshipName() + " Township");
			}
			
			//check and save blocks for wards
			if(blocksInWards != null && !blocksInWards.isEmpty() && blocksInWards.containsKey(wardObj.getWardName())){
				
				List<SelectOptionVO> blocksList = blocksInWards.get(wardObj.getWardName());
				checkAndSaveBlocksInAWard(wardObj,blocksList);
				hasBlocks = true;
			}
			
			
			//check and save delimitation ward details
			@SuppressWarnings("unused")
			DelimitationWard delimWardObj = null;
			List<DelimitationWard> delimitationWard = delimitationWardDAO.findByDelimitationConstituenyTownAndWard(delimitationTown.getDelimitationConstituencyTownId(), wardObj.getWardId());
			
			if(delimitationWard != null && delimitationWard.size() > 1){
				throw new Exception("More Than One Ward Exists With Name " + ward.getName() + " In " + delimitationTown.getTownship().getTownshipName() + " town");
			}else if(delimitationWard != null && delimitationWard.size()  == 1){
				delimWardObj = delimitationWard.get(0);
			}else{
				DelimitationWard delimWardObject = new DelimitationWard();
				delimWardObject.setDelimitationConstituencyTown(delimitationTown);
				delimWardObject.setWard(wardObj);
				if(hasBlocks)
					delimWardObject.setIsPartial(1L);
				
				delimWardObject = delimitationWardDAO.save(delimWardObject);
				
				//check and save delimitation blocks for wards
				if(blocksInWards != null && !blocksInWards.isEmpty() && blocksInWards.containsKey(wardObj.getWardName())){
					
					List<SelectOptionVO> blocksList = blocksInWards.get(wardObj.getWardName());
					checkAndSaveDelimitationBlocksInAWard(wardObj.getWardId(),wardObj.getWardName(),delimWardObject,blocksList);
					
				}
			}
		}
	}
	
	*//**
	 * 
	 * @param ward
	 * @param blocksList
	 *//*
	private void checkAndSaveBlocksInAWard(Ward ward, List<SelectOptionVO> blocksList) throws Exception{
		
		if(blocksList != null && blocksList.size() > 0){
			
			for(SelectOptionVO block:blocksList){
				
				
				Boolean isAvailable = checkForBlockExistence(ward.getWardId(),block.getName().trim());
				
				if(!isAvailable){
					Block blockObj = new Block();
					blockObj.setBlockName(block.getName().trim());
					blockObj.setWard(ward);
					
					blockDAO.save(blockObj);
				}
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	*//**
	 * Check Wheather block exists or not
	 *//*
	private Boolean checkForBlockExistence(Long wardId,String blockName) throws Exception{
		
		Boolean isAvailable = false;
		List blockLst = blockDAO.findByWardIdAndBlockName(wardId, blockName);
		
		if(blockLst != null && blockLst.size() > 0){
			
			Object count = (Object)blockLst.get(0);
			Long countVal = (Long)count;
			
			if(countVal > 0)
				isAvailable = true;
		}
	 return isAvailable;
	}
	
	*//**
	 * 
	 * @param wardId
	 * @param delimWardObj
	 * @param blocksList
	 * @throws Exception
	 *//*
	private void checkAndSaveDelimitationBlocksInAWard(Long wardId,String wardName,DelimitationWard delimWardObj,List<SelectOptionVO> blocksList) throws Exception{
		
		if(!wardId.equals(0L) && delimWardObj != null && blocksList != null && blocksList.size() > 0){
			
			for(SelectOptionVO block:blocksList){
				
				List<Block> blocksLst = blockDAO.findBlockByWardIdAndBlockName(wardId, block.getName().trim());
				Block blockObj = null;
				
				if(blocksLst == null || blocksLst.size() > 1){
					throw new Exception("Zero Or More Blocks Exists By Name :" + block + " In Ward " + wardName);
				}
				else if(blocksLst.size() == 1){
					blockObj = blocksLst.get(0);
				}
				
				List<DelimitationBlock> delimBlocks = delimitationBlockDAO.findByDelimitationWardIdAndBlock(delimWardObj.getDelimitationWardId(), blockObj.getBlockId());
				
				if(delimBlocks == null || delimBlocks.size() > 1){
					throw new Exception("Zero Or More Delimitation Blocks Exists By Name :" + block + " In Ward " + wardName);
				}else if(delimBlocks.size() == 1){
					
					log.debug("Delimitation Blocks Exists For Block :" + block + " In Ward " + wardName);
				}else{
					
					DelimitationBlock delimBlock = new DelimitationBlock();
					delimBlock.setBlock(blockObj);
					delimBlock.setDelimitationWard(delimWardObj);
					delimBlock.setDescription("");
					
					delimitationBlockDAO.save(delimBlock);
					
				}
			}
		}
	}
	
	*//**
	 * Check and Save Delimitation Constituency Town Mappings 
	 * @param stateObj
	 * @param districtObj
	 * @param constituencyObj
	 * @param townsMap
	 * @param delimitationConstituency
	 *//*
	private void checkAndSaveDelimitationConstituencyTownsAndSubRegions(State stateObj,District districtObj,Constituency constituencyObj,
			Map<String,MandalSubRegionsVO> townsMap,DelimitationConstituency delimitationConstituency) throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("Started To Save Delimitation Constituency Town Details ..");
		}
		
		if(!townsMap.isEmpty() && townsMap.size() > 0){
			
			Set<String> townNames = townsMap.keySet();
			for(String town:townNames){
				
				//Check for Town
				MandalSubRegionsVO townData = townsMap.get(town);
				List<Township> townshipsList = townshipDAO.findTownsByTownNameAndTypeAndDistrict(town, "T", districtObj.getDistrictId());
				Township townshipObj = null;
				
				if(townshipsList != null && townshipsList.size() > 1){
					
					townshipsList = townshipDAO.findTownsByTownNameAndTypeAndDistrict(town, "T", districtObj.getDistrictId(),townData.getMandalForTown());
					
					if(townshipsList != null && townshipsList.size() > 1){
						throw new Exception("More Than One Town Exists With Name " + town +" In " + districtObj.getDistrictName() + " District");
					}else if(townshipsList != null && townshipsList.size() == 1){
						townshipObj = townshipsList.get(0);
					}
					
				}else if(townshipsList != null && townshipsList.size() == 1){
					townshipObj = townshipsList.get(0);
				}else{
					throw new Exception("No Town Exists With Name " + town +" In " + districtObj.getDistrictName() + " District");
				}
				
				//Check And Insert Delimitation Constituency Town
				List<DelimitationConstituencyTown> delimConstiTownList = delimitationConstituencyTownDAO.findByDelimitationConstituencyAndTownship(delimitationConstituency.getDelimitationConstituencyID(), townshipObj.getTownshipId());
				DelimitationConstituencyTown delimitationTown = null;
				
				if(delimConstiTownList != null && delimConstiTownList.size() > 1){
					throw new Exception("More Than One Delimitation Constituency Town Exists for " + town +" Mandal In " + districtObj.getDistrictName() + " District");
				}else if(delimConstiTownList != null && delimConstiTownList.size() == 1){
					delimitationTown = delimConstiTownList.get(0);
				}else{
					
					DelimitationConstituencyTown delimTown = new DelimitationConstituencyTown();
					delimTown.setDelimitationConstituency(delimitationConstituency);
					delimTown.setTownship(townshipObj);
					Boolean isPartial = townData.getIsPartial();
					if(isPartial)
						delimTown.setIsPartial(1L);
					else
						delimTown.setIsPartial(0L);
					delimitationTown = delimitationConstituencyTownDAO.save(delimTown);
				}
				
				//check for villages
				if(townData.getVillagesList() != null && townData.getVillagesList().size() > 0){
					saveDelimitationWardDetails(delimitationTown,townData.getVillagesList(),townData.getBlocksInWards());
				}
							
			}
			
		}
		
	}
	*//**
	 * check for State Exists with given name
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param stateName
	 * @param stateObj
	 * @param districtObj
	 *//*
	private Boolean checkForState(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String stateName) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check For State ..");
		
		Boolean isState = false;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No State Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No State Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || levelCell.getCellType() != HSSFCell.CELL_TYPE_STRING || !levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.STATE))
			return false;
		
		//check wheather cell value is STATE 
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.STATE)){
			
			isState = true;
		}
	 return isState;
	}
	
	*//**
	 * 
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param stateName
	 * @return
	 *//*
	private State getStateObjectByStateName(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String stateName) throws Exception{
		
		State stateObj = null;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No State Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No State Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.STATE)){
			
			stateName = regionNameCell.getRichStringCellValue().toString();
			List<State> state = stateDAO.findByStateName(stateName);
			
			if(state == null || state.size() > 1){
				throw new Exception("No State Or More Than One State Exists With Name " + stateName);
			}
			stateObj = state.get(0);
						
			if(log.isInfoEnabled())
				log.info(" Mappings Upload Started For State " +stateName);
		}
		
	 return stateObj;
	}
	
		
	*//**
	 * check for District Exists with given name
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param districtName
	 * @param districtObj
	 *//*
	public Boolean checkForDistrict(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String districtName,District districtObj) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check For District ..");
		
		Boolean isDistrict = false;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No District Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No District Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || levelCell.getCellType() != HSSFCell.CELL_TYPE_STRING || !levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.DISTRICT))
			return false;
		
		//check wheather cell value is DISTRICT 
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.DISTRICT)){
			
			isDistrict = true;
		}
	 return isDistrict;
	}
	
	*//**
	 * 
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param districtName
	 * @param stateId
	 * @return
	 *//*
	private District getDistrictObjectByDistrictNameAndState(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String districtName,Long stateId) throws Exception{
		
		District districtObj = null;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No District Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No District Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.DISTRICT)){
			
			districtName = regionNameCell.getRichStringCellValue().toString();
			List<District> district = districtDAO.getDistrictIDByStateIDAndDistrictName(stateId,districtName);
			
			if(district == null || district.size() > 1){
				throw new Exception("No District Or More Than One District Exists With Name" + districtName);
			}
			districtObj = district.get(0);
						
			if(log.isInfoEnabled())
				log.info(" Mappings Upload Started For State " +districtName);
		
		}
	 return districtObj;
	}
	
	*//**
	 * check for Constituency Exists with given name
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param constituencyName
	 * @param constituencyObj
	 * @return true if it is constituency cell else returns false
	 * @throws Exception
	 *//*
	private Boolean checkForConstituency(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String constituencyName,Constituency constituencyObj,Long districtId,Long stateId) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Check For Constituency ..");
		
		Boolean isConstituency = false;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No Constituency Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No Constituency Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || levelCell.getCellType() != HSSFCell.CELL_TYPE_STRING || !levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.CONSTITUENCY))
			return false;
		
		//check wheather cell value is Constituency 
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			
			isConstituency = true;
		}
		
	 return isConstituency;
	}
	
	*//**
	 * 
	 * @param levelColumnNo
	 * @param regionColumnNo
	 * @param row
	 * @param constituencyName
	 * @param districtId
	 * @param stateId
	 * @return
	 *//*
	private Constituency getConstituencyByConstituencyNameAndDistrictId(Integer levelColumnNo,Integer regionColumnNo,HSSFRow row,String constituencyName,Long districtId,Long stateId) throws Exception{
		

		Constituency constituencyObj = null;
		HSSFCell levelCell      = row.getCell(levelColumnNo);
		HSSFCell regionNameCell = row.getCell(regionColumnNo);
		
		if(levelCell == null){
			throw new Exception("No Constituency Level Is Mentioned At Column " + levelColumnNo + " In Row " + row.getRowNum());
		}
		
		if(regionNameCell == null){
			throw new Exception("No Constituency Name Is Mentioned At Column " + regionColumnNo + " In Row " + row.getRowNum());
		}
		
		if(levelCell.getCellType() != HSSFCell.CELL_TYPE_BLANK && levelCell.getCellType() == HSSFCell.CELL_TYPE_STRING 
				&& levelCell.getRichStringCellValue().toString().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			
			constituencyName = regionNameCell.getRichStringCellValue().toString();
			List<Constituency> constituency = constituencyDAO.findByConstituencyNameAndDistrictId(constituencyName, districtId,IConstants.ASSEMBLY_ELECTION_TYPE);
			
			if(constituency == null || constituency.size() > 1){
				throw new Exception("No Constituency Or More Than One Constituency Exists By Name " + constituencyName);
			}
			
			constituencyObj = constituency.get(0);
		}
		
	 return constituencyObj;
	}
	
	
    public static void main(String args[]){
		
        try{
			
			InputStream myxls = new FileInputStream("censusMapping(Nalgonda).xls");
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(0);  
			
			System.out.println(" Last Row No :" + sheet.getLastRowNum());
			
			// first sheet
			HSSFRow row     = sheet.getRow(5);         // first row
			HSSFCell cell1   = row.getCell(0);        // first cell
			HSSFCell cell2   = row.getCell(1);       // second cell
			HSSFCell cell3   = row.getCell(2);      // third cell
			HSSFCell cell4   = row.getCell(3);     // fourth cell
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Exception Raised :"+ ex);
		}
		finally{
			System.out.println("Entered Into Finally ..");
		}
	}
	
*/}
