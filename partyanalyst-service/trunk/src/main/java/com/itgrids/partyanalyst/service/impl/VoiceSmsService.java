package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceSmsResponseDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceSmsVerifiedNumbersDAO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.model.VoiceRecordingDetails;
import com.itgrids.partyanalyst.model.VoiceSmsResponseDetails;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoiceSmsService implements IVoiceSmsService {
	
	private static final Logger log = Logger.getLogger(VoiceSmsService.class);

	private IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO;
	private IUserDAO userDAO;
	private IVoiceSmsResponseDetailsDAO voiceSmsResponseDetailsDAO;
	private IVoiceSmsVerifiedNumbersDAO voiceSmsVerifiedNumbersDAO;
	
	
	public IVoiceSmsVerifiedNumbersDAO getVoiceSmsVerifiedNumbersDAO() {
		return voiceSmsVerifiedNumbersDAO;
	}

	public void setVoiceSmsVerifiedNumbersDAO(
			IVoiceSmsVerifiedNumbersDAO voiceSmsVerifiedNumbersDAO) {
		this.voiceSmsVerifiedNumbersDAO = voiceSmsVerifiedNumbersDAO;
	}

	public IVoiceSmsResponseDetailsDAO getVoiceSmsResponseDetailsDAO() {
		return voiceSmsResponseDetailsDAO;
	}

	public void setVoiceSmsResponseDetailsDAO(
			IVoiceSmsResponseDetailsDAO voiceSmsResponseDetailsDAO) {
		this.voiceSmsResponseDetailsDAO = voiceSmsResponseDetailsDAO;
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
	
	
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description)
	{
		log.debug("Entered into the sendVoiceSMS service method");
		int noOfSmsSent = 1;
		try {
			
					
		    //Date mydate = new Date(System.currentTimeMillis());
            //String path="http://www.partyanalyst.com/uploaded_files/"+userId+"/"+audioPath;
		    //URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no=919676696760&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://control.msg91.com/send_voice_mail.php?user=karthik1&password=184314&sender=918978236214&mobile_no=919676696760&url_file_name="+audioPath);
			//URL url = new URL("http://dnd.smschilly.com/send_voice_mail.php?user=voicedemo1&password=abcd1234&sender=919985420156&mobile_no=919963655717&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://dnd.smschilly.com/send_voice_mail.php?user=voicedemo1&password=abcd1234&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name="+audioPath);
			//URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name=			http://122.169.253.134:8080/PartyAnalyst/voice_recordings/1/test2.wav");
			//URL url = new URL("http://control.msg91.com/send_voice_mail.php?user=karthik1&password=184314&sender="+senderMobileNumber+"&mobile_no="+mobileNumbers+"&url_file_name="+audioPath);
			
			URL url = new URL("http://control.msg91.com/send_voice_mail.php?user="+IConstants.VOICE_SMS_USER_NAME+"&password="+IConstants.VOICE_SMS_PASS_WORD+"&sender="+senderMobileNumber+"&mobile_no="+mobileNumbers+"&url_file_name="+audioPath);


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
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		
		saveResponseDetails(buffer.toString() , userId , noOfSmsSent,mobileNumbers,description);
		
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in sendVoiceSMS service method");
			return IConstants.ERROR_IN_VOICE_SMS;
		}
		return IConstants.VOICE_SMS_SUCCESSFULLY_SENT;
   }
	
	public void saveResponseDetails(String responseCode , Long userId , int noOfSmsSent,String mobileNumbers,String description)
	{
		
		log.debug("Entered into the saveResponseDetails service method");
		try
		{
			VoiceSmsResponseDetails voiceSmsResponseDetails = new VoiceSmsResponseDetails();
			
			voiceSmsResponseDetails.setNoOfSmsSent(new Long(noOfSmsSent));
			voiceSmsResponseDetails.setResponseCode(responseCode);
			voiceSmsResponseDetails.setUser(userDAO.get(userId));
			voiceSmsResponseDetails.setMobileNumbers(mobileNumbers);
			voiceSmsResponseDetails.setSmsDescription(description);
			
			voiceSmsResponseDetailsDAO.save(voiceSmsResponseDetails);
			
		}catch(Exception e)
		{
			log.error("Exception raised in   the saveResponseDetails service method");
			e.printStackTrace();
		}
	}
	
	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistoryForAuser(Long userId)
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
				
			
			
			//List<VoiceSmsResponseDetails> responseDetailsList = voiceSmsResponseDetailsDAO.getVoiceSmsHistoryForAuser(userId);
			
			List<VoiceSmsResponseDetails> responseDetailsList = voiceSmsResponseDetailsDAO.getVoiceSmsHistoryForAllSubUsers(subUserIdsList);
			
			for(VoiceSmsResponseDetails details:responseDetailsList)
			{
				VoiceSmsResponseDetailsVO responseVO = new VoiceSmsResponseDetailsVO();
				
				responseVO.setResponseId(details.getVoiceSmsResponseDetailsId());
				responseVO.setResponseCode(details.getResponseCode());
				responseVO.setNumbers(details.getMobileNumbers());
				responseVO.setDateSent(details.getSentDate().toString());
				responseVO.setDescription(details.getSmsDescription());
				responseVO.setUserName(details.getUser().getFirstName()+" "+details.getUser().getLastName());
				resultList.add(responseVO);
				
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
	
	public Map<String,Map<String,Integer>> generateVoiceSmsReport()
	{
		log.debug("Entered into the generateVoiceSmsReport service method");
		Map<String ,Map<String,Integer>> resultMap = new HashMap<String, Map<String,Integer>>();
		Map<String,Integer> map = null;
		
		try
		{
			List<Object[]> userDetailsList = voiceSmsResponseDetailsDAO.getVoiceSmsSentUserDetails();
			
			
			for(Object[] obj:userDetailsList)
			{
				List<String> responseCodesList = voiceSmsResponseDetailsDAO.getResponseCodesForAnUser((Long)obj[2]);
				
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
}
