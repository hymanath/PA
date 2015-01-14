package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;

public interface ICadreCommitteeService {
	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId);
	public List<LocationWiseBoothDetailsVO> getLocationsList(Long constituencyId,String level);
}
