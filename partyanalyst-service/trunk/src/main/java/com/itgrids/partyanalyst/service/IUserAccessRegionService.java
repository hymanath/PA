package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.UserAccessRegionVO;

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
	
}
