package com.itgrids.service;

import com.itgrids.dto.UserVO;

public interface IUserService { 
	
	public UserVO userAuthentication(String userName,String password);

}
