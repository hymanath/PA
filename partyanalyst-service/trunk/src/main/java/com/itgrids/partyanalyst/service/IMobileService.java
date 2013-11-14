package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMobileService {
	
 //public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path);
 public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo);
 
 public List<SelectOptionVO> getConstituencyList();
 
 public ResultStatus saveUserData(final RegistrationVO registrationVO);
 
	public ResultStatus checkAuthenticateUserAndUpdateLastAuthorisedTime(String userId,String macAddressId);
	
	public ResultStatus sendSmsToMobileAppUser(String mobileNo,Long mobileAppuserId,String accessKey,Long userID);
	
	public List<SelectOptionVO> getMobileAppUsers();

}
