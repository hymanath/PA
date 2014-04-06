package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;

public interface IVoiceSmsService {
	
	public void saveUploadedAudioFileDetails(String fileName , Long userId , String description);
	public Map<String , String> getAllTheRecordedFilesOfAUser(Long userId);
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description,VoiceSmsResponseDetailsVO voiceSmsResponseDetailsVO );
	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistoryForAuser(Long userId , Integer startIndex , Integer maxResults, boolean forCoun);
	public List<Long> getVerifiedNumbersOfUser(Long userId);
	public Map<String,Map<String,Integer>> generateVoiceSmsReport(Date fromDate,Date toDate);
	public List<VoiceSmsResponseDetailsVO> getResponseDetailsByResponseCode(String code);
	public int getVerifiedNumbersCountOfUser(Long userId);
	
	public String saveCustomerContactsUpdations(Long custmerId,String mobileNo);
	public Map<String,String> getAllTheCastesOfConstituency(Long constituencyId , Long userId , Long publicationDateId);
	public List<SMSSearchCriteriaVO> getVotersDetailsBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount , boolean directSend);

	public List<SMSSearchCriteriaVO> getAllInfluencingPeopleDetailsForVoiceSMS(SMSSearchCriteriaVO searchVO);
	public Long getVotersDetailsCountBySearchToSendSMS(SMSSearchCriteriaVO searchVO ,boolean forCount);
	public List<Long> sendVoiceSmsDirectlyToVoters(SMSSearchCriteriaVO searchVO);

    public boolean checkUploadFileExists(String fileName);
    public List<VoiceSmsResponseDetailsVO> getSmsDetails(Long userId,Long typeId,Long constituencyId);
    public String deleteSmsDetails(List<Long> smsResponseid);
    public ResultStatus resendSmsResponseDetails(String message,Long receiverId,Long userId);
	public ResultStatus saveSmsDetails(String smsDescription,List<Long> receiverIds,Long userId,String smsType);
	public List<RegistrationVO> getInformationManagers(Long userId);
	public List<String> getMobileNosForReceiverIds(List<Long> receiverIds);
	public List<VoiceSmsResponseDetailsVO> getDatewiseSortingDetails(Long userId,Long typeId,String date);
	public List<VoiceSmsResponseDetailsVO> getSmsDetailsBySearch(Long userId,Long typeId,String namesearchText,String mobilesearch);
	
	public List<VoiceSmsResponseDetailsVO> getSmsDetailsByLocationSearch(Long userId,Long typeId,String locationsearchText);
}