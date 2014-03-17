package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;

public interface IMobileService {
	
 //public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path);
 public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo);
 
 public List<SelectOptionVO> getConstituencyList();
 
 public ResultStatus saveUserData(final RegistrationVO registrationVO);
 
	public ResultStatus checkAuthenticateUserAndUpdateLastAuthorisedTime(String userId,String macAddressId);
	
	public ResultStatus sendSmsToMobileAppUser(String mobileNo,Long mobileAppuserId,String accessKey,Long userID,String type);
	
	public List<SelectOptionVO> getMobileAppUsers();
	
 	public List<RegistrationVO> getMobileAppUserDetails();
 	
	public ResultStatus enableOrdisableAccessByUniqueCode(List<Long> uniqueCodes,String type);
	
	public List<RegistrationVO> getMobileAppUserDetailInfo(Long userId);
	
	public Long saveSuperAdminInfoInMobileAppUser(String uname,String pwd,String uniqueCode);
	
  	public List<SelectOptionVO> getSuperAdminMobileAppUsers();
  	
  	public List<SelectOptionVO> getPingDetails(Long mobileAppUserId);
  	
	public List<RegistrationVO> getMobileAppUserPopulateData(Long userId);
  	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo);

	
	public ResultStatus createDataDumpFileForAConstituency(RegistrationVO reVo);
	
	public ResultStatus createDataDumpFileForAParliamnetConstituency(RegistrationVO reVo);
	public List<SelectOptionVO> getPCConstituencyList();
	
	public String replaceSpecialChars(String str);
	
	public ResultStatus getMobileAppLastAuthorisedTime();
}
