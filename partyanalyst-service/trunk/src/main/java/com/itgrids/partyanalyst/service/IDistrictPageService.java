package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IDistrictPageService {

	public List<SelectOptionVO> getConstituenciesForDistrict(Long districtId);
	
	public List<MandalVO>	getMandalsForDistrict(Long districtId);
}
