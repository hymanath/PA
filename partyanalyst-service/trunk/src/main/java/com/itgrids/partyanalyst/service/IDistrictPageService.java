package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.MandalVO;
public interface IDistrictPageService {

	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear);
	
	public List<MandalVO>	getMandalsForDistrict(Long districtId);
	
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId);
}
