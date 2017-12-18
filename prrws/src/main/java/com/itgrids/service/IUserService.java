package com.itgrids.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.itgrids.dto.AddressVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.dto.UserVO;

public interface IUserService { 
	
	public UserVO userAuthentication(String userName,String password,HttpServletRequest request);
	public AddressVO getOriginalLocationIdForTempId( Long searchLevelId,String searchLevelValue,String fromPage,String toPage);
	public ResultVO saveFavouriteComponentDtls(IdNameVO inputVO);
	public List<IdNameVO> getFavouriteComponents(IdNameVO inputVO);
	public ResultVO deleteFavouriteComponent(IdNameVO inputVO);
	
	public ResultVO saveFavouriteComponentOrderDtls(IdNameVO inputVO);
	
}
