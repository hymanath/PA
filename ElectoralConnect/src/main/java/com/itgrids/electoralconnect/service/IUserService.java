package com.itgrids.electoralconnect.service;

import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.electoralconnect.dto.UserVO;

public interface IUserService {
	public String validateEmail(String emailId);
	public UserVO registerUser(UserProfileVO userProfileVO);
}
