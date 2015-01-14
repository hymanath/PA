package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;

public interface ICadreCommitteeService {
	public String genarateOTP(Long userId, Long mobileNo);
	public String validateOTPForUser(Long userId, Long mobileNo,Long refNo, Long otpNumber);
	public List<String> getAgeRangeDetailsForCadre();
	public List<GenericVO> getAllCasteDetailsForState();
	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId);
	public List<LocationWiseBoothDetailsVO> getLocationsList(Long constituencyId,String level);
}
