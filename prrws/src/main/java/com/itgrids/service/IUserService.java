package com.itgrids.service;

import com.itgrids.dto.AddressVO;
import com.itgrids.dto.UserVO;

public interface IUserService { 
	
	public UserVO userAuthentication(String userName,String password);
	public AddressVO getOriginalLocationIdForTempId( Long searchLevelId,String searchLevelValue,String fromPage,String toPage);
}
