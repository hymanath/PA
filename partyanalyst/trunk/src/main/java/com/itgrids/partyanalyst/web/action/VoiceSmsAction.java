package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	private List<Long> verifiedNumbers;
	private Map<String ,Map<String,Integer>> resultMap ;
	private JSONObject jObj;
	private String task;
	private  List<VoiceSmsResponseDetailsVO> responseDetailsList;
	
	
    private String fromDate;
    private String toDate;
	

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
			if(requestURL.contains(".com"))
				
			//	audioFilePath.append(""+user.getRegistrationID()+"/"+request.getParameter("audioFileName"));
				audioFilePath.append(IWebConstants.LIVE_VOICE_RECORDINGS_URL+"/"+user.getRegistrationID()+"/"+request.getParameter("audioFileName"));
			else
				audioFilePath.append("http://122.169.253.134:8080/TDP/voice_recording/test6.wav");

			 jObj = new JSONObject(getTask());
			
			 status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,jObj.getString("mobileNumbers"),jObj.getLong("senderMobileNumber"),jObj.getString("description"));
			 
			//status = voiceSmsService.sendVoiceSMS(audioFilePath.toString() , user.getRegistrationID() ,request.getParameter("mobileNumbers"),Long.parseLong(request.getParameter("senderMobileNumber")),request.getParameter("description"));
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
			
			voiceSmsResponseDetails = voiceSmsService.getVoiceSmsHistoryForAuser(user.getRegistrationID());
			
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

	
}
