package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;

public interface IRtcUnionService {
     
	public List<IdNameVO> getDistrictsForState(Long stateId);
	public List<IdNameVO> getConstituenciesForDistrict(Long districtId);
	public List<LocationWiseBoothDetailsVO> getLocationsOfSublevelConstituencyMandal(Long constituencyId, String mandalStr, Long locationLevelId);
	public List<IdNameVO> getAllRTCZones();
	public List<IdNameVO> getRegionsOfZone(Long zoneId);
	public List<IdNameVO> getDepotsOfRegion(Long regionId);
	public List<IdNameVO> getDesignationsOfUnionType(Long uniontypeId);
}
