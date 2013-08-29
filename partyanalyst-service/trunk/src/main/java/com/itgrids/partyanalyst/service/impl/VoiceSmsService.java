package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceSmsResponseDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceSmsVerifiedNumbersDAO;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.VoiceRecordingDetails;
import com.itgrids.partyanalyst.model.VoiceSmsResponseDetails;
import com.itgrids.partyanalyst.model.VoiceSmsVerifiedNumbers;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoiceSmsService implements IVoiceSmsService {
	
	private static final Logger log = Logger.getLogger(VoiceSmsService.class);

	private IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO;
	private IUserDAO userDAO;
	private IVoiceSmsResponseDetailsDAO voiceSmsResponseDetailsDAO;
	private IVoiceSmsVerifiedNumbersDAO voiceSmsVerifiedNumbersDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IInfluencingPeopleDAO influencingPeopleDAO;

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
	
	
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description,VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO )
	{
		log.debug("Entered into the sendVoiceSMS service method");
		try {
			
			StringBuilder result = new StringBuilder();
		    for(Long number : voiceSmsResponseDetailsVO.getAllmobileNumbers()) {
		        result.append(number);
		        result.append(",");
		    }
		    
		    String mobileNumbersString = "";
		    
		    
		    if(mobileNumbers.length() >0)
		    	mobileNumbersString = mobileNumbersString + mobileNumbers;
		    else
		    {
		    	if(result.length() >0)
			    	mobileNumbersString = result.substring(0, result.length()-1);
		    }
		    	
		    	
					
		    //Date mydate = new Date(System.currentTimeMillis());
            //String path="http://www.partyanalyst.com/uploaded_files/"+userId+"/"+audioPath;
		    //URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no=919676696760&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://control.msg91.com/send_voice_mail.php?user=karthik1&password=184314&sender=918978236214&mobile_no=919676696760&url_file_name="+audioPath);
			//URL url = new URL("http://dnd.smschilly.com/send_voice_mail.php?user=voicedemo1&password=abcd1234&sender=919985420156&mobile_no=919963655717&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://dnd.smschilly.com/send_voice_mail.php?user=voicedemo1&password=abcd1234&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name=http://www.kozco.com/tech/LRMonoPhase4.wav");
			//URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name="+audioPath);
			//URL url = new URL("http://voice.dial4sms.com/send_voice_mail.php?user=samba&password=564396&sender=919985420156&mobile_no="+mobileNumbers+"&url_file_name=			http://122.169.253.134:8080/PartyAnalyst/voice_recordings/1/test2.wav");
			//URL url = new URL("http://control.msg91.com/send_voice_mail.php?user=karthik1&password=184314&sender="+senderMobileNumber+"&mobile_no="+mobileNumbers+"&url_file_name="+audioPath);
			
			URL url = new URL("http://control.msg91.com/send_voice_mail.php?user="+IConstants.VOICE_SMS_USER_NAME+"&password="+IConstants.VOICE_SMS_PASS_WORD+"&sender="+senderMobileNumber+"&mobile_no="+mobileNumbersString+"&url_file_name="+audioPath);
			//URL url = new URL("http://control.msg91.com/send_voice_mail.php?user="+IConstants.VOICE_SMS_USER_NAME+"&password="+IConstants.VOICE_SMS_PASS_WORD+"&sender="+senderMobileNumber+"&mobile_no="+mobileNumbers+"&url_file_name=http://www.partyanalyst.com/voice_recordings//1/test.wav");


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
		
		saveResponseDetails(buffer.toString() , userId , voiceSmsResponseDetailsVO.getAllmobileNumbers().size(),mobileNumbers,description);
		
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
				List<VoiceSmsResponseDetails> responseDetailsList = voiceSmsResponseDetailsDAO.getVoiceSmsHistoryForAllSubUsers(subUserIdsList,startIndex,maxResults);
				
				for(VoiceSmsResponseDetails details:responseDetailsList)
				{
					VoiceSmsResponseDetailsVO responseVO = new VoiceSmsResponseDetailsVO();
					
					responseVO.setResponseId(details.getVoiceSmsResponseDetailsId());
					responseVO.setResponseCode(details.getResponseCode());
					responseVO.setNumbers(details.getMobileNumbers());
					responseVO.setDateSent(details.getTimeSent().toString());
					responseVO.setDescription(details.getSmsDescription());
					responseVO.setUserName(details.getUser().getFirstName()+" "+details.getUser().getLastName());
					resultList.add(responseVO);
					
				}
			}
			else
			{
				
				List<Long> countList = voiceSmsResponseDetailsDAO.getVoiceSmsHistoryCountForAllSubUsers(subUserIdsList);
				
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
			List<Object[]> userDetailsList = voiceSmsResponseDetailsDAO.getVoiceSmsSentUserDetails( fromDate, toDate);
			
			
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
			List<SMSSearchCriteriaVO> list = getVotersDetailsBySearchToSendSMS(searchVO ,forCount);
			
			if(list != null)
				return list.get(0).getTotalCount();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return 0L;
		
	}
	
	public List<SMSSearchCriteriaVO> getVotersDetailsBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount)
	{
		log.debug("Entered into the getVotersDetailsBySearchToSendSMS service method");
		List<SMSSearchCriteriaVO> resultList = new ArrayList<SMSSearchCriteriaVO>();
		
		try
		{
			StringBuffer queryString = new StringBuffer();
			
			if(forCount == true)
			{
				queryString.append("select count(UVD.voter.name) from UserVoterDetails UVD , BoothPublicationVoter BPV " +
				           "where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
			
			}
			else
			queryString.append("select UVD.voter.name ,UVD.voter.houseNo ,UVD.voter.mobileNo,UVD.voter.age,UVD.voter.voterIDCardNo from UserVoterDetails UVD , BoothPublicationVoter BPV " +
					           "where UVD.user.userId = :userId and UVD.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDate ");
			
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
				queryString.append("and UVD.voter.gender = :gender");
			
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
			
			queryString.append(" order by UVD.voter.name "+ searchVO.getOrder());
			
		
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
			
			
						for(Object[] voterDetails:votersList)
						{
							
							SMSSearchCriteriaVO vo = new SMSSearchCriteriaVO();
							
							vo.setName(voterDetails[0].toString());
							vo.setHouseNo(voterDetails[1].toString());
							if(voterDetails[2] != null && !voterDetails[2].toString().trim().equalsIgnoreCase(""))
								vo.setMobileNumber(Long.parseLong(voterDetails[2].toString()));
							else
							vo.setMobileNumber(999999999L);
							vo.setStartAge(Integer.parseInt(voterDetails[3].toString()));
							vo.setVoterIdCardNo(voterDetails[4].toString());
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

		queryString.append(" model.firstName,model.lastName,model.phoneNo,model1.casteState.caste.casteName,model.influencingScope from " +
				" InfluencingPeople model,UserVoterDetails model1 where model.voter.voterId = model1.voter.voterId and " +
				" model1.user.userId =:userId ");

		if(searchVO.isAgeSelected())
		queryString.append(" ");
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

		if(searchVO.getLocationType().equalsIgnoreCase("constituency"))
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
		vo.setCasteIds(list[3].toString());
		vo.setLocationType(list[4].toString());
		
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
