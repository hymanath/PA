package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.VoiceSmsResponseDetailsVO;

public interface IVoiceSmsService {
	
	public void saveUploadedAudioFileDetails(String fileName , Long userId , String description);
	public Map<String , String> getAllTheRecordedFilesOfAUser(Long userId);
	public String sendVoiceSMS(String audioPath ,Long userId , String mobileNumbers,Long senderMobileNumber,String description);
	public List<VoiceSmsResponseDetailsVO> getVoiceSmsHistoryForAuser(Long userId);
	public List<Long> getVerifiedNumbersOfUser(Long userId);
	public Map<String,Map<String,Integer>> generateVoiceSmsReport();
	public List<VoiceSmsResponseDetailsVO> getResponseDetailsByResponseCode(String code);
	public int getVerifiedNumbersCountOfUser(Long userId);


}
