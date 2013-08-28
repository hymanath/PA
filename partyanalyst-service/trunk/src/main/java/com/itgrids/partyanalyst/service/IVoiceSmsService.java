package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;

public interface IVoiceSmsService {
	
	public void saveUploadedAudioFileDetails(String fileName , Long userId , String description);
	public Map<String , String> getAllTheRecordedFilesOfAUser(Long userId);
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description,VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO );
	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistoryForAuser(Long userId);
	public List<Long> getVerifiedNumbersOfUser(Long userId);
	public Map<String,Map<String,Integer>> generateVoiceSmsReport(Date fromDate,Date toDate);
	public List<VoiceSmsResponseDetailsVO> getResponseDetailsByResponseCode(String code);
	public int getVerifiedNumbersCountOfUser(Long userId);
	
	public String saveCustomerContactsUpdations(Long custmerId,String mobileNo);
	public Map<Long,String> getAllTheCastesOfConstituency(Long constituencyId , Long userId , Long publicationDateId);
	public List<SMSSearchCriteriaVO> getVotersDetailsBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount);

	public List<SMSSearchCriteriaVO> getAllInfluencingPeopleDetailsForVoiceSMS(SMSSearchCriteriaVO searchVO);
	public Long getVotersDetailsCountBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount);



}