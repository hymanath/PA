package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserAccessRegionVO;
import com.itgrids.partyanalyst.dto.UserDetailsInfoVO;

public interface IUserAccessRegionService {

	public UserAccessRegionVO getAccessDetailsByUserId(Long userId);
	
	public UserAccessRegionVO getCountryDetails();
	
	public UserAccessRegionVO getStateDetailForDistrictAndAssConstituency(Long userId);
	
	public UserAccessRegionVO getDistrictDetailsByStateIdUserId(Long stateId,Long userId);
	
	public UserAccessRegionVO getCountryDetailsByUserId(Long userId);
	
	 public UserAccessRegionVO getStateDetailsByUserId(Long userId);
	 
	 public UserAccessRegionVO getAssemblyConsDetailsByStateIdUserId(Long stateId,Long userId);
	 
	 public UserAccessRegionVO getParliConsDetailsByUserId(Long userId);
	 
	 public UserAccessRegionVO saveUserCountryAccessDetail(Long userId,String countryIds);
	 
	 public UserAccessRegionVO saveUserStateAccessDetail(Long userId,String stateIds);
	 
	 public UserAccessRegionVO saveUserDistrictAccessDetail(Long userId,Long stateId,String districtIds);
	 
	 public UserAccessRegionVO saveUserAssemblyConstituencyAccessDetail(Long userId,Long stateId,String constituencyIds);
	 
	 public UserAccessRegionVO saveUserParliamentConstituencyAccessDetail(Long userId,String constituencyIds);
	 
	 public List<SelectOptionVO> getAllRestrictedUsers();
	 
	 public ResultStatus saveRestrictedUser(Long userID);
	 public List<SelectOptionVO> getAllUsers();
	 public ResultStatus saveUserInUserAccessIpAddress(Long userID,String IpAddress);
	  
	 public List<UserDetailsInfoVO> getAllIpAddressForUser(Long userId);
	 
	 public ResultStatus deleteUserIpAddress(List<UserDetailsInfoVO> list);
	
}
