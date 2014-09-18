package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.MobileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.model.Job;
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
      /* if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.IVR_MOBILE_NUMBERS_RETRIVAL))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.IVR_MOBILE_NUMBERS_RETRIVAL))
			return ERROR;*/
        if(request.getRequestURL().toString().contains("localhost"))
        	filePath = "/PartyAnalyst/mobileNumbers.txt";
        else
        	filePath = "/mobileNumbers.txt";
      
        return Action.SUCCESS;
	}
	public String getIvrMobileNumbers()
	{
		try{
			jObj = new JSONObject(getTask());
			JSONArray arr = jObj.getJSONArray("locationIds");
			boolean questions = jObj.getBoolean("questions");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<arr.length();i++)
				locationIds.add(new Long(arr.get(i).toString()));	
			Long fileFormatVal = jObj.getLong("fileFormat");
			JSONArray questionOptionsArray = jObj.getJSONArray("queOptionsArr");
			//mobileVo = mobileService.getIvrMobileNumbers(jObj.getLong("scopeId"),locationIds,fileFormatVal,jObj.getInt("maxIndex"),jObj.getBoolean("multipleFileCheck"),jObj.getInt("noOfFiles"));
			if(!questions)
			mobileVo = mobileService.getMobileNumbersByLocations(jObj.getLong("scopeId"),locationIds,fileFormatVal,jObj.getInt("maxIndex"),jObj.getInt("checkedTypeVal"),jObj.getInt("noOfFiles"));
			else
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String path = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator+"mobile_numbers";
				File sourceDir = new File(path + pathSeperator+date);
				File destDir = new File(path + pathSeperator+date+"QOpt");
				destDir.mkdir();
				for(int i=0;i<questionOptionsArray.length();i++)
				{
					JSONObject JSONObject = questionOptionsArray.getJSONObject(i);
					int question = JSONObject.getInt("question");
					int optionsCount = JSONObject.getInt("optionsCnt");
					int maxIndex = jObj.getInt("maxIndex") / optionsCount;
					int opt = 0;
					for(int j=0;j<optionsCount;j++)
					{
						opt++;
						
					 mobileVo = mobileService.getMobileNumbersByLocations(jObj.getLong("scopeId"),locationIds,fileFormatVal,maxIndex,1,0);//single file
					 File oldName = new File(mobileVo.getOptionFilePath());
					 File newName = null;
					 if(fileFormatVal == 1)
						 oldName = new File(path + pathSeperator+date+pathSeperator+question+opt+".csv");
				      else
				      newName = new File(path + pathSeperator+date+pathSeperator+question+opt+".txt"); 
					  oldName.renameTo(new File(path + pathSeperator+date+"QOpt" +pathSeperator+ newName.getName()));
					
					}
				}
				 
				 FileOutputStream fos = new FileOutputStream(path + pathSeperator+date+"QOpt.zip");
				 ZipOutputStream zos = new ZipOutputStream(fos);
			     System.gc();
				 for(File rf : destDir.listFiles())
				 addToZip(rf.getAbsolutePath(), zos);
				 zos.close();
				 fos.close();
				 mobileVo.setStatus("/mobile_numbers/"+date+"QOpt.zip");
				 
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
}
