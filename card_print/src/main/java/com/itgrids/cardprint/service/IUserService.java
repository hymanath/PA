package com.itgrids.cardprint.service;

import com.itgrids.cardprint.dto.UserVO;

public interface IUserService
{
	public UserVO validateUserLogin(String userName,String password,String uniqueKey);
}
