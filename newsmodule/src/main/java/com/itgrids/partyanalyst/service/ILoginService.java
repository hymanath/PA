package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ILoginService {
	
	public List<String> getDefaultEntitlements(String defaultEntitlementsGroup);
	
	public Boolean checkForRegionToViewReport(RegistrationVO registrationVO, final String regionType, Long regionId);
	
	public ResultStatus checkForUserNameAndPassword(String username,String password);
	
	public RegistrationVO getUserByUserNameAndPassword(String username,String password);
	
	public List<SelectOptionVO> getEntitlements(Long roleId);
	
}
