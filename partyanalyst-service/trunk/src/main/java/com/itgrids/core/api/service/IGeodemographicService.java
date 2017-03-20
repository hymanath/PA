package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IGeodemographicService {
	public List<SelectOptionVO> getDistricts(Long stateId);
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID);
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID);
	public List<SelectOptionVO> getVillagesForMandalId(Long mandalId);
	public List<SelectOptionVO> getBoothsList(Long panchayatId);

}
