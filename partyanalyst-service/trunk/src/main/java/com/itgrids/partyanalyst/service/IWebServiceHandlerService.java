package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IWebServiceHandlerService {
	
	public String checkForUserAuthentication(String userName , String passWord);
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
	
	public ResultStatus sendSmsToUser(String uniquecode);
	public ResultStatus updatePassword(String uniqueCode,String pwd,String accessKey);
	
	public String sendVoiceSMS(String uniqueCode,String mobileNos,String audioFilePath);
	
	public List<BasicVO> getUserVoiceRecordedFiles(String uniqueCode);
}
