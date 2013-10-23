package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMobileService {
	
 //public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path);
 public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId,String path,final RegistrationVO reVo);
 
 public List<SelectOptionVO> getConstituencyList();
 
 public ResultStatus saveUserData(final RegistrationVO registrationVO);

}
