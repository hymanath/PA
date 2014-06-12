package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;

public interface IWebServiceHandlerService {
	
	public String checkForUserAuthentication(String userName , String passWord);
	
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
	
	public ResultStatus sendSmsToUser(String uniquecode);
	
	public ResultStatus updatePassword(String uniqueCode,String pwd,String accessKey);
	
	public String sendVoiceSMS(String uniqueCode,String mobileNos,String audioFilePath);
	
	public List<WSResultVO> getUserVoiceRecordedFiles(String uniqueCode);
	
	public ResultStatus sendSMS(String uniqueCode,String mobileNos,String message);
	
	public String getBaseUrlForApp(String appName);
	
	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo);
	
	public String saveCadreFromAndroid(VoterDetailsVO voterDetails);
	
	public String updateVoterDetails(String uniqueCode,Long voterId,Long casteStateId,String mobileNumber);
	
	
	
	public String updateCadreDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId);
	
	public String updateIPDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId,Long partyId);
	
	public String updateFalgDetails(String uniqueId,String flagName,String flagColor,String voterIds);
	
	public String updateVoterMobileNumberAndCaste(String voterID,
			Long casteStateId,
			String mobileNo,String uniqueId);
	
	public String updateVoterTagDetails(VoterTagVO voterTagVO);
	
	public String updateVoterBoothActivitiesDetails(VoterTagVO voterTagVO);
	
	public String requestForAuthorisationAccesskey(String uniqueCode);
	
	public String verificationForAuthorisationAccessKey(String uniqueCode,String accesskey);
	
	public EffectedBoothsResponse getInfectedBoothsOfConstituency(Long constituencyId);
	public WSResultVO getLoginFieldDataUser(String uname,String pwd);

}
