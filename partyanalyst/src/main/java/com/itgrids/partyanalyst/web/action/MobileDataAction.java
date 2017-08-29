package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MobileAppUserDetailsVO;
import com.itgrids.partyanalyst.dto.MobileVO;
import com.itgrids.partyanalyst.dto.PollManagementSummaryVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class MobileDataAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 8560512792729556421L;
	
	private String task;
	private JSONObject jObj;
	private HttpServletRequest request;
	private HttpSession session;
	private IMobileService mobileService;
	private ResultStatus resultStatus;
	public static final Logger LOG = Logger.getLogger(MobileDataAction.class);
	private List<SelectOptionVO> constituencyList,userList,superAdminUsersList,pcconstituencyList,districts,mandals;
	private String filePath;
	private IRegistrationService registrationService;
	private EntitlementVO allRegisteredUsersData;
	private Long populateID;
	private MobileVO mobileVo;
	private EntitlementsHelper entitlementsHelper;
	private MobileAppUserDetailsVO					mobileAppUserDetailsVO;
	private List<TabDetailsVO> tabDetailsVOList;
	private String surveyDate;
	private String divisonId;
	private String userId;
	
	private Long				divisionId;
	private String				fromDate;
	private String				toDate;
	private String				division;
	private List<IdNameVO>		idNamevoList;
	private PollManagementVO	pollManagementVO;
	private List<PollManagementVO> pollManagementVOList;
	private List<PollManagementSummaryVO> pollManagementSummaryVOList;
	private List<LocationWiseBoothDetailsVO> idNamevoList1;
	
	
	public List<IdNameVO> getIdNamevoList() {
		return idNamevoList;
	}

	public void setIdNamevoList(List<IdNameVO> idNamevoList) {
		this.idNamevoList = idNamevoList;
	}

	public List<PollManagementVO> getPollManagementVOList() {
		return pollManagementVOList;
	}

	public void setPollManagementVOList(List<PollManagementVO> pollManagementVOList) {
		this.pollManagementVOList = pollManagementVOList;
	}

	public PollManagementVO getPollManagementVO() {
		return pollManagementVO;
	}

	public void setPollManagementVO(PollManagementVO pollManagementVO) {
		this.pollManagementVO = pollManagementVO;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	public String getDivisonId() {
		return divisonId;
	}

	public void setDivisonId(String divisonId) {
		this.divisonId = divisonId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<TabDetailsVO> getTabDetailsVOList() {
		return tabDetailsVOList;
	}

	public void setTabDetailsVOList(List<TabDetailsVO> tabDetailsVOList) {
		this.tabDetailsVOList = tabDetailsVOList;
	}
	
	public MobileAppUserDetailsVO getMobileAppUserDetailsVO() {
		return mobileAppUserDetailsVO;
	}

	public void setMobileAppUserDetailsVO(
			MobileAppUserDetailsVO mobileAppUserDetailsVO) {
		this.mobileAppUserDetailsVO = mobileAppUserDetailsVO;
	}
	public List<SelectOptionVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<SelectOptionVO> mandals) {
		this.mandals = mandals;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public MobileVO getMobileVo() {
		return mobileVo;
	}

	public void setMobileVo(MobileVO mobileVo) {
		this.mobileVo = mobileVo;
	}

	public List<SelectOptionVO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}

	public List<SelectOptionVO> getPcconstituencyList() {
		return pcconstituencyList;
	}

	public void setPcconstituencyList(List<SelectOptionVO> pcconstituencyList) {
		this.pcconstituencyList = pcconstituencyList;
	}

	public Long getPopulateID() {
		return populateID;
	}

	public void setPopulateID(Long populateID) {
		this.populateID = populateID;
	}

	public List<SelectOptionVO> getSuperAdminUsersList() {
		return superAdminUsersList;
	}

	public void setSuperAdminUsersList(List<SelectOptionVO> superAdminUsersList) {
		this.superAdminUsersList = superAdminUsersList;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}

	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}

	public List<SelectOptionVO> getUserList() {
		return userList;
	}

	public void setUserList(List<SelectOptionVO> userList) {
		this.userList = userList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
	this.request = request;	
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<PollManagementSummaryVO> getPollManagementSummaryVOList() {
		return pollManagementSummaryVOList;
	}

	public void setPollManagementSummaryVOList(
			List<PollManagementSummaryVO> pollManagementSummaryVOList) {
		this.pollManagementSummaryVOList = pollManagementSummaryVOList;
	}
	
	public List<LocationWiseBoothDetailsVO> getIdNamevoList1() {
		return idNamevoList1;
	}

	public void setIdNamevoList1(List<LocationWiseBoothDetailsVO> idNamevoList1) {
		this.idNamevoList1 = idNamevoList1;
	}

	public String execute()
	{
		try{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null)
    	  return ERROR;
		
        if(request.getRequestURL().toString().contains("localhost"))
        	filePath = "/PartyAnalyst/sqlite_dump.sql";
        else
        	filePath = "/sqlite_dump.sql";
        
        constituencyList = mobileService.getConstituencyList();
        if(constituencyList != null)
         constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
        allRegisteredUsersData = registrationService.getAllRegisterdUsers();
        superAdminUsersList = mobileService.getSuperAdminMobileAppUsers();
        pcconstituencyList = mobileService.getPCConstituencyList();
		}
		
		catch (Exception e) {
		 e.printStackTrace();
		 LOG.error("Exception Occured in execute() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
		 jObj = new JSONObject(getTask());
		 
		 if(jObj.getString("task").equalsIgnoreCase("createDataDump"))
		 {
			 String path = IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB";
			 JSONArray arr = jObj.getJSONArray("userData");
			 JSONObject jSONObject= arr.getJSONObject(0);
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setFirstName(jSONObject.getString("firstName"));
			 regVo.setLastName(jSONObject.getString("lastName"));
			 regVo.setGender(jSONObject.getString("gender"));
			 regVo.setUserName(jSONObject.getString("userName"));
			 regVo.setPassword(jSONObject.getString("password"));
			 regVo.setUniqueCode(jSONObject.getString("uniqueCode"));// uniqueCode
			 regVo.setAppId(jSONObject.getString("appId"));
			 regVo.setMobile(jSONObject.getString("deviceId"));
			 regVo.setAddress(jSONObject.getString("macAddressId"));
			 regVo.setRegistrationID(jSONObject.getLong("userId"));
			 regVo.setMobile(jSONObject.getString("mobileNo"));
			 regVo.setEmail(jSONObject.getString("email"));
			 regVo.setSuperAdminId(jSONObject.getLong("superAdmin"));
			 regVo.setPublicationDateId(jSONObject.getLong("publicationId"));
			 resultStatus = mobileService.createDataDumpFileForSelectedConstituency(jObj.getLong("constituencyId"),path,regVo);
		 }
		 
		 else if(jObj.getString("task").equalsIgnoreCase("createDataDumpForCMS"))
		 {
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setConstituencyId(jObj.getLong("constituencyId"));
			 regVo.setPublicationDateId(jObj.getLong("publicationId"));
			 regVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB");
			 resultStatus = mobileService.createDataDumpFileForAConstituency(regVo);
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("createDataDumpForCMSPC"))
		 {
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setConstituencyId(jObj.getLong("constituencyId"));
			 regVo.setPublicationDateId(jObj.getLong("publicationId"));
			 regVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB");
			 resultStatus = mobileService.createDataDumpFileForAParliamnetConstituency(regVo);
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("createDataDumpForSURVEYPC"))
		 {
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setConstituencyId(jObj.getLong("constituencyId"));
			 regVo.setPublicationDateId(jObj.getLong("publicationId"));
			 regVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB_SURVEY");
			 resultStatus = mobileService.createSurveySqliteFileForAParliamnetConstituency(regVo);
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("saveSuperAdmin"))
		 {
			 populateID = mobileService.saveSuperAdminInfoInMobileAppUser(jObj.getString("userName"),jObj.getString("password"),jObj.getString("uniqueCode")); 
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("getSuperAdminUsersList"))
		 {
			 superAdminUsersList = mobileService.getSuperAdminMobileAppUsers();
		 }
		/* else if(jObj.getString("task").equalsIgnoreCase("saveMobileAppUserDetails"))
		 {
			 JSONArray arr = jObj.getJSONArray("userData");
			 JSONObject jSONObject= arr.getJSONObject(0);
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setFirstName(jSONObject.getString("firstName"));
			 regVo.setLastName(jSONObject.getString("lastName"));
			 regVo.setGender(jSONObject.getString("gender"));
			 regVo.setUserName(jSONObject.getString("userName"));
			 regVo.setPassword(jSONObject.getString("password"));
			 regVo.setUniqueCode(jSONObject.getString("uniqueCode"));// uniqueCode
			 regVo.setAppId(jSONObject.getString("appId"));
			 regVo.setMobile(jSONObject.getString("deviceId"));
			 regVo.setAddress(jSONObject.getString("macAddressId"));
			
			 resultStatus = mobileService.saveUserData(regVo);
		 }*/
		 else if(jObj.getString("task").equalsIgnoreCase("createDataDumpForCadre"))
		 {
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setConstituencyId(jObj.getLong("constituencyId"));
			 regVo.setPublicationDateId(jObj.getLong("publicationId"));
			 regVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB_CADRE");
			 resultStatus = mobileService.createCadreDataSqliteFileForAParliamnetConstituency(regVo);
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("createDataDumpForCadre2016"))
		 {
			 RegistrationVO regVo = new RegistrationVO();
			 regVo.setConstituencyId(jObj.getLong("constituencyId"));
			 regVo.setPublicationDateId(jObj.getLong("publicationId"));
			 regVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL+"SQLITE_DB_CADRE_2016");
			 resultStatus = mobileService.create2016CadreDataSqliteFileForAParliamnetConstituency(regVo);
		 }
			
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	public String sendSmsToMobileAppUserAccessKey()
	{
		try{
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long userID = user.getRegistrationID();
			resultStatus = mobileService.sendSmsToMobileAppUser(jObj.getString("mobileNo"),jObj.getLong("mobileAppuserId"),jObj.getString("accessKey"),userID,jObj.getString("type"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getMobileAppUsers()
	{
		try{
			jObj = new JSONObject(getTask());
			userList= mobileService.getMobileAppUsers();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	public String ivrExecute()
	{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
        if(user == null)
    	  return ERROR;
        List<String> entitlements = null;
		if(user.getEntitlements() != null && user.getEntitlements().size()>0){
			entitlements = user.getEntitlements();
			if(user == null && !entitlements.contains(IConstants.IVR_MOBILE_NUMBERS_RETRIVAL)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.IVR_MOBILE_NUMBERS_RETRIVAL)){
				return ERROR;
			}
       /* if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.IVR_MOBILE_NUMBERS_RETRIVAL))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.IVR_MOBILE_NUMBERS_RETRIVAL))
			return ERROR;*/
        if(request.getRequestURL().toString().contains("localhost"))
        	filePath = "/PartyAnalyst/mobileNumbers.txt";
        else
        	filePath = "/mobileNumbers.txt";
		}
        return Action.SUCCESS;
	}
	public String getIvrMobileNumbers()
	{
		try{
			List<String> checkedLevels = new ArrayList<String>();
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("locationIds");
			boolean questions = jObj.getBoolean("questions");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++)
				locationIds.add(new Long(arr.get(i).toString()));	
			Long fileFormatVal = jObj.getLong("fileFormat");
			JSONArray questionOptionsArray = jObj.getJSONArray("queOptionsArr");
			JSONArray levelsarr = jObj.getJSONArray("levelsarr");
			for(int j=0;j<levelsarr.length();j++)
				checkedLevels.add(levelsarr.get(j).toString());
			//mobileVo = mobileService.getIvrMobileNumbers(jObj.getLong("scopeId"),locationIds,fileFormatVal,jObj.getInt("maxIndex"),jObj.getBoolean("multipleFileCheck"),jObj.getInt("noOfFiles"));
			if(!questions)
			mobileVo = mobileService.getMobileNumbersByLocations(jObj.getLong("scopeId"),locationIds,fileFormatVal,jObj.getInt("maxIndex"),jObj.getInt("checkedTypeVal"),jObj.getInt("noOfFiles"),checkedLevels);
			else
			{
				 Random rand = new Random();
					int randNO = rand.nextInt(4);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String path = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator+"mobile_numbers";
				
				File destDir = new File(path + pathSeperator+date+randNO+"QOpt");
				destDir.mkdir();
				for(int i=0;i<questionOptionsArray.length();i++)
				{
					JSONObject JSONObject = questionOptionsArray.getJSONObject(i);
					String question = JSONObject.getString("question");
					int optionsCount = JSONObject.getInt("optionsCnt");
					int maxIndex = jObj.getInt("maxIndex") /(questionOptionsArray.length() * optionsCount);
					int opt = 0;
					for(int j=0;j<optionsCount;j++)
					{
						opt++;
						
					 mobileVo = mobileService.getMobileNumbersByLocations(jObj.getLong("scopeId"),locationIds,fileFormatVal,maxIndex,1,0,checkedLevels);//single file
					 File oldName = new File(mobileVo.getOptionFilePath());
					 File newName = null;
					 if(fileFormatVal == 1)
						 oldName = new File(path + pathSeperator+date+randNO+pathSeperator+question+"Opt"+opt+".csv");
				      else
				      newName = new File(path + pathSeperator+date+randNO+pathSeperator+question+"Opt"+opt+".txt"); 
					  oldName.renameTo(new File(path + pathSeperator+date+randNO+"QOpt" +pathSeperator+ newName.getName()));
					
					}
				}
				 
				 FileOutputStream fos = new FileOutputStream(path + pathSeperator+date+randNO+"QOpt.zip");
				 ZipOutputStream zos = new ZipOutputStream(fos);
			     System.gc();
				 for(File rf : destDir.listFiles())
				 addToZip(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
				 mobileVo.setStatus("/mobile_numbers/"+date+randNO+"QOpt.zip");
				 
			}
					
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	 public  void addToZip(String fileName, ZipOutputStream zos)
  	 {
			try {
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(fileName);
				zos.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0){
					zos.write(bytes, 0, length);
				}
				zos.closeEntry();
				fis.close();
			} catch (Exception e) {
				LOG.error("Exception Occured in addToZipFile() Method ");
			}
			
		}
	public String getDistrictList()
	{
		try{
		districts = mobileService.getDistrictsList(1l);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencies()
	{
		try{
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("districtIds");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++)
				locationIds.add(new Long(arr.get(i).toString()));
			
				constituencyList = mobileService.getConstituencyList(locationIds);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getMandalsList()
	{
		try{
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("districtIds");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++)
				locationIds.add(new Long(arr.get(i).toString()));
				mandals = mobileService.getTehsilList(locationIds);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getParliamentConstituencies()
	{
		try{
			jObj = new JSONObject(getTask());
			pcconstituencyList = mobileService.getpcconstituencyList(jObj.getLong("regionId"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String getLocationWiseMobileNumbersCount()
	{
		try{
			jObj = new JSONObject(getTask());
			mobileVo = mobileService.getLocationWiseMobileNumbersCountByRegionAndScope(jObj.getLong("scopeId"),jObj.getString("regionType"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String resetAllMobileNos()
	{
		try{
			jObj = new JSONObject(getTask());
			resultStatus = mobileService.resetAllMobileNos();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/*public String getUserWiseDivisionSummary(){

		try{
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user==null){
				mobileAppUserDetailsVO = new MobileAppUserDetailsVO();
				mobileAppUserDetailsVO.setErrorCode(1);
				mobileAppUserDetailsVO.setStatusMsg("Session Expired, Please Check");
				return Action.ERROR;
			}
			
			jObj = new JSONObject(getTask());
			JSONArray usersArr = jObj.getJSONArray("usersArr");
			List<String> usersList = new ArrayList<String>();
			if(usersArr != null && usersArr.length() > 0){
				for (int i = 0; i < usersArr.length(); i++) {
					String userStr = usersArr.getString(i);
					usersList.add(userStr);
				}
			}
			
			mobileAppUserDetailsVO = mobileService.getUserWiseDivisionSummary(jObj.getLong("locationId"), jObj.getString("locationType"), jObj.getString("startDate"), jObj.getString("endDate"),usersList);
			mobileAppUserDetailsVO.setErrorCode(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}*/
	
	/*public String getmobileAppDivisionWiseUsers(){
		try {
			// LOGIN 
			RegistrationVO user = (RegistrationVO) request.getSession().getAttribute("USER");
			if(user==null){
				return Action.ERROR;
			}
			Long userId = user.getRegistrationID();
			String accessValue = user.getAccessValue();
			
			idNamevoList = mobileService.getAssignedWardsByUser(userId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getmobileAppDivisionWiseUsers()", e);
		}
		return Action.SUCCESS;
	}*/
	
	/*public String showMapForMobileAppUserVoter(){
		try {
			jObj = new JSONObject(getTask());
			
			List<String> datesStrList = new ArrayList<String>(0);
			JSONArray jArray = jObj.getJSONArray("datesArr");
			
			for (int i = 0; i < jArray.length(); i++) 
			{
				datesStrList.add(jArray.getString(i));
			}
			
			tabDetailsVOList = mobileService.showMapForMobileAppUserVoter(jObj.getLong("userId"),jObj.getLong("divisonId"),datesStrList);
		} catch (Exception e) {
			LOG.error("Exception riased at showMapForMobileAppUserVoter", e);
		}
		
		return Action.SUCCESS;
	}*/
	
	public String showGoogleMapDetails()
	{
		return Action.SUCCESS;
	}
	
	public String getUserTrackingDetails(){
		try {
			jObj = new JSONObject(getTask());
			
			tabDetailsVOList = mobileService.getUserTrackingDetails(jObj.getLong("userId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised at getUserTrackingDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String overAllPollManagementSummary(){
		try {
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("locationIds");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++){
				locationIds.add(new Long(arr.get(i).toString()));
			}
			
			pollManagementVO = mobileService.overAllPollManagementSummary(locationIds);
		} catch (Exception e) {
			LOG.error("Exception raised at overAllPollManagementSummary", e);
		}
		return Action.SUCCESS;
	}
	
	public String overAllPollManagementSummaryByDivisionOrWard(){
		try {
			jObj = new JSONObject(getTask());
			pollManagementVO = mobileService.overAllPollManagementSummaryByDivisionOrWard(jObj.getLong("divisonId"));
		} catch (Exception e) {
			LOG.error("Exception raised at overAllPollManagementSummaryByDivisionOrWard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getNotYetPolledMembers(){
	    try{
	      jObj = new JSONObject(getTask());
	      
	      pollManagementVOList = mobileService.getNotYetPolledMembers(jObj.getString("searchType"),jObj.getLong("boothId") );
	    
	    }catch (Exception e) {
	      LOG.error("Exception raised at getNotYetPolledMembers", e);
	    }
	    return Action.SUCCESS;
	  }
	
	public String getAllDivisons(){
		try {
			RegistrationVO user = (RegistrationVO) request.getSession().getAttribute("USER");
			if(user==null){
				return Action.ERROR;
			}
			Long userId = user.getRegistrationID();
			
			idNamevoList = mobileService.getAssignedWardsByUser(userId);	
		} catch (Exception e) {
			LOG.error("Exception raised at getAllDivisons", e);
		}
		return Action.SUCCESS;
	}
	
	public String divisonVotingAcitivty(){
		try {
			jObj = new JSONObject(getTask());
			List<Long> list = new ArrayList<Long>(0);
			list.add(jObj.getLong("divisonId"));
			pollManagementSummaryVOList = mobileService.divisionWiseVotingActivity(list);
		} catch (Exception e) {
			LOG.error("Exception raised at divisonVotingAcitivty", e);
		}
		return Action.SUCCESS;
	}
	public String divisionWiseVotingActivity(){
		try {
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("locationIds");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++){
				locationIds.add(new Long(arr.get(i).toString()));
			}
			
			pollManagementSummaryVOList= mobileService.divisionWiseVotingActivity(locationIds);
		} catch (Exception e) {
			LOG.error("Exception raised at divisionWiseVotingActivity", e);
		}
		return Action.SUCCESS;
	}
	public String boothWiseVotingActivity(){
		try {
			jObj = new JSONObject(getTask());
			pollManagementSummaryVOList= mobileService.boothWiseVotingActivity(jObj.getLong("wardId"));
		} catch (Exception e) {
			LOG.error("Exception raised at divisionWiseVotingActivity", e);
		}
		return Action.SUCCESS;
	}
	public String getUserWiseDivisionSummary(){

		try{
			/*RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user==null){
				mobileAppUserDetailsVO = new MobileAppUserDetailsVO();
				mobileAppUserDetailsVO.setErrorCode(1);
				mobileAppUserDetailsVO.setStatusMsg("Session Expired, Please Check");
				return Action.ERROR;
			}*/
			
			jObj = new JSONObject(getTask());
			JSONArray usersArr = jObj.getJSONArray("usersArr");
			List<String> usersList = new ArrayList<String>();
			if(usersArr != null && usersArr.length() > 0){
				for (int i = 0; i < usersArr.length(); i++) {
					String userStr = usersArr.getString(i);
					usersList.add(userStr);
				}
			}
			
			mobileAppUserDetailsVO = mobileService.getUserWiseDivisionSummary(jObj.getLong("locationId"), jObj.getLong("levelId"), jObj.getString("startDate"), jObj.getString("endDate"),jObj.getLong("publicationDateId"),jObj.getLong("electionYearId"),usersList);
			mobileAppUserDetailsVO.setErrorCode(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String getmobileAppDivisionWiseUsers(){
		try {
			// LOGIN 
			RegistrationVO user = (RegistrationVO) request.getSession().getAttribute("USER");
			if(user==null){
				return Action.ERROR;
			}
			Long userId = user.getRegistrationID();
			String accessValue = user.getAccessValue();
			
			idNamevoList1 = mobileService.getAssignedWardsByUser(accessValue,userId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getmobileAppDivisionWiseUsers()", e);
		}
		return Action.SUCCESS;
	}
	
	public String showMapForMobileAppUserVoter(){
		try {
			jObj = new JSONObject(getTask());
			
			List<String> datesStrList = new ArrayList<String>(0);
			JSONArray jArray = jObj.getJSONArray("datesArr");
			
			for (int i = 0; i < jArray.length(); i++) 
			{
				datesStrList.add(jArray.getString(i));
			}
			
			tabDetailsVOList = mobileService.showMapForMobileAppUserVoter(jObj.getLong("userId"),jObj.getLong("locationId"),jObj.getLong("levelId"),datesStrList);
		} catch (Exception e) {
			LOG.error("Exception riased at showMapForMobileAppUserVoter", e);
		}
		
		return Action.SUCCESS;
	}
}
