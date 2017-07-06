package com.itgrids.service;

import com.itgrids.dto.UserVO;

public interface IUserService { 
	
	public UserVO userAuthentication(String userName,String password);
	public String getAssignedSearchIdByTypeId( Long searchLevelId,Long searchLevelValue,String fromPage,String toPage);
	

}
