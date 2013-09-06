package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.dao.ISmsModuleDAO;
import com.itgrids.partyanalyst.dao.ISmsResponseDetailsDAO;
import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceSmsVerifiedNumbersDAO;
import com.itgrids.partyanalyst.dao.hibernate.LocalElectionBodyDAO;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsModule;
import com.itgrids.partyanalyst.model.SmsResponseDetails;
import com.itgrids.partyanalyst.model.SmsTrack;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.VoiceRecordingDetails;
import com.itgrids.partyanalyst.model.VoiceSmsVerifiedNumbers;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;


public class VoiceSmsService implements IVoiceSmsService {
	
	private static final Logger log = Logger.getLogger(VoiceSmsService.class);

	private IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO;
	private IUserDAO userDAO;
	private ISmsResponseDetailsDAO smsResponseDetailsDAO;
	private IVoiceSmsVerifiedNumbersDAO voiceSmsVerifiedNumbersDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IInfluencingPeopleDAO influencingPeopleDAO;
	private ISmsTrackDAO smsTrackDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private TransactionTemplate transactionTemplate = null;
	private ISmsHistoryDAO smsHistoryDAO;
	private ISmsModuleDAO smsModuleDAO;
	private TaskExecutor taskExecutor;
	private LocalElectionBodyDAO localElectionBodyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public LocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(LocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ISmsResponseDetailsDAO getSmsResponseDetailsDAO() {
		return smsResponseDetailsDAO;
	}

	public void setSmsResponseDetailsDAO(
			ISmsResponseDetailsDAO smsResponseDetailsDAO) {
		this.smsResponseDetailsDAO = smsResponseDetailsDAO;
	}


	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public ISmsModuleDAO getSmsModuleDAO() {
		return smsModuleDAO;
	}

	public void setSmsModuleDAO(ISmsModuleDAO smsModuleDAO) {
		this.smsModuleDAO = smsModuleDAO;
	}

	public ISmsHistoryDAO getSmsHistoryDAO() {
		return smsHistoryDAO;
	}

	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		this.smsHistoryDAO = smsHistoryDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public ISmsTrackDAO getSmsTrackDAO() {
		return smsTrackDAO;
	}

	public void setSmsTrackDAO(ISmsTrackDAO smsTrackDAO) {
		this.smsTrackDAO = smsTrackDAO;
	}

	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IVoiceSmsVerifiedNumbersDAO getVoiceSmsVerifiedNumbersDAO() {
		return voiceSmsVerifiedNumbersDAO;
	}

	public void setVoiceSmsVerifiedNumbersDAO(
			IVoiceSmsVerifiedNumbersDAO voiceSmsVerifiedNumbersDAO) {
		this.voiceSmsVerifiedNumbersDAO = voiceSmsVerifiedNumbersDAO;
	}

	

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IVoiceRecordingDetailsDAO getVoiceRecordingDetailsDAO() {
		return voiceRecordingDetailsDAO;
	}

	public void setVoiceRecordingDetailsDAO(
			IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO) {
		this.voiceRecordingDetailsDAO = voiceRecordingDetailsDAO;
	}

	
	public void saveUploadedAudioFileDetails(String fileName , Long userId , String description)
	{
		log.debug("Entered into the saveUploadedAudioFileDetails service method");
		
		try
		{
			
			VoiceRecordingDetails voiceRecordingDetails = new VoiceRecordingDetails();
			
			voiceRecordingDetails.setRecordingName(fileName);
			voiceRecordingDetails.setUser(userDAO.get(userId));
			voiceRecordingDetails.setRecordingDescription(description);			
			
			voiceRecordingDetailsDAO.save(voiceRecordingDetails);
			
		}catch(Exception e)
		{
			log.error("Exception raised in  saveUploadedAudioFileDetails service method");
			e.printStackTrace();
			
		}
		
	}
	
	public Map<String , String> getAllTheRecordedFilesOfAUser(Long userId)
	{
		log.debug("Entered into the getAllTheRecordedFilesOfAUser service method");
		
		Map<String , String> resultMap = new HashMap<String, String>();
		try
		{
			List<Object[]> detailsList = voiceRecordingDetailsDAO.getAllTheRecordingDetailsOfUser(userId);
			
			
			for(Object[] obj:detailsList)
				resultMap.put(obj[0].toString(), obj[1].toString()+"--"+obj[2].toString());
				
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in  the getAllTheRecordedFilesOfAUser service method");

		}
		return resultMap;
		
	}
	
	
	/**
	 * This method will format all the mobile numbers in required format , verify the balance SMS and send SMS 
	 * @param audioPath , audio file path to send voice SMS
	 * @param userId 
	 * @param mobileNumbers , these are entered by user manually
	 * @param description , sms description
	 * @param voiceSmsResponseDetailsVO , contains all the details of cadre , influence people ,voter mobile numbers
	 * @return string
	 */
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description,VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO )
	{
		log.debug("Entered into the sendVoiceSMS service method");
		try {
			
			StringBuilder result = new StringBuilder();
			if(voiceSmsResponseDetailsVO.getAllmobileNumbers() != null)
		    for(Long number : voiceSmsResponseDetailsVO.getAllmobileNumbers()) {
		        result.append(number);
		        result.append(",");
		    }
		    
		    String mobileNumbersString = "";    
		    
		    if(mobileNumbers.length() >0)
		    	mobileNumbersString = result + mobileNumbers;
		    else
		    {
		    	if(result.length() >0)
			    	mobileNumbersString = result.substring(0, result.length()-1);
		    }
		    
		    List<Long> otherNumbers = new ArrayList<Long>();
		    
		    if(!mobileNumbers.equals(""))
		    for(int i=0;i<mobileNumbers.split(",").length;i++)
		    	otherNumbers.add(Long.parseLong(mobileNumbers.split(",")[i]));
		    	
			  List<SmsTrack> smsDetails =   smsTrackDAO.getUserSmsDetailsByUserIdAndSMSType(userId,IConstants.VOICE_SMS_TYPE);
			  
			  String userName = "";
			  String password = "";
			  Long count = 0L;
			  
			  if(smsDetails != null && smsDetails.size() >0)
			  {
				  userName = smsDetails.get(0).getSmsUsername();
				  password = smsDetails.get(0).getSmsPassword();
				  count = smsDetails.get(0).getRenewalSmsCount();
				  
				  if(mobileNumbersString.split(",").length > count)
					  return "Your account doee not have sufficient voice SMS balance.Please contact us....";
				  
				  SmsTrack smsTrack = smsDetails.get(0);
				  
				  Long reaminingCount = count - mobileNumbersString.split(",").length;			  
				  smsTrack.setRenewalSmsCount(reaminingCount);
				  smsTrack.setRenewalDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
				  
				  smsTrackDAO.save(smsTrack);
			  }
			  
			  String allNumbers[] = Arrays.copyOf(mobileNumbersString.split(","), 3);
			  
			  int noOfIterations = 0;
			  if(allNumbers.length % 10000 == 0)
				  noOfIterations = allNumbers.length / 10000;				  
			 else
				  
				  noOfIterations = (allNumbers.length / 10000)+1;			  
		
			  
			  if(allNumbers.length -1 >10000){
				  
				 for(int i=0;i < noOfIterations ;i++)
				 {
					 String[]  subSet = null;
										 
					 if(i != noOfIterations-1)
					 {
						 if(allNumbers.length >= (i+1)*10000 )
					       subSet = Arrays.copyOfRange(allNumbers, i*10000+1, (i+1)*10000);
						 else
						   subSet = Arrays.copyOfRange(allNumbers, i*10000+1, allNumbers.length);
					   
					 }else
					 {
						subSet = Arrays.copyOfRange(allNumbers, i*10000+1,(i+1)*10000);
					 }					 
					 
					 StringBuilder subString = new StringBuilder();
					 
					 for(String number:subSet)
					 {
						 subString.append(number+",");
					 }
					 
					 String mobileNoStr = subString.toString();
					 mobileNoStr = mobileNoStr.substring(0, mobileNoStr.length()-1);
					 
					  taskExecutor.execute(sendVoiceSMSUsingProvider(userName , password , senderMobileNumber.toString() ,mobileNoStr ,audioPath, voiceSmsResponseDetailsVO , userId , otherNumbers , description , mobileNumbers));

					 
				 }
				  
			  }else			
			  taskExecutor.execute(sendVoiceSMSUsingProvider(userName , password , senderMobileNumber.toString() ,mobileNumbersString ,audioPath, voiceSmsResponseDetailsVO , userId , otherNumbers , description , mobileNumbers));
		 
		 
		
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in sendVoiceSMS service method");
			return IConstants.ERROR_IN_VOICE_SMS;
		}
		return IConstants.VOICE_SMS_SUCCESSFULLY_SENT;
   }
	
	
	/**
	 * This method will send the SMS to mobile numbers using provider
	 * @param userName
	 * @param password
	 * @param senderMobileNumber
	 * @param mobileNumbersString
	 * @param audioPath
	 * @return String
	 */
	public Runnable sendVoiceSMSUsingProvider(String userName , String password , String senderMobileNumber ,String mobileNumbersString ,String audioPath , VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO,Long userId ,List<Long> otherNumbers,String description ,String mobileNumbers)
	{
		log.debug("Entered into the sendVoiceSMSUsingProvider service method");
		StringBuffer buffer = new StringBuffer();
		
		try
		{			
			
			URL url = new URL("http://control.msg91.com/send_voice_mail.php?user="+userName+"&password="+password+"&sender="+senderMobileNumber+"&mobile_no="+mobileNumbersString+"&url_file_name="+audioPath);
	
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null)
			{
			  buffer.append(line).append("\n");
			}
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		
			
		 Long responseId = 	saveResponseDetails(buffer.toString() , userId , voiceSmsResponseDetailsVO.getAllmobileNumbers().size(),mobileNumbers,description);
		     
		     
		     saveSentSMSHistoryDetails(
						userId,
						voiceSmsResponseDetailsVO.getCadreMobileNumbers(),
						voiceSmsResponseDetailsVO.getInfluencePeopleMobileNumbers(),
						voiceSmsResponseDetailsVO.getVotersMobileNumbers(),
						otherNumbers,responseId);	     
			
		
		}catch(Exception e)
		{
			log.error("Exception raised in  sendVoiceSMSUsingProvider service method");
			e.printStackTrace();
		}
		return new Thread();
	}
	
	/**
	 * This method will save the type of people whom SMS sent
	 * @param userId
	 * @param cadreNumbers
	 * @param influencePeopleNumbers
	 * @param votersNumbers
	 * @param otherNumbers
	 * @param responseId
	 */
	
	public void  saveSentSMSHistoryDetails(Long userId ,List<Long> cadreNumbers , List<Long> influencePeopleNumbers , List<Long> votersNumbers , List<Long> otherNumbers,Long responseId)
	{
		log.debug("Entered into the saveSentSMSHistoryDetails service method");
		try
		{
			SmsModule smsModule = null;
			
			if(cadreNumbers != null && cadreNumbers.size() >0)
				for(Long cadreNumber:cadreNumbers)
				{
					
					List<SmsModule> list = smsModuleDAO.findByModuleName(IConstants.Cadre_Management);
					
					if(list != null && list.size() > 0)
						smsModule = list.get(0);
					
					saveSmsHistoryDetails(userId ,cadreNumber.toString(),smsModule,responseId );
				}
			
			if(influencePeopleNumbers != null && influencePeopleNumbers.size() >0)
				for(Long influencePeopleNumber:influencePeopleNumbers)
				{
                    List<SmsModule> list = smsModuleDAO.findByModuleName(IConstants.Influencing_People);
					
					if(list != null && list.size() > 0)
						smsModule = list.get(0);
					
					saveSmsHistoryDetails(userId ,influencePeopleNumber.toString(),smsModule ,responseId);
				}
			
			if(votersNumbers != null && votersNumbers.size() >0)
				for(Long voterNumber:votersNumbers)
				{
                    List<SmsModule> list = smsModuleDAO.findByModuleName(IConstants.VOTER);
					
					if(list != null && list.size() > 0)
						smsModule = list.get(0);
					saveSmsHistoryDetails(userId ,voterNumber.toString(),smsModule ,responseId);
				}
			
			if(otherNumbers != null && otherNumbers.size() >0)
				for(Long otherNumber:otherNumbers)
				{
                   List<SmsModule> list = smsModuleDAO.findByModuleName(IConstants.VOTER);
					
					if(list != null && list.size() > 0)
						smsModule = list.get(0);
					
					saveSmsHistoryDetails(userId ,otherNumber.toString(),smsModule,responseId );
				}
			
		}catch(Exception e)
		{
			log.error("Exception raised in saveSentSMSHistoryDetails service method");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to save the sms hisory details
	 * @param userId
	 * @param mobileNumber
	 * @param smsModule
	 * @param responseId
	 */
	public void saveSmsHistoryDetails(Long userId ,String mobileNumber,SmsModule smsModule,Long responseId )
	{
		log.debug("Entered into the saveSmsHistoryDetails service method");

		try
			{
			SmsHistory smsHistory = new SmsHistory();
			
			smsHistory.setUserId(userId);
			smsHistory.setMobileNumber(mobileNumber);
			smsHistory.setSentDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
			smsHistory.setSmsModule(smsModule);
			smsHistory.setSmsResponseDetailsId(responseId);
	
	        smsHistoryDAO.save(smsHistory);
		}catch(Exception e)
		{
			log.error("Exception raised in saveSmsHistoryDetails service method");
			e.printStackTrace();
			
		}
		
	}
	
	public Long saveResponseDetails(String responseCode , Long userId , int noOfSmsSent,String mobileNumbers,String description)
	{
		SmsResponseDetails smsResponseDetails = new SmsResponseDetails();

		
		log.debug("Entered into the saveResponseDetails service method");
		try
		{
			smsResponseDetails.setNoOfSmsSent(new Long(noOfSmsSent));
			smsResponseDetails.setResponseCode(responseCode);
			smsResponseDetails.setUser(userDAO.get(userId));
			//voiceSmsResponseDetails.setMobileNumbers(mobileNumbers);
			smsResponseDetails.setSmsDescription(description);
			smsResponseDetails.setTimeSent(dateUtilService.getCurrentDateAndTime());
			smsResponseDetails.setSmsTypeId(1L);
			
			smsResponseDetails = smsResponseDetailsDAO.save(smsResponseDetails);
			
		}catch(Exception e)
		{
			log.error("Exception raised in   the saveResponseDetails service method");
			e.printStackTrace();
		}
		return smsResponseDetails.getSmsResponseDetailsId();
	}
	
	

	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistoryForAuser(Long userId , Integer startIndex , Integer maxResults , boolean forCount)
	{
		log.debug("Entered into the getVoiceSmsHistoryForAuser service method");
		
		List<VoiceSmsResponseDetailsVO> resultList = new ArrayList<VoiceSmsResponseDetailsVO>();
		try
		{
			List<Long> subUserIdsList = new ArrayList<Long>();
			
			subUserIdsList.add(userId);
			
			List<Object[]> userIds = userDAO.getSubusersByParentUserId(userId);
			
			for(Object[] obj:userIds)
				subUserIdsList.add((Long)obj[1]);
				
			
			

			
			if(forCount == false)
			{
				List<SmsResponseDetails> responseDetailsList = smsResponseDetailsDAO.getVoiceSmsHistoryForAllSubUsers(subUserIdsList,startIndex,maxResults);
				
				for(SmsResponseDetails details:responseDetailsList)
				{
					VoiceSmsResponseDetailsVO responseVO = new VoiceSmsResponseDetailsVO();
					
					responseVO.setResponseId(details.getSmsResponseDetailsId());
					responseVO.setResponseCode(details.getResponseCode());
					responseVO.setDateSent(details.getTimeSent().toString());
					responseVO.setDescription(details.getSmsDescription());
					responseVO.setUserName(details.getUser().getFirstName()+" "+details.getUser().getLastName());
					resultList.add(responseVO);
					
				}
			}
			else
			{
				
				List<Long> countList = smsResponseDetailsDAO.getVoiceSmsHistoryCountForAllSubUsers(subUserIdsList);
				
				if(countList != null && countList.size() >0)
				{
					VoiceSmsResponseDetailsVO responseVO = new VoiceSmsResponseDetailsVO();					
					responseVO.setResponseCount(countList.get(0));
					resultList.add(responseVO);
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in  getVoiceSmsHistoryForAuser service method");
		}
		
		return resultList;
	}
	
	
	public List<Long> getVerifiedNumbersOfUser(Long userId)
	{
		List<Long> verifiedNumbers = new ArrayList<Long>();
		
		log.debug("Entered into the getVerifiedNumbersOfUser service method");
		try
		{
			verifiedNumbers = voiceSmsVerifiedNumbersDAO.getVerifiedNumbersOfUser(userId);
		}
		catch(Exception e)
		{
			log.error("Exception raised in getVerifiedNumbersOfUser service method");
		}
		
		return verifiedNumbers;
		
	}
	
	public Map<String,Map<String,Integer>> generateVoiceSmsReport(Date fromDate,Date toDate)
	{
		log.debug("Entered into the generateVoiceSmsReport service method");
		Map<String ,Map<String,Integer>> resultMap = new HashMap<String, Map<String,Integer>>();
		Map<String,Integer> map = null;
		
		try
		{
			List<Object[]> userDetailsList = smsResponseDetailsDAO.getVoiceSmsSentUserDetails( fromDate, toDate);
			
			
			for(Object[] obj:userDetailsList)
			{
				List<String> responseCodesList = smsResponseDetailsDAO.getResponseCodesForAnUser((Long)obj[2]);
				
				map = new HashMap<String, Integer>();
				for(String code:responseCodesList)
				{					
					URL url = new URL("http://control.msg91.com/api/check_voice_dlr.php?user="+IConstants.VOICE_SMS_USER_NAME+"&password="+IConstants.VOICE_SMS_PASS_WORD+"&msgid="+code);

				    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setUseCaches(false);
					conn.connect();
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					StringBuffer buffer = new StringBuffer();
					while ((line = rd.readLine()) != null)
					{
					  buffer.append(line).append("\n");
					}
					
					String resultString = buffer.toString();
					
					for(int i = 1;i < resultString.split("<br />").length-1;i++)
					{
						String row = resultString.split("<br />")[i];
						
						String status =row.split("\\|")[5];
						
						if(map.get(status) != null)
						{
							int count = map.get(status);
							count++;
							map.put(status,count);
							
						}else
						{
							map.put(status,1);
							
						}					
					}				
				}
				
				resultMap.put(obj[0].toString()+"-"+obj[1].toString()+"-"+obj[2].toString(), map);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in  generateVoiceSmsReport service method");

		}

		return resultMap;
	}
	
	
	public List<VoiceSmsResponseDetailsVO> getResponseDetailsByResponseCode(String code)
	{
		
		log.debug("Entered into the getResponseDetailsByResponseCode service method");
		
		List<VoiceSmsResponseDetailsVO> resultList = new ArrayList<VoiceSmsResponseDetailsVO>();
		
		try
		{
			URL url = new URL("http://control.msg91.com/api/check_voice_dlr.php?user="+IConstants.VOICE_SMS_USER_NAME+"&password="+IConstants.VOICE_SMS_PASS_WORD+"&msgid="+code);

		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null)
			{
			  buffer.append(line).append("\n");
			}
			
			String resultString = buffer.toString();
			
			for(int i = 1;i < resultString.split("<br />").length-1;i++)
			{
				VoiceSmsResponseDetailsVO vo = new VoiceSmsResponseDetailsVO();
				
				String row = resultString.split("<br />")[i];
				
				String status =row.split("\\|")[5];
				
				vo.setNumbers(row.split("\\|")[1]);
				vo.setSentStatus(status);
				
				resultList.add(vo);
								
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception occured in getResponseDetailsByResponseCode service method");
			
		}
		
		return resultList;
		
	}
	
	
	public int getVerifiedNumbersCountOfUser(Long userId)
	{
		try
		{
		return voiceSmsVerifiedNumbersDAO.getVerifiedNumbersOfUser(userId).size();
			
		}catch(Exception e)
		{
          e.printStackTrace();	
          return 0;
		}
		
	}
	
	public String saveCustomerContactsUpdations(Long custmerId,String mobileNo){
		User user = new User();
		mobileNo = "91".concat(mobileNo);
		try{
			user = userDAO.get(custmerId);			
				VoiceSmsVerifiedNumbers voiceSmsVerifiedNumbers = new VoiceSmsVerifiedNumbers();
				voiceSmsVerifiedNumbers.setUser(user);
				voiceSmsVerifiedNumbers.setSenderMobileNumber(Long.valueOf(mobileNo));
				voiceSmsVerifiedNumbersDAO.save(voiceSmsVerifiedNumbers);
		
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		return "failur";
		}	
	}
	
	public Map<String,String> getAllTheCastesOfConstituency(Long constituencyId , Long userId , Long publicationDateId)
	{
		log.debug("Entered into the getAllTheCastesOfConstituency service method");
		
		Map<String,String> casteDetailsMap = new HashMap<String, String>();
		
		try
		{
			List<Object[]> casteDetailsList = userVoterDetailsDAO.getAllTheCastesOfConstituency(constituencyId, userId, publicationDateId);
			
			for(Object[] obj:casteDetailsList)
				casteDetailsMap.put(obj[0].toString(), obj[1].toString());
			
		}catch(Exception e)
		{
			log.error("Exception raised in getAllTheCastesOfConstituency service method");
			e.printStackTrace();			

		}		
		return casteDetailsMap;
	}
	
	public Long getVotersDetailsCountBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount)
	{
		try
		{
			List<SMSSearchCriteriaVO> list = getVotersDetailsBySearchToSendSMS(searchVO ,forCount,false);
			
			if(list != null)
				return list.get(0).getTotalCount();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return 0L;
		
	}
	
	public List<Long> sendVoiceSmsDirectlyToVoters(SMSSearchCriteriaVO searchVO )
	{
		List<Long> mobileNumbers = new ArrayList<Long>();

		try
		{
			List<SMSSearchCriteriaVO> votreNumbersList = getVotersDetailsBySearchToSendSMS(searchVO,false,true);
			
			if(votreNumbersList != null && votreNumbersList.size() >0)
				mobileNumbers =  votreNumbersList.get(0).getMobileNumbersList();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mobileNumbers;
	}
	
	public List<SMSSearchCriteriaVO> getVotersDetailsBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount , boolean directSend)
	{
		log.debug("Entered into the getVotersDetailsBySearchToSendSMS service method");
		List<SMSSearchCriteriaVO> resultList = new ArrayList<SMSSearchCriteriaVO>();
		
		try
		{
			StringBuffer queryString = new StringBuffer();
			
			if(forCount == true)
			{
				queryString.append("select count(UVD.voter.name) from UserVoterDetails UVD , BoothPublicationVoter BPV " +
				           "where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDate and UVD.mobileNo is not null ");
			}
			else
			queryString.append("select UVD.voter.name ,UVD.voter.houseNo ,UVD.mobileNo,UVD.voter.age,UVD.voter.voterIDCardNo from UserVoterDetails UVD , BoothPublicationVoter BPV " +
					           "where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDate and UVD.mobileNo is not null ");
			
			if(searchVO.isAgeSelected())
				queryString.append("and UVD.voter.age between "+searchVO.getStartAge()+" and "+searchVO.getEndAge());
			if(searchVO.isNameSelected())
				queryString.append("and UVD.voter.name like %"+searchVO.getName()+"%" );
			if(searchVO.isFamilySelected())
				queryString.append("and UVD.voter.houseNo = :houseNo ");
			if(searchVO.isCasteSelected()){
				
				String castes[] = searchVO.getCasteIds().split("-");
				
				List<Long> casteIds = new ArrayList<Long>();
				
				for(String caste:castes)
					casteIds.add(Long.parseLong(caste));				
				searchVO.setSelectedCastes(casteIds);
				
				queryString.append("and UVD.casteState.caste.casteId in(:casteIds) ");
			}
			if(searchVO.isGenderSelected())
				queryString.append("and UVD.voter.gender = :gender ");
			
			if(searchVO.getLocationType().equalsIgnoreCase("constituency"))
				queryString.append("and BPV.booth.constituency.constituencyId = :locationValue");
			else if(searchVO.getLocationType().equalsIgnoreCase("mandal"))
				queryString.append("and BPV.booth.tehsil.tehsilId = :locationValue");
			else if(searchVO.getLocationType().equalsIgnoreCase("panchayat"))
				queryString.append("and BPV.booth.panchayat.panchayatId = :locationValue");
			else if(searchVO.getLocationType().equalsIgnoreCase("ward"))
				queryString.append("and BPV.booth.localBodyWard.constituencyId = :locationValue");
			else if(searchVO.getLocationType().equalsIgnoreCase("booth"))
				queryString.append("and BPV.booth.boothId = :locationValue");
			else if(searchVO.getLocationType().equalsIgnoreCase("hamlet"))
				queryString.append("and UVD.hamlet.hamletId = :locationValue");
			
			if(directSend != true)
			   queryString.append(" order by UVD.voter.name "+ searchVO.getOrder());
			
			searchVO.setDirectSent(directSend);
			
		
			List<Object[]> votersList = new ArrayList<Object[]>();
			Long count = 0L;
			
			if(forCount == true)
			{
				 List<Long> countList = userVoterDetailsDAO.getVotersDetailsCountBySearchToSendSMS(queryString.toString(),searchVO);
				 count = countList.get(0);
				 
				 SMSSearchCriteriaVO vo = new SMSSearchCriteriaVO();
				 
				 vo.setTotalCount(count);
				 resultList.add(vo);
			}
			else{
				
				
			   votersList = userVoterDetailsDAO.getVotersDetailsBySearchToSendSMS(queryString.toString(),searchVO);			
			
			
			       if(directSend != true)
			       {
						for(Object[] voterDetails:votersList)
						{
							
							SMSSearchCriteriaVO vo = new SMSSearchCriteriaVO();
							
							vo.setName(voterDetails[0].toString());
							vo.setHouseNo(voterDetails[1].toString());
							if(voterDetails[2] != null && !voterDetails[2].toString().trim().equalsIgnoreCase(""))
								vo.setMobileNumber(Long.parseLong(voterDetails[2].toString()));
							vo.setStartAge(Integer.parseInt(voterDetails[3].toString()));
							vo.setVoterIdCardNo(voterDetails[4].toString());
							resultList.add(vo);
							
						}
			       }else
			       {
			    	   List<Long> allVoterMobileNumbers = new ArrayList<Long>();
			    	   
			    	   for(Object[] voterDetails:votersList)
							if(voterDetails[2] != null && !voterDetails[2].toString().trim().equalsIgnoreCase(""))
			    		      allVoterMobileNumbers.add(Long.parseLong("91"+voterDetails[2].toString()));
			    	   
			    		SMSSearchCriteriaVO vo = new SMSSearchCriteriaVO();
						
					    vo.setMobileNumbersList(allVoterMobileNumbers);
						resultList.add(vo);
							
			    	   
			       }
			}
			
		}catch(Exception e)
		{
			log.error("Exception raised in getVotersDetailsBySearchToSendSMS service method");
			e.printStackTrace();
		}
		
		return resultList;

	}
	
	public List<SMSSearchCriteriaVO> getAllInfluencingPeopleDetailsForVoiceSMS(SMSSearchCriteriaVO searchVO) {
		log.debug("Entered into the getAllInfluencingPeopleDetailsForVoiceSMS service method");
		List<SMSSearchCriteriaVO> resultList = new ArrayList<SMSSearchCriteriaVO>();

		try
		{
		StringBuffer queryString = new StringBuffer();

		queryString.append(" model.firstName,model.lastName,model.phoneNo,model.influencingScope,model2.casteState.caste.casteName from " +
				" InfluencingPeople model,UserVoterDetails model2 where model.user.userId =:userId and model2.user.userId =:userId and " +
				" model.voter.voterId = model2.voter.voterId ");

		if(searchVO.isNameSelected())
		queryString.append(" and ( model.firstName like '%"+searchVO.getName()+"%' or model.lastName like '%"+searchVO.getName()+"%' )");
		if(searchVO.isFamilySelected())
		queryString.append("  ");
		if(searchVO.isCasteSelected()){

		String castes[] = searchVO.getCasteIds().split("-");

		List<Long> casteIds = new ArrayList<Long>();

		for(String caste:castes)
		casteIds.add(Long.parseLong(caste));
		
		searchVO.setSelectedCastes(casteIds);

		if(searchVO.getSelectedCastes().size()==1){
			queryString.append(" and ( model.caste like '"+searchVO.getSelectedCastes().get(0).toString()+"')");
		}
		if(searchVO.getSelectedCastes().size()>1){
			int count = searchVO.getSelectedCastes().size();
			queryString.append(" and ( ");
			for (Long parms : casteIds) {
				if(count >1)
					queryString.append(" model.caste like '"+parms.toString()+"' or ");
				else
					queryString.append(" model.caste like '"+parms.toString()+"' ");
				count = count-1;
			}
			queryString.append(" ) ");
		}
		
		}
		if(searchVO.isGenderSelected())
			if(!searchVO.getGender().equalsIgnoreCase("All") || !searchVO.getGender().equalsIgnoreCase(""))
				queryString.append("and model.gender like '"+searchVO.getGender()+"%' ");
		//Location Based Search for Influencing People
		if(searchVO.getLocationType().equalsIgnoreCase(IConstants.STATE))
			queryString.append(" and model.userAddress.state.stateId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.DISTRICT))
			queryString.append(" and model.userAddress.district.districtId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.CONSTITUENCY))
			queryString.append(" and model.userAddress.constituency.constituencyId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.MANDAL))
			queryString.append(" and model.userAddress.tehsil.tehsilId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
			LocalElectionBody localBody = localElectionBodyDAO.get(new Long(assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(searchVO.getConstituencyId())));
			queryString.append(" and model.userAddress.localElectionBody is not null and model.userAddress.localElectionBody.localElectionBodyId ="+localBody.getLocalElectionBodyId()+""+
					" and model.userAddress.ward is not null and model.userAddress.constituency.constituencyId = "+searchVO.getConstituencyId()+"");
		}
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.WARD))
			queryString.append(" and model.userAddress.ward.constituencyId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.BOOTH))
			queryString.append(" and model.userAddress.booth.boothId =:locationId ");
		else if(searchVO.getLocationType().equalsIgnoreCase(IConstants.HAMLET))
			queryString.append(" and model.userAddress.hamlet.hamletId =:locationId ");
		
		/*	Region Based Search for Influencing People
		 * if(searchVO.getLocationType().equalsIgnoreCase("constituency"))
		queryString.append(" and (model.influencingScope like '"+IConstants.CONSTITUENCY+"' and model.influencingScopeValue like '"+searchVO.getLocationValue().toString()+"')");
		else if(searchVO.getLocationType().equalsIgnoreCase("mandal"))
		queryString.append(" and (model.influencingScope like '"+IConstants.MANDAL+"' and model.influencingScopeValue like '"+searchVO.getLocationValue().toString()+"')");
		else if(searchVO.getLocationType().equalsIgnoreCase("panchayat"))
		queryString.append(" and (model.influencingScope like '"+IConstants.PANCHAYAT+"' and model.influencingScopeValue like '"+searchVO.getLocationValue().toString()+"')");
		else if(searchVO.getLocationType().equalsIgnoreCase("ward"))
		queryString.append(" and (model.influencingScope like '"+IConstants.WARD+"' and model.influencingScopeValue like '"+searchVO.getLocationValue().toString()+"')");
		else if(searchVO.getLocationType().equalsIgnoreCase("booth"))
		queryString.append(" and (model.influencingScope like '"+IConstants.BOOTH+"' and model.influencingScopeValue like '"+searchVO.getLocationValue().toString()+"')");
		else if(searchVO.getLocationType().equalsIgnoreCase("hamlet"))
		queryString.append(" and (model.influencingScope like '"+IConstants.BOOTH+"' and model.hamlet.hamletId like '"+searchVO.getLocationValue().toString()+"')");
		 */
		
		queryString.append(" order by model.firstName "+ searchVO.getOrder());



		List<Object[]> influencingPeopleList = influencingPeopleDAO.getInfluencingPeopleDetailsToSendSMS(queryString.toString(),searchVO);

		List<Object[]> totalCount = influencingPeopleDAO.getInfluencingPeopleDetailsToSendSMS(queryString.toString(),searchVO,null);
		
		for (Object[] prms : totalCount) {
			SMSSearchCriteriaVO vo1 = new SMSSearchCriteriaVO();
			int count = Integer.parseInt(prms[0].toString());
			vo1.setTotalResultsCount(count);
			resultList.add(vo1);
		}
		
		for(Object[] list:influencingPeopleList)
		{

		SMSSearchCriteriaVO vo = new SMSSearchCriteriaVO();

		vo.setName(list[0].toString()+" " +list[1].toString());
		//vo.setHouseNo(list[2].toString());
		vo.setMobileNumber(Long.valueOf(list[2].toString()));
		vo.setCasteIds(list[4].toString());
		vo.setLocationType(list[3].toString());
		
		//vo.setStartAge(Integer.parseInt(list[3].toString()));
		resultList.add(vo);

		}
			
		}catch(Exception e)
		{
		log.error("Exception raised in getAllInfluencingPeopleDetailsForVoiceSMS service method");
		e.printStackTrace();
		}

		return resultList;
	}
}
