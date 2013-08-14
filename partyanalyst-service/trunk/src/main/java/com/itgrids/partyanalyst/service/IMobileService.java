package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMobileService {
	
 public ResultStatus createDataDumpFileForSelectedConstituency(Long constituencyId);
 
 public List<SelectOptionVO> getConstituencyList();

}
