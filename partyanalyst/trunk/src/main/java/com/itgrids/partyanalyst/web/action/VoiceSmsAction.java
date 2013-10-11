package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class VoiceSmsAction implements ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(VoiceSmsAction.class);

	private HttpServletRequest request;
	private File recordedVoice;
	private String recordedVoiceContentType;
	private String recordedVoiceFileName;
	private String voiceFileName;
	private String voiceDescription;
	private String userId;
	private String status;
	private List<VoiceSmsResponseDetailsVO> voiceSmsResponseDetails;
	private VoiceSmsResponseDetailsVO responseDtls;
	private List<Long> mobileNumbersList  ;
	private String fileName;
	
	private boolean check;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public List<Long> getMobileNumbersList() {
		return mobileNumbersList;
	}

	public void setMobileNumbersList(List<Long> mobileNumbersList) {
		this.mobileNumbersList = mobileNumbersList;
	}

	public VoiceSmsResponseDetailsVO getResponseDtls() {
		return responseDtls;
	}

	public void setResponseDtls(VoiceSmsResponseDetailsVO responseDtls) {
		this.responseDtls = responseDtls;
	}

	private List<Long> verifiedNumbers;
	private Map<String ,Map<String,Integer>> resultMap ;
	private JSONObject jObj;
	private String task;
	private  List<VoiceSmsResponseDetailsVO> responseDetailsList;
	private Map<String ,String> casteMap;

	private String fromDate;
    private String toDate;
    
    private SMSSearchCriteriaVO votersDetails; 
    private CadreManagementService cadreManagementService;
    private ResultStatus result;
	


	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public SMSSearchCriteriaVO getVotersDetails() {
		return votersDetails;
	}

	public void setVotersDetails(SMSSearchCriteriaVO votersDetails) {
		this.votersDetails = votersDetails;
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

	public List<VoiceSmsResponseDetailsVO> getResponseDetailsList() {
		return responseDetailsList;
	}

	public void setResponseDetailsList(
			List<VoiceSmsResponseDetailsVO> responseDetailsList) {
		this.responseDetailsList = responseDetailsList;
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

	
	public Map<String, Map<String, Integer>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Map<String, Integer>> resultMap) {
		this.resultMap = resultMap;
	}

	public List<Long> getVerifiedNumbers() {
		return verifiedNumbers;
	}

	public void setVerifiedNumbers(List<Long> verifiedNumbers) {
		this.verifiedNumbers = verifiedNumbers;
	}

	public List<VoiceSmsResponseDetailsVO> getVoiceSmsResponseDetails() {
		return voiceSmsResponseDetails;
	}

	public void setVoiceSmsResponseDetails(
			List<VoiceSmsResponseDetailsVO> voiceSmsResponseDetails) {
		this.voiceSmsResponseDetails = voiceSmsResponseDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Map<String , String> recordingsDetailsMap;
	
	public Map<String, String> getRecordingsDetailsMap() {
		return recordingsDetailsMap;
	}

	public void setRecordingsDetailsMap(Map<String, String> recordingsDetailsMap) {
		this.recordingsDetailsMap = recordingsDetailsMap;
	}

	public String getVoiceDescription() {
		return voiceDescription;
	}

	public void setVoiceDescription(String voiceDescription) {
		this.voiceDescription = voiceDescription;
	}

	private IVoiceSmsService voiceSmsService;
	
	

	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	public String getVoiceFileName() {
		return voiceFileName;
	}

	public void setVoiceFileName(String voiceFileName) {
		this.voiceFileName = voiceFileName;
	}

	public File getRecordedVoice() {
		return recordedVoice;
	}

	public void setRecordedVoice(File recordedVoice) {
		this.recordedVoice = recordedVoice;
	}

	public String getRecordedVoiceContentType() {
		return recordedVoiceContentType;
	}

	public void setRecordedVoiceContentType(String recordedVoiceContentType) {
		this.recordedVoiceContentType = recordedVoiceContentType;
	}

	public String getRecordedVoiceFileName() {
		return recordedVoiceFileName;
	}

	public void setRecordedVoiceFileName(String recordedVoiceFileName) {
		this.recordedVoiceFileName = recordedVoiceFileName;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
    public Map<String, String> getCasteMap() {
		return casteMap;
	}

	public void setCasteMap(Map<String, String> casteMap) {
		this.casteMap = casteMap;
	}
	
	


	public Date getDate(String dateStr){
		  String[] dateArray =  dateStr.split("-");
		  Calendar cal = Calendar.getInstance(); 
		  cal.set(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[0]));
		  return cal.getTime();
	  }
	
	
	
	
	
	public String execute()
	{
		 HttpSession session = request.getSession();			
		 RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		 
		 if(user == null)
			 return Action.ERROR;
		 
		userId = user.getRegistrationID().toString();
		
		return Action.SUCCESS;
		
	}
	public void prepare() throws Exception {
		
		System.out.println("prepare method called");
	}
	
	public String uploadAudioFile() throws Exception
	{
		try
		{
			  HttpSession session = request.getSession();			
			  RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			  
			  if(user == null)
				  return Action.INPUT;
			
			
			if(recordedVoiceContentType == null)
				return Action.SUCCESS;
			
		
			String recordingName = "";
			
			if(recordedVoiceContentType.contains("mp3"))
				recordingName = voiceFileName +".mp3";
			else
				recordingName = voiceFileName +".wav";				
		
		    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);			
		    String filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + "voice_recordings" + pathSeperator + user.getRegistrationID()+ pathSeperator;
	

			File fileToCreate = new File(filePath, recordingName);
			FileUtils.copyFile(recordedVoice, fileToCreate);
			
			
					
				voiceSmsService.saveUploadedAudioFileDetails(recordingName,user.getRegistrationID(),voiceDescription);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			status = "Error Occured While Uploading Audio File.Please Try Again.....";	
			return Action.ERROR;
		}
		status = "Audio Uploaded Successfully...";			
		return Action.SUCCESS;
	}
	
	public String getAllTheRecordedFilesOfAUser()
	{
		try
		{
			
			HttpSession session = request.getSession();
			
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			
			if(user != null)			
			    recordingsDetailsMap = voiceSmsService.getAllTheRecordedFilesOfAUser(user.getRegistrationID());
			
			else
				return Action.ERROR;
		
		    
		}catch(Exception e)
		{
			e.printStackTrace();
			return Action.ERROR;

			
		}
		return Action.SUCCESS;		
		
	}
	
	public String sendVoiceSms()
	{
		try
		{
			HttpSession session = request.getSession();			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			String requestURL = request.getRequestURL().toString();	
			
			StringBuffer audioFilePath =  new StringBuffer();
			
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);	
			
			 jObj = new JSONObject(getTask());

			if(requestURL.contains(".com"))				
			//	audioFilePath.append(""+user.getRegistrationID()+"/"+request.getParameter("audioFileName"));
				audioFilePath.append(IWebConstants.LIVE_VOICE_RECORDINGS_URL+"/"+user.getRegistrationID()+"/"+jObj.getString("audioFileName"));
			else
				audioFilePath.append("http://122.169.253.134:8080/TDP/voice_recording/test6.wav");
			
			JSONArray mobileNumbersArray = jObj.getJSONArray("selectedMobileNumbers");
			
			List<Long> allMobileNumbers = new ArrayList<Long>();
			
			for(int i=0; i<mobileNumbersArray.length(); i++)
			{
				Long mobileNumber = Long.parseLong(mobileNumbersArray.get(i).toString());
				allMobileNumbers.add(mobileNumber);
			}
			
			
			
			List<Long> cadreMobileNumbers = new ArrayList<Long>();
			List<Long> influenceMobileNumbers = new ArrayList<Long>();
			List<Long> votersMobileNumbers = new ArrayList<Long>();
			
			 
			 JSONArray cadreArray = jObj.getJSONArray("cadreDetails");
			 JSONArray influencePeopleArray = jObj.getJSONArray("influencePeopleDetails");
			 JSONArray votersArray = jObj.getJSONArray("votersDetails");
			 
			 for(int i=0; i<cadreArray.length(); i++)
				 cadreMobileNumbers.add(Long.parseLong(cadreArray.get(i).toString()));
					
			 
			 for(int i=0; i<influencePeopleArray.length(); i++)
				 influenceMobileNumbers.add(Long.parseLong(influencePeopleArray.get(i).toString()));
					
			 
			 for(int i=0; i<votersArray.length(); i++)
				 votersMobileNumbers.add(Long.parseLong(votersArray.get(i).toString()));
			 
			 
			 VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO = new VoiceSmsResponseDetailsVO();
			 
			 voiceSmsResponseDetailsVO.setCadreMobileNumbers(cadreMobileNumbers);
			 voiceSmsResponseDetailsVO.setInfluencePeopleMobileNumbers(influenceMobileNumbers);
			 voiceSmsResponseDetailsVO.setVotersMobileNumbers(votersMobileNumbers);
			 voiceSmsResponseDetailsVO.setAllmobileNumbers(allMobileNumbers);

			
			 //status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,jObj.getString("mobileNumbers"),jObj.getLong("senderMobileNumber"),jObj.getString("description"),voiceSmsResponseDetailsVO);
			 
			//status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,request.getParameter("mobileNumbers"),Long.parseLong(request.getParameter("senderMobileNumber")),request.getParameter("description"));
			 status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,jObj.getString("mobileNumbers"),null,jObj.getString("description"),voiceSmsResponseDetailsVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
		
	}
	
	
	public String sendTextsm()
	{
		try
		{
			
			HttpSession session = request.getSession();			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			
			
			List<SmsVO> cadreMobileNumbers = new ArrayList<SmsVO>();
			List<SmsVO> influenceMobileNumbers = new ArrayList<SmsVO>();
			List<SmsVO> votersMobileNumbers = new ArrayList<SmsVO>();
			List<SmsVO> otherMobileNumbers = new ArrayList<SmsVO>();
			
			 jObj = new JSONObject(getTask());
			 
			 JSONArray cadreArray = jObj.getJSONArray("cadreDetails");
			 JSONArray influencePeopleArray = jObj.getJSONArray("influencePeopleDetails");
			 JSONArray votersArray = jObj.getJSONArray("votersDetails");
			 JSONArray otherNumbersArray = jObj.getJSONArray("otherNumbers");
			 
			 for(int i=0; i<cadreArray.length(); i++)
			 {
				 SmsVO vo = new SmsVO();
				 vo.setMobileNO(cadreArray.get(i).toString());
				 cadreMobileNumbers.add((vo));
					
			 }
			 
			 for(int i=0; i<influencePeopleArray.length(); i++)
			 {

				 SmsVO vo = new SmsVO();
				 vo.setMobileNO(influencePeopleArray.get(i).toString());
				 influenceMobileNumbers.add((vo));
					
			 }
			 
			 for(int i=0; i<votersArray.length(); i++)
			 {
				 SmsVO vo = new SmsVO();
				 vo.setMobileNO(votersArray.get(i).toString());
				 votersMobileNumbers.add((vo));
			 }
			 
			 for(int i=0; i<otherNumbersArray.length(); i++)
			 {
				 SmsVO vo = new SmsVO();
				 vo.setMobileNO(otherNumbersArray.get(i).toString());
				 otherMobileNumbers.add((vo));
			 }
			 
			 if(cadreMobileNumbers.size() > 0)
			   cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
						"NO", true, jObj.getString("message"),
						IConstants.Cadre_Management,cadreMobileNumbers);
			 
			 if(influenceMobileNumbers.size() > 0 )
			   cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
						"NO", true, jObj.getString("message"),
						IConstants.Influencing_People,influenceMobileNumbers);
			 
			 if(votersMobileNumbers.size() >0)
			   cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
						"NO", true, jObj.getString("message"),
						IConstants.VOTER, votersMobileNumbers);
			  if(otherMobileNumbers.size() >0)
			   cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
						"NO", true, jObj.getString("message"),
						IConstants.User_Groups, otherMobileNumbers);
			 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getVoiceSmsHistoryForAuser()
	{
		try
		{
			HttpSession session = request.getSession();			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			//voiceSmsResponseDetails = voiceSmsService.getVoiceSmsHistoryForAuser(user.getRegistrationID());
			
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			
			voiceSmsResponseDetails = voiceSmsService.getVoiceSmsHistoryForAuser(user.getRegistrationID(),startIndex ,maxRecords,false );
			
			responseDtls = new VoiceSmsResponseDetailsVO();
			
			responseDtls.setResponseDetailsList(voiceSmsResponseDetails);

			voiceSmsResponseDetails = voiceSmsService.getVoiceSmsHistoryForAuser(user.getRegistrationID(),0,0,true);
			
			if(voiceSmsResponseDetails != null && voiceSmsResponseDetails.size() >0)
			responseDtls.setResponseCount(voiceSmsResponseDetails.get(0).getResponseCount());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	
	public String getVerifiedNumbersOfUser()
	{
		try
		{
			HttpSession session = request.getSession();
			
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			
			if(user == null)
				return Action.INPUT;
			
			verifiedNumbers = voiceSmsService.getVerifiedNumbersOfUser(user.getRegistrationID());;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}

	public String voiceSmsReport()
	{
		return Action.SUCCESS;
	}
	
	
	public String generateVoiceSmsReport()
	{
		
		try
		{
			 Date fromDate1 = null;
			 Date toDate1 = null;
			
			  if(fromDate.trim().length() > 0)
				   fromDate1 = getDate(fromDate.trim());
			   if(toDate.trim().length() > 0)
				   toDate1 = getDate(toDate.trim());
			
		
			resultMap = voiceSmsService.generateVoiceSmsReport(fromDate1,toDate1);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}
	
	public String getResponseDetailsForSms()
	{
		try
		{
			 jObj = new JSONObject(getTask());
			 
			 responseDetailsList = voiceSmsService.getResponseDetailsByResponseCode(jObj.getString("messageResponseCode"));
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
		
	}
	
	public String voiceSmsHistory()
	{
		
		HttpSession session = request.getSession();			
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
			return Action.INPUT;
		
		//voiceSmsResponseDetails = voiceSmsService.getVoiceSmsHistoryForAuser(user.getRegistrationID());
		
		return Action.SUCCESS;
		
	}
	
	public String getAllTheCastesOfConstituency()
	{
		try
		{
			HttpSession session = request.getSession();		
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			 jObj = new JSONObject(getTask());
			 
			 casteMap = voiceSmsService.getAllTheCastesOfConstituency(jObj.getLong("constituencyId"),user.getRegistrationID() , jObj.getLong("publicationDateId"));
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
	}
	
	public String getVotersDetailsBySearchToSendSMS()
	{
		List<SMSSearchCriteriaVO> votersDetailsList = new ArrayList<SMSSearchCriteriaVO>();
		try
		{
			votersDetails = new SMSSearchCriteriaVO();	
			HttpSession session = request.getSession();			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			
			SMSSearchCriteriaVO searchVO = new SMSSearchCriteriaVO();
			
			searchVO.setAgeSelected(Boolean.valueOf(request.getParameter("isAgeSelected")));
			searchVO.setCasteSelected(Boolean.valueOf(request.getParameter("isCasteSelected")));
			searchVO.setFamilySelected(Boolean.valueOf(request.getParameter("isFamilySelected")));
			searchVO.setNameSelected(Boolean.valueOf(request.getParameter("isNameSelected")));
			searchVO.setGenderSelected(Boolean.valueOf(request.getParameter("isGenderSelected")));
			
			
			if(searchVO.isAgeSelected()){
				searchVO.setStartAge(Integer.parseInt(request.getParameter("startAge")));
				searchVO.setEndAge(Integer.parseInt(request.getParameter("endAge")));
			}
			
			if(searchVO.isFamilySelected())
				searchVO.setHouseNo(request.getParameter("houseNo"));
			
			if(searchVO.isNameSelected())
				searchVO.setName(request.getParameter("name"));
			
			if(searchVO.isCasteSelected())
				searchVO.setCasteIds(request.getParameter("casteIds"));
			
			if(searchVO.isGenderSelected())
				searchVO.setGender(request.getParameter("gender"));
			
			
			searchVO.setLocationType(request.getParameter("locationType"));
			searchVO.setLocationValue(Long.parseLong(request.getParameter("locationValue")));
			searchVO.setUserId(user.getRegistrationID());
			searchVO.setPublicationDateId(Long.parseLong(request.getParameter("publicationDateId")));
			
			searchVO.setStartIndex(startIndex);
			searchVO.setMaxRecords(maxRecords);
			searchVO.setColumnName(columnName);
			searchVO.setOrder(order);
			
			votersDetailsList = voiceSmsService.getVotersDetailsBySearchToSendSMS(searchVO,false,false);
			
			
             votersDetails.setResultVotersList(votersDetailsList);
             
             votersDetails.setTotalCount(voiceSmsService.getVotersDetailsCountBySearchToSendSMS(searchVO,true));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	
	public String sendVoiceSMSDirectlyToVoters()
	{
		List<SMSSearchCriteriaVO> votersDetailsList = new ArrayList<SMSSearchCriteriaVO>();
		try
		{
			votersDetails = new SMSSearchCriteriaVO();	
			HttpSession session = request.getSession();			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			 jObj = new JSONObject(getTask());

			
			
			SMSSearchCriteriaVO searchVO = new SMSSearchCriteriaVO();
			
			searchVO.setAgeSelected(jObj.getBoolean("isAgeSelected"));
			searchVO.setCasteSelected(jObj.getBoolean("isCasteSelected"));
			searchVO.setFamilySelected(jObj.getBoolean("isFamilySelected"));
			searchVO.setNameSelected(jObj.getBoolean("isNameSelected"));
			searchVO.setGenderSelected(jObj.getBoolean("isGenderSelected"));
			
			
			if(searchVO.isAgeSelected()){
				searchVO.setStartAge(jObj.getInt("startAge"));
				searchVO.setEndAge(jObj.getInt("endAge"));
			}
			
			if(searchVO.isFamilySelected())
				searchVO.setHouseNo(jObj.getString("houseNo"));
			
			if(searchVO.isNameSelected())
				searchVO.setName(jObj.getString("name"));
			
			if(searchVO.isCasteSelected())
				searchVO.setCasteIds(jObj.getString("casteIds"));
			
			if(searchVO.isGenderSelected())
				searchVO.setCasteIds(jObj.getString("gender"));
			
			
			searchVO.setLocationType(jObj.getString("locationType"));
			searchVO.setLocationValue(jObj.getLong("locationValue"));
			searchVO.setUserId(user.getRegistrationID());
			searchVO.setPublicationDateId(jObj.getLong("publicationDateId"));
			
			
			mobileNumbersList = voiceSmsService.sendVoiceSmsDirectlyToVoters(searchVO);
			
			
			if(mobileNumbersList.size() == 0)
			{
				status ="No Matched Mobile Numbers Found..";
				return Action.SUCCESS;
				
			}
			
			VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO = new VoiceSmsResponseDetailsVO();
			
			voiceSmsResponseDetailsVO.setVotersMobileNumbers(mobileNumbersList);
			voiceSmsResponseDetailsVO.setAllmobileNumbers(mobileNumbersList);
			
		
			String requestURL = request.getRequestURL().toString();	
			
			
			StringBuffer audioFilePath =  new StringBuffer();
			
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);	
			
	
			if(requestURL.contains(".com"))				
			//	audioFilePath.append(""+user.getRegistrationID()+"/"+request.getParameter("audioFileName"));
				audioFilePath.append(IWebConstants.LIVE_VOICE_RECORDINGS_URL+"/"+user.getRegistrationID()+"/"+jObj.getString("audioFileName"));
			else
				audioFilePath.append("http://122.169.253.134:8080/TDP/voice_recording/test6.wav");
			
			
			/*   cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
						"NO", true, jObj.getString("message"),
						IConstants.User_Groups, otherMobileNumbers);*/
			
			if(jObj.getString("smsType").equalsIgnoreCase("voice"))				
			 status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,"",jObj.getLong("senderMobileNumber"),jObj.getString("description"),voiceSmsResponseDetailsVO);
			else
			{
				List<SmsVO> votersMobileNumbers = new ArrayList<SmsVO>();

 
				
				for(Long number:mobileNumbersList)
				{
					SmsVO vo = new SmsVO();
					vo.setMobileNO(number.toString().substring(2));
					votersMobileNumbers.add(vo);
					
					
				}
				
			 cadreManagementService.sendSMSToSelectedMobileNumbers(user.getRegistrationID(),
								"NO", true, jObj.getString("message"),
								IConstants.VOTER, votersMobileNumbers);
				
			}
				
	             
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	
	public String uploadFileExists()
	{
		
	 check=	voiceSmsService.checkUploadFileExists(fileName);
		
		
		
		return Action.SUCCESS;

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
