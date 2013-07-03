package com.itgrids.electoralconnect.service;

import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.dto.UserVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IUserService {
	
	public String validateEmail(String emailId);
	
	public RegistrationVO registerUser(UserProfileVO userProfileVO);
	
	public RegistrationVO checkForValidUser(String username,String passward);
	
	public ResultStatus updateUserPassword(String password,Long userId);
	
	public RegistrationVO forgetPasswordService(String username);
	
	public ResultStatus saveComment(Long userId,Long annoncementId,String comment);
}
