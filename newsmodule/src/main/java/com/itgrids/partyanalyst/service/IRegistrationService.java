package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IRegistrationService {
	
	public Boolean saveDataIntoUser(final RegistrationVO regVo,final String userType);
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName);
	
	public List<SelectOptionVO> getAllRoles();
	
	public List<SelectOptionVO> getAllDepartments();
}
